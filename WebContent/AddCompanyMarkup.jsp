<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html  >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company MarkUp</title>
<%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<!-- <link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>   -->
   <%-- <script src= "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>  
   <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"> </script> --%>
  <%--  <script src="https://rawgit.com/dbrekalo/attire/master/dist/js/build.min.js"></script> --%>
  <!--  <link rel="stylesheet" href="https://rawgit.com/dbrekalo/attire/master/dist/css/build.min.css"> -->
     <link rel="stylesheet" href="css/fastselect.min.css">
 <script src="js/bootstrap-select.js"></script> 
 <!-- <link href="css/jquerydarkness-ui.min.css" rel="stylesheet" type="text/css"/> 
 <link href="css/slider.css" rel="stylesheet" type="text/css"/>  
   -->
   <%--  <link href="css/slider.css" rel="stylesheet" type="text/css"/>  
   <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script> 
  
  
 <link href="css/RangeSlider.css" rel="stylesheet" type="text/css"/> 
  <link href="css/slider.css" rel="stylesheet" type="text/css"/>  --%>
    <style>

            .fstElement { font-size: 0.9em; }
            .fstToggleBtn { min-width: 16.5em; }
            .submitBtn { display: none; }
            .fstMultipleMode { display: block; }
            .fstMultipleMode .fstControls { width: 100%; }

        </style>
 <link rel="stylesheet" href="css/alert.css">
  <link rel="stylesheet" href="css/bootstrap-select.css">
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"markupList";
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
 });
 </script>
  
 <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_arr").datepicker({
		 dateFormat: "dd-mm-yy",
			 minDate: 0,
		/*  changeMonth: true,
		 changeYear: true */
		 onSelect : function(selectedDate) {
				
			 $(this).valid();
			 
		 }
	 }); 
	 });
	 
 </script> 
     <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_dep").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate: 0,
		 onSelect : function(selectedDate) {
			
			 $(this).valid();
			 
		 }
	 });
	 }); 
 </script>
   <script>
 $(document).ready(function() 
 { 
	 $("#datepicker_PromofareStart").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate: 0,
		 onSelect : function(selectedDate) {
				
			 $(this).valid();
			 
		 }
	 });
	 }); 
 </script>
   <script>
 $(document).ready(function() 
 { 
	 
	 $("#datepicker_PromofareEnd").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate: 0,
		 onSelect : function(selectedDate) {
				
			 $(this).valid();
			 
		 }
	 });
	 }); 

 
 </script>
 
  
<%--  <script type="text/javascript">
 	 $(function() {
		 loadstations();
	 });
 	function loadstations() {
		  $.getJSON("airport2.json",{
				format : "json"
			}).done(
					 function(jsonbusdata) {
					  	citylist = [];
						  names = [];
						  var originArray = [];
						  var destArray = [];
						  var container = document.getElementById('displayOrigins'); //Cache container.
						  var destContainer = document.getElementById('displayDestinations'); //Cache container.
						 $.each(jsonbusdata, function(i, value) {
							citylist.push(value.city+","+value.country+","+"("+value.code+")");   
							 
						 });
						 $("#origin").autocomplete({
							
					        source: citylist,
					        select: function(event, ui) {
					        	 originArray.push(ui.item.value);
					        	  $("#myOrigins").val(originArray.join(";"));
					        	 
					        	   for (i = 0; i <  originArray.length; i++) {
						        		 var tb = document.createElement('input');
						        	        tb.type = 'text';
						        	        tb.id = i;
						        	        tb.value=originArray[i];// Set id based on "i" value
						        	     }  
					        	   container.appendChild(tb); 
					          }
						 
						  });
						
				        
				          $("#destination").autocomplete({
						        source: citylist,
						        select: function(event, ui) {
						        	 destArray.push(ui.item.value);
						        	 
						        	  $("#myDestinations").val(destArray.join(";"));
						        	 
						        	   for (i = 0; i < destArray.length; i++) {
							        		 var Dtb = document.createElement('input');
							        		 Dtb.type = 'text';
							        		 Dtb.id = i;
							        		 Dtb.value=destArray[i];// Set id based on "i" value
							        	     }  
						        	   destContainer.appendChild(Dtb)
						             
						          }
						        
						         
						    });
						 
					
					    $.ui.autocomplete.filter = function (array, term) {
					        var matcher = new RegExp("" + $.ui.autocomplete.escapeRegex(term), "i");
					        return $.grep(array, function (value) {
					            return matcher.test(value.label || value.value || value);
					        });
					    };
					    
					 
					    if(originArray!=null && originArray.length==0){
					     $("#myOrigins").val("");
					 	  $("#origin").val("");
					    }
					    if(destArray!=null && destArray.length==0){
						     $("#myDestinations").val("");
						 	  $("#destination").val("");
						    }
					    
					  });
	  }
 	
 	 </script>
 	  --%>
  <style type="text/css">
   .ui-autocomplete {
              max-height: 200px;
               width:auto;
              overflow-y: auto;
              /* prevent horizontal scrollbar */
              overflow-x: auto;
              font-family: "Trebuchet MS","Helvetica","Arial","Verdana","sans-serif";
			font-size:1em;
              /* add padding to account for vertical scrollbar */
              
      }
      /* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
      * html .ui-autocomplete {
          height:  200px;
          width: auto;
      }
 
 </style>
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
 <style type="text/css">
/* enable absolute positioning */
.inner-addon {
  position: relative;
}

/* style glyph */
.inner-addon .glyphicon {
  position: absolute;
  padding: 5px 3px 4px 2px;
  pointer-events: none;
}

/* align glyph */
.left-addon .glyphicon  { left:  0px;}
.right-addon .glyphicon { right: 0px;}

/* add padding  */
.left-addon input  { padding-left:  30px; }
.right-addon input { padding-right: 30px; }

.error {
    color:red;
      border-color: #dd4b39 !important;
    box-shadow: none;
}
.valid {
    color:green;
      border-color: green !important;
    box-shadow: none;
    }
    
.form-control-feedback{
  display: none;
}

.form-horizontal .has-feedback .form-control-feedback {
    right: 12px !important;
}

.has-feedback label ~.form-control-feedback {
    top: 21px !important;
}
 
</style>
</head>
<body >
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper" >
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Add Flight Markup <%-- <small>Control panel</small> --%>
			</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			 <div class="col-sm-12">
						<h4  >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
			<!-- Small boxes (Stat box) -->
			 <s:if test="hasActionErrors()">
						<div class="succfully-updated clearfix" id="error-alert">

							<div class="col-sm-2">
								<i class="fa fa-check fa-3x"></i>
							</div>

							<div class="col-sm-10">

								<p>
									<s:actionerror />
								</p>

								<button type="button" id="cancel" class="btn btn-primary">Ok</button>

							</div>

						</div>


					</s:if>

					<s:if test="hasActionMessages()">
						<div class="sccuss-full-updated" id="success-alert">
							<div class="succfully-updated clearfix">

								<div class="col-sm-2">
									<i class="fa fa-check fa-3x"></i>
								</div>

								<div class="col-sm-10">
									<s:actionmessage />
									<button type="button" id="success" class="btn btn-primary">Ok</button>

								</div>

							</div>
						</div>
					</s:if>
			<div class="clearfix">
				 
				<form action="setMarkup" method="post" class="form-horizontal" id="myfform">
				  <input type="hidden" name="myOrigins" id="myOrigins">
				   <input type="hidden" name="myDestinations" id="myDestinations">
				<div class="col-md-12 col-sm-12 col-xs-12">	
				<div class="col-sm-3">	
				  <div class="form-group">
						<label for="City" >Config Number
							 </label>						 
							<select class="form-control input-sm" name="configData"
								id="configData" autocomplete="off" required>
								<option selected value="">Select Company ConfigNumber </option>
								<s:if test="%{#session.Company.companyRole.isDistributor()}">
								 <s:iterator value="companyConfigIdsList">
									<option value="<s:property value="company_id"/>/<s:property value="config_id"/>/<s:property value="configname"/>/<s:property value="config_number"/>/<s:property value="companyName"/>"><s:property value="config_number"/>  - <s:property value="configname"/> (<s:property value="companyName"/>-<s:property value="companyUserId"/>)</option>
								  </s:iterator>
								  <s:iterator value="AgencyConfiglist">
									<option value="<s:property value="company_id"/>/<s:property value="config_id"/>/<s:property value="configname"/>/<s:property value="config_number"/>/<s:property value="companyName"/>"><s:property value="config_number"/>  - <s:property value="configname"/> (<s:property value="companyName"/>-<s:property value="companyUserId"/>)</option>
								  </s:iterator>
 						 		</s:if>
 						 		<s:elseif test="%{#session.Company.companyRole.isAgent()}">
								  <s:iterator value="AgencyConfiglist">
									<option value="<s:property value="company_id"/>/<s:property value="config_id"/>/<s:property value="configname"/>/<s:property value="config_number"/>/<s:property value="companyName"/>"><s:property value="config_number"/>  - <s:property value="configname"/> (<s:property value="companyName"/>-<s:property value="companyUserId"/>)</option>
							  	 </s:iterator>
 						 		</s:elseif>
 						 		
								<s:else>
								<s:iterator value="companyConfigIdsList">
							<option value="<s:property value="company_id"/>/<s:property value="config_id"/>/<s:property value="configname"/>/<s:property value="config_number"/>/<s:property value="companyName"/>"><s:property value="config_number"/>  - <s:property value="configname"/> (<s:property value="companyName"/>-<s:property value="companyUserId"/>)</option>
								 </s:iterator>
 							</s:else>
						 

							</select>
						</div>
					</div>
				<div class="col-sm-3">	
				 		<div class="form-group">
				      <label for="Username"  >MarkUp
								Name</label>
							 
								<input type="text" class="form-control input-sm" id="username"
									name="name"  value='<s:property value="name" />'  placeholder="markup Name" autocomplete="off" maxlength="50"
									required>
							</div>
						</div>
						<div class="col-sm-3">	
						<div class="form-group">
							<label for="Country"  >Applicable on existing markups</label>
							 
								<select class="form-control input-sm"   name="accumulative"
									 required>
									  <option value="true" selected="selected">YES</option>
									<option value="false"  >NO</option>
									 
								</select>
							</div>
						</div>
						<div class="col-sm-3">	
						<div class="form-group">
							<label for="Country" >is
								FixedAmount</label>
							 
								<select class="form-control input-sm" name="fixedAmount"
									  autocomplete="off" required>
 									<option value="true">yes</option>
									<option value="false">no</option>
									 
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">		
					 <div class="col-sm-3">	
						<div class="form-group">
							<label for="last-name"  >MarkUp</label>
							 
								<input type="text" class="form-control input-sm allow_only_numbers" id="last-name"
									name="markup"   value='<s:property value="markup" />'      placeholder="amount" autocomplete="off" maxlength="20" required>
							</div>
						</div>
						<div class="col-sm-3">	
						<div class="form-group">
							<label for="Country"  >Position of MarkUp</label>
							
							 
								 <input type="text" class="form-control input-sm allow_only_numbers" name="positionOfMarkup" maxlength="20"
									id="country" autocomplete="off" required  >
							</div>
						</div>
						<div class="col-sm-3">	
							<div class="form-group">
							<label for="Country"  >Airline</label>
							 
								<select class="form-control input-sm "  name="airline" 
									id="country" autocomplete="off" required>
 								<option value="ALL" selected="selected">ALL</option>
									<s:iterator value="allAirlineList">
									<option value="<s:property value="airlinecode"/>"><s:property
											value="airline"></s:property></option>
											 
								</s:iterator>
									 
								</select>
							</div>
						</div>
						<div class="col-sm-3">	
							<div class="form-group">
						<label for="City"  >Destination Type</label>
							<select class="form-control input-sm" name="destinationType" id="destinationType"
								autocomplete="off" required>
								<option selected value="ALL">ALL</option>
								<option  value="Domestic">Domestic</option>
								<option  value="International">International</option>
							</select>
						</div>
					</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">	
					<div class="col-sm-3">	
						<div class="form-group">
							<label for="Country" >Fare Basis Code</label>
							 
								<input type="text" class="form-control input-sm" id="last-name"
									name="fareBaseCode"   value='ALL' placeholder="Fare Basis Code" autocomplete="off" 
									required /> 
							</div>
						</div>
						<div class="col-sm-3">	
				<div class="form-group">
						<label for="last-name" >Departure
							Date</label>						 
							<input type="text" id="datepicker_dep"
								class="form-control input-sm" name="deptDate"  value='<s:property value="deptDate" />'  autocomplete="off"
								placeholder="ALL" readonly required />
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="last-name" >Arrival
							Date</label>
						 
							<input type="text" id="datepicker_arr"
								class="form-control input-sm"   value='<s:property value="arrvDate" />'    name="arrvDate" autocomplete="off"
								placeholder="ALL" readonly required />
						</div>
					</div>
					
<div class="col-sm-3">	
					<div class="form-group">
						<label for="last-name" >Promofare StartDate
							 </label>
						 
							<input type="text" id="datepicker_PromofareStart"
								class="form-control input-sm" name="promofareStartDate"  value='<s:property value="promofareStartDate" />'  autocomplete="off"
								placeholder="ALL" required />
						</div>
					</div>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">	
					<div class="col-sm-3">	
						<div class="form-group">
						<label for="last-name" >Promofare EndDate
							 </label>
						 
							<input type="text" id="datepicker_PromofareEnd"
								class="form-control input-sm" name="promofareEndDate"  value='<s:property value="promofareEndDate" />'  autocomplete="off"
								placeholder="ALL" required />
						</div>
					</div>
					
					 <div class="col-sm-3">					
					<div class="form-group">
						<label for="last-name"  >Origin</label>
						
						  <input type="text" multiple class="form-control input-sm multipleInputDynamic" id="origin" name="origin" data-url="markupairportlist.json"  
						   value='<s:property value="origin"/>' placeholder="ALL" />
							<%-- <input type="text" id="origin" class="form-control input-sm"  
								name="origin"    value='<s:property value="origin" />'    placeholder="ALL"   > --%>
						</div>
					</div>
 				<%-- <div class="form-group">
						<label for="last-name"    class="col-sm-2 control-label">Origin</label>
						<div class="col-sm-8">
							<input type="text" id="origin" class="form-control input-sm"  
								name="origin"    value='<s:property value="origin" />'  autocomplete="off" placeholder="ALL"   auto-complete-directive ui-items="names">
						</div>
					</div> --%>
					
					
				<div class="col-sm-3">	
					<div class="form-group" >
						<label for="last-name"  >Destination</label>
						<input type="text" multiple class="form-control input-sm multipleInputDynamic" id="destination" name="destination" data-url="markupairportlist.json"   value='<s:property value="destination"/>'   placeholder="ALL"  />
						
							<%-- <input type="text"   class="form-control input-sm"  id="destination"
								name="destination"   value='<s:property value="destination"/>'   placeholder="ALL"
							  > --%>
						</div>
					</div>
   

                       <input type="hidden" name="country" value="ALL" required />
		
				<%-- 	<div class="form-group">
						<label for="City" class="col-sm-2 control-label">Country </label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="country" id="country"
								autocomplete="off" required>
								<option selected value="ALL">ALL</option>
								<s:iterator value="#session.countryList">
									<option value="<s:property value="c_name"/>"><s:property
											value="c_name"></s:property></option>
								</s:iterator>

							</select>
						</div>
					</div> --%>
					
					
					
					
					<!-- <div class="form-group">
							<label for="last-name" class="col-sm-2 control-label">Airline</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="last-name"
									name="airline" placeholder="airline" autocomplete="off"
									required>
							</div>
						</div> -->
							<div class="col-sm-3">	
						<div class="form-group">
							<label for="Country" >ClassOfService</label>
							 
								<select class="form-control input-sm" name="classOfService"
									id="country" autocomplete="off" required>
									<option selected="selected" value="ALL">ALL</option>
									<option value="Economy">Economy</option>
									<option value="Premium">Premium Economy</option>
									<option value="Business">Business</option>
										<option value="First">First</option>
									
								</select>
							</div>
						</div>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">	
						<div class="col-sm-3" id="displayDestinations">	
					 <div class="form-group" ></div>
					 </div>
					<div class="col-sm-3" id="displayOrigins">	
					 <div class="form-group" ></div>
					 </div>
					 </div>
						<div class="form-group text-center">
							<div class="col-xs-12 submitWrap text-center">
								<button id="setMarkUP" type="button" class="btn btn-primary btn-lg">Set
									Markup</button>
							</div>
						</div>
				</form>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	
	 <script>
  
 function isNumberKey(evt,obj){
	 
     evt = (evt) ? evt : window.event;
       var charCode = (evt.which) ? evt.which : evt.keyCode;
       if (charCode > 31 &&  (charCode < 48 || charCode > 57))  
       
           return false;
       
    
      
   }
 function IsAlphaNumeric(evt,obj) {
    /*  var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
     var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode));
     document.getElementById("error").style.display = ret ? "none" : "inline";
     return false; */
     evt = (evt) ? evt : window.event;
     var charCode = (evt.which) ? evt.which : evt.keyCode;
     if ((charCode >= 48 && charCode <= 57) || (charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122) || (specialKeys.indexOf(evt.keyCode) != -1 && evt.charCode != evt.keyCode))  
     
         return false;
 }
 
 $(document).ready(function() {
	  $(".allow_only_numbers").keydown(function(e) {
	    // Allow: backspace, delete, tab, escape, enter and .
	    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
	      // Allow: Ctrl+A,Ctrl+C,Ctrl+V, Command+A
	      ((e.keyCode == 65 || e.keyCode == 86 || e.keyCode == 67) && (e.ctrlKey === true || e.metaKey === true)) ||
	      // Allow: home, end, left, right, down, up
	      (e.keyCode >= 35 && e.keyCode <= 40)) {
	      // let it happen, don't do anything
	      return;
	    }
	    // Ensure that it is a number and stop the keypress
	    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
	      e.preventDefault();
	    }
	  });
	});
 </script>
	
	<!-- /.content-wrapper -->
  <%-- <script type="text/javascript" src="js/app.js"></script>  --%>
	<%--   --%>
	    <%@ include file="DashboardFooter.jsp"%> 
	<%--  <%@ include file="DashFooter.jsp" %>  --%>
	<%--  <script src=js/listcomplete.js> </script> --%>
</body>

 <script src ="js/fastselect.standalone.js"> </script>
 
    <script>

                $('.multipleInputDynamic').fastselect();
       $(document).ready(function(){
    	  
    	   $('#setMarkUP').click(function(){
    		   $("#myfform").valid();
    		   if($("#myfform").valid()){
    			   document.getElementById("myfform").submit();
    		   }
    		   
    	   })
    	   $("#myfform").validate({
    		   submitHandler: function (form) {  
   	            
   	            return false;
   	        }
    	   })
       })

            </script>
 
 
</html>