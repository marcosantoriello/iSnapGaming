package com.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.UserManagement.User;
import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.UserManagement.Address;
import com.isnapgaming.UserManagement.Customer;

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

    public CustomerDAO(DataSource dataSource) throws SQLException{
        this.dataSource = dataSource;
    }

    public synchronized int doSave(Customer customer) throws SQLException, IllegalArgumentException {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        Connection connection = dataSource.getConnection();
        // Saving user fields
        UserDAO userDAO = new UserDAO(dataSource);
        int id = userDAO.doSave(customer);

        // Saving customer fields
        String query = "INSERT INTO " + TABLE_NAME + " (id) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setInt(1, id);
        ps.execute();
        connection.close();
        return id;
    }

    public synchronized Customer findByKey(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Customer ID cannot be negative");
        }

        Connection connection = dataSource.getConnection();
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

        connection.close();
        return customer;
    }
    private synchronized List<Address> findAddressesByCustomerId(int customerId) throws SQLException, IllegalArgumentException {
        if (customerId < 0) {
            throw new IllegalArgumentException("Customer ID cannot be negative");
        }

        Connection connection = dataSource.getConnection();
        List<Address> addresses = new ArrayList<>();

        String query = "SELECT * FROM address WHERE customerId = ?";
        PreparedStatement ps = connection.prepareStatement(query);

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
        connection.close();
        return addresses;
    }

}
