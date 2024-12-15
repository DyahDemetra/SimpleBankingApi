package ru.bankingservices.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bankingservices.dto.AccountRequest;
import ru.bankingservices.model.Account;
import ru.bankingservices.model.User;
import ru.bankingservices.service.AccountService;
import ru.bankingservices.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@Valid @RequestBody AccountRequest request) {
        User owner = userService. findById(request.getOwnerId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Account account = new Account();
        account.setOwner(owner);
        account.setBalance(request.getBalance());
        return accountService.createAccount(account);
    }
    @PatchMapping("/{id}/balance")
    public Account updateBalance(@PathVariable("id") Long id, @RequestBody Double amount){
        return accountService.updateBalance(id, amount);
    }
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> listAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(listAccounts);
    }
}
