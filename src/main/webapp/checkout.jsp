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
        <title>Checkout</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="styles/product.css">
        <script src="scripts/validate.js"></script>
    </head>
        <body>
            <jsp:include page="fragments/header.jsp" />
                <div class="content">
                    <div class="col fixed-center d-flex justify-content-center align-items-center page" style="margin: 100px auto;">
                        <form action="Payment" method="POST" id="checkoutForm" onsubmit="return checkCheckout(this)" style="width: 250px;">
                            <h2 class="mb-3" style="text-align: center;">SHIPPING ADDRESS</h2>
                            <div class="form-group" style="margin: 15px 0;">
                                <label for="streetCustomer">Street</label>
                                <input id="streetCustomer" type="text" name="streetCustomer" class="form-control" required>
                            </div>
                            <br/><br/><br/>
                            <h2 class="mb-3" style="text-align: center;">PAYMENT DATA</h2>

                            <div class="form-group" style="margin: 15px 0;">
                                <label for="cardNumber">Card Number</label>
                                <input id="cardNumber" type="text" name="cardNumber" class="form-control" required onChange="return validateCard()">
                            </div>

                            <span id="cardNumberError" style="max-width: 209px; font-size: 15px;"></span>

                            <div class="form-group" style="margin: 15px 0;">
                                <label for="expiryDate">Expiry Date</label>
                                <input id="expiryDate" type="date" name="expiryDate" class="form-control" required onChange="return validateExpiredCard()">
                            </div>

                            <span id="expiryError" style="max-width: 209px; font-size: 15px;"></span>

                            <div class="form-group" style="margin: 15px 0;">
                                <label for="securityNumber">Security Number</label>
                                <input id="securityNumber" type="text" name="securityNumber" class="form-control" required onChange="return validateSecurityNumber()">
                            </div>

                            <span id="securityNumberError" style="max-width: 209px; font-size: 15px;"></span>

                            <div class="submit-button">
                                <input type="submit" value="Pay" class="btn btn-primary btn-block" style="width: 100%; margin: 20% 0"/>
                            </div>
                        </form>
                    </div>
                </div>
            <jsp:include page="fragments/footer.jsp" />
        </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</html>