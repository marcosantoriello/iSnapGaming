<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/access.css">
</head>
<body>
<jsp:include page="fragments/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="content">
    <div class="col fixed-center d-flex justify-content-center align-items-center page" style="margin: 100px auto;">
        <form action="SignUp" method="POST">
            <h2 class="mb-3" style="text-align: center;">SIGN UP</h2>
            <br/>
            <div class="form-group" style="margin: 15px 0;">
                <label for="firstName">First Name</label>
                <input id="firstName" type="text" name="firstName" class="form-control" required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="lastName">Last Name</label>
                <input id="lastName" type="text" name="lastName" class="form-control"  required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="dateOfBirth">Date Of Birth</label>
                <input id="dateOfBirth" type="date" name="dateOfBirth" class="form-control"  required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="email">Email</label>
                <input id="email" type="email" name="email" class="form-control" required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="password">Password</label>
                <input id="password" type="password" name="password" class="form-control" required>
            </div>
            <div class="form-group" style="margin: 15px 0;">
                <label for="confirmPassword">Confirm Password</label>
                <input id="confirmPassword" type="password" name="confirmPassword" class="form-control" required>
            </div>
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