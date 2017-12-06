$(document).ready(
			function() {
				var techSupportList = [];
				var techHeadList = [];
				var techMap =[];
			  $.ajax({
					url :"getAllTechnicalSupportList",
					dataType : "json",
					 success : function(data, textStatus, jqXHR) {
						 
						for (var i = 0; i < data.agentList.length; i++) {
							var userRole=data.agentList[i].userrole_id;
							if(userRole.techHead==true){
								techHeadList.push(data.agentList[i].username+"("+data.agentList[i].email+")");
							}
							if(userRole.techSupport==true){
								techSupportList.push(data.agentList[i].username+"("+data.agentList[i].email+")");
							}
							 var techObj ={"key":data.agentList[i].id,"value":data.agentList[i].username+"("+data.agentList[i].email+")"}
							 techMap.push(techObj);
						  }
						 
						 
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
				$("#assignTo").autocomplete(
						{
							source : function(request, response) {
								var matcher = new RegExp(''
										+ $.ui.autocomplete
												.escapeRegex(request.term),
										"i");
								response($.grep(techSupportList, function(item) {
									return matcher.test(item);

								}));
							},
						 select: function( event , ui ) {
	      			           $.map(techMap, function(value, key) {
	          				    if(value.value==ui.item.label){
	          						console.log("value---"+value.value+"--------key----------"+value.key);
	          						$("#techSupportId").val(value.key);
	          					  }
	          				  
	          				});  
	      			        } 
						});
						$("#assignby").autocomplete(
						{
							source : function(request, response) {
								var matcher = new RegExp(''
										+ $.ui.autocomplete
												.escapeRegex(request.term),
										"i");
								response($.grep(techHeadList, function(item) {
									return matcher.test(item);

								}));
							},
							 select: function(event,ui) {
		      			           $.map(techMap, function(value, key) {
		          				    if(value.value==ui.item.label){
		          						console.log("value---"+value.value+"--------key----------"+value.key);
		          						$("#techHeadId").val(value.key);
		          					  }
		          				  
		          				});  
		      			        }
						});
				 
				
			  });
	
	$(document).ready(function() {
		  $('#assignTo').focus(function() {
		   $(this).val('');
		  });
		   
	});
	$(document).ready(function() {
		  $('#assignby').focus(function() {
		   $(this).val('');
		  });
		   
	});

	 
	 $(document).ready(function() 
	 { 
		 $("#asssignDate").datepicker({
			 dateFormat: "dd-mm-yy"  
			 
		 }).datepicker("setDate", new Date());
		 $("#startToWorkDate").datepicker({
			 dateFormat: "dd-mm-yy"  
			 
		 }).datepicker("setDate", new Date());
		 $("#workFinishDate").datepicker({
			 dateFormat: "dd-mm-yy"  
			 
		 }).datepicker("setDate", new Date());  
		 $("#asssignDate, #startToWorkDate, #workFinishDate").datepicker("option", "prevText", ""); 
		 $("#asssignDate, #startToWorkDate, #workFinishDate").datepicker("option", "nextText", ""); 
		 });
		 
	 
