<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html  >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bus MarkUp</title>
 
     <link rel="stylesheet" href="css/fastselect.min.css">
 <script src="js/bootstrap-select.js"></script>  
    <style>

            .fstElement { font-size: 0.9em; }
            .fstToggleBtn { min-width: 16.5em; }
            .submitBtn { display: none; }
            .fstMultipleMode { display: block; }
            .fstMultipleMode .fstControls { width: 100%; }

        </style>
 <link rel="stylesheet" href="css/alert.css">
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
  <link rel="stylesheet" href="css/bootstrap-select.css">

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"busMarkupList";
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
			 onSelect : function(selectedDate) {
				 $(this).valid(); 
			 }
		/*  changeMonth: true,
		 changeYear: true */
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
 
</head>
<body >
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper" >
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Add Bus Markup <%-- <small>Control panel</small> --%>
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
				 
				<form action="setBusMarkup" method="post" class="form-horizontal" id="myfform">
				  <input type="hidden" name="myOrigins" id="myOrigins">
				   <input type="hidden" name="myDestinations" id="myDestinations">
					
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
									name="markupName"  value='<s:property value="markupName" />'  placeholder="markup Name" autocomplete="off"
									required>
							</div>
						</div>
						<div class="col-sm-3">	
						<div class="form-group">
							<label for="Country"  >isAccumulative</label>
							 
								<select class="form-control input-sm"   name="isAccumulative"
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
 									<option selected="selected" value="true">yes</option>
									<option value="false">no</option>
									 
								</select>
							</div>
						</div>
						
					 <div class="col-sm-3">	
						<div class="form-group">
							<label for="last-name"  >MarkUp Amount</label>
							 
								<input type="text" class="form-control input-sm" id="last-name"
									name="markupAmount"   value='<s:property value="markupAmount" />'   placeholder="Markup Amount" autocomplete="off" required onkeypress="return isNumberKey(event,this);">
							</div>
						</div>
						<div class="col-sm-3">	
							<div class="form-group">
								<label for="positionOfMarkup"  >Position of MarkUp</label> 
								 <input type="text" class="form-control input-sm" name="positionOfMarkup"
									id="positionOfMarkup" autocomplete="off" required onkeypress="return isNumberKey(event,this);">
							</div>
						</div>
						<div class="col-sm-3">	
							<div class="form-group">
							<label for="Country"  >Bus Operator</label>
							 
								<select class="form-control input-sm "  name="airline" 
									id="country" autocomplete="off" required>
 								<option value="ALL" selected="selected">ALL</option>
									<s:iterator value="allBuslineList">
									<option value="<s:property value="busOperators"/>"><s:property
											value="busOperators"></s:property></option>
											 
								</s:iterator>
									 
								</select>
							</div>
						</div>
						
					 
						<div class="col-sm-3">	
				<div class="form-group">
						<label for="last-name" >Departure
							Date</label>						 
							<input type="text" id="datepicker_dep"
								class="form-control input-sm" name="deptDate"  value='<s:property value="deptDate" />'  autocomplete="off"
								placeholder="Departure Date" required>
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label for="last-name" >Arrival
							Date</label>
						 
							<input type="text" id="datepicker_arr"
								class="form-control input-sm"   value='<s:property value="arrDate" />'    name="arrDate" autocomplete="off"
								placeholder="Arrival Date" required>
						</div>
					</div>
					
<div class="col-sm-3">	
					<div class="form-group">
						<label for="last-name" >Promofare StartDate
							 </label>
						 
							<input type="text" id="datepicker_PromofareStart"
								class="form-control input-sm" name="promofareStartDate"  value='<s:property value="promofareStartDate" />'  autocomplete="off"
								placeholder="Promofare StartDate" required>
						</div>
					</div>
					<div class="col-sm-3">	
						<div class="form-group">
						<label for="last-name" >Promofare EndDate
							 </label>
						 
							<input type="text" id="datepicker_PromofareEnd"
								class="form-control input-sm" name="promofareEndDate"  value='<s:property value="promofareEndDate" />'  autocomplete="off"
								placeholder="Promofare EndDate" required>
						</div>
					</div>
					
					 <div class="col-sm-3">					
					<div class="form-group">
						<label for="last-name"  >Origin</label>
						
						  <input type="text" multiple class="form-control input-sm multipleInputDynamic" id="origin" name="origin" data-url="markupairportlist.json"  value='<s:property value="origin"/>'  placeholder="All" />
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
						<input type="text" multiple class="form-control input-sm multipleInputDynamic" id="destination" name="destination" data-url="markupairportlist.json"   value='<s:property value="destination"/>'    placeholder="ALL"  />
						
							<%-- <input type="text"   class="form-control input-sm"  id="destination"
								name="destination"   value='<s:property value="destination"/>'   placeholder="ALL"
							  > --%>
						</div>
					</div> 
							<div class="col-sm-3">	
						<div class="form-group">
							<label for="busType" >Bus Type</label> 
								<select class="form-control input-sm" name="busTypes"
									id="busType" autocomplete="off" required>
									<option selected="selected" value="ALL">ALL</option>
									<option value="Ac Sleeper">Ac Sleeper</option>
									<option value="Non-Ac Sleeper">Non-Ac Sleeper</option>
									<option value="Ac-Seater">Ac-Seater</option>
									<option value="Non-Ac Seater">Non-Ac Seater</option>
									<option value="Cab">Cab</option> 
								</select>
							</div>
						</div>
						
						<div class="col-sm-3">	
						<div class="form-group">
							<label for="isMarkUpPerPassenger" >isMarkUpPerPassenger</label> 
								<select class="form-control input-sm" name="isMarkUpPerPassenger"
									id="isMarkUpPerPassenger" autocomplete="off" required>
									<option selected="selected" value="No">No</option> 
									<option  value="Yes">Yes</option>
								 
								</select>
							</div>
						</div>
						<div class="col-sm-3">	
						<div class="form-group">
							<label for="isMarkUpOnTotal" >isMarkUpOnTotal</label> 
								<select class="form-control input-sm" name="isMarkUpOnTotal"
									id="isMarkUpOnTotal" autocomplete="off" required>
									<option selected="selected" value="No">No</option> 
									<option  value="Yes">Yes</option>
									
								</select>
							</div>
						</div>
			<!-- 			<div class="col-sm-3" id="displayDestinations">	
					 <div class="form-group" ></div>
					 </div>
					<div class="col-sm-3" id="displayOrigins">	
					 <div class="form-group" ></div>
					 </div> -->
						<div class="form-group text-center">
							<div class="col-xs-12 submitWrap text-center">
								<button type="button" id="setMarkup" class="btn btn-primary btn-lg">Set
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
    $(document).ready(function(){
    	
		
    	jQuery.validator.addMethod("countryTypeVal",function(value) {
            var country =  $('#country').val();
            return (country !=  '0');
          }, "please select country");
    	 $("#myfform").validate({
        	 ignore: false,  
        	rules: {
               
                "email": {
                    required: true,
                    email: true
                },
            },
            
            messages: {
                
                "email": {
                    required: "Please, enter an email",
                    email: "Email is invalid"
                },
            },
            submitHandler: function (form) { // for demo
                // for demo
                return false; // for demo
            }, 
            highlight: function(element, errorClass, validClass) { 
                $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-ok').addClass('glyphicon-remove');
                $(element).addClass(errorClass).removeClass(validClass);
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
              },
              success: function(element) {
                $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-remove').addClass('glyphicon-ok');
             element.closest('.form-group').removeClass('has-error').addClass('has-success');
                $(element).remove();
              }
        });
        
    	
    	
    $('#setMarkup').click(function() {
    	 $("#myfform").valid();
    	 if($("#myfform").valid()){     		        
         	 document.getElementById("myfform").submit();
         }
    })

    });
    
    
    
                $('.multipleInputDynamic').fastselect();

          
                
                
                
                </script>
 
 
</html>