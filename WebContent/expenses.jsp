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

	 
 
	<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
 

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
										<form action="flightexpansesForm" method="post"
					class="form-horizontal" name="flightexpensemyForm" id="flightexpensesFormId">
															 
					
					<div class="col-sm-12">
						<div class="col-sm-4" id="flightnumberdiv">
												<div class="form-group">
												<div><label for="hotel address1" class="control-label">Have OrderId ?</label></div>
													 <label for="chkYes">
  																<input type="radio" id="chkYes" name="chkPinNo"  value="m"/> Yes
													</label>
													<label for="chkNo">
 																 <input type="radio" id="chkNo" name="chkPinNo"  value="f"/> No
													</label>


												</div>
											</div>
											</div>
											
	
 
 
 
										<div class="col-sm-12" id="flightOrderId">
											
												<div class="form-group">
													<label for="hotelName" class=" control-label">Order Id </label> <input type="text" autocomplete="off"
														name="flightExpense.OrderId" id="flightorderid"
														class="form-control input-sm" placeholder="Order Id"
														value=""  >

												</div>
											
											</div>
											
										
			 <div class=" withoutOrderId">
			                                     	<div class="col-sm-4" id="flightpnrnumberdiv">
												<div class="form-group">
													<label for="hotel address1" class="control-label">PNR Number</label> <input type="text" autocomplete="off"
														name="flightExpense.pnrNumber" id="flightpnrnumber"
														class="form-control input-sm" placeholder="Flight pnr Number"
														value="" >

												</div>
											</div>
													<div class="col-sm-4" id="flightnumberdiv">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Flight Number</label> <input type="text" autocomplete="off"
														name="flightExpense.flightNumber" id="flightnumber"
														class="form-control input-sm" placeholder="Flight Number"
														value=""  >

												</div>
											</div>
											<div class="col-sm-4" id="flightcarrierdiv">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Flight Carrier </label> <input type="text" autocomplete="off"
														name="flightExpense.flightCarrier" id="flightcarrier"
														class="form-control input-sm" placeholder="Flight Carrier"
														value=""  >

												</div>
											</div>
											
											<div class="col-sm-4" id="flightdepartureDatediv">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Departure Date </label> <input type="text" autocomplete="off"
														name="flightExpense.departuralDate" id="flightdepartureDate"
														class="form-control input-sm" placeholder="Flight Departure Date"
														 >

												</div>
											</div>
											<div class="col-sm-4" id="flightarrivDatediv">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Arrival Date </label> <input type="text" autocomplete="off"
														name="flightExpense.arrivalDate" id="flightarrivDate"
														class="form-control input-sm" placeholder="Arrival  Date"
														value=""  >

												</div>
											</div>
											
											</div>
											<div class="flightwithorderIdcommon"> 
											<div class="col-sm-4" >

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value="" 
															placeholder="Department type" >

													</div>
												</div>
												<div class="col-sm-4">
             <div class="form-group">
        <label for="flightExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="flightExpense.expenseCurrency" >
         <option value=""  selected="selected">select currency</option>
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
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="flightExpense.totalAmount" id="flighttotalamount"
														class="form-control input-sm" placeholder="Total Amount"
														value=""  >

												</div>
											</div>
											
													<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="flightExpense.expenseReason" id="flightexpansereason" placeholder="comments" ></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													<!-- <input type="text" autocomplete="off"
														name="flightExpense.Reimbursable" id="flightreimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<select class="form-control input-sm"  name="flightExpense.Reimbursable" id="flightreimbursable"  >
															
															<option value="" selected="selected">select Reimbursable</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>
														

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													
																
													<select class="form-control input-sm"
														name="flightExpense.Billable">
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
									<input type="file" id="flightexpensivefile" accept="image/*"  data-ng-file-select="onFileSelect($files)" name="flightExpense.receiptFilename">
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
									<input type="file" id="flightExpense.receiptFilename" accept="image/*" ng-file-select="onFileSelect($files)" name="flightExpense.receiptFilename" > 
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
														
														<button type="submit"  name="expense.trip_id" value="${trip_id}" id="flightexpensesubmitnew" 
								class="btn btn-primary btn-lg">Flight Expanse Save</button> <!-- <input type="text" autocomplete="off" onclick="return expensesFormSubmit();
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
										<form action="createhotelexpenseForm" method="post"
					class="form-horizontal" name="hotelexpensesFormId" id="hotelexpensesFormId">
												<div class="col-sm-12">
													<div class="col-sm-4" id="flightnumberdiv">
												<div class="form-group">
												<div><label for="hotel expenses" class="control-label">Have OrderId ?</label></div>
													 <label for="chkYes1">
  																<input type="radio" id="chkYes1" name="chkPinNo1"  value="1"/> Yes
													</label>
													<label for="chkNo1">
 																 <input type="radio" id="chkNo1" name="chkPinNo1"  value="2"/> No
													</label>


												</div>
											</div>
											</div>

											
													<div class="col-sm-12"  id="hotelOrderIdforradio">
											
												<div class="form-group">
													<label for="hotelName" class=" control-label">Order Id </label> <input type="text" autocomplete="off"
														name="hotelExpense.OrderId" id="hotelorderId"
														class="form-control input-sm" placeholder="Order Id"
														value="" >

												</div>
										
											</div>
						 <div class=" hotelwithoutOrderId">					
											
							              <div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Hotel Confirmation Number</label> <input type="text" autocomplete="off"
															name="hotelExpense.HotelConfirmNumber"
															class="form-control input-sm" id="hotelCobfirmNumber"
															value="" 
															placeholder="Hotel Confirmation Number" >

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Hotel Name</label> <input type="text" autocomplete="off"
															name="hotelExpense.hotelName"
															class="form-control input-sm" id="hotelname"
															value="" 
															placeholder="Hotel Name" >

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="hotelExpense.location"
															class="form-control input-sm" id="hotellocation"
															value="${hotelQuotation.roomType}"
															placeholder="Location " >

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Room
															Type </label> <input type="text" autocomplete="off"
															name="hotelExpense.roomType"
															class="form-control input-sm" id="hotelRoomtype"
															value="" 
															placeholder="Room Type" >

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">CheckIn Date</label> <input type="text" autocomplete="off"
															name="hotelExpense.checkInDateTemp"
															class="form-control input-sm" id="hotelcheckindate"
															value="${hotelQuotation.hotelTravelRequest.checkIn}"
															placeholder="CheckIn Date" >

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">CheckOut  Date</label> <input type="text" autocomplete="off"
															name="hotelExpense.checkOutDateTemp"
															class="form-control input-sm" id="hotelcheckoutdate"
															value="" 
															placeholder="CheckOut  Date" >

													</div>
												</div>
												</div>
												<div class="hotelcommonOrderId">
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="hotelExpense.department"
															class="form-control input-sm" id="hoteldepartmentType"
															value="" required
															placeholder="Department" >

													</div>
												</div>
												
											
												<div class="col-sm-4">
													<div class="form-group">
								<label for="hotelExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="hotelExpense.expenseCurrency" id="hotelexpenseCurrency" required>
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>
												
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="hotelExpense.totalAmount" id="hoteltotalamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required >

												</div>
											</div>
							
											
												<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="hotelExpense.expenseReason" id="hotelexpensereason" placeholder="comments" required></textarea>

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
															<select class="form-control input-sm"  name="hotelExpense.Reimbursable" id="hotelreimbursable" required >
															
															<option value="" selected="selected">select Reimbursable</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

													</div>
												</div>
															<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label>
													 
															
																<select class="form-control input-sm"
														name="hotelExpense.Billable">
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
									<input type="file" id="hotelExpense.receiptFilename" accept="image/*" data-ng-file-select="onFileSelect($files)" name="hotelExpense.receiptFilename"> 
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
														
														<button type="submit"   name="expense.trip_id" value="${trip_id}"  id="hotelexpensesubmitnew" 
								class="btn btn-primary btn-lg">Hotel Expanse Save</button> <!-- <input type="text" autocomplete="off"
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
										<form action="createcarxpenseForm" method="post"
					class="form-horizontal" name="carexpensesFormId" id="carexpensesFormId">
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
														<label for="checkInDate" class=" control-label"> Car Company Name </label> 
														<input type="text" autocomplete="off"
															name=carExpense.carCompanyName
															class="form-control input-sm" id="carcompanyName"
															value="" required
															placeholder="Car Company name">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> 
														<input type="text" autocomplete="off"
															name="carExpense.location"
															class="form-control input-sm" id="carlocation"
															value="" required
															placeholder="Location ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">PickUp </label>
														 <input type="text" autocomplete="off"
															name="carExpense.pickUp"
															class="form-control input-sm" id="carpickup"
															value="" required
															placeholder="PickUp Location ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">DropOff </label> 
														<input type="text" autocomplete="off"
															name="carExpense.dropOff"
															class="form-control input-sm" id="cardropoff"
															value="" required
															placeholder="DropOff Location ">

													</div>
												</div>
												<%--  --%>
										<%-- 	<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Expense Date </label> <input type="text" autocomplete="off"
														name="hotelOrderHotelData.city" id="carexpenseDate"
														class="form-control input-sm" placeholder="Expense Date"
														value="">

												</div> 
											</div>--%>
											<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Travel Date </label> <input type="text" autocomplete="off"
														name="carExpense.travelDateTemp" id="cartravelDate"
														class="form-control input-sm" placeholder="Travel Date "
														value="" required>

												</div> 
											</div>
											<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> 
														<input type="text" autocomplete="off"
															name="carExpense.department"
															class="form-control input-sm" id="cardepartmenttype"
															value="" required
															placeholder="Department">

													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
								<label for="carExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="carExpense.expenseCurrency" id="carexpenseCurrency" required>
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>
											
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="carExpense.totalAmount" id="carexpenseamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required>

												</div>
											</div>
							
												
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> <textarea rows="2" cols="2" class="form-control input-sm" name="carExpense.expenseReason" placeholder="comments" required></textarea>

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
															
															<option value="" selected="selected">select Reimbursable</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													<%-- <select class="form-control input-sm"  name="carExpense.isBillable" id="carbillable">
															<option value="" selected="selected">select Billable Type</option>
																<option value="true">Yes</option>
															
																<option value="false">No</option>
																</select>
 --%>
												<select class="form-control input-sm"
														name="carExpense.Billable">
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>
												</div>
											</div>
														<!-- 				<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="busuploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="carExpense.receiptFilename">
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
						<label for="uploadimagecar" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="carExpense.receiptFilename" accept="image/*" ng-file-select="onFileSelect($files)" name="carExpense.receiptFilename"> 
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
														
														<button type="submit"  name="expense.trip_id" value="${trip_id}"  id="carexpensesubmitnew" 
								class="btn btn-primary btn-lg">Car Expanse Save</button> <!-- <input type="text" autocomplete="off"
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
										<form action="createtrainexpenseForm" method="post"
					class="form-horizontal" name="trainexpensesFormId" id="trainexpensesFormId">
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
													<label for="hotel address1" class="control-label">PNR Number</label> 
													<input type="text" autocomplete="off"
														name="trainExpense.pnrNumber" id="trainpnr"
														class="form-control input-sm" placeholder="PNR Number"
														value="" required>

												</div>
											</div>
								 
								 					<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Train Number</label> <input type="text" autocomplete="off"
														name="trainExpense.trainNumber" id="trainnumber"
														class="form-control input-sm" placeholder="Train Number"
														value="" required>

												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">From Location</label> <input type="text" autocomplete="off"
														name="trainExpense.Fromlocation" id="trainFromLocation"
														class="form-control input-sm" placeholder="From Location"
														value="${hotelQuotation.hotelAddress}">

												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">To Location</label> <input type="text" autocomplete="off"
														name="trainExpense.Tolocation" id="trainToLocation"
														class="form-control input-sm" placeholder=" To Location"
														value="${hotelQuotation.hotelAddress}">

												</div>
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Travel Date</label> <input type="text" autocomplete="off"
														name="trainExpense.travelDateTemp" id="traintravelDate"
														class="form-control input-sm" placeholder="Travel Date"
														value="" required>

												</div>
											</div>
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> 
														<input type="text" autocomplete="off"
															name="trainExpense.department" id="traindeptType"
															class="form-control input-sm" 
															value="" required
															placeholder="Department">

													</div>
												</div>
												
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
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>
										
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="trainExpense.totalAmount" id="traintotalamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required>

												</div>
											</div>
											
											
											
											
												
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="trainExpense.expenseReason" id="trainexpansereason" placeholder="comments" required></textarea>

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
															
															<option value="" selected="selected">select Reimbursable</option>
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
														name="trainExpense.Billable">
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
									<input type="file" id="trainExpense.receiptFilename" accept="image/*" ng-file-select="onFileSelect($files)" name="trainExpense.receiptFilename"> 
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
														
														<button type="submit" name="expense.trip_id" value="${trip_id}" id="trainexpensesubmitnew" 
								class="btn btn-primary btn-lg">Train Expanse Save</button> <!-- <input type="text" autocomplete="off"
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
										<form action="createbusexpenseForm" method="post"
					class="form-horizontal" name="busexpensesFormId" id="busexpensesFormId">
											
											
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Bus  Type </label> <input type="text" autocomplete="off"
															name="busExpense.busType"
															class="form-control input-sm" id="bustype"
															value="" required
															placeholder="Bus Type">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="busExpense.location" id="buslocation" 
															class="form-control input-sm"
															value="" required
															placeholder="Location ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">PickUp Location</label> <input type="text" autocomplete="off"
															name="busExpense.pickUp" id="buspickupLocation"
															class="form-control input-sm"
															value="" required
															placeholder="PickUp Location ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">DropOff Location</label> <input type="text" autocomplete="off"
															name="busExpense.dropOff" id="busdropOffLocation"
															class="form-control input-sm"
															value="" required
															placeholder="DropOff Location ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">TravelDate</label>
														 <input type="text" autocomplete="off"
															name="busExpense.travelDateTemp" id="bustraveldate"
															class="form-control input-sm"
															value="" required
															placeholder="Select Travel Date ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="busExpense.department" id="busdepartmentType"
															class="form-control input-sm"
															value="" required
															placeholder="Department">

													</div>
												</div>
																<div class="col-sm-4">
             <div class="form-group">
        <label for="busExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="busExpense.expenseCurrency" required>
         <option value="" selected="selected">select currency</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
												
							<%-- 				
							<div class="col-sm-4">
													<div class="form-group">
								<label for="busExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="busExpense.expenseCurrency" id="busexpenseCurrency" >
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div> --%>
										
										
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="busExpense.totalAmount" id="busexpenseamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required>

												</div>
											</div>
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="busExpense.expenseReason" id="busexpansereason" placeholder="comments" required></textarea>

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
															
															<option value="" selected="selected">select Reimbursable</option>
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
														name="busExpense.Billable">
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
									<input type="file" id="busExpense.receiptFilename" accept="image/*" ng-file-select="onFileSelect($files)" name="busExpense.receiptFilename"> 
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
														
														<button type="submit"  name="expense.trip_id" value="${trip_id}" id="busexpensesubmitnew" 
								class="btn btn-primary btn-lg">Bus Expanse Save</button> <!-- <input type="text" autocomplete="off"
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
											
											<form action="createmealexpenseForm" method="post"
					class="form-horizontal" name="mealexpensesFormId" id="mealexpensesFormId">
											<!-- -->
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Vendor </label>
														 <input type="text" autocomplete="off"
															name="mealExpense.vendor" id="mealVendor"
															class="form-control input-sm"
															value="" required
															placeholder="Vendor">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> 
														<input type="text" autocomplete="off"
															name="mealExpense.location" id="mealLocation"
															class="form-control input-sm"
															value="" required
															placeholder="Location ">

													</div>
												</div>
												<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Expense Date </label> 
													<input type="text" autocomplete="off"
														name="mealExpense.expenseDateTemp" id="mealexpensiveDate"
														class="form-control input-sm" placeholder="Expense Date"
														value="" required>

												</div>
											</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="mealExpense.department" id="mealDepartment"
															class="form-control input-sm"
															value="" required
															placeholder="Department">

													</div>
												</div> 
																							<div class="col-sm-4">
             <div class="form-group">
        <label for="mealExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="mealExpense.expenseCurrency" required>
         <option value="" selected="selected">select currency</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
											
											<%-- <div class="col-sm-4">
													<div class="form-group">
								<label for="mealExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="mealExpense.expenseCurrency" id="mealexpenseCurrency" >
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div> --%>
													
											<!--  -->
											
											
											
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="mealExpense.totalAmount" id="mealexpenseAmount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required>

												</div>
											</div>
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="mealExpense.expenseReason" id="mealeExpensereason" placeholder="comments" required></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label" >Reimbursable</label>
													<!--  <input type="text" autocomplete="off"
														name="mealExpense.Reimbursable" id="mealReimbursable" 
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<select class="form-control input-sm"  name="mealExpense.Reimbursable" id="mealReimbursable"  required>
															
															<option value="" selected="selected">select Reimbursable</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													<%-- <select class="form-control input-sm"  name="mealExpense.isBillable" id="mealsbillable" >
															<option value="" selected="selected">select Billable Type</option>
															
																<option value="true">Yes</option>
															
																<option value="false">No</option>
																</select> --%>
																<select class="form-control input-sm"
														name="mealExpense.Billable">
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
									<input type="file" id="mealExpense.receiptFilename" accept="image/*" ng-file-select="onFileSelect($files)" name="mealExpense.receiptFilename"> 
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
														
														<button type="submit"  name="expense.trip_id" value="${trip_id}"  id="mealexpensesubmitnew"
														
								class="btn btn-primary btn-lg">Meal Expanse Save</button> <!-- <input type="text" autocomplete="off"
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
										<form action="createlaborexpenseForm" method="post"
					class="form-horizontal" name="laborexpensesFormId" id="laborexpensesFormId">
										<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">WorkName </label> <input type="text" autocomplete="off"
															name="laborExpense.workName" id="labourWorkname"
															class="form-control input-sm"
															value="" required
															placeholder="Work Name">

													</div>
												</div>
										<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="laborExpense.department" id="labourDepartment"
															class="form-control input-sm"
															value="${hotelQuotation.roomType}"
															placeholder="Department">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="laborExpense.location" id="labourLocation"
															class="form-control input-sm"
															value="${hotelQuotation.roomType}"
															placeholder="Location ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Hours </label> <input type="text" autocomplete="off"
															name="laborExpense.hours" id="labourHours"
															class="form-control input-sm" id="labourworkinghours" placeholder="Woked Hours">
													</div>
												</div>
												
												<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Expense Date </label> <input type="text" autocomplete="off"
														name="laborExpense.expenseDateTemp" id="labourexpenseDate" 
														class="form-control input-sm" placeholder="Expense Date"
														value="" required>

												</div>
											</div>
																	<div class="col-sm-4">
             <div class="form-group">
        <label for="laborExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="laborExpense.expenseCurrency" required>
         <option value="" selected="selected">select currency</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
											<!--  -->
												
											<%-- <div class="col-sm-4">
													<div class="form-group">
								<label for="laborExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="laborExpense.expenseCurrency" id="laborexpenseCurrency" >
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>
											 --%>
											
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="laborExpense.totalAmount" id="laborexpenseAmount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required>

												</div>
											</div>
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="laborExpense.expenseReason" placeholder="comments" required></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													<!-- <input type="text" autocomplete="off"
														name="laborExpense.Reimbursable" id="labourReimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<select class="form-control input-sm" name="laborExpense.Reimbursable" id="labourReimbursable"  required>
															
															<option value="" selected="selected">select Reimbursable</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													<%-- <select class="form-control input-sm"  name="laborExpense.isBillable" id="laborbillable">
															<option value="" selected="selected">select Billable Type</option>
															
																<option value="true">Yes</option>
															
																<option value="false">No</option>
																</select> --%>
																<select class="form-control input-sm"
														name="laborExpense.Billable">
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
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="laborExpense.receiptFilename" accept="image/*" ng-file-select="onFileSelect($files)" name="laborExpense.receiptFilename"> 
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
														
														<button type="submit"  name="expense.trip_id" value="${trip_id}"  id="laborexpensesubmitnew" 
								class="btn btn-primary btn-lg">Labor Expanse Save</button> <!-- <input type="text" autocomplete="off"
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
								
								
								
								 <!-- mislanious---------------- panel-8   -->     
                                         
                                         
                                         
                                                   	<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseNine">Miscellaneous Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
									<div id="collapseNine" class="panel-collapse collapse">
										<div class="panel-body">
										<form action="createmiscellaneousexpenseForm" method="post"
					class="form-horizontal" name="miscellaneousexpensesFormId" id="miscellaneousexpensesFormId">
										<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="miscellaneousExpense.department" id="miscellaneousDepartment"
															class="form-control input-sm"
															value="" required
															placeholder="Department">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="miscellaneousExpense.location" id="miscellaneousLocation"
															class="form-control input-sm"
															value="" required
															placeholder="Location ">

													</div>
												</div>
												
											<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Expense Date </label> <input type="text" autocomplete="off"
													 name="miscellaneousExpense.expenseDateTemp"  id="miscellaneousExpenseDate"
														class="form-control input-sm" placeholder="Expense Date"
														value="" required>

												</div>
											</div>
											<div class="col-sm-4">
													<div class="form-group">
								<label for="miscellaneousExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="miscellaneousExpense.expenseCurrency" id="mislaniousexpenseCurrency" required>
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>
										
											
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="miscellaneousExpense.totalAmount" id="mislaniousexpenseAmount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required>

												</div>
											</div>
											
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="miscellaneousExpense.expenseReason" id="mislaniouseExpenseReason"  placeholder="comments" required></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													<!-- <input type="text" autocomplete="off"
														name="miscellaneousExpense.Reimbursable" id="mislaniouseReimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
																		<select class="form-control input-sm"  name="miscellaneousExpense.Reimbursable" id="mislaniouseReimbursable"  required>
															
															<option value="" selected="selected">select Reimbursable</option>
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
														name="miscellaneousExpense.Billable">
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
						<label for="uploadimagemislanious" class="col-sm-2 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="miscellaneousExpense.receiptFilename" accept="image/*" ng-file-select="onFileSelect($files)" name="miscellaneousExpense.receiptFilename"> 
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
														
														<button type="submit"  name="expense.trip_id" value="${trip_id}"  id="miscellaneousexpensesubmitnew" 
								class="btn btn-primary btn-lg">Miscellaneous Expanse Save</button> <!-- <input type="text" autocomplete="off"
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
	 $('#miscellaneousExpenseDate').datepicker({
         dateFormat: 'dd-mm-yy',
        
         
      });  
	 $('#labourHours').timepicker({
         dateFormat: 'HH:mm',
        
         
      });  
	 
	 
		function expensesFormSubmit() {
			/* var expensetripId=document.getElementsByName("expense.trip_id")[0].value;
			var flightorderid=document.getElementsByName("flightExpense.OrderId")[0].value;
			var flightpnr=document.getElementsByName("flightExpense.pnrNumber")[0].value;
			var hotelorderid=document.getElementsByName("hotelExpense.OrderId")[0].value;
			var hotelreferenceno=document.getElementsByName("hotelExpense.HotelConfirmNumber")[0].value;
			   
			 
			  if((expensetripId == '') && ((flightorderid == '' || flightpnr =='') || (hotelorderid=='' || hotelreferenceno=='')) ) {
					$("#emailModal").modal('show');
		        	$("#emailModal .modal-body").empty(); 
		        	txt1 = "Select  tripid     ";
		        	txt2 = "Select  OrderId Type     ";
		        	
		        	$("#emailModal .modal-body").append(txt1+"  And "+txt2);
					
					return false;
                
			  }
					else{
						$("#emailModal").modal('show');
			        	$("#emailModal .modal-body").empty(); 
			        	txt1 = "Expense is Successfully Created     ";
			        	
			        	
			        	$("#emailModal .modal-body").append(txt1);
				
					return true;
				}
				 
			    
			     */
			
			
			
			
			
		}
		
	</script>
	<script type="text/javascript">
	
	 $("#uploadimageflight" ).change(function() {
		   var  fileExtension = $('#uploadimageflight').val().substr(($('#uploadimageflight').val().lastIndexOf('.') + 1));
			if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
			{
				 
			}
			else{
				  alert("Please select Image File.");
				  reset_form_element( $('#uploadimageflight') );
				    e.preventDefault();
				  
			}
	});
	 $("#uploadimagehotel" ).change(function() {
		   var  fileExtension = $('#uploadimagehotel').val().substr(($('#uploadimagehotel').val().lastIndexOf('.') + 1));
			if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
			{
				 
			}
			else{
				  alert("Please select Image File.");
				  reset_form_element( $('#uploadimagehotel') );
				    e.preventDefault();
				  
			}
	});
	 $("#uploadimagecar" ).change(function() {
		   var  fileExtension = $('#uploadimagecar').val().substr(($('#uploadimagecar').val().lastIndexOf('.') + 1));
			if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
			{
				 
			}
			else{
				  alert("Please select Image File.");
				  reset_form_element( $('#uploadimagecar') );
				    e.preventDefault();
				  
			}
	});
	 $("#uploadimagetrain" ).change(function() {
		   var  fileExtension = $('#uploadimagetrain').val().substr(($('#uploadimagetrain').val().lastIndexOf('.') + 1));
			if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
			{
				 
			}
			else{
				  alert("Please select Image File.");
				  reset_form_element( $('#uploadimagetrain') );
				    e.preventDefault();
				  
			}
	});
	 $("#uploadimagebus" ).change(function() {
		   var  fileExtension = $('#uploadimagebus').val().substr(($('#uploadimagebus').val().lastIndexOf('.') + 1));
			if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
			{
				 
			}
			else{
				  alert("Please select Image File.");
				  reset_form_element( $('#uploadimagebus') );
				    e.preventDefault();
				  
			}
	});
	 
	 $("#uploadimagemeal" ).change(function() {
		   var  fileExtension = $('#uploadimagemeal').val().substr(($('#uploadimagemeal').val().lastIndexOf('.') + 1));
			if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
			{
				 
			}
			else{
				  alert("Please select Image File.");
				  reset_form_element( $('#uploadimagemeal') );
				    e.preventDefault();
				  
			}
	});
	 $("#uploadimagelabour" ).change(function() {
		   var  fileExtension = $('#uploadimagelabour').val().substr(($('#uploadimagelabour').val().lastIndexOf('.') + 1));
			if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
			{
				 
			}
			else{
				  alert("Please select Image File.");
				  reset_form_element( $('#uploadimagelabour') );
				    e.preventDefault();
				  
			}
	});
	 $("#uploadimagemislanious" ).change(function() {
		   var  fileExtension = $('#uploadimagemislanious').val().substr(($('#uploadimagemislanious').val().lastIndexOf('.') + 1));
			if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
			{
				 
			}
			else{
				  alert("Please select Image File.");
				  reset_form_element( $('#uploadimagemislanious') );
				    e.preventDefault();
				  
			}
	});
	
	
	
	
	</script>
	
	
	<script type="text/javascript">
	
	
	
	 
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
	 
	
	</script>
</body>
</html>