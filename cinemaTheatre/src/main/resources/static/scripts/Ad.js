var action="create";
var adId;
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
};
$(document).ready ( function(){
    var pathname = window.location.href;
    if(pathname.indexOf("adId") > -1){
        action="update";
        adId=Url.get.adId;
        getAd(adId);
    }
});

function makeAd(){
    $.ajax({
        url: "/advert",
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "nameAd": $("#nameAd").val(),
            "description": $("#description").val(),
            "imageAd": "",
            "userId": "",
            "priceAd": $("#priceAd").val(),
            "dateEndOfBids": $("#dateEndOfBids").val()
        }),
        success: function(data) {
            uploadImageAdd(data);
        }
    });

}

function openUpdateAd(id){
    $(location).attr('href', 'Ad.html?adId='+id);
}
function chooseMethod(){
    if(action == "create"){
        makeAd();

    }else{
        updateAd(adId);
    }

}
function getAd(adId){
    $.ajax({
        url: "/advert/"+adId,
        dataType: "json",
        type: "GET",
        success: function(data) {
            $("#userId").val(data.user.id);
            $("#nameAd").val(data.nameAd);
            $("#description").val(data.description);
            $("#imageAd").val(data.imageAd);
            $("#priceAd").val(data.priceAd);
            $("#dateEndOfBids").val(data.dateEndOfBids);
        }
    });

}
function updateAd(adId){
    $.ajax({
        url: "/advert/"+adId,
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "nameAd": $("#nameAd").val(),
            "description": $("#description").val(),
            "imageAd": $("#imageAd").val(),
            "userId": $("#userId").val(),
            "priceAd": $("#priceAd").val(),
            "dateEndOfBids": $("#dateEndOfBids").val()
        }),
        success: function(data) {
            $(location).attr('href', 'FanZone.html')
        }
    });
}

function deleteAd(adId){
    $.ajax({
        url: "/advert/"+adId,
        contentType: "application/json",
        dataType: "text",
        type: "DELETE",
        success: function(data) {
            window.open(data);
            $(location).attr('href', 'FanZone.html')
        }
    });

}
function uploadImageAdd(adId){
    $.ajax({
        url: "/advert/"+adId+"/image/",
        contentType: false,
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "nameAd": $("#nameAd").val(),
            "description": $("#description").val(),
            "imageAd": "",
            "userId": "",
            "priceAd": $("#priceAd").val(),
            "dateEndOfBids": $("#dateEndOfBids").val()
        }),
        success: function(data) {
            $(location).attr('href', 'FanZone.html')
        }
    });
}
function openFanZone(){
    $(location).attr('href', 'FanZone.html')
}
function openMerchPage(){
    $(location).attr('href', 'Merch.html')
}
function openAdPage(){
    $(location).attr('href', 'Ad.html')
}
