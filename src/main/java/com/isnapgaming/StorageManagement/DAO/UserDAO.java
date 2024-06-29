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

    public synchronized void doUpdate(User user) throws SQLException, IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Connection connection = dataSource.getConnection();
        int id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        LocalDate dateOfBirth = user.getDateOfBirth();

        String query = "UPDATE " + UserDAO.TABLE_NAME + " SET username = ?, password = ?, firstName = ?, lastName = ?, dateOfBirth = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, firstName);
        ps.setString(4, lastName);
        ps.setDate(5, java.sql.Date.valueOf(dateOfBirth));
        ps.setInt(6, id);

        ps.execute();
        connection.close();
    }
    public synchronized User getUserByUsernameAndPassword(String username, String password) throws SQLException, IllegalArgumentException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        Connection connection = dataSource.getConnection();

        String query = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE username = ? AND password = ?";
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
        }
        connection.close();
        return null;
    }
    // Retrieving all the roles associated with a user
    public synchronized List<String> getUserRoles(int userId) throws SQLException {
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
        connection.close();
        return rs.next();
    }

    private boolean isOrderManager(int userId) throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM OrderManager WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        connection.close();
        return rs.next();
    }

    private boolean isProductManager(int userId) throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM ProductManager WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        connection.close();
        return rs.next();
    }

    // Methods to add a new role to an existing user
    public synchronized void assignProductManager(int userId) throws SQLException, IllegalArgumentException {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
        Connection connection = dataSource.getConnection();
        String query = "INSERT INTO ProductManager (id) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        ps.executeUpdate();
        connection.close();
    }

    public synchronized void assignOrderManager(int userId) throws SQLException, IllegalArgumentException {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
        Connection connection = dataSource.getConnection();
        String query = "INSERT INTO OrderManager (id) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        ps.executeUpdate();
        connection.close();
    }

    public synchronized void assignCustomer(int userId) throws SQLException, IllegalArgumentException {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
        Connection connection = dataSource.getConnection();
        String query = "INSERT INTO Customer (id) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, userId);
        ps.executeUpdate();
        connection.close();
    }

    public synchronized User findByKey(int id) throws SQLException, IllegalArgumentException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);

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
            throw new SQLException("Error: no User found with the given username.");
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

    public synchronized List<User> doRetrieveAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM " + UserDAO.TABLE_NAME;
        PreparedStatement ps = connection.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        List<User> users = new ArrayList<>();
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
        connection.close();
        return users;
    }

    public synchronized List<Address> findAddressesByUserId(int userId) throws SQLException, IllegalArgumentException {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
        Connection connection = dataSource.getConnection();
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
        connection.close();
        return addresses;
    }
}
