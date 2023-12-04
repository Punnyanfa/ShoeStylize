<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Status | ShoeStylize</title>
        <link rel="icon" href="./assets/logo.png" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css">
        <jsp:include page="/templates/common_lib.jsp"/>
    </head>

    <body>
        <div class="app">
            <jsp:include page="/templates/header.jsp"/>
            <div class="content">
                <h2>You have finished your transactions</h2>
                <p>Please check your cart for progressing orders</p>
                
            </div>
            <jsp:include page="/templates/footer.jsp"/>
            <script type="text/javascript" src="./js/jquery-3.7.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
        </div>

    </body>

</html>