# Spring Security Basic Module

- Spring Boot 3.2.9
- Spring Security 6.2.6
- MySQL (Home Server, Schema: easybank, user: develop, password: develop12!@)

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

- Authentication type(객체) ?
    - AuthenticationProviders 내부에서 인증이 성공했는지에 대한 여부를 확인함.(객체를 들고있다. Authentication 객체)
    - 또한 ProviderManager 에서도 항상 인증 객체를 처리하고 있음.
        - 인증객체만을 사용하여 사용자의 이름,비밀번호,권한 과 관련된 세부정보를 저장할 수 있음
        - 다만 계정잠금, 만료, 자격증명만료, 활성화 여부 등을 저장할 필요는 없다. 인증이 성공해야지만, 세부 정보를 저장할 수 있기 떄문
    - 따라서 Security 내부로 유저 세부정보를 다시 전달할 필요 없음 -> Authentication 객체를 통해 인증 정보를 전달함.
   
- @EnableWebSecurity: Main Class? 
    - Spring Security 를 사용하기 위한 설정을 추가하는 어노테이션
    - Main Class 에 명시하지 않아도 됨.
    - dependency 에 Security 가 존재하면, Auto 활성화 됨. Boot 없이 사용한다면 설정필요
    
- UserDetailManager: methods? 
    - 해당 인터페이스의 메서드들 user(crud) 관련을 필수로 사용 X -> 필요에 따라 별도로 구현.
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


## 3. Spring Security - User 
```TEXT
- Spring Security User 관련 

* Flow
UserDetailService -> UserDetailsManager -> (InMemoryUserDetailsManager, JdbcUserDetailsManager, LdapUserDetailsManager, JpaUserDetailsManager)
    - 각각의 (Manager)는 UserDetails 를 구현한 객체를 반환함.

UserDetailService: 
    - 사용자 정보를 가져오는 인터페이스 -> 입력받은 유저 정보를 기준으로 사용자 정보를 가져옴.
    - loadUserByUsername() 메소드를 구현해야함. (해당 Method 는 추상 Method 임)
        - 유저이름만 가져오는 이유는: 비밀번호를 불필요하게 네트워크 전송 X, 웹 및 DB 에서 비밀번호를 가져오는 것은 보안상 위험함.
        - Security 에서는, 유저이름을 기준으로 사용자 정보를 가져오고, 나중에 비밀번호를 비교하는 로직을 추가하는 방식.
    - 사용자 정보를 가져오는 방법은 구현체에 따라 다름.
    
UserDetailsManager:
    - UserDetailsService 를 확장하는(extends) 인터페이스
    - 유저 세부 정부 관리함. -> DB, 저장 시스템에서 유저의 세부 정보를 가져오는(로드) 역할, 유저의 세부 정보를 저장하는 역할(save, update, delete, 패스워드 변경)
    - 해당 인터페이스에서 일부 샘플 구현을 제공함. (InMemoryUserDetailsManager, JdbcUserDetailsManager, LdapUserDetailsManager, JpaUserDetailsManager)
    - 자체 인증로직이 있거나, 수동 구현시 -> AuthenticationProvider 를 구현하여 사용자 인증을 처리해야함.
    
UserDetails: 
    - 유저 세부 정보 인터페이스
    - 위의 2가지의 인터페이스에서 사용됨, 또한 모든 상위 클래스, 및 인터페이스에서 사용됨
    - 사용자의 세부 정보를 담고 있는 인터페이스
        - User Class 를 통해 구현됨(implements)
    - getAuthorities(), getPassword(), getUsername(), isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled()
        각각의 메서드들을 통해, 역할기반액세스 메커니즘구현, 패스워드, 유저이름, 계정만료, 계정잠금, 자격증명만료, 활성화 여부를 확인함.
    - sample 또한 제공하고 있음
        -> security.core.userdetails.User 클래스를 사용하여 구현되어있음.
        해당 인터페이스의 Method 들을 갖고 있고 Override 되어있음.
        -> 해당 sample class 를 사용하지 않고 직접 구현해도 됨.(이제까지 보통 직접 구현했었음. 회원관리 로직의 복잡성 때문)
    - setter 가 없고 생성자를 통해 값을 설정함 -> 보안관련문제(setter 사용시 값 재지정 가능성이 있기때문)
    
    ------------------------------------------------------------------------------------------------------
    LDAPUserDetailsManager:
        - LDAP 서버에서 사용자 정보를 가져오는 인터페이스
        - LDAP은 Lightweight Directory Access Protocol 의 약자로, 디렉토리 서비스 프로토콜임.
            - 네트워크를 통해 조직내의 사용자 정보를 검색하고 수정하는 프로토콜
        - Spring-Security-Starter 에는 LDAP 이 없기에 별도의 의존성을 추가해야함.(spring-ldap-core, spring-security-ldap)
        - Spring Security 에서는 LDAP 서버에서 사용자 정보를 가져오는 방법을 제공함. (LdapUserDetailsManager)
        - SecurityConfig 에서 설정을 추가하여 사용할 수 있음.(@Bean return LdapUserDetailsManager 객체)
        - 특정 조직이 아니면, 잘 사용되지 않음.
    
    InMemoryUserDetailsManager:
        - 메모리에 사용자 정보를 저장하는 인터페이스
        - 사용자 정보를 메모리에 저장하고, 사용자 정보를 가져오는 역할을 함.
        - 사용자 정보를 저장하는 방법은 구현체에 따라 다름.
    
    JdbcUserDetailsManager:
        - JDBC 를 사용하여 사용자 정보를 가져오는 인터페이스
        - JDBC 를 사용하여 사용자 정보를 가져오는 방법을 제공함.
        - DataSource 를 사용하여 DB 에서 사용자 정보를 가져옴.
        - DB 에서 사용자 정보를 가져올 때 어떻게 가져오는지?
            - table 구조, 컬럼명 등등 
            - Security 에서 DB, Table, Column 등등 구조를 설계해놓고 해당 클래스에 구현해놨음.(query)
        - users.ddl 파일안에 필수 table, column 과 같은 생성정보가 존재하고, 해당 파일을 참조함
        

```