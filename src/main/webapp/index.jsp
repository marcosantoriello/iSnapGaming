<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.isnapgaming.ProductManagement.Product" %>
<%@ page import="com.isnapgaming.StorageManagement.DAO.ProductDAO" %>
<%@ page import="com.isnapgaming.OrderManagement.Cart" %>
<%@ page import="java.util.List" %>
<%
  if (session.getAttribute("cart") == null) {
    session.setAttribute("cart", new Cart());
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Home</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="styles/index.css">

  <%
    DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
    ProductDAO pDAO = new ProductDAO(ds);
    List<Product> products = pDAO.doRetrieveAll();

  %>

</head>
<body>
<jsp:include page="fragments/header.jsp" />
<div class="content">
  <div class="products">
    <%
      if(!products.isEmpty()){
        for(Product p: products){
    %>
    <div class="card" style="width: 18rem;">
      <img src="app_imgs/logo.png" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">
          <%= p.getName() %>
          <span style="float: right;"><%= p.getPrice() %> €</span>
        </h5>
        <div class="d-flex justify-content-center">
          <a href="ProductDetails?prodCode=<%= p.getProdCode() %>" class="btn btn-primary" style="margin: 5% auto; padding: 2% 25%;">View details</a>
        </div>
      </div>
    </div>
    <%
        }
      }
    %>
  </div>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>