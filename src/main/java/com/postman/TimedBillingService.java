package com.postman;

import org.payments.billing.BillResult;
import org.payments.billing.BillingService;
import org.payments.customer.CustomerBillingDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * PART 1
 * <p>
 * Will time the billing call
 */
public class TimedBillingService implements BillingService {
    private static final Logger log = LoggerFactory.getLogger(TimedBillingService.class);
    private final BillingService billingService;

    public TimedBillingService(BillingService billingService) {
        this.billingService = billingService;
    }

    @Override
    public Set<BillResult> bill(List<CustomerBillingDetails> customers) {
        long start = System.currentTimeMillis();
        Set<BillResult> result = billingService.bill(customers);
        long total = System.currentTimeMillis() - start;
        log.info("Execution took {}s {}ms", total / 1000, total % 1000);
        return result;
    }
}
