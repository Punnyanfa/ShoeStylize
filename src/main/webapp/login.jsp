<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login | ShoeStylize</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
        <jsp:include page="/templates/common_lib.jsp"/>
    </head>

    <body>
        <div class="app">
            <jsp:include page="/templates/header.jsp"/>
            <div class="content row">
                <section id="panel--login" class="col-md-6">
                    <h1>Login</h1>
                    <form id="login-form" action="login" method="post">
                        <p class="text-danger text-center">${error}</p>
                        <f:view>
                            <h:form>
                                <p style="color: red">${SUCCESS_CUS}</p>
                            </h:form>
                        </f:view>
                        <f:view>
                            <h:form>
                                <p style="color: red">${SUCCESS_PROV}</p>
                            </h:form>
                        </f:view>
                        <div>
                            <label for="login-form--username" class="form-label">Username</label>
                            <div class="input-group mb-3">
                                <input type="text" name="username" class="form-control" id="login-form--username" aria-describedby="login">
                            </div>
                        </div>

                        <div>
                            <label for="login-form--password" class="form-label">Password</label>
                            <div class="input-group mb-3">
                                <input type="password" name="password" class="form-control" id="login-form--password" aria-describedby="login">
                            </div>
                        </div>
                        <div>
                            <input class="btn btn-dark" type="submit" value="Login">
                        </div>
                    </form>
                    <div class="google-form">
                        <p>Or login by Google instead</p>
                        <button type="button" class="btn btn-primary" dir-to="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/ShoeStylize_Tomcat/LoginGoogleHandler&response_type=code
                                &client_id=989356944004-1q9hg3s8ifv3f6j26fra0qq4fc9ai8cd.apps.googleusercontent.com&approval_prompt=force">Login with Google</button>
                    </div>

                    <div class="login-addi">
                        <p>Did not have an account? <a class="btn--a-sign-up" href="${pageContext.request.contextPath}/register">Sign up here</a></p>
                    </div>
                    <div class="login-addi">
                        <p><a class="btn--a-sign-up" href="${pageContext.request.contextPath}/forgot-password">Forgot password</a></p>
                    </div>
                </section>
                <section id="panel--info" class="col-md-6">
                    <h2>Welcome to ShoeStylize!</h2>
                    <p>Log in now to start your ambition of your footwear.</p>
                    <button class='panel--info-btn zoom' dir-to="${pageContext.request.contextPath}/home.jsp">More info</button>
                </section>
            </div>
            <jsp:include page="/templates/footer.jsp"/>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
            <script src="https://accounts.google.com/gsi/client" async defer></script>

        </div>
    </body>
</html>
