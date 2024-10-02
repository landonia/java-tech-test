package org.payments.billing.impl;

import org.payments.PaymentProvider;
import org.payments.billing.BillingService;
import org.payments.customer.CustomerBillingDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
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
        this.paymentProvider = paymentProvider;
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void bill(List<CustomerBillingDetails> customers) {
        List<Future<Boolean>> payments = new ArrayList<>();
        for (CustomerBillingDetails customer : customers) {
            payments.add(executorService
                    .submit(() -> paymentProvider.pay(customer.getCreditCardNumber(), customer.getServiceCost())));
        }

        // Block until all the payments are complete
        payments.forEach(booleanFuture -> {
            try {
                booleanFuture.get();
            } catch (Exception e) {
                // Doh
            }
        });
    }
}
