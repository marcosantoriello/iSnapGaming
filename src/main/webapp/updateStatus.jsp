<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.isnapgaming.OrderManagement.CustomerOrder "%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Status</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="styles/updateStatus.css">
        <%
            CustomerOrder order = (CustomerOrder) request.getAttribute("order");
        %>
    </head>
    <body>
        <jsp:include page="fragments/header.jsp" />
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
            <div class="content">
                <div class="col fixed-center d-flex justify-content-center align-items-center page" style="margin: 100px auto;">
                    <form action="UpdateStatus" method="POST">
                        <input type="hidden" id="orderId" name="orderId" value="<%=order.getId()%>">
                        <h2 class="mb-3" style="text-align: center;">UPDATE STATUS</h2>
                        <br/>
                        <div class="form-group" style="margin: 15px 0;">
                            <label>Current State: <%=order.getStatus()%></label>
                        </div>
                        New State
                        <select name="newStatus" class="form-select" aria-label="Default select example">
                            <option selected value="UNDER_PREPARATION">UNDER PREPARATION</option>
                            <option value="READY_FOR_SENDING">READY FOR SENDING</option>
                            <option value="SHIPPED">SHIPPED</option>
                            <option value="DELIVERED">DELIVERED</option>
                        </select>
                        <div class="submit-button">
                            <input type="submit" value="Update Status" class="btn btn-primary btn-block" style="width: 100%; margin: 20% 0"/>
                        </div>
                    </form>
                </div>
            </div>
        <jsp:include page="fragments/footer.jsp" />
    </body>
</html>