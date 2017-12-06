<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<%-- 
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script> --%>
<style type="text/css">
.error {
    color:red;
}
.valid {
    color:green;
    }

</style>
<%-- <script >
  $(function() {
	 supportEmail=0;
	  $("#btnAdd").bind("click", function() {
	   var div = $("<div />");
	    div.html(GetDynamicTextBox(""));
	    $("#content").append(div);
	    
	  });
	  
	     $("#btnSave").bind("click", function() {
	    	    var values= $.map($("input[name=DynamicTextBox]"), function(el) {
	 	    	 values.push(el.value)
	 	    	 return el.value
	 	      });
	    	  if(values.length>0){
	    		  $("#emailArray").val(values);
	    	  }
	    	  
	    	  
	      });
	     
	   $("body").on("click", ".remove", function() {
	    $(this).closest("div").remove();
	    if(supportEmail>0) 
	    	supportEmail--;
	    });
	  
	
	  
	  
	  
	  
	  
	});

	function GetDynamicTextBox(value) {
		supportEmail++;
		return '<div class="form-group"><label for="last-name" class="col-sm-2 control-label">Email '+ supportEmail + ':</label>'+
		'<div class="col-sm-8"><input type="email"  autocomplete="off"        placeholder="Support Email ' + supportEmail + '"  class="form-control input-sm"   value = "' + value + '"   name="DynamicTextBox"/></div>'+
		'<input type="button" value="Remove" class="remove" /></div>'
	 } 
 
</script>  --%>

<link rel="stylesheet" href="css/alert.css">
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "apiProviderList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>

</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Add Supplier(s)</h1>
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
			<form action="createApiProvider" method="post" id="createApiProvider"
				class="form-horizontal" name="myForm">
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Vendor
						Name</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="vendorName"
							name="vendorName" placeholder="Vendor Name" autocomplete="off"
							     min="10" maxlength="30"    required >
					</div>
				</div>
				 
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Supplier Mode </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="apiMode" required>
							<option value="">Select Supplier Mode</option>
							<option value="live" selected="selected">Live</option>
							<option value="test" >Test</option>
							</select>
					</div>
 					</div>
				
				 <div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Travel
						Type </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="travelTypeList"   required >
							<option value="">Select Travel Type</option>
							<option value="flight" selected="selected">Flight</option>
							<option value="hotel">Hotel</option>
							<option value="car">Car</option>
							<option value="bus">Bus</option>
							<option value="train">Train</option>
							<option value="visa">Visa</option>
							<option value="insurance">Insurance</option>
							
							

						</select>
					</div>

				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label"> </label>
					<div class="col-sm-8">
						<div class="support">

							<a class="h5" role="button" data-toggle="collapse"
								href="#support" aria-expanded="false"> Support Details <i
								class="fa fa-plus btn btn-primary"></i>
							</a>

							<div class="collapse" id="support">

								<h4>Support details</h4>
								<!-- level1 -->
								<div class="level1">
										<div class="well clearfix" id="myfform">
											<p class="h4">Level:1</p>
											
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Email</label>
												 
													<input type="email" class="form-control input-sm"
														name="apiProviderSupportDetails.email1"
														placeholder="Support Email" autocomplete="off"   >
												</div>
											</div>
											
											<div class="col-sm-3">	 
											<div class="form-group">
												<label  >Mobile/Phone</label> 												 
													<input type="number" class="form-control input-sm"
														name="apiProviderSupportDetails.mob1" placeholder="phone"
														autocomplete="off"     >
												</div>
											</div>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Name</label> 
													<input type="text" class="form-control input-sm"
														name="apiProviderSupportDetails.name1" placeholder="Name"
														autocomplete="off">
												</div>
											</div>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Location</label>
												 
													<input type="text" class="form-control input-sm"
														name="apiProviderSupportDetails.loc1"
														placeholder="Location" autocomplete="off">
												</div>
											</div>


										</div>
									 
								</div>


								<!-- level1 -->

								<!-- level1 -->
								<div class="level1"> 
										<div class="well clearfix" id="myfform">
											<p class="h4">Level:2</p>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Email</label>
												 
													<input type="email" class="form-control input-sm"
														name="apiProviderSupportDetails.email2"
														placeholder="Support Email" autocomplete="off">
												</div>
											</div>
											<div class="col-sm-3">	

											<div class="form-group">
												<label  >Mobile/Phone</label>
												 
													<input type="number" class="form-control input-sm"
														name="apiProviderSupportDetails.mob2" placeholder="phone"
														autocomplete="off">
												</div>
											</div>
											<div class="col-sm-3">	
											<div class="form-group">
												<label >Name</label>
												 
													<input type="text" class="form-control input-sm"
														name="apiProviderSupportDetails.name2" placeholder="Name"
														autocomplete="off">
												</div>
											</div>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Location</label>
												 
													<input type="text" class="form-control input-sm"
														name="apiProviderSupportDetails.loc2"
														placeholder="Location" autocomplete="off">
												</div>
											</div>


										</div>
									 
								</div>


								<!-- level1 -->


							</div>
							<!-- support -->
							<!-- tech support -->
							<a class="h5" role="button" data-toggle="collapse"
								href="#tecsupport" aria-expanded="false"> Technical Support
								<i class="fa fa-plus btn btn-primary"></i>
							</a>


							<div class="collapse" id="tecsupport">

								<h4>Technical Support details</h4>
								<!-- level1 -->
								<div class="level1"> 
										<div class="well clearfix" id="myfform">
											<p class="h4">Level:1</p>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Email</label>
												 
													<input type="email" class="form-control input-sm"
														name="apiProviderTechSupportDetails.email1"
														placeholder="Email" autocomplete="off"     >
												</div>
											</div>
												<div class="col-sm-3">	
											<div class="form-group">
												<label  >Mobile/Phone</label>
												 
													<input type="number" class="form-control input-sm"
														name="apiProviderTechSupportDetails.mob1"
														placeholder="Mobile/Phone" autocomplete="off"     >
												</div>
											</div>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Name</label>
												 
													<input type="text" class="form-control input-sm"
														name="apiProviderTechSupportDetails.name1"
														placeholder="Name" autocomplete="off">
												</div>
											</div>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Location</label>
												 
													<input type="text" class="form-control input-sm"
														name="apiProviderTechSupportDetails.loc1"
														placeholder="Location" autocomplete="off"     >
												</div>
											</div>

										 
									</div>
								</div>


								<!-- level1 -->

								<!-- level1 -->
								<div class="level1 clearfix" >
									 
										<div class="well clearfix" id="myfform">
											<p class="h4">Level:2</p>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Email</label>
												 
													<input type="email" class="form-control input-sm"
														name="apiProviderTechSupportDetails.email2"
														placeholder="Email" autocomplete="off">
												</div>
											</div>
												<div class="col-sm-3">	
											<div class="form-group">
												<label  >Mobile/Phone</label>
												 
													<input type="number" class="form-control input-sm"
														name="apiProviderTechSupportDetails.mob2"
														placeholder="Mobile/Phone" autocomplete="off">
												</div>
											</div>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Name</label>
												 
													<input type="text" class="form-control input-sm"
														name="apiProviderTechSupportDetails.name2"
														placeholder="Name" autocomplete="off">
												</div>
											</div>
											<div class="col-sm-3">	
											<div class="form-group">
												<label  >Location</label>
												 
													<input type="text" class="form-control input-sm"
														name="apiProviderTechSupportDetails.loc2"
														placeholder="Location" autocomplete="off">
												</div>
											</div>

										</div>
									</div>
								</div>


								<!-- level1 -->


							 
							<!-- tech support ends -->

						 </div>

						<!--  support -->

					</div>
				</div>


				<div class="form-group">

					<label for="Address" class="col-sm-2 control-label">
						Support Address</label>
					<div class="col-sm-8">
						<textarea class="form-control input-sm" id="address"
							name="supportAddress" placeholder="Support Address"
							maxlength="300" required ></textarea>
					</div>
				</div>

				<div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="button" id="addSupplierBtn" class="btn btn-primary btn-lg">Add Supplier</button>
					</div>
				</div>
			</form>
		</section>
	</div>


	<%@ include file="DashboardFooter.jsp"%>

</body>
<script type="text/javascript">
$(document).ready(function(){
	
	 $("#createApiProvider").validate({
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
    
	
	
$('#addSupplierBtn').click(function() {
	 $("#createApiProvider").valid();
	 if($("#createApiProvider").valid()){     		        
     	 document.getElementById("createApiProvider").submit();
     }
})

});
</script>
</html>