package com.isnapgaming.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.isnapgaming.ProductManagement.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static final String TABLE_NAME = "product";
    DataSource dataSource = null;

    public ProductDAO(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }

    public synchronized int doSave(Product product) throws SQLException, IllegalArgumentException{
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        Connection connection = dataSource.getConnection();

        int prodCode = product.getProdCode();
        String name = product.getName();
        String softwareHouse = product.getSoftwareHouse();
        Product.Platform platform = product.getPlatform();
        int price = product.getPrice();
        int quantity = product.getQuantity();
        Product.Category category = product.getCategory();
        Product.Pegi pegi = product.getPegi();
        int releaseYear = product.getReleaseYear();
        String imagePath = product.getImagePath();

        String query = "INSERT INTO " + ProductDAO.TABLE_NAME +  " (prodCode, name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, prodCode);
        ps.setString(2, name);
        ps.setString(3, softwareHouse);
        ps.setString(4, platform.toString());
        ps.setInt(5, price);
        ps.setInt(6, quantity);
        ps.setString(7, category.toString());
        ps.setString(8, pegi.toString());
        ps.setInt(9, releaseYear);
        ps.setString(10, imagePath);

        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        if (!rs.next()) {
            throw new SQLException("Error: no generated keys. The Product has not been saved.");
        }
        connection.close();
        return rs.getInt(1);
    }

    public synchronized void doUpdate(Product product) throws SQLException, IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        Connection connection = dataSource.getConnection();

        int prodCode = product.getProdCode();
        String name = product.getName();
        String softwareHouse = product.getSoftwareHouse();
        Product.Platform platform = product.getPlatform();
        int price = product.getPrice();
        int quantity = product.getQuantity();
        Product.Category category = product.getCategory();
        Product.Pegi pegi = product.getPegi();
        int releaseYear = product.getReleaseYear();
        String imagePath = product.getImagePath();

        String query = "UPDATE " + ProductDAO.TABLE_NAME + " SET prodCode = ?, name = ?, softwareHouse = ?, platform = ?, price = ?, quantity = ?, category = ?, pegi = ?, releaseYear = ?, imagePath = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.executeUpdate();
        connection.close();
    }

    public synchronized Product findByKey(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        Connection connection = dataSource.getConnection();
        Product product = new Product();

        String query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();


        product.setId(rs.getInt("id"));
        product.setProdCode(rs.getInt("prodCode"));
        product.setName(rs.getString("name"));
        product.setSoftwareHouse(rs.getString("softwareHouse"));
        product.setPlatform(Product.Platform.valueOf(rs.getString("platform")));
        product.setPrice(rs.getInt("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setCategory(Product.Category.valueOf(rs.getString("category")));
        product.setPegi(Product.Pegi.valueOf(rs.getString("pegi")));
        product.setReleaseYear(rs.getInt("releaseYear"));
        product.setImagePath(rs.getString("imagePath"));

        connection.close();
        return product;
    }

    public synchronized Product findByProdCode(int prodCode) throws SQLException, IllegalArgumentException {
        if (prodCode < 0) {
            throw new IllegalArgumentException("Product Code cannot be negative");
        }

        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE prodCode = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, prodCode);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            throw new SQLException("Error: no User found with the given id.");
        }

        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setProdCode(rs.getInt("prodCode"));
        product.setName(rs.getString("name"));
        product.setSoftwareHouse(rs.getString("softwareHouse"));
        product.setPlatform(Product.Platform.valueOf(rs.getString("platform")));
        product.setPrice(rs.getInt("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setCategory(Product.Category.valueOf(rs.getString("category")));
        product.setPegi(Product.Pegi.valueOf(rs.getString("pegi")));
        product.setReleaseYear(rs.getInt("releaseYear"));
        product.setImagePath(rs.getString("imagePath"));

        connection.close();
        return product;
    }

    public synchronized List<Product> findByCategory(Product.Category category) throws SQLException, IllegalArgumentException {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        List<Product> products = new ArrayList<>();
        Connection connection = dataSource.getConnection();

        String query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE category = ?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, category.toString());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product product = new Product();

            product.setId(rs.getInt("id"));
            product.setProdCode(rs.getInt("prodCode"));
            product.setName(rs.getString("name"));
            product.setSoftwareHouse(rs.getString("softwareHouse"));
            product.setPlatform(Product.Platform.valueOf(rs.getString("platform")));
            product.setPrice(rs.getInt("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setCategory(Product.Category.valueOf(rs.getString("category")));
            product.setPegi(Product.Pegi.valueOf(rs.getString("pegi")));
            product.setReleaseYear(rs.getInt("releaseYear"));
            product.setImagePath(rs.getString("imagePath"));
            products.add(product);
        }
        connection.close();
        return products;
    }

    public synchronized List<Product> doRetrieveAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection connection = dataSource.getConnection();

        String query = "SELECT * FROM "  + ProductDAO.TABLE_NAME;
        PreparedStatement ps = connection.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Product product = new Product();

            product.setId(rs.getInt("id"));
            product.setProdCode(rs.getInt("prodCode"));
            product.setName(rs.getString("name"));
            product.setSoftwareHouse(rs.getString("softwareHouse"));
            product.setPlatform(Product.Platform.valueOf(rs.getString("platform")));
            product.setPrice(rs.getInt("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setCategory(Product.Category.valueOf(rs.getString("category")));
            product.setPegi(Product.Pegi.valueOf(rs.getString("pegi")));
            product.setReleaseYear(rs.getInt("releaseYear"));
            product.setImagePath(rs.getString("imagePath"));
            products.add(product);
        }

        connection.close();
        return products;
    }
}
