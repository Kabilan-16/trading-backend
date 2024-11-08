package com.trading3.trading.service;

import com.trading3.trading.domain.VerificationType;
import com.trading3.trading.modal.ForgetPasswordToken;
import com.trading3.trading.modal.User;
import com.trading3.trading.repository.ForgetPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgetPasswordImpl implements ForgetPasswordService {

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @Override
    public ForgetPasswordToken createToken(User user,
                                           String id,
                                           String otp,
                                           VerificationType verificationType,
                                           String sendTo) {
        ForgetPasswordToken token=new ForgetPasswordToken();
        token.setUser(user);
        token.setSendTo(sendTo);
        token.setVerificationType(verificationType);
        token.setOtp(otp);
        token.setId(id);
        return forgetPasswordRepository.save(token);

    }

    @Override
    public ForgetPasswordToken findById(String id) {
        Optional<ForgetPasswordToken>token=forgetPasswordRepository.findById(id);
        return token.orElse(null);
    }

    @Override
    public ForgetPasswordToken findByUser(Long userId) {
        return forgetPasswordRepository.findByUserId(userId);
    }

    @Override
    public void deleteToken(ForgetPasswordToken token) {
       forgetPasswordRepository.delete(token);
    }
}