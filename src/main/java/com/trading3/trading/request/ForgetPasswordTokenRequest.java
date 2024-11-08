package com.trading3.trading.request;

import com.trading3.trading.domain.VerificationType;
import lombok.Data;

@Data
public class ForgetPasswordTokenRequest {
    private String sendTo;
    private VerificationType verificationType ;
}
