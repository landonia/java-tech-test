package org.payments.customer;

import lombok.Builder;

/**
 * Stores the basic billing details for a customer including the customers name,
 * credit card number and the amount to bill.
 *
 * @author Landon Wainwright
 */
@Builder
public record CustomerBillingDetails(String customerName, String creditCardNumber, Double serviceCost) {
}
