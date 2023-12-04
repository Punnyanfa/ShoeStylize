const aget = (u, j, s, e) => {
    $.ajax({
        type: "get",
        data: j,
        url: u,
        success: s,
        error: e
    });
};

const apost = (u, j, s, e) => {
    $.ajax({
        type: "post",
        contentType: 'application/json',
        data: JSON.stringify(j),
        url: u,
        success: s,
        error: e
    });
};

const apostform = async (u, j, s, e) => {
    const formData = new FormData();
    for (const key in j) {
        if (j.hasOwnProperty(key)) {
            formData.append(key, j[key]);
        }
    }

    $.ajax({
        type: 'post',
        data: formData,
        url: u,
        contentType: false,
        processData: false,
        success: s,
        error: e
    });
};

$(document).ready((e) => {
    $(document).click((e) => {
        let target = e.target;

        let u = $(target.closest("[dir-to]")).attr("dir-to");
        if (u === null || u === undefined)
            return;

        window.location.href = u;
    });

    $("#btn-logout").click(e => {
        apost("http://localhost:8080/ShoeStylize_Tomcat/LogoutServlet", {}, s => {
            window.location.reload();
        });
    });

    $('.nav-link.profile').click(function (e) {
        e.preventDefault();
        $('#myDropdown').toggleClass('show');
    });

    $(document).on('click', function (e) {
        let $target = $(e.target)
        if (!$target.closest('.nav-item.dropdown').length) {
            $('#myDropdown').removeClass('show');
        }

        if ($target.closest(".nav-search-node").length === 0) {
            $searchContainer.empty()
            $searchContainerBox.css("display", "none");
        }
    });

    let $navbarSearch = $('#nav-search')
    let $searchContainerBox = $('.search-container')
    let $searchContainer = $('.search-container-ab')
    let delayTimer;

    function fetchDataFromApi(query) {
        const apiEndpoint = `http://localhost:8080/ShoeStylize_Tomcat/api/search/shoes`;

        // Simulate an API request (replace this with your actual API request)
        console.log(query)
        aget(apiEndpoint, {
            query: query
        }, s => {
            $searchContainerBox.css("display", "block");
            $searchContainer.empty()

            if (s.shoes.length === 0) {
                $searchContainer.append(`<p>No result found</p>`)
            } else {
                s.shoes.forEach(shoe => {
                    $searchContainer.append(`
                <div class="nav-search-node" dir-to="http://localhost:8080/ShoeStylize_Tomcat/details/${shoe.id}">
                    <img class="nav-search-img" src="${shoe.image_link}">
                    <p>${shoe.title}</p>
                    <p class="nav-search-price">$${shoe.price}</p>
                </div>
                <hr>
            `)
                })
            }
        })
    }

    function handleInputChange() {
        const inputValue = $('#nav-search').val();
        clearTimeout(delayTimer);
        delayTimer = setTimeout(function() {
            fetchDataFromApi(inputValue);
        }, 1000);
    }

    $navbarSearch.focus((f)=> {
        fetchDataFromApi($('#nav-search').val())
    });

    $navbarSearch.on('input', handleInputChange);

    $searchContainerBox.css("display", "none");
});