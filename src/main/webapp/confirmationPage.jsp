<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.isnapgaming.OrderManagement.CustomerOrder" %>
<%@ page import="com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int orderId = (int) session.getAttribute("orderId");

%>
<html>
<head>
    <title>Your order</title>
</head>
<body>
<jsp:include page="fragments/header.jsp" />
<div class="content">
    <h2>Thank you for your order!</h2>
    Your order with id <%=request.getParameter("orderId")%> has been saved.<br/>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>
