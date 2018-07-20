
function onSignIn(googleUser) {
    console.log("123");
    alert(123);
    let profile=googleUser.getBasicProfile();
    $("#user-name-label").text(profile.getEmail());
    let id_token = googleUser.getAuthResponse().id_token;
    console.log("ID Token: " + id_token);
}


function initCounter() {
    let size = $("#shoppingcartlink").attr('value');
    $("#shoppingcarticon").attr('data-content', size);
}

function addToCartListener() {

    $(".addtocart").click(
        function (event) {
            //console.log(event)
            let size = $("#shoppingcartlink").attr('value');
            $("#shoppingcartlink").attr('value', parseInt(size) + 1);
            $("#shoppingcarticon").attr('data-content', parseInt(size) + 1);
            let prodid = event.target.dataset.prodid;
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