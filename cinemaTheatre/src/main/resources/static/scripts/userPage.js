window.onload = editUser;
function editUser(){

    $.ajax({
        url: "/authenticate/getUser",
        dataType: "json",
        type: "GET",
        success: function(data){
            var user = data;
            if(user!=null){
                $('#editFNdiv').empty();
                $('#editLNdiv').empty();
                $('#editCitydiv').empty();
                $('#editPhonediv').empty();
                $('#editPassdiv').empty();
                $('#username').empty();
                $('#username').append('<img src="images\\userImage.png " height="62" width="62">'+user.firstName+' '+user.lastName+'');
                $('#editFNdiv').append(' <br><label>First Name: </label><input type="text" class="form-control" id="editFirstName" placeholder="First Name" value="'+user.firstName+'"><br>');
                $('#editLNdiv').append(' <br><label>Last Name: </label><input type="text" class="form-control" id="editLastName" placeholder="Last Name" value="'+user.lastName+'"><br>');
                $('#editCitydiv').append(' <br><label>City: </label><input type="text" class="form-control" id="editCity" placeholder="City" value="'+user.city+'"><br>');
                $('#editPhonediv').append(' <br><label>Phone: </label><input type="text" class="form-control" id="editPhone" placeholder="Phone" value="'+user.phone+'"><br>');
                $('#editPassdiv').append(' <br><label>Password: </label><input type="password" class="form-control" id="editPassword" placeholder="Password" value="'+user.password+'"><br>');


            }
        }
    });

}

$(document).on('submit', '#formaEditUser', function(e){
    e.preventDefault();

    var firstName = document.getElementById("editFirstName").value;
    var lastName = document.getElementById("editLastName").value;
    var password = document.getElementById("editPassword").value;
    var city = document.getElementById("editCity").value;
    var phone = document.getElementById("editPhone").value;
    var ok = true;
    if(!firstName || !lastName || !password || !city || !phone ) {
        ok = false;
        $('#porukaDiv').empty();
        $('#porukaDiv').append('<label class="col-lg-8 control-label">All fields must me filled</label>');
    }
    if(ok){
        $.ajax({
            type: "POST",
            url: "/user/edit",
            contentType: "application/json",
            dataType: "text",
            data: JSON.stringify({
                "firstName": firstName,
                "lastName": lastName,
                "password": password,
                "city": city,
                "phone": phone,
            }),
            success: function (data) {
                if(data=="nok"){
                    $('#porukaDiv').empty();
                    $('#porukaDiv').append('<label class="col-lg-8 control-label">Edit unsuccessful. User not found.</label>');
                } else  if(data=="ok"){
                    $('#porukaDiv').empty();
                    $('#porukaDiv').append('<label class="col-lg-8 control-label">User information updated.</label>');
                }

            }
        });
    }
});

$(document).on('submit', '#formaSearchByName', function(e){
    e.preventDefault();
    var firstName = document.getElementById("searchName").value;
    var justFriends = document.getElementById("justFriendsName").checked;
    var results = $('#resultsDiv');
    var ok = true;
    if(!firstName ) {
        ok = false;
        results.empty();
        results.append('<h4>No users found with name "'+firstName+'" </h4>');
    }

    if(ok){
        if(justFriends) {
            $.ajax({
                type : "GET",
                url : "/friends/friendsName/" + firstName,
                dataType : "json",
                success : function(data) {
                    var friends = (data instanceof Array) ? data : [data];
                    results.empty();
                    if(friends.length==0){
                        results.append('<h4>No friends found with name "'+firstName+'" </h4>');
                    }

                    $.each(friends, function(idx, friend) {
                        var div = $('<div></div>');
                        var span = $('<div id = "'+friend.id+'"></div>');
                        div.append('<h4>'+friend.firstName+' '+friend.lastName+'</h4><p>'+friend.email+'</p><br>');
                        span.append('<button class="btn" onclick="removeFriend('+friend.id+')">Remove friend</button>');
                        div.append(span);
                        results.append(div);


                        // results.append('<h4>'+friend.firstName+' '+friend.lastName+'</h4><p>'+friend.email+'</p><br>');
                        // var forma =$('<form action="" class="form-horizontal" id="formaObrisiPrijatelja"></form>');
                        // var hidden = $('<input id="hiddenObrisi" type="hidden" value="' + friend.id + '">');
                        // forma.append(hidden);
                        // forma.append('<input class="btn" type="submit" value="Remove friend">');
                        // results.append(forma);

                    });

                }
            });
        } else{

            $.ajax({
                type : "GET",
                url : "/friends/allName/" + firstName,
                dataType : "json",
                success : function(data) {
                    var friends = (data instanceof Array) ? data : [data];
                    results.empty();
                    if(friends.length==0){
                        results.append('<h4>No friends found with name "'+firstName+'" </h4>');
                    }
                    $.each(friends, function(idx, friend) {
                        // results.append('<h4>'+friend.firstName+' '+friend.lastName+'</h4>'+friend.email+'');
                        var id = friend.id;
                        $.ajax({
                            type : "GET",
                            url : "/friends/isFriends/" + id,
                            dataType : "text",
                            success : function(data) {
                                var span = $('<div id = "'+friend.id+'"></div>');
                                var div = $('<div></div>');
                                if(data=="no") {
                                    div.append('<h4>'+friend.firstName+' '+friend.lastName+'</h4><p>'+friend.email+'</p><br>');
                                    span.append('<button class="btn" onclick="addFriend('+friend.id+')">Add friend</button>');
                                    div.append(span);
                                    results.append(div);
                                }else{

                                    div.append('<h4>'+friend.firstName+' '+friend.lastName+'</h4><p>'+friend.email+'</p><br>');
                                    span.append('<button class="btn" onclick="removeFriend('+friend.id+')">Remove friend</button>');
                                    div.append(span);
                                    results.append(div);
                                }
                            }
                        });
                    });

                }
            });

        }
    }

});

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
                span.append('<button class="btn" onclick="removeFriend('+id+')">Remove friend</button>');
            } else {
                toastr.error('Cannot add user.');
            }
        }
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

// $(document).on('submit', '#formaDodajPrijatelja', function(e) {
//     e.preventDefault();
//     var loggedIn;
//     var id = document.getElementById("hiddenDodaj").value;
//     var results = $('#resultsDiv');
//     $.ajax({
//         url: "/authenticate/getUser",
//         dataType: "json",
//         type: "GET",
//         success: function (data) {
//             loggedIn = data;
//         }
//     });
//
//     $.ajax({
//         url: "/friends/addFriend/" + id,
//         dataType: "text",
//         type: "GET",
//         success: function (data) {
//             var added = data;
//             if (added == "sent") {
//                 toastr.success('Friend request sent');
//                 $('#formaDodajPrijatelja').remove();
//                 var forma = $('<form action="" class="form-horizontal" id="formaObrisiPrijatelja"></form>');
//                 var hidden = $('<input id="hiddenObrisi" type="hidden" value="' + id + '">');
//                 forma.append(hidden);
//                 forma.append('<input class="btn" type="submit" value="Remove friend">');
//                 results.append(forma);
//             } else {
//                 toastr.error('Cannot add user.');
//             }
//         }
//     });
// });
// $(document).on('submit', '#formaObrisiPrijatelja', function(e){
//         e.preventDefault();
//        obrisiPrijatelja();
// });
//
// function obrisiPrijatelja(){
//     var results = $('#resultsDiv');
//     var loggedIn;
//     var id = document.getElementById("hiddenObrisi").value;
//     $.ajax({
//         url: "/authenticate/getUser",
//         dataType: "json",
//         type: "GET",
//         success: function(data){
//             loggedIn = data;
//         }
//     });
//
//     $.ajax({
//         url: "/friends/declineRequest/" + id,
//         dataType: "text",
//         type: "GET",
//         success: function(data){
//             var added = data;
//             if(added=="deleted"){
//                 toastr.success('Friend removed');
//                 $('#formaObrisiPrijatelja').remove();
//                 var forma =$('<form action="" class="form-horizontal" id="formaDodajPrijatelja"></form>');
//                 var hidden = $('<input id="hiddenDodaj" type="hidden" value="' + id + '">');
//                 forma.append(hidden);
//                 forma.append('<input class="btn" type="submit" value="Add friend">');
//                 results.append(forma);
//
//             }else{
//                 toastr.error('Cannot delete user.');
//             }
//         }
//     });
// }

$(document).on('submit', '#formaFriendRequests', function(e){
    e.preventDefault();
    var results = $('#resultsDiv');
    $.ajax({
        url: "/friends/getFriendRequests",
        dataType: "json",
        type: "GET",
        success: function(data){
            var friends = (data instanceof Array) ? data : [data];
            results.empty();
            if(friends.length==0){
                results.append('<h4>No friend requests</h4>');
            }
            $.each(friends, function(idx, friend) {
                results.append('<h4 id="'+friend.id+'">'+friend.firstName+' '+friend.lastName+'</h4><p>'+friend.email+'</p><br>');
                var forma =$('<form action="" class="form-horizontal" id="formaPrihvatiPrijatelja"></form>');
                var hidden = $('<input id="hiddenPrihvati" type="hidden" value="' + friend.id + '">');
                forma.append(hidden);
                forma.append('<input class="btn" type="submit" value="Accept request">');
                results.append(forma);
                var forma1 =$('<form action="" class="form-horizontal" id="formaOdbiPrijatelja"></form>');
                var hidden1 = $('<input id="hiddenOdbi" type="hidden" value="' + friend.id + '">');
                forma1.append(hidden1);
                forma1.append('<input class="btn" type="submit" value="Decline request">');
                results.append(forma1);

            });

        }
    });


});

$(document).on('submit', '#formaPrihvatiPrijatelja', function(e){
    e.preventDefault();
    var id = document.getElementById("hiddenPrihvati").value;
    $.ajax({
        url: "/friends/acceptFriendRequest/" + id,
        dataType: "text",
        type: "GET",
        success: function(data){
            if(data=="accepted") {
                toastr.success('Accepted friend request');
                $('#formaPrihvatiPrijatelja').remove();
                $('#formaOdbiPrijatelja').remove();
                var child = document.getElementById(id);
                // $('#resultsDiv').removeChild(child);

            } else
                toastr.error('Error accepting');
        }
    });

});
$(document).on('submit', '#formaOdbiPrijatelja', function(e){
    e.preventDefault();
    var id = document.getElementById("hiddenOdbi").value;
    $.ajax({
        url: "/friends/declineRequest/" + id,
        dataType: "text",
        type: "GET",
        success: function(data){
            if(data=="deleted") {
                toastr.success('Deleted friend request');
                $('#formaOdbiPrijatelja').remove();
                $('#formaPrihvatiPrijatelja').remove();
                var child = document.getElementById(id);
                // $('#resultsDiv').removeChild(child);

            } else
                toastr.error('Error accepting');
        }
    });

});
