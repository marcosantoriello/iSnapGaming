package com.isnapgaming.isnapgaming.OrderManagement;

import com.isnapgaming.isnapgaming.ProductManagement.Product;

public class OrderProduct {
    private int orderId;
    private int productId;
    private int quantity;
    private int price;

    public OrderProduct() {
        orderId = 0;
        productId = 0;
        quantity = 0;
        price = 0;
    }
    public OrderProduct(int orderId, int productId, int quantity, int price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getTotalPrice() {
        return price * quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
