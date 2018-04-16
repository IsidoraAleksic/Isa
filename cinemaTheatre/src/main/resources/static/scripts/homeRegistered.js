$(document).ready(function(){
    loadHome();
});

function loadHome(){
    var userInformation = $('#userInformation');
    var results = $('#resultsDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var sortDiv = $('#sortByDiv');

    $.ajax({
        url: "/authenticate/getUser",
        dataType: "json",
        type: "GET",
        success: function(data){
            var user = data;
            if(user!=null){
                $('#userId').empty();
                searchDiv.empty();
                results.empty();
                infoDiv.empty();
                leftPane.empty();
                sortDiv.empty();
                $('#userId').append(''+user.firstName+'');
                $('#divLogout').empty();
                $('#divLogout').append('<button class="w3-bar-item w3-button" style="float: right" onclick="logOut()">Logout</button>');
                userInformation.empty();
                userInformation.append('<img src="images\\userImage.png " height="62" width="62">');
                userInformation.append('<h3><b>'+user.firstName+'  '+user.lastName+' </b></h3>');
                userInformation.append('<p>'+user.email+'</p>');
                userInformation.append('<p>'+user.city+'</p>');
                userInformation.append('<p>'+user.phone+'</p>');
                listVisitedCinemaTheaters(user);


            }
        }
    });
}

function listVisitedCinemaTheaters(user){
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    searchDiv.empty();
    infoDiv.empty();
    var forma = $('<form action="" class="form-horizontal" id="formaSearchCinemaTheaters"></form>');
    var span = $('<span></span>');
    var input = $('<input type="text" class="form-control" name="search" id="searchCinemaTheater" placeholder="Search visited"/>');
    var search = $('<button type="button" class="btn btn-default">\n' +
        '      <span class="glyphicon glyphicon-search"></span> Search\n' +
        '    </button>');
    span.append(input);
    span.append(search);
    forma.append(span);
    searchDiv.append(forma);


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
    var sortDiv = $('#sortByDiv');


    $.ajax({
        url: "/friends/allFriends",
        dataType: "json",
        type: "GET",
        success: function(data){
            var friends = (data instanceof Array) ? data : [data];
            results.empty();
            sortDiv.empty();
            if(friends.length==0){
                results.append('<h4>No friends found</h4>');
            }
            sortDiv.append('<label>Sort by: </label><button class="btn" onclick="sortFriends(\'firstName\')">first Name</button>' +
                '<button class="btn" onclick="sortFriends(\'lastName\')">last Name</button>');

            showFriends(friends);
        }
    });
}
function sortFriends(name){
    var results = $('#resultsDiv');
    $.ajax({
        url: "/friends/sortFriendsBy/" + name,
        dataType: "json",
        type: "GET",
        success: function(data){
            var friends = (data instanceof Array) ? data : [data];
            results.empty();
            if(friends.length==0){
                results.append('<h4>No friends found</h4>');
            }
            showFriends(friends);
        }
    });
}
function showFriends(friends){
    var results = $('#resultsDiv');
    $.each(friends, function(idx, friend) {
        var div = $('<div></div>');
        var span = $('<div id = "'+friend.id+'"></div>');
        div.append('<p><b>'+friend.firstName+' '+friend.lastName+'</b></p>');
        span.append('<button class="btn" onclick="removeFriend('+friend.id+')">Remove friend</button>');
        div.append(span);
        results.append(div);
    });
}

function removeFriend(id){
    var span = $('#'+id+'');
    $.ajax({
        url: "/friends/declineRequest/" + id,
        dataType: "text",
        type: "GET",
        success: function(data){
            var added = data;
            if(added=="deleted"){
                toastr.success('Friend removed');
                span.empty();
                span.append('<button class="btn" onclick="addFriend('+id+')">Add friend</button>');

            }else{
                toastr.error('Cannot delete user.');
            }
        }
    });
}

function addFriend(id){
    // var span = document.getElementById(id);
    var span = $('#'+id+'');
    $.ajax({
        url: "/friends/addFriend/" + id,
        dataType: "text",
        type: "GET",
        success: function (data) {
            var added = data;
            if (added == "sent") {
                toastr.success('Friend request sent');
                span.empty();
                span.append('<button class="btn" onclick="removeFriend('+id+')">Remove request</button>');
            } else {
                toastr.error('Cannot add user.');
            }
        }
    });
}

function listCinemas(){

    var results = $('#resultsDiv');
    var sortDiv = $('#sortByDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    $.ajax({
        url: "/cinemas" ,
        success: function(data){
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
           showCinemas(data);


        }
    });
}

function showCinemas(data){
    var i;
    var newct;
    var results = $('#resultsDiv');
    var table = $('<table id="cinemaTable1">\n' +
        '    <tr>\n' +
        '        <th>Name    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortCinemas(\'name\')"><i class="down"></i></button></th>\n' +
        '        <th>Distance    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortCinemas(\'distance\')"><i class="down"></i></button></th>\n' +
        '        <th>Rating    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortCinemas(\'rating\')"><i class="down"></i></button></th>\n' +
        '        <th>Projections</th>\n' +
        '    </tr>\n' +
        '</table>');
    for(i = 0; i < data.content.length;i++){
        newct = "<tr><td>"+data.content[i].name +"</td>"
            +"<td>"+data.content[i].address+"</td>"
            +"<td>"+data.content[i].ambient+"</td>"
            +"<td><form action=\"\" class=\"form-horizontal\" id=\"formaVisitCinemaTheater\"><input id='hiddenVisitCT' type='hidden' value='" + data.content[i].id + "'><input class=\"btn\"  type=\"submit\" value=\"Visit\"/></form></td></tr>";
        table.append(newct);
        results.append(table);
    }

}

function sortCinemas(criteria){
    var i;
    var newct;
    var results = $('#resultsDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var sortDiv = $('#sortByDiv');
    var results = $('#resultsDiv');
    var table = $('<table id="cinemaTable1">\n' +
        '    <tr>\n' +
        '        <th>Name   <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortCinemas(\'name\')"><i class="down"></i></button></th>\n' +
        '        <th>Distance    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortCinemas(\'distance\')"><i class="down"></i></button></th>\n' +
        '        <th>Rating    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortCinemas(\'rating\')"><i class="down"></i></button></th>\n' +
        '        <th>Projections</th>\n' +
        '    </tr>\n' +
        '</table>');
    $.ajax({
        url: "/sortCinemasBy/" +criteria,
        dataType:"json",
        type: "GET",
        success: function(data){
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
            for(i = 0; i < data.length;i++){
                newct = "<tr><td>"+data[i].name +"</td>"
                    +"<td>"+data[i].address+"</td>"
                    +"<td>"+data[i].ambient+"</td>"
                    +"<td><form action=\"\" class=\"form-horizontal\" id=\"formaVisitCinemaTheater\"><input id='hiddenVisitCT' type='hidden' value='" + data[i].id + "'><input class=\"btn\"  type=\"submit\" value=\"Visit\"/></form></td></tr>";
                table.append(newct);
                results.append(table);
            }

        }
    });
}

function sortTheaters(criteria){
    var i, newct;
    var results = $('#resultsDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var sortDiv = $('#sortByDiv');
    var table = $('<table id="theaterTable1">\n' +
        '    <tr>\n' +
        '        <th>Name    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortTheaters(\'name\')"><i class="down"></i></button></th>\n' +
        '        <th>Distance    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortTheaters(\'distance\')"><i class="down"></i></button></th>\n' +
        '        <th>Rating    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortTheaters(\'rating\')"><i class="down"></i></button></th>\n' +
        '        <th>Projections</th>\n' +
        '    </tr>\n' +
        '</table>');
    $.ajax({
        url: "/sortTheatersBy/" +criteria,
        dataType:"json",
        type: "GET",
        success: function(data){
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
            for(i = 0; i < data.length;i++){
                newct = "<tr><td>"+data[i].name+"</td>"
                    +"<td>"+data[i].address+"</td>"
                    +"<td>"+data[i].ambient+"</td>"
                    +"<td><form action=\"\" class=\"form-horizontal\" id=\"formaVisitCinemaTheater\"><input id='hiddenVisitCT' type='hidden' value='" + data[i].id + "'><input class=\"btn\" type=\"submit\" value=\"Visit\"/></form></td></tr>";
                table.append(newct);
                results.append(table);
            }

        }
    });
}
function listTheatres(){
    var results = $('#resultsDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var sortDiv = $('#sortByDiv');

    $.ajax({
        url: "/theaters" ,
        success: function(data){
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
            showTheaters(data);

        }
    });
}
function showTheaters(data){
    var i;
    var newct;
    var results = $('#resultsDiv');
    var table = $('<table id="theaterTable1">\n' +
        '    <tr>\n' +
        '        <th>Name    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortTheaters(\'name\')"><i class="down"></i></button></th>\n' +
        '        <th>Distance    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortTheaters(\'distance\')"><i class="down"></i></button></th>\n' +
        '        <th>Rating    <button style="background-color: dimgrey; border: none; outline:none;" onclick="sortTheaters(\'rating\')"><i class="down"></i></button></th>\n' +
        '        <th>Projections</th>\n' +
        '    </tr>\n' +
        '</table>');
    for(i = 0; i < data.content.length;i++){
        newct = "<tr><td>"+data.content[i].name+"</td>"
            +"<td>"+data.content[i].address+"</td>"
            +"<td>"+data.content[i].ambient+"</td>"
            +"<td><form action=\"\" class=\"form-horizontal\" id=\"formaVisitCinemaTheater\"><input id='hiddenVisitCT' type='hidden' value='" + data.content[i].id + "'><input class=\"btn\" type=\"submit\" value=\"Visit\"/></form></td></tr>";
        table.append(newct);
        results.append(table);
    }

}
$(document).on('submit','#formaVisitCinemaTheater', function(e){
    e.preventDefault();
    var id = document.getElementById("hiddenVisitCT").value;
    window.location = "http://localhost:9080/ctProfile.html?id=" + id;



});

function openFanZone(){
    $(location).attr('href', 'FanZone.html');
}