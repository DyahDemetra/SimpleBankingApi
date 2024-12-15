package ru.bankingservices.testutils;

import ru.bankingservices.model.Account;
import ru.bankingservices.model.User;

public class TestEntityFactory {
    public static Account createAccountForTest(Long id, User user, Double balance) {
        Account account = new Account();
        account.setId(id);
        account.setBalance(balance);
        account.setOwner(user);
        return account;
    }
    public static User createUserForTest(Long id, String username, String password) {
        User user = new User();
        user.setPassword(password);
        user.setId(id);
        user.setUsername(username);
        return user;
    }
}
