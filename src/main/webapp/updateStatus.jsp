<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Status</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="styles/updateStatus.css">
    </head>
    <body>
        <jsp:include page="fragments/header.jsp" />
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
            <div class="content">
                <div class="col fixed-center d-flex justify-content-center align-items-center page" style="margin: 100px auto;">
                    <form action="UpdateStatus" method="POST">
                        <h2 class="mb-3" style="text-align: center;">UPDATE STATUS</h2>
                        <br/>
                        <div class="form-group" style="margin: 15px 0;">
                            <label>Current State: Current State</label>
                        </div>
                        New State
                        <select class="form-select" aria-label="Default select example">
                            <option selected>Select...</option>
                            <option value="1">Option 1</option>
                            <option value="2">Option 1</option>
                            <option value="3">Option 1</option>
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