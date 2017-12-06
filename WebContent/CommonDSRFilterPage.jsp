<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/alert.css">
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<style>
.dsrform select.reportty, .dsrform input.reportty {
	width: 100%;
	height: 20px;
}

span.multiselect-native-select {
	position: relative
}

span.multiselect-native-select select {
	border: 0 !important;
	clip: rect(0, 0, 0, 0) !important;
	height: 1px !important;
	margin: -1px -1px -1px -3px !important;
	overflow: hidden !important;
	padding: 0 !important;
	position: absolute !important;
	width: 1px !important;
	left: 50%;
	top: 30px
}

.multiselect-container {
	position: absolute;
	list-style-type: none;
	margin: 0;
	padding: 0
}

.multiselect-container .input-group {
	margin: 5px
}

.multiselect-container>li {
	padding: 0
}

.multiselect-container>li>a.multiselect-all label {
	font-weight: 700
}

.multiselect-container>li.multiselect-group label {
	margin: 0;
	padding: 3px 20px 3px 20px;
	height: 100%;
	font-weight: 700
}

.multiselect-container>li.multiselect-group-clickable label {
	cursor: pointer
}

.multiselect-container>li>a {
	padding: 0
}

.multiselect-container>li>a>label {
	margin: 0;
	height: 100%;
	cursor: pointer;
	font-weight: 400;
	padding: 3px 20px 3px 40px
}

.multiselect-container>li>a>label.radio, .multiselect-container>li>a>label.checkbox
	{
	margin: 0
}

.multiselect-container>li>a>label>input[type=checkbox] {
	margin-bottom: 5px
}

.btn-group>.btn-group:nth-child(2)>.multiselect.btn {
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px
}

.multiselect-container label.checkbox, .multiselect-container label.radio
	{
	padding: 3px 20px 3px 40px
}

.multiselect-container li a label.checkbox input[type=checkbox],
	.multiselect-container li a label.radio input[type=radio] {
	margin-left: -20px;
	margin-right: 0
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var company_list = [];
		var citymap = [];
		$.ajax({
			//Action Name
			url :"CompanyListUnderSuperUser",
			dataType : "json",
			data : {
			 parent_company_user_id : $("#companyUserId").val(),
				email : $("#email").val()
			},
			success : function(data, textStatus, jqXHR) {
				for (var i = 0; i < data.records.length; i++) {
					company_list.push(data.records[i].companyname);
					var cityObj ={"key":data.records[i].company_userid,"value":data.records[i].companyname}
					citymap.push(cityObj);
				}
				console.log(company_list);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
		$("#companyName").autocomplete(
				{
					source : function(request, response) {
						var matcher = new RegExp('^'
								+ $.ui.autocomplete
										.escapeRegex(request.term),
								"i");
						response($.grep(company_list, function(item) {
							return matcher.test(item);

						}));
					},
					select : function(event, ui) {
						$.map(citymap, function(value, key) {
							if(value.value==ui.item.label){
								console.log("value---"+value.value+"--------key----------"+value.key);
								$("#companyId").val(value.key);
							}

						});  
					}
					 
				});		 
});
 
 </script>


<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Common DSR Filter Page</h1>

	</section>
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="col-sm-12">
			<h4>
				<a href="javascript:history.back();"><span class="pull-right"><i
						class="fa fa-angle-left"></i> Back</span></a>
			</h4>
		</div>
		<div class="sccuss-full-updated" id="success-alert"
			style="display: none;">
			<div class="succfully-updated clearfix">

				<div class="col-sm-10">
					<h5 class='tax-report-text'></h5>
					<div style="margin-right: -70px;">
						<button type="button" id="cancel" class="btn btn-primary">Cancel</button>
						<button type="button" id="success"
							style="background-color: #00a65a; border-color: #008d4c;"
							class="btn btn-primary">OK</button>
					</div>
				</div>

			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 clearfix">
				<s:if test="%{#session.Company!=null && #session.User!=null}">
					<input id="flightCityUrl" type="hidden"
						value="<s:text name="global.flightCitiesUrl" ></s:text>">
					<!-- getCommonDisReports -->
					<form action="" method="post" id="filterform">
						<input type="hidden"
							value="<s:property value="%{#session.Company.company_userid}"/>"
							id="companyUserId"> <input type="hidden"
							value="<s:property value="%{#session.Company.Email}"/>"
							id="email"> <input type="hidden"
							value="<s:property value="%{#session.User.company_userid}"/>"
							id="user_id"> <input type="hidden" name="showType"
							value="<s:property value="showType"/>">


						<!-- filter box started -->
						<div class="filter-box" id="filters">
							<div class="well">
								<div class="clearfix">
									<!-- Filter of main info -->
									<div class=" filter-text col-sm-12 clearfix">
										<div class="row">
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Travel Type</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="commonDsrFilters.travelType" id="travelType"
															required>
															<option value="All" selected="selected">All</option>
															<option value="F">Flight</option>
															<option value="H">Hotel</option>
															<option value="C">Car</option>
															<option value="B">Bus</option>
															<option value="T">Train</option>
															<option value="V">Visa</option>
															<option value="I">Insurance</option>
															<option value="M">Miscellaneous</option>
														</select>
													</div>
												</div>
											</div>

											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Report Type</label>
													<div id="selreportz" class="col-sm-12">
														<select class="form-control"
															name="commonDsrFilters.travelReportType" id="reportType"
															required>
															<option value="All" selected="selected">DSR
																Report</option> 
																<option value="OutstandingReport">Outstanding report
															 </option>
															 <!-- <option value="AgingReport">Aging Report
															 </option> -->
															<%-- <s:if
																test="%{#session.Company.companyRole.isCorporate() || #session.Company.companyRole.isSuperUser() || #session.Company.companyRole.isDistributor()}">
																<option value="AirSalesReport">Air Sales Report
																</option>
																<option value="AirRouteWiseSalesReport">Air
																	Route Wise Sales Report</option>
																	<option value="AirAdvencedPurchaseSalesReport">Air
																	Advenced Purchased Sales Report</option>
																<option value="AirRMissedSavingSalesReport">Air
																	Missed Saving Report</option>
																	<option value="plannedAirTripReport">Planned Air Trip Report</option>
																	
																<option value="HotelSalesReport">Hotel Sales
																	Report</option>
																<option value="HotelcitywiseSalesReport">Hotel
																	City Wise Sales Report</option>
																	<option value="Advancepurchasehotelreport">Advance purchase hotel report</option>
																<option value="CarSalesReport">Car Sales Report
																</option>
																<option value="BusSalesReport">Bus Sales Report
																</option>
																<option value="TrainSalesReport">Train Sales
																	Report</option>
																<option value="VisaSalesReport">Visa Sales
																	Report</option>
																<option value="InsuranceSalesReport">Insurance
																	Sales Report</option>
																<option value="CustomDsrReport">Common DSR
																	Report</option>

															</s:if>
 --%>

														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking
														Status</label>
													<div class="col-sm-12">
															<select class="form-control" id="bookingStatus"
															name="commonDsrFilters.bookingStatus" required>
															<option value="Confirmed" selected="selected">Confirmed</option>
																<!-- <option value="Failed">Failed</option> -->
															<!-- <option value="Pending">Pending</option>
														 -->
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking Mode</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="commonDsrFilters.bookingMode" required>
															<option value="All" selected="selected">All</option>
															<option value="Offline">Offline</option>
															<option value="Online">Online</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-2 flight-report-type" id="fromDateDivLabel">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">From Date</label>
													<div class="col-sm-12">
														<input type="text" id="fromDate" placeholder="from date"
															class="form-control" name="commonDsrFilters.fromDate"
															required>
													</div>
												</div>
											</div>

											<div class="col-sm-2 flight-report-type" id="toDateDivLabel">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">To Date</label>
													<div class="col-sm-12">
														<input type="text" id="toDate" placeholder="to date"
															class="form-control" name="commonDsrFilters.toDate"
															required>
													</div>
												</div>
											</div>

											<s:if
												test="%{#session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false && #session.Company.companyRole.isDistributor()==false}">
												<div class="col-sm-2 flight-report-type">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Company
															Name</label>
														<div class="col-sm-12">
															<input type="text" id="companyName"
																placeholder="type company name" autocomplete="off"
																class="form-control"> <input type="hidden"
																id="companyId" class="form-control"
																name="commonDsrFilters.companyUserId">
														</div>
													</div>
												</div>
											</s:if>
											<div class="defaultshowdiv">
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking Date</label>
													<div class="col-sm-12">
														<input type="text" id="bookingDate"
															placeholder="Booking Date" class="form-control"
															name="commonDsrFilters.bookingDate" required>
													</div>
												</div>
											</div>
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Travel Date</label>
													<div class="col-sm-12">
														<input type="text" id="travelDate"
															placeholder="travel Date" class="form-control"
															name="commonDsrFilters.travelDate" required>
													</div>
												</div>
											</div>
											
											
											<%-- <s:if test="<s:property value="%{#session.Company.companyRole.isSuperUser}"/> == 'Corporate'"> --%>
											<!-- 
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Is Single PDF</label>
													<div class="col-sm-12">
														<input id="isSinglePDF" class="isSinglePDF" value="Yes" name="isSinglePDF"  type="checkbox">
													</div>
												</div>
											</div> -->
											<div class="col-sm-2 flight-report-type" id="commondiv">
											<div class="form-group clearfix">
											<div id="new">
    												<label><input type="radio" name="pdfOrZip"  id="zipneeded" value="true" /> Zip File</label>
    												<label><input type="radio" name="pdfOrZip"  id="pdfneeded" value="true" /> Single Pdf</label>
    
											</div>
										 <button id="pdfclick" type="button" onclick="callApi();">Download</button> 
											
											
											
											</div>
											</div>

											
											<%-- </s:if> --%>
											</div>
											<div class="defaulthidediv">
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking Date</label>
													<div class="col-sm-12">
														<input type="text" id="bookingDateForAdvancePurchase"
															placeholder="Booking Date" class="form-control"
															name="commonDsrFilters.bookingDateForAdvancePurchase" required>
													</div>
												</div>
											</div>
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Travel Date</label>
													<div class="col-sm-12">
														<input type="text" id="travelDateForAdvancePurchase"
															placeholder="travel Date" class="form-control"
															name="commonDsrFilters.travelDateForAdvancePurchase" required>
													</div>
												</div>
											</div>
											</div>
										
											<div class="defaultPlannedTriphidediv">
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">From Travel Date</label>
													<div class="col-sm-12">
														<input type="text" id="airPlannedTripFromTravelDate"
															placeholder="From Travel Date" class="form-control"
															name="commonDsrFilters.airPlannedTripFromTravelDate" required>
													</div>
												</div>
											</div>
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">To Travel Date</label>
													<div class="col-sm-12">
														<input type="text" id="airPlannedTripToTravelDate"
															placeholder="To Travel Date" class="form-control"
															name="commonDsrFilters.airPlannedTripToTravelDate" required>
													</div>
												</div>
											</div>
											</div>

											<div class="defaulthotelhidediv">
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking Date</label>
													<div class="col-sm-12">
														<input type="text" id="hotelbookingDateForAdvancePurchase"
															placeholder="Booking Date" class="form-control"
															name="commonDsrFilters.hotelbookingDateForAdvancePurchase" required>
													</div>
												</div>
											</div>
											<div class="col-sm-2 flight-report-type">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">CheckIn  Date</label>
													<div class="col-sm-12">
														<input type="text" id="hoteltravelDateForAdvancePurchase"
															placeholder="travel Date" class="form-control"
															name="commonDsrFilters.hoteltravelDateForAdvancePurchase" required>
													</div>
												</div>
											</div>
											</div>
											<%-- 
													</s:if> --%>

											<div class="others-filter" style="display: none">
												<div class="col-sm-2 flight-report-type">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Confirmation_Number</label>
														<div class="col-sm-12">
															<input type="text" id="pnr" placeholder="Confirmation "
																class="form-control"
																name="commonDsrFilters.confirmationNumber">
														</div>
													</div>
												</div>
											</div>

											<div class="flight-filter" style="display: none">

												<div class="col-sm-2 flight-report-type">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">PNR</label>
														<div class="col-sm-12">
															<input type="text" id="pnr" placeholder="PNR"
																class="form-control" name="commonDsrFilters.pnr"
																required>
														</div>
													</div>
												</div>
												<%-- <div class="col-sm-2 flight-report-type">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Booking Class</label>
															  <div class="col-sm-12">
  																<select  class="form-control" name="commonDsrFilters.bookingClass" >
  																<option value="All">ALL</option>
  																<option value="Economy">Economy</option>
																<option value="PremiumEconomy">Premium Economy</option>
																<option value="Business">Business</option>
																<option value="PremiumBusiness">Premium Business</option>

															 
															</select>
															</div>  
														</div>
													</div>  --%>
												<%-- <div class="col-sm-2 flight-report-type">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Destination Type</label>
															<div class="col-sm-12">
  																<select  class="form-control" name="commonDsrFilters.destinationType" >
  																<option value="All">ALL</option>
  																<option value="Dom">Domestic</option>
  																<option value="Inter">International</option>
															 
															</select>
															</div>
														</div>
													</div>  --%>

												<div class="col-sm-2 flight-report-type">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Airline</label>
														<div class="col-sm-12">
															<select class="form-control"
																name="commonDsrFilters.airline">
																<option value="ALL">ALL</option>
																<s:iterator value="airlineList">
																	<option value="<s:property value="airlinename"/>"><s:property
																			value="airlinename"></s:property></option>
																</s:iterator>
															</select>
														</div>
													</div>
												</div>


												<div class="col-sm-2 flight-report-type">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Origin</label>
														<div class="col-sm-12">
															<input type="text" id="origin" placeholder="origin"
																class="form-control"> <input type="hidden"
																id="originCode" class="form-control"
																name="commonDsrFilters.origin" required>
														</div>
													</div>
												</div>
												<div class="col-sm-2 flight-report-type">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Destination</label>
														<div class="col-sm-12">
															<input type="text" id="destination"
																placeholder="destination" class="form-control">
															<input type="hidden" id="destinationCode"
																name="commonDsrFilters.destination">
														</div>
													</div>
												</div>

											</div>


											<div class="hotel-filter" style="display: none">
												<div class="col-sm-2 flight-report-type">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Order
															Reference</label>
														<div class="col-sm-12">
															<input type="text" id="orderReference"
																placeholder="Order Reference" class="form-control"
																name="commonDsrFilters.orderReference" required>
														</div>
													</div>
												</div>
												<%--  <div class="col-sm-2 flight-report-type">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Destination Type</label>
															<div class="col-sm-12">
  																<select  class="form-control" name="commonDsrFilters.hotelDestinationType">
  																<option value="All">ALL</option>
  																<option value="Dom">Domestic</option>
  																<option value="Inter">International</option>
															 
															</select>
															</div>
														</div>
													</div>  --%>
												<div class="col-sm-2 flight-report-type">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Hotel City</label>
														<div class="col-sm-12">
															<input type="text" id="hotelCitySearch"
																placeholder="Hotel city" class="form-control"
																name="commonDsrFilters.city">
														</div>
													</div>
												</div>
												<div class="col-sm-2 flight-report-type">
													<div class="form-group clearfix">
														<label class="col-sm-12 control-label">Hotel
															Country</label>
														<div class="col-sm-12">
															<select class="form-control"
																name="commonDsrFilters.country">
																<option value="ALL">ALL</option>
																<s:iterator value="countryList">
																	<option value="<s:property value="c_name"/>"><s:property
																			value="c_name"></s:property></option>
																</s:iterator>
															</select>
														</div>
													</div>
												</div>
											</div>

											<div class="col-sm-12">				
											
												<div class="clearfix text-right">
												<button type="button" class="btn btn-primary viewPage" id="viewPage" >View Page</button>
													<button type="button" class="btn btn-success"
														id="xlsdownload">
														<i class="fa fa-file-excel-o"></i> download
													</button>
												</div>
											</div>

										</div>
									</div>
								</div>

								<!-- filter box ending -->
							</div>
						</div>

					</form>

				</s:if>
			</div>
		</div>
	</section>
</div>

<%@include file="DashboardFooter.jsp"%>
<script src="js/city_code.js"></script>
<script src="js/flight_city_url.js" type="text/javascript"></script>
<script>
	 $("#fromDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	 $("#toDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	 $("#bookingDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	 
	 $("#travelDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	 $("#bookingDateForAdvancePurchase").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	 $("#travelDateForAdvancePurchase").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	 $("#airPlannedTripFromTravelDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	 
	 $("#airPlannedTripToTravelDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
		 $("#hotelbookingDateForAdvancePurchase").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	 
	 $("#hoteltravelDateForAdvancePurchase").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy"
		});
	 
	 
	 
	 
	 $(function() {
		 if ($('#reportType').val() == "All") {
			 $('#bookingStatus').append('<option value="Failed">Failed</option>');
			}
			$("#reportType").change(function() {
				var reportType = $(this).val();
				var travelType = $(this).val();
				 if (reportType == "All" && (travelType=='All' ||travelType=='F' || travelType=='H' ||travelType=='C' ||travelType=='B' ||travelType=='T' ||travelType=='V' ||travelType=='I' ||travelType=='M')) {
					 $('#bookingStatus').append('<option value="Failed">Failed</option>');
					}
				 
				 else{
					 $('#bookingStatus').find('option').remove(); 	
					 $('#bookingStatus').append('<option value="Confirmed">Confirmed</option>');
					 
				 }
				
			 });
		 
		 
		 
		
	if ($('#travelType').val() == "All") {
			$('.viewPage').hide();
		}
	 
		$("#travelType")
				.change(
						function() {
							var travelType = $(this).val();
							if (travelType == "All") {
								//var flightreport = false;
								var count = 0;
								$('#reportType').find('option').remove();
								$('#reportType')
										.append(
												'<option value="All" selected="selected">DSR Report</option>');
								$('#reportType')
								.append(
										'<option value="OutstandingReport" >Outstanding Report</option>');
								$(".flight-filter").hide();
								$(".hotel-filter").hide();
								$(".others-filter").hide();
								$('.viewPage').show();
							}
							if (travelType == "F") {
								var flightreport = false;
								var count = 0;
								$('#reportType').find('option').remove();
								$('#reportType')
										.append(
												'<option value="All" selected="selected">DSR Report</option>');
								$('#reportType')
										.append(
												'<option value="AirSalesReport" >Air Sales Report</option>');
								$('#reportType')
								.append(
										'<option value="AirRouteWiseSalesReport">Air Route Wise Sales Report</option>');
								$('#reportType')
								.append(
										'<option value="AirAdvencedPurchaseSalesReport">Air Advenced Purchase Sales Report</option>');
								$('#reportType')
								.append(
										'<option value="AirMissedSavingReport">Air Missed Saving Report</option>');
								$('#reportType')
										.append(
												'<option value="CustomDsrReport">Common Dsr Report</option>');
								
								$('#reportType')
								.append(
										'<option value="plannedAirTripReport">Planned Air Trip Report</option>');
								
								
								$(".flight-filter").show();
								$(".hotel-filter").hide();
								$(".others-filter").hide();
								$('.viewPage').show();
							}
							if (travelType == "H") {
								var hotelreport = false;

								$('#reportType').find('option').remove();
								$('#reportType')
										.append(
												'<option value="All" selected="selected">DSR Report</option>');
								$('#reportType')
										.append(
												'<option value="HotelSalesReport" >Hotel Sales Report</option>');
								$('#reportType')
								.append(
										'<option value="HotelcitywiseSalesReport">Hotel City Wise Sales Report</option>');
								$('#reportType')
										.append(
												'<option value="CustomDsrReport">Common Dsr Report</option>');
								$('#reportType')
								.append(
										'<option value="Advancepurchasehotelreport">Advance purchase hotel report</option>');

								$(".hotel-filter").show();
								$(".flight-filter").hide();
								$(".others-filter").hide();
								$('.viewPage').show();
							} else if (travelType == "C") {
								var cartreport = false;
								$('#reportType').find('option').remove();
								$('#reportType')
										.append(
												'<option value="All" selected="selected">DSR Report</option>');
								$('#reportType')
										.append(
												'<option value="CarSalesReport" >Car Sales Report</option>');
								$('#reportType')
										.append(
												'<option value="CustomDsrReport">Common Dsr Report</option>');
								$(".hotel-filter").hide();
								$(".flight-filter").hide();
								$(".others-filter").show();
								$('.viewPage').show();
							} else if (travelType == "B") {
								var busreport = false;
								$('#reportType').find('option').remove();
								$('#reportType')
										.append(
												'<option value="All" selected="selected">DSR Report</option>');
								$('#reportType')
										.append(
												'<option value="BusSalesReport" selected="selected">Bus Sales Report</option>');
								$('#reportType')
										.append(
												'<option value="CustomDsrReport">Common Dsr Report</option>');
								$(".hotel-filter").hide();
								$(".flight-filter").hide();
								$(".others-filter").show();
								$('.viewPage').show();
							} else if (travelType == "T") {
								var traintreport = false;
								$('#reportType').find('option').remove();
								$('#reportType')
										.append(
												'<option value="All" selected="selected">DSR Report</option>');
								$('#reportType')
										.append(
												'<option value="TrainSalesReport"  >Train Sales Report</option>');
								$('#reportType')
										.append(
												'<option value="CustomDsrReport">Common Dsr Report</option>');
								$(".hotel-filter").hide();
								$(".flight-filter").hide();
								$(".others-filter").show();
								$('.viewPage').show();
							} else if (travelType == "V") {
								var visareport = false;
								$('#reportType').find('option').remove();
								$('#reportType')
										.append(
												'<option value="All" selected="selected">DSR Report</option>');
								$('#reportType')
										.append(
												'<option value="VisaSalesReport" >Visa Sales Report</option>');
								$('#reportType')
										.append(
												'<option value="CustomDsrReport">Common Dsr Report</option>');

								$(".hotel-filter").hide();
								$(".flight-filter").hide();
								$(".others-filter").show();
								$('.viewPage').show();
							} else if (travelType == "I") {
								var insurancereport = false;
								$('#reportType').find('option').remove();
								$('#reportType')
										.append(
												'<option value="All" selected="selected">DSR Report</option>');
								$('#reportType')
										.append(
												'<option value="InsuranceSalesReport"  >Insurance Sales Report</option>');
								$('#reportType')
										.append(
												'<option value="CustomDsrReport">Common Dsr Report</option>');

								$(".hotel-filter").hide();
								$(".flight-filter").hide();
								$(".others-filter").show();
								$('.viewPage').show();
							}

							else if (travelType == "M") {
								var insurancereport = false;
								$('#reportType').find('option').remove();
								$('#reportType')
										.append(
												'<option value="All" selected="selected">DSR Report</option>');
								
								$('#reportType')
								.append(
										'<option value="MiscellaneousSalesReport"  >Miscellaneous Sales Report</option>');
								
								$('#reportType')
										.append(
												'<option value="CustomDsrReport">Common Dsr Report</option>');
								
								
								
								$(".hotel-filter").hide();
								$(".flight-filter").hide();
								$(".others-filter").show();
								$('.viewPage').show();
							}

							else if (travelType == "All") {
								$('.viewPage').hide();
								$(".hotel-filter").hide();
								$(".flight-filter").hide();
								$(".others-filter").hide();
							}

						});

	});
	$('#viewPage').click(function() {
		$("#filterform").attr("action", "CommonDisReportViewPage");
		$("#filterform").submit();

	});
	$('#xlsdownload')
			.click(
					function() {
						var travelType = $('#travelType').val();
						var reportType = $('#reportType').val();
						var fromDate = $('#fromDate').val();
						var toDate = $('#toDate').val();
						var gstDateStart = '2017-06-30';

						if (travelType == 'All' || travelType == 'F'
								|| travelType == 'H' || travelType == 'C'
								|| travelType == 'B' || travelType == 'T'
								|| travelType == 'V' || travelType == 'I'
								|| travelType == 'M') {
							$("#filterform").attr("action",
									"CommonDisReportExcelDownload");
							if (fromDate != '' && toDate != '') {
								var fromDateChange = String(fromDate)
										.split("-");
								fromDate = fromDateChange[2] + "-"
										+ fromDateChange[1] + "-"
										+ fromDateChange[0];
								var toDateChange = String(toDate).split("-");
								toDate = toDateChange[2] + "-"
										+ toDateChange[1] + "-"
										+ toDateChange[0];
								var msg = "";
								if ((new Date(fromDate) > new Date(toDate))) {
									$('#success-alert').show()
									msg = "FromDate must be greater than to Todate";
									$('.tax-report-text').text(msg);
									$('#success').hide();
								} else if ((new Date(fromDate) > new Date(
										gstDateStart))
										&& (new Date(toDate) > new Date(
												gstDateStart))) {
									$('#success-alert').show()
									msg = "Based on selected dates only Gst tax reports will be download";
									$('.tax-report-text').text(msg);
									$('#success').show();
								} else if ((new Date(fromDate) > new Date(
										gstDateStart))
										&& (new Date(toDate) <= new Date(
												gstDateStart))) {
									$('#success-alert').show()
									msg = "Gst tax reports will be download from 01-07-2017 . So please make sure fromDate and toDate on July 2017 onwards";
									$('.tax-report-text').text(msg);
									$('#success').hide();
								} else if ((new Date(fromDate) <= new Date(
										gstDateStart))
										&& (new Date(toDate) > new Date(
												gstDateStart))) {
									$('#success-alert').show()
									msg = "Gst tax reports will be download from 01-07-2017 . So please make sure fromDate and toDate on July 2017 onwards";
									$('.tax-report-text').text(msg);
									$('#success').hide();
								}

								else if ((new Date(fromDate) <= new Date(
										gstDateStart))
										&& (new Date(toDate) <= new Date(
												gstDateStart))) {
									$('#success-alert').show()
									msg = "Service tax reports  only will be download";
									$('.tax-report-text').text(msg);
									$('#success').show();
								}

							} else {
								$('#success-alert').show()
								msg = "Default only Gst tax reports will be download";
								$('.tax-report-text').text(msg);
								$('#success').show();
							}

							$('#success').click(function() {
								$('#success-alert').hide()
								$("#filterform").submit();
							});
							$('#cancel').click(function() {
								$('#success-alert').hide()
							});
						} else {
							$("#filterform").attr("action",
									"downloadHotelDisReport");
							$("#filterform").submit();
						}

					});
	
	
	
	
</script>
<script type="text/javascript">
	$('#companyName').focus(function() {
		$('#companyName').val('');
		$("#companyId").val('');
	});
	$('#fromDate').focus(function() {
		$('#fromDate').val('');
		if($('#fromDate').val() == ''){
			$("#commondiv").hide();
		}
		
	});
	$('#bookingDate').focus(function() {
		$('#bookingDate').val('');
	});
	$('#travelDate').focus(function() {
		$('#travelDate').val('');
	});
	$('#bookingDateForAdvancePurchase').focus(function() {
		$('#bookingDateForAdvancePurchase').val('');
	});
	
	$('#travelDateForAdvancePurchase').focus(function() {
		$('#travelDateForAdvancePurchase').val('');
	});
	
	$('#airPlannedTripFromTravelDate').focus(function() {
		$('#airPlannedTripFromTravelDate').val('');
	});
	$('#airPlannedTripToTravelDate').focus(function() {
		$('#airPlannedTripToTravelDate').val('');
	});
	
	$('#hotelbookingDateForAdvancePurchase').focus(function() {
		$('#hotelbookingDateForAdvancePurchase').val('');
	});
	$('#hoteltravelDateForAdvancePurchase').focus(function() {
		$('#hoteltravelDateForAdvancePurchase').val('');
	});
	
	$('#toDate').focus(function() {
		$('#toDate').val('');
		if($('#toDate').val() == ''){
			$("#commondiv").hide();
		}
		
		
		
	});
	$('#pnr').focus(function() {
		$('#pnr').val('');
	});
	$('#origin').focus(function() {
		$('#origin').val('');
		$('#originCode').val('');
	});
	$('#destination').focus(function() {
		$('#destination').val('');
		$('#destinationCode').val('');
	});
	$('#orderReference').focus(function() {
		$('#orderReference').val('');
	});
	$('#hotelCitySearch').focus(function() {
		$('#hotelCitySearch').val('');
	});
</script>
<script>
$(function() {
	$('.defaulthidediv').hide();
	$('.defaulthotelhidediv').hide();
	$('.defaultshowdiv').show();
	 $('.defaultPlannedTriphidediv').hide();
	 $('#reportType').change(function(){
	    //$('.flight-report-typehide').hide();
	    if($(this).val()== 'AirAdvencedPurchaseSalesReport'){
	    	//alert("basha");
	    	$('.defaulthidediv').show();
	    	$('.defaultshowdiv').hide();
	    }
	    else if($('#reportType').val() == 'Advancepurchasehotelreport'){
       $('.defaultshowdiv').hide();
       $('.defaulthotelhidediv').show();
        }
	    
	    else if($('#reportType').val() == 'plannedAirTripReport'){
	        $('.defaultshowdiv').hide();
	        $('.defaulthotelhidediv').hide();
	        $('.defaultPlannedTriphidediv').show();
	        $("#fromDateDivLabel").hide();
	        $("#toDateDivLabel").hide();
	         }
	    else{
	    	$('.defaulthidediv').hide();
	    	$('.defaultshowdiv').show();
	    	
	    }
	   
	  });
	});



</script>
<!-- added by basha for pdf and zip download  -->
<script type="text/javascript">
$("#commondiv").hide();
$('#pdfclick').hide();
$('#travelType , #fromDate , #toDate, #bookingStatus , #pdfneeded , #zipneeded').on('change', function (){
	var travelType=$('#travelType').val();
	var fromDate=$('#fromDate').val();
	var toDate=$('#toDate').val();
	var bookingstatus=$('#bookingStatus').val();
	if(travelType != 'All'   && fromDate != '' && fromDate.trim().length>0 && toDate != '' && toDate.trim().length>0 && bookingstatus == 'Confirmed'){
		//alert(fromDate);
		$("#commondiv").show();
		if(document.getElementById('pdfneeded').checked || document.getElementById('zipneeded').checked){
		if (document.getElementById('pdfneeded').checked) {
			 $('#pdfclick').show();
		}
		if (document.getElementById('zipneeded').checked) {
			 $('#pdfclick').show();
			}
			}else{
				$('#pdfclick').hide();
			}	
		
		
		
		
		
		
		
	}else{
		//alert(fromDate);
		//alert("no");
		$("#commondiv").hide();
		 
	}
}); 

alert(document.getElementById('pdfneeded').checked.val());
alert(document.getElementById('zipneeded').checked.val());
$(document).ready(function() {
    $("#new label").on("mouseup", function(e) {
        var thisItem = $(this);
        var previous = thisItem.children("input").prop("checked");
        if (previous) {
            setTimeout(function() {
            thisItem.children("input").prop("checked", false).blur();
            }, 10);
        }

    });       
    
});










function callApi() {
	var travelType=$('#travelType').val();
	var fromDate=$('#fromDate').val();
	var toDate=$('#toDate').val();
	var pdfneeded='false';
	var zipneeded='false';
	var bookingstatus=$('#bookingStatus').val();
	if (document.getElementById('pdfneeded').checked) {
		pdfneeded= document.getElementById('pdfneeded').value;
		}
	if (document.getElementById('zipneeded').checked) {
		zipneeded= document.getElementById('zipneeded').value;
		}
	
	var urlPath = 'resources/ApiResourceURLcredential.properties';
		var apiUrl = '';
			 $.ajax({
				url : urlPath,
				type : 'GET',
				dataType : 'json',
				success : function(response) {
					apiUrl = response.ApiResourceURL;
					  
					var finalUrl = apiUrl+"BulkInvoice/SinglePdf";
					console.log("finalUrl",finalUrl);
					finalUrl=finalUrl+"?travelType="+travelType+"&fromDate="+fromDate+"&toDate="+toDate+"&isSinglePdf="+pdfneeded+"&isZipFile="+zipneeded;
					 //window.open(finalUrl);
					 window.location.href=finalUrl;
					//console.log("finalUrl",finalUrl);
					/* $.ajax({
						 	crossOrigin: true,
						    method: "GET",
						   	accept: 'application/pdf,application/zip',
						    url:finalUrl,
						    data:{travelType:travelType,fromDate:fromDate,toDate:toDate,isSinglePdf:pdfneeded,isZipFile:zipneeded},
						   	success:function(data,status,xhr)
							{ 
						   	console.log("Sucess");
						   console.log("xhr",xhr);
						  	console.log("Data",data);	
						  	},error: function(xhr,status,error){
						  		console.log("We are facing some technical issues please try again   !!!!!!!!!!!!");
						  		console.log("finalUrl",finalUrl);
						  		console.log("Data",data);
						  		//console.log("Data",data);
						  		console.log("xhr",xhr);
							}
						});  */
					  
					
				},
				error : function(xhr, status, error) {
					console.log("newxhr",xhr);
				}

			}); 
}










/* 
$('#pdfclick').click(function(){
    if ($('#pdfneeded').is(':checked')) {
        alert('pdfneeded !');
    }
    if ($('#zipneeded').is(':checked')) {
        alert('zipneeded !');
    }
}); */

/* $('#isSinglePDF').on('change', function (){
	if($(this).prop( "checked" )){
		var pdfNeeded = 'Yes';
		var travelType=$('#travelType').val();
		var fromDate=$('#fromDate').val();
	 	var toDate=$('#toDate').val();
	 	var bookingstatus=$('#bookingStatus').val();
	 	if(travelType != 'All'   && fromDate.trim().length>0  && toDate.trim().length>0 && bookingstatus == 'Confirmed'){
	 		var urlPath = 'resources/ApiResourceURLcredential.properties';
	 		var apiUrl = '';
	 			 $.ajax({
	 				url : urlPath,
	 				type : 'GET',
	 				dataType : 'json',
	 				success : function(response) {
	 					apiUrl = response.ApiResourceURL;
	 					  
	 					var finalUrl = apiUrl+"BulkInvoice/SinglePdf";
	 					//console.log("finalUrl",finalUrl);
	 					  $.ajax({
	 						 	crossOrigin: true,
	 						    method: "GET",
	 						   	accept: 'application/pdf',
	 						    url:finalUrl,
	 						    data:{travelType:travelType,fromDate:fromDate,toDate:toDate,isSinglePdf:pdfneeded,isZipFile:zipneded},
	 						   	success:function(data,status,xhr)
	 							{ 
	 						   	console.log("Sucess");
	 						   console.log("xhr",xhr);
	 						  	console.log("Data",data);	
	 						  	},error: function(xhr,status,error){
	 						  		console.log("We are facing some technical issues please try again   !!!!!!!!!!!!");
	 						  		console.log("finalUrl",finalUrl);
	 						  		console.log("Data",data);
	 						  		//console.log("Data",data);
	 						  		console.log("xhr",xhr);
	 							}
	 						}); 
	 					
	 				},
	 				error : function(xhr, status, error) {
	 					console.log("newxhr",xhr);
	 				}

	 			}); 
	 	
	 	}else{
	 		console.log("Not saticified the required conditions");
	 	}
	 	
	}else{
		var pdfNeeded = 'No';
	}
	 console.log("pdfNeeded",pdfNeeded);
}); */

</script>



<%-- <script>

$(function() {
	 $('.flight-report-typehide').hide(); 
	$('.flight-report-type').show(); 
    $('#reportType').change(function(){
        if($('#reportType').val() == 'AirAdvencedPurchaseSalesReport') {
        	$('.flight-report-typehide').show();
        	$('.flight-report-type').hide();
        }
        else if($('#reportType').val() == 'Advancepurchasehotelreport'){
        $('.flight-report-type').hide();
        $('.hotel-report-typehide').show();
        }else {
        	$('.flight-report-typehide').hide();
        	$('.flight-report-type').show();
        } 
    });
});
 
</script>
 --%>
<!-- ended by basha for pdf and zip download  -->
