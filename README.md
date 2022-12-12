# Java_NetworkProgramming
NetworkProgramming

1. 간단한 배송관리 서비스 with Java Swing, MySQL
- 해당 프로젝트의ㅡ problem 폴더의 하위폴더에서 실행할 수 있다.
- 클래스 설명와 실행 클래스 설명
  [기본적인 UI를 위함]
  1) Product : order_id(상품 번호), admin_id(관리자 번호), name(상품명), status(배송 상태), created_at(생성 시간)
  2) User : pw(비밀번호)
  3) ProductSystemUI
    기능 : 로그인을 할 수 있다. 로그인을 성공하면 CardLayout을 통해 내가 가지고 있는 Product들을 column으로 보여준다.
          로그인, 종료, 추가, 업데이트, 삭제를 할 수 있다.
          여기서 main을 실행하면 대략적인 모습을 볼 수 있다. (서버와 소통하지 않은 상태)
          ![image](https://user-images.githubusercontent.com/57055730/206972397-a8762e23-18e6-4f72-9758-eff02ab6fde0.png)
  [서버와의 통신을 위함]
  4) HTTPRequestController : 서버에 Request를 하기 위한 요청 함수 API
    기능  
    - POST /login API : setLoginRequest -> 로그인 버튼을 눌렀을 때 요청
    - GET /{학번} : setGetRequest -> 학번을 통해 로그인을 하였을 때, 로그인된 정보에 해당하는 Product 요청
    - POST / : setPostRequest -> 상품 추가를 요청하는 API
    - PUT / : (생략)
    - PATCH / : setPatchRequest -> 상품 수정을 요청하는 API
    - DELETE /{상품id} : setDeleteRequest -> 상품 삭제를 요청하는 DELETE
   5) HTTPResponseController : 서버에서 Response를 하기 위한 응답 함수 API
    기능
    - POST /login 성공 API : setSuccessLoginResponse
    - Get /{학번} : setSuccessGetResponse -> 해당 Product 정보를 반환해서 전달
    - POST / : setSuccessPostResponse -> 상품 추가
    - PATCH / : setSuccessPatchResponse -> 상품 수정
    - DELETE /{상품id} : setSuccessDeleteResponse -> 상품 삭제
    - 실패 : setFailedResponse
   6) ProductSystemServer : 서버의 기능 구현, Thread 10개를 Thread Pool에 두어서 실행한다. 
   ServerSocket을 생성하고 Request가 들어오면 Socket을 생성한다.
    기능 : HTTP 메서드에 따라서 분기처리를 하고 ProductService class에 등록한 함수를 실행한다. 
      여기에 main함수를 두어 서버를 실행할 수 있다.
   7) ProductService : ProductSystemServer에서 동작하기 위한 함수들이 정의되어 있다. 
      (login, getProducts, addProduct, updateProduct, deleteProduct, setDefaultResponse)
        또한, mysql DB 연결도 여기서 실행해준다.
  
  ## 실행방법 : ProductSystemServer 실행(서버 실행) -> MyClientController 실행(Client 실행)
  ### 예시 화면
  ![image](https://user-images.githubusercontent.com/57055730/206975979-39ec23a4-10fd-40cc-81b4-41b9a31eba54.png)
  ![image](https://user-images.githubusercontent.com/57055730/206976006-a4453677-e3d1-4b8e-81e3-1030c11ab881.png)

    
  
