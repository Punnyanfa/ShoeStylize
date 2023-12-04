<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Users | Shoe Stylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin-tab.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/orders.css">
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
            <ul class="nav nav-tabs" id="myTabs">
                <li class="nav-item">
                    <a class="nav-link active" id="all-tab" data-bs-toggle="tab" href="#tab-all" aria-selected="true">All orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pending-tab" data-bs-toggle="tab" href="#pending">Pending</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="progressing-tab" data-bs-toggle="tab" href="#progressing">Progressing</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="cancelled-tab" data-bs-toggle="tab" href="#cancelled">Cancelled</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="finished-tab" data-bs-toggle="tab" href="#finished">Finished</a>
                </li>
            </ul>

            <div class="tab-content mt-2">
                <div class="tab-pane fade show active" id="tab-all">
                    <jsp:include page="/templates/admin-order/admin-order-all.jsp" />
                </div>
                <div class="tab-pane fade" id="pending">
                    <jsp:include page="/templates/admin-order/admin-order-pending.jsp" />
                </div>
                <div class="tab-pane fade" id="progressing">
                    <jsp:include page="/templates/admin-order/admin-order-progressing.jsp" />
                </div>
                <div class="tab-pane fade" id="cancelled">
                    <jsp:include page="/templates/admin-order/admin-order-cancelled.jsp" />
                </div>
                <div class="tab-pane fade" id="finished">
                    <jsp:include page="/templates/admin-order/admin-order-finished.jsp" />
                </div>
            </div>

            <div class="modal fade" id="modal-preview" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Preview</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div id="modal-content-canvas">
                                <canvas></canvas>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/templates/common_js.jsp"/>
    <script>
        let $purchaseTableAll = $("#order-table-all")
        let $orderTablePending = $("#order-table-pending")
        let $orderTableProgressing = $("#order-table-progressing")
        let $orderTableCancelled = $("#order-table-cancelled")
        let $orderTableSuccess = $("#order-table-success")

        $purchaseTableAll.dataTable({
            responsive: true,
            autoWidth: false,
        });

        $orderTablePending.dataTable({
            responsive: true,
            autoWidth: false,
        });

        $orderTableProgressing.dataTable({
            responsive: true,
            autoWidth: false,
        });

        $orderTableCancelled.dataTable({
            responsive: true,
            autoWidth: false,
        });

        $orderTableSuccess.dataTable({
            responsive: true,
            autoWidth: false,
        });
    </script>
    <script type="module" src="${pageContext.request.contextPath}/js/admin/order.js"></script>
</div>
</body>

</html>
