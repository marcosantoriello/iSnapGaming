package com.isnapgaming.view.Guest;

import java.io.*;
import java.sql.SQLException;

import com.isnapgaming.StorageManagement.DAO.ProductDAO;
import com.isnapgaming.UserManagement.User;
import com.isnapgaming.OrderManagement.Cart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;


import com.isnapgaming.ProductManagement.Product;


@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

        int quantitySelected = 0;
        int prodCode = 0;

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        prodCode = Integer.parseInt(request.getParameter("prodCode"));
        quantitySelected = Integer.parseInt(request.getParameter("quantitySelected"));

        ProductDAO pDAO = null;

        try {
                pDAO = new ProductDAO(ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Product product = null;

        try {
            product = pDAO.findByProdCode(prodCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("product", product);

        if (quantitySelected <= product.getQuantity()) {
            cart.addToCart(product, quantitySelected);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "The selected quantity is not available");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product.jsp");
            dispatcher.forward(request, response);
        }
    }
}