<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/addProduct.css">
</head>
<body>
<jsp:include page="fragments/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="content">
    <div class="col fixed-center d-flex justify-content-center align-items-center page" style="margin: 100px auto;">
        <form action="AddProduct" method="POST">
            <h2 class="mb-3" style="text-align: center;">ADD PRODUCT</h2>
            <br/>
            <div class="form-group" style="margin: 15px 0;">
                <label for="idProduct">ID</label>
                <input id="idProduct" type="text" name="idProduct" class="form-control" required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="nameProduct">Name</label>
                <input id="nameProduct" type="text" name="nameProduct" class="form-control"  required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="softwareHouse">Software House</label>
                <input id="softwareHouse" type="text" name="softwareHouse" class="form-control"  required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="platform">Platform</label>
                <input id="platform" type="text" name="platform" class="form-control" required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="category">Category</label>
                <input id="category" type="text" name="category" class="form-control" required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="relaseDateProduct">PEGI</label>
            <select class="form-select" aria-label="Default select example">
                <option selected>select </option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="relaseDateProduct">Relase Date</label>
                <input id="relaseDateProduct" type="date" name="relaseDateProduct" class="form-control" required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="quantityProduct">Quantity</label>
                <input id="quantityProduct" type="number" name="quantityProduct" class="form-control" required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="priceProduct">Price</label>
                <input id="priceProduct" type="number" name="priceProduct" class="form-control" required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="imageProduct">Image</label>
                <input id="imageProduct" type="file" name="imageProduct" class="form-control" required>
            </div>
            <div class="submit-button">
                <input type="submit" value="Submit" class="btn btn-primary btn-block" style="width: 100%; margin: 20% 0"/>
            </div>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>