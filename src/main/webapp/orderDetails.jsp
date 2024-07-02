<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="styles/orderDetails.css">
    </head>
    <body>
        <jsp:include page="fragments/header.jsp" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <div class="content">
            <div class="product">
                <div class="product-left-side">
                    <img src="app_imgs/logo/logo.png" alt="Product Image" class="product-img">
                </div>
                <div class="product-right-side">
                    <div class="product-title">Product Title</div>
                    <div class="product-quantity" style="text-align: center">Quantity: 99 </div>
                </div>
            </div>
            <div class="submit-button">
                <a href="updateStatus.jsp" class="btn btn-primary btn-block" style="width: 350px;"> Update Status </a>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp" />
    </body>
</html>