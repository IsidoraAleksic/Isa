var reservedSeats = [];
var seatPlaces = [];
var reservedClicked;

$(document).ready(function () {
    reservedClicked = false;
    reservedSeats = [];
    loadHome();
});

function loadHome() {
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
        success: function (data) {
            var user = data;
            if (user != null) {
                $('#userId').empty();
                searchDiv.empty();
                results.empty();
                infoDiv.empty();
                leftPane.empty();
                sortDiv.empty();
                $('#userId').append('' + user.firstName + '');
                $('#divLogout').empty();
                $('#divLogout').append('<button class="w3-bar-item w3-button" style="float: right" onclick="logOut()">Logout</button>');
                userInformation.empty();
                userInformation.append('<img src="images\\userImage.png " height="62" width="62">');
                userInformation.append('<h3><b>' + user.firstName + '  ' + user.lastName + ' </b></h3>');
                userInformation.append('<p>' + user.email + '</p>');
                userInformation.append('<p>' + user.city + '</p>');
                userInformation.append('<p>' + user.phone + '</p>');
                listVisitedCinemaTheaters(user);
            }
        }
    });
}

function listVisitedCinemaTheaters(user) {
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var forma = $('<form action="" class="form-horizontal" id="formaSearchCinemaTheaters"></form>');
    var span = $('<span></span>');
    var input = $('<input type="text" class="form-control" name="search" id="searchCinemaTheater" placeholder="Search visited"/>');
    var search = $('<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Search</button>');
    var i;
    var newct;
    var tickets;
    var table = $('<table id="visitedCT"><tr><th style="padding:1%;">History</button></th></tr></table>');
    searchDiv.empty();
    infoDiv.empty();
    span.append(input);
    span.append(search);
    forma.append(span);
    searchDiv.append(forma);
    $.ajax({
        async:false,
        url: "/reserve/tickets",
        type:"GET",
        data:"json",
        success: function (data) {
            tickets = data instanceof Array ? data : [data];
        }
    });
    if(tickets.length>0) {
        for (i = 0; i < tickets.length; i++) {
            newct = "<tr><td style='padding:0px;'>" + tickets[i].projection.ct.name + "</td>";
            table.append(newct);
        }
        infoDiv.append(table);
    }
}

$(document).on('submit', '#formaSearchCinemaTheaters', function (e) {
    e.preventDefault();
    var infoDiv = $('#listCinemaTheaterDiv');
    var firstName = document.getElementById("searchCinemaTheater").value;
    infoDiv.empty();
    var i;
    var newct;
    var tickets;
    var table = $('<table id="visitedCT"><tr><th>History <button style="background-color: dimgrey; border: none; outline:none;" onclick=""><i class="down"></i></button></th></tr></table>');
    $.ajax({
        async:false,
        url: "/reserve/tickets",
        type:"GET",
        data:"json",
        success: function (data) {
            tickets = data instanceof Array ? data : [data];
        }
    });
    for (i = 0; i < tickets.length; i++) {
        if(tickets[i].projection.name==firstName) {
            newct = "<tr><td>" + tickets[i].projection.ct.name + "</td>";
            table.append(newct);

        }
    }
    infoDiv.append(table);


});

function logOut() {
    $.ajax({
        url: "/authenticate/logout",
        dataType: "text",
        type: "GET",
        success: function (data) {
            var ok = data;
            if (ok == "ok") {
                window.location = "/loginRegister.html";
            } else
                toastr.error('logout failed');
        }
    });
}

function goToProfile() {
    window.location = "/userPage.html";
}

function listFriends() {
    var results = $('#resultsDiv');
    var sortDiv = $('#sortByDiv');


    $.ajax({
        url: "/friends/allFriends",
        dataType: "json",
        type: "GET",
        success: function (data) {
            var friends = (data instanceof Array) ? data : [data];
            results.empty();
            sortDiv.empty();
            if (friends.length == 0) {
                results.append('<h4>No friends found</h4>');
            }
            sortDiv.append('<label>Sort by: </label><button class="btn" onclick="sortFriends(\'firstName\')">first Name</button>' +
                '<button class="btn" onclick="sortFriends(\'lastName\')">last Name</button>');

            showFriends(friends);
        }
    });
}

function sortFriends(name) {
    var results = $('#resultsDiv');
    $.ajax({
        url: "/friends/sortFriendsBy/" + name,
        dataType: "json",
        type: "GET",
        success: function (data) {
            var friends = (data instanceof Array) ? data : [data];
            results.empty();
            if (friends.length == 0) {
                results.append('<h4>No friends found</h4>');
            }
            showFriends(friends);
        }
    });
}

function showFriends(friends) {
    var results = $('#resultsDiv');
    $.each(friends, function (idx, friend) {
        var div = $('<div></div>');
        var span = $('<div id = "' + friend.id + '"></div>');
        div.append('<p><b>' + friend.firstName + ' ' + friend.lastName + '</b></p>');
        span.append('<button class="btn" onclick="removeFriend(' + friend.id + ')">Remove friend</button>');
        div.append(span);
        results.append(div);
    });
}

function removeFriend(id) {
    var span = $('#' + id + '');
    $.ajax({
        url: "/friends/declineRequest/" + id,
        dataType: "text",
        type: "GET",
        success: function (data) {
            var added = data;
            if (added == "deleted") {
                toastr.success('Friend removed');
                span.empty();
                span.append('<button class="btn" onclick="addFriend(' + id + ')">Add friend</button>');

            } else {
                toastr.error('Cannot delete user.');
            }
        }
    });
}

function addFriend(id) {
    // var span = document.getElementById(id);
    var span = $('#' + id + '');
    $.ajax({
        url: "/friends/addFriend/" + id,
        dataType: "text",
        type: "GET",
        success: function (data) {
            var added = data;
            if (added == "sent") {
                toastr.success('Friend request sent');
                span.empty();
                span.append('<button class="btn" onclick="removeFriend(' + id + ')">Remove request</button>');
            } else {
                toastr.error('Cannot add user.');
            }
        }
    });
}

function listCinemas() {

    var results = $('#resultsDiv');
    var sortDiv = $('#sortByDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    $.ajax({
        url: "/cinemas",
        success: function (data) {
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
            showCinemas(data);


        }
    });
}

function showCinemas(data) {
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
    for (i = 0; i < data.content.length; i++) {
        newct = "<tr><td>" + data.content[i].name + "</td>"
            + "<td>" + data.content[i].address + "</td>"
            + "<td>" + data.content[i].ambient + "</td>"
            + "<td><form action=\"\" class=\"form-horizontal\" id=\"formaVisitCinemaTheater\"><input id='hiddenVisitCT' type='hidden' value='" + data.content[i].id + "'><input class=\"btn\"  type=\"submit\" value=\"Visit\"/></form></td></tr>";
        table.append(newct);
        results.append(table);
    }

}

function sortCinemas(criteria) {
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
        url: "/sortCinemasBy/" + criteria,
        dataType: "json",
        type: "GET",
        success: function (data) {
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
            for (i = 0; i < data.length; i++) {
                newct = "<tr><td>" + data[i].name + "</td>"
                    + "<td>" + data[i].address + "</td>"
                    + "<td>" + data[i].ambient + "</td>"
                    + "<td><form action=\"\" class=\"form-horizontal\" id=\"formaVisitCinemaTheater\"><input id='hiddenVisitCT' type='hidden' value='" + data[i].id + "'><input class=\"btn\"  type=\"submit\" value=\"Visit\"/></form></td></tr>";
                table.append(newct);
                results.append(table);
            }

        }
    });
}

function sortTheaters(criteria) {
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
        url: "/sortTheatersBy/" + criteria,
        dataType: "json",
        type: "GET",
        success: function (data) {
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
            for (i = 0; i < data.length; i++) {
                newct = "<tr><td>" + data[i].name + "</td>"
                    + "<td>" + data[i].address + "</td>"
                    + "<td>" + data[i].ambient + "</td>"
                    + "<td><form action=\"\" class=\"form-horizontal\" id=\"formaVisitCinemaTheater\"><input id='hiddenVisitCT' type='hidden' value='" + data[i].id + "'><input class=\"btn\" type=\"submit\" value=\"Visit\"/></form></td></tr>";
                table.append(newct);
                results.append(table);
            }

        }
    });
}



function listTheatres(){
  // $(document).ready ( function(){
  //     $.ajax({
  //         url: "/authenticate/checkIfFirstLogin",
  //         contentType: "application/json",
  //         dataType: "text",
  //         type: "GET",
  //         success: function(data) {
  //             if(data=="true"){
  //                 $(location).attr('href', 'loginAgain.html');
  //             }
  //         }
  //     });
  // });

    var results = $('#resultsDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var sortDiv = $('#sortByDiv');

    $.ajax({
        url: "/theaters",
        success: function (data) {
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
            showTheaters(data);

        }
    });
}

function showTheaters(data) {
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
    for (i = 0; i < data.content.length; i++) {
        newct = "<tr><td>" + data.content[i].name + "</td>"
            + "<td>" + data.content[i].address + "</td>"
            + "<td>" + data.content[i].ambient + "</td>"
            + "<td><form action=\"\" class=\"form-horizontal\" id=\"formaVisitCinemaTheater\"><input id='hiddenVisitCT' type='hidden' value='" + data.content[i].id + "'><input class=\"btn\" type=\"submit\" value=\"Visit\"/></form></td></tr>";
        table.append(newct);
        results.append(table);
    }

}

$(document).on('submit', '#formaVisitCinemaTheater', function (e, id) {
    e.preventDefault();
    var _id = document.getElementById("hiddenVisitCT").value;
    window.location = "/ctProfile.html?id=" + id;
});

function tableList(data, type) {
    var results = $('#resultsDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var sortDiv = $('#sortByDiv');
    results.empty();
    leftPane.empty();
    searchDiv.empty();
    infoDiv.empty();
    sortDiv.empty();

    if (type == "cinema") {

        for (i = 0; i < data.content.length; i++) {
            var well = $(' <div class="well"></div>');
            var media = $(' <div class="media"></div>');
            var image = $(' <a class="pull-left" href="#"><img width="220" height="178" class="media-object" src="../images/movie.jpg" ></a>');
            var body = $(' <div class="media-body"></div>');
            var starsUl = $(' <ul class="list-inline list-unstyled"></ul>');
            var starsLi = $('<li></li>');
            var starsSpan = $('<span class="glyphicon glyphicon-star"></span>');
            var dugme = $('<p class="text-right" ></p>');
            var j = 0;
            media.append(image);
            body.append('<h4 class="media-heading">' + data.content[i].name + '</h4>');
            dugme.append('<button class="btn" onclick="openCinema(' + data.content[i].id + ')">Visit</button>');
            body.append(dugme);
            body.append('<p>' + data.content[i].address + '</p>');
            body.append('<p>' + data.content[i].description + '</p>');
            var stars = $('');
            if (data.content[i].ambient == 1)
                starsSpan = $('<span class="glyphicon glyphicon-star"></span>');
            else if (data.content[i].ambient > 0 && data.content[i].ambient < 2)
                starsSpan = $('<span class="glyphicon glyphicon-star"></span>');
            else if (data.content[i].ambient >= 2 && data.content[i].ambient < 3)
                starsSpan = $('<span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span>');
            else if (data.content[i].ambient > 3 && data.content[i].ambient < 4)
                starsSpan = $('<span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span>');
            else if (data.content[i].ambient > 4 && data.content[i].ambient < 5)
                starsSpan = $('<span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span>');
            else if (data.content[i].ambient == 5)
                starsSpan = $('<span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span>');

            starsLi.append(starsSpan);
            starsUl.append(starsLi);
            body.append(starsUl);
            media.append(body);
            well.append(media);
            results.append(well);
        }
    } else if (type == "projections") {
        $.each(data, function (idx, projection) {
            var well = $(' <div class="well"></div>');
            var media = $(' <div class="media"></div>');
			var img = projection.imagePath === "path" ? "../images/movie.jpg" : projection.imagePath;
            var image = $(' <a class="pull-left" href="#"><img width="220" height="178" class="media-object" src="'+img+'" ></a>');
            var body = $(' <div id="' + projection.id + '"class="media-body"></div>');
            var starsUl = $(' <ul class="list-inline list-unstyled"></ul>');
            var dugme = $('<p class="text-right" ></p>');
            var i = 0;
            media.append(image);
            body.append('<h4 class="media-heading">' + projection.name + '</h4>');
            body.append('<p><b>Genre: </b>' + projection.genre + '</p>');
            body.append('<p><b>Director: </b>' + projection.director + '</p>');
            body.append('<p><b>Actors: </b>' + projection.actors + '</p>');
            body.append('<p><b>Duration: </b>' + projection.duration + '</p>');
            body.append('<p><b>Price: ' + projection.price + '</b></p>');

            $.ajax({
                url: "/reserve/getDatesOfProjection/" + projection.name,
                dataType: "json",
                type: "GET",
                success: function (data) {
                    var dates = data instanceof Array ? data : [data];
                    $.each(dates, function (idx, date) {
                        dugme.append('<button class="btn" onclick="reserveDate(\'' + date + '\',\'' + projection.name + '\',\'' + projection.id + '\')">' + date + '</button>');
                        body.append(dugme);
                    });
                    media.append(body);
                    well.append(media);
                    results.append(well);

                }
            });
        });

    }
}

function reserveTicketCinema() {
    var results = $('#resultsDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var sortDiv = $('#sortByDiv');

    $.ajax({
        url: "/ct",
        success: function (data) {
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
            tableList(data, "cinema");

        }
    });
}

function reserveDate(date, name, idP) {
    var body = $('#' + idP + '');
    var projection;


    $.ajax({
        async: false,
        url: "/projections/getById/" + idP,
        dataType: "json",
        type: "GET",
        success: function (data) {
            projection = data;
        }
    });
    $.ajax({
        async: false,
        url: "/reserve/getTimesOfProjection/" + name + "/" + date,
        dataType: "json",
        type: "GET",
        success: function (data) {
            var times = data instanceof Array ? data : [data];
            body.empty();
            body.append('<h4 class="media-heading">' + projection.name + '</h4>');
            body.append('<p><b>Genre: </b>' + projection.genre + '</p>');
            body.append('<p><b>Director: </b>' + projection.director + '</p>');
            body.append('<p><b>Actors: </b>' + projection.actors + '</p>');
            body.append('<p><b>Duration: </b>' + projection.duration + '</p>');
            body.append('<p><b>Price: ' + projection.price + '</b></p>');

            $.each(times, function (idx, time) {
                var dugme = $('<button class="btn" onclick="getHall(\'' + date + '\',\'' + name + '\',\'' + projection.id +'\',\'' + time + '\')">' + time + '</button>');
                body.append(dugme);
            });
        }
    });
}

function getHall(date, name,idP, time) {
    var body = $('#' + idP + '');
    var projection;

    $.ajax({
        async: false,
        url: "/reserve/projectionSeats/" + date + "/" + name+ "/" + time,
        dataType: "json",
        type: "GET",
        success: function (data) {
            projection = data;
        }
    });
    $.ajax({
        async:false,
        url: "/reserve/getHall/" + projection.id,
        dataType: "json",
        type: "GET",
        success: function (data) {
            var halls = data instanceof Array ? data : [data];
            body.empty();
            body.append('<h4 class="media-heading">' + projection.name + '</h4>');
            body.append('<p><b>Genre: </b>' + projection.genre + '</p>');
            body.append('<p><b>Director: </b>' + projection.director + '</p>');
            body.append('<p><b>Actors: </b>' + projection.actors + '</p>');
            body.append('<p><b>Duration: </b>' + projection.duration + '</p>');

            $.each(halls, function (idx, hall) {
                var dugme = $('<button class="btn" onclick="getSeats(\'' + hall.id + '\',\'' + projection.id +'\',\'' + projection.name+ '\',\'' + hall.rows+ '\',\'' + hall.cols+ '\')">' + hall.name + '</button>');
                body.append(dugme);
            });
        }
    });
}

function getSeats(idH,idP,name,rows,cols) {
    var results = $('#resultsDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var sortDiv = $('#sortByDiv');
    results.empty();
    leftPane.empty();
    searchDiv.empty();
    infoDiv.empty();
    sortDiv.empty();
    $.ajax({
        async: false,
        url: "/reserve/getSeats/" + idP +"/"+ idH,
        success: function (data) {
            var seats = data instanceof Array ? data : [data];
            var caption = '<caption><h1><u>' + name.toUpperCase() + '</u></h1></caption>';
            var table = $('<table id="seatsTable" cellpadding="0" cellspacing="0" ></table>');
            table.append(caption);
            var i, j;
            for (i = 0; i < rows; i++) {
                var row = $('<tr id="seatsTr" ></tr>');
                for (j = 0; j < cols; j++) {
                    var found = false;
                    for (z = 0; z < seats.length; z++) {
                        if (seats[z].row == i && seats[z].col == j) {
                            if (seats[z].seatType == "AVAILABLE") {
                                found = true;
                                var ij = i + "" + j;
                                var col = $('<td id="seatsTd" style="padding:0px;"><div id="' + ij + '"><button id=' + ij + ' class="seat-available" onclick="selectSeat(\'' + i + '\',\'' + j + '\',\'' + seats[z].id+ '\',\'' +idH + '\')"></button></div></td>');
                                row.append(col);
                            } else if (seats[z].seatType == "TAKEN") {
                                found = true;
                                var col = $('<td id="seatsTd" style="padding:0px;"><button class="seat-taken" disabled="true"></button></td>');
                                row.append(col);
                            } else if (seats[z].seatType == "VIP") {
                                found = true;
                                var col = $('<td id="seatsTd" style="padding:0px;"><button class="seat-vip" disabled="true"></button></td>');
                                row.append(col);
                            } else if (seats[z].seatType == "REDACTED") {
                                found = true;
                                var col = $('<td id="seatsTd"  style="padding:0px;"><button class="seat-redacted" disabled="true"></button></td>');
                                row.append(col);
                            }
                        }
                    }
                    if (!found) {
                        var col = $('<td id="seatsTd" style="padding:0px;"><button class="seat-speed" disabled="true"></button></td>');
                        row.append(col);
                    }
                }
                table.append(row);
            }
            results.append(table);
            if(!reservedClicked)
                results.append('<button class="btn" id="reserveButton" onclick="reserve(\'' + idH + '\',\'' + idP +'\',\'' +name+ '\',\'' + rows+ '\',\'' + cols+ '\')">Reserve</button>');
            results.append('<button class="btn" onclick="noFriends(\'' + idP + '\',\'' + idH + '\')">Done</button>');
        }
    });
}

function listFriendsToInvite(id, idHall){
    var right = $('#listCinemaTheaterDiv');
    // var friends = $('#inviteFriendsButton');
    document.getElementById("inviteFriendsButton").disabled = true;
    // reservedClicked  = true;
    right.empty();

    if(reservedSeats.length<2){
        toastr.error("Select more than one seat.");
    } else {
        $.ajax({
            url: "/friends/allFriends",
            dataType: "json",
            type: "GET",
            success: function (data) {
                var friends = (data instanceof Array) ? data : [data];
                $.each(friends, function (idx, friend) {
                    if (reservedSeats.length > 1 && friends.length > 0) {
                        var div = $('<div></div>');
                        var span = $('<div id = "' + friend.id + '"></div>');
                        span.append('<p><b>' + friend.firstName + ' ' + friend.lastName + '</b></p>');

                        span.append('<button class="btn" onclick="inviteFriend(\'' + id + '\',\'' + friend.id + '\',\'' + idHall + '\')">Invite friend</button>');
                        div.append(span);
                    }
                    right.append(div);
                });

            }
        });
    }

}

function inviteFriend(idP, id, idHall) {
    var span = $('#'+id+'');
    var rowCol = seatPlaces.pop();
    if(reservedSeats.length<2)
        toastr.error("You only have one ticket left. Select more tickets");
    else {
        $.ajax({
            url: "/friends/inviteFriend/" + id + "/" + idP + "/" + reservedSeats,
            dataType: "json",
            type: "GET",
            success: function (data) {
                if (data == null)
                    toastr.error("Friend not invited");
                else {
                    reservedSeats = data;
                    span.html("");
                    var td = $('#' + rowCol + '');
                    td.empty();
                    td.append('<button id=' + rowCol + ' class="seat-taken" disabled="true" ></button>');
                    toastr.success("Friend invited");
                }
            }
        });
    }

}

function reserve(idH, idP,name,rows,cols) {
    // var reserveButton = $('#reserveButton');

    reservedClicked  = true;

    if(reservedSeats.length<1){
        toastr.error("Select at least one seat.");
    } else {
        $.ajax({
            url: "/reserve/reserveTickets/" + idP + "/" + reservedSeats,
            dataType: "text",
            type: "GET",
            success: function (data) {
                if(data=="already")
                    toastr.error("Somebody else just reserved that seat. Please choose another one.");
                if (data == "ok") {
                    toastr.success("Reserved!");
                    getSeats(idH, idP,name,rows,cols);
                    $.ajax({
                        url: "/friends/allFriends",
                        dataType: "json",
                        type: "GET",
                        success: function (data) {
                            var friends = (data instanceof Array) ? data : [data];
                            if (friends.length > 0 && reservedSeats.length > 1) {
                                var results = $('#resultsDiv');
                                results.append('<button class="btn" id="inviteFriendsButton" onclick="listFriendsToInvite(\'' + idP + '\',\'' + idH + '\')">Invite friends</button>');

                            }
                        }
                    });
                    // window.location = "/homeRegistered.html";

                } else {
                    toastr.error("Reservation failed");
                }
            }
        });
    }

}

function noFriends(idP,idH){
    reservedSeats = [];
    window.location = "/homeRegistered.html";
    toastr.success("Successful reservation");
}

function openCinema(id) {
    $.ajax({
        url: "/reserve/getCinemaTheater/" + id,
        dataType: "json",
        type: "GET",
        success: function (data) {
            var projections = data instanceof Array ? data : [data];
            tableList(projections, "projections");
        }
    });

}

function selectSeat(row, col, id, idHall) {
    var right = $('#listCinemaTheaterDiv');

    right.empty();
    var rowCol = row + "" + col;
    seatPlaces.push(rowCol);
    reservedSeats.push(id);
    var td = $('#' + rowCol + '');
    td.empty();
    td.append('<button class="seat-selected" onclick="deselectSeat(\'' + row + '\',\'' + col + '\',\'' + id + '\',\'' + idHall+ '\')" ></button>');
}

function deselectSeat(row, col, id,idHall) {
    var right = $('#listCinemaTheaterDiv');
    right.empty();
    var rowCol = row + "" + col;
    reservedSeats = reservedSeats.filter(function (x, i) {
        return x !== id
    });
    seatPlaces = seatPlaces.filter(function (x, i) {
        return x !== rowCol;
    });
    var td = $('#' + rowCol + '');
    td.empty();
    td.append('<button id=' + rowCol + ' class="seat-available" onclick="selectSeat(\'' + row + '\',\'' + col + '\',\'' + id +'\',\'' + idHall+'\')" ></button>');
}


function openFanZone(){
    $(location).attr('href', 'FanZone.html');
}

function returnToHome(){
    $(location).attr('href', 'homeRegistered.html');
}

function setScale(){
    $.ajax({
        url: "/userTierScale/setScale",
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "minBronze": $('#minBronzePoints').val(),
            "minSilver": $('#minSilverPoints').val(),
            "minGolden": $('#minGoldenPoints').val()
        }),
        success: function(data) {
            $('#scaleModal').modal('hide');
        }
    });
}
function openModalScale() {
    $("#scaleModal").modal();

}
