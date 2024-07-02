<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.isnapgaming.ProductManagement.Product" %>
<%@ page import="com.isnapgaming.OrderManagement.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.isnapgaming.StorageManagement.DAO.CustomerOrderDAO" %>
<%@ page import="com.isnapgaming.OrderManagement.CustomerOrder" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Order Manager Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/orderManagerDashboard.css">
    <%
      DataSource ds = (DataSource) request.getServletContext().getAttribute("DataSource");
      CustomerOrderDAO coDAO = new CustomerOrderDAO(ds);
      List<CustomerOrder> orders = coDAO.doRetrieveAll();

    %>
  </head>
  <body>
    <jsp:include page="fragments/header.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
      <div class="content">
        <div class="dashboard-order-manager">
          <ul class="order-list">
            <%
              if(!orders.isEmpty()){
                for(CustomerOrder co: orders){
            %>
            <li>Order #<%=co.getId()%>: <a href="GetOrderDetails?orderId=<%=co.getId()%>" class="btn btn-primary btn-block" style="width: 250px; margin: 0 0 0 5%;"> Details </a></li>
            <%
                }
              }
            %>
          </ul>
        </div>
      </div>
    <jsp:include page="fragments/footer.jsp" />
  </body>
</html>