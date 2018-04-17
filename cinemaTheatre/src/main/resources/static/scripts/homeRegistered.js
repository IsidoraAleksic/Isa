$(document).ready(function(){
    loadHome();
});

function loadHome(){
    $.ajax({
        url: "/authenticate/getUser",
        dataType: "json",
        type: "GET",
        success: function(data){
            var user = data;
            if(user!=null){
                $('#userId').empty();
                $('#userId').append(''+user.firstName+'');
                $('#divLogout').empty();
                $('#divLogout').append('<button class="w3-bar-item w3-button" style="float: right" onclick="logOut()">Logout</button>');
            }
        }
    });
}

function logOut(){
    $.ajax({
        url: "/authenticate/logout",
        dataType: "text",
        type: "GET",
        success: function(data){
            var ok = data;
            if(ok=="ok"){
               window.location = "http://localhost:9080/loginRegister.html";
            }else
                toastr.error('logout failed');
        }
    });
}

function goToProfile(){
    window.location = "http://localhost:9080/userPage.html";
}

function listFriends(){
    var results = $('#resultsDiv');

    $.ajax({
        url: "/friends/allFriends",
        dataType: "json",
        type: "GET",
        success: function(data){
            var friends = (data instanceof Array) ? data : [data];
            results.empty();
            if(friends.length==0){
                results.append('<h4>No friends found</h4>');
            }
            $.each(friends, function(idx, friend) {
                var div = $('<div id="addDeleteDiv"></div>');
                results.append('<h4>'+friend.firstName+' '+friend.lastName+'</h4><p>'+friend.email+'</p><br>');
                var forma =$('<form action="" class="form-horizontal" id="formaObrisiPrijatelja"></form>');
                var hidden = $('<input id="hiddenObrisi" type="hidden" value="' + friend.id + '">');
                forma.append(hidden);
                forma.append('<input class="btn" type="submit" value="Remove friend">');
                results.append(forma);

            });
        }
    });
}

$(document).on('submit', '#formaObrisiPrijatelja', function(e){
    e.preventDefault();
    var results = $('#resultsDiv');
    var div = $('#addDeleteDiv');

    var id = document.getElementById("hiddenObrisi").value;

    $.ajax({
        url: "/friends/declineRequest/" + id,
        dataType: "text",
        type: "GET",
        success: function(data){
            var added = data;
            if(added=="deleted"){
                toastr.success('Friend removed');
                $('#formaObrisiPrijatelja').remove();
                var forma =$('<form action="" class="form-horizontal" id="formaDodajPrijatelja"></form>');
                var hidden = $('<input id="hiddenDodaj" type="hidden" value="' + id + '">');
                forma.append(hidden);
                forma.append('<input class="btn" type="submit" value="Add friend">');
                div.append(forma);
                results.append(div);

            }else{
                toastr.error('Cannot delete user.');
            }
        }
    });
});

function listCinemas(){
    var results = $('#resultsDiv');
    var table = $('<table id="dataTable" style="width:50%; margin-left: auto; margin-right:auto;"><tbody></tbody></table>')
    $.ajax({
        url: "//cinemas",
        success: function(data){

            for(i = 0; i < data.content.length;i++){
                newct = "<tr class=\"bottom\"><td rowspan=\"5\" class=\"pic\"><img src=\"images/placeholder.png\"/></td>"
                    + "<td class=\"irs\">Name: "+data.content[i].name+"</td></tr>"
                    +"<tr><td class=\"irs\">Address: "+data.content[i].address+"</td></tr>"
                    +"<tr><td rowspan=\"2\">Description: "+data.content[i].description+"</td></tr>"
                    +"<tr></tr>"
                    +"<tr><td class=\"irs\">Rating: "+data.content[i].ambient+"</td></tr>"
                    +"<tr><td class=\"extra\" colspan=\"2\"></td></tr>";
                table.append(newct);
                results.append(table);
            }

        }
    });
}
function listTheatres(){
    var results = $('#resultsDiv');
    var table = $('<table id="dataTable" style="width:50%; margin-left: auto; margin-right:auto;"><tbody></tbody></table>')
    $.ajax({
        url: "//theaters" ,
        success: function(data){

            for(i = 0; i < data.content.length;i++){
                newct = "<tr class=\"bottom\"><td rowspan=\"5\" class=\"pic\"><img src=\"images/placeholder.png\"/></td>"
                    + "<td class=\"irs\">Name: "+data.content[i].name+"</td></tr>"
                    +"<tr><td class=\"irs\">Address: "+data.content[i].address+"</td></tr>"
                    +"<tr><td rowspan=\"2\">Description: "+data.content[i].description+"</td></tr>"
                    +"<tr></tr>"
                    +"<tr><td class=\"irs\">Rating: "+data.content[i].ambient+"</td></tr>"
                    +"<tr><td class=\"extra\" colspan=\"2\"></td></tr>";
                table.append(newct);
                results.append(table);
            }

        }
    });
}

$(document).ready ( function(){
    $.ajax({
        url: "/authenticate/checkIfFirstLogin",
        contentType: "application/json",
        dataType: "text",
        type: "GET",
        success: function(data) {
            if(data=="true"){
                $(location).attr('href', 'loginAgain.html');
            }
        }
    });
});