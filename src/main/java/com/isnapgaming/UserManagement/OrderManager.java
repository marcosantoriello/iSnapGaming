package com.isnapgaming.UserManagement;

import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class OrderManager extends Manager{

    public OrderManager() {
        super();
    }

    // Business Logic methods
    public List<CustomerOrder> getAllCustomerOrders(DataSource dataSource) throws SQLException {
        CustomerOrderDAO orderDAO = new CustomerOrderDAO(dataSource);
        return orderDAO.doRetrieveAll();
    }

    public void checkProduct(CustomerOrder order, DataSource dataSource) throws SQLException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        CustomerOrderDAO orderDAO = new CustomerOrderDAO(dataSource);
        order.setStatus(CustomerOrder.Status.UNDER_PREPARATION);
        orderDAO.updateStatus(order.getId(), order.getStatus());
    }

    public void packProduct(CustomerOrder order, DataSource dataSource) throws SQLException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        CustomerOrderDAO orderDAO = new CustomerOrderDAO(dataSource);
        order.setStatus(CustomerOrder.Status.READY_FOR_SENDING);
        orderDAO.updateStatus(order.getId(), order.getStatus());
    }

    public void replaceProduct(CustomerOrder order, DataSource dataSource) throws SQLException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        CustomerOrderDAO orderDAO = new CustomerOrderDAO(dataSource);
        order.setStatus(CustomerOrder.Status.UNDER_PREPARATION);
        orderDAO.updateStatus(order.getId(), order.getStatus());
    }

    public void contactCourier(CustomerOrder order, DataSource dataSource) throws SQLException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        CustomerOrderDAO orderDAO = new CustomerOrderDAO(dataSource);
        order.setStatus(CustomerOrder.Status.SHIPPED);
        orderDAO.updateStatus(order.getId(), order.getStatus());
    }

    public void restoreOrder(CustomerOrder order, DataSource dataSource) throws SQLException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        CustomerOrderDAO orderDAO = new CustomerOrderDAO(dataSource);
        order.setStatus(CustomerOrder.Status.UNDER_PREPARATION);
        orderDAO.updateStatus(order.getId(), order.getStatus());
    }

    public void confirmDelivery(CustomerOrder order, DataSource dataSource) throws SQLException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        CustomerOrderDAO orderDAO = new CustomerOrderDAO(dataSource);
        order.setStatus(CustomerOrder.Status.DELIVERED);
        orderDAO.updateStatus(order.getId(), order.getStatus());
    }

}
