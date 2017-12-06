<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- <html data-ng-app="app"> -->
<html  >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
 
 

<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
	$(function() {
		 
		 $('#success').click(function() {
			 window.location.assign(document.referrer);
			$('#success-alert').hide();
		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});  
	});
</script>

</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="row">
				<div class="col-sm-3 clearfix pull-left">
					<h3>Flight Quotations</h3>
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
							<a href="goFlightRequestEdit?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-edit"></i> Travel Request
							</a> <a
								href="flightOrderQuotationView?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-eye"></i> Travel Request
							</a> <a
								href="goFlightRequestTravelQuotation?flightQuotationRequestId=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-plus"></i> Quotation
							</a>							
		</div>
			</div>		 
			</div> 	 
<%-- 				<div class="col-sm-9 clearfix pull-right " data-placement="top">
					<div class="row">
						<div class="col-sm-5 clearfix pull-left " data-placement="top">
							<a href="goFlightRequestEdit?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-edit"></i> Travel Request
							</a> <a
								href="flightOrderQuotationView?id=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-eye"></i> Travel Request
							</a> <a
								href="goFlightRequestTravelQuotation?flightQuotationRequestId=${flightQuotationRequestId}"
								class="btn btn-success btn-xs" data-toggle="modal"> <i
								class="fa fa-plus"></i> Quotation
							</a>							
						</div>
							<div class="row">
		<div class="col-sm-6 clearfix " data-placement="top">	
		     <a href="goTrips"
					class="btn btn-top-link btn-xs"  >
					 All Trips
				</a>	
				<a href="FlightTravelRequestList"
					class="btn btn-top-link btn-xs"  >
					 Flight Trips
				</a>
				<a href="HotelTravelRequestList"
					class="btn btn-top-link btn-xs"  >
					 Hotel Trips
				</a>
				<a href="CarTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Car Trips
				</a>				
				<a href="BusTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Bus Trips
				</a>
				<a href="TrainTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Train Trips
				</a>
				<a href="VisaTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Visa Trips
				</a>
				<a href="InsuranceTravelRequestList"
					class="btn btn-top-link btn-xs" >
					 Insurance Trips
				</a>
			</div>		
			<div class="col-sm-3 clearfix pull-right" data-placement="top">
				<div class="myDropdown">
				  <button class="btn btn-top-link btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Create Trip
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				    <li><a href="goHotelTravelRequest">Hotel</a></li>
				    <li><a href="goFlightTravelRequest">Flight</a></li>
				    <li><a href="goCarTravelRequest">Car</a></li>
				    <li><a href="goBusTravelRequest">Bus</a></li>
				    <li><a href="goTrainTravelRequest">Train</a></li>
				    <li><a href="goVisaTravelRequest">Visa</a></li>
				      <li><a href="goInsuranceTravelRequest">Insurance</a></li>
				
				   <!--  <li><a href="allexpenses">Expenses</a></li> -->
				  </ul>
				</div>
			</div>
				</div> 
					</div>
				</div> --%>
			</div>
		</section>
		<!-- Main content -->
		<section class="content clearfix">
			<!-- Small boxes (Stat box) -->
			<%-- <div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div> --%>
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
			<div class="form-group">
				<label class="col-sm-2 control-label"> </label>
				<div class="col-sm-12">
					<div class="support">
						<input type="hidden"
							value='<s:property value="%{#session.User.email}"/>'
							id="logineEmail" />

						<div class="col-sm-12">
							<div class="col-sm-2 pull-right">
							<s:if test="flightRequestQuotationList.size!=0">
								<div id="send_emails" class=" btn btn-primary">
									<i class="fa fa-envelope fa-1x"></i> &nbsp; &nbsp;Send Email
									Quotation(s)
								</div>
								</s:if>
							</div>
						</div>
						 
						<s:if test="flightRequestQuotationList.size!=0">
				<div><input type="checkbox" id="select_all" /> Select All 
						</div>
					</s:if>
			 <s:else>
			 	<a href="goFlightRequestTravelQuotation?flightQuotationRequestId=${flightQuotationRequestId}"  class=" btn btn-primary pull-right">Create Quotation</a>
			 </s:else>
						<div class="level1">
							<div id="level1">

								<s:iterator value="flightRequestQuotationList" status="rowCount">
									<form method="post" name="myForm" class="form-inline">
										<input type="hidden" name="id"
											value="<s:property value="id"/>"> <input
											type="hidden" name="travelRequestId${rowCount.count}"
											id="travelRequestId${rowCount.count}"
											value="${flightTravelRequest.id}">
										<s:if test="Booked==true">
											<div class="row clearfix">
												<div class="col-sm-12 message-confirm">
													<span> &nbsp &nbsp &nbsp &nbsp Flight Booking done
														with confirmation number : <s:property
															value="flightOrderRow.orderId"></s:property>
													</span>
												</div>
											</div>
										</s:if>




										<div id="clonedInput1" class="clonedInput">
											<p class="h4">
												Q:
												<s:property value="%{#rowCount.count}" />
												<input class="checkbox" type="checkbox" name="check"
													value="${id}">
											</p>
											<s:if test="%{Booked==true && Hide==true}">
												<a
													href="goFlightOfflineVoucher?id=<s:property value="orderRowId"/>"
													class=" btn btn-primary">Voucher</a>
												<a
													href="addFlightCustomerPaymentTransaction?flightOrderId=<s:property value="orderRowId"/>"
													class=" btn btn-primary">Customer Payment</a>
												<a
													href="addFlightApiProviderPaymentTransaction?flightOrderId=<s:property value="orderRowId"/>"
													class=" btn btn-primary">Supplier Payment</a>
											</s:if>
											<%-- 
														 <s:elseif test="%{Booked==false && Hide==true}">
														<a href="goFlightBookingPage?flightRequestQuotationId=<s:property value="id"/>" class=" btn btn-primary" id="Booked" style="display: none" >Book Now</a>
														  </s:elseif>   --%>
											<s:else>
												<div class="col-sm-12 ">
													<div class="pull-right clearfix">
														<div class="col-sm-3 clearfix" data-placement="top">
															<a
																href="goFlightTravelQuotationEdit?flightQuotationRequestId=<s:property value="id"/>"
																class=" btn btn-primary" id="Booked">Edit</a>
														</div>

														<div class="col-sm-3 clearfix" data-placement="top">
															<div class="myDropdown">
																<button class="btn btn-top-link btn-xs dropdown-toggle"
																	type="button" data-toggle="dropdown">
																	Booking <span class="caret"></span>
																</button>
																<ul class="dropdown-menu">
																	<li><a
																		href="goFlightBookingPage?flightQuotationRequestId=<s:property value="id"/>">Offline</a></li>
																	<li><a href="${onlineUrl}"
																		target="_blank">Online</a>
																		<%-- <a href="#" onclick="onlinesumit(<s:property value="id"/>);">Online</a> --%>
																		<input type="hidden" id="dammytext"
																		value="<s:property value="%{#session.Encryptedid}"/>">
																		<input type="hidden"
																		id="onlineurl<s:property value="id"/>"
																		value="${onlineUrl}"></li>
																</ul>
														</div>
												</div>


														<%-- 
													 
												  <a href="goFlightBookingPage?flightQuotationRequestId=<s:property value="id"/>" class=" btn btn-primary" id="Booked"  >Book Now</a> --%>
													</div>
												</div>
											</s:else>

											<div class="qotationlist clearfix">

												<ul class="clearfix">
													<li><p>
															<b>Origin : </b><span><s:property value="origin" /></span>
														</p></li>
													<li><p>
															<b>Destination : </b><span><s:property
																	value="destination" /></span>
														</p></li>
													<li><p>
															<b>Trip Type : </b>
															<c:if test="${tripType=='R'}">
																<span>Round Trip</span>
															</c:if>
															<c:if test="${tripType=='O'}">
																<span>One Way </span>
															</c:if></li>
													<li><p>
															<b>Departure Date : </b><span><s:property
																	value="transDepartureDate" /></span>
														</p></li>
														<li><p>
																<b>Arrival Date : </b><span><s:property
																		value="transArrivalDate" /></span>
															</p></li>
													<%-- <c:if test="${tripType=='R'}">
														<li><p>
																<b>Arrival Date : </b><span><s:property
																		value="transArrivalDate" /></span>
															</p></li>
													</c:if> --%>
													<li><p>
															<b>Airline : </b><span><s:property value="airline" /></span>
														</p></li>
													<li><p>
															<b>Booking Class : </b><span><s:property
																	value="bookingClassPrefer" /></span>
														</p></li>
													<li><p>
															<b>Passenger Count: </b><span><s:property
																	value="passengerCount" /></span>
														</p></li>
													<li><p>
															<b>Total Amount: </b><span><s:property
																	value="totalAmount" /> ${flightTravelRequest.currency}</span>
														</p></li>


												</ul>
											</div>
										</div>
									</form>

								</s:iterator>


							</div>
						</div>

					</div>
					<!--  support -->
				</div>
			</div>

		</section>
	</div>
	<div class="modal fade" id="emailModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Alert !</h4>
				</div>
				<div class="modal-body">
					<p id="desc"></p>

				</div>

			</div>
		</div>
	</div>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript">
	$(document).ready(function(){ 
		$("#send_emails").click( function(e) {
		 
			var email= $('#logineEmail').val();
			var travelRequestId = $('#travelRequestId1').val();
			
	        var checkValues = $('input[name=check]:checked').map(function()
	        {
	            return $(this).val();
	        }).get();
	        console.debug(checkValues);
	        if(checkValues.length==0){
	    		//alert("Please select quotation(s).");
	    		 $("#emailModal").modal('show');
	    		 $("#emailModal #desc").text();
	        	 $("#emailModal #desc").text("Please select quotation(s).");
	    	}
	        
	        else{ 
	        	$("#emailModal").modal('show');
	        	$("#emailModal .modal-body").empty(); 
	        	txt1 = "<div id='mailsform'><p>You have selected  "+email+", to send quotaions , If you want to send quotations to alternative emails enter valid emails !</p><span style='color:red;font-size:10px'>* Please separate emails with semicolon </span><div class='form-group clearfix'><label class='col-sm-2 control-label'>To:</label><div class='col-sm-8'><input type='text' value='' class='form-control input-sm' id='alternativeToEmail'/></div></div><div class='form-group clearfix'><label class='col-sm-2 control-label'>CC:</label><div class='col-sm-8'><input type='text' value='' class='form-control input-sm' id='alternativeCCEmail'/></div></div><div class='col-sm-12 clearfix'><div class='col-sm-12 text-center'><div id='send_email' class=' btn btn-primary'> <i class='fa fa-envelope fa-1x'></i> Send Quotation(s)</div></div></div></div>";
	        	$("#emailModal .modal-body").append(txt1);   
	        	
	        }
		
		
		$("#send_email").click( function() {
			/* var checkValues = $('input[name=check]:checked').map(function()
			        {
			            return $(this).val();
			        }).get(); */
			//var travelRequestId = $('#travelRequestId1').val();
	       	//var email= $('#logineEmail').val(); 
	        var alternativeToEmail= $('#alternativeToEmail').val();
	        var alternativeCCEmail= $('#alternativeCCEmail').val();
	        if((alternativeToEmail!="" && alternativeCCEmail!="")||alternativeToEmail!="" ||alternativeCCEmail!=""){
	        	
	        	$.ajax({
	          	  
		                url: 'insertFlightQuotationAlternativeMailData?travelRequestId='+travelRequestId+'&selectedQuotaionList='+checkValues+'&toEmail='+alternativeToEmail+'&CCEmail='+alternativeCCEmail,
		                type: 'post',
		                success:function(data){
		                if(data.dataResponseJson.status=='1')
		                	{
		                	$("#mailsform").hide();
		                	var txt2 = $("<p class='text-center clearfix' style='color:green'></p>").text("We have sent selected quotaions to email(s), Please check your email!");
		                	 $("#emailModal .modal-body").append( txt2);
		                	
		                	$('#alternativeToEmail').val('');
		                	$('#alternativeCCEmail').val('');
		                	setTimeout(function() {$('#emailModal').modal('hide');}, 2000);
		                	
		                	}
		                else{
		                	$("#mailsform").hide();
		                	var txt2 = $("<p class='text-center clearfix' style='color:red'></p>").text(" We could not send email , please check back again ");
		                	 $("#emailModal .modal-body").append( txt2);
		                	 setTimeout(function() {$('#emailModal').modal('hide');}, 2000);
		                }
		                }
		            }); 
	        }
	        	else{
	        	
	        	 $.ajax({
	        		   url: 'insertFlightQuotationMailData?travelRequestId='+travelRequestId+'&selectedQuotaionList='+checkValues,
		                type: 'post',
		                success:function(data){
		                if(data.dataResponseJson.status=='1')
		                	{ 
		                	$("#mailsform").hide();
		                	var txt2 = $("<p class='text-center clearfix' style='color:green'></p>").text(" We have sent selected quotaions to "+email+", Please check your email!");
		                	 $("#emailModal .modal-body").append( txt2);
		                	$('#alternativeToEmail').val('');
		                	$('#alternativeCCEmail').val('');
		                	
		                	setTimeout(function() {$('#emailModal').modal('hide');}, 2000);
		                	}
		                else{
		                	$("#mailsform").hide();
		                	var txt2 = $("<p class='text-center clearfix' style='color:red'></p>").text(" We could not send email , please check back again ");
		                	 $("#emailModal .modal-body").append( txt2);
		                	 setTimeout(function() {$('#emailModal').modal('hide');}, 2000);
		                }
		                }
		            });
	        	}
	}); 
		}); 
		  }); 
  
$("#select_all").change(function(){  //"select all" change 
    var status = this.checked; // "select all" checked status
    $('.checkbox').each(function(){ //iterate all listed checkbox items
        this.checked = status; //change ".checkbox" checked status
    });
});

$('.checkbox').change(function(){ //".checkbox" change 
    //uncheck "select all", if one of the listed checkbox item is unchecked
    if(this.checked == false){ //if this item is unchecked
        $("#select_all")[0].checked = false; //change "select all" checked status to false
    }
    
    //check "select all" if all checkbox items are checked
    if ($('.checkbox:checked').length == $('.checkbox').length ){ 
        $("#select_all")[0].checked = true; //change "select all" checked status to true
    }
});

 
		$('body').on('focus',".datepicker_recurring_start", function(){
			
		    $(this).datepicker({
		    	dateFormat : "dd-mm-yy"
		    }
		    );
		});
		 /* function onlinesumit(id){
			 var url = $('#onlineurl'+id).val();
			 //alert(url);
			 var dammytext = $('#dammytext').val();
			 var mapForm = document.createElement("form");
			    mapForm.target = "_blank";
			    mapForm.method = "POST"; // or "post" if appropriate
			    mapForm.action = url;

			 var mapInput = document.createElement("input");
			    mapInput.type = "text";
			    mapInput.name = "dammytext";
			    mapInput.value = dammytext;
			    mapForm.appendChild(mapInput);

			    document.body.appendChild(mapForm);

			    // map = window.open();
			    mapForm.submit();
			if (map) {
			   // mapForm.submit();
			   
			} else {
			  //  alert('You must allow popups for this map to work.');
			}
			 } */
			
  </script>
</body>

</html>