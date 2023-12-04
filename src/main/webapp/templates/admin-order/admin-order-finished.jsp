<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table id="order-table-success" class="stripe">
        <thead>
        <tr>
            <th>ID</th>
            <th>Image</th>
            <th>Title</th>
            <th>Size</th>
            <th>Total Price</th>
            <th>Buyer</th>
            <th>Status</th>
            <th>View</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ORDERS_SUCCESS}" var="Order">
            <tr>
                <td>${Order.getId()}</td>
                <td><img class="order--img" src="${Order.getShoe().getImageLink()}"></td>
                <td>
                    <c:choose>
                        <c:when test="${Order.getOrderShoeStatus() == 'pending'}">
                            <p class="order-tag" style="background-color: #adadad">Processing</p>
                        </c:when>
                        <c:when test="${Order.getOrderShoeStatus() == 'in_progress'}">
                            <p class="order-tag" style="background-color: #083dea">In progress</p>
                        </c:when>
                        <c:when test="${Order.getOrderShoeStatus() == 'cancelled'}">
                            <p class="order-tag" style="background-color: #e50000">Cancelled</p>
                        </c:when>
                        <c:when test="${Order.getOrderShoeStatus() == 'success'}">
                            <p class="order-tag" style="background-color: #00b711">Completed</p>
                        </c:when>
                    </c:choose>
                    <div>
                        <a href="${pageContext.request.contextPath}/details/${Order.getShoe().getId()}">${Order.getShoe().getTitle()}</a>
                        <p class="order-date"><b>Date ordered: </b>${Order.getFormatDate()}</p>
                    </div>
                </td>
                <td>${Order.getSize().getGender()} / ${Order.getSize().getSizeName()}</td>
                <td class="order--price">$${Order.getTotalPrice()}</td>
                <td>${Order.getUser().getUsername()}</td>
                <td>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#modal-status-finished-${Order.getId()}">
                        Change
                    </button>

                    <div class="modal fade" id="modal-status-finished-${Order.getId()}" tabindex="-1"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <form method="POST"
                                      action="${pageContext.request.contextPath}/api/admin/order-edit/status">
                                    <input type="hidden" name="id" value="${Order.getId()}">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Change status</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label class="form-label">Status:</label>
                                            <select class="form-select form-statusedit" name="status">
                                                <option value="pending">Pending</option>
                                                <option value="in_progress">In Progress</option>
                                                <option value="cancelled">Cancel / Reject</option>
                                                <option value="success">Success</option>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="#order-sp-msg-${Order.getId()}" class="form-label">Note for this order:</label>
                                            <textarea name="order-sp-note" class="form-control" placeholder="Enter your note here">${Order.getServiceProviderNote()}</textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                                data-bs-dismiss="modal">Close
                                        </button>
                                        <input type="submit" class="btn btn-success" value="Save changes">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <button type="button" class="btn btn-wide btn-preview btn-primary"
                            data-id="${Order.getId()}"
                            data-textureData='${Order.getTextureData()}'
                            data-textureURL='${pageContext.request.contextPath}/api/order-texture?id=${Order.getId()}'
                    >
                        Design
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#modal-info-${Order.getId()}">
                        Info
                    </button>
                    <div class="modal fade" id="modal-info-${Order.getId()}" tabindex="-1"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Info for this order</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div>
                                        <h5>Item contents:</h5>
                                        <div>
                                            <table class="table table-striped table-hover">
                                                <thead>
                                                <tr>
                                                    <th>Item</th>
                                                    <th>Value</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>Base price</td>
                                                    <td>$${Order.getShoe().getPrice()}</td>
                                                </tr>
                                                <c:forEach items="${Order.getExtras()}" var="Extra">
                                                    <tr>
                                                        <td>
                                                                ${Extra.getTitle()}
                                                        <td>
                                                        <td>${Extra.getPrice()}</td>
                                                    </tr>
                                                </c:forEach>
                                                <tr>
                                                    <td>Shipping fee</td>
                                                    <td>$2.5</td>
                                                </tr>
                                                <tr>
                                                    <td><b>Grand total</b></td>
                                                    <td class="order--price">$${Order.getTotalPrice()}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <hr>
                                    <div>
                                        <h5>Personal name</h5>
                                        <div>
                                            <p><b>Username: </b>${Order.getUser().getUsername()}</p>
                                            <p><b>Full name: </b>${Order.getUser().getFullname()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div>
                                        <h5>Address</h5>
                                        <div>
                                            <p><b>Address 1: </b>${Order.getAddress1()}</p>
                                            <p><b>Address 2: </b>${Order.getAddress2()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div>
                                        <h5>Phone</h5>
                                        <div>
                                            <p><b>Phone 1: </b>${Order.getPhone1()}</p>
                                            <p><b>Phone 2: </b>${Order.getPhone2()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div>
                                        <div>
                                            <p><b>Customer's Note:</b></p>
                                            <p>${Order.getCustomerNote()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div>
                                        <h5>References</h5>
                                        <div>
                                            <p><b>Prompt:</b> ${Order.getReferencePrompt()}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                        Close
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>