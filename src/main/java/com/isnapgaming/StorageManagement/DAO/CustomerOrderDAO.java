package com.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.OrderManagement.OrderProduct;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDAO {

    private static final String TABLE_NAME = "customerorder";
    private DataSource dataSource = null;

    public CustomerOrderDAO(DataSource dataSource) throws SQLException{
        this.dataSource = dataSource;
    }

    public synchronized int doSave(CustomerOrder order) throws SQLException, IllegalArgumentException {
        if (order == null) {
            throw new IllegalArgumentException("CustomerOrder cannot be null");
        }
        Connection connection = dataSource.getConnection();

        // Persisting CustomerOrder
        String sql = "INSERT INTO " + CustomerOrderDAO.TABLE_NAME + " (customerId, status, address, orderDate, totalAmount) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, order.getCustomerId());
        ps.setString(2, order.getStatus().toString());
        ps.setString(3, order.getAddress());
        ps.setDate(4, java.sql.Date.valueOf(order.getOrderDate()));
        ps.setInt(5, order.getTotalAmount());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (!rs.next()) {
            throw new SQLException("Error: no generated keys. The Order has not been saved.");
        }
        int customerOrderId = rs.getInt(1);

        // Persisting OrderProduct
        String sql2 = "INSERT INTO orderproduct (orderId, productId, quantity, price) VALUES (?, ?, ?, ?)";
        PreparedStatement ps2 = connection.prepareStatement(sql2);
        for (OrderProduct orderProduct : order.getProducts()) {
            ps2.setInt(1, customerOrderId);
            ps2.setInt(2, orderProduct.getProductId());
            ps2.setInt(3, orderProduct.getQuantity());
            ps2.setInt(4, orderProduct.getPrice());
            ps2.addBatch();
        }
        ps2.executeBatch(); // This way the queries to add multiple OrderProducts will be executed all together

        connection.close();
        return customerOrderId;
    }

    public synchronized void updateStatus(int orderId, CustomerOrder.Status status) throws SQLException, IllegalArgumentException {
        if (orderId < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        Connection connection = dataSource.getConnection();
        String query = "UPDATE " + CustomerOrderDAO.TABLE_NAME + " SET status = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status.toString());
            ps.setInt(2, orderId);
            int ret = ps.executeUpdate();
            if (ret == 0) {
                throw new SQLException("No CustomerOrder found with the given id");
            }
        } catch (SQLException e) {
            throw new SQLException("No CustomerOrder found with the given id");
        } finally {
            connection.close();
        }
    }

    public synchronized CustomerOrder findByKey(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("id cannot be negative");
        }

        Connection connection = dataSource.getConnection();

        // Retrieving CustomerOrder
        String query = "SELECT * FROM " + CustomerOrderDAO.TABLE_NAME + " WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CustomerOrder order = new CustomerOrder();
                order.setId(rs.getInt("id"));
                order.setCustomerId(rs.getInt("customerId"));
                order.setStatus(CustomerOrder.Status.valueOf(rs.getString("status")));
                order.setAddress((rs.getString("address")));
                order.setOrderDate(rs.getDate("orderDate").toLocalDate());

                // Retrieving OrderProducts
                String query2 = "SELECT * FROM orderproduct WHERE orderId = ?";
                PreparedStatement ps2 = connection.prepareStatement(query2);
                ps2.setInt(1, id);
                ResultSet rs2 = ps.executeQuery();
                List<OrderProduct> orderProducts = new ArrayList<>();
                rs2.next();
                while(rs2.next()) {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrderId(rs2.getInt("orderId"));
                    orderProduct.setProductId(rs2.getInt("productId"));
                    orderProduct.setQuantity(rs2.getInt("quantity"));
                    orderProduct.setPrice(rs2.getInt("price"));
                    orderProducts.add(orderProduct);
                }
                order.setProducts(orderProducts);
                return order;
            } else {
                throw new SQLException("No CustomerOrder found with the given id");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            connection.close();
        }
    }
    public List<CustomerOrder> doRetrieveAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM " + CustomerOrderDAO.TABLE_NAME;
        PreparedStatement ps = connection.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        List<CustomerOrder> orders = new ArrayList<>();
        while (rs.next()) {
            CustomerOrder order = new CustomerOrder();
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("customerId"));
            order.setStatus(CustomerOrder.Status.valueOf(rs.getString("status")));
            order.setAddress((rs.getString("address")));
            order.setOrderDate(rs.getDate("orderDate").toLocalDate());
            orders.add(order);
        }
        // Retrieving OrderProducts for every order in orders
        for (CustomerOrder relOrd: orders) {
            String query2 = "SELECT * FROM orderproduct WHERE orderId = ?";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setInt(1, relOrd.getId());
            ResultSet rs2 = ps.executeQuery();
            List<OrderProduct> orderProducts = new ArrayList<>();

            rs2.next();
            while(rs2.next()) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrderId(rs2.getInt("orderId"));
                orderProduct.setProductId(rs2.getInt("productId"));
                orderProduct.setQuantity(rs2.getInt("quantity"));
                orderProduct.setPrice(rs2.getInt("price"));
                orderProducts.add(orderProduct);
            }
            relOrd.setProducts(orderProducts);
        }
        connection.close();
        return orders;
    }

}
