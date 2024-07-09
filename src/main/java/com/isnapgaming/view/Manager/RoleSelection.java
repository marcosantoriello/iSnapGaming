package com.isnapgaming.view.Manager;

import java.io.*;

import com.isnapgaming.OrderManagement.Cart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RoleSelection", value = "/RoleSelection")
public class RoleSelection extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String roleSelected = request.getParameter("role");
        HttpSession session = request.getSession();
        String redirectUrl = (String) session.getAttribute("redirectLogin");

        if (roleSelected == null || roleSelected.isEmpty()) {
            request.setAttribute("error", "Please select a role");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/roleSelection.jsp");
            dispatcher.forward(request, response);
            throw new ServletException("Unknown role");
        }

        if (roleSelected.equals("Customer")){
            // If the user is a customer, then I take him back to where he was before the login
            session.setAttribute("role", "Customer");
            response.sendRedirect(redirectUrl);
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