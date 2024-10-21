# 프로젝트 설명

### order-system : 내부 시스템
- url: http://localhost:8080
- 클라이언트 생성 API: http://localhost:8080/api/clients
    ```
    {
      "name":"humus",
      "taxCode":"123123",
      "serverRootPath":"http://127.0.0.1:9090"
    }
    ```
- 클라이언트의 인터페이스 설정 API: http://localhost:8080/api/clients/{client-id}/interface-settings
    ```
    {
      "clientType":"HUMUS",
      "interfaceTypeList":["CURRENT_ORDER", "ORDER_STATUS"],
      "interfaceApiPathList":["/api/orders/new-orders", "/api/orders/order-info"],
      "interfaceEnableList":["true","true"]
    }
    ```
- spring scheduler 를 사용하여 외부 연동 인터페이스를 통해 외부 시스템과 연동
  - 주문 데이터 목록을 가져와 저장 (SchedulerService.scheduledSearchCurrentOrder() -> ClientInterfaceHelper.getCurrentClientOrderInterface()
  - 주문 데이터 정보를 조회하여 상태 변경 (SchedulerService.scheduledUpdateClientOrderStatus() -> ClientInterfaceHelper.getClientOrderInterface()

### external-interface: 외부 연동 인터페이스
- 내부 시스템 -> 외부 시스템
  - 최근 주문 데이터 목록 조회 API: http://localhost:8081/api/client/current-orders
  - 주문 데이터 정보 조회 API: http://localhost:8081/api/client/order-status
- 외부 시스템 -> 내부 시스템
  - 주문 데이터 목록 조회 API: http://localhost:8081/api/platform/orders
- 클래스 설명
  - DTOAdapter
    - 데이터 전송 시에 사용되는 전체 DTO의 공통 필드와 각 클라이언트별 데이터 변환 부분 담당
    - 모든 DTO 에서 상속
  - VOAdapter
    - 응답받은 데이터의 전체 VO의 공통 필드와 각 클라이언트별 공통 검증 부분 또는 각 VO 의 검증 또는 prehandle, posthandle 정의 가능
    - 모든 VO 에서 상속
  - Service
    - interface ClientService <- {각 클라이언트의}ServiceAdapter <- {각 클라이언트의}Service
    - ClientService
      - 클라이언트와의 모든 API 정의
    - ClientServiceProxy
      - 각 클라이언트별 서비스 클래스를 Map 저장한 serviceMap을 가지고 있음
      - selectService 메소드를 사용하여 Controller 에서 서비스 메소드를 호출하였을 때 해당 클라이언트의 서비스를 선택하여 필요한 클라이언트의 서비스 메소드를 호출할 수 있도록 함
    - {Client}Service (ex, HumusService)
      - 클라이언트 서비스 메소드 구현 부분
      - 해당 클라이언트에서 지원하지 않는 서비스인 경우 ClientService 에서 정의해둔 exception 발생
    - {Client}ServiceAdapter
      - 각 클라이언트별 통신 메소드 정의 (sendDtoToClient)
      - 전달받은 VO에 대한 checkValidation, preHandle, postHandle 호출
      - 각 클라이언트 서비스는 해당하는 serviceAdapter 를 상속받음

### paper-company : 예시로 만든 외부 시스템
- 주문 데이터 생성 API: http://localhost:9090/api/orders
    ```
      {
        "orderStatus":"0"
      }
    ```
- 주문 데이터 상태 수정 API:  http://localhost:9090/api/orders/{order-id}
    ```
      {
        "orderStatus":"0"
      }
    ```
- 최근 주문 데이터 목록 조회 API:  http://localhost:9090/api/orders/new-orders
    ```
    {
      "orderNo":"0"
    }
    ```
- 주문 정보 조회 API:  http://localhost:9090/api/orders/order-info
    ```
    {
      "orderNo":"0"
    }
    ```
- 내부 시스템의 주문 정보 목록 조회 API:  http://localhost:9090/api/orders/interface
    ```
    {
      "taxCode":"123123",
      "fromDate":"20241001",
      "toDate":"20241031"
    }
    ```


