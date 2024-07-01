package com.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.UserManagement.Address;
import com.isnapgaming.UserManagement.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static final String TABLE_NAME = "user";
    DataSource dataSource = null;

    public UserDAO(DataSource dataSource) throws SQLException{
        this.dataSource = dataSource;
    }

    public synchronized int doSave(User user) throws SQLException, IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        Connection connection = dataSource.getConnection();

        String username = user.getUsername();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        LocalDate dateOfBirth = user.getDateOfBirth();

        String query = "INSERT INTO " + UserDAO.TABLE_NAME + " (username, password, firstName, lastName, dateOfBirth) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

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

        connection.close();
        return userId;
    }

    public synchronized User getUserByUsernameAndPassword(String username, String password) throws SQLException, IllegalArgumentException {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Cannot have empty username or password");
        }
        Connection connection = dataSource.getConnection();

        String query = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE username = ? AND password = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
                return user;
            } else {
                throw new SQLException("No User found with the given credentials");
            }
        } catch (SQLException e) {
            throw new SQLException("No User found with the given credentials");
        } finally {
            connection.close();
        }
    }
    // Retrieving all the roles associated with a user
    public synchronized List<String> getUserRoles(int userId) throws SQLException {
        if (userId < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        // Checking if user exists in db or not
        this.findByKey(userId);
        Connection connection = dataSource.getConnection();
        List<String> userRoles = new ArrayList<>();
        if (isCustomer(userId)) {
            userRoles.add("Customer");
        }
        if (isOrderManager(userId)) {
            userRoles.add("OrderManager");
        }
        if (isProductManager(userId)) {
            userRoles.add("ProductManager");
        }
        connection.close();
        return userRoles;
    }

    private boolean isCustomer(int userId) throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM Customer WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        boolean result = rs.next();
        connection.close();
        return result;
    }

    private boolean isOrderManager(int userId) throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM OrderManager WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        boolean result = rs.next();
        connection.close();
        return result;
    }

    private boolean isProductManager(int userId) throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM ProductManager WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        boolean result = rs.next();
        connection.close();
        return result;
    }

    public synchronized User findByKey(int userId) throws SQLException, IllegalArgumentException {
        if (userId < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException("No User found with the given id");
        }

        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());

        connection.close();
        return user;
    }

    public synchronized User findByUsername(String username) throws SQLException, IllegalArgumentException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException("No User found with the given username");
        }

        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());

        connection.close();
        return user;
    }
}
