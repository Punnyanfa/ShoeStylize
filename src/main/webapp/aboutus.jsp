<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>About us | ShoeStylize</title>
    <link rel="stylesheet" type="text/css" href="./css/base.css">
    <link rel="stylesheet" type="text/css" href="./css/aboutus.css">
    <jsp:include page="/templates/common_lib.jsp"/>
</head>

<body>
<div class="app">
    <jsp:include page="/templates/header.jsp"/>
    <div class="content">
        <section id="aboutus">
            <div class="page-title text-center" style="margin-bottom: 35px;">
                <h1>About us</h1>
                <h4 class="blur">Foundation of Shoe Stylize</h4>
            </div>
            <p>ShoeStylize stands at the forefront of the footwear customization movement, offering a comprehensive and
                transformative experience for shoe enthusiasts worldwide. Our brand name, ShoeStylize, encapsulates our
                mission perfectly &ndash; to empower individuals to style their shoes in ways that are as unique as they
                are. At ShoeStylize, we believe that footwear is more than just an accessory; it&apos;s an opportunity
                for self-expression, and our platform is the perfect canvas for this creative journey.</p>
            <p>Our foundation is grounded in the fusion of innovation and craftsmanship. When users entrust their
                cherished shoes to ShoeStylize, they embark on a collaborative journey with our team of skilled artisans
                and designers. Together, we turn their design concepts into stunning reality, whether that means
                incorporating custom artwork, adding personalized embellishments, or completely reimagining the shoe&apos;s
                silhouette. Through this process, ShoeStylize not only fulfills your footwear dreams but also promotes
                sustainable fashion practices by breathing new life into existing footwear, reducing the need for new
                purchases and waste.</p>
            <p>What truly sets ShoeStylize apart is the sense of community and empowerment that it fosters. It&apos;s
                not just a service; it&apos;s a vibrant hub where like-minded individuals come together to celebrate
                their passion for shoes. Our platform encourages the exchange of ideas, inspirations, and stories,
                making ShoeStylize more than just a place to customize shoes &ndash; it&apos;s a cultural movement.
                Here, you&apos;re not just purchasing shoes; you&apos;re crafting wearable art that tells your story and
                showcases your unique style. Join us in this exciting journey where the foundation of shoe stylization
                is not just about the shoes; it&apos;s about the meaningful connections and self-expression that they
                represent. Step into the world of ShoeStylize and make every step count, both for your style and for the
                environment.</p>
            <h4>Welcome to ShoeStylize, where every step is a style statement!</h4>
        </section>

    </div>
    <jsp:include page="/templates/footer.jsp"/>
    <script type="text/javascript" src="./js/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="./js/loader.js"></script>
</div>

</body>

</html>