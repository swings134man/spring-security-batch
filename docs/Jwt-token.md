## Jwt Token & Session 

Token 이란? 
```TEXT
Token 은 UUID 형식의 랜덤 문자열 혹은, JWT 토큰의 한 종류일 수 있다.
 
로그인 이후 발행된 토큰은 서버로 보내지며, 서버는 해당 토큰을 검증하여 사용자를 인증한다.
서버는 해당 토큰이 유효한 토큰인지 확인을 거치게 된다.

토큰 사용시 첫 로그인에만 자격증명을 인증, 공유하면된다. 
이후 토큰 공유를 통해 자격증명을 할 수 있다. (추가 인증 X)
또한 MSA 의 경우에도 Token 안에 들어있는 정보로, 인증을 통해 접근이 가능하다.(JSESSIONID 의 경우 이러한 유연성이 없음)

토큰은 수명을 설정하여, 만료시간을 설정할 수 있다. 
보안이 중요한 서비스에는 AT(Access Token) 의 수명을 짧게 하여 처리함

구글의 경우 토큰을 재사용함
-> 구글과 연관된 이메일, 유튜브, 구글맵 같은것들을 토큰으로 자격증명을 유지하고 사용하게함.
-> SSO(Single Sign On) 을 통해 다른 서비스에도 토큰을 공유하여 사용할 수 있게함

```

Spring Boot, Security 에서는 
JSESSIONID, XSRF-TOKEN 을 Token 으로 사용하여, 세션 유지 및, CSRF 방어를 수행하고 있었다. 

1. JSESSIONID: 세션 유지
* 무작위 랜덤 값
* 로그인 성공 이후 생성되는 값, 이후 모든 요청에 대해 전달되어 추가 인증하지 않아도 된다. 
* 해당 토큰은 사이즈가 작은 어플리케이션에 한해 효과적임.
  * 해당 토큰은 유저 데이터를 갖고 있지 않음
  * 브라우저 속 쿠키로 저장됨. 유저 세션과 엮이게 되고, 세션이 유효하다면 브라우저에서 쿠키를 탈취해 악용할 여지가 있음

<br/>

2. XSRF-TOKEN: CSRF 방어
* CsrfTokenRepository 에서 생성된 값
* UI -> server 에 header 안에 넣어서 사용
* CSRF 방어를 위해 사용됨


### JWT Token 

* JWT (Json Web Token) 은 JSON 형식으로 인코딩된 토큰이다.
  * 내부적으로 Header, Payload, Signature 로 구성되어 있다. 각 부분은 마침표(.) 로 구분됨
    * Header: 토큰의 타입과 해싱 알고리즘을 담고 있다.
    * Payload: 토큰에 담을 정보를 담고 있다.
    * Signature: 토큰의 유효성을 검증하기 위한 서명이다.
  * 또한 각각의 데이터는 base64 Encoding 되어있다.
  * Signature 는 옵션이고, 디지털서명과 같은 역할이다, 이는 JWT Token 이 조작되었는지 확인하는데 사용된다
    * SHA256 과 같은 알고리즘의 도움을 받으며, 헤더, 내용, +서버의 비밀키와 함께 사용되어 생성된다. 
    * 즉 헤더, 바디가 조작되는 순간, 서명의 해시값또한 달라지게 되어, 유효한 서명이 되지않는 원리이다.

* JWT With Security Config
  * JWT 를 사용하기 위해서는 SecurityConfig 에서 설정을 해주어야 한다.
  * JSESSIONID 를 사용하지 않고, JWT 를 사용하게 되면, 세션을 유지하지 않아도 된다.
  * .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JSESSIONID Not Used
  * 또한 UI에 JWT 를 전달하고, 서버에서 받을 수 있어야 하기에
    * cors.setExposedHeaders(Arrays.asList("Authorization")); 를 사용한다.

* JWT Token 을 사용하려면 아래와 같은 실행구조가 일어난다
1. 로그인 요청
2. 로그인 성공시 JWT 토큰을 생성하여, 클라이언트에게 전달
3. 이후 클라이언트는 JWT 토큰을 가지고 서버에 요청을 보낼때마다, 헤더에 토큰을 담아서 보낸다. (Authorization: Bearer {token})
4. 서버는 해당 토큰을 검증하여, 사용자를 인증 및 권한 부여하며, 응답을 보낸다.