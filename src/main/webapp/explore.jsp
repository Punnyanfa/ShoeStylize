<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Explore your style | ShoeStylize</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/explore.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://fonts.googleapis.com/css2?family=Send+Flowers&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <div class="banner">
        <div class="banner--title">
            <h2>Explore your styles</h2>
            <p>Choose your shoes</p>
        </div>
        <div class="component--search">
            <form>
                <input type="text" placeholder="Search for..." name="query"/>
                <input type="submit" value="Search">
            </form>
        </div>
    </div>
    <div class="row">
        <div class="tab-filter col-md-3">
            <h4>Search options</h4>
            <div class="filter">
                <form>
                    <fieldset>
                        <legend>Sort by alphabet:</legend>
                        <select name="sortAlphabet">
                            <option value="a-z">From A-Z</option>
                            <option value="z-a">From Z-A</option>
                        </select>
                    </fieldset>
                    <fieldset>
                        <legend>Sort by price:</legend>
                        <select name="sortPrice">
                            <option value="asc">Low to High</option>
                            <option value="desc">High to Low</option>
                        </select>
                    </fieldset>
                    <fieldset>
                        <legend>Style type:</legend>

                        <label>
                            <input type="checkbox" class="form-check-input" name="shoeType[]" value="low-tops"
                                   <c:if test="${filterType.contains('low-tops')}">checked</c:if>> Low-Tops
                        </label><br>
                        <label>
                            <input type="checkbox" class="form-check-input" name="shoeType[]" value="high-tops"
                                   <c:if test="${filterType.contains('high-tops')}">checked</c:if>> High-Tops
                        </label><br>
                        <label>
                            <input type="checkbox" class="form-check-input" name="shoeType[]" value="boots"
                                   <c:if test="${filterType.contains('boots')}">checked</c:if>> Boots
                        </label><br>

                        <input type="submit" class="btn btn-dark" value="Submit">

                    </fieldset>
                </form>
            </div>
        </div>
        <div class="tab-styles col-md-9">
            <div class="style-nodes">
                <c:forEach items="${requestScope.shoes}" var="shoe">
                    <div class="style-node zoom"
                         dir-to="${pageContext.request.contextPath}/details/${shoe.getInt("id")}">
                        <img class="image-explore" src="${shoe.getString("image_link")}">
                        <p class="style--title">${shoe.getString("title")}</p>
                        <p class="style--price">Starting from $${shoe.getDouble("price")}</p>
                        <button class="style-info-btn">More info</button>
                    </div>
                </c:forEach>
            </div>
        </div>
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