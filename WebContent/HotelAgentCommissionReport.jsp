<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html >
<html>
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
 -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>

<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>


<link rel="stylesheet" href="css/alert.css">
<%-- <script type="text/javascript">
	  $(function() {
		 
		var filterCompanyType=$("#companyType").val();
		var airline=$("#airline").val();
		var status=$("#status").val();
		 console.log("------filterCompanyType----------"+filterCompanyType);
		 console.log("------airline----------"+airline);
		 console.log("------status----------"+status);
   		document.getElementById('filterCompanyType').value =filterCompanyType;
		 document.getElementById('airlinename').value =airline;
		 document.getElementById('stat').value =status;

	 }); 
	
	
</script> --%>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Hotel Commission Report</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="row">
				<div class="col-sm-12 clearfix report-search">
					<s:if test="%{#session.Company!=null && #session.User!=null}">
					 <form class="form-inline" action="hotelFilterCommissionReport"
								method="post">
								<div class="form-group">
									<label for="exampleInputAmount">From Date</label>
									<div class="input-group">
										<input type="text" class="form-control input-sm" id="twodt1"
											placeholder="yyyy-mm-dd" value="<s:property value="hotelAgentagentTotalReportCommObj.yesterDayDate"/>"   required="required" name="yesterDayDate"
											 >
										<div class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="exampleInputAmount">To Date</label>
									<div class="input-group">
										<input type="text" class="form-control input-sm" id="twodt2"
												placeholder="yyyy-mm-dd"  value="<s:property value="hotelAgentagentTotalReportCommObj.todayDate"/>"   required="required" name="todayDate"
											 >
										<div class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</div>
									</div>
								</div>
								<div class="form-group rep-buto">
									<button type="submit" class="btn btn-primary">Filter</button>
								</div>
								<div class="form-group rep-buto">
									<s:property value="hotelAgentagentTotalReportCommObj.status"/>
								</div>
								
								
								</form>
									<s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
						
							<div class="table-responsive dash-table">
								<div class="box clearfix">
 									<table id="example" class="table table-striped table-bordered"
											cellspacing="0" width="100%">
											<thead>
												 <tr>
												<th>S.No</th>
												<th>OrderId</th>
												<!-- <th>TicketPrice</th> -->
												<th>MyMarkup</th>
												<!-- <th>WholesalerMarkup</th>
												<th>AgencyMarkup</th> -->
												<th>BookingAmount</th>
												<th>TotalCommission</th>
												<!-- <th>MyCommission</th> -->
												<th>CommissionShared</th>
												<th>MyRevenue</th>
												<th>Status</th>
												<th>BookedBy</th>
												<!-- <th>CheckInDate</th>
												<th>CheckoutDate</th> -->
												<!-- <th>BookedCurrency</th> -->
											</tr>
											 
												 
											</thead>
											<tbody>
												<s:iterator value="hotelCommissionReportList"
													status="rowCount">
													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td><s:property value="orderRef"/></td>
														<%-- <td><s:property value="ticketPrice" /></td> --%>
														<td><s:property value="markup" /></td>
														<%-- <td><s:property value="distributorMarkup" /></td>
														<td><s:property value="childMarkup" /></td> --%>
														<td><s:property value="finalPrice" /></td>
														<td><s:property value="myCommission" /></td>
														<td><s:property value="sharedCommission" /></td>
														<td><s:property value="myProfit" /></td>
														<td><s:property value="status" /></td>
														<td><s:property value="createdBy" /></td>
														<%-- <td><s:property value="checkInDate" /></td>
														<td><s:property value="checkOutDate" /></td> --%>
														<%-- <td><s:property value="curCode" /></td> --%>
													</tr>
												 </s:iterator>
 												</tbody>
												<tbody>
														<tr>
															<th></th>
															<th></th>
															<!-- <th>TotalTicketPrice</th> -->
															<th>TotalMyMarkup</th>
															<!-- <th>TotalWholesalerMarkup</th>
															<th>TotalAgencyMarkup</th> -->
															<th>TotalBookingAmount</th>
															<th>TotalMyCommission</th>
															  <th>TotalSharedCommissin</th>
															 <th> 
															 TotalRevenue
															   </th>
															  <th> 
															  </th>
															  <th> 
															  </th>
															 
														 </tr>
														<tr>
															<th></th>
															<th></th>
															<%-- <th><s:property
																value="hotelAgentagentTotalReportCommObj.totTicketPrice"/></th> --%>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyMarkup"/></th>
														<%-- <th><s:property
																value="hotelAgentagentTotalReportCommObj.totWholeSalerMarkup"/></th>
														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totAgencyMarkup"/></th> --%>
														
														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totAmountSpent"/></th>
														 
														 <th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyCommission"/></th>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totSharedCommission"/></th>
															 <th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAgentSComm" />
															</th>
															 
															<th></th>
															 <th></th>
														 </tr>
 														</tbody>
														</table>
 														</div>
								 
							</div>
							 
						</s:if>

						<s:elseif
							test="%{#session.Company.companyRole.isDistributor()}">
							 
								 <div class="table-responsive dash-table">

									<!-- testing -->

									<div class="box clearfix">
										 
												<table id="example" class="table table-striped table-bordered"
											cellspacing="0" width="100%">
											<thead>
												<tr>
												 
												<th>S.No</th>
												<th>OrderId</th>
												<!-- <th>TicketPrice</th> -->
												<th>MyMarkup</th>
												<!-- <th>WholesalerMarkup</th> -->
												<!-- <th>AgencyMarkup</th> -->
												<th>TotalAmount</th>
												<th>TotalCommission</th>
												<!-- <th>MyCommission</th> -->
												<th>CommissionShared</th>
												<s:if test="hotelAgentagentTotalReportCommObj.tdsType=='TDS'">
												<th><s:property value="hotelAgentagentTotalReportCommObj.tdsType"/>(<s:property value="hotelAgentagentTotalReportCommObj.TDS"/>%) on Commission</th>
												</s:if>
												 <th>MyRevenue</th>
												<th>Status</th>
												<th>BookedBy</th>
												<!-- <th>CheckInDate</th>
												<th>CheckoutDate</th> -->
											</tr>
													 
													<!-- <th>Agent_comm</th> -->


												 
											</thead>
											<tbody>
												<s:iterator value="hotelCommissionReportList"
													status="rowCount">
													
													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td><s:property value="orderRef"/></td>
														<%-- <td><s:property value="ticketPrice" /></td> --%>
														<td><s:property value="markup" /></td>
													<%-- 	<td><s:property value="childMarkup" /></td> --%>
														<td><s:property value="finalPrice" /></td>
														<td><s:property value="myCommission" /></td>
														<td><s:property value="sharedCommission" /></td>
														 <s:if test="hotelAgentagentTotalReportCommObj.tdsType=='TDS'">
														<td><s:property value="tdsOnCommission"/></td>
														 <td><s:property value="myProfitwithDeductTDS" /></td>
														 </s:if>
														 <s:else>
														 <td><s:property value="myProfit"/></td>
														  </s:else>
														<%--  <td><s:property value="myProfit" /></td> --%>
														<td><s:property value="status" /></td>
														<td><s:property value="createdBy" /></td>
														<%-- <td><s:property value="checkInDate" /></td>
														<td><s:property value="checkOutDate" /></td> --%>
 												</tr>

												</s:iterator>

											</tbody>
													<tbody>
														<tr>
															<th></th>
															<th></th>
															<!-- <th>TotalTicketPrice</th> -->
															<th>TotalMyMarkup</th>
															<!--  <th>TotalAgencyMarkup</th> -->
															<th>TotalBookingAmount</th>
															<th>TotalMyCommission</th>
															  <th>TotalSharedCommissin</th>
															  	<th></th>
															 <th> TotalRevenue </th>
															  <th> </th>
															  <th> </th>
															 
														 </tr>
														<tr>
															<th></th>
															<th></th>
															<%-- <th><s:property
																value="hotelAgentagentTotalReportCommObj.totTicketPrice"/></th> --%>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyMarkup"/></th>
														 
														<%-- <th><s:property
																value="hotelAgentagentTotalReportCommObj.totAgencyMarkup"/></th> --%>
														
														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totAmountSpent"/></th>
														 
														 <th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyCommission"/></th>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totSharedCommission"/></th>
																	<th></th>
															 <th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAgentSComm" />
															</th>
															 
															<th></th>
															 <th></th>
														 </tr>
 														</tbody>
										</table>


									</div>
									<!-- /.box -->

								</div>
								<!-- table-responsive -->
							 
						</s:elseif>

						<s:elseif
							test="%{#session.Company.companyRole.isAgent()}">
							 
								<div class="table-responsive dash-table">

									<!-- testing -->

									<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered"
											cellspacing="0" width="100%">
											<thead>
											<tr>
											<th>S.No</th>
												<th>OrderId</th>
												<!-- <th>TicketPrice</th> -->
												<th>MyMarkup</th>
												 <th>TotalAmount</th>
												<th>TotalCommission</th>
												<s:if test="hotelAgentagentTotalReportCommObj.tdsType=='TDS'">
												<th><s:property value="hotelAgentagentTotalReportCommObj.tdsType"/>(<s:property value="hotelAgentagentTotalReportCommObj.TDS"/>%) on Commission</th>
												</s:if>
												<th>Status</th>
												<th>BookedBy</th>
												<!-- <th>CheckInDate</th>
												<th>CheckoutDate</th> -->
											
											 </tr>
											</thead>
											<tbody>
												<s:iterator value="hotelCommissionReportList"
													status="rowCount">
														<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td><s:property value="orderRef" /></td>
														<%-- <td><s:property value="ticketPrice" /></td> --%>
														<td><s:property value="markup" /></td>
														<td><s:property value="finalPrice" /></td>
														 <td><s:property value="myCommission" /> </td>
														   <s:if test="hotelAgentagentTotalReportCommObj.tdsType=='TDS'">
														<td><s:property value="tdsOnCommission"/></td>
														 <td><s:property value="myProfitwithDeductTDS" /></td>
														 </s:if>
														 <s:else>
														 <td><s:property value="myProfit"/></td>
														  </s:else>
														 <td><s:property value="status" /></td>
														<td><s:property value="createdBy" /></td>
														<%--  <td><s:property value="checkInDate" /></td>
														<td><s:property value="checkOutDate" /></td> --%>
														 
													</tr>

												</s:iterator>

											</tbody>
												<tbody>
														<tr>
															<th></th>
															<th></th>
														<!-- 	<th>TotalTicketPrice</th> -->
															<th>TotalMyMarkup</th>
															<th>TotalBookingAmount</th>
															<th>TotalMyCommission</th>
															<th></th>
															 
															 <!--  <th>TotalSharedCommissin</th> -->
															 <th> TotalRevenue </th>
															   
															  <th> </th>
															 
														 </tr>
														<tr>
															<th></th>
															<th></th>
															<%-- <th><s:property
																value="hotelAgentagentTotalReportCommObj.totTicketPrice"/></th> --%>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyMarkup"/></th>
														  <th><s:property
																value="hotelAgentagentTotalReportCommObj.totAmountSpent"/></th>
														 
														 <th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyCommission"/></th>
																 
																<th></th>
 														<%-- <th><s:property
																value="hotelAgentagentTotalReportCommObj.totSharedCommission"/></th> --%>
															 <th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAgentSComm" />
															</th>
															 
															<th></th>
															 <th></th>
														 </tr>
 														</tbody>

										</table>


									</div>
									<!-- /.box -->

								</div>
								<!-- table-responsive -->
							 
						</s:elseif>
 					<%-- <s:elseif
					test="%{(#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms())|| 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isCms()) || 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder())|| (#session.User.userrole_id.isAdmin()) || #session.Company.companyRole.isCorporate()}">
						
						
						<s:if
							test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isDistributor()}">
							 
								 <div class="table-responsive dash-table">

									<!-- testing -->

									<div class="box clearfix">
										 
												<table id="example" class="table table-striped table-bordered"
											cellspacing="0" width="100%">
											<thead>
												<tr>
												 
												<th>S.No</th>
												<th>OrderId</th>
												<th>TicketPrice</th>
												<th>MyMarkup</th>
												<!-- <th>WholesalerMarkup</th> -->
												<th>AgencyMarkup</th>
												<th>TotalAmount</th>
												<th>TotalCommission</th>
												<!-- <th>MyCommission</th> -->
												<th>CommissionShared</th>
												<th><s:property value="hotelAgentagentTotalReportCommObj.tdsType"/>(<s:property value="hotelAgentagentTotalReportCommObj.TDS"/>%) on Commission</th>
												
												 <th>MyRevenue</th>
												<th>Status</th>
												<th>BookedBy</th>
												<!-- <th>CheckInDate</th>
												<th>CheckoutDate</th> -->
											</tr>
													 
													<!-- <th>Agent_comm</th> -->


												 
											</thead>
											<tbody>
												<s:iterator value="hotelCommissionReportList"
													status="rowCount">
													
													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td><s:property value="orderRef"/></td>
														<td><s:property value="ticketPrice" /></td>
														<td><s:property value="markup" /></td>
														<td><s:property value="childMarkup" /></td>
														<td><s:property value="finalPrice" /></td>
														<td><s:property value="myCommission" /></td>
														<td><s:property value="sharedCommission" /></td>
														  <td><s:property value="tdsOnCommission"/></td>
														  <td><s:property value="myProfitwithDeductTDS" /></td>
														  
														 <td><s:property value="myProfit" /></td>
														<td><s:property value="status" /></td>
														<td><s:property value="createdBy" /></td>
														<td><s:property value="checkInDate" /></td>
														<td><s:property value="checkOutDate" /></td>
 												</tr>

												</s:iterator>

											</tbody>
													<tbody>
														<tr>
															<th></th>
															<th></th>
															<th>TotalTicketPrice</th>
															<th>TotalMyMarkup</th>
															 <th>TotalAgencyMarkup</th>
															<th>TotalBookingAmount</th>
															<th>TotalMyCommission</th>
															  <th>TotalSharedCommissin</th>
															  	<th></th>
															 <th> TotalRevenue </th>
															  <th> </th>
															  <th> </th>
															 
														 </tr>
														<tr>
															<th></th>
															<th></th>
															<th><s:property
																value="hotelAgentagentTotalReportCommObj.totTicketPrice"/></th>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyMarkup"/></th>
														 
														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totAgencyMarkup"/></th>
														
														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totAmountSpent"/></th>
														 
														 <th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyCommission"/></th>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totSharedCommission"/></th>
																	<th></th>
															 <th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAgentSComm" />
															</th>
															 
															<th></th>
															 <th></th>
														 </tr>
 														</tbody>
										</table>


									</div>
									<!-- /.box -->

								</div>
								<!-- table-responsive -->
							 
						</s:if>
						
						<s:elseif
							test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isAgent()}">
							 
								<div class="table-responsive dash-table">

									<!-- testing -->

									<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered"
											cellspacing="0" width="100%">
											<thead>
											<tr>
											<th>S.No</th>
												<th>OrderId</th>
												<th>TicketPrice</th>
												<th>MyMarkup</th>
												 <th>TotalAmount</th>
												<th>TotalCommission</th>
												<th><s:property value="hotelAgentagentTotalReportCommObj.tdsType"/>(<s:property value="hotelAgentagentTotalReportCommObj.TDS"/>%) on Commission</th>
												  <th>MyRevenue</th>
												<th>Status</th>
												<th>BookedBy</th>
												<!-- <th>CheckInDate</th>
												<th>CheckoutDate</th> -->
											
											 </tr>
											</thead>
											<tbody>
												<s:iterator value="hotelCommissionReportList"
													status="rowCount">
														<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td><s:property value="orderRef" /></td>
														<td><s:property value="ticketPrice" /></td>
														<td><s:property value="markup" /></td>
														<td><s:property value="finalPrice" /></td>
														 <td><s:property value="myCommission" /> </td>
														  <td><s:property value="tdsOnCommission"/></td>
														 
														
														 <td><s:property value="myProfitwithDeductTDS" /></td>  <td><s:property value="myProfit" /></td>
														 <td><s:property value="status" /></td>
														<td><s:property value="createdBy" /></td>
														 <td><s:property value="checkInDate" /></td>
														<td><s:property value="checkOutDate" /></td>
														 
													</tr>

												</s:iterator>

											</tbody>
												<tbody>
														<tr>
															<th></th>
															<th></th>
															<th>TotalTicketPrice</th>
															<th>TotalMyMarkup</th>
															<th>TotalBookingAmount</th>
															<th>TotalMyCommission</th>
															<th></th>
															 
															 <!--  <th>TotalSharedCommissin</th> -->
															 <th> TotalRevenue </th>
															   
															  <th> </th>
															 
														 </tr>
														<tr>
															<th></th>
															<th></th>
															<th><s:property
																value="hotelAgentagentTotalReportCommObj.totTicketPrice"/></th>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyMarkup"/></th>
														  <th><s:property
																value="hotelAgentagentTotalReportCommObj.totAmountSpent"/></th>
														 
														 <th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyCommission"/></th>
																 
																<th></th>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totSharedCommission"/></th>
															 <th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAgentSComm" />
															</th>
															 
															<th></th>
															 <th></th>
														 </tr>
 														</tbody>
 														</table>
 										</div>
									<!-- /.box -->

								</div>
								<!-- table-responsive -->
							 
						</s:elseif>
						<s:else>
							<div class="table-responsive dash-table">
								<div class="box clearfix">
 									<table id="example" class="table table-striped table-bordered"
											cellspacing="0" width="100%">
											<thead>
												 <tr>
												<th>S.No</th>
												<th>OrderId</th>
												<th>TicketPrice</th>
												<th>MyMarkup</th>
												<th>WholesalerMarkup</th>
												<th>AgencyMarkup</th>
												<th>BookingAmount</th>
												<th>TotalCommission</th>
												<!-- <th>MyCommission</th> -->
												<th>CommissionShared</th>
												<th>MyRevenue</th>
												<th>Status</th>
												<th>BookedBy</th>
												<!-- <th>CheckInDate</th>
												<th>CheckoutDate</th> -->
												<!-- <th>BookedCurrency</th> -->
											</tr>
											 
												 
											</thead>
											<tbody>
												<s:iterator value="hotelCommissionReportList"
													status="rowCount">
													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td><s:property value="orderRef" /></td>
														<td><s:property value="ticketPrice" /></td>
														<td><s:property value="markup" /></td>
														<td><s:property value="distributorMarkup" /></td>
														<td><s:property value="childMarkup" /></td>
														<td><s:property value="finalPrice" /></td>
														<td><s:property value="myCommission" /></td>
														<td><s:property value="sharedCommission" /></td>
														<td><s:property value="myProfit" /></td>
														<td><s:property value="status" /></td>
														<td><s:property value="createdBy" /></td>
														<td><s:property value="checkInDate" /></td>
														<td><s:property value="checkOutDate" /></td>
														<td><s:property value="curCode" /></td>
													</tr>
												 </s:iterator>
 												</tbody>
												<tbody>
														<tr>
															<th></th>
															<th></th>
															<th>TotalTicketPrice</th>
															<th>TotalMyMarkup</th>
															<th>TotalWholesalerMarkup</th>
															<th>TotalAgencyMarkup</th>
															<th>TotalBookingAmount</th>
															<th>TotalMyCommission</th>
															  <th>TotalSharedCommissin</th>
															 <th> 
															 TotalRevenue
															   </th>
															  <th> 
															  </th>
															  <th> 
															  </th>
															 
														 </tr>
														<tr>
															<th></th>
															<th></th>
															<th><s:property
																value="hotelAgentagentTotalReportCommObj.totTicketPrice"/></th>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyMarkup"/></th>
														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totWholeSalerMarkup"/></th>
														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totAgencyMarkup"/></th>
														
														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totAmountSpent"/></th>
														 
														 <th><s:property
																value="hotelAgentagentTotalReportCommObj.totMyCommission"/></th>
 														<th><s:property
																value="hotelAgentagentTotalReportCommObj.totSharedCommission"/></th>
															 <th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAgentSComm" />
															</th>
															 
															<th></th>
															 <th></th>
														 </tr>
 														</tbody>
														</table>
 														</div>
								 
													</div>
												 </s:else>
											 </s:elseif>

								<s:elseif
								test="%{#session.User.userrole_id.isReports() && #session.User.userrole_id.isOrder()}">
								<s:if
								test="%{#session.User.Companyid==#session.Company.companyid}">
								 
									<div class="table-responsive dash-table">
 
										<div class="box clearfix">
												<table id="example" class="table table-striped table-bordered"
											cellspacing="0" width="100%">
											<thead>
												<tr>
													<th>S.No</th>
													<!-- <th>Order id</th> -->
													<th>OrderId</th>
													<th>CheckIn</th>
												  	<th>HotelName</th>
													<th>Percentage</th>
													<th>Commission</th>
													<th>FinalAmount</th>
												 
													<th>BookedBy</th>
													 
													<!-- <th>Agent_comm</th> -->


												</tr>
											</thead>
											<tbody>
												<s:iterator value="hotelCommissionReportList"
													status="rowCount">
													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td><s:property value="orderRef" /></td>
														<td><s:property value="checkInDate" /></td>
														 <td><s:property value="hotelName" /></td>
														<td><s:property value="commissionPercentage" /></td>
														<td><s:property value="agentCommByRate" /></td>
														<td><s:property value="finalPrice"/></td>
														 
														<td><s:property value="createdBy" /></td>
														 
													</tr>

												</s:iterator>

											</tbody>
												<tbody>
														<tr>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															 
														  	<th>Total</th>

															<th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAgentSComm" />
															</th>
															<th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAmountSpent" /></th>
															<th></th>
															 
														</tr>


													</tbody>
										</table>
										</div>
										<!-- /.box -->
									</div>
									<!-- table-responsive -->
								 
							</s:if>
						</s:elseif>
						<s:elseif test="%{#session.User.userrole_id.isReports()}">
							<s:if
								test="%{#session.User.Companyid==#session.Company.companyid}">
								 
								 
									<!-- </form> -->
									<div class="table-responsive dash-table">

									 

										<div class="box clearfix">
											 
												<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>S.No</th>
												<th>OrderId</th>
												<th>Percentage</th>
												<th>Commission</th>
												<th>FinalAmount</th>
												<th>BookedBy</th>
												<th>BookedDate</th>
												<th>BookedCurrency</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="%{#session.companyCommissionReportList.size>0}">
												<s:iterator value="#session.companyCommissionReportList"
													status="rowCount">

													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td><s:property value="orderRef"/></td>
														<td><s:property value="commissionPercentage" /></td>
														<td><s:property value="agentCommByRate" /></td>
														<td><s:property value="finalPrice" /></td>
														<td><s:property value="createdBy" /></td>
														<td><s:property value="bookingDate" /></td>
														<td><s:property value="curCode" /></td>
													</tr>
												</s:iterator>
												<tbody>
													<tr>
														<th></th>
														<th></th>
														<th>Total</th>

														<th><s:property
																value="%{#session.companyCommissionTotalObj.totAgentSComm}" />
														</th>
														<th><s:property
																value="%{#session.companyCommissionTotalObj.totAmountSpent}" /></th>
														<th></th>
														 <th></th>
														 <th></th>
													</tr>


												</tbody>
											</s:if>
										 

										</tbody>
									</table><table id="example" class="table table-striped table-bordered"
											cellspacing="0" width="100%">
											<thead>
												<tr>
													<th>S.No</th>
													<!-- <th>Order id</th> -->
													<th>OrderId</th>
													<th>CheckIn</th>
												  	<th>HotelName</th>
													<th>Percentage</th>
													<th>Commission</th>
													<th>FinalAmount</th>
												 
													<th>BookedBy</th>
													 
													<!-- <th>Agent_comm</th> -->


												</tr>
											</thead>
											<tbody>
												<s:iterator value="hotelCommissionReportList"
													status="rowCount">
													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td><s:property value="orderRef" /></td>
														<td><s:property value="checkInDate" /></td>
														 <td><s:property value="hotelName" /></td>
														<td><s:property value="commissionPercentage" /></td>
														<td><s:property value="agentCommByRate" /></td>
														<td><s:property value="finalPrice"/></td>
														 
														<td><s:property value="createdBy" /></td>
														 
													</tr>

												</s:iterator>

											</tbody>
												<tbody>
														<tr>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															 
														  	<th>Total</th>

															<th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAgentSComm" />
															</th>
															<th><s:property
																	value="hotelAgentagentTotalReportCommObj.totAmountSpent" /></th>
															<th></th>
															 
														</tr>


													</tbody>
										</table>
										</div>
										<!-- /.box -->
									</div>
									<!-- table-responsive -->
								 
							</s:if>
						</s:elseif> --%>
					</s:if>
 
				</div>

			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->

	</div>
	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script>
		$(document).ready(function() {
			$("#twodt2").datepicker({
				dateFormat : "yy-mm-dd",
				changeMonth : true,
				changeYear : true
			});
			$("#twodt1").datepicker({
				dateFormat : "yy-mm-dd",
				changeMonth : true,
				changeYear : true
			});
		});
	</script>
	<script type="text/javascript">/* , 'pdf', 'print'  */
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						"paging" : true,
						"info" : true,
						"searching" : true,
						"ordering" : true,
						"search" : {
							"regex" : true,
						},
						/* lengthChange : true,
						"pagingType" : "full_numbers", */
						"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],
						buttons : [ 'excel']
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});

		/*  $(function () {
		 	$('#example').DataTable({
		    	 "paging": true,
		         "lengthChange": true,
		        "searching": true,
		        "ordering": true,  
		           "info": true,
		         "autoWidth": false,  
		        "search": {
		      	    "regex": true,
		      	  }, 
		      	 
		      "pagingType": "full_numbers",
		      "lengthMenu": [10, 25, 50, 75, 100, 500 ],
		     
		      
		     });  
		  
		   });   */
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

	<!-- 
 
  $(document).ready(function() 
    	 { 
    		 $("#twodpd1").datepicker({
    			 dateFormat: "yy-mm-dd"  
    			/*  changeMonth: true,
    			 changeYear: true */
    		 }); 
    		 }); -->

</body>
</html>