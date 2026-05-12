
관련 공식 문서
---
시스템 개요 / 인증
CDN 관련 각종 통계 데이터를 API를 통해 수신할 수 있습니다. 단일 도메인/게시지점(singledomain) 뿐만 아니라 모든 서비스 또는 그룹별(totaldomain) 합산 정보를 제공합니다.
기본 정보
공통 URL
https://openapi.cloudn.co.kr
전체 URL 형식
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/{singledomain|totaldomain}/{Method_Name}
지원 프로토콜
SSL (HTTPS) — REST (HTTP POST)
응답 형식
JSON
최대 조회 기간
6개월 (현재일 기준)
domain_unit 구분
구분
URL 경로
설명
singledomain
/statistics/singledomain/{method}
특정 도메인/게시지점 1개의 통계. domain_name 또는 publishing_point 필수
totaldomain
/statistics/totaldomain/{method}
계정 내 모든 도메인/게시지점 통계 배열 반환. domain_name 불필요
interval 지원 범위
조회 범위
Single Domain
Total Domain
1일 미만
5분 / 1시간
5분 / 1시간
1일 이상 ~ 3일 이하
5분 / 1시간 / 1일
5분 / 1시간 / 1일
3일 초과 ~ 7일 이하
1시간 / 1일
1일
7일 초과 ~ 1달 이하
1시간 / 1일
1일
1달 초과
1일
1일
※ 방문자 수(visitor) / 컨텐츠 정보(contents) / 스토리지 사용량(storage) 통계는 5분 데이터를 지원하지 않습니다.

🔷 Single Domain — 공통 요청 파라미터
구분
Parameter
Required
Description
비고
common
id
Y (필수)
인증 ID
ex) testuser_01
password
택1 필수
인증 Password
password 또는 cloud_key_value 중 1개 필수
ex) cloud_key_value: ap5ik30eys72ample9
cloud_key_value
택1 필수
인증 API Key
data
domain_name
택1 필수
통계 데이터를 요청할 도메인 명
domain_name 또는 publishing_point 중 1개 필수. cache 서비스 subpath 조회 시 domain_name + path 함께 입력
publishing_point
택1 필수
통계 데이터를 요청할 게시지점 명 (live 서비스 전용)
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 도메인 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date
형식: yyyyMMddHHmm / 사용 시 두 값 모두 입력 필수. 없을 시 최근값 제공
end_date
선택
통계 데이터 기간 종료 date
date_interval
선택
통계 데이터 간격
1=5분, 2=1시간, 3=1일(기본값)
3일 이하: 5분/1시간/1일 | 1달 이하: 1시간/1일 | 1달 초과: 1일
🔶 Total Domain — 공통 요청 파라미터
구분
Parameter
Required
Description
비고
common
id
Y (필수)
인증 ID
ex) testuser_01
password
택1 필수
인증 Password
password 또는 cloud_key_value 중 1개 필수
cloud_key_value
택1 필수
인증 API Key
data
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수. 없을 시 일 데이터 제공
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (1=5분, 2=1시간, 3=1일)
3일 이하: 5분/1시간/1일, 7일 이하: 1시간/1일, 7일 초과: 1일
※ totaldomain은 domain_name 입력 불필요. 계정 내 모든 도메인/게시지점 데이터를 배열로 반환합니다.
API 목록
Method Name
설명
singledomain URI
totaldomain URI
transfer
전송량
/statistics/singledomain/transfer
/statistics/totaldomain/transfer
traffic
Traffic (대역폭)
/statistics/singledomain/traffic
/statistics/totaldomain/traffic
requestcount
HTTP 요청 건수
/statistics/singledomain/requestcount
/statistics/totaldomain/requestcount
concurrentcount
동시 접속자 수
/statistics/singledomain/concurrentcount
/statistics/totaldomain/concurrentcount
visitor
방문자 수
/statistics/singledomain/visitor
/statistics/totaldomain/visitor
contents
컨텐츠 정보
/statistics/singledomain/contents
/statistics/totaldomain/contents
hitcount
Hit/Miss count
/statistics/singledomain/hitcount
/statistics/totaldomain/hitcount
isp
ISP 통계
/statistics/singledomain/isp
/statistics/totaldomain/isp
useragentos
User Agent OS
/statistics/singledomain/useragentos
/statistics/totaldomain/useragentos
useragentbrowser
User Agent Browser
/statistics/singledomain/useragentbrowser
/statistics/totaldomain/useragentbrowser
country
국가별 요청 건수
/statistics/singledomain/country
/statistics/totaldomain/country
origintransfer
Origin 전송량
/statistics/singledomain/origintransfer
/statistics/totaldomain/origintransfer
origintraffic
Origin Traffic
/statistics/singledomain/origintraffic
/statistics/totaldomain/origintraffic
originrequestcount
Origin HTTP 요청 건수
/statistics/singledomain/originrequestcount
/statistics/totaldomain/originrequestcount
contentserror
40x/50x 컨텐츠 정보
/statistics/singledomain/contentserror
/statistics/totaldomain/contentserror
httpstatuscount
HTTP Status 정보별 수
/statistics/singledomain/httpstatuscount
/statistics/totaldomain/httpstatuscount
originstatuscount
Origin HTTP Status 정보별 수
/statistics/singledomain/originstatuscount
/statistics/totaldomain/originstatuscount
storage
스토리지 사용량
/statistics/singledomain/storage
/statistics/totaldomain/storage
groupsum/transfer
/statistics/groupsum/transfer
groupsum/traffic
/statistics/groupsum/traffic
domainlist
/cdnservice/domainapi/domainlist
Method URL 목록 — 서비스별 지원 현황
공통 URL (https://openapi.cloudn.co.kr)에 아래 각 Method별 URI를 합치면 전체 URL이 완성됩니다.
구분
소구분
제공 데이터
URI
Cache
Service
Streaming
Service
Statistics
Single
Domain
전송량
/statistics/singledomain/transfer
O
O
Traffic
/statistics/singledomain/traffic
O
O
HTTP 요청 건수
/statistics/singledomain/requestcount
O

동시 접속자 수
/statistics/singledomain/concurrentcount
O
O
방문자
/statistics/singledomain/visitor
O
O
컨텐츠 정보
/statistics/singledomain/contents
O

40x/50x 컨텐츠 정보
/statistics/singledomain/contentserror
O

HTTP Status 정보별 수
/statistics/singledomain/httpstatuscount
O

Origin HTTP Status 정보별 수
/statistics/singledomain/originstatuscount
O

Hit/Miss count 수
/statistics/singledomain/hitcount
O

ISP
/statistics/singledomain/isp
O
O
User Agent OS
/statistics/singledomain/useragentos
O
O
User Agent browser
/statistics/singledomain/useragentbrowser
O
O
국가별 요청 건수
/statistics/singledomain/country
O
O
Origin 전송량
/statistics/singledomain/origintransfer
O

Origin Traffic
/statistics/singledomain/origintraffic
O

Origin HTTP 요청 건수
/statistics/singledomain/originrequestcount
O

스토리지 사용량
/statistics/singledomain/storage
O

Statistics
Total
Domain
전송량
/statistics/totaldomain/transfer
O
O
Traffic
/statistics/totaldomain/traffic
O
O
HTTP 요청 건수
/statistics/totaldomain/requestcount
O

동시 접속자 수
/statistics/totaldomain/concurrentcount
O
O
방문자
/statistics/totaldomain/visitor
O
O
HTTP Status 정보별 수
/statistics/totaldomain/httpstatuscount
O

Origin HTTP 요청 건수
/statistics/totaldomain/originrequestcount
O

Origin HTTP Status 정보별 수
/statistics/totaldomain/originstatuscount
O

컨텐츠 정보
/statistics/totaldomain/contents
O

40x/50x 컨텐츠 정보
/statistics/totaldomain/contentserror
O

Hit/Miss count 수
/statistics/totaldomain/hitcount
O

ISP
/statistics/totaldomain/isp
O
O
User Agent OS
/statistics/totaldomain/useragentos
O
O
User Agent browser
/statistics/totaldomain/useragentbrowser
O
O
국가별 요청 건수
/statistics/totaldomain/country
O
O
Origin 전송량
/statistics/totaldomain/origintransfer
O

Origin Traffic
/statistics/totaldomain/origintraffic
O

스토리지 사용량
/statistics/totaldomain/storage
O
O
Statistics
Group
Sum
전송량
/statistics/groupsum/transfer
O
O
Traffic
/statistics/groupsum/traffic
O
O
데이터 제공 안내 (주의사항)
※ 기간 설정 시, 금일 이전의 날을 설정해야 합니다.
※ 작일의 데이터 요청 시에는 금일 오전 6:00시 이후에 요청해야 합니다.
※ 요청 기간이 한달이 넘어갈 경우, 시간/일 통계만 제공 가능합니다.
※ 전월 데이터는 익월 오후 12시 이후에 제공 가능합니다.
※ 시간 데이터는 2시간 이전의 데이터까지 제공 가능합니다.
※ 5분 데이터는 10분 전의 데이터까지 제공 가능합니다.
※ singledomain에서 start_date / end_date 없이 요청 시, date_interval 값에 따라 최근 5분/시간/일 데이터를 제공합니다. date_interval도 없는 경우 최근 일 데이터를 제공합니다.
※ totaldomain에서 start_date / end_date / date_interval 모두 없이 요청 시, 최근 일 데이터를 제공합니다.
※ domain_name(도메인) 또는 publishing_point(게시지점) 중 반드시 하나만 설정 (singledomain 전용).
※ 계정에 할당된 도메인/게시지점만 조회 가능합니다.
※ 조회 가능 범위: 1~31일(일 기준) / 1~48시간(시간 기준) / 5~1440분(분 기준).

POST
전송량 (Transfer)
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/transfer
단일 서비스의 전송량 통계를 조회합니다. 시각(yyyyMMddHHmm):byte 형태의 배열로 반환됩니다.
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name
택1
통계 데이터를 요청할 도메인 명
domain_name 또는 publishing_point 중 1개 필수 입력. cache 서비스 subpath 조회 시 domain_name + path 함께 입력
publishing_point
택1
통계 데이터를 요청할 게시지점 명 (live 서비스 전용)
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 도메인 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수. 없을 시 date_interval 값에 따른 최근값 제공
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (1=5분, 2=1시간, 3=1일)
3일 이하: 5분/1시간/1일, 1달 이하: 1시간/1일, 1달 초과: 1일. 없을 시 기본 일데이터 제공
SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name
요청한 도메인 명

publishing_point
요청한 게시지점 명

result_code
결과 코드
부록 참조
transfer
전송량 데이터 배열
시각(yyyyMMddHHmm):byte 형태
result_msg
결과 메시지
ex) success
🔶 Total Domain — 요청 파라미터 (POST JSON)
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/transfer
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수. 없을 시 일 데이터 제공
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (1=5분, 2=1시간, 3=1일)
3일 이하: 5분/1시간/1일, 7일 이하: 1시간/1일, 7일 초과: 1일. 없을 시 일 데이터 제공
※ totaldomain은 domain_name 입력 불필요. 계정 내 모든 도메인/게시지점의 개별 데이터를 배열로 반환합니다.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간

service_name
Open API 서비스 명
ex) cdn
version
버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 전송량 배열
domain_name + transfer 배열
REQUEST / RESPONSE EXAMPLE
🔷 Single DomainRequest (POST JSON)
{
  "common": {
    "id": "testuser_01",
    "cloud_key_value": "ap5ik30eys72ample9"
  },
  "data": {
    "domain_name": "www.example.com",
    "start_date": "202512300000",
    "end_date": "202512310000",
    "date_interval": "2"
  }
}
Copy
Response
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "transfer": [
      {"202512301700": "10241768"},
      {"202512301800": "20487625"}
    ]
  }
}
Copy
🔶 Total DomainRequest (POST JSON)
{
  "common": {
    "id": "testuser_01",
    "cloud_key_value": "ap5ik30eys72ample9"
  },
  "data": {
    "start_date": "202512300000",
    "end_date": "202512310000",
    "date_interval": "3"
  }
}
Copy
Response
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "transfer": [
          {"20251230": "10241768"},
          {"20251231": "20487625"}
        ]
      },
      {
        "domain_name": "www.example2.com",
        "transfer": [
          {"20251230": "5120884"},
          {"20251231": "10244312"}
        ]
      }
    ]
  }
}
Copy

POST
Traffic (대역폭)
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/traffic
단일 서비스의 Traffic(대역폭, 단위: bps) 통계를 조회합니다.
💡 모든 통계 API는 동일한 요청 파라미터 구조를 사용합니다. 위 공통 요청 파라미터 섹션의 공통 파라미터 표를 참조하세요.
SINGLEDOMAIN 응답
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name
요청한 도메인 명

publishing_point
요청한 게시지점 명

result_code
결과 코드
부록 참조
traffic
Traffic 데이터 배열
시각(yyyyMMddHHmm):bps 형태
result_msg
결과 메시지
ex) success
🔶 Total Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/traffic
※ totaldomain 요청 파라미터: domain_name 불필요. 위 공통 요청 파라미터 섹션의 totaldomain 공통 파라미터 표를 참조하세요.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 traffic 배열
domain_name + traffic(일(yyyyMMdd):bps 배열)
RESPONSE EXAMPLE
🔷 Single Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "traffic": [
      {"202512301700": "125829120"},
      {"202512301800": "251658240"}
    ]
  }
}
Copy
🔶 Total Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "traffic": [
          {"20251230": "125829120"},
          {"20251231": "251658240"}
        ]
      },
      {
        "domain_name": "www.example2.com",
        "traffic": [
          {"20251230": "62914560"},
          {"20251231": "104857600"}
        ]
      }
    ]
  }
}
Copy

POST
HTTP 요청 건수 (Request Count)
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/requestcount
단일 서비스의 HTTP 요청 건수 통계를 조회합니다. ※ cache 서비스 전용 (domain_name 필수, publishing_point 미지원).
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name
Y
통계 데이터를 요청할 도메인 명
cache 서비스 전용. live 서비스(publishing_point) 미지원
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수. 없을 시 date_interval에 따른 최근값 제공
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (1=5분, 2=1시간, 3=1일)
3일 이하: 5분/1시간/1일, 1달 이하: 1시간/1일, 1달 초과: 1일. 없을 시 기본 일 데이터 제공
※ HTTP 요청 건수(requestcount)는 cache 서비스 전용 — domain_name 필수, publishing_point 미지원
SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name
요청한 도메인 명

result_code
결과 코드
부록 참조
count
HTTP 요청 건수 데이터 배열
시각(yyyyMMddHHmm):건수 형태
result_msg
결과 메시지
ex) success
🔶 Total Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/requestcount
※ totaldomain 요청 파라미터: domain_name 불필요. 위 공통 요청 파라미터 섹션의 totaldomain 공통 파라미터 표를 참조하세요.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 요청 건수 배열
domain_name + count(일(yyyyMMdd):건수 배열)
RESPONSE EXAMPLE
🔷 Single Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "count": [
      {"202512301700": "45000"},
      {"202512301800": "52300"}
    ]
  }
}
Copy
🔶 Total Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "count": [
          {"20251230": "45000"},
          {"20251231": "52300"}
        ]
      },
      {
        "domain_name": "www.example2.com",
        "count": [
          {"20251230": "23000"},
          {"20251231": "28100"}
        ]
      }
    ]
  }
}
Copy

POST
동시 접속자 수 / 방문자 수
🔷 Single Domain — 동시 접속자 수 (Concurrent)
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/concurrentcount
🔷 Single Domain — 방문자 수 (Visitor)
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/visitor
※ visitor의 경우 5분 데이터 미지원. date_interval: 1달이하=1시간/1일, 1달초과=1일
🔷 동시 접속자 수 (Concurrent) — 요청 파라미터
💡 concurrentcount 요청 파라미터는 공통 파라미터 구조와 동일합니다. 위 공통 요청 파라미터 섹션의 공통 파라미터 표를 참조하세요.
🔷 방문자 수 (Visitor) — 요청 파라미터
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name
택1
통계 데이터를 요청할 도메인 명
domain_name 또는 publishing_point 중 1개 필수 입력
publishing_point
택1
통계 데이터를 요청할 게시지점 명 (live 서비스 전용)
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHH)
형식: yyyyMMddHH (분 단위 미지원). 사용 시 두 값 모두 입력 필수
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHH)
date_interval
선택
통계 데이터 간격 (2=1시간, 3=1일)
1달 이하: 1시간/1일, 1달 초과: 1일. 5분(1) 미지원
※ visitor는 날짜 형식 yyyyMMddHH (분 단위 없음), 5분 데이터 미지원
CONCURRENT SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name / publishing_point
요청한 도메인/게시지점

count
동시 접속자 수 데이터 배열
시각(yyyyMMddHHmm):수 형태
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
VISITOR SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name / publishing_point
요청한 도메인/게시지점

visitor
방문자 수 데이터 배열
시각(yyyyMMddHH):수 형태. ※ 5분 미지원
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
🔶 동시 접속자 수 (Concurrent) — Total Domain 요청 파라미터
💡 concurrentcount totaldomain 요청 파라미터는 공통 totaldomain 파라미터 구조와 동일합니다. 위 공통 요청 파라미터 섹션을 참조하세요.
🔶 방문자 수 (Visitor) — Total Domain 요청 파라미터
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHH)
형식: yyyyMMddHH (분 단위 미지원). 사용 시 두 값 모두 입력 필수
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHH)
date_interval
선택
통계 데이터 간격 (2=1시간, 3=1일)
7일 이하: 1시간/1일, 7일 초과: 1일. 5분(1) 미지원
※ totaldomain은 domain_name 입력 불필요. visitor 날짜 형식 yyyyMMddHH (분 미지원), 5분 데이터 미지원.
🔶 Total Domain — 동시 접속자 수
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/concurrentcount
🔶 Total Domain — 방문자 수
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/visitor
※ visitor totaldomain: date_interval 7일이하=1시간/1일, 7일초과=1일. 5분 미지원
CONCURRENT TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 동시접속 배열
domain_name + count(일(yyyyMMdd):수 배열)
VISITOR TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 방문자 배열
domain_name + visitor(일(yyyyMMdd):수 배열)
RESPONSE EXAMPLE
🔷 Single — Concurrent
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "count": [
      {"202512301700": "1234"},
      {"202512301800": "1567"}
    ]
  }
}
Copy
🔷 Single — Visitor
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "visitor": [
      {"2025123016": "5678"},
      {"2025123017": "6234"}
    ]
  }
}
Copy
🔶 Total — Concurrent
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "count": [
          {"20251230": "1234"},
          {"20251231": "1456"}
        ]
      },
      {
        "domain_name": "www.example2.com",
        "count": [
          {"20251230": "567"},
          {"20251231": "789"}
        ]
      }
    ]
  }
}
Copy
🔶 Total — Visitor
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "visitor": [
          {"20251230": "5678"},
          {"20251231": "6234"}
        ]
      }
    ]
  }
}
Copy

POST
컨텐츠 정보 (Contents)
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/contents
단일 서비스의 컨텐츠별 통계 정보를 조회합니다. 가장 많이 요청된 컨텐츠의 전송량, 요청 건수를 확인할 수 있습니다. ※ 시간/일 데이터만 제공 (5분 미지원), cache 서비스 전용.
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name
Y
통계 데이터를 요청할 도메인 명
cache 서비스 전용. live 서비스(publishing_point) 미지원
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHH)
형식: yyyyMMddHH (분 단위 미지원). 사용 시 두 값 모두 입력 필수
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHH)
date_interval
선택
통계 데이터 간격 (2=1시간, 3=1일)
1달 이하: 1시간/1일, 1달 초과: 1일. 5분(1) 미지원
※ 날짜 형식 yyyyMMddHH (분 단위 없음), 5분 데이터 미지원. cache 서비스 전용
SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
contents[].domain_name
조회 요청 도메인

contents[].contents_info[].contents_name
컨텐츠 명
ex) /sample.txt
contents[].contents_info[].detail.count
컨텐츠 요청 수
ex) 100
contents[].contents_info[].detail.transfer
컨텐츠 전송량 (bytes)
ex) 3498740
🔶 Total Domain — 요청 파라미터
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/contents
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHH)
형식: yyyyMMddHH (분 단위 미지원). 사용 시 두 값 모두 입력 필수
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHH)
date_interval
선택
통계 데이터 간격 (2=1시간, 3=1일)
1일 이하: 1시간/1일, 1일 초과: 1일. 5분(1) 미지원
※ totaldomain은 domain_name 입력 불필요. 날짜 형식 yyyyMMddHH (분 미지원), 5분 데이터 미지원.
※ totaldomain contents: 1일이하=1시간/1일, 1일초과=1일. 5분 미지원
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
contents[]
도메인 수만큼의 배열
singledomain과 동일 구조
RESPONSE EXAMPLE
🔷 Single Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "contents": [
      {
        "domain_name": "www.example.com",
        "contents_info": [
          {
            "contents_name": "/images/banner.jpg",
            "detail": {
              "count": "15234",
              "transfer": "4823456789"
            }
          },
          {
            "contents_name": "/video/intro.mp4",
            "detail": {
              "count": "3421",
              "transfer": "98765432100"
            }
          }
        ]
      }
    ]
  }
}
Copy
🔶 Total Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "contents": [
      {
        "domain_name": "www.example.com",
        "contents_info": [
          {
            "contents_name": "/images/banner.jpg",
            "detail": {
              "count": "15234",
              "transfer": "4823456789"
            }
          }
        ]
      },
      {
        "domain_name": "www.example2.com",
        "contents_info": [
          {
            "contents_name": "/assets/logo.png",
            "detail": {
              "count": "8900",
              "transfer": "2340000000"
            }
          }
        ]
      }
    ]
  }
}
Copy

POST
Hit/Miss Count
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/hitcount
단일 서비스의 캐시 Hit/Miss 수 통계를 조회합니다. 5분/시간/일별 hit count, miss count, total count, hit rate를 제공합니다.
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name / publishing_point
택1
통계 데이터를 요청할 도메인 명 또는 게시지점 명
domain_name 또는 publishing_point 중 1개 필수. cache 서비스: domain_name, live 서비스: publishing_point
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수. 없을 시 date_interval에 따른 최근값 제공
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (1=5분, 2=1시간, 3=1일)
3일 이하: 5분/1시간/1일, 1달 이하: 1시간/1일, 1달 초과: 1일. 없을 시 기본 일 데이터 제공
SINGLEDOMAIN 응답
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name / publishing_point
요청한 도메인 명 또는 게시지점 명

result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
Hit_info
Hit/Miss 데이터 배열
시각: [{miss_count, total_count, hit_rate, hit_count}] 형태
🔶 Total Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/hitcount
※ totaldomain 요청 파라미터: domain_name 불필요. 위 공통 요청 파라미터 섹션의 totaldomain 공통 파라미터 표를 참조하세요.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 Hit/Miss 배열
domain_name + Hit_info
RESPONSE EXAMPLE
🔷 Single Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "Hit_info": {
      "202512301700": [
        {
          "miss_count": 5000,
          "total_count": 50000,
          "hit_rate": 90.0,
          "hit_count": 45000
        }
      ],
      "202512301800": [
        {
          "miss_count": 4700,
          "total_count": 52300,
          "hit_rate": 91.01,
          "hit_count": 47600
        }
      ]
    }
  }
}
Copy
🔶 Total Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "Hit_info": {
          "20251230": [
            {
              "miss_count": 50000,
              "total_count": 500000,
              "hit_rate": 90.0,
              "hit_count": 450000
            }
          ]
        }
      },
      {
        "domain_name": "www.example2.com",
        "Hit_info": {
          "20251230": [
            {
              "miss_count": 25000,
              "total_count": 200000,
              "hit_rate": 87.5,
              "hit_count": 175000
            }
          ]
        }
      }
    ]
  }
}
Copy

POST
ISP / User Agent
🔷 Single Domain — ISP 통계
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/isp
🔷 Single Domain — User Agent OS
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/useragentos
🔷 Single Domain — User Agent Browser
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/useragentbrowser
💡 모든 통계 API는 동일한 요청 파라미터 구조를 사용합니다. 위 공통 요청 파라미터 섹션의 공통 파라미터 표를 참조하세요.
SINGLEDOMAIN 응답 파라미터 — ISP
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name / publishing_point
요청한 도메인 명 또는 게시지점 명

result_code
결과 코드
부록 참조
isp
ISP별 데이터 배열
시각: [{total, SK, KT, LG, undefined}] 형태
result_msg
결과 메시지
ex) success
SINGLEDOMAIN 응답 파라미터 — USER AGENT
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name / publishing_point
요청한 도메인 명 또는 게시지점 명

result_code
결과 코드
부록 참조
user_agent
OS/브라우저별 데이터 배열
시각: [{total, OS/브라우저명:수}] 형태
result_msg
결과 메시지
ex) success
🔶 Total Domain — ISP
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/isp
🔶 Total Domain — User Agent OS
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/useragentos
🔶 Total Domain — User Agent Browser
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/useragentbrowser
※ totaldomain 요청 파라미터: domain_name 불필요. 위 공통 요청 파라미터 섹션의 totaldomain 공통 파라미터 표를 참조하세요.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 배열
domain_name + isp/user_agent 배열
RESPONSE EXAMPLE
🔷 Single — ISP
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "isp": {
      "202512301700": [
        {
          "total": 582,
          "SK": 137,
          "KT": 154,
          "LG": 156,
          "undefined": 135
        }
      ]
    }
  }
}
Copy
🔷 Single — User Agent OS
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "user_agent": {
      "202512301700": [
        {
          "total": 63476,
          "Windows": 25102,
          "Mac OS": 12890,
          "iOS": 10540,
          "Android": 9832,
          "Linux": 3215,
          "ETC": 1897
        }
      ]
    }
  }
}
Copy
🔷 Single — User Agent Browser
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "user_agent": {
      "202512301700": [
        {
          "total": 63476,
          "Chrome": 28235,
          "Safari": 9068,
          "IE11": 8955,
          "Edge": 7261,
          "Firefox": 5402,
          "ETC": 4555
        }
      ]
    }
  }
}
Copy
🔶 Total — ISP
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "isp": {
          "20251230": [
            {"total":582,"SK":137,"KT":154,"LG":156,"undefined":135}
          ]
        }
      },
      {
        "domain_name": "www.example2.com",
        "isp": {
          "20251230": [
            {"total":340,"SK":120,"KT":130,"LG":90}
          ]
        }
      }
    ]
  }
}
Copy
🔶 Total — User Agent OS
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "user_agent": {
          "20251230": [
            {"total":63476,"Windows":25102,"Mac OS":12890,"iOS":10540,"Android":9832,"Linux":3215,"ETC":1897}
          ]
        }
      },
      {
        "domain_name": "www.example2.com",
        "user_agent": {
          "20251230": [
            {"total":28400,"Windows":11200,"Mac OS":6800,"iOS":5100,"Android":3500,"ETC":1800}
          ]
        }
      }
    ]
  }
}
Copy
🔶 Total — User Agent Browser
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "user_agent": {
          "20251230": [
            {"total":63476,"Chrome":28235,"Safari":9068,"IE11":8955,"Edge":7261,"Firefox":5402,"ETC":4555}
          ]
        }
      },
      {
        "domain_name": "www.example2.com",
        "user_agent": {
          "20251230": [
            {"total":28400,"Chrome":12500,"Safari":5200,"Edge":4300,"Firefox":3800,"ETC":2600}
          ]
        }
      }
    ]
  }
}
Copy

POST
국가별 요청 건수 (Country)
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/country
국가별 요청 건수 통계를 조회합니다. 시각별로 국가명:요청건수 형태로 반환됩니다.
💡 모든 통계 API는 동일한 요청 파라미터 구조를 사용합니다. 위 공통 요청 파라미터 섹션의 공통 파라미터 표를 참조하세요.
SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name / publishing_point
요청한 도메인/게시지점

result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
country
국가별 요청 건수 배열
시각: [{total, Korea Rep.:수, Japan:수, ...}] 형태
🔶 Total Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/country
※ totaldomain 요청 파라미터: domain_name 불필요. 위 공통 요청 파라미터 섹션의 totaldomain 공통 파라미터 표를 참조하세요.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 국가 통계 배열
domain_name + country 배열
RESPONSE EXAMPLE
🔷 Single Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "country": {
      "202512301700": [
        {
          "total": 11760,
          "Korea, Rep.": 7549,
          "Japan": 4211
        }
      ]
    }
  }
}
Copy
🔶 Total Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "country": {
          "20251230": [
            {"total":11760,"Korea, Rep.":7549,"Japan":4211}
          ]
        }
      },
      {
        "domain_name": "www.example2.com",
        "country": {
          "20251230": [
            {"total":5830,"Korea, Rep.":4200,"US":1630}
          ]
        }
      }
    ]
  }
}
Copy

POST
Origin 통계
🔷 Single Domain — Origin 전송량
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/origintransfer
🔷 Single Domain — Origin Traffic
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/origintraffic
🔷 Single Domain — Origin HTTP 요청 건수
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/originrequestcount
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name / publishing_point
택1
통계 데이터를 요청할 도메인 명 또는 게시지점 명
domain_name 또는 publishing_point 중 1개 필수. cache 서비스: domain_name, live 서비스: publishing_point
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수. 없을 시 date_interval에 따른 최근값 제공
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (1=5분, 2=1시간, 3=1일)
3일 이하: 5분/1시간/1일, 1달 이하: 1시간/1일, 1달 초과: 1일. 없을 시 기본 일 데이터 제공
SINGLEDOMAIN 응답 파라미터 — ORIGIN TRANSFER
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name / publishing_point
요청한 도메인 명 또는 게시지점 명

result_code
결과 코드
부록 참조
transfer
Origin 전송량 배열 (bytes)
시각(yyyyMMddHHmm):byte 형태
result_msg
결과 메시지
ex) success
SINGLEDOMAIN 응답 파라미터 — ORIGIN TRAFFIC / REQUEST COUNT
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name / publishing_point
요청한 도메인 명 또는 게시지점 명

result_code
결과 코드
부록 참조
traffic
Origin Traffic 배열 (bps)
시각(yyyyMMddHHmm):bps 형태
count
Origin HTTP 요청 건수 배열
시각(yyyyMMddHHmm):count 형태
result_msg
결과 메시지
ex) success
🔶 Total Domain — Origin 전송량
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/origintransfer
🔶 Total Domain — Origin Traffic
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/origintraffic
🔶 Total Domain — Origin HTTP 요청 건수
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/originrequestcount
※ totaldomain 요청 파라미터: domain_name 불필요. 위 공통 요청 파라미터 섹션의 totaldomain 공통 파라미터 표를 참조하세요.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 배열
domain_name + transfer/traffic/count 배열
RESPONSE EXAMPLE
🔷 Single — Origin Transfer
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "transfer": [
      {"202512301700": "524288000"},
      {"202512301800": "1048576000"}
    ]
  }
}
Copy
🔷 Single — Origin Traffic
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "traffic": [
      {"202512301700": "8388608"},
      {"202512301800": "16777216"}
    ]
  }
}
Copy
🔷 Single — Origin HTTP 요청 건수
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "requestcount": [
      {"202512301700": "4820"},
      {"202512301800": "5130"}
    ]
  }
}
Copy
🔶 Total — Origin Transfer
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "transfer": [
          {"20251230": "524288000"},
          {"20251231": "1048576000"}
        ]
      },
      {
        "domain_name": "www.example2.com",
        "transfer": [
          {"20251230": "262144000"},
          {"20251231": "524288000"}
        ]
      }
    ]
  }
}
Copy
🔶 Total — Origin Traffic
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "traffic": [
          {"20251230": "8388608"},
          {"20251231": "16777216"}
        ]
      },
      {
        "domain_name": "www.example2.com",
        "traffic": [
          {"20251230": "4194304"},
          {"20251231": "8388608"}
        ]
      }
    ]
  }
}
Copy
🔶 Total — Origin HTTP 요청 건수
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "domain_name": "www.example.com",
        "requestcount": [
          {"20251230": "4820"},
          {"20251231": "5130"}
        ]
      },
      {
        "domain_name": "www.example2.com",
        "requestcount": [
          {"20251230": "3200"},
          {"20251231": "3580"}
        ]
      }
    ]
  }
}
Copy

POST
40x/50x 컨텐츠 정보 (Contents Error)
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/contentserror
40x/50x 에러가 발생한 컨텐츠별 상태코드 및 건수 통계를 조회합니다. cache 서비스 전용. ※ 5분 데이터 미지원.
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name
Y
통계 데이터를 요청할 도메인 명
cache 서비스 전용. live 서비스(publishing_point) 미지원
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHH)
형식: yyyyMMddHH (분 단위 미지원). 사용 시 두 값 모두 입력 필수
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHH)
date_interval
선택
통계 데이터 간격 (2=1시간, 3=1일)
1달 이하: 1시간/1일, 1달 초과: 1일. 5분(1) 미지원
※ 날짜 형식 yyyyMMddHH (분 단위 없음), 5분 데이터 미지원. cache 서비스 전용
SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
contents[].domain_name
조회 요청 도메인

contents[].contents_info[].detail
시각별 status_code + count 배열
ex) status_code:504, count:1402
🔶 Total Domain — 요청 파라미터
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/contentserror
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHH)
형식: yyyyMMddHH (분 단위 미지원). 사용 시 두 값 모두 입력 필수
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHH)
date_interval
선택
통계 데이터 간격 (2=1시간, 3=1일)
1일 이하: 1시간/1일, 1일 초과: 1일. 5분(1) 미지원
※ totaldomain은 domain_name 입력 불필요. 날짜 형식 yyyyMMddHH (분 미지원), 5분 데이터 미지원.
※ totaldomain contentserror: 1일이하=1시간/1일, 1일초과=1일. 5분 미지원
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
contents[]
도메인 수만큼의 배열
domain_name + contents_info(detail: status_code, count)
RESPONSE EXAMPLE
🔷 Single Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "contents": [
      {
        "domain_name": "www.example.com",
        "contents_info": [
          {
            "contents_name": "test.img",
            "detail": [
              {
                "2025123000": [
                  {"status_code": 504, "count": 1402},
                  {"status_code": 502, "count": 1415}
                ]
              },
              {
                "2025123001": [
                  {"status_code": 504, "count": 1411},
                  {"status_code": 502, "count": 1222}
                ]
              }
            ]
          }
        ]
      }
    ]
  }
}
Copy
🔶 Total Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "contents": [
      {
        "domain_name": "www.example.com",
        "contents_info": [
          {
            "contents_name": "test.img",
            "detail": [
              {
                "20251230": [
                  {"status_code": 504, "count": 1402},
                  {"status_code": 502, "count": 1415}
                ]
              }
            ]
          }
        ]
      },
      {
        "domain_name": "www.example2.com",
        "contents_info": [
          {
            "contents_name": "sample.jpg",
            "detail": [
              {
                "20251230": [
                  {"status_code": 504, "count": 1121},
                  {"status_code": 502, "count": 1456}
                ]
              }
            ]
          }
        ]
      }
    ]
  }
}
Copy

POST
HTTP Status 정보별 수
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/httpstatuscount
HTTP Status 코드별 요청 수 통계를 조회합니다. cache 서비스 전용 (domain_name 필수, publishing_point 미지원).
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name
Y
통계 데이터를 요청할 도메인 명
cache 서비스 전용. live 서비스(publishing_point) 미지원
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수. 없을 시 date_interval에 따른 최근값 제공
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (1=5분, 2=1시간, 3=1일)
3일 이하: 5분/1시간/1일, 1달 이하: 1시간/1일, 1달 초과: 1일. 없을 시 기본 일 데이터 제공
※ HTTP 요청 건수(requestcount)는 cache 서비스 전용 — domain_name 필수, publishing_point 미지원
SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name
요청한 도메인 명

result_code
결과 코드
부록 참조
http_status_code
HTTP Status별 수 데이터 배열
시각: [{status_code, count}] 형태
result_msg
결과 메시지
ex) success
🔶 Total Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/httpstatuscount
※ totaldomain 요청 파라미터: domain_name 불필요. 위 공통 요청 파라미터 섹션의 totaldomain 공통 파라미터 표를 참조하세요.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 HTTP Status 배열
element{domain_name + http_status_code}
RESPONSE EXAMPLE
🔷 Single Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "http_status_code": [
      {
        "202512301500": [
          {"status_code": 504, "count": 56},
          {"status_code": 502, "count": 64},
          {"status_code": 500, "count": 59}
        ]
      },
      {
        "202512301600": [
          {"status_code": 504, "count": 80},
          {"status_code": 302, "count": 56}
        ]
      }
    ]
  }
}
Copy
🔶 Total Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "element": {
          "domain_name": "www.example.com",
          "http_status_code": [
            {
              "20251230": [
                {"status_code": 504, "count": 1473},
                {"status_code": 502, "count": 1420}
              ]
            }
          ]
        }
      }
    ]
  }
}
Copy

POST
Origin HTTP Status 정보별 수
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/originstatuscount
Origin 서버에서 반환하는 HTTP Status 코드별 요청 수 통계를 조회합니다. cache 서비스 전용.
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name
Y
통계 데이터를 요청할 도메인 명
cache 서비스 전용. live 서비스(publishing_point) 미지원
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수. 없을 시 date_interval에 따른 최근값 제공
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (1=5분, 2=1시간, 3=1일)
3일 이하: 5분/1시간/1일, 1달 이하: 1시간/1일, 1달 초과: 1일. 없을 시 기본 일 데이터 제공
※ HTTP 요청 건수(requestcount)는 cache 서비스 전용 — domain_name 필수, publishing_point 미지원
SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name
요청한 도메인 명

result_code
결과 코드
부록 참조
origin_http_status_code
Origin HTTP Status별 수 데이터 배열
시각: [{status_code, count}] 형태
result_msg
결과 메시지
ex) success
🔶 Total Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/originstatuscount
※ totaldomain 요청 파라미터: domain_name 불필요. 위 공통 요청 파라미터 섹션의 totaldomain 공통 파라미터 표를 참조하세요.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 Origin HTTP Status 배열
element{domain_name + origin_http_status_code}
RESPONSE EXAMPLE
🔷 Single Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "origin_http_status_code": [
      {
        "202512301300": [
          {"status_code": 200, "count": 31},
          {"status_code": 404, "count": 19}
        ]
      },
      {
        "202512301400": [
          {"status_code": 200, "count": 28},
          {"status_code": 502, "count": 11}
        ]
      }
    ]
  }
}
Copy
🔶 Total Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "element": {
          "domain_name": "www.example.com",
          "origin_http_status_code": [
            {
              "20251230": [
                {"status_code": 200, "count": 147},
                {"status_code": 201, "count": 142}
              ]
            }
          ]
        }
      }
    ]
  }
}
Copy

POST
스토리지 사용량 (Storage)
🔷 Single Domain
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/singledomain/storage
단일 서비스의 스토리지 사용량(GByte)을 조회합니다. ※ 5분 데이터 미지원.
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
domain_name
택1
통계 데이터를 요청할 도메인 명
domain_name 또는 publishing_point 중 1개 필수 입력
publishing_point
택1
통계 데이터를 요청할 게시지점 명 (live 서비스 전용)
path
선택
통계 데이터를 요청할 path 명 (cache 서비스 전용)
ex) /images/
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (2=1시간, 3=1일)
1달 이하: 1시간/1일, 1달 초과: 1일. 5분(1) 미지원
SINGLEDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
domain_name
요청한 도메인 명

publishing_point
요청한 게시지점 명

result_code
결과 코드
부록 참조
storage_size
스토리지 사용량 데이터 배열 (GByte)
시각(yyyyMMddHHmm):storage_size 형태
result_msg
결과 메시지
ex) success
🔶 Total Domain — 요청 파라미터
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/totaldomain/storage
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHH)
형식: yyyyMMddHH. 사용 시 두 값 모두 입력 필수
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHH)
date_interval
선택
통계 데이터 간격 (2=1시간, 3=1일)
7일 이하: 1시간/1일, 7일 초과: 1일. 5분(1) 미지원
※ totaldomain은 domain_name 입력 불필요. 5분 데이터 미지원.
TOTALDOMAIN 응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
totaldomain[]
도메인별 스토리지 배열
element{domain_name + storage_size}
RESPONSE EXAMPLE
🔷 Single Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "200",
    "result_msg": "success",
    "storage_size": [
      {"2025123006": "1231"},
      {"2025123007": "1231"}
    ]
  }
}
Copy
🔶 Total Domain
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "totaldomain": [
      {
        "element": {
          "domain_name": "www.example.com",
          "storage_size": [
            {"20251230": "1422"},
            {"20251231": "1422"}
          ]
        }
      },
      {
        "element": {
          "domain_name": "www.example2.com",
          "storage_size": [
            {"20251230": "151"},
            {"20251231": "151"}
          ]
        }
      }
    ]
  }
}
Copy

POST
그룹별 합계 (Group Sum)
🔷 그룹별 전송량 합계
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/groupsum/transfer
🔷 그룹별 Traffic 합계
https://openapi.cloudn.co.kr/cdnservice/statisticsapi/statistics/groupsum/traffic
계정 내 모든 도메인/게시지점의 전송량 또는 Traffic(bps)을 서비스 그룹(product_code)별로 합산하여 조회합니다. domain_name 입력 불필요.
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
start_date
선택
통계 데이터 기간 시작 date (yyyyMMddHHmm)
사용 시 두 값 모두 입력 필수. 없을 시 최근 일 데이터 제공
end_date
선택
통계 데이터 기간 종료 date (yyyyMMddHHmm)
date_interval
선택
통계 데이터 간격 (1=5분, 2=1시간, 3=1일)
3일 이하: 5분/1시간/1일, 1달 이하: 1시간/1일, 1달 초과: 1일
※ domain_name 입력 불필요. 계정 내 서비스 그룹별 합산 데이터 반환
응답 파라미터 — GROUPSUM/TRANSFER
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
groupsum[]
그룹별 전송량 배열
element{product_code + transfer(일:byte 배열)}
응답 파라미터 — GROUPSUM/TRAFFIC
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
groupsum[]
그룹별 Traffic 배열 (bps)
element{product_code + traffic(일:bps 배열)}
RESPONSE EXAMPLE
🔷 groupsum/transfer
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "groupsum": [
      {
        "element": {
          "product_code": "download",
          "transfer": [
            {"20251230": "1024768"},
            {"20251231": "20487625"}
          ]
        }
      },
      {
        "element": {
          "product_code": "vod",
          "transfer": [
            {"20251230": "1024768"},
            {"20251231": "20487625"}
          ]
        }
      }
    ]
  }
}
Copy
🔷 groupsum/traffic
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "200",
    "result_msg": "success",
    "groupsum": [
      {
        "element": {
          "product_code": "download",
          "traffic": [
            {"20251230": "1024768"},
            {"20251231": "20487625"}
          ]
        }
      },
      {
        "element": {
          "product_code": "vod",
          "traffic": [
            {"20251230": "1024768"},
            {"20251231": "20487625"}
          ]
        }
      }
    ]
  }
}
Copy

POST
도메인 목록 조회 (Domain List)
https://openapi.cloudn.co.kr/cdnservice/domainapi/domainlist
계정에 할당된 도메인 목록을 조회합니다. ※ 이 API는 statisticsapi가 아닌 domainapi 경로를 사용합니다.
요청 파라미터 (POST JSON)
구분
Parameter
Required
Description
비고
common
action_date
Y
실행 요청시 현재 시간 (ISO 8601)
ex) 2025-11-13T15:19:21+00:00
service_name
Y
Open API 서비스 명
ex) cdn
version
Y
Open API 버전 정보
ex) 1.0.0
id
Y
인증 ID
ex) testuser_01
password
택1
인증 Password
password 또는 cloud_key_value 중 1개 필수 선택
cloud_key_value
택1
인증 API Key
data
action
Y
동작 내용
도메인 목록 조회 시: "domainlist"
※ 통계 API와 달리 common에 action_date, service_name, version이 필수입니다.
응답 파라미터
구분
Parameter
Description
비고
common
action_date
실행 요청시 현재 시간 (ISO 8601)

service_name
Open API 서비스 명
ex) cdn
version
Open API 버전 정보
ex) 1.0.0
data
result_code
결과 코드
부록 참조
result_msg
결과 메시지
ex) success
domainlist
유저 ID에 해당하는 도메인 목록 배열
ex) ["example.com", "example2.com"]
REQUEST / RESPONSE EXAMPLE
Request (POST JSON)
{
  "common": {
    "action_date": "2025-11-13T15:19:21+00:00",
    "version": "1.0.0",
    "service_name": "cdn",
    "id": "testuser_01",
    "password": "aaaa"
  },
  "data": {
    "action": "domainlist"
  }
}
Copy
Response
{
  "common": {
    "service_name": "cdn",
    "action_date": "2025-11-13T12:12:23+00:00",
    "version": "1.0.0"
  },
  "data": {
    "result_msg": "success",
    "domainlist": [
      "example.com",
      "example2.com",
      "example3.com"
    ],
    "result_code": "200"
  }
}
Copy

에러 코드
Statistics Open API 서버의 응답(오류) 코드입니다.
2XX — 성공
코드
내역
비고
200
통계 데이터 요청 응답 완료
CDN 서비스가 정상 처리된 것을 알림
4XX — 실패
코드
내역
비고
401
JSON 파일 형식 오류
JSON 형식이 잘못되었거나 요청 시 데이터 상세 정보 설정이 잘못된 경우
404
데이터 없음
요청한 도메인/게시지점/고객에 대한 시간대 구간의 정보가 하나도 없을 경우
51X — 통계 데이터 오류
코드
내역
비고
511
통계 데이터 처리 오류
통계 데이터 요청에 대한 CDN 서비스 처리가 실패되었음을 알림
52X — USER 정보 오류
코드
내역
비고
520
ID/PW 불일치
인증 정보(ID/Password 또는 cloud_key_value)가 올바르지 않음
에러 응답 예시 (JSON)
// 520 — ID/PW 불일치
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "domain_name": "www.example.com",
    "result_code": "520",
    "result_msg": "invalid_auth_info"
  }
}
Copy
// 401 — JSON 형식 오류
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "401",
    "result_msg": "invalid_json_format"
  }
}
// 404 — 데이터 없음
{
  "common": {
    "action_date": "2025-12-31T09:00:00+00:00",
    "service_name": "cdn",
    "version": "1.0.0"
  },
  "data": {
    "result_code": "404",
    "result_msg": "data_not_found"
  }
}
Copy
💡 에러 발생 시 result_code와 result_msg로 원인을 확인하십시오.

