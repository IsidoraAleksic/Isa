$(document).ready ( function(){
    $.ajax({
        url: "/notification/userId",
        dataType: "json",
        type: "GET",
        success: function(data){
            var notifications = data;
            var content = "";
            for (var notification in notifications) {
                content+=createNotificationDiv(notifications[notification]);
            }
            $("#notificationNumber").empty().append(notifications.length);
            $("#notifications").empty().append(content);

        }
    });
});

function createNotificationDiv(notification) {
    var content = "";
    content+="<p>"+notification.topic +":" + notification.message+"</p>";
    return content;
}