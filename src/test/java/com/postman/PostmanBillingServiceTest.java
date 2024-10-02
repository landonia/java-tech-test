package com.postman;

import org.payments.billing.BillingService;
import org.payments.customer.CustomerBillingDetails;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Landon Wainwright
 */
public class PostmanBillingServiceTest {

    @Test
    public void testBilling() {

        /////////////////////////////////////////////////////////////////////////////////
        // TODO PART 1: WHY IS IS TAKING SO LONG? WHAT IS THE MAX TIME IT SHOULD TAKE? //
        /////////////////////////////////////////////////////////////////////////////////

        long start = System.currentTimeMillis();

        BillingService postmanBillingService = new PostmanBillingService();
        System.out.println("Start Billing");

        // Create the customers we want to bill
        List<CustomerBillingDetails> billingDetails = new ArrayList<>();
        billingDetails.add(new CustomerBillingDetails("OpenAI Inc", "9876 9876 9876 9876", 1250.00));
        billingDetails.add(new CustomerBillingDetails("Google Inc (Dep A)", "1234 1234 1234 1234", 1500.80));
        billingDetails.add(new CustomerBillingDetails("Google Inc (Dep B)", "1234 1234 1234 1234", 1200.10));
        billingDetails.add(new CustomerBillingDetails("Yahoo Inc", "2345 2345 2345 2345", 1100.10));
        billingDetails.add(new CustomerBillingDetails("Microsoft Inc", "3456 3456 3456 3456", 1999.40));
        billingDetails.add(new CustomerBillingDetails("Adobe Inc", "4567 4567 4567 4567", 1499.40));
        billingDetails.add(new CustomerBillingDetails("Meta Inc", "5678 5678 5678 5678", 1800.00));
        billingDetails.add(new CustomerBillingDetails("Oracle Inc", "6789 6789 6789 6789", 2000.00));
        billingDetails.add(new CustomerBillingDetails("Verizon Inc", "4321 4321 4321 4321", 1880.00));
        billingDetails.add(new CustomerBillingDetails("Splunk Corp", "4321 6789 4321 6789", 1070.00));

        // Initiate the billing
        postmanBillingService.bill(billingDetails);
        System.out.println("Finished Billing in " + (System.currentTimeMillis() - start) + "ms");
    }
}