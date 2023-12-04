<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User setting | ShoeStylize</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user-setting.css">
        <jsp:include page="/templates/common_lib.jsp"/>
    </head>
    <body>
        <div class="app">
            <jsp:include page="/templates/header.jsp"/>
            <div class="row" id="content">
                <div id="v-content">
                    <p style="font-weight: 600; font-size: 28px;">Edit profile</p>
                    <hr>
                    <p class="form-success">${MSG_SUCCESS}</p>
                    <form id="form-edit" action='${pageContext.request.contextPath}/api/user-settings' method='POST' accept-charset="UTF-8">
                        <section>
                            <h5>Basic info:</h5>
                            <div>
                                <label for="form-email" class="form-label label-important">Username</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" value="${user.getUsername()}" readonly required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <label for="form-firstname" class="form-label label-important">First name</label>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="form-firstname" name="form-firstname" value="${user.getFirstname()}" required>
                                    </div>
                                    <p class="form-error">${FIRSTNAME_ERR}</p>
                                </div>
                                <div class="col">
                                    <label for="form-lastname" class="form-label label-important">Last name</label>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="form-lastname" name="form-lastname" value="${user.getLastname()}" required>
                                    </div>
                                    <p class="form-error">${LASTNAME_ERR}</p>
                                </div>
                            </div>
                            <div>
                                <label for="form-email" class="form-label label-important">Email</label>
                                <div class="input-group mb-3">
                                    <input type="email" class="form-control" id="form-email" name="form-email" value="${user.getEmail()}" required>
                                </div>
                                <p class="form-error">${EMAIL_ERR}</p>
                            </div>
                            <div>
                                <label for="form-phone" class="form-label label-important">Phone</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" id="form-phone" name="form-phone" value="${user.getPhonenumber()}" required>
                                </div>
                                <p class="form-error">${PHONE_ERR}</p>
                            </div>
                            <div>
                                <label for="form-address" class="form-label label-important">Address</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" id="form-address" name="form-address" value="${user.getAddress()}" required>
                                </div>
                                <p class="form-error">${ADDRESS_ERR}</p>
                            </div>
                            <div>
                                <label for="form-dob" class="form-label label-important">Date of birth</label>
                                <div class="input-group mb-3">
                                    <input type="date" class="form-control" id="form-dob" name="form-dob" value="${user.getDob()}" required>
                                </div>
                                <p class="form-error">${DOB_ERR}</p>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <label for="form-city" class="form-label">City</label>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="form-city" name="form-city" value="${user.getCity()}">
                                    </div>
                                    <p class="form-error">${CITY_ERR}</p>
                                </div>
                                <div class="col">
                                    <label for="form-state" class="form-label">State</label>
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" id="form-state" name="form-state" value="${user.getState()}">
                                    </div>
                                    <p class="form-error">${STATE_ERR}</p>
                                </div>
                            </div>
                        </section>
                        <section>
                            <h5>Change password:</h5>
                            <div class="row">
                                <div class="col">
                                    <label for="form-password" class="form-label">Password</label>
                                    <div class="input-group mb-3">
                                        <input type="password" class="form-control" id="form-password" name="form-password">
                                    </div>
                                    <p class="form-error">${PASSWORD_ERR}</p>
                                </div>
                                <div class="col">
                                    <label for="form-repassword" class="form-label">Re-enter new password</label>
                                    <div class="input-group mb-3">
                                        <input type="password" class="form-control" id="form-repassword" name="form-repassword">
                                    </div>
                                    <p class="form-error">${REPASSWORD_ERR}</p>
                                </div>
                            </div>
                        </section>
                        <div>
                            <input type="submit" class="btn btn-success" value="Update my profile">
                        </div>
                    </form>
                </div>
            </div>
            <jsp:include page="/templates/footer.jsp"/>
            <jsp:include page="/templates/common_js.jsp"/>
        </div>
    </body>

</html>
