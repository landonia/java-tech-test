package com.postman.provider.part4;

import org.payments.PaymentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * PART 4
 * <p>
 * A simple implementation of the selectable payment provider
 */
public class SelectablePaymentProvider implements PaymentProvider {
    private static final Logger log = LoggerFactory.getLogger(SelectablePaymentProvider.class);
    private final PaymentProvider paymentProvider;
    private final Function<String, Boolean> accept;

    public SelectablePaymentProvider(PaymentProvider paymentProvider, Function<String, Boolean> accept) {
        assert paymentProvider != null;
        assert accept != null;
        this.paymentProvider = paymentProvider;
        this.accept = accept;
    }

    public boolean accepts(String creditCardNo) {
        return accept.apply(creditCardNo);
    }

    @Override
    public boolean pay(String creditCardNumber, Double amount) {
        log.info("selected payment provider for credit card number {}", creditCardNumber);
        return paymentProvider.pay(creditCardNumber, amount);
    }
}
