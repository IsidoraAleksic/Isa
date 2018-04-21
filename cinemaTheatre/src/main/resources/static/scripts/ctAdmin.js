$(document).ready(function(){
	loadCT();
});

function home(){
	$(location).attr('href','homeRegistered.html');
}

$(document).on("click",".delete", function(e){
	e.preventDefault();
	var confirmed = confirm("Are you sure?");
	if (confirmed){
		var url = $(this).attr("href");
		tr_parent = $(this).closest("tr");
		$.ajax({
			url: url,
			type: "DELETE",
			success: function(){
				tr_parent.remove();
			}
		});
	}
});

function loadCT(){
	
	$("#middle").empty();
	$("#ct_id").empty();
	$.ajax({
	url: "/authenticate/getUser",
	dataType: "json",
	type: "GET",
	success: function(data){
		var id = data.id;
		console.log(id);
		$.ajax({
			url: "/admin/" + id,
			dataType: "json",
			type: "GET",
			success: function(ctData){
				$("#middle").html('<button id=>Add</button>');
				$('#middle').html('<br><form id="form"></form>');
				$('#form').html('<br><label>Name: </label><input type="text" class="form-control" id="editName" placeholder="Name" value="'+ctData.name+'"><br>'
				+'<br><label>Address: </label><input type="text" class="form-control" id="editAddress" placeholder="Address" value="'+ctData.address+'"><br>'
				+'<br><label>Description: </label><input type="text" class="form-control" id="editDescription" placeholder="Description" value="'+ctData.description+'"><br>'
				+'<input class="button" type="submit" value="Edit">'
				);
				$('<p>').attr({
					type: 'hidden',
					id: 'ctid',
					name: 'ctid',
				}).appendTo('#ct_id');
				$('#ctid').text(ctData.id.toString());
			},
			error: function(){
				alert('Unauthorized access');
				$(location).attr('href', '/index.html')
			}
			
		});
		
		
	}
	});

}

$(document).on('submit','#form', function(e){
	e.preventDefault();
	
	var id = $('#ctid').text();
	var name = document.getElementById('editName').value;
	var address = document.getElementById('editAddress').value;
	var description = document.getElementById('editDescription').value;
	var ok = true;
	if(!name || !address || !description){
		ok = false;
		alert('All fields must be filled');
	}
	if(ok){
		$.ajax({
			type: "PUT",
			url: "/ct/"+id,
			contentType: "application/json",
			dataType: "text",
			data: JSON.stringify({
				"id": id,
				"name": name,
				"address": address,
				"description": description
			}),
			success: function(data){
				if(data === "ok"){
					alert("Information updated.")
				} else (
					alert('Something went wrong.')
				)
			}
		});
	}
	
});

function highlightRow(row){
	if(!$(row).hasClass("header")){
  		$(".highlighted").removeClass("highlighted");
    	$(row).addClass("highlighted");
    	sync($(row));
    }
}

function sync(item){
	oznaka = item.find(".first").html()
	naziv = item.find(".second").html()
	id = item.find(".third").html()
	$(".first").val(oznaka);
	$(".second").val(naziv);
	$(".third").val(id);
}

$(document).on("click", "tr", function(event) {
	highlightRow(this)
});

function halls(){
	
	$("#middle").empty();
	
	$.ajax({
		url: '/hall/halls/' + $('#ctid').text(),
		type: "GET",
		success: function(data) {
			$("#middle").append('<button type="button" class="btn btn-primary" onclick="addH()">'
			+'Add Hall</button>'
			+'<br><table id="hallTable" class="table"></table>');
			$("#hallTable").html('<tr class="header"><th>Name</th><th>Rows</th><th>Seats per row</th><th>Delete</th></tr>');
			for(var i = 0; i < data.length; i++){
				$("#hallTable").append('<tr>'
				+'<td class="name first">'+data[i].name+'</td>'
				+'<td class="rows second">'+data[i].rows+'</td>'
				+'<td class="cols third">'+data[i].cols+'</td>'
				+'<td><a class="delete" href="/hall/'+data[i].id+'"><img src="images/remove.gif"/></a>'
				+'</tr>');
			}
				
		}
	});
	
}

function projections(){

	$("#middle").empty();
	
	$.ajax({
		url: '/projections/ct/' +  $('#ctid').text(),
		type: "GET",
		success: function(data) {
			
			$("#middle").append('<button type="button" class="btn btn-primary" onclick="addProj()">'
			+'Add Projection</button><br>'
			+'<table id="projectionsTable" class="table"></table>');
			$("#projectionsTable").html('<tr class = "header"><th>Hall Name</th><th>Date</th><th>Time</th><th>Delete</th></tr>');
			for(var i = 0; i < data.length; i++){
				$("#projectionsTable").append('<tr>'
				+'<td class="name first">'+data[i].hall_name+'</td>'+
				'<td class="rows second">'+data[i].date+'</td>'
				+'<td class="cols third">'+data[i].time+'</td>'
				+'<td><a class="delete" href="/projections/'+data[i].id+'"><img src="images/remove.gif"/></a>'
				+'</tr>');
			}
			$("#projectionsTable").css("css/ctAdmin.css");	
		}
	});
	
}

function addH(){
	$("#middle").empty();
	$("#middle").html('<form id="hallForm"></form>');
	$("#hallForm").html('<label>Name: </label><input type="text" class="form-control" id ="hallName" placeholder="Name"><br>'
	+'<label>Rows: </label><input type="text" class="form-control" id ="hallRows" placeholder="Rows" onkeyup="this.value=this.value.replace(/[^\\d]/,\'\')"><br>'
	+'<label>Seats per row: </label><input type="text" class="form-control" id ="hallCols" placeholder="Cols" onkeyup="this.value=this.value.replace(/[^\\d]/,\'\')"><br>'
	+'<input class="button" type="submit" value="Add">');
	
}

$(document).on("submit","#hallForm", function(e){
	e.preventDefault();
	
	var hName = document.getElementById('hallName').value;
	var hCols = document.getElementById('hallCols').value;
	var hRows = document.getElementById('hallRows').value;
	var ct_id = $("#ctid").text();
	$.ajax({
		type: "POST",
		url: "/hall/add",
		contentType: "application/json",
		dataType: "text",
		data: JSON.stringify({
			"name": hName,
			"rows": hRows,
			"cols": hCols,
			"ctId": ct_id
		}),
		success: function(data){
			alert('Hall added')
		}
	});
	
});

function addProj(){
	
	$("#middle").empty();
	$("#middle").html('<form id="projForm"></form>');
	$('#projForm').html('<label>Name: </label><input type="text" class="form-control" id="projName" placeholder="Name"><br>'
	+'<label>Actors: </label><input type="text" class="form-control" id="projActors" placeholder="Actors"><br>'
	+'<label>Genre: </label><input type="text" class="form-control" id="projGenre" placeholder="Genre"><br>'
	+'<label>Director: </label><input type="text" class="form-control" id="projDirector" placeholder="Director"><br>'
	+'<label>Duration: </label><input type="text" class="form-control" id="projDuration" onkeyup="this.value=this.value.replace(/[^\\d]/,\'\')" placeholder="Duration"><br>'
	+'<label>Description: </label><input type="text" class="form-control" id="projDescription" placeholder="Description"><br>'
	+'<label>Price: <input type="text" class="form-control" id ="projPrice" placeholder="Price" onkeyup="this.value=this.value.replace(/[^\\d]/,\'\')"><br>'
	+'<label>Hall: </label> <select id="hallOp"></select><br>'
	+'<label>Date: </label> <input type="date" id="projDate" data-date-format="YYYY-MM-DD"><br>'
	+'<label>Time: </label><input type="text" class="form-control" id="projTime" placeholder="HH:00:00"><br>'
	+'<div class="dropzone"><div class="info"></div></div><br>'
	+'<script type="text/javascript" src="scripts/imgur.js"></script>'
    +'<script type="text/javascript" src="scripts/upload.js"></script>'
	);
	$.ajax({
		url: '/hall/halls/' + $('#ctid').text(),
		type: "GET",
		success: function(data) {
			for(var i = 0; i < data.length; i++){
				$("#hallOp").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
			}
		}
	});
	
	$("#middle").append('<br><button type="button" onclick="addProjection()">Submit</button>');
	
}


function addProjection(){
	var pName = document.getElementById('projName').value;
	var pActors = document.getElementById('projActors').value;
	var pGenre = document.getElementById('projGenre').value;
	var pDirector = document.getElementById('projDirector').value;
	var pDuration = document.getElementById('projDuration').value;
	var pImage = document.getElementById('imagelink').value;
	var pDescription = document.getElementById('projDescription').value;
	var pPrice = document.getElementById('projPrice').value;
	var pDate = document.getElementById('projDate').value;	
	var pTime = document.getElementById('projTime').value;
	if(/^(\d{2}:\d{2}:\d{2})$/.test(pTime)){
	} else{
		alert("Time format: XX:XX");
		return
	}
	var ctid = $('#ctid').text();
	var tmp = document.getElementById("hallOp");
	var hallid = tmp.options[tmp.selectedIndex].value;
	$.ajax({
        type: "POST",
		url: "/projections/add",
		contentType: "application/json",
		dataType: "text",
		data: JSON.stringify({
			"name": pName,
			"actors": pActors,
			"genre": pGenre,
			"director": pDirector,
			"duration": pDuration,
			"imagePath": pImage,
			"description": pDescription,
			"price": pPrice,
			"date": pDate,
			"time": pTime,
			"ct_id": ctid,
			"hall_id": hallid
		}),
		success: function(data){
			alert('Projection added')
		}
	})
}

function statistics(){
	
	$("#middle").empty();
	
	$("#middle").html('<p>Please select the desired time unit and the amount of iterations you wish to see the statistics for</p>');
	
	$("#middle").append('<form id="statisticsForm"></form>');
	$("#statisticsForm").html('<input type="radio" name="dwm" value="day">Day<br>'
	+'<input type="radio" name="dwm" value="week">Week<br>'
	+'<input type="radio" name="dwm" value="month">Month<br>'
	+'<input type="text" id="statText" onkeyup="this.value=this.value.replace(/[^\\d]/,\'\')"><br>'
	);
	$("#middle").append('<br><button class="btnStat">Get Statistics</button><br><br><div id="canvDiv"></div>');
	$("#canvDiv").append('<canvas id="graph" width="60%" height="400"></canvas>');
}

$(document).on("click", ".btnStat", function(e){
	e.preventDefault();
	var ctid = $('#ctid').text();
	var sForm = document.getElementById("statisticsForm");
	var s = "";
	for(var i = 0; i < sForm.length; i++){
		if(sForm[i].checked) {
			s = sForm[i].value;
			break;
		}
	}
	var num = document.getElementById("statText").value;
	
	$.ajax({
		url: "/projections/stats/"+ctid+"/"+s+"/"+num,
		type: "GET",
		success: function(data){
			var values = [];
			for(var i = 0; i < data.length; i++)
				values.push({X: data[i].x, Y: data[i].y});
			statGraph(values);
		}
	});
	
	
});

function statGraph(data){
	var graph;
	var xPadding = 30;
	var yPadding = 10;
	function getMaxY() {
		var max = 0;

		for(var i = 0; i < data.values.length; i ++) {
			if(data.values[i].Y > max) {
				max = data.values[i].Y;
			}
		}

		// max += 10 - max % 10;
		return max;
	}

	function getMinY(){
		var min = 2147483647;
	  
		for(var i = 0; i < data.values.length; i ++) {
			if(data.values[i].Y < min) {
				min = data.values[i].Y;
			}
			}
	return min;
	}

	function getXPixel(val) {
		return ((graph.width() - xPadding) / data.values.length) * val + (xPadding * 1.5);
	}

	function getYPixel(val) {
		return graph.height()/2 - (((graph.height()/2 - 10 - yPadding) / getMaxY()) * val) - yPadding;
	}

	graph = $('#graph');
	var c = graph[0].getContext('2d');            

	c.lineWidth = 2;
	c.strokeStyle = '#000000';
	c.font = 'italic 8pt sans-serif';
	c.textAlign = "center";

	c.beginPath();
	c.moveTo(xPadding, 0);
	c.lineTo(xPadding, graph.height()/2 - yPadding);
	c.lineTo(graph.width(), graph.height()/2 - yPadding);
	c.stroke();


	for(var i = 0; i < data.values.length; i ++) {
		c.fillText(data.values[i].X, getXPixel(i), graph.height()/2 - yPadding + 20);
	}

	c.textAlign = "right";
	c.textBaseline = "middle";

	for(var i = 0; i <= getMaxY(); i += getMaxY()/10) {
		c.fillText(Math.round(i), xPadding - 10, getYPixel(i));
	}

	c.strokeStyle = '#ff0000';

	c.beginPath();
	c.moveTo(getXPixel(0), getYPixel(data.values[0].Y));
	for(var i = 1; i < data.values.length; i ++) {
	  c.lineTo(getXPixel(i), getYPixel(data.values[i].Y));
	}
	c.stroke();

	c.fillStyle = '#000000';

	for(var i = 0; i < data.values.length; i ++) {  
		c.beginPath();
		c.arc(getXPixel(i), getYPixel(data.values[i].Y), 4, 0, Math.PI * 2, true);
		c.fill();
	}
}