 
	$.getJSON('hotel-city.json', {
		format: "json"
	})
	.error(function() { 
		//console.log("error");
	$.getJSON('resources/Apicredential.properties', {

		format: "json"
	})
	.done(function( data ) {		
		//console.log(data);
		var apiUrl = data.ApiURL +"cities/search?";
		$.ajax({
			url : apiUrl,
			dataType : 'json',
			type : 'GET',
			async : true,
			statusCode : {
				404 : function(data) {
					// alert(404);
				},
				200 : function(data) {

					citylist = [];
					citymap = [];
					$.each(data.areas, function( i, value) {   
					citylist.push(value.name); 
						citymap[value.name] = value.id;
					
					});

					$("#hotelCitySearch").autocomplete({
						source: function( request, response ) {
							var matcher = new RegExp('^'+$.ui.autocomplete.escapeRegex( request.term ), "gi" );
							response( $.grep( citylist, function( item ){	
								var itemavailavle = item;					
								return matcher.test( item );
							}).slice(0, 15));
						},
						select: function (event, ui) {
							
							var value = ui.item.value;					
							var citycode = citymap[ui.item.value];
							$('#childCount').focus();
							$('#citycode1').val(citycode);
							 
						}

					});  

				}
			},
			error : function(jqXHR, status, errorThrown) {
				//  alert('error');
			}
		});
	})

	})
	.done(function( data ) {		
		citylist = [];
		citymap = [];
		$.each(data.areas, function( i, value) {        
			citylist.push(value.name); 
			citymap[value.name] = value.id;
		});
		$("#hotelCitySearch").autocomplete({
			source: function( request, response ) {
				var matcher = new RegExp('^'+ $.ui.autocomplete.escapeRegex( request.term ), "gi" );
				response( $.grep( citylist, function( item ){	
					var itemavailavle = item;					
					return matcher.test( item );
				}).slice(0, 15));
			},
			select: function (event, ui) {				
				var label = ui.item.label;
				var value = ui.item.value;					
				var citycode = citymap[ui.item.value];
				$('#datain').focus();
				$('#cityname').val(ui.item.value);
				$('#citycode').val(citycode);
				$('#citycode1').val(citycode);
				 
			}
		});  		
	});
 
	 


