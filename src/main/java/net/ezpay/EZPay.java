package net.ezpay;

import java.util.Random;


/**
 * EZPay represents a library that we will consume that would be provided by the EZPay company
 *
 * @author Landon Wainwright
 */
public final class EZPay {


    //////////////////////////////////////////////
    //// LIBRARY CODE PROVIDED BY THIRD PARTY ////
    //////////////////////////////////////////////


    public boolean collectPayment(String creditCardNumber, Double amount) {
        try {
            // Mimic that the request could take up to 3 seconds
            Thread.sleep((new Random().nextInt(3000) + 1));
            return true;
        } catch (InterruptedException ie) {
            return false;
        }
    }
}