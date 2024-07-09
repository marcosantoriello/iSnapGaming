package com.isnapgaming.view.Guest;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.isnapgaming.OrderManagement.ItemCart;
import com.isnapgaming.StorageManagement.DAO.ProductDAO;
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
        ItemCart prod = null;
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        List<ItemCart> items = cart.getItems();
        for (ItemCart item : items) {
            if (item.getProduct().getProdCode() == prodCode) {
                prod = item;
            }
        }

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

        request.setAttribute("product", product);

        if("add".equals(action)){
            if (prod.getQuantity() + 1 <= product.getQuantity()) {
                cart.addToCart(product, 1);
            } else {
                request.setAttribute("error", "The selected quantity is not available");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
                dispatcher.forward(request, response);
            }


        }else if("decrease".equals(action)){
            cart.decreaseQuantityCart(product, 1);

        }else if("remove".equals(action)){

            cart.removeFromCart(product);
        }


        response.sendRedirect(getServletContext().getContextPath() + "/cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}