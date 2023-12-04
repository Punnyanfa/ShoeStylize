<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home | ShoeStylize</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/logo.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://fonts.googleapis.com/css2?family=Send+Flowers&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
    />

</head>
<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <section class="keyvisual">
        <div class="keyvisual-image"></div>
        <div class="container-black">
            <h2>FOOTWEAR <br/> DESIGNED BY YOU</h2>
            <a href="${pageContext.request.contextPath}/explore">
                TRY IT NOW
            </a>
        </div>
    </section>
    <section class="keyvisual-bottom">
        <div class="container">
            <div class="keyvisual-bottom-item">
                <img src="https://d1ou7i8vjlwywf.cloudfront.net/landing/sustainable.jpg" alt="alt"/>
                <p>SUSTAINABLE<br/>MATERIALS</p>
            </div>
            <div class="keyvisual-bottom-item">
                <img src=https://cdn.discordapp.com/attachments/1072081944491339810/1175377749603524608/image_1.png?ex=656b02d4&is=65588dd4&hm=182cb142bf91ebc1663fc5e427375e2332c278c7632cf9379946deab5a2a46a1&
                     alt="alt"/>
                <p>MADE & DELIVERED<br/>LESS THAN 1 MONTH</p>
            </div>
            <div class="keyvisual-bottom-item">
                <img src="https://d1ou7i8vjlwywf.cloudfront.net/landing/sizes.jpg" alt="alt"/>
                <p>SIZES FROM <br/>35 TO 44</p>
            </div>
        </div>
    </section>
    <section class="product-modal">
        <p>START DESIGNING</p>
        <h3>NEW ARRIVAL</h3>
        <div class="style-container">
            <div class="style-nodes">
                <c:forEach items="${Shoes}" var="shoe">
                    <div class="style-node zoom" dir-to="${pageContext.request.contextPath}/details/${shoe.getId()}">
                        <img class="image-explore" src="${shoe.getImageLink()}">
                        <p class="style--title">${shoe.getTitle()}</p>
                        <button type="button" class="btn btn-dark">More info</button>
                    </div>
                </c:forEach>
            </div>
        </div>
        <button dir-to="${pageContext.request.contextPath}/explore" type="button" class="btn btn-dark"
                style="font-size: 20px; font-weight: 600; margin-top: 45px; width: 50vw">All styles
        </button>
    </section>


    <section class="desc">
        <h3>What to expect</h3>
        <p>Design your own shoes right from scratch - choose all the materials and fabrics that you want.

            We are eco-fiendly - means that we use only vegan & 100% sustainable materials and fabrics.</p>
        <div class="desc-list">
            <div class="desc-list-item">
                <div class="desc-list-item-image">
                    <img src="https://nueqvbuobysxbgpelaih.supabase.co/storage/v1/object/public/shoes-explore/home/pic1.jpg" alt="alt"/>
                </div>
                <h4 dir-to="/aboutus.jsp">OUR VALUES</h4>
            </div>
            <div class="desc-list-item">
                <div class="desc-list-item-image">
                    <img src="https://nueqvbuobysxbgpelaih.supabase.co/storage/v1/object/public/shoes-explore/home/pic2.jpg" alt="alt"/>
                </div>
                <h4>SHOE-MAKING PROCESS</h4>
            </div>
        </div>
    </section>
    <section class="product-modal">
        <h3>WHAT WE OFFER</h3>
        <div class="product-modal-content product-offer">
            <div class="product-modal-item">
                <div class="product-modal-item-image">
                    <img src="./assets/home/shoes/low-tops/1.png" alt="alt"/>
                </div>
                <h4>Low top shoes</h4>
            </div>
            <div class="product-modal-item">
                <div class="product-modal-item-image">
                    <img src="./assets/home/shoes/sneaker/1.png" alt="alt"/>
                </div>
                <h4>Sneakers</h4>
            </div>
            <div class="product-modal-item">
                <div class="product-modal-item-image">
                    <img src="./assets/home/shoes/high-tops/1.png" alt="alt"/>
                </div>
                <h4>High tops shoes</h4>
            </div>
            <div class="product-modal-item">
                <div class="product-modal-item-image">
                    <img src="./assets/home/shoes/boots/1.png" alt="alt"/>
                </div>
                <h4>Boots</h4>
            </div>
            <div class="product-modal-item">
                <div class="product-modal-item-image">
                    <img src="./assets/home/shoes/others/1.png" alt="alt"/>
                </div>
                <h4>Other designs</h4>
            </div>

        </div>
    </section>
    <section class="keyvisual-bottom">
        <div class="desc">
            <h3>How to customize your shoe</h3>
        </div>

        <div class="container">
            <div class="keyvisual-bottom-item">
                <img src="./assets/home/shoes/1.png" alt="alt"/>
                <h4>Pick Your Shoe</h4>
                <p class="keyvisual-bottom-note">Select size</p>
            </div>
            <div class="keyvisual-bottom-item">
                <img src="./assets/home/shoes/2.png" alt="alt"/>
                <h4>Create a design</h4>
                <p class="keyvisual-bottom-note">Use your own image or Design Maker's tools
                    to achieve the desired look</p>
            </div>
            <div class="keyvisual-bottom-item">
                <img src="./assets/home/shoes/3.png" alt="alt"/>
                <h4>Place an order</h4>
                <p class="keyvisual-bottom-note">Treat yourself or your customers to one-of-a-
                    kind footwear</p>
            </div>
        </div>
    </section>
    <section class="keyvisual-bottom">
        <div class="desc">
            <h3>Unique shoes designed by you</h3>
            <p>Our shoe drawing customization service lets you dive into a world of endless possibilities. Picture this:
                a vast array of fabrics and materials at your fingertips, empowering you to craft your sneakers from the
                ground up.
                <br>
                <br>
                You're the maestro here, selecting colors, playing with combinations, and choosing textures to ensure
                your sneakers speak volumes about your unique style. With thousands of potential combinations, you're
                sure to discover the perfect expression of your individuality.
                <br>
                <br>
                But wait, there's more! At ShoeStylize, we take pride in our commitment to the planet. Our shoes are
                crafted through an eco-friendly process, using vegan sustainable materials. They're made only upon
                demand, ensuring a responsible approach to production. And yes, every pair is meticulously handmade,
                delivering not just shoes, but a masterpiece that reflects your personality. Isn't that the ideal fusion
                of style and sustainability?
            </p>
            <button dir-to="${pageContext.request.contextPath}/about" type="button" class="btn btn-dark"
                    style="font-size: 20px; font-weight: 600; margin-top: 45px; width: 50vw">More details
            </button>
        </div>
        <div class="container">

        </div>
    </section>
    <section class="keyvisual-bottom">
        <div class="desc">
            <h3>Frequently Asked Questions</h3>
            <section id="faq">
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
                                Typically, our orders are fulfilled in a span of 2-3 months. However, if the drawing
                                request is particularly intricate, our response time may be delayed.

                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false"
                                    aria-controls="panelsStayOpen-collapseThree">
                                What should I do if the requested order encountered a problem during
                                shipping/processing?
                            </button>
                        </h2>
                        <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse"
                             aria-labelledby="panelsStayOpen-headingThree">
                            <div class="accordion-body">
                                <strong>.</strong> Feel free
                                to contact our system or send an email, and we'll strive to reply at the earliest
                                convenience.

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
            <button dir-to="${pageContext.request.contextPath}/help.jsp" type="button" class="btn btn-dark"
                    style="font-size: 20px; font-weight: 600; margin-top: 45px; width: 50vw">More details
            </button>
        </div>
        <div class="container">

        </div>
    </section>

    <jsp:include page="/templates/footer.jsp"/>
    <jsp:include page="/templates/common_js.jsp"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

</div>
</body>

</html>