$(function() {
    tgt = "/" + window.location.href.split("ct=")[1];
	$.ajax({
        url: tgt,
        success: function(data){

            console.log(data.content);
            for(i = 0; i < data.content.length;i++){
                newct =  "<tr class=\"bottom\"><td rowspan=\"5\" class=\"pic\">"
					+"<a href=\"ctProfile.html?id="+data.content[i].id+"\">"
					+"<img src=\"images/placeholder.png\"/></a></td>"
                    +"<td class=\"irs\">Name: "+data.content[i].name+"</td></tr>"
                    +"<tr><td class=\"irs\">Address: "+data.content[i].address+"</td></tr>"
                    +"<tr><td rowspan=\"2\">Description: "+data.content[i].description+"</td></tr>"
                    +"<tr></tr>"
                    +"<tr><td class=\"irs\">Rating: "+data.content[i].ambient+"</td></tr>"
                    +"<tr><td class=\"extra\" colspan=\"2\"></td></tr>";
                $("#dataTable").append(newct);
            }
        }
    });
	
	//#.ajax({
	//	url: ""
	//})

});