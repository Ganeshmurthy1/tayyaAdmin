<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.*" %>




<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Notification</title> 
<link rel="stylesheet" href="css/alert.css">

<style>
#ui-datepicker-div{

position: relative;

}
.pad10px{
padding-left:30px ;}


</style>
<%-- 
<% 
String  notificationId=	request.getParameter("notifyId");
String  title=	request.getParameter("title");
String  fromDate=	request.getParameter("fromDate");
String  toDate=	request.getParameter("toDate");
String  preferTime=	request.getParameter("preferTime");
String  comments=	request.getParameter("comments");
 
%> --%>


 



</head>
<body >
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="row">
				<div class="col-sm-3 clearfix pull-left">
					<h3>Edit Notification</h3>
				</div>
				
			</div>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
			 
        			<div class="row">
			<div class="col-sm-12">
				<!-- createHoteOfflineBooking -->
				<form action="" method="post"
					class="form-horizontal" name="myForm" id="myFormReset">
					<div id="myfform"> 
					<input type="hidden" id="notiCreatedUserId" name="userId"
								value="<s:property value="#session.User.id" />"> 
								<input  name="companyId"
								type="hidden" id="notiCreatedCompanyId"
								value="<s:property value="#session.User.Companyid" />">
								<input type="hidden" id="Notifyid" name="Notifyid" value="" > 
			<!-- <input type="hidden" id="title" name="title" value="">
			<input type="hidden" id="comments" name="comments" value=""> -->
								
												 	 
												  <div class="form-group">
														<label for="currency" class="col-sm-2 control-label"> 
															Notification Title </label>
														<div class="col-sm-8">
															<input type="text" class="form-control input-sm" value=""
																required="required" name="description" id="description" placeholder="Notification Title"
																>
														</div>
												 </div>
												
												 
												 <div class="form-group">
														<label for="currency" class="col-sm-2 control-label"> 
															Select Notification Type </label>
															
															<div class="col-sm-8 checkbox notify-check">
															 
					
<label class="checkbox-inline">
  <input type="checkbox" id="admin" name="admin"  class="chk  " value="false"  > Admin
</label>										 
  <label class="checkbox-inline">
<!--   commented by basha because presently we are not sending any mails for edit notification  when we want enable notification edit email please uncomment bellow code --> 
  <input type="checkbox"  disabled="disabled" id="email" name="email" class="chk " value="false" > Email
  <!-- <input type="checkbox" onclick="return false" readonly /> Email -->
</label>

<label class="checkbox-inline">
<!-- <input type="checkbox" onclick="return false" /> SMS -->
<!-- Enable this input tag when SMS IS need to be clickable  -->

 <input type="checkbox" disabled="disabled" id="sms" name="sms" class="chk" value="false">SMS
</label>
 
 
 </div> 
 </div>
 
 <div class="clearfix">
<div class="col-sm-8 col-sm-offset-2">
 
 <div class="notify-esabox clearfix">
 
 <div class="col-sm-6 fromdate">
 <div class="form-group">
		<label class=" control-label"> 
			From Date </label> 
		 
			 <input type="text" name="Notification Start Date" class="form-control input-sm" required="required" value=" " id="fromDate" placeholder="Select Notification Start Date" />
</div>
 </div>
 <div class="col-sm-6 todate">
  <div class="form-group">
		<label class="  control-label"> To Date
			  <font size="1" color="red">*(Select time for Preferred time)</font></label> 
			 <input type="text" name="Notification End Date" class="form-control input-sm" id="toDate" required="required" value=" " placeholder="Select Notification End Date" />
		<input type="hidden" name="Notification End Date" class="form-control input-sm" id=frmDate required="required" value=" " placeholder="Select Notification End Date" />
		
		</div>
 </div> 
  <div class="col-sm-6 fromdate">
 <div class="form-group">
		<label class=" control-label"> 
			To: </label> 
		 
			 <input type="email" name="Notification Start Date" class="form-control input-sm" required="required" value="" id="alternativeToEmail" placeholder="alternativeToEmail" />

 </div>
 </div>
 <div class="col-sm-6 fromdate">
 <div class="form-group">
		<label class=" control-label"> 
			CC: </label> 
		 
			 <input type="email" name="Notification Start Date" class="form-control input-sm" required="required" value="" id="alternativeCCEmail" placeholder="alternativeCCEmail" />

 </div>
 </div>
  
 </div>
 </div> 	
 
 
 </div>
 
  <div class="form-group clearfix">
								<label for="comments" class="col-sm-2 control-label">Notification
									Prefer Time </label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" <%-- value=" ${notification.timeInterval}" --%>
										required="required" name="timeInterval" id="timeInterval"  value=" "
										placeholder="Select PreferTime" readonly>
								</div>
							</div> 
 	<div class="form-group clearfix">
								<label for="comments" class="col-sm-2 control-label">Notification
									Message </label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" value=""
										required="required" name="comments" id="comments"
										placeholder="Write Message here">
								</div>
							</div> 

					<!-- harsha added colapse ended -->

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button"  onclick="opencustomNotiffyurl()"  
								class="btn btn-primary btn-lg" id="notification">Edit Notification  <span class="pull-right pad10px" id="loading">
							  <img src="images/loginLoader.gif" width="20px" height="20px"/> Please Wait
							</span></button>	
						</div>
					</div>
					
					
						</div>
				</form>
			</div>
			</div>
        
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
		<div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body clearfix">
          <p id="desc">
     
      </p>
         
      </div>
     
    </div>
  </div>
</div>
	
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript" src="js/jquery-ui-timepicker-addon.js"></script>
    
	<script type="text/javascript">
	//Spliting the parameters from the Url Params
	//added by basha split url for gett the values of thats specific custom notification
	function getUrlVars() {
	    var vars = {};
	    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,    
	    function(m,key,value) {
	      vars[key] = value;
	    });
	    return vars;
  	}
	
	//Getting All paramters from the Url
	$(document).ready ( function(){
		var paramId = getUrlVars()["ntId"];
		var paramTitle = decodeURIComponent(getUrlVars()["tTl"]);
		var paramcmnts= decodeURIComponent(getUrlVars()["cmnt"]);
		
		
		/* $(document).ajaxStart(function(){
		    $('#loading').show();
		 }).ajaxStop(function(){
		    $('#loading').hide();
		 }); */
		//added by basha assigning the url params into the form 
		$("#Notifyid").val(paramId);
			$("#description").val(paramTitle);
			$("#comments").val(paramcmnts);
			
		});
	//date picker and time picker is used for selecting the data and times 
	//perevent to select previous date and times by user 
	
	 $('#fromDate').datepicker({
			 //showTimePicker: false,
			 minDate:0,
			 dateFormat: 'dd-mm-yy', 
			 onSelect: function( selectedDate ) {
			    	var date2 = $("#fromDate").datepicker('getDate', '+1d'); 
			  	  date2.setDate(date2.getDate()); 
			  	  $( "#toDate" ).datepicker('setDate', date2);
			  	$( "#toDate" ).datepicker( "option", "minDate", date2 ); 
			    },
			  onClose: function() {
			      $("#toDate").focus();
			  }
			  
		  }).datepicker("setDate", new Date());
	          
	     
 
 $('#toDate').datetimepicker({
	 //timepicker: false,
	 dateFormat: 'dd-mm-yy',
   	 minDate:0,
     onSelect: function(selectedDate,i) { 
         var todate = $(this).val();
         $("#frmDate").val(todate);  
         var date2 = $("#toDate").datepicker('getDate', '+1d'); 
         
         //console.log("date2",date2);
         var cHour = date2.getHours();
         var cMin = date2.getMinutes();
         //var cSec = date2.getSeconds();
         //alert(cHour+ ":" + cMin+ ":" +cSec );
        
         //console.log("dt2",cHour+ ":" + cMin);
         var newtime=cHour+ ":" + cMin;
        // console.log("newtime",newtime);
         $('#timeInterval').val(newtime);
         
     }
 
 });
//added by basha this total functionality is for checkbox for selecting the custom notification type is admin or email or sms or all 
//by depending upon the checkbox from date and todate will display 
$('.notify-esabox').hide();

	
	

  $(".notify-check").each(function(){ 
	   
	 
	$('input[type="checkbox"]').click(function () {
	 
			
		 
		
		
			 
			
		if ($('#admin').prop('checked') && ! $('#email').prop('checked')){
			$(".notify-esabox").show();
	    	$('#admin').change(function(){
		    	  if($(this).prop('checked')){
		            $(this).val('true');
		            //alert($(this)); 
		       		}else{
		            $(this).val('false');
		       		}  
	    	});
	    	 
	    	$(".todate").show();
	    	 $(".fromdate").hide(); 
	    	 $(".alternativeToEmail").hide(); 
	    	 $(".alternativeCCEmail").hide();
	    		
	    		 
	    }else if ($('#email').prop('checked') && ! $('#admin').prop('checked') ){
	    	$(".notify-esabox").show();
	    	$('#email').change(function(){
		    	  if($(this).prop('checked')){
		            $(this).val('true');
		           // alert($(this)); 
		       		}else{
		            $(this).val('false');
		       		}  
	    	});
	    	 
	    	$(".todate").show();
	    	 $(".fromdate").show();   
	    	 $(".alternativeToEmail").show(); 
	    	 $(".alternativeCCEmail").show();
	    		
	    		 
	    }else if ($('#email').prop('checked') && $('#admin').prop('checked') ){
	    	$(".notify-esabox").show(); 
	    	$('#email').change(function(){
		    	  if($(this).prop('checked')){
		            $(this).val('true');
		           // alert($(this)); 
		       		}else{
		            $(this).val('false');
		       		}  
	    	});
	    	$('#admin').change(function(){
		    	  if($(this).prop('checked')){
		            $(this).val('true');
		           // alert($(this)); 
		       		}else{
		            $(this).val('false');
		       		}  
	    	});
	    	
	    	$(".todate").show();
	    	 $(".fromdate").show();   
	    	 $(".alternativeToEmail").show(); 
	    	 $(".alternativeCCEmail").show();
	    		
	    		 
	    }
		else{
	    	
	    	 $(".notify-esabox").hide();
	    	}
	    
	}); 
	 
	}); 
 
//added by basha for getting the api url dynamically from Apicredential.properties file 
//here ajax call will be called by open custom notify
  
  $('#loading').hide();
	function opencustomNotiffyurl() {
		//
		//$('#qnimate').addClass('popup-box-on');

		var finalUrl = 'resources/ApiResourceURLcredential.properties';
		var ApiURL = '';
		$.ajax({
			url : finalUrl,
			type : 'GET',
			dataType : 'json',
			success : function(response) {
				//console.log("bashaUrl", response.ApiURL);
				ApiURL = response.ApiResourceURL;
				//console.log("ApiURL12345", ApiURL);
				//getNotification(ApiURL);
				//ajax call will be calls from this function
				EditNotificationUpdateCall(ApiURL);

			},
			error : function(xhr, status, error) {
				 $('#loading').hide();
			},complete: function(){
				$('#loading').hide();
		      }

		});

	}
  
  
  //added by basha here ajax call will goes with required params
  
	 function EditNotificationUpdateCall(apiUrl){ 
		 

	 			var notificationId=$("#Notifyid").val();
	 			console.log("notificationId",notificationId);
				var userId=$("#notiCreatedUserId").val();
				console.log("userId",userId);
				var companyId=$("#notiCreatedCompanyId").val();
				console.log("companyId",companyId);
				var description=$("#description").val();
				console.log("description",description);
				var comments=$("#comments").val();
				console.log("comments",comments);
				var timeInterval=$("#timeInterval").val();
				console.log("timeInterval",timeInterval)
				var currentUrl=$(location).attr('href');
				 var baseUrl=currentUrl.substr(0,currentUrl.lastIndexOf('/')+1);
	            var homeUrl =baseUrl.concat("home");
	        	var finalUrl = apiUrl+"notification/editNotification"; 
				 var admin = $('#admin').val();	
				console.log("admin",admin)
				 var email = $('#email').val();
				console.log("email",email)
				 if(admin=="true" && email !="true"){
				
					//var fromDate=$("#frmDate").val()+" 00:00";
					  var fromDateDummy=$("#frmDate").val();
					 //console.log("fromDateDummy",fromDateDummy);
					 
					 var fromDateDummyResult=fromDateDummy.split(' ')[0];
					//console.log("fromDateDummyResult",fromDateDummyResult);
					
					 var fromDate=fromDateDummyResult+" 00:00"; 
					 
					 
					 var todateDummy=$("#toDate").val();
					var  todateDummyresult = todateDummy.split(' ')[0];
					 var toDate=todateDummyresult+" 23:59";
					 var alternativeToEmail=$("#alternativeToEmail").val();
					 var alternativeCCEmail=$("#alternativeCCEmail").val();
					
					var toDate=$("#toDate").val()+" 23:59";
					 //console.log("alternativeToEmail",alternativeToEmail);
					 //console.log("alternativeCCEmail",alternativeCCEmail);
					 //console.log("fromDate",fromDate);
					//console.log("toDate",toDate);
					
					 
				 }
				 else if(email=="true" && admin !="true"){
					
					// var toDate=$("#toDate").val()+" 23:59";
					 var todateDummy=$("#toDate").val();
					 var  todateDummyresult = todateDummy.split(' ')[0];
					 var toDate=todateDummyresult+" 23:59";
								
					 var fromDate=$("#fromDate").val()+" 00:00";
					 var alternativeToEmail=$("#alternativeToEmail").val();
					 var alternativeCCEmail=$("#alternativeCCEmail").val();
					// console.log("alternativeToEmail",alternativeToEmail);
					//console.log("alternativeCCEmail",alternativeCCEmail);
					 //console.log("fromDate",fromDate);
					 //console.log("toDate",toDate);
				 }
				 else if(email=="true" && admin=="true"){
					 //var toDate=$("#toDate").val()+" 23:59";
					  var todateDummy=$("#toDate").val();
					  var  todateDummyresult = todateDummy.split(' ')[0];
					  var toDate=todateDummyresult+" 23:59";
								
					 var fromDate=$("#fromDate").val()+" 00:00";
					 var alternativeToEmail=$("#alternativeToEmail").val();
					 var alternativeCCEmail=$("#alternativeCCEmail").val();
					 //console.log("alternativeToEmail",alternativeToEmail);
					//console.log("alternativeCCEmail",alternativeCCEmail);
					 //console.log("fromDate",fromDate);
					 //console.log("toDate",toDate);
					
				 } else{
					 $("#emailModal").modal('show');
			  		   $("#emailModal .modal-body").empty(); 
			  		 $("#emailModal #myModalLabel").empty(); 
			  		     
			        	txt1 = "Select notification Type"; 
			        	$("#emailModal .modal-body").append(txt1);  
				 }
					// alert("Select notification Type")
				
				 if((description==""  || comments=="" ) || (description==null  || comments==null) ){
					 //alert("notidfication title needeed"); 
					 $("#emailModal").modal('show');
			        	$("#emailModal .modal-body").empty(); 
			        	txt1 = "Fill The Required Feilds";
			        	$("#emailModal .modal-body").append(txt1); 
			        	setTimeout(function(){
			        	$('#emailModal').modal('hide');	        		  
			        		}, 1000);
					 }else{
			
				
				 $.ajax({
								    method: "GET",
								    url:finalUrl,
								    headers: {'Content-Type' : 'application/json'},
								    data:{
								    	notificationId:notificationId,
								    	description:description,
								    	fromDate:fromDate,
								    	toDate:toDate,
								    	comments:comments,
								    	timeInterval:timeInterval,
								    	isAdmin:admin,
								    	isEmail:email,
								    	toEmail:alternativeToEmail,
								    	ccEmail:alternativeCCEmail
								    	},
			    	 
								  	success:function(data,status,headers)
									{
								  		$(".modal").hide();
								  		//alert("Notification Created Succesfully");
								  		//console.log(data,status);
								  		if(data.status==='SUCESS'){
								  			/* alert("Notification Created Succesfully"); */
								  			 $("#emailModal").modal('show');
								  		   $("#emailModal .modal-body").empty(); 
								        	txt1 = "Notification Updated Succesfully"; 
								        	$("#emailModal .modal-body").append(txt1); 
								        	setTimeout(function(){
								        		  $('#emailModal').modal('hide');	
								        		  window.location = homeUrl;
								        		  //window.location.reload();								        		  
								        		}, 1000);
								        	
								        	/* 
								        	$(".modal").on("hidden.bs.modal", function () {
								        	    window.location = homeUrl;
								        	}); */
								  		}
								  		
								  		else{
								  			$("#emailModal").modal('show');
								        	$("#emailModal .modal-body").empty(); 
								        	txt1 = "Update Notification Failed .Please try again.";
								        	$("#emailModal .modal-body").append(txt1); 
								  			 
								  		}
								  		
								  		
									},
								  		error: function(xhr,status,error)
									{
								  		//alert("Notification Created Succesfully",);
								  			$("#emailModal").modal('show');
								        	$("#emailModal .modal-body").empty(); 
								        	txt1 = "We found some error. Please wait......";
								        	$("#emailModal .modal-body").append(txt1); 
										
									},complete: function(){
										$('#loading').hide();
								      }
								});  
					 }
				 } 
		</script>
		
		
		<script> 
	$(document).ready(function(){  
	    $("#myFormReset").validate({
	    	 rules: { 
	            "email": {
	                required: true,
	                email: true
	            }   
	        }, 
	        
	        messages: { 
	            "email": {
	                required: "Please, enter an email",
	                email: "Email is invalid"
	            },
	        }, 
	        highlight: function(element, errorClass, validClass) { 
	            $(element).addClass(errorClass).removeClass(validClass);
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	          },
	          success: function(element) { 
	         element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            $(element).remove();
	          },
	        submitHandler: function (form) {   
	            return false;  
	        } 
	    });
	    
	    $('#notification').click(function() { 
	  	if($("#myFormReset").valid())  
	    	 document.getElementById("myFormReset").submit();
	  /* 	else
	  		document.getElementById("requiredspan").val = "Please Fill Required Feilds" */
	    });    
	    
	});

	</script>

</body>
</html>
