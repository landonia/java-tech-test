package org.payments.billing;

import org.payments.customer.CustomerBillingDetails;

import java.util.Date;

/**
 * Will return whether the billing details were successful
 *
 * @author Landon Wainwright
 */
public record BillResult(CustomerBillingDetails billingDetails, boolean success, Date transaction) {

    public static class BillResultBuilder {
        private CustomerBillingDetails billingDetails;
        private boolean success;
        private Date transaction;

        public BillResultBuilder billingDetails(CustomerBillingDetails billingDetails) {
            this.billingDetails = billingDetails;
            return this;
        }

        public BillResultBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public BillResultBuilder transaction(Date transaction) {
            this.transaction = transaction;
            return this;
        }

        public BillResult build() {
            return new BillResult(billingDetails, success, transaction);
        }
    }

    public static BillResultBuilder builder() {
        return new BillResultBuilder();
    }
}
