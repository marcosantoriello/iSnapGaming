package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.isnapgaming.OrderManagement.Cart;
import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.OrderManagement.OrderCreation;
import com.isnapgaming.OrderManagement.OrderProduct;
import com.isnapgaming.ProductManagement.Product;
import com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO;
import com.isnapgaming.StorageManagement.DAO.ProductDAO;
import com.isnapgaming.UserManagement.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "PayOrder", value = "/PayOrder")
public class PayOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("streetCustomer");
        String cardNumber = request.getParameter("cardNumber");
        LocalDate expirationDate = LocalDate.parse(request.getParameter("expirationDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String cvv = request. getParameter("cvv");


        if (address == null || address.isEmpty()) {
            throw new ServletException("Please, insert a valid address");
        }
        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new ServletException("Please, insert a valid cardNumber");
        }

        if (cvv == null ||cvv.isEmpty()) {
            throw new ServletException("Please, insert a valid expiration date");
        }

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart.getTotalPrice() < 0) {
            throw new ServletException("Not a valid price");
        }

        User customer = (User) session.getAttribute("user");

        CustomerOrder order = OrderCreation.makeOrder(cart, customer, address);
        boolean res = false;
        try {
             res = OrderCreation.pay(order, address, cardNumber, expirationDate, cvv);
        } catch (ServletException e) {
            //response.setAttribute("error", e.getMessage());
            //response.sendRedirect("/errorPage.jsp");
            response.sendRedirect(getServletContext().getContextPath()+"/errorPage.jsp?errorMessage="+e.getMessage());
        }
        int orderId = 0;
        if (res) {
            DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
            try {
                CustomerOrderDAO orderDAO = new CustomerOrderDAO(ds);
                orderId = orderDAO.doSave(order);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("There was an error in saving your order.");
            }
            session.setAttribute("cart", new Cart());
            session.setAttribute("orderId", orderId);

            // Decrementing product quantity in database
            for (OrderProduct orderProduct : order.getProducts()) {
                try {
                    ProductDAO productDAO = new ProductDAO(ds);
                    int quantity = orderProduct.getQuantity();
                    Product product = productDAO.findByKey(orderProduct.getProductId());
                    System.out.println();
                    product.setQuantity(product.getQuantity() - quantity);
                    productDAO.doUpdate(product);
                } catch (SQLException e) {
                    response.sendRedirect(getServletContext().getContextPath()+"/errorPage.jsp?errorMessage="+e.getMessage());
                }

            }
            response.sendRedirect(getServletContext().getContextPath()+"/confirmationPage.jsp");

        }



    }
}