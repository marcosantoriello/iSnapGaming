package com.isnapgaming.view;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import com.isnapgaming.UserManagement.User;
import java.util.List;


/**
 * Servlet Filter implementation class AccessControlFilter
 */
@WebFilter(filterName = "/AccessControlFilter", urlPatterns = "/*")
public class AccessControlFilter extends HttpFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        List<String> roles = null;
        String role = (String) httpServletRequest.getSession().getAttribute("role");

        boolean isLogged = false;
        boolean isManager = false;
        boolean isOnlyCustomer = false;
        boolean isProductManager = false;
        boolean isOrderManager = false;

        if(user != null) {
            isLogged = true;
            roles = (List<String>) httpServletRequest.getSession().getAttribute("roles");

            if(roles.size() >= 2)
                isManager = true;

            if(roles.size() == 1 && !("ProductManager".equals(role)) && !("OrderManager".equals(role)))
                isOnlyCustomer = true;

            if("ProductManager".equals(role))
                isProductManager = true;

            if("OrderManager".equals(role))
                isOrderManager = true;
        }

        String path = httpServletRequest.getServletPath();


        if( (!isLogged)  && (
                //Checks for general servlet
                path.contains("/Logout")  || path.contains("/RoleSelection") || path.contains("/PayOrder") ||
                //Checks for ProductManager servlet
                path.contains("/AddProduct") ||
                //Checks for OrderManager servlet
                path.contains("/GetOrderDetails") || path.contains("GetOrdersList") || path.contains("/UpdateStatus") ||


                //Checks for general jsp pages
                path.contains("/checkout.jsp") || path.contains("/confirmationPage.jsp") || path.contains("/roleSelection.jsp") ||
                //Checks for ProductManager jsp pages
                path.contains("/addProduct.jsp") || path.contains("/productManagerDashboard.jsp") ||
                //Checks for OrderManager jsp pages
                path.contains("/orderDetails.jsp") || path.contains("/orderManagerDashboard.jsp") || path.contains("/updateStatus.jsp") || path.contains("/customerOrdersList.jsp")
                )) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.jsp");
            return;
        }

        if( (!isManager && isLogged && isOnlyCustomer)  && (
                //Checks for general servlet
                path.contains("/RoleSelection") || path.contains("/Login") || path.contains("/Signup") ||
                        //Checks for ProductManager servlet
                        path.contains("/AddProduct") ||
                        //Checks for OrderManager servlet
                        path.contains("/GetOrderDetails") || path.contains("GetOrdersList") || path.contains("/UpdateStatus") ||


                        //Checks for general jsp pages
                        path.contains("/roleSelection.jsp") || path.contains("/signup.jsp") ||
                        //Checks for ProductManager jsp pages
                        path.contains("/addProduct.jsp") || path.contains("/productManagerDashboard.jsp") ||
                        //Checks for OrderManager jsp pages
                        path.contains("/orderDetails.jsp") || path.contains("/orderManagerDashboard.jsp") || path.contains("/updateStatus.jsp") || path.contains("/customerOrdersList.jsp")
        )) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.jsp");
            return;
        }

        if( (isManager && isProductManager)  && (
                //Checks for general servlet
                 path.contains("Signup") || path.contains("Login") || path.contains("AddToCart") || path.contains("UpdateCart") || path.contains("PayOrder") ||
                        //Checks for ProductManager servlet

                        //Checks for OrderManager servlet
                        path.contains("/GetOrderDetails") || path.contains("GetOrdersList") || path.contains("/UpdateStatus") ||


                        //Checks for general jsp pages
                        path.contains("/checkout.jsp") || path.contains("/confirmationPage.jsp") || path.contains("/index.jsp") ||
                        path.contains("/login.jsp") || path.contains("/signup.jsp") || path.contains("/cart.jsp") ||
                        //Checks for ProductManager jsp pages

                         //Checks for OrderManager jsp pages
                        path.contains("/orderDetails.jsp") || path.contains("/orderManagerDashboard.jsp") || path.contains("/updateStatus.jsp") || path.contains("/customerOrdersList.jsp")
        )) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/productManagerDashboard.jsp");
            return;
        }

        if( (isManager && isOrderManager)  && (
                //Checks for general servlet
                path.contains("Signup") || path.contains("Login") || path.contains("AddToCart") || path.contains("UpdateCart") ||
                        path.contains("PayOrder") || path.contains("ProductDetails") ||
                        //Checks for ProductManager servlet
                        path.contains("/AddProduct") ||
                        //Checks for OrderManager servlet


                        //Checks for general jsp pages
                        path.contains("/checkout.jsp") || path.contains("/confirmationPage.jsp") || path.contains("/index.jsp") ||
                        path.contains("/login.jsp") || path.contains("/signup.jsp") || path.contains("/cart.jsp") ||
                        //Checks for ProductManager jsp pages
                        path.contains("/addProduct.jsp") || path.contains("/productManagerDashboard.jsp")
                        //Checks for OrderManager jsp pages

        )) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/orderManagerDashboard.jsp");
            return;
        }


        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

    private static final long serialVersionUID = 1L;

}