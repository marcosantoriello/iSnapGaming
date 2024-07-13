<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.isnapgaming.ProductManagement.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/product.css">
    <%
        Product p = (Product) request.getAttribute("product");
    %>
</head>
<body>
<jsp:include page="fragments/header.jsp" />
<div class="content">
    <div class="product">
        <div class="product-left-side">
            <img src="ImageServlet?image=<%=p.getProdCode()%>_1.jpg" alt="Product Image" class="product-img">
            <div class="product-details">
                <p><strong>Software House:</strong> <%= p.getSoftwareHouse()%></p>
                <p><strong>Platform:</strong> <%= p.getPlatform()%> </p>
                <p><strong>Category:</strong> <%= p.getCategory()%> </p>
                <p><strong>PEGI:</strong> <%= p.getPegi()%></p>
                <p><strong>Release Year:</strong> <%= p.getReleaseYear()%> </p>
            </div>
        </div>
        <div class="product-right-side" style="padding: 100px 30px;">
            <div class="product-title" style="font-size: 40px;"><%= p.getName()%></div>
            <div class="product-quantity" style="text-align: center">
                <label>Quantity: <%= p.getQuantity() %></label>
            </div>
            <div class="product-price" style="color: black"><b><%= p.getPrice()%>.00 €</b></div>
            <a href="ManageProduct?prodCode=<%= p.getProdCode() %>" class="btn btn-warning"><i class="fas fa-solid fa-wrench"></i> Manage</a>
            <%
                if(p.isAvailable()){
            %>
                <a href="ProductAvailability?action=makeUnavailable&prodCode=<%= p.getProdCode() %>" class="btn btn-danger"> Make unavailable</a>
            <%
                }else{
            %>
            <a href="ProductAvailability?action=makeAvailable&prodCode=<%= p.getProdCode() %>" class="btn btn-success"> Make available</a>
            <%
                }
            %>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</html>