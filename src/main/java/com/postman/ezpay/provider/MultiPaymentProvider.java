package com.postman.ezpay.provider;

import org.payments.PaymentProvider;
import java.util.List;

public class MultiPaymentProvider implements PaymentProvider {
    List<PaymentProvider> paymentProviders;
    
    public MultiPaymentProvider(List<PaymentProvider> paymentProviders) {
        this.paymentProviders = paymentProviders;
    }

    public boolean pay(String creditCardNumber, Double amount) {
        // creditCard --> paymentProvider to use
        PaymentProvider paymentProvider = getPaymentProvider(creditCardNumber);
        return paymentProvider.pay(creditCardNumber, amount);
    }

    private PaymentProvider getPaymentProvider(String creditCardNumber) {
        return paymentProviders.get(0);
    }
}