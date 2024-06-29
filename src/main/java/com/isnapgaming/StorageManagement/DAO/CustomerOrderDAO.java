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
            throw new IllegalArgumentException("Order cannot be null");
        }
        System.out.println("Persisting order...");
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
            ps2.setInt(1, orderProduct.getOrderId());
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
            throw new IllegalArgumentException("Order ID must be greater than 0");
        }
        Connection connection = dataSource.getConnection();
        String query = "UPDATE " + CustomerOrderDAO.TABLE_NAME + " SET status = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, status.toString());
        ps.setInt(2, orderId);
        ps.execute();

        connection.close();
    }

    public synchronized CustomerOrder findByKey(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }

        Connection connection = dataSource.getConnection();

        // Retrieving CustomerOrder
        String query = "SELECT * FROM " + CustomerOrderDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Error: no order found with ID " + id);
        }
        CustomerOrder order = new CustomerOrder();
        order.setId(rs.getInt("id"));
        order.setCustomerId(rs.getInt("customerId"));
        order.setStatus(CustomerOrder.Status.valueOf(rs.getString("status")));
        order.setAddress((rs.getString("address")));
        order.setOrderDate(rs.getDate("orderDate").toLocalDate());

        // Retrieving OrderProducts
        String query2 = "SELECT * FROM orderproduct WHERE orderId = ?";
        PreparedStatement ps2 = connection.prepareStatement(query2);
        ps2.setInt(1, order.getId());
        ResultSet rs2 = ps.executeQuery();
        List<OrderProduct> orderProducts = new ArrayList<>();

        while(rs2.next()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(rs2.getInt("orderId"));
            orderProduct.setProductId(rs2.getInt("productId"));
            orderProduct.setQuantity(rs2.getInt("quantity"));
            orderProduct.setPrice(rs2.getInt("price"));
            orderProducts.add(orderProduct);
        }
        order.setProducts(orderProducts);
        connection.close();
        return order;
    }

    public synchronized List<CustomerOrder> findByStatus(CustomerOrder.Status status) throws SQLException, IllegalArgumentException {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM " + CustomerOrderDAO.TABLE_NAME + " WHERE status = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, status.toString());
        ResultSet rs = ps.executeQuery();
        List<CustomerOrder> orders = new ArrayList<>();
        while (rs.next()) {
            CustomerOrder order = new CustomerOrder();
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("customerId"));
            order.setStatus(CustomerOrder.Status.valueOf(rs.getString("status")));
            order.setAddress((rs.getString("address")));
            order.setOrderDate(rs.getDate("orderDate").toLocalDate());

            // Retrieving OrderProducts
            String query2 = "SELECT * FROM orderproduct WHERE orderId = ?";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setInt(1, order.getId());
            ResultSet rs2 = ps.executeQuery();
            List<OrderProduct> orderProducts = new ArrayList<>();

            while(rs2.next()) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrderId(rs2.getInt("orderId"));
                orderProduct.setProductId(rs2.getInt("productId"));
                orderProduct.setQuantity(rs2.getInt("quantity"));
                orderProduct.setPrice(rs2.getInt("price"));
                orderProducts.add(orderProduct);
            }
            order.setProducts(orderProducts);
            orders.add(order);
        }

        connection.close();
        return orders;
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

            // Retrieving OrderProducts
            String query2 = "SELECT * FROM orderproduct WHERE orderId = ?";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setInt(1, order.getId());
            ResultSet rs2 = ps.executeQuery();
            List<OrderProduct> orderProducts = new ArrayList<>();

            while(rs2.next()) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrderId(rs2.getInt("orderId"));
                orderProduct.setProductId(rs2.getInt("productId"));
                orderProduct.setQuantity(rs2.getInt("quantity"));
                orderProduct.setPrice(rs2.getInt("price"));
                orderProducts.add(orderProduct);
            }
            order.setProducts(orderProducts);
            orders.add(order);
        }
        connection.close();
        return orders;
    }

    public List<OrderProduct> findOrderProductsByOrderId(int orderId) throws SQLException, IllegalArgumentException{
        if (orderId <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }

        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM orderproduct WHERE orderId=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        List<OrderProduct> products = new ArrayList<>();
        while(rs.next()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(rs.getInt("orderId"));
            orderProduct.setProductId(rs.getInt("productId"));
            orderProduct.setQuantity(rs.getInt("quantity"));
            orderProduct.setPrice(rs.getInt("price"));
            products.add(orderProduct);
        }

        connection.close();
        return products;
    }

}
