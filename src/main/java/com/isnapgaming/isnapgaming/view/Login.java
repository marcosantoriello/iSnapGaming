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

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new ServletException("Wrong email or password.");
        }

        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");

        User user;
        try {
            UserDAO userDAO = new UserDAO(dataSource);
            user = userDAO.getUserByUsernameAndPassword(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("SQL error");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            List<String> roles;
            try {
                UserDAO userDAO = new UserDAO(dataSource);
                roles = userDAO.getUserRoles(user.getId());

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("SQL error");
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }

            session.setAttribute("roles", roles);
            // TO-DELETE
            System.out.println("Roles: ");
            List<String> sess_roles = (List<String>) session.getAttribute("roles");
            for (String role : sess_roles) {
                System.out.println(role);
            }

            response.sendRedirect("/roleSelection.jsp");

        } else {
            request.setAttribute("error", "Wrong username or password.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}