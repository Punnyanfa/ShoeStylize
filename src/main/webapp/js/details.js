$(document).ready((e) => {

    $(".image-preview--img").click(e => {
        let target = e.target;

        let url = $(target).attr("src");
        $(".item-node--l > img").attr("src", url)
    })

    $(".mirror-image-container").hover(
        function () {
            // Mouse enter
            $(this).find("img").css("transform", "scaleX(-1)");
        },
        function () {
            // Mouse leave
            $(this).find("img").css("transform", "scaleX(1)");
        }
    );

    $("#btn-sharecopy").click(function () {
        var textToCopy = window.location.href;
        var tempInput = $("<input>");
        $("body").append(tempInput);
        tempInput.val(textToCopy).select();
        document.execCommand("copy");
        tempInput.remove();
        $('#i--share-noti').html('URL copied to clipboard')
        setTimeout(() => {
            $('#i--share-noti').html('')
        }, 5000)
    });

    $('#sharecopy-input').val(window.location.href)
    $("#share-mail").attr("href", `mailto:?subject=ShoeStylize&body=This%20is%20the%20best%20shoe%20customization%20service%20you'll%20ever%20look%20for:%20${window.location.href}`)


});