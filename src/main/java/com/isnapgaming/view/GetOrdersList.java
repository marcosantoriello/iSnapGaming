package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.UserManagement.User;
import com.isnapgaming.OrderManagement.Cart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO;
import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.UserManagement.OrderManager;

import javax.sql.DataSource;

@WebServlet(name = "GetOrdersList", value = "/GetOrdersList")
public class GetOrdersList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");

        OrderManager orderManager = new OrderManager();
        List<CustomerOrder> orders = null;

        try {
             orders = orderManager.getAllCustomerOrders(ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("orders", orders);


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customerOrdersList.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}