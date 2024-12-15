package ru.bankingservices.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bankingservices.BankingApp;

@SpringBootTest(classes = BankingApp.class)
public class TokenServiceTest {
    @Autowired
    private TokenService tokenService;
    @Test
    void testGenerateValidToken() {
        String username = "user1";
        String token = tokenService.generateToken(username);
        Assertions.assertTrue(tokenService.validateToken(token));
        Assertions.assertEquals(username, tokenService.getUsernameFromToken(token));
    }
    @Test
    void testInvalidToken() {
        Assertions.assertFalse(tokenService.validateToken("invalid_token"));
    }
}
