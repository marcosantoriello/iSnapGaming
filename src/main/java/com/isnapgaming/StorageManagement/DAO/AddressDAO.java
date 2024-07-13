package com.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.UserManagement.Address;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    DataSource dataSource = null;
    private static final String TABLE_NAME = "address";

    public AddressDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public synchronized int doSave(Address address) throws SQLException, IllegalArgumentException {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        Connection connection = dataSource.getConnection();

        String query = "INSERT INTO " + AddressDAO.TABLE_NAME + " (customerId, street, city, postalCode) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, address.getCustomerId());
        ps.setString(2, address.getStreet());
        ps.setString(3, address.getCity());
        ps.setInt(4, address.getPostalCode());

        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        if (!rs.next()) {
            throw new SQLException("Error: no generated keys. The Address has not been saved.");
        }
        int addressId = rs.getInt(1);

        connection.close();
        return addressId;
    }

    public synchronized Address findByKey(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        Connection connection = dataSource.getConnection();

        String query = "SELECT * FROM " + AddressDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException("No Address found with the given id");
        }

        Address address = new Address(rs.getInt("customerId"), rs.getString("street"), rs.getString("city"), rs.getInt("postalCode"));
        address.setId(rs.getInt("id"));

        connection.close();
        return address;
    }

    public synchronized List<Address> findByCustomerId(int customerId) throws SQLException, IllegalArgumentException {
        if (customerId < 0) {
            throw new IllegalArgumentException("CustomerId cannot be negative");
        }

        List<Address> addresses = new ArrayList<>();

        Connection connection = dataSource.getConnection();

        String query = "SELECT * FROM " + AddressDAO.TABLE_NAME + " WHERE customerId = ?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, customerId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Address address = new Address();
            address.setId(rs.getInt("id"));
            address.setCustomerId(rs.getInt("customerId"));
            // I don't set the orderId because customer address is not linked with any order (so it's equal to 0)
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setPostalCode(rs.getInt("postalCode"));
            addresses.add(address);
        }

        connection.close();
        return addresses;
    }
}
