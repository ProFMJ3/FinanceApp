package com.jules.financeapp.repository;

import com.jules.financeapp.entity.Transaction;
import com.jules.financeapp.entity.Account;
import com.jules.financeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount(Account account);

    List<Transaction> findByUser(User user);
}
