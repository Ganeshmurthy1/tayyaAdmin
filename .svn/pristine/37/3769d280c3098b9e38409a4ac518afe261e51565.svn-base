<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	

				<!-- <form action="adddesignation" method="post" class="form-horizontal"
					name="myForm"> -->
				<form action="insertBandName" method="post" class="form-horizontal"
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

					<div class="form-group">
						<label for="themeName" class="col-sm-2 control-label">Theme Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value=""
								required id="themeName" name="themeName"
								placeholder="Enter theme name">
						</div>
					</div>
					<div class="form-group">
						<label for="designPattern" class="col-sm-2 control-label">Design Pattern</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value=""
								required id="designPattern" name="designPattern"
								placeholder="Enter design pattern">
						</div>
					</div>
					<div class="form-group">
						<label for="logoImagePath" class="col-sm-2 control-label">Logo Image Path</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value=""
								required id="logoImagePath" name="logoImagePath"
								placeholder="Enter Logo Image Path">
						</div>
					</div>
					<div class="form-group">
						<label for="cssPath" class="col-sm-2 control-label">CSS path</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" value=""
								required id="cssPath" name="cssPath"
								placeholder="Enter CSS path">
						</div>
					</div>
					
					<div class="form-group">
						<label for="enableFlight" class="col-sm-2 control-label">Enable Flight</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableFlight"
									id="enableFlight">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableHotel" class="col-sm-2 control-label">Enable Hotel</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableHotel"
									id="enableHotel">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableCar" class="col-sm-2 control-label">Enable Car</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableCar"
									id="enableCar">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableBus" class="col-sm-2 control-label">Enable Bus</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableBus"
									id="enableBus">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableTrain" class="col-sm-2 control-label">Enable Train</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableTrain"
									id="enableTrain">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableInsurance" class="col-sm-2 control-label">Enable Insurance</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableInsurance"
									id="enableInsurance">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableVisa" class="col-sm-2 control-label">Enable Visa</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableVisa"
									id="enableVisa">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableMiscellaneous" class="col-sm-2 control-label">Enable Miscellaneous</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableFlight"
									id="enableMiscellaneous">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableLogoOnVoucher" class="col-sm-2 control-label">Enable Logo On Voucher</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableLogoOnVoucher"
									id="enableLogoOnVoucher">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableHeader" class="col-sm-2 control-label">Enable Header</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableHeader"
									id="enableHeader">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableFooter" class="col-sm-2 control-label">Enable Footer</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableFooter"
									id="enableFooter">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enablePaymentGateway" class="col-sm-2 control-label">Enable Payment Gateway</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enablePaymentGateway"
									id="enablePaymentGateway">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableB2B" class="col-sm-2 control-label">Enable B2B</label>
						<div class="col-sm-3">
							<select class="form-control input-sm" name="enableB2B"
									id="enableB2B">
									<option value="">Select band</option>
									<option value="true">True</option>
									<option value="false">False</option>
								</select>
						</div>
					</div>
					
					<div class=" text-center">
					<div class="col-xs-12 submitWrap text-center">
						<!-- <button type="button" id="addBandButton"
							onclick="checkNameExistingOrSave(document.getElementById('newBandName').value,document.getElementById('newBandCode').value);"
							class="btn btn-primary btn-lg">Add Band</button> -->

						<button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Add Company Theme</button>
					</div>
				</div>
					<!-- <div class="col-sm-12" id="hiddenDiv"></div> -->

				</form>
				
				
				
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