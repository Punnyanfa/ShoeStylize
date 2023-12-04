<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>News | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/news-info.css">
    <jsp:include page="/templates/common_lib.jsp"/>
</head>

<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <div class="content">
        <div class="bc">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/news">News</a></li>
                    <li class="breadcrumb-item active" aria-current="page">${News.getTitle()}</li>
                </ol>
            </nav>
        </div>
        <div id="news-box">
            <h3>${News.getTitle()}</h3>
            <p id="news-box--time"><i class="fa-solid fa-calendar-days"></i> ${News.getFormatDateCreated()}
                at ${News.getFormatTimeCreated()}</p>
            <hr>
            <p>${News.getContent()}</p>
        </div>
        <hr>
        <div id="news-more">
            <h5>Watch more</h5>
            <div>
                <c:forEach items="${News_Recommend}" var="News">
                    <p class="news-more-node" dir-to="${pageContext.request.contextPath}/news?id=${News.getId()}">
                            <span class="news-more--date">${News.getFormatDateCreated()}</span><span class="news-more--time">${News.getFormatTimeCreated()}</span> ${News.getTitle()}
                    </p>
                </c:forEach>
            </div>
        </div>
    </div>
    <jsp:include page="/templates/footer.jsp"/>
    <jsp:include page="/templates/common_js.jsp"/>
</div>
</body>

</html>