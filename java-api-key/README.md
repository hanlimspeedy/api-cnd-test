# CDN Statistics API Java Sample (`cloud_key_value`)

This sample uses only the Java standard library. No external dependency or build tool is required.

## Requirements

- Java 17 or newer
- Java 21 was used for local verification

## Prepare

Use the repo-root `.env` file:

```text
your_api_id
your_cloud_key_value
```

The first line is sent as `common.id`.
The second line is sent as `common.cloud_key_value`.

## Run

Run from this directory:

```bash
java CdnStatisticsApiKeySample.java domainlist
java CdnStatisticsApiKeySample.java transfer
java CdnStatisticsApiKeySample.java analyze-transfer
java CdnStatisticsApiKeySample.java traffic
java CdnStatisticsApiKeySample.java analyze-traffic
```

Run every request and analysis step:

```bash
java CdnStatisticsApiKeySample.java all
```

Optional arguments for `transfer` and `traffic`:

```bash
java CdnStatisticsApiKeySample.java transfer DOMAIN START_DATE END_DATE DATE_INTERVAL
java CdnStatisticsApiKeySample.java traffic DOMAIN START_DATE END_DATE DATE_INTERVAL
```

Defaults:

- `DOMAIN=spdy-flexg-main.flexgate.co.kr`
- `START_DATE=202604270000`
- `END_DATE=202604272359`
- `DATE_INTERVAL=3`

## Notes

- The live API requires the request body to be wrapped with `api_request`.
- Authentication uses `common.id` and `common.cloud_key_value`.
- Responses are saved to `out/*.json`.
- Success is determined by `api_response.data.action_result.result_code == "200"`.
