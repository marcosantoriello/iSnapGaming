package com.isnapgaming.view.OrderManager;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.isnapgaming.OrderManagement.CustomerOrder;
import com.isnapgaming.StorageManagement.DAO.*;
import com.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.UserManagement.User;
import com.isnapgaming.OrderManagement.Cart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "GetOrderDetails", value = "/GetOrderDetails")
public class GetOrderDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
        CustomerOrderDAO coDAO = null;

        try {
            coDAO = new CustomerOrderDAO(ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CustomerOrder customerOrder = null;
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        try {
            customerOrder = coDAO.findByKey(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("customerOrder", customerOrder);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateStatus.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}