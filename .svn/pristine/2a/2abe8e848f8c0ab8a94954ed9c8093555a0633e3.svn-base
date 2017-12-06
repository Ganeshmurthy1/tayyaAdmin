<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
  
 
<title><s:property value="user" /></title>
 
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

<style type="text/css">
.ui-autocomplete {
	max-height: 200px;
	width: auto;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: auto;
	font-family: "Trebuchet MS", "Helvetica", "Arial", "Verdana",
		"sans-serif";
	font-size: 1em;
	/* add padding to account for vertical scrollbar */
}
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}

.items label {
	margin-top: 5px;
}
 
</style>
<style>
#pagtable {
	border-collapse: collapse;
	width: auto;
	float: right;
}

#th, #td {
	text-align: left;
	padding: 8px;
}
</style>
<script type="text/javascript">
		$(function() {
		  var  reportType=document.getElementById('reportTypeHidden').value;
			 document.getElementById('reportType').value = reportType;
		});
	</script>

 
<script type="text/javascript">
	$(document).ready(
			function() {
				var company_list = [];
				var agents_list = [];
				  var user_list = []; 
				 var cityMap=[];
				 var userMap=[];

				$.ajax({
					//Action Name
					url :"CompanyListUnderSuperUser",
					dataType : "json",
					data : {
					 parent_company_user_id : $("#companyUserId").val(),
						email : $("#email").val()
					},
					success : function(data, textStatus, jqXHR) {
						var items = data.records;
						for (var i = 0; i < data.records.length; i++) {
							//company_list.push(data.records[i].companyname +"("+data.records[i].company_userid+")"+","
							//		+ data.records[i].companyid);
							company_list.push(data.records[i].companyname);
							 var cityObj ={"key":data.records[i].companyid,"value":data.records[i].companyname}
	        				 cityMap.push(cityObj);
							 
						}
						console.log(company_list);
						//response(items);
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
								 $.map(cityMap, function(value, key) {
			          				    if(value.value==ui.item.label){
			          						console.log("value---"+value.value+"--------key----------"+value.key);
			          						$("#companyId").val(value.key);
			          					  }
			          				  
			          				});  
							 }
						});
				
				
				$.ajax({
					//Action Name
					url :"AgentsListUnderSuperUser",
					dataType : "json",
					data : {
					 parent_company_user_id : $("#companyUserId").val(),
						email : $("#email").val()
					},

					success : function(data, textStatus, jqXHR) {
 					for (var i = 0; i < data.agentList.length; i++) {
 						agents_list.push(data.agentList[i].username);
							/* agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")"  + ","
									+ data.agentList[i].id); */
									 var userObj ={"key":data.agentList[i].id,"value":data.agentList[i].username}
									userMap.push(userObj);
 							 
						}
						console.log("------agents_list------"+agents_list);
						 user_list=agents_list;
							console.log("------user_list------"+user_list);
						 userlist(user_list,userMap);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
				
			  });
	
	 function userlist(userlist,userMap)
			{
		  if(userlist.length>0){
				$("#agentName").autocomplete(
						{
		 				source : function(request, response) {
								var matcher = new RegExp('^'
										+ $.ui.autocomplete
												.escapeRegex(request.term),
										"i");
								response($.grep(userlist, function(item) {
									return matcher.test(item);

								}));
							},
							select : function(event, ui) {
							 $.map(userMap, function(value, key) {
		          				    if(value.value==ui.item.label){
		          						console.log("value---"+value.value+"--------key----------"+value.key);
		          						$("#userId").val(value.key);
		          					  }
		          				  
		          				});  
						 }
						});	 
		 }
		  
	}
 </script>
 
 <script type="text/javascript">
  function  sendFlightVoucherToCustomerEmail(confirmNumber,invoiceMail){
	 
    	  //var orderId=$("#orderId").val();
    	  var msg="";
    	  console.log("confirmNumber..."+confirmNumber);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl=newUrl+"sendFlightVoucherToCustomerEmail";
  		if(invoiceMail=="invoice"){
  			 finalUrl = newUrl+"sendFlightInvoiceToCustomerEmail";
  			msg="Successfully sent flight Invoice to email.";
  		}
  		else{
  			 finalUrl = newUrl+"sendFlightVoucherToCustomerEmail";
  			msg="Successfully sent flight voucher to email.";
  		}
  		
  	  console.log("finalUrl..."+finalUrl);
  		$('#h4').show();
  			  $.ajax({
				    method: "POST",
				    url:finalUrl,
				    data: {orderId:confirmNumber},
				    success:function(data,status)
					{ 
				        $.each(data, function(index, element) {
				    		  console.log("data-------"+element.status);
						     	if(element.status=="success"){
						     		$('#h4').hide();
						    		  $('#success-alert').show();
									  $('#message').text(msg);
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					  //window.location.assign($(location).attr('href'));
						  					});					  				
						    	}
						    	else if(element.status=="fail"){
						    		$('#h4').hide();
						    	 $('#success-alert').show();
									  $('#message').text("Failed.Try again.");
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					 
						  					});  
					    	}
						    	 
				    	 });
				     },
					error: function(xhr, status, error)
					{
						$('#h4').hide();
					 $('#success-alert').show();
						 $('#message').text(error);
						  $('#success').click(function() {
		  					  $('#success-alert').hide();
		  					 });  
					   console.log("Error----------"+error);
					}
				});   
    	   }
      
  </script> 
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Flight Reports</h1>
<div class="sccuss-full-updated" id="success-alert"
				style="display: none">
				<div class="succfully-updated clearfix">

					<div class="col-sm-2">
						<i class="fa fa-check fa-3x"></i>
					</div>

					<div id="message" class="col-sm-10"></div>
					<button type="button" id="success" class="btn btn-primary">Ok</button>
				</div>

			</div>
		</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<!-- searchCompanyReportsList -->
			<div class="row">
			<!-- <input type="button" id="downloadExcel" value="downloadExcel"> -->
				<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">

				<form action="companyReportList" method="post" id="filterform">
					<input type="hidden" name="showType"
					value="<s:property value="showType"/>">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-8">
								<a class="btn btn-primary" role="button" data-toggle="collapse"
									href="#filters" aria-expanded="false" aria-controls="filters">
									<i class="fa fa-filter" aria-hidden="true"></i> Filters
								</a>
							</div>

							<div class="col-sm-4 pull-right items">
								<div class="form-group clearfix">

									<div class="col-sm-6">
										<select class="form-control" name="flightReportPage.maxItems"
											id="maxItems" required onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${flightReportPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${flightReportPage.maxItems != null && flightReportPage.maxItems == maxItems}">
													 <c:choose>
													 <c:when
														test="${flightReportPage.maxItems == -1}">
													 <option value="${maxItems}" selected="selected">ALL</option>
													</c:when>
													 
													<c:otherwise>
														<option value="${maxItems}" selected="selected">${maxItems}</option>
													</c:otherwise>
													</c:choose>
													</c:when>
													 
													<c:otherwise>
													 <c:choose>
													 <c:when
														test="${maxItems == -1}">
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
									<label class="col-sm-5 control-label text-left">Items
										Per Page </label>

								</div>
							</div>

						</div>


						<div class="collapse filter-box" id="filters">
							<div class="well">
								<div class="panel-body">



									<!-- Filter of main info -->
									<div class=" filter-text col-sm-12 clearfix">
										<div class="row">
										
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Report Type</label>
													<div class="col-sm-12">

														<input type="hidden" id="reportTypeHidden"
															value="<s:property value="flightReportPage.flightReportFilter.reportType"/>">
														<select class="form-control"
															name="flightReportPage.flightReportFilter.reportType"
															id="reportType" required>
															<option value="1">My Reports</option>
															<s:if test="%{#session.emulateByCompany!=null && #session.emulateByUser!=null}">
															</s:if>
															<s:else>
															<s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false) || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
															<option value="0">ALL</option>
															<s:if
																test="%{#session.Company.companyRole.isDistributor()}">
																<option value="4">My Agency(s)</option>
															</s:if>
															<s:if
																test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
																<%-- <option value="3">My
																	<s:text name="global.Wholesaler"></s:text>(s)
																</option> --%>
																<option value="8">All Corporate(s)</option>
																<option value="4">My Agency(s)</option>
																<option value="6">All
																	<s:text name="global.Wholesaler"></s:text>(s)
																</option>
																<option value="7">All Agency(s)</option>
															</s:if>
														   </s:if>	
														   </s:else>
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">PNR</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.pnr"/>"
															placeholder="type pnr" autocomplete="off"    name="flightReportPage.flightReportFilter.pnr"  id="pnr">
													</div>
												</div>
											</div>  

											  <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">PaxName</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.paxName"/>"
															placeholder="paxName"  autocomplete="off"     id="paxName" name="flightReportPage.flightReportFilter.paxName">
													</div>
												</div>
											</div>  
											<s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Company Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="companyName" placeholder="type company Name"
															name="flightReportPage.flightReportFilter.companyName"
															value="<s:property value="flightReportPage.flightReportFilter.companyName"/>">
															<input type="hidden"  id="companyId"  name="flightReportPage.flightReportFilter.companyId"     value="<s:property value="flightReportPage.flightReportFilter.companyId"/>">
															 
													</div>
												</div>
											</div>
										</s:if>		


											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Employee
														Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="on"
															id="agentName" placeholder="type emp user id"
															name="flightReportPage.flightReportFilter.userName"
															value="<s:property value="flightReportPage.flightReportFilter.userName"/>">
															<input type="hidden"  id="userId"  name="flightReportPage.flightReportFilter.userId"     value="<s:property value="flightReportPage.flightReportFilter.userId"/>">
															
													</div>
												</div>
											</div>


											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Airline Name</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="flightReportPage.flightReportFilter.airlineName"
															id="airlinename" required>
															<option value="ALL">ALL</option>
															<s:iterator value="airlineList">

																<s:if
																	test="flightReportPage.flightReportFilter.airlineName != null && flightReportPage.flightReportFilter.airlineName == airlinename">
																	<option value="<s:property value="airlinename"/>"
																		selected="selected"><s:property
																			value="airlinename"></s:property></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="airlinename"/>"><s:property
																			value="airlinename"></s:property></option>
																</s:else>
															</s:iterator>
														</select>

													</div>
												</div>
											</div>

											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Booking
														Status</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="flightReportPage.flightReportFilter.bookingStatus"
															id="bookingStatus" required>

															<option value="ALL">ALL</option>
															<c:forEach var="statusItem"
																items="${flightReportPage.flightReportFilter.bookingStatusQueue}">
																<c:choose>
																	<c:when
																		test="${flightReportPage.flightReportFilter.bookingStatus != null && flightReportPage.flightReportFilter.bookingStatus == statusItem}">
																		<option value="${statusItem}" selected="selected">${statusItem}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${statusItem}">${statusItem}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>

											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Payment
														Status</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="flightReportPage.flightReportFilter.paymentStatus"
															id="paymentStatus" required>

															<option value="ALL">ALL</option>
															<c:forEach var="paymentStatusItem"
																items="${flightReportPage.flightReportFilter.paymentStatusQueue}">
																<c:choose>
																	<c:when
																		test="${flightReportPage.flightReportFilter.paymentStatus != null && flightReportPage.flightReportFilter.paymentStatus == paymentStatusItem}">
																		<option value="${paymentStatusItem}"
																			selected="selected">${paymentStatusItem}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${paymentStatusItem}">${paymentStatusItem}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Invoice No</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.invoiceNo"/>"
															placeholder="type invoiceNo" autocomplete="off"    name="flightReportPage.flightReportFilter.invoiceNo"  id="invoiceNo">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">OrderId</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.orderId"/>"
															placeholder="type orderId" autocomplete="off"    name="flightReportPage.flightReportFilter.orderId"  id="orderId">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">First Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.firstName"/>"
															placeholder="type firstName" autocomplete="off"    name="flightReportPage.flightReportFilter.firstName"  id="firstName">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Last Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.lastName"/>"
															placeholder="type lastName" autocomplete="off"    name="flightReportPage.flightReportFilter.lastName"  id="lastName">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Email</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.email"/>"
															placeholder="type email" autocomplete="off"    name="flightReportPage.flightReportFilter.email"  id="email">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Mobile</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.mobile"/>"
															placeholder="type mobile" autocomplete="off"    name="flightReportPage.flightReportFilter.mobile"  id="mobile">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Origin</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.origin"/>"
															placeholder="type origin" autocomplete="off"    name="flightReportPage.flightReportFilter.origin"  id="origin">
													</div>
											</div>
									</div>
									<div class="col-sm-2">
											<div class="form-group clearfix">
												<label class="col-sm-12 control-label">Destination</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="flightReportPage.flightReportFilter.destination"/>"
															placeholder="type destination" autocomplete="off"    name="flightReportPage.flightReportFilter.destination"  id="destination">
													</div>
											</div>
									</div>
									
											  
										</div>
									</div>


									<div class="date  clearfix">
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">From Date</label>
													 
														<input type="text" class="form-control" id="twodt1"
															placeholder="From Date(dd-mm-yyyy)"
															name="flightReportPage.flightReportFilter.dateFilterBooking.dtStart"
															value='<s:property value="flightReportPage.flightReportFilter.dateFilterBooking.dtStart"/>'>
													 
													<%-- <div class="col-sm-6">
														<input type="text" class="form-control" id="twodt2"
															placeholder="To Date(dd-mm-yyyy)"
															name="flightReportPage.flightReportFilter.dateFilterBooking.dtEnd"
															value='<s:property value="flightReportPage.flightReportFilter.dateFilterBooking.dtEnd"/>'>
													</div> --%>
												</div>
												 
											</div>
											
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">To Date</label>
													 
														<input type="text" class="form-control" id="twodt2"
															placeholder="To Date(dd-mm-yyyy)"
															name="flightReportPage.flightReportFilter.dateFilterBooking.dtEnd"
															value='<s:property value="flightReportPage.flightReportFilter.dateFilterBooking.dtEnd"/>'>

													 
												</div>
												 
											</div>
											<s:if test="%{#session.Company.companyRole.isSuperuser==true}">
	 										<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Supplier Name</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="flightReportPage.flightReportFilter.supplierName"
															id="supplierName" required>
															<option value="ALL">ALL</option>
															<s:iterator value="ApiProviders">
																<s:if
																	test="commonReportPage.flightReportFilter.supplierName != null && commonReportPage.flightReportFilter.supplierName == vendorName">
																	<option value="<s:property value="vendorName"/>"
																		selected="selected"><s:property value="vendorName"></s:property></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="vendorName"/>"><s:property
																		value="vendorName" /></option>
																</s:else>
																
															</s:iterator>
														</select>

													</div>
												</div>
											</div>
											</s:if>
										</div>
									</div>
									<div class="date  clearfix">
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Travel From Date</label>
													 
														<input type="text" class="form-control" id="twodt11"
															placeholder="From Date(dd-mm-yyyy)"
															name="flightReportPage.flightReportFilter.dateFilterTravel.dtStart"
															value='<s:property value="flightReportPage.flightReportFilter.dateFilterTravel.dtStart"/>'>
													 
												</div>
												 
											</div>
											
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Travel To Date</label>
													 
														<input type="text" class="form-control" id="twodt21"
															placeholder="To Date(dd-mm-yyyy)"
															name="flightReportPage.flightReportFilter.dateFilterTravel.dtEnd"
															value='<s:property value="flightReportPage.flightReportFilter.dateFilterTravel.dtEnd"/>'>

													 
												</div>
												 
											</div>
 
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Invoice From Date</label>
													 
														<input type="text" class="form-control" id="twodt12"
															placeholder="From Date(dd-mm-yyyy)"
															name="flightReportPage.flightReportFilter.dateFilterInvoice.dtStart"
															value='<s:property value="flightReportPage.flightReportFilter.dateFilterInvoice.dtStart"/>'>
													 
												</div>
												 
											</div>
											
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Invoice To Date</label>
													 
														<input type="text" class="form-control" id="twodt22"
															placeholder="To Date(dd-mm-yyyy)"
															name="flightReportPage.flightReportFilter.dateFilterInvoice.dtEnd"
															value='<s:property value="flightReportPage.flightReportFilter.dateFilterInvoice.dtEnd"/>'>

													 
												</div>
												 
											</div>
 
										</div>
									</div>
									
									<div class="col-sm-12">

										<div class="col-sm-6 clearfix cc-all">
											<a href="" id="reset" class="text-right"><i
												class="fa fa-close"></i> Clear All</a>
										</div>
										<div class="text-right filtr-btn col-sm-6 clearfix">
											<button type="submit" class="btn btn-primary"
												name="flightReportPage.currentPageIndex"
												value="${flightReportPage.currentPageIndex}">Submit</button>
										</div>
									</div>
 
								</div>
							</div>
						</div>
					</div>
					<!-- Filter ends here.... -->
					<div class="col-sm-12 clearfix report-search">
						<div class="table-responsive dash-table">
							<!-- testing -->
							<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered">
									<thead>
										<tr>
											<!-- <th>Select</th> -->
											<th>S.No</th>
											<th>CreatedDate</th>
											<th>PaxName</th>
											<th>PNR</th>
											<th>Airline</th>
											<th>Origin</th>
											<th>Destination</th>
											<th>BookingDate</th>
											<th>TravelDate</th>
											<s:if test="%{#session.Company.companyRole.isCorporate()==false}">
											<th>NetPayable</th>
											<th>MarkUp</th>
											</s:if>
											<th>FinalAmount</th>
											<th>BookingStatus</th>
											<th>PaymentStatus</th>
											<s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false)||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<th>Agency</th>
											</s:if>
											<s:else>
											<th>Corporate</th>
											</s:else>
											<s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false)||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<th>SalesPersonName</th>
											</s:if>
											<s:elseif test="%{#session.Company.companyRole.isCorporate()}">
											<th>BookerUserName</th>
											</s:elseif>
											<th>OrderId</th>
											
											<th>Invoice Value</th> 
											<th>Invoice No.</th> 
											
											<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<th class="noExport">Download Voucher</th>
											<th class="noExport">Voucher Email</th>											
											</s:if>
											<s:elseif test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isCorporate()}">
											<th class="noExport">Download Voucher/Invoice</th>
											<th class="noExport">Voucher Email</th>
											<th class="noExport">Invoice Email</th>
											</s:elseif>
											
											 <s:else>
											 <th class="noExport">Download Voucher</th>
											<th class="noExport">Voucher Email</th>	
											 </s:else>
											  <th class="noExport">Action</th>
											  <th class="noExport">Insurance View</th>
											 <s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false &&#session.Company.companyRole.Corporate==false)}">
													<th>SupplierName</th>
													</s:if>
											<!--  <th>ARR</th> -->
										</tr>
									</thead>
									<tbody>
										<s:iterator value="flightReportPage.items" status="rowCount">
											<tr>
											
												<%-- <td><s:if test="status=='Confirmed' || status=='Cancelled'"><input type="checkbox" name="checkBox" value="<s:property value="id" />"> </s:if> </td>
												 --%><td><s:property value="%{((flightReportPage.currentPageIndex - 1)*flightReportPage.maxItems)+#rowCount.count}"/></td>
												<td><s:date name="createdAt" format="dd-MM-yyyy"></s:date></td>
												<td><s:property value="firstName" /> <s:property value="lastName" /></td>
												<td><s:property value="pnr" /></td>
												<td><s:property value="airline" /></td>
												<td><s:property value="origin" /></td>
													<td><s:property value="route" /></td>
												<td><s:property value="bookingDate" /></td>
													<td><s:property value="departureDate"/></td>
												<s:if test="%{#session.Company.companyRole.isCorporate()==false}">
												<td><s:property value="netAmnt"/></td>
												<td><s:property value="markUp"/></td>
												</s:if>
												 <td><s:property value="finalPrice" />  	
												
												
												<%-- 
												<td><s:property value="netAmnt"/></td>
												<s:if test="%{#session.Company.companyRole.isSuperUser() || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
												<td><s:property value="markUp"/></td>
												</s:if>
												<s:elseif test="%{#session.Company.companyRole.isCorporate()}">
												</s:elseif>
												<s:else><td><s:property value="markUp"/></td></s:else>
											
												<td><s:property value="finalPrice" />  --%>
												 <!-- </td> -->
													 <td><s:property value="status" /></td>
														<td><s:property value="paymentStatus"/></td>
														<s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent() || #session.Company.companyRole.isCorporate()}">
														<s:if test="createdBy=='DirectUser'">
														 <td>B2C</td>
														</s:if>
														<s:else>
														<td><s:property value="createdBy"/></td>
														</s:else>
														</s:if>
														 <s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
															<th><s:property value="salesPersonName"/></th>
														</s:if>
														 <s:else><th><s:property value="createdBy"/></th></s:else>
													 <td><s:property value="orderId"/></td>
													 <td><s:property value="invoiceAmount" /></td>
												<td><s:property value="invoiceNo" /></td>
													  <td><p data-placement="top" >
													  <s:if test="status=='Confirmed' || status=='Cancelled'">
													  <a
															href=goFlightOfflineVoucher?id=${id}
															class="btn btn-success btn-xs"   data-toggle="modal">
															Voucher </a>
															<s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isCorporate()}">
													   <a
															href=goFlightOfflineInvoice?id=${id}
															class="btn btn-success btn-xs"   data-toggle="modal">
															Invoice </a>
													   </s:if>
													  </s:if>
													  <s:else>
													  NIL													  
													  </s:else>										  
														
													</p></td>
													  
													  <td><p data-placement="top" >
													  <s:if test="status=='Confirmed' || status=='Cancelled'">
													  <a href="javascript:history.void(0);"
															class="btn btn-success btn-xs"  onclick="sendFlightVoucherToCustomerEmail('${orderId}');"      data-toggle="modal">
															Send Voucher To Email</a>
													  </s:if>
													  <s:else>
													  NIL													  
													  </s:else>										  
														
													</p></td>
													<s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isCorporate()}">
													<td><p data-placement="top" >
													  <s:if test="status=='Confirmed' || status=='Cancelled'">
													  <a href="javascript:history.void(0);"
															class="btn btn-success btn-xs"  onclick="sendFlightVoucherToCustomerEmail('${orderId}','invoice');"      data-toggle="modal">
															Send Invoice To Email</a>
													  </s:if>
													  <s:else>
													  NIL													  
													  </s:else>										  
														
													</p></td>
													 </s:if>
											 		<td>
													<p data-placement="top" class="details">
														<a href="showPassengerDetails?id=<s:property value="id"/>&orderId=<s:property value="orderId"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Details </a>
															 <s:if test="%{#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false}">
									 						<s:if test="apiResponseMessage!=null">
															<span class="detailstiptext" ><s:property value="apiResponseMessage"/></span>  
															</s:if>
															<s:else>
															<span class="detailstiptext">No Response</span>  
															</s:else>
															</s:if>
															<s:else>
															<s:if test="apiResponseMessage!=null && apiResponseMessage=='Confirmed'">
															<span class="detailstiptext" ><s:property value="apiResponseMessage"/></span>  
															</s:if>
															<s:else>
															<span class="detailstiptext">Please Contact Tayyarah</span>  
															</s:else>
															</s:else>
											<%--   <span class="detailstiptext" ><s:property value="apiResponseMessage" /></span>   --%>
															 
													</p>

												</td>
												<s:if test="isInsuranceAdded == true">
												<td>
												<p data-placement="top" class="InsuranceView">
												<a href="showPassengerDetailsOfInsuranceReports?id=<s:property value="insuranceOrderRowId"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Details </a>
												</td>
												</s:if>
												<s:else>
												<td>N/A</td>
												</s:else>
												<s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false && #session.Company.companyRole.isCorporate()==false )}">
													<td><s:property value="apiProvider"/></td>
													</s:if>





												<%-- 	<td><s:property value="departureDate" /></td>
														<td><s:property value="arrivalDate" /></td> --%>

											</tr>
										</s:iterator>
									</tbody>
								</table>
								<table id="pagtable">
									<tr id="tr">
										<span>Showing <s:property
												value="%{((flightReportPage.currentPageIndex - 1)*flightReportPage.maxItems)+1}" />
											to <s:property
												value="%{((flightReportPage.currentPageIndex*flightReportPage.maxItems) <= flightReportPage.availableItems)?(flightReportPage.currentPageIndex*flightReportPage.maxItems):flightReportPage.availableItems}" />
											of <s:property value="%{flightReportPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">

										<c:if test="${flightReportPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="flightReportPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="flightReportPage.currentPageIndex"
													value="${flightReportPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach
											begin="${(flightReportPage.currentPageIndex) > 1? (flightReportPage.currentPageIndex) : 1}"
											end="${ (flightReportPage.currentPageIndex + 4) <= flightReportPage.availablePages ? (flightReportPage.currentPageIndex + 4) :  flightReportPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="flightReportPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${flightReportPage.currentPageIndex == i}">
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
											test="${(flightReportPage.currentPageIndex + 4) < flightReportPage.availablePages}">
											<td id="td"><button type="submit"
													name="flightReportPage.currentPageIndex"
													value="${flightReportPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="flightReportPage.currentPageIndex"
													value="${flightReportPage.availablePages}"
													class="btn btn-primary">Last</button></td>
										</c:if>

									</tr>
								</table>
							</div>
						</div>
						</div>
				</form>
			</div>
			</section>
	</div>
	 

	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript"  src="js/reset-form.js"></script>
	<script>
		$(document).ready(function() {
			$("#twodt2").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth:true,
				changeYear:true
			});
			$("#twodt1").datepicker({
				dateFormat : "dd-mm-yy",
					changeMonth:true,
					changeYear:true
			 
			});
			$("#twodt11").datepicker({
				dateFormat : "dd-mm-yy",
					changeMonth:true,
					changeYear:true
			 
			});
			$("#twodt21").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth:true,
				changeYear:true
			});
			$("#twodt12").datepicker({
				dateFormat : "dd-mm-yy",
					changeMonth:true,
					changeYear:true
			 
			});
			$("#twodt22").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth:true,
				changeYear:true
			});
			$("#arrivalDate1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			$("#arrivalDate2").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			
			$("#departureDate1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			$("#departureDate2").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			 
		});
	</script>
	<script type="text/javascript">/* , 'pdf', 'print'  */
		$(document).ready(
				function() {
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

				});
 
	</script>
	<script type="text/javascript">
		$(function() {
			/*  $('#row_dim').hide();  */
			$('#user').change(function() {
				//alert($('#user').val());
				if ($('#user').val() == 'ALL') {
					$('#company_form-group').hide();
				} else if ($('#user').val() == '0') {
					$('#company_form-group').show();

				} else {
					$('#company_form-group').hide();
				}
			});

			$('#companyName').change(function() {
				//alert($('#companyName').val());
				if ($('#companyName').val() == 'ALL') {
					$('#user_form-group').hide();
				} else if ($('#companyName').val() == '0') {
					$('#user_form-group').show();

				} else {
					$('#user_form-group').hide();
				}
			});
			 
			 
		});
	</script>
		<script>
	 $('spna[data-toggle="tooltip"]').tooltip({
    animated: 'fade',
    placement: 'bottom',
});
	</script>
	 
</body>
</html>