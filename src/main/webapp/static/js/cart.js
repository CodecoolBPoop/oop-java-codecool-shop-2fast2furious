
function initCounter() {
    let size = $("#shoppingcarticon").attr('value');
    $("#shoppingcarticon").after().setAttribute('content', size)
}

function addToCartListener() {
    $(".addtocart").click(
        function (event) {
            //console.log(event)
            let prodid = event.target.dataset.prodid;
            $("#shoppingcarticon").after().setAttribute('content', '10')
            $.ajax({
                url:`/add_to_cart?id=${prodid}`

            })
        }
    )
}

function main() {
    addToCartListener();
    initCounter();
}


$(document).ready(function () {
    main();
})