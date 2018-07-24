
function showRegisterForm() {
    $('.loginBox').fadeOut('fast', function () {
        $('.registerBox').fadeIn('fast');
        $('.login-footer').fadeOut('fast', function () {
            $('.register-footer').fadeIn('fast');
        });
        $('.modal-title').html('Regisztráció');
    });
    $('.error').removeClass('alert alert-danger').html('');
}

function flipToRegister() {
    $("#signup-alert").hide();
    $("#signup-password").val("");
    $("#signup-email").val("");
    $("#signup-username").val("");
    $("#loginbox").hide();
    $("#signupbox").show();
}

function flipToLogin() {
    $("#login-alert").hide();
    $("#login-password").val("");
    $("#login-username").val("");
    $("#signupbox").hide();
    $("#loginbox").show();
}

function showLoginForm() {
    $('#loginModal .registerBox').fadeOut('fast', function () {
        $('.loginBox').fadeIn('fast');
        $('.register-footer').fadeOut('fast', function () {
            $('.login-footer').fadeIn('fast');
        });

        $('.modal-title').html('Bejelentkezés');
    });
    $('.error').removeClass('alert alert-danger').html('');
}

function openLoginModal() {
    showLoginForm();
    setTimeout(function () {
        $('#loginModal').modal('show');
    }, 230);

}

function openRegisterModal() {
    showRegisterForm();
    $("#login-alert").hide();
    $("#signup-alert").hide();
    setTimeout(function () {
        $('#loginModal').modal('show');
    }, 230);

}


function shakeModal() {
    $('#loginModal .modal-dialog').addClass('shake');
    $('.error').addClass('alert alert-danger').html("Sajnos nem egyezik a név és a jelszó");
    $('input[type="password"]').val('');
    setTimeout(function () {
        $('#loginModal .modal-dialog').removeClass('shake');
    }, 1000);
}

   