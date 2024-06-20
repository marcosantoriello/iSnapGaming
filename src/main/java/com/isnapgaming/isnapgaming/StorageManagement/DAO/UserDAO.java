package com.isnapgaming.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.isnapgaming.UserManagement.Address;
import com.isnapgaming.isnapgaming.UserManagement.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAO {
    public static final String TABLE_NAME = "user";
    DataSource dataSource = null;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int doSave(User user) throws SQLException, IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Connection c = dataSource.getConnection();

        String username = user.getUsername();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        LocalDate dateOfBirth = user.getDateOfBirth();

        String query = "INSERT INTO " + UserDAO.TABLE_NAME + " (username, password, firstName, lastName, dateOfBirth) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, firstName);
        ps.setString(4, lastName);
        ps.setDate(5, java.sql.Date.valueOf(dateOfBirth));

        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        if (!rs.next()) {
            throw new SQLException("Error: no generated keys. The User has not been saved.");
        }
        int userId = rs.getInt(1);

        c.close();
        return userId;
    }

    public void doUpdate(User user) throws SQLException, IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Connection c = dataSource.getConnection();

        int id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        LocalDate dateOfBirth = user.getDateOfBirth();

        String query = "UPDATE " + UserDAO.TABLE_NAME + " SET username = ?, password = ?, firstName = ?, lastName = ?, dateOfBirth = ? WHERE id = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, firstName);
        ps.setString(4, lastName);
        ps.setDate(5, java.sql.Date.valueOf(dateOfBirth));
        ps.setInt(6, id);

        ps.execute();

        c.close();
    }

    public User findByKey(int id) throws SQLException, IllegalArgumentException {
        Connection c = dataSource.getConnection();

        String query = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Error: no User found with the given id.");
        }

        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());

        c.close();
        return user;
    }

    public User findByUsername(String username) throws SQLException, IllegalArgumentException {
        Connection c = dataSource.getConnection();

        String query = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE username = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Error: no User found with the given username.");
        }

        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());

        c.close();
        return user;
    }

    public Set<User> doRetrieveAll() throws SQLException {
        Connection c = dataSource.getConnection();

        String query = "SELECT * FROM " + UserDAO.TABLE_NAME;
        PreparedStatement ps = c.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        Set<User> users = new HashSet<>();
        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
            users.add(user);
        }

        c.close();
        return users;
    }

    public List<Address> findAddressesByUserId(int userId) throws SQLException, IllegalArgumentException {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }

        List<Address> addresses = new ArrayList<>();
        Connection c = dataSource.getConnection();

        String query = "SELECT * FROM address WHERE customerId = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setInt(1, userId);
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

}
