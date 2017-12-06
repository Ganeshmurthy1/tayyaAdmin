<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>

 

 

<link rel="stylesheet" href="css/alert.css">

	<link rel="stylesheet" type="text/css" href="print-pdf-excel/print-resource/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="print-pdf-excel/print-resource/buttons.dataTables.min.css">
	
	 <link rel="stylesheet" type="text/css" href="print-pdf-excel/print-resource/jquery.dataTables.min.css">
	
	
	<script type="text/javascript" async="" src="print-pdf-excel/print-resource/ga.js.download"></script>
	<%-- <script type="text/javascript" src="print-pdf-excel/print-resource/site.js.download">
	</script> --%>
	<%-- <script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/jquery-1.12.3.js.download">
	</script> --%>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/jquery.dataTables.min.js.download">
	</script>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/dataTables.buttons.min.js.download">
	</script>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/buttons.flash.min.js.download">
	</script>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/jszip.min.js.download">
	</script>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/pdfmake.min.js.download">
	</script>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/vfs_fonts.js.download">
	</script>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/buttons.html5.min.js.download">
	</script>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/buttons.print.min.js.download">
	</script>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/demo.js.download">
	</script>
	<script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/buttons.colVis.min.js">
	</script>

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
<script src="js/load_company_emp_names.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	$(function() {
		var reportType = document.getElementById('reportTypeHidden').value;

		document.getElementById('reportType').value = reportType;

	});
</script>

</head>
<body>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		
			<h1>DSR Report</h1>

		</section>
		<section class="content">
			
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
						<h4 >
							  <a href="goCommonDisReport"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
			 
			<%-- </section>
		 <section class="content"> --%>
		 
		 <div>
				
				<!-- filter box started -->

				<!-- filter box ending -->
			</div>
			<div class="row">
				<div class="col-sm-12 clearfix">
					<s:if test="%{#session.Company!=null && #session.User!=null}">
						<form action="CommonDisReportViewPage" method="post" id="filterform">
						<div class="row">
					<div class="col-sm-4 pull-right items">
						<div class="form-group clearfix">

							<div class="col-sm-6">
								<select class="form-control" name="commonDsrPage.maxItems"
									id="maxItems" required onchange="this.form.submit()">
									<c:forEach var="maxItems"
										items="${commonDsrPage.pageSizeQueue}">
										<c:choose>
											<c:when
												test="${commonDsrPage.maxItems != null && commonDsrPage.maxItems == maxItems}">
												<c:choose>
													<c:when test="${commonDsrPage.maxItems == -1}">
														<option value="${maxItems}" selected="selected">ALL</option>
													</c:when>

													<c:otherwise>
														<option value="${maxItems}" selected="selected">${maxItems}</option>
													</c:otherwise>
												</c:choose>
											</c:when>

											<c:otherwise>
												<c:choose>
													<c:when test="${maxItems == -1}">
														<option value="${maxItems}">ALL</option>
													</c:when>

													<c:otherwise>
														<option value="${maxItems}">${maxItems}</option>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</c:forEach>

								</select>
							</div>
							<label class="col-sm-5 control-label text-left">Items Per
								Page </label>

						</div>
					</div>
				</div>
						
						
						
						
						
							<input type="hidden" id="reportTypeHidden"
								name="commonDsrFilters.travelType" 
								value="<s:property value="commonDsrPage.commonDsrFilters.travelType"/>">
							<input type="hidden"
								name="commonDsrFilters.travelReportType"
								value="<s:property value="commonDsrFilters.travelReportType"/>">
							<input type="hidden"
								name="commonDsrFilters.bookingStatus"
								value="<s:property value="commonDsrFilters.bookingStatus"/>">
							<input type="hidden"
								name="commonDsrFilters.fromDate"
								value="<s:property value="commonDsrFilters.fromDate"/>">
							<input type="hidden"
								name="commonDsrFilters.toDate"
								value="<s:property value="commonDsrFilters.toDate"/>">
										
								<input type="hidden"
								name="commonDsrFilters.bookingDate"
								value="<s:property value="commonDsrFilters.bookingDate"/>">
								
								<input type="hidden"
								name="commonDsrFilters.travelDate"
								value="<s:property value="commonDsrFilters.travelDate"/>">
								
								
							<input type="hidden"
								name="commonDsrFilters.companyUserId"
								value="<s:property value="commonDsrFilters.companyUserId"/>">
							<input type="hidden"
								name="commonDsrFilters.pnr"
								value="<s:property value="commonDsrFilters.pnr"/>">
							<input type="hidden"
								name="commonDsrFilters.airline"
								value="<s:property value="commonDsrFilters.airline"/>">
							<input type="hidden"
								name="commonDsrFilters.origin"
								value="<s:property value="commonDsrFilters.origin"/>">
							<input type="hidden"
								name="commonDsrFilters.destination"
								value="<s:property value="commonDsrFilters.destination"/>">

							
							<div class="col-sm-12 clearfix report-search">
								<div class="table-responsive dash-table">
									<div class="box clearfix">
										<table id="example" class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>S.No</th>
													
													<th>Booking Reference</th>
													<th>System Invoice Id</th>
													<th>Booking Billing Type</th>
													<th>Amendment Type</th>
													<th>Invoice Date</th>
													<th>Booking Date</th>
													<th>Corporate Name</th>
													<th>Billing Entity</th>
													<th>Booker Name</th>
													<th>Booker Login Id</th>
													<th>Supplier Code</th>
													<th>Supplier Name</th>
													<th>Supplier Charge</th>
													<th>Product Type</th>
													<th>Itinary type</th>
													<th>Product Name</th>
													<th>Product Code</th>
													<th>Description</th>
													<th>Airline PNR/Prov booking</th>
													<th>GDS PNR</th>
													<th>Ticket No/Final Booking</th>
													<th>Fare type</th>
													<th>Booking Refund Type</th>
													<th>Fare Basis</th>
													<th>Cabin Class</th>
													<th>Booking Class</th>
													<th>Pax Type</th>
													<th>Traveller Name</th>
													<th>Total Nights</th>
													<th>Trip Start Date</th>
													<th>Trip End Date</th>
													<th>Journey Type</th>
													<th>Base Fare</th>
													<th>YQ Tax</th>
													<th>YR Tax</th>
													<th>PSF Tax</th>
													<th>UDF Tax</th>
													<th>JN Tax</th>
													<th>OB Tax</th>
													<th>OC Tax</th>
													<th>Other Tax</th>
													<th>Extra Charge(Meal,Baggage,etc)</th>
													<th>Supplier Amendment/Cancellation fees</th>
													<th>Gross Fare</th>
													<th>Service Tax base</th>
													<th>Swach Bharat Cess</th>
													<th>Krishi Kalyan Cess</th>
													<th>Service Tax</th>
													<th>Tayyarah Charges</th>
													<th>Convenience Charge</th>
													<th>Discount</th>
													<th>Net Fare</th>
													<th>Total Markup</th>
													<th>Mode Of Payment</th>
													<th>Travel Status</th>
													<th>Corporate ROE</th>
													<th>Personal Booking</th>
													<th>Corporate Currency</th>
													<th>Tour Code</th>
													<th>Cost Center Code</th>
													<th>Emp Code</th>
													
													
												</tr>
											</thead>
											<tbody>
												<s:iterator value="commonDsrTravelReports" status="rowCount">
													<tr>
														<td><s:property
																value="%{((commonDsrPage.currentPageIndex - 1)*commonDsrPage.maxItems)+#rowCount.count}" /></td>

														<td><s:property value="BkgRef" /></td>
														<td><s:property value="SystemInvoiceId" /></td>
														<td><s:property value="BookingType" /></td>
														<td><s:property value="AmendmentType" /></td>
														<td><s:property value="Invoicedate" /></td>
														<td><s:property value="BookingDate" /></td>
														<td><s:property value="CorporateName" /></td>
														<td><s:property value="BillingEntity" /></td>
														<td><s:property value="BookerName" /></td>
														<td><s:property value="BookersLoginId" /></td>
														<td><s:property value="SupplierCode" /></td>
														<td><s:property value="SupplierName" /></td>
														<td><s:property value="SupplierCharge" /></td>
														<td><s:property value="ProductType" /></td>
														<td><s:property value="ItineraryType" /></td>
														<td><s:property value="ProductName" /></td>
														<td><s:property value="ProductCode" /></td>
														<td><s:property value="Description" /></td>
														<td><s:property value="AirlinePNRorProvBooking" /></td>
														<td><s:property value="GDSPNR" /></td>
														<td><s:property value="TicketNumorFinalBooking" /></td>
														<td><s:property value="FareType" /></td>
														<td><s:property value="BookingRefundType" /></td>
														<td><s:property value="FareBasis" /></td>
														<td><s:property value="CabinClass" /></td>
														<td><s:property value="BookingClass" /></td>
														<td><s:property value="PaxType" /></td>
														<td><s:property value="Traveller" /></td>
														<td><s:property value="TotalNights" /></td>
														<td><s:property value="TripStartsDate" /></td>
														<td><s:property value="TripEndDate" /></td>
														<td><s:property value="JourneyType" /></td>
														
														<td><s:property value="BaseFare" /></td>
														<td><s:property value="YQTax" /></td>
														<td><s:property value="YRTax" /></td>
														<td><s:property value="PSFTax" /></td>
														<td><s:property value="UDFTax" /></td>
														<td><s:property value="JNTax" /></td>
														<td><s:property value="OBTax" /></td>
														<td><s:property value="OCTax" /></td>
														<td><s:property value="OtherTaxes" /></td>
														<td><s:property value="ExtraCharge" /></td>
														<td><s:property value="SupplierAmendmentOrCancellationFee" /></td>
														<td><s:property value="GrossFare" /></td>
														<td><s:property value="ServiceTaxBase" /></td>
														<td><s:property value="SwachBharatCess" /></td>
														<td><s:property value="KrishiKalyanCess" /></td>
														<td><s:property value="ServiceTax" /></td>
														<td><s:property value="TayyarahServiceCharges" /></td>
														<td><s:property value="ConvenienceCharge" /></td>
														<td><s:property value="Discount" /></td>
														<td><s:property value="NetFare" /></td>
														<td><s:property value="Markup" /></td>
														<td><s:property value="ModeOfPayment" /></td>
														<td><s:property value="TravelStatus" /></td>
														<td><s:property value="CorporateROE" /></td>
														<td><s:property value="PersonalBooking" /></td>
														<td><s:property value="CorporateCurrency" /></td>
														<td><s:property value="TourCode" /></td>
														<td><s:property value="CostCenterCode" /></td>
														<td><s:property value="EmpCode" /></td>

													</tr>
												</s:iterator>
											</tbody>
										</table>
										<table id="pagtable">
											<tr id="tr">
												<span>Showing <s:property
														value="%{((commonDsrPage.currentPageIndex - 1)*commonDsrPage.maxItems)+1}" />
													to <s:property
														value="%{((commonDsrPage.currentPageIndex*commonDsrPage.maxItems) <= commonDsrPage.availableItems)?(commonDsrPage.currentPageIndex*commonDsrPage.maxItems):commonDsrPage.availableItems}" />
													of <s:property value="%{commonDsrPage.availableItems}" />
													items
												</span>

											</tr>
											<tr id="tr">

												<c:if test="${commonDsrPage.currentPageIndex>1}">
													<td id="td"><button type="submit"
															name="commonDsrPage.currentPageIndex" value="1"
															class="btn btn-primary">First</button></td>
													<td id="td"><button type="submit"
															name="commonDsrPage.currentPageIndex"
															value="${commonDsrPage.currentPageIndex - 1}"
															class="btn btn-primary">Prev</button></td>
												</c:if>

												<c:forEach
													begin="${(commonDsrPage.currentPageIndex) > 1? (commonDsrPage.currentPageIndex) : 1}"
													end="${ (commonDsrPage.currentPageIndex + 4) <= commonDsrPage.availablePages ? (commonDsrPage.currentPageIndex + 4) :  commonDsrPage.availablePages }"
													var="i">
													<td>
														<button type="submit"
															name="commonDsrPage.currentPageIndex" value="${i}"
															class="btn btn-primary">
															<c:choose>
																<c:when test="${commonDsrPage.currentPageIndex == i}">
																	<u>${i}</u>
																</c:when>

																<c:otherwise>
															${i}								
														</c:otherwise>
															</c:choose>
														</button>
													</td>
												</c:forEach>
												<c:if
													test="${(commonDsrPage.currentPageIndex + 4) < commonDsrPage.availablePages}">
													<td id="td"><button type="submit"
															name="commonDsrPage.currentPageIndex"
															value="${commonDsrPage.currentPageIndex + 1}"
															class="btn btn-primary">Next</button></td>
													<td id="td"><button type="submit"
															name="commonDsrPage.currentPageIndex"
															value="${commonDsrPage.availablePages}"
															class="btn btn-primary">Last</button></td>
												</c:if>

											</tr>
										</table>
									</div>
								</div>
							</div>
						</form>

					</s:if>
				</div>
			</div>
		</section>

	</div>
 
	<%@ include file="DashboardFooter.jsp"%>
	  
	<script type="text/javascript">
	$(document).ready(function() {
		var currentDate = "export-data_"+$.datepicker.formatDate('yyyy-mm-dd', new Date())+(new Date).getTime();
		$('#example').DataTable( {
			dom: 'Bfrtip',
			"paging" : false,
			"info" : false,
			"searching" : false,
			 
		buttons: [
		            {
		                extend: 'csv',
		                exportOptions: {
		                	columns: "thead th:not(.noExport)"
		                },
		                filename: currentDate
		            },
		            'colvis'
		        ]
			        
		} );


		
				var company_list = [];
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
							citymap[data.records[i].companyname] = data.records[i].company_userid;
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
								var  companyUserId = citymap[ui.item.value];
								  $('#companyId').val(companyUserId);
							 }
						});
				 
	});
	 
 </script> 


</body>
</html>