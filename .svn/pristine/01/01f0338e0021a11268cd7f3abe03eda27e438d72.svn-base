<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Designation</title>
<%-- <script src="js/angular.js" type="text/javascript"></script>
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> --%>
	<%-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> 
	<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script> --%>
	<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script> --%>

<!-- 
<link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css"> -->
	<!-- <link href="css/jquery-uii.css" rel="stylesheet" type="text/css" /> -->
  <style>

<style type="text/css">
#rooms {
	margin-top: 5px;
}

.panel-group .panel-heading+.panel-collapse>.panel-body {
	border: 1px solid #ddd;
}

.panel-group, .panel-group .panel, .panel-group .panel-heading,
	.panel-group .panel-heading a, .panel-group .panel-title, .panel-group .panel-title a,
	.panel-group .panel-body, .panel-group .panel-group .panel-heading+.panel-collapse>.panel-body
	{
	border-radius: 2px;
	border: 0;
}

.panel-group .panel-heading {
	padding: 0;
}

.panel-group .panel-heading a {
	display: block;
	background: #668bb1;
	color: #ffffff;
	padding: 8px;
	text-decoration: none;
	position: relative;
}

.panel-group .panel-heading a.collapsed {
	background: #eeeeee;
	color: inherit;
}

.panel-group .panel-heading a:after {
	content: '-';
	position: absolute;
	right: 20px;
	top: 8px;
	font-size: 20px;
}

.panel-group .panel-heading a.collapsed:after {
	content: '+';
}

.more-details a {
	width: 60px;
	text-align: left;
	position: relative;
}

.more-details a:after {
	content: '-';
	position: absolute;
	right: 10%;
	top: 18%;
	font-size: 15px;
	color: #fff;
}

.more-details a.collapsed:after {
	content: '+';
	/*   position: absolute;
  left: 10%;
  top:0px;
  font-size:25px; */
	color: #fff;
}

.panel-group .panel-collapse {
	margin-top: 5px !important;
}

.panel-group .panel-body {
	background: #ffffff;
	padding: 15px;
}

.panel-group .panel {
	background-color: transparent;
}

.panel-group .panel-body p:last-child, .panel-group .panel-body ul:last-child,
	.panel-group .panel-body ol:last-child {
	margin-bottom: 0;
}

.add-remove {
	margin: 20px 0px 20px;
}
</style>
<script type="text/javascript">
$(function(){
	var id=$("#uniqueId").val();
	var flightreiumber=('#flightreiumber').val();
	document.getElementById('flightreiumber'+id).value =flightreiumber;
	
	
});






</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/alert.css">
<script src="js/angular.js" type="text/javascript"></script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="row">
				<div class="col-sm-3 clearfix pull-left">
					<h3>Expenses Entry Form</h3>
				</div>
				<div class="col-sm-9 clearfix pull-right " data-placement="top">
					<div class="row">
						<div class="col-sm-6 clearfix pull-left " data-placement="top">
							
						</div>
						<div class="col-sm-4 clearfix " data-placement="top">
							<a href="goTrips" class="btn btn-top-link btn-xs"> All Trips
							</a> <a href="HotelTravelRequestList" class="btn btn-top-link btn-xs"
								title="Check Hotel Trips"> Hotel Trips </a> <a
								href="FlightTravelRequestList" class="btn btn-top-link btn-xs"
								title="Check Flight Trips"> Flight Trips </a>
						</div>
						<div class="col-sm-2 clearfix pull-right" data-placement="top">
							<div class="myDropdown">
								<button class="btn btn-top-link btn-xs dropdown-toggle"
									type="button" data-toggle="dropdown">
									Create Trip <span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="goHotelTravelRequest">Hotel</a></li>
									<li><a href="goFlightTravelRequest">Flight</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
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
			
			<div class="row">
				<!-- createHoteOfflineBooking  -->
				<!-- <form action="createexpansesForm" method="post"
					class="form-horizontal" name="myForm" id="expensesFormId"> -->
					<div id="myfform">
						<%-- <input type="hidden" id="notiCreatedUserId" name="userId"
								value="<s:property value="#session.User.id" />"> 
								<input  name="companyId"
								type="hidden" id="notiCreatedCompanyId"
								value="<s:property value="#session.User.Companyid" />">
							<div class="form-group">
						<label for="expense.trip_id" class="col-sm-1 control-label" style="text-align: right">Trip Id</label>
						 --%>
						<%-- <div class="col-sm-8">
			 <select class="form-control input-sm" name="expense.trip_id" id="tripId">
									<option value="" selected="selected">select TripId</option>
									<c:forEach items="${ListOfTripId}" var="list">
        								<option value="${list}">
          								  ${list}
       									</option>
   								 </c:forEach>
								 </select>
				 
						</div> --%>
						 
					</div> 
						<!-- harsha added colapse -->
						<div class="col-sm-12">
							<div class="panel-group" id="accordion">
								
								
								
								<!-- /.flight-----------panel -->
								

								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseTwo"> Flight Expenses </a>
										</h4>
									</div>
									
									<!--/.panel-heading -->
									<div id="collapseTwo" class="panel-collapse collapse">
									
										<div class="panel-body">
										<form action="updateflightexpenseForm" method="post" enctype="multipart/form-data"
					class="form-horizontal" name="flightexpensemyForm" id="flightexpensesFormId">
															 
					
					<!-- <div class="col-sm-12">
						<div class="col-sm-4" id="flightnumberdiv">
												<div class="form-group">
												<div><label for="hotel address1" class="control-label">Have OrderId ?</label></div>
													 <label for="chkYes">
  																<input type="radio" id="chkYes" name="chkPinNo"  value="m" readonly/> Yes
													</label>
													<label for="chkNo">
 																 <input type="radio" id="chkNo" name="chkPinNo"  value="f"/> No
													</label>


												</div>
											</div>
											</div>
											 -->
	
 
 
 
										<!-- <div class="col-sm-12" id="flightOrderId">
											
												<div class="form-group">
													<label for="hotelName" class=" control-label">Order Id </label> <input type="text" autocomplete="off"
														name="flightExpense.OrderId" id="flightorderid"
														class="form-control input-sm" placeholder="Order Id"
														value=""  >

												</div>
											
											</div> -->
											
										
			 <div class=" withoutOrderId">
			                                     	<div class="col-sm-4" id="flightpnrnumberdiv">
												<div class="form-group">
													<label for="hotel address1" class="control-label">PNR Number</label> 
													<%-- <input type="hidden" value="<s:property value="${flightExpense.id}"/>" id="uniqueId"> --%>
													<input type="hidden" value="<s:property value="id"/>" id="uniqueId">
													<input type="text" autocomplete="off"
														name="flightExpense.pnrNumber" id="flightpnrnumber"
														class="form-control input-sm" placeholder="Flight pnr Number"
														value="${flightExpense.pnrNumber}" required>

												</div>
											</div>
													<div class="col-sm-4" id="flightnumberdiv">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Flight Number</label> <input type="text" autocomplete="off"
														name="flightExpense.flightNumber" id="flightnumber"
														class="form-control input-sm" placeholder="Flight Number"
														value="${flightExpense.flightNumber}"  required>

												</div>
											</div>
											<div class="col-sm-4" id="flightcarrierdiv">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Flight Carrier </label> <input type="text" autocomplete="off"
														name="flightExpense.flightCarrier" id="flightcarrier"
														class="form-control input-sm" placeholder="Flight Carrier"
														value="${flightExpense.flightCarrier}" required >

												</div>
											</div>
											
											<div class="col-sm-4" id="flightdepartureDatediv">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Departure Date </label> <input type="text" autocomplete="off"
														name="flightExpense.departuralDate" id="flightdepartureDate"
														class="form-control input-sm" placeholder="Flight Departure Date"
														 value="${flightExpense.departureDate}" required>

												</div>
											</div>
											<div class="col-sm-4" id="flightarrivDatediv">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Arrival Date </label> <input type="text" autocomplete="off"
														name="flightExpense.arrivalDate" id="flightarrivDate"
														class="form-control input-sm" placeholder="Arrival  Date"
														value="${flightExpense.arrivDate}"  required>

												</div>
											</div>
											
											</div>
											<div class="flightwithorderIdcommon"> 
											<div class="col-sm-4" >

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value="${flightExpense.department}" 
															placeholder="Department type" required>

													</div>
												</div>
												<div class="col-sm-4">
             <div class="form-group">
        <label for="flightExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="flightExpense.expenseCurrency" value="${flightExpense.expenseCurrency}" required>
         <option value="${flightExpense.expenseCurrency}"  selected="selected">${flightExpense.expenseCurrency}</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
											<%-- <div class="col-sm-4">
													<div class="form-group">
								<label for="flightExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="flightcurrency" required="required">
									<option value="" selected="selected" >select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div> --%>
											
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="text" autocomplete="off"
														name="flightExpense.totalAmount" id="flighttotalamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${flightExpense.totalAmount}" required >

												</div>
											</div>
											
													<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 

														<textarea rows="2" cols="2" class="form-control input-sm" name="flightExpense.expenseReason" id="flightexpansereason" placeholder="comments" ><c:out value="${flightExpense.expenseReason}"/></textarea>
												</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													<!-- <input type="text" autocomplete="off"
														name="flightExpense.Reimbursable" id="flightreimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<input type="hidden" value="<s:property value="flightExpense.Reimbursable"/>" id="flightreiumber">
							
														<select class="form-control input-sm"  name="flightExpense.Reimbursable" id="flightreiumber<s:property value="flightExpense.reimbursable"/>"  required>
															
															
															<option value="${flightExpense.reimbursable}" selected="selected">${flightExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>
														

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													
																
													<select class="form-control input-sm"
														name="flightExpense.billable" value="${flightExpense.billable}" required>
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>
												

												</div>
											</div>
											
											
											
												<!-- 			<div class="col-sm-4">
												<div class="form-group">
													<label for="flightExpense.receiptFilename" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img data-ng-src="{{imageSrc}}" >
									</figure>
									<input type="file" id="flightexpensivefile" accept="file_extension|audio/*|video/*|image/*|media_type"  data-ng-file-select="onFileSelect($files)" name="flightExpense.receiptFilename">
									<input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>

								ng-file-select="onFileSelect($files)"
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>

										<div id="filesize">7.6 KB</div>
										<div id="filetype">image/jpeg</div>
										<div id="filedim">90 x 50</div>
									</div>
								</div>
							</div>
												</div>
											</div> -->
											<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimageflight" class="col-sm-5 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="flightExpense.receiptFilename" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" value="${flightExpense.receiptFilename}" name="flightExpense.receiptFilename" > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
											
								</div>		
								
								
								
								
								<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit"  name="flightExpense.id" value="${id}" id="updateflightexpenseForm" 
								class="btn btn-primary btn-lg">Flight Expanse Update</button> <!-- <input type="text" autocomplete="off" onclick="return expensesFormSubmit();
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>	
								
											
											
											</div>
											
										
											
											
											
											
											
											
											
											</form>
										
										</div>
										
										
										
										
										
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>

                                                 <!-- hotel-------------panel  -->
                                                 
                                                 
                                                 
                                                 
                                                 
                                                 
                                                 	<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseThree">  Hotel Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseThree" class="panel-collapse collapse">
										<div class="panel-body">
										<form action="updatehotelexpenseForm" method="post"
					class="form-horizontal" name="hotelexpensesFormId" id="hotelexpensesFormId" enctype="multipart/form-data">
												<div class="col-sm-12">
													<div class="col-sm-4" id="flightnumberdiv">
											<!-- 	<div class="form-group">
												<div><label for="hotel expenses" class="control-label">Have OrderId ?</label></div>
													 <label for="chkYes1">
  																<input type="radio" id="chkYes1" name="chkPinNo1"  value="1" readonly/> Yes
													</label>
													<label for="chkNo1">
 																 <input type="radio" id="chkNo1" name="chkPinNo1"  value="2"/> No
													</label>


												</div> -->
											</div>
											</div>

											
													<%-- <div class="col-sm-12"  id="hotelOrderIdforradio">
											
												<div class="form-group">
													<label for="hotelName" class=" control-label">Order Id </label> <input type="text" autocomplete="off"
														name="hotelExpense.OrderId" id="hotelorderId"
														class="form-control input-sm" placeholder="Order Id"
														value="${hotelExpense.orderId}" >

												</div>
										
											</div> --%>
						 <div class=" hotelwithoutOrderId">					
											
							              <div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Hotel Confirmation Number</label> <input type="text" autocomplete="off"
															name="hotelExpense.HotelConfirmNumber"
															class="form-control input-sm" id="hotelCobfirmNumber"
															value="${hotelExpense.hotelConfirmNumber}" 
															placeholder="Hotel Confirmation Number" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Hotel Name</label> <input type="text" autocomplete="off"
															name="hotelExpense.hotelName"
															class="form-control input-sm" id="hotelname"
															value="${hotelExpense.hotelName}" 
															placeholder="Hotel Name" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="hotelExpense.location"
															class="form-control input-sm" id="hotellocation"
															value="${hotelExpense.location}"
															placeholder="Location " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Room
															Type </label> <input type="text" autocomplete="off"
															name="hotelExpense.roomType"
															class="form-control input-sm" id="hotelRoomtype"
															value="${hotelExpense.roomType}" 
															placeholder="Room Type" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">CheckIn Date</label> <input type="text" autocomplete="off"
															name="hotelExpense.checkInDateTemp"
															class="form-control input-sm" id="hotelcheckindate"
															value="${hotelExpense.checkInDate}"
															placeholder="CheckIn Date" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">CheckOut  Date</label> <input type="text" autocomplete="off"
															name="hotelExpense.checkOutDateTemp"
															class="form-control input-sm" id="hotelcheckoutdate"
															value="${hotelExpense.checkOutDate}" 
															placeholder="CheckOut  Date" required>

													</div>
												</div>
												</div>
												<div class="hotelcommonOrderId">
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="hotelExpense.department"
															class="form-control input-sm" id="hoteldepartmentType"
															value="${hotelExpense.department}" required
															placeholder="Department" required>

													</div>
												</div>
												
											
												<div class="col-sm-4">
													<div class="form-group">
								<label for="hotelExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="hotelExpense.expenseCurrency" id="hotelexpenseCurrency" required>
									<option value="${hotelExpense.expenseCurrency}" selected="selected">${hotelExpense.expenseCurrency}</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>
												
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="text" autocomplete="off"
														name="hotelExpense.totalAmount" id="hoteltotalamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${hotelExpense.totalAmount}" required >

												</div>
											</div>
							
											
												<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="hotelExpense.expenseReason" id="hotelexpensereason"  placeholder="comments" required><c:out value="${hotelExpense.expenseReason}"/></textarea>
													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Reimbursable</label>
														 <!-- <input type="text" autocomplete="off"
															name="hotelExpense.Reimbursable"
															class="form-control input-sm" id="hotelreimbursable"
															value=""
															placeholder="Reimbursable"> -->
															<select class="form-control input-sm"  name="hotelExpense.Reimbursable" id="hotelreimbursable"  required >
															
															<option value="${hotelExpense.reimbursable}" selected="selected">${hotelExpense.reimbursable}</option>
															<option value="yes">Yes</option>
															<option value="no">No</option>
																</select>

													</div>
												</div>
															<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label>
													 
															
																<select class="form-control input-sm"
														name="hotelExpense.Billable" value="${hotelExpense.billable}" required>
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>

												</div>
											</div>
												
															<!-- <div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="busuploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="hotelExpense.receiptFilename">
									<input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>

								ng-file-select="onFileSelect($files)"
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>

										<div id="filesize">7.6 KB</div>
										<div id="filetype">image/jpeg</div>
										<div id="filedim">90 x 50</div>
									</div>
								</div>
							</div>
												</div>
											</div> -->
								<div class="col-sm-4">			
					 <div class="form-group">
						<label for="uploadimagehotel" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img data-ng-src="{{imageSrc}}" >
									</figure>
									<input type="file" id="hotelExpense.receiptFilename" value="${hotelExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" data-ng-file-select="onFileSelect($files)" name="hotelExpense.receiptFilename" > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
					 </div>
					 	
								<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit"   name="hotelExpense.id" value="${id}"  id="updatehotelepenseForm" 
								class="btn btn-primary btn-lg">Hotel Expanse Update</button> <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
					 
					 
											</div>
											
												</form>
											</div>

											<!-- nested colapse happens here -->
											<!-- nested -->

											<!-- /.panel-group -->
											<!-- nested -->
										</div>
										
										
										
										
										
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								
                                                 
                                                 
                                         <!--    car-----------------panel-3     -->
                                           
                                                 	<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseFour">  Car Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseFour" class="panel-collapse collapse">
										<div class="panel-body">
										<form action="updatecarexpenseForm" method="post"
					class="form-horizontal" name="carexpensesFormId" id="carexpensesFormId" enctype="multipart/form-data">
													<%-- <div class="col-sm-12">
											<div  style="text-align:center;">
												<div class="form-group">
													<label for="hotelName" class=" control-label">Order Id </label> <input type="text" autocomplete="off"
														name="hotelOrderHotelData.name"
														class="form-control input-sm" placeholder="Order Id"
														value="">

												</div>
											</div>
											</div>
											
											<center>
				<h2>OR</h2>
			</center>
							 --%>
							 <div class="col-sm-4">

													<div class="form-group">
														<label for="confirmationNumber" class=" control-label"> Confirmation Number </label> 
														<input type="text" autocomplete="off"
															name=carExpense.confirmationNumber
															class="form-control input-sm" id="confirmationNumber"
															value="${carExpense.confirmationNumber}" required
															placeholder="Enter Confirmation Number ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="passengerName" class=" control-label"> Passenger  Name </label> 
														<input type="text" autocomplete="off"
															name=carExpense.passengerName
															class="form-control input-sm" id="passengerName"
															value="${carExpense.passengerName}" required
															placeholder="Enter Passenger Name ">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="supplierPrice" class=" control-label"> Supplier  Price </label> 
														<input type="text" autocomplete="off"
															name=carExpense.supplierPrice
															class="form-control input-sm" id="supplierPrice"
															value="${carExpense.supplierPrice}" required
															placeholder="Enter Supplier Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="basePrice" class=" control-label"> Base  Price </label> 
														<input type="text" autocomplete="off"
															name=carExpense.basePrice
															class="form-control input-sm" id="basePrice"
															value="${carExpense.basePrice}" required
															placeholder="Enter Base Price">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="otherTaxes" class=" control-label"> Other Taxes </label> 
														<input type="text" autocomplete="off"
															name=carExpense.otherTaxes
															class="form-control input-sm" id="otherTaxes"
															value="${carExpense.otherTaxes}" required
															placeholder="Enter Other Taxes ">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="tollOrParkingCharges" class=" control-label"> Toll or Parking Charges </label> 
														<input type="text" autocomplete="off"
															name=carExpense.tollOrParkingCharges
															class="form-control input-sm" id="tollOrParkingCharges"
															value="${carExpense.tollOrParkingCharges}" required
															placeholder="Enter toll/park charges">

													</div>
												</div>		
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="driverAllowanceDay" class=" control-label"> Driver Allowance Day </label> 
														<%-- <input type="text" autocomplete="off" class="form-control input-sm" name="carExpense.driverAllowanceDay   value="${carExpense.driverAllowanceDay}" 
															placeholder="Enter Driver Allowance Day" required> --%>
															<input type="text" autocomplete="off"
															name="carExpense.driverAllowanceDay"
															class="form-control input-sm" id="driverAllowanceDay"
															value="${carExpense.driverAllowanceDay}" required
															placeholder="Enter Driver Allowance Day">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="driverAllowanceNight" class=" control-label"> Driver Allowance Night </label> 
														<input type="text" autocomplete="off"
															name="carExpense.driverAllowanceNight"
															class="form-control input-sm" id="driverAllowanceNight"
															value="${carExpense.driverAllowanceNight}" required
															placeholder="Enter Driver Allowance Night">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="managementFee" class=" control-label">Management Fee</label> 
														<input type="text" autocomplete="off"
															name="carExpense.managementFee"
															class="form-control input-sm" id="managementFee"
															value="${carExpense.managementFee}" required
															placeholder="Enter Management Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label"> Convenience Fee </label> 
														<input type="text" autocomplete="off"
															name="carExpense.convenienceFee"
															class="form-control input-sm" id="convenienceFee"
															value="${carExpense.convenienceFee}" required
															placeholder="Enter Convenience Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="remarks" class=" control-label"> Remarks </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="carExpense.remarks" placeholder="remarks" required><c:out value="${carExpense.remarks}"/></textarea>
															</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label"> Service Tax </label> 
														<input type="text" autocomplete="off"
															name="carExpense.serviceTax"
															class="form-control input-sm" id="serviceTax"
															value="${carExpense.serviceTax}" required
															placeholder="Enter Service Tax ">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="extraKM" class=" control-label"> Extra KM </label> 
														<input type="text" autocomplete="off"
															name="carExpense.extraKM"
															class="form-control input-sm" id="extraKM"
															value="${carExpense.extraKM}" required
															placeholder="Enter extraKM">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="extraHours" class=" control-label"> Extra Hours </label> 
														<input type="text" autocomplete="off"
															name="carExpense.extraHours"
															class="form-control input-sm" id="extraHours"
															value="${carExpense.extraHours}" required
															placeholder="Enter Extra Hours">

													</div>
												</div>
												
											<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label"> Car Company Name </label> 
														<input type="text" autocomplete="off"
															name="carExpense.carCompanyName"
															class="form-control input-sm" id="carcompanyName"
															value="${carExpense.carCompanyName}" 
															placeholder="Car Company name" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> 
														<input type="text" autocomplete="off"
															name="carExpense.location"
															class="form-control input-sm" id="carlocation"
															value="${carExpense.location}" 
															placeholder="Location " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">PickUp </label>
														 <input type="text" autocomplete="off"
															name="carExpense.pickUp"
															class="form-control input-sm" id="carpickup"
															value="${carExpense.pickUp}" 
															placeholder="PickUp Location "  required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">DropOff </label> 
														<input type="text" autocomplete="off"
															name="carExpense.dropOff"
															class="form-control input-sm" id="cardropoff"
															value="${carExpense.dropOff}" 
															placeholder="DropOff Location "  required>

													</div>
												</div>
												
											<div class="col-sm-4">
													<div class="form-group">
								<label for="carExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="carExpense.expenseCurrency" id="carexpenseCurrency" required>
									<option value="${carExpense.expenseCurrency}" selected="selected">${carExpense.expenseCurrency}</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
											
											<div class="col-sm-13">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> 
														<input type="text" autocomplete="off"
															name="carExpense.department"
															class="form-control input-sm" id="cardepartmenttype"
															value="${carExpense.department}" 
															placeholder="Department" required>

													</div>
												</div>
												<div class="col-sm-13">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Travel Date </label> <input type="text" autocomplete="off"
														name="carExpense.travelDateTemp" id="cartravelDate"
														class="form-control input-sm" placeholder="Travel Date "
														value="${carExpense.travelDate}" required>

												</div> 
											</div>
												
											
													</div>
													
													
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="text" autocomplete="off"
														name="carExpense.totalAmount" id="carexpenseamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${carExpense.totalAmount}" required>

												</div>
											</div>
							
												
										
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													<!-- <input type="text" autocomplete="off"
														name="carExpense.Reimbursable" id="carreimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<select class="form-control input-sm"  name="carExpense.Reimbursable" id="carreimbursable"  required>
															<option value="${carExpense.reimbursable}" selected="selected">${carExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
												<select class="form-control input-sm"
														name="carExpense.Billable" value="${carExpense.billable}" required>
														
														<option  selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>
												</div>
											</div>
														
												<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="1" cols="1" class="form-control input-sm"  name="carExpense.expenseReason" placeholder="comments" required><c:out value="${carExpense.expenseReason}"/></textarea>

													</div>
													</div>
												<div class="col-sm-6">
											 <div class="form-group">
						<label for="uploadimagecar" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-7">

							<div class="row">
								<div class="col-sm-9 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="carExpense.receiptFilename" value="${carExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="carExpense.receiptFilename" > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
											
								</div>			
											
												
											<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit"  name="carExpense.id" value="${id}"  id="updatecaressxpenseForm" 
								class="btn btn-primary btn-lg">Car Expanse Update</button> <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>	
											
										</form>
										</div>
										
										
										
										
										
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>
                                                 
                                                 
                                                 
                                         <!--  Train----------------panel-4   -->     
                                         
                                         
                                         
                                                   	<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseFive">  Train Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseFive" class="panel-collapse collapse">
										<div class="panel-body">
										<form action="updatetrainexpenseForm" method="post"
					class="form-horizontal" name="trainexpensesFormId" id="trainexpensesFormId" enctype="multipart/form-data">
										<%-- 			<div class="col-sm-12">
											<div  style="text-align:center;">
												<div class="form-group">
													<label for="hotelName" class=" control-label">Order Id </label> <input type="text" autocomplete="off"
														name="hotelOrderHotelData.name"
														class="form-control input-sm" placeholder="Order Id"
														value="">

												</div>
											</div>
											</div>
											
											<center>
				<h2>OR</h2>
			</center>
							
								 --%>			<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Confirmation Number</label> 
													<input type="text" autocomplete="off"
														name="trainExpense.pnrNumber" id="trainpnr"
														class="form-control input-sm" placeholder="PNR Number"
														value="${trainExpense.pnrNumber}" required>

												</div>
											</div>
											
								 
								 
													 <div class="col-sm-4">

													<div class="form-group">
														<label for="passengerName" class=" control-label"> Passenger  Name </label> 
														<input type="text" autocomplete="off"
															name="trainExpense.passengerName"
															class="form-control input-sm" id="passengerName"
															value="${trainExpense.passengerName}" required
															placeholder="Enter Passenger Name ">

													</div>
												</div>


													<div class="col-sm-4">

													<div class="form-group">
														<label for="remarks" class=" control-label"> Remarks </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="trainExpense.remarks"  placeholder="remarks" required><c:out value="${trainExpense.remarks}"/></textarea>
															

													</div>
												</div>
								 
								 					<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Train Number</label> <input type="text" autocomplete="off"
														name="trainExpense.trainNumber" id="trainnumber"
														class="form-control input-sm" placeholder="Train Number"
														value="${trainExpense.trainNumber}" required>

												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">From Location</label> <input type="text" autocomplete="off"
														name="trainExpense.Fromlocation" id="trainFromLocation"
														class="form-control input-sm" placeholder="From Location"
														value="${trainExpense.fromlocation}" required>

												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">To Location</label> <input type="text" autocomplete="off"
														name="trainExpense.Tolocation" id="trainToLocation"
														class="form-control input-sm" placeholder=" To Location"
														value="${trainExpense.tolocation}" required>

												</div>
											</div>
											<%--  --%>
												
											<!-- <div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Expense Date </label> <input type="text" autocomplete="off"
														name="hotelOrderHotelData.city" id="trainexpensedate"
														class="form-control input-sm" placeholder="Expense Date"
														value="">

												</div>
											</div> -->
												<div class="col-sm-4">
													<div class="form-group">
								<label for="trainExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="trainExpense.expenseCurrency" id="trainexpenseCurrency" required>
									<option value="${trainExpense.expenseCurrency}" selected="selected">${trainExpense.expenseCurrency}</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>
													<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Travel Date</label> <input type="text" autocomplete="off"
														name="trainExpense.travelDateTemp" id="traintravelDate"
														class="form-control input-sm" placeholder="Travel Date"
														value="${trainExpense.travelDate}" required>

												</div>
											</div>
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> 
														<input type="text" autocomplete="off"
															name="trainExpense.department" id="traindeptType"
															class="form-control input-sm" 
															value="${trainExpense.department}" required
															placeholder="Department">

													</div>
												</div>
													
													
													
													<div class="col-sm-4">

													<div class="form-group">
														<label for="basePrice" class=" control-label"> Base  Price </label> 
														<input type="text" autocomplete="off"
															name=trainExpense.basePrice
															class="form-control input-sm" id="basePrice"
															value="${trainExpense.basePrice}" required
															placeholder="Enter Base Price">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="supplierPrice" class=" control-label"> Supplier  Price </label> 
														<input type="text" autocomplete="off"
															name=trainExpense.supplierPrice
															class="form-control input-sm" id="suplierPrice"
															value="${trainExpense.supplierPrice}" required
															placeholder="Enter Supplier Price">

													</div>
												</div>
												
													<div class="col-sm-4">

													<div class="form-group">
														<label for="otherTaxes" class=" control-label"> Other Taxes </label> 
														<input type="text" autocomplete="off"
															name=trainExpense.otherTaxes
															class="form-control input-sm" id="otherTaxes"
															value="${trainExpense.otherTaxes}" required
															placeholder="Enter Other Taxes ">

													</div>
												</div>
												
												

											<div class="col-sm-4">

													<div class="form-group">
														<label for="managementFee" class=" control-label">Management Fee</label> 
														<input type="text" autocomplete="off"
															name=trainExpense.managementFee
															class="form-control input-sm" id="managementFee"
															value="${trainExpense.managementFee}" required
															placeholder="Enter Management Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label"> Convenience Fee </label> 
														<input type="text" autocomplete="off"
															name=trainExpense.convenienceFee
															class="form-control input-sm" id="convenienceFee"
															value="${trainExpense.convenienceFee}" required
															placeholder="Enter Convenience Fee">

													</div>
												</div>
												
										<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label"> Service Tax </label> 
														<input type="text" autocomplete="off"
															name=trainExpense.serviceTax
															class="form-control input-sm" id="serviceTax"
															value="${trainExpense.serviceTax}" required
															placeholder="Enter Service Tax ">

													</div>
												</div>
										
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="text" autocomplete="off"
														name="trainExpense.totalAmount" id="traintotalamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${trainExpense.totalAmount}" required>

												</div>
											</div>
											
											
											
											
												
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="trainExpense.expenseReason" id="trainexpansereason" placeholder="comments" required><c:out value="${trainExpense.expenseReason}"/></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													<!-- <input type="text" autocomplete="off"
														name="trainExpense.Reimbursable" id="trainReimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<select class="form-control input-sm"   name="trainExpense.Reimbursable" id="trainReimbursable" required >
															
															<option value="${trainExpense.reimbursable}" selected="selected">${trainExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													<%-- <select class="form-control input-sm" name="trainExpense.isBillable" id="trainbillable">
															<option value="" selected="selected">select Billable Type</option>
															
																<option value="true">Yes</option>
															
																<option value="false">No</option>
																</select>
 --%>
 														<select class="form-control input-sm"
														name="trainExpense.Billable" value="${trainExpense.billable}"  required>
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>
												</div>
											</div>
											<!-- 	<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="trainuploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="trainExpense.receiptFilename">
									<input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>

								ng-file-select="onFileSelect($files)"
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>

										<div id="filesize">7.6 KB</div>
										<div id="filetype">image/jpeg</div>
										<div id="filedim">90 x 50</div>
									</div>
								</div>
							</div>
												</div>
											</div> -->
											
															<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimagetrain" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="trainExpense.receiptFilename" value="${trainExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="trainExpense.receiptFilename"  > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
											
								</div>	
											
											<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit" name="trainExpense.id" value="${id}" id="updatetrainexpenseForm" 
								class="btn btn-primary btn-lg">Train Expanse Update</button> <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
												</form>
										</div>
										
										
										
										
										
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>
                                                 
                                                 
                                                 
                                                 
                                                      
                                         <!--  Bus---------------panel-5   -->     
                                         
                                         
                                         
                                                   	<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseSix">  Bus Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseSix" class="panel-collapse collapse">
										<div class="panel-body">
										<form action="updatebusexpenseForm" method="post"
					class="form-horizontal" name="busexpensesFormId" id="busexpensesFormId" enctype="multipart/form-data">
											
											
											
												<div class="col-sm-4">

													<div class="form-group">
														<label for="confirmationNumber" class=" control-label"> Confirmation Number </label> 
														<input type="text" autocomplete="off"
															name=busExpense.confirmationNumber
															class="form-control input-sm" id="confirmationNumber"
															value="${busExpense.confirmationNumber}" required
															placeholder="Enter Confirmation Number ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="passengerName" class=" control-label"> Passenger  Name </label> 
														<input type="text" autocomplete="off"
															name=busExpense.passengerName
															class="form-control input-sm" id="passengerName"
															value="${busExpense.passengerName}" required
															placeholder="Enter Passenger Name ">

													</div>
												</div>
											
													<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Bus  Type </label> <input type="text" autocomplete="off"
															name="busExpense.busType"
															class="form-control input-sm" id="bustype"
															value="${busExpense.busType}" 
															placeholder="Bus Type"  required>

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="busExpense.location" id="buslocation" 
															class="form-control input-sm"
															value="${busExpense.location}" 
															placeholder="Location " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">PickUp Location</label> <input type="text" autocomplete="off"
															name="busExpense.pickUp" id="buspickupLocation"
															class="form-control input-sm"
															value="${busExpense.pickUp}" 
															placeholder="PickUp Location "  required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">DropOff Location</label> <input type="text" autocomplete="off"
															name="busExpense.dropOff" id="busdropOffLocation"
															class="form-control input-sm"
															value="${busExpense.dropOff}" 
															placeholder="DropOff Location " required>

													</div>
												</div>
												
																<div class="col-sm-4">
             <div class="form-group">
        <label for="busExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="busExpense.expenseCurrency" required>
         <option value="${busExpense.expenseCurrency}" selected="selected">${busExpense.expenseCurrency}</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">TravelDate</label>
														 <input type="text" autocomplete="off"
															name="busExpense.travelDateTemp" id="bustraveldate"
															class="form-control input-sm"
															value="${busExpense.travelDate}" 
															placeholder="Select Travel Date " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="busExpense.department" id="busdepartmentType"
															class="form-control input-sm"
															value="${busExpense.department}" 
															placeholder="Department"  required>

													</div>
												</div>
						
										<div class="col-sm-4">

													<div class="form-group">
														<label for="supplierPrice" class=" control-label"> Supplier  Price </label> 
														<input type="text" autocomplete="off"
															name=busExpense.supplierPrice
															class="form-control input-sm" id="supplierPrice"
															value="${busExpense.supplierPrice}" required
															placeholder="Enter Supplier Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="basePrice" class=" control-label"> Base  Price </label> 
														<input type="text" autocomplete="off"
															name=busExpense.basePrice
															class="form-control input-sm" id="basePrice"
															value="${busExpense.basePrice}" required
															placeholder="Enter Base Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="managementFee" class=" control-label"> Management Fee   </label> 
														<input type="text" autocomplete="off"
															name=busExpense.managementFee
															class="form-control input-sm" id="managementFee"
															value="${busExpense.managementFee}" required
															placeholder="Enter Management Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label"> Convenience Fee </label> 
														<input type="text" autocomplete="off"
															name=busExpense.convenienceFee
															class="form-control input-sm" id="convenienceFee"
															value="${busExpense.convenienceFee}" required
															placeholder="Enter Convenience Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label"> Service Tax </label> 
														<input type="text" autocomplete="off"
															name=busExpense.serviceTax
															class="form-control input-sm" id="serviceTax"
															value="${busExpense.serviceTax}" required
															placeholder="Enter Service Tax">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="otherTaxes" class=" control-label"> Other Taxes </label> 
														<input type="text" autocomplete="off"
															name=busExpense.otherTaxes
															class="form-control input-sm" id="otherTaxes"
															value="${busExpense.otherTaxes}" required
															placeholder="Enter Other Taxes ">

													</div>
												</div>
												
												
										
										
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="text" autocomplete="off"
														name="busExpense.totalAmount" id="busexpenseamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${busExpense.totalAmount}" required>

												</div>
											</div>
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="busExpense.expenseReason" id="busexpansereason" placeholder="comments" required><c:out value="${busExpense.expenseReason}"/></textarea>

													</div>
													</div>
													<div class="col-sm-4">

													<div class="form-group">
														<label for="remarks" class=" control-label">Remarks </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="busExpense.remarks" id="busexpanseremarks" placeholder="comments" required><c:out value="${busExpense.remarks}"/></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label>
													 <!-- <input type="text" autocomplete="off"
														name="busExpense.Reimbursable" id="busReimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<select class="form-control input-sm"  name="busExpense.Reimbursable" id="busReimbursable" required >
															
															<option value="${busExpense.reimbursable}" selected="selected">${busExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													<%-- <select class="form-control input-sm"  name="busExpense.isBillable" id="busbillable" >
															<option value="" selected="selected">select Billable Type</option>
															
																<option value="true">Yes</option>
															
																<option value="false">No</option>
																</select> --%>
																<select class="form-control input-sm"
														name="busExpense.Billable" value="${busExpense.billable}" required>
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>

												</div>
											</div>
											<!-- 	<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="busuploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="busExpense.receiptFilename">
									<input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>

								ng-file-select="onFileSelect($files)"
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>

										<div id="filesize">7.6 KB</div>
										<div id="filetype">image/jpeg</div>
										<div id="filedim">90 x 50</div>
									</div>
								</div>
							</div>
												</div>
											</div> -->
											
																			<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimagebus" class="col-sm-8control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="busExpense.receiptFilename" value="${busExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="busExpense.receiptFilename" > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
											
								</div>	
											
												<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit"  name="busExpense.id" value="${id}" id="updatebusexpenseForm" 
								class="btn btn-primary btn-lg">Bus Expanse Update</button> <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
											
										</form>
										</div>
										
										
										
										
										
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>       
                                                 
                                                 
                                                 
                                                 
                                                          
                                         <!-- Meal---------------------- panel-6   -->     
                                         
                                         
                                         
                                                   	<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseSeven"> Meal Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseSeven" class="panel-collapse collapse">
										<div class="panel-body">
											
											<form action="updatemealexpenseForm" method="post"
					class="form-horizontal" name="mealexpensesFormId" id="mealexpensesFormId" enctype="multipart/form-data">
											<!-- -->
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Vendor </label>
														 <input type="text" autocomplete="off"
															name="mealExpense.vendor" id="mealVendor"
															class="form-control input-sm"
															value="${mealExpense.vendor}" 
															placeholder="Vendor" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> 
														<input type="text" autocomplete="off"
															name="mealExpense.location" id="mealLocation"
															class="form-control input-sm"
															value="${mealExpense.location}" 
															placeholder="Location " required>

													</div>
												</div>
												<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Expense Date </label> 
													<input type="text" autocomplete="off"
														name="mealExpense.expenseDateTemp" id="mealexpensiveDate"
														class="form-control input-sm" placeholder="Expense Date"
														value="${mealExpense.expenseDate}" required>

												</div>
											</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="mealExpense.department" id="mealDepartment"
															class="form-control input-sm"
															value="${mealExpense.department}" required
															placeholder="Department">

													</div>
												</div> 
																							<div class="col-sm-4">
             <div class="form-group">
        <label for="mealExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="mealExpense.expenseCurrency" required>
         <option value="${mealExpense.expenseCurrency}" selected="selected">${mealExpense.expenseCurrency}</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
											
											
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="text" autocomplete="off"
														name="mealExpense.totalAmount" id="mealexpenseAmount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${mealExpense.totalAmount}" required>

												</div>
											</div>
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2"  class="form-control input-sm" name="mealExpense.expenseReason" id="mealeExpensereason" placeholder="comments" required><c:out value="${mealExpense.expenseReason}"/></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label" >Reimbursable</label>
													
														<select class="form-control input-sm"  name="mealExpense.Reimbursable" id="mealReimbursable"  required>
															
															<option value="${mealExpense.reimbursable}" selected="selected">${mealExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													
																<select class="form-control input-sm"
														name="mealExpense.Billable" value="${mealExpense.billable}"  required>
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>

												</div>
											</div>
												<!-- <div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="mealuploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="mealExpense.receiptFilename">
									<input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>

								ng-file-select="onFileSelect($files)"
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>

										<div id="filesize">7.6 KB</div>
										<div id="filetype">image/jpeg</div>
										<div id="filedim">90 x 50</div>
									</div>
								</div>
							</div>
												</div>
											</div>
											 -->
												
											<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimagemeal" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="mealExpense.receiptFilename" value="${mealExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="mealExpense.receiptFilename" > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
											
								</div>	
									<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit"  name="mealExpense.id" value="${id}"  id="updatemealexpenseForm"
														
								class="btn btn-primary btn-lg">Meal Expanse Update</button> <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
										</form>
										</div>
										
										
										
										
										
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>   
                                                 
                                                 
                                                 
                                                 
                                                             
                                         <!-- Labour---------------- panel-7   -->     
                                         
                                         
                                         
                                                   	<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseEight"> Labor Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseEight" class="panel-collapse collapse">
										<div class="panel-body">
										<form action="updatelaborexpenseForm" method="post"
					class="form-horizontal" name="laborexpensesFormId" id="laborexpensesFormId" enctype="multipart/form-data">
										<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">WorkName </label> <input type="text" autocomplete="off"
															name="laborExpense.workName" id="labourWorkname"
															class="form-control input-sm"
															value="${laborExpense.workName}" required
															placeholder="Work Name">

													</div>
												</div>
										<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="laborExpense.department" id="labourDepartment"
															class="form-control input-sm"
															value="${laborExpense.department}"
															placeholder="Department" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="laborExpense.location" id="labourLocation"
															class="form-control input-sm"
															value="${laborExpense.location}"
															placeholder="Location " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Hours </label> <input type="text" autocomplete="off"
															name="laborExpense.hours" id="labourHours" value="${laborExpense.hours}"
															class="form-control input-sm" id="labourworkinghours" placeholder="Woked Hours" required>
													</div>
												</div>
												
												
																	<div class="col-sm-4">
             <div class="form-group">
        <label for="laborExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="laborExpense.expenseCurrency" required>
         <option value="${laborExpense.expenseCurrency}" selected="selected">${laborExpense.expenseCurrency}</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
             
             								<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Expense Date </label> <input type="text" autocomplete="off"
														name="laborExpense.expenseDateTemp" id="labourexpenseDate" 
														class="form-control input-sm" placeholder="Expense Date"
														value="${laborExpense.expenseDate}" required>

												</div>
											</div>
										
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="text" autocomplete="off"
														name="laborExpense.totalAmount" id="laborexpenseAmount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${laborExpense.totalAmount}" required>

												</div>
											</div>
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="1" cols="1" class="form-control input-sm"  name="laborExpense.expenseReason" placeholder="comments" required><c:out value="${laborExpense.expenseReason}"/></textarea>
														</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													
														<select class="form-control input-sm" name="laborExpense.Reimbursable" id="labourReimbursable"  required>
															
															<option value="${laborExpense.reimbursable}" selected="selected">${laborExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													
																<select class="form-control input-sm"
														name="laborExpense.Billable" value="${laborExpense.billable}">
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>

												</div>
											</div>
												<!-- <div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="labouruploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="laborExpense.receiptFilename">
									<input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>

								ng-file-select="onFileSelect($files)"
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>

										<div id="filesize">7.6 KB</div>
										<div id="filetype">image/jpeg</div>
										<div id="filedim">90 x 50</div>
									</div>
								</div>
							</div>
												</div>
											</div> -->
											
																
											<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimagelabour" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-4 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure> 
									<input type="file" id="laborExpense.receiptFilename"  value="${laborExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="laborExpense.receiptFilename" > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-4">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
											
								</div>	
												<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit"  name="laborExpense.id" value="${id}"  id="updatelaborexpenseForm" 
								class="btn btn-primary btn-lg">Labor Expanse Update</button> <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
										</form>
										</div>
										
										
										
										
										
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>
								
								 <!-- visa----------------START     panel-9   -->
								 
								 	<div class="panel panel-default">
								 	<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseNine">Visa Expenses </a>
										</h4>
									</div>
								 	
								 	<div id="collapseNine" class="panel-collapse collapse">
										<div class="panel-body">
										<form action="updateVisaExpense" method="post"
					class="form-horizontal" name="updateVisaExpense" id="updateVisaExpense" enctype="multipart/form-data">
					
					
					
													<div class="col-sm-4">

													<div class="form-group">
														<label for="confirmationNumber" class=" control-label"> Confirmation Number </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.confirmationNumber
															class="form-control input-sm" id="confirmationNumber"
															value="${visaExpense.confirmationNumber}" required
															placeholder="Enter Confirmation Number ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="passengerName" class=" control-label"> Passenger  Name </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.passengerName
															class="form-control input-sm" id="passengerName"
															value="${visaExpense.passengerName}" required
															placeholder="Enter Passenger Name ">

													</div>
												</div>

												<div class="col-sm-4">

													<div class="form-group">
														<label for="travelDate" class=" control-label"> Travel Date </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.travelDateTemp
															class="form-control input-sm" id="travelvisaDate"
															value="${visaExpense.travelDate}" required
															placeholder="Enter Travel Date ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="discription" class=" control-label"> Discription </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="visaExpense.description"  placeholder="Discription" required><c:out value="${visaExpense.description}"/></textarea>
														</div>
												</div>

													<div class="col-sm-4">

													<div class="form-group">
														<label for="remarks" class=" control-label"> Remarks </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="visaExpense.remarks"  placeholder="remarks" required><c:out value="${visaExpense.remarks}"/></textarea>
															

													</div>
												</div>

												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="supplierPrice" class=" control-label"> Supplier  Price </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.supplierPrice
															class="form-control input-sm" id="supplierPrice"
															value="${visaExpense.supplierPrice}" required
															placeholder="Enter Supplier Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="basePrice" class=" control-label"> Base  Price </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.basePrice
															class="form-control input-sm" id="basePrice"
															value="${visaExpense.basePrice}" required
															placeholder="Enter Base Price">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="otherTaxes" class=" control-label"> Other Taxes </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.otherTaxes
															class="form-control input-sm" id="otherTaxes"
															value="${visaExpense.otherTaxes}" required
															placeholder="Enter Other Taxes ">

													</div>
												</div>
												
												

													<div class="col-sm-4">

													<div class="form-group">
														<label for="managementFee" class=" control-label">Management Fee</label> 
														<input type="text" autocomplete="off"
															name=visaExpense.managementFee
															class="form-control input-sm" id="managementFee"
															value="${visaExpense.managementFee}" required
															placeholder="Enter Management Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label"> Convenience Fee </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.convenienceFee
															class="form-control input-sm" id="convenienceFee"
															value="${visaExpense.convenienceFee}" required
															placeholder="Enter Convenience Fee">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label"> Service Tax </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.serviceTax
															class="form-control input-sm" id="serviceTax"
															value="${visaExpense.serviceTax}" required
															placeholder="Enter Service Tax ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="vfsCharges" class=" control-label"> VFS Charges </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.vfsCharges
															class="form-control input-sm" id="vfsCharges"
															value="${visaExpense.vfsCharges}" required
															placeholder="Enter VFS Charges  ">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="courierCharges" class=" control-label"> Courier Charges</label> 
														<input type="text" autocomplete="off"
															name=visaExpense.courierCharges
															class="form-control input-sm" id="courierCharges"
															value="${visaExpense.courierCharges}" required
															placeholder="Enter Courier Charges ">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="totalInvoiceAmount" class=" control-label"> Total Amount </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.totalInvoiceAmount
															class="form-control input-sm" id="totalInvoiceAmount"
															value="${visaExpense.totalInvoiceAmount}" required
															placeholder="Enter Total Amount ">

													</div>
												</div>
												
												
												<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimage" class="col-sm-6 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-6">

							<div class="row">
								<div class="col-sm-4 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="visaExpense.receiptFilename" accept="file_extension|audio/*|video/*|image/*|media_type" value="${visaExpense.receiptFilename}" ng-file-select="onFileSelect($files)" name="visaExpense.receiptFilename" > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-4">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
											
								</div>	
												
												
												
												
												
												
												<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit"  name="visaExpense.id" value="${id}"  id="visaexpensesubmitupdate" 
								class="btn btn-primary btn-lg">Visa Expanse Update</button> <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
					
					
										</form>
										</div>
										</div>
								 	
								 	
								 	</div>
								 
								 
								  <!-- VISA----------------END     panel-9   -->
								  
								  
								  
								   <!-- INSURENCE----------------START    panel-10  -->
								   <div class="panel panel-default">
								 	<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseTen">Insurance Expenses </a>
										</h4>
										
										
										<div id="collapseTen" class="panel-collapse collapse">
										<div class="panel-body">
										<form action="updateInsurenseExpense" method="post"
					class="form-horizontal" name="createInsurenseExpense" id="createInsurenseExpense" enctype="multipart/form-data">
					
					<div class="col-sm-4">

													<div class="form-group">
														<label for="confirmationNumber" class=" control-label"> Confirmation Number </label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.confirmationNumber
															class="form-control input-sm" id="confirmationNumber"
															value="${insuranceExpense.confirmationNumber}" required
															placeholder="Enter Confirmation Number ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="passengerName" class=" control-label"> Passenger  Name </label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.passengerName
															class="form-control input-sm" id="passengerName"
															value="${insuranceExpense.passengerName}" required
															placeholder="Enter Passenger Name ">

													</div>
												</div>

												<div class="col-sm-4">

													<div class="form-group">
														<label for="travelDate" class=" control-label"> Travel Date </label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.travelDateTemp
															class="form-control input-sm" id="travelinsuranceDate"
															value="${insuranceExpense.travelDate}" required
															placeholder="Enter Travel Date ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="discription" class=" control-label"> Discription </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="insuranceExpense.description"  placeholder="Discription" required><c:out value="${insuranceExpense.description}"/></textarea>
															

													</div>
												</div>

													<div class="col-sm-4">

													<div class="form-group">
														<label for="remarks" class=" control-label"> Remarks </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="insuranceExpense.remarks"  placeholder="remarks" required><c:out value="${insuranceExpense.remarks}"/></textarea>
															

													</div>
												</div>

												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="supplierPrice" class=" control-label"> Supplier  Price </label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.supplierPrice
															class="form-control input-sm" id="supplierPrice"
															value="${insuranceExpense.supplierPrice}" required
															placeholder="Enter Supplier Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="basePrice" class=" control-label"> Base  Price </label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.basePrice
															class="form-control input-sm" id="basePrice"
															value="${insuranceExpense.basePrice}" required
															placeholder="Enter Base Price">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="otherTaxes" class=" control-label"> Other Taxes </label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.otherTaxes
															class="form-control input-sm" id="otherTaxes"
															value="${insuranceExpense.otherTaxes}" required
															placeholder="Enter Other Taxes ">

													</div>
												</div>
												
												

													<div class="col-sm-4">

													<div class="form-group">
														<label for="managementFee" class=" control-label">Management Fee</label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.managementFee
															class="form-control input-sm" id="managementFee"
															value="${insuranceExpense.managementFee}" required
															placeholder="Enter Management Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label"> Convenience Fee </label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.convenienceFee
															class="form-control input-sm" id="convenienceFee"
															value="${insuranceExpense.convenienceFee}" required
															placeholder="Enter Convenience Fee">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label"> Service Tax </label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.serviceTax
															class="form-control input-sm" id="serviceTax"
															value="${insuranceExpense.serviceTax}" required
															placeholder="Enter Service Tax ">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="totalInvoiceAmount" class=" control-label"> Total Amount </label> 
														<input type="text" autocomplete="off"
															name=insuranceExpense.totalInvoiceAmount
															class="form-control input-sm" id="totalInvoiceAmount"
															value="${insuranceExpense.totalInvoiceAmount}" required
															placeholder="Enter Total Amount ">

													</div>
												</div>
													<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimage" class="col-sm-6 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-6">

							<div class="row">
								<div class="col-sm-4 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="insuranceExpense.receiptFilename" value="${insuranceExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="insuranceExpense.receiptFilename" > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-4">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
											
								</div>	
												
												
												<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit"  name="insuranceExpense.id" value="${id}"  id="insuranceexpensesubmitupdate" 
								class="btn btn-primary btn-lg">Insurance Expanse Update</button> <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
										</form>
										</div>
										</div>
									</div>
								 	
								 	
								 	
								 	
								 	</div>
								 
								   
								   
								   
								     <!-- INSURENCE----------------END    panel-10   -->
								
								
								
								
								
								
								
								
								
								
								 <!-- mislanious---------------- panel-8   -->     
                                         
                                         
                                         
                                                   	<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseEleven">Miscellaneous Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseEleven" class="panel-collapse collapse">
										<div class="panel-body">
										<form action="updatemislaniousexpenseForm" method="post"
					class="form-horizontal" name="createMiscellaneousExpense" id="createMiscellaneousExpense" enctype="multipart/form-data">
										<div class="col-sm-4">

													<div class="form-group">
														<label for="confirmationNumber" class=" control-label"> Confirmation Number </label> 
														<input type="text" autocomplete="off"
															name=miscellaneousExpense.confirmationNumber
															class="form-control input-sm" id="confirmationNumber"
															value="${miscellaneousExpense.confirmationNumber}" required
															placeholder="Enter Confirmation Number ">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="passengerName" class=" control-label"> Passenger  Name </label> 
														<input type="text" autocomplete="off"
															name=miscellaneousExpense.passengerName
															class="form-control input-sm" id="passengerName"
															value="${miscellaneousExpense.passengerName}" required
															placeholder="Enter Passenger Name ">

													</div>
												</div>
												 
										
										
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="miscellaneousExpense.location" id="miscellaneousLocation"
															class="form-control input-sm"
															value="${miscellaneousExpense.location}" required
															placeholder="Location ">

													</div>
												</div>
												
											<div class="col-sm-4">
													<div class="form-group">
								<label for="miscellaneousExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="miscellaneousExpense.expenseCurrency" id="mislaniousexpenseCurrency" required>
									<option value="${miscellaneousExpense.expenseCurrency}" selected="selected">${miscellaneousExpense.expenseCurrency}</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>
													<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="miscellaneousExpense.department" id="miscellaneousDepartment"
															class="form-control input-sm"
															value="${miscellaneousExpense.department}" required
															placeholder="Department">

													</div>
												</div>
												
											<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Expense Date </label> <input type="text" autocomplete="off"
													 name="miscellaneousExpense.expenseDateTemp"  id="miscellaneousExpenseDate"
														class="form-control input-sm" placeholder="Expense Date"
														value="${miscellaneousExpense.expenseDate}" required>

												</div>
											</div>
													<div class="col-sm-4">

													<div class="form-group">
														<label for="supplierPrice" class=" control-label"> Supplier  Price </label> 
														<input type="text" autocomplete="off"
															name=miscellaneousExpense.supplierPrice
															class="form-control input-sm" id="supplierPrice"
															value="${miscellaneousExpense.supplierPrice}" required
															placeholder="Enter Supplier Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="basePrice" class=" control-label"> Base  Price </label> 
														<input type="text" autocomplete="off"
															name=miscellaneousExpense.basePrice
															class="form-control input-sm" id="basePrice"
															value="${miscellaneousExpense.basePrice}" required
															placeholder="Enter Base Price">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="otherTaxes" class=" control-label"> Other Taxes </label> 
														<input type="text" autocomplete="off"
															name=miscellaneousExpense.otherTaxes
															class="form-control input-sm" id="otherTaxes"
															value="${miscellaneousExpense.otherTaxes}" required
															placeholder="Enter Other Taxes ">

													</div>
												</div>
												
													<div class="col-sm-4">

													<div class="form-group">
														<label for="managementFee" class=" control-label">Management Fee</label> 
														<input type="text" autocomplete="off"
															name=miscellaneousExpense.managementFee
															class="form-control input-sm" id="managementFee"
															value="${miscellaneousExpense.managementFee}" required
															placeholder="Enter Management Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label"> Convenience Fee </label> 
														<input type="text" autocomplete="off"
															name=miscellaneousExpense.convenienceFee
															class="form-control input-sm" id="convenienceFee"
															value="${miscellaneousExpense.convenienceFee}" required
															placeholder="Enter Convenience Fee">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label"> Service Tax </label> 
														<input type="text" autocomplete="off"
															name=miscellaneousExpense.serviceTax
															class="form-control input-sm" id="serviceTax"
															value="${miscellaneousExpense.serviceTax}" required
															placeholder="Enter Service Tax ">

													</div>
												</div>
												
										
											
										
											
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="text" autocomplete="off"
														name="miscellaneousExpense.totalAmount" id="mislaniousexpenseAmount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${miscellaneousExpense.totalAmount}" required>

												</div>
											</div>
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="remarks" class=" control-label"> Remarks </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="miscellaneousExpense.remarks"  placeholder="remarks" required><c:out value="${miscellaneousExpense.remarks}"/></textarea>
													</div>
												</div>
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="miscellaneousExpense.expenseReason" id="mislaniouseExpenseReason"  placeholder="comments" required><c:out value="${miscellaneousExpense.expenseReason}"/></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													<!-- <input type="text" autocomplete="off"
														name="miscellaneousExpense.Reimbursable" id="mislaniouseReimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
																		<select class="form-control input-sm"   name="miscellaneousExpense.Reimbursable" id="mislaniouseReimbursable"  required>
															
															<option value="${miscellaneousExpense.reimbursable}" selected="selected">${miscellaneousExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>
														

												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													<%-- <select class="form-control input-sm" name="miscellaneousExpense.isBillable" id="mislaniousbillable">
															<option value="" >select Billable Type</option>
															
																<option value="true" selected>Yes</option>
															
																<option value="false">No</option>
																</select> --%>
																<select class="form-control input-sm"
														name="miscellaneousExpense.Billable" value="${miscellaneousExpense.billable}" required>
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>
																

												</div>
											</div>
												<!-- <div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="mislaniousuploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="miscellaneousExpense.receiptFilename">
									<input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>

								ng-file-select="onFileSelect($files)"
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>

										<div id="filesize">7.6 KB</div>
										<div id="filetype">image/jpeg</div>
										<div id="filedim">90 x 50</div>
									</div>
								</div>
							</div>
												</div>
											</div> -->
											
													<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimagemislanious" class="col-sm-4 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-6">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="miscellaneousExpense.receiptFilename" value="${miscellaneousExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="miscellaneousExpense.receiptFilename"  > 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
											
								</div>	
												<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														
														<button type="submit"  name="miscellaneousExpense.id" value="${id}"  id="updatemislaniousexpenseForm" 
								class="btn btn-primary btn-lg">Miscellaneous Expanse Update</button> <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
											</form>
										
										</div>
										
										
										
										
										
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
								</div>
								
								
								
								
								
								
								
                                                 
                                                 
                                                 
                             

								
								<!-- /.panel -->

								<div id="roomscount"></div>
							</div>
							<!-- /.panel-group -->
						</div>
					</div>

					<!-- harsha added colapse ended -->

					

				
			</div>

			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body clearfix">
          <p id="desc">
     
      </p>
         
      </div>
     
    </div>
  </div>
</div>
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	<link rel="stylesheet" href="css/alert.css">
	
	<script type="text/javascript">
	
	
	
	
	
	
	 $(document).ready(function() 
			 { $("#hotelcheckindate").keyup(function () {
			  $(this).next("#hotelcheckoutdate").focus();
			});
			 $("#flightdepartureDate").keyup(function () {
				  $(this).next("#flightarrivDate").focus();
				});
			
				  }); 
			
			
			 
			  $( "#hotelcheckindate" ).datepicker({
			    
			    dateFormat: "dd-mm-yy",
			   
			    onSelect: function( selectedDate ) {
			    	var date2 = $("#hotelcheckindate").datepicker('getDate', '+1d'); 
			  	  date2.setDate(date2.getDate()+1); 
			  	  $( "#hotelcheckoutdate" ).datepicker('setDate', date2);
			  	$( "#hotelcheckoutdate" ).datepicker( "option", "minDate", date2 ); 
			    },
			  onClose: function() {
			      $("#hotelcheckoutdate").focus();
			  }
			  
			  });
			  $( "#hotelcheckoutdate" ).datepicker({      
			   
			    dateFormat: "dd-mm-yy",
			    minDate:1,
			    onSelect: function(selected) { 
			    	           $("#hotelcheckindate").datepicker("option", selected)
			    	        }

			   
			  }); 
			   
			  $( "#flightdepartureDate" ).datepicker({
				    
				    dateFormat: "dd-mm-yy",
				   
				    onSelect: function( selectedDate ) {
				    	var date2 = $("#flightdepartureDate").datepicker('getDate'); 
				  	  date2.setDate(date2.getDate()); 
				  	  $( "#flightarrivDate" ).datepicker('setDate', date2);
				  	$( "#flightarrivDate" ).datepicker( "option", "minDate", date2 ); 
				    },
				  onClose: function() {
				      $("#flightarrivDate").focus();
				  }
				  
				  });
				  $( "#flightarrivDate" ).datepicker({      
				   
				    dateFormat: "dd-mm-yy",
				    minDate:0,
				    onSelect: function(selected) { 
				    	           $("#flightdepartureDate").datepicker("option", selected)
				    	        }

				   
				  }); 
		
	
	
	/*  $('#flightarrivDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });  
	
	 
	 $('#flightdepartureDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });  */
	
	
	
	/*  $('#flightarrivDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });  
	
	 
	 
	 $('#flightdepartureDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });  */
	/*  $('#hotelcheckindate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });   
	 $('#hotelcheckoutdate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });    */
	 $('#hotelexpenseDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });   
	 $('#cartravelDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });  
	 
	 $('#traintravelDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });  
	
	
	 $('#bustraveldate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });   
	 $('#mealexpensiveDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });   
	 $('#labourexpenseDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      }); 
	 $('#travelinsuranceDate ').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });
	 $('#travelvisaDate').datepicker({
        dateFormat: 'dd-mm-yy',
       
        
     });  
	 $('#miscellaneousExpenseDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });  
	 $('#labourHours').timepicker({
         dateFormat: 'HH:mm',
        
         
      });  
	 
	 
		
		
	</script>
	
	
	
	<script type="text/javascript">
	
	
	
	<%--  
	 $(function() {
		 $('#flightOrderId').hide();
	    	$('.withoutOrderId').hide();
	    	$('.flightwithorderIdcommon').hide();
		   $("input[name='chkPinNo']").click(function() {
		     if ($("#chkYes").is(":checked")) {
		    	 $('#flightOrderId').show();
			    	$('.withoutOrderId').hide();
			    	$('.flightwithorderIdcommon').show();
		     } else {
		    	 $('#flightOrderId').hide();
			    	$('.withoutOrderId').show();
			    	$('.flightwithorderIdcommon').show();
		     }
		   });
		 });
	 </script>
	 <script type="text/javascript">
	 $(function() {
		 $('#hotelOrderIdforradio').hide();
	    	$('.hotelwithoutOrderId').hide();
	    	$('.hotelcommonOrderId').hide();
		   $("input[name='chkPinNo1']").click(function() {
		     if ($("#chkYes1").is(":checked")) {
		    	 $('#hotelOrderIdforradio').show();
			    	$('.hotelwithoutOrderId').hide();
			    	$('.hotelcommonOrderId').show();
		     } else {
		    	 $('#hotelOrderIdforradio').hide();
			    	$('.hotelwithoutOrderId').show();
			    	$('.hotelcommonOrderId').show();
		     }
		   });
		 });
	  --%>
	
	</script>
</body>
</html>