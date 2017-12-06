  $(function() {
		  var  reportType=document.getElementById('reportTypeHidden').value;
		   document.getElementById('reportType').value = reportType;
		  	 
	 });
	$(document).ready(
			function() {

				var company_list = [];
				var agents_list = [];
				  var user_list = []; 
				 

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
							/* company_list.push(data.records[i].companyname +"("+data.records[i].company_userid+")"+","
									+ data.records[i].companyid); */
							company_list.push(data.records[i].companyname);
						}
						console.log(company_list);
						//response(items);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});

				$("#companyName").autocomplete(
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
											/* user_list.push(data.user_records[i].username + "("+data.user_records[i].company_userid+")"  + ","
													+ data.user_records[i].id); */
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
				
				
				$.ajax({
					//Action Name
					url :"AgentsListUnderSuperUser",
					dataType : "json",
					data : {
					 parent_company_user_id : $("#companyUserId").val(),
						email : $("#email").val()
					},

					success : function(data, textStatus, jqXHR) {
 					for (var i = 0; i < data.agentList.length; i++) {
 						agents_list.push(data.agentList[i].username);
							/* agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")"  + ","
									+ data.agentList[i].id); */
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
				
			  });
	
	 function userlist(userlist)
			{
		 
		
		 if(userlist.length>0){
				$("#agentName").autocomplete(
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
 