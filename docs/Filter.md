## Spring Security - Filter

Filter: 요청에 대해서 처리를 하기 전에 처리하는 역할을 하는 클래스이다. Spring Security 에서는 Filter를 이용하여 인증, 권한 부여, 로깅 등의 작업을 수행한다.
Spring Security 에서는 FilterChain이 가장먼저 실행된다. FilterChain은 여러개의 Filter를 가지고 있으며, 각 Filter는 요청에 대한 처리를 수행한다.

Request -> Filter -> DispatcherServlet -> Controller 순서로 실행됨

Spring Security에서는 FilterChainProxy를 이용하여 여러개의 Filter를 적용할 수 있다.

* 커스텀하여 사용하는 이유?
  * 요구사항이 다양할 수 있고
  * 인증 및 인가의 흐름중에서 특정부분을 하우스키핑 활동을 해야할 수도 있음
  * 또한 추적 보고에도 사용되며
  * 사용자 추적, 로깅 , 암복호화, OTP 등등 과 같은 요구사항을 추가 개발할 수 있음

----

### Security 내장 Filter

- Security 내장 Filter 에 대해서 debugging, console 확인등을 하려면 설정 추가가 필요하다
```Java
// Main Class
@EnableWebSecurity(debug = true)

// in yml - Debugging loging -> Only Dev Profiles
logging:
  level:
    org.springframework.security.web.FilterChainProxy: DEBUG
```

- Security 내장 Filter List
  * WebAsyncManagerIntegrationFilter
  * SecurityContextPersistenceFilter
  * HeaderWriterFilter
  * CsrfFilter
  * LogoutFilter
  * UsernamePasswordAuthenticationFilter
  * DefaultLoginPageGeneratingFilter
  * DefaultLogoutPageGeneratingFilter
  * BasicAuthenticationFilter
  * RequestCacheAwareFilter
  * SecurityContextHolderAwareRequestFilter
  * AnonymousAuthenticationFilter
  * SessionManagementFilter
  * ExceptionTranslationFilter
  * FilterSecurityInterceptor

- FilterChainProxy.java 에서 
  -  Security 의 모든 Filter 를 적용 실행 관리하는 코드가 존재하고, doFilter() 내부에서 실행됨

- Custom Filter 추가 정의
```TEXT
    
- jakarta.servlet.Filter(javax) 인터페이스를 구현하여 Filter 를 정의하고, 사용자 정의에 맞는 추가 작업을 구현한다.
    - doFilter() 내부에 구현하려는 모든 로직 작성
        - 3개의 매개변수 존재함 (ServletRequest, ServletResponse, FilterChain)
        - 유저로 부터 오는 HTTP 요청 및 응답 이고, FilterChain 은 실행되는 Filter 의 집합.
            - 해당 Filter 의 실행이 종료되면 다음 Filter 를 호출실행 하기 위해 FilterChain.doFilter() 를 호출해야함
    
- Security Filter Chain 에 Custom Filter 추가
    - 우선 3가지의 메서드 존재함
    - 보편적으로 매개변수의 1번은 Custom Filter Class 이고, 2번은 Spring boot 내부 Built-in Filter Class 이다.
      빌트인 클래스 기준으로, 커스텀 필터를 앞,뒤, 또는 특정 위치(동일한 위치)에 추가할 수 있다.
        - addFilterBefore(Filter filter, Class<? extends Filter> beforeFilter)
        - addFilterAfter(Filter filter, Class<? extends Filter> afterFilter)
        - addFilterAt(Filter filter, Class<? extends Filter> atFilter)
            - Spring Security Filter 중 하나와 같은 위치에 Custom Filter 를 추가할 수 있다.
            - 다만 같은 위치에 있는 Filter 들은 순서가 중요하다. 기본값으로는 Security Framework 에서 무작위로 실행...
            - ex) 인증이 진행중임을 알리는 email 발송, 인증중인 로깅, 알림, 트래킹 등등

- GenericFilterBean
    - Filter 인터페이스를 구현한 추상 클래스
    - 모든 유형의 필터에 대해 편리하고 좋은 클래스라고 명시되어있음.
    - web.xml 이나 배포설명시, 설정 파라미터, 초기 파라미터, 서블릿 컨텍스트 파라미터의 세부정보를 제공함.
        - 커스텀 필터에서 해당 설정(세부 정보)들에 접근해야 한다면 사용하기 좋은 필터임.
   
        
- OncePerRequestFilter
    - Filter 를 한번만 실행하도록 보장하는 추상 클래스
    - Spring, Spring Security Framework 는 기본적으로 Filter 가 요청당 한번만 실행된다고 보장하지 않는다.
         여러 이유로 Servlet Container 가 Filter 를 여러번 실행할 수 있다.
    - Filter 를 실행하기 전에, 이미 실행되었는지 확인하고, 실행되지 않았다면 실행한다.
    - Filter 를 실행한 후에는, 실행되었다는 플래그를 설정한다.
    - doFilterInternal() 메서드 내부에 비지니스 로직을 작성한다.
    - 이 클래스를 상속받아서 Filter 를 구현하면, Filter 를 한번만 실행하도록 보장할 수 있다.
    - BasicAuthenticationFilter, UsernamePasswordAuthenticationFilter 등등 이 클래스를 상속받아 구현되어있다.

```