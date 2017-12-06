
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	rel="stylesheet">



<%-- <script src="js/angular.js" type="text/javascript"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<style type="text/css">
#register {
	margin-left: 100px;
}

#register label {
	margin-right: 5px;
}

#register input {
	padding: 5px 7px;
	border: 1px solid #d5d9da;
	box-shadow: 0 0 5px #e8e9eb inset;
	width: 250px;
	font-size: 1em;
	outline: 0;
}

#result {
	margin-left: 5px;
}

.short {
	color: #FF0000;
}

.weak {
	color: #E66C2C;
}

.good {
	color: #2D98F3;
}

.strong {
	color: #006400;
}

#sss .input-group-addon, .input-group-btn {
	width: 25%;
}

#sss .input-group-addon, .input-group-btn select {
	padding: 0px;
}

.panel-group .panel-heading+.panel-collapse>.panel-body {
	border: 1px solid #ddd;
}

.panel-group, .panel-group .panel, .panel-group .panel-heading,
	.panel-group .panel-heading a, .panel-group .panel-title, .panel-group .panel-title a,
	.panel-group .panel-body, .panel-group .panel-group .panel-heading+.panel-collapse>.panel-body
	{
	border-radius: 2px;
	border: 0;
}

.panel-group .panel-heading {
	padding: 0;
}

.panel-group .panel-heading a {
	display: block;
	background: #668bb1;
	color: #ffffff;
	padding: 8px;
	text-decoration: none;
	position: relative;
}

.panel-group .panel-heading a.collapsed {
	background: #eeeeee;
	color: inherit;
}

.panel-group .panel-heading a:after {
	content: '-';
	position: absolute;
	right: 20px;
	top: 8px;
	font-size: 20px;
}

.panel-group .panel-heading a.collapsed:after {
	content: '+';
}

#State option {
	color: #555;
}

.form-control {
	border-color: #afafaf;
}

#myfform .form-group {
	margin-bottom: 5px;
}
</style>
<style type="text/css">
.ui-autocomplete {
	max-height: 200px;
	width: auto;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: auto;
	font-family: "Trebuchet MS", "Helvetica", "Arial", "Verdana",
		"sans-serif";
	font-size: 1em;
	/* add padding to account for vertical scrollbar */
}
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}
</style>
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"getApprovalCompaniesList";
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
 });
 
 
$(function() {
    $('#password').keydown(function(e) {
        if (e.keyCode == 32) // 32 is the ASCII value for a space
            e.preventDefault();
    });
    $("#company").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    $("#registerNumber").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    $("#Website").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    $("#username").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    $("#email").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    $("#address").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    $("#city").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    
    $("#location").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    
    $("#Billingcompany").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    $("#Billingaddress").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
 });
 
 
 </script>
<script type="text/javascript">
$(document).ready(function()
		{
			/*
				assigning keyup event to password field
				so everytime user type code will execute
			*/

			$('#password').keyup(function()
			{
				$('#result').html(checkStrength($('#password').val()))
			})	
			
			/*
				checkStrength is function which will do the 
				main password strength checking for us
			*/
			
			function checkStrength(password)
			{
				//initial strength
				var strength = 0
				
				//if the password length is less than 6, return message.
				if (password.length < 6) { 
					$('#result').removeClass()
					$('#result').addClass('short')
					return 'Too short' 
				}
				
				//length is ok, lets continue.
				
				//if length is 8 characters or more, increase strength value
				if (password.length > 7) strength += 1
				
				//if password contains both lower and uppercase characters, increase strength value
				if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))  strength += 1
				
				//if it has numbers and characters, increase strength value
				if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/))  strength += 1 
				
				//if it has one special character, increase strength value
				if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/))  strength += 1
				
				//if it has two special characters, increase strength value
				if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
				
				//now we have calculated strength value, we can return messages
				
				//if value is less than 2
				if (strength < 2 )
				{
					$('#result').removeClass()
					$('#result').addClass('weak')
					return 'Weak'			
				}
				else if (strength == 2 )
				{
					$('#result').removeClass()
					$('#result').addClass('good')
					return 'Good'		
				}
				else
				{
					$('#result').removeClass()
					$('#result').addClass('strong')
					return 'Strong'
				}
			}
		});
</script>
<script type="text/javascript">
 

 function isNumberKey(evt,obj){
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57))   
	    
	        return false;
	 }
	function InvalidMsg(textbox) {
	    
	    if(textbox.validity.patternMismatch){
	       textbox.setCustomValidity('Mobile number not vaild.');
	   }    
	   else {
	       textbox.setCustomValidity('');
	   }
	   return true;
	}
	function InvalidEmailMsg(textbox) {
	    
	    if(textbox.validity.patternMismatch){
	       textbox.setCustomValidity('Email address not vaild.');
	   }    
	   else {
	       textbox.setCustomValidity('');
	   }
	   return true;
	}

	function onlyAlphabets(e, t) {
        try {
            if (window.event) {
                var charCode = window.event.keyCode;
            }
            else if (e) {
                var charCode = e.which;
            }
            else { return true; }
            if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
                return true;
            else
                return false;
        }
        catch (err) {
            alert(err.Description);
        }}

 </script>
<script type="text/javascript">
 $(document).ready(
   function() {
    var currency_list = [];
     $('#country').change(function(){
     var country=$('#country').val();
      document.getElementById('Billingcountry').value =country;

      if(country !='India'){
    	  $('#txtState').attr('class','form-control input-sm').show();
    	 // $('#txtState').attr('class','form-control input-sm').show();
    	  $('#state').hide();
      }
      if (country =='India') {
          $("#txtState").removeAttr('class').hide();
          $('#state').show();
      }
      var billcountry=$('#Billingcountry').val();
      if(billcountry !='India'){
    	  $('#txtBillingState').attr('class','form-control input-sm').show(); 
            $('#Billingstate').hide();
      }
      if(billcountry =='India'){
    	  $('#txtBillingState').removeAttr('class').hide(); 
            $('#Billingstate').show();
      }
      });
     $('#state').change(function(){
         var state=$('#state').val();
          document.getElementById('Billingstate').value =state; 
          });
     
     $("#txtState").keyup(function() {
		 $("#txtBillingState").val($("#txtState").val());
      });
           });
  
 </script>






<!-- <!-- 
       if(country !='India'){
    	  $('#txtState').attr('class','form-control input-sm').show();
    	//  $('#txtBillingState').attr('class','form-control input-sm').show(); 
        //  $('#Billingstate').hide();
    	  $('#state').hide();
      }
      if (country =='India') {
          $("#txtState").removeAttr('class').hide();
       //   $("#txtBillingState").removeAttr('class').hide();
          $('#state').show();
       //   $('#Billingstate').show();
      }
      
      });
     var billcountry=$('#country').val();
     $('#state').change(function(){
         var state=$('#state').val();
          document.getElementById('Billingstate').value =state; 
          });
  
     
	 $("#txtState").keyup(function() {
		 $("#txtBillingState").val($("#txtState").val()); -->
-->


<script type="text/javascript">

        $(document).ready(function(){
 			 $('#bilAdress').click(function(){
 					if($(this).is(":checked")){
                	if ($('textarea#address') != undefined) {
                		   var message = $('textarea#address').val();
                		   document.getElementById('Billingaddress').value =message;
                		 }
 				  }

                else if($(this).is(":not(:checked)")){
                	 document.getElementById('Billingaddress').value ="";
                }

            });

        });

    </script>



<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"getNonApprovalCompaniesList";
	 
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		  window.location.assign(document.referrer); 
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
			<h1>Company Registration</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
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
				<div class="sccuss-full-updated" id="error-alert">
					<div class="succfully-updated clearfix">

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

			<s:if
				test="%{#session.User.userrole_id.isSuperuser() || #session.Company.companyRole.isDistributor()}">

				<div class="row" id="dash-us-register">

					<input type="hidden" id="thelink" name="Registerid"
						value="<s:property value="Registerid"/>">
					<center>
						<div
							style="font-size: 20px; border: 1px solid #949599; height: 45px; margin: 20px; width: 400px;">
							Link : <a href="#" onclick="adminform();">Register Here</a>
						</div>
					</center>

				</div>

			</s:if>
			<br>
			<center>
				<h2 style="margin-top: 0px;">OR</h2>
			</center>
			<br>

			<div class="clearfix">
				<form action="companyreg" method="post" class="form-horizontal"
					name="myForm" enctype="multipart/form-data" id="myfform">
					<input type="hidden"
						value="<s:property value="%{#session.User.id}"/>"
						id="createdUserId"> <input type="hidden" name="currency"
						value="MYR">


					<div class="companysetup clearfix">
						<div class="companyset-heading">
							<div class="companyset-icon">
								<i class="fa fa-home fa-2x"></i> <b>Company Setup</b>
							</div>
						</div>
						<div class="inner-compreg">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="Username">Company Name</label> <input type="text"
										class="form-control input-sm" id="company" name="Companyname"
										value='<s:property value="Companyname"/>'
										placeholder="Company Name" autocomplete="off" required>

								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<label for="Username"> <s:text name="global.KPPPno"></s:text>
									</label> <input type="text" class="form-control input-sm"
										id="registerNumber" name="registerNumber"
										value='<s:property value="registerNumber"/>'
										placeholder="<s:text name="global.KPPPno" ></s:text>"
										autocomplete="off" required="required">
								</div>
							</div>

							<div class="col-sm-2">
								<div class="form-group">
									<label for="Username"> GSTIn </label> <input type="text"
										class="form-control input-sm" id="companyGstIn"
										name="companyGstIn" value='<s:property value="companyGstIn"/>'
										placeholder="Company Gst In" autocomplete="off"
										required="required">


								</div>
							</div>



							<div class="col-sm-2">
								<div class="form-group">
									<label for="Username">Company Type</label> <select
										class="form-control input-sm" name="companyType"
										id="companyType" autocomplete="off" required>
										<s:if test="%{#session.Company!=null && #session.User!=null}">
											<s:if test="%{#session.User.userrole_id.isSuperuser()}">
												<option selected value="agent">Agency</option>
												<option value="distributor"><s:text
														name="global.Wholesaler"></s:text></option>
												<option value="corporate">Corporate</option>

											</s:if>
											<s:elseif
												test="%{#session.Company.companyRole.isDistributor()}">
												<option selected value="agent">Agency</option>
											</s:elseif>

											<s:elseif test="%{#session.User.userrole_id.isAdmin()}">
												<s:if
													test="%{#session.User.Companyid==#session.Company.companyid}">
													<s:if
														test="%{#session.Company.companyRole.isDistributor()}">
														<option selected value="agent">Agency</option>
													</s:if>
													<s:else>
														<option selected value="agent">Agency</option>
														<option value="distributor"><s:text
																name="global.Wholesaler"></s:text></option>
														<option value="corporate">Corporate</option>
													</s:else>
												</s:if>
											</s:elseif>


										</s:if>

									</select>
								</div>
							</div>


							<div class="col-sm-2">
								<div class="form-group">
									<label for="Username">Wallet Type</label> <select
										class="form-control input-sm" name="typeOfWallet"
										id="typeOfWallet" autocomplete="off" required>

										<s:if test="%{#session.Company!=null && #session.User!=null}">
											<s:if test="%{#session.User.userrole_id.isSuperuser()}">
												<option selected value="">select wallet type</option>
												<option value="Prepaid"><s:text
														name="global.Prepaid"></s:text>
												</option>
												<option value="Postpaid"><s:text
														name="global.Postpaid"></s:text></option>
											</s:if>
											<s:elseif
												test="%{#session.Company.companyRole.isDistributor()}">
												<option value="Prepaid"><s:text
														name="global.Prepaid"></s:text></option>
												<option value="Postpaid"><s:text
														name="global.Postpaid"></s:text></option>
											</s:elseif>
											<s:elseif test="%{#session.User.userrole_id.isAdmin()}">
												<s:if
													test="%{#session.User.Companyid==#session.Company.companyid}">
													<s:if
														test="%{#session.Company.companyRole.isDistributor()}">
														<option value="Prepaid"><s:text
																name="global.Prepaid"></s:text></option>
														<option value="Postpaid"><s:text
																name="global.Postpaid"></s:text></option>
													</s:if>
													<s:else>
														<option selected value="">select wallet type</option>
														<option value="Prepaid"><s:text
																name="global.Prepaid"></s:text></option>
														<option value="Postpaid"><s:text
																name="global.Postpaid"></s:text></option>
													</s:else>
												</s:if>
											</s:elseif>
										</s:if>
									</select>
								</div>
							</div>
							<s:if test="%{#session.Company.companyRole.isSuperUser()}">
								<div class="col-sm-2" style="margin-top: 12px;">
									<div class="form-group">
										<label></label>
										<div class="checkbox">
											<label><input type="checkbox" class="checkMyBox"
												name="demoUser" id="demoUser" value="false">Is Demo
												Account</label>
										</div>
									</div>
								</div>

							</s:if>

							<div class="col-sm-12 row">

								<div class="col-sm-2" id="distributor-type-div"
									style="display: none">

									<div class="form-group">
										<label for="Username">Wholesaler Type</label> <select
											class="form-control input-sm" name="distributorType"
											id="distributorType" autocomplete="off" required>
											<option selected value="ibe">IBE</option>
											<option value="api">API</option>
										</select>
									</div>
								</div>
								<div class="col-sm-2 " id="Wallet-type-div"
									style="display: none">
									<div class="form-group">
										<label for="Username">PostPaid Amount Range</label> <input
											type="text" onkeypress="return isNumberKey(event,this)"
											class="form-control input-sm" id="postAmount"
											name="postAmount" value="<s:property value="postAmount"/>"
											placeholder="Amount" autocomplete="off" maxlength="10">
									</div>
								</div>



								<div class="col-sm-2 corpoate" style="display: none">
									<div class="form-group">
										<label for="Website">Agreement Expiry Date</label> <input
											type="text" class="form-control input-sm"
											id="agreementExpiryDt" name="agreementExpiryDt" value=""
											placeholder="Agreement Expiry Date" autocomplete="off"
											required readonly>

									</div>
								</div>
								<div class="col-sm-2 corpoate" style="display: none">
									<div class="form-group">
										<label for="Website">Flight Time Window Minutes</label> <input
											type="text" class="form-control input-sm"
											id="timewindow" name="flightTimeWindowMinutes" value="0"
											placeholder="Flight Time Window Minutes" autocomplete="off" 
											 onkeypress="return isNumberKey(event,this)">

									</div>
								</div>
								<div class="col-sm-2 corpoate" style="display: none">
									<div class="form-group">
										<label for="Website">Flight Threshold Buffer</label> <input
											type="text" class="form-control input-sm"
											id="thresholdbuffer" name="flightThresHoldBuffer" value="0.0"
											placeholder="Flight Threshold Buffer" autocomplete="off"
											 onkeypress="return isNumberKey(event,this)" >

									</div>
								</div>
								<div class="col-sm-2 corpoate"
									style="display: none; margin-top: 18px;">
									<div class="form-group">
										<div class="checkbox">
											<label><input type="checkbox" name="MyEmailDir"
												class="checkMyBox" id="isMyEmailDir" checked value="true">Is
												My Email Directory</label>
										</div>
									</div>
								</div>


							</div>
						</div>
					</div>





					<div class="companysetup clearfix">
						<div class="companyset-heading">
							<div class="companyset-icon">
								<i class="fa fa-handshake-o fa-2x" aria-hidden="true"></i> <b>Account
									Manager Details</b>
							</div>
						</div>
						<div class="inner-compreg">
							<div class="col-sm-2">
								<div class="form-group">
									<label for="Username">Account Manager</label> <select
										class="form-control input-sm" id="salesPersonUserId"
										name="salesPersonUserId" required="required">
										<option value="">Select Account Manager</option>
										<s:iterator value="salesPersonRecords">
											<option value="${id}">${username}(${email})</option>


										</s:iterator>
									</select>


								</div>
							</div>
							<div class="col-sm-2  clearfix">
								<div class="form-group">
									<label for="Username">Lead Type</label> <select
										class="form-control input-sm" id="leadType"
										name="salesLeadGeneration.leadType" required="required">
										<option value="">Select Lead Type</option>
										<option value="Media">Media</option>
										<option value="Online">Online</option>
										<option value="Brochure">Brochure</option>
									</select>

								</div>
							</div>



							<div class="col-sm-2">
								<div class="form-group">
									<label for="Website">Website</label> <input
										class="form-control input-sm" type="text" name="Website"
										value="<s:property value="Website"/>" id="Website"
										placeholder="http://www.example.com">
								</div>
							</div>
							<input type="hidden" id="username" name="Username"
								value="<s:property value="Username"/>">

							<div class="col-sm-2">
								<div class="form-group">
									<label for="Email">Email</label> <input type="email"
										class="form-control input-sm" name="Email" id="email"
										value="<s:property value="Email"/>" placeholder="Email"
										autocomplete="off" required>
								</div>
							</div>

							<div class="col-sm-2">
								<div class="form-group">
									<label for="password">Password </label> <input
										name="password" id="password" type="password"
										class="form-control input-sm" placeholder="Password"
										autocomplete="off" required> <span id="result"></span>


								</div>
								<div class="col-sm-2 text-left result-pasword">
									<span id="result"></span>
								</div>
							</div>


							<div class="col-sm-2" id="sss">
								<div class="form-group">
									<label for="Username">phone</label>
									<div class="input-group">
										<div class="input-group-btn">
											<select class="form-control input-sm" 
												id="ccc" name="countryCode" required>
												<s:iterator value="CountryList">
													<s:if test="c_name=='India'">
														<option selected="selected"><s:property
																value="isd_code"></s:property></option>
													</s:if>
													<s:if test="c_name !='India'">
														<option>
															<s:property value="isd_code"></s:property></option>
													</s:if>
												</s:iterator>
											</select>
										</div>
										<input type="tel" class="form-control input-sm" name="Phone"
											id="telphone" value="<s:property value="Phone"/>"
											placeholder="Phone" autocomplete="off" maxlength="16"
											required onkeypress="return isNumberKey(event,this)">
									</div>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<label for="Username">City</label> <input type="text"
										class="form-control input-sm" name="City" id="city"
										value="<s:property value="City"/>" placeholder="city"
										autocomplete="off" required>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label for="Country">Country</label> <select
										class="form-control input-sm" name="Countryname" id="country"
										required>
										<s:iterator value="CountryList">
											<s:if test="c_name=='India'">
												<option selected="selected"><s:property
														value="c_name"></s:property></option>
											</s:if>
											<s:if test="c_name !='India'">
												<option><s:property value="c_name"></s:property></option>
											</s:if>
										</s:iterator>

									</select>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label for="Country">State</label> <select
										class="form-control input-sm" name="Statename" id="state"
										required>
										<s:iterator value="StateList">
											<option><s:property value="stateName"></s:property></option>
										</s:iterator>
									</select> <input type="text" name="Statename" id="txtState"
										hidden="hidden">
								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<label for="City">Language</label> <select
										class="form-control input-sm" name="Language" id="Language"
										required>

										<s:iterator value="LanguageList">
											<s:if test="language=='English'">
												<option selected="selected"><s:property
														value="language"></s:property></option>
											</s:if>
											<s:if test="language !='English'">
												<option><s:property value="language"></s:property></option>
											</s:if>

										</s:iterator>

									</select>
								</div>
							</div>
							<div class="col-sm-4  clearfix">
								<div class="form-group">
									<label for="Address">Sales Person Remarks</label>

									<textarea rows="2" class="form-control input-sm"
										id="salesPersonRemarks"
										name="salesLeadGeneration.salesPersonRemarks"
										placeholder="Sales Person Remarks" required><s:property
											value="salesLeadGeneration.salesPersonRemarks" /></textarea>
								</div>

							</div>
							<div class="col-sm-4 clearfix">
								<div class="form-group">
									<label for="salesLeadGeneration.companyRemarks">Company
										Remarks</label>

									<textarea rows="2" class="form-control input-sm"
										id="companyRemarks" name="salesLeadGeneration.companyRemarks"
										placeholder="Company Remarks" required><s:property
											value="salesLeadGeneration.companyRemarks" /></textarea>
								</div>

							</div>

							<div class="col-sm-4 ">
								<div class="form-group">
									<label for="Addresss">Address</label>
									<textarea rows="2" class="form-control input-sm" id="address"
										name="Address" placeholder="Address" required><s:property
											value="Address" /></textarea>
								</div>
							</div>

							<div class="col-sm-12 ">
								<div class="form-group">
									<label for="Addresss">Terms and Conditions</label>
									<textarea class="form-control input-sm" id="temsandconditions"
										name="temsandcondtions" placeholder="Terms and Conditions"
										required><s:property value="termsAndConditions" /></textarea>
								</div>
							</div>

						</div>
					</div>


					<div class="companysetup clearfix">
						<div class="companyset-heading">
							<div class="companyset-icon">

								<i class="fa fa-file-image-o fa-2x"></i> <b>Logo</b>
							</div>
						</div>
						<div class="inner-compreg">



							<div class="form-group">
								<label for="uploadimage" class="col-sm-2 control-label">Upload
									Image</label>
								<div class="col-sm-8">

									<div class="row">
										<div class="col-sm-6 file-upload">

											<figure>
												<!-- <img ng-src="{{imageSrc}}" height="100" width="100"> -->
											</figure>
											<input type="file" id="uploadimage" ngf-pattern="image/*"
												ng-file-select="onFileSelect($files)" name="Imagepath">
											<div>Note - Recommended image specifications (size - max 1 mb, format - jpeg, png)</div>
										</div>

										<!-- ng-file-select="onFileSelect($files)" -->
										<div class="col-sm-6 ">
											<div id="fileinfo">

												<div id="fileError"></div>

											</div>
										</div>
									</div>


								</div>

							</div>
						</div>
					</div>



					<!-- ----------------- company entity --------------- -->

					<div class="companysetup clearfix">
						<div class="companyset-heading">
							<div class="companyset-icon">
								<i class="fa fa-id-badge fa-2x" aria-hidden="true"></i> <b>Add
									Company Entity</b>
							</div>
						</div>
						<div class="inner-compreg">

							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												href="#companyEntity">Company entity <span
												style="color: red; font-size: 12px;">( It is not
													mandatory to add Company entity)</span></a>
										</h4>
									</div>
									<div id="companyEntity" class="panel-collapse collapse">
										<div class="panel-body">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a class="collapsed" data-toggle="collapse"
															data-parent="#accordion" href="#companyEntityDetails">
															Company entity 1 </a>
													</h4>
												</div>

												<div id="companyEntityDetails"
													class="panel-collapse collapse">

													<div class="panel-body">
														<div class="col-sm-3">
															<div class="form-group">
																<label for="companyEntityGstIn" class="  control-label">companyEntityGstIn</label>

																<input type="text" class="form-control input-sm"
																	id="companyEntityGstIn"
																	name="companyEntityList[0].companyEntityGstIn"
																	placeholder="CompanyEntityGstIn" autocomplete="off">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="Companyname" class="  control-label">Companyname</label>

																<input type="text" class="form-control input-sm"
																	id="Companyname"
																	name="companyEntityList[0].Companyname"
																	placeholder="Companyname" autocomplete="off">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="CompanyEntityName" class="  control-label">CompanyEntityName</label>

																<input type="text" class="form-control input-sm"
																	id="CompanyEntityName"
																	name="companyEntityList[0].CompanyEntityName"
																	placeholder="CompanyEntityName" autocomplete="off">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="Email" class=" control-label">Email</label>

																<input type="text" class="form-control input-sm"
																	id="Email" name="companyEntityList[0].Email"
																	placeholder="Email" autocomplete="off">

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="Countryname" class="  control-label">Country</label>

																<select class="form-control input-sm"
																	name="companyEntityList[0].Countryname"
																	id="Countryname">

																	<s:iterator value="CountryList">
																		<s:if test="c_name=='India'">
																			<option selected="selected"><s:property
																					value="c_name"></s:property></option>
																		</s:if>
																		<s:if test="c_name !='India'">
																			<option><s:property value="c_name"></s:property></option>
																		</s:if>
																	</s:iterator>

																</select>

															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="State" class="  control-label">State</label>

																<select class="form-control input-sm"
																	name="companyEntityList[0].State" id="State" required>
																	<s:iterator value="StateList">
																		<option
																			value="<s:property value="stateName"/>-<s:property value="StateCode"/>"><s:property
																				value="stateName"></s:property></option>
																	</s:iterator>

																</select>

																<!-- <input type="text" class="form-control input-sm"
								id="State" name="companyEntityList[0].State"
								placeholder="State" autocomplete="off"
								required> -->
															</div>

														</div>

														<div class="col-sm-3">
															<div class="form-group">
																<label for="City" class="  control-label">City</label> <input
																	type="text" class="form-control input-sm" id="City"
																	name="companyEntityList[0].City" placeholder="City"
																	autocomplete="off">

															</div>
														</div>


														<div class="col-sm-3">
															<div class="form-group">
																<label for="PhoneNo" class=" control-label">phone</label>
																<div class="input-group">
																	<div class="input-group-btn">
																		<select class="form-control input-sm"
																			 id="ccc" name="companyEntityList[0].PhoneNo">
																			<s:iterator value="CountryList">
																				<s:if test="c_name=='India'">
																					<option selected="selected"><s:property
																							value="isd_code"></s:property></option>
																				</s:if>
																				<s:if test="c_name !='India'">
																					<option>
																						<s:property value="isd_code"></s:property></option>
																				</s:if>
																			</s:iterator>
																		</select>
																	</div>
																	<input type="tel" class="form-control input-sm"
																		name="companyEntityList[0].PhoneNo" id="telphone"
																		placeholder="Phone" autocomplete="off">
																</div>
															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="Address1" class=" control-label">Address1</label>
																<textarea class="form-control input-sm" id="Address1"
																	name="companyEntityList[0].Address1"
																	placeholder="Address"> </textarea>
															</div>
														</div>
														<div class="col-sm-3">
															<div class="form-group">
																<label for="Address2" class=" control-label">Address2</label>


																<textarea class="form-control input-sm" id="Address2"
																	name="companyEntityList[0].Address2"
																	placeholder="Address2"> </textarea>


															</div>
														</div>
													</div>
												</div>

											</div>
											<div id="add-entity"></div>
											<div class="col-sm-12 " style="margin-top: 20px;">
												<div class="row">

													<a class="btn btn-primary btn-sm" id="add_companyenity">Add
														Company Enity</a> <a class="btn btn-primary btn-sm"
														id="remove_entity">Remove Enity</a>
												</div>

											</div>

										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- ----------------- company entity ends ---------- -->


					<div class="companysetup clearfix">
						<div class="companyset-heading">
							<div class="companyset-icon">

								<i class="fa fa-address-card fa-2x"></i> <b>Billing Address</b>
							</div>
						</div>
						<div class="inner-compreg">
							<div class="col-sm-4">
								<div class="form-group">
									<label for="Company">Company Name</label> <input type="text"
										class="form-control input-sm" id="Billingcompany"
										name="Billingcompany" placeholder="Company Name"
										value="<s:property value="Billingcompany"/>"
										autocomplete="off" required>
								</div>

							</div>

							<div class="col-sm-4">
								<div class="form-group ">
									<label for="Country">Country</label> <select
										class="form-control input-sm" name="Billingcountry"
										id="Billingcountry" required>

										<s:iterator value="CountryList">
											<s:if test="c_name=='India'">
												<option selected="selected"><s:property
														value="c_name"></s:property></option>
											</s:if>
											<s:if test="c_name !='India'">
												<option><s:property value="c_name"></s:property></option>
											</s:if>
										</s:iterator>

									</select>

								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group ">
									<label for="Country">State</label> <select
										class="form-control input-sm" name="Billingstate"
										id="Billingstate" required>
										<s:iterator value="StateList">
											<option><s:property value="stateName"></s:property></option>
										</s:iterator>
									</select> <input type="text" name="Billingstate" id="txtBillingState"
										hidden="hidden">

								</div>
							</div>
							<div class="col-sm-12 row">

								<div class="form-group">
									<div class="col-sm-8">
										<label for="Address">Address</label>
										<textarea class="form-control input-sm" id="Billingaddress"
											name="Billingaddress" placeholder="Address" required><s:property
												value="Billingaddress" /></textarea>
									</div>
									<div class="col-sm-4" style="margin-top: 35px;">
										<label> <input type="checkbox" class="control-label"
											id="bilAdress"> Same as Above Address?
										</label>
									</div>
								</div>

							</div>
						</div>
					</div>




					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="submit" id="companyReg" class="btn btn-primary btn-lg">Register</button>
						</div>
					</div>
				</form>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<script src="js/app.js" type="text/javascript"></script>
	<%@ include file="DashboardFooter.jsp"%>
	<%--  <script src="js/sales_person_names.js" type="text/javascript"></script>  --%>

	<script type="text/javascript">
	
	
	$(document).ready(function() {
		 $("#myfform").validate({
	    	rules: {
	            "email": {required: true,email: true},
	            "Companyname":{required: true },
	            "registerNumber":{required: true}
	        }, 
	        messages: {"email": {required: "Please, enter an email",email: "Email is invalid"},
	            "Companyname":{required: "Please, enter a company name"},
	            "registerNumber":{required: "Please, enter a PAN Number"}
	        },
	        submitHandler: function (form) { 
	            return false; 
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
	    	

	$('#companyReg').click(function() {
		 $("#myfform").valid();
		 if($("#myfform").valid()){			 	
	     	document.getElementById("myfform").submit();
	     }
	})
	});
		
	
	
	
	
	
	
	
$(function() {
	 $( "#agreementExpiryDt" ).datepicker({
		    changeMonth: true,  
		    changeYear:true,
		    dateFormat: "dd-mm-yy",
		    minDate:0,
		    });
	    
	$("#Billingcompany").focus(function() {
		 $("#Billingcompany").val("");
		});
	$("#Billingaddress").focus(function() {
		 $("#Billingaddress").val("");
		});
	 
	 $("#company,#address").keyup(function() {
		 $("#Billingcompany").val($("#company").val());
		 $("#Billingaddress").val($("#address").val());
		 $("#username").val($("#company").val());
		 
		 
		});
	 
	  $('#companyType').change(function(){
    	 if($('#companyType').val()== 'distributor') {
            $('#distributor-type-div').show(); 
            $(".corpoate").hide();
        } 
        else if($('#companyType').val() == 'agent') {
        	 $('#distributor-type-div').hide(); 
        	  $(".corpoate").hide();
          
       } 
        else if($('#companyType').val() == 'corporate') {
        	  $(".corpoate").show();
        	   $('#distributor-type-div').hide(); 
      } 
    	 
    });
    
    $('#typeOfWallet').change(function(){
   	 if($('#typeOfWallet').val()== 'Postpaid') {
           $('#Wallet-type-div').show();
           $('#postAmount').val("");
        } 
       else if($('#typeOfWallet').val() == 'Prepaid') {
       	 $('#Wallet-type-div').hide(); 
         $('#postAmount').val("");
         
      } 
       
   });
  
 });


$('.checkMyBox').change(function(){ 
    if($(this).is(':checked')){
          $(this).val('true'); 
          $(this).attr('checked','checked');
     }else{
    	  $(this).removeAttr('checked');
          $(this).val('false');
     }
});


 </script>

	<script>
 
 $("#uploadimage" ).change(function() {
	   var  fileExtension = $('#uploadimage').val().substr(($('#uploadimage').val().lastIndexOf('.') + 1));
		if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
		{
			 
		}
		else{
			  alert("Please select Image File.");
			  reset_form_element($('#uploadimage'));
			    e.preventDefault();
			  
		}
});
function reset_form_element (e) {
  e.wrap('<form>').parent('form').trigger('reset');
  e.unwrap();
}
 </script>

	<script>
 
 function adminform(){
 var data = $('#thelink').val();
  var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"RegiserUs";
	  
  var mapForm = document.createElement("form");
    mapForm.target = "Map";
    mapForm.method = "get"; // or "post" if appropriate
    mapForm.action = finalUrl;/* "http://stage.mylintas.com/TravelAdmin/RegiserUs"; */
 	 var mapInput = document.createElement("input");
 	 //alert(mapInput);
    mapInput.type = "text";
    mapInput.name = "Registerid";
    mapInput.value = data;
    mapForm.appendChild(mapInput);
 	document.body.appendChild(mapForm);

    // map = window.open();
    mapForm.submit();

 }
 $(function() {
 var data = $('#thelink').val();
 var finalUrl = newUrl+"RegiserUs?Registerid="+data;
 $("#link").text(finalUrl);
 });
 
 
</script>
	<script>

$('#add_companyenity').on('click', function() { 
	 	var $entity = $('#companyEntity .panel-default:first').clone();
 			  $('#add-entity').append($entity); 
			var noOfEntitys = $('#add-entity .panel-default').length; 
		 for (var i = noOfEntitys; i <= noOfEntitys; i++) {
			 $entity.find("h4.panel-title a:first").text("Company entity" + (i+1)); 
			 $entity.find("h4.panel-title a[href='#companyEntityDetails']").attr("href", "#companyEntityDetails" + (i));
			 $entity.find("div#companyEntityDetails").attr("id", "companyEntityDetails" + (i)); 
			 $entity.find(".panel-body .form-group input[name^=companyEntityList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				});
			 $entity.find(".panel-body .form-group select[name^=companyEntityList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				}); 
			 $entity.find(".panel-body .form-group textarea[name^=companyEntityList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				}); 
		 }
		 
		 
	 	  
});

$('#remove_entity').on('click', function() {
	
	$('#add-entity > .panel-default:last').remove();
	 
	var count=	$ ('#add-entity > .panel-default').val();
	/* if(count>0){
		count=count-1;
		$ ('#tripDetailCount').val(count);
	}
	else{
		$ ('#tripDetailCount').val(count);
	} */
});



</script>


</body>

</html>
