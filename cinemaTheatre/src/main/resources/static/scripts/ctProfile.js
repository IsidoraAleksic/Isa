$(document).ready(function() {
	tgt = window.location.href.split("id=")[1];
    $.ajax({
        url: "/ct/" + tgt,
        success: function(data){
			
			$('#name').text(data.name);
			$('#address').text(data.address);
			$('#description').text(data.description);
			$('#rating').text(data.ambient.toString());
			var sn = Math.round(data.ambient);
			var stars = "";
			for(i = 1; i < 6; i++){
				var tmp = "<span class=\"fa fa-star" + getClass(i > sn) + "\"></span>";
				stars = stars + tmp;
			}
			$('#stars').append(stars);
			
			$.ajax({
				url: "/reserve/speed/" + tgt,
				dataType: "json",
				type: "GET",
				success: function(data2){
					console.log(data2.length);
					$("#speed").html('<table id="speedTable"></table>');
					$("#speedTable").html('<tr><th>Movie name</th><th>Hall name</th><th>Time</th><th>Row</th><th>Seat</th><th>Price</th><th>Reserve</th></tr>');
					for(j = 0; j < data2.length; j++){
						$("#speedTable").append('<tr>'
						+'<td>'+data2[j].movie_name+'</td>'
						+'<td>'+data2[j].hall_name+'</td>'
						+'<td>'+data2[j].time+'</td>'
						+'<td>'+data2[j].row+'</td>'
						+'<td>'+data2[j].col+'</td>'
						+'<td>'+data2[j].price+'</td>'
						+'<td><a class="speed" href="/reserve/speed/reserve/'+data2[j].id+'"><img src="/images/edit.gif"/></a></td>'
						+'</tr>');
						
					}
				}
	
			});
			
        }
		
	});
	
	$(document).on("click", ".speed", function(e){

		e.preventDefault();
		var confirmed = confirm("Are you sure you wish to reserve?");
		if (confirmed){
		var url = $(this).attr("href");
		var tr_parent = $(this).closest("tr");
			$.ajax({
				url: url,
				type: "GET",
				success: function(){
					tr_parent.remove();
					location.reload();
				}
			})
		}
	});
	
	function getClass(bool){
		return bool ? "" : " checked";
	}
	
});

