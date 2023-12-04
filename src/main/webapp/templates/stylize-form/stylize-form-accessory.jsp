<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="accessory-box">
    <h2>Accessory Picker</h2>
    <p>Pick a suitable accessory that works with your personalization</p>
    <div class="tip-box">
        <i class="fa-solid fa-lightbulb"></i> <b>Tip:</b>
        This is optional. You can continue without one.
    </div>
    <div style="width: 100%; padding: 2vw;">
        <div style="margin-bottom: 45px;">
            <h6>Added accessories</h6>
            <div class="container-se"></div>
        </div>
        <div>
            <h6>All accessories</h6>
            <table id="accessory-table" class="stripe">
                <thead>
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ShoeExtras}" var="SE">
                    <tr id="accessory-item-${SE.getId()}">
                        <td><img class="img-accessory" src="${SE.getImageLink()}"></td>
                        <td>${SE.getTitle()}</td>
                        <td>${SE.getDescription()}</td>
                        <td>$${SE.getPrice()}</td>
                        <td>
                            <button type="button" class="btn-add-extra btn btn-primary"
                                    id-value="${SE.getId()}"
                                    data-title="${SE.getTitle()}"
                                    data-price="$${SE.getPrice()}">
                                Add
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>