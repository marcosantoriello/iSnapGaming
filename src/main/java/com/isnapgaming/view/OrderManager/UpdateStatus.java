package com.isnapgaming.view.OrderManager;

import java.io.*;
import java.sql.SQLException;

import com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO;
import com.isnapgaming.UserManagement.OrderManager;
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
        HttpSession session = request.getSession();

        String newStatus = request.getParameter("newStatus");
        String currentStatus = request.getParameter("currentStatus");
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        CustomerOrderDAO customerOrderDAO = null;
        CustomerOrder customerOrder = null;

        try {
            customerOrderDAO = new CustomerOrderDAO(ds);
            customerOrder = customerOrderDAO.findByKey(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        OrderManager orderManager = new OrderManager();

        if("UNDER_PREPARATION".equals(newStatus) && "SHIPPED".equals(currentStatus)){
            try {
                orderManager.restoreOrder(customerOrder,ds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if("UNDER_PREPARATION".equals(newStatus) && "READY_FOR_SENDING".equals(currentStatus) ){
            try {
                orderManager.replaceProduct(customerOrder,ds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if("UNDER_PREPARATION".equals(newStatus) ){
            try {
                orderManager.checkProduct(customerOrder,ds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if("READY_FOR_SENDING".equals(newStatus)){
            try {
                orderManager.packProduct(customerOrder,ds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if("SHIPPED".equals(newStatus)){
            try {
                orderManager.contactCourier(customerOrder,ds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if("DELIVERED".equals(newStatus)){
            try {
                orderManager.confirmDelivery(customerOrder,ds);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        request.setAttribute("customerOrder", customerOrder);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/updateStatus.jsp");
        dispatcher.forward(request, response);


    }
}