package com.isnapgaming.isnapgaming.StorageManagement.interfaceDS;

import com.isnapgaming.isnapgaming.ProductManagement.Product;

import java.sql.SQLException;
import java.util.Set;

public interface ProductInterface {
    public int doSave(Product product) throws SQLException, IllegalArgumentException;
    public void doUpdate(Product product) throws SQLException, IllegalArgumentException;
    public Product findByKey(int id) throws SQLException, IllegalArgumentException;
    public Set<Product> findByCategory(Product.Category category) throws SQLException, IllegalArgumentException;
    //public Set<Product> doRetrieveByOrder(Order order) throws SQLException, IllegalArgumentException; // TO-DO: BEAN ORDER
    public Set<Product> doRetrieveAll() throws SQLException;


}
