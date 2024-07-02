<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.isnapgaming.ProductManagement.Product" %>
<%@ page import="com.isnapgaming.StorageManagement.DAO.ProductDAO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Manager Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/productManagerDashboard.css">
    <%
        DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
        ProductDAO pDAO = new ProductDAO(ds);
        List<Product> products = pDAO.doRetrieveAll();
    %>
</head>
<body>
<jsp:include page="fragments/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="content">
    <div class="dashboard-product-manager" style="border-radius: 15px;">
        <div class="left-dashboard-product-manager">
            <a href="addProduct.jsp" class="btn btn-primary btn-block" style="margin: auto 10%;">Add Product</a>
        </div>
        <div class="right-dashboard-product-manager">
            <div class="product-list">
                <%
                    if(!products.isEmpty()){
                        for(Product p: products){
                %>
                <div class="product-item">
                    <div class="product-info">
                        <img src="ImageServlet?image=<%=p.getProdCode()%>_1.jpg" alt="Product Image">
                        <h3 style="padding: 20px; width: 220px; text-align: center"><%= p.getName()%></h3>
                    </div>
                    <a href="ProductDetails?prodCode=<%= p.getProdCode() %>" class="btn btn-secondary">Select</a>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>