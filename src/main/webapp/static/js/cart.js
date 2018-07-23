
function onSignIn(googleUser) {
    $("#navbar-login").hide();
    $("#navbar-logout-google").show();
    $('#loginModal').modal('hide');
    let profile=googleUser.getBasicProfile();
    $("#navbar-logout-google .user-name-label").html('Welcome, ' + profile.getName());
    let id_token = googleUser.getAuthResponse().id_token;
    console.log("ID Token: " + id_token);
    $.ajax({
        url:"/gmail-login",
        method: "POST",
        data: {
            idToken: id_token,
        }
    })
}

function signOutGoogle() {
    $("#navbar-login").show();
    $("#navbar-logout-google").hide();
    let auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
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

