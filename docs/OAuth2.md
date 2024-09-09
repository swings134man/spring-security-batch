# OAuth2

> MSA, Mobile Service, 다중 Server 로 동작되고 있는 서비스라면, OAuth2 를 적용하여 <br/>
> 인증 및 권한 부여와 같은, 인증 체계를 구성할 수 있다.

 - 보통의 경우, 보안 로직, 인증, 허가 로직은 서비스의 핵심이며, <br/>
   이러한 로직을 서비스의 핵심 로직과 분리하여, 관리하는 것이 좋다.

 - Google, FaceBook 과 같은 큰 기업들에서는 OAuth2 인증 방식을 통해, 각 서비스들간의 <br/>
   인증 및 권한 부여를 통해, 서비스간의 연동을 가능하게 한다.

<br/>

### OAuth2 Action Flow
```TEXT
1. Client Request : 클라이언트가 인증 서버에 인증 요청
2. Authorization Grant : 인증 서버가 클라이언트에게 인증 코드를 발급
3. Authorization: 클라이언트가 인증 코드를 가지고, 인증 서버에 인증 요청
4. Access Token : 인증 서버가 클라이언트에게 Access Token, Refresh Token 발급
5. Resource Request : 클라이언트가 리소스 서버에 리소스 요청
6. Resource Response : 리소스 서버가 클라이언트에게 리소스 응답
```

### 용어
```TEXT
- Resource Owner : 자원 소유자 (실 사용자)
- Resource Server : 자원 서버(자원 소유자에 대한 정보들을 가지고 있음(리소스, 데이터))
- Client : 클라이언트(제 3 Application)
- Authorization Server : 인증 서버(자원 소유자에 대한 정보를 알고있음): 인증, 허가, 승인 로직 담당
- scope: 클라이언트가 요청하는 자원에 대한 권한: 클라이언트가 원하는 세분화된 허가 
```


### Access Token?
> 하나의 서비스에서 인증 받은후, 연관된 다른 서비스에서 무언가 Action 을 할떄 <br/>
> Access Token 을 통해, Action 에 대한 인증과 권한을 부여받는다.
> !!!! 제한된 허가 !!!! 
> 제 3 Application 이 어떤 행위를 취할 수 있는지 어떤 권한을 가지는지, 어떤 허가를 내리는지 결정할 수 있는 요소
> 제한된 허가를 통해, 제한된 기능만 사용가능하게 할 수 있는 장점 존재

### Refresh Token?
> Access Token 이 만료되었을때, Access Token 을 재발급 받을 수 있는 토큰
> Access Token 은 보통 짧은 시간동안만 유효하며, 만료되었을때, Refresh Token 을 통해, Access Token 을 재발급 받을 수 있다.
>> Access Token 만료(클라이언트 Request) -> Refresh Token 만료 여부 확인 -> 만료X 시 RT 를 통해 AT, RT 를 재발급 <br/>
>> -> 클라이언트에게 전달 및 저장 -> 요청


### OpenId Connect (OIDC)

- OAuth2.0 을 기반으로, 인증을 위한 프로토콜
- AT, ID Token, RT 를 발급 받음 (RT는 무시될 수 있음)
    - ID Token: 사용자 세부사항 정보(권한 관리)
- openid scope 를 통해, OIDC 를 사용할 수 있음
- ID Token 은 JWT 형태로 발급
- 

IAM: Identity Access Management
```TEXT
- IAM: 사용자의 인증 및 권한을 관리하는 서비스
- 정보 식별 접근관리 강화
```