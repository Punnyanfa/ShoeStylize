$(document).ready(d => {
    $('.btn-add-extra').click(c => {
        let $target = $(c.target)
        let id = $target.attr("id-value")
        let $generated = $('.container-se').append(`
            <div class="container-se--node">
                <p>${$target.attr("data-title")}</p>
                <p>${$target.attr("data-price")}</p>
                <button type="button" class="btn btn-danger btn-remove" data-id="${id}">Remove</button>
                <input type="hidden" class="input-se" id="input-se-${id}" name="se" value="${id}">
            </div>
        `)
        $generated.find(".btn-danger").click(c => {
            $(`#accessory-item-${id}`).show()
            $generated.empty()
        })
        $(`#accessory-item-${id}`).hide()
    })
})