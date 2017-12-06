 
        $(function () {
        	var hotelCityList =[];
        	var cityMap =[];
        	 $.ajax({
        		 url:$("#cityUrl").val(),	
        		 type: 'GET', 
        		 dataType: 'json',  
        		 success: function(data) {
        			 for (var i = 0; i < data.areas.length; i++) {
        				   hotelCityList.push(data.areas[i].name);
        				   var cityObj ={"key":data.areas[i].id,"value":data.areas[i].name}
        				      cityMap.push(cityObj);
        					 
        			 }
        			 
        			 
        			 
        		 },
        		 error:function(jqXhr, textStatus, errorThrown){
        			 console.log("Error---"+errorThrown+"-------Status----------"+textStatus);
        	     }
        		 }); 
        	 
        	  $("#city").autocomplete({
      					source : function(request, response) {
      						var matcher = new RegExp('^'
      								+ $.ui.autocomplete
      								.escapeRegex(request.term),
      						"i");
      						response($.grep(hotelCityList, function(item) {
      							return matcher.test(item);

      						}));
      					},
        	  select: function( event , ui ) {
                  $.map(cityMap, function(value, key) {
                   if(value.value==ui.item.label){
                    console.log("value---"+value.value+"--------key----------"+value.key);
                    $("#citycode").val(value.key);
                   }

                  });  
                 }
      					 
        });
        });
  