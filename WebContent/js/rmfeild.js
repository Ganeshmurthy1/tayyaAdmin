 var corporateID = $('#companyRole').val();  
 var iscorID=$("#companyId").val();  
 var url = '';
 
 
 function isPriceKey(event,textbox){   
	    if ((event.which != 46 || $(textbox).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	               return false;
	    event.preventDefault();
	  }
	  }
  function isAgeKey(event,textbox){ 
 	// event.preventDefault();
 	 console.log($(textbox).val());
 	// console.log('event',event);
	    if ( (event.which < 48 || event.which > 57)||($(textbox).val() >= 13)) {
	               return false;
	   
	  }
	  }
 
 $.ajax({
	   url : 'resources/ApiResourceURLcredential.properties',
	   type : 'GET',
	   dataType : 'json',
	   success : function(response) {
		   url = response.ApiResourceURL;
	    console.log("ApiURL",url);
	   }, 
	   error : function(xhr, status, error) {
	   },complete: function(){
	        }

	  });
if(corporateID=="true") {
$("#firstName").autocomplete({ 
    source: function (request, response) {  
         $.ajax({ 
        	// url: "https://dev.tayyarah.com/devtayyarahapi/employee/list?companyid="+iscorID,
        	 url: url+"employee/list?companyid="+iscorID,
             dataType : "json",
             data: request,
             success: function (data) { 
            	   response(data);
            	   console.log(data);
            	   response($.map(data, function(item) {
                       return {
                           label: item.firstName,
                           value: item.firstName,
                           lastName: item.lastName, 
                           id:item.id, 
                           data:item
                       } 
            	   }));
            	   
             }
         });
    },
    select: function (event, ui) {
    	 console.dir(ui);
         $('#firstName').val(ui.item.firstName);  
         $('#lastName').val(ui.item.lastName); 
         
    }, 
     change: function (event, ui) { console.log("uidddddd",ui);
     console.log( url+"employee/empdetailsById?id="+ui.item.id);
     $.ajax({ 
         //url: "https://dev.tayyarah.com/devtayyarahapi/employee/empdetailsById?id="+ui.item.id,  
    	 url: url+"employee/empdetailsById?id="+ui.item.id,
         dataType: "json",  
         success: function(datas) {
            console.log(datas);  
            if(datas.rmDataListDetails.department!=undefined || datas.rmDataListDetails.department==""){
           	 $('#rmDepartment').val(datas.rmDataListDetails.department); 
           }
            if(datas.rmDataListDetails.empCode!=undefined || datas.rmDataListDetails.empCode==""){
           	 $('#rmempCode').val(datas.rmDataListDetails.empCode); 
           }
            if(datas.rmDataListDetails.costCenter!=undefined || datas.rmDataListDetails.costCenter==""){
            	 $('#rmcostCenter').val(datas.rmDataListDetails.costCenter); 
            }
            
            if(datas.rmDataListDetails.approverName!=undefined || datas.rmDataListDetails.approverName==""){
           	 $('#rmapproverName').val(datas.rmDataListDetails.approverName); 
           }
            if(datas.rmDataListDetails.location!=undefined || datas.rmDataListDetails.location==""){
           	 $('#rmlocation').val(datas.rmDataListDetails.location); 
           }
            if(datas.rmDataListDetails.trNumber!=undefined || datas.rmDataListDetails.trNumber==""){
           	 $('#rmtrNumber').val(datas.rmDataListDetails.trNumber); 
           }
            if(datas.rmDataListDetails.bussinessUnit!=undefined || datas.rmDataListDetails.bussinessUnit==""){
           	 $('#rmbussinessUnit').val(datas.rmDataListDetails.bussinessUnit); 
           }
            
            if(datas.rmDataListDetails.projectCode!=undefined || datas.rmDataListDetails.projectCode==""){
              	 $('#rmprojectCode').val(datas.rmDataListDetails.projectCode); 
              }
            
            if(datas.rmDataListDetails.reasonForTravel!=undefined || datas.rmDataListDetails.reasonForTravel==""){
              	 $('#rmreasonForTravel').val(datas.rmDataListDetails.reasonForTravel); 
              }
              if(datas.rmDataListDetails.billNonBill!=undefined || datas.rmDataListDetails.billNonBill==""){
               	 $('#rmbillNonBill').val(datas.rmDataListDetails.billNonBill); 
               } 
            
         },
         error: function(datas) { 
           console.log(datas);
         }
       }); 
    } 
}); 
}
  