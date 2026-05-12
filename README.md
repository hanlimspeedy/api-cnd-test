# CDN Statistics API 샘플

CloudN CDN Statistics API 호출을 재현하고, 전송량과 트래픽 통계 값이 실제로 내려오는지 검증하기 위한 샘플 저장소입니다.

이 저장소의 샘플은 실제 호출 테스트로 확인된 요청 구조를 기준으로 작성되었습니다. 공식 문서의 필드 설명을 따르되, 실제 서버 호출에는 최상위 `api_request` 래핑이 필요합니다.

## 구성

| 경로 | 설명 |
| --- | --- |
| `curl-api-key/` | API Key 인증 방식의 `curl` 샘플 |
| `java-api-key/` | API Key 인증 방식의 Java 샘플. 외부 라이브러리 없이 Java 표준 라이브러리만 사용 |
| `curl-id-passwd/` | ID/Password 인증 방식의 `curl` 샘플 |
| `api-doc.md` | 테스트에 참고한 공식 API 문서 사본 |

## 필요 환경

- `curl`
- `jq`: `curl` 결과 분석 스크립트에서 사용
- Java 17 이상: `java-api-key/` 실행 시 사용

Java 샘플은 Maven, Gradle, 외부 JAR 없이 실행할 수 있습니다.

## 인증 파일

API Key 방식 샘플은 저장소 최상위의 `.env` 파일을 사용합니다.

```text
your_api_id
your_cloud_key_value
```

첫 번째 줄은 `common.id`로 전송됩니다.
두 번째 줄은 `common.cloud_key_value`로 전송됩니다.

`.env`는 git ignore 대상이며 커밋하지 않습니다.

ID/Password 방식 샘플은 `curl-id-passwd/.id-passwd-env` 파일을 사용합니다.

```text
API ID:your_api_id
API PW:your_api_password
```

이 파일도 git ignore 대상입니다.

## 빠른 실행

API Key 방식 `curl` 샘플:

```bash
cd curl-api-key
./010_verify_account_and_list_available_domains.sh
./020_fetch_reported_domain_daily_transfer.sh
./040_analyze_transfer_response_values.sh
./030_fetch_reported_domain_daily_traffic.sh
./050_analyze_traffic_response_values.sh
```

API Key 방식 Java 샘플:

```bash
cd java-api-key
java CdnStatisticsApiKeySample.java all
```

## 기본 테스트 조건

샘플은 기본적으로 아래 통계 조건을 사용합니다.

| 필드 | 값 |
| --- | --- |
| `domain_name` | `spdy-flexg-main.flexgate.co.kr` |
| `start_date` | `202604270000` |
| `end_date` | `202604272359` |
| `date_interval` | `3` |

성공 기준은 `result_code=200`만이 아닙니다. 응답에 실제 통계 값이 포함되어야 합니다.

검증 시 확인된 값:

```text
transfer 20260427 = 5383917778640 bytes
traffic  20260427 = 1825459657 bps
```

## 요청 구조

실제 서버는 요청 본문 최상위에 `api_request`가 있는 구조로 정상 처리됩니다.

API Key 방식 예시:

```json
{
  "api_request": {
    "common": {
      "action_date": "2026-05-12T10:00:00+09:00",
      "service_name": "cdn",
      "version": "1.0.0",
      "id": "your_api_id",
      "cloud_key_value": "your_cloud_key_value"
    },
    "data": {
      "domain_name": "spdy-flexg-main.flexgate.co.kr",
      "start_date": "202604270000",
      "end_date": "202604272359",
      "date_interval": "3"
    }
  }
}
```

## 출력 파일

각 샘플은 원본 API 응답을 자기 폴더의 `out/` 아래에 저장합니다.

```text
curl-api-key/out/
java-api-key/out/
curl-id-passwd/out/
```

`out/` 디렉터리는 git ignore 대상입니다.

## 보안 주의사항

- `.env`, `.id-passwd-env`, `out/` 디렉터리는 커밋하지 않습니다.
- 인증 정보가 로그, 화면 캡처, git 히스토리에 노출되면 즉시 회전합니다.
- 고객 전달용 검증에는 API Key 방식 샘플을 우선 사용합니다.
