## Spring Boot For EazyBank UI Project 

### 1. Project Structure
1. Spring Boot 3.2.9
2. Java 17 
3. Spring Security 6.2
4. Spring Data JPA
5. MySql 8.0
6. UI: Angular

### 2. Info 
This Project Is Testing For Spring Security With UI Project. <br/>
This is a project to apply and test what we learned about Spring Security.

### 3. Apply Skills And Problems
1. CORS
2. CSR & CSRF
3. ...

### 4. ETC
Use script.sql For DB Setting. <br/>


### 5. AS I Know
```TEXT
CORS: Cross-Origin Resource Sharing
    - 두 가지의 출처를 의미함.(출처는 URL)
    - 다른 도메인에서 리소스를 요청할때, 브라우저에서 보안상의 이유로 차단하는 것
    - 서버에서 허용하는 도메인을 설정해주면, 해당 도메인에서만 리소스를 요청할 수 있음.
    - 보안공격으로 부터 보호하기 위해 사용되는것.
    - 브라우저의 pre-flight request 를 통해 확인함. 
        - pre-flight request: 실제 요청을 보내기 전에 브라우저가, 서버에게 CORS 에 관하여 요청을 보내는 것. 
    - 해결방법
        1. 서버에서 허용하는 도메인을 설정(@CrossOrigin(origins = "http://localhost:4200"), 또는 @CrossOrigin("*")) in Controller
        2. SecurityConfig 의 FilterChain 에서 설정.
           - 상세하게 설정가능함, HTTP Methods, Domains, Credentials, Headers, MaxAge 등등
    
CSRF: Cross-Site Request Forgery
    - 사용자가 의도하지 않은 요청을 통해 공격하는것.
    - 사용자가 로그인한 상태에서, 악의적인 사이트를 통해 요청을 보내는것.
    - 해결방법
        1. CSRF Token 사용
            - 서버에서 토큰을 생성하여, 사용자에게 전달하고, 사용자는 해당 토큰을 요청시 함께 보내는 방식.
            - Spring Security 에서는 기본적으로 CSRF Token 을 사용함.
        2. SameSite Cookie 설정
            - Cookie 를 전송할때, SameSite 설정을 통해, 요청이 같은 사이트에서만 가능하도록 설정.
            - Strict, Lax, None 설정 가능.
        3. Referrer Policy 설정
            - Referrer Policy 를 설정하여, 요청을 보낼때, Referer 헤더를 제어할 수 있음.
            - no-referrer, no-referrer-when-downgrade, same-origin, origin, strict-origin, origin-when-cross-origin, strict-origin-when-cross-origin, unsafe-url
```