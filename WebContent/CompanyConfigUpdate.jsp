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
		var id = $("#uniqueId").val();
		 var configType =$("#configType").val();
		 
		 if(configType==""){
			 document.getElementById('type'+id).value = "B2C";
		 }
		 else{
			//document.getElementById('type'+id).value = configType;
		 }
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

<script>

$(document).ready(function() {	
	
	var taxtype = $("select[name=taxtype] option:selected").val() 
	
	if( taxtype=='GST'){
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
		    $('.advertisement-gst-form-group').show();
		    $('.miscellaneous-gst-form-group').show();
	}else{
		
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
		    $('.advertisement-gst-form-group').hide();
		    $('.miscellaneous-gst-form-group').hide();
		
	}
	
	$('#taxTypenew').on('change', function() {
		if($(this).val() == 'servicetax'){
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
			    $('.advertisement-gst-form-group').hide();
			    $('.miscellaneous-gst-form-group').hide();
			
		}else{
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
			   $('.advertisement-gst-form-group').show();
			   $('.miscellaneous-gst-form-group').show();
		}
		
	});
	
});


	
	
	</script>




<%-- <script type="text/javascript">
	$(function() {
		var id = $("#uniqueId").val();
		//var country_id=$("#country_id").val();
		var configStatus = $("#status").val();
		var rateType = $("#rateType1").val();
		var commissionType = $("#commissionType1").val();
		console.log("configStatus-----" + configStatus);
		console.log("rateType-----" + rateType);
		console.log("commissionType1-----" + commissionType);
		//alert(id+"\n"+configStatus);
		// document.getElementById('sales_country_id'+id).value =country_id;
		document.getElementById('configStatus' + id).value = configStatus;
		document.getElementById('rateType' + id).value = rateType;
		document.getElementById('commissionType' + id).value = commissionType;

		if ($('#rateType' + id).val() == 'Commission') {
			$('.commission-group').show();
		} else {
			$('.commission-group').hide();

		}

	});
</script> --%>
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
				<form action="updateCompanyConfigData" method="post" id="formcheck"
					class="form-horizontal">
					  <div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Company
							UserId </label>
						<div class="col-sm-8">
							<input type="text" disabled="disabled"
								class="form-control input-sm" id="configname" name="configname"
								value="<s:property value="%{#session.CompanyconfigProfile.companyUserId}"/>"
								placeholder="config name" autocomplete="off" required>

						</div>
					</div>
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Config
							Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="configname"
								name="configname"
								value="<s:property value="%{#session.CompanyconfigProfile.configname}"/>"
								placeholder="config name" autocomplete="off" required readonly="readonly">

						</div>
					</div>
				 <div class="form-group" id="configtype_div"
						style="display: block">
						<label for="City" class="col-sm-2 control-label">Config Type</label>
						<%-- <input type="hidden" value="<s:property value="%{#session.CompanyconfigProfile.configType}"/>" id="configType">
						<div class="col-sm-8">
							<select class="form-control input-sm" name="configType"   id="confitypevalue"   required disabled>
									<option value="<s:property value="%{#session.CompanyconfigProfile.configType}"/>"><s:property value="%{#session.CompanyconfigProfile.configType}"/></option>
							  		
								 
							</select>
						</div> --%>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="configType" name="configType"
								value="<s:property value="%{#session.CompanyconfigProfile.configType}"/>"
								 autocomplete="off" required readonly="readonly">

						</div>
						</div>
						 <c:if test="${session.CompanyconfigProfile.configType=='B2E'}">
						<div class="form-group taxtype" id="configtype_div"
						style="display: block">
						<label for="City" class="col-sm-2 control-label">Tax Type</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="taxtype"
								id="taxTypenew"  required>
								<c:choose>
								<c:when test="${session.CompanyconfigProfile.taxtype=='GST'}">
								<option selected="selected" value="GST">GST</option>
								<option value="servicetax">Service Tax </option>
								</c:when>
								<c:otherwise>
								<option  value="GST">GST</option>
								<option selected="selected" value="servicetax">Service Tax </option> 
								</c:otherwise>
								
								</c:choose>
							
							</select>
						</div>
						 
						
							 
					</div> 
	</c:if>
							
							  
					
					<div class="form-group">
						<input type="hidden"
							value="<s:property value="%{#session.CompanyconfigProfile.rateTypeFlight}"/>"
							id="rateType1"> <label for="City"
							class="col-sm-2 control-label">Rate Type Flight</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="rateTypeFlight"
								disabled="disabled" id="rateTypeFlight<s:property value="%{#session.CompanyconfigProfile.config_id}"/>"
								autocomplete="off" required>
								<s:if test="%{#session.CompanyconfigProfile.rateTypeFlight=='Net'}">
								<option value="Net">Nett</option>
								 </s:if>
								 <s:else>
								 	<option value="Commission"><s:text
										name="global.Incentive"></s:text></option>
								 </s:else>
							 </select>
						</div>
					</div>
					  <input type="hidden" name="dealSheetStatus"    value="<s:property value="%{#session.CompanyconfigProfile.SheetMode}"/>" id="dealSheetStatus"/>
					 
					<s:if test="%{#session.CompanyconfigProfile.SheetMode==true}">
					<div class="commission-group-flight" style="display: block">
					<div class="form-group"
							style="text-align: left; margin-left: 126px;">
							<label><input type="checkbox" id="checkSheet">
								Use Deal Sheet</label>
						</div>
						
					<div class="form-group" id="sheet_block_div" style="display: block">
						<label for="City" class="col-sm-2 control-label">Select
							Block </label>
						<div class="col-sm-8">
						<input type="hidden" value="<s:property value="%{#session.CompanyconfigProfile.appliedBlockId}"/>"
								id="appliedBlockId">
							<select class="form-control input-sm" name="appliedBlockId"
						id="appliedAffiliateSheet<s:property value="%{#session.CompanyconfigProfile.config_id}"/>" autocomplete="off" >
						<s:iterator value="companyBlockList">
						<option value="<s:property value="id"/>"><s:property value="name"/></option>
						 </s:iterator>
					 </select>
						</div>
					</div>
					 
					</div>
					
					</s:if>
					
					 
					<div class="commission-group-flight" style="display:block" id="d222">
						<div class="form-group">
							<label for="City" class="col-sm-2 control-label"><s:text
									name="global.Incentive"></s:text> Type Flight</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" disabled="disabled"
									name="commissionTypeFlight" id="commissionTypeFlight"
									autocomplete="off">
								 <s:if test="%{#session.CompanyconfigProfile.commissionTypeFlight=='Fixed'}">
									<option value="Fixed" >Fixed</option>
									</s:if>
									<s:else>
										<option value="Percentage" selected="selected">Percentage</option>
									</s:else>
								 </select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="Username" class="col-sm-2 control-label"><s:text
									name="global.Incentive"></s:text> Flight</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="commission" disabled="disabled"
								 value="<s:property value="%{#session.CompanyconfigProfile.commissionAmountFlight}"/>" name="commissionAmountFlight" placeholder="amount"
									autocomplete="off">
							</div>
						</div>
						</div>
							 
						
						
						 
						 <div class="form-group">
							<label for="City" class="col-sm-2 control-label">Rate
								Type Hotel </label>
							<div class="col-sm-8">
								<select class="form-control input-sm" name="rateTypeHotel"
									id="rateTypeHotel" autocomplete="off" disabled="disabled">
									 <s:if test="%{#session.CompanyconfigProfile.rateTypeHotel=='Net'}">
									<option value="Net">Nett</option>
									</s:if>
									<s:else>
									<option value="Commission"><s:text
											name="global.Incentive"></s:text></option>
									</s:else>
								 </select>
							</div>
						</div>
						<div class="commission-group-hotel" style="display: block" id="d222">
						<div class="form-group">
							<label for="City" class="col-sm-2 control-label"><s:text
									name="global.Incentive"></s:text> Type Hotel</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" name="commissionTypeHotel" id="commissionType" disabled="disabled">
							 <s:if test="%{#session.CompanyconfigProfile.commissionTypeHotel=='Fixed'}">
							<option value="Fixed">Fixed</option>
						 </s:if>
						 <s:else>
						 	<option value="Percentage" >Percentage</option>
						   </s:else>
							 </select>
							</div>
						</div>
						<div class="form-group">
							<label for="Username" class="col-sm-2 control-label"><s:text
									name="global.Incentive"></s:text> Hotel</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="commission" disabled="disabled"
									value="<s:property value="%{#session.CompanyconfigProfile.commissionAmountHotel}"/>"  name="commissionAmountHotel"  placeholder="amount"
									autocomplete="off">
							</div>
						</div>
					</div>
					
					
					 <c:if test="${session.CompanyconfigProfile.configType=='B2E'}">
				
					
					<div class="flight-international-gst-form-group">
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Flight - International - GST</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="flightInternationalGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalGstTaxConfig.id}"/>"> 
						<input type="hidden" name="flightInternationalGstTaxConfig.serviceType" value="International">
						<input type="text" class="form-control input-sm" name="flightInternationalGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="flightInternationalGstTaxConfig.CGST"  id="flightinternationalCGST"  value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalGstTaxConfig.CGST}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightinternationalSGST" name="flightInternationalGstTaxConfig.SGST" value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalGstTaxConfig.SGST}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightInternationalIGST" name="flightInternationalGstTaxConfig.IGST" value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalGstTaxConfig.IGST}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="flightInternationalUGSTTax" name="flightInternationalGstTaxConfig.UGST" value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalGstTaxConfig.UGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightInternationalGSTconvenienceFee" name="flightInternationalGstTaxConfig.convenienceFee"  value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalGstTaxConfig.convenienceFee}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightInternationalGSTmanagementFee" name="flightInternationalGstTaxConfig.managementFee" value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalGstTaxConfig.managementFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="flightInternationalGstTaxConfig.totalGst"  id="flightinternationaltotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div> --%>
				
					</div>
					
					
					
					<div class="flight-domestic-gst-form-group">
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Flight - Domestic - GST</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="flightDomesticGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticGstTaxConfig.id}"/>"> 
						<input type="hidden" name="flightDomesticGstTaxConfig.serviceType" value="Domestic">
						<input type="text" class="form-control input-sm" name="flightDomesticGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="flightDomesticGstTaxConfig.CGST"  id="flightdomesticCGST"  value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticGstTaxConfig.CGST}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightdomesticSGST" name="flightDomesticGstTaxConfig.SGST" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticGstTaxConfig.SGST}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightdomesticIGST" name="flightDomesticGstTaxConfig.IGST" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticGstTaxConfig.IGST}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="flightdomesticUGSTTax" name="flightDomesticGstTaxConfig.UGST" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticGstTaxConfig.UGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightdomesticGSTconvenienceFee" name="flightDomesticGstTaxConfig.convenienceFee"  value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticGstTaxConfig.convenienceFee}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightdomesticGSTmanagementFee" name="flightDomesticGstTaxConfig.managementFee" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticGstTaxConfig.managementFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="flightDomesticGstTaxConfig.totalGst"  id="flightDomesticGstTaxConfigtotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div> --%>
				
					</div>
					
					
					<div class="hotel-gst-form-group">
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Hotel - GST</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="hotelGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.hotelGstTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="hotelGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="hotelGstTaxConfig.CGST"  id="hotelCGST"  value="<s:property value="%{#session.CompanyconfigProfile.hotelGstTaxConfig.CGST}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hotelSGST" name="hotelGstTaxConfig.SGST"  value="<s:property value="%{#session.CompanyconfigProfile.hotelGstTaxConfig.SGST}"/>"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hotelIGST" name="hotelGstTaxConfig.IGST"  value="<s:property value="%{#session.CompanyconfigProfile.hotelGstTaxConfig.IGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="hotelUGSTTax" name="hotelGstTaxConfig.UGST"  value="<s:property value="%{#session.CompanyconfigProfile.hotelGstTaxConfig.UGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hotelGSTconvenienceFee" name="hotelGstTaxConfig.convenienceFee"   value="<s:property value="%{#session.CompanyconfigProfile.hotelGstTaxConfig.convenienceFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee (International)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hotelGSTmanagementFee" name="hotelGstTaxConfig.intlManagementFee"  value="<s:property value="%{#session.CompanyconfigProfile.hotelGstTaxConfig.intlManagementFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee (Domestic)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hotelGSTdomesticmanagementFee" name="hotelGstTaxConfig.domesticManagementFee"  value="<s:property value="%{#session.CompanyconfigProfile.hotelGstTaxConfig.domesticManagementFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="hotelGstTaxConfig.totalGst"  id="hotelGSTtotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.hotelGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div> --%>
				
					</div>
					
					<div class="car-gst-form-group">
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Car - GST</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="carGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.carGstTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="carGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="carGstTaxConfig.CGST"  id="carCGST"   value="<s:property value="%{#session.CompanyconfigProfile.carGstTaxConfig.CGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="carSGST" name="carGstTaxConfig.SGST"  value="<s:property value="%{#session.CompanyconfigProfile.carGstTaxConfig.SGST}"/>"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="carIGST" name="carGstTaxConfig.IGST"  value="<s:property value="%{#session.CompanyconfigProfile.carGstTaxConfig.IGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="carUGSTTax" name="carGstTaxConfig.UGST"  value="<s:property value="%{#session.CompanyconfigProfile.carGstTaxConfig.UGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="carGSTconvenienceFee" name="carGstTaxConfig.convenienceFee"   value="<s:property value="%{#session.CompanyconfigProfile.carGstTaxConfig.convenienceFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="carGSTmanagementFee" name="carGstTaxConfig.managementFee"  value="<s:property value="%{#session.CompanyconfigProfile.carGstTaxConfig.managementFee}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="carGstTaxConfig.totalGst"  id="carGSTtotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.carGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div> --%>
				
					</div>
					
					<div class="bus-gst-form-group">
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Bus - GST</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="busGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.busGstTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="busGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="busGstTaxConfig.CGST"  id="busCGST"   value="<s:property value="%{#session.CompanyconfigProfile.busGstTaxConfig.CGST}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="busSGST" name="busGstTaxConfig.SGST"  value="<s:property value="%{#session.CompanyconfigProfile.busGstTaxConfig.SGST}"/>"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="busIGST" name="busGstTaxConfig.IGST"  value="<s:property value="%{#session.CompanyconfigProfile.busGstTaxConfig.IGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="busUGSTTax" name="busGstTaxConfig.UGST"  value="<s:property value="%{#session.CompanyconfigProfile.busGstTaxConfig.UGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="busGSTconvenienceFee" name="busGstTaxConfig.convenienceFee"   value="<s:property value="%{#session.CompanyconfigProfile.busGstTaxConfig.convenienceFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="busGSTmanagementFee" name="busGstTaxConfig.managementFee"  value="<s:property value="%{#session.CompanyconfigProfile.busGstTaxConfig.managementFee}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="busGstTaxConfig.totalGst"  id="busGSTtotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.busGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div> --%>
				
					</div>
					
					
					<div class="rail-gst-form-group">
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Rail - Gst</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="trainGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.trainGstTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="trainGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="trainGstTaxConfig.CGST"  id="railCGST"   value="<s:property value="%{#session.CompanyconfigProfile.trainGstTaxConfig.CGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railSGST" name="trainGstTaxConfig.SGST"  value="<s:property value="%{#session.CompanyconfigProfile.trainGstTaxConfig.SGST}"/>"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railIGST" name="trainGstTaxConfig.IGST"  value="<s:property value="%{#session.CompanyconfigProfile.trainGstTaxConfig.IGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="railUGSTTax" name="trainGstTaxConfig.UGST"  value="<s:property value="%{#session.CompanyconfigProfile.trainGstTaxConfig.UGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railGSTconvenienceFee" name="trainGstTaxConfig.convenienceFee"   value="<s:property value="%{#session.CompanyconfigProfile.trainGstTaxConfig.convenienceFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee (General)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railGSTmanagementFee" name="trainGstTaxConfig.managementFee"  value="<s:property value="%{#session.CompanyconfigProfile.trainGstTaxConfig.managementFee}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee (Tatkal)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railGSTmanagementFeeTatkal" name="trainGstTaxConfig.managementFeeTatkal"  value="<s:property value="%{#session.CompanyconfigProfile.trainGstTaxConfig.managementFeeTatkal}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="trainGstTaxConfig.totalGst"  id="railGSTtotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.trainGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div> --%>
				
					</div>
					
					
					<div class="visa-gst-form-group">
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Visa - GST</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="visaGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.visaGstTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="visaGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="visaGstTaxConfig.CGST"  id="visaCGST"   value="<s:property value="%{#session.CompanyconfigProfile.visaGstTaxConfig.CGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="visaSGST" name="visaGstTaxConfig.SGST"  value="<s:property value="%{#session.CompanyconfigProfile.visaGstTaxConfig.SGST}"/>"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="visaIGST" name="visaGstTaxConfig.IGST"  value="<s:property value="%{#session.CompanyconfigProfile.visaGstTaxConfig.IGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="visaUGSTTax" name="visaGstTaxConfig.UGST"  value="<s:property value="%{#session.CompanyconfigProfile.visaGstTaxConfig.UGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="visaGSTconvenienceFee" name="visaGstTaxConfig.convenienceFee"   value="<s:property value="%{#session.CompanyconfigProfile.visaGstTaxConfig.convenienceFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="visaGSTmanagementFee" name="visaGstTaxConfig.managementFee"  value="<s:property value="%{#session.CompanyconfigProfile.visaGstTaxConfig.managementFee}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="visaGstTaxConfig.totalGst"  id="visaGSTtotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.visaGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
				 --%>
				
					</div>
					
					<div class="insurance-gst-form-group">					
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Insurance - Gst</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="insuranceGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.insuranceGstTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="insuranceGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="insuranceGstTaxConfig.CGST"  id="insuranceCGST"   value="<s:property value="%{#session.CompanyconfigProfile.insuranceGstTaxConfig.CGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="insuranceSGST" name="insuranceGstTaxConfig.SGST"  value="<s:property value="%{#session.CompanyconfigProfile.insuranceGstTaxConfig.SGST}"/>"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="insuranceIGST" name="insuranceGstTaxConfig.IGST"  value="<s:property value="%{#session.CompanyconfigProfile.insuranceGstTaxConfig.IGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="insuranceUGSTTax" name="insuranceGstTaxConfig.UGST"  value="<s:property value="%{#session.CompanyconfigProfile.insuranceGstTaxConfig.UGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="insuranceGSTconvenienceFee" name="insuranceGstTaxConfig.convenienceFee"   value="<s:property value="%{#session.CompanyconfigProfile.insuranceGstTaxConfig.convenienceFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="insuranceGSTmanagementFee" name="insuranceGstTaxConfig.managementFee"  value="<s:property value="%{#session.CompanyconfigProfile.insuranceGstTaxConfig.managementFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="insuranceGstTaxConfig.totalGst"  id="insuranceGSTtotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.insuranceGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div> --%>
				
					</div>
					<div class="holiday-gst-form-group">					
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">holiday - GST</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="holidayGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.holidayGstTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="holidayGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="holidayGstTaxConfig.CGST"  id="holidayCGST"   value="<s:property value="%{#session.CompanyconfigProfile.holidayGstTaxConfig.CGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidaySGST" name="holidayGstTaxConfig.SGST"  value="<s:property value="%{#session.CompanyconfigProfile.holidayGstTaxConfig.SGST}"/>"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidayIGST" name="holidayGstTaxConfig.IGST"  value="<s:property value="%{#session.CompanyconfigProfile.holidayGstTaxConfig.IGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="holidayUGSTTax" name="holidayGstTaxConfig.UGST"  value="<s:property value="%{#session.CompanyconfigProfile.holidayGstTaxConfig.UGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidayGSTconvenienceFee" name="holidayGstTaxConfig.convenienceFee"   value="<s:property value="%{#session.CompanyconfigProfile.holidayGstTaxConfig.convenienceFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidayGSTmanagementFee" name="holidayGstTaxConfig.managementFee"  value="<s:property value="%{#session.CompanyconfigProfile.holidayGstTaxConfig.managementFee}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="holidayGstTaxConfig.totalGst"  id="holidayGSTtotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.holidayGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div> --%>
				
					</div>
					
					<div class="advertisement-gst-form-group">					
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Advertisement - GST</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="advertisementGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.advertisementGstTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="advertisementGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="advertisementGstTaxConfig.CGST"  id="advertisementCGST"   value="<s:property value="%{#session.CompanyconfigProfile.advertisementGstTaxConfig.CGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="advertisementSGST" name="advertisementGstTaxConfig.SGST"  value="<s:property value="%{#session.CompanyconfigProfile.advertisementGstTaxConfig.SGST}"/>"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="advertisementIGST" name="advertisementGstTaxConfig.IGST"  value="<s:property value="%{#session.CompanyconfigProfile.advertisementGstTaxConfig.IGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="advertisementUGSTTax" name="advertisementGstTaxConfig.UGST"  value="<s:property value="%{#session.CompanyconfigProfile.advertisementGstTaxConfig.UGST}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="advertisementGSTconvenienceFee" name="advertisementGstTaxConfig.convenienceFee"   value="<s:property value="%{#session.CompanyconfigProfile.advertisementGstTaxConfig.convenienceFee}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="advertisementGSTmanagementFee" name="advertisementGstTaxConfig.managementFee"  value="<s:property value="%{#session.CompanyconfigProfile.advertisementGstTaxConfig.managementFee}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">Total-GST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="advertisementGstTaxConfig.totalGst"  id="advertisementGSTtotalGst"  value="<s:property value="%{#session.CompanyconfigProfile.advertisementGstTaxConfig.totalGst}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div> --%>
				
					</div>
					
					<div class="miscellaneous-gst-form-group">					
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Miscellaneous - GST</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="miscellaneousGstTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.miscellaneousGstTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="miscellaneousGstTaxConfig.applicableFare" value="ManagementFee" autocomplete="off"  readonly="readonly">
						
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">CGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" name="miscellaneousGstTaxConfig.CGST"  id="miscellaneousCGST"  value="<s:property value="%{#session.CompanyconfigProfile.miscellaneousGstTaxConfig.CGST}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">SGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="miscellaneousSGST" name="miscellaneousGstTaxConfig.SGST" value="<s:property value="%{#session.CompanyconfigProfile.miscellaneousGstTaxConfig.SGST}"/>"  placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">IGST</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="miscellaneousIGST" name="miscellaneousGstTaxConfig.IGST" value="<s:property value="%{#session.CompanyconfigProfile.miscellaneousGstTaxConfig.IGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">UGST(Applicable for Union Territory )</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="miscellaneousUGSTTax" name="miscellaneousGstTaxConfig.UGST" value="<s:property value="%{#session.CompanyconfigProfile.miscellaneousGstTaxConfig.UGST}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="miscellaneousGSTconvenienceFee" name="miscellaneousGstTaxConfig.convenienceFee"  value="<s:property value="%{#session.CompanyconfigProfile.miscellaneousGstTaxConfig.convenienceFee}"/>"   placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="miscellaneousGSTmanagementFee" name="miscellaneousGstTaxConfig.managementFee" value="<s:property value="%{#session.CompanyconfigProfile.miscellaneousGstTaxConfig.managementFee}"/>"    placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					
				
					</div>
					
					
					
					
					
					<div class="flight-international-form-group">
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Flight - International</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="flightInternationalServiceTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalServiceTaxConfig.id}"/>"> 
						<input type="hidden" name="flightInternationalServiceTaxConfig.serviceType" value="International">
						<input type="text" class="form-control input-sm" name="flightInternationalServiceTaxConfig.applicableFare" value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalServiceTaxConfig.applicableFare}"/>" autocomplete="off"  readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Basic Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightinternationalbasetax1"  name="flightInternationalServiceTaxConfig.basicTax"  value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalServiceTaxConfig.basicTax}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Swatch Bharath Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightinternationalswatchBharathCess" name="flightInternationalServiceTaxConfig.swatchBharathCess"   value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalServiceTaxConfig.swatchBharathCess}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Krishi Kalyan Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightInternationalkrishiKalyanCess" name="flightInternationalServiceTaxConfig.krishiKalyanCess"  value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalServiceTaxConfig.krishiKalyanCess}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Total Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="flightInternationaltotalTax" name="flightInternationalServiceTaxConfig.totalTax"  value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalServiceTaxConfig.totalTax}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="flightInternationalconvenienceFee" name="flightInternationalServiceTaxConfig.convenienceFee"  value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalServiceTaxConfig.convenienceFee}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightInternationalmanagementFee" name="flightInternationalServiceTaxConfig.managementFee"  value="<s:property value="%{#session.CompanyconfigProfile.flightInternationalServiceTaxConfig.managementFee}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					</div>
					
					<div class="flight-domestic-form-group" >
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Flight - Domestic</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">
						<input type="hidden" name="flightDomesticServiceTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticServiceTaxConfig.id}"/>"> 
						<input type="hidden" name="flightDomesticServiceTaxConfig.serviceType" value="Domestic">
						<input type="text" class="form-control input-sm" name="flightDomesticServiceTaxConfig.applicableFare" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticServiceTaxConfig.applicableFare}"/>" autocomplete="off"	 readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Basic Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightDomesticbasicTax" name="flightDomesticServiceTaxConfig.basicTax" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticServiceTaxConfig.basicTax}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Swatch Bharath Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightDomesticswatchBharathCess" name="flightDomesticServiceTaxConfig.swatchBharathCess" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticServiceTaxConfig.swatchBharathCess}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Krishi Kalyan Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightDomestickrishiKalyanCess" name="flightDomesticServiceTaxConfig.krishiKalyanCess" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticServiceTaxConfig.krishiKalyanCess}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Total Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightDomestictotalTax" name="flightDomesticServiceTaxConfig.totalTax" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticServiceTaxConfig.totalTax}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightDomesticconvenienceFee" name="flightDomesticServiceTaxConfig.convenienceFee" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticServiceTaxConfig.convenienceFee}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="flightDomesticmanagementFee" name="flightDomesticServiceTaxConfig.managementFee" value="<s:property value="%{#session.CompanyconfigProfile.flightDomesticServiceTaxConfig.managementFee}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					</div>
					
						<div class="hotel-form-group" >
						
						<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Hotel</label>				
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">						
						<input type="hidden" name="hotelServiceTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.hotelServiceTaxConfig.id}"/>"> 
						<input type="text" class="form-control input-sm" name="hotelServiceTaxConfig.applicableFare" value="<s:property value="%{#session.CompanyconfigProfile.hotelServiceTaxConfig.applicableFare}"/>" autocomplete="off"	 readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Basic Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hotelbasicTax" name="hotelServiceTaxConfig.basicTax" value="<s:property value="%{#session.CompanyconfigProfile.hotelServiceTaxConfig.basicTax}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Swatch Bharath Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm"  id="hotelswatchBharathCess" name="hotelServiceTaxConfig.swatchBharathCess" value="<s:property value="%{#session.CompanyconfigProfile.hotelServiceTaxConfig.swatchBharathCess}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Krishi Kalyan Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hotelkrishiKalyanCess" name="hotelServiceTaxConfig.krishiKalyanCess" value="<s:property value="%{#session.CompanyconfigProfile.hotelServiceTaxConfig.krishiKalyanCess}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Total Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hoteltotalTax" name="hotelServiceTaxConfig.totalTax" value="<s:property value="%{#session.CompanyconfigProfile.hotelServiceTaxConfig.totalTax}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hotelconvenienceFee" name="hotelServiceTaxConfig.convenienceFee" value="<s:property value="%{#session.CompanyconfigProfile.hotelServiceTaxConfig.convenienceFee}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee  (International)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hotelmanagementFee" name="hotelServiceTaxConfig.managementFee" value="<s:property value="%{#session.CompanyconfigProfile.hotelServiceTaxConfig.managementFee}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee (Domestic)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="hoteldomesticmanagementfee" name="hotelServiceTaxConfig.domesticManagementFee" value="<s:property value="%{#session.CompanyconfigProfile.hotelServiceTaxConfig.domesticManagementFee}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					</div>
					
					<div class="bus-form-group" >
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Bus/Car</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">				
						<input type="hidden" name="busServiceTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.busServiceTaxConfig.id}"/>"> 		
						<input type="text" class="form-control input-sm"  id="busapplicableFare" name="busServiceTaxConfig.applicableFare" value="<s:property value="%{#session.CompanyconfigProfile.busServiceTaxConfig.applicableFare}"/>" autocomplete="off" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Basic Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="busbasicTax" name="busServiceTaxConfig.basicTax" value="<s:property value="%{#session.CompanyconfigProfile.busServiceTaxConfig.basicTax}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Swatch Bharath Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="bussbt" name="busServiceTaxConfig.swatchBharathCess" value="<s:property value="%{#session.CompanyconfigProfile.busServiceTaxConfig.swatchBharathCess}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Krishi Kalyan Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="buskkt" name="busServiceTaxConfig.krishiKalyanCess" value="<s:property value="%{#session.CompanyconfigProfile.busServiceTaxConfig.krishiKalyanCess}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Total Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="bustotaltax" name="busServiceTaxConfig.totalTax" value="<s:property value="%{#session.CompanyconfigProfile.busServiceTaxConfig.totalTax}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="busconvenienceFee" name="busServiceTaxConfig.convenienceFee" value="<s:property value="%{#session.CompanyconfigProfile.busServiceTaxConfig.convenienceFee}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee (Bus)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="busmanagementfee" name="busServiceTaxConfig.managementFee" value="<s:property value="%{#session.CompanyconfigProfile.busServiceTaxConfig.managementFee}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee (Car)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="carmanagementfee" name="busServiceTaxConfig.carManagementFee" value="<s:property value="%{#session.CompanyconfigProfile.carServiceTaxConfig.managementFee}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					</div>
					
				
					
					<div class="holiday-form-group" >
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Holiday Package</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">			
						<input type="hidden" name="holidayServiceTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.holidayServiceTaxConfig.id}"/>"> 				
						<input type="text" class="form-control input-sm" name="holidayServiceTaxConfig.applicableFare" value="<s:property value="%{#session.CompanyconfigProfile.holidayServiceTaxConfig.applicableFare}"/>" autocomplete="off"	 readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Basic Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidaybasicTax" name="holidayServiceTaxConfig.basicTax" value="<s:property value="%{#session.CompanyconfigProfile.holidayServiceTaxConfig.basicTax}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Swatch Bharath Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidayswatchBharathCess" name="holidayServiceTaxConfig.swatchBharathCess" value="<s:property value="%{#session.CompanyconfigProfile.holidayServiceTaxConfig.swatchBharathCess}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Krishi Kalyan Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidaykkc" name="holidayServiceTaxConfig.krishiKalyanCess" value="<s:property value="%{#session.CompanyconfigProfile.holidayServiceTaxConfig.krishiKalyanCess}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Total Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidaytotaltax" name="holidayServiceTaxConfig.totalTax" value="<s:property value="%{#session.CompanyconfigProfile.holidayServiceTaxConfig.totalTax}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidayconvfee" name="holidayServiceTaxConfig.convenienceFee" value="<s:property value="%{#session.CompanyconfigProfile.holidayServiceTaxConfig.convenienceFee}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee  (International)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidaymgmtfeeinternational" name="holidayServiceTaxConfig.managementFee" value="<s:property value="%{#session.CompanyconfigProfile.holidayServiceTaxConfig.managementFee}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee (Domestic)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="holidaydomesticManagementfee" name="holidayServiceTaxConfig.domesticManagementFee"   value="<s:property value="%{#session.CompanyconfigProfile.holidayServiceTaxConfig.domesticManagementFee}"/>" placeholder="0.00" autocomplete="off">
						</div>
					</div>
					</div>
					
					<div class="rail-form-group" >
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Product Type</label>							
						<div class="col-sm-8">					
						<label class="form-control input-sm">Rail/Visa/Advertisement & Others</label>				
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Applicable Fare</label>							
						<div class="col-sm-8">					
						<input type="hidden" name="railServiceTaxConfig.id" value="<s:property value="%{#session.CompanyconfigProfile.railServiceTaxConfig.id}"/>"> 					
						<input type="text" class="form-control input-sm" name="railServiceTaxConfig.applicableFare" value="<s:property value="%{#session.CompanyconfigProfile.railServiceTaxConfig.applicableFare}"/>" autocomplete="off"	 readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Basic Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railbasicTax" name="railServiceTaxConfig.basicTax" value="<s:property value="%{#session.CompanyconfigProfile.railServiceTaxConfig.basicTax}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Swatch Bharath Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railsbc" name="railServiceTaxConfig.swatchBharathCess" value="<s:property value="%{#session.CompanyconfigProfile.railServiceTaxConfig.swatchBharathCess}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Krishi Kalyan Cess</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railkkc"  name="railServiceTaxConfig.krishiKalyanCess" value="<s:property value="%{#session.CompanyconfigProfile.railServiceTaxConfig.krishiKalyanCess}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Total Tax</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railtotaltax" name="railServiceTaxConfig.totalTax" value="<s:property value="%{#session.CompanyconfigProfile.railServiceTaxConfig.totalTax}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Convenience Fee</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railconviencefee" name="railServiceTaxConfig.convenienceFee" value="<s:property value="%{#session.CompanyconfigProfile.railServiceTaxConfig.convenienceFee}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee  (Rail)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railmgmtfee" name="railServiceTaxConfig.managementFee" value="<s:property value="%{#session.CompanyconfigProfile.railServiceTaxConfig.managementFee}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee Tatkal(Rail)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="railmgmtfee" name="railServiceTaxConfig.managementFeeTatkal" value="<s:property value="%{#session.CompanyconfigProfile.railServiceTaxConfig.managementFeeTatkal}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee (Visa)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="visamgmtfee" name="railServiceTaxConfig.visaManagementFee" value="<s:property value="%{#session.CompanyconfigProfile.visaServiceTaxConfig.managementFee}"/>" placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Management Fee(Advertisement & Others)</label>							
						<div class="col-sm-8">						
						<input type="text" class="form-control input-sm" id="advtandothertax" name="railServiceTaxConfig.advertisementAndOtherManagementFee" value="<s:property value="%{#session.CompanyconfigProfile.advertiseandOtherServiceTaxConfig.managementFee}"/>"  placeholder="0.00" autocomplete="off"	>
						</div>
					</div>
					</div>
					</c:if>
					 
					
					
					
				
					
					
					
					 <div class="form-group">
						<label for="City" class="col-sm-2 control-label">Status </label>
						<div class="col-sm-8">
							<input type="hidden" value="<s:property value="configStatus"/>"
								id="status"> <select class="form-control input-sm"
								name="configStatus"
								id="configStatus<s:property value="%{#session.CompanyconfigProfile.config_id}"/>"
								autocomplete="off" required>
								<option value="yes">Unlocked</option>
								<option value="no">Locked</option>
							</select>
						</div>
					</div>
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
 

	<script type="text/javascript">
	 
	$(document).ready(function() {
		$(" #flightinternationalbasetax1 , #flightinternationalswatchBharathCess , #flightInternationalkrishiKalyanCess , #flightInternationaltotalTax , #flightInternationalconvenienceFee , #flightInternationalmanagementFee , #flightDomesticbasicTax ,#flightDomesticswatchBharathCess , #flightDomestickrishiKalyanCess , #flightDomestictotalTax , #flightDomesticconvenienceFee ,	 #flightDomesticmanagementFee , #hotelbasicTax , #hotelswatchBharathCess , #hotelkrishiKalyanCess , #hoteltotalTax , #hotelconvenienceFee,	#hotelmanagementFee , #hoteldomesticmanagementfee , #busapplicableFare, #busbasicTax , #bussbt , #buskkt , #bustotaltax , #busconvenienceFee, #busmanagementfee , #carmanagementfee , #holidaybasicTax , #holidayswatchBharathCess , #holidaykkc , #holidaytotaltax , #holidayconvfee,#holidaymgmtfeeinternational , #holidaydomesticManagementfee , #railbasicTax , #railsbc , #railkkc , #railtotaltax , #railconviencefee,#railmgmtfee , #visamgmtfee , #advtandothertax" ).keydown(function (e) {
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
	
	
	
	/* $('.flight-domestic-form-group').hide();
	   $('.flight-international-form-group').hide();
	   $('.hotel-form-group').hide();						   
	   $('.bus-form-group').hide();
	    $('.holiday-form-group').hide();						   
	    $('.rail-form-group').hide(); */
	
	 $(function () {
		 
		
		 
		/*  var confitypevalue=$("#configType").val();
		 console.log("confitypevalue",confitypevalue);
	        $("#confitypevalue").on(function () {
	            if ($(this).val() == "B2E") {
	            	 $('.flight-domestic-form-group').show();
					   $('.flight-international-form-group').show();
					   $('.hotel-form-group').show();						   
					   $('.bus-form-group').show();
					    $('.holiday-form-group').show();						   
					    $('.rail-form-group').show();
	            } else {
	            	$('.flight-domestic-form-group').hide();
	 			   $('.flight-international-form-group').hide();
	 			   $('.hotel-form-group').hide();						   
	 			   $('.bus-form-group').hide();
	 			    $('.holiday-form-group').hide();						   
	 			    $('.rail-form-group').hide();
	            }
	        });
	    */
			   
			   
			var status=$("#dealSheetStatus").val();
			var appliedBlockId=$("#appliedBlockId").val();
			
		  	var id = $("#uniqueId").val();
			var configStatus = $("#status").val();
			 if(status=='true'){
				$('#checkSheet').prop('disabled', true);
				$('#checkSheet').prop("checked", true);
				}
			 document.getElementById('configStatus'+id).value = configStatus;
			 document.getElementById('appliedAffiliateSheet'+id).value = appliedBlockId;
			
			 
			 
		 });
		    
		   
		   
		   
		/*  
		   var configTypes = jQuery('#configType');
		   var select = this.value;
		   configTypes.change(function () {
			   console.log("select",select);
		       if ($(this).val() == 'B2E') {
		    	   
		    		  $('.flight-domestic-form-group').show();
					   $('.flight-international-form-group').show();
					   $('.hotel-form-group').show();						   
					   $('.bus-form-group').show();
					    $('.holiday-form-group').show();						   
					    $('.rail-form-group').show();
		       }
		       else  $('.flight-domestic-form-group').hide();
			   $('.flight-international-form-group').hide();
			   $('.hotel-form-group').hide();						   
			   $('.bus-form-group').hide();
			    $('.holiday-form-group').hide();						   
			    $('.rail-form-group').hide();
		   });
		   
		  
		   
		    */
		   
		   
		  
		  			/*  $("#flightCutOffDate").datepicker({
		  				 dateFormat: "dd-mm-yy",
		  				 minDate: 0
		  				
		  			 });  */
		  			  
		   
		   
		    /* 
		  			 $("#hotelCutOffDate").datepicker({
		  				 dateFormat: "dd-mm-yy",
		  				 minDate: 0
		  				
		  			 });  */
		  				   
		   
  </script>
  
 
  
  
 <%@ include file="DashboardFooter.jsp"%>
<script type="text/javascript" src="js/app.js"></script>
  
	
	
	
</body>
</html>