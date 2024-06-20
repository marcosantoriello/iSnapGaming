package com.isnapgaming.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.isnapgaming.UserManagement.Address;

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

    public int doSave(Address address) throws SQLException, IllegalArgumentException {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        Connection c = dataSource.getConnection();

        String query = "INSERT INTO " + AddressDAO.TABLE_NAME + " (customerId, street, city, postalCode) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

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

        c.close();
        return addressId;
    }

    public void doUpdate(Address address) throws SQLException, IllegalArgumentException {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        Connection c = dataSource.getConnection();

        String query = "UPDATE " + AddressDAO.TABLE_NAME + " SET customerId = ?, street = ?, city = ?, postalCode = ? WHERE id = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setInt(1, address.getId());
        ps.setInt(2, address.getCustomerId());
        ps.setString(3, address.getStreet());
        ps.setString(4, address.getCity());
        ps.setInt(5, address.getPostalCode());

        ps.execute();

        c.close();
    }

    public Address findByKey(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        Connection c = dataSource.getConnection();

        String query = "SELECT * FROM " + AddressDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Error: no Address found with id " + id);
        }

        Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setCustomerId(rs.getInt("customerId"));
        address.setStreet(rs.getString("street"));
        address.setCity(rs.getString("city"));
        address.setPostalCode(rs.getInt("postalCode"));

        c.close();
        return address;
    }

    public List<Address> findByCustomerId(int customerId) throws SQLException, IllegalArgumentException {
        if (customerId < 0) {
            throw new IllegalArgumentException("CustomerId cannot be negative");
        }

        List<Address> addresses = new ArrayList<>();

        Connection c = dataSource.getConnection();

        String query = "SELECT * FROM " + AddressDAO.TABLE_NAME + " WHERE customerId = ?";
        PreparedStatement ps = c.prepareStatement(query);

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

        c.close();
        return addresses;
    }


    public void doDelete(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        Connection c = dataSource.getConnection();
        String query = "DELETE FROM " + AddressDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setInt(1, id);
        ps.execute();

        c.close();
    }
}
