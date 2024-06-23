package com.isnapgaming.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.isnapgaming.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.isnapgaming.UserManagement.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "RoleSelection", value = "/RoleSelection")
public class RoleSelection extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String roleSelected = request.getParameter("role");
        HttpSession session = request.getSession();

        //TO-DELETE
        System.out.println("Ruolo selezionato: " + roleSelected);

        if (roleSelected == null || roleSelected.isEmpty()) {
            throw new ServletException("Unknown role");
        }

        if (roleSelected.equals("Customer")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            session.setAttribute("role", "Customer");
            dispatcher.forward(request, response);
        } else if (roleSelected.equals("ProductManager")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productManagerDashboard.jsp");
            session.setAttribute("role", "ProductManager");
            dispatcher.forward(request, response);
        } else if (roleSelected.equals("OrderManager")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/orderManagerDashboard.jsp");
            session.setAttribute("role", "OrderManager");
            dispatcher.forward(request, response);
        } else {
            throw new ServletException("Unknown role");
        }
    }
}