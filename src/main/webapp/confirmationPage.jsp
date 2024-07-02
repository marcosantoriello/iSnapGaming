<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.isnapgaming.OrderManagement.CustomerOrder" %>
<%@ page import="com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int orderId = (int) session.getAttribute("orderId");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Checkout</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="styles/confirmationPage.css">
    </head>
    <body>
        <jsp:include page="fragments/header.jsp" />
        <div class="content">
            <div class="alert alert-success" role="alert" style="margin: 11% 25%;text-align: center;">
                <h1> <i class="fas fa-solid fa-check"></i></h1>
                <br>
                <h2>Thank you for your order!</h2>
                <br>
                Your order with id <b> <%=orderId%> </b> has been saved.
                <br><br>
                <h6 class="mb-0"><a href="index.jsp" class="text-body"><i
                        class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
            </div>
        </div>

        <jsp:include page="fragments/footer.jsp" />
    </body>
</html>
