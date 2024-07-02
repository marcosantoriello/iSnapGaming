package com.isnapgaming.OrderManagement;

import com.isnapgaming.PaymentManagement.PaymentAuthorizationServiceAdapter;
import com.isnapgaming.UserManagement.User;
import jakarta.servlet.ServletException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderCreation {

    public OrderCreation() {
    }

    public static CustomerOrder makeOrder(Cart cart, User customer, String address) {
        if (cart == null || customer == null || address == null || address.isEmpty()) {
            throw new IllegalArgumentException("There was an error in creating the order.");
        }

        List<OrderProduct> products = new ArrayList<>();
        int customerId = customer.getId();
        for (ItemCart item : cart.getItems()) {
            System.out.println(item.getProduct().getName());
            OrderProduct op = new OrderProduct();
            op.setProductId(item.getProduct().getId());
            op.setQuantity(item.getQuantity());
            op.setPrice(item.getPrice());
            products.add(op);
        }
        CustomerOrder order = CustomerOrder.makeCustomerOrder(customerId, address, LocalDate.now(), products);
        order.calculateTotalAmount();
        return order;
    }

    public static boolean pay(CustomerOrder order, String address, String cardNumber, LocalDate expirationDate, String cvv) throws ServletException{
        PaymentAuthorizationServiceAdapter bank = new PaymentAuthorizationServiceAdapter();

        if (!bank.checkPaymentData(cardNumber, expirationDate, cvv, order.getTotalAmount())) {
            throw new ServletException("An error has occurred while trying to pay. The payment has been rejected.");
        }

        if (!bank.executePayment(cardNumber, expirationDate, cvv, order.getTotalAmount())) {
            throw new ServletException("An error has occurred while trying to pay.");
        } else {
            return true;
        }
    }
}
