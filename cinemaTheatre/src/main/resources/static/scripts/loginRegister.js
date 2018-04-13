$(function() {

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
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
                    window.location= "http://localhost:9080/homeRegistered.html";
                }else{
                    $("#loginMessage").empty();
                    $("#loginMessage").append('<p>Email not confirmed. Please confirm email.</p>');

                }

            }
        });

    });

});

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

    if(password != confirm){
        $("#loginMessage").empty();
        $("#loginMessage").append('<p>Passwords do not match</p>');
    }else{
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
            }),success: function(data){
                var user = data;
                if(user=="nok") {
                    $("#loginMessage").empty();
                    $("#loginMessage").append('<p>Please eneter all the fields. </p>');
                }else if(user=="exists"){
                    $("#loginMessage").empty();
                    $("#loginMessage").append('<p>User already exists. </p>');
                }else if(user=="ok"){
                    window.location= "http://localhost:9080/homeRegistered.html";
                }else if(user=="not confirmed") {
                    $("#loginMessage").empty();
                    $("#register-form").empty();
                    $("#loginMessage").append('<p>Email sent. Please confirm email.</p>');

                }

            }
        });
    }



});
