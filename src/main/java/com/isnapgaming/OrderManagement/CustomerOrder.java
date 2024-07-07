package com.isnapgaming.OrderManagement;

import com.isnapgaming.UserManagement.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CustomerOrder {
    public enum Status {
        TO_BE_MANAGED,
        UNDER_PREPARATION,
        READY_FOR_SENDING,
        SHIPPED,
        DELIVERED
    }

    private int id;
    private int customerId;
    private Status status;
    private String address;
    private LocalDate orderDate;
    private int totalAmount;
    private List<OrderProduct> products;

    public CustomerOrder() {
        this.id = 0;
        this.customerId = 0;
        this.status = Status.TO_BE_MANAGED;
        this.address = null;
        this.orderDate = null;
        this.totalAmount = 0;
        this.products = null;
    }
    public static CustomerOrder makeCustomerOrder(int customerId, String address, LocalDate orderDate, List<OrderProduct> products) {
        if (customerId < 0) {
            throw new IllegalArgumentException("CustomerId cannot be null");
        }
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        if (orderDate == null) {
            throw new IllegalArgumentException("OrderDate cannot be null");
        }
        if (products == null) {
            throw new IllegalArgumentException("Cannot have an order with no products in it.");
        }

        CustomerOrder order = new CustomerOrder();
        order.setCustomerId(customerId);
        order.setStatus(Status.TO_BE_MANAGED);
        order.setAddress(address);
        order.setOrderDate(orderDate);
        order.products = products;
        order.setTotalAmount(order.calculateTotalAmount());
        return order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public void addProduct(OrderProduct product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.products.add(product);
    }

    public int calculateTotalAmount() {
        totalAmount = products.stream().mapToInt(OrderProduct::getTotalPrice).sum();
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
