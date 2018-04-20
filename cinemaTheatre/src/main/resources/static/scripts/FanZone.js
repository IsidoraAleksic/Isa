var userr;
$(document).ready(function () {
    getUser();
});


function getUser(){
$.ajax({
    url: "/authenticate/getUser",
    dataType: "json",
    type: "GET",
    success: function (data) {
        userr = data;
        var content2="";
        var content="";
        if(userr.role == "GUEST"){
            content+="<button type=\"button\" class=\"btn btn-primary\" onclick=\"openAdPage()\">New Ad</button>";

            content2+="<li><a data-toggle=\"pill\" href=\"#menu3\" onclick=\"getAllBidsByGuestId()\">My Bids</a></li>\n" +
                "<li><a data-toggle=\"pill\" href=\"#menu4\" onclick=\"getAllBids()\">All Bids for my ads</a></li>";
            pillsBids(content2);
            return $("#NewAdMerch").append(content);

        }else if(userr.role == "ADMINFZ"){
            content+="<button type=\"button\" class=\"btn btn-primary\" onclick=\"openMerchPage()\">New Merch</button>";
            return $("#NewAdMerch").append(content);
        }

    }
});
}
function pillsBids(content2){
    content=content2;
    return $("#pills").append(content);
}
function getAllMerchs(){
    $.ajax({
        url: "/merchandise/allMerchandise",
        dataType: "json",
        type: "GET",
        success: function(data){
            var merchs = data;
            var content = "";

            for (var merch in merchs) {
                content+=createMerchDiv(merchs[merch]);
            }
            $("#zvanicna-prodavnica").empty().append(content);

        }
    });
}
function getAllAds(){
    $.ajax({
        url: "/advert/allAds",
        dataType: "json",
        type: "GET",
        success: function(data){
            var ads = data;
            var content = "";
            for (var ad in ads) {
                content+=createAdDiv(ads[ad]);
            }

            $("#oglasi").empty().append(content);

        }
    });
}
function getAllBids(){
    $.ajax({
        url: "/bid/allBidsForUsersAds",
        dataType: "json",
        type: "GET",
        success: function(data){
            var bids = data;
            var content = "";
            for (var bid in bids) {
                content+=createBidForMyAdsDiv(bids[bid]);
            }
            $("#ponudeOglasi").empty().append(content);

        }
    });
}
function getAllBidsByGuestId() {
    $.ajax({
        url: "/bid/guestId",
        dataType: "json",
        type: "GET",
        success: function(data){
            var bids = data;
            var content = "";
            for (var bid in bids) {
                content+=createBidDiv(bids[bid]);
            }

            $("#ponude").empty().append(content);

        }
    });
}

function createMerchDiv(merch) {
    var content = "";
    content+="<div class=\"card merch-container\"><h1 class=\"card-title\"> "
        +merch.priceMerchandise+"<small class=\"text-muted\">$</small>" +
        "</h1><img class='merch-image' src="+merch.imageMerchandise+">" + "  " + merch.nameMerchandise + "<br/>"
        + merch.description+"  <input type=\"submit\" onclick=\"reserveMerchs("+merch.id+")\" class=\"btn btn-primary btn-md\" value=\"Reserve\">"+
        "<input type=\"submit\" id=\"deleteM\" onclick=\"deleteMerch("+merch.id+")\" class=\"btn btn-primary btn-md\" value=\"Delete Merch\">"+
        "<input type=\"submit\" id=\"updateM\" onclick=\"openUpdateMerch("+merch.id+")\" class=\"btn btn-primary btn-md\" value=\"Update Merch\"></div>";

        /*if(userr.role!="ADMINFZ"){
            document.getElementById('deleteM').style.display='none';
            document.getElementById('updateM').style.display='none';
        }*/
    return content;
}

function createBidDiv(bid){
    var content = "";
    content+="<div class=\"card bid-container\"><h2>"+ "Ad:" + bid.ad.id + "Price of bid:" + bid.priceBid + "</h2>"+"<input type=\"submit\" onclick=\"deleteBid("+bid.id+")\" class=\"btn btn-primary btn-md\" value=\"Delete Bid\">" +
        "<input type=\"submit\" onclick=\"openModalBidUpdate("+bid.ad.id+","+bid.idGuestBid+","+ bid.id+")\" class=\"btn btn-primary btn-md\" value=\"Update Bid\"></div>";

    return content;
}

function createBidForMyAdsDiv(bid){
    var content = "";
    content+="<div class=\"card bid-container\"><h2>"+ "Ad:" + bid.ad.id + "Price of bid:" + bid.priceBid + "</h2>"+"<button type=\"button\" class=\"btn btn-primary\" onclick=\"acceptBid("+bid.idGuestBid+")\">Accept Bid</button>" +
        "<button type=\"button\" class=\"btn btn-primary\" onclick=\"rejectBid("+bid.idGuestBid+")\">Reject Bid</button></div>";

    return content;
}


function createAdDiv(ad){
    var content = "";
    content+="<div class=\"card merch-container\"><h1 class=\"card-title\"> "+ad.priceAd+"<small class=\"text-muted\">$</small></h1><img class='merch-image' src="+ad.imageAd+">" + "  " + ad.nameAd + "<br/>" + ad.description+"<button type=\"button\"  class=\"btn btn-primary\" onclick=\"openModalBidCreate("+ad.id+")\">Create Bid</button> "+
        "<button type=\"button\" class=\"btn btn-primary\" onclick=\"openUpdateAd("+ad.id+")\">Update Ad</button><br/>" +
        "<button type=\"button\" class=\"btn btn-primary\" onclick=\"deleteAd("+ad.id+")\">Delete Ad</button>" +
        "<button type=\"button\" class=\"btn btn-primary\" onclick=\"acceptAd("+ad.user.id+")\">Accept Ad</button>" +
        "<button type=\"button\" class=\"btn btn-primary\" onclick=\"rejectAd("+ad.user.id+")\">Reject Ad</button></div>";
    return content;

}
function acceptAd(adUserId){
    $.ajax({
        url: "/notification",
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "receiverId":adUserId,
            "message": "Your ad has been approved",
            "topic": "Ad"
        }),
        success: function(data) {
            window.open(data)
        }
    });
}
function rejectAd(adUserId){
    $.ajax({
        url: "/notification",
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "receiverId":adUserId,
            "message": "Your ad has been rejected",
            "topic": "Ad"
        }),
        success: function(data) {
            window.open(data)
        }
    });
}
function acceptBid(bidUserId){
    $.ajax({
        url: "/notification",
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "receiverId":bidUserId,
            "message": "Your bid has been approved",
            "topic": "Bid"
        }),
        success: function(data) {
            window.open(data)
        }
    });
}
function rejectBid(bidUserId){
    $.ajax({
        url: "/notification",
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "receiverId":bidUserId,
            "message": "Your bid has been rejected",
            "topic": "Bid"
        }),
        success: function(data) {
            window.open(data)
        }
    });
}
function reserveMerchs(merchId) {

    $.ajax({
        url: "/reservationMerch",
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "merchId": merchId,
            "userId": ""
        }),
        success: function(data) {
            content="";
            if(data=="Successfully created merch reservation."){
               $(location).attr('href', 'FanZone.html');
            }else{
                alert("That merchandise has been already reserved by another user!");
            }

        }
    });
}

function deleteBid(bidId) {
    $.ajax({
        url: "/bid/"+bidId,
        contentType: "application/json",
        dataType: "text",
        type: "DELETE",
        success: function (data) {
            $(location).attr('href', 'FanZone.html');
        }
    });
}
function deleteMerch(merchId){
    $.ajax({
        url: "/merchandise/"+merchId,
        contentType: "application/json",
        dataType: "text",
        type: "DELETE",
        success: function (data) {
            $(location).attr('href', 'FanZone.html');
        }
    });

}
