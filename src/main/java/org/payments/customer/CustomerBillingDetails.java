package org.payments.customer;

/**
 * Stores the basic billing details for a customer including the customers name,
 * credit card number and the amount to bill.
 *
 * @author Landon Wainwright
 */
public record CustomerBillingDetails(String customerName, String creditCardNumber, Double serviceCost) {

    public static class CustomerBillingDetailsBuilder {
        private String customerName;
        private String creditCardNumber;
        private Double serviceCost;

        public CustomerBillingDetailsBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public CustomerBillingDetailsBuilder creditCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
            return this;
        }

        public CustomerBillingDetailsBuilder serviceCost(Double serviceCost) {
            this.serviceCost = serviceCost;
            return this;
        }

        public CustomerBillingDetails build() {
            assert customerName != null;
            assert creditCardNumber != null;
            assert serviceCost != null;
            return new CustomerBillingDetails(customerName, creditCardNumber, serviceCost);
        }
    }

    public static CustomerBillingDetailsBuilder builder() {
        return new CustomerBillingDetailsBuilder();
    }
}
