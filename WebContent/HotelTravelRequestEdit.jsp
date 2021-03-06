<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Designation</title>
<%-- <script src="js/angular.js" type="text/javascript"></script>
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">
 <style type="text/css">
   .ui-autocomplete {
              max-height: 200px;
               width:auto;
              overflow-y: auto;
              /* prevent horizontal scrollbar */
              overflow-x: auto;
              font-family: "Trebuchet MS","Helvetica","Arial","Verdana","sans-serif";
			font-size:1em;
              /* add padding to account for vertical scrollbar */
              
      }
      /* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
      * html .ui-autocomplete {
          height:  200px;
          width: auto;
      }
 
 </style>
</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<div class="row">
		<div class="col-sm-3 clearfix pull-left">
		<h4> Hotel Travel Request Edit </h4>
		</div>
		
					<div class="col-sm-9 clearfix pull-right " data-placement="top">
		<div class="row">
			<div class="col-sm-12 clearfix pull-right " data-placement="top">
				<div class="row">
					<div class="col-sm-10 clearfix " data-placement="top">
						<a href="goTrips" class="btn btn-top-link btn-xs">
							All Trips </a>
						<a href="HotelTravelRequestList" class="btn btn-top-link btn-xs">
							Hotel Trips </a> <a href="FlightTravelRequestList"
							class="btn btn-top-link btn-xs"> Flight Trips </a> <a
							href="CarTravelRequestList" class="btn btn-top-link btn-xs">
							Car Trips </a> <a href="BusTravelRequestList"
							class="btn btn-top-link btn-xs"> Bus Trips </a> <a
							href="TrainTravelRequestList" class="btn btn-top-link btn-xs">
							Train Trips </a> <a href="VisaTravelRequestList"
							class="btn btn-top-link btn-xs"> Visa Trips </a> <a
							href="InsuranceTravelRequestList" class="btn btn-top-link btn-xs">
							Insurance Trips </a>
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
								<li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>									
							</ul>
						</div>
					</div>
				</div>
			</div>	  
			</div>	
			<div class="row">
				<div class="col-sm-12">
				</div>
			</div>
		<div class="row">
		<div class="col-sm-12 clearfix pull-left " data-placement="top">
				<a href="hotelTravelRequestEdit?id=<s:property value="id"/>"
					class="btn btn-success btn-xs" data-toggle="modal">
					 Edit
				</a>
				<a href="hotelOrderQuotationView?id=<s:property value="id"/>"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-eye"></i> Travel Request
				</a>
				
				<a href="goHotelRequestTravelQuotation?hotelQuotationRequestId=<s:property value="id"/>"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-plus"></i> Quotation
				</a>
				<a href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=<s:property value="id"/>"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-list"></i> Quotations
				</a>
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
			<div class="row col-sm-12"  >

				<form action="HotelTravelRequestUpdate" method="post" class="form-horizontal" name="myForm" id="myfform">
					 <input id="cityUrl" type="hidden" value="<s:text name="global.hotelCitiesUrl" ></s:text>">
					 <input  type="hidden" name="id" value="${hotelQuotationRow.id}"> 
					 
					 <div class="companysetup clearfix"> 
					<div class="companyset-heading"> 
						<div class="companyset-icon">  
								 <b>Hotel Travel Details Edit</b>  
						</div> 
					</div>
					<div class="inner-compreg clearfix">
					<div class="col-sm-2">
												<div class="form-group">
						<label for="hotelName" class="  control-label">Title</label>
						
						<select  class="form-control input-sm"  required="required"  name="title">
						<c:if test="${hotelQuotationRow.title=='Mr'}">
													<option value="Mr" selected="selected">Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
						</c:if>
						<c:if test="${hotelQuotationRow.title=='Mrs'}">
													<option value="Mr" >Mr</option>
													<option value="Mrs" selected="selected">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
						</c:if>
						<c:if test="${hotelQuotationRow.title=='Miss'}">
						<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss" selected="selected">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master">Master</option>
						</c:if>
						<c:if test="${hotelQuotationRow.title=='Ms'}">
						<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms" selected="selected">Ms</option>
													<option value="Master">Master</option>
						</c:if>
						<c:if test="${hotelQuotationRow.title=='Master'}">
						<option value="Mr" >Mr</option>
													<option value="Mrs">Mrs</option>
													<option value="Miss">Miss</option>
													<option value="Ms">Ms</option>
													<option value="Master" selected="selected">Master</option>
						</c:if>
						 
						</select>
						</div>
					</div>
					
					<div class="col-sm-2">
					
					<div class="form-group">
						<label for="hotelName" class="  control-label">FirstName</label>
						 
							<input type="text" autocomplete="off" name="firstName"
								class="form-control input-sm" required="required"
								placeholder="First Name"  value="${hotelQuotationRow.firstName}">

						</div>
					</div>
					<div class="col-sm-2">
					<div class="form-group">
						<label for="hotelName" class="control-label">LastName</label> 
							<input type="text" autocomplete="off" name="lastName"
								class="form-control input-sm" required="required"
								placeholder="Last Name"  value="${hotelQuotationRow.lastName}">

						</div>
					</div>
					<div class="col-sm-2">
				 <div class="form-group">
						<label for="hotelName" class=" control-label">Alternative Email
							 </label>
						 
							<input type="text" autocomplete="off" name="alternativeEmail"
								class="form-control input-sm"  required="required"
								placeholder="Alternative Email"  value="${hotelQuotationRow.alternativeEmail}">

						</div>
					</div>  
					<div class="col-sm-2">
					<div class="form-group">
						<label for="hotelName" class=" control-label">TR
							No </label>
						 
							<input type="text" autocomplete="off" name="TRNo"
								class="form-control input-sm" required="required"
								placeholder="Travel Request No" value="${hotelQuotationRow.TRNo}">

						</div>
					</div>
<div class="col-sm-2">
					<div class="form-group">
						<label for="hotelName" class=" control-label">Entity
						</label>
						 
							<input type="text" autocomplete="off" name="entity"
								class="form-control input-sm" required="required"
								placeholder="Entity" value="${hotelQuotationRow.entity}">

						</div>
					</div>
					 
				<div class="col-sm-2">
					   <div class="form-group">
						<label for="checkOutDate" class=" control-label">City
						</label>
						 
							<input type="text" autocomplete="on"   value="${hotelQuotationRow.city}"  id="city"    name="city"  class="form-control input-sm"  required="required" placeholder="City">
						
						</div>
					</div>  
					<div class="col-sm-2">
					<div class="form-group">
						<label for="checkInDate" class=" control-label">CheckIn
							Date </label>
					 
							<input type="text" autocomplete="off" id="checkInDate"
								name="checkIn" class="form-control input-sm" required="required"
								placeholder="checkInDate"  value="${hotelQuotationRow.checkIn}">

						</div>
					</div>
<div class="col-sm-2">
					<div class="form-group">
						<label for="checkOutDate" class=" control-label">CheckOut
							Date </label>
						 
							<input type="text" autocomplete="off" id="checkOutDate"
								name="checkOut" class="form-control input-sm"
								required="required" placeholder="checkOutDate" value="${hotelQuotationRow.checkOut}">

						</div>
					</div>
  
					<c:if test="${fieldName[0]!=null}">		
					<div class="col-sm-2">						
									<div class="form-group">
										<label for="empCode" class=" control-label" > ${fieldName[0]}</label> 
											 <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField1}"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField1}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[1]!=null}">
							<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> ${fieldName[1]}</label> <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField2}"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField2}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[2]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> ${fieldName[2]}</label> <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField3}"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField3}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[3]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> ${fieldName[3]}</label> <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField4}"
											class="form-control input-sm"
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField4}" >
									</div>
								</div>
							</c:if>
							<c:if test="${fieldName[4]!=null}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> ${fieldName[4]}</label>  <input type="text"
											autocomplete="off" name="${configTripDetailsModel.manualField5}"
											class="form-control input-sm" 
											placeholder="Enter department Details" value="${configTripDetailsModel.manualField5}" >
									</div>
								</div>
							</c:if>
						
							<c:if test="${rmConfigModel.department}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label"> Department</label>  <input type="text"
											autocomplete="off" name="configTripDetailsModel.department"
											class="form-control input-sm" required="required"
											placeholder="Enter department Details" value="<s:property value="configTripDetailsModel.department"/>" >
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.approverName}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label">Approver Name</label>  <input type="text"
											autocomplete="off" name="configTripDetailsModel.approverName"
											class="form-control input-sm" required="required"
											placeholder="Enter approverName Details" value="${configTripDetailsModel.approverName}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.location}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label">location</label>  <input type="text"
											autocomplete="off" name="configTripDetailsModel.location"
											class="form-control input-sm" required="required"
											placeholder="Enter location Details" value="${configTripDetailsModel.location}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.bussinessUnit}">
							<div class="col-sm-2">
									<div class="form-group">
										<label for="empCode" class=" control-label">Bussiness Unit</label>  <input type="text"
											autocomplete="off"
											name="configTripDetailsModel.bussinessUnit"
											class="form-control input-sm" required="required"
											placeholder="Enter bussiness Unit Details" value="${configTripDetailsModel.bussinessUnit}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.projectCode}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="projectCode" class=" control-label"> Project Code</label>  <input
											type="text" autocomplete="off"
											name="configTripDetailsModel.projectCode"
											class="form-control input-sm" required="required"
											placeholder="Enter projectCode Details" value="${configTripDetailsModel.projectCode}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.reasonForTravel}">
							<div class="col-sm-2">
									<div class="form-group">
										<label for="reasonForTravel" class=" control-label">Reason For Travel</label>  <input
											type="text" autocomplete="off"
											name="configTripDetailsModel.reasonForTravel"
											class="form-control input-sm" required="required"
											placeholder="Enter reasonForTravel Details" value="${configTripDetailsModel.reasonForTravel}">
									</div>
								</div>
							</c:if>
							<c:if test="${rmConfigModel.billNonBill}">
								<div class="col-sm-2">
									<div class="form-group">
										<label for="billNonBill" class=" control-label">Billing/Non Billing</label>  <input
											type="text" autocomplete="off"
											name="configTripDetailsModel.billNonBill"
											class="form-control input-sm" required="required"
											placeholder="Enter billNonBill Details" value="${configTripDetailsModel.billNonBill}">
									</div>
								</div>
							</c:if>	
					</div>
					</div>
					 <div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<!-- <button type="submit" class="btn btn-primary btn-lg">Update</button> -->
							<button id="hotelSubmitUpdate" type="button" class="btn btn-primary btn-lg">Update</button>
						</div>
					</div>

					 
				</form>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	<script src="js/city.js"></script>
	 
	<script>
	var s  = $( "#checkInDate" ).val();
	  
	   $( "#checkInDate" ).datepicker({
	      changeMonth: true,  
	      changeYear:true,
	      dateFormat: "dd-mm-yy",
	      minDate:s,
	      onSelect: function( selectedDate ) {
	         var dt = $.datepicker.parseDate('dd-mm-yy', selectedDate);
	             dt.setDate(dt.getDate() + 1);
	              
	        $( "#checkOutDate" ).datepicker( "option", "minDate", dt );
	       
	      }
	    });
	    $( "#checkOutDate" ).datepicker({      
	      changeMonth: true,   
	      changeYear:true,
	      dateFormat: "dd-mm-yy"
	    });
 </script>
  
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"HotelTravelRequestList";
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
 });
 
	$(document).ready(function(){
		  
		   $('#hotelSubmitUpdate').click(function(){
			   $("#myfform").valid();
			   if($("#myfform").valid()){
				   document.getElementById("myfform").submit();
			   }
		   });
		 		   
		   $("#myfform").validate({
			   submitHandler: function (form) {  
		            
		            return false;
		        },
		        highlight: function(element, errorClass, validClass) { 
		            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-ok').addClass('glyphicon-remove');
		            $(element).addClass(errorClass).removeClass(validClass);
		            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		          },
		          success: function(element) {
		            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-remove').addClass('glyphicon-ok');
		         element.closest('.form-group').removeClass('has-error').addClass('has-success');
		            $(element).remove();
		          }
		   })
	});
	</script>
</body>
</html>