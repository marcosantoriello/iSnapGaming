package com.isnapgaming.UserManagement;

import com.isnapgaming.ProductManagement.Product;
import com.isnapgaming.StorageManagement.DAO.ProductDAO;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ProductManager extends Manager{

    public ProductManager() {
        super();
    }

    // Business Logic methods
    public synchronized void addProduct(Product product, DataSource dataSource) throws SQLException {
        ProductDAO productDAO = new ProductDAO(dataSource);
        productDAO.doSave(product);
    }

    public synchronized void updateProduct(Product product, DataSource dataSource) throws SQLException {
        ProductDAO productDAO = new ProductDAO(dataSource);
        productDAO.doUpdate(product);
    }

    public synchronized Product removeProduct(Product product, DataSource dataSource) throws SQLException {
        ProductDAO productDAO = new ProductDAO(dataSource);
        product.setAvailable(false);
        productDAO.doUpdate(product);
        return product;
    }

    public synchronized Product makeProductAvailable(Product product, DataSource dataSource) throws SQLException {
        ProductDAO productDAO = new ProductDAO(dataSource);
        product.setAvailable(true);
        productDAO.doUpdate(product);
        return product;
    }
    public synchronized Product getProductByProdCode(int prodCode, DataSource dataSource) throws SQLException {
        ProductDAO productDAO = new ProductDAO(dataSource);
        return productDAO.findByProdCode(prodCode);
    }

    public List<Product> getAllProducts(DataSource dataSource) throws SQLException {
        ProductDAO productDAO = new ProductDAO(dataSource);
        return productDAO.doRetrieveAll();
    }
}
