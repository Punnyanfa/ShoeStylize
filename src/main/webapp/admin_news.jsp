<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin News | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin-tab.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/news.css">
    <jsp:include page="/templates/common_lib.jsp"/>
</head>

<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <div class="content row">
        <div class="content-tab col-md-2">
            <jsp:include page="/templates/admin_tab.jsp"/>
        </div>
        <div class="content-frame col-md-10">
            <div class="module">
                <h4>Create your news here</h4>
                <hr>
                <form id="admin-news-create-form" action="${pageContext.request.contextPath}/api/admin/news/create"
                      method="post">
                    <div>
                        <label for="news_title" class="form-label label-important">Title</label>
                        <div class="input-group mb-3">
                            <input required name="news_title" type="text" class="form-control" id="news_title"
                                   placeholder="Enter your title"
                                   aria-describedby="news_title">
                        </div>
                    </div>
                    <div>
                        <label for="news_content" class="form-label label-important">Enter your content</label>
                        <div class="input-group">
                            <textarea name="news_content" id="news_content" class="form-control"
                                      aria-label="With textarea" placeholder="Enter your news content"
                                      required></textarea>
                        </div>
                    </div>
                    <div>
                        <input class="btn btn-success" type="submit" value="Create news">
                    </div>
                </form>
            </div>
            <div class="module">
                <h4>All news created</h4>
                <hr>
                <div>
                    <table id="news-table" class="stripe">
                        <thead>
                        <tr>
                            <th class="text-center">ID</th>
                            <th>Title</th>
                            <th>Content</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${LIST_NEWS}" var="News">
                            <tr>
                                <td class="text-center">${News.getId()}</td>
                                <td>${News.getTitle()}</td>
                                <td>${News.getContent()}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/api/admin/news/delete"
                                          method='post'>
                                        <input type="hidden" name="id" value="${News.getId()}">
                                        <input class="btn btn-danger" type="submit" value="Delete">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/templates/common_js.jsp"/>
    <script>
        let table = new DataTable('#news-table', {
            responsive: true
        });
    </script>
</div>
</body>

</html>