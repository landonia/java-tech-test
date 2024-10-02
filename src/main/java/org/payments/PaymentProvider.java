package org.payments;

/**
 * PaymentProvider provides an interface for taking payment from the provider
 */
public interface PaymentProvider {

    /**
     * This will attempt to take the payment from the card for the amount specified
     *
     * @param creditCardNumber The credit card to bill
     * @param amount           The amount to bill the credit card
     */
    boolean pay(String creditCardNumber, Double amount);
}