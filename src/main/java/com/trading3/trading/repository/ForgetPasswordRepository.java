package com.trading3.trading.repository;

import com.trading3.trading.modal.ForgetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPasswordToken,String>{
    ForgetPasswordToken findByUserId(Long userId);
}
