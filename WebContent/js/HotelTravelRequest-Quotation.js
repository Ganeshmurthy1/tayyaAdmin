$(function () {
	
	$("select#country").change(function(){
		var hotel_cities_list = [];
		var hotel_names_list=[];
		var selectedCountry = $("#country option:selected").val();
		console.log("selectedCountry---------------"+selectedCountry)
		$("#hotelcityname").val("");
		$("#hotelName").val("");
		
		$.ajax({
			//Action Name
			url :"getHotelCityList",	
			dataType : "json",
			data : {
				country :selectedCountry
			},
			success : function(data, textStatus, jqXHR) {
				for (var i = 0; i < data.hotelCitylist.length; i++) {
					hotel_cities_list.push(data.hotelCitylist[i]);
				}
				if(hotel_cities_list.length>0){
					$("#hotelcityname").autocomplete(
							{
			 				source :hotel_cities_list ,
								select: function (event, ui) {	
									$("#hotelName").val("");
	            					var city = ui.item.value;
	            				 
	            		        	     $.ajax({
	            		        	    	 url :"getHotelNameList",	
	            		        				dataType : "json",
	            		        				data : {
	            		        					city :city
	            		        				},
	            		        		 success: function(data, textStatus, jqXHR) {
	            		        			 hotel_names_list=[];
	            		        			 for (var i = 0; i < data.hotelnameslist.length; i++) {
	            		        				 hotel_names_list.push(data.hotelnameslist[i]);
	            		     				}
	            		        			 console.log("hotel_names_list---------------------------"+hotel_names_list);
	            		        				if(hotel_names_list.length>0){
	            		        		 	  $("#hotelName").autocomplete({
	            		        		 		  source: hotel_names_list
	            		        		 		  });	
	            		        				}
	            		        		 	  }

	            		        		 });
								},
	            		        		 error:function(jqXhr, textStatus, errorThrown){
	            		        			 console.log("Error---"+errorThrown+"-------Status----------"+textStatus);
	            		        	     }
							});	 
			 }
				 
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
		
		
	});
});

