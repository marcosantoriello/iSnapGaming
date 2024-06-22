package com.isnapgaming.isnapgaming.UserManagement;

import com.isnapgaming.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.isnapgaming.StorageManagement.DAO.CustomerOrderDAO;;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class OrderManager extends Manager{

    public OrderManager() {
        super();
    }

    // Business Logic methods
    public synchronized void setOrderStatus(CustomerOrder order, CustomerOrder.Status status, DataSource dataSource) throws SQLException {
        CustomerOrderDAO orderDAO = new CustomerOrderDAO(dataSource);
        orderDAO.updateStatus(order.getId(), status);
    }

    public List<CustomerOrder> getAllProducts(DataSource dataSource) throws SQLException {
        CustomerOrderDAO orderDAO = new CustomerOrderDAO(dataSource);
        return orderDAO.();
    }
}
