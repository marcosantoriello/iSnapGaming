package com.isnapgaming.PaymentManagement;

import java.time.LocalDate;
import java.util.Random;

public class PaymentAuthorizationService {

    public PaymentAuthorizationService() {}

    public boolean executePayment(String cardNumber, LocalDate expiration, String cvv, int price){

        Random rand = new Random();
        int n = 1;
        int genNumber = rand.nextInt((10 - 1) + 1) + 1;

        if(genNumber == n)
            return false;
        else
            return true;
    }

    public boolean checkDataFormat(String cardNumber, LocalDate expiration, String cvv, int price){

        boolean check = true;

        if(cardNumber == null || !cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}"))
            check = false;
        if(expiration == null || !expiration.isAfter(LocalDate.now()))
            check = false;
        if(cvv == null || !cvv.matches("\\d{3}"))
            check = false;
        if(price <= 0)
            check = false;
        
        return check;
    }
}
