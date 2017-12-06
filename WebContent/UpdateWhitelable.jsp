<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Config</title>

<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
	$(function() {
		
	});
</script>

</head>
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Update Company Configuration</h1>
		</section>
		<section class="content">
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
				<input type="hidden"
					value="<s:property value="%{#session.CompanyconfigProfile.config_id}"/>"
					id="uniqueId">
				<form action="updateWhitelable" method="post" id="formcheck"
					class="form-horizontal">
					  <!-- Whitelable -->
					<div class="whitelabel-form-group" >
					<input type="hidden" class="form-control input-sm" value="<s:property value="%{companyTheme.id}"/>" id="whitelabelActive" name="themeRequestModel.id">
						<div class="form-group">
							<label class="col-sm-2 control-label">Product Type</label>							
							<div class="col-sm-7">					
							<label class="form-control input-sm">WhiteLabel</label>				
							</div>
						</div>
						
						<div class="form-group">
						<label for="themeName" class="col-sm-2 control-label">Theme Name</label>
						<div class="col-sm-7">
							<input type="text" class="form-control input-sm" value="<s:property value="%{companyTheme.themeName}"/>"
								 id="themeName" name="themeRequestModel.themeName"
								placeholder="Enter theme name">
						</div>
					</div>
					<div class="form-group">
						<label for="designPattern" class="col-sm-2 control-label">Design Pattern</label>
						<div class="col-sm-7">
							<input type="text" class="form-control input-sm" value="<s:property value="%{companyTheme.themeName}"/>"
								 id="designPattern" name="themeRequestModel.designPattern"
								placeholder="Enter design pattern">
						</div>
					</div>
					<div class="form-group">
						<label for="logoImagePath" class="col-sm-2 control-label">Logo Image Path</label>
						<div class="col-sm-7">
							<input type="text" class="form-control input-sm" value="<s:property value="%{companyTheme.themeName}"/>"
								 id="logoImagePath" name="themeRequestModel.logoImagePath"
								placeholder="Enter Logo Image Path">
						</div>
					</div>
					<div class="form-group">
						<label for="cssPath" class="col-sm-2 control-label">CSS path</label>
						<div class="col-sm-7">
							<input type="text" class="form-control input-sm" value="<s:property value="%{companyTheme.themeName}"/>"
								 id="cssPath" name="themeRequestModel.cssPath"
								placeholder="Enter CSS path">
						</div>
					</div>
					<%-- <div>
					<div class="form-group">
						<label for="enableFlight" class="col-sm-2 control-label">Enable Flight</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableFlight"
									id="enableFlight">
									<c:choose>
										<c:when test="${companyTheme.enableFlight}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableHotel" class="col-sm-2 control-label">Enable Hotel</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableHotel"
									id="enableHotel">
									<c:choose>
										<c:when test="${companyTheme.enableHotel}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableCar" class="col-sm-2 control-label">Enable Car</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableCar"
									id="enableCar">
									<c:choose>
										<c:when test="${companyTheme.enableCar}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
			  	<div class="form-group">
						<label for="enableBus" class="col-sm-2 control-label">Enable Bus</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableBus"
									id="enableBus">
									<c:choose>
										<c:when test="${companyTheme.enableBus}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>  				
					<div class="form-group">
						<label for="enableTrain" class="col-sm-2 control-label">Enable Train</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableTrain"
									id="enableTrain">
									<c:choose>
										<c:when test="${companyTheme.enableTrain}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableInsurance" class="col-sm-2 control-label">Enable Insurance</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableInsurance"
									id="enableInsurance">
									<c:choose>
										<c:when test="${companyTheme.enableInsurance}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableVisa" class="col-sm-2 control-label">Enable Visa</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableVisa"
									id="enableVisa">
									<c:choose>
										<c:when test="${companyTheme.enableVisa}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableMiscellaneous" class="col-sm-2 control-label">Enable Miscellaneous</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableMiscellaneous"
									id="enableMiscellaneous">
									<c:choose>
										<c:when test="${companyTheme.enableMiscellaneous}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableLogoOnVoucher" class="col-sm-2 control-label">Enable Logo On Voucher</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableLogoOnVoucher"
									id="enableLogoOnVoucher">
									<c:choose>
										<c:when test="${companyTheme.enableLogoOnVoucher}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableHeader" class="col-sm-2 control-label">Enable Header</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableHeader"
									id="enableHeader">
									<c:choose>
										<c:when test="${companyTheme.enableHeader}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableFooter" class="col-sm-2 control-label">Enable Footer</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableFooter"
									id="enableFooter">
									<c:choose>
										<c:when test="${companyTheme.enableFooter}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enablePaymentGateway" class="col-sm-2 control-label">Enable Payment Gateway</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enablePaymentGateway"
									id="enablePaymentGateway">
									<c:choose>
										<c:when test="${companyTheme.enablePaymentGateway}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					<div class="form-group">
						<label for="enableB2B" class="col-sm-2 control-label">Enable Customer Login</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableB2B"
									id="enableB2B">
									<c:choose>
										<c:when test="${companyTheme.enableB2B}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
						<div class="form-group">
						<label for="enableB2E" class="col-sm-2 control-label">Enable User Login</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableB2E"
									id="enableB2E">
									<c:choose>
										<c:when test="${companyTheme.enableB2E}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
				 
						<div class="form-group">
						<label for="enableAboutUS" class="col-sm-2 control-label">Enable About US</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableAboutUS"
									id="enableAboutUS">
									<c:choose>
										<c:when test="${companyTheme.enableAboutUS}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
						<div class="form-group">
						<label for="enableContactUs" class="col-sm-2 control-label">Enable Contact Us</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableContactUs"
									id="enableContactUs">
									<c:choose>
										<c:when test="${companyTheme.enableContactUs}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
						<div class="form-group">
						<label for="enableTermsCondition" class="col-sm-2 control-label">Enable Terms & Condition</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableTermsCondition"
									id="enableTermsCondition">
									<c:choose>
										<c:when test="${companyTheme.enableTermsCondition}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
						<div class="form-group">
						<label for="enablePrivacyPolicy" class="col-sm-2 control-label">Enable Privacy Policy</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enablePrivacyPolicy"
									id="enablePrivacyPolicy">
									<c:choose>
										<c:when test="${companyTheme.enablePrivacyPolicy}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
						<div class="form-group">
						<label for="enableSocialMedia" class="col-sm-2 control-label">Enable SocialMedia</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableSocialMedia"
									id="enableSocialMedia">
									<c:choose>
										<c:when test="${companyTheme.enableSocialMedia}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
						<div class="form-group">
						<label for="enableDeals" class="col-sm-2 control-label">Enable EnableDeals</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableDeals"
									id="enableDeals">
									<c:choose>
										<c:when test="${companyTheme.enableDeals}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
						<div class="form-group">
						<label for="enableSlider" class="col-sm-2 control-label">Enable EnableSlider</label>
						<div class="col-sm-7">
							<select class="form-control input-sm" name="themeRequestModel.enableSlider"
									id="enableSlider">
									<c:choose>
										<c:when test="${companyTheme.enableSlider}">
											<option value="true" selected="selected">True</option>
											<option value="false">False</option>
										</c:when>
										<c:otherwise>
											<option value="false" selected="selected">False</option>	
											<option value="true" >True</option>
										</c:otherwise>
									</c:choose>
								</select>
						</div>
					</div>
					</div> --%>
					
					<div>
					<div class="row">				
					 <div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableFlight}">
											<input type="checkbox" class="toCheck" id="enableFlight" name="themeRequestModel.enableFlight" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableFlight" name="themeRequestModel.enableFlight" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableFlight" class="control-label">Enable Flight</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableHotel}">
											<input type="checkbox" class="toCheck" id="enableHotel" name="themeRequestModel.enableHotel" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableHotel" name="themeRequestModel.enableHotel" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enableHotel" class=" control-label">Enable Hotel</label>
					</div>	
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableCar}">
											<input type="checkbox" class="toCheck" id="enableCar" name="themeRequestModel.enableCar" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableCar" name="themeRequestModel.enableCar" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableCar" class="control-label">Enable Car</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableBus}">
											<input type="checkbox" class="toCheck" id="enableBus" name="themeRequestModel.enableBus" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableBus" name="themeRequestModel.enableBus" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enableBus" class=" control-label">Enable Bus</label>
					</div>	
					</div>
					<div class="row">				
					 <div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableTrain}">
											<input type="checkbox" class="toCheck" id="enableTrain" name="themeRequestModel.enableTrain" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableTrain" name="themeRequestModel.enableTrain" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableTrain" class="control-label">Enable Train</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableInsurance}">
											<input type="checkbox" class="toCheck" id="enableInsurance" name="themeRequestModel.enableInsurance" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableInsurance" name="themeRequestModel.enableInsurance" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enableInsurance" class=" control-label">Enable Insurance</label>
					</div>	
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableVisa}">
											<input type="checkbox" class="toCheck" id="enableVisa" name="themeRequestModel.enableVisa" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableVisa" name="themeRequestModel.enableVisa" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableVisa" class="control-label">Enable Visa</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableMiscellaneous}">
											<input type="checkbox" class="toCheck" id="enableMiscellaneous" name="themeRequestModel.enableMiscellaneous" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableMiscellaneous" name="themeRequestModel.enableMiscellaneous" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enableMiscellaneous" class=" control-label">Enable Miscellaneous</label>
					</div>	
					</div>
					<div class="row">				
					 <div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableLogoOnVoucher}">
											<input type="checkbox" class="toCheck" id="enableLogoOnVoucher" name="themeRequestModel.enableLogoOnVoucher" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableLogoOnVoucher" name="themeRequestModel.enableLogoOnVoucher" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableLogoOnVoucher" class="control-label">Enable Logo On Voucher</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableHeader}">
											<input type="checkbox" class="toCheck" id="enableHeader" name="themeRequestModel.enableHeader" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableHeader" name="themeRequestModel.enableHeader" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enableHeader" class=" control-label">Enable Header</label>
					</div>	
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableFooter}">
											<input type="checkbox" class="toCheck" id="enableFooter" name="themeRequestModel.enableFooter" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableFooter" name="themeRequestModel.enableFooter" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableFooter" class="control-label">Enable Footer</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enablePaymentGateway}">
											<input type="checkbox" class="toCheck" id="enablePaymentGateway" name="themeRequestModel.enablePaymentGateway" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enablePaymentGateway" name="themeRequestModel.enablePaymentGateway" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enablePaymentGateway" class=" control-label">Enable Payment Gateway</label>
					</div>	
					</div>	
					<div class="row">				
					 <div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableB2B}">
											<input type="checkbox" class="toCheck" id="enableB2B" name="themeRequestModel.enableB2B" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableB2B" name="themeRequestModel.enableB2B" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableB2B" class="control-label">Enable Customer Login</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableB2E}">
											<input type="checkbox" class="toCheck" id="enableB2E" name="themeRequestModel.enableB2E" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableB2E" name="themeRequestModel.enableB2E" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enableB2E" class=" control-label">Enable User Login</label>
					</div>	
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableAboutUS}">
											<input type="checkbox" class="toCheck" id="enableAboutUS" name="themeRequestModel.enableAboutUS" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableAboutUS" name="themeRequestModel.enableAboutUS" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableAboutUS" class="control-label">Enable About US</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableContactUs}">
											<input type="checkbox" class="toCheck" id="enableContactUs" name="themeRequestModel.enableContactUs" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableContactUs" name="themeRequestModel.enableContactUs" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enableContactUs" class=" control-label">Enable Contact Us</label>
					</div>	
					</div>
					<div class="row">				
					 <div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableTermsCondition}">
											<input type="checkbox" class="toCheck" id="enableTermsCondition" name="themeRequestModel.enableTermsCondition" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableTermsCondition" name="themeRequestModel.enableTermsCondition" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableTermsCondition" class="control-label">Enable Terms & Condition</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enablePrivacyPolicy}">
											<input type="checkbox" class="toCheck" id="enablePrivacyPolicy" name="themeRequestModel.enablePrivacyPolicy" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enablePrivacyPolicy" name="themeRequestModel.enablePrivacyPolicy" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enablePrivacyPolicy" class=" control-label">Enable Privacy Policy</label>
					</div>	
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableSocialMedia}">
											<input type="checkbox" class="toCheck" id="enableSocialMedia" name="themeRequestModel.enableSocialMedia" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableSocialMedia" name="themeRequestModel.enableSocialMedia" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>
						 <label for="enableSocialMedia" class="control-label">Enable SocialMedia</label>
					</div>
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableDeals}">
											<input type="checkbox" class="toCheck" id="enableDeals" name="themeRequestModel.enableDeals" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableDeals" name="themeRequestModel.enableDeals" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enableDeals" class=" control-label">Enable EnableDeals</label>
					</div>	
					</div>
					<div class="row">
					<div class="form-group col-md-3 col-sm-3 col-xs-3 floatingLeft">
									<c:choose>
										<c:when test="${companyTheme.enableSlider}">
											<input type="checkbox" class="toCheck" id="enableSlider" name="themeRequestModel.enableSlider" value="true" checked/>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toCheck" id="enableSlider" name="themeRequestModel.enableSlider" value="false"/>
										</c:otherwise>
									</c:choose>
								</select>						 
						<label for="enableSlider" class=" control-label">Enable Slider</label>
					</div>	
				
					
					</div>
					</div>
					
					</div>
					<!-- END Whitelabel -->
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="submit" class="btn btn-primary btn-lg">Update
								Changes</button>
						</div>
					</div>
				</form>
			</div>
		</section>
	 </div>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"> 
</script>


	<script type="text/javascript">
	$(document).ready(function() {
		
	});
	
	 $(function () {
		
	});
		    
  </script>
  
  <script>
  $(document).ready(function(){
	     $('.toCheck').click(function(){
      $(this).val($(this).is(':checked'));
   
  	
  });  

	});
</script> 
  
  
  	<script type="text/javascript" src="js/app.js"></script>
	<%@ include file="DashboardFooter.jsp"%>
	
</body>
</html>