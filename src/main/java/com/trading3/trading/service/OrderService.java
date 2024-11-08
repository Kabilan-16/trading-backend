package com.trading3.trading.service;

import com.trading3.trading.domain.OrderType;
import com.trading3.trading.modal.Coin;
import com.trading3.trading.modal.Order;
import com.trading3.trading.modal.OrderItem;
import com.trading3.trading.modal.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, OrderItem orderItem, OrderType orderType);

    Order getOrderById(Long orderId) throws Exception;

    List<Order> getAllOrderOfUser(Long userId,OrderType orderType,String assetSymbol);

    Order processOrder(Coin coin,double quantity, OrderType orderType,User user) throws Exception;

}
