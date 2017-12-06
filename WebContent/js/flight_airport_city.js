$(function () {
	var flightCityList =[];
	$.ajax({
		url:'airportCities',	
		type: 'GET', 
		dataType: 'json',  
		success: function(data) {
			alert(data);
			for (var i = 0; i < data.airportCityList.length; i++) {
				flightCityList.push(data.airportCityList[i].city+","+data.airportCityList[i].country+","+data.airportCityList[i].airport_code);
			}
			console.log("-------flightCityList----------"+flightCityList);
		},
		error:function(jqXhr, textStatus, errorThrown){
			console.log("-------flightCityList----------"+flightCityList);
		}
	}); 

	$("#origin").autocomplete({
		source : function(request, response) {
			var matcher = new RegExp(''
					+ $.ui.autocomplete
					.escapeRegex(request.term),
			"i");
			response($.grep(flightCityList, function(item) {
				return matcher.test(item);

			}));
		} 
	
	
	
	

	});
});
