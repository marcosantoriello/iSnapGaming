<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Select Role</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/select-role.css">
</head>
<body>
<%
    List<String> roles = (List<String>) session.getAttribute("roles");
%>
<jsp:include page="fragments/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="content">
    <div class="select-role">
        <h2>Select your role</h2>
        <form>
            <div class="radio-buttons">
                <%
                    for(String role: roles){
                %>
                <input type="radio" name="role" value="<%= role %>"> <%= role %><br>
                <%
                    }
                %>
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