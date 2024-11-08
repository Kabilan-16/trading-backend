package com.trading3.trading.repository;

import com.trading3.trading.modal.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawal,Long> {
    List<Withdrawal>findByUserId(Long userId);
}
