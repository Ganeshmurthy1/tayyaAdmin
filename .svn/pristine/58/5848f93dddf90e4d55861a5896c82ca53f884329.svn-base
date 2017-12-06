<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib prefix="s" uri="/struts-tags"%>
      <link rel="stylesheet" href="css/alert.css">
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
       <link href="css/dashboard-search.css" rel="stylesheet" type="text/css" />
     <!--
@author is : Basha -04-08-2017>
-->  
<style>

 #search{
	color:red;
    text-align:center;

}
.items label {
	margin-top: 5px;
}
 #example td 
{
padding:0px;
height:10px;
font-weight:normal;
}
#example1 td
{
padding:0px;
height:10px;
font-weight:normal;
} 
</style>

 
 <div class="content-wrapper" >
        <!-- Content Header (Page header) -->
        <section class="content-header">
			<h3 >Search Result Page</h3>
			<%-- <small>Search Result Page</small> --%>
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
        <section class="content">
         <form action="dashboardsearchmanagernew" method="post">
          <div class="container">
	<div class="row">
           <div id="custom-search-input">
                            <div class="input-group col-md-11">
                                <input type="text" id="search" class="search-query form-control" name="searchingvalue" value="" minlength="3" maxlength="25" placeholder="Search like PNR/OrderId/Invoice No" required="required" autocomplete="off"/>
                                <span class="input-group-btn">
                                    <button class="btn btn-danger"  type="submit">
                                        <span class=" glyphicon glyphicon-search"></span>
                                    </button>
                                </span>
                            </div>
                        </div>
	</div>
</div>
</form>

			<!-- Small boxes (Stat box) -->
			<!-- searchCompanyReportsList -->
			 <form action="dashboardsearchmanager" method="post">
			<div class="row">
			<!-- <input type="button" id="downloadExcel" value="downloadExcel"> -->
				<input type="hidden" value="<s:property value="%{#session.Company.company_userid}"/>" id="companyUserId">
				<input type="hidden" value="${searchingvalue}" id="searchingvalue" name="searchingvalue">
				<input type="hidden" value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden" value="<s:property value="%{#session.User.company_userid}"/>" id="user_id">
				<input type="hidden" value="<s:property value="dashBoardSearchCommonVirtualObject"/>" id="mylist">
					
					<s:if test="dashBoardSearchCommonVirtualObject.size()>0">
					<h3 align="center">Reports </h3>
					<div class="col-sm-12 clearfix report-search" >
						<div class="table-responsive dash-table">
							<!-- testing -->
							<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered" >
									<thead>
										<tr>
											<th>S.No</th>
											<th>Service Type</th>
											<th>Booking CreatedDate</th>
											<th>PaxName/GuestName</th>
											<th>Hotel Name</th>
											<th>PNR</th>
											<th>Airline</th>
											<th>Origin</th>
											<th>Destination</th>
											<th>BookingDate</th>
											<th>CheckIn Date</th>
											<th>CheckOut Date</th>
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
											<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<th class="noExport">Download Voucher</th>
											<th class="noExport">Voucher Email</th>											
											</s:if>
											<s:elseif test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isCorporate()}">
											<th class="noExport">DownloadVoucher/Invoice</th>
											<th class="noExport">Voucher Email</th>
											<th class="noExport">Invoice Email</th>
											</s:elseif>
											
											 <s:else>
											 <th class="noExport">Download Voucher</th>
											<th class="noExport">Voucher Email</th>	
											 </s:else>
											  <th class="noExport">Action</th>
											 <s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false &&#session.Company.companyRole.Corporate==false)}">
													<th>SupplierName</th>
													</s:if>
											<!--  <th>ARR</th> -->
										</tr>
									</thead>
									<tbody>
										<s:iterator value="dashBoardSearchCommonVirtualObject" status="rowCount">
											<tr>
												<td><s:property value="%{#rowCount.count}"/></td>
												<td><s:property value="servicetype" /></td>
												<td><s:date name="createdAt" format="dd/MM/yyyy"></s:date></td>
												<td><s:property value="title" />&nbsp;&nbsp;&nbsp;<s:property value="firstName" />
												&nbsp;&nbsp;&nbsp; <s:property value="lastName" /></td>
												<s:if test="servicetype == 'Hotel'">
												<td><s:property value="hotelName" /></td>
												</s:if>
												<s:else>
												<td>NA</td>
												</s:else>
												<s:if test="servicetype == 'Flight' ">
												<td><s:property value="pnr" /></td>
												<td><s:property value="airline" /></td>
												<td><s:property value="origin" /></td>
												<td><s:property value="destination" /></td>
												</s:if>
												<s:else>
												<td>NA</td>
												<td>NA</td>
												<td>NA</td>
												<td>NA</td>
												</s:else>
												<td><s:property value="bookingDate" /></td>
												<s:if test="servicetype == 'Hotel' ">
												<td><s:property value="checkInDate" /></td>
												<td><s:property value="checkOutDate" /></td>
												</s:if>
												<s:else>
												<td>NA</td>
												<td>NA</td>
												</s:else>
												<s:if test="servicetype == 'Flight' || servicetype == 'Car' || servicetype == 'Bus' || servicetype == 'Train' || servicetype == 'Visa' || servicetype == 'Insurance' || servicetype == 'Miscellaneous'">
												<td><s:property value="departureDate"/></td>
												</s:if>
												<s:else>
												<td>NA</td>
												</s:else>
												<s:if test="%{#session.Company.companyRole.isCorporate()==false}">
												<td><s:property value="netAmnt"/></td>
												<td><s:property value="markUp"/></td>
												</s:if>
												<td><s:property value="finalPrice" />  	
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
													   <s:if test="servicetype=='Flight'">
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
													</s:if>
													
													<s:elseif test="servicetype=='Hotel'">
												<td>
															<p data-placement="top">
																<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																	<a href=getHotelOfflineVoucher?hotelOrderId=${id}
																		class="btn btn-success btn-xs" data-toggle="modal">
																		Voucher </a>
																</s:if>
																<s:else>
																NA
																</s:else>
															<s:if test="%{(#session.Company.companyRole.isSuperUser()==true) || #session.Company.companyRole.isCorporate()}">
																	<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																		<a href=getHotelOfflineInvoice?hotelOrderId=${id}
																			class="btn btn-success btn-xs" data-toggle="modal">
																			Invoice </a>
																	</s:if>
																	<s:else>
 															NA
 															</s:else>
																
															</s:if></p>
														</td>
														
														</s:elseif>
														
														
														<s:elseif test="servicetype=='Car'">
														<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>

															<p data-placement="top">

																<a href=getCarOfflineInvoice?orderId=${id}
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Invoice </a>
															</p>

														</s:else></td>
														</s:elseif>
														
														<s:elseif test="servicetype=='Bus'">
														<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>


															<p data-placement="top">

																<a href=getBusOfflineInvoice?orderId=${id}
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Invoice </a> <a
																	href="getBusOfflineVoucher?orderId=<s:property value="id"/>"
																	class="btn btn-success btn-xs " data-toggle="modal">
																	voucher </a>
															</p>
														</s:else></td>
														</s:elseif>
														
														<s:elseif test="servicetype=='Train'">
														<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>


															<p data-placement="top">

																<a href=getTrainOfflineInvoice?orderId=${id}
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Invoice </a>
															</p>
														</s:else></td>
														</s:elseif>
														
														<s:elseif test="servicetype=='Visa'">
														<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>


															<p data-placement="top">

																<a href=getVisaOfflineInvoice?orderId=${id}
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Invoice </a>
															</p>
														</s:else></td>
														</s:elseif>
														
														<s:elseif test="servicetype=='Insurance'">
														<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>


															<p data-placement="top">

																<a href=getInsuranceOfflineInvoice?orderId=${id}
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Invoice </a>
															</p>

														</s:else></td>
														</s:elseif>
														
														<s:elseif test="servicetype=='Miscellaneous'">
														<td>	
														
														<s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>


															<p data-placement="top" >
 															
															<a href=goMiscellaneousInvoice?orderId=${id}
															class="btn btn-success btn-xs"   data-toggle="modal">
															Invoice </a>														
 															</p>

														</s:else>
														
														 	
														 </td>		
														
														</s:elseif>
														
													 
														<s:if test="servicetype=='Flight'">
													  
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
													 </s:if>
													 <s:elseif test="servicetype=='Hotel'">
													 		
														<td>
															<p data-placement="top">
																<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																	<a href="javascript:history.void(0);"
																		onclick="sendHotelVoucherToCustomerEmail('${orderRef}','voucher');"
																		class="btn btn-success btn-xs" data-toggle="modal">
																		Send Voucher To Email </a>
																</s:if>
																<s:else>
 															NA
 															</s:else>
															</p>
														</td>
														<s:if test="%{(#session.Company.companyRole.isSuperUser()==true) || #session.Company.companyRole.isCorporate()}">
													<td><p data-placement="top" >
													  <s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
													  <a href="javascript:history.void(0);"
															class="btn btn-success btn-xs"  onclick="sendHotelVoucherToCustomerEmail('${orderRef}','invoice');"      data-toggle="modal">
															Send Invoice To Email</a>
													  </s:if>
													  <s:else>
													  NIL													  
													  </s:else>										  
														
													</p></td>
													 </s:if>
													 
													  
													 </s:elseif>
													 
													  <s:elseif test="servicetype=='Car'">
													  
													  <td>N/A
															<%-- <p data-placement="top">
																<s:if test="statusAction=='Confirmed' || statusAction=='Cancelled'">
																	<a href="javascript:history.void(0);"
																		onclick="sendHotelVoucherToCustomerEmail('${orderRef}','voucher');"
																		class="btn btn-success btn-xs" data-toggle="modal">
																		Send Voucher To Email </a>
																</s:if>
																<s:else>
 															NA
 															</s:else>
															</p> --%>
														</td>
													  <td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>
															<p data-placement="top">

																<a href="javascript:history.void(0);"
																	onclick="sendCarInvoiceToCustomerEmail('${orderId}');"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Send Invoice To Email </a>


															</p>
														</s:else></td>
													 
													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Bus'">
													  <td> N/A</td>
													  
													  
													  
													  <td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>
															<p data-placement="top">

																<a href="javascript:history.void(0);"
																	onclick="sendBusInvoiceToCustomerEmail('${orderId}');"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Send Invoice To Email </a>


															</p>
														</s:else></td>
													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Train'">
													  	<td> N/A</td>
													<td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>
															<p data-placement="top">

																<a href="javascript:history.void(0);"
																	onclick="sendTrainInvoiceToCustomerEmail('${orderId}');"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Send Invoice To Email </a>


															</p>
														</s:else></td>

													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Visa'">
													  <td> N/A</td>
													  
													  
													  <td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>
															<p data-placement="top">

																<a href="javascript:history.void(0);"
																	onclick="sendVisaInvoiceToCustomerEmail('${orderId}');"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Send Invoice To Email </a>

															</p>
														</s:else></td>
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Insurance'">
													  <td> N/A</td>
													  <td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>
															<p data-placement="top">

																<a href="javascript:history.void(0);"
																	onclick="sendInsuranceInvoiceToCustomerEmail('${orderId}');"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	Send Invoice To Email </a>


															</p>
														</s:else></td>
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Miscellaneous'">
													   <td> N/A</td>
													  <td><s:if
															test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
														N/A
														</s:if> <s:else>
 															<p data-placement="top" >
 															
 															<a
															href="javascript:history.void(0);" onclick="sendMiscellaneousInvoiceToCustomerEmail('${orderId}');"
															class="btn btn-success btn-xs"   data-toggle="modal">
															Send Invoice To Email </a>
 															
 																											
 													</p>														 
														 </s:else></td>
													  </s:elseif>
													  
													 <s:if test="servicetype=='Flight'">
													 
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
											  <span class="detailstiptext" ><s:property value="apiResponseMessage" /></span>  
															 
													</p>

												</td>
												 </s:if>
													<s:elseif test="servicetype=='Hotel'">
													  <td>
															<p data-placement="top" class="details">
																<a
																	href="showReportDetails?selectedRowIndex=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">Details</a>
																<s:if
																	test="%{#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false}">
																	<s:if test="apiResponseMessage!=null">
																		<span class="detailstiptext"><s:property
																				value="apiResponseMessage" /></span>
																	</s:if>
																	<s:else>
																		<span class="detailstiptext">No Response</span>
																	</s:else>
																</s:if>
																<s:else>
																	<s:if
																		test="apiResponseMessage!=null && apiResponseMessage=='Confirmed'">
																		<span class="detailstiptext"><s:property
																				value="apiResponseMessage" /></span>
																	</s:if>
																	<s:else>
																		<span class="detailstiptext">Please Contact
																			Tayyarah</span>
																	</s:else>
																</s:else>
																<s:if test="apiResponseMessage!=null}">
															<span class="detailstiptext" ><s:property value="apiResponseMessage" /></span>  
															</s:if>
															<s:else>
															<span class="detailstiptext">No Response</span>  
															</s:else>

															</p>
														</td>
													 
													 </s:elseif> 
													 
													 <s:elseif test="servicetype=='Car'">
													  <td>
														<p data-placement="top" class="details">
															<a
																href="showPassengerDetailsOfCarReports?id=<s:property value="id"/>"
																class="btn btn-success btn-xs " data-toggle="modal">
																Details </a>

														</p>

													</td>
													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Bus'">
													  <td>
														<p data-placement="top" class="details">
															<a
																href="showPassengerDetailsOfBusReports?id=<s:property value="id"/>"
																class="btn btn-success btn-xs " data-toggle="modal">
																Details </a>

														</p>

													</td>
													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Train'">
													  <td>
														<p data-placement="top" class="details">
															<a
																href="showPassengerDetailsOfTrainReports?id=<s:property value="id"/>"
																class="btn btn-success btn-xs " data-toggle="modal">
																Details </a>

														</p>

													</td>
													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Visa'">
													  <td>
														<p data-placement="top" class="details">
															<a
																href="showPassengerDetailsOfVisaReports?id=<s:property value="id"/>"
																class="btn btn-success btn-xs " data-toggle="modal">
																Details </a>

														</p>

													</td>
													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Insurance'">
													  
													  <td>
														<p data-placement="top" class="details">
															<a
																href="showPassengerDetailsOfInsuranceReports?id=<s:property value="id"/>"
																class="btn btn-success btn-xs " data-toggle="modal">
																Details </a>

														</p>

													</td>
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Miscellaneous'">
													  <td>
													<p data-placement="top" class="details">
														<a
															href="showPassengerDetailsOfMiscellaneousReports?id=<s:property value="id"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Details </a>

													</p>

												</td>
													  
													  </s:elseif>
													  
											 		<s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false && #session.Company.companyRole.isCorporate()==false )}">
													<s:if test="apiProvider != null && apiProvider != '' ">
													<td><s:property value="apiProvider"/></td>
													</s:if>
													<s:else> <td>NA</td></s:else>
													</s:if>

											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
						</div>
					
					
					
					<div>
					<h3 align="center">Orders</h3>
					<div class="table-responsive dash-table">
							<div class="box clearfix">
								<table id="example1" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Service Type</th>
											<th>Customer Name</th>
											<th>Booking Created Date</th>
											<th>OrderId</th>
											<th>Invoice No</th>
											<th>PNR</th>
											<th>Booking Date</th>
											<th>Check In Date</th>
											<th>Check Out Date</th>
											<th>Travel Date</th>
											<th>Airline</th>
											<th>Route</th>
											<th>Hotel Name</th>
											<th>Status</th>
											<th>Total</th>
											<th>BookedBy</th>
											 <th>Supplier Name</th>
											<th>Action</th>

										</tr>
									</thead>
									<tbody>
										<%-- <s:if test="%{#session.reportFilter_list.size>0}"> --%>

										<s:iterator value="dashBoardSearchCommonVirtualObject" status="rowCount">
											<s:if test="status == 'Confirmed' || status == 'Cancelled'">
											<tr>
												<td><s:property value="%{#rowCount.count}" /></td>
												<td><s:property value="servicetype" /></td>
												<td><s:property value="title" />&nbsp;&nbsp;&nbsp;<s:property value="firstName" />
												&nbsp;&nbsp;&nbsp; <s:property value="lastName" /></td>
												<td><s:date name="createdAt" format="dd/MM/yyyy"></s:date></td>
												<td><s:property value="orderId" /></td>
												<s:if test="%{#session.Company.companyRole.isDistributor()==false || #session.User.userrole_id.isAgent() ==false}">
													<td><s:property value="invoiceNo" /></td>
													</s:if>
													<s:else>
													N/A</s:else>
												<s:if test="servicetype=='Flight'">
												<td><s:property value="pnr" /></td>
												</s:if>
												<s:else>
												<td>NA</td>
												</s:else>
												<td><s:property value="bookingDate" /></td>
												<s:if test="servicetype=='Hotel'">
												<td><s:property value="checkInDate" /></td>
												<td><s:property value="checkOutDate" /></td>
												</s:if>
												<s:else>
												<td>NA</td>
												<td>NA</td>
												</s:else>
												<s:if test="servicetype!='Hotel'">
												<td><s:property value="departureDate" /></td>
												</s:if>
												<s:else>
												<td>NA</td>
												</s:else>
												
												<s:if test="servicetype=='Flight'">
												<td><s:property value="airline" /></td>
												<td><s:property value="route" /></td>
												</s:if>
												<s:else>
												<td>NA</td>
												<td>NA</td>
												</s:else>
												<s:if test="servicetype=='Hotel'">
												<td><s:property value="hotelName" /></td>
												</s:if>
												<s:else>
												<td>NA</td>
												</s:else>
												<td><s:property value="status" /></td>
												
									
												<s:if test="%{#session.User.userrole_id.isSuperuser()||#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
													<td><s:property value="basePrice" /> 
													<s:property value="curCode" /></td>
												</s:if>
												<s:else>
													<td><s:property value="finalPrice" /> 
													<s:property value="curCode" /></td>
												</s:else>
												<td><s:property value="createdBy" /></td>
												<s:if test="supplierName != null">
												<td><s:property value="supplierName" /></td>
												</s:if>
												<s:else><td>NA</td></s:else>
												
													<s:if test="servicetype=='Flight'">
												<td>
													<p data-placement="top">
													 <a
															href="showPassengerOrderDetails?id=<s:property value="id"/>&orderId=<s:property value="orderId"/>&companyId=<s:property value="companyId"/>"
															class="btn btn-success btn-xs" data-toggle="modal">
															Edit </a>
													 
														<s:if
															test="OrderRequested==true && CreditNoteIssued==false">
															<i title="Request" class="fa fa-check-circle red">Order
																Request</i>
														</s:if>
														<s:if test="CreditNoteIssued==true && OrderUpdated==true">
															<i title="Request" class="fa fa-check-circle success">CreditNote
																Issued</i>
														</s:if>
													</p>
												</td>
												</s:if>
												<s:elseif test="servicetype=='Hotel'">
												<td>
											<p data-placement="top" title="edit">
												<a
													href="showGuestDetails?id=<s:property value="id"/>&orderRef=<s:property value="orderRef"/>&companyId=<s:property value="companyId"/>"
													class="btn btn-success btn-xs" data-toggle="modal">edit</a>

												<s:if test="orderRequested==true && CreditNoteIssued==false">
													<i title="Request" class="fa fa-check-circle red">Order
														Request</i>
												</s:if>
												<s:if test="CreditNoteIssued==true && OrderUpdated==true">
													<i title="Request" class="fa fa-check-circle success">CreditNote
														Issued</i>
												</s:if>
											</p>
										</td>
												</s:elseif>
												
												
													<s:elseif test="servicetype=='Car'">
													  <td>
													<p data-placement="top" title="edit">
														<a
															href="showPassengerDetailsOfCarOrders?id=<s:property value="id"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Edit </a>
														<%-- 	&orderId=<s:property value="orderId"/>&companyId=<s:property value="companyId"/> --%>
														<s:if
															test="OrderRequested==true && CreditNoteIssued==false">
															<i title="Request" class="fa fa-check-circle red">Order
																Request</i>
														</s:if>
														<s:if test="CreditNoteIssued==true && OrderUpdated==true">
															<i title="Request" class="fa fa-check-circle success">CreditNote
																Issued</i>
														</s:if>
													</p>

												</td>
													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Bus'">
													  
													  <td>
													<p data-placement="top" class="edit">
														<a
															href="showPassengerDetailsOfBusOrders?id=<s:property value="id"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Edit </a>
														<%-- 	&orderId=<s:property value="orderId"/>&companyId=<s:property value="companyId"/> --%>
														<s:if
															test="OrderRequested==true && CreditNoteIssued==false">
															<i title="Request" class="fa fa-check-circle red">Order
																Request</i>
														</s:if>
														<s:if test="CreditNoteIssued==true && OrderUpdated==true">
															<i title="Request" class="fa fa-check-circle success">CreditNote
																Issued</i>
														</s:if>
													</p>

												</td>
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Train'">
													  
													  <td>
													<p data-placement="top" class="edit">
														<a
															href="showPassengerDetailsOfTrainOrders?id=<s:property value="id"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Edit </a>
														<%-- 	&orderId=<s:property value="orderId"/>&companyId=<s:property value="companyId"/> --%>
														<s:if
															test="OrderRequested==true && CreditNoteIssued==false">
															<i title="Request" class="fa fa-check-circle red">Order
																Request</i>
														</s:if>
														<s:if test="CreditNoteIssued==true && OrderUpdated==true">
															<i title="Request" class="fa fa-check-circle success">CreditNote
																Issued</i>
														</s:if>
													</p>

												</td>
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Visa'">
													  <td>
													<p data-placement="top" class="details">
														<a
															href="showPassengerDetailsOfVisaOrders?id=<s:property value="id"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Edit </a>
															<!-- &orderId=<s:property value="orderId"/>&companyId=<s:property value="companyId"/> -->
														<s:if
															test="OrderRequested==true && CreditNoteIssued==false">
															<i title="Request" class="fa fa-check-circle red">Order
																Request</i>
														</s:if>
														<s:if test="CreditNoteIssued==true && OrderUpdated==true">
															<i title="Request" class="fa fa-check-circle success">CreditNote
																Issued</i>
														</s:if>
													</p>

												</td>
													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Insurance'">
													  <td>
													<p data-placement="top" class="details">
														<a
															href="showPassengerDetailsOfInsuranceOrders?id=<s:property value="id"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Edit </a>
															<%-- &orderId=<s:property value="orderId"/>&companyId=<s:property value="companyId"/> --%>
														<s:if
															test="OrderRequested==true && CreditNoteIssued==false">
															<i title="Request" class="fa fa-check-circle red">Order
																Request</i>
														</s:if>
														<s:if test="CreditNoteIssued==true && OrderUpdated==true">
															<i title="Request" class="fa fa-check-circle success">CreditNote
																Issued</i>
														</s:if>
													</p>

												</td>
													  
													  
													  </s:elseif>
													  
													  <s:elseif test="servicetype=='Miscellaneous'">
													  
													  <td>
													<p data-placement="top" class="details">
														<a
															href="showPassengerDetailsOfMiscellaneousOrders?id=<s:property value="id"/>"
															class="btn btn-success btn-xs " data-toggle="modal">
															Edit </a>
															<%-- &orderId=<s:property value="orderId"/>&companyId=<s:property value="companyId"/> --%>
														<s:if
															test="OrderRequested==true && CreditNoteIssued==false">
															<i title="Request" class="fa fa-check-circle red">Order
																Request</i>
														</s:if>
														<s:if test="CreditNoteIssued==true && OrderUpdated==true">
															<i title="Request" class="fa fa-check-circle success">CreditNote
																Issued</i>
														</s:if>
													</p>

												</td>
													  </s:elseif>
												
											</tr>
											</s:if>
										</s:iterator>
									</tbody>
									
								</table>
								 
							</div>
							<!-- /.box -->

						</div>
					</div>
					</s:if>
					<s:else>
					<div class="row">
					 <h3 align="center">No Result Found For Ur Search</h3>
					</div>
					
					</s:else>
					 
					</div>
					</form>
					</section>
        
        
         <!-- /.content -->
      </div><!-- /.content-wrapper -->
    <%@ include file="DashboardFooter.jsp"%>   
   <script type="text/javascript">
		/* , 'pdf', 'print'  */
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
					 lengthChange : true,
						"pagingType" : "full_numbers",
					 "lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],
						buttons : [ 'excel' ]
					});
					var table = $('#example1').DataTable({
						"paging" : true,
						"info" : true,
						"searching" : true,
						"ordering" : true,
						"search" : {
							"regex" : true,
						},
					  lengthChange : true,
						"pagingType" : "full_numbers", 
						 "lengthMenu" : [ 10, 25, 50, 75, 100, 500 ], 
						buttons : [ 'excel' ]
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');
		});

	</script>
	<script type="text/javascript">
	$('spna[data-toggle="tooltip"]').tooltip({
		animated : 'fade',
		placement : 'bottom',
	});
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
  
  
  
  function  sendHotelVoucherToCustomerEmail(confirmNumber,invoiceMail){
	  var msg="";
    	  console.log("orderId..."+confirmNumber);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl = newUrl+"sendHotelVoucherToCustomerEmail";
  		if(invoiceMail=="invoice"){
  			 finalUrl = newUrl+"sendHotelInvoiceToCustomerEmail";
  			msg="Successfully sent hotel Invoice to email.";
  		}
  		else{
  			 //finalUrl = newUrl+"sendHotelVoucherToCustomerEmail";
  			//msg="Successfully sent hotel voucher to email.";
  			 finalUrl = newUrl+"sendHotelVoucherToCustomerEmail";
   			msg="Successfully sent hotel voucher to email.";
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
  
  
  function sendCarInvoiceToCustomerEmail(confirmNumber) {
		var msg = "";
		//var orderId=$("#orderId").val();
		console.log("confirmNumber..." + confirmNumber);
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "sendCarInvoiceToCustomerEmail";
		console.log("finalUrl..." + finalUrl);
		msg = "Successfully sent Car Invoice to email.";
		$('#h4').show();
		$.ajax({
			method : "POST",
			url : finalUrl,
			data : {
				orderId : confirmNumber
			},
			success : function(data, status) {
				$.each(data, function(index, element) {
					console.log("data-------" + element.status);
					if (element.status == "success") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text(msg);
						$('#success').click(function() {
							$('#success-alert').hide();
							//window.location.assign($(location).attr('href'));
						});
					} else if (element.status == "fail") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text("Failed.Try again.");
						$('#success').click(function() {
							$('#success-alert').hide();

						});
					}

				});
			},
			error : function(xhr, status, error) {
				$('#h4').hide();
				$('#success-alert').show();
				$('#message').text(error);
				$('#success').click(function() {
					$('#success-alert').hide();
				});
				console.log("Error----------" + error);
			}
		});

	}
  
  
  function sendBusInvoiceToCustomerEmail(confirmNumber) {
		var msg = "";
		//var orderId=$("#orderId").val();
		console.log("confirmNumber..." + confirmNumber);
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "sendBusInvoiceToCustomerEmail";
		msg = "Successfully sent Bus Invoice to email.";
		console.log("finalUrl..." + finalUrl);
		$('#h4').show();
		$.ajax({
			method : "POST",
			url : finalUrl,
			data : {
				orderId : confirmNumber
			},
			success : function(data, status) {
				$.each(data, function(index, element) {
					console.log("data-------" + element.status);
					if (element.status == "success") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text(msg);
						$('#success').click(function() {
							$('#success-alert').hide();
							//window.location.assign($(location).attr('href'));
						});
					} else if (element.status == "fail") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text("Failed.Try again.");
						$('#success').click(function() {
							$('#success-alert').hide();

						});
					}

				});
			},
			error : function(xhr, status, error) {
				$('#h4').hide();
				$('#success-alert').show();
				$('#message').text(error);
				$('#success').click(function() {
					$('#success-alert').hide();
				});
				console.log("Error----------" + error);
			}
		});
	}
  
  function sendTrainInvoiceToCustomerEmail(confirmNumber) {
		var msg = "";
		//var orderId=$("#orderId").val();
		console.log("confirmNumber..." + confirmNumber);
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "sendTrainInvoiceToCustomerEmail";
		msg = "Successfully sent Train Invoice to email.";
		console.log("finalUrl..." + finalUrl);
		$('#h4').show();
		$.ajax({
			method : "POST",
			url : finalUrl,
			data : {
				orderId : confirmNumber
			},
			success : function(data, status) {
				$.each(data, function(index, element) {
					console.log("data-------" + element.status);
					if (element.status == "success") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text(msg);
						$('#success').click(function() {
							$('#success-alert').hide();
							//window.location.assign($(location).attr('href'));
						});
					} else if (element.status == "fail") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text("Failed.Try again.");
						$('#success').click(function() {
							$('#success-alert').hide();

						});
					}

				});
			},
			error : function(xhr, status, error) {
				$('#h4').hide();
				$('#success-alert').show();
				$('#message').text(error);
				$('#success').click(function() {
					$('#success-alert').hide();
				});
				console.log("Error----------" + error);
			}
		});
	}
      
  
  
  function sendVisaInvoiceToCustomerEmail(confirmNumber) {

		var msg = "";
		//var orderId=$("#orderId").val();
		console.log("confirmNumber..." + confirmNumber);
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "sendVisaInvoiceToCustomerEmail";
		msg = "Successfully sent Visa Invoice to email.";
		console.log("finalUrl..." + finalUrl);
		$('#h4').show();
		$.ajax({
			method : "POST",
			url : finalUrl,
			data : {
				orderId : confirmNumber
			},
			success : function(data, status) {
				$.each(data, function(index, element) {
					console.log("data-------" + element.status);
					if (element.status == "success") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text(msg);
						$('#success').click(function() {
							$('#success-alert').hide();
							//window.location.assign($(location).attr('href'));
						});
					} else if (element.status == "fail") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text("Failed.Try again.");
						$('#success').click(function() {
							$('#success-alert').hide();

						});
					}

				});
			},
			error : function(xhr, status, error) {
				$('#h4').hide();
				$('#success-alert').show();
				$('#message').text(error);
				$('#success').click(function() {
					$('#success-alert').hide();
				});
				console.log("Error----------" + error);
			}
		});
	}
  
  
  function sendInsuranceInvoiceToCustomerEmail(confirmNumber) {
		var msg = "";
		//var orderId=$("#orderId").val();
		console.log("confirmNumber..." + confirmNumber);
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "sendInsuranceInvoiceToCustomerEmail";
		msg = "Successfully sent Insurance Invoice to email.";
		console.log("finalUrl..." + finalUrl);
		$('#h4').show();
		$.ajax({
			method : "POST",
			url : finalUrl,
			data : {
				orderId : confirmNumber
			},
			success : function(data, status) {
				$.each(data, function(index, element) {
					console.log("data-------" + element.status);
					if (element.status == "success") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text(msg);
						$('#success').click(function() {
							$('#success-alert').hide();
							//window.location.assign($(location).attr('href'));
						});
					} else if (element.status == "fail") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text("Failed.Try again.");
						$('#success').click(function() {
							$('#success-alert').hide();

						});
					}

				});
			},
			error : function(xhr, status, error) {
				$('#h4').hide();
				$('#success-alert').show();
				$('#message').text(error);
				$('#success').click(function() {
					$('#success-alert').hide();
				});
				console.log("Error----------" + error);
			}
		});
	}
  
  
  function sendMiscellaneousInvoiceToCustomerEmail(confirmNumber) {
		var msg="";
		//var orderId=$("#orderId").val();
		console.log("confirmNumber..." + confirmNumber);
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "sendMiscellaneousInvoiceToCustomerEmail";
		msg="Successfully sent Miscellaneous Invoice to email.";
		console.log("finalUrl..." + finalUrl);
		$('#h4').show();
		$.ajax({
			method : "POST",
			url : finalUrl,
			data : {
				orderId : confirmNumber
			},
			success : function(data, status) {
				$.each(data, function(index, element) {
					console.log("data-------" + element.status);
					if (element.status == "success") {
						$('#h4').hide();
						$('#success-alert').show();
						 $('#message').text(msg);
						$('#success').click(function() {
							$('#success-alert').hide();
							//window.location.assign($(location).attr('href'));
						});
					} else if (element.status == "fail") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text("Failed.Try again.");
						$('#success').click(function() {
							$('#success-alert').hide();

						});
					}

				});
			},
			error : function(xhr, status, error) {
				$('#h4').hide();
				$('#success-alert').show();
				$('#message').text(error);
				$('#success').click(function() {
					$('#success-alert').hide();
				});
				console.log("Error----------" + error);
			}
		});
	}
  </script> 
	