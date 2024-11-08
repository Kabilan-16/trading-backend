package com.trading3.trading.service;

import com.trading3.trading.domain.WalletTransactionType;
import com.trading3.trading.modal.Transaction;
import com.trading3.trading.modal.User;
import com.trading3.trading.modal.Wallet;
import com.trading3.trading.modal.WalletTransaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Transaction transactionRequest, User user);
    List<Transaction>getUserTransactions(User user);

    WalletTransaction createTransaction(Wallet userWallet, WalletTransactionType walletTransactionType, Object o, String bankAccountWithdrawal, Long amount);
}
