<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Order Manager Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles/orderManagerDashboard.css">
  </head>
  <body>
    <jsp:include page="fragments/header.jsp" />
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
      <div class="content">
        <div class="dashboard-order-manager">
          <ul class="order-list">
            <li>Order #1: <a href="orderDetails.jsp" class="btn btn-primary btn-block" style="width: 200px;"> Details </a></li>
          </ul>
        </div>
      </div>
    <jsp:include page="fragments/footer.jsp" />
  </body>
</html>