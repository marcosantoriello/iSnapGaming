package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;

import com.isnapgaming.UserManagement.ProductManager;
import com.isnapgaming.ProductManagement.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "ManageProduct", value = "/ManageProduct")
public class ManageProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
        Product product = null;

        int productCode = Integer.parseInt(request.getParameter("prodCode"));

        ProductManager productManager = new ProductManager();
        try {
            product  = productManager.getProductByProdCode(productCode,ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("product", product);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateProduct.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}