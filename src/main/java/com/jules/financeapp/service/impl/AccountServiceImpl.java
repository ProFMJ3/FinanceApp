package com.jules.financeapp.service.impl;

import com.jules.financeapp.entity.Account;
import com.jules.financeapp.entity.User;
import com.jules.financeapp.repository.AccountRepository;
import com.jules.financeapp.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(User user) {
        Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);

        return accountRepository.save(account);
    }

    @Override
    public List<Account> getUserAccounts(User user) {
        return accountRepository.findByUser(user);
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Invalid amount");
        }

        Account account = getAccountByNumber(accountNumber);
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);

        if (amount.compareTo(BigDecimal.ZERO) <= 0 ||
                account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
    }
}
