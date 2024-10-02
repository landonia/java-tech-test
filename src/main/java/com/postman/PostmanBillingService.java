package com.postman;

import lombok.AllArgsConstructor;
import net.ezpay.provider.EZPayPaymentProvider;
import org.payments.billing.BillResult;
import org.payments.billing.BillingService;
import org.payments.billing.impl.BatchBillingService;
import org.payments.customer.CustomerBillingDetails;

import java.util.List;
import java.util.Set;

/**
 * Just a bootstrap class that allows us to abstract the payment.
 *
 * @author Landon Wainwright
 */
@AllArgsConstructor
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
        this(new BatchBillingService(new EZPayPaymentProvider()));
    }

    public Set<BillResult> bill(List<CustomerBillingDetails> billingDetails) {

        // Bill for the product
        return billingService.bill(billingDetails);
    }
}
