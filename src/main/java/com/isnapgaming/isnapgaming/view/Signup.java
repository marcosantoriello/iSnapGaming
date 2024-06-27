package com.isnapgaming.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;

import com.isnapgaming.isnapgaming.StorageManagement.DAO.CustomerDAO;
import com.isnapgaming.isnapgaming.UserManagement.Customer;
import com.isnapgaming.isnapgaming.UserManagement.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "Signup", value = "/Signup")
public class Signup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        HttpSession session = request.getSession();
        String redirectUrl = (String) session.getAttribute("redirectSignup");
        System.out.println("Redirect URL: " + redirectUrl);

        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || dateOfBirth == null || email == null || email.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            throw new ServletException("There was an error in signing up. Please try again.");
        }

        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");

        Customer user = new Customer();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setUsername(email);
        user.setPassword(password);

        try {
            CustomerDAO customerDAO = new CustomerDAO(dataSource);
            int id = customerDAO.doSave(user);
            Customer customer = customerDAO.findByKey(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("SQL error");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        // redirecting user where he left before the signup
        response.sendRedirect(redirectUrl);

    }
}