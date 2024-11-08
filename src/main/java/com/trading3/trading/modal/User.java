package com.trading3.trading.modal;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.trading3.trading.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data


public class User {

    //Automatically generate the User id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

     private String fullName;
     private String email;
     @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
     private String password;

     @Embedded
     private TwoFactorAuth twoFactorAuth=new TwoFactorAuth();


     private USER_ROLE role= USER_ROLE.ROLE_CUSTOMER;



}
