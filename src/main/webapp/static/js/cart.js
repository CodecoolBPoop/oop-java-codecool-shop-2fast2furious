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
                email: username,
                password: password
            },
            success: function (resp) {

                if (resp === "success") {
                    window.location.reload();
                    snackbar("You logged in!")
                } else {
                    $("#login-alert").show();
                    $("#login-alert").text(resp);
                }

            }
        })
    }
}

function onSignIn(googleUser) {

    let profile=googleUser.getBasicProfile();

    if ($("#login-type").attr("value") == "") {
        let id_token = googleUser.getAuthResponse().id_token;
        $.ajax({
            url:"/gmail-login",
            method: "POST",
            data: {
                idToken: id_token
            },
            success: function() {
                window.location.reload();
                snackbar("You logged in!")
            }
        })
    } else {
        $("#navbar-logout-google .user-name-label").html(
            '<p style="margin-right: 20px; color: white;">Welcome, ' + profile.getName() + '</p>'
        );
        $("#gmail-profile-image").attr("src", profile.getImageUrl());
    }

}


function signOut() {
    if ($("#login-type").attr('value') === "google") {
        let auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut()
    }

    $.ajax({
        url:"/signout",
        method:"POST",
        success: function(resp) {
            snackbar("You logged out!")
            window.location.reload();
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
                    $("#loginModal").hide()
                    window.location.reload();
                } else {
                    $("#signup-alert").show();
                    $("#signup-alert").text(resp);
                }
            }
        })
    }

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

function userCheck() {
    let email = $("#logged-in-email").attr("value")
    console.log(email)
    if (email == "") {
        $("#navbar-login").show();
    } else {
        let login_type = $("#login-type").attr("value")
        if (login_type === "google") {
            $("#navbar-logout-google").show();
        } else {
            $("#navbar-logout-basic").show();
            $("#navbar-logout-basic .user-name-label").html(
                '<p style="margin-right: 20px; color: white;">Welcome, ' + $("#logged-in-username").attr("value") + '</p>'
            );
        }
    }
}

function main() {
    userCheck();
    addToCartListener();
    buttonListeners();
    initCounter();
}


$(document).ready(function () {
    main();
})

