package ru.bankingservices.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import ru.bankingservices.model.Account;
import ru.bankingservices.model.User;
import ru.bankingservices.service.TokenService;
import ru.bankingservices.testutils.TestEntityFactory;

import java.util.Optional;

@DataJpaTest
@Transactional
public class AccountAndUserRepositoryTest {
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public UserRepository userRepository;
    @MockBean
    private TokenService tokenService;
    @BeforeEach
    void setup() {
        Mockito.when(tokenService.validateToken("valid_token")).thenReturn(true);
    }
    @Test
    void testFindById() {
        User user = TestEntityFactory.createUserForTest(1L, "user1", "pass");
        Account account = TestEntityFactory.createAccountForTest(1L, user, 30.0);
        userRepository.save(user);
        accountRepository.save(account);
        Optional<Account> foundAcc = accountRepository.findById(account.getId());
        Assertions.assertTrue(foundAcc.isPresent());
        Assertions.assertEquals("user1", user.getUsername());
        Assertions.assertEquals("pass", user.getPassword());
        Optional<User> foundUser = userRepository.findById(user.getId());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(30.0, account.getBalance());
    }
}
