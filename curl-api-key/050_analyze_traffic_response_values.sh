#!/usr/bin/env bash

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
INPUT_FILE="${1:-${SCRIPT_DIR}/out/030_fetch_reported_domain_daily_traffic.json}"

if [[ ! -f "${INPUT_FILE}" ]]; then
  echo "Missing input file: ${INPUT_FILE}" >&2
  exit 1
fi

RESULT_CODE="$(jq -r '.api_response.data.action_result.result_code // empty' "${INPUT_FILE}")"
RESULT_MSG="$(jq -r '.api_response.data.action_result.result_msg // empty' "${INPUT_FILE}")"
DOMAIN_NAME="$(jq -r '.api_response.data.action_result.domain_name // empty' "${INPUT_FILE}")"
POINT_COUNT="$(jq -r '(.api_response.data.action_result.traffic // []) | map(to_entries[]) | length' "${INPUT_FILE}")"

if [[ "${RESULT_CODE}" != "200" ]]; then
  echo "Traffic response is not successful. result_code=${RESULT_CODE} result_msg=${RESULT_MSG}" >&2
  exit 1
fi

if [[ "${POINT_COUNT}" -eq 0 ]]; then
  echo "Traffic response has no values." >&2
  exit 1
fi

echo "Traffic analysis"
echo "domain_name=${DOMAIN_NAME}"
echo "result_code=${RESULT_CODE}"
echo "result_msg=${RESULT_MSG}"
echo "points=${POINT_COUNT}"
echo
echo "date,value_bps"
jq -r '.api_response.data.action_result.traffic[] | to_entries[] | "\(.key),\(.value)"' "${INPUT_FILE}"
echo
echo "summary"
jq -r '
  (.api_response.data.action_result.traffic // [])
  | map(to_entries[])
  | map(.value |= tonumber) as $points
  | "first_date=\($points[0].key)\n" +
    "last_date=\($points[-1].key)\n" +
    "avg_bps=\((($points | map(.value) | add) / ($points | length)) | floor)\n" +
    "min_bps=\(($points | map(.value) | min))\n" +
    "max_bps=\(($points | map(.value) | max))\n" +
    "latest_bps=\($points[-1].value)"
' "${INPUT_FILE}"
