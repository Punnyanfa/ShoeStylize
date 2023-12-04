<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Check before pay | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart-checkout.css">
    <jsp:include page="/templates/common_lib.jsp"/>
</head>

<body>
<div class="app check_address">
    <jsp:include page="/templates/header.jsp"/>
    <div class="content row">
        <div class="col-md-8">
            <div class="page-check">
                <p class="sect-title">Confirmed orders</p>
                <hr>
                <div class="orders">
                    <c:forEach items="${bill.getOrders()}" var="Order">
                        <div class="node-order row">
                            <div class="col-md-3">
                                <img id="form-view--img" src="${Order.getShoeImage()}">
                                <button type="button" class="btn btn-preview btn-primary"
                                        data-uuid="${Order.getUUID()}"
                                        data-textureData='${Order.getTextureData()}'>
                                    Check design
                                </button>
                            </div>
                            <div class="col-md-9">
                                <div>
                                    <h4 id="form-view--title">${Order.getTitle()}
                                        (Size: ${Order.getSizeGender()}/${Order.getSizeName()})</h4>
                                    <hr>
                                </div>
                                <div>
                                    <table class="table table-md table-cart">
                                        <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>Price</th>
                                        </tr>
                                        </thead>
                                        <tbody id="form-view--extras">
                                        <tr>
                                            <td>${Order.getTitle()}</td>
                                            <td>$${Order.getPrice()}</td>
                                        </tr>
                                        <c:forEach items="${Order.getExtras()}" var="Extras">
                                            <tr>
                                                <td>${Extras.getTitle()}</td>
                                                <td>$${Extras.getPrice()}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <hr>
                                <div style="display: grid; grid-template-columns: 0.4fr 0.6fr;">
                                    <div>
                                        <h6>Phones</h6>
                                        <ul>
                                            <li><b>Phone 1: </b><span id="form-view--phone1">${Order.getPhone1()}</span>
                                            </li>
                                            <li><b>Phone 2: </b><span id="form-view--phone2"></span>${Order.getPhone2()}
                                            </li>
                                        </ul>
                                    </div>
                                    <div>
                                        <h6>Address</h6>
                                        <ul>
                                            <li><b>Address 1: </b><span
                                                    id="form-view--address1">${Order.getAddress1()}</span></li>
                                            <li><b>Address 2: </b><span
                                                    id="form-view--address2">${Order.getAddress2()}</span></li>
                                        </ul>
                                    </div>
                                </div>
                                <hr>
                                <div>
                                    <span><h6>Your note: </h6>${Order.getCustomerNote()}</span>
                                </div>
                                <hr>
                                <div>
                                    <h6>Clue prompt:</h6>
                                    <p id="form-view--referencePrompt">${Order.getReferenceInput()}</p>
                                </div>
                                <hr>
                                <div>
                                    <h5 class="label-grand-total">Grand total: <span
                                            id="form-view--totalPrice">$${Order.getTotalPrice()}</span>
                                    </h5>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
                <div class="modal fade" id="modal-preview" tabindex="-1">
                    <div class="modal-dialog modal-dialog-scrollable modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Preview</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div id="modal-content-canvas">
                                    <canvas></canvas>
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
        <div class="col-md-4">
            <form action="${pageContext.request.contextPath}/cart/checkout" method="POST">
                <div id="page-pay">
                    <p class="sect-title">Choose payment method:</p>
                    <div class="container mt-5">
                        <div class="input-group mt-2">
                            <div class="input-group-text">
                                <input class="form-check-input" type="radio" name="paymentOption" id="cod" value="COD"
                                       checked>
                                <label class="form-check-label" for="cod">
                                    <span>&nbsp;Cash On Delivery (COD)</span>
                                </label>
                            </div>
                        </div>

                        <!-- ViettelPay -->
                        <div class="input-group mt-2">
                            <div class="input-group-text">
                                <input class="form-check-input" type="radio" name="paymentOption" id="viettelPay"
                                       value="ViettelPay">
                                <label class="form-check-label" for="viettelPay">
                                    <img class="method-icon"
                                         src=https://th.bing.com/th/id/R.f804811b5e03ea05441a383914125be7?rik=iiFw8fZ9%2fjfrDA&pid=ImgRaw&r=0"
                                         alt="icon">
                                    ViettelPay</label>
                            </div>
                        </div>

                        <!-- Momo -->
                        <div class="input-group mt-2">
                            <div class="input-group-text">
                                <input class="form-check-input" type="radio" name="paymentOption" id="momo"
                                       value="Momo">
                                <label class="form-check-label" for="momo">
                                    <img class="method-icon"
                                         src=https://th.bing.com/th/id/OIP.R5SlFc9mZwn-2N7e2-RChQHaEN?pid=ImgDet&rs=1"
                                         alt="icon">
                                    Momo</label>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="page-total">
                    <div class="page-total--node">
                        <label>Total price</label>
                        <p class="price-status">$${bill.getTotalPrice(false)}</p>
                    </div>
                    <div class="page-total--node">
                        <label>Shipping fee included</label>
                        <p class="price-status">$${bill.getShippingFee()}</p>
                    </div>
                    <hr>
                    <div class="page-total--node">
                        <label>Final price</label>
                        <p id="price-final">$${bill.getTotalPrice(true)}</p>
                    </div>
                </div>
                <div id="page-button">
                    <div class="input-group mb-3">
                        <div class="input-group-text">
                            <input class="form-check-input mt-0" name="form_email" type="checkbox"
                                   aria-label="Checkbox for email">
                        </div>
                        <span class="form-control"> Receive order confirmation via email</span>
                    </div>
                    <input type="submit" id="btn-submit" class="btn btn-dark" value="Proceed transaction">
                </div>
            </form>
        </div>

    </div>
    <jsp:include page="/templates/footer.jsp"/>
    <jsp:include page="/templates/common_js.jsp"/>
    <script type="module" src="${pageContext.request.contextPath}/js/cart-checkout.js"></script>
</div>
</body>
</html>