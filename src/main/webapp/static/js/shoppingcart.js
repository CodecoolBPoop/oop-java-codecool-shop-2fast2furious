


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
    $("#checkoutsend").click(function (event) {
        event.preventDefault();
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

        let data = [name, phonenumber,event,saddress,scity, scountry, szip, baddress, bcity, bcountry, bzip];

        for(let element of data){
            console.log(element);
            if(element === ""){
                $("#checkoutdanger").show();
                return
            }
        }

        $.ajax({
            method: "POST",
            url: "/checkout",
            dataType: "json",
            async: false,
            data: {
                name: name,
                phonenumber: phonenumber,
                email: email,
                baddress: JSON.stringify({
                    address: baddress,
                    city: bcity,
                    country: bcountry,
                    zip: bzip
                }),
                saddress: JSON.stringify({
                    address: saddress,
                    city: scity,
                    country: scountry,
                    zip: szip
                },
            success:function (resp) {
                console.log(resp)
                $("#checkouttoggle").prop("disabled", true);
                $("#paytoggle").prop("disabled", false);
                $("#checkout").slideToggle();
            }
            }
        })
    })
}

function checkOutToggle() {
    $("#checkouttoggle").click(function () {
        $("#checkout").slideToggle();
    })
}

function payToggle() {
    $("#paytoggle").click(function () {
        $("#pay").slideToggle()
    })
}


function main() {
    incrementButtonsListener();
    decrementButtonListener();
    sendCheckout();
    checkOutToggle();
    payToggle();

}


$(document).ready(function () {
    main()
    $("#paytoggle").prop("disabled", true);
    $("#checkoutdanger").hide();

})

