package com.trading3.trading.controller;

import com.trading3.trading.modal.Transaction;
import com.trading3.trading.modal.User;
import com.trading3.trading.service.TransactionService;
import com.trading3.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(
            @RequestBody Transaction transactionRequest,
            @RequestHeader("Authorization") String jwt) throws  Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Transaction transaction = transactionService.createTransaction(transactionRequest, user);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Transaction> transactions = transactionService.getUserTransactions(user);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
