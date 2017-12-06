
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>

  <script src="js/angular.js" type="text/javascript"></script>
 <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 
<script src="js/sales_person_names.js" type="text/javascript"></script>
  <link href="css/bootstrap.css" rel="stylesheet">
  <link href="css/Tayyarahadmin-lintas.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	rel="stylesheet">
<link href="css/admin-lintas.css" rel="stylesheet">
<link rel="stylesheet" href="css/alert.css">
<style type="text/css">

#register {
	margin-left:100px;	
}
#register label{
	margin-right:5px;
}
.register{
background: #fff;
text-align: center;
}
.register h4{
margin-bottom: 0px;

}
#register input {
	padding:5px 7px;
	border:1px solid #d5d9da;
	box-shadow: 0 0 5px #e8e9eb inset;
	width:250px;
	font-size:1em;
	outline:0;
}

 #dash-us-register .control-label{
 		font-size: 12px;
 }
 #register{
  font-size: 10px; 
 }
    
.short{
	color:#FF0000;
}

 .weak{
	color:#E66C2C;
}

 .good{
	color:#2D98F3;
}

 .strong{
	color:#006400;
	}
	.content{
	padding-top: 0px;
	}
</style>
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	//  var finalUrl = newUrl+"getApprovalCompaniesList";
	$('#success').click(function() {
	// window.location.assign(finalUrl); 
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
							   });
 		        });
	 
 </script>

 

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
	  var finalUrl = newUrl+"Success.jsp";
	  
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

<%-- 
	<header>
		<nav class="navbar navbar-default" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="container">
				<div class="navbar-header col-sm-3">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-ex1-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="logo">
						<h1>
							<a href="#"><img src="<s:text name="global.indexlogo" ></s:text>"
								class="img-responsive"></a>
						</h1>
					</div>



				</div>

				<div class="col-sm-9">
					 
				</div>
			</div>
			</div>
		</nav>
	</header> --%>
	<!-- Content Wrapper. Contains page content -->
	<div id="common">
		<!-- Content Header (Page header) -->
		<div class="register  ">
						<h1 style="margin: 0px;padding: 10px 0px;">
							<a href="https://www.tayyarah.com/"><img src="images/tayarrah-admin.png" class="img-responsive" style="width: 150px;height: auto; display: inline-block;"></a>
						</h1>
			<h4 class="h2 ">Company Registration</h4>
		</div>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			
			<s:if test="hasActionErrors()">
			<div class="sccuss-full-updated" id="error-alert">
				<div class="succfully-updated clearfix" >

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
			<div class="row" id="dash-us-register">
				<form action="Externalcompanyreg" id="myfform" method="post" class="form-horizontal"
					name="myForm" enctype="multipart/form-data">
					<input type="hidden" name="currency" value="MYR">
					<input type="hidden"
					value="<s:property value="%{#session.User.id}"/>"
					id="createdUserId">
					
					<div class="companysetup clearfix text-left"> 
						<div class="companyset-heading"> 
							<div class="companyset-icon"> 
							<i class="fa fa-home fa-2x" aria-hidden="true"></i> 
									 <b>Company Setup</b>  
							</div> 
						</div>
						<div class="inner-compreg"> 
						<div class="col-sm-2">
							
											<div class="form-group">
						<label for="Username" class=" control-label">Company
							Name</label>
						 
							<input type="text" class="form-control input-sm" id="company"
								name="Companyname" value='<s:property value="Companyname"/>'
								placeholder="Company Name" autocomplete="off" required    >

						</div>
					</div>
					<div class="col-sm-2">
				 <div class="form-group">
						<label for="Username" class=" control-label"> <s:text name="global.KPPPno" ></s:text>
							 </label>
						 
							<input type="text" class="form-control input-sm" id="registerNumber"
								name="registerNumber" value='<s:property value="registerNumber"/>'
								placeholder="KKKP number" autocomplete="off" required="required"> <!-- (Note : for lintas use only) -->

						</div>
					</div>
				 <div class="col-sm-2">
					<div class="form-group">
						<label for="Username" class=" control-label">Company
							Type</label>
						
							<select class="form-control input-sm" name="companyType"
								id="companyType" autocomplete="off" required>
								<s:if test="%{#session.Company!=null && #session.User!=null}">
									<s:if test="%{#session.User.userrole_id.isSuperuser()}">
										<option selected value="agent">Agency</option>
										<option value="distributor"><s:text name="global.Wholesaler" ></s:text></option>
										<!-- <option value="corporate">Corporate</option> -->
										 
									</s:if>
									<s:elseif
										test="%{#session.Company.companyRole.isDistributor()}">
										<option selected value="agent">Agency</option>
									</s:elseif>

									<s:elseif test="%{#session.User.userrole_id.isAdmin()}">
										<s:if
											test="%{#session.User.Companyid==#session.Company.companyid}">
											<s:if test="%{#session.Company.companyRole.isDistributor()}">
												<option selected value="agent">Agency</option>
											</s:if>
											<s:else>
												<option selected value="agent">Agency</option>
												<option value="distributor">Wholesaler</option>
												<option value="corporate">Corporate</option>
											</s:else>
										</s:if>
									</s:elseif>


								</s:if>

							</select>
						</div>
					</div>
					
					<div class="col-sm-2" id="distributor-type-div"
						style="display: none">
					
					<div class="form-group" >
						<label for="Username" class=" control-label">Wholesaler
							Type</label>
						 
							<select class="form-control input-sm" name="distributorType"
								id="distributorType" autocomplete="off" required>
								<option selected value="ibe">IBE</option>
								<option value="api">API</option>
							</select>
						</div>
					</div>
<div class="col-sm-2">
					<div class="form-group">
						<label for="Username" class=" control-label">Wallet Type</label>
						
							<select class="form-control input-sm" name="typeOfWallet"
								id="typeOfWallet" autocomplete="off"   required>
								
								<s:if test="%{#session.Company!=null && #session.User!=null}">
									<s:if test="%{#session.User.userrole_id.isSuperuser()}">
									<option selected value="" >select wallet type</option>
								<option  value="Prepaid">Prepaid</option>
								<option value="Postpaid">Postpaid</option>
								</s:if>
								<s:elseif
										test="%{#session.Company.companyRole.isDistributor()}">
									<option  value="Prepaid">Prepaid</option>
									<option value="Postpaid">Postpaid</option>
									</s:elseif>
									<s:elseif test="%{#session.User.userrole_id.isAdmin()}">
										<s:if
											test="%{#session.User.Companyid==#session.Company.companyid}">
											<s:if test="%{#session.Company.companyRole.isDistributor()}">
												<option  value="Prepaid">Prepaid</option>
												<option value="Postpaid">Postpaid</option>
											</s:if>
											<s:else>
											<option selected value="" >select wallet type</option>
												<option  value="Prepaid">Prepaid</option>
												<option value="Postpaid">Postpaid</option>
											</s:else>
										</s:if>
									</s:elseif>
								 </s:if>
							</select>
						</div>
					</div>
					<div class="col-sm-2" id="Wallet-type-div" style="display: none">
				<div class="form-group" >
						<label for="Username" class="col-sm-2 control-label">Postpaid Amount Range</label>
						<input   type="number"   onkeypress="return isNumberKey(event,this)" class="form-control input-sm" id="postAmount"
								name="postAmount"  value="<s:property value="postAmount"/>"
								placeholder="Amount" autocomplete="off" >
							 
						</div>
					</div>
					
						</div>
					</div>
											
					

				<div class="companysetup clearfix text-left"> 
						<div class="companyset-heading"> 
							<div class="companyset-icon"> 
							<i class="fa fa-handshake-o fa-2x" aria-hidden="true"></i> 
									 <b>Sales Person Details</b>  
							</div> 
						</div>
						<div class="inner-compreg"> 
							<div class="col-sm-2">		 
					<div class="form-group" >
						<label for="Username" class="  control-label">Sales Person Name</label>
						
						<input   type="text"   class="form-control input-sm" id="sales_person_name"
								name="salesPersonUserId"  
								placeholder="Sales Person Name" autocomplete="on" >
						</div>
					</div>
						<div class="col-sm-2">
					<div class="form-group" >
						<label for="Username" class=" control-label">Lead Type</label>
					
						<select class="form-control input-sm" id="leadType" name="salesLeadGeneration.leadType" required="required" >
						<option  value="">Select Lead Type</option>
						<option  value="Media">Media</option>
						<option value="Online">Online</option>
						<option value="Brochure">Brochure</option>
						</select>
							 
						</div>
					</div>
					<div class="col-sm-3">
					<div class="form-group">
						<label for="Address" class=" control-label">Sales Person Remarks</label>
						
							<textarea rows="1" class="form-control input-sm" id="salesPersonRemarks"
								name="salesLeadGeneration.salesPersonRemarks"   placeholder="Sales Person Remarks" required><s:property value="salesLeadGeneration.salesPersonRemarks"/></textarea>
						</div>
						 
					</div>
					<div class="col-sm-3">
					<div class="form-group">
						<label for="salesLeadGeneration.companyRemarks" class=" control-label">Company Remarks</label>
						
							<textarea rows="1" class="form-control input-sm" id="companyRemarks"
								name="salesLeadGeneration.companyRemarks"   placeholder="Company Remarks" required><s:property value="salesLeadGeneration.companyRemarks"/></textarea>
								
								 
						</div>
						 
					</div>
				
					</div>
					</div>
					

				 
				 
				 <div class="companysetup clearfix text-left"> 
						<div class="companyset-heading"> 
							<div class="companyset-icon"> 
							<i class="fa fa-building fa-2x" aria-hidden="true"></i> 
									 <b>Company Details</b>  
							</div> 
						</div>
						<div class="inner-compreg"> 
						<div class="col-sm-2">					
					<div class="form-group">
						<label for="Website" class=" control-label">Website</label>
						
							<input class="form-control input-sm"   type="text" name="Website"
								value="<s:property value="Website"/>"
								placeholder="http://www.example.com" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
						<label for="Username" class=" control-label">
						User ID </label> 
							<input type="text" class="form-control input-sm" id="username"
								name="Username" value="<s:property value="Username"/>"
								placeholder="User ID " autocomplete="off" required  >
						</div>
					</div>
					<div class="col-sm-2"> 
 								<div class="form-group">
								<label for="password" class=" control-label">Password
									 <span id="result"></span></label> 
											<input name="password" id="password" type="password"  
											class="form-control input-sm"  placeholder="Password" autocomplete="off"   required>
								
								</div>
								 
							</div>
							<div class="col-sm-2">
					<div class="form-group">
						<label for="Email" class=" control-label">Email</label>
						 
							<input type="email" class="form-control input-sm" name="Email"
								id="email" value="<s:property value="Email"/>"
								placeholder="Email" autocomplete="off" required>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label for="Username" class=" control-label">Location</label>
						 
							<input type="text" class="form-control input-sm" name="location"
								id="location" value="<s:property value="location"/>"
								placeholder="location" autocomplete="off"   required >
						</div>
					</div>
					 

<div class="col-sm-2">
					<div class="form-group">
						<label for="Country" class=" control-label">Country</label>
						 
							<select class="form-control input-sm" name="Countryname"
								id="country" required>
							 
								<option selected  value="">Select Country</option>
								<s:iterator value="CountryList">
									<option><s:property value="c_name"></s:property></option>
								</s:iterator>

							</select>
						</div>
					</div>
					 
					<div class="col-sm-2">
					<div class="form-group">
						<label for="City" class="control-label">Language</label> 
							<select class="form-control input-sm" name="Language"
								id="Language" required> 
								<option selected="selected" value="">Select Language</option>
								<s:iterator value="LanguageList">
								<option value='<s:property value="language"/>'>
								<s:property value="language" /></option>
								</s:iterator>

							</select>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label for="Username" class=" control-label">City</label> 
							<input type="text" class="form-control input-sm" name="City"
								id="city" value="<s:property value="City"/>" placeholder="city"
								autocomplete="off" required>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label for="telphone" class=" control-label">Phone</label>
							<input type="tel" class="form-control input-sm" name="Phone"
								id="telphone" value="<s:property value="Phone"/>"
								placeholder="Phone" autocomplete="off"  required onkeypress="return isNumberKey(event,this)" >
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label for="Address" class=" control-label">Address</label>
						 
							<textarea rows="1" class="form-control input-sm" id="address"
								name="Address" placeholder="Address" required><s:property
									value="Address" /></textarea>

						</div>
					</div>
					
					
						</div>
						</div>
						
				 <div class="companysetup clearfix text-left"> 
						<div class="companyset-heading"> 
							<div class="companyset-icon"> 
							<i class="fa fa-file-image-o fa-2x" aria-hidden="true"></i> 
									 <b>Logo</b>  
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
									<input type="file" id="uploadimage" accept="image/*" ng-file-select="onFileSelect($files)" name="Imagepath"
										 > 
								</div>
							
 
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
				 
				 
<div class="companysetup clearfix text-left"> 
						<div class="companyset-heading"> 
							<div class="companyset-icon"> 
							<i class="fa fa-address-card fa-2x" aria-hidden="true"></i> 
									 <b>Billing Address</b>  
							</div> 
						</div>
						<div class="inner-compreg"> 
						
						<div class="col-sm-2">
							<div class="form-group">
								<label for="Company" class=" control-label">Company</label> 
								<input type="text"   class="form-control input-sm" id="company"
								name="Billingcompany" placeholder="Company Name"
								value="<s:property value="Billingcompany"/>" autocomplete="off"
								required>
						</div>
					</div>
							
						<div class="col-sm-2">			
					<div class="form-group ">
						<label for="Country" class=" control-label">Country</label> 
							<select class="form-control input-sm" name="Billingcountry"
								id="Billingcountry" required>
								<option selected value="0">Select Country</option> 
								<s:iterator value="CountryList" >								
									<option><s:property value="c_name"></s:property></option>
								</s:iterator> 
							</select>
						</div>
					</div>
					<div class="col-sm-4">
							<div class="form-group">
								<label for="Address" class=" control-label">Address</label> 
								<textarea rows="1" class="form-control input-sm" id="Billingaddress"
								name="Billingaddress"   placeholder="Address" required><s:property
									value="Billingaddress" /></textarea>
							</div> 
					</div> 
					<div class="col-sm-3" style="margin-top: 25px;"> 
						<input type="checkbox" class="control-label" id="bilAdress">
					Same as Above Address? 
					</div>
					
						</div>
						</div> 
						
						
 
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="submit" class="btn btn-primary btn-lg">Register</button>
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
	

	<script type="text/javascript">
$(function() {
  
    $('#companyType').change(function(){
    	 if($('#companyType').val()== 'distributor') {
            $('#distributor-type-div').show(); 
        } 
        else if($('#companyType').val() == 'agent') {
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
 </script>







</body>

</html>
