 
	$(document).ready(
			function() {

				var email_list = [];
				var agents_list = [];
				  var user_list = []; 
				  console.log("------companyUserId------"+$("#companyUserId").val());
				 $.ajax({
					//Action Name
					url :"fetchAllUserIdsUnderSuperUserForJson",
					dataType : "json",
					data : {
					 parent_company_user_id : $("#companyUserId").val(),
						email : $("#email").val()
					},

					success : function(data, textStatus, jqXHR) {

						var items = data.usersList;
						for (var i = 0; i < data.usersList.length; i++) {
							agents_list.push(data.usersList[i].username/* +","+data.usersList[i].id + "("+data.agentList[i].company_userid+")"  + ","
									+ data.agentList[i].id */);
							 email_list.push(data.usersList[i].email);
						
						}
						 
						 user_list=agents_list;
						  console.log("------user_list------"+user_list);
						 userlist(user_list);
							$("#Email").autocomplete(
									{
					 				source : function(request, response) {
											var matcher = new RegExp('^'
													+ $.ui.autocomplete
															.escapeRegex(request.term),
													"i");
											response($.grep(email_list, function(item) {
												return matcher.test(item);

											}));
										}
									});	 
						  },
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
				 
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
		  
		  $('#userIdSearch').focus(function() {
			   $(this).val('');
			  });
		  $('#orderId').focus(function() {
			   $(this).val('');
			  });
		  $('#twodpd2').focus(function() {
			   $(this).val('');
			  });
		  $('#twodpd1').focus(function() {
			   $(this).val('');
			  });
	});