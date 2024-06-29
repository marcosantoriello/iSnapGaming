package com.isnapgaming.ProductManagement;

import java.util.Objects;

public class Product {
    public enum Platform {PC, PS4, PS5, XBOX, SWITCH};
    public enum Category {ACTION, ADVENTURE, RPG, STRATEGY, SIMULATION, SPORTS, PUZZLE, HORROR, SURVIVAL, SHOOTER};
    public enum Pegi {PEGI3, PEGI7, PEGI12, PEGI16, PEGI18};

    private int id;
    private int prodCode;
    private String name;
    private String softwareHouse;
    private Platform platform;
    private int price;
    private int quantity;
    private Category category;
    private Pegi pegi;
    private int releaseYear;
    private String imagePath;
    private boolean available;

    public Product() {
        this.platform = null;
        this.category = null;
        this.pegi = null;
    }

    public static Product makeProduct(int prodCode, String name, String softwareHouse, Platform platform, int price, int quantity, Category category, Pegi pegi, int releaseYear, String imagePath, boolean available) {

        // Checking parameters
        if (prodCode == 0) {
            throw new IllegalArgumentException("Code cannot be 0");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (softwareHouse == null) {
            throw new IllegalArgumentException("Software House cannot be null");
        }
        if (platform == null) {
            throw new IllegalArgumentException("Platform cannot be null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (pegi == null) {
            throw new IllegalArgumentException("Pegi cannot be null");
        }
        if (releaseYear < 0) {
            throw new IllegalArgumentException("Release Date cannot be negative");
        }


        Product product = new Product();
        product.setName(name);
        product.setSoftwareHouse(softwareHouse);
        product.setPlatform(platform);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setPegi(pegi);
        product.setReleaseYear(releaseYear);
        product.setAvailable(available);

        if (imagePath != null) {
            product.setImagePath(imagePath);
        }

        return product;
    }

    public int getProdCode() {
        return prodCode;
    }

    public void setProdCode(int prodCode) {
        this.prodCode = prodCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoftwareHouse() {
        return softwareHouse;
    }

    public void setSoftwareHouse(String softwareHouse) {
        this.softwareHouse = softwareHouse;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Pegi getPegi() {
        return pegi;
    }

    public void setPegi(Pegi pegi) {
        this.pegi = pegi;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
