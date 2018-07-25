function defaultLogin() {
    let username = $("#login-username").val();
    let password = $("#login-password").val();
    if (username === "" || password === "") {
        $("#login-alert").text("Wrong username or password!");
        $("#login-alert").show();
    } else {
        $.ajax({
            url:"/default-login",
            method: "POST",
            data: {
                username: username,
                password: password
            },
            success: function (resp) {
                $('#loginModal').modal('hide');
                $('#navbar-login').hide();
                $('#navbar-logout-basic').show();
                $("#login-password").val("");
                $("#login-username").val("");

                snackbar("You logged in!")
            }
        })
    }
}



function onSignIn(googleUser) {
    $("#navbar-login").hide();
    $("#navbar-logout-google").show();
    $('#loginModal').modal('hide');
    let profile=googleUser.getBasicProfile();
    $("#navbar-logout-google .user-name-label").html('Welcome, ' + profile.getName());
    $("#gmail-profile-image").attr("src", profile.getImageUrl());
    let id_token = googleUser.getAuthResponse().id_token;
    console.log("ID Token: " + id_token);
    $.ajax({
        url:"/gmail-login",
        method: "POST",
        data: {
            idToken: id_token
        },
        success: function() {
            snackbar("You logged in!")
        }
    })
}


function signOutGoogle() {
    $("#navbar-logout-google").hide();
    $("#navbar-logout-google .user-name-label").html("");
    let auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
    signOut();
}

function signOutBasic() {
    $("#navbar-logout-basic .user-name-label").html("");
    $("#navbar-logout-basic").hide();
    signOut();
}

function signOut() {
    $("#navbar-login").show();
    $.ajax({
        url:"/signout",
        method:"POST",
        success: function(resp) {
            snackbar("You logged out!")
        }
    })
}

function signUp() {
    let email = $("#signup-email").val();
    let username = $("#signup-username").val();
    let password = $("#signup-password").val();

    if (email === "" || username === "" || password === "") {
        $("#signup-alert").show();
        $("#signup-alert").text("Wrong input!");
    } else if (!$("#check-terms").prop('checked')) {
        $("#signup-alert").show();
        $("#signup-alert").text("You have to accept Terms and agreement!");
    } else {
        $.ajax({
            url: "/signup",
            method: "POST",
            data: {
                email: email,
                username: username,
                password: password
            },
            success: function(resp) {
                if (resp === "success") {
                    validSignUp();
                    alert("success");
                } else {
                    $("#signup-alert").show();
                    $("#signup-alert").text(resp);
                }
            }
        })
    }

}

function validSignUp() {

}

function initCounter() {
    let size = $("#shoppingcartlink").attr('value');
    $("#shoppingcarticon").attr('data-content', size);
}


function snackbar(message) {
    var x = document.getElementById("snackbar");
    x.innerText = message;
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

function addToCartListener() {

    $(".addtocart").click(
        function (event) {
            console.log(event)
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

function buttonListeners() {
    $("#btn-login").click(
        function (event) {
            defaultLogin();
        }
    )
    $("#btn-signup").click(
        function (event) {
            signUp();
        }
    )
}

function main() {
    addToCartListener();
    buttonListeners();
    initCounter();
}


$(document).ready(function () {
    main();
})

