package com.isnapgaming.UserManagement;

import java.util.Objects;

public class Address {
    private int id;
    private int customerId;
    private String street;
    private String city;
    private int postalCode;

    public Address() {}

    public static Address makeAddress(int customerId, String street, String city, int postalCode) {
        if (customerId < 0) {
            throw new IllegalArgumentException("Customer id cannot be negative");
        }
        if (street == null) {
            throw new IllegalArgumentException("Street cannot be null");
        }
        if (city == null) {
            throw new IllegalArgumentException("City cannot be null");
        }
        if (postalCode < 0) {
            throw new IllegalArgumentException("Postal code cannot be negative");
        }
        Address address = new Address();
        address.setCustomerId(customerId);
        address.setStreet(street);
        address.setCity(city);
        address.setPostalCode(postalCode);
        return address;
    }

    public Address(int customerId, String street, String city, int postalCode) {
        this.customerId = customerId;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
