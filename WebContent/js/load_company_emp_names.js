$(function() {
	var company_list = [];
	var agents_list = [];
	var user_list = []; 
	var cityMap=[];
	var userMap=[];
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
				var cityObj ={"key":data.records[i].companyid,"value":data.records[i].companyname}
				cityMap.push(cityObj);
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
					var matcher = new RegExp(''
							+ $.ui.autocomplete
							.escapeRegex(request.term),
					"i");
					response($.grep(company_list, function(item) {
						return matcher.test(item);

					}));
				},select : function(event, ui) {
					$.map(cityMap, function(value, key) {
						if(value.value==ui.item.label){
							console.log("value---"+value.value+"--------key----------"+value.key);
							$("#companyId").val(value.key);
						}

					});  
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
				agents_list.push(data.agentList[i].username /*+ "("+data.agentList[i].company_userid+")"  + ","
								+ data.agentList[i].id*/);
				var userObj ={"key":data.agentList[i].id,"value":data.agentList[i].username}
				userMap.push(userObj);
			}
			console.log("------agents_list------"+agents_list);
			user_list=agents_list;
			console.log("------user_list------"+user_list);
			userlist(user_list,userMap);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus);
		}
	});

});

function userlist(userlist,userMap)
{
	if(userlist.length>0){
		$("#agentName").autocomplete(
				{
					source : function(request, response) {
						var matcher = new RegExp(''
								+ $.ui.autocomplete
								.escapeRegex(request.term),
						"i");
						response($.grep(userlist, function(item) {
							return matcher.test(item);

						}));
					}
				,
				select : function(event, ui) {
					$.map(userMap, function(value, key) {
						if(value.value==ui.item.label){
							console.log("value---"+value.value+"--------key----------"+value.key);
							$("#userId").val(value.key);
						}

					});  
				}
				});	 
	}

}


