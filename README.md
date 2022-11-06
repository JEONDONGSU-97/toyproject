# ToyProject
### Spring boot + JPA를 이용한 남성 전용 쇼핑몰 프로젝트입니다.

**블로그 주소 : https://velog.io/@my_id**

# 개발환경

- Java11
- Spring Boot
- Spring Security
- Junit
- Gradle
- Jpa
- Thymeleaf
- H2 DB
- BootStrap
- JQuery
- Html/css/javaScript

# 사용한 API
- iamport API (결제기능)
- daum API (주소찾기 기능)

# 구현기능
  - 회원관련
    - 로그인/로그아웃
    - 회원가입
    - 주문조회
    - 장바구니 조회
  - 상품관련
    - 카테고리별 상품 조회
    - 장바구니(추가/삭제/결제)
    - 결제(카카오페이)

# ERD
![erd](https://user-images.githubusercontent.com/83935410/200153989-30012277-6c1b-46b1-902b-e5ea6da20c27.png)
    
# 메인 페이지
  - 상단의 **Outer/Top/Bottoms/Shoes**로 카테고리 구분
  - 우측상단의 버튼을 이용해 **로그인/회원가입** 등 다양한 서비스 기능을 이용
  
  ![screencapture-localhost-8080-2022-11-06-13_31_43](https://user-images.githubusercontent.com/83935410/200154327-fcbb4de4-66db-4363-8f83-8eb2d14244b3.png)

# 회원가입 페이지
  - `Springframework.validation`의 `Validator`인터페이스를 구현하여 비밀번호 유효성 검증 로직과 아이디 중복 검증 로직을 구현
  - `javax.validation`의 `@Valid`를 이용하여 회원가입시 객체의 제약 조건을 검증 => 오류시 에러 메시지를 각각 출력 
  
  ![screencapture-localhost-8080-member-signUp-2022-11-06-13_39_04](https://user-images.githubusercontent.com/83935410/200154532-3d273e13-5cff-435b-8aef-ef73c7ce4471.png)
  
# 로그인 페이지
  - **Spring Security**를 이용하여 로그인/로그아웃 기능 구현
  - `springframework.security.web.authentication`의 `AuthenticationFailureHandler`인터페이스를 구현해서 각기 다른 예외에 따라 다른 메시지를 출력
  - 하지만 `AbstractUserDetailsAuthenticationProvider`클래스에서 `UsernameNotFoundException`을 catch하면 `BadCrendentialsException`을 생성하기 때문에 `UsernameNotFoundException`을 이용한 메시지는 사용할 수 없게 됨
  
  
  ![screencapture-localhost-8080-member-login-2022-11-06-13_56_02](https://user-images.githubusercontent.com/83935410/200154933-9608937a-9ad0-4a62-917e-a74800953849.png)
