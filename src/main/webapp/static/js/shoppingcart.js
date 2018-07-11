


function incrementButtonsListener(){
    $(".inc").click(function (event) {
        console.log(event)
        let prodid = event.currentTarget.dataset.prodid;
        $.ajax({
            url:`/add_to_cart?id=${prodid}`,
            success: function (resp) {
                console.log(resp)
                $("#total").text(`The total of your order is ${resp} USD`)
                let quantity = parseInt($(`[data-qid='${prodid}']`).html());
                $(`[data-qid='${prodid}']`).text(quantity+1)

            }
        })
    })
}

function decrementButtonListener() {
    $(".dec").click(function (event) {
        console.log(event)
        let prodid = event.currentTarget.dataset.prodid;
        let quantity = parseInt($(`[data-qid='${prodid}']`).html())
        if(quantity > 0){
            $.ajax({
                url:`/remove_from_cart?id=${prodid}`,
                success: function (resp) {
                    console.log(resp)
                    $("#total").text(`The total of your order is ${resp} USD`)
                    ;
                    $(`[data-qid='${prodid}']`).text(quantity-1)

                }
            })}
    })
}

function sendCheckout() {
    $("#checkoutsend").click(function () {
        let name = $("#name").val()
        let phonenumber = $("#phonenumber").val()
        let email = $("#email").val()
        let saddress = $("#saddress").val()
        let scity = $("#scity").val()
        let scountry = $("#scountry").val()
        let szip = $("#szip").val()
        let baddress = $("#baddress").val()
        let bcity = $("#bcity").val()
        let bcountry = $("#bcountry").val()
        let bzip = $("#bzip").val()

        $.ajax({
            url: "/checkout",
            data: {
                name: name,
                phonenumber: phonenumber,
                email: email,
                baddress: {
                    address: baddress,
                    city: bcity,
                    country: bcountry,
                    zip: bzip
                },
                saddress: {
                    address: saddress,
                    city: scity,
                    country: scountry,
                    zip: szip
                }
            success:function (resp) {
                console.log(resp)
            }
            }
        })
    })
}


function main() {
    incrementButtonsListener();
    decrementButtonListener();
    sendCheckout();
}


$(document).ready(function () {
    main()
})

