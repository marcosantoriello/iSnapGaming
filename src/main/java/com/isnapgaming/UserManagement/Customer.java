package com.isnapgaming.UserManagement;

import com.isnapgaming.OrderManagement.CustomerOrder;


import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<CustomerOrder> orders;
    private List<Address> addresses;

    public Customer() {
        super();
        orders = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


}
