<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Whitelabel</title>
<%-- 
<script src="js/angular.js" type="text/javascript"></script>


<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "adduserdesignation";
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
			<h1>White Label</h1>

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
			<div class="row" id="dash-us-register">

				<!-- <form action="adddesignation" method="post" class="form-horizontal"
					name="myForm"> -->

				<s:if test="isUserExisted">
						<jsp:include page="AddWhiteLabel.jsp" />
				</s:if>
				<s:else>
					<jsp:include page="WhiteLabelView.jsp" />
				</s:else>


				<%-- <form action="insertBandName" method="post" class="form-horizontal"
					name="myForm" id="formSubmit">
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



				</form> --%>


				<!-- <div class=" text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="button" id="addBandButton"
							onclick="checkNameExistingOrSave(document.getElementById('newBandName').value,document.getElementById('newBandCode').value);"
							class="btn btn-primary btn-lg">Add Band</button>

						<button type="button" class="btn btn-primary btn-lg">Add
								Designation</button>
					</div>
				</div> -->
				<!-- </form> -->
			</div>

			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>

	<script>
		function checkNameExistingOrSave(bandName, bandCode) {
			var boolName = true;
			var boolCode = true;
			<c:forEach items="${bandModelList}" var="item" varStatus="status">
			if ("${item.bandName}".toLowerCase() === bandName.toLowerCase()) {
				document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Name Already Existed !   Please try another ...</i></span>';
				boolName = false;
			}
			if ("${item.bandCode}".toLowerCase() === bandCode.toLowerCase()) {
				document.getElementById('hiddenDiv').innerHTML = '<span style="color:red;font-size: small; "><i>Code Already Existed !   Please try another ...</i></span>';
				boolCode = false;
			}
			</c:forEach>
			if (boolName && boolCode) {
				$("#formSubmit").submit();
			}
		}
	</script>
	
					
				<script>
				
				
				$(document).ready(function(){ 
					 
				    $("#formSubmit").validate({
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
				    
				    $('#buttonSubmit').click(function() { 
				  	if($("#formSubmit").valid())  
				    	 document.getElementById("formSubmit").submit();
				  /* 	else
				  		document.getElementById("requiredspan").val = "Please Fill Required Feilds"  */
				    });    
				    
				});

				</script>

</body>

</html>