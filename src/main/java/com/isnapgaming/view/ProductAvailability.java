package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.UserManagement.ProductManager;
import com.isnapgaming.UserManagement.User;
import com.isnapgaming.OrderManagement.Cart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.isnapgaming.ProductManagement.Product;

import javax.sql.DataSource;

@WebServlet(name = "ProductAvailability", value = "/ProductAvailability")
public class ProductAvailability extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");

        int productCode = Integer.parseInt(request.getParameter("prodCode"));
        String action = request.getParameter("action");

        ProductManager productManager = new ProductManager();
        Product product = null;

        if("makeUnavailable".equals(action)) {
            try {
                product = productManager.getProductByProdCode(productCode, ds);
                product = productManager.removeProduct(product, ds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if("makeAvailable".equals(action)){
            try {
                product = productManager.getProductByProdCode(productCode, ds);
                product = productManager.makeProductAvailable(product, ds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        request.setAttribute("product", product);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productManagerDashboard.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}