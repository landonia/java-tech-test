package org.payments.billing;

import org.payments.customer.CustomerBillingDetails;

import java.util.List;
import java.util.Set;

/**
 * @author Landon Wainwright
 */
public interface BillingService {

    /**
     * Will attempt to process payments for the following customers.
     *
     * @param customers The {@link CustomerBillingDetails}
     * @return The billing result
     */
    Set<BillResult> bill(List<CustomerBillingDetails> customers);
}
