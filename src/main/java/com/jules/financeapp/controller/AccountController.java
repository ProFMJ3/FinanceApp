package com.jules.financeapp.controller;

import com.jules.financeapp.entity.Account;
import com.jules.financeapp.entity.User;
import com.jules.financeapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public Account createAccount(@AuthenticationPrincipal User user) {
        return accountService.createAccount(user);
    }

    @GetMapping
    public List<Account> myAccounts(@AuthenticationPrincipal User user) {
        return accountService.getUserAccounts(user);
    }

    @PostMapping("/{accountNumber}/deposit")
    public void deposit(
            @PathVariable String accountNumber,
            @RequestParam BigDecimal amount
    ) {
        accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public void withdraw(
            @PathVariable String accountNumber,
            @RequestParam BigDecimal amount
    ) {
        accountService.withdraw(accountNumber, amount);
    }
}
