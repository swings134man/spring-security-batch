## OAuth2.0 인증 서버 테스트 GitHub

Spring Boot: 리소스 서버, 클라이언트서버
GitHub: 인증서버(Authorization Server)

### Info 
- 해당 Boot Server 는, Client Server, Resource Server 의 역할을 겸하며
Authorization Server 는 GitHub 을 사용한다.
OAuth2.0 으로 인증 후 리소스 서버에 접근하는 테스트

1. GitHub OAuth2.0 인증 서버 등록
    - GitHub -> Settings -> Developer settings -> OAuth Apps -> New OAuth App
    - Application name, Homepage URL, Authorization callback URL 입력
        - Name: BootOAuth
        - Homepage URL: http://localhost:8080
        - Authorization callback URL: http://localhost:8080
    - Client ID, Client Secret 발급