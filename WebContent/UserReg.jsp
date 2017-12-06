<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>

<%-- <script src="js/angular.js" type="text/javascript"></script>
<link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>  
   <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>  
   <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"> </script>
 --%>
<link rel="stylesheet" href="css/alert.css">
<style type="text/css">

#register {
	margin-left:100px;	
}
#register label{
	margin-right:5px;
}
#register input {
	padding:5px 7px;
	border:1px solid #d5d9da;
	box-shadow: 0 0 5px #e8e9eb inset;
	width:250px;
	font-size:1em;
	outline:0;
}

#result{
	margin-left:5px;
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
</style>
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"superUser_UserList";
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
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
		 $('#lastname234').keydown(function (e) {
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
	 

</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<div class="sccuss-full-updated" id="success-alertdelete"
			style="display: none">
			<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-check fa-3x"></i>
			</div>

			<div class="col-sm-10" id="messagedelete"></div>
			<button type="button" id="successdelete" class="btn btn-primary">Ok</button>

			</div>
		</div>
		<div class="sccuss-full-updated" id="failed-alertdelete"
		style="display: none">
		<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-ban fa-3x" style="color: red;"></i>
			</div>

			<div class="col-sm-10" id="errormessagedelete"></div>
			<button type="button" id="faileddelete" class="btn btn-primary">Ok</button>

		</div>
	</div>
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
					<div class="col-sm-12">
						<h4>
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
					
					<div id="addBandPopup" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Employee Band</h4>
						</div>

						<div class="modal-body">
							<%-- <span style='color: red; font-size: 11px; display: block'>
								(* Please separate emails with semicolon) </span> --%>
							<form action="insertBandName" method="post" class="form-horizontal"
								name="myForm" id="formSubmit">
								<div class="row">
									<div class="col-sm-12">

										<div class="col-sm-12  ">
											<div class="form-group">
												<label for="newBandName">Band Name</label>
												<input type="text" class="form-control input-sm" value=""
													required id="newBandName" name="newBandName"
													placeholder="Enter new band name">
											</div>
										</div>
										<div class="col-sm-12  ">
											<div class="form-group">
												<label for="newBandCode">Band Code</label>
												<input type="text" class="form-control input-sm" value=""
													required id="newBandCode" name="newBandCode"
													placeholder="Enter new band code">
											</div>
										</div>
										<div class="col-sm-12" id="hiddenDiv">
										</div>
									</div>
								</div>

							</form>

						</div>
						<div class="modal-footer">
							<div class="col-sm-12">
								<button type="button" id="addBandButton"
									onclick="checkNameExistingOrSave(document.getElementById('newBandName').value,document.getElementById('newBandCode').value);"
									class="btn btn-primary btn-sm">Add</button>

								<button type="button" class="btn btn-danger btn-sm"
									data-dismiss="modal">Close</button>

							</div>

						</div>
					</div>

				</div>
			</div>
			<div id="addDesignationPopup" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Employee Designation</h4>
						</div>

						<div class="modal-body">
							<form action="insertDesignationName" method="post" class="form-horizontal"
								name="myForm" id="formSubmitDesg">
								<div class="row">
									<div class="col-sm-12">

										<div class="col-sm-12  ">
											<div class="form-group">
												<label for="newDesgName">Designation Name</label>
												<input type="text" class="form-control input-sm" value=""
													required id="newDesgName" name="newDesgName"
													placeholder="Enter new Designation name">
											</div>
										</div>
										<div class="col-sm-12  ">
											<div class="form-group">
												<label for="newDesgCode">Designation Code</label>
												<input type="text" class="form-control input-sm" value=""
													required id="newDesgCode" name="newDesgCode"
													placeholder="Enter new Designation Code">
											</div>
										</div>
										<div class="col-sm-12" id="hiddenDivforError">
										</div>
										<div class="col-sm-12 " style="display: none;" id=addDesgBandList>
											<div class="form-group">
												<label for="designation" >Band List (Code)</label>
													<!-- <div class="col-sm-8"> -->
														<select class="form-control input-sm" name="newDesgBandid"
															id="newDesgBandid" >
															<option value="">Select band</option>
															<c:forEach var="band" items="${bandModelList}">
																<c:choose>
																	<c:when test="${band.bandName!= null}">
																		<option value="${band.bandId}">${band.bandName} ( ${band.bandCode} )</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${band.bandId}">${band.bandName} ( ${band.bandCode} )</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													<!-- </div> -->
													<!-- <div class="col-sm-1">
														<h4>
															<a href="#addBandPopup" class="btn btn-info " data-toggle="modal" 
																style="border: 2px solid #a1a1a1; font-size: x-small; border-radius: 35px"
																role="button"> <i class="fa fa-plus"> Add Band</i></a>
														</h4>
							
													</div> -->
											</div>

										</div>
									</div>
								</div>

							</form>

						</div>
						<div class="modal-footer">
							<div class="col-sm-12">
								<button type="button" id="addDesgButton"
									onclick="checkNameExistingOrSaveForEmployee(document.getElementById('newDesgName').value,document.getElementById('newDesgCode').value,document.getElementById('newDesgBandid').value);"
									class="btn btn-primary btn-sm">Add</button>

								<button type="button" class="btn btn-danger btn-sm"
									data-dismiss="modal">Close</button>

							</div>

						</div>
					</div>

				</div>
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
					
					
			<div class="row" id="dash-us-register">
			
			<%--  <s:if test="#session.Company.companyRole.isCorporate()">
			  <form action="doExcelUpload" method="post" class="form-horizontal" enctype="multipart/form-data">
				 		
					<div class="form-group">
						<!-- <label for="City" class="col-sm-2 control-label">Description</label> -->
						<div class="col-xs-12 ">
						<label for="City">Upload Designation Excel File</label><a href="downloaddesignationexcel?fileName=Book1.xls">(Download Sample)</a>
						</div>
					</div>
					
					
							<div class="form-group">    
							    <div class="col-xs-12 ">
							    <input type="file" name="upload" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" style="display:inline-block">
							  <div class="submitWrap text-center"  style="display:inline-block">
							  <input type="submit" class="btn btn-primary btn-sm" value="Upload"> 
							  </div>
							   </div>
							  </div>
					 </form>
					 
					  <div class="text-center clearfix">
						<div class="col-xs-12 ">
							<h5><b>OR</b></h5>
						</div>
					</div>
					
			 
			 
			 </s:if>
				 --%>
			 <form action="userregister" method="post" class="form-horizontal"
					name="myForm" id="myFormRegister" enctype="multipart/form-data">
					 
					<div class="form-group">
					  <s:if test="#session.Company.companyRole.isCorporate()">
					  <label for="Username" class="col-sm-2 control-label">Employee ID </label>
					 </s:if>
					 <s:else>
					 <label for="Username" class="col-sm-2 control-label">User ID </label>
					 </s:else>
						
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="username"
								name="Username" placeholder="User ID " autocomplete="off"
								required min="10" maxlength="30">
						</div>
					</div>
					<input type="hidden" name="typeOfWallet" value="Prepaid">
					<%-- <div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Wallet Type</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="typeOfWallet"
								id="typeOfWallet" autocomplete="off"   required>
								<s:if test="%{#session.User.userrole_id.isUsermode()}">
									<s:if test="%{#session.Company.companyRole.isAgent() && #session.User.agentWallet.walletType=='Prepaid'}">
									<option  value="Prepaid"><s:text name="global.Prepaid" ></s:text></option>
									</s:if>
									<s:else>
									<option value="Postpaid"><s:text name="global.Postpaid" ></s:text></option>
									</s:else>
									 </s:if>
								<s:if test="%{#session.User.userrole_id.isAdmin()}">
										<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
											 
									<s:if test="%{#session.Company.companyRole.isAgent() && #session.User.agentWallet.walletType=='Prepaid'}">
									<option  value="Prepaid"><s:text name="global.Prepaid" ></s:text></option>
									
									</s:if>
									<s:else>
									<option value="Postpaid"><s:text name="global.Postpaid" ></s:text></option>
									</s:else>
									  </s:if>
									</s:if>
							 </select>
						</div>
					</div> --%>
						<div class="form-group" id="Wallet-type-div" style="display: none">
						<label for="Username" class="col-sm-2 control-label">Postpaid Amount Range</label>
						<div class="col-sm-8">
						<input   type="number"   onkeypress="return isNumberKey(event,this)" class="form-control input-sm" id="postAmount"
								name="postAmount"  value="<s:property value="postAmount"/>"
								placeholder="Amount" autocomplete="off" >
							 
						</div>
					</div>
					
					
					
					<div class="form-group">
						<label for="first-name" class="col-sm-2 control-label">First
							Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="firstname"
								name="Firstname" placeholder="First Name" autocomplete="off"
								required min="10" maxlength="30">
						</div>
					</div>

					<div class="form-group">
						<label for="last-name" class="col-sm-2 control-label">Last
							Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="lastname"
								name="Lastname" placeholder="Last Name" autocomplete="off"
								required required min="10" maxlength="30">
						</div>
					</div>
					
					  <s:if test="#session.Company.companyRole.isCorporate()">
					<%-- <div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Employee Designation</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="designation"
								 autocomplete="off" required>								
								<s:iterator value="designationList">
									<option><s:property value="designation"></s:property></option>
								</s:iterator>
 							</select>
						</div>
					</div> --%>
					
					
					<div class="form-group">
						<label for="designation" class="col-sm-2 control-label">Designation
						</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="designation" 
								id="designation" >
								<option value="">Select Designation (Code)</option>
								<c:forEach var="designation" items="${employeeDesignationsList}">
									<c:choose>
										<c:when test="${designation.desgName!= null}">
											<option value="${designation.desgName}">${designation.desgName} ( ${designation.desgCode} )</option>
										</c:when>
										<c:otherwise>
											<option value="${designation.desgName}">${designation.desgName} ( ${designation.desgCode} </option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-1">
							<h4>
								<a href="#addDesignationPopup" class="btn btn-info " data-toggle="modal" 
									style="border: 2px solid #a1a1a1; font-size: x-small; border-radius: 35px"
									role="button"> <i class="fa fa-plus"> Add Desg</i></a>
							</h4>

						</div>
					</div>
					<div class="form-group">
						<label for="designation" class="col-sm-2 control-label">Band
							List </label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="bandName"
								id="bandName" >
								<option value="">Select band (Code)</option>
								<c:forEach var="band" items="${bandModelList}">
									<c:choose>
										<c:when test="${band.bandName!= null}">
											<option value="${band.bandName}">${band.bandName} ( ${band.bandCode} )</option>
										</c:when>
										<c:otherwise>
											<option value="${band.bandName}">${band.bandName} ( ${band.bandCode} )</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-1">
							<h4>
								<a href="#addBandPopup" class="btn btn-info " data-toggle="modal" 
									style="border: 2px solid #a1a1a1; font-size: x-small; border-radius: 35px"
									role="button"> <i class="fa fa-plus"> Add Band</i></a>
							</h4>

						</div>
					</div>
					</s:if>
					
					<div class="form-group">
						<label for="userroletype" class="col-sm-2 control-label">
						Employee Role</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="userroletype"
								name="userroletype"  multiple="multiple" required>
									<option value=""> select employee role</option>
								<option value="admin">Admin</option>
								<option value="report">Accounts</option>
								<option value="order">Operations</option>
								<option value="traveldesk">Travel Desk</option>
								<option value="corporateemployee" selected="selected">Employee</option>
							</select>
						</div>
					</div>

					<div class="form-group">
								<label for="password" class="col-sm-2 control-label">Password
									 </label>
								<div class="col-sm-8">
									<input name="password"   id="password" type="password"  
									class="form-control input-sm"  placeholder="Password" autocomplete="off" required>
								</div>
								<div class="col-sm-2 text-left result-pasword">
								<span id="result"></span>
								</div>
							</div>




					<div class="form-group">
						<label for="Email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-8">
							<input type="email" class="form-control input-sm" name="Email"
								id="email"  maxlength="100" placeholder="Email" autocomplete="off" required>
						</div>
					</div>
					<%-- <div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Status</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="Status" name="Status"
								required>
								<option selected="selected" value="active">Unlocked</option>
								<option value="inactive">Locked</option>

							</select>
						</div>
					</div> --%>
					<div class="form-group">
						<label for="telphone" class="col-sm-2 control-label">Phone</label>
						<div class="col-sm-8">
							<input type="tel" class="form-control input-sm" name="Mobile"
								id="telphone" placeholder="Phone" autocomplete="off"  
								required onkeypress="return isNumberKey(event,this)" >
						</div>
					</div>
					<div class="form-group">
						<label for="Country" class="col-sm-2 control-label">Country</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="Countryname"
								id="country" autocomplete="off" required>
								<option value="">Select Country</option>
								<c:forEach var="country" items="${CountryList}">
										<c:choose>
											<c:when test="${country.c_name == 'India'}">
												<option value="${country.c_name}" selected="selected">${country.c_name} </option>
											</c:when>
											<c:otherwise>
											<option value="${country.c_name}">${country.c_name} </option>
											</c:otherwise>
										</c:choose>
								</c:forEach>
								<%-- <s:iterator value="CountryList">
								
									<option><s:property value="c_name"></s:property></option>
								</s:iterator> --%>
 							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="language_id" class="col-sm-2 control-label">Language</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="Language"
								id="language_id" autocomplete="off" required>
								<option selected="selected" value="">Select Language</option>
								<c:forEach var="lang" items="${LanguageList}">
										<c:choose>
											<c:when test="${lang.language == 'English'}">
												<option value="${lang.language}" selected="selected">${lang.language} </option>
											</c:when>
											<c:otherwise>
											<option value="${lang.language}">${lang.language} </option>
											</c:otherwise>
										</c:choose>
								</c:forEach>
								<%-- <s:iterator value="LanguageList">
									<option value="<s:property value="language"/>"><s:property
											value="language"></s:property></option>
								</s:iterator> --%>

							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="City" class="col-sm-2 control-label">City</label>
						<div class="col-sm-8">
							<input type="text"  maxlength="50" class="form-control input-sm" id="city"
								name="City" placeholder="City" autocomplete="off" required>
						</div>
					</div>
					<div class="form-group">
						<label for="Address" class="col-sm-2 control-label">Address</label>
						<div class="col-sm-8">
							<textarea class="form-control input-sm" id="address"
								name="Address" placeholder="Address" maxlength="300" autocomplete="off" required></textarea>

						</div>
					</div>

                <s:if test="#session.Company.companyRole.isCorporate()">

					<div class="form-group">
						<label for="passportno" class="col-sm-2 control-label">Passport Number</label>
						<div class="col-sm-8">
							<input type="text" 	class="form-control input-sm" name="passportno" id="passportno"
								 placeholder="PassportNumber" autocomplete="off" required>
							
						</div>
					</div>
					
					<div class="form-group">
						<label for="passport_expirydate" class="col-sm-2 control-label">Passport ExpiryDate</label>
						<div class="col-sm-8">
							<input type="text" id="passport_expirydate"	class="form-control input-sm" name="passport_expirydate"
								 placeholder="passport_expirydate" autocomplete="off" required>
							
						</div>
					</div>
					<div class="form-group">
						<label for="passport_issuedate" class="col-sm-2 control-label">Passport IssueDate</label>
						<div class="col-sm-8">
							<input type="text" id="passport_issuedate"	class="form-control input-sm" name="passport_issuedate"
								 placeholder="passport_issuedate" autocomplete="off" required>
							
						</div>
					</div>
					<div class="form-group">
						<label for="passport_issueplace" class="col-sm-2 control-label">Passport IssuePlace</label>
						<div class="col-sm-8">
							<input type="text" 	class="form-control input-sm" name="passport_issueplace" id="passport_issueplace"
								 placeholder="passport_issueplace" autocomplete="off" required>
							
						</div>
					</div>
					<div class="form-group">
						<label for="dateofbirth" class="col-sm-2 control-label">Date Of Birth</label>
						<div class="col-sm-8">
							<input type="text" id="dateofbirth"	class="form-control input-sm" name="dateofbirth"
								 placeholder="dateofbirth" autocomplete="off" required>
							
						</div>
					</div>
					<div class="form-group">
						<label for="uploadimage" class="col-sm-2 control-label">Upload Passport Size Photo
							 </label>
						<div class="col-sm-8">
							<div class="row">
								<div class="col-sm-6 file-upload">
									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="passportSizeImage" accept="image/*" ng-file-select="onFileSelect($files)" name="passportSizeImage"> 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
									<div id="fileinfo">
										<div id="fileError"></div>
									</div>
								</div>  
							</div>
						</div>
					</div> 
					 <div class="form-group">
						<label for="uploadimage" class="col-sm-2 control-label">Upload Passport Copy
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">
									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="passportScanCopy" accept="image/*" ng-file-select="onFileSelect($files)" name="passportScanCopy"> 
								</div>
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
									</div>
								</div>  
							</div>
						</div>
					</div> 
					</s:if>

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
										 > <input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>
							
								<!-- ng-file-select="onFileSelect($files)" -->
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										
										<!-- <div id="filesize">7.6 KB</div>
										<div id="filetype">image/jpeg</div>
										<div id="filedim">90 x 50</div> -->
									</div>
								</div>
							</div>


						</div>

					</div>

						

					<div class="form-group">
						<label for="Description" class="col-sm-2 control-label"><s:text name="global.Designation"></s:text></label>
						<div class="col-sm-8">
							<textarea class="form-control input-sm" id="Description" maxlength="300" name="Description"
								name="Description" placeholder="<s:text name="global.Designation"></s:text>" autocomplete="off"
								required></textarea>
						</div>
					</div>
					
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" class="btn btn-primary btn-lg" onclick="checkExistingValidation();" >Register</button>
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
   
	<%@ include file="DashboardFooter.jsp"%>
<script src="js/app.js" type="text/javascript"></script>   
 <%-- <script src="js/upload_passportsize_photo.js" type="text/javascript"></script>  --%>
 <script>
 $(document).ready(function() 
 { 
	 $("#passport_issuedate,#dateofbirth").datepicker({
		 dateFormat: "dd-mm-yy",
		 maxDate:0,
		 numberOfMonths: 2,
		 yearRange: "-100:+0",
			changeMonth: true,
			changeYear: true
	 });
	 $("#passport_expirydate").datepicker({
		 dateFormat: "dd-mm-yy",
		 minDate:0,
		 numberOfMonths: 2,
		 yearRange: "+0:+30",
			changeMonth: true,
			changeYear: true
	 });
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
			  reset_form_element( $('#uploadimage') );
			    e.preventDefault();
			  
		}
});
function reset_form_element (e) {
  e.wrap('<form>').parent('form').trigger('reset');
  e.unwrap();
}
 </script>
<script >
function checkNameExistingOrSave(bandName,bandCode){
	var boolName=true;
	var boolCode=true;
	<c:forEach items="${bandModelList}" var="item" varStatus="status"> 
		if("${item.bandName}".toLowerCase() === bandName.toLowerCase()){
			document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Name Already Existed !   Please try another ...</i></span>';
			boolName=false;
		}
		if("${item.bandCode}".toLowerCase() === bandCode.toLowerCase()){
			document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Code Already Existed !   Please try another ...</i></span>';
			boolCode=false;
		}
	</c:forEach>
	if(boolName && boolCode){
		$("#formSubmit").submit();
	}
}
function checkNameExistingOrSaveForEmployee(empName,empCode,newDesgBandid){
	var boolName=true;
	var boolCode=true;
	<c:forEach items="${employeeDesignationsList}" var="item" varStatus="status"> 
		if("${item.desgName}".toLowerCase() === empName.toLowerCase()){
			document.getElementById('hiddenDivforError').innerHTML = '<span style="color:red;font-size: small; "><i>Name Already Existed !   Please try another ...</i></span>';
			boolName=false;
		}
		if("${item.desgCode}".toLowerCase() === empCode.toLowerCase()){
			document.getElementById('hiddenDivforError').innerHTML = '<span style="color:red;font-size: small; "><i>Code Already Existed !   Please try another ...</i></span>';
			boolCode=false;
		}
	</c:forEach>
	if(boolName && boolCode){
		document.getElementById('hiddenDivforError').innerHTML = '<span style="color:green;font-size: small; "><i>Name is Available !  Please Associate it with Any Band</i></span>';
		$("#addDesgBandList").show();
		if(newDesgBandid>0){
			  $("#formSubmitDesg").submit(); 
		}
		else 
			document.getElementById('hiddenDivforError').innerHTML = '<span style="color:red;font-size: small; "><i>Please select any band</i></span>';
	}
}
function checkExistingValidation1(obj){}
function checkExistingValidation(){
	var totUrl = $(location).attr('href');
	
	if(username.value ==''){
		popupDisplayBox("Please select the id ")
	}
	else if(firstname.value ==''){
		popupDisplayBox("Please select the username ")
	}
	else if(lastname.value ==''){
		popupDisplayBox("Please enter the lastname");
	}
	/* else if(designation.value ==''){
		popupDisplayBox("Please select the designation ")
	} */
	else if(userroletype.value ==''){
		popupDisplayBox("Please select the userroletype ");
	}
	else if(password.value ==''){
		popupDisplayBox("Please enter the   password");
	}
	else if(email.value ==''){
		popupDisplayBox("Please enter the   email");
	}
	else if(address.value ==''){
		popupDisplayBox("Please enter the address");
	}
	/* else if(passportno.value ==''){
		popupDisplayBox("Please enter the passport no.");
	}
	else if(passport_expirydate.value ==''){
		popupDisplayBox("Please select the passport_expirydate");
	}
	else if(passport_issuedate.value ==''){
		popupDisplayBox("Please select the passport_issuedate");
	}
	else if(passport_issueplace.value ==''){
		popupDisplayBox("Please enter the passport_issueplace");
	} */
	/* else if(dateofbirth.value ==''){
		popupDisplayBox("Please select the dateofbirth");
	}
	else if(country.value ==''){
		popupDisplayBox("Please select the country");
	}
	else if(language_id.value ==''){
		popupDisplayBox("Please select the language_id");
	} */
	else if(city.value ==''){
		popupDisplayBox("Please enter the City");
	}
	else if(telphone.value ==''){
		popupDisplayBox("Please enter the telphone");
	}
	else if(Description.value ==''){
		popupDisplayBox("Please enter the Description");
	}
	/* else if(bandName.value ==''){
		popupDisplayBox("Please select the Band name");
	} */
	else{
		 
		 $("#myFormRegister").submit();
		//checkDesignationAssignedtoAnyBand();
	}
}

function popupDisplayBox(msg){
	$('#failed-alertdelete').show();
	$('#errormessagedelete') .text(msg);
	$('#faileddelete')
			.click( function() {
						$( '#failed-alertdelete').hide();
					});
}

function checkDesignationAssignedtoAnyBand(){
	/* $("#empDesgForm").submit(); */ 
	        var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "CheckingDesignationAvailabilty";
			bandId = $("#bandId").val();
			userName = $("#userName").val();
	        designation = $("#designation").val();
	        bandName = $("#bandName").val(); 
	        $.ajax({
	            type : "POST",
	            url : finalUrl,
	            data : {
	            	bandId : bandId, userName : userName, designation : designation, bandName :bandName
				},
	            success : function(data, status) {
	            	console.log("data",data);
	            	
					if (data.insertionMsgMap.response == "inserted") {
						$( "#myFormRegister" ).submit();
						/* $('#success-alertdelete').show();
						$('#messagedelete').text(data.insertionMsgMap.msg);
						$('#successdelete').click(
										function() {
											$('#success-alertdelete').hide();
											window.location.assign($(location).attr('href'));
										}); */

					} else if (data.insertionMsgMap.response == "already" || data.insertionMsgMap.response == "warning") {
						$('#failed-alertdelete').show();
						$('#errormessagedelete').text( data.insertionMsgMap.msg);
						$('#faileddelete')
								.click( function() {
											$( '#failed-alertdelete').hide();
										});
					}
					else{
						alert('Data is null OR Data Insufficient in Database');
					}
				},
				error : function(data, status) {
					console.log(data);
					console.log(status);
				}
	        });
	   
}
$('#designation').on('change', function() {
	  
	  var desgName=this.value;
	  <c:forEach items="${employeeDesignationsList}" var="item" varStatus="status"> 
		if("${item.desgName}".toLowerCase() === desgName.toLowerCase()){
			$('#bandName').val("${item.employeeBandModel.bandName}");
			
	 }
		
	</c:forEach>
	  
})
</script>
</body>

</html>