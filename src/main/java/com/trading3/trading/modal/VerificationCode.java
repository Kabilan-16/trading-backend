package com.trading3.trading.modal;

import com.trading3.trading.domain.VerificationType;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.ANTLRErrorStrategy;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Data
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private String otp;

    @OneToOne
    private User user;

    private String email;

    private String mobile;

    private VerificationType verificationType;
}
