package com.trading3.trading.service;

import com.trading3.trading.domain.WalletTransactionType;
import com.trading3.trading.modal.Transaction;
import com.trading3.trading.modal.User;
import com.trading3.trading.modal.Wallet;
import com.trading3.trading.modal.WalletTransaction;
import com.trading3.trading.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transactionRequest, User user) {
        transactionRequest.setUser(user);
        transactionRequest.setDate(LocalDateTime.now());
        return transactionRepository.save(transactionRequest);
    }

    @Override
    public List<Transaction> getUserTransactions(User user) {
        return transactionRepository.findByUser(user);
    }

    @Override
    public WalletTransaction createTransaction(
            Wallet userWallet,
            WalletTransactionType walletTransactionType,
            Object o,
            String bankAccountWithdrawal,
            Long amount)
    {
        return null;
    }
}
