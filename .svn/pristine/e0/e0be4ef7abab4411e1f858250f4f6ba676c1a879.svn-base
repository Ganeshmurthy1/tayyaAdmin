   var sales_person_list = [];
   var cityMap =[];
   var pageNameList=[];
   var pageNameMap=[];
   $.ajax({
    url :"getAllUserListByCompanyUserId",
    dataType : "json",
    /*data : {
     createdByUserId : $("#createdUserId").val()

    },*/

    success : function(data, textStatus, jqXHR) {
     for (var i = 0; i < data.agentList.length; i++) {
      //agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")");
      sales_person_list.push(data.agentList[i].username+"("+data.agentList[i].email+")"/*+"-"+data.salesPersonRecords[i].id*/);
      var cityObj ={"key":data.agentList[i].id,"value":data.agentList[i].username+"("+data.agentList[i].email+")"}
      cityMap.push(cityObj);


     }
     console.log("------sales_person_list------"+sales_person_list);
     if(sales_person_list.length>0){
      $("#searchByCreatedUser").autocomplete(
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
         select: function( event , ui ) {
          $.map(cityMap, function(value, key) {
           if(value.value==ui.item.label){
            console.log("value---"+value.value+"--------key----------"+value.key);
            $("#searchByCreatedUserId").val(value.key);
            var indexval = value.value.indexOf('(');
            var username = value.value.substring(0,indexval);
            
            $("#username").val(username);
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
 

   $.ajax({
	    url :"getBrowsingPageNameList",
	    dataType : "json",
	   

	    success : function(data, textStatus, jqXHR) {
	     for (var i = 0; i < data.pageNameList.length; i++) {
	    	 pageNameList.push(data.pageNameList[i].pageName);
	      var  pageNameObj ={"key":data.pageNameList[i].id,"value":data.pageNameList[i].pageName}
	      pageNameMap.push(pageNameObj);


	     }
	     console.log("------pageNameList------"+pageNameList);
	     if(pageNameList.length>0){
	      $("#searchByPageName").autocomplete(
	        {
	         source : function(request, response) {
	          var matcher = new RegExp(''
	            + $.ui.autocomplete
	            .escapeRegex(request.term),
	          "i");
	          response($.grep(pageNameList, function(item) {
	           return matcher.test(item);

	          }));
	         },
	         select: function( event , ui ) {
	          $.map(pageNameMap, function(value, key) {
	           if(value.value==ui.item.label){
	            console.log("value---"+value.value+"--------key----------"+value.key);
	            $("#searchByPageId").val(value.key);
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
	 
   
   
   


 