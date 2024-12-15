package ru.bankingservices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bankingservices.model.Account;
import ru.bankingservices.model.Transaction;
import ru.bankingservices.repository.AccountRepository;
import ru.bankingservices.repository.TransactionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public void transfer(Long senderAccountId, Long recipientAccountId, Double amount) {
        Account sender = accountRepository.findById(senderAccountId).orElseThrow(() -> new IllegalArgumentException("Sender account not found"));
        Account recipient = accountRepository.findById(recipientAccountId).orElseThrow(() -> new IllegalArgumentException("Recipient account not found"));
        if (sender.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        accountRepository.save(sender);
        accountRepository.save(recipient);

        Transaction transaction = new Transaction();
        transaction.setSenderAccountId(senderAccountId);
        transaction.setRecipientAccountId(recipientAccountId);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionForAccount(Long accountId) {
        return transactionRepository.findBySenderAccountIdOrRecipientAccountId(accountId, accountId);
    }
}
