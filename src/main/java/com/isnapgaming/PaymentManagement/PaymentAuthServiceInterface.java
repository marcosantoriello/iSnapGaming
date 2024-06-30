package com.isnapgaming.PaymentManagement;

import java.time.LocalDate;

public interface PaymentAuthServiceInterface {
    boolean executePayment(String cardNumber, LocalDate expiration, String cvv, int price);
    public boolean checkPaymentData(String cardNumber, LocalDate expiration, String cvv, int price);
}
