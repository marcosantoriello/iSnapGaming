<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Error</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <link rel="stylesheet" type="text/css" href="styles/errorPage.css">
        </head>

        <%
            String role = (String) session.getAttribute("role");
        %>
    <body>
        <jsp:include page="fragments/header.jsp" />
            <div class="content">
                <div class="alert alert-danger" role="alert" style="margin: 11% 25%;text-align: center;">
                    <h2>Ops, something went wrong</h2>
                    <br>
                    <%= request.getAttribute("jakarta.servlet.error.message") %>
                    <br><br>
                    <%
                        if("ProductManager".equals(role)){
                    %>
                        <h6 class="mb-0"><a href="productManagerDashboard.jsp" class="text-body"><i class="fas fa-long-arrow-alt-left me-2"></i>Back to dashboard</a></h6>
                    <%
                             }else if("OrderManager".equals(role)){
                    %>
                         <h6 class="mb-0"><a href="orderManagerDashboard.jsp" class="text-body"><i class="fas fa-long-arrow-alt-left me-2"></i>Back to dashboard</a></h6>
                    <%
                                 }else{
                    %>
                             <h6 class="mb-0"><a href="index.jsp" class="text-body"><i class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
                           <%
                               }
                           %>
                </div>
            </div>
        <jsp:include page="fragments/footer.jsp" />
    </body>
</html>
