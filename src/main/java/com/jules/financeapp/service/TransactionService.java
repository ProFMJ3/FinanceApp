package com.jules.financeapp.service;

import com.jules.financeapp.entity.Transaction;
import com.jules.financeapp.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    void transfer(
            String fromAccount,
            String toAccount,
            BigDecimal amount,
            User user
    );

    List<Transaction> getUserTransactions(User user);
}
