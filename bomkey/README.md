## Bomkey Module - OAtuth 2.0 Authorization Server

### Description
- Using this Module, you can use Authorization Server to Authenticate and Authorize the user.
- This Server provides AT(Access Token), RT(Refresh Token) 
- <del>This server does not have any information about users, it only provides the token.</del>
---

[//]: # (### Initial settings)

[//]: # (- You Must be registered the <b>server name, info, url, Redirect Url</b>)

[//]: # (  - this information is used to identify the server.)

[//]: # (- if you success the registration, you can get the <b>server key, And Jwt Secret Key.</b>)

[//]: # ()
[//]: # ()
[//]: # (--- )

[//]: # (### Flow )

[//]: # (#### Resource Server Request Parameter List)

[//]: # ()
[//]: # (#### 1. Generate Token)

[//]: # (1. Server Key&#40;String&#41;: Server Key is the key which is used to identify the server.)

[//]: # (2. Client ID&#40;String&#41;)

[//]: # (3. Client Roles: if this Data Empty, default value is "user" Roles)

[//]: # (4. AT, RT Expire time: if this Data Empty, default value is 2 hour&#40;AT&#41;, 1 day&#40;RT&#41;)

[//]: # ()
[//]: # (#### 2. expire Access Token&#40;Refresh Token is not expired&#41;)

[//]: # (1. Server Key&#40;String&#41;)

[//]: # (2. Client ID&#40;String&#41;)

[//]: # (3. Refresh Token&#40;String&#41;)