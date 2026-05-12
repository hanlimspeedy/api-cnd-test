#!/usr/bin/env bash

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ENV_FILE="${SCRIPT_DIR}/.id-passwd-env"
OUT_DIR="${SCRIPT_DIR}/out"
OUTPUT_FILE="${OUT_DIR}/020_fetch_reported_domain_daily_transfer.json"
REQUEST_URL="https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/transfer"

mkdir -p "${OUT_DIR}"

if [[ ! -f "${ENV_FILE}" ]]; then
  echo "Missing ${ENV_FILE}. Copy .id-passwd-env.example to .id-passwd-env and fill API ID/API PW." >&2
  exit 1
fi

API_ID="$(sed -n '1s/^API ID://p' "${ENV_FILE}")"
API_PW="$(sed -n '2s/^API PW://p' "${ENV_FILE}")"

if [[ -z "${API_ID}" || -z "${API_PW}" ]]; then
  echo "Invalid ${ENV_FILE}. Expected lines: API ID:<value> and API PW:<value>." >&2
  exit 1
fi

DOMAIN_NAME="${1:-spdy-flexg-main.flexgate.co.kr}"
START_DATE="${2:-202604270000}"
END_DATE="${3:-202604272359}"
DATE_INTERVAL="${4:-3}"
ACTION_DATE="$(date +'%Y-%m-%dT%H:%M:%S+09:00')"
RESPONSE_FILE="$(mktemp)"
trap 'rm -f "${RESPONSE_FILE}"' EXIT

HTTP_STATUS="$(
  curl -sS --location \
    --header 'Content-Type: application/json; charset=UTF-8' \
    --output "${RESPONSE_FILE}" \
    --write-out '%{http_code}' \
    --data @- \
    "${REQUEST_URL}" <<EOF
{
  "api_request": {
    "common": {
      "action_date": "${ACTION_DATE}",
      "service_name": "cdn",
      "version": "1.0.0",
      "id": "${API_ID}",
      "password": "${API_PW}"
    },
    "data": {
      "domain_name": "${DOMAIN_NAME}",
      "start_date": "${START_DATE}",
      "end_date": "${END_DATE}",
      "date_interval": "${DATE_INTERVAL}"
    }
  }
}
EOF
)"

cp "${RESPONSE_FILE}" "${OUTPUT_FILE}"
cat "${RESPONSE_FILE}"
printf '\nHTTP_STATUS:%s\n' "${HTTP_STATUS}"

RESULT_CODE="$(jq -r '.api_response.data.action_result.result_code // empty' "${RESPONSE_FILE}")"
RESPONSE_DOMAIN="$(jq -r '.api_response.data.action_result.domain_name // empty' "${RESPONSE_FILE}")"

if [[ "${RESULT_CODE}" != "200" ]]; then
  echo "API request failed. Response saved to ${OUTPUT_FILE}." >&2
  exit 1
fi

if [[ "${RESPONSE_DOMAIN}" != "${DOMAIN_NAME}" ]]; then
  echo "Unexpected domain_name in response. Response saved to ${OUTPUT_FILE}." >&2
  exit 1
fi

jq -e '.api_response.data.action_result.transfer | arrays' "${RESPONSE_FILE}" >/dev/null

echo "Transfer request succeeded. Response saved to ${OUTPUT_FILE}."
