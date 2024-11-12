## Bomkey Module - OAtuth 2.0 Authorization Server

### Description
- Using this Module, you can use Authorization Server to Authenticate and Authorize the user.
- This Server provides AT(Access Token), RT(Refresh Token), scope, token_type, expires_in, etc.
- This Server is based on OAuth 2.0 Authorization Server.
---

### Flow
1. Client sends a request to the Authorization Server.
2. Auth Server provides the Login Page.
3. User enters the credentials.
4. Auth Server validates the credentials.
5. Auth Server provides the Authorization Code.
6. Client sends the Authorization Code to the Auth Server.
7. Auth Server provides the Access Token and Refresh Token.

--- 
### Info
> https://docs.spring.io/spring-authorization-server/reference/getting-started.html <br/>
> 해당 문서의 기반한 OAuth2.0 Authorization Server를 구현한 모듈입니다.<br/>
> 기존의 설정에서 변경된 내역은 아래와 같습니다. <br/>
>> 1. User 정보를 DB에서 가져오도록 변경.
>> 2. Client 정보(각 서버)를 DB에서 가져오도록 변경.
>> 3. RSA Key를 DB에서 가져오도록 변경.
>> 4. JWT 토큰에 Claim 추가로직.
>> 
> TEST 는 <a>Flow</a> 의 흐름과 test.http 파일을 참고하여 진행

---
#### ETC
http://localhost:9999/.well-known/openid-configuration
- 해당 URL 접속시, 해당 서버에서 제공하는 메타데이터를 제공함.(OpenID Connect)