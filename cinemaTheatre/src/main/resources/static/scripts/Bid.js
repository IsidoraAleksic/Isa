var action="create";
var idBid;
/*
Url = {
    get get(){
        var vars= {};
        if(window.location.search.length!==0)
            window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value){
                key=decodeURIComponent(key);
                if(typeof vars[key]==="undefined") {vars[key]= decodeURIComponent(value);}
                else {vars[key]= [].concat(vars[key], decodeURIComponent(value));}
            });
        return vars;
    }
};*/
/*$(document).ready ( function(){
    var pathname = window.location.href;
    if(pathname.indexOf("bidId") > -1){
        action="update";
        bidId=Url.get.bidId;
        updateBid(bidId);
        //getBid(bidId);
    }
});*/

function openModalBidUpdate(id,idGuestBid,bidId) {
    idBid=bidId;
    action="update";
    $("#adId").val(id);
    $("#idGuestBid").val(idGuestBid);
    $("#bidModal").modal();

}
function openModalBidCreate(id) {
    action="create";
    $("#adId").val(id);
    $("#bidModal").modal();

}
function chooseMethodBid(){
    if(action == "create"){
        makeBid();
    }else{
        updateBid();
    }
}
function makeBid() {
    $.ajax({
        url: "/bid",
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "adId": $("#adId").val(),
            "idGuestBid": "",
            "priceBid": $("#priceBid").val()
        }),
        success: function (data) {
            $('#bidModal').modal('hide');
        }
    });
}

function updateBid(){
    $.ajax({
        url: "/bid/"+idBid,
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "adId": $("#adId").val(),
            "idGuestBid":  $("#idGuestBid").val(),
            "priceBid": $("#priceBid").val()
        }),
        success: function(data) {
            $('#bidModal').modal('hide');
        }
    });
}