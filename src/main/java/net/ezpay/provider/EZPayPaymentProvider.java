package net.ezpay.provider;

import net.ezpay.EZPay;
import org.payments.PaymentProvider;

/**
 * The EZPay implementation of our {@link PaymentProvider}
 *
 * @author Landon Wainwright
 */
public class EZPayPaymentProvider implements PaymentProvider {
    private static final EZPay ezPay = new EZPay();

    public boolean pay(String creditCardNumber, Double amount) {
        synchronized (ezPay) {
            return ezPay.collectPayment(creditCardNumber, amount);
        }
    }
}
