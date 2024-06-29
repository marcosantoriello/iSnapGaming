package com.isnapgaming.isnapgaming.OrderManagement;

import com.isnapgaming.isnapgaming.ProductManagement.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ItemCart> items;
    private int totalPrice;

    public Cart() {
        items = new ArrayList<>();
        totalPrice = 0;
    }

    public void addToCart(Product product, int quantity) {
        for (ItemCart item : items) {
            // If the product is already in the cart I just increment the quantity
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                calculateTotalPrice();
                return;
            }
            items.add(new ItemCart(this, product, quantity));
            calculateTotalPrice();
        }
    }

    public void removeFromCart(Product product) {
        items.removeIf(item -> item.getProduct().getId() == product.getId());
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = items.stream().mapToInt(ItemCart::getTotalPrice).sum();
    }

    public void setItems(List<ItemCart> items) {
        this.items = items;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<ItemCart> getItems() {
        return items;
    }
}
