function userTickets() {
    var results = $('#resultsDiv');
    var leftPane = $('#userInformation');
    var searchDiv = $('#searchCinemaTheaterDiv');
    var infoDiv = $('#listCinemaTheaterDiv');
    var sortDiv = $('#sortByDiv');

    $.ajax({
        url: "/reserve/tickets",
        success: function (data) {
            var tickets = data instanceof Array ? data : [data];
            results.empty();
            leftPane.empty();
            searchDiv.empty();
            infoDiv.empty();
            sortDiv.empty();
            showTickets(tickets);

        }
    });
}

function showTickets(tickets) {
    var results = $('#resultsDiv');
    results.empty();
    if (tickets.length == 0)
        results.append('<h1>No reserved tickets</h1>');
    else {
        $.each(tickets, function (idx, ticket) {
            var div = $('<div></div>');
            var span = $('<div id = "' + ticket.id + '"></div>');
            span.append('<p><b>' + ticket.projection.name + '</b></p><p>'+ticket.projection.date+'</p> <p>'+ticket.projection.time+'</p>');
            span.append('<button class="btn" onclick="cancelProjection(' + ticket.id + ')">Cancel projection</button>');
            div.append(span);
            results.append(div);
        });
    }

}

function cancelProjection(idT){
    var start = Date.now().toString();
    var span = $('#'+idT+'');

    $.ajax({
        url: "/reserve/removeTicket/" + idT + "/" + start,
        dataType: "text",
        type: "GET",
        success: function (data) {
            if(data=="30")
                toastr.error("Cannot cancel less than 30min before.");
            else if(data=="1")
                toastr.error("This ticket is for a friend.");
            else {
                span.html("");
                toastr.success("Reservation canceled");
            }


        }

    });

}