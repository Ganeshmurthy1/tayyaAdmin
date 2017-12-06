<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Config</title>
<link rel="stylesheet" href="css/alert.css">

<style>
.error {
    color:red;
}
.valid {
    color:green;
}
#nav {
     
   position: relative;
   z-index: 999; 
   margin-top: 52px;
}
#nav > ul{
padding-left: 0px;

}
#nav > a {
    display: none;
}
#nav li {
    position: relative;
}
#nav li a {
    color: #fff;
    display: block;
}
#nav li a:active {
    background-color: #c00 !important;
}
#nav span:after {
    width: 0;
    height: 0;
    border: 0.313em solid transparent; /* 5 */
    border-bottom: none;
    border-top-color: #efa585;
    content: '';
    vertical-align: middle;
    display: inline-block;
    position: relative;
    right: -0.313em; /* 5 */
}
/* first level */

            #nav > ul {
   height: 3.0em;   
    background-color: #e15a1f;
        line-height: 32px;
}
#nav > ul > li {
    width: 10%; 
    height: 100%;
    float: left;
    list-style-type: none;
}
#nav > ul > li > a {
    height: 100%;
    font-size: 1.2em; /* 24 */
    /* line-height: 2.5em; */ /* 60 (24) */
    text-align: center;
}
#nav > ul > li:not( :last-child ) > a {
    border-right: 1px solid #cc470d;
}
#nav > ul > li:hover > a, #nav > ul:not( :hover ) > li.active > a {
    background-color: #cc470d;
}
/* second level */

 #nav li ul {
    background-color: #cc470d;
    display: none;
    position: absolute;
    top: 100%;
    width: 100%;
    padding: 0px;
    width: 220px;
}
#nav li:hover ul {
    display: block;
    left: 0;
    right: 0;
}
#nav li:not( :first-child ):hover ul {
    left: -1px;
}
#nav li ul a {
    font-size: 1.0em; /* 20 */
    border-top: 1px solid #e15a1f;
    padding: 0.2em; /* 15 (20) */
        padding-left: 20px;
}
#nav li ul li {
list-style: none;
}
#nav li ul li a:hover, #nav li ul:not( :hover ) li.active a {
    background-color: #e15a1f;
}
 @media only screen and ( max-width: 62.5em ) /* 1000 */ {
#nav {
    width: 100%;
    position: static;
    margin: 0;
}
}
 @media only screen and ( max-width: 40em ) /* 640 */ {
html {
    font-size: 75%; /* 12 */
}
#nav {
    position: relative;
    top: auto;
    left: auto;
}
#nav > a {
    width: 3.125em; /* 50 */
    height: 3.125em; /* 50 */
    text-align: left;
    text-indent: -9999px;
    background-color: #e15a1f;
    position: relative;
}
#nav > a:before, #nav > a:after {
    position: absolute;
    border: 2px solid #fff;
    top: 35%;
    left: 25%;
    right: 25%;
    content: '';
    
}
#nav > a:after {
    top: 60%;
}
#nav:not( :target ) > a:first-of-type, #nav:target > a:last-of-type {
    display: block;
}
/* first level */

            #nav > ul {
    height: auto;
    display: none;
    position: absolute;
    left: 0;
    right: 0;
}
#nav:target > ul {
    display: block;
}
#nav > ul > li {
    width: 100%;
    float: none;
}
#nav > ul > li > a {
    height: auto;
    text-align: left;
    padding: 0 0.833em; /* 20 (24) */
}
#nav > ul > li:not( :last-child ) > a {
    border-right: none;
    border-bottom: 1px solid #cc470d;
}
/* second level */

                #nav li ul {
    position: static;
    padding: 1.25em; /* 20 */
    padding-top: 0;
        
}
}

</style>
<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "c_ConfigList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
 
</script>
<script type="text/javascript">
function numbersonly(thisObject) {
	//var intRegex = /^\d+$/;
	var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
	if(!floatRegex.test($(thisObject).val())) {
		$(thisObject).val('0');
		$(thisObject).focus();
	}
}
</script>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body>

	<!-- Content Wrapper. Contains page content -->
	
	
	
	

	<div class="content-wrapper"> 
	
	
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1> Company Configuration </h1> 
  		</section>

		<!-- Main content -->
		<section class="content clearfix"> 
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
			<!-- Small boxes (Stat box) -->
			<s:if test="hasActionErrors()">
				<div class="sccuss-full-updated" id="error-alert">
					<div class="succfully-updated clearfix"> 
						<div class="col-sm-12">
							<h4>
								<a href="javascript:history.back();"><span class="pull-right"><i class="fa fa-angle-left"></i> Back</span></a>
							</h4>
						</div>
					</div>
				</div>
			</s:if>
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
			<div class="row col-sm-12" ><!-- id="dash-us-register" -->
				<input type="hidden" id="parentid" value="<s:property value="%{#session.Company.companyid}"/>"> 
					<input type="hidden" id="selectedCompanyUserId">
				<input type="hidden" id="selectchildid" value="">
				<form action="AddCompanyConfigData" method="post" enctype="multipart/form-data" 
					class="form-horizontal" id="myfform"> 
					<input type="hidden"
						value="<s:property value="%{#session.Company.company_userid}"/>"
						class="form-control input-sm" id="companyUserId"> 
						  <input type="hidden" value="<s:property value="%{#session.Company.companyRole.isDistributor()}"/>"
						class="form-control input-sm" id="companyRoleType"> 
						
						 <input type="hidden" value="<s:property value="%{#session.Company.companyRole.isSuperuser()}"/>"
						class="form-control input-sm" id="companyRoleTypesuper"> 
						<div class="companysetup clearfix"> 
					<div class="companyset-heading"> 
						<div class="companyset-icon"> 
								 <i class="fa fa-home fa-2x"></i>  
								 <b>Company Configuration Setup</b>  
						</div> 
					</div>
					<div class="inner-compreg">
<div class="col-sm-2">
					<div class="form-group">
						<label for="City" class="control-label">Company UserId </label> 
							<select class="form-control input-sm" name="configCompanyName"
								id="company_id" autocomplete="off" required>
								<option selected value="">Select Company UserId</option>
								<s:if test="%{#session.Company.companyRole.isDistributor()}">
									<s:iterator value="companyUserIdsList">
										<option
											value="<s:property value="companyid"/>,<s:property value="Companyname"/>,<s:property value="company_userid"/>"><s:property
												value="company_userid" />(
											<s:property value="Companyname" />)
										</option>
									</s:iterator>
								</s:if>
								<s:else>
									<s:iterator value="companyUserIdsList">
										<option
											value="<s:property value="companyid"/>,<s:property value="Companyname"/>,<s:property value="company_userid"/>"><s:property
												value="company_userid" />(
											<s:property value="Companyname" />)
										</option>
									</s:iterator>
								</s:else>
							</select>
						</div>
</div>


						<s:if test="%{#session.User.userrole_id.isSuperuser()}">
							<div class="col-sm-1" id="Whitelable_div" style="display: none;margin-top: 20px;margin-left: 10px;">
								<div class="checkbox">
									<input type="checkbox" name="Whitelable" id="Whitelablenew" value="true">is
									Whitelable?
								</div>
							</div>
						</s:if>
						
			<div class="col-sm-2"  id="parentconfigid_div"
						style="display: none">			
				<div class="form-group">
						<label for="City" class="control-label">Parent ConfigId </label>
						 
							<select class="form-control input-sm" name="parentConfigIdSplit"
								id="parentConfigIdSplit"   required>
								<option selected value="0">Select parent configid</option>
								<s:iterator value="configList">
								  <option
										value="<s:property value="config_id"/>,<s:property value="companyUserId"/>,<s:property value="rateType"/>,<s:property value="commissionType"/>,<s:property value="commissionAmount"/>">
										<s:property value="configname" />(
										<s:property value="companyUserId" />(Flight:
										<s:property value="rateTypeFlight"/>-
										<s:property value="commissionTypeFlight" />-
										<s:property value="commissionAmountFlight" />)(Hotel:<s:property value="rateTypeHotel"/>-
										<s:property value="commissionTypeHotel" />-
										<s:property value="commissionAmountHotel" />))
										<s:if test="isActive()">
										 Active
										</s:if>
										<s:else>
										  InActive
										</s:else> 
										<s:if test="companyConfigType.isB2C()">
										 B2C 
										</s:if>
										<s:if test="companyConfigType.isB2B()">
										 B2B
										</s:if>
										<s:if test="companyConfigType.isB2E()">
										 B2E
										</s:if>
										<s:if test="companyConfigType.isAPI()">
										 API
										</s:if>
										<s:if test="companyConfigType.isWhitelable()">
										Whitelable
										</s:if> 
										
									 </option>
									 
								</s:iterator>
							</select>
						</div>
					</div> 
					<div class="col-sm-2" >
					<div class="form-group" id="configtype_div"
						style="display: block">
						<label for="City" class="control-label">Config Type</label> 
							<select class="form-control input-sm" name="configType"
								id="configType"  required>
								<option  selected value="">select config type</option>
									<option value="B2C">B2C</option>
									<option value="B2B">B2B</option>
									<option value="B2E">B2E</option>
									<option value="API">API</option>
									<option value="WL">Whitelable</option> 
							</select>
						</div>  
					</div> 
					<div class="col-sm-1" id="myconfig_div" style="display: none">
								<div class="checkbox">
									<input type="checkbox" name="MyConfig"  id="MyConfig" value="true">is MyConfig?
								</div>
							</div>
							
							<div class="col-sm-2 taxtype" id="configtype_div"
						style="display: none">							
					<div class="form-group " >
						<label for="City" class=" control-label">Tax Type</label> 
							<select class="form-control input-sm" name="taxtype"
								id="taxTypenew"  >
								<option  selected value="">Select Your Tax Type</option>
									<option value="GST">GST</option>
									<option value="servicetax">Service Tax </option>
								 
							</select>
						</div> 
					</div> 
							
							
							
					
					<div class="col-sm-2" >
					<div class="form-group">
						<label for="Username" class=" control-label">Config
							Name</label> 
						 <input type="hidden" id="parentid1"
					value="${configList[2].companyConfigType.b2E}"> 
					 <input type="hidden" id="parentid2"
					value="${configList[2].companyConfigType.b2B}"> 
					 <input type="hidden" id="parentid3"
					value="${configList[2].companyConfigType.b2C}"> 
					 <input type="hidden" id="parentid1"
					value="${configList[2].companyConfigType.API}">
					 <input type="hidden" id="parentid1"
					value="${configList[2].companyConfigType.whitelable}">
							<input type="text" class="form-control input-sm" id="configname"
								name="configname" placeholder="config name" autocomplete="off"
								required>
						</div>
					</div>
					<div class="col-sm-2 b2eHide" >
				<div class="form-group ">
						<label for="City" class=" control-label">Rate Type
							Flight </label>
						 
							<select class="form-control input-sm" name="rateTypeFlight"
								id="rateTypeFlight" autocomplete="off">
								<option value="Net" selected >Nett</option>
								<option value="Commission"><s:text
										name="global.Incentive"></s:text></option>
							</select>
						</div>
					</div>
					<div class="col-sm-2 b2eHide" >
					<div class="form-group ">
							<label for="City" class=" control-label">Rate
								Type Hotel </label>
							 
								<select class="form-control input-sm" name="rateTypeHotel"
									id="rateTypeHotel" >
									<option value="Net" selected>Nett</option>
									<option value="Commission"><s:text
											name="global.Incentive"></s:text></option>
								</select>
							</div>
						</div>
						<div class="col-sm-2" >
						 <div class="form-group">
						<label for="City" class="control-label">Status </label>
						 
							<select class="form-control input-sm" name="configStatus"
								id="configStatus" autocomplete="off" required>
								<option value="yes" selected="selected">Unlocked</option>
								<option value="no">Locked</option>
							</select>
						</div>
					</div>
					
					
					<div class="commission-group-flight col-sm-12" style="display: none">
					<div class="col-sm-1" >
					<div class="form-group"
							style="text-align: left;">
							<label><input type="checkbox" id="checkSheet" name="SheetMode"  value="true" >
								Use Deal Sheet</label> <!-- <input type="hidden" value="0"
								name="dealSheetStatus" id="dealSheetStatus" /> -->
						</div>
						</div>
					<div class="col-sm-2" id="sheet_block_div" style="display: none" >	
					<div class="form-group" >
						<label for="City" class=" control-label">Select
							Block </label>
						 
							<select class="form-control input-sm" name="appliedBlockId"
								id="blocksSelect" autocomplete="off">
							</select>
						</div>
					</div>
					<div class="col-sm-2" id="sheet_emptyblock_div"
						style="display: none">
					<div class="form-group" >
						 
							<label id="configdealsheeterror" style="color: red;"></label>
						</div>
					</div>
					</div>
					
					
					<div class="commission-group-flight col-sm-12" style="display: none" id="d222">
					<div class="col-sm-2">
						<div class="form-group">
							<label for="City" class=" control-label"><s:text
									name="global.Incentive"></s:text> Type Flight</label>
							
								<select class="form-control input-sm"
									name="commissionTypeFlight" id="commissionTypeFlight"
									 >
									<option value="Percentage" selected="selected">Percentage</option>
									<option value="Fixed" readonly="readonly">Fixed</option>
								</select>
							</div>
						</div>
							<div class="col-sm-2">
						<div class="form-group">
							<label for="Username" class=" control-label"><s:text
									name="global.Incentive"></s:text> Flight</label>
							 
								<input type="text" class="form-control input-sm" id="commission"
									value="0.00" name="commissionAmountFlight" placeholder="amount"
									autocomplete="off" onkeypress="return isNumberKey(event,this);">
							</div>
						</div>
						</div>
						
												
						<div class="commission-group-hotel col-sm-12" style="display: none" id="d222">
						<div class="col-sm-2">
						<div class="form-group" id="commission_amount_div">
							<label for="Username" class="col-sm-2 control-label"> </label> 
								<table border="1">
									<tr>
										<td style="text-align: center; color: red">Child <s:text
												name="global.Incentive"></s:text> must be less than or equal
											to parent <s:text name="global.Incentive"></s:text></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="col-sm-2">
						<div class="form-group">
							<label for="City" class=" control-label"><s:text
									name="global.Incentive"></s:text> Type Hotel</label>
						 
								<select class="form-control input-sm" name="commissionTypeHotel"
									id="commissionType" >
									<option value="Percentage" selected="selected">Percentage</option>
									<option value="Fixed" disabled="disabled">Fixed</option>
								</select>
							</div>
						</div>
					

						<div class="col-sm-2">
						<div class="form-group">
							<label for="Username" class=" control-label"><s:text
									name="global.Incentive"></s:text> Hotel</label> 
								<input type="text" class="form-control input-sm" id="commission"
									value="0.00" name="commissionAmountHotel" placeholder="amount"
									autocomplete="off" onkeypress="return isNumberKey(event,this);">
							</div>
						</div>
					</div>
						
					</div>
					</div>
					
					
		
					<div class="companysetup clearfix whitelabel-form-group"> 
						<div class="companyset-heading"> 
							<div class="companyset-icon"> 
									 <i class="fa fa-life-ring fa-2x"></i>  
									 <b>WhiteLabel</b>  
							</div> 
						</div>
				<div class="inner-compreg"> 
					<input type="hidden" class="form-control input-sm" value="false" id="whitelabelActive" name="insertionRequestModel.whitelabelActive">
						<!-- <div class="form-group">
							<label class="control-label">Product Type</label>							
												
							<label class="form-control input-sm">WhiteLabel</label>				
							</div>
						</div> -->
						<div class="col-sm-12 row">
				<div class="col-sm-2">		
				 <div class="form-group">
						<label for="themeName" class="control-label">Theme Name</label>
					 
							<!-- <input type="text" class="form-control input-sm" value=""
								 id="themeName" name="insertionRequestModel.themeName"
								placeholder="Enter theme name"> -->
							<select class="form-control input-sm" name="insertionRequestModel.themeName"
									id="themeName">
									    <option value="0">Please select</option>
										<option value="Default">Default</option>
										<option value="Mercury">Mercury</option>								 	 
								</select>
								
						</div>
					</div>
					<div class="col-sm-2">		
					<div class="form-group">
						<label for="designPattern" class=" control-label">Design Pattern</label>
						 
							<input type="text" class="form-control input-sm" value=""
								 id="designPattern" name="insertionRequestModel.designPattern"
								placeholder="Enter design pattern">
						</div>
					</div>
					<div class="col-sm-2">	
					<div class="form-group">
						<label for="designPattern" class="col-sm-2 control-label">Url</label>
						 
							<input type="text" class="form-control input-sm" value=""
								 id="designPattern" name="insertionRequestModel.url"
								placeholder="Enter Url">
						</div>
					</div>
					<div class="col-sm-2">	
					<div class="form-group">
						<label for="cssPath" class=" control-label">CSS path</label>
						 
							<!-- <input type="file" class="form-control input-sm" value=""
								 id="uploadimage" name="insertionRequestModel.cssFile"
								placeholder="Enter CSS path"> -->								
									<input type="text" class="form-control input-sm" value=""
								 id="cssName" name="insertionRequestModel.cssPath"
								placeholder="Selected Css" readonly>
						</div>
						
					</div> 
					<div class="col-sm-3">
					<div class="form-group">
						<label for="logoImagePath" class=" control-label">Logo Image Path</label>
						<div class="col-sm-12 row">
							<!-- <input type="file" class="form-control input-sm" value=""
								 id="uploadimage" name="insertionRequestModel.logoImageFile"
								placeholder="Enter Logo Image Path"> -->
								 

							<div class="row">
								<div class="col-sm-6 file-upload"> 
									<input type="file" id="uploadimage" ngf-pattern="image/*"
										ng-file-select="onFileSelect($files)" name="insertionRequestModel.logoImageFile">									
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
					 
					<div class="col-sm-12 row"> 
					<div class="form-group col-sm-3 col-md-2">
						<input type="checkbox" class="toCheck" id="FlightCheck" name="insertionRequestModel.enableFlight" value="false"/>
						<label for="enableFlight" class="control-label">Enable Flight</label>
					</div>
					<div class="form-group col-sm-3 col-md-2  floatingLeft">
						<input type="checkbox" class="toCheck" id="enableHotel" name="insertionRequestModel.enableHotel" value="false"/>
						<label for="enableFlight" class="control-label">Enable Hotel</label>
					</div>
					<div class="form-group  col-sm-3 col-md-2  floatingLeft">
						<input type="checkbox" class="toCheck" id="enableCar" name="insertionRequestModel.enableCar" value="false"/>
						<label for="enableCar" class="control-label">Enable Car</label>
					</div>
					<div class="form-group col-sm-3 col-md-2  floatingLeft">
						<input type="checkbox" class="toCheck" id="enableBus" name="insertionRequestModel.enableBus" value="false"/>
						<label for="enableBus" class="control-label">Enable Bus</label>
					</div>				
					<!-- </div>
					
					 <div class="row"> -->
					<div class="form-group  col-sm-3 col-md-2  floatingLeft">
						<input type="checkbox" class="toCheck" id="enableTrain" name="insertionRequestModel.enableTrain" value="false"/>
						<label for="enableTrain" class="control-label">Enable Train</label>
					</div>
					<div class="form-group  col-sm-3 col-md-2  floatingLeft">
						<input type="checkbox" class="toCheck" id="enableInsurance" name="insertionRequestModel.enableInsurance" value="false"/>
						<label for="enableInsurance" class="control-label">Enable Insurance</label>
					</div>
					<div class="form-group  col-sm-3 col-md-2 floatingLeft">
						<input type="checkbox" class="toCheck" id="enableVisa" name="insertionRequestModel.enableVisa" value="false"/>
						<label for="enableVisa" class="control-label">Enable Visa</label>
					</div>
					<div class="form-group col-sm-3 col-md-2  floatingLeft">
						<input type="checkbox" class="toCheck" id="enableMiscellaneous" name="insertionRequestModel.enableMiscellaneous" value="false"/>
						<label for="enableMiscellaneous" class="control-label">Enable Miscellaneous</label>
					</div>				
					<!-- </div>
				 <div class="row"> -->
					<div class="form-group col-sm-3 col-md-2   floatingLeft">
						<input type="checkbox" class="toCheck" id="enableLogoOnVoucher" name="insertionRequestModel.enableLogoOnVoucher" value="false"/>
						<label for="enableLogoOnVoucher" class="control-label">Enable Logo On Voucher</label>
					</div>
					<div class="form-group col-sm-3 col-md-2  floatingLeft">
						<input type="checkbox" class="toCheck" id="enableHeader" name="insertionRequestModel.enableHeader" value="false"/>
						<label for="enableHeader" class="control-label">Enable Header</label>
					</div>
					<div class="form-group  col-sm-3 col-md-2  floatingLeft">
						<input type="checkbox" class="toCheck" id="enableFooter" name="insertionRequestModel.enableFooter" value="false"/>
						<label for="enableFooter" class="control-label">Enable Footer</label>
					</div>
					<div class="form-group  col-sm-3 col-md-2  floatingLeft">
						<input type="checkbox" class="toCheck" id="enablePaymentGateway" name="insertionRequestModel.enablePaymentGateway" value="false"/>
						<label for="enablePaymentGateway" class="control-label">Enable Payment Gateway</label>
					</div>				
					</div>
					  
					
					 <div class="underLineDiv">
					  <p class="text-center fontConf" style="border-bottom: 1px solid #b7aeae; text-align: left"> Page Configurations </p>
					 
					<div class="row col-sm-12">
					<div class="form-group col-sm-3 col-md-2 floatingLeft">
						<input type="checkbox" class="toCheck" id="enableAboutUS" name="insertionRequestModel.enableAboutUS" value="false"/>
						<label for="enableAboutUS" class="control-label">Enable About US</label>
					</div>
					<div class="form-group col-sm-3 col-md-2 floatingLeft">
						<input type="checkbox" class="toCheck" id="enableContactUs" name="insertionRequestModel.enableContactUs" value="false"/>
						<label for="enableContactUs" class="control-label">Enable Contact Us</label>
					</div>
					<div class="form-group col-sm-3 col-md-2 floatingLeft">
						<input type="checkbox" class="toCheck" id="enableTermsCondition" name="insertionRequestModel.enableTermsCondition" value="false"/>
						<label for="enableTermsCondition" class="control-label">Enable Terms & Condition</label>
					</div>
					<div class="form-group col-sm-3 col-md-2 floatingLeft">
						<input type="checkbox" class="toCheck" id="enablePrivacyPolicy" name="insertionRequestModel.enablePrivacyPolicy" value="false"/>
						<label for="enablePrivacyPolicy" class="control-label">Enable Privacy Policy</label>
					</div>				
					<!-- </div>
					
					 <div class="row"> -->
					<div class="form-group col-sm-3 col-md-2 floatingLeft">
						<input type="checkbox" class="toCheck" id="enableSocialMedia" name="insertionRequestModel.enableSocialMedia" value="false"/>
						<label for="enableSocialMedia" class="control-label">Enable SocialMedia</label>
					</div>
					<div class="form-group col-sm-3 col-md-2 floatingLeft">
						<input type="checkbox" class="toCheck" id="enableDeals" name="insertionRequestModel.enableDeals" value="false"/>
						<label for="enableDeals" class="control-label">Enable EnableDeals</label>
					</div>
					<div class="form-group col-sm-3 col-md-2 floatingLeft">
						<input type="checkbox" class="toCheck" id="enableSlider" name="insertionRequestModel.enableSlider" value="false"/>
						<label for="enableSlider" class="control-label">Enable EnableSlider</label>
					</div> 				
					</div>			  
					 </div> 
					</div>
					</div>
					
					
					<div class="companysetup clearfix flight-international-gst-form-group"> 
						<div class="companyset-heading"> 
							<div class="companyset-icon"> 
									 <i class="fa fa-life-ring fa-2x"></i>  
									 <b>GST</b>  
							</div> 
						</div>
						<div class="inner-compreg"> 
						
						
				<div class="flight-international-gst-form-group">
				
				<div class="col-sm-12">
					<div class="form-group" style="margin-bottom: 0px;"> 
						<b style="border-bottom: 1px solid #000;">Flight - International - GST</b>				
						</div>
					</div>
					
					 
					 
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Applicable Fare</label>							
						
						<input type="hidden" name="flightInternationalGstTaxConfig.serviceType" value="International">
						<input type="text" class="form-control input-sm" name="flightInternationalGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
						</div>
					 
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" name="flightInternationalGstTaxConfig.CGST"  id="flightinternationalCGST"  value="${companyConfigTax.gstTaxObj.flightInternationalGstTaxConfig.CGST }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightinternationalSGST" name="flightInternationalGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.flightInternationalGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">IGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightInternationalIGST" name="flightInternationalGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.flightInternationalGstTaxConfig.IGST }"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">UGST(Applicable for Union Territory )</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightInternationalUGSTTax" name="flightInternationalGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.flightInternationalGstTaxConfig.UGST }"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightInternationalGSTconvenienceFee" name="flightInternationalGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.flightInternationalGstTaxConfig.convenienceFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Management Fee</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightInternationalGSTmanagementFee" name="flightInternationalGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.flightInternationalGstTaxConfig.managementFee }"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					 
				</div>
					 
					
					
					
					<div class="flight-domestic-gst-form-group"> 
						<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Flight - Domestic - GST</b>				
							</div>
						</div>
					 
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Applicable Fare</label>							
						
						<input type="text" class="form-control input-sm" name="flightDomesticGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" name="flightDomesticGstTaxConfig.CGST"  id="flightdomesticCGST"  value="${companyConfigTax.gstTaxObj.flightDomesticGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightdomesticSGST" name="flightDomesticGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.flightDomesticGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">IGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightdomesticIGST" name="flightDomesticGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.flightDomesticGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">UGST(Applicable for Union Territory )</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightdomesticUGSTTax" name="flightDomesticGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.flightDomesticGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightdomesticGSTconvenienceFee" name="flightDomesticGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.flightDomesticGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Management Fee</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="flightdomesticGSTmanagementFee" name="flightDomesticGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.flightDomesticGstTaxConfig.managementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					 
					 </div>
					
					
					<div class="hotel-gst-form-group">
					
					<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Hotel - GST</b>				
							</div>
						</div> 
					<div class="col-sm-1">
					
					<div class="form-group">
						<label class="control-label">Applicable Fare</label>							
						
						<input type="text" class="form-control input-sm"  name="hotelGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>							
												
						<input type="text" class="form-control input-sm" name="hotelGstTaxConfig.CGST" onchange="numbersonly(this)"  id="hotelCGST"  value="${companyConfigTax.gstTaxObj.hotelGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="hotelSGST" name="hotelGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.hotelGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">IGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="hotelIGST" name="hotelGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.hotelGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">UGST(Applicable for Union Territory )</label>							
												
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="hotelUGSTTax" name="hotelGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.hotelGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)" id="hotelGSTconvenienceFee" name="hotelGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.hotelGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Management Fee (International)</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="hotelGSTmanagementFee" name="hotelGstTaxConfig.intlManagementFee" value="${companyConfigTax.gstTaxObj.hotelGstTaxConfig.intlManagementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Management Fee (Domestic)</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="hotelGSTdomesticmanagementFee" name="hotelGstTaxConfig.domesticManagementFee" value="${companyConfigTax.gstTaxObj.hotelGstTaxConfig.domesticManagementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					 
				
					</div>
					
					<div class="car-gst-form-group">
						<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Car - GST</b>				
							</div>
						</div>  
					<div class="col-sm-1"> 
					<div class="form-group">
						<label class="control-label">Applicable Fare</label>							
						
						<input type="text" class="form-control input-sm" name="carGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  name="carGstTaxConfig.CGST"  id="carCGST"  value="${companyConfigTax.gstTaxObj.carGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label>							
												
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="carSGST" name="carGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.carGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">IGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="carIGST" name="carGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.carGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">UGST(Applicable for Union Territory )</label>							
												
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)"  id="carUGSTTax" name="carGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.carGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>							
												
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="carGSTconvenienceFee" name="carGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.carGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Management Fee</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="carGSTmanagementFee" name="carGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.carGstTaxConfig.managementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					 
				
					</div>
					
					<div class="bus-gst-form-group">
					<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Bus - GST</b>				
							</div>
						</div>   
					 
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Applicable Fare</label>							
						
						<input type="text" class="form-control input-sm" name="busGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  name="busGstTaxConfig.CGST"  id="busCGST"  value="${companyConfigTax.gstTaxObj.busGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="busSGST" name="busGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.busGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">IGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="busIGST" name="busGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.busGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">UGST(Applicable for Union Territory )</label>							
												
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)"  id="busUGSTTax" name="busGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.busGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="busGSTconvenienceFee" name="busGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.busGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Management Fee</label>							
												
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="busGSTmanagementFee" name="busGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.busGstTaxConfig.managementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					 
				
					</div>
					
					
					<div class="rail-gst-form-group">
					
					<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Train - Gst</b>				
							</div>
						</div> 
					
					 
					 
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Applicable Fare</label>							
						
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" name="trainGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  name="trainGstTaxConfig.CGST"  id="railCGST"  value="${companyConfigTax.gstTaxObj.trainGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label>							
												
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="railSGST" name="trainGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.trainGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">IGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="railIGST" name="trainGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.trainGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">UGST(Applicable for Union Territory )</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="railUGSTTax" name="trainGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.trainGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>							
												
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="railGSTconvenienceFee" name="trainGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.trainGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Management Fee (General)</label>							
												
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="railGSTmanagementFee" name="trainGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.trainGstTaxConfig.managementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Management Fee (Tatkal)</label>							
						 						
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="railGSTmanagementFeeTatkal" name="trainGstTaxConfig.managementFeeTatkal" value="${companyConfigTax.gstTaxObj.trainGstTaxConfig.managementFeeTatkal}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					 
				
					</div>
					
					
					<div class="visa-gst-form-group">
					
						<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Visa - GST</b>				
							</div>
						</div>  
				 
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Applicable Fare</label>	 
						<input type="text" class="form-control input-sm" name="visaGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>  
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  name="visaGstTaxConfig.CGST"  id="visaCGST"  value="${companyConfigTax.gstTaxObj.visaGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label> 
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="visaSGST" name="visaGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.visaGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">IGST</label>	 
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="visaIGST" name="visaGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.visaGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">UGST(Applicable for Union Territory )</label>	 
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)"  id="visaUGSTTax" name="visaGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.visaGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>							
						 			
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="visaGSTconvenienceFee" name="visaGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.visaGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Management Fee</label> 
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="visaGSTmanagementFee" name="visaGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.visaGstTaxConfig.managementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div> 
				
					</div>
					
					<div class="insurance-gst-form-group">					
					<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Insurance - Gst</b>				
							</div>
						</div> 
					 
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Applicable Fare</label>							
					 
						<input type="text" class="form-control input-sm" name="insuranceGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>							
					 					
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  name="insuranceGstTaxConfig.CGST"  id="insuranceCGST"  value="${companyConfigTax.gstTaxObj.insuranceGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label>							
						 					
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="insuranceSGST" name="insuranceGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.insuranceGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">IGST</label>							
						 					
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="insuranceIGST" name="insuranceGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.insuranceGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">UGST(Applicable for Union Territory )</label>							
						 						
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)"  id="insuranceUGSTTax" name="insuranceGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.insuranceGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>							
					 					
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="insuranceGSTconvenienceFee" name="insuranceGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.insuranceGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Management Fee</label> 
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="insuranceGSTmanagementFee" name="insuranceGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.insuranceGstTaxConfig.managementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					 
				
					</div>
					<div class="holiday-gst-form-group">					
					<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Holiday - GST</b>				
							</div>
						</div> 
				 
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Applicable Fare</label>	 
						<input type="text" class="form-control input-sm" name="holidayGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label> 
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  name="holidayGstTaxConfig.CGST"  id="holidayCGST"  value="${companyConfigTax.gstTaxObj.holidayGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label> 
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="holidaySGST" name="holidayGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.holidayGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">IGST</label>							
						 					
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="holidayIGST" name="holidayGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.holidayGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">UGST(Applicable for Union Territory )</label>							
						 			
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)"  id="holidayUGSTTax" name="holidayGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.holidayGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>							
						 					
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="holidayGSTconvenienceFee" name="holidayGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.holidayGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Management Fee</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="holidayGSTmanagementFee" name="holidayGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.holidayGstTaxConfig.managementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div> 
					</div>
					
					<div class="advertisement-gst-form-group">					
					<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Advertisement - GST</b>				
							</div>
						</div>  
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Applicable Fare</label> 
						<input type="text" class="form-control input-sm" name="advertisementGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>							
						 			
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" name="advertisementGstTaxConfig.CGST"  id="advertisementCGST"  value="${companyConfigTax.gstTaxObj.advertisementGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">SGST</label>							
						 				
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="advertisementSGST" name="advertisementGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.advertisementGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">IGST</label> 
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="advertisementIGST" name="advertisementGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.advertisementGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">UGST(Applicable for Union Territory )</label> 
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)"  id="advertisementUGSTTax" name="advertisementGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.advertisementGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="  control-label">Convenience Fee</label>	 
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="advertisementGSTconvenienceFee" name="advertisementGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.advertisementGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Management Fee</label>	 
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="advertisementGSTmanagementFee" name="advertisementGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.advertisementGstTaxConfig.managementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div> 
					</div>
					
					
					
					<div class="miscellaneous-gst-form-group">					
					<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Miscellaneous - GST</b>				
							</div>
						</div> 
					
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Applicable Fare</label>							
						 
						<input type="text" class="form-control input-sm" name="miscellaneousGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">CGST</label>							
												
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  name="miscellaneousGstTaxConfig.CGST"  id="miscellaneousCGST"  value="${companyConfigTax.gstTaxObj.miscellaneousGstTaxConfig.CGST}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">SGST</label>							
						 					
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="miscellaneousSGST" name="miscellaneousGstTaxConfig.SGST" value="${companyConfigTax.gstTaxObj.miscellaneousGstTaxConfig.SGST}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">IGST</label>							
				 						
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="miscellaneousIGST" name="miscellaneousGstTaxConfig.IGST" value="${companyConfigTax.gstTaxObj.miscellaneousGstTaxConfig.IGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">UGST(Applicable for Union Territory )</label>	 
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)"  id="miscellaneousUGSTTax" name="miscellaneousGstTaxConfig.UGST" value="${companyConfigTax.gstTaxObj.miscellaneousGstTaxConfig.UGST}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Convenience Fee</label>	
						<input type="text" class="form-control input-sm" onchange="numbersonly(this)"  id="miscellaneousGSTconvenienceFee" name="miscellaneousGstTaxConfig.convenienceFee"  value="${companyConfigTax.gstTaxObj.miscellaneousGstTaxConfig.convenienceFee}"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class="control-label">Management Fee</label>							
						 				
						<input type="text" class="form-control input-sm"  onchange="numbersonly(this)" id="miscellaneousGSTmanagementFee" name="miscellaneousGstTaxConfig.managementFee" value="${companyConfigTax.gstTaxObj.miscellaneousGstTaxConfig.managementFee}"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					
				
					</div>
							
							
							
						</div>
				</div>
				
					<div class="companysetup clearfix flight-international-form-group"> 
						<div class="companyset-heading"> 
							<div class="companyset-icon">  
									 <i class="fa fa-pie-chart fa-2x"></i>  
									 <b>Service Tax</b>  
							</div> 
						</div>
						<div class="inner-compreg"> 
						<div class="flight-international-form-group clearfix row col-sm-12">
					
					<div class="form-group" style="margin-bottom: 0px;">
						 					
						<div class="col-sm-12">					
							<b style="border-bottom: 1px solid #000;">Flight - International</b>				
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label">Applicable Fare</label> 
							<input type="hidden" name="flightInternationalServiceTaxConfig.serviceType" value="International">
							<input type="text" class="form-control input-sm" name="flightInternationalServiceTaxConfig.applicableFare" value="Basic + YQ" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Basic Tax</label>	 
						<input type="text" class="form-control input-sm" name="flightInternationalServiceTaxConfig.basicTax"  id="flightinternationalbasetax"  value="${companyConfigTax.serviceTaxObj.flightInternationalServiceTaxConfig.basicTax }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Swatch Bharath Cess</label> 
						<input type="text" class="form-control input-sm" id="flightinternationalswatchBharathCess" name="flightInternationalServiceTaxConfig.swatchBharathCess" value="${companyConfigTax.serviceTaxObj.flightInternationalServiceTaxConfig.swatchBharathCess}" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Krishi Kalyan Cess</label>							
						 						
						<input type="text" class="form-control input-sm" id="flightInternationalkrishiKalyanCess" name="flightInternationalServiceTaxConfig.krishiKalyanCess" value="${companyConfigTax.serviceTaxObj.flightInternationalServiceTaxConfig.krishiKalyanCess }"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					
					<div class="col-sm-1">
						<div class="form-group">
							<label class=" control-label">Convenience Fee</label> 
							<input type="text" class="form-control input-sm" id="flightInternationalconvenienceFee" name="flightInternationalServiceTaxConfig.convenienceFee"  value="${companyConfigTax.serviceTaxObj.flightInternationalServiceTaxConfig.convenienceFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Management Fee</label> 
						<input type="text" class="form-control input-sm" id="flightInternationalmanagementFee" name="flightInternationalServiceTaxConfig.managementFee" value="${companyConfigTax.serviceTaxObj.flightInternationalServiceTaxConfig.managementFee }"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					
					<div class="col-sm-1">
					<div class="form-group">
						<label class="control-label">Total Tax</label>	 
						<input type="text" class="form-control input-sm"  id="flightInternationaltotalTax" name="flightInternationalServiceTaxConfig.totalTax" value="${companyConfigTax.serviceTaxObj.flightInternationalServiceTaxConfig.totalTax }"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div> 
					</div>
					 		
					<div class="flight-domestic-form-group clearfix row col-sm-12">
					<div class="col-sm-12">
					<div class="form-group" style="margin-bottom: 0px;"> 
						<b style="border-bottom: 1px solid #000;">Flight - Domestic</b>				
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<label class=" control-label">Applicable Fare</label> 
							<input type="hidden" name="flightDomesticServiceTaxConfig.serviceType" value="Domestic">
							<input type="text" class="form-control input-sm" name="flightDomesticServiceTaxConfig.applicableFare" value="Basic + YQ" autocomplete="off"	 readonly="readonly">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Basic Tax</label>		 
						<input type="text" class="form-control input-sm" id="flightDomesticbasicTax" name="flightDomesticServiceTaxConfig.basicTax"  value="${companyConfigTax.serviceTaxObj.flightDomesticServiceTaxConfig.basicTax }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Swatch Bharath Cess</label>	 
						<input type="text" class="form-control input-sm" id="flightDomesticswatchBharathCess" name="flightDomesticServiceTaxConfig.swatchBharathCess" value="${companyConfigTax.serviceTaxObj.flightDomesticServiceTaxConfig.swatchBharathCess }"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Krishi Kalyan Cess</label>	 
						<input type="text" class="form-control input-sm" id="flightDomestickrishiKalyanCess" name="flightDomesticServiceTaxConfig.krishiKalyanCess" value="${companyConfigTax.serviceTaxObj.flightDomesticServiceTaxConfig.krishiKalyanCess }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Total Tax</label>		 
						<input type="text" class="form-control input-sm" id="flightDomestictotalTax" name="flightDomesticServiceTaxConfig.totalTax" value="${companyConfigTax.serviceTaxObj.flightDomesticServiceTaxConfig.totalTax }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Convenience Fee</label> 
						<input type="text" class="form-control input-sm" id="flightDomesticconvenienceFee" name="flightDomesticServiceTaxConfig.convenienceFee" value="${companyConfigTax.serviceTaxObj.flightDomesticServiceTaxConfig.convenienceFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Management Fee</label>							
						 						
						<input type="text" class="form-control input-sm" id="flightDomesticmanagementFee" name="flightDomesticServiceTaxConfig.managementFee" value="${companyConfigTax.serviceTaxObj.flightDomesticServiceTaxConfig.managementFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					</div>
					
						<div class="hotel-form-group clearfix row col-sm-12"  >
						<div class="col-sm-12">
						<div class="form-group" style="margin-bottom: 0px;"> 
							<b style="border-bottom: 1px solid #000;">Hotel</b>				
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Applicable Fare</label> 
						<input type="text" class="form-control input-sm" name="hotelServiceTaxConfig.applicableFare" value="Total Fare" autocomplete="off"	 readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Basic Tax</label>							
						 					
						<input type="text" class="form-control input-sm" id="hotelbasicTax" name="hotelServiceTaxConfig.basicTax" value="${companyConfigTax.serviceTaxObj.hotelServiceTaxConfig.basicTax }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Swatch Bharath Cess</label> 
						<input type="text" class="form-control input-sm" id="hotelswatchBharathCess" name="hotelServiceTaxConfig.swatchBharathCess" value="${companyConfigTax.serviceTaxObj.hotelServiceTaxConfig.swatchBharathCess }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Krishi Kalyan Cess</label> 
						<input type="text" class="form-control input-sm" id="hotelkrishiKalyanCess" name="hotelServiceTaxConfig.krishiKalyanCess" value="${companyConfigTax.serviceTaxObj.hotelServiceTaxConfig.krishiKalyanCess }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Total Tax</label>		 
						<input type="text" class="form-control input-sm" id="hoteltotalTax" name="hotelServiceTaxConfig.totalTax" value="${companyConfigTax.serviceTaxObj.hotelServiceTaxConfig.totalTax }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Convenience Fee</label>							
						 				
						<input type="text" class="form-control input-sm" id="hotelconvenienceFee" name="hotelServiceTaxConfig.convenienceFee" value="${companyConfigTax.serviceTaxObj.hotelServiceTaxConfig.convenienceFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Management Fee (International)</label> 
						<input type="text" class="form-control input-sm" id="hotelmanagementFee" name="hotelServiceTaxConfig.managementFee" value="${companyConfigTax.serviceTaxObj.hotelServiceTaxConfig.managementFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Management Fee (Domestic)</label> 
						<input type="text" class="form-control input-sm" id="hoteldomesticmanagementfee" name="hotelServiceTaxConfig.domesticManagementFee" value="${companyConfigTax.serviceTaxObj.hotelServiceTaxConfig.domesticManagementFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					</div>
					
					<div class="bus-form-group clearfix row  col-sm-12" >
					<div class="col-sm-12">
				<div class="form-group" style="margin-bottom: 0px;"> 
						<b style="border-bottom: 1px solid #000;">Bus/Car</b>				
						</div>
					</div>
					<div class="col-sm-1"> 
					<div class="form-group">
						<label class=" control-label">Applicable Fare</label>							
						 					
						<input type="text" class="form-control input-sm" id="busapplicableFare" name="busServiceTaxConfig.applicableFare" value="Total Fare" autocomplete="off" readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Basic Tax</label>							
						 			
						<input type="text" class="form-control input-sm" id="busbasicTax" name="busServiceTaxConfig.basicTax" value="${companyConfigTax.serviceTaxObj.busServiceTaxConfig.basicTax }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Swatch Bharath Cess</label>	 
						<input type="text" class="form-control input-sm" id="bussbt" name="busServiceTaxConfig.swatchBharathCess" value="${companyConfigTax.serviceTaxObj.busServiceTaxConfig.swatchBharathCess }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Krishi Kalyan Cess</label> 
						<input type="text" class="form-control input-sm" id="buskkt" name="busServiceTaxConfig.krishiKalyanCess" value="${companyConfigTax.serviceTaxObj.busServiceTaxConfig.krishiKalyanCess }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Total Tax</label>	 
						<input type="text" class="form-control input-sm" id="bustotaltax" name="busServiceTaxConfig.totalTax" value="${companyConfigTax.serviceTaxObj.busServiceTaxConfig.totalTax }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group" >
						<label class=" control-label">Convenience Fee</label>							
						 						
						<input type="text" class="form-control input-sm" id="busconvenienceFee" name="busServiceTaxConfig.convenienceFee" value="${companyConfigTax.serviceTaxObj.busServiceTaxConfig.convenienceFee }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label"> Management Fee (Bus)</label>	 
						<input type="text" class="form-control input-sm" id="busmanagementfee" name="busServiceTaxConfig.managementFee" value="${companyConfigTax.serviceTaxObj.busServiceTaxConfig.managementFee }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label"> Management Fee (Car)</label>	 
						<input type="text" class="form-control input-sm" id="carmanagementfee" name="busServiceTaxConfig.carManagementFee" value="${companyConfigTax.serviceTaxObj.carServiceTaxConfig.managementFee }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					</div> 
					
					<div class="holiday-form-group clearfix row col-sm-12"  >
					<div class="col-sm-12">
				<div class="form-group" style="margin-bottom: 0px;"> 
						<b style="border-bottom: 1px solid #000;">Holiday Package</b>				
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Applicable Fare</label>							
						 					
						<input type="text" class="form-control input-sm" name="holidayServiceTaxConfig.applicableFare" value="Total Fare" autocomplete="off"	 readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Basic Tax</label>							
						 					
						<input type="text" class="form-control input-sm" id="holidaybasicTax" name="holidayServiceTaxConfig.basicTax" value="${companyConfigTax.serviceTaxObj.holidayServiceTaxConfig.basicTax }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Swatch Bharath Cess</label>							
						 				
						<input type="text" class="form-control input-sm" id="holidayswatchBharathCess" name="holidayServiceTaxConfig.swatchBharathCess" value="${companyConfigTax.serviceTaxObj.holidayServiceTaxConfig.swatchBharathCess }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Krishi Kalyan Cess</label> 
						<input type="text" class="form-control input-sm" id="holidaykkc" name="holidayServiceTaxConfig.krishiKalyanCess" value="${companyConfigTax.serviceTaxObj.holidayServiceTaxConfig.krishiKalyanCess }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Total Tax</label>							
						 				
						<input type="text" class="form-control input-sm" id="holidaytotaltax" name="holidayServiceTaxConfig.totalTax" value="${companyConfigTax.serviceTaxObj.holidayServiceTaxConfig.totalTax}"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Convenience Fee</label>							
						 					
						<input type="text" class="form-control input-sm" id="holidayconvfee" name="holidayServiceTaxConfig.convenienceFee" value="${companyConfigTax.serviceTaxObj.holidayServiceTaxConfig.convenienceFee }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Management Fee (International)</label>							
						 				
						<input type="text" class="form-control input-sm" id="holidaymgmtfeeinternational" name="holidayServiceTaxConfig.managementFee" value="${companyConfigTax.serviceTaxObj.holidayServiceTaxConfig.managementFee }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Management Fee (Domestic)</label>							
						 				
						<input type="text" class="form-control input-sm"  id="holidaydomesticManagementfee" name="holidayServiceTaxConfig.domesticManagementFee" value="${companyConfigTax.serviceTaxObj.holidayServiceTaxConfig.domesticManagementFee }"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					</div>
					
				
					
					<div class="rail-form-group clearfix row col-sm-12"  > 
					<div class="col-sm-12"> 
					<div class="form-group" style="margin-bottom: 0px;"> 
						<b style="border-bottom: 1px solid #000;">Train/Visa/Advertisement &amp; Others</b>	
						</div> 
					</div>
					<div class="col-sm-1"> 
					<div class="form-group">
						<label class=" control-label">Applicable Fare</label>							
						 						
						<input type="text" class="form-control input-sm" name="railServiceTaxConfig.applicableFare" value="Only Fee" autocomplete="off"	 readonly="readonly">
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Basic Tax</label>							
						 					
						<input type="text" class="form-control input-sm" id="railbasicTax" name="railServiceTaxConfig.basicTax"  value="${companyConfigTax.serviceTaxObj.railServiceTaxConfig.basicTax }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Swatch Bharath Cess</label>							
						 			
						<input type="text" class="form-control input-sm" id="railsbc" name="railServiceTaxConfig.swatchBharathCess"  value="${companyConfigTax.serviceTaxObj.railServiceTaxConfig.swatchBharathCess }" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Krishi Kalyan Cess</label>							
						 			
						<input type="text" class="form-control input-sm" id="railkkc" name="railServiceTaxConfig.krishiKalyanCess" value="${companyConfigTax.serviceTaxObj.railServiceTaxConfig.krishiKalyanCess }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-1">
					<div class="form-group">
						<label class=" control-label">Total Tax</label>							
						 					
						<input type="text" class="form-control input-sm" id="railtotaltax" name="railServiceTaxConfig.totalTax" value="${companyConfigTax.serviceTaxObj.railServiceTaxConfig.totalTax }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Convenience Fee</label>							
						 				
						<input type="text" class="form-control input-sm" id="railconviencefee"  name="railServiceTaxConfig.convenienceFee" value="${companyConfigTax.serviceTaxObj.railServiceTaxConfig.convenienceFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label class=" control-label">Management Fee General(Rail)</label>							
						 					
						<input type="text" class="form-control input-sm" id="railmgmtfee" name="railServiceTaxConfig.managementFee" value="${companyConfigTax.serviceTaxObj.railServiceTaxConfig.managementFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
						<label class=" control-label">Management Fee Tatkal(Rail)</label>	 
						<input type="text" class="form-control input-sm" id="railmgmtfee" name="railServiceTaxConfig.managementFeeTatkal" value="${companyConfigTax.serviceTaxObj.railServiceTaxConfig.managementFeeTatkal }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-2">			
					<div class="form-group">
						<label class=" control-label">Management Fee (Visa)</label>							
						 				
						<input type="text" class="form-control input-sm" id="visamgmtfee" name="railServiceTaxConfig.visaManagementFee"  value="${companyConfigTax.serviceTaxObj.visaServiceTaxConfig.managementFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="col-sm-3">	
					<div class="form-group">
						<label class=" control-label">Management Fee(Advertisement &amp; Others)</label>							
						 					
						<input type="text" class="form-control input-sm" id="advtandothertax" name="railServiceTaxConfig.advertisementAndOtherManagementFee" value="${companyConfigTax.serviceTaxObj.advertiseandOtherServiceTaxConfig.managementFee }"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					</div>
							
						</div>
				</div>
				
					
 
				 
					<div class="form-group text-center">
						<div class="col-xs-11 submitWrap text-center">
							<button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Add</button>
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

	<%-- <script src="js/jquery-ui-timepicker-addon.min.js">
	
</script> --%>

	<%-- <%@ include file="DashFooter.jsp"%> --%>
	
	<script type="text/javascript">
	
	
	


	 
	</script>
	
	
<script type="text/javascript"> 
		$(function() {				 
			 $('.Product-form-group').hide();
			 $('.taxtype').hide();
   		  $('.flight-domestic-form-group').hide();
			   $('.flight-international-form-group').hide();
			   $('.hotel-form-group').hide();						   
			   $('.bus-form-group').hide();
			    $('.holiday-form-group').hide();						   
			    $('.rail-form-group').hide();
			    $('.flight-international-gst-form-group').hide();
			    $('.flight-domestic-gst-form-group').hide();
			    $('.hotel-gst-form-group').hide();
			    $('.car-gst-form-group').hide();
			    $('.rail-gst-form-group').hide();
			    $('.bus-gst-form-group').hide();
			    $('.insurance-gst-form-group').hide();
			    $('.visa-gst-form-group').hide();
			    $('.holiday-gst-form-group').hide();
			    $('.whitelabel-form-group').hide();
			    $('.advertisement-gst-form-group').hide();
			    $('.miscellaneous-gst-form-group').hide();
			    $('input[name="Whitelable"]').prop('checked', false);
			   
			  $('#configType').change(function() {				   
				      	if($(this).val() == 'B2E'){
			    		 $('.taxtype').show();
						   $('.b2eHide').hide();
						    $('input[name="Whitelable"]').prop('checked', false);
			    	}else if($(this).val() == 'WL'){
			    		 
			    		 $('.Product-form-group').hide();
			    		  $('.flight-domestic-form-group').hide();
						   $('.flight-international-form-group').hide();
						   $('.hotel-form-group').hide();						   
						   $('.bus-form-group').hide();
						    $('.holiday-form-group').hide();						   
						    $('.rail-form-group').hide();
						   $('.taxtype').hide();
						   $('.flight-international-gst-form-group').hide();
						   $('.flight-domestic-gst-form-group').hide();
						   $('.hotel-gst-form-group').hide();
						    $('.car-gst-form-group').hide();
						    $('.rail-gst-form-group').hide();
						    $('.bus-gst-form-group').hide();
						    $('.insurance-gst-form-group').hide();
						    $('.visa-gst-form-group').hide();
						    $('.holiday-gst-form-group').hide();
						    $('.advertisement-gst-form-group').hide();
						    $('.miscellaneous-gst-form-group').hide();
						    $('.whitelabel-form-group').show();	
						    $('input[name="Whitelable"]').prop('checked', true);  
						    $('#whitelabelActive').val("true");
					      
			    	}else{
			    		 $('.Product-form-group').hide();
			    		  $('.flight-domestic-form-group').hide();
						   $('.flight-international-form-group').hide();
						   $('.hotel-form-group').hide();						   
						   $('.bus-form-group').hide();
						    $('.holiday-form-group').hide();						   
						    $('.rail-form-group').hide();
						   $('.taxtype').hide();
						   $('.flight-international-gst-form-group').hide();
						   $('.flight-domestic-gst-form-group').hide();
						   $('.hotel-gst-form-group').hide();
						    $('.car-gst-form-group').hide();
						    $('.rail-gst-form-group').hide();
						    $('.bus-gst-form-group').hide();
						    $('.insurance-gst-form-group').hide();
						    $('.visa-gst-form-group').hide();
						    $('.holiday-gst-form-group').hide();
						    $('.whitelabel-form-group').hide();
						    $('.advertisement-gst-form-group').hide();
						    $('.miscellaneous-gst-form-group').hide();
						    $('input[name="Whitelable"]').prop('checked', false);
						    $('.b2eHide').show();
			    	}
			    });
			
			  
			  $('#taxTypenew').change(function() {
				  
				  if($(this).val() == ''){
					  $('.Product-form-group').hide();
		    		  $('.flight-domestic-form-group').hide();
					   $('.flight-international-form-group').hide();
					   $('.hotel-form-group').hide();						   
					   $('.bus-form-group').hide();
					    $('.holiday-form-group').hide();						   
					    $('.rail-form-group').hide();
					    $('.flight-international-gst-form-group').hide();
					    $('.flight-domestic-gst-form-group').hide();
					    $('.hotel-gst-form-group').hide();
					    $('.car-gst-form-group').hide();
					    $('.rail-gst-form-group').hide();
					    $('.bus-gst-form-group').hide();
					    $('.insurance-gst-form-group').hide();
					    $('.visa-gst-form-group').hide();
					    $('.holiday-gst-form-group').hide();
					    $('.whitelabel-form-group').hide();
					    $('.advertisement-gst-form-group').hide();
					    $('.miscellaneous-gst-form-group').hide();
					    }else if($(this).val() == 'servicetax'){
			    		  $('.Product-form-group').show();
			    		  $('.flight-domestic-form-group').show();
						   $('.flight-international-form-group').show();
						   $('.hotel-form-group').show();						   
						   $('.bus-form-group').show();
						    $('.holiday-form-group').show();						   
						    $('.rail-form-group').show();
						    $('.flight-international-gst-form-group').hide();
						    $('.flight-domestic-gst-form-group').hide();
						    $('.hotel-gst-form-group').hide();
						    $('.car-gst-form-group').hide();
						    $('.rail-gst-form-group').hide();
						    $('.bus-gst-form-group').hide();
						    $('.insurance-gst-form-group').hide();
						    $('.visa-gst-form-group').hide();
						    $('.holiday-gst-form-group').hide();
						    $('.whitelabel-form-group').hide();
						    $('.advertisement-gst-form-group').hide();
						    $('.miscellaneous-gst-form-group').hide();
						}else if($(this).val() == 'GST'){
				    		  $('.Product-form-group').show();
				    		  $('.flight-domestic-form-group').hide();
							   $('.flight-international-form-group').hide();
							   $('.hotel-form-group').hide();						   
							   $('.bus-form-group').hide();
							    $('.holiday-form-group').hide();						   
							    $('.rail-form-group').hide();
							    $('.flight-international-gst-form-group').show();
							    $('.flight-domestic-gst-form-group').show();
							    $('.hotel-gst-form-group').show();
							    $('.car-gst-form-group').show();
							    $('.rail-gst-form-group').show();
							    $('.bus-gst-form-group').show();
							    $('.insurance-gst-form-group').show();
							    $('.visa-gst-form-group').show();
							    $('.holiday-gst-form-group').show();
							    $('.whitelabel-form-group').hide();
							    $('.advertisement-gst-form-group').show();
							    $('.miscellaneous-gst-form-group').show();
							   
				    		
				    	}else{
			    		 $('.Product-form-group').hide();
			    		  $('.flight-domestic-form-group').hide();
						   $('.flight-international-form-group').hide();
						   $('.hotel-form-group').hide();						   
						   $('.bus-form-group').hide();
						    $('.holiday-form-group').hide();						   
						    $('.rail-form-group').hide();
						    $('.flight-international-gst-form-group').hide();
						    $('.flight-domestic-gst-form-group').hide();
						    $('.hotel-gst-form-group').hide();
						    $('.car-gst-form-group').hide();
						    $('.rail-gst-form-group').hide();
						    $('.bus-gst-form-group').hide();
						    $('.insurance-gst-form-group').hide();
						    $('.visa-gst-form-group').hide();
						    $('.holiday-gst-form-group').hide();
						    $('.whitelabel-form-group').hide();
						    $('.advertisement-gst-form-group').hide();
						    $('.miscellaneous-gst-form-group').hide();
						    $('.taxtype').hide();
			    	}
			    }); 
			  
			  $("#Whitelablenew").click(function () {
		            if ($(this).is(":checked")) {
		            	$('.whitelabel-form-group').show();
		            	$('#whitelabelActive').val("true");
		            	$('#configType').val("WL");
		            } else {
		            	$('.whitelabel-form-group').hide();
		            	$('#whitelabelActive').val("false");
		            	$('#configType').val("");
		            }
		        });
			  
			  
					
				
			
			
			
			$('#rateTypeFlight').change(function() {
				
				if ($('#rateTypeFlight').val() == 'Commission') {
					$('#d333').hide();
					$('.commission-group-flight').show();
					if ($('#company_id').val() == "") {
						$('#commission_alert-div').hide();
					} else {
						$('#commission_alert-div').show();
					}
				} else {
					$('.commission-group-flight').hide();
					$('#commission_alert-div').show();
				}
			});

			var companyUserId1 = null;
			var rateTypeFlight1 = null;
			var commissionTypeFlight1 = null;
			var commissionAmountFlight1 = "0.00";
			var parentCompanyUserId = null;
			var netFlight = null;
			var commFlight = null;
			var percentageFlight = null;
			var fixedFlight = null;
			var parentConfigValidOption = null;
			$('#company_id')
					.change(
							function() {

								 var parentConfigValid = document
										.getElementById("parentConfigIdSplit");
								parentConfigValidOption = parentConfigValid.options[0].value;
								var typeFlight = document
										.getElementById("rateTypeFlight");
								netFlight = typeFlight.options[0].value;
								commFlight = typeFlight.options[1].value;

								var cTypeFlight = document
										.getElementById("commissionType");
								percentageFlight = cTypeFlight.options[0].value;
								fixedFlight = cTypeFlight.options[1].value;
								var array = $('#company_id').val().split(",");
								parentCompanyUserId = array[2];
								$('#selectedCompanyUserId').val(array[2]);
								$('#selectchildid').val(array[0]);
								// alert(parentCompanyUserId);
								if ($('#configname').val() != "") {
									$('#configname').val("");
								}
								if ($('#rateTypeFlight').val() == 'Commission') {
									$('.commissionFlight-group').hide();
									$('#rateTypeFlight').val("Net");
									$('#commission_alert-div').hide();
								}
								if ($('#company_id').val() == "") {
									$('#parentconfigid_div').hide();
									$('#commission_alert-div').hide();
									$('#Whitelable_div').hide();
									$('#sheet_block_div').hide();
									//$('#myconfig_div').hide();
								} else {
									$('#parentconfigid_div').show();
									$('#commission_alert-div').show();
									//$('#myconfig_div').hide();
								}
								//alert(array[2]+"---"+$('#companyUserId').val());
								if (parentCompanyUserId != $('#companyUserId')
										.val()) {
									$('#configType').val("");
									//alert($('#parentConfigIdSplit').val());
									  $('#myconfig_div').hide();
									if ($('#company_id').val() == "") {
										$('#parentconfigid_div').hide();
										$('#commission_alert-div').hide();
										//$('#myconfig_div').hide();
									} else {
										$('#parentconfigid_div').show();
										$('#commission_alert-div').show();
										$('#Whitelable_div').show();
										$('#sheet_block_div').hide();
										//$('#myconfig_div').hide();
									}

									if ($('#parentConfigIdSplit').val() != "0") {
										// $('#parentConfigIdSplit').val(""); 
 											 $('#parentConfigIdSplit').val("0");
 											 
 											 
									} else {
										$('#commission_alert-div').hide();
									}
									if ($('#parentConfigIdSplit').val() == "0") {
										parentConfigValid.options[0].value = "0";

									} else {
										$('#commission_alert-div').hide();
									}
									$('#parentConfigIdSplit')
											.change(
													function() {
														$(':checkbox:checked')
																.prop(
																		'checked',
																		false);
														$('#blocksSelect')
																.empty();
														var parentArray = $(
																'#parentConfigIdSplit')
																.val().split(
																		",");
														companyUserId1 = parentArray[1];
														rateTypeFlight1 = parentArray[2];
														commissionTypeFlight1 = parentArray[3];
														commissionAmountFlight1 = parentArray[4];
														$('.modelType')
																.text(
																		rateTypeFlight1);
														$('.commType')
																.text(
																		commissionTypeFlight1);
														$('.commAmnt')
																.text(
																		commissionAmountFlight1);

														if ($('#company_id')
																.val() == "") {
															$(
																	'#commission_alert-div')
																	.hide();
														} else {
															$(
																	'#commission_alert-div')
																	.show();
														}
														if ($('#configname')
																.val() != "") {
															$('#configname')
																	.val("");
														}

														if (netFlight == rateTypeFlight1) {
															document
																	.getElementById("rateTypeFlight").value = netFlight;
															document
																	.getElementById("commissionType").value = "";
															document
																	.getElementById("commissionFlight").value = "0.00";

															if ($(
																	'#companyRoleType')
																	.val() == "true") {
																$(
																		'#rateTypeFlight option[value*="'
																				+ commFlight
																				+ '"]')
																		.prop(
																				'disabled',
																				true);
															}
															$(
																	'#commission_amount_div')
																	.hide();
															$(
																	'.commissionFlight-group')
																	.hide();
															$(
																	'#commission_alert-div')
																	.hide();
															$('#blocksSelect')
																	.empty();
															//document.getElementById("commissionType").value=commissionTypeFlight1;
														} else if (commFlight == rateTypeFlight1) {
															document
																	.getElementById("commissionFlight").value = "0.00";
															document
																	.getElementById("rateTypeFlight").value = commFlight;
															$(
																	'.commissionFlight-group')
																	.show();
															document
																	.getElementById("commissionType").value = commissionTypeFlight1;
															if ($(
																	'#companyRoleType')
																	.val() == "true") {
																$(
																		'#rateTypeFlight option[value*="'
																				+ commFlight
																				+ '"]')
																		.prop(
																				'disabled',
																				false);
															}
															if (commissionTypeFlight1 == percentageFlight) {
																$(
																		'#commissionType option[value*="'
																				+ fixedFlight
																				+ '"]')
																		.prop(
																				'disabled',
																				true);
															} else if (commissionTypeFlight1 == fixedFlight) {
																$(
																		'#commissionType option[value*="'
																				+ percentageFlight
																				+ '"]')
																		.prop(
																				'disabled',
																				true);
															}

														} else {
															$(
																	'.commissionFlight-group')
																	.hide();
															document
																	.getElementById("commissionType").value = rateTypeFlight1;
														}
														$(':checkbox:checked')
																.prop(
																		'checked',
																		false);
														$('#blocksSelect')
																.empty();
													});
								} else {
									$('#parentconfigid_div').hide();
									$('#commission_alert-div').hide();
									$('.commissionFlight-group').hide();
									$('#Whitelable_div').hide();
									$('#configType').val("");
									//$('#myconfig_div').show();
									document.getElementById("commissionFlight").value = "0.00";
									document.getElementById("configname").value = "";
									document
											.getElementById("commissionTypeFlight").value = "";
									document.getElementById("rateTypeFlight").value = "Net";
									if ($('#parentConfigIdSplit').val() == "") {
										parentConfigValid.options[0].value = "0";
										$(':checkbox:checked').prop('checked',
												false);
										$('#blocksSelect').empty();

									}
								}

								$(':checkbox:checked').prop('checked', false);

							});
					$('#rateTypeFlight')
					.change(
							function() {
								if ($('#rateTypeFlight').val() == 'Commission') {
									if (parentCompanyUserId != $(
											'#companyUserId').val()) {
										if ($('#company_id').val() == "") {
											$('#commission_alert-div').hide();
										} else {
											document
													.getElementById("commissionTypeFlight").value = "Percentage";
											document
													.getElementById("commissionFlight").value = "";
											$('#commission_alert-div').show();
											$(':checkbox:checked').prop(
													'checked', false);
											$('#blocksSelect').empty();
										}
									} else {
										$('#commission_alert-div').hide();
										$(
												'#commissionTypeFlight option[value*="'
														+ fixedFlight + '"]')
												.prop('disabled', false);
										$(
												'#commissionTypeFlight option[value*="'
														+ percentageFlight
														+ '"]').prop(
												'disabled', false);
										document
												.getElementById("commissionTypeFlight").value = "";
										document
												.getElementById("commissionTypeFlight").value = "Percentage";
										$(':checkbox:checked').prop('checked',
												false);
										$('#blocksSelect').empty();
									}
								} else {
									document.getElementById("commissionFlight").value = "0.00";
									$(':checkbox:checked').prop('checked',
											false);
									$('#blocksSelect').empty();
								}
							});
			$("#commissionFlight")
					.keyup(
							function() {
								//alert(Math.round(parseFloat(commissionAmountFlight1)*100)/100);
								if (parentCompanyUserId == $('#companyUserId')
										.val()) {
									$('#commission_amount_div').hide();
								} else if (Math
										.round(parseFloat(commissionAmountFlight1) * 100) / 100 >= Math
										.round(parseFloat($('#commissionFlight')
												.val()) * 100) / 100) {
									$('#commission_amount_div').hide();
								} else if ($('#commissionFlight').val() == "") {
									$('#commission_amount_div').hide();
								} else {

									if (companyUserId1 == $('#companyUserId')
											.val()) {
										if (commFlight == rateTypeFlight1) {
											if (Math
													.round(parseFloat(commissionAmountFlight1) * 100) / 100 == 0) {
												$('#commission_alert-div')
														.hide();
											} else {
												$('#commission_amount_div')
														.show();
												$('#commissionFlight').val("");
												$(':checkbox:checked').prop(
														'checked', false);
												$('#blocksSelect').empty();
											}
										}
									} else {
										$('#commission_amount_div').show();
										$(':checkbox:checked').prop('checked',
												false);
										$('#blocksSelect').empty();
									}

								}
								var val = $(this).val();
								if (isNaN(val)) {
									val = val.replace(/[^0-9\.]/g, '');
									if (val.split('.').length > 0)
										val = val.replace(/\.+$/, "");
								}
								$(this).val(val);
								if ($('#commissionFlight').val().length == 0) {
									$('#commission_amount_div').hide();
								}
							});
			
			
			
		});

		// deal sheet for flights

		function calliatapage() {
			var parentid = $('#parentid').val();
			var childid = $('#selectchildid').val();
			$("#iatacommhref").attr(
					"href",
					"airlinediscommission?parentid=" + parentid + "&childid="
							+ childid);
		}

		$(function() {
			//input:checkbox
			$("#checkSheet").change(function() {
				var ischecked = $(this).is(':checked');
				if (ischecked) {
					$('#sheet_block_div').show();
					var childid = $('#selectchildid').val();
					$('#d222').hide();
					$('#dealSheetStatus').val("1");
					getCompanyCommissionBlocks(childid);
				} else {
					$('#sheet_block_div').hide();
					$('#d222').show();
					$('#blocksSelect').empty();
					$('#sheet_emptyblock_div').hide();
				}
			});

			$(':checkbox:checked').prop('checked', false);
		});

		function getCompanyCommissionBlocks(companyId) {
			console.log("companyId-------" + companyId);
			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "companyCommissionBlocks";
			$
					.ajax({
						method : "POST",
						url : finalUrl,
						data : {
							companyId : companyId
						},
						success : function(data, status) {
							$('#blocksSelect').empty();
							console.log("data------" + data.companyBlockList);

							if (data.companyBlockList != 'undefined'
									&& data.companyBlockList.length > 0) {
								$('#sheet_emptyblock_div').hide();
								$.each(data.companyBlockList, function(index,
										object) {
									console.log("id------" + object.id);
									console.log("id------" + object.id);
									$('#blocksSelect').append(
											$("<option></option>").attr(
													"value", object.id).text(
													object.name));
								});

							} else {
								$('#sheet_block_div').hide();
								$('#sheet_emptyblock_div').show();

								$('#configdealsheeterror')
										.text(
												'Please Create Deal Sheet, before configuration.');
							}
						},
						error : function(xhr, status, error) {
							$('#sheet_block_div').hide();
							$('#sheet_emptyblock_div').show();
							$('#configdealsheeterror')
									.text(
											'Please Create Deal Sheet, before configuration.');
							console.log("Error----------" + error);
						}
					});
		}
	</script>
	<script type="text/javascript">
		$(function() {
			$('#rateTypeHotel').change(function() {
				if ($('#rateTypeHotel').val() == 'Commission') {
					$('#d333').hide();
					$('.commission-group-hotel').show();
					if ($('#company_id').val() == "") {
						$('#commission_alert-div').hide();
					} else {
						$('#commission_alert-div').show();
					}
				} else {
					$('.commission-group-hotel').hide();
					$('#commission_alert-div').show();
				}
			});
			$('#configType').val("")
			var companyUserId1 = null;
			var rateTypeHotel1 = null;
			var commissionTypeHotel1 = null;
			var commissionAmountHotel1 = "0.00";
			var parentCompanyUserId = null;
			var netHotel = null;
			var commHotel = null;
			var percentageHotel = null;
			var fixedHotel = null;
			var parentConfigValidOption = null;
			var array=null;
			$('#company_id')
					.change(
							function() {
								$('#configType').val("");
 								var parentConfigValid = document
										.getElementById("parentConfigIdSplit");
								parentConfigValidOption = parentConfigValid.options[0].value;
								var typeHotel = document
										.getElementById("rateTypeHotel");
								netHotel = typeHotel.options[0].value;
								commHotel = typeHotel.options[1].value;

								var cTypeHotel = document
										.getElementById("commissionTypeHotel");
								percentageHotel = cTypeHotel.options[0].value;
								fixedHotel = cTypeHotel.options[1].value;
								 array = $('#company_id').val().split(",");
								parentCompanyUserId = array[2];
								$('#selectchildid').val(array[0]);
							 
								if ($('#configname').val() != "") {
									$('#configname').val("");
								}
								if ($('#rateTypeHotel').val() == 'Commission') {
									$('.commissionHotel-group').hide();
									$('#rateTypeHotel').val("Net");
									$('#commission_alert-div').hide();
								}
								if ($('#company_id').val() == "") {
									$('#parentconfigid_div').hide();
									$('#commission_alert-div').hide();
									$('#Whitelable_div').hide();
									$('#sheet_block_div').hide();
								} else {
									$('#parentconfigid_div').show();
									$('#commission_alert-div').show();
								}
								//alert(array[2]+"---"+$('#companyUserId').val());
								if (parentCompanyUserId != $('#companyUserId')
										.val()) {
									
									//alert($('#parentConfigIdSplit').val());
									if ($('#company_id').val() == "") {
										$('#parentconfigid_div').hide();
										$('#commission_alert-div').hide();
									} else {
										$('#parentconfigid_div').show();
										$('#commission_alert-div').show();
										$('#Whitelable_div').show();
										$('#sheet_block_div').hide();
									}

									if ($('#parentConfigIdSplit').val() != "0") {
										// $('#parentConfigIdSplit').val(""); 

										$('#parentConfigIdSplit').val("0");
									} else {
										$('#commission_alert-div').hide();
									}
									if ($('#parentConfigIdSplit').val() == "0") {
										parentConfigValid.options[0].value = "";

									} else {
										$('#commission_alert-div').hide();
									}
									$('#parentConfigIdSplit')
											.change(
													function() {
														$(':checkbox:checked')
																.prop(
																		'checked',
																		false);
														$('#blocksSelect')
																.empty();
														var parentArray = $(
																'#parentConfigIdSplit')
																.val().split(
																		",");
														companyUserId1 = parentArray[1];
														rateTypeHotel1 = parentArray[2];
														commissionTypeHotel1 = parentArray[3];
														commissionAmountHotel1 = parentArray[4];
														$('.modelType').text(
																rateTypeHotel1);
														$('.commType')
																.text(
																		commissionTypeHotel1);
														$('.commAmnt')
																.text(
																		commissionAmountHotel1);

														if ($('#company_id')
																.val() == "") {
															$(
																	'#commission_alert-div')
																	.hide();
														} else {
															$(
																	'#commission_alert-div')
																	.show();
														}
														if ($('#configname')
																.val() != "") {
															$('#configname')
																	.val("");
														}

														if (netHotel == rateTypeHotel1) {
															document
																	.getElementById("rateTypeHotel").value = netHotel;
															document
																	.getElementById("commissionTypeHotel").value = "";
															document
																	.getElementById("commissionHotel").value = "0.00";

															if ($(
																	'#companyRoleType')
																	.val() == "true") {
																$(
																		'#rateTypeHotel option[value*="'
																				+ commHotel
																				+ '"]')
																		.prop(
																				'disabled',
																				true);
															}
															$(
																	'#commission_amount_div')
																	.hide();
															$(
																	'.commissionHotel-group')
																	.hide();
															$(
																	'#commission_alert-div')
																	.hide();
															$('#blocksSelect')
																	.empty();
															//document.getElementById("commissionTypeHotel").value=commissionTypeHotel1;
														} else if (commHotel == rateTypeHotel1) {
															document
																	.getElementById("commissionHotel").value = "0.00";
															document
																	.getElementById("rateTypeHotel").value = commHotel;
															$(
																	'.commissionHotel-group')
																	.show();
															document
																	.getElementById("commissionTypeHotel").value = commissionTypeHotel1;
															if ($(
																	'#companyRoleType')
																	.val() == "true") {
																$(
																		'#rateTypeHotel option[value*="'
																				+ commHotel
																				+ '"]')
																		.prop(
																				'disabled',
																				false);
															}
															if (commissionTypeHotel1 == percentageHotel) {
																$(
																		'#commissionTypeHotel option[value*="'
																				+ fixedHotel
																				+ '"]')
																		.prop(
																				'disabled',
																				true);
															} else if (commissionTypeHotel1 == fixedHotel) {
																$(
																		'#commissionTypeHotel option[value*="'
																				+ percentageHotel
																				+ '"]')
																		.prop(
																				'disabled',
																				true);
															}

														} else {
															$(
																	'.commissionHotel-group')
																	.hide();
															document
																	.getElementById("commissionTypeHotel").value = rateTypeHotel1;
														}
														$(':checkbox:checked')
																.prop(
																		'checked',
																		false);
														$('#blocksSelect')
																.empty();
													});
								} else {
									$('#parentconfigid_div').hide();
									$('#commission_alert-div').hide();
									$('.commissionHotel-group').hide();
									//$('#myconfig_div').hide();
									$('#configType').val("");
									
									
									document.getElementById("commissionHotel").value = "0.00";
									document.getElementById("configname").value = "";
									document
											.getElementById("commissionTypeHotel").value = "";
									document.getElementById("rateTypeHotel").value = "Net";
									if ($('#parentConfigIdSplit').val() == "") {
										parentConfigValid.options[0].value = "0";
										$(':checkbox:checked').prop('checked',
												false);
										$('#blocksSelect').empty();

									}
								}

								$(':checkbox:checked').prop('checked', false);

							});
			$('#rateTypeHotel')
					.change(
							function() {
								if ($('#rateTypeHotel').val() == 'Commission') {
									if (parentCompanyUserId != $(
											'#companyUserId').val()) {
										if ($('#company_id').val() == "") {
											$('#commission_alert-div').hide();
										} else {
											document
													.getElementById("commissionTypeHotel").value = "Percentage";
											document
													.getElementById("commissionHotel").value = "";
											$('#commission_alert-div').show();
											$(':checkbox:checked').prop(
													'checked', false);
											$('#blocksSelect').empty();
										}
									} else {
										$('#commission_alert-div').hide();
										$(
												'#commissionType option[value*="'
														+ fixedHotel + '"]')
												.prop('disabled', false);
										$(
												'#commissionType option[value*="'
														+ percentageHotel
														+ '"]').prop(
												'disabled', false);
										document
												.getElementById("commissionTypeHotel").value = "";
										document
												.getElementById("commissionTypeHotel").value = "Percentage";
										$(':checkbox:checked').prop('checked',
												false);
										$('#blocksSelect').empty();
									}
								} else {
									document.getElementById("commissionHotel").value = "0.00";
									$(':checkbox:checked').prop('checked',
											false);
									$('#blocksSelect').empty();
								}
							});
			$("#commissionHotel")
					.keyup(
							function() {
								//alert(Math.round(parseFloat(commissionAmountHotel1)*100)/100);
								if (parentCompanyUserId == $('#companyUserId')
										.val()) {
									$('#commission_amount_div').hide();
								} else if (Math
										.round(parseFloat(commissionAmountHotel1) * 100) / 100 >= Math
										.round(parseFloat($('#commissionHotel')
												.val()) * 100) / 100) {
									$('#commission_amount_div').hide();
								} else if ($('#commissionHotel').val() == "") {
									$('#commission_amount_div').hide();
								} else {

									if (companyUserId1 == $('#companyUserId')
											.val()) {
										if (commHotel == rateTypeHotel1) {
											if (Math
													.round(parseFloat(commissionAmountHotel1) * 100) / 100 == 0) {
												$('#commission_alert-div')
														.hide();
											} else {
												$('#commission_amount_div')
														.show();
												$('#commissionHotel').val("");
												$(':checkbox:checked').prop(
														'checked', false);
												$('#blocksSelect').empty();
											}
										}
									} else {
										$('#commission_amount_div').show();
										$(':checkbox:checked').prop('checked',
												false);
										$('#blocksSelect').empty();
									}

								}
								var val = $(this).val();
								if (isNaN(val)) {
									val = val.replace(/[^0-9\.]/g, '');
									if (val.split('.').length > 0)
										val = val.replace(/\.+$/, "");
								}
								$(this).val(val);
								if ($('#commissionHotel').val().length == 0) {
									$('#commission_amount_div').hide();
								}
		 });
		});
	</script>
 
<script type="text/javascript">
 
 $(function() {
	 
	 if ($('#selectedCompanyUserId').val() != $('#companyUserId').val()) {
		  $('#myconfig_div').hide();
	  }
	 
	 $('#configType').change(function() {
		
		   var  parentUserId = $('#selectedCompanyUserId').val();
		  if($('#configType').val()==""){
			  $('#myconfig_div').hide();
		  }
		  else if($('#configType').val() == "B2E"){
			  $('.Product-form-group').show();
		  }
		  else{
			   $('#myconfig_div').show();  
		  }
		  if (parentUserId != $('#companyUserId').val()) {
			  $('#myconfig_div').hide();
			  var selectedCompanyUserId = $('#selectedCompanyUserId').val();
			  var companyid = $('#selectchildid').val();
			    $.ajax({
					url :"ChildCompanyConfigCountUnderParent",
					dataType : "json",
					data : {
						myConfigCompanyUserId :selectedCompanyUserId,
						createdByCompanyId:companyid,
						configType: $('#configType').val()
					 },
							success : function(data, textStatus, jqXHR) {
							var items = data.childConfigCountMap;
							if(items!=null){
								for (var key in items) {
	 					            if (items.hasOwnProperty("B2B")) {
	 					            	alert("Parent Configuration Using Direct Childs:"+"\n"+"Config Type : "+key+"\n"+"Count : "+items[key]);
	 					            	//console.log(key + ': ' + items[key]);
	 					            }
	 					        }
							}
							  
						//console.log(items);
						 
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
			  
		 }
		  $('#MyConfig').prop('checked', false);
		  });
	 
	  $('#MyConfig').change(function() {
			  if ($(this).is(':checked')) {
				  var selectedCompanyUserId = $('#selectedCompanyUserId').val();
				  var companyid = $('#selectchildid').val();
				    $.ajax({
						url :"ChildCompanyConfigCountUnderParent",
						dataType : "json",
						data : {
							myConfigCompanyUserId :selectedCompanyUserId,
							createdByCompanyId:companyid,
							configType: $('#configType').val()
						 },
 							success : function(data, textStatus, jqXHR) {
 							var items = data.childConfigCountMap;
 							if(items!=null){
 								for (var key in items) {
 	 					            if (items.hasOwnProperty("B2B")) {
 	 					            	alert("Parent Configuration Using Direct Childs:"+"\n"+"Config Type : "+key+"\n"+"Count : "+items[key]);
 	 					            	//console.log(key + ': ' + items[key]);
 	 					            }
 	 					        }
 							}
 							  
							//console.log(items);
							 
						},
						error : function(jqXHR, textStatus, errorThrown) {
							console.log(textStatus);
						}
					}); 
 				 }  
			});
		
	});
 
 
 function isAlphabetKey(evt,textbox){                	
     evt = (evt) ? evt : window.event;
      var charCode = (evt.which) ? evt.which : evt.keyCode;
      if(textbox.value.trim() == ''){
      	if(charCode == 32)
      	       return false;
      }
      if (charCode > 32 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122))) {
          return false;
      }
    //  AllowSingleSpaceNotInFirstAndLast(textbox);
      return true;               
    }
 function isNumberKey(evt,obj){
     evt = (evt) ? evt : window.event;
       var charCode = (evt.which) ? evt.which : evt.keyCode;
       if (charCode > 31 && charCode== 110 && (charCode < 48 || charCode > 57))  
       
           return false;
       
       if(obj.value.trim().split('.').length>2)
    	   obj.value = obj.value.replace(/\.+$/,"");
      
   }
 
</script>
<script type="text/javascript">
$(document).ready(function() {
	 
	$("#flightinternationalbasetax , #flightinternationalswatchBharathCess , #flightInternationalkrishiKalyanCess , #flightInternationaltotalTax , #flightInternationalconvenienceFee , #flightInternationalmanagementFee , #flightDomesticbasicTax ,#flightDomesticswatchBharathCess , #flightDomestickrishiKalyanCess , #flightDomestictotalTax , #flightDomesticconvenienceFee ,	 #flightDomesticmanagementFee , #hotelbasicTax , #hotelswatchBharathCess , #hotelkrishiKalyanCess , #hoteltotalTax , #hotelconvenienceFee,	#hotelmanagementFee , #hoteldomesticmanagementfee , #busapplicableFare, #busbasicTax , #bussbt , #buskkt , #bustotaltax , #busconvenienceFee, #busmanagementfee , #carmanagementfee , #holidaybasicTax , #holidayswatchBharathCess , #holidaykkc , #holidaytotaltax , #holidayconvfee,#holidaymgmtfeeinternational , #holidaydomesticManagementfee , #railbasicTax , #railsbc , #railkkc , #railtotaltax , #railconviencefee,#railmgmtfee , #visamgmtfee , #advtandothertax" ).keydown(function (e) {
	    var isModifierkeyPressed = (e.metaKey || e.ctrlKey || e.shiftKey);
	            var isCursorMoveOrDeleteAction = ([46,8,9,37,38,39,40,110,190].indexOf(e.keyCode) != -1);
	            var isNumKeyPressed = (e.keyCode >= 48 && e.keyCode <= 58) || (e.keyCode >=96 && e.keyCode <= 105);
	            var vKey = 86, cKey = 67,aKey = 65;
	            switch(true){
	                case isCursorMoveOrDeleteAction:
	                case isModifierkeyPressed == false && isNumKeyPressed:
	                case (e.metaKey || e.ctrlKey) && ([vKey,cKey,aKey].indexOf(e.keyCode) != -1):
	                    break;
	                default:
	                    e.preventDefault();
	            }
	    });
				});



</script>
<script type="text/javascript">
$("#uploadimage" ).change(function() {
	   var  fileExtension = $('#uploadimage').val().substr(($('#uploadimage').val().lastIndexOf('.') + 1));
		console.log("fileExtension",fileExtension);
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
 
var parent = $("#companyRoleTypesuper").val(); 
var parentId = $("#companyUserId").val();
$(".customiseddsrreport").hide();
$('#company_id').change(function(){ 
	if(parentId=="TA1" && parent==='true'){
		$(".customiseddsrreport").show();
	} else{
		$(".customiseddsrreport").hide();
	}
});


$("#select_all").change(function(){  
	    var status = this.checked;  
	    $('.checkbox').each(function(){ 
	        this.checked = status; 
	        if(status){
	        	$(this).val('true');
		        $(this).attr('checked','checked');
	        }else{
	        	 $(this).val('false');
			        $(this).removeAttr('checked');
	        }
	      
	    });
	});

	$('.checkbox').change(function(){  
	    if(this.checked == false){ 
	        $("#select_all")[0].checked = false;  
	      if($(this).attr('checked')){
	          $(this).val('false');
	        $(this).removeAttr('checked'); 
	     }
	     
	    }  
	    if ($('.checkbox:checked').length == $('.checkbox').length ){ 
	        $("#select_all")[0].checked = true;  
	      $(this).val('true');
	        $(this).attr('checked','checked');
	    }
	});
		   
 
 

</script>
  <script>
  $(document).ready(function(){
	     $('.toCheck').click(function(){
      $(this).val($(this).is(':checked'));
   
  	
  });  

	});
</script>  
<script>
$("#themeName").change(function(){
    $("#cssName").val($(this).val());
});
 </script>

<script type="text/javascript">
$(document).ready(function(){
	
     $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
          return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
      }, "This field cannot have symbols.");

      $.validator.addMethod("cusValidationAlphaName",function(value,element){
          return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
      },"This field cannot have numbers and symbols."); 
      $.validator.addMethod("cusValidationforprice",function(value,element){
          return this.optional(element) || /^[0-9.]+$/i.test(value);
      },"This field cannot have Char and symbols.");


$("#myfform").validate({
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
         }
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


$('#buttonSubmit').click(function() {
	   if($("#myfform").valid())
	    	document.getElementById("myfform").submit();
	 	 
	}); 

});


</script>  
	<script type="text/javascript" src="js/app.js"></script>
	<%@ include file="DashboardFooter.jsp"%>

	
</body>

</html>
