$(document).ready(
  function() {
   var sales_person_list = [];
   var cityMap =[];
   $.ajax({
    url :"getAllUserListByCompanyUserId",
    dataType : "json",
    success : function(data, textStatus, jqXHR) {
     for (var i = 0; i < data.agentList.length; i++) {
      //agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")");
      sales_person_list.push(data.agentList[i].username+"("+data.agentList[i].email+")"/*+"-"+data.salesPersonRecords[i].id*/);
      var cityObj ={"key":data.agentList[i].id,"value":data.agentList[i].username+"("+data.agentList[i].email+")"}
      cityMap.push(cityObj);
     }
     console.log("------sales_person_list------"+sales_person_list);
     if(sales_person_list.length>0){
      $("#userName").autocomplete(
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
            $("#userId").val(value.key);
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

$(document).ready(function() {
	  
	  
	  $('#userName').focus(function() {
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