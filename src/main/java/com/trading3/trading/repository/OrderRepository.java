package com.trading3.trading.repository;

import com.trading3.trading.modal.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
   List<Order> findByUserId(Long userId);
}
