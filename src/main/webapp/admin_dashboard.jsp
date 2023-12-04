<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Dashboard | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin-tab.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/dashboard.css">
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
            <div class="content-frame-scrollable">
                <h3 style="margin-bottom: 25px;">Admin Dashboard</h3>
                <section id="sect-links">
                    <h4>Quick access:</h4>
                    <div id="sect-links--grid">
                        <div class="node-link zoom" dir-to="${pageContext.request.contextPath}/admin/users">
                            <p><i class="fa-solid fa-user"></i></p>
                            <p>Manage Users</p>
                        </div>
                        <div class="node-link zoom" dir-to="${pageContext.request.contextPath}/admin/news">
                            <p><i class="fa-solid fa-newspaper"></i></p>
                            <p>Manage News</p>
                        </div>
                        <div class="node-link zoom" dir-to="${pageContext.request.contextPath}/admin/orders">
                            <p><i class="fa-solid fa-cart-shopping"></i></p>
                            <p>Manage Orders</p>
                        </div>
                        <div class="node-link zoom" dir-to="${pageContext.request.contextPath}/admin/styles">
                            <p><i class="fa-solid fa-paintbrush"></i></p>
                            <p>Manage Styles</p>
                        </div>
                    </div>
                </section>
                <section id="sect-users" class="row">
                    <h4>User status:</h4>
                    <div class="row col-md-5 node-users-row">
                        <div class="node-users">
                            <p class="node-users--label">Total registered users:</p>
                            <p class="node-users--count">
                                ${NUMBER_OF_USERS}
                            </p>
                        </div>
                    </div>
                    <div class="row col-md-7 node-users-row">
                        <div class="col-md-6 node-users">
                            <p class="node-users--label">Number of Admins</p>
                            <p class="node-users--count">
                                ${NUMBER_OF_ADMIN}
                            </p>
                        </div>
                        <div class="col-md-6 node-users">
                            <p class="node-users--label">Number of Service Providers</p>
                            <p class="node-users--count">
                                ${NUMBER_OF_SERVICE_PROVIDERS}
                            </p>
                        </div>
                        <div class="col-md-6 node-users">
                            <p class="node-users--label">Number of Customers</p>
                            <p class="node-users--count">${NUMBER_OF_CUSTOMERS}</p>
                        </div>
                        <div class="col-md-6 node-users">
                        </div>
                    </div>
                </section>
                <section id="sect-orders" class="row">
                    <h4>Orders:</h4>
                    <div class="col-md-5 node-orders-row">
                        <div class="node-orders">
                            <p class="node-orders--label">Total orders</p>
                            <p class="node-orders--count">
                                ${NUMBER_OF_ORDERS}
                            </p>
                        </div>
                    </div>
                    <div class="col-md-7 node-orders-row">
                        <div class="node-orders">
                            <div id="orders-graph-container">
                                <canvas id="orders-graph"></canvas>
                            </div>
                        </div>

                    </div>
                </section>
                <section id="sect-profit">
                    <h4>Total profit:</h4>
                    <div id="sect-profit--grid">
                        <div class="node-profit">
                            <p class="node-profit--label">Profit in 1 day</p>
                            <p class="node-profit--count">
                                $${NUMBER_OF_PROFITS_IN_1_DAY}
                            </p>
                        </div>
                        <div class="node-profit">
                            <p class="node-profit--label">Profit in 30 day</p>
                            <p class="node-profit--count">
                                $${NUMBER_OF_PROFITS_IN_30_DAYS}
                            </p>
                        </div>
                        <div class="node-profit">
                            <p class="node-profit--label">Profit in 90 day</p>
                            <p class="node-profit--count">
                                $${NUMBER_OF_PROFITS_IN_90_DAYS}
                            </p>
                        </div>
                    </div>
                </section>
            </div>

        </div>
    </div>
    <jsp:include page="/templates/common_js.jsp"/>
    <script>
        const DATA_COUNT = 5;
        const NUMBER_CFG = {count: DATA_COUNT, min: 0, max: 100};

        const data = {
            labels: ['Pending', 'Cancelled', 'In queue', 'Finished'],
            datasets: [
                {
                    label: 'Dataset 1',
                    data: [${NUMBER_OF_PENDING}, ${NUMBER_OF_CANCELLED}, ${NUMBER_OF_PROGRESS}, ${NUMBER_OF_SUCCESS}],
                    backgroundColor: ['#606060', '#C20000', '#0024da', '#00E007'],
                }
            ]
        };

        const config = {
            type: 'pie',
            data: data,

            options: {
                responsive: true,
                animation: {
                    animateRotate: true
                },
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Order graph'
                    }
                }
            },
        };

        new Chart(
            document.getElementById('orders-graph'), config)
    </script>

</div>
</body>

</html>