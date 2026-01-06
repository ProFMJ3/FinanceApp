package com.jules.financeapp.service.impl;

import com.jules.financeapp.entity.Account;
import com.jules.financeapp.entity.Transaction;
import com.jules.financeapp.entity.enums.TransactionType;
import com.jules.financeapp.entity.User;
import com.jules.financeapp.repository.AccountRepository;
import com.jules.financeapp.repository.TransactionRepository;
import com.jules.financeapp.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public void transfer(String fromAccount, String toAccount, BigDecimal amount, User user) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Invalid amount");
        }

        Account source = accountRepository.findByAccountNumber(fromAccount)
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        Account destination = accountRepository.findByAccountNumber(toAccount)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (source.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        source.setBalance(source.getBalance().subtract(amount));
        destination.setBalance(destination.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType(TransactionType.TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setUser(user);
        transaction.setAccount(source);

        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getUserTransactions(User user) {
        return transactionRepository.findByUser(user);
    }
}
