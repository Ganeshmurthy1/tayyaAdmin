
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Common DSR Report Config</title>

<link rel="stylesheet" href="css/alert.css">



</head>
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Common DSR Report Config</h1>
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



			<div class="row ">
				<input type="hidden"
					value="<s:property value="%{#session.CompanyconfigProfile.config_id}"/>"
					id="uniqueId">
				<form action="insertCommonDsrReportConfg" method="post"
					id="formcheck" class="form-horizontal">
					<input type="hidden"
						value="<s:property value="%{#session.Company.companyRole.isDistributor()}"/>"
						class="form-control input-sm" id="companyRoleType"> <input
						type="hidden"
						value="<s:property value="%{#session.Company.companyRole.isSuperuser()}"/>"
						class="form-control input-sm" id="companyRoleTypesuper"> <input
						type="hidden" name="commonDsrReportConfg.companyId"
						value="${companyid}" class="form-control input-sm"
						id="companyRoleTypesuper"> <input type="hidden"
						name="commonDsrReportConfg.id"
						value="<s:property value="%{commonDsrReportConfg.id}"/>">
					<input type="hidden"
						name="commonDsrReportConfg.createdbyCompanyUserId"
						value="<s:property value="%{commonDsrReportConfg.createdbyCompanyUserId}"/>">
					<input type="hidden"
						name="commonDsrReportConfg.modifiedbyCompanyUserId"
						value="<s:property value="%{commonDsrReportConfg.modifiedbyCompanyUserId}"/>">
							
					<input type="hidden"
						name="commonDsrReportConfg.createdAt"
						value="<s:property value="%{commonDsrReportConfg.createdAt}"/>">
						
							



					<div class="customiseddsrreport clearfix">
						<div class="form-group">
							<!-- 	<label class="col-sm-2 control-label">Product Type</label>	 -->
							<!-- <div class="col-sm-12">					
						<label class="form-control input-sm">Customised Dsr report</label>				
						</div> -->
						</div>

						<div class="col-sm-12 text-left">
							<input type="checkbox" id="select_all" /> <label>Select
								All </label>

						</div>
						<div class="customiseddsr">

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="bookingReference"
											name="commonDsrReportConfg.bookingReference"
											value="<s:property value="%{commonDsrReportConfg.bookingReference}"/>"
											<c:if test="${commonDsrReportConfg.bookingReference == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="bookingReference">Booking
										Reference</label>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="systemInvoiceId"
											name="commonDsrReportConfg.systemInvoiceId"
											value="<s:property value="%{commonDsrReportConfg.systemInvoiceId}"/>"
											<c:if test="${commonDsrReportConfg.systemInvoiceId == true}">checked="checked"</c:if>>
									</div>

									<label class=" control-label col-sm-10" for="systemInvoiceId">System
										Invoice Id</label>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="bookingType"
											name="commonDsrReportConfg.bookingType"
											value="<s:property value="%{commonDsrReportConfg.bookingType}"/>"
											<c:if test="${commonDsrReportConfg.bookingType == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="bookingType">Booking
										Type </label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="amendmentType"
											name="commonDsrReportConfg.amendmentType"
											value="<s:property value="%{commonDsrReportConfg.amendmentType}" />"
											<c:if test="${commonDsrReportConfg.amendmentType == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="amendmentType">Amendment
										Type</label>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="invoiceDate"
											name="commonDsrReportConfg.invoiceDate"
											value="<s:property value="%{commonDsrReportConfg.invoiceDate}" />"
											<c:if test="${commonDsrReportConfg.invoiceDate == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="invoiceDate">Invoice
										date</label>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="bookingDate"
											name="commonDsrReportConfg.bookingDate"
											value="<s:property value="%{commonDsrReportConfg.bookingDate}"/>"
											<c:if test="${commonDsrReportConfg.bookingDate == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="bookingDate">Booking
										Date</label>
								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="corporateName"
											name="commonDsrReportConfg.corporateName"
											value="<s:property value="%{commonDsrReportConfg.corporateName}"/>"
											<c:if test="${commonDsrReportConfg.corporateName == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="corporateName">Corporate
										Name</label>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="billingEntity"
											name="commonDsrReportConfg.billingEntity"
											value="<s:property value="%{commonDsrReportConfg.billingEntity}"/>"
											<c:if test="${commonDsrReportConfg.billingEntity == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="billingEntity">Billing
										Entity</label>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="bookerName"
											name="commonDsrReportConfg.bookerName"
											value="<s:property value="%{commonDsrReportConfg.bookerName}"/>"
											<c:if test="${commonDsrReportConfg.bookerName == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="bookerName">Booker
										Name</label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="bookersLoginId"
											name="commonDsrReportConfg.bookersLoginId"
											value="<s:property value="%{commonDsrReportConfg.bookersLoginId}"/>"
											<c:if test="${commonDsrReportConfg.bookersLoginId == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="bookersLoginId">Bookers
										LoginId</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="supplierCode"
											name="commonDsrReportConfg.supplierCode"
											value="<s:property value="%{commonDsrReportConfg.supplierCode}"/>"
											<c:if test="${commonDsrReportConfg.supplierCode == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="supplierCode">Supplier
										Code</label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="supplierName"
											name="commonDsrReportConfg.supplierName"
											value="<s:property value="%{commonDsrReportConfg.supplierName}"/>"
											<c:if test="${commonDsrReportConfg.supplierName == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="supplierName">Supplier
										Name</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="supplierCharge"
											name="commonDsrReportConfg.supplierCharge"
											value="<s:property value="%{commonDsrReportConfg.supplierCharge}"/>"
											<c:if test="${commonDsrReportConfg.supplierCharge == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="supplierCharge">Supplier
										Charge</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="productType"
											name="commonDsrReportConfg.productType"
											value="<s:property value="%{commonDsrReportConfg.productType}"/>"
											<c:if test="${commonDsrReportConfg.productType == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="productType">Product
										Type</label>

								</div>
							</div>



							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="itineraryType"
											name="commonDsrReportConfg.itineraryType"
											value="<s:property value="%{commonDsrReportConfg.itineraryType}"/>"
											<c:if test="${commonDsrReportConfg.itineraryType == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="itineraryType">Itinerary
										Type</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="productName"
											name="commonDsrReportConfg.productName"
											value="<s:property value="%{commonDsrReportConfg.productName}"/>"
											<c:if test="${commonDsrReportConfg.productName == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="productName">Product
										Name</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="productCode"
											name="commonDsrReportConfg.productCode"
											value="<s:property value="%{commonDsrReportConfg.productCode}"/>"
											<c:if test="${commonDsrReportConfg.productCode == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="productCode">Product
										Code</label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="description"
											name="commonDsrReportConfg.description"
											value="<s:property value="%{commonDsrReportConfg.description}"/>"
											<c:if test="${commonDsrReportConfg.description == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="description">Description</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="description2"
											name="commonDsrReportConfg.description2"
											value="<s:property value="%{commonDsrReportConfg.description2}"/>"
											<c:if test="${commonDsrReportConfg.description2 == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="description2">Description2</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="provBooking"
											name="commonDsrReportConfg.provBooking"
											value="<s:property value="%{commonDsrReportConfg.provBooking}"/>"
											<c:if test="${commonDsrReportConfg.provBooking == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="provBooking">Prov
										Booking</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="gdsPnr"
											name="commonDsrReportConfg.gdsPnr"
											value="<s:property value="%{commonDsrReportConfg.gdsPnr}"/>"
											<c:if test="${commonDsrReportConfg.gdsPnr == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="gdsPnr">Gds
										Pnr</label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="ticketNum"
											name="commonDsrReportConfg.ticketNum"
											value="<s:property value="%{commonDsrReportConfg.ticketNum}"/>"
											<c:if test="${commonDsrReportConfg.ticketNum == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="ticketNum">Ticket
										Num</label>

								</div>
							</div>



							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="fareType"
											name="commonDsrReportConfg.fareType"
											value="<s:property value="%{commonDsrReportConfg.fareType}"/>"
											<c:if test="${commonDsrReportConfg.fareType == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="fareType">FareType</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="bookingRefundType"
											name="commonDsrReportConfg.bookingRefundType"
											value="<s:property value="%{commonDsrReportConfg.bookingRefundType}"/>"
											<c:if test="${commonDsrReportConfg.bookingRefundType == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="bookingRefundType">Booking
										RefundType</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="fareBasis"
											name="commonDsrReportConfg.fareBasis"
											value="<s:property value="%{commonDsrReportConfg.fareBasis}" />"
											<c:if test="${commonDsrReportConfg.fareBasis == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="fareBasis">Fare
										Basis</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="cabinClass"
											name="commonDsrReportConfg.cabinClass"
											value="<s:property value="%{commonDsrReportConfg.cabinClass}" />"
											<c:if test="${commonDsrReportConfg.cabinClass == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="cabinClass">Cabin
										Class</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="bookingClass"
											name="commonDsrReportConfg.bookingClass"
											value="<s:property value="%{commonDsrReportConfg.bookingClass}" />"
											<c:if test="${commonDsrReportConfg.bookingClass == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="bookingClass">Booking
										Class</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="paxType"
											name="commonDsrReportConfg.paxType"
											value="<s:property value="%{commonDsrReportConfg.paxType}" />"
											<c:if test="${commonDsrReportConfg.paxType == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="paxType">Pax
										Type</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="travellerName"
											name="commonDsrReportConfg.travellerName"
											value="<s:property value="%{commonDsrReportConfg.travellerName}" />"
											<c:if test="${commonDsrReportConfg.travellerName == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="travellerName">Traveller
										Name</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="totalNights"
											name="commonDsrReportConfg.totalNights"
											value="<s:property value="%{commonDsrReportConfg.totalNights}" />"
											<c:if test="${commonDsrReportConfg.totalNights == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="totalNights">Total
										Nights</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="tripStartDate"
											name="commonDsrReportConfg.tripStartDate"
											value="<s:property value="%{commonDsrReportConfg.tripStartDate}" />"
											<c:if test="${commonDsrReportConfg.tripStartDate == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="tripStartDate">Trip
										StartDate</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="tripEndDate"
											name="commonDsrReportConfg.tripEndDate"
											value="<s:property value="%{commonDsrReportConfg.tripEndDate}" />"
											<c:if test="${commonDsrReportConfg.tripEndDate == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="tripEndDate">Trip
										EndDate</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="journeyType"
											name="commonDsrReportConfg.journeyType"
											value="<s:property value="%{commonDsrReportConfg.journeyType}" />"
											<c:if test="${commonDsrReportConfg.journeyType == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="journeyType">Journey
										Type</label>

								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="baseFare"
											name="commonDsrReportConfg.baseFare"
											value="<s:property value="%{commonDsrReportConfg.baseFare}"/>"
											<c:if test="${commonDsrReportConfg.baseFare == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="baseFare">Base
										Fare</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="yqTax"
											name="commonDsrReportConfg.yqTax"
											value="<s:property value="%{commonDsrReportConfg.yqTax}"/>"
											<c:if test="${commonDsrReportConfg.yqTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="yqTax">YQ
										Tax</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="yrTax"
											name="commonDsrReportConfg.yrTax"
											value="<s:property value="%{commonDsrReportConfg.yrTax}"/>"
											<c:if test="${commonDsrReportConfg.yrTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="yrTax">yrTax</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="k3Tax"
											name="commonDsrReportConfg.k3Tax"
											value="<s:property value="%{commonDsrReportConfg.k3Tax}"/>"
											<c:if test="${commonDsrReportConfg.k3Tax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="k3Tax">k3Tax</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="psfTax"
											name="commonDsrReportConfg.PsfTax"
											value="<s:property value="%{commonDsrReportConfg.psfTax}"/>"
											<c:if test="${commonDsrReportConfg.psfTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="psfTax">PsfTax</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="uDfTax"
											name="commonDsrReportConfg.uDfTax"
											value="<s:property value="%{commonDsrReportConfg.uDfTax}"/>"
											<c:if test="${commonDsrReportConfg.uDfTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="uDfTax">UDfTax</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="jnTax"
											name="commonDsrReportConfg.jnTax"
											value="<s:property value="%{commonDsrReportConfg.jnTax}"/>"
											<c:if test="${commonDsrReportConfg.jnTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="jnTax">GnTax</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="inTax"
											name="commonDsrReportConfg.inTax"
											value="<s:property value="%{commonDsrReportConfg.inTax}"/>"
											<c:if test="${commonDsrReportConfg.inTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="inTax">InTax</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="ocTax"
											name="commonDsrReportConfg.ocTax"
											value="<s:property value="%{commonDsrReportConfg.ocTax}"/>"
											<c:if test="${commonDsrReportConfg.ocTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="ocTax">OcTax</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="otherTax"
											name="commonDsrReportConfg.otherTax"
											value="<s:property value="%{commonDsrReportConfg.otherTax}"/>"
											<c:if test="${commonDsrReportConfg.otherTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="otherTax">OtherTax</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="vFSTax"
											name="commonDsrReportConfg.vFSTax"
											value="<s:property value="%{commonDsrReportConfg.vFSTax}"/>"
											<c:if test="${commonDsrReportConfg.vFSTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="vFSTax">VFSTax</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="courierCharge"
											name="commonDsrReportConfg.courierCharge"
											value="<s:property value="%{commonDsrReportConfg.courierCharge}"/>"
											<c:if test="${commonDsrReportConfg.courierCharge == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="courierCharge">CourierCharge</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="extraKmCharge"
											name="commonDsrReportConfg.extraKmCharge"
											value="<s:property value="%{commonDsrReportConfg.extraKmCharge}"/>"
											<c:if test="${commonDsrReportConfg.extraKmCharge == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="extraKmCharge">ExtraKmCharge</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="driverAllowDay"
											name="commonDsrReportConfg.driverAllowDay"
											value="<s:property value="%{commonDsrReportConfg.driverAllowDay}"/>"
											<c:if test="${commonDsrReportConfg.driverAllowDay == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="driverAllowDay">DriverAllowDay</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="driverAllowNight"
											name="commonDsrReportConfg.driverAllowNight"
											value="<s:property value="%{commonDsrReportConfg.driverAllowNight}"/>"
											<c:if test="${commonDsrReportConfg.driverAllowNight == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="driverAllowNight">Driver
										Allow Night</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox"
											id="tollOrParkingCharge"
											name="commonDsrReportConfg.tollOrParkingCharge"
											value="<s:property value="%{commonDsrReportConfg.tollOrParkingCharge}"/>"
											<c:if test="${commonDsrReportConfg.tollOrParkingCharge== true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10"
										for="tollOrParkingCharge">TollOrParkingCharge</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="extraHourCharge"
											name="commonDsrReportConfg.extraHourCharge"
											value="<s:property value="%{commonDsrReportConfg.extraHourCharge}"/>"
											<c:if test="${commonDsrReportConfg.extraHourCharge == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="extraHourCharge">ExtraHourCharge</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="extraCharge"
											name="commonDsrReportConfg.extraCharge"
											value="<s:property value="%{commonDsrReportConfg.extraCharge}"/>"
											<c:if test="${commonDsrReportConfg.extraCharge == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="extraCharge">ExtraCharge</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="supplierAmendment"
											name="commonDsrReportConfg.supplierAmendment"
											value="<s:property value="%{commonDsrReportConfg.supplierAmendment}"/>"
											<c:if test="${commonDsrReportConfg.supplierAmendment == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="supplierAmendment">supplier
										Amendment</label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="grossFare"
											name="commonDsrReportConfg.grossFare"
											value="<s:property value="%{commonDsrReportConfg.grossFare}"/>"
											<c:if test="${commonDsrReportConfg.grossFare == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="grossFare">Gross
										Fare</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="cgst"
											name="commonDsrReportConfg.cgst"
											value="<s:property value="%{commonDsrReportConfg.cgst}"/>"
											<c:if test="${commonDsrReportConfg.cgst == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="cgst">Cgst</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="sgstorIgstorUgst"
											name="commonDsrReportConfg.sgstorIgstorUgst"
											value="<s:property value="%{commonDsrReportConfg.sgstorIgstorUgst}"/>"
											<c:if test="${commonDsrReportConfg.sgstorIgstorUgst == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="sgstorIgstorUgst">SgstorIgstorUgst</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="gstTax"
											name="commonDsrReportConfg.gstTax"
											value="<s:property value="%{commonDsrReportConfg.gstTax}"/>"
											<c:if test="${commonDsrReportConfg.gstTax == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="gstTax">Gst
										Tax</label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="tayyarahCharge"
											name="commonDsrReportConfg.tayyarahCharge"
											value="<s:property value="%{commonDsrReportConfg.tayyarahCharge}"/>"
											<c:if test="${commonDsrReportConfg.tayyarahCharge  == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="tayyarahCharge">Tayyarah
										Charge</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="convienceFee"
											name="commonDsrReportConfg.convienceFee"
											value="<s:property value="%{commonDsrReportConfg.convienceFee}"/>"
											<c:if test="${commonDsrReportConfg.convienceFee == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="convienceFee">ConvienceFee</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="discount"
											name="commonDsrReportConfg.discount"
											value="<s:property value="%{commonDsrReportConfg.discount}"/>"
											<c:if test="${commonDsrReportConfg.discount == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="discount">Discount</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="netFare"
											name="commonDsrReportConfg.netFare"
											value="<s:property value="%{commonDsrReportConfg.netFare}"/>"
											<c:if test="${commonDsrReportConfg.netFare == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="netFare">NetFare</label>

								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="totalMarkup"
											name="commonDsrReportConfg.totalMarkup"
											value="<s:property value="%{commonDsrReportConfg.totalMarkup}"/>"
											<c:if test="${commonDsrReportConfg.totalMarkup == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="totalMarkup">Total
										Markup</label>

								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="modeOfPayment"
											name="commonDsrReportConfg.modeOfPayment"
											value="<s:property value="%{commonDsrReportConfg.modeOfPayment}"/>"
											<c:if test="${commonDsrReportConfg.modeOfPayment == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="modeOfPayment">Mode
										Of Payment</label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="travelStatus"
											name="commonDsrReportConfg.travelStatus"
											value="<s:property value="%{commonDsrReportConfg.travelStatus}"/>"
											<c:if test="${commonDsrReportConfg.travelStatus == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="travelStatus">TravelStatus</label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="personalBooking"
											name="commonDsrReportConfg.personalBooking"
											value="<s:property value="%{commonDsrReportConfg.personalBooking}"/>"
											<c:if test="${commonDsrReportConfg.personalBooking == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="personalBooking">Personal
										Booking</label>

								</div>
							</div>


							<div class="col-sm-3">
								<div class="form-group">
									<div class="col-sm-2">
										<input class="checkbox" type="checkbox" id="corporateCurrency"
											name="commonDsrReportConfg.corporateCurrency"
											value="<s:property value="%{commonDsrReportConfg.corporateCurrency}"/>"
											<c:if test="${commonDsrReportConfg.corporateCurrency == true}">checked="checked"</c:if>>
									</div>
									<label class=" control-label col-sm-10" for="corporateCurrency">CorporateCurrency</label>

								</div>
							</div>


						</div>

					</div>

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="submit" class="btn btn-primary btn-lg">Add / Update</button>
						</div>
					</div>
				</form>
			</div>
		</section>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {
			$("#select_all").change(function(){   
			    var status = this.checked;  
			    $('.checkbox').each(function(){ 
			        this.checked = status; 
			        if(status){
			        	$(this).val('true'); 
				        $(this).prop('checked',true);
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
 		    
			    if(this.checked == true ){  
			    	$(this).val('true');
			        $(this).attr('checked','checked');
			        $(this).prop('checked',true); 
			       	var a = $('.customiseddsr .checkbox:checked').length; 
				      var d = $('.customiseddsr .checkbox').length; 
				      if(a===d){ 
				    	  $("#select_all")[0].checked = true; 
				      } 
			    } 
			     
			});  
			
			var a = $('.customiseddsr .checkbox:checked').length;  
		       var d = $('.customiseddsr .checkbox').length; 
		      if(a===d){ 
		    	  $("#select_all")[0].checked = true; 
		      }   
		   
		});
	</script>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript" src="js/app.js"></script>
</body>
</html>