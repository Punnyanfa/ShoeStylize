<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>News | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/news.css">
    <jsp:include page="/templates/common_lib.jsp"/>
</head>

<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <div class="content">
        <div id="component--title">
            <img src="assets/logo.png" style="width: 40px; height: 50px">
            <h1>News</h1>
        </div>
        <div id="component--search">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Search news title..." aria-label="News"
                       aria-describedby="button-addon2">
                <button class="btn btn-outline-dark" type="button" id="button-addon2">Search</button>
            </div>
        </div>
        <div id="news-container">
            <c:forEach items="${LIST_NEWS}" var="News">
                <div class="news-node">
                    <p class="news--date">${News.getFormatDateCreated()}</p>
                    <p class="news--time">${News.getFormatTimeCreated()}</p>
                    <p class="news--title" dir-to="${pageContext.request.contextPath}/news?id=${News.getId()}">${News.getTitle()}</p>
                </div>
            </c:forEach>

            <nav id="news-pagination" aria-label="News pagination">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
                    <li class="page-item"><a class="page-link" href="?page=3">2</a></li>
                    <li class="page-item"><a class="page-link" href="?page=3">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </nav>
        </div>
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