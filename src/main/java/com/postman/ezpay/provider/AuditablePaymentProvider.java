package com.postman.ezpay.provider;

import org.payments.PaymentProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The EZPay implementation of our {@link PaymentProvider}
 *
 * @author Landon Wainwright
 */
public class AuditablePaymentProvider implements PaymentProvider {
    private final PaymentProvider paymentProvider;

    private static final Logger log = LoggerFactory.getLogger(AuditablePaymentProvider.class);
    
    public AuditablePaymentProvider(PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public boolean pay(String creditCardNumber, Double amount) {
        long start = System.currentTimeMillis();
        log.info("{}: Starting payment for credit card {} for {}", start, creditCardNumber, amount);
        boolean result = this.paymentProvider.pay(creditCardNumber, amount);
        log.info("{}: Finished payment for credit card {} for {} - Completed in {} seconds", System.currentTimeMillis(), creditCardNumber, amount, (System.currentTimeMillis() - start) / 1000);
        return result;
    }
}
