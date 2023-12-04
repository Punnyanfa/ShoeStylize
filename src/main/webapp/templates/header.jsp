<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-light nav-fixed">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
            <p class="ss-logo pointer" style="width: 50px; height: 50px;"></p>
        </a>
        <form>
            <div class="d-flex">
                <input class="form-control me-2" type="search" id="nav-search" placeholder="Quick search" aria-label="Search">
            </div>
            <div class="search-container">
                <div class="search-container-ab"></div>
            </div>
        </form>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav mb-2 mb-lg-0 align-items-center">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/explore">EXPLORE YOUR STYLES</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/news"
                       class="text-decoration-none">NEWS</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/contact.jsp"
                       class="text-decoration-none">CONTACT US</a>
                </li>
                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item d-flex">
                        <a class="nav-link btn-cart" href="${pageContext.request.contextPath}/carts"><i
                                class="fa-solid fa-cart-shopping"></i></a>
                        <c:if test="${bill != null && !bill.getOrders().isEmpty()}">
                            <div class="cart-circle">${bill.getOrders().size()}</div>
                        </c:if>
                    </li>
                </c:if>
                <li class="nav-item dropdown" id="sel-acc">
                    <a class="nav-link profile dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fa-regular fa-user"></i>
                        <div>
                            <p>${user.getUsername()}</p>
                            <p id="profile--fullname">${user.getFirstname()} ${user.getLastname()}</p>
                        </div>
                    </a>
                    <ul class="dropdown-menu z-highest" aria-labelledby="navbarDropdown">
                        <c:choose>
                            <c:when test="${sessionScope.user != null}">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/purchases">My purchases</a></li>
                                <c:if test='${user.getRole() == "admin"}'>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/dashboard">Admin
                                        panel</a></li>
                                </c:if>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/settings">Setting</a></li>
                                <li><p class="dropdown-item" id="btn-logout">Logout</p></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Login</a>
                                </li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/register.jsp">Register</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

</nav>
