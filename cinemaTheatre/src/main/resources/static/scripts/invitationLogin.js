$(function() {

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
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
                    window.location= "/invitation.html";
                }
            }
        });

    });

});
