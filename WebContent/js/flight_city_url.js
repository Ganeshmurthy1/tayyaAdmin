$(function () {
	var flightCityList =[];
	var cityMap =[];
	$.ajax({
		url:'airPortCityList'/*$("#flightCityUrl").val()*/,	
		type: 'GET', 
		dataType: 'json',  
		success: function(data) {
			var cityList=data.airportCityData.airCityList;
			/*console.log("cityList----"+cityList);*/
			for (var i = 0; i < cityList.length; i++) {
				flightCityList.push(cityList[i].city+","+cityList[i].country+",("+cityList[i].airport_code+")");
				 var cityObj ={"key":cityList[i].airport_code,"value":cityList[i].city+","+cityList[i].country+",("+cityList[i].airport_code+")"}
				 cityMap.push(cityObj);
			}
			flightCityList=Array.from(new Set(flightCityList));
			/*console.log("flightCityList----"+flightCityList);*/
			cityMap=Array.from(new Set(cityMap));
			 
		 },
		error:function(jqXhr, textStatus, errorThrown){
			console.log("Error---"+errorThrown+"-------Status----------"+textStatus);
		}
	}); 

	$("#origin").autocomplete({
		source : function(request, response) {
			var matcher = new RegExp(
					$.ui.autocomplete
					.escapeRegex(request.term),
			"i");
			response($.grep(flightCityList, function(item) {
				return matcher.test(item);

			}));
		} 
	,
	 select: function( event , ui ) {
          $.map(cityMap, function(value, key) {
		    if(value.value==ui.item.label){
				console.log("value---"+value.value+"--------key----------"+value.key);
				$("#originCode").val(value.key);
			  }
		  
		});  
       }
	
	});
	
	$("#destination").autocomplete({
		source : function(request, response) {
			var matcher = new RegExp(
					$.ui.autocomplete
					.escapeRegex(request.term),
			"i");
			response($.grep(flightCityList, function(item) {
				return matcher.test(item);

			}));
		}
	,
	 select: function( event , ui ) {
          $.map(cityMap, function(value, key) {
		    if(value.value==ui.item.label){
				console.log("value---"+value.value+"--------key----------"+value.key);
				$("#destinationCode").val(value.key);
			  }
		  
		});  
       }
	
	});
});
