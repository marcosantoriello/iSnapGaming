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
        <div class="dashboard-order-manager" style="border-radius: 15px;">
          <div class="left-dashboard-order-manager">
            <a href="GetOrdersList" class="btn btn-primary" style="margin: auto 10%;">Show Orders</a>
          </div>
          <div class="right-dashboard-order-manager">
          </div>
        </div>
      </div>
    <jsp:include page="fragments/footer.jsp" />
  </body>
</html>