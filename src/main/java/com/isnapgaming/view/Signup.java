package com.isnapgaming.view;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;

import com.isnapgaming.StorageManagement.DAO.AddressDAO;
import com.isnapgaming.StorageManagement.DAO.CustomerDAO;
import com.isnapgaming.StorageManagement.DAO.UserDAO;
import com.isnapgaming.UserManagement.Address;
import com.isnapgaming.UserManagement.Customer;
import com.isnapgaming.UserManagement.User;
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

        String street = request.getParameter("street");
        String city = request.getParameter("city");
        int postalCode = Integer.parseInt(request.getParameter("postalCode"));

        HttpSession session = request.getSession();
        String redirectUrl = (String) session.getAttribute("redirectSignup");
        System.out.println("Redirect URL: " + redirectUrl);
        boolean exists = false;

        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || dateOfBirth == null || email == null || email.isEmpty() ||
                password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || !(password.equals(confirmPassword)) || street == null || street.isEmpty() || city == null || city.isEmpty() || postalCode < 0) {
            throw new ServletException("There was an error in signing up. Please try again.");
        }


        DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");

        Customer user = new Customer();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setUsername(email);
        user.setPassword(password);

        Address address;

        // Checking if user already exists
        UserDAO userDAO = null;
        try {
            userDAO = new UserDAO(dataSource);
            User tempUser = userDAO.findByUsername(user.getUsername());
            if (user.getUsername().toLowerCase().equals(tempUser.getUsername().toLowerCase())) {
                exists = true;
            }
        } catch (SQLException e) {
            // Do nothing
        }

        if (!exists) {
            try {
                CustomerDAO customerDAO = new CustomerDAO(dataSource);
                int id = customerDAO.doSave(user);
                Customer customer = customerDAO.findByKey(id);
                AddressDAO addressDAO = new AddressDAO(dataSource);
                address = Address.makeAddress(id, street, city, postalCode);
                addressDAO.doSave(address);
                customer.addAddress(address);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("SQL error");
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
            // redirecting user where he left before the signup
            response.sendRedirect(redirectUrl);
        } else {
            request.setAttribute("error", "That user already exists.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signup.jsp");
            dispatcher.forward(request, response);
        }
    }
}