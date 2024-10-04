package com.postman.provider;

import org.payments.PaymentProvider;

import java.util.UUID;

/**
 * PART 2
 * <p>
 * Takes a payment provider and audits the call
 */
public class AuditingPaymentProvider implements PaymentProvider {
    private final PaymentProvider paymentProvider;

    public AuditingPaymentProvider(PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    @Override
    public boolean pay(String creditCardNumber, Double amount) {
        long start = System.currentTimeMillis();
        UUID id = UUID.randomUUID();
        System.out.printf("%s: %s: starting payment from %s for %f%n", paymentProvider.getClass(), id, creditCardNumber, amount);
        boolean success = paymentProvider.pay(creditCardNumber, amount);

        long total = System.currentTimeMillis() - start;
        System.out.printf("%s: %s: finished payment from %s for %f in %ds %dms%n", paymentProvider.getClass(), id, creditCardNumber, amount, total/1000, total%1000);
        return success;
    }
}
