package com.isnapgaming.isnapgaming.StorageManagement.interfaceDS;

import com.isnapgaming.isnapgaming.OrderManagement.CustomerOrder;

import java.sql.SQLException;
import java.util.List;

public interface CustomerOrderInterface {
    public int doSave(CustomerOrder order) throws SQLException, IllegalArgumentException;

    public CustomerOrder findByKey(int id) throws SQLException, IllegalArgumentException;

    public List<CustomerOrder> findByUser(int userId) throws SQLException, IllegalArgumentException;

    public List<CustomerOrder> findByStatus(CustomerOrder.Status status) throws SQLException, IllegalArgumentException;

}
