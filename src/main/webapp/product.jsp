<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.isnapgaming.ProductManagement.Product" %>
<%@ page import="com.isnapgaming.OrderManagement.Cart" %>

<%
    if (session.getAttribute("cart") == null) {
        session.setAttribute("cart", new Cart());
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/product.css">
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var quantityInput = document.getElementById('quantity');
            var maxQuantity = quantityInput.max;

            quantityInput.addEventListener('input', function() {
                if (parseInt(quantityInput.value) > parseInt(maxQuantity)) {
                    quantityInput.value = maxQuantity;
                }
            });
        });
    </script>
    <%
        Product p = (Product) request.getAttribute("product");
        String error = (String) request.getAttribute("error");
    %>
</head>
<body>
<jsp:include page="fragments/header.jsp" />
<div class="content">
    <%
        if(error != null){
    %>
    <div class="alert alert-danger" role="alert" style="margin: 5% auto; width: 300px; text-align: center;">
        <%=error%>
    </div>
    <%
        }
    %>
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
                <form action="AddToCart" method="post" class="form-product" style="flex: 1; display: flex; flex-direction: column; justify-content: space-between;gap: 50px;">
                <input type="hidden" name="prodCode" value="<%= p.getProdCode() %>">
                <div class="product-title" style="font-size: 40px;"><%= p.getName()%></div>
                <div class="product-quantity" style="text-align: center">
                    <label for="quantitySelected">Quantity: </label>
                    <input type="number" id="quantitySelected" name="quantitySelected" min="1" value="1" style="width: 50px;">
                </div>
                <div class="product-price" style="color: black"><b><%= p.getPrice()%>.00 €</b></div>
                <%
                    if(p.isAvailable() && p.getQuantity() > 0){
                %>
                <button type="submit" class="btn btn-primary btn-block"><i class="fas fa-shopping-cart mr-1 icon-finder"></i> Add to cart </button><!--<a href="AddToCart?prodCode=" class="btn btn-primary"><i class="fas fa-shopping-cart mr-1 icon-finder"></i> Add to cart</a> -->
                <%
                }else if(!(p.isAvailable()) || p.getQuantity() == 0){
                %>
                <a class="btn btn-primary btn-lg disabled" role="button" aria-disabled="true" style="background-color: #3498db;color: white;border: none; padding: 10px 20px;cursor: pointer;font-size: 16px;"><i class="fas fa-shopping-cart mr-1 icon-finder"></i> Add to cart</a>
                <span style="color: red; text-align: center">Product temporarily unavailable</span>
                <%
                    }
                %>
                </form>
            </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</html>