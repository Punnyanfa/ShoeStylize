<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help | ShoeStylize</title>
        <link rel="stylesheet" type="text/css" href="./css/base.css">
        <link rel="stylesheet" type="text/css" href="./css/help.css">
        <jsp:include page="/templates/common_lib.jsp"/>
    </head>

    <body>
        <div class="app">
            <jsp:include page="/templates/header.jsp"/>
            <div class="content">
                <div class="page-title text-center" style="margin-bottom: 35px;">
                    <h1>FAQ</h1>
                    <h4>Frequently asked question</h4>
                </div>

                <section>
                    <div class="accordion" id="accordionPanelsStayOpenExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
                                        aria-controls="panelsStayOpen-collapseOne">
                                    What are the available methods of shipping?
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show"
                                 aria-labelledby="panelsStayOpen-headingOne">
                                <div class="accordion-body">
                                    <strong>.</strong> We exclusively offer 
                                    a single official shipping option for intra-city deliveries, and we will 
                                    make an effort to provide shipping services for destinations outside the city.
                                   
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false"
                                        aria-controls="panelsStayOpen-collapseTwo">
                                    How long does it usually take to finish a requested order?
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse"
                                 aria-labelledby="panelsStayOpen-headingTwo">
                                <div class="accordion-body">
                                    <strong>.</strong> 
                                    Typically, our orders are fulfilled in a span of 2-3 months. However, if the drawing request is particularly intricate, our response time may be delayed.
                                  
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false"
                                        aria-controls="panelsStayOpen-collapseThree">
                                    What should I do if the requested order encountered a problem during shipping/processing?
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse"
                                 aria-labelledby="panelsStayOpen-headingThree">
                                <div class="accordion-body">
                                    <strong>.</strong> Feel free 
                                    to contact our system or send an email, and we'll strive to reply at the earliest convenience.
                                    
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingFour">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#panelsStayOpen-collapseFour" aria-expanded="false"
                                        aria-controls="panelsStayOpen-collapseFour">
                                    Does the service offer promotion/discounts?
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseFour" class="accordion-collapse collapse"
                                 aria-labelledby="panelsStayOpen-headingFour">
                                <div class="accordion-body">
                                    <strong>.</strong> We plan to 
                                    provide special promotions or discounts during holidays and significant occasions.
                                  
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </div>
            <jsp:include page="/templates/footer.jsp"/>
            <script type="text/javascript" src="./js/jquery-3.7.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
        </div>

    </body>

</html>