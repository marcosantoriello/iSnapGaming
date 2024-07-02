package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO;
import com.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.UserManagement.User;
import com.isnapgaming.OrderManagement.Cart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.isnapgaming.OrderManagement.CustomerOrder;

import javax.sql.DataSource;

@WebServlet(name = "UpdateStatus", value = "/UpdateStatus")
public class UpdateStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");

        String newStatus = request.getParameter("newStatus");
        int orderId = Integer.parseInt(request.getParameter("orderId"));



        CustomerOrderDAO coDAO = null;

        try {
            coDAO = new CustomerOrderDAO(ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            coDAO.updateStatus(orderId, CustomerOrder.Status.valueOf(newStatus));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CustomerOrder order = null;
        try {
            order = coDAO.findByKey(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("order", order);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateStatus.jsp");
        dispatcher.forward(request, response);


    }
}