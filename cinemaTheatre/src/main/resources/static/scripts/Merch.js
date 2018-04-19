var action="create";
var merchId;
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
    if(pathname.indexOf("merchId") > -1){
        action="update";
        merchId=Url.get.merchId;
        getMerch(merchId);
    }
});
function getMerch(merchId){
    $.ajax({
        url: "/advert/"+merchId,
        dataType: "json",
        type: "GET",
        success: function(data) {
            $("#userId").val(data.user.id);
            $("#nameMerchandise").val(data.nameMerchandise);
            $("#description").val(data.description);
            $("#imageMerchandise").val(data.imageMerchandise);
            $("#priceMerchandise").val(data.priceMerchandise);
        }
    });

}
function openUpdateMerch(id){
    $(location).attr('href', 'Merch.html?merchId='+id);
}
function chooseMethodMerch(){
    if(action == "create"){
        makeMerch();
    }else{
        updateMerch(merchId);
    }

}
function makeMerch(){
    //e.preventDefault();
    var objFile = $('#icon');
    var file = objFile[0].files[0];
    $.ajax({
        url: "/merchandise",
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "userId": "",
            "nameMerchandise": $("#nameMerchandise").val(),
            "description":  $("#descriptionMerch").val(),
            "priceMerchandise":$("#priceMerchandise").val(),
            "imageMerchandise": ""
        }),
        success: function(data) {
            sendIcon(data,file);
        }
    });

}
function updateMerch(merchId) {
    $.ajax({
        url: "/merchandise/"+merchId,
        contentType: "application/json",
        dataType: "text",
        type: "POST",
        data: JSON.stringify({
            "userId": $("#userId").val(),
            "nameMerchandise": $("#nameMerchandise").val(),
            "description":  $("#descriptionMerch").val(),
            "priceMerchandise":$("#priceMerchandise").val(),
            "imageMerchandise": ""
        }),
        success: function(data) {
            $(location).attr('href', 'FanZone.html')
        }
    });
}
function sendIcon(id, file) {
    $.ajax({
        async : false,
        type : "POST",
        url : "/merchandise/addImage/",
        contentType : false,
        data: JSON.stringify({
            "file": file
        }),
        processData: false,
        success: function(data) {
            $(location).attr('href', 'FanZone.html')
        }
    });
}