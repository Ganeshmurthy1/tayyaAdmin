$(document).ready(function() {
				var airlinesList = [];
				 $.ajax({
						url :"airlinesJson",
						dataType : "json",
						success : function(data, textStatus, jqXHR) {
							for (var i = 0; i < data.airlist.length; i++){
								var airlineName=data.airlist[i].airlinename;
								if(airlineName!=null) 
									airlinesList.push(airlineName);
								
								
							}
							airlinesList = Array
							.from(new Set(
									airlinesList));
						 },error : function(jqXHR, textStatus, errorThrown) {
								//console.log(textStatus);
							}
				 });
				 
				 
				 
				 
				 $("#airline").autocomplete(
							{
								source : function(request, response) {
									var matcher = new RegExp(''
											+ $.ui.autocomplete
													.escapeRegex(request.term),
											"i");
									response($.grep(airlinesList, function(item) {
										return matcher.test(item);

									}));
								},
							 
							});
				 
				
			});

				
