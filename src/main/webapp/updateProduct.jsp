<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.isnapgaming.ProductManagement.Product" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Update Product</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="styles/addProduct.css">
  <script src="scripts/validate.js"></script>
  <%
    Product p = (Product) request.getAttribute("product");
  %>

</head>
<body>
<jsp:include page="fragments/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="content">
  <div class="col fixed-center d-flex justify-content-center align-items-center page" style="margin: 100px auto;" onsubmit="return checkUpdateProduct(this)">
    <form action="ProductUpdater" id="productForm" method="POST">
      <input type="hidden" id="productId" name="productId" value="<%=p.getId()%>">
      <input type="hidden" id="isAvailable" name="isAvailable" value="true">
      <h2 class="mb-3" style="text-align: center;">UPDATE PRODUCT</h2>
      <br/>
      <div class="form-group" style="margin: 15px 0;">
        <label for="productCode">Product Code</label>
        <input value="<%= p.getProdCode()%>" id="productCode" type="number" name="productCode" class="form-control"  onChange="return validateProductCode()">
      </div>
      <span id="productCodeError" style="max-width: 209px; font-size: 15px;"></span>
      <div class="form-group" style="margin: 15px 0;">
        <label for="nameProduct">Name</label>
        <input value="<%= p.getName()%>" id="nameProduct" type="text" name="nameProduct" class="form-control"  onChange="return validateNameProduct()">
      </div>
      <span id="nameProductError" style="max-width: 209px; font-size: 15px;"></span>
      <div class="form-group" style="margin: 15px 0;">
        <label for="softwareHouseProduct">Software House</label>
        <input value="<%= p.getSoftwareHouse()%>" id="softwareHouseProduct" type="text" name="softwareHouseProduct" class="form-control"  onChange="return validateSoftwareHouseProduct()">
      </div>
      <span id="productSoftwareHouseError" style="max-width: 209px; font-size: 15px;"></span>
      <div class="form-group" style="margin: 15px 0;">
        <label>Platform</label>
        <select name="platformProduct" class="form-select" aria-label="Default select example" onChange="return validateSoftwareHouseProduct()">
          <option selected><%= p.getPlatform()%> </option>
          <option value=""></option>
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
          <option selected><%= p.getCategory()%> </option>
          <option value=""></option>
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
        <select name="pegiProduct" class="form-select" aria-label="Default select example" onChange="return validatePegiProduct()">
          <option selected><%= p.getPegi()%> </option>
          <option value=""></option>
          <option value="PEGI3">PEGI3</option>
          <option value="PEGI7">PEGI7</option>
          <option value="PEGI12">PEGI12</option>
          <option value="PEGI16">PEGI16</option>
          <option value="PEGI18">PEGI18</option>
        </select>
      </div>
      <span id="productPegiError" style="max-width: 209px; font-size: 15px;"></span>
      <div class="form-group" style="margin: 15px 0;">
        <label for="releaseYearProduct">Release Year</label>
        <input value="<%= p.getReleaseYear()%>" id="releaseYearProduct" type="number" min="1" name="releaseYearProduct" class="form-control" onChange="return validateYearReleaseProduct()">
      </div>
      <span id="productYearReleaseError" style="max-width: 209px; font-size: 15px;"></span>
      <div class="form-group" style="margin: 15px 0;">
        <label for="quantityProduct">Quantity</label>
        <input value="<%= p.getQuantity()%>" id="quantityProduct" type="number" name="quantityProduct" class="form-control" onChange="return validateQuantityProduct()">
      </div>
      <span id="productQuantityError" style="max-width: 209px; font-size: 15px;"></span>
      <div class="form-group" style="margin: 15px 0;">
        <label for="priceProduct">Price</label>
        <input value="<%= p.getPrice()%>" id="priceProduct" type="number" name="priceProduct" class="form-control" onChange="return validatePriceProduct()">
      </div>
      <span id="productPriceError" style="max-width: 209px; font-size: 15px;"></span>
      <div class="submit-button">
        <input type="submit" value="Update Product" class="btn btn-primary btn-block" style="width: 100%; margin: 20% 0"/>
      </div>
    </form>
  </div>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>