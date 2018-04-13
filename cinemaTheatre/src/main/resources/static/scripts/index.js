$(document).ready(function(){
    loadIndex();
});

function loadIndex(){
    $.ajax({
        url: "/authenticate/getUser",
        dataType: "json",
        type: "GET",
       success: function(data){
            var user = data;
            if(user!=null){
                $('#loginLink').empty();
                $('#loginLink').append(' <a href="userPage.html">'+user.firstName+'</a>');
            }
       }
       });

}