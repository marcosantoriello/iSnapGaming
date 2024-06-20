package com.isnapgaming.isnapgaming.OrderManagement;

import com.isnapgaming.isnapgaming.ProductManagement.Product;
import com.isnapgaming.isnapgaming.UserManagement.Address;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private Address address;
    private LocalDate orderDate;
    private int totalAmount;
    private List<Product> products;

    public CustomerOrder() {
        this.products = new ArrayList<>();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
