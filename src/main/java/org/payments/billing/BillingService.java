package org.payments.billing;

import org.payments.customer.CustomerBillingDetails;

import java.util.List;

/**
 * @author Landon Wainwright
 */
public interface BillingService {

    /**
     * Will attempt to process payments for the following customers.
     *
     * @param customers The {@link CustomerBillingDetails}
     */
    void bill(List<CustomerBillingDetails> customers);
}
