


function addToCartListener() {
    $(".addtocart").click(
        function (event) {
            //console.log(event)
            let prodid = event.target.dataset.prodid;
            $.ajax({
                url:`/add_to_cart?id=${prodid}`

            })
        }
    )
}

function main() {
    addToCartListener();
}


$(document).ready(function () {
    main();
})