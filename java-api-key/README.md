# CDN Statistics API Java 샘플: API Key 방식

API Key 인증으로 CDN Statistics API를 호출하는 Java 샘플입니다. 외부 라이브러리 없이 Java 표준 라이브러리만 사용합니다.

## 필요 환경

- Java 17 이상
- 로컬 검증 환경: Java 21

Maven, Gradle, 외부 JAR는 필요하지 않습니다.

## 파일 구성

| 파일 | 설명 |
| --- | --- |
| `CdnStatisticsApiKeySample.java` | API 호출, 응답 저장, 전송량/트래픽 분석을 모두 포함한 단일 Java 파일 |
| `.env.example` | API Key 인증 파일 예시 |
| `.gitignore` | `out/` 결과 파일 제외 |

## 준비

저장소 최상위에 `.env` 파일을 둡니다.

```text
your_api_id
your_cloud_key_value
```

첫 번째 줄은 `common.id`로 전송됩니다.
두 번째 줄은 `common.cloud_key_value`로 전송됩니다.

## 실행

`java-api-key` 폴더에서 실행합니다.

```bash
java CdnStatisticsApiKeySample.java domainlist
java CdnStatisticsApiKeySample.java transfer
java CdnStatisticsApiKeySample.java analyze-transfer
java CdnStatisticsApiKeySample.java traffic
java CdnStatisticsApiKeySample.java analyze-traffic
```

전체 요청과 분석을 한 번에 실행하려면 아래 명령을 사용합니다.

```bash
java CdnStatisticsApiKeySample.java all
```

`transfer`, `traffic` 명령은 조회 조건을 인자로 바꿀 수 있습니다.

```bash
java CdnStatisticsApiKeySample.java transfer DOMAIN START_DATE END_DATE DATE_INTERVAL
java CdnStatisticsApiKeySample.java traffic DOMAIN START_DATE END_DATE DATE_INTERVAL
```

기본값:

| 항목 | 값 |
| --- | --- |
| `DOMAIN` | `spdy-flexg-main.flexgate.co.kr` |
| `START_DATE` | `202604270000` |
| `END_DATE` | `202604272359` |
| `DATE_INTERVAL` | `3` |

## 성공 기준

- API 응답의 `api_response.data.action_result.result_code`가 `200`이어야 합니다.
- 전송량 응답에는 `transfer` 배열이 있어야 합니다.
- 트래픽 응답에는 `traffic` 배열이 있어야 합니다.
- 분석 명령이 날짜별 실제 값을 출력해야 합니다.

검증 시 확인된 값:

```text
transfer 20260427 = 5383917778640 bytes
traffic  20260427 = 1825459657 bps
```

## 출력 파일

원본 응답은 `out/` 아래에 저장됩니다.

```text
out/010_verify_account_and_list_available_domains.json
out/020_fetch_reported_domain_daily_transfer.json
out/030_fetch_reported_domain_daily_traffic.json
```

`out/`은 git ignore 대상입니다.

## 참고

- 요청 본문은 반드시 `api_request`로 감쌉니다.
- 인증 필드는 `common.id`와 `common.cloud_key_value`를 사용합니다.
- Java 코드는 `java.net.http.HttpClient`를 사용합니다.
