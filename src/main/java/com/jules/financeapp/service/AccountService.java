package com.jules.financeapp.service;

import com.jules.financeapp.entity.Account;
import com.jules.financeapp.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    Account createAccount(User user);

    List<Account> getUserAccounts(User user);

    Account getAccountByNumber(String accountNumber);

    void deposit(String accountNumber, BigDecimal amount);

    void withdraw(String accountNumber, BigDecimal amount);
}
