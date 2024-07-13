package com.isnapgaming.PaymentManagement;

import java.time.LocalDate;

public class PaymentAuthorizationServiceAdapter implements PaymentAuthServiceInterface{

    private final PaymentAuthorizationService paymentAuthorizationService;

    public PaymentAuthorizationServiceAdapter() {
        this.paymentAuthorizationService = new PaymentAuthorizationService();
    }


    @Override
    public boolean executePayment(String cardNumber, LocalDate expiration, String cvv, int price) {
        return paymentAuthorizationService.executePayment(cardNumber, expiration, cvv, price);
    }

    @Override
    public boolean checkPaymentData(String cardNumber, LocalDate expiration, String cvv, int price) {
        return paymentAuthorizationService.checkDataFormat(cardNumber, expiration, cvv, price);
    }
}
