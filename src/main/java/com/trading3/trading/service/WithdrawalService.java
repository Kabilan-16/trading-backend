package com.trading3.trading.service;

import com.trading3.trading.modal.User;
import com.trading3.trading.modal.Withdrawal;


import java.util.List;

public interface WithdrawalService {

    Withdrawal requestWithdrawal(Long amount, User user);

    Withdrawal procedWithdrawal(Long withdrawalId, boolean accept) throws Exception;

    List<Withdrawal>getUsersWithdrawalHistory(User user);

    List<Withdrawal>getAllWithdrawalRequest();
}
