package com.isnapgaming.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.isnapgaming.UserManagement.Address;
import com.isnapgaming.isnapgaming.UserManagement.Customer;
import com.isnapgaming.isnapgaming.UserManagement.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    DataSource dataSource = null;
    private static final String TABLE_NAME = "customer";

    public CustomerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int doSave(Customer customer) throws SQLException, IllegalArgumentException {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        Connection c = dataSource.getConnection();
        // Saving user fields
        UserDAO userDAO = new UserDAO(dataSource);
        int id = userDAO.doSave(customer);

        // Saving customer fields
        String query = "INSERT INTO " + TABLE_NAME + " (id) VALUES (?)";
        PreparedStatement ps = c.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setInt(1, id);
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();

        if (!rs.next()) {
            throw new SQLException("Error: no generated keys. The Customer has not been saved.");
        }
        int customerId = rs.getInt(1);

        c.close();
        return customerId;

    }

    public Customer doRetrieve(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Customer ID cannot be negative");
        }

        Connection c = dataSource.getConnection();
        UserDAO userDAO = new UserDAO(dataSource);

        System.out.print("Fetching USER with id=" + id + "...");
        User user = userDAO.findByKey(id);
        System.out.println("Done.");

        Customer customer = new Customer();
        customer.setId(user.getId());
        customer.setUsername(user.getUsername());
        customer.setPassword(user.getPassword());
        customer.setFirstName(user.getFirstName());
        customer.setLastName(user.getLastName());
        customer.setDateOfBirth(user.getDateOfBirth());
        customer.setAddresses(findAddressesByCustomerId(id));
        customer.setProducts(findOrderByCustomerId(id));
        return customer;
    }
    public List<Address> findAddressesByCustomerId(int customerId) throws SQLException, IllegalArgumentException {
        if (customerId < 0) {
            throw new IllegalArgumentException("Customer ID cannot be negative");
        }

        List<Address> addresses = new ArrayList<>();
        Connection c = dataSource.getConnection();

        String query = "SELECT * FROM address WHERE customerId = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Address address = new Address();
            address.setId(rs.getInt("id"));
            address.setCustomerId(rs.getInt("customerId"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setPostalCode(rs.getInt("postalCode"));
            addresses.add(address);
        }
        return addresses;
    }

    public List<CustomerOrder> findOrderByCustomerId(int customerId) throws SQLException, IllegalArgumentException {
        if (customerId < 0) {
            throw new IllegalArgumentException("User ID must be greater than 0");
        }

        Connection c = ds.getConnection();

        String query = "SELECT * FROM " + CustomerOrderDAO.TABLE_NAME + " WHERE customerId = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setInt(1, customerId);

        ResultSet rs = ps.executeQuery();
        List<CustomerOrder> orders = new ArrayList<>();
        while (rs.next()) {
            AddressDAO addressDAO = new AddressDAO(ds);
            CustomerOrder order = new CustomerOrder();
            order.setId(rs.getInt("id"));
            order.setCustomerId(rs.getInt("customerId"));
            order.setStatus(CustomerOrder.Status.valueOf(rs.getString("status")));
            order.setAddress(addressDAO.findByKey(rs.getInt("addressId")));
            order.setOrderDate(rs.getDate("orderDate").toLocalDate());
            order.setProducts(findProductsByOrderId(order.getId()));
            orders.add(order);
        }

        c.close();
        return orders;
    }
}
