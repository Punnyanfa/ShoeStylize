$document.ready((e) => {
    $(window).scroll(function() {
        var navbar = $("nav.navbar");
        var fixedNavbarPosition = 150;
        if ($(this).scrollTop() >= fixedNavbarPosition) {
            navbar.removeClass("bgcolor");
        } else {
            navbar.addClass("bgcolor");
        }
    });
});