package com.postman.ezpay.provider;

import org.payments.PaymentProvider;

import java.util.function.Function;

public class SelectablePaymentProvider implements PaymentProvider {
    private final PaymentProvider paymentProvider;
    Function<String, Boolean> paymentProviderSelector;
  
    public SelectablePaymentProvider(PaymentProvider paymentProvider, Function<String, Boolean> paymentProviderSelector) {
        this.paymentProvider = paymentProvider;
        this.paymentProviderSelector = paymentProviderSelector;
    }

    public boolean shouldPay(String creditCardNumber) {
        return paymentProviderSelector.apply(creditCardNumber);
    }

    public boolean pay(String creditCardNumber, Double amount) {
        return paymentProvider.pay(creditCardNumber, amount);
    }
}