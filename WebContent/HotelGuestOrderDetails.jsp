<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->
 <link rel="stylesheet" href="css/alert.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="CurrentHotelReport.agencyUsername" /></title>
 <style>
#alter_table {
    border-collapse: collapse;
    width: 100%;
    
}
 
#td:nth-child(even){background-color: #f2f2f2}

#th {
   background-color: rgb(0, 130, 203);
	color: white;
}

#cancel-status-table {
color: #333; /* Lighten up font color */
font-family: Helvetica, Arial, sans-serif; /* Nicer font */
width: 100%;
border-collapse:
collapse; border-spacing: 0;
}

#cancel-status-table td, th { border: 1px solid #CCC; height: 30px; } /* Make cells a bit taller */

#cancel-status-table th {
background: rgb(0, 130, 203); /*blue background */
font-weight: bold; /* Make sure they're bold */
text-align: center;
font-size: 20px;
color:white;
}

#cancel-status-table td {
background: #FAFAFA; /* Lighter grey background */
text-align: center; /* Center our text */
}
element {
    width: 543px;
    height: 59px;
}

</style>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
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
		
		<section class="content-header">
			<h1>Hotel Guest Details</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>





		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
			
			<div>
			<s:if test="%{#session.Company.companyRole.SuperUser==true}">
				
			  
			 
			 <s:if test="%{(CurrentHotelReport.status =='Confirmed' || CurrentHotelReport.status =='Cancelled') && CurrentHotelReport.bookingMode == 'Online' && CurrentHotelReport.cancelMode == null}">
			 
			 <a href="#" id="showdiv" class="btn btn-danger" data-toggle="modal" data-target="#myModal" data-ng-click="showmodelforhotel('Cancel')"  >  <i class="fa fa-file"></i> Cancel Booking</a>
			 <img class="flightloading-image" src="images/divloading.gif"	alt="Loading..." data-ng-show="ticketprocessloader" data-ng-cloak/>
			{{processcomments}}   {{bookingcommentsforhotel}}
        
			
			
			</s:if>
			 
			
			
			
			 
				</s:if>
			
			 
			 <c:if test="${CurrentHotelReport.cancelMode != null}">
			 <c:choose>
			 <c:when  test="${CurrentHotelReport.APIStatusCode == 3 || CurrentHotelReport.cancelMode == 'Offline'}">
			 <div class="row">
			 <h4 style="color:green">Your Booking is Cancelled Sucessfully With OrderId :  ${CurrentHotelReport.orderRef} .As a CancellationMode Is:  ${CurrentHotelReport.cancelMode}</h4>
			</div>
			 
			 </c:when >
			 <c:otherwise>
			 <s:if test="%{#session.Company.companyRole.SuperUser==true}">
			  <a href="#" id="showdiv" class="btn btn-danger" data-toggle="modal" data-target="#myModal1" data-ng-click="showmodelforhotel('Status')"  >  <i class="fa fa-file"></i> Cancel Status</a>
			 <img class="flightloading-image" src="images/divloading.gif"	alt="Loading..." data-ng-show="ticketprocessloader" data-ng-cloak/>
			{{processcomments}}   {{bookingcommentsforhotel}}
        </s:if>
			  <div class="row">
			 <h4 style="color:red">Please Check Hotel Booking Cancel  Status</h4>
			</div>
			 </c:otherwise>
			 </c:choose>
			 
			 
			 </c:if>
			 
			 
			 
				</div>
				<input type="hidden" id="userid" value="<s:property value="%{#session.User.id}"/>">
				<input type="hidden" id="username" value="<s:property value="%{#session.User.Username}"/>">
				<input type="hidden" id="appkey" value="<s:property value="CurrentHotelReport.appkey"/>">
				<input type="hidden" id="status" value="<s:property value="CurrentHotelReport.status"/>">
				<input type="hidden" id="orderid" value="<s:property value="CurrentHotelReport.orderRef"/>">
				<input type="hidden" id="ref_code" value="<s:property value="CurrentHotelReport.ref_code"/>">
				<input type="hidden" id="bookingMode" value="<s:property value="CurrentHotelReport.bookingMode"/>">
				<input type="hidden" id="password" value="<s:property value="%{#session.Company.Password}"/>">
				<input type="hidden" id="paymode" value="<s:property value="CurrentHotelReport.paymentMethod"/>">
				<input type="hidden" id="cansmode" value="<s:property value="CurrentHotelReport.cancelMode"/>">
				<input type="hidden" id="hotelOrderRowCancellationData" value="${hotelOrderRowCancellation.statusMessage}">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
			<div class="row">
				<div class="col-sm-12 clearfix report-search">

					<!-- 		<form class="form-inline" action="" method="post"> -->

					<div class="table-responsive dash-table">

						<!-- testing -->

						<div class="box clearfix">
							<!-- <div class="box-body"> -->

							<label for="Country"><h4>
									<b><s:property value="user" /></b>
								</h4></label>

							<div class="modal-body" >
								<table class="table table-striped table-bordered">
									<tr>
									<th>CreatedBy</th>
									<th>Order Id</th>
										<th>Hotel Confirmation Number</th>
										<th>Reference Code </th>
										 
										<th>Booking Status</th>
											<th>Booking Mode</th>
												<th></th>
										 
									</tr>
									<tr>
									<th>
										<s:property
												value="CurrentHotelReport.createdBy" />	
											</th>
										<th><s:property
												value="CurrentHotelReport.orderRef"/></th>
												<th><s:property
												value="CurrentHotelReport.confirmNo"/></th>
										<th><s:property
												value="CurrentHotelReport.referenceCode"/> </th>
									 
										 <th><s:property
												value="CurrentHotelReport.status" /></th>
											<th>
										<s:property
												value="CurrentHotelReport.bookingMode" />	
											</th>
												<th></th>
									</tr>
									</table>
									 <table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									<tr>
										<th colspan="6" ><h5>
											 <b><big>HOTEL INFORMATION</big></b>
											</h5></th>


									</tr>
									<tr>
										<th>Hotel_name</th>
										<th>Location</th>
										<th>state</th>
										<th>Country</th>
										<th>Hotel_category</th>
										<th></th>
										

									</tr>
									<tr>
										<th><s:property value="CurrentHotelReport.hotelName" /></th>
										<th><s:property value="CurrentHotelReport.hotel_loc" /></th>
										<th><s:property value="CurrentHotelReport.state" /></th>
										<th><s:property value="CurrentHotelReport.country" /></th>
										
										<th><s:property value="CurrentHotelReport.hotel_cat" /></th>
										<th></th>


									</tr>
									</table>
									<table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									<tr>
										<th colspan="6"><h5>
												 <b><big>ROOM INFORMATION</big></b>
											</h5></th>
									</tr>
									<tr>
										<th>S.No</th>
										<th>Created_at</th>
										<th>MealType</th>
										<th>Hotel_name</th>
										<th>Status</th>
										<th></th>

									</tr>
									<s:iterator value="roomInfo" status="serial">
										<tr>
											<th><s:property value="%{#serial.count}" /></th>
											<th><s:property value="createdDate" /></th>
											<th><s:property value="mealType" /></th>
											<th><s:property value="hotelName" /></th>
											<th><s:property value="CurrentHotelReport.status"/></th>
											<th></th>
											

										</tr>
									</s:iterator>
									<table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									<tr>
										<th colspan="6"><h5>
												 <b><big>GUEST INFORMATION</big></b>
											</h5></th>
									</tr>
									<tr>
										<th>S.No</th>
										<th>FirstName</th>
										<th>LastName</th>
										<th>Email</th>
										<th> </th>
										<th> </th>

									</tr>
									<s:iterator value="roomGuestInfos" status="serial">
										<tr>

											<th><s:property value="%{#serial.count}" /></th>
											<th><s:property value="firstname" /></th>
											<th><s:property value="lastname" /></th>
											<th><s:property value="email" /></th>
										<th> </th>
										<th> </th>
										</tr>
									</s:iterator></table>
									
									<table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									<tr>
									<th colspan="12"><h5>
											<b><u>RM CONFIG</u></b>
										</h5></th>
								</tr>
								<tr>
									<th>RM Config For</th>
									<th>empCode</th>
									<th>department</th>
									<th>costCenter</th>
									<th>approverName</th>
									<th>location</th>
									<th>bussinessUnit</th>
									<th>projectCode</th>
									<th>reasonForTravel</th>
									<th>billNonBill</th>
									<s:if test="fieldName!=null && fieldName.size()>0">
											<s:if test="fieldName[0]!=null ">
												<td>${fieldName[0]}</td>
											</s:if>
											<s:if test="fieldName[1]!=null ">
												<td>${fieldName[1]}</td>
											</s:if>
											<s:if test="fieldName[2]!=null ">
												<td>${fieldName[2]}</td>
											</s:if>
											<s:if test="fieldName[3]!=null ">
												<td>${fieldName[3]}</td>
											</s:if>
											<s:if test="fieldName[4]!=null ">
												<td>${fieldName[4]}</td>
											</s:if>
									</s:if>
									
								</tr>
								<s:iterator value="roomGuestInfos" >
									<tr>
									<td><s:property value="firstname" /></td>
										 <td><s:property value="empCode" /></td>
										<td><s:property value="department" /></td>
										<td><s:property value="costCenter" /></td>
										<td><s:property value="approverName" /></td>
										<td><s:property value="location" /></td>
										<td><s:property value="bussinessUnit" /></td>
										<td><s:property value="projectCode" /></td>
										<td><s:property value="reasonForTravel" /></td>
										<td><s:property value="billNonBill" /></td>
										 
										 <s:if test="fieldName!=null && fieldName.size()>0">
											 <s:if test="fieldName[0]!=null ">
												<td><s:property value="manualField1" /></td>
											</s:if>
											<s:if test="fieldName[1]!=null ">
												<td><s:property value="manualField2" /></td>
											</s:if>
											<s:if test="fieldName[2]!=null ">
												<td><s:property value="manualField3" /></td>
											</s:if>
											<s:if test="fieldName[3]!=null ">
												<td><s:property value="manualField4" /></td>
											</s:if>
											<s:if test="fieldName[4]!=null ">
												<td><s:property value="manualField5" /></td>
											</s:if>
										</s:if>
									</tr>
								</s:iterator>
									
									</table>
									
									
										<table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									<tr>
										<th colspan="6"><h5>
												<b><big>PAYMENT INFORMATION</u></big>
											</h5></th>
									</tr>
									<tr>
										<th>OrderId</th>
										<th>Amount</th>
										<th>ResMessage</th>
										<th>Payment_status</th>
										<th>Payment_Type</th>
										<th></th>

									</tr>
					 
											<tr>
												<td>${paymentTransaction.api_transaction_id}</td>
												<td>${paymentTransaction.amount}&nbsp;<s:property value="CurrentHotelReport.curCode"/></td>
 												<td><s:property value="paymentTransaction.response_message" /></td>
												<td><s:property value="paymentTransaction.payment_status" /></td>
												<td><s:property value="paymentTransaction.payment_method" /></td>
												<td></td>
												 
											</tr>
										 
									 
									 </table>
									<%-- <tr>
										<th colspan="3"><h5>
												<b><u>CANCELLATION INFO</u></b>
											</h5></th>
									</tr>
									<tr>
										<th>S.No</th>
										<th>Start</th>
										<th>End</th>
										<th>Currency</th>
										<th>Amount</th>
										<th>Fee_amount</th>
										<th>Fee_type</th>
										<th>Remarks</th>
									</tr>
									<s:iterator value="cancellationPoliciesInfo"
										status="serial">
										<tr>

											<th><s:property value="%{#serial.count}" /></th>
											<th><s:property value="startDate" /></th>
											<th><s:property value="endDate" /></th>
											<th><s:property value="curCode" /></th>
											<th><s:property value="feeAmount" /></th>
											<th><s:property value="formattedFeeAmount" /></th>
											<th><s:property value="feeType" /></th>
											<th><s:property value="remarks" /></th>
										</tr>
									</s:iterator> --%>
							<table class="table table-striped table-bordered"
								cellspacing="0" width="100%">
									<tr>
									<th colspan="3"><h4>
											<b>Guests : <s:property
													value="CurrentHotelReport.guests" /></b>
										</h4></th>

									<th  colspan="3" align="right"><h4>
											<b>Total:<s:property value="CurrentHotelReport.total" />
												<s:property value="CurrentHotelReport.curCode" /></b>
										</h4></th>
								</tr>
								</table>
									<table class="table table-striped table-bordered"
									cellspacing="0" width="100%">
									<tr>
 								  	<s:if
										test="%{CurrentHotelReport.myCreditNote.CreditnoteIssued==false && CurrentHotelReport.OrderUpdated==true }">
										<th><a
											href="generateHotelCreditNote?id=<s:property value="CurrentHotelReport.id"/>"
											id="orderNow1" class="btn btn-primary">Generate Credit Note</a>
											<Strong Style="color:red;font-size:12px;">Click here to generate your credit note</Strong>
										</th>
									</s:if>
									<s:elseif test="%{ (creditNoteList!=null && creditNoteList.size >0 && CurrentHotelReport.CreditNoteIssued==false) && (#session.Company.companyRole.Corporate==true ||#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true)}">
									<Strong Style="color:red;font-size:15px;">Waiting for Admin Response
									to 
									<s:if test="%{ CurrentHotelReport.creditNoteActiontype=='IssueCreditNote'}">
									 cancel your reservation
									</s:if>
									, please contact Admin for urgent quries</Strong>
									</s:elseif>
									<s:elseif test="%{(creditNoteList!=null && creditNoteList.size >0 && CurrentHotelReport.CreditNoteIssued==false ) && #session.Company.companyRole.SuperUser==true}">
									<Strong Style="color:red;font-size:15px;">
									 You received request for 
									 <s:if test="%{ CurrentHotelReport.creditNoteActiontype=='IssueCreditNote'}">
									 cancellation, Kindly process on time
									 </s:if>
									</Strong>
									</s:elseif>
									
									
									<s:if
										test="%{CurrentHotelReport.CreditNoteIssued==true && CurrentHotelReport.myCreditNote.CreditnoteIssued==true &&  CurrentHotelReport.creditNoteActiontype=='IssueCreditNote'}">
										<th><a
											href="generateHotelCreditNote?id=<s:property value="CurrentHotelReport.id"/>"
											id="orderNow1" class="btn btn-primary">View Credit Note</a>
										<Strong Style="color:red;font-size:12px;">Click here to view your credit note</Strong>	
										</th>

									</s:if>
									
									<%-- <s:if
										test="%{#session.Company.Companyid==CurrentHotelReport.companyId}">
										<s:if test="%{CurrentHotelReport.isOrderUpdated()}">
											<th><a href="generateHotelCustomerCreditNote?id=<s:property value="CurrentHotelReport.id"/>&companyId=<s:property value="CurrentHotelReport.companyId"/>" id="orderNow1"
										 class="btn btn-primary">Customer Credit Note</a></th>
										</s:if>
									</s:if>	 --%>
									  </tr> 
									 </table>
									 
									<!-- Displaying cancellation status here -->
									
									 <form action="insertHotelOrderModifiedInfo" method="get">
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
									<s:if test="%{CurrentHotelReport.hotelOrderRowCancellation!=null}">
									 <table id="cancel-status-table">
									<tr>
    								<th colspan="4">Cancellation Status</th>    									 
	 											</tr>
	 											 <tr>
	 											 <td>Status</td>
	 											  <td>Requested on</td>
	 											  <td>Updated on</td>
	 											  <s:if test="%{#session.Company.Companyid==CurrentHotelReport.companyId}">
	 											<!-- <td>Action</td> -->
	 											</s:if>
	 											 </tr>
	 											<tr>
    										<td><s:property value="CurrentHotelReport.hotelOrderRowCancellation.statusMessage"/></td>
    										<td> <s:property value="CurrentHotelReport.hotelOrderRowCancellation.createdDate"/> </td>
   											<td><s:property value="CurrentHotelReport.hotelOrderRowCancellation.updatedDate"/> </td>
											 
											<%--    <s:if test="%{CurrentHotelReport.hotelOrderRowCancellation.statusCode==3}">
											    <td> 
    									  	   <button type="submit" name="cancelType" value="2" disabled="disabled">Get Status</button>
    									  	   </td>
    									  	  </s:if>
											<s:else>
											  
											<s:if test="%{#session.Company.Companyid==CurrentHotelReport.companyId}">
											 <td>
										  	<button type="submit" name="cancelType" value="2" >Get Status</button>
										  	</td>
											 </s:if>
											
											</s:else> --%>
											    
												</tr>
											</table>											 
									 	</s:if>									 	
									 	<s:else>								 
							  		<table id="cancel-status-table">
									<tr>
    								<th colspan="4">Cancellation Status</th>    									 
	 											</tr>
	 											 <tr>
	 											 <td>Status</td>	 											 
	 											<td>Action</td>
	 											 </tr>
	 											<tr>
    										<td>
    										To be cancelled 
    									  	<td>
    									  	<!-- #session.Company.Companyid==CurrentHotelReport.companyId || -->
    									  	<s:if test="% #session.Company.companyRole.SuperUser==true}">
    									  	 <select name="cancelMode" id="cancelMode" autocomplete="off" required>
												<s:if test="%{(#session.Company.companyRole.SuperUser==true ||CurrentHotelReport.bookingMode =='Offline')}">
													<option value="Offline">Offline</option>
												</s:if>
												<s:if test="%{CurrentHotelReport.bookingMode =='Online'}">
												<option value="Online" selected>Online</option>
												</s:if>
											  </select>
											  </s:if>
    									  	<button type="submit" name="cancelType" value="1">Cancel Booking</button>	
    									  	   </td>
												</tr>
											</table>
									 	</s:else>	
									 		 							 	
									   </table>
							 </div>
 						</div>
 						 </div>
 						
 					<!-- Request for credit note for the paricular order -->	 
 			
		
			<table id="editOrder1" style="border: 2px solid #2283E1" class="table table-striped table-bordered">
				
				<tr>
						<td colspan="6"><h4
								style="background-color: rgb(0, 130, 203); padding: 5px; color: white; font-weight: bold;">Edit
								or Cancel Order</h4></td>
					</tr>
				 <tr>
 				<td><h5>BookingStatus</h5>
 				 <s:if
							test="CurrentHotelReport.isOrderRequested()">
							<select name="statusAction" id="statusAction" autocomplete="off"
								required>
								<option value="<s:property value="CurrentHotelReport.status"/>"><s:property
										value="CurrentHotelReport.status" /></option>
							</select>
						</s:if> 
						<s:else>
							<select name="statusAction" id="statusAction" autocomplete="off"
								required>
								<!-- <option selected="selected" value="">Select booking
									status</option>
								<option value="failed">failed</option>
											  <option value="issued">issued</option>
											<option value="confirmed">confirmed</option>
											<option value="pending">pending</option> -->
											<option value="Cancelled">Cancelled</option>

							</select>
						</s:else>
						 <input type="hidden" name="hotelOrderRowId"
						value="<s:property value="CurrentHotelReport.id"/>">
						<input
						type="hidden" name="befPayStatus"
						value="<s:property value="CurrentHotelReport.paymentStatus"/>">
						<input
						type="hidden" name="beforeStatus"
						value="<s:property value="CurrentHotelReport.status"/>"> <input
						type="hidden" name="userId"
						value="<s:property value="%{#session.User.id}"/>"> <input
						type="hidden" name="companyId"
						value="<s:property value="%{#session.Company.companyid}"/>">
						<input type="hidden" name="gstAmount"
						value="<s:property value="CurrentHotelReport.gstOnMarkup"/>"> <input
						type="hidden" name="totalBookingAmount"
						value="<s:property value="CurrentHotelReport.finalPrice"/>"> <input
						type="hidden" name="updatedBy"
						value="<s:property value="%{#session.User.Username}"/>">
					</td>

					<td><h5>PaymentStatus</h5> <s:if
							test="CurrentHotelReport.isOrderRequested()">
							<select name="paymentStatus" id="paymentStatus"
								autocomplete="off" required>
								<option selected="selected" value="<s:property value="CurrentHotelReport.paymentStatus"/>"><s:property
										value="CurrentHotelReport.paymentStatus" /></option>

							</select>
						</s:if> <s:else>
							<select name="paymentStatus" id="paymentStatus"
								autocomplete="off" required>
								<!-- <option selected="selected" value="">Select payment
									status</option>
									<option value="failed">failed</option>
								<option value="pending">pending</option>
								<option value="success">success</option> -->
								 <option selected="selected" value="Refund">Refund</option>

							</select>
						</s:else>
						<td><h5>Action Type</h5>
						 	<s:if test="CurrentHotelReport.creditNoteActiontype=='UpdateOrder'">
						 		<select name="actionType" id="actionType"
								autocomplete="off" required>
								 <option selected="selected" value="UpdateOrder">UpdateOrder</option>
							 </select>
						 	 </s:if>
						 	 <s:elseif test="CurrentHotelReport.creditNoteActiontype=='IssueCreditNote'">
						 	 <select name="actionType" id="actionType"
								autocomplete="off" required>
									<option selected="selected" value="IssueCreditNote">IssueCreditNote</option>
							 </select>
						 	   </s:elseif>
						 	<s:else>
							 	<select name="actionType" id="actionType"
									autocomplete="off" required>
									<!-- <option selected="selected" value="">Select Action Type
										 </option>
									<option value="UpdateOrder">UpdateOrder</option> -->
									<option selected="selected" value="IssueCreditNote">IssueCreditNote</option>
								</select>
						 	</s:else>
						 	  </td>
						 	  
						 	 <s:if test="%{#session.Company.companyRole.SuperUser==true}">
								<td><h5>Management Fees</h5>
									 <input type="text" name="managementFees" id="managementFees"
											autocomplete="off"   value="${CurrentHotelReport.managementFee}" readonly>
									 <input
									type="hidden" id="creditNoteActiontype"
									value="<s:property value="CurrentHotelReport.creditNoteActiontype"/>">
								</td>
							 </s:if>	 
							 <s:elseif test="%{CurrentHotelReport.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true)}">
								<td><h5>Management Fees</h5>
									 <input type="text" name="managementFees" id="managementFees"
											autocomplete="off"   value="${CurrentHotelReport.managementFee}" readonly>
								</td>
							</s:elseif>
							<s:elseif test="%{#session.Company.companyRole.Corporate==false}">	
								<td><h5>Management Fees</h5>
									 <input type="text" name="managementFees" id="managementFees"
											autocomplete="off"   value="${CurrentHotelReport.managementFee}" readonly>
								</td>	 	  
						 	  </s:elseif>
						 	<s:if test="%{#session.Company.companyRole.SuperUser==true}">
						<td><h5>Convenience Fees</h5>
						<input type="text" name="convenienceFees" id="convenienceFees" autocomplete="off"   value="${CurrentHotelReport.convenienceFees}"> 
							   </td>
						</s:if>	  
						
						<s:elseif test="%{CurrentHotelReport.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true || #session.Company.companyRole.Corporate==true)}">
						<td><h5>Convenience Fees</h5>
						<input type="text"  name="convenienceFees" id="convenienceFees"
									autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  value="${CurrentHotelReport.convenienceFees}"> 
					 	 </td>
						</s:elseif> 
						<s:else>
						<td><h5>Convenience Fees</h5>
						<input type="hidden"  name="convenienceFees" id="convenienceFees" autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  
						value="0"> 
					   <small>Convenience Fees automatically appears <br>once order is cancelled by admin</small>
					    </td>
						</s:else>	 
						 	  
						 	  
						<s:if test="%{#session.Company.companyRole.SuperUser==true}">
						
						<s:if test="%{ReportData.cancelMode == 'Online' }">
						<td><h5>CancellationFees</h5>
						<input type="text" name="cancellationFees" id="cancellationFees"
									autocomplete="off"   value="${CurrentHotelReport.cancellationFees}"> 
							   </td>
							   </s:if>
							   <s:else >
							   <td><h5>CancellationFees</h5>
						<input type="text" name="cancellationFees" id="cancellationFees"
									autocomplete="off"   value="${CurrentHotelReport.cancellationFees}"> 
							   </td>
							   
							   </s:else>
						
						</s:if>	  
						
						<s:elseif test="%{CurrentHotelReport.isOrderUpdated() && (#session.Company.companyRole.Distributor==true || #session.Company.companyRole.Agent==true || #session.Company.companyRole.Corporate==true)}">
						<td><h5>Cancellation Fees</h5>
						<input type="text"  name="cancellationFees" id="cancellationFees"
									autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  value="${CurrentHotelReport.cancellationFees}"> 
					 	 </td>
						</s:elseif> 
						<s:else>
						<td><h5>Cancellation Fees</h5>
						<input type="hidden"  name="cancellationFees" id="cancellationFees" autocomplete="off"    readonly="readonly"  style="background:rgb(170, 169, 176)"  
						value="0"> 
					   <small>Cancellation Fees automatically appears <br>once order is cancelled by admin</small>
					    </td>
						</s:else>	  
				 </tr>
					<tr> 
					<td colspan="5">
						<h5>EmpComments</h5> <textarea name="employeeComments" style="width:100%" > </textarea>

					</td>
					<%-- <td><h5>Action</h5>
					<s:if test="%{CurrentHotelReport.hotelOrderRowCancellation.statusCode==3}">
					<button type="submit" class="btn btn-primary" style="width: 78%;" name="cancelType"  value="3">Credit Note Request</button>
					</s:if>
					 			
					</td>		 --%>		
					</tr>
						 
			</table>
		</form> 
		<s:if test="creditNoteList.size()>0">
		<table id="alter_table">
				<tr id="th">
					<th>S.no</th>
					<th>Alter By</th>
					<th>Convenience Fees</th>
					<th>Cancellation Fees</th>
					<th>Management Fees</th>
					<th>Before Status</th>
					<th>Before PayStatus</th>
					<th>After Status</th>
					<th>After PayStatus</th>
					<th>Action Type</th>
					<th>Date</th>

				</tr>
			 
					<s:iterator value="creditNoteList" status="serial">
						<tr id="td">
							<td><s:property value="%{#serial.count}" /></td>
							<td><s:property value="alterBy" /></td>
							<td><s:property value="convenienceFees" /></td>
							<td><s:property value="cancellationFees" /></td>
							<td><s:property value="managementFees" /></td>
							<td><s:property value="beforeStatus" /></td>
							<td><s:property value="befPayStatus" /></td>
							<td><s:property value="afterStatus" /></td>
							<td><s:property value="afterPayStatus" /></td>
							<td><s:property value="actionType" /></td>
							<td><s:property value="convertDate" /></td>

						</tr>

					</s:iterator>
				 
			</table>
			</s:if>
			</div>
			 </div>
			 
		</section>
		<!-- /.content -->
	</div>
	
	
 	<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content"  >
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">{{modeltitle}}</h4>
      </div>
      <div class="modal-body"><p>{{modelcontent}} </p>
      
      </div>
      <div class="modal-footer">
       
     
      <form action="" id="myForm" name="myForm" >
       <div class="form-group">
      <div class="row" >
      <input type="text" class="form-control" data-ng-model='remarks' id="remarks" placeholder="Enter remarks Here" data-ng-minlength="5" data-ng-maxlength="40" data-ng-required="true"/>
                   </div>
                  </div>
     
    			<input type="hidden" id="userid" value="<s:property value="%{#session.User.id}"/>">
				<input type="hidden" id="username" value="<s:property value="%{#session.User.Username}"/>">
				<input type="hidden" id="appkey" value="<s:property value="CurrentHotelReport.appkey"/>">
				<input type="hidden" id="status" value="<s:property value="CurrentHotelReport.status"/>">
				<input type="hidden" id="orderid" value="<s:property value="CurrentHotelReport.orderRef"/>">
				<input type="hidden" id="ref_code" value="<s:property value="CurrentHotelReport.ref_code"/>">
				<input type="hidden" id="bookingMode" value="<s:property value="CurrentHotelReport.bookingMode"/>">
				<input type="hidden" id="password" value="<s:property value="%{#session.Company.Password}"/>">
				
      
       <button type="button" class="btn btn-default"   data-ng-click="myForm.$valid && showmodelforhotel('Proceed')"  data-dismiss="modal"   data-ng-show="bookingstatus == 'Confirmed' || bookingstatus == 'Cancelled'">Cancel</button>
      <button type="button" class="btn btn-default"	data-dismiss="modal">Close</button>
       
       </form> </div>
    </div>

  </div>
</div>
	
 	<div id="myModal1" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content"  >
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">{{modeltitle}}</h4>
      </div>
      <div class="modal-body"><p>{{modelcontent}} </p>
      
      </div>
      <div class="modal-footer">
       
     
      <form action="" id="myForm" name="myForm" >
       <!-- <div class="form-group">
      <div class="row" >
      <input type="text" class="form-control" data-ng-model='remarks' id="remarks" placeholder="Enter remarks Here" data-ng-minlength="5" data-ng-maxlength="40" data-ng-required="true"/>
                   </div>
                  </div> -->
     
    			<input type="hidden" id="userid" value="<s:property value="%{#session.User.id}"/>">
				<input type="hidden" id="username" value="<s:property value="%{#session.User.Username}"/>">
				<input type="hidden" id="appkey" value="<s:property value="CurrentHotelReport.appkey"/>">
				<input type="hidden" id="status" value="<s:property value="CurrentHotelReport.status"/>">
				<input type="hidden" id="orderid" value="<s:property value="CurrentHotelReport.orderRef"/>">
				<input type="hidden" id="ref_code" value="<s:property value="CurrentHotelReport.ref_code"/>">
				<input type="hidden" id="bookingMode" value="<s:property value="CurrentHotelReport.bookingMode"/>">
				<input type="hidden" id="password" value="<s:property value="%{#session.Company.Password}"/>">
				
      
       <button type="button" class="btn btn-default"   data-ng-click="showmodelforhotel('Proceed')"  data-dismiss="modal"   data-ng-show="bookingstatus == 'Confirmed' || bookingstatus == 'Cancelled'">Cancel Status</button>
      <button type="button" class="btn btn-default"	data-dismiss="modal">Close</button>
       
       </form> </div>
    </div>

  </div>
</div>
	
	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
<script>
		$(document).ready(function() {
			$("#twodpd2").datepicker({
				dateFormat : "yy-mm-dd"
			});
			$("#twodpd1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
		});
	</script>
<script type="text/javascript">
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						lengthChange : false,
						   "searching": false,
						   "ordering": false,  
						   "paging": false,
						   "info": false,
						 
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});
	</script>
 <script type="text/javascript">
		$(document).ready(function() {
			$("#orderNow").click(function() {
				$("#editOrder").toggle("slow", function() {

				});
			});
		});
	</script>
	 <script type="text/javascript">
$(function() {
 $('#success').click(function() {
		  window.location.assign(document.referrer); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		  window.location.assign(document.referrer); 
		   $('#error-alert').hide();
			
		});  
 });
 </script>
 <script type="text/javascript">
 function validate(){
	 var x = document.forms["myForm"]["remarks"].value;
	 if (x == "" && x == null) {
		 alert("remarks are required")
	        return false;
	 
	 }
 }
 </script>
 
 </body>
</html>