<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Users | Shoe Stylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin-tab.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/users.css">
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
            <table id="user-table" class="stripe">
                <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th>Username</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Edit</th>
                </tr>
                </thead>
                <tbody id="user-data">
                <c:forEach items="${USERS}" var="User">
                    <tr>
                        <td class="text-center">
                            <p>${User.getId()}</p>
                        </td>
                        <td>
                            <p>${User.getUsername()}</p>
                        </td>
                        <td>
                            <p>${User.getFullname()}</p>
                        </td>
                        <td>
                            <p>${User.getEmail()}</p>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${User.getRole() == 'admin'}">
                                    <p class="user-tag" style="background-color: #c40000">Admin</p>
                                </c:when>
                                <c:when test="${User.getRole() == 'service_provider'}">
                                    <p class="user-tag" style="background-color: #00218f">Staff</p>
                                </c:when>
                                <c:when test="${User.getRole() == 'customer'}">
                                    <p class="user-tag" style="background-color: #048f00">Customer</p>
                                </c:when>
                            </c:choose>
                        </td>
                        <td><button type="button" class="btn btn-dark btn-edit" id-value="${User.getId()}">Edit</button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div>
                <div class="modal fade" id="userModal" tabindex="-1" aria-labelledby="userModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form method="POST" action="${pageContext.request.contextPath}/api/admin/users-update">
                                <input type="hidden" name="id" id="id">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="userModalLabel">Edit user info</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="username" class="form-label">Username</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="username"
                                                   readonly>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="firstName" class="form-label">First Name</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="firstName" name="firstName"
                                                   required>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="lastName" class="form-label">Last Name</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="lastName" name="lastName"
                                                   required>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email</label>
                                        <div class="input-group">
                                            <span class="input-group-text">@</span>
                                            <input type="email" class="form-control" id="email" name="email" required>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="address" class="form-label">Address</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="address" name="address"
                                                   required>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="city" class="form-label">City</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="city" name="city" required>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="state" class="form-label">State</label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="state" name="state" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Terminate account</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <input type="submit" class="btn btn-primary" value="Save changes">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/templates/common_js.jsp"/>
    <script>
        let table = new DataTable('#user-table', {
            responsive: true
        });

        $('.btn-edit').click(c => {
            aget('${pageContext.request.contextPath}/api/admin/user', {
                id: $(c.target).attr("id-value")
            }, s => {
                $('#id').val(s.id)
                $('#username').val(s.username)
                $("#firstName").val(decodeURIComponent(s.first_name))
                $('#lastName').val(decodeURIComponent(s.last_name))
                $('#email').val(s.email)
                $('#address').val(decodeURIComponent(s.address))
                $('#state').val(decodeURIComponent(s.state))
                $('#city').val(decodeURIComponent(s.city))

                var userModal = new bootstrap.Modal($("#userModal"), {
                    keyboard: false
                })
                userModal.toggle()
            })
        })
    </script>
</div>
</body>

</html>