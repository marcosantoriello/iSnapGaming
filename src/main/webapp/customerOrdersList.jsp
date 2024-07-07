<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.isnapgaming.OrderManagement.CustomerOrder" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Manager Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/orderManagerDashboard.css">
    <%

            List<CustomerOrder> orders = (List<CustomerOrder>) request.getAttribute("orders");

            if (orders == null) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/GetOrdersList");
                dispatcher.forward(request, response);
                return;
            }

    %>
</head>
<body>
<jsp:include page="fragments/header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<div class="content">
    <div class="dashboard-order-manager" style="border-radius: 15px;">
        <div class="left-dashboard-order-manager">
            <a class="btn btn-primary disabled" style="margin: auto 10%;">Show Orders</a>
        </div>
        <div class="right-dashboard-order-manager">
            <%

                if(!orders.isEmpty()){
                    for(CustomerOrder co: orders){
            %>
            <div class="order-item">
                <div class="order-info">
                    <h3 style="padding: 20px; width: 220px; text-align: center">Order #<%=co.getId()%></h3>
                </div>
                <a href="GetOrderDetails?orderId=<%=co.getId()%>" class="btn btn-primary"> Details </a>
            </div>
            <%
                        }
                }
            %>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>