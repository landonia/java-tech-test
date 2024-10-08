package com.postman.ezpay.provider;

import net.ezpay.EZPay;
import org.payments.PaymentProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The EZPay implementation of our {@link PaymentProvider}
 *
 * @author Landon Wainwright
 */
public class PostmanEZPayPaymentProvider implements PaymentProvider {
    private static final EZPay ezPay = new EZPay();
    Map<String, Object> processingCards = new ConcurrentHashMap<>();
    
    public boolean pay(String creditCardNumber, Double amount) {
        processingCards.putIfAbsent(creditCardNumber, new Object());
        synchronized(processingCards.get(creditCardNumber)) {
            return ezPay.collectPayment(creditCardNumber, amount);
        }
    }
}
