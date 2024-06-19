package com.isnapgaming.isnapgaming.StorageManagement.interfaceDS;

import com.isnapgaming.isnapgaming.UserManagement.User;
import com.isnapgaming.isnapgaming.UserManagement.Address;

import java.sql.*;
import java.util.List;
import java.util.Set;

public interface UserInterface {
    public int doSave(User user) throws SQLException, IllegalArgumentException;
    public void doUpdate(User user) throws SQLException, IllegalArgumentException;
    public User findByKey(int id) throws SQLException, IllegalArgumentException;
    public User findByUsername(String username) throws SQLException, IllegalArgumentException;
    public Set<User> doRetrieveAll() throws SQLException;
    public List<Address> findAddressesByUserId(int userId) throws SQLException, IllegalArgumentException;
}
