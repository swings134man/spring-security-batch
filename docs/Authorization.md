## Authorization

### 1. Keywords
Authentication: 인증(사용자 확인) <br/>
Authorization: 인가(권한부여)

- 인증, 인가에 관한 차이를 명확하게 이해, 구분해야함
  - 인증: 사용자가 누구인지 확인하는 것
    - 안전한 인증된 사람만 사용하는 API, 서비스 등을 사용하게 함
    - 유저 세부정보가 필요함(아이디, pw, otp, 자격증명 등등)
  - 인가: 사용자에게 특정한 권한을 부여하는 것
    - 이미 인증된 유저에 대해 인가를 통해 특정한 권한을 부여함, 혹은 역할 부여
    - 일반유저와, 관리자, 매니저, 소유주 등등 권한 부여로 접근레벨에 대해 차별을 부여함
    - 인가에서는 유저의 권한이나, 역할에 대한 정보만을 필요로 함


### 2. Spring Security

Spring Security 에서는 2가지 keyword 로 인가에 관련되어있음
- Authorities: 권한
- Roles: 역할




```TEXT
Authority 관련 Class, interface, Methods

* Spring Security 는 항상 interface 와 그의 구현체를 제공하고 있음

    - GrantedAuthority: 인증된 사용자의 권한을 나타내는 인터페이스, getAuthority() 메소드 구현필요
    - SimpleGrantedAuthority: GrantedAuthority 의 구현체, 생성자에 권한을 넣어 생성
        - SimpleGrantedAuthority(String role) {this.role = role;}

권한과 역할은 보통의 경우 DB 에 저장되어 있으며, 인증중에 호출되는 UserDetails 에서 조회되고
Spring Security 에서 사용하게 됨.
UsernamePasswordAuthenticationToken(class) 이후 getAuthorities() 메소드를 통해 권한을 조회함
 -> 해당 클래스의 생성자에는 (username, pwd, authorities) 를 넣어 생성함.

UserDetails, User 두개의 Class 에 전부 getAuthorities() 메소드가 존재함
 -> UserDetails 에서는 Collection<? extends GrantedAuthority> getAuthorities() 메소드를 구현함
 -> User 에서는 Collection<? extends GrantedAuthority> getAuthorities() 메소드를 구현함
* 즉 Spring Security 에서 2개의 Class 에서는 어떤 Methods 를 사용하더라도 같은 구조를 사용한다는것을 알 수 있음. 


---------------------------
Role 

- ROLE 이란: 
    - 여러 권한(Authority)이나, 작업의 그룹을 나타냄
    - 보통은 ROLE_ 로 시작함
    - 보통의 product 는 권한이 매우 복잡하고, endpoint 가 많기 때문에, 각각 접근할 수 있는 권한을 계속 생성하기 보다는
      ROLE 로 묶어서 사용함

- hasRole(), hasAnyRole(), access() 를 통해 REST API 접근 역할 확인 가능.


```

