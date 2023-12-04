<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Details | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/details.css">
    <jsp:include page="/templates/common_lib.jsp"/>
</head>

<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <div class="content">
        <div class="row row-pay">
            <div class="col-md-5">
                <div class="img-preview">
                    <div class="img-preview--prev scrollbar">
                        <c:forEach items="${Shoe_Gallery}" var="Gallery">
                            <div class="img-preview--node">
                                <image class="image-preview--img" src="${Gallery.getImageLink()}">
                            </div>
                        </c:forEach>
                    </div>
                    <div class="item-node--l">
                        <img src="${Shoe.getImageLink()}">
                    </div>
                </div>

                <div>
                    <button type="button" id="btn-share" class="btn btn-dark" data-bs-toggle="modal"
                            data-bs-target="#shareModal">
                        <i class="fa-solid fa-share"></i> Share
                    </button>

                    <div class="modal fade" id="shareModal" data-bs-backdrop="static" data-bs-keyboard="false"
                         tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="staticBackdropLabel">Share</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="sharecopy-input" readonly
                                               aria-describedby="btn-sharecopy">
                                        <button class="btn btn-secondary" type="button" id="btn-sharecopy">Copy</button>
                                    </div>
                                    <p id="i--share-noti"></p>
                                    <p class="share-note">- or share it via -</p>
                                    <div class="share-external">
                                        <div dir-to="https://facebook.com">
                                            <div class="share-icon">
                                                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/2021_Facebook_icon.svg/768px-2021_Facebook_icon.svg.png">
                                            </div>
                                            <p>Facebook</p>
                                        </div>
                                        <div dir-to="https://messenger.com">
                                            <div class="share-icon">
                                                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Facebook_Messenger_logo_2020.svg/1200px-Facebook_Messenger_logo_2020.svg.png">
                                            </div>
                                            <p>Messenger</p>
                                        </div>
                                        <div dir-to="https://zalo.me">
                                            <div class="share-icon">
                                                <img src="https://i.pinimg.com/736x/1d/07/c2/1d07c2bacaefc123ddc782e6288ddc41.jpg">
                                            </div>
                                            <p>Zalo</p>
                                        </div>
                                        <div dir-to="https://web.whatsapp.com/">
                                            <div class="share-icon">
                                                <img src="https://1.bp.blogspot.com/-MtxfH-ywp50/XxWTPtleoyI/AAAAAAAAMOQ/Sqhvarh2yH8KJ2NJoLMH3Za9l5U_vZNRACLcBGAsYHQ/s640/1.JPG">
                                            </div>
                                            <p>Whatsapp</p>
                                        </div>
                                        <div>
                                            <a id="share-mail" href="">
                                                <div class="share-icon">
                                                    <img src="https://th.bing.com/th/id/R.de75a68d4cb0c3f5b178f8a41b80b19c?rik=JZZAa5A9PFwBBg&pid=ImgRaw&r=0">
                                                </div>
                                                <p>Email</p>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-7 product-detail">
                <div class="view_product_name">
                    <p class="i--title">${Shoe.getTitle()}</p>
                </div>
                <div class="product_money">
                    <p id="i--price">$${Shoe.getPrice()}</p>
                </div>

                <div class="button_pay">
                    <form method="post" action="${pageContext.request.contextPath}/api/detail-select">
                        <input type="hidden" name="id" value="${Shoe.getId()}">
                        <input type="submit" class="buy-button" value="Stylize now!">
                        <p style="font-style: italic">Estimated delivery time: 15-30 business days.</p>
                    </form>
                </div>
                <div id="size-view">
                    <h5>Available sizes</h5>
                    <hr>
                    <div class="size-nodes">
                        <h6>Vietnam</h6>
                        <div class="row">
                            <div class="col-md-6">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th>Gender</th>
                                        <th>Size</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${Shoe_Size_M}" var="size">
                                        <tr>
                                            <td>${size.getGenderName()}</td>
                                            <td><a href="${pageContext.request.contextPath}/stylize-form?id=${Shoe.getId()}&size=${size.getId()}">${size.getSizeName()} </a> </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-6">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th>Gender</th>
                                        <th>Size</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${Shoe_Size_W}" var="size">
                                        <tr>
                                            <td>${size.getGenderName()}</td>
                                        
                                            <td><a href="${pageContext.request.contextPath}/stylize-form?id=${Shoe.getId()}&size=${size.getId()}">${size.getSizeName()} </a> </td>
                                           
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${Shoe.getDescription()}</td>
                    </tr>
                    </tbody>
                </table>


            </div>
        </div>
    </div>
</div>              

<jsp:include page="/templates/footer.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
<script src="${pageContext.request.contextPath}/js/details.js"></script>
</div>

</body>

</html>