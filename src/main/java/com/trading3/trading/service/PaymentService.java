package com.trading3.trading.service;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.trading3.trading.domain.PaymentMethod;
import com.trading3.trading.modal.PaymentOrder;
import com.trading3.trading.modal.User;
import com.trading3.trading.response.PaymentResponse;

public interface PaymentService {

    PaymentOrder createOrder(User user,
                             Long amount,
                             PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    Boolean ProccedPaymentOrder(PaymentOrder paymentOrder,
                                String paymentId) throws RazorpayException;

    PaymentResponse createRazorpayPaymentLing(User user, Long amount, Long orderId) throws RazorpayException;

    PaymentResponse createStripePaymentLing(User user, Long amount,Long orderId) throws StripeException;
}
