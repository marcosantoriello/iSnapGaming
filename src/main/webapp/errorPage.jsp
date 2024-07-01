
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="fragments/header.jsp" />

Ops, something went wrong.<br/>

<%=request.getParameter("errorMessage")%>

<jsp:include page="fragments/footer.jsp" />
</body>
</html>
