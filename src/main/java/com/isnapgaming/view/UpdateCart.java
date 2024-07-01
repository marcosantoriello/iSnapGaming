package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.isnapgaming.StorageManagement.DAO.ProductDAO;
import com.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.UserManagement.User;
import com.isnapgaming.OrderManagement.Cart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;


import com.isnapgaming.ProductManagement.Product;

@WebServlet(name = "UpdateCart", value = "/UpdateCart")
public class UpdateCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

        String action = (String) request.getParameter("action");
        int prodCode = Integer.parseInt(request.getParameter("prodCode"));

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Product product = null;
        ProductDAO pDAO = null;

        try {
            pDAO = new ProductDAO(ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            product = pDAO.findByProdCode(prodCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        if("add".equals(action)){

            cart.addToCart(product, 1);

        }else if("decrease".equals(action)){
            cart.decreaseQuantityCart(product, 1);

        }else if("remove".equals(action)){

            cart.removeFromCart(product);
        }



        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}