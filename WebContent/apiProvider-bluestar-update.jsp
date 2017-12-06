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
<link rel="stylesheet" href="css/alert.css">
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
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Update Bluestar Config</h1>
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
			<form action="updateBluestarConfig" id="updateBluestarConf" method="post"
				class="form-horizontal" name="myForm">
				<input type="hidden"  
							name="id"  
							   value="${apiProviderBluestarConfig.id}" />
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Title</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="title"
							name="title" placeholder="Title" autocomplete="off"
							required min="10" maxlength="40"  value="${apiProviderBluestarConfig.title}" />
					</div>
				</div>
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Status </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="active" required="required">
								<option value="true" selected="selected">active</option>
							<option value="false">inactive</option>
						 </select>
					</div>
 					</div>
			
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">agentCode</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="agentCode"
							name="agentCode" placeholder="Agent Code" autocomplete="off"
							required min="10" maxlength="30" value="${apiProviderBluestarConfig.agentCode}" />
					</div>
				</div>
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">PassWord</label>
					<div class="col-sm-8">
						<input type="password" class="form-control input-sm" id="password"
							name="password" placeholder="password" autocomplete="off"
							required min="10" maxlength="30" value="${apiProviderBluestarConfig.password}" />
					</div>
				</div>
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Currency</label>
					<div class="col-sm-8">
						 <input type="text" class="form-control input-sm" id="apiCurrency"
							name="apiCurrency" placeholder="api Currency" autocomplete="off"
							 value="${apiProviderBluestarConfig.apiCurrency}" onkeypress="return isNumberKey(event,this);" required/>
					</div>
 					</div>
				<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Environment </label>
					<div class="col-sm-8">
						<select  class="form-control input-sm"
							name="environment" required="required">
								<option value="test" selected="selected">Test</option>
							<option value="live">Live</option>
						 </select>
					</div>
 					</div>
 					<%-- <div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Provider Id</label>
					<div class="col-sm-8">
						 <input type="text" class="form-control input-sm" id="providerId"
							name="providerId" placeholder="property Id" autocomplete="off"
							  value="${apiProviderBluestarConfig.providerId}" />
					</div>
 					</div>
 					 --%>

 					<div class="form-group">
					<label for="last-name" class="col-sm-2 control-label">Interface AuthKey </label>
					<div class="col-sm-8">
						 <input type="text" class="form-control input-sm" id="interfaceAuthKey"
							name="interfaceAuthKey" placeholder="Interface AuthKey" autocomplete="off"
							  value="${apiProviderBluestarConfig.interfaceAuthKey}" required/>
					</div>
 					</div>
 					
 					 
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">Interface Code</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="interfaceCode"
							name="interfaceCode" placeholder="Interface Code" autocomplete="off"
							  value="${apiProviderBluestarConfig.interfaceCode}" required/>
					</div>
				</div>
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">HostUrl</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="hostUrl"
							name="hostUrl" placeholder="hostUrl" autocomplete="off"
							  value="${apiProviderBluestarConfig.hostUrl}" required/>
					</div>
				</div>
				<div class="form-group">
					<label for="first-name" class="col-sm-2 control-label">EndPointUrl</label>
					<div class="col-sm-8">
						<input type="text" class="form-control input-sm" id="endPointUrl"
							name="endPointUrl" placeholder="EndPoint Url" autocomplete="off"
							  value="${apiProviderBluestarConfig.endPointUrl}" required/>
					</div>
				</div>

				 
				<div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button id="bluestarSubmit" type="submit" class="btn btn-primary btn-lg">Update</button>
					</div>
				</div>
			</form>
		</section>
	</div>

<script>

$(document).ready(function(){
	  
	   $('#bluestarSubmit').click(function(){
		   $("#updateBluestarConf").valid();
		   if($("#updateBluestarConf").valid()){
			   document.getElementById("updateBluestarConf").submit();
		   }
	   });
	   $("#updateBluestarConf").validate({
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
function isNumberKey(evt,obj){
	 
    evt = (evt) ? evt : window.event;
      var charCode = (evt.which) ? evt.which : evt.keyCode;
      if (charCode > 31 &&  (charCode < 48 || charCode > 57))  
      
          return false;
      
   
     
  }
</script>

	<%@ include file="DashboardFooter.jsp"%>

</body>

</html>