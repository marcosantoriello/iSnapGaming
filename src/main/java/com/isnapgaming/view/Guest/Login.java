package com.isnapgaming.view.Guest;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.UserManagement.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new ServletException("Wrong email or password.");
        }

        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
        String redirectUrl = (String) request.getSession().getAttribute("redirectLogin");
        if (redirectUrl == null) {
            redirectUrl = "http://localhost:8080/iSnapGaming_war/index.jsp";
        }

        User user = null;
        try {
            UserDAO userDAO = new UserDAO(dataSource);
            user = userDAO.getUserByUsernameAndPassword(email, password);
        } catch (SQLException e) {}

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            List<String> roles = null;
            try {
                UserDAO userDAO = new UserDAO(dataSource);
                roles = userDAO.getUserRoles(user.getId());

            } catch (SQLException e) {
                request.setAttribute("error", "Wrong username or password.");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
            session.setAttribute("roles", roles);

            // Checking number of roles
            if (roles.size() >= 2) {
                // If the user has more than one role, the user must choose one
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/roleSelection.jsp");
                dispatcher.forward(request, response);
            } else if (roles.get(0).equals("Customer")) {
                // If the user is a customer, then I take him back to where he was before the login
                session.setAttribute("role", "Customer");
                response.sendRedirect(redirectUrl);
            } else if (roles.get(0).equals("ProductManager")) {
                session.setAttribute("role", "ProductManager");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productManagerDashboard.jsp");
            } else if (roles.get(0).equals("OrderManager")) {
                session.setAttribute("role", "OrderManager");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/orderManagerDashboard.jsp");
            } else {
                throw new ServletException("Unknown role");
            }

        } else {
            request.setAttribute("error", "Wrong username or password.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp?returnurl="+redirectUrl);
            dispatcher.forward(request, response);
        }
    }
}