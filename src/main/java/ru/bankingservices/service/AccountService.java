package ru.bankingservices.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ru.bankingservices.model.Account;
import ru.bankingservices.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account updateBalance(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not find"));
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }
}
