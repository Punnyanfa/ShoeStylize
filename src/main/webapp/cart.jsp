<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cart | ShoeStylize</title>
    <link rel="icon" href="./assets/logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css">
    <jsp:include page="/templates/common_lib.jsp"/>
</head>

<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <div class="content">
        <div>
            <div class="content-tab">
                <h3>Your queued orders</h3>
                <c:choose>
                    <c:when test="${bill.getOrders().size() > 0}">
                        <button type="button" class="btn btn-success"
                                dir-to="${pageContext.request.contextPath}/cart/checkout">Start my order
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-secondary">There is no order in queue</button>
                    </c:otherwise>
                </c:choose>
            </div>
            <hr>
            <div class="cart">
                <table id="cart-table" class="stripe">
                    <thead>
                    <tr>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Total price</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Orders}" var="Order">
                        <tr>
                            <td>
                                <img class="order-image" src="${Order.getShoeImage()}">
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/details/${Order.getShoeId()}">${Order.getShoeTitle()}
                            </td>
                            <td class="order-price">$${Order.getPrice()}</td>
                            <td>
                                <button type="button" class="btn btn-primary btn-cart-info btn-wide"
                                        data-uuid="${Order.getUUID()}"
                                >View info
                                </button>
                                <form method="POST" action="${pageContext.request.contextPath}/api/carts/delete">
                                    <input type="hidden" name="id-value" value="${Order.getUUID()}">
                                    <input type="submit" class="btn btn-danger btn-wide" value="Remove">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="modal fade" id="modal-view" tabindex="-1" aria-labelledby="model label" aria-hidden="true">
                    <div class="modal-dialog modal-xl modal-dialog-scrollable">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Cart info</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <canvas class="webgl"></canvas>
                                        <p>Move to view 3D</p>
                                    </div>
                                    <div class="col-md-6">
                                        <div>
                                            <h4 id="form-view--title"></h4>
                                            <hr>
                                        </div>
                                        <div>
                                            <h6>Item info</h6>
                                            <table class="table table-md">
                                                <thead>
                                                <tr>
                                                    <th>Title</th>
                                                    <th>Price</th>
                                                </tr>
                                                </thead>
                                                <tbody id="form-view--extras">
                                                </tbody>
                                            </table>
                                        </div>

                                        <div>
                                            <h5>Grand total: <span id="form-view--totalPrice"></span></h5>
                                        </div>
                                        <hr>
                                        <div>
                                            <h6>Phones</h6>
                                            <ul>
                                                <li><b>Phone 1: </b><span id="form-view--phone1"></span></li>
                                                <li><b>Phone 2: </b><span id="form-view--phone2"></span></li>
                                            </ul>
                                        </div>
                                        <div>
                                            <h6>Address</h6>
                                            <ul>
                                                <li><b>Address 1: </b><span id="form-view--address1"></span></li>
                                                <li><b>Address 2: </b><span id="form-view--address2"></span></li>
                                            </ul>
                                        </div>
                                        <div>
                                            <h6>Your note:</h6>
                                            <p id="form-view--customerNote"></p>
                                        </div>
                                        <hr>
                                        <h5>References:</h5>
                                        <div>
                                            <h6>Clue prompt:</h6>
                                            <p id="form-view--referencePrompt"></p>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/templates/footer.jsp"/>
    <jsp:include page="/templates/common_js.jsp"/>
    <script type="module" src="${pageContext.request.contextPath}/js/cart_design.js"></script>
    <script>
        new DataTable('#cart-table', {
            responsive: true
        });
    </script>
</div>

</body>

</html>