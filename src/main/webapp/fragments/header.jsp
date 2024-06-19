<%--
  Created by IntelliJ IDEA.
  User: Fabio
  Date: 19/06/2024
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Header</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="styles/header.css">
</head>
<body>
<nav class="navbar navbar-light" style="background-color: #0e6ac7 !important; padding: 20px;">
    <a class="navbar-brand" href="index.jsp">
        <img src="images/logo.png" width="50" height="50" alt="">
    </a>
    <form class="form-inline  justify-content-center">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
            <i class="fas fa-search icon-finder"></i>
        </button>
    </form>
    <div class="ml-auto">
        <button class="btn btn-outline-info mr-2 button-hover-effect" type="button"><i class="fas fa-shopping-cart mr-1 icon-finder"></i> Cart</button>
        <button class="btn btn-outline-info mr-2 button-hover-effect" type="button"><i class="fas fa-sign-in-alt mr-1 icon-finder"></i> Sign In</button>
        <button class="btn btn-outline-info mr-2 button-hover-effect" type="button"><i class="fas fa-user-plus mr-1 icon-finder"></i> Sign Up</button>
    </div>
</nav>
</body>
</html>
