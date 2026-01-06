package com.jules.financeapp.controller;

import com.jules.financeapp.entity.Transaction;
import com.jules.financeapp.entity.User;
import com.jules.financeapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public void transfer(
            @RequestParam String fromAccount,
            @RequestParam String toAccount,
            @RequestParam BigDecimal amount,
            @AuthenticationPrincipal User user
    ) {
        transactionService.transfer(fromAccount, toAccount, amount, user);
    }

    @GetMapping
    public List<Transaction> myTransactions(@AuthenticationPrincipal User user) {
        return transactionService.getUserTransactions(user);
    }
}
