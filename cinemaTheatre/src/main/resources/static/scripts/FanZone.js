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
function getAllBids() {
    $.ajax({
        url: "/bid/allBids",
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
    content+="<div class=\"card merch-container\"><h1 class=\"card-title\"> "+merch.priceMerchandise+"<small class=\"text-muted\">$</small></h1><img class='merch-image' src="+merch.imageMerchandise+">" + "  " + merch.nameMerchandise + "<br/>" + merch.description+"  <input type=\"submit\" onclick=\"reserveMerchs("+merch.id+")\" class=\"btn btn-primary btn-md\" value=\"Rezervisi\"></div>";

    return content;
}
function createBidDiv(bid){
    var content = "";
    content+="<div class=\"card bid-container\"><h1>"+ "Ad:" + bid.adId + "Price of bid:" + bid.priceBid + "</h1><input type=\"submit\" onclick=\"deleteBid("+bid.id+")\" class=\"btn btn-primary btn-md\" value=\"Delete Bid\"></div>";

    return content;
}
function createAdDiv(ad){
    var content = "";
    var merch = ad.merchandise;
    content+="<div class=\"card merch-container\"><h1 class=\"card-title\"> "+ad.priceAd+"<small class=\"text-muted\">$</small></h1><img class='merch-image' src="+ad.imageAd+">" + "  " + ad.nameAd + "<br/>" + ad.description+"<button type=\"button\"  class=\"btn btn-primary\" onclick=\"openModalBid("+ad.id+")\">Make Bid</button> "+
        "<button type=\"button\" class=\"btn btn-primary\" onclick=\"openUpdateAd("+ad.id+")\">Update Ad</button><br/>" +
        "<button type=\"button\" class=\"btn btn-primary\" onclick=\"deleteAd("+ad.id+")\">Delete Ad</button></div>";

    return content;

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
            window.open(data)
        }
    });
}
function openModalBid(id) {
    $("#adId").val(id);
    $("#bidModal").modal();
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
            window.open(data);
            bidId=data;
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
            window.open(data);
            $(location).attr('href', 'FanZone.html');
        }
    });
}