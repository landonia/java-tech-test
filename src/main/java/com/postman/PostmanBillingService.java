package com.postman;

import net.ezpay.provider.EZPayPaymentProvider;
import org.payments.billing.BillingService;
import org.payments.billing.impl.BatchBillingService;
import org.payments.customer.CustomerBillingDetails;

import java.util.List;

/**
 * Just a bootstrap class that allows us to abstract the payment.
 *
 * @author Landon Wainwright
 */
public class PostmanBillingService implements BillingService {

    ///////////////////////////////////////////////
    // TODO PART 2: ONLY ONE UNIQUE CREDIT CARD //
    //////////////////////////////////////////////

    ////////////////////////////////////////////////////////////
    // TODO PART 3: PRINT OUT THE PAYMENT DETAILS WHEN BILLED //
    ////////////////////////////////////////////////////////////

    private final BillingService billingService;

    public PostmanBillingService() {

        // We will be using the EZPay provider
        this.billingService = new BatchBillingService(new EZPayPaymentProvider());
    }

    public PostmanBillingService(BillingService billingService) {
        this.billingService = billingService;
    }

    public void bill(List<CustomerBillingDetails> billingDetails) {

        // Bill for the product
        billingService.bill(billingDetails);
    }
}
