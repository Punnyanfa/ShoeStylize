<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Google | ShoeStylize</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
        <jsp:include page="/templates/common_lib.jsp"/>
    </head>

    <body>
        <div class="app">
            <jsp:include page="/templates/header.jsp"/>
            <div class="content row">
                <section class="col-md-4" id="panel--info">
                    <h2>Welcome to ShoeStylize!</h2>
                    <p>Register now and become an ambitious member of your new footwear designs.</p>
                    <button class='panel--info-btn zoom' dir-to="${pageContext.request.contextPath}/home">More info</button>
                </section>
                <section id="panel--reg" class="overflow-y-scroll col-md-8">
                    <h1>Register (Google)</h1>
                    <h6>Finish this form to register through your Google account.</h6>
                    <form id="reg-form" action="${pageContext.request.contextPath}/api/register-google" method="post">
                        <div>
                            <label for="username" class="label-important">Username:</label>
                            <input type="text" class="form-control" id="username" name="username" value="${USERNAME}" required>
                        </div>
                        <p style="color: red">${USERNAME_ERR}</p>
                        <div>
                            <label for="password" class="label-important">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" value="${PASSWORD}" required>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label for="firstname" class="label-important">First Name:</label>
                                <input type="text" id="firstname" class="form-control" name="firstname" value="${FIRSTNAME}"
                                       required>
                                <p style="color: red">${FIRSTNAME_ERR}</p>
                            </div>

                            <div class="col">
                                <label for="lastname" class="label-important">Last Name:</label>
                                <input type="text" id="lastname" class="form-control" name="lastname" value="${LASTNAME}"
                                       required>
                                <p style="color: red">${LASTNAME_ERR}</p>
                            </div>
                        </div>
                        <div>
                            <label for="email" class="label-important">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" value="${EMAIL}" required readonly>
                            <p style="color: red">${EMAIL_ERR}</p>
                        </div>
                        <input type="hidden" name="password" value="1111">
                        <div>
                            <label for="dob" class="label-important">Date of birth:</label>
                            <input type="date" class="form-control" id="dob" name="dob" value="${DOB}" required>
                            <p style="color: red">${DOB_ERR}</p>
                        </div>
                        <div>
                            <label for="phone" class="label-important">Phone number:</label>
                            <input type="text" class="form-control" id="phone" name="phone" value="${PHONE}">
                            <p style="color: red">${PHONE_ERR}</p>
                        </div>
                        <div>
                            <label for="address" class="label-important">Address:</label>
                            <input type="text" class="form-control" id="address" name="address" value="${ADDRESS}">
                            <p style="color: red">${ADDRESS_ERR}</p>
                        </div>
                        <div>
                            <label for="city" class="">City:</label>
                            <input type="text" class="form-control" id="city" name="city" value="${CITY}">
                        </div>
                        <div>
                            <label for="state" class="">State:</label>
                            <input type="text" class="form-control" id="state" name="state" value="${STATE}">
                        </div>
                        <div>
                            <input id="btn--reg" class="btn btn-dark" type="submit" value="Register">
                        </div>
                    </form>
                    <div id="reg-addi">
                        <p>Already have account? <a id="btn--a-login" href="./login.jsp">Sign in now</a>.</p>
                    </div>
                </section>
            </div>
            <jsp:include page="/templates/common_js.jsp"/>
        </div>
    </body>
</html>
