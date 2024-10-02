package org.payments.customer;

/**
 * Stores the basic billing details for a customer including the customers name,
 * credit card number and the amount to bill.
 *
 * @author Landon Wainwright
 */
public final class CustomerBillingDetails {
    private final String customerName;
    private final String creditCardNumber;
    private final Double serviceCost;

    public CustomerBillingDetails(String customerName, String creditCardNumber, Double serviceCost) {
        this.customerName = customerName;
        this.creditCardNumber = creditCardNumber;
        this.serviceCost = serviceCost;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    public Double getServiceCost() {
        return this.serviceCost;
    }
}
