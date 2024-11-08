package com.trading3.trading.service;

import com.trading3.trading.domain.VerificationType;
import com.trading3.trading.modal.ForgetPasswordToken;
import com.trading3.trading.modal.User;

public interface ForgetPasswordService {

    ForgetPasswordToken createToken(User user,
                                    String id,
                                    String otp,
                                    VerificationType verificationType,
                                    String sendTo);
    ForgetPasswordToken findById(String id);

    ForgetPasswordToken findByUser(Long userId);

    void deleteToken(ForgetPasswordToken token);
}
