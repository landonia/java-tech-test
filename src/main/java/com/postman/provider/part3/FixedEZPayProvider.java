package com.postman.provider.part3;

import net.ezpay.EZPay;
import org.payments.PaymentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * PART 3
 * <p>
 * This will ensure that the payment provider will process the same credit card synchronously otherwise allowing
 * different cards to be processed asynchronously.
 */
public class FixedEZPayProvider implements PaymentProvider {
    private static final Logger log = LoggerFactory.getLogger(FixedEZPayProvider.class);
    private static final EZPay ezPay = new EZPay();
    private static final Map<String, Object> mutexes = new HashMap<>();

    @Override
    public boolean pay(String creditCardNumber, Double amount) {

        // We need to get the correct mutex from the card based on the credit card number
        Object mutex = mutexes.computeIfAbsent(creditCardNumber, k -> new Object());
        synchronized (mutex) {
            log.info("entering mutex payment for {}", creditCardNumber);
            boolean success = ezPay.collectPayment(creditCardNumber, amount);
            log.info("leaving mutex payment for {}", creditCardNumber);
            return success;
        }
    }
}
