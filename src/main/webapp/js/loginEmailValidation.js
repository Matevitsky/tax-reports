$(document).ready(function () {
});
var pattern = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
var loginEmail = $('#email');

loginEmail.blur(function () {
    if (loginEmail.val() != '') {
        if (loginEmail.val().search(pattern) == 0) {
            $('#loginValid').text('valid');
            $('#login-submit').attr('disabled', false);
            loginEmail.removeClass('error').addClass('ok');
        } else {
            $('#loginValid').text('not valid email');
            $('#login-submit').attr('disabled', true);
            loginEmail.addClass('ok');
        }
    } else {
        $('#loginValid').text('The e-mail field should not be empty!');
        loginEmail.addClass('error');
        $('#login-submit').attr('disabled', true);
    }

});