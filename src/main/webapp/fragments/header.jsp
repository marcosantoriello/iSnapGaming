<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Header</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="styles/header.css">

    <%
        String role = (String) session.getAttribute("role");
        List<String> userRoles = (List<String>) session.getAttribute("roles");

        String currentPage = request.getRequestURI();
    %>

</head>
<body>
<nav class="navbar navbar-light" style="background-color: #0e6ac7 !important; padding: 20px;">
    <%
        if ("ProductManager".equals(role)) {
    %>
    <a class="navbar-brand" href="productManagerDashboard.jsp">
        <img src="app_imgs/logo.png" width="50" height="50" alt="">
    </a>
    <%
    } else if ("OrderManager".equals(role)) {
    %>
    <a class="navbar-brand" href="orderManagerDashboard.jsp">
        <img src="app_imgs/logo.png" width="50" height="50" alt="">
    </a>
    <%
    } else {
    %>
    <a class="navbar-brand" href="index.jsp">
        <img src="app_imgs/logo.png" width="50" height="50" alt="">
    </a>
    <%
        }
    %>
    <%
        if(!(currentPage.contains("login.jsp")) && !(currentPage.contains("roleSelection.jsp")) && !(currentPage.contains("signup.jsp"))){
    %>
    <form class="form-inline  justify-content-center">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
            <i class="fas fa-search icon-finder"></i>
        </button>
    </form>
    <div class="ml-auto">
        <%
            if(!("ProductManager".equals(role)) && !("OrderManager".equals(role)) && !(currentPage.contains("cart.jsp"))){
        %>
        <a href="cart.jsp" class="btn btn-outline-info mr-2 button-hover-effect">
            <i class="fas fa-shopping-cart mr-1 icon-finder"></i> Cart
        </a>
        <%
            }if("ProductManager".equals(role) || "OrderManager".equals(role) || (userRoles != null && userRoles.size() >= 2)){
        %>
        <a href="roleSelection.jsp" class="btn btn-outline-info mr-2 button-hover-effect">
            <i class="fas fa-solid fa-bars"></i> Switch Role
        </a>
        <%
            }if(role == null){
        %>
        <a href="login.jsp" class="btn btn-outline-info mr-2 button-hover-effect">
            <i class="fas fa-sign-in-alt mr-1 icon-finder"></i> Login
        </a>
        <a href="signup.jsp" id="signup-link" class="btn btn-outline-info mr-2 button-hover-effect">
            <i class="fas fa-user-plus mr-1 icon-finder"></i> Sign Up
        </a>
        <script>
            document.getElementById('signup-link').href = 'signup.jsp?returnurl=' + encodeURIComponent(window.location.href);
        </script>

        <%
            }if(!(role == null)){
        %>
        <a href="Logout" class="btn btn-danger mr-2 button-hover-effect">
            <i class="fas fa-light fa-door-open icon-finder"></i> Logout
        </a>
        <%
                }
            }
        %>
    </div>
</nav>
</body>
</html>
