import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CdnStatisticsApiKeySample {
    private static final String DOMAINLIST_URL = "https://openapi.cloudn.co.kr/cdnservice/domainapi/domainlist";
    private static final String TRANSFER_URL = "https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/transfer";
    private static final String TRAFFIC_URL = "https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/traffic";

    private static final String DEFAULT_DOMAIN = "spdy-flexg-main.flexgate.co.kr";
    private static final String DEFAULT_START_DATE = "202604270000";
    private static final String DEFAULT_END_DATE = "202604272359";
    private static final String DEFAULT_DATE_INTERVAL = "3";

    private static final Path OUT_DIR = Path.of("out");
    private static final DateTimeFormatter ACTION_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");

    public static void main(String[] args) throws Exception {
        String command = args.length == 0 ? "all" : args[0].toLowerCase(Locale.ROOT);
        Credentials credentials = readCredentials();
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        switch (command) {
            case "domainlist" -> requestDomainList(client, credentials);
            case "transfer" -> requestStatistics(client, credentials, "transfer", args);
            case "traffic" -> requestStatistics(client, credentials, "traffic", args);
            case "analyze-transfer" -> analyze("transfer", inputFile(args, "out/020_fetch_reported_domain_daily_transfer.json"));
            case "analyze-traffic" -> analyze("traffic", inputFile(args, "out/030_fetch_reported_domain_daily_traffic.json"));
            case "all" -> {
                requestDomainList(client, credentials);
                requestStatistics(client, credentials, "transfer", new String[] {"transfer"});
                analyze("transfer", Path.of("out/020_fetch_reported_domain_daily_transfer.json"));
                requestStatistics(client, credentials, "traffic", new String[] {"traffic"});
                analyze("traffic", Path.of("out/030_fetch_reported_domain_daily_traffic.json"));
            }
            default -> {
                usage();
                System.exit(2);
            }
        }
    }

    private static void requestDomainList(HttpClient client, Credentials credentials) throws Exception {
        String body = """
                {
                  "api_request": {
                    "common": {
                      "action_date": "%s",
                      "service_name": "cdn",
                      "version": "1.0.0",
                      "id": "%s",
                      "cloud_key_value": "%s"
                    },
                    "data": {
                      "action": "domainlist"
                    }
                  }
                }
                """.formatted(actionDate(), json(credentials.id()), json(credentials.apiKey()));

        Path output = OUT_DIR.resolve("010_verify_account_and_list_available_domains.json");
        ApiResponse response = post(client, DOMAINLIST_URL, body, output);
        printRawResponse(response);
        requireSuccess(response.body(), output);
        System.out.printf("Domain list request succeeded. Response saved to %s.%n", output);
    }

    private static void requestStatistics(
            HttpClient client,
            Credentials credentials,
            String metric,
            String[] args
    ) throws Exception {
        String domain = args.length > 1 ? args[1] : DEFAULT_DOMAIN;
        String startDate = args.length > 2 ? args[2] : DEFAULT_START_DATE;
        String endDate = args.length > 3 ? args[3] : DEFAULT_END_DATE;
        String dateInterval = args.length > 4 ? args[4] : DEFAULT_DATE_INTERVAL;

        String body = """
                {
                  "api_request": {
                    "common": {
                      "action_date": "%s",
                      "service_name": "cdn",
                      "version": "1.0.0",
                      "id": "%s",
                      "cloud_key_value": "%s"
                    },
                    "data": {
                      "domain_name": "%s",
                      "start_date": "%s",
                      "end_date": "%s",
                      "date_interval": "%s"
                    }
                  }
                }
                """.formatted(
                actionDate(),
                json(credentials.id()),
                json(credentials.apiKey()),
                json(domain),
                json(startDate),
                json(endDate),
                json(dateInterval)
        );

        String filePrefix = metric.equals("transfer") ? "020" : "030";
        Path output = OUT_DIR.resolve(filePrefix + "_fetch_reported_domain_daily_" + metric + ".json");
        ApiResponse response = post(client, metric.equals("transfer") ? TRANSFER_URL : TRAFFIC_URL, body, output);
        printRawResponse(response);
        requireSuccess(response.body(), output);

        String responseDomain = findString(response.body(), "domain_name");
        if (!domain.equals(responseDomain)) {
            throw new IllegalStateException("Unexpected domain_name in response. Response saved to " + output);
        }

        if (metricValues(response.body(), metric).isEmpty()) {
            throw new IllegalStateException(metric + " response has no values. Response saved to " + output);
        }

        System.out.printf("%s request succeeded. Response saved to %s.%n", capitalize(metric), output);
    }

    private static ApiResponse post(HttpClient client, String url, String body, Path output) throws Exception {
        Files.createDirectories(output.getParent());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        Files.writeString(output, response.body(), StandardCharsets.UTF_8);
        return new ApiResponse(response.statusCode(), response.body());
    }

    private static void analyze(String metric, Path inputFile) throws IOException {
        if (!Files.isRegularFile(inputFile)) {
            throw new IllegalArgumentException("Missing input file: " + inputFile);
        }

        String body = Files.readString(inputFile, StandardCharsets.UTF_8);
        String resultCode = findString(body, "result_code");
        String resultMsg = findString(body, "result_msg");
        String domain = findString(body, "domain_name");
        List<MetricValue> values = metricValues(body, metric);

        if (!"200".equals(resultCode)) {
            throw new IllegalStateException("%s response is not successful. result_code=%s result_msg=%s"
                    .formatted(capitalize(metric), resultCode, resultMsg));
        }
        if (values.isEmpty()) {
            throw new IllegalStateException(capitalize(metric) + " response has no values.");
        }

        values.sort(Comparator.comparing(MetricValue::date));

        if (metric.equals("transfer")) {
            printTransferAnalysis(domain, resultCode, resultMsg, values);
        } else {
            printTrafficAnalysis(domain, resultCode, resultMsg, values);
        }
    }

    private static void printTransferAnalysis(
            String domain,
            String resultCode,
            String resultMsg,
            List<MetricValue> values
    ) {
        System.out.println("Transfer analysis");
        printHeader(domain, resultCode, resultMsg, values.size());
        System.out.println("date,value_bytes");
        values.forEach(value -> System.out.printf("%s,%s%n", value.date(), value.value()));
        System.out.println();
        System.out.println("summary");
        System.out.printf("first_date=%s%n", values.get(0).date());
        System.out.printf("last_date=%s%n", values.get(values.size() - 1).date());
        System.out.printf("total_bytes=%s%n", sum(values));
        System.out.printf("min_bytes=%s%n", min(values));
        System.out.printf("max_bytes=%s%n", max(values));
        System.out.printf("latest_bytes=%s%n", values.get(values.size() - 1).value());
    }

    private static void printTrafficAnalysis(
            String domain,
            String resultCode,
            String resultMsg,
            List<MetricValue> values
    ) {
        System.out.println("Traffic analysis");
        printHeader(domain, resultCode, resultMsg, values.size());
        System.out.println("date,value_bps");
        values.forEach(value -> System.out.printf("%s,%s%n", value.date(), value.value()));
        System.out.println();
        System.out.println("summary");
        System.out.printf("first_date=%s%n", values.get(0).date());
        System.out.printf("last_date=%s%n", values.get(values.size() - 1).date());
        System.out.printf("avg_bps=%s%n", sum(values).divide(BigInteger.valueOf(values.size())));
        System.out.printf("min_bps=%s%n", min(values));
        System.out.printf("max_bps=%s%n", max(values));
        System.out.printf("latest_bps=%s%n", values.get(values.size() - 1).value());
    }

    private static void printHeader(String domain, String resultCode, String resultMsg, int pointCount) {
        System.out.printf("domain_name=%s%n", domain);
        System.out.printf("result_code=%s%n", resultCode);
        System.out.printf("result_msg=%s%n", resultMsg);
        System.out.printf("points=%d%n%n", pointCount);
    }

    private static void printRawResponse(ApiResponse response) {
        System.out.println(response.body());
        System.out.printf("HTTP_STATUS:%d%n", response.statusCode());
    }

    private static void requireSuccess(String body, Path output) {
        String resultCode = findString(body, "result_code");
        if (!"200".equals(resultCode)) {
            throw new IllegalStateException("API request failed. Response saved to " + output);
        }
    }

    private static Credentials readCredentials() throws IOException {
        Path envFile = envFile();
        List<String> lines = Files.readAllLines(envFile, StandardCharsets.UTF_8).stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .toList();

        if (lines.size() < 2) {
            throw new IllegalArgumentException("Invalid " + envFile + ". Expected line 1: API ID, line 2: cloud_key_value.");
        }
        return new Credentials(lines.get(0), lines.get(1));
    }

    private static Path envFile() {
        String override = System.getenv("CDN_API_ENV");
        if (override != null && !override.isBlank()) {
            return Path.of(override);
        }

        Path current = Path.of(".env");
        if (Files.isRegularFile(current)) {
            return current;
        }

        Path parent = Path.of("..", ".env");
        if (Files.isRegularFile(parent)) {
            return parent;
        }

        throw new IllegalArgumentException("Missing .env. Create it from .env.example with API ID and cloud_key_value.");
    }

    private static String actionDate() {
        return OffsetDateTime.now(ZoneOffset.ofHours(9)).format(ACTION_DATE_FORMAT);
    }

    private static String findString(String body, String key) {
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(body);
        return matcher.find() ? matcher.group(1) : "";
    }

    private static List<MetricValue> metricValues(String body, String metric) {
        Pattern arrayPattern = Pattern.compile("\"" + Pattern.quote(metric) + "\"\\s*:\\s*\\[(.*?)]", Pattern.DOTALL);
        Matcher arrayMatcher = arrayPattern.matcher(body);
        if (!arrayMatcher.find()) {
            return List.of();
        }

        Pattern entryPattern = Pattern.compile("\"(\\d{8,12})\"\\s*:\\s*\"?(\\d+)\"?");
        Matcher entryMatcher = entryPattern.matcher(arrayMatcher.group(1));
        List<MetricValue> values = new ArrayList<>();
        while (entryMatcher.find()) {
            values.add(new MetricValue(entryMatcher.group(1), new BigInteger(entryMatcher.group(2))));
        }
        return values;
    }

    private static BigInteger sum(List<MetricValue> values) {
        return values.stream().map(MetricValue::value).reduce(BigInteger.ZERO, BigInteger::add);
    }

    private static BigInteger min(List<MetricValue> values) {
        return values.stream().map(MetricValue::value).min(Comparator.naturalOrder()).orElse(BigInteger.ZERO);
    }

    private static BigInteger max(List<MetricValue> values) {
        return values.stream().map(MetricValue::value).max(Comparator.naturalOrder()).orElse(BigInteger.ZERO);
    }

    private static Path inputFile(String[] args, String defaultPath) {
        return Path.of(args.length > 1 ? args[1] : defaultPath);
    }

    private static String json(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    private static String capitalize(String value) {
        return value.substring(0, 1).toUpperCase(Locale.ROOT) + value.substring(1);
    }

    private static void usage() {
        System.err.println("Usage:");
        System.err.println("  java CdnStatisticsApiKeySample.java domainlist");
        System.err.println("  java CdnStatisticsApiKeySample.java transfer [DOMAIN START_DATE END_DATE DATE_INTERVAL]");
        System.err.println("  java CdnStatisticsApiKeySample.java traffic [DOMAIN START_DATE END_DATE DATE_INTERVAL]");
        System.err.println("  java CdnStatisticsApiKeySample.java analyze-transfer [RESPONSE_JSON]");
        System.err.println("  java CdnStatisticsApiKeySample.java analyze-traffic [RESPONSE_JSON]");
        System.err.println("  java CdnStatisticsApiKeySample.java all");
    }

    private record Credentials(String id, String apiKey) {
    }

    private record ApiResponse(int statusCode, String body) {
    }

    private record MetricValue(String date, BigInteger value) {
    }
}
