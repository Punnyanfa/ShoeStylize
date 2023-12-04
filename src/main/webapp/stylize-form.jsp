<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Stylize Form | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/stylize-form.css">
    <jsp:include page="/templates/common_lib.jsp"/>
</head>

<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <form method="POST" action="${pageContext.request.contextPath}/api/stylize-form/input"
          enctype="multipart/form-data">
        <div class="content row y-locked">
            <section class="col-md-7" id="sect-design">
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="design" role="tabpanel" aria-labelledby="design-tab">
                        <div id="design-panel">
                            <h5>Pick your color</h5>
                            <div id="design-panel-selector">
                                <div id="d-front-container">
                                    <span>Front</span>
                                    <div class="color-picker">
                                        <input type="color" class="primary_color" id="color-front" class="field-radio"
                                               name="color-front" value="#FFFFFF">
                                    </div>
                                </div>
                                <div>
                                    <span>Footer</span>
                                    <div class="color-picker">
                                        <input type="color" class="primary_color" id="color-footer" class="field-radio"
                                               name="color-footer" value="#FFFFFF">
                                    </div>
                                </div>
                                <div>
                                    <span>Lace</span>
                                    <div class="color-picker">
                                        <input type="color" class="primary_color" id="color-lace" class="field-radio"
                                               name="color-lace" value="#FFFFFF">
                                    </div>
                                </div>
                                <div>
                                    <span>Inner</span>
                                    <div class="color-picker">
                                        <input type="color" class="primary_color" id="color-inner" class="field-radio"
                                               name="color-inner" value="#FFFFFF">
                                    </div>
                                </div>
                                <div>
                                    <span>Pin</span>
                                    <div class="color-picker">
                                        <input type="color" class="primary_color" id="color-pin" class="field-radio"
                                               name="color-pin" value="#FFFFFF">
                                    </div>
                                </div>
                                <div>
                                    <span>Pad</span>
                                    <div class="color-picker">
                                        <input type="color" class="primary_color" id="color-pad" class="field-radio"
                                               name="color-pad" value="#FFFFFF">
                                    </div>
                                </div>
                                <div>
                                    <span>Mini String</span>
                                    <div class="color-picker">
                                        <input type="color" class="primary_color" id="color-mini-string"
                                               class="field-radio"
                                               name="color-mini-string"
                                               value="#FFFFFF">
                                    </div>
                                </div>
                            </div>
                            <!-- Add Texture (File Upload) -->
                            <div class="mb-3">
                                <label for="textureUpload" class="form-label">Add Texture</label>
                                <input type="file" class="form-control" id="textureUpload" name="textureUpload"
                                       enctype="multipart/form-data">
                            </div>
                            <canvas class="webgl"></canvas>
                            <p class="tip-box" id="reference-link">
                                <i class="fa-solid fa-lightbulb"></i> <b>Tip:</b> Stuck on ideas? Use reference mode
                                instead.
                            </p>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="reference" role="tabpanel" aria-labelledby="reference-tab">
                        <div id="reference-box">
                            <h2>Reference mode enabled</h2>
                            <p>If you want your shoe custom drawn, please <span id="design-link">click here</span>.</p>
                            <div style="width: 100%; padding: 0 3vw;">
                                <div class="mb-3">
                                    <label for="referencePrompt" class="form-label label-important">Prompt:</label>
                                    <div class="input-group">
                                        <textarea class="form-control" id="referencePrompt" name="form-referencePrompt"
                                                  rows="3"
                                                  placeholder="Enter your prompt here"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="tip-box">
                                <i class="fa-solid fa-lightbulb"></i> <b>Tip:</b>
                                Reference mode is used for customers who might need the help from our service providers
                                in
                                case you don't know what to add.
                                <br>
                                Just provide your prompt then we'll keep you in touch.
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="accessory" role="tabpanel" aria-labelledby="accessory-tab">
                        <jsp:include page="/templates/stylize-form/stylize-form-accessory.jsp"/>
                    </div>
                </div>
            </section>
            <section class="col-md-5" id="sect-form">
                <div>
                    <p>You are processing your order for</p>
                    <h3>${Shoe.getTitle()}
                        <i class="fa-solid fa-link" style="cursor: pointer;"
                           dir-to="${pageContext.request.contextPath}/details/${Shoe.getId()}"></i></h3>
                    <p>Initial price: <b style="color: #FF0000">$${Shoe.getPrice()}</b></p>
                </div>
                <div>
                    <h5>Choose your styling method...</h5>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active"
                                    id="design-tab"
                                    data-bs-toggle="tab"
                                    data-bs-target="#design"
                                    type="button"
                                    role="tab"
                                    aria-controls="design"
                                    aria-selected="true">Design
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link"
                                    id="reference-tab"
                                    data-bs-toggle="tab"
                                    data-bs-target="#reference"
                                    type="button"
                                    role="tab"
                                    aria-controls="reference"
                                    aria-selected="false">Reference
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link"
                                    id="accessory-tab"
                                    data-bs-toggle="tab"
                                    data-bs-target="#accessory"
                                    type="button"
                                    role="tab"
                                    aria-controls="accessory"
                                    aria-selected="false">Accessory
                            </button>
                        </li>
                    </ul>
                </div>
                <div>
                    <h5>...then finish the form.</h5>
                    <div>
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="firstName" class="form-label label-important">First Name</label>
                                <input type="text" class="form-control" id="firstName" name="form-firstName"
                                       value="${user.getFirstname()}" readonly>
                            </div>
                            <div class="col-md-6">
                                <label for="lastName" class="form-label label-important">Last Name</label>
                                <input type="text" class="form-control" id="lastName" name="form-lastName"
                                       value="${user.getLastname()}" readonly>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label label-important">Email</label>
                            <input type="email" class="form-control" id="email" name="form-email"
                                   value="${user.getEmail()}"
                                   readonly>
                        </div>

                        <!-- Address 1 -->
                        <div class="mb-3">
                            <label for="address1" class="form-label label-important">Address 1</label>
                            <input type="text" class="form-control" id="address1" name="form-address1"
                                   value="${user.getAddress()}" readonly>
                        </div>

                        <!-- Address 2 -->
                        <div class="mb-3">
                            <label for="address2" class="form-label">Address 2</label>
                            <input type="text" class="form-control" id="address2" name="form-address2">
                        </div>

                        <!-- Phone 1 -->
                        <div class="mb-3">
                            <label for="phone1" class="form-label label-important">Phone 1</label>
                            <input type="tel" class="form-control" id="phone1" name="form-phone1"
                                   value="${user.getPhonenumber()}">
                        </div>

                        <!-- Phone 2 -->
                        <div class="mb-3">
                            <label for="phone2" class="form-label">Phone 2</label>
                            <input type="tel" class="form-control" id="phone2" name="form-phone2">
                        </div>

                        <!-- Size Option -->
                        <div class="mb-3">
                            <label for="size" class="form-label">Size</label>

                            <select class="form-select" id="size" name="form-size">
                                <c:forEach items="${Shoe_Size}" var="Size">
                                    <c:choose>
                                        <c:when test="${Size.getId() eq Shoe_size_selected}">
                                            <!-- Nếu là giá trị đã chọn, thêm thuộc tính selected -->
                                            <option value="${Size.getId()}" selected>${Size.getGenderName()} / ${Size.getSizeName()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Nếu không phải giá trị đã chọn -->
                                            <option value="${Size.getId()}">${Size.getGenderName()} / ${Size.getSizeName()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- Customer Note -->
                        <div class="mb-3">
                            <label for="customerNote" class="form-label">Customer Note</label>
                            <textarea class="form-control" id="customerNote" name="form-customerNote"></textarea>
                        </div>

                        <input type="hidden" id="textureData" name="form-textureData">
                        <!-- Submit Button -->
                        <input type="submit" id="btn-continue-order" class="btn btn-primary"
                               value="Continue my order...">
                    </div>
                </div>
            </section>
        </div>
    </form>
</div>

<jsp:include page="/templates/common_js.jsp"/>
<script src="${pageContext.request.contextPath}/js/stylize-form.js"></script>
<script type="module" src="${pageContext.request.contextPath}/js/details_design.js"></script>
<script>
    let table = new DataTable('#accessory-table', {
        responsive: true
    });

    $('#design-link').click(c => {
        let tab = new bootstrap.Tab($('#design-tab'))
        tab.show();
    })
    $('#reference-link').click(c => {
        let tab = new bootstrap.Tab($('#reference-tab'))
        tab.show();
    })

    let textureData = {}

    let loadTextureData = () => {
        textureData = {
            front: $('#color-front').val(),
            footer: $('#color-footer').val(),
            lace: $('#color-lace').val(),
            inner: $('#color-inner').val(),
            pin: $('#color-pin').val(),
            pad: $('#color-pad').val(),
            mini_string: $('#color-mini-string').val()
        }
        $('#textureData').val(JSON.stringify(textureData))
    }

    $("#color-front").change(function () {
        loadTextureData()
    });

    $("#color-footer").change(function () {
        loadTextureData()
    });

    $("#color-lace").change(function () {
        loadTextureData()
    });

    $("#color-inner").change(function () {
        loadTextureData()
    });

    $("#color-pin").change(function () {
        loadTextureData()
    });

    $("#color-pad").change(function () {
        loadTextureData()
    });

    $("#color-mini-string").change(function () {
        loadTextureData()
    });

    loadTextureData();
</script>
</div>
</body>
</html>