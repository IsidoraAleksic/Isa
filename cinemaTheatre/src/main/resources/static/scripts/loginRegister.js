$(function() {

    $('#login-form-link').click(function(e) {
        $("#loginMessage").empty();
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#loginMessage").empty();
        $('#socialMedia').empty();
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#login-submit').click(function(event){
        event.preventDefault();
        $("#loginMessage").empty();
        var email = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        $.ajax({
            url: "/authenticate/login",
            contentType: "application/json",
            dataType: "text",
            type: "POST",
            data: JSON.stringify({
                "email": email,
                "password": password
            }),success: function(data){
                var user = data;
                if(user=="nok") {
                    $("#loginMessage").empty();
                    $("#loginMessage").append('<p>User with email and password not found</p>');
                }else if(user=="ok"){
                    window.location= "/homeRegistered.html";
                }else{
                    $("#loginMessage").empty();
                    $("#loginMessage").append('<p>Email not confirmed. Please confirm email.</p>');

                }

            }
        });

    });

});

function googleFace(){
    toastr.info("Has not been implemented yet.");
}

$(document).on('submit', '#register-form', function(e){
    e.preventDefault();
    $("#loginMessage").empty();
    event.preventDefault();
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("passwordR").value;
    var confirm = document.getElementById("confirm-password").value;
    var city = document.getElementById("city").value;
    var phone = document.getElementById("phone").value;

    var ok = true;
    if(firstName==""){
        ok = false;
        $("#loginMessage").empty();
        $("#loginMessage").append('<p>First name cannot be empty</p>');
    }
    if(lastName==""){
        ok = false;
        $("#loginMessage").empty();
        $("#loginMessage").append('<p>Last name cannot be empty</p>');
    }
    if(email==""){
        ok = false;
        $("#loginMessage").empty();
        $("#loginMessage").append('<p>Email cannot be empty</p>');
    }
    if(city==""){
        ok = false;
        $("#loginMessage").empty();
        $("#loginMessage").append('<p>City cannot be empty</p>');
    }
    if(phone==""){
        ok = false;
        $("#loginMessage").empty();
        $("#loginMessage").append('<p>Phone cannot be empty</p>');
    }


    if(password != confirm){
        ok = false;
        $("#loginMessage").empty();
        $("#loginMessage").append('<p>Passwords do not match</p>');
    }
    if(ok){
        $.ajax({
            url: "/authenticate/register",
            contentType: "application/json",
            dataType: "text",
            type: "POST",
            data: JSON.stringify({
                "firstName": firstName,
                "lastName": lastName,
                "email": email,
                "password": password,
                "city": city,
                "phone": phone,
                // "role": $('#role').val()

            }),success: function(data){
                var user = data;
                if(user=="nok") {
                    $("#loginMessage").empty();
                    $("#loginMessage").append('<p>Please eneter all the fields. </p>');
                }else if(user=="exists"){
                    $("#loginMessage").empty();
                    $("#loginMessage").append('<p>User already exists. </p>');
                }else if(user=="ok"){
                    window.location= "/homeRegistered.html";
                }else if(user=="not confirmed") {
                    $("#loginMessage").empty();
                    // $("#register-form").empty();
                    // $("#loginMessage").append('<p>Email sent. Please confirm email.</p>');

                    toastr.success("Registration successful. Please verify email.");
                }

            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                if(XMLHttpRequest.status=="406"){
                    $("#loginMessage").empty();
                    $("#loginMessage").append('<p>Fields must be valid. Check email and password</p>');
                }

            }
        });
    }
});


$(document).ready ( function(){
    $.ajax({
        url: "/authenticate/getUser",
        dataType: "json",
        type: "GET",
        async: false,
        success: function(data){
            content = '';
            if(data.role == "UNREGISTERED") {
                content = '<option value=\"UNREGISTERED"\">';
            } else if(data.role == 'ADMIN') {
                content = '<option value=\"ADMIN"\">'+'<option value=\"ADMINCT"\">'+'<option value=\"ADMINFZ"\">';
            }
            $('#roles').append(content);
        }
    });
});

function changePassword(){
    var newPassword = $("#passwordNew").val();
    var newPasswordConfirm = $("#passwordNewConfirm").val();
    if(newPassword!=newPasswordConfirm){
        alert("Passwords don't match!");
    }else{
        $.ajax({
            url: "/authenticate",
            contentType: "application/json",
            dataType: "text",
            type: "POST",
            data: JSON.stringify({
                "userId":"",
                "passwordOld": $("#passwordOld").val(),
                "passwordNew": newPassword
            }),
            success: function(data) {
                $(location).attr('href', 'homeRegistered.html')
            }
        });
    }


}
function registerAdmin(){
    $(location).attr('href', 'loginRegister.html');
}
function openChangePasswordPage(){
    $(location).attr('href', 'loginAgain.html')
}