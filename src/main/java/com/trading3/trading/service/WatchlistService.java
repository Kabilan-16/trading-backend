package com.trading3.trading.service;

import com.trading3.trading.modal.Coin;
import com.trading3.trading.modal.User;
import com.trading3.trading.modal.Watchlist;

public interface WatchlistService {

    Watchlist findUserWatchlist(Long userId) throws Exception;
    Watchlist createWatchlist(User user);

    Watchlist findById(Long id) throws Exception;

    Coin addItemToWatchlist(Coin coin,User user) throws Exception;

}
