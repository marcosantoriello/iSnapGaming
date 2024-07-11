<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/addProduct.css">
    <script src="scripts/validate.js"></script>
</head>
<%
    String error = (String) request.getAttribute("error");
%>
<body>
<jsp:include page="fragments/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="content">
    <%
        if(error != null){
    %>
    <div class="alert alert-danger" role="alert" style="margin: 5% auto; width: 300px; text-align: center;">
        <%= error %>
    </div>
    <%
        }
    %>
    <div class="col fixed-center d-flex justify-content-center align-items-center page" style="margin: 100px auto;">
        <form action="AddProduct" method="POST" id="productForm" enctype="multipart/form-data" onsubmit="return checkAddProduct(this)">
            <h2 class="mb-3" style="text-align: center;">ADD PRODUCT</h2>
            <br/>
            <div class="form-group" style="margin: 15px 0;">
                <label for="productCode">Product Code</label>
                <input id="productCode" type="number" min="1" name="productCode" class="form-control" onChange="return validateProductCode()" >
            </div>
            <span id="productCodeError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="form-group" style="margin: 15px 0;">
                <label for="nameProduct">Name</label>
                <input id="nameProduct" type="text" name="nameProduct" class="form-control"  onChange="return validateNameProduct()">
            </div>
            <span id="nameProductError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="form-group" style="margin: 15px 0;">
                <label for="softwareHouseProduct">Software House</label>
                <input id="softwareHouseProduct" type="text" name="softwareHouseProduct" class="form-control"  onChange="return validateSoftwareHouseProduct()">
            </div>
            <span id="productSoftwareHouseError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="form-group" style="margin: 15px 0;">
                <label>Platform</label>
                <select name="platformProduct" class="form-select" aria-label="Default select example" onChange="return validateSoftwareHouseProduct()">
                    <option value ="" selected></option>
                    <option value="PC">PC</option>
                    <option value="PS4">PS4</option>
                    <option value="PS5">PS5</option>
                    <option value="XBOX">XBOX</option>
                    <option value="SWITCH">SWITCH</option>
                </select>
            </div>
            <span id="productPlatformError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="form-group" style="margin: 15px 0;">
                <label>Category</label>
                <select name="categoryProduct" class="form-select" aria-label="Default select example" onChange="return validateCategoryProduct()">
                    <option value = "" selected></option>
                    <option value="ACTION">ACTION</option>
                    <option value="ADVENTURE">ADVENTURE</option>
                    <option value="RPG">RPG</option>
                    <option value="STRATEGY">STRATEGY</option>
                    <option value="SIMULATION">SIMULATION</option>
                    <option value="SPORTS">SPORTS</option>
                    <option value="PUZZLE">PUZZLE</option>
                    <option value="HORROR">HORROR</option>
                    <option value="SURVIVAL">SURVIVAL</option>
                    <option value="SHOOTER">SHOOTER</option>
                </select>
            </div>
            <span id="productCategoryError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="form-group" style="margin: 15px 0;">
                <label>PEGI</label>
                <select name="pegiProduct" class="form-select" aria-label="Default select example"  onChange="return validatePegiProduct()">
                    <option value ="" selected></option>
                    <option value="PEGI3">PEGI3 </option>
                    <option value="PEGI7">PEGI7</option>
                    <option value="PEGI12">PEGI12</option>
                    <option value="PEGI16">PEGI16</option>
                    <option value="PEGI18">PEGI18</option>
                </select>
            </div>
            <span id="productPegiError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="form-group" style="margin: 15px 0;">
                <label for="releaseYearProduct">Release Year</label>
                <input id="releaseYearProduct" type="number" min="1" name="releaseYearProduct" class="form-control" onChange="return validateYearReleaseProduct()">
            </div>
            <span id="productYearReleaseError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="form-group" style="margin: 15px 0;">
                <label for="quantityProduct">Quantity</label>
                <input id="quantityProduct" type="number"  name="quantityProduct" class="form-control" onChange="return validateQuantityProduct()">
            </div>
            <span id="productQuantityError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="form-group" style="margin: 15px 0;">
                <label for="priceProduct">Price</label>
                <input id="priceProduct" type="number"  name="priceProduct" class="form-control" onChange="return validatePriceProduct()" >
            </div>
            <span id="productPriceError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="form-group" style="margin: 15px 0;">
                <label for="imageProduct">Image</label>
                <input id="imageProduct" type="file" name="imageProduct" class="form-control" accept="image/*" >
            </div>
            <span id="productImageError" style="max-width: 209px; font-size: 15px;"></span>
            <div class="submit-button">
                <input type="submit" value="Add Product" class="btn btn-primary btn-block" style="width: 100%; margin: 20% 0" accept='image/*'>
            </div>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>