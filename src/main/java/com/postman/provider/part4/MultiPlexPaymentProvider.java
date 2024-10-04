package com.postman.provider.part4;

import org.payments.PaymentProvider;

import java.util.List;

/**
 * PART 4
 * <p>
 * This will allow the payment providers to be switched out based on the credit card number
 */
public class MultiPlexPaymentProvider implements PaymentProvider {
    private final PaymentProvider defaultProvider;
    private final List<? super SelectablePaymentProvider> paymentProviders;

    public MultiPlexPaymentProvider(PaymentProvider defaultProvider, List<? super SelectablePaymentProvider> paymentProviders) {
        assert defaultProvider != null;
        assert paymentProviders != null;
        this.defaultProvider = defaultProvider;
        this.paymentProviders = paymentProviders;
    }

    @Override
    public boolean pay(String creditCardNumber, Double amount) {

        // Find the first provider that can handle this card (or the default) and delegate
        return paymentProviders.stream()

                // Check for selectable payment providers
                .filter(pp -> pp instanceof SelectablePaymentProvider)
                .map(pp -> (SelectablePaymentProvider) pp)

                // filter to the ones that accept this credit card
                .filter(o -> o.accepts(creditCardNumber))
                .map(pp -> (PaymentProvider) pp)
                .findFirst()

                // Otherwise use the default
                .orElse(defaultProvider)
                .pay(creditCardNumber, amount);
    }
}
