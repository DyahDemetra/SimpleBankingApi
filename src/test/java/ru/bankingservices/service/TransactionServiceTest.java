package ru.bankingservices.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bankingservices.repository.AccountRepository;
import ru.bankingservices.repository.TransactionRepository;
import ru.bankingservices.testutils.TestEntityFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.bankingservices.model.Account;
import ru.bankingservices.model.User;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testTransferSuccess() {
        User testUser = TestEntityFactory.createUserForTest(1L, "user1", "pass1");
        Account sender = TestEntityFactory.createAccountForTest(1L, testUser, 100.0);
        Account recipient = TestEntityFactory.createAccountForTest(2L, testUser, 20.0);

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(sender));
        Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(recipient));

        transactionService.transfer(1L, 2L, 10.0);

        Assertions.assertEquals(90.0, sender.getBalance());
        Assertions.assertEquals(30.0, recipient.getBalance());

        Mockito.verify(accountRepository).save(sender);
        Mockito.verify(accountRepository).save(recipient);
    }

    @Test
    void testTransferInsufficientFunds() {
        User testUser = TestEntityFactory.createUserForTest(1L, "user1", "pass1");
        Account sender = TestEntityFactory.createAccountForTest(1L, testUser, 10.0);
        Account recipient = TestEntityFactory.createAccountForTest(2L, testUser, 10.0);

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(sender));
        Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(recipient));

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> transactionService.transfer(1L, 2L, 50.0));

        Assertions.assertEquals("Insufficient funds", exception.getMessage());
    }
}
