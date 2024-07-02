<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Manager Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="styles/productManagerDashboard.css">
    </head>
    <body>
        <jsp:include page="fragments/header.jsp" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <div class="content">
            <div class="dashboard-product-manager">
                <div class="left-dashboard-product-manager">
                    <a href="addProduct.jsp" class="btn btn-primary btn-block" style="margin: auto 10%;">Add Product</a>
                </div>
                <div class="right-dashboard-product-manager">
                    <div class="product-list">
                        <div class="product-item">
                            <div class="product-info">
                                <img src="app_imgs/logo/logo.png" alt="Product Image">
                                <h3 style="padding: 20px;">Product Name</h3>
                            </div>
                            <a href="#" class="btn btn-secondary">Details</a>
                        </div>
                        <div class="product-item">
                            <div class="product-info">
                                <img src="app_imgs/logo/logo.png" alt="Product Image">
                                <h3 style="padding: 20px;">Product Name</h3>
                            </div>
                            <a href="#" class="btn btn-secondary">Details</a>
                        </div>
                        <div class="product-item">
                            <div class="product-info">
                                <img src="app_imgs/logo/logo.png" alt="Product Image">
                                <h3 style="padding: 20px;">Product Name</h3>
                            </div>
                            <a href="#" class="btn btn-secondary">Details</a>
                        </div>
                        <div class="product-item">
                            <div class="product-info">
                                <img src="app_imgs/logo/logo.png" alt="Product Image">
                                <h3 style="padding: 20px;">Product Name</h3>
                            </div>
                            <a href="#" class="btn btn-secondary">Details</a>
                        </div>
                        <div class="product-item">
                            <div class="product-info">
                                <img src="app_imgs/logo/logo.png" alt="Product Image">
                                <h3 style="padding: 20px;">Product Name</h3>
                            </div>
                            <a href="#" class="btn btn-secondary">Details</a>
                        </div>
                        <!-- Repeat the above structure for each product -->
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp" />
    </body>
</html>