$(function() {
    tgt = "/" + window.location.href.split("ct=")[1];
	$.ajax({
        url: tgt,
        success: function(data){
			$("#dataTable").append('<tr class="header"><th>Name</th><th>Address</th><th>Description</th><th>Ambient Rating</th><th>View page</th></tr>');
            for(i = 0; i < data.content.length;i++){
				newct = '<tr>'
				+'<td>'+data.content[i].name+'</td>'
				+'<td>'+data.content[i].address+'</td>'
				+'<td>'+data.content[i].description+'</td>'
				+'<td>'+data.content[i].ambient+'</td>'
				+'<td><a href="ctProfile.html?id='+data.content[i].id+'"><img src ="/images/edit.gif"/></a></td>'
				+'</tr>';
				
                $("#dataTable").append(newct);
            }
        }
    });
	
	//#.ajax({
	//	url: ""
	//})

});