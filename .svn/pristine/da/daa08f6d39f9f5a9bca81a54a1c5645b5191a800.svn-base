$(document).ready(
		function() {
			 var configNamesList = [];
			 
			 var currurl=window.location.pathname.split( '/' );
				var lastpath=currurl[2];
				console.log("newUrl",lastpath);

				if(lastpath !="getAllCorporate"){
			var company_list = [];
			var agents_list = [];
			  var user_list = []; 
			 
			  var companyEmailList = [];

			$.ajax({
				//Action Name
				url :"CompanyListUnderSuperUser",
				dataType : "json",
				data : {
				 parent_company_user_id : $("#companyUserId").val(),
					email : $("#email").val()
				},

				success : function(data, textStatus, jqXHR) {

					var items = data.records;
					for (var i = 0; i < data.records.length; i++) {
						 
						company_list.push(data.records[i].companyname);
						 companyEmailList.push(data.records[i].email);
						
					}
					 
					if(companyEmailList.length>0){
						$("#companyEmail").autocomplete(
								{
				 				source : function(request, response) {
										var matcher = new RegExp('^'
												+ $.ui.autocomplete
														.escapeRegex(request.term),
												"i");
										response($.grep(companyEmailList, function(item) {
											return matcher.test(item);

										}));
									}
								});	 
				 }
					
				 
					//response(items);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});

			$("#search").autocomplete(
					{

						source : function(request, response) {
							var matcher = new RegExp('^'
									+ $.ui.autocomplete
											.escapeRegex(request.term),
									"i");
							response($.grep(company_list, function(item) {
								return matcher.test(item);

							}));
						},
						select : function(event, ui) {
							var label = ui.item.label;
							var company_userid = ui.item.value;
							console.log(company_userid);
						if(company_userid!=null){ 
							$.ajax({
								//Action Name
								url : "UserListUnderCompany",
								dataType : "json",
								data : {
									 company_user_id : company_userid 
								},

								success : function(data, textStatus, jqXHR) {
									 user_list=[];
									console.log("--data---------"+data);
									
									var items = data.user_records;
									
									for (var i = 0; i < data.user_records.length; i++) {
										//user_list.push(data.user_records[i].username + "("+data.user_records[i].company_userid+")");
										user_list.push(data.user_records[i].username);
									}
									console.log(user_list);
									userlist(user_list);
								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(textStatus);
								}
							});
						}
						
						 
						 }
					});
				}
			$("#toCreate").autocomplete(
					{
						source : function(request, response) {
							var matcher = new RegExp('^'
									+ $.ui.autocomplete
											.escapeRegex(request.term),
									"i");
							response($.grep(company_list, function(item) {
							 
								return matcher.test(item);

							}));
						}
					});
			  
			$.ajax({
				//Action Name
				url :"fetchCompanyConfigNamesForJson",
				dataType : "json",
				data : {
				 parent_company_user_id : $("#companyUserId").val(),
					email : $("#email").val()
				},

				success : function(data, textStatus, jqXHR) {
						if(data.companyConfigList)
							{
								for (var i = 0; i < data.companyConfigList.length; i++) {
								//agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")");
								configNamesList.push(data.companyConfigList[i].configname);
							}
					}
					console.log("------configNamesList------"+configNamesList);
					if(configNamesList.length>0){
						$("#configname").autocomplete(
								{
				 				source : function(request, response) {
										var matcher = new RegExp('^'
												+ $.ui.autocomplete
														.escapeRegex(request.term),
												"i");
										response($.grep(configNamesList, function(item) {
											return matcher.test(item);

										}));
									}
								});	 
				 }
					 
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
			
		/*	$.ajax({
				//Action Name
				url :"CompanyConfigNames",
				dataType : "json",
				data : {
				 parent_company_user_id : $("#companyUserId").val(),
					email : $("#email").val()
				},

				success : function(data, textStatus, jqXHR) {

					var items = data.agentList;
					for (var i = 0; i < data.agentList.length; i++) {
						//agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")");
						agents_list.push(data.agentList[i].username);
					}
					console.log("------agents_list------"+agents_list);
					 user_list=agents_list;
						console.log("------user_list------"+user_list);
					 userlist(user_list);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
			
			*/
			
		  });

 function userlist(userlist)
		{
	 if(userlist.length>0){
			$("#userIdSearch").autocomplete(
					{
	 				source : function(request, response) {
							var matcher = new RegExp('^'
									+ $.ui.autocomplete
											.escapeRegex(request.term),
									"i");
							response($.grep(userlist, function(item) {
								return matcher.test(item);

							}));
						}
					});	 
	 }
	  
}
 
 
 
$(document).ready(function() {
	  $('#companyEmail').focus(function() {
	   $(this).val('');
	  });
	  $('#search').focus(function() {
		   $(this).val('');
		  });
	  $('#phone').focus(function() {
		   $(this).val('');
		  });
	  $('#pan_number').focus(function() {
		   $(this).val('');
		  });
	  $('#configName').focus(function() {
			$(this).val('');
		});
	  $('#configname').focus(function() {
			$(this).val('');
		});
 });
 
 