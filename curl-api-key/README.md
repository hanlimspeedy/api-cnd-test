# CDN Statistics API `curl` Samples (`cloud_key_value`)

These scripts use the request shape that was verified against the live API.

## Files

- `010_verify_account_and_list_available_domains.sh`
- `020_fetch_reported_domain_daily_transfer.sh`
- `030_fetch_reported_domain_daily_traffic.sh`
- `040_analyze_transfer_response_values.sh`
- `050_analyze_traffic_response_values.sh`

## Prepare

1. Use the repo-root `.env` file.
2. Fill line 1 with the API ID and line 2 with the API key.

Expected file format:

```text
your_api_id
your_cloud_key_value
```

## Run

```bash
./010_verify_account_and_list_available_domains.sh
./020_fetch_reported_domain_daily_transfer.sh
./030_fetch_reported_domain_daily_traffic.sh
./040_analyze_transfer_response_values.sh
./050_analyze_traffic_response_values.sh
```

Optional arguments for `020` and `030`:

```bash
./020_fetch_reported_domain_daily_transfer.sh DOMAIN START_DATE END_DATE DATE_INTERVAL
./030_fetch_reported_domain_daily_traffic.sh DOMAIN START_DATE END_DATE DATE_INTERVAL
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
- `040` reads the saved transfer response and prints each date/value plus summary stats.
- `050` reads the saved traffic response and prints each date/value plus summary stats.
