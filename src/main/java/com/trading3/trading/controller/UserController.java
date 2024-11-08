package com.trading3.trading.controller;

import com.trading3.trading.request.ForgetPasswordTokenRequest;
import com.trading3.trading.domain.VerificationType;
import com.trading3.trading.modal.ForgetPasswordToken;
import com.trading3.trading.modal.User;
import com.trading3.trading.modal.VerificationCode;
import com.trading3.trading.request.ResetPasswordRequest;
import com.trading3.trading.response.ApiResponse;
import com.trading3.trading.response.AuthResponse;
import com.trading3.trading.service.EmailService;
import com.trading3.trading.service.ForgetPasswordService;
import com.trading3.trading.service.UserService;
import com.trading3.trading.service.VerificationCodeService;
import com.trading3.trading.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ForgetPasswordService forgetPasswordService;
    private String jwt;

    @GetMapping("/api/users/profile")
    public ResponseEntity<User>getUserProfile(@RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);

        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @PostMapping("/api/users/verification/{verificationType}/send-otp")
    public ResponseEntity<String>sendVerificationOtp(
            @RequestHeader("Authorization")String jwt,
            @PathVariable VerificationType verificationType) throws Exception {

        User user=userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode=verificationCodeService.
                getVerificationCodeByUser(user.getId());

        if(verificationCode==null)
        {
             verificationCode=verificationCodeService
                     .sendVerificationCode(user, verificationType);
        }
        if(verificationType.equals(VerificationType.EMAIL)){
            emailService.sendVerificationOtpEmail(user.getEmail(),verificationCode.getOtp());
        }


        return new ResponseEntity<>("Verification OTP Send SuccessFully", HttpStatus.OK);
    }

    @PatchMapping("/api/users/enable-two-factor/verify-otp{otp}")
    public ResponseEntity<User>enableTwoFactorAuthentication(
            @PathVariable String otp,
            @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode=verificationCodeService.getVerificationCodeByUser(user.getId());

        String sendTo=verificationCode.getVerificationType().equals(VerificationType.EMAIL)?
                verificationCode.getEmail():verificationCode.getMobile();

        boolean isVerified=verificationCode.getOtp().equals(otp);
        if(isVerified){
            User updateUser=userService.enableTwoFactorAuthentication(
                    verificationCode.getVerificationType(),sendTo,user);
            verificationCodeService.deleteVerificationCodeById(verificationCode);

            return new ResponseEntity<>(updateUser, HttpStatus.OK);

        }
        throw new Exception("Wrong Otp");
    }

    @PostMapping("/auth/users/reset-password/send-otp")
    public ResponseEntity<AuthResponse>sendForgetPasswordOtp(

            @RequestBody ForgetPasswordTokenRequest req) throws Exception {

        User user=userService.findUserByEmail(req.getSendTo());
        String otp= OtpUtils.generateOtp();
        UUID uuid=UUID.randomUUID();
        String id=uuid.toString();

        ForgetPasswordToken token=forgetPasswordService.findByUser(user.getId());

        if(token==null){
            token=forgetPasswordService.createToken(user,
                    id,
                    otp,
                    req.getVerificationType(),
                    req.getSendTo());
        }
        if(req.getVerificationType().equals(VerificationType.EMAIL)){
            emailService.sendVerificationOtpEmail(
                    user.getEmail(),
                    token.getOtp()
            );
        }
        AuthResponse response=new AuthResponse();
        response.setSession(token.getId());
        response.setMessage("Password reset OTP send SuccessFully");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/auth/users/reset-password/verify-otp")
    public ResponseEntity<ApiResponse>resetPassword(
            @RequestParam String id,
            @RequestBody ResetPasswordRequest req,
            @RequestHeader("Authorization")String jwt) throws Exception {


       ForgetPasswordToken forgetPasswordToken=forgetPasswordService.findById(id);

        boolean isVerified=forgetPasswordToken.getOtp().equals(req.getOtp());

        if(isVerified){
            userService.updatePassword(forgetPasswordToken.getUser(),req.getPassword());

            ApiResponse res=new ApiResponse();
            res.setMessage("Password update Successfully");
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
        }

        throw new Exception("Wrong OTP");

    }
}
