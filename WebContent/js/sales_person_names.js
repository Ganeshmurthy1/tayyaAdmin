$(document).ready(
		function() {
			var sales_person_list = [];
			var cityMap =[];
			$.ajax({
				url :"salesPersonNames",
				dataType : "json",
				data : {
					createdByUserId : $("#createdUserId").val()
					 
				},

				success : function(data, textStatus, jqXHR) {
						for (var i = 0; i < data.salesPersonRecords.length; i++) {
						//agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")");
							sales_person_list.push(data.salesPersonRecords[i].username+"("+data.salesPersonRecords[i].email+")"/*+"-"+data.salesPersonRecords[i].id*/);
							 var cityObj ={"key":data.salesPersonRecords[i].id,"value":data.salesPersonRecords[i].username+"("+data.salesPersonRecords[i].email+")"}
	        				 cityMap.push(cityObj);
						
						
						}
					console.log("------sales_person_list------"+sales_person_list);
					if(sales_person_list.length>0){
						$("#sales_person_name").autocomplete(
								{
				 				source : function(request, response) {
										var matcher = new RegExp(''
												+ $.ui.autocomplete
														.escapeRegex(request.term),
												"i");
										response($.grep(sales_person_list, function(item) {
											return matcher.test(item);

										}));
									},
									 select: function(event , ui ) {
				      			           $.map(cityMap, function(value, key) {
				          				    if(value.value==ui.item.label){
				          						console.log("value---"+value.value+"--------key----------"+value.key);
				          						$("#salesPerssonUserId").val(value.key);
				          					  }
				          				  
				          				});  
				      			        }
								});	 
				 }
					 
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
	 
			
		  });

  
	  
 
 
  