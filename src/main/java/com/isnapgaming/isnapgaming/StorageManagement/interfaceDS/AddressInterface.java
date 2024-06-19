package com.isnapgaming.isnapgaming.StorageManagement.interfaceDS;

import com.isnapgaming.isnapgaming.UserManagement.Address;
import com.isnapgaming.isnapgaming.UserManagement.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface AddressInterface {
    public int doSave(Address address) throws SQLException, IllegalArgumentException;

    public void doUpdate(Address address) throws SQLException, IllegalArgumentException;

    public Address findByKey(int id) throws SQLException, IllegalArgumentException;

    public List<Address> findByUserId(int userId) throws SQLException, IllegalArgumentException;

    public void doDelete(int id) throws SQLException, IllegalArgumentException;
}
