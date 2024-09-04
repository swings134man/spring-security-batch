## Methods Level - 메서드 레벨 보안

Spring Security 에서는 Method level 보안을 지원한다. 이는 메서드 호출 시에 권한을 확인하는 것을 의미한다.
메서드 레벨 보안은 `@PreAuthorize`, `@PostAuthorize`, `@Secured`, `@RolesAllowed` 등의 어노테이션을 사용하여 구현할 수 있다.
WebApplication 이 아닌 경우에도 End Point 보안으로 접근에 대해 제어 할 수 있다.

`@EnableMethodSecurity` 를 통해 적용가능하다. `@Configuration` 으로 Security 설정을 구현한 클래스에 적용한다.
- 해당 어노테이션에는 여러가지의 옵션이 존재한다.
  - prePostEnabled: `@PreAuthorize`, `@PostAuthorize` 어노테이션을 사용할 수 있도록 한다.
  - securedEnabled: `@Secured` 어노테이션을 사용할 수 있도록 한다. (Secured 는 Spring Security 에서 제공하는 어노테이션이다.)
  - jsr250Enabled: `@RolesAllowed` 어노테이션을 사용할 수 있도록 한다. (RolesAllowed 는 JSR-250 스펙에 정의된 어노테이션이다.)

  - @Secured, @RolesAllowed 는 @preAuthorize, @PostAuthorize 보다 제한적이고, 덜 유연하다. 

@PreAuthorize: 
- 메서드 호출 전에 권한을 확인한다.
- SpEL (Spring Expression Language) 을 사용하여 권한을 확인한다.
- 아래와 같은 옵션을 사용할 수 있다
  - ("hasRole('ROLE_USER')") 
  - ("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  - ("hasAuthority('ROLE_USER')")
  - ("hasAuthority('VIEW_DASHBOARD')")
  - ("# username == authentication.principal.username")

@PostAuthorize:
- 메서드 호출 후에 권한을 확인한다.
- 메서드의 리턴값을 확인하여 권한을 확인한다.
- 메서드의 실행을 멈추지는 않는다
- PermissionEvaluator 를 구현하여 메서드 호출 허용에대한 로직을 작성할 수 있다.
- Clinet 로 Return 되는 Object 를 기반으로 확인하려 할때 사용한다.

위 2가지의 메서드는 Spring Security Framework 에서 AOP 를 사용하여 Runtime 시, 메서드의 호출 이전에 인터셉트하여
권한 보유관련 규칙을 정의하고 동작하게한다. 

-----
@PreFilter:
- 메서드에서 request, response 를 모두 필터링을 수행하여
필터링 조건에 걸리는 객체가 있다면, 걸러진다. -> 해당 요소 삭제,,

- @PreFilter("filterObject.contactName != Test") // Test 라는 이름을 가진 Contact 는 필터링한다.
- 다만 Map, List 와 같은 Collection 객체에만 적용이 가능하다.

@PostFilter:
- 메서드의 리턴값을 필터링하여
  필터링 조건에 걸리는 객체가 있다면, 걸러진다.
- @PostFilter("filterObject.contactName != Test") // Test 라는 이름을 가진 Contact 는 필터링한다.
- 마찬가지로 Map, List 와 같은 Collection 객체에만 적용이 가능하다.