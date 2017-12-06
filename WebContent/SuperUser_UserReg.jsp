<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<%-- <script src="js/angular.js" type="text/javascript"></script>


<script
	src="js/jquery.min.js"></script> --%>

<link rel="stylesheet" href="css/alert.css">
<style type="text/css">
#register {
	margin-left: 100px;
}
.emailClr{
color: #000;
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
</style>
<script type="text/javascript">

$(function () {
	 $('#firstname123').keydown(function (e) {
	 if (e.shiftKey || e.ctrlKey || e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 });
$(function () {
	 $('#lastname1234').keydown(function (e) {
	 if (e.shiftKey || e.ctrlKey || e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 });

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
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "superUser_UserList";
		$('#success').click(function() {
			 $("#uploadfile").css("display", "none");
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			 $("#uploadfile").css("display", "none");
			$('#error-alert').hide();

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
	
	$(function() {
	    $('#username').keydown(function(e) {
	        if (e.keyCode == 32) // 32 is the ASCII value for a space
	            e.preventDefault();
	    });
	    $("#firstname").on("keypress", function(e) {
	        if (e.which === 32 && !this.value.length)
	            e.preventDefault();
	    });
	    $("#lastname").on("keypress", function(e) {
	        if (e.which === 32 && !this.value.length)
	            e.preventDefault();
	    });
	    $("#password").on("keypress", function(e) {
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
	    
	    
	    $("#telphone").on("keypress", function(e) {
	        if (e.which === 32 && !this.value.length)
	            e.preventDefault();
	    });
	    
	     $("#Description").on("keypress", function(e) {
	        if (e.which === 32 && !this.value.length)
	            e.preventDefault();
	    });
	    
	 });
	 
	 
	
	
	
	
	
	
</script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var currency_list = [];
						$('#country')
								.change(
										function() {
											var country = $('#country').val();
											$
													.ajax({
														//Action Name
														url : "currencyJson",
														dataType : "json",
														success : function(
																data,
																textStatus,
																jqXHR) {
															$
																	.each(
																			data.currencyMap,
																			function(
																					key,
																					value) {
																				if (country != "0") {
																					if (key == country) {
																						console
																								.log(key
																										+ ": "
																										+ value);
																						document
																								.getElementById('currency').value = value;
																						document
																								.getElementById('Billingcountry').value = country;

																					}
																				} else {
																					document
																							.getElementById('currency').value = "0";
																					document
																							.getElementById('Billingcountry').value = "0";
																				}

																			});

															/* console.log(data.currencyMap); */

														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															console
																	.log("-------Error-----status-----------"
																			+ textStatus);
															console
																	.log("-------jqXHR--- -----------"
																			+ jqXHR);
															console
																	.log("-----errorThrown-----------"
																			+ errorThrown);
														}
													});

										});
					});
</script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Employee Registration</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>

		<!-- Main content -->
		<section class="content">

			<!-- Small boxes (Stat box) -->
			<%-- <div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a> <a href="superUser_UserList"
						class="btn btn-top-link btn-xs"><span class="pull-right">
							Employee List</span></a>

				</h4>
			</div> --%>
<div class="companysetup clearfix"> 
					<div class="companyset-heading"> 
						<div class="companyset-icon"> 
						<i class="fa fa-upload fa-2x" aria-hidden="true"></i> 
								 <b>Upload Employee</b>  
						</div> 
					</div>
					<div class="inner-compreg">
					
										<label for="uploadimage" class="col-sm-2 control-label">Upload
						File </label>
					<form action="doExcelUpload" method="post" id="uploadform"
						enctype="multipart/form-data">
						<div class="col-sm-6">
							<div class="row">
								<div class="col-sm-6 file-upload">
									<input type="file" id="filePath"
										accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
										ng-file-select="onFileSelect($files)" name="excelFile">
								</div>
								<div class="col-sm-6 ">
									<div id="fileinfo">
										<div id="fileError"></div>

									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="inline">
								<button type="button" id="save" class="btn btn-primary btn-xs" onclick="validate();">Upload</button>
								 <img alt="" src="images/divloading.gif" id="uploadfile" style="display: none;background: #CC5D00;width: 25px; height: 25px;" >
							</div>
							<div class="inline">
							<a class="btn btn-primary btn-xs" href="downloadSampleEmployeeExcelFileDownload?fileName=TestingEmployeeData.xlsx"
										class="btn btn-success btn-xs" >Download Sample Excel File</a>
										</div>
						</div>
						
							
								
					</form>
					
					</div>
					</div>
			<!-- <div align="center" style="font-size: 15px;">Upload Employee
				Excel File</div> -->
<!-- 			<div class="clearfix" style="border: green 1px solid;position: relative;padding-top: 12px;">
				<div class="form-group">

					<label for="uploadimage" class="col-sm-2 control-label">Upload
						File </label>
					<form action="doExcelUpload" method="post" id="uploadform"
						enctype="multipart/form-data">
						<div class="col-sm-6">
							<div class="row">
								<div class="col-sm-6 file-upload">
									<input type="file" id="filePath"
										accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
										ng-file-select="onFileSelect($files)" name="excelFile">
								</div>
								<div class="col-sm-6 ">
									<div id="fileinfo">
										<div id="fileError"></div>

									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="inline">
								<button type="button" id="save" class="btn btn-primary btn-xs" onclick="validate();">Upload</button>
								 <img alt="" src="images/divloading.gif" id="uploadfile" style="display: none;background: #CC5D00;width: 25px; height: 25px;" >
							</div>
							<div class="inline">
							<a class="btn btn-primary btn-xs" href="downloadSampleEmployeeExcelFileDownload?fileName=TestingEmployeeData.xlsx"
										class="btn btn-success btn-xs" >Download Sample Excel File</a>
										</div>
						</div>
						
							
								
					</form>
				</div>
			</div> -->

			<div align="center" style="font-size: 15px;">OR</div>
			 
 
			<s:if test="hasActionErrors()">
				<div class="succfully-updated clearfix" id="error-alert">

					<div class="col-sm-2">
						<i class="fa fa-close fa-3x red"></i>
					</div>

					<div class="col-sm-10">

						<p>
						<s:actionerror />
						<s:iterator var="studentEntry" value="existedEmailMap.entrySet()">
						 <p>  
                          Employee Id : <s:property value="%{#studentEntry.getKey()}"/>,
                          Email Address : <s:property value="%{#studentEntry.getValue().emailId}"/>,
                          Message : <s:property value="%{#studentEntry.getValue().errorMessage}"/></p>
                       </s:iterator>			
							
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
				<form id="addSuperUsuer_userForm" action="addSuperUsuer_user" method="post"
					class="form-horizontal" name="myForm" enctype="multipart/form-data"
					id="myfform">
					<s:if test="hasActionErrors()">
						<div class="success-alert" style="display: none">
							<span class="fa fa-thumbs-o-up fa-1x"></span>
							<s:actionerror />
						</div>
					</s:if>
					<s:if test="hasActionMessages()">
						<div class="success-alert" style="display: none">
							<span class="fa fa-thumbs-o-up fa-1x"></span>
							<s:actionmessage />
						</div>
					</s:if>
					<input type="hidden" name="typeOfWallet" value="Prepaid"> 
				<div class="companysetup clearfix"> 
					<div class="companyset-heading"> 
						<div class="companyset-icon"> 
						<i class="fa fa-user fa-2x" aria-hidden="true"></i> 
								 <b>Register Employee</b>  
						</div> 
					</div>
				<div class="inner-compreg">  
						<div class="col-sm-2">
							<div class="form-group">
								<label for="Username"> Employee ID </label> <input type="text"
									class="form-control input-sm" id="username" name="Username"
									placeholder="User ID " autocomplete="off" required
									maxlength="30">
							</div>
						</div>

						<div class="col-sm-2">
							<div class="form-group">
								<label for="first-name">First Name</label> <input type="text"
									class="form-control input-sm" maxlength="30" id="firstname"
									name="Firstname" placeholder="First Name" autocomplete="off"
									required>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="last-name">Last Name</label> <input type="text"
									class="form-control input-sm" id="lastname" name="Lastname"
									placeholder="Last Name" maxlength="30" autocomplete="off"
									required>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="password">Password <span id="result"></span></label> <input name="password"
									id="password" type="password" class="form-control input-sm"
									placeholder="Password" autocomplete="off" required>
							</div>
							<%-- <div class="col-sm-2 text-left result-pasword">
								<span id="result"></span>
							</div> --%>
						</div>
					 
						<div class="col-sm-2">
							<div class="form-group">
								<label for="Email">Email</label> <input type="email"
									class="form-control input-sm emailClr" name="Email" id="email"
									placeholder="Email" autocomplete="off" required>
							</div>
						</div>
						 
						<div class="col-sm-2">
							<div class="form-group">
								<label for="telphone">Phone</label> <input type="tel"
									class="form-control input-sm" name="Mobile" id="telphone"
									value="<s:property value="Phone"/>" placeholder="Phone"
									autocomplete="off" required
									onkeypress="return isNumberKey(event,this)">
							</div>
						</div>
 
						<div class="col-sm-2">
							<div class="form-group">
								<label for="City">City</label> <input type="text"
									class="form-control input-sm" id="city" name="City"
									placeholder="City" autocomplete="off" required>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="Country">Country</label> <select
									class="form-control input-sm" name="Countryname" id="country"
									autocomplete="off" required>
									<s:iterator value="CountryList">
										<%-- <option value="<s:property value="c_name"/>"><s:property value="c_name"></s:property></option> --%>
										<c:choose>
											<c:when test="${c_name=='India'}">
												<option value="<s:property value="c_name"/>" selected>
													<s:property value="c_name" /></option>
											</c:when>
											<c:otherwise>
												<option value="<s:property value="c_name"/>">
													<s:property value="c_name" /></option>
											</c:otherwise>
										</c:choose>
									</s:iterator>
								</select>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="City">Language</label> <select
									class="form-control input-sm" name="Language" id="Language"
									required>

									<option selected="selected" value="">Select Language</option>
									<s:iterator value="LanguageList">
										<c:choose>
											<c:when test="${language=='English'}">
												<option value="<s:property value="language"/>" selected>
													<s:property value="language" /></option>
											</c:when>
											<c:otherwise>
												<option value="<s:property value="language"/>">
													<s:property value="language" /></option>
											</c:otherwise>
										</c:choose> 
									</s:iterator> 
								</select>
							</div>
						</div>
					 
						<div class="col-sm-4">
							<div class="form-group">
								<label for="Country"> Employee Role</label> <select
									class="form-control input-sm" id="userroletype"  
									name="userroletype" multiple="multiple" required>
									<option value="">Please select Employee Role</option>
									<s:if
										test="%{#session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isCorporate()==false}">
										<option value="admin">Admin</option>
										<option value="report">Accounts</option>
										<option value="order">Operations</option>
										<option value="demo">Demo User</option>
										<option value="cms">CMS</option>
										<option value="tech_head">Technical Head</option>
										<option value="tech_support">Technical Support</option>
										<option value="traveldesk">TravelDesk</option>
									</s:if>
									<s:elseif test="%{#session.User.userRole.isTechHead()==true}">
										<option value="tech_support">Technical Support</option>
									</s:elseif>
									<s:else>
										<s:if
											test="%{#session.User.userRole.isDemoUser()==true || #session.Company.companyRole.isCorporate()==true">
											<option value="corporateemployee" selected="selected">Employee</option>
										</s:if>
										<s:else>
											<option value="admin">Admin</option>
											<option value="report">Accounts</option>
											<option value="order">Operations</option>
											<s:if
												test="%{#session.Company.companyRole.isCorporate()==true}">
												<option value="corporateemployee" selected="selected">Employee</option>
											</s:if>
										</s:else>
									</s:else>
								</select>
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="form-group">
								<label for="Address">Address</label>

								<textarea class="form-control input-sm" id="address"
									name="Address" placeholder="Address" autocomplete="off"
									required></textarea>

							</div>
						</div>
						<div class="col-sm-4">
						<div class="form-group">
							<label for="uploadimage"  >Upload Profile
								Image</label>
								<div class="row">
									<div class="col-sm-6 file-upload"> 
										<input type="file" id="uploadimage" accept="file_extension"
											ng-file-select="onFileSelect($files)" name="Imagepath">
									</div>

									<div class="col-sm-6 ">
										<div id="fileinfo">

											<div id="fileError"></div> 
										</div>
									</div>
								</div>
						 
							</div>
						</div>
				<!-- 	</div> -->
					<!-- <div class="row">
						<div class="col-sm-12"> 
							<p class="h4">
								<b>Profile Image</b>
							</p> 
						</div>

						<div class="form-group">
							<label for="uploadimage" class="col-sm-2 control-label">Upload
								Image</label>
							<div class="col-sm-8">

								<div class="row">
									<div class="col-sm-6 file-upload"> 
										<input type="file" id="uploadimage" accept="file_extension"
											ng-file-select="onFileSelect($files)" name="Imagepath">
									</div>

									<div class="col-sm-6 ">
										<div id="fileinfo">

											<div id="fileError"></div> 
										</div>
									</div>
								</div>
							</div>
						</div>
					</div> -->
					</div>
					</div>
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<!-- <button type="submit" class="btn btn-primary btn-lg">Register</button> -->
							<button id="regSubmit" type="button" class="btn btn-primary btn-lg">Register</button>
						</div>
					</div>
				</form>
			</div>







						<%-- 	<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Wallet Type</label>
						<div class="col-sm-8">
									<select class="form-control input-sm" name="typeOfWallet"
								id="typeOfWallet" autocomplete="off"   required>
								
								<s:if test="%{#session.Company!=null && #session.User!=null}">
									<s:if test="%{#session.User.userrole_id.isSuperuser()}">
									<option selected value="" >select wallet type</option>
								<option  value="Prepaid"><s:text name="global.Prepaid" ></s:text></option>
								<option value="Postpaid"><s:text name="global.Postpaid" ></s:text></option>
								</s:if>
								<s:elseif test="%{#session.User.userrole_id.isUsermode()}">
									<s:if test="%{(#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()) && #session.User.agentWallet.walletType=='Prepaid'}">
									<option  value="Prepaid"><s:text name="global.Prepaid" ></s:text></option>
									
									</s:if>
									<s:else>
									<option value="Postpaid"><s:text name="global.Postpaid" ></s:text></option>
									</s:else>
									 </s:elseif>
									<s:if test="%{#session.User.userrole_id.isAdmin()}">
										<s:if
											test="%{#session.User.Companyid==#session.Company.companyid}">
											<s:if test="%{#session.User.userrole_id.isUsermode()}">
									<s:if test="%{(#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()) && #session.User.agentWallet.walletType=='Prepaid'}">
									<option  value="Prepaid"><s:text name="global.Prepaid" ></s:text></option>
									
									</s:if>
									<s:else>
									<option value="Postpaid"><s:text name="global.Postpaid" ></s:text></option>
									</s:else>
									 </s:if>
									 <s:else>
											<option selected value="">select wallet type</option>
												<option  value="Prepaid"><s:text name="global.Prepaid" ></s:text></option>
												<option value="Postpaid"><s:text name="global.Postpaid" ></s:text></option>
											</s:else>
										</s:if>
									</s:if>
								 </s:if>
							</select>
						</div>
					</div> --%>

						<%-- <div class="col-sm-4" id="Wallet-type-div" style="display: none">
						<div class="form-group" >
						<label for="Username"  >Postpaid Amount Range</label>
						 
						<input   type="number"   onkeypress="return isNumberKey(event,this)" class="form-control input-sm" id="postAmount"
								name="postAmount"  value="<s:property value="postAmount"/>"
								placeholder="Amount" autocomplete="off" >
							 
						</div>
					</div> --%>

	
	
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
        <h4 class="modal-title" id="myModalLabel">Alert !</h4>
      </div>
      <div class="modal-body clearfix">
          <p id="desc">
     
      </p>
         
      </div>
     
    </div>
  </div>
</div>
	<script src="js/app.js" type="text/javascript"></script>
	<!--optinal-->
	<%@ include file="DashboardFooter.jsp"%>
	<script>
	
		   
		    
		 
	 
	
 
 $("#uploadimage" ).change(function() {
	   var  fileExtension = $('#uploadimage').val().substr(($('#uploadimage').val().lastIndexOf('.') + 1));
		if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
		{
			 
		}
		else{
			  alert("Please select Image File.");
			  reset_form_element( $('#uploadimage') );
			    e.preventDefault();
			  
		}
});
function reset_form_element (e) {
  e.wrap('<form>').parent('form').trigger('reset');
  e.unwrap();
}
function validate() { 
	
	 var _validFileExtensions = [".csv", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel",];    
	     var oForm = $("#uploadform #filePath");
	    
	     var arrInputs =  $("#uploadform #filePath");
	     for (var i = 0; i < arrInputs.length; i++) {
	         var oInput = arrInputs[i];
	         if (oInput.type == "file") {
	             var sFileName = oInput.value;
	             if (sFileName.length > 0) {
	            	 $("#uploadfile").css("display", "inline-block");
	        		 $('form#uploadform').submit();
	             }
	             else{
	            	 $("#uploadfile").css("display", "none");	            	
	            	 $("#emailModal").modal('show');		    		
		        	 $("#emailModal #desc").text("Select your employee excel file.");
	            	 }
	             }
	         }
}
$(document).ready(function(){
	  
	   $('#regSubmit').click(function(){
		   $("#addSuperUsuer_userForm").valid();
		   if($("#addSuperUsuer_userForm").valid()){
			   document.getElementById("addSuperUsuer_userForm").submit();
		   }
	   });
	 		   
	   $("#addSuperUsuer_userForm").validate({
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
	   })
});
 </script>
</body>

</html>