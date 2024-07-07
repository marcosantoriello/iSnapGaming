package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;

import com.isnapgaming.UserManagement.ProductManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

import com.isnapgaming.StorageManagement.DAO.ProductDAO;
import com.isnapgaming.ProductManagement.Product;

@WebServlet(name = "ProductUpdater", value = "/ProductUpdater")
public class ProductUpdater extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
        ProductDAO productDAO = null;
        ProductManager productManager = new ProductManager();

        int productId = Integer.parseInt(request.getParameter("productId"));
        int productCode = Integer.parseInt(request.getParameter("productCode"));
        String nameProduct = request.getParameter("nameProduct");
        String softwareHouseProduct = request.getParameter("softwareHouseProduct");
        String platformProduct = request.getParameter("platformProduct");
        String categoryProduct = request.getParameter("categoryProduct");
        String pegiProduct = request.getParameter("pegiProduct");
        int releaseYearProduct = Integer.parseInt(request.getParameter("releaseYearProduct"));
        int quantityProduct = Integer.parseInt(request.getParameter("quantityProduct"));
        int priceProduct = Integer.parseInt(request.getParameter("priceProduct"));

        Product product = new Product();
        product.setId(productId);
        product.setProdCode(productCode);
        product.setName(nameProduct);
        product.setSoftwareHouse(softwareHouseProduct);
        product.setPlatform(Product.Platform.valueOf(platformProduct));
        product.setCategory(Product.Category.valueOf(categoryProduct));
        product.setPegi(Product.Pegi.valueOf(pegiProduct));
        product.setReleaseYear(releaseYearProduct);
        product.setQuantity(quantityProduct);
        product.setPrice(priceProduct);
        product.setAvailable(true);

        try {
            productManager.updateProduct(product,ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        request.setAttribute("product", product);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productManagerDashboard.jsp");
        dispatcher.forward(request, response);
    }
}