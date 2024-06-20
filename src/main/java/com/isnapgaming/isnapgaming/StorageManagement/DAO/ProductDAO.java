package com.isnapgaming.isnapgaming.StorageManagement.DAO;

import com.isnapgaming.isnapgaming.ProductManagement.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ProductDAO {

    public static final String TABLE_NAME = "product";
    DataSource dataSource = null;

    public ProductDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int doSave(Product product) throws SQLException, IllegalArgumentException{
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        Connection c = dataSource.getConnection();

        String name = product.getName();
        String softwareHouse = product.getSoftwareHouse();
        Product.Platform platform = product.getPlatform();
        int price = product.getPrice();
        int quantity = product.getQuantity();
        Product.Category category = product.getCategory();
        Product.Pegi pegi = product.getPegi();
        int releaseYear = product.getReleaseYear();
        String imagePath = product.getImagePath();

        String query = "INSERT INTO " + ProductDAO.TABLE_NAME +  " (name, softwareHouse, platform, price, quantity, category, pegi, releaseYear, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, name);
        ps.setString(2, softwareHouse);
        ps.setString(3, platform.toString());
        ps.setInt(4, price);
        ps.setInt(5, quantity);
        ps.setString(6, category.toString());
        ps.setString(7, pegi.toString());
        ps.setInt(8, releaseYear);
        ps.setString(9, imagePath);

        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        if (!rs.next()) {
            throw new SQLException("Error: no generated keys. The Product has not been saved.");
        }
        int productId = rs.getInt(1);

        c.close();
        return productId;
    }

    public void doUpdate(Product product) throws SQLException, IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        Connection c = dataSource.getConnection();

        String name = product.getName();
        String softwareHouse = product.getSoftwareHouse();
        Product.Platform platform = product.getPlatform();
        int price = product.getPrice();
        int quantity = product.getQuantity();
        Product.Category category = product.getCategory();
        Product.Pegi pegi = product.getPegi();
        int releaseYear = product.getReleaseYear();
        String imagePath = product.getImagePath();

        String query = "UPDATE " + ProductDAO.TABLE_NAME + " SET name = ?, softwareHouse = ?, platform = ?, price = ?, quantity = ?, category = ?, pegi = ?, releaseYear = ?, imagePath = ? WHERE id = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.executeUpdate();

        c.close();
    }

    public Product findByKey(int id) throws SQLException, IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        Product product = new Product();

        Connection c = dataSource.getConnection();

        String query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE id = ?";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setSoftwareHouse(rs.getString("softwareHouse"));
            product.setPlatform(Product.Platform.valueOf(rs.getString("platform")));
            product.setPrice(rs.getInt("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setCategory(Product.Category.valueOf(rs.getString("category")));
            product.setPegi(Product.Pegi.valueOf(rs.getString("pegi")));
            product.setReleaseYear(rs.getInt("releaseYear"));
            product.setImagePath(rs.getString("imagePath"));
        }

        c.close();
        return product;
    }

    public Set<Product> findByCategory(Product.Category category) throws SQLException, IllegalArgumentException {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        Connection c = dataSource.getConnection();
        Set<Product> products = new HashSet<>();


        String query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE category = ?";
        PreparedStatement ps = c.prepareStatement(query);

        ps.setString(1, category.toString());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product product = new Product();

            product.setId(rs.getInt("id"));
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

        c.close();

        return products;
    }

    public Set<Product> doRetrieveAll() throws SQLException {
        Connection c = dataSource.getConnection();
        Set<Product> products = new HashSet<>();

        String query = "SELECT * FROM "  + ProductDAO.TABLE_NAME;
        PreparedStatement ps = c.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            Product product = new Product();

            product.setId(rs.getInt("id"));
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

        c.close();

        return products;
    }
}
