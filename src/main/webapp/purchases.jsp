<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Purchases | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/purchases.css">
    <jsp:include page="/templates/common_lib.jsp"/>
</head>

<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <div class="container mt-5">
        <ul class="nav nav-tabs" id="cart-tabs">
            <li class="nav-item">
                <a class="nav-link active" id="all-tab" data-bs-toggle="tab" href="#all">All</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="pending-tab" data-bs-toggle="tab" href="#pending">Pending</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="inProgress-tab" data-bs-toggle="tab" href="#inProgress">In Progress</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="cancelled-tab" data-bs-toggle="tab" href="#cancelled">Cancelled</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="success-tab" data-bs-toggle="tab" href="#success">Success</a>
            </li>
        </ul>

        <div class="order-container">
            <div class="tab-content mt-2">
                <div class="tab-pane fade show active" id="all">
                    <div id="search-box-all" class="search-box"></div>
                    <jsp:include page="/templates/purchases/purchases-all.jsp" />
                </div>
                <div class="tab-pane fade" id="pending">
                    <div id="search-box-pending" class="search-box"></div>
                    <jsp:include page="/templates/purchases/purchases-pending.jsp" />
                </div>
                <div class="tab-pane fade" id="inProgress">
                    <div id="search-box-inProgress" class="search-box"></div>
                    <jsp:include page="/templates/purchases/purchases-inProgress.jsp" />
                </div>
                <div class="tab-pane fade" id="cancelled">
                    <div id="search-box-cancelled" class="search-box"></div>
                    <jsp:include page="/templates/purchases/purchases-cancelled.jsp" />
                </div>
                <div class="tab-pane fade" id="success">
                    <div id="search-box-success" class="search-box"></div>
                    <jsp:include page="/templates/purchases/purchases-success.jsp" />
                </div>
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
    <jsp:include page="/templates/footer.jsp"/>
    <jsp:include page="/templates/common_js.jsp"/>
    <script>
        let $purchaseTableAll = $("#purchases-table-all")
        let $purchaseTablePending = $("#purchases-table-pending")
        let $purchaseTableInProgress = $("#purchases-table-inProgress")
        let $purchaseTableCancelled = $("#purchases-table-cancelled")
        let $purchaseTableSuccess = $("#purchases-table-success")

        $purchaseTableAll.dataTable({
            responsive: true,
            autoWidth: false,
            "columnDefs": [
                { "width": "5%" },
                { "width": "10%" },
                { "width": "30%" },
                { "width": "35%" },
                { "width": "5%" },
                { "width": "15%" }
            ]
        });

        $purchaseTablePending.dataTable({
            responsive: true,
            autoWidth: false,
            "columnDefs": [
                { "width": "5%" },
                { "width": "10%" },
                { "width": "30%" },
                { "width": "35%" },
                { "width": "5%" },
                { "width": "15%" }
            ]
        });

        $purchaseTableInProgress.dataTable({
            responsive: true,
            autoWidth: false,
            "columnDefs": [
                { "width": "5%" },
                { "width": "10%" },
                { "width": "30%" },
                { "width": "35%" },
                { "width": "5%" },
                { "width": "15%" }
            ]
        });

        $purchaseTableCancelled.dataTable({
            responsive: true,
            autoWidth: false,
            "columnDefs": [
                { "width": "5%" },
                { "width": "10%" },
                { "width": "30%" },
                { "width": "35%" },
                { "width": "5%" },
                { "width": "15%" }
            ]
        });

        $purchaseTableSuccess.dataTable({
            responsive: true,
            autoWidth: false,
            "columnDefs": [
                { "width": "5%" },
                { "width": "10%" },
                { "width": "30%" },
                { "width": "35%" },
                { "width": "5%" },
                { "width": "15%" }
            ]
        });

        $("#purchases-table-all_filter").empty()
        $('#search-box-all').append(`
            <input type='text' class="form-control" placeholder="Search prompt" aria-controls="purchases-table-all">
        `)

        $("#purchases-table-pending_filter").empty()
        $('#search-box-pending').append(`
            <input type='text' class="form-control" placeholder="Search prompt" aria-controls="purchases-table-pending">
        `)

        $("#purchases-table-inProgress_filter").empty()
        $('#search-box-inProgress').append(`
            <input type='text' class="form-control" placeholder="Search prompt" aria-controls="purchases-table-inProgress">
        `)

        $("#purchases-table-cancelled_filter").empty()
        $('#search-box-cancelled').append(`
            <input type='text' class="form-control" placeholder="Search prompt" aria-controls="purchases-table-cancelled">
        `)

        $("#purchases-table-success_filter").empty()
        $('#search-box-success').append(`
            <input type='text' class="form-control" placeholder="Search prompt" aria-controls="purchases-table-success">
        `)
    </script>
    <script type="module" src="${pageContext.request.contextPath}/js/purchases.js"></script>
</div>

</body>

</html>
