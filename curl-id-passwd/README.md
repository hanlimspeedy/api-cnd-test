# CDN Statistics API `curl` 샘플: ID/Password 방식

ID/Password 인증으로 CDN Statistics API를 호출하는 `curl` 샘플입니다. 고객 전달용 검증에는 API Key 방식을 우선 사용하고, 이 폴더는 ID/Password 방식 비교 또는 내부 확인용으로 사용합니다.

## 파일 구성

| 파일 | 설명 |
| --- | --- |
| `010_verify_account_and_list_available_domains.sh` | 계정 인증과 도메인 목록 조회 |
| `020_fetch_reported_domain_daily_transfer.sh` | 메일에 나온 도메인의 일 단위 전송량 조회 |
| `030_fetch_reported_domain_daily_traffic.sh` | 메일에 나온 도메인의 일 단위 트래픽 조회 |
| `040_analyze_transfer_response_values.sh` | 저장된 전송량 응답에서 실제 값을 추출하고 요약 |
| `050_analyze_traffic_response_values.sh` | 저장된 트래픽 응답에서 실제 값을 추출하고 요약 |

## 준비

`.id-passwd-env.example`를 `.id-passwd-env`로 만들고 실제 값을 입력합니다.

```text
API ID:your_api_id
API PW:your_api_password
```

`.id-passwd-env`는 git ignore 대상입니다.

## 실행

```bash
./010_verify_account_and_list_available_domains.sh
./020_fetch_reported_domain_daily_transfer.sh
./040_analyze_transfer_response_values.sh
./030_fetch_reported_domain_daily_traffic.sh
./050_analyze_traffic_response_values.sh
```

`020`, `030` 스크립트는 조회 조건을 인자로 바꿀 수 있습니다.

```bash
./020_fetch_reported_domain_daily_transfer.sh DOMAIN START_DATE END_DATE DATE_INTERVAL
./030_fetch_reported_domain_daily_traffic.sh DOMAIN START_DATE END_DATE DATE_INTERVAL
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
- 분석 스크립트가 날짜별 실제 값을 출력해야 합니다.

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
- 인증 필드는 `common.id`와 `common.password`를 사용합니다.
- HTTP 상태 코드는 `201`로 내려오더라도, 실제 성공 여부는 JSON 본문의 `result_code`를 기준으로 판단합니다.
