<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check before pay | ShoeStylize</title>
        <link rel="icon" href="./assets/logo.png" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart-checkout.css">
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
              integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
              crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href="https://fonts.googleapis.com/css2?family=Send+Flowers&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    </head>

    <body>
        <div class="check_address">
            <jsp:include page="/templates/header.jsp"/>
            <div class="content row">
                <div class="error">
                    <img src="${pageContext.request.contextPath}/assets/icons/warning.png" alt="warning">
                    <h1>Your cart is empty!</h1>
                    <p>Please add something into your order queue before you can proceed!</p>
                </div>

            </div>

            <jsp:include page="/templates/footer.jsp"/>

            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>


    </body>

</html>