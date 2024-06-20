<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/product.css">
</head>
<body>
<jsp:include page="fragments/header.jsp" />
<div class="content">
    <div class="product">
        <div class="product-left-side">
            <img src="app_imgs/logo.png" alt="Product Image" class="product-img">
            <div class="product-details">
                <p><strong>Developer:</strong> Software House</p>
                <p><strong>Platform:</strong> Platform </p>
                <p><strong>Category:</strong> Category </p>
                <p><strong>PEGI:</strong> PEGI</p>
                <p><strong>Release Date:</strong> Release </p>
            </div>
        </div>
        <div class="product-right-side">
            <div class="product-title">Product Title</div>
            <div class="product-quantity" style="text-align: center">
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" min="1" value="1" style="width: 50px;">
            </div>
            <div class="product-price">$49.99</div>
            <a href="product.jsp" class="btn btn-primary"><i class="fas fa-shopping-cart mr-1 icon-finder"></i> Add to cart</a>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</html>