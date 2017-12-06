$(document).ready(
			function() {
				var  userNames = [];
			  $.ajax({
					url :"getUserNames",
					dataType : "json",
					 success : function(data, textStatus, jqXHR) {
						for (var i = 0; i < data.usersList.length; i++) {
							userNames.push(data.usersList[i].username);
						  }
						console.log(userNames);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
				$("#userName").autocomplete(
						{
							source : function(request, response) {
								var matcher = new RegExp(''
										+ $.ui.autocomplete
												.escapeRegex(request.term),
										"i");
								response($.grep(userNames, function(item) {
									return matcher.test(item);

								}));
							},
						 select: function(event , ui ) {
	          				   /* if(value.value==ui.item.label){
	          						console.log("value---"+value.value+"--------key----------"+value.key);
	          						$("#techSupportId").val(value.key);
	          					  }*/
	          				  
	          				 
	      			        } 
						});
						 
				 
				
			  });
	
	 