package com.postman.provider.part2;

import org.payments.PaymentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * PART 2
 * <p>
 * Takes a payment provider and audits the call
 */
public class AuditingPaymentProvider implements PaymentProvider {
    private static final Logger log = LoggerFactory.getLogger(AuditingPaymentProvider.class);
    private final PaymentProvider paymentProvider;

    public AuditingPaymentProvider(PaymentProvider paymentProvider) {
        assert paymentProvider != null;
        this.paymentProvider = paymentProvider;
    }

    @Override
    public boolean pay(String creditCardNumber, Double amount) {
        long start = System.currentTimeMillis();
        UUID paymentId = UUID.randomUUID();
        log.info("ID:{}: starting payment from {} for {}", paymentId, creditCardNumber, amount);
        boolean success = paymentProvider.pay(creditCardNumber, amount);
        long total = System.currentTimeMillis() - start;
        log.info("ID:{}: finished payment from {} for {} in {}s {}ms", paymentId, creditCardNumber, amount, total / 1000, total % 1000);
        return success;
    }
}
