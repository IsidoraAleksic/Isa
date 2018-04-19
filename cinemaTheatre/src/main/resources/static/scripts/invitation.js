$(document).ready(function () {
    $.ajax({
        url: "/authenticate/getUser",
        dataType: "json",
        type: "GET",
        success: function (data) {
            var user = data;
            if (user == null) {
                $.ajax({
                    url: "/reserve/seeReservationAfterLogin",
                    dataType: "text",
                    type: "GET",
                    success: function (data) {
                        if (data == "no") {
                            toastr.error("Error");
                            open(window.location, '_self').close();
                        }
                    }
                });
            }
        }
    });

});


function acceptInvitation(){
    $.ajax({
        url: "/reserve/acceptInvitation",
        dataType: "text",
        type: "GET",
        success: function (data) {
            if (data == "ok") {
                toastr.success("Invitation accepted");


            }else
                toastr.error("Failed to accept");
            open(window.location, '_self').close();
        }
    });
}

function declineInvitation(){
    $.ajax({
        url: "/reserve/declineInvitation",
        dataType: "text",
        type: "GET",
        success: function (data) {
            if (data == "ok") {
                toastr.success("Invitation declined");

            }
            else
                toastr.error("Failed to decline");
            open(window.location, '_self').close();

        }
    });
}



