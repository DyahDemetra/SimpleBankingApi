package ru.bankingservices.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bankingservices.model.Account;
import ru.bankingservices.model.Transaction;
import ru.bankingservices.service.TransactionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PatchMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public void transfer(@RequestParam("senderAccountId") Long senderAccountId, @RequestParam("recipientAccountId") Long recipientAccountId, @RequestParam("amount") Double amount) {
        transactionService.transfer(senderAccountId, recipientAccountId, amount);
    }
    @GetMapping("/{id}/transactions")
    public List<Transaction> getAccountTransactions(@PathVariable("id") Long id) {
        return transactionService.getTransactionForAccount(id);
    }
}
