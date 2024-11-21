package com.lucas.jwt;

/**
 * Jwt Generator Main
 * - Nimbus JOSE + JWT Library Using Sample Module Main(test)
 * This Main Class Include Sample Code For Generate JWT Token + Validate JWT Token
 *
 * If Using Library, Delete This Class
 *
 * 2024.11.15 Lucas
 */
@Deprecated
public class GeneratorMain {
    public static void main(String[] args) throws Exception{

//        // 1. RSA Key Init(In Product: This Rsa Key Must Be Managed Securely(ex.DB))
//        RsaKeyProvider.initializeKey();
//
//        // 2. Access Token, Refresh Token Generate
//        String accessToken = JwtService.createAccessToken("lucas", "ROLE_USER");
//        String accessTokenTest = JwtService.createAccessTokenForTest("lucas Test", "ROLE_USER");
//        String refreshToken = JwtService.createRefreshToken("lucas");
//
//        // 3. Validate Tokens
//        ValidationResult isValidAccess = JwtValidator.validateToken(accessToken);
//        ValidationResult isValidAccessTest = JwtValidator.validateToken(accessTokenTest);
//        ValidationResult isValidRefresh = JwtValidator.validateToken(refreshToken);
//
//        // 4. Get Claim
//        String testClaim = JwtValidator.getClaim(accessToken, "test");
//        String roleClaim = JwtValidator.getClaim(accessToken, "role");
//
//        // 5. Expire Token Test
//        Thread.sleep(4000);
//        ValidationResult isExpired = JwtValidator.validateToken(accessTokenTest);
//
//
//        // 6. Print Result
//        System.out.println("Access Token : " + accessToken);
//        System.out.println("Refresh Token : " + refreshToken);
//        System.out.println();
//        System.out.println("isValid Access Token: " + isValidAccess.getMessage());
//        System.out.println("isValid Access Token Test: " + isValidAccessTest.getMessage());
//        System.out.println("isValid Refresh Token: " + isValidRefresh.getMessage());
//        System.out.println("Test Claim : " + testClaim);
//        System.out.println("Role Claim : " + roleClaim);
//        System.out.println("------------------ test ------------------");
//        System.out.println("isExpired Access Token Test: " + isExpired.getMessage());
//        System.out.println("isExpired Is : " + isExpired.isValid());
    }
}
