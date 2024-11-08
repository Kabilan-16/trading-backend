package com.trading3.trading.service;

import com.trading3.trading.modal.Order;
import com.trading3.trading.modal.User;
import com.trading3.trading.modal.Wallet;

public interface WalletService {
    Wallet getUserWallet (User user);
    Wallet addBalance(Wallet wallet,Long money);

    Wallet findWalletById(Long id) throws Exception;
    Wallet walletToWalletTransfer(User sender,Wallet receiverWallet,Long amount) throws Exception;
    Wallet payOrderPayment(Order order, User user) throws Exception;
}
