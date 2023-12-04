<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Styles | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin-tab.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/styles.css">

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
            <h3>Manage Style - Admin</h3>
            <div class="content-tool">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#add-style-modal">
                    Add new styles
                </button>
            </div>
            <div class="modal fade" id="add-style-modal" tabindex="-1" aria-hidden="true">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Add new style</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="basic-form">
                                <div>
                                    <label for="basic-title" class="form-label label-important">Title</label>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="basic-title"
                                               aria-describedby="basic-title">
                                    </div>
                                    <p class="p-error" id="basic-errTitle"></p>
                                </div>
                                <div>
                                    <label for="basic-image" class="form-label label-important">Image URL</label>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="basic-image"
                                               aria-describedby="basic-image">
                                    </div>
                                    <p class="p-error" id="basic-errImage"></p>
                                </div>
                                <div>
                                    <label for="basic-description"
                                           class="form-label label-important">Description</label>
                                    <textarea id="basic-description" class="form-control"
                                              aria-label="With textarea"></textarea>
                                    <p class="p-error" id="basic-errDescription"></p>
                                </div>
                                <div>
                                    <label for="basic-price" class="form-label label-important">Price</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">$</span>
                                        <input type="text" class="form-control" id="basic-price"
                                               aria-describedby="basic-price">
                                    </div>
                                    <div>
                                        <p class="p-error" id="basic-errPrice"></p>
                                    </div>
                                </div>
                                <div>
                                    <label for="basic-type" class="form-label label-important">Type</label>
                                    <div class="input-group mb-3">
                                        <select class="form-select" id="basic-type">
                                            <option selected>Choose...</option>
                                            <option value="low-tops">Low-tops</option>
                                            <option value="high-tops">High-tops</option>
                                            <option value="boots">Boots</option>
                                        </select>
                                    </div>
                                    <p class="p-error" id="basic-errType"></p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <button type="button" id="basic-submit" class="btn btn-success">Add new style
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <table id="style-table" class="stripe">
                    <thead>
                    <tr>
                        <th class="text-center">ID</th>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Type</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${LIST_SHOES}" var="Shoe">
                        <tr>
                            <td class="text-center">
                                <p>${Shoe.getId()}</p>
                                <input type="hidden" name="txtId" value="${User.getId()}"/>
                            </td>
                            <td>
                                <img src="${Shoe.getImageLink()}" style="width: 100%; aspect-ratio: 1;">
                            </td>
                            <td>
                                <p>${Shoe.getTitle()}</p>
                            </td>
                            <td>
                                <p>${Shoe.getType()}</p>
                            </td>
                            <td><p>${Shoe.getDescription()}</p></td>
                            <td><p>${Shoe.getPrice()}</p></td>
                            <td><input type="submit" class="btn btn-primary" value="Edit"></td>
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
    let table = new DataTable('#style-table', {
        responsive: true
    });

    $('#basic-submit').click(c => {
        apost('${pageContext.request.contextPath}/api/admin/add-style', {
            title: $('#basic-title').val(),
            type: $('#basic-type').val(),
            description: $('#basic-description').val(),
            image: $('#basic-image').val(),
            price: $('#basic-price').val()
        }, s => {
            if (s.code == 400) {
                $('#basic-errTitle').text(s.error.title)
                $('#basic-errType').text(s.error.type)
                $('#basic-errDescription').text(s.error.desc)
                $('#basic-errImage').text(s.error.image)
                $('#basic-errPrice').text(s.error.price)
                return;
            }

            if (s.code === 201) {
                window.location.reload()
            }
        })
    });
</script>
</div>
</body>

</html>