package com.isnapgaming.OrderManagement;

import com.isnapgaming.ProductManagement.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
    private List<ItemCart> items;
    private int totalPrice;

    public Cart() {
        items = new ArrayList<>();
        totalPrice = 0;
    }

    public void addToCart(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }

        for (ItemCart item : items) {
            // If the product is already in the cart I just increment the quantity
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                calculateTotalPrice();
                return;
            }
        }
        items.add(new ItemCart(this, product, quantity));
        calculateTotalPrice();
    }

    public void removeFromCart(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        boolean res = items.removeIf(item -> item.getProduct().getId() == product.getId());
        if (!res) {
            throw new RuntimeException("No such product found in cart.");
        }
        calculateTotalPrice();
    }

    public void decreaseQuantityCart(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        if (!isProductInCart(product)) {
            throw new RuntimeException("No such product found in cart.");
        }
        Iterator<ItemCart> iterator = items.iterator();
        while (iterator.hasNext()) {
            ItemCart item = iterator.next();
            if (item.getProduct().equals(product)) {
                if (quantity > item.getQuantity()) {
                    throw new RuntimeException("Invalid quantity");
            } else if (quantity == item.getQuantity()) {
                    iterator.remove();
                    calculateTotalPrice();
                } else {
                    item.setQuantity(item.getQuantity() - quantity);
                    calculateTotalPrice();
                }
            }
        }

    }

    private boolean isProductInCart(Product product) {
        for (ItemCart item : items) {
            if (item.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
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
