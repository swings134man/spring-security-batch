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
        - 사용자가 악성 사이트에 접속시(같은 브라우저 창), 사용자에겐 정당한 똑같은 사이트 노출(악성 코드, 링크가 담긴)
        이후 해커가 사용자의 권한으로 서버에 요청을 보냄
    - 사용자의 권한을 이용하여, 악의적인 요청을 보내는것. 즉 정당한 유저가 정당한 요청을 하는지 판별해야함
    - Spring Security 에서는 READ 외에는 기본적으로 허용하지 않음(403 Forbidden Error)
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
   
            
CsrfTokenRequestAttributeHandler: CSRF Token 설정 및 UI 가 CSRF Token 을 받아서 사용할 수 있게 해주는 Class
    - Token 생성, 전달 
    - SecurityConfig 설정
        - 내부의 ignoringRequestMatchers() 에서 CSRF Token 을 사용하지 않을 URL 설정 가능(인증이 필요없는 POST, PUT, DELETE)
        - csrfTokenRepository() 에서 CSRF Token 을 사용할 방식과, Cookie 설정 가능
            - CookieCsrfTokenRepository.withHttpOnlyFalse() -> Csrf Token 을 Cookie 에 저장하고, JS 에서 사용가능하게 함.
            X-XSRF-TOKEN: 헤더에서 해당 이름으로 찾게됨
            XSRF-TOKEN: 쿠키에 해당 Key 로 저장됨.
            withHttpOnlyFalse: JS 에서 사용가능하게 함. UI Framework 에서 사용할때 필요함. UI 에서 읽어야 하기 떄문(JS)
            
        -UI 에게 보내게될 모든 응답에, 첫로그인 이후 헤더와 토큰 값을 보내야함. -> (Filter Class 생성) CsrfCookieFilter
        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class) // CSRF Token 생성후, 전달 되며
        BasicAuthenticationFilter.class 해당 Filter 를 통과해야 로그인 동작 완료됨: 로그인 성공시, CsrfCookieFilter 가 실행됨.
        -> UI 의 interceptor 에서 httpHeaders.append('Authorization', 'Basic ' + window.btoa(this.user.email + ':' + this.user.password));
        해당 코드가 서버로 요청되면, BasicAuthenticationFilter 가 실행됨(Basic 키워드로 username, pwd 를 추출하여 인증진행됨)
```