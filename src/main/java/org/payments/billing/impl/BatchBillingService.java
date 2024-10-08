package org.payments.billing.impl;

import org.payments.PaymentProvider;
import org.payments.billing.BillResult;
import org.payments.billing.BillingService;
import org.payments.customer.CustomerBillingDetails;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Simple billing service that uses an executor to batch billing requests.
 *
 * @author Landon Wainwright
 */
public class BatchBillingService implements BillingService {
    private static final int DEFAULT_THREAD_POOL = 3;

    /*
     * Used to execute the billing
     */
    private final ExecutorService executorService;

    /**
     * The payment provider that will take the payment
     */
    private final PaymentProvider paymentProvider;

    /**
     * Creates the batch service using an executor to use a pool of 3
     *
     * @param paymentProvider The actual payment provider to take the payment
     */
    public BatchBillingService(PaymentProvider paymentProvider) {
        this(paymentProvider, DEFAULT_THREAD_POOL);
    }

    /**
     * Creates the batch service using the provided settings.
     *
     * @param paymentProvider The actual payment provider to take the payment
     * @param poolSize        The number of payments that can be taken at the same
     *                        time
     */
    public BatchBillingService(PaymentProvider paymentProvider, int poolSize) {
        assert paymentProvider != null;
        assert poolSize > 0;
        this.paymentProvider = paymentProvider;
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    // 3 customers at a time, 3 seconds max per customer
    // n customers / 3 customer at a time * 3 seconds max --> n seconds
    @Override
    public Set<BillResult> bill(List<CustomerBillingDetails> customers) {
        Set<BillResult> result = new HashSet<>();
        List<Future<BillResult>> payments = new ArrayList<>();
        for (CustomerBillingDetails customer : customers) {
            payments.add(executorService
                    .submit(() -> BillResult.builder()
                            .billingDetails(customer)
                            .transaction(new Date())
                            .success(paymentProvider.pay(customer.creditCardNumber(), customer.serviceCost()))
                            .build()));
        }

        // Block until all the payments are complete
        payments.forEach(booleanFuture -> {
            try {
                result.add(booleanFuture.get());
            } catch (Exception e) {
                // Doh
            }
        });
        return result;
    }
}
