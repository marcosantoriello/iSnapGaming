package com.isnapgaming.view.Guest;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

import com.isnapgaming.StorageManagement.DAO.ProductDAO;
import com.isnapgaming.ProductManagement.Product;
@WebServlet(name = "ProductDetails", value = "/ProductDetails")
public class ProductDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
        ProductDAO productDAO = null;

        try {
            productDAO = new ProductDAO(ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Product product = null;
        int productCode = Integer.parseInt(request.getParameter("prodCode"));

        try {
            product = productDAO.findByProdCode(productCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        request.setAttribute("product", product);

        HttpSession session = request.getSession();
        String isProductManager = (String) session.getAttribute("role");

        if("ProductManager".equals(isProductManager)){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productDetailsPM.jsp");
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}