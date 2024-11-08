package com.trading3.trading.repository;

import com.trading3.trading.modal.Transaction;
import com.trading3.trading.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUser(User user);
}
