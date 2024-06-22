package com.isnapgaming.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.isnapgaming.ProductManagement.Product;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDAO {

    private static final String TABLE_NAME = "customerorder";
    private DataSource ds = null;

    public CustomerOrderDAO(DataSource ds) {
        this.ds = ds;
    }

    public synchronized int doSave(CustomerOrder order) throws SQLException, IllegalArgumentException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        System.out.println("Persisting order...");
        Connection c = ds.getConnection();

        String sql = "INSERT INTO " + CustomerOrderDAO.TABLE_NAME + " (customerId, status, address, orderDate, totalAmount) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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
        System.out.println("Order saved with ID: " + customerOrderId);
        System.out.println("Persisting order products...");
        for (Product product : order.getProducts()) {
            ProductDAO productDAO = new ProductDAO(ds);
            Product prod = productDAO.findByProdCode(product.getProdCode());
            saveOrderProduct(customerOrderId, prod.getId());
        }

        c.close();
        return customerOrderId;
    }

    public synchronized void updateStatus(int orderId, CustomerOrder.Status status) throws SQLException, IllegalArgumentException {
        if (orderId < 0) {
            throw new IllegalArgumentException("Order ID must be greater than 0");
        }
        Connection c = ds.getConnection();
        String query = "UPDATE " + CustomerOrderDAO.TABLE_NAME + " SET status = ? WHERE id = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, status.toString());
        ps.setInt(2, orderId);
        ps.execute();
    }

    private synchronized  void saveOrderProduct(int orderId, int productId) throws SQLException, IllegalArgumentException{
        if (orderId < 0 || productId < 0) {
            throw new IllegalArgumentException("Order ID and Product ID must be greater than 0");
        }
        Connection c = ds.getConnection();
        String query = "INSERT INTO orderproduct (orderId, productId) VALUES (?, ?)";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, orderId);
        ps.setInt(2, productId);
        ps.execute();
    }

    public synchronized List<Product> findProductsByOrderId(int orderId) throws SQLException, IllegalArgumentException {
        if (orderId < 0) {
            throw new IllegalArgumentException("Order ID must be greater than 0");
        }
        Connection c = ds.getConnection();

        String query = "SELECT p.* FROM Product p JOIN OrderProduct op ON p.id = op.productId WHERE op.orderId = ?";
        List<Product> products = new ArrayList<>();

        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setProdCode(rs.getInt("prodCode"));
            product.setName(rs.getString("name"));
            product.setSoftwareHouse(rs.getString("softwareHouse"));
            product.setPlatform(Product.Platform.valueOf(rs.getString("platform")));
            product.setPrice(rs.getInt("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setCategory(Product.Category.valueOf(rs.getString("category")));
            product.setPegi(Product.Pegi.valueOf(rs.getString("pegi")));
            product.setReleaseYear(rs.getInt("releaseYear"));
            product.setImagePath(rs.getString("imagePath"));
            products.add(product);

        }
        return products;
    }

    public synchronized CustomerOrder findByKey(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }

        Connection c = ds.getConnection();

        String query = "SELECT * FROM " + CustomerOrderDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Error: no order found with ID " + id);
        }

        // Retrieving associated address and products
        AddressDAO addressDAO = new AddressDAO(ds);

        CustomerOrder order = new CustomerOrder();
        order.setId(rs.getInt("id"));
        order.setCustomerId(rs.getInt("customerId"));
        order.setStatus(CustomerOrder.Status.valueOf(rs.getString("status")));
        order.setAddress((rs.getString("address"))); // PROBLEMA QUI. ID = 0
        order.setOrderDate(rs.getDate("orderDate").toLocalDate());
        order.setProducts(findProductsByOrderId(order.getId()));

        c.close();
        return order;
    }

    public synchronized List<CustomerOrder> findByStatus(CustomerOrder.Status status) throws SQLException, IllegalArgumentException {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        Connection c = ds.getConnection();

        String query = "SELECT * FROM " + CustomerOrderDAO.TABLE_NAME + " WHERE status = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setString(1, status.toString());

        ResultSet rs = ps.executeQuery();
        List<CustomerOrder> orders = new ArrayList<>();
        while (rs.next()) {
            AddressDAO addressDAO = new AddressDAO(ds);
            CustomerOrder order = new CustomerOrder();
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("customerId"));
            order.setStatus(CustomerOrder.Status.valueOf(rs.getString("status")));
            order.setAddress(rs.getString("address"));
            order.setOrderDate(rs.getDate("orderDate").toLocalDate());
            order.setProducts(findProductsByOrderId(order.getId()));
            orders.add(order);
        }

        c.close();
        return orders;
    }

    // TODO: Implement doRetrieveAll

}
