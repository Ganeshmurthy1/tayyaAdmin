<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script>
 --%>
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
			<div class="col-sm-2 clearfix pull-left">
				<h4><b>Hotel Travel Request Details</b></h4>
			</div>
			<div class="col-sm-10 clearfix pull-right " data-placement="top">
		<div class="row">
			<div class="col-sm-12 clearfix pull-right " data-placement="top">
				<div class="row">
					<div class="col-sm-8 clearfix " data-placement="top">
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
							<a href="MiscellaneousTravelRequestList"
									class="btn btn-top-link btn-xs" >
									 Miscellaneous Trips
								</a>
					</div>
					<div class="col-sm-2 clearfix pull-left"  >
						<div class="myDropdown">
							<button class="btn btn-top-link btn-xs dropdown-toggle"
								type="button" data-toggle="dropdown">
								Create Quick Trip <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="goQuickLinkOfflibeHotelBookingPage">Hotel</a></li>
								<li><a href="goQuickLinkOfflibeFlightBookingPage">Flight</a></li>
								<!-- <li><a href="goCarTravelRequest">Car</a></li>
								<li><a href="goBusTravelRequest">Bus</a></li>
								<li><a href="goTrainTravelRequest">Train</a></li>
								<li><a href="goVisaTravelRequest">Visa</a></li>
								<li><a href="goInsuranceTravelRequest">Insurance</a></li>	
								<li><a href="goMiscellaneousRequest">Miscellaneous</a></li>		 -->					
							</ul>
						</div>
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
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-edit"></i> Travel Request
						</a>						
						<a
							href="goHotelRequestTravelQuotation?hotelQuotationRequestId=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-plus"></i> Quotation
						</a> <a
							href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=<s:property value="id"/>"
							class="btn btn-success btn-xs" data-toggle="modal"> <i
							class="fa fa-list"></i> Quotation
						</a>
		</div>
			</div>			
			</div>
		</div>
		</section>	
		<!-- Main content -->
		<section class="content clearfix">
			<!-- Small boxes (Stat box) -->
			<input type="hidden" value='<s:property value="%{#session.User.email}"/>' id="logineEmail"/>
			
			<div class="col-sm-12">
			<div class="col-sm-2 pull-right">
			
			<s:if test="hotelRequestQuotationList.size!=0">
			<div id="send_emails" class=" btn btn-primary"> <i class="fa fa-envelope fa-1x"></i> &nbsp; &nbsp;Send Email Quotation(s)</div>
			</s:if>
			 
			</div>	
			</div>
			
			<%-- <div class="col-sm-12">
			<div class="col-sm-2 pull-right" data-placement="top">
			  <div class="alternative">
				  <button class="btn btn-top-link btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Alternative <i class="fa fa-envelope fa-1x"></i>
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				 <li>   To:<span style="color:red;font-size:10px">* Please separate email with semicolon </span> <a> <input type="text" value='' id="alternativeToEmail"/></a></li>
				   <li> CC:<span style="color:red;font-size:10px">* Please separate email with semicolon </span><a> <input type="text" value='' id="alternativeCCEmail"/></a></li>
				  </ul>
				</div> 
			</div>	
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
					<s:if test="hotelRequestQuotationList.size!=0">
				<div><input type="checkbox" id="select_all" /> Select All 
						</div>
					</s:if>
			 <s:else>
			 	<a href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${hotelQuotationRequestId}"  class=" btn btn-primary pull-right">Create Quotation</a>
			 </s:else>
						
						<input
							id="hotelQuotationPdfUrl" type="hidden"
							value="<s:text name="global.hotelQuotationPdfUrl" ></s:text>">
						<input id="userid" type="hidden"
							value="<s:property value="%{#session.User.id}"/>">
						<div class="level1">
							<div id="level1">
								<s:iterator value="hotelRequestQuotationList" status="rowCount">
									<form action="hotelRequestQuotationUpdate" method="post"
										name="myForm" class="form-inline clearfix">
									 
										<input type="hidden" name="id"
											value="<s:property value="id"/>">
											<input type="hidden" name="travelRequestId${rowCount.count}" id="travelRequestId${rowCount.count}"
											value="${hotelTravelRequest.id}">
										<s:if test="Booked==true">
												<div class="row clearfix">
												<div class="col-sm-12 message-confirm">
														<span>  Hotel Booking done with
															confirmation number : <s:property value="hotelOrderRow.orderReference"></s:property>
														</span>
												</div>
												</div>
											</s:if>	
										<div id="clonedInput1" class="clonedInput">
											<div class="row clearfix ">
											<div class="col-sm-12">
												 <input class="checkbox" type="checkbox" name="check" value="${id}"> &nbsp; 
												 Q: <s:property value="%{#rowCount.count}" />
											</div>
											</div>
											
											<div class="row clearfix pull-right">
											<div class="col-sm-12">
											<s:if test="%{Booked==true && Hide==true}">
												<a
													href="goHotelBookingPage?hotelRequestQuotationId=<s:property value="id"/>"
													class=" btn btn-primary" id="Booked" style="display: none">Booked</a>
												<a
													href="getHotelOfflineVoucher?hotelOrderId=<s:property value="orderRowId"/>"
													class=" btn btn-primary">Voucher</a>
												 
													<a
													href="addCustomerPaymentTransaction?hotelOrderId=<s:property value="orderRowId"/>"
													class=" btn btn-primary">Customer Payment(s)</a>
													
												
												<a
													href="addApiProviderPaymentTransaction?hotelOrderId=<s:property value="orderRowId"/>"
													class=" btn btn-primary">Supplier Payment(s)</a>
											</s:if>
											<s:elseif test="%{Booked==false && Hide==true}">
												<a
													href="goHotelBookingPage?hotelRequestQuotationId=<s:property value="id"/>"
													class=" btn btn-primary" id="Booked" style="display: none">Book
													Now</a>
											</s:elseif>
											<s:else>
														<div class="col-sm-12">
															<div class="pull-right clearfix">
																<div class="col-sm-3 clearfix" data-placement="top">
																	<a
																		href="HotelQuotationEdit?hotelQuotationRequestId=<s:property value="id"/>"
																		class=" btn btn-primary" id="Booked">Edit</a>
																</div>
																<div class="col-sm-3 clearfix" data-placement="top">
																	<div class="myDropdown">
																		<button
																			class="btn btn-top-link btn-xs dropdown-toggle"
																			type="button" data-toggle="dropdown">
																			Booking <span class="caret"></span>
																		</button>
																		<ul class="dropdown-menu">
																			<li><a
																				href="goHotelBookingPage?hotelRequestQuotationId=<s:property value="id"/>">Offline</a></li>
																			<li>
																			<a href="${onlineUrl}" target="_blank">Online</a>
																			<%-- <a href="#" onclick="onlinesumit(<s:property value="id"/>);">Online</a> --%>
																				<input type="hidden" id="dammytext"
																				value="<s:property value="%{#session.Encryptedid}"/>">
																				<input type="hidden" id="onlineurl<s:property value="id"/>"
																				value="${onlineUrl}"></li>
																		</ul>
																	</div>
																</div>

															</div>
														</div>
													</s:else>
											</div>
											</div>
 									<div class="row clearfix">
							<!-- edit form column -->
							<div class="col-sm-12 personal-info">
					<div class="row ">
					<div class="col-sm-3">
						<div class="p-info clearfix">
						<p><b>HotelName: </b><span><s:property value="hotelName"/></span></p> 
						</div>
					</div>
					<div class="col-sm-3">
						<div class="p-info clearfix">
						<p><b>No of nights: </b><span><s:property value="nightCount"/></span></p> 
						</div>
					</div>
					
					  <div class="col-sm-3">
						<div class="p-info clearfix">
								<p><b>Booking Mode:</b> <span><s:property value="bookingMode"/></span></p>
						</div>
					</div> 
					<div class="col-sm-5">
						<div class="p-info clearfix">
								<p><b>HotelAddress:</b><span><s:property value="hotelAddress"/></span></p>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="p-info clearfix">
						<p><b>Payment Option(s): </b><span><s:property value="availablePaymentOption"/></span></p> 
						</div>
					</div>
					
				</div>
					<div class="qotationlist clearfix">
											<ul class="clearfix">
												<li><p><b>Hotel Category : </b><span><s:property value="hotelCategory"/></span></p>	</li>
												<li><p><b>Hotel City : </b><span><s:property value="hotelCity"/></span></p>	</li>
												<%-- <li><p><b>Hotel Country : </b><span><s:property value="hotelCountry"/></span></p>	</li> --%>
												<li><p><b>Project Address : </b><span><s:property value="projectAddress"/></span></p>	</li>
												<li><p><b>Distance from Work : </b><span><s:property value="distanceFromWork"/></span></p>	</li>
												<li><p><b>Room Type : </b><span><s:property value="roomType"/></span></p>	</li>
												<li><p><b>CheckInDate : </b><span><s:property value="checkIn"/></span></p>	</li>
												<li><p><b>CheckInTime : </b><span><s:property value="checkInTime"/></span></p>	</li>
												<li><p><b>CheckOutDate : </b><span><s:property value="checkOut"/></span></p>	</li>
												<li><p><b>CheckOutTime : </b><span><s:property value="checkOutTime"/></span></p>	</li>
												<li><p><b>Room Count : </b><span>${roomCount}</span></p>	</li>
												<li><p><b>Adult Count : </b><span><s:property value="adultCount"/></span></p>	</li>
												<li><p><b>Child Count : </b><span><s:property value="childCount"/></span></p>	</li>
												<li><p><b>RoomRate/Night : </b><span><s:property value="roomRatePerNight"/>  <s:property value="currency"/>  </span></p>	</li>
												<%-- <s:property value="roomRatePerNight"/> --%>
												<li><p><b>Taxes : </b><span>${taxes}</span></p>	</li>
												<li><p><b>Breakfast : </b><span>${breakfast}</span></p>	</li>
												<li><p><b>Internet : </b><span>${internet}</span></p>	</li>
												
												<li><p><b>Cancellation : </b><span>${cancellationPolicy}</span></p>	</li>
												
												<li><p><b>Prefer Property : </b><span>
												<c:choose>
												<c:when test="${preferProperty==true}">
													Yes
												</c:when>
												<c:otherwise>
												No
												</c:otherwise>
												</c:choose>
												</span></p>	</li>
											</ul>
					</div>
			</div>
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
	<div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Alert !</h4>
      </div>
      <div class="modal-body clearfix">
          <p id="desc">
     
      </p>
         
      </div>
     
    </div>
  </div>
</div>
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript">
	function checkEmail(email) {
		var regExp = /\b[A-Z._%+-]+@[A-Z.-]+\.[A-Z]\b/i;
		return regExp.test(email);
		}

		function checkEmails(){
			var emails = $('#alternativeToEmail').val();
			var emailArray = emails.split(",");
		var hasErrors=false;
		var errorMessage="";
			for(i = 0; i <= (emailArray.length - 1); i++){
				if(checkEmail(emailArray[i])){
					//Do what ever with the email.
				}else{
					hasErrors=true;
					errorMessage+="invalid email: " + emailArray[i]+"\n\r";
				}
			}
			if(hasErrors){
				alert(errorMessage);
			}
		}
		
		
		
		$(document).ready(function(e){
			
			
			$("#send_emails").click( function(e) {
				e.preventDefault();
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
		          	  
			                url: 'insertHotelQuotationAlternativeMailData?travelRequestId='+travelRequestId+'&selectedQuotaionList='+checkValues+'&toEmail='+alternativeToEmail+'&CCEmail='+alternativeCCEmail,
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
		        		   url: 'insertHotelQuotationMailData?travelRequestId='+travelRequestId+'&selectedQuotaionList='+checkValues,
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
			                	 setTimeout(function() {$('#emailModal').modal('hide');}, 
			                			 2000);
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
	 
		
		 
		 function onlinesumit(id){
		 var url = $('#onlineurl'+id).val();
	/* 	 alert(url);
 */		 var dammytext = $('#dammytext').val();

		 var mapForm = document.createElement("form");
		    mapForm.target = "_blank";
		    mapForm.method = "post"; // or "post" if appropriate
		    mapForm.action = url;

		   /*  var mapInput = document.createElement("input");
		    mapInput.type = "hidden";
		    mapInput.name = "dammytext";
		    mapInput.value = encodeURIComponent(dammytext);
		    mapForm.appendChild(mapInput);
		    console.log(mapForm); */
		    document.body.appendChild(mapForm);

		    // map = window.open();
		    mapForm.submit();
		
		 }
		
  </script>

</body>

</html>