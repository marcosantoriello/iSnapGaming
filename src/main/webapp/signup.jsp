<%@ page import="com.isnapgaming.UserManagement.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String returnUrl = (String) request.getParameter("returnurl");
    User user = (User) session.getAttribute("User");
    if (user != null) {
        response.sendRedirect(returnUrl);
    } else {
        session.setAttribute("redirectSignup", returnUrl);
    }
    String error = (String) request.getAttribute("error");

%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Sign Up</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="styles/access.css">
        <script src="scripts/validate.js"></script>
    </head>
    <body>
        <jsp:include page="fragments/header.jsp" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
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
            <div class="col fixed-center d-flex justify-content-center align-items-center page" style="margin: 100px auto;">
                <form action="Signup" method="POST" id="signUpForm" onsubmit="return checkSignUp(this)"  style="width: 250px;">
                    <h2 class="mb-3" style="text-align: center;">SIGN UP</h2>
                    <br/>
                    <div class="form-group" style="margin: 15px 0;">
                        <label for="firstName">First Name</label>
                        <input id="firstName" type="text" name="firstName" class="form-control" required onChange="return validateFirstName()">
                    </div>
                    <span id="errorFirstName" style="max-width: 209px; font-size: 15px;"></span>
                    <div class="form-group" style="margin: 15px 0;">
                        <label for="lastName">Last Name</label>
                        <input id="lastName" type="text" name="lastName" class="form-control"  required onChange="return validateLastName()">
                    </div>
                    <span id="errorLastName" style="max-width: 209px; font-size: 15px;"></span>
                    <div class="form-group" style="margin: 15px 0;">
                        <label for="dateOfBirth">Date Of Birth</label>
                        <input id="dateOfBirth" type="date" name="dateOfBirth" class="form-control"  required onChange="return validateDateOfBirth()">
                    </div>
                    <span id="errorDateOfBirth" style="max-width: 209px; font-size: 15px;"></span>
                    <div class="form-group" style="margin: 15px 0;">
                        <label for="email">Email</label>
                        <input id="email" type="email" name="email" class="form-control" required onChange="return validateEmail()">
                    </div>
                    <span id="errorEmail" style="max-width: 209px; font-size: 15px;"></span>
                    <div class="form-group" style="margin: 15px 0;">
                        <label for="password">Password</label>
                        <input id="password" type="password" name="password" class="form-control" required onChange="return validatePassword()">
                    </div>
                    <span id="errorPassword" style="max-width: 209px; font-size: 15px;"></span>
                    <div class="form-group" style="margin: 15px 0;">
                        <label for="confirmPassword">Confirm Password</label>
                        <input id="confirmPassword" type="password" name="confirmPassword" class="form-control" required onChange="return passwordMatching()">
                    </div>
                    <span id="matchError" style="max-width: 209px; font-size: 15px;"></span>
                    <div class="form-group" style="margin: 15px 0;">
                        <label for="street">Street</label>
                        <input id="street" type="text" name="street" class="form-control" required>
                    </div>
                    <div class="form-group" style="margin: 15px 0;">
                        <label for="city">City</label>
                        <input id="city" type="text" name="city" class="form-control" required>
                    </div>
                    <span id="errorCity" style="max-width: 209px; font-size: 15px;"></span>
                    <div class="form-group" style="margin: 15px 0;">
                        <label for="postalCode">Postal Code</label>
                        <input id="postalCode" type="text" name="postalCode" class="form-control" required>
                    </div>
                    <span id="errorPostalCode" style="max-width: 209px; font-size: 15px;"></span>
                    <div class="submit-button">
                        <input type="submit" value="Sign Up" class="btn btn-primary btn-block" style="width: 100%; margin: 20% 0"/>
                    </div>
                    <P>Already registered? <a href="login.jsp">Click here</a></P>
                </form>
            </div>
        </div>
        <jsp:include page="fragments/footer.jsp" />
    </body>
</html>