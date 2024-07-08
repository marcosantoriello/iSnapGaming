<%@ page import="com.isnapgaming.UserManagement.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  String returnUrl = (String) request.getParameter("returnurl");
  User user = (User) session.getAttribute("user");
  if (user != null) {
    if (returnUrl != null) {
      response.sendRedirect(returnUrl);
    } else {
      response.sendRedirect("http://localhost:8080/iSnapGaming_war/index.jsp");
    }

  } else {
    session.setAttribute("redirectLogin", returnUrl);
  }

%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/access.css">
    <script src="scripts/validate.js"></script>

    <%
      String error = (String) request.getAttribute("error");
    %>

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
        <form action="Login" method="POST" id="signUpForm" onsubmit="return checkLogin(this)" style="width: 250px;">
          <h2 class="mb-3" style="text-align: center;">LOGIN</h2>
          <br/>
          <div class="form-group" style="margin: 15px 0;">
            <label for="email">Email</label>
            <input id="email" type="email" name="email" class="form-control" required onChange="return validateEmail()">
          </div>
          <span id="errorEmail" style="max-width: 209px; font-size: 15px;"></span>
          <div class="form-group" style="margin: 15px 0;">
            <label for="password">Password</label>
            <input id="password" type="password" name="password" class="form-control" required">
          </div>
          <span id="errorPassword" style="max-width: 209px; font-size: 15px;"></span>
          <div class="submit-button">
            <input type="submit" value="Login" class="btn btn-primary btn-block" style="width: 100%; margin: 20% 0"/>
          </div>
          <P>Not registered yet? <a href="signup.jsp">Click here</a></P>
        </form>
      </div>
    </div>
    <jsp:include page="fragments/footer.jsp" />
  </body>
</html>