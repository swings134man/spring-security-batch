# Spring Security Basic Module

- Spring Boot 3.2.9
- Spring Security 6.2.6

- Easy-Bank 라는 테스트 UI(Angular) 로 테스트 진행 혹은 Postman 으로 진행함.
- 해당 Module 에서는 기본적인 Spring Security 설정 진행 + UI 테스트 진행

## 1. Security Basic
```TEXT
Spring Security 는 Spring 기반의 어플리케이션의 보안(인증, 권한)을 담당하는 프레임워크이다.

흐름은 아래와 같음.
- Request -> Security FilterChain -> AuthenticationManager -> AuthenticationProvider -> UserDetailsManager/UserDetailsService -> UserDetails

- 기본 Security 설정에서, 한번만 인증하는 방식? 
    - 인증이 성공하면, SecurityContextHolder 에 인증 정보를 저장함.
    - 이후, 인증이 필요한 요청이 들어오면, SecurityContextHolder 에 저장된 인증 정보를 사용하여 인증을 처리함.
    - JSESSIONID 라는 값으로 사용자를 식별하고, 브라우저에 Cookie 로 저장함. 
    - 이후, 인증이 필요한 요청이 들어오면, JSESSIONID 를 사용하여 사용자를 식별함.
      즉, 세션값이 같거나, 유효하다면, 자격증명을 하지 않아도 보안관련 처리를 하지 않음.
      
- Security Config Lambda 설정? 
    - Spring Security 6.1, Spring boot 3.1.0 를 시작으로
    (Lambda)DSL 스타일 사용을 권장.
    또한 몇몇 Method 들은 Deprecated 되었음. -> spring Security7 부터는 삭제될 예정.
    
```

> ### Flow 별 클래스
>> Security FilterChain(Authentication Filter) : 요청을 받아서 인증을 처리하는 필터체인
>>> HTTP Request 를 가로채어, 인증 정보 추출, 이를 기반으로 일련의 인증 과정들을 진행함.<br/>
>>> Ex) 사용자명, 비밀번호, 토큰 등을 추출하여 AuthenticationManager 에게 전달<br/>
>>> Filter 는 여러개가 존재할 수 있으며, 순차적으로 실행됨.
>> 
> 
>> AuthenticationManager : 인증을 처리하는 인터페이스
>>> AuthenticationProvider 들을 호출하여 인증을 처리함.<br/>
>>> 인증이 성공하면, Authentication 객체를 생성하여 SecurityContextHolder 에 저장함.<br/>
>>> 인증이 실패하면, AuthenticationException 을 발생시킴.<br/>
>>> 인증을 처리하는 방법은 AuthenticationProvider 에게 위임함.<br/>
>>> ProviderManager 는 AuthenticationProvider 구현체이며, 여러 AuthenticationProvider 들을 관리, 순차적 인증시도
>> 
>
>> AuthenticationProvider : 실제 인증을 처리하는 인터페이스
>>> Authentication(인증) 을 처리하며, 사용자의 자격 증명 검증 </br>
>>> Customizing 하여 보통사용하게 됨.(유저의 인증 방식 정의) </br>
>>> DaoAuthenticationProvider 를 확장함. (DB 기반 사용자 정보 사용 인증)</br>
>>> JwtAuthenticationProvider 는 JWT 토큰을 사용하여 인증함 </br></br>
>>> UserDetailsService, PasswordEncoder 등을 사용하여 사용자 인증 처리하게됨.

<br/><br/>

## 2. Spring Security Class Info

- SpringBootWebSecurityConfiguration: Spring Security 설정을 위한 클래스
    - defaultSecurityFilterChain: 기본적으로 동작하는 SecurityFilter -> 기본적으로 모든 http 요청이 증명되어야함