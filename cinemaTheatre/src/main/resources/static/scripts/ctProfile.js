$(function() {
	tgt = "/ct/" + window.location.href.split("id=")[1];
    $.ajax({
        url: tgt,
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
			
        }

	});
	
	function getClass(bool){
		return bool ? "" : " checked";
	}
	
});

