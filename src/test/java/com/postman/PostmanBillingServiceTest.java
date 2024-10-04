package com.postman;

import org.junit.Test;
import org.payments.billing.BillResult;
import org.payments.billing.BillingService;
import org.payments.customer.CustomerBillingDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Landon Wainwright
 */
public class PostmanBillingServiceTest {
    private static final Logger log = LoggerFactory.getLogger(PostmanBillingServiceTest.class);

    @Test
    public void testBilling() {
        BillingService postmanBillingService = new PostmanBillingService();
        log.info("Start Billing Cycle");

        // Create the customers we want to bill
        List<CustomerBillingDetails> billingDetails = new ArrayList<>();
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("OpenAI Inc").creditCardNumber("9876 9876 9876 9876").serviceCost(1250.00)
                .build());
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("Google Inc (Dep A)").creditCardNumber("1234 1234 1234 1234").serviceCost(1500.80)
                .build());
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("Google Inc (Dep B)").creditCardNumber("1234 1234 1234 1234").serviceCost(1200.10)
                .build());
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("Yahoo Inc").creditCardNumber("2345 2345 2345 2345").serviceCost(1100.10)
                .build());
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("Microsoft Inc").creditCardNumber("3456 3456 3456 3456").serviceCost(1999.40)
                .build());
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("Adobe Inc").creditCardNumber("4567 4567 4567 4567").serviceCost(1499.40)
                .build());
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("Meta Inc").creditCardNumber("5678 5678 5678 5678").serviceCost(1800.00)
                .build());
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("Oracle Inc").creditCardNumber("6789 6789 6789 6789").serviceCost(2000.00)
                .build());
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("Verizon Inc").creditCardNumber("4321 4321 4321 4321").serviceCost(1880.00)
                .build());
        billingDetails.add(CustomerBillingDetails.builder()
                .customerName("Splunk Corp").creditCardNumber("4321 6789 4321 6789").serviceCost(1070.00)
                .build());

        // Initiate the billing
        Set<BillResult> result = postmanBillingService.bill(billingDetails);
        log.info("Finished Billing Cycle");

        assertEquals(billingDetails.size(), result.size());
        result.forEach(billResult -> assertTrue(billResult.success()));
    }
}