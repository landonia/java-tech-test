package org.payments.billing;

import lombok.Builder;
import org.payments.customer.CustomerBillingDetails;

import java.util.Date;

/**
 * Will return whether the billing details were successful
 *
 * @author Landon Wainwright
 */
@Builder
public record BillResult(CustomerBillingDetails billingDetails, boolean success, Date transaction) {
}
