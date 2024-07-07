package com.isnapgaming.UserManagement;

import com.isnapgaming.OrderManagement.CustomerOrder;


import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<Address> addresses;

    public Customer() {
        super();
        addresses = new ArrayList<>();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

}
