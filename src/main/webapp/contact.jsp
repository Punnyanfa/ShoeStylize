<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contact | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contact.css">
    <jsp:include page="/templates/common_lib.jsp"/>

</head>

<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <div class="content">
        <div class="content-background">
            <div class="frame-black">
                <h1>Contact</h1>
                <p>Feel free to reach out to us through the following contacts:</p>
                <ul>
                    <li>Email: <a href="mailto:shoestylize@gmail.com">shoestylize@gmail.com</a></li>
                    <li>Phone: <a href="tel:+1234567890">+84 332-567-890</a></li>
                    <li>Address: 123/3B, 120 Street, Thu Duc District, Ho Chi Minh City</li>
                    <li><i class="fa-brands fa-facebook"></i> fb.com/shoestylize</li>
                    <li><i class="fa-brands fa-instagram"></i> @shoestylize</li>
                </ul>

                <p>Follow us on social media for updates and more!</p>
            </div>
        </div>
    </div>
    <jsp:include page="/templates/footer.jsp"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
</div>

</body>

</html>