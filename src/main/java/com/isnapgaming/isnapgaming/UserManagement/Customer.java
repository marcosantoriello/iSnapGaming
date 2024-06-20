package com.isnapgaming.isnapgaming.UserManagement;

import com.isnapgaming.isnapgaming.OrderManagement.CustomerOrder;


import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<CustomerOrder> products;
    private List<Address> addresses;

    public Customer() {
        super();
        products = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public List<CustomerOrder> getProducts() {
        return products;
    }

    public void setProducts(List<CustomerOrder> products) {
        this.products = products;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


}
