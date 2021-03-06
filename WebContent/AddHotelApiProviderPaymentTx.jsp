<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jstl/core"  prefix="c"%>
<!DOCTYPE html >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Designation</title>

 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<%-- <script src="js/angular.js" type="text/javascript"></script>
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">
<style type="text/css">
.border-common {
	margin: 2px 0px 0px;
	text-align: left;
}

.border-common p {
	margin-bottom: 2px;
	margin-top: 0px;
	border-bottom: 0px solid #0082cb;
	padding-bottom: 2px;
}

 .error {
    color:red;
}
.valid {
    color:green;
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
		<h4>Hotel Customer Payment Details</h4>
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
				<%-- <a href="hotelTravelRequestEdit?id=${hotelQuotationRequestId}"
					class="btn btn-success btn-xs" data-toggle="modal">
					  <i class= "fa fa-edit"></i> Travel Request
				</a>
				<a href="hotelOrderQuotationView?id=${hotelQuotationRequestId}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-eye"></i> Travel Request
				</a>
				
				<a href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${hotelQuotationRequestId}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-plus"></i> Quotation
				</a> --%>
			<a href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${hotelQuotation.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 All Quotations
				</a>
		</div>
			</div>			
			</div>
 
		</div>  
<%-- 		
		
		<div class="clearfix">
		<div class="col-sm-6">
			<h3>Hotel Supplier Payment Details</h3> 
			</div>
				<div class="col-sm-9 clearfix pull-right " data-placement="top">
		<div class="row">
		<div class="col-sm-5 clearfix pull-left " data-placement="top">
				<a href="hotelTravelRequestEdit?id=${hotelQuotation.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					  <i class= "fa fa-edit"></i> Travel Request
				</a>
				<a href="hotelOrderQuotationView?id=${hotelQuotation.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-eye"></i> Travel Request
				</a>
				
				<a href="goHotelRequestTravelQuotation?hotelQuotationRequestId=${hotelQuotation.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 <i class= "fa fa-plus"></i> Quotation
				</a>
				<a href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${hotelQuotationRequestId}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 All Quotations
				</a>
				<a href="getHotelRequestTravelQuotationList?hotelQuotationRequestId=${hotelQuotation.hotelTravelRequest.id}"
					class="btn btn-success btn-xs" data-toggle="modal">
					 All Quotations
				</a>
		</div>
		<div class="col-sm-4 clearfix " data-placement="top">		
				<a href="goTrips"
					class="btn btn-top-link btn-xs" >
					 All Trips
				</a>
				<a href="HotelTravelRequestList"
					class="btn btn-top-link btn-xs"  title="Check Hotel Trips">
					 Hotel Trips
				</a>
				<a href="FlightTravelRequestList"
					class="btn btn-top-link btn-xs"  title="Check Flight Trips">
					 Flight Trips
				</a>
			</div>		
			<div class="col-sm-3 clearfix pull-right" data-placement="top">
				<div class="myDropdown">
				  <button class="btn btn-top-link btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Create Trip
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				    <li><a href="goHotelTravelRequest">Hotel</a></li>
				    <li><a href="goFlightTravelRequest">Flight</a></li>
				  </ul>
				</div>
			</div>	  
			</div>			
			</div>
			</div> --%>
		</section>
		 
		<!-- Modal -->
<div id="myNotification" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Payment Notification Alert</h4>
      </div>
         <span style='color:red;font-size:15px'>* Please separate emails with semicolon </span>
      <div class="modal-body">
      
      <form action="" method="post"
     class="form-horizontal" name="myForm"  id="notificationAlertForm">
     <div class="row">
     <div class="col-sm-12"> 
      <div class="col-sm-6"> 
      <div class="form-group">
  <label for="comment">To Mail(s):</label> 
  <textarea class="form-control" rows="2" id="toEmails"></textarea>
</div>
</div>
     
     <div class="col-sm-6"> 
      <div class="form-group">
  <label for="comment">CC Mail(s):</label>
  <textarea class="form-control" rows="2" id="ccEmails"></textarea>
</div>
</div>
  
     <div class="col-sm-6"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       From Date </label>
         <input type="hidden" value="${apiProviderPaymentTransaction.api_transaction_id}" id="paymentTxId">
        <input type="text" class="form-control input-sm" value=""
         required id="fromDate"   name="" placeholder="FromDate" >
       </div>
      </div>
      <div class="col-sm-6"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       To Date <font size="1" color="red">(Selected time is preferred)</font></label>
        
        <input type="text" class="form-control input-sm" value=""
         required id="toDate"   name="" placeholder="Notification Title" >
       </div>
      </div>
      <div class="col-sm-6"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       Due Date </label>
        
        <input type="text" class="form-control input-sm" value=""
         required id="dueDate"   name="" placeholder="FromDate" >
       </div>
      </div>
      <div class="col-sm-6"> 
      <div class="form-group">
      <label for="currency" class=" control-label"> 
       Paid Date </label>
        
        <input type="text" class="form-control input-sm" value=""
         required id="paidDate"   name="" placeholder="FromDate" >
       </div>
      </div>
      <div class="col-sm-12"> 
      <div class="form-group">
  <label for="comment">Comment:</label>
  <textarea class="form-control" rows="1" id="comment"></textarea>
</div>
</div>
      
     </div>
     </div>
     
     </form>
       
      </div>
      <div class="modal-footer">
 
       
       <button type="button"  id="paymentNotificationAlert"   class="btn btn-primary btn-sm">Create Notification</button>
       
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

		<!-- Main content -->
		<section class="content clearfix">
			<!-- Small boxes (Stat box) -->
			<div class="pull-right">
			  <a href="javascript:history.back();" style="font-size:18px"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				  
			</div>


			<!--  <a href="PaymenttransactionDetails">payment</a> -->

			<!-- <a href="HotelQuotationPdf">quatationvocher</a>
			 -->
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
			<div class="border-common">
				<p class="h4">
				  <b>Supplier Payment Details   <s:if test="apiProviderPaymentTransaction.isPaymentSuccess==false"> <a  class="btn btn-info btn-xs" data-toggle="modal" href="#myNotification">Create Notification </a></s:if> </b>
					 
				</p>
			</div>
			<div class="col-sm-12">
				<table class="table table-bordered">
					<thead>
						<tr>
						<th>Order Id</th>
						<th>Guest Name</th>
							<th> Booking Amount</th>
					<s:if test="apiProviderPaymentTransaction.isPaymentSuccess==false">
						<th>Due Amount</th>
					</s:if>
						<s:else>
							<th>Paid Amount</th>
						</s:else>	
						<th>Booking Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<td>${apiProviderPaymentTransaction.api_transaction_id}</td>
						<td>${hotelQuotation.hotelOrderRow.orderCustomer.firstName} ${hotelQuotation.hotelOrderRow.orderCustomer.lastName}
						</td> 	
						
							<td>${apiProviderPaymentTransaction.amount} ${apiProviderPaymentTransaction.currency}</td>
							<s:if test="apiProviderPaymentTransaction.isPaymentSuccess==false">
							<s:if test="apiProviderPaymentTransaction.balance==null">
							<td>${apiProviderPaymentTransaction.amount} ${apiProviderPaymentTransaction.currency}</td>
							</s:if>
							<s:else>
							 <td><span id="balance">${apiProviderPaymentTransaction.balance}</span> ${apiProviderPaymentTransaction.currency}</td>
							</s:else>
							</s:if>
								<s:else>
							 <td>${apiProviderPaymentTransaction.amount} ${apiProviderPaymentTransaction.currency}</td>
							</s:else>
						 <td>${hotelQuotation.hotelOrderRow.statusAction}</td>
						</tr>

					</tbody>
				</table>

			</div>

<s:if test="apiProviderPaymentTransaction.isPaymentSuccess==false">
<div class="border-common">
				<p class="h4">
					<b>Add Supplier Payment (${apiProviderPaymentTransaction.currency})</b>
				</p>
			</div>
			<div class="col-sm-12">
				<form action="insertApiProviderPaymentTransaction" method="post"
					class="clearfix" name="myForm" id="myForm">
					<input type="hidden" value="${apiProviderPaymentTransaction.id}" name="paymentTransaction.id">
					<div class="clearfix" id="payment">
						 
								  <input
									type="hidden"  value="${apiProviderPaymentTransaction.api_transaction_id}"
									name="apiTransactionId">

							 
													<div class="commonone clearfix">
													<div class="form-group clearfix">
													<label class="control-label">PaymentMethod</label> 
													<select  class="form-control input-sm" id="clientPaymentMethod"
															name="paymentMethod" required>
															<option value="card">CARD</option>
															<option value="cash">CASH</option>
															<option value="online">ONLINE</option>
														</select>
													</div>
													</div>
													<div class="commonone clearfix">
							<div class="form-group clearfix">
								<label class="control-label">CardHolderName</label> 
								<select class="form-control input-sm productTypeVal required"  
															name="supplierCardHolderId" required id="holderName">
															<option value="0">Select card holder name</option>
															<s:iterator value="paymentCardList">
															<option value="${id}">${userName}(${cardNumber})</option>
															</s:iterator>
														</select>
														</div>
													</div> 
						<div class="commonone clearfix">
							<div class="form-group clearfix">
								<label class="control-label">Amount</label> <input type="text"
									class="form-control amountValid cusValidationforprice required"   placeholder="Amount"  value="${apiProviderPaymentTransaction.balance}" id="payBalance" required
									autocomplete="off" name="amount">

							</div>
						</div>

						 
							 <input type="hidden"  value="${apiProviderPaymentTransaction.currency}" name="currency">
   
						<div class="commonone clearfix">
							<div class="form-group clearfix">
								<label class=" control-label">Paid By</label> <input type="text"
									class="form-control"  placeholder="Paid By" id="PaidBy" required
									autocomplete="off" name="paymentPaidBy">

							</div>
						</div>
						<div class="commonone clearfix">
							<div class="form-group clearfix">
								<label class="control-label">Payment CollectionType</label> <select
									class="form-control input-sm" required name="paymentCollectionType" id="paymentCollectionType">
									 <option selected="selected" value="Partial">Partial Payment</option>
									<option value="Full">Full Payment</option>
									
								</select>

							</div>
						</div>


						<div class="commonone clearfix">
							<div class="form-group clearfix">
								<label class="control-label">Payment Status</label> <select
									class="form-control input-sm" required name="paymentStatus" id="paymentStatus" >
									 <option selected="selected"    value="Pending">Pending</option>
									<option value="Paid">Paid</option>
									
								</select>

							</div>
						</div>
				  
 													
					 	
						<div class="clearfix client-comments"    style="display: none">
							<div class="form-group clearfix">
								<label class="control-label">Payment Information</label>
								<textarea rows="2" cols="2" class="form-control" style="width: 93%;" required="required"
									placeholder="Payment Information" name="paymentInformation"></textarea>


							</div>
						</div> 
						    <div class="clearfix">
							<div class="form-group text-center">
								<input type="button" class="btn btn-primary btn-m"  id="add-pay" value="ADD PAYMENT">
							</div>
							</div> 
					</div>
				</form>

</div>


</s:if>
			
<s:if test="apiProviderpaymentTransactionDetailList!=null && apiProviderpaymentTransactionDetailList.size()>0">
				<div class="border-common">
					<p class="h4">
						<b>Supplier Payment Transaction(s)</b>
					</p>
				</div>
				<div class="col-sm-12">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>SNo</th>
								<th>TransactionId</th>
								<th>PaidAmount</th>
								<th>DueAmount</th>
								<!-- <th>PaymentType</th> -->
								<th>Date</th>
								<!-- <th>CardHolder</th>
								<th>Bank</th> -->
								<th>PaymentMethod</th>
								<th>Comments</th>
								<th>PaidBy</th>
								<th><a title="Download"    class='btn btn-primary btn-xs'    href='<s:text name="global.customerOrSupplierTxPdfUrl"/>?orderids=${paymentTransaction.api_transaction_id}&type=Supplier'> <i class="fa fa-file-pdf-o"></i> PDF</a> </th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="apiProviderpaymentTransactionDetailList" status="loopCount">
						  <tr>
								<td><s:property value="%{#loopCount.count}"/></td>
								<td><s:property value="transactionId"/></td>
								<td><s:property value="amount"/></td>
								<td><s:property value="balance"/></td>
								<%-- <td><s:property value="paymentCollectionType"/></td> --%>
								<td><s:property value="createdDate"/></td>
									<%--<td>${apiProviderPaymentCardInfo.userName}(${apiProviderPaymentCardInfo.cardNumber})</td>
								<td>${apiProviderPaymentCardInfo.bankName}</td> --%>
								<td>${paymentMethod}</td>
								<s:if test="paymentInformation==''">
								<td>---</td>
								</s:if>
								<s:else>
									<td>${paymentInformation}</td>
								</s:else>
							
								
								<td><s:property value="paymentPaidBy"/></td>
								<td></td>
								<td></td>
								 
							</tr>
						
						</s:iterator>
						 
						
 				 
						</tbody>
					</table>

				</div>
</s:if>
</section>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		 
	<!-- /.content-wrapper -->
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
	
	<script type="text/javascript">
	$(document).ready(function(){
		
		jQuery.validator.addMethod("amountValid",function(value) {
            var startprice = 1;
            return startprice < parseFloat($('#payBalance').val());
          }, "please add Amount Value");
		
		jQuery.validator.classRuleSettings.baseFareprice = { amountValid: true };
		
		jQuery.validator.addMethod("productTypeVal",function(value) {
            var startprice =  $('#holderName').val();
            return (startprice !=  '0');
          }, "please select card holder name");
		
	     $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
	          return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
	      }, "This field cannot have symbols.");

	      $.validator.addMethod("cusValidationAlphaName",function(value,element){
	          return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
	      },"This field cannot have numbers and symbols."); 
	      $.validator.addMethod("cusValidationforprice",function(value,element){
	          return this.optional(element) || /^[0-9.]+$/i.test(value);
	      },"This field cannot have Char and symbols.");


	$("#myForm").validate({
		 rules: {
	         
	         "email": {
	             required: true,
	             email: true
	         },
	     },
	     
	     messages: {
	         
	         "email": {
	             required: "Please, enter an email",
	             email: "Email is invalid"
	         },
	     },

	    submitHandler: function (form) { 
	        return false; 
	    }
	});

	});
	
	 $('#add-pay').click(function() {
	        $("#myForm").valid();
	        
	        var balance=Math.round(parseFloat($('#balance').text())*100)/100;
			var payBalance=Math.round(parseFloat($('#payBalance').val())*100)/100;
			var PaidBy=$('#PaidBy').val();
			var holderName=$('#holderName').val();
			var  paymentInformation=$('#paymentInformation').text();
			 if(payBalance<balance){
				 document.getElementById('paymentCollectionType').value ="Partial";
				 document.getElementById('paymentStatus').value ="Pending";
			 }
			  if(payBalance==balance){
				 document.getElementById('paymentCollectionType').value ="Full";
				 document.getElementById('paymentStatus').value ="Paid";
			 }
			  if(payBalance>balance){
					 document.getElementById('paymentCollectionType').value ="Full";
					 document.getElementById('paymentStatus').value ="Paid";
					 alert("Paying amount is more than due amount.Please check.");
				} 
			 /* else if(PaidBy==""){
				 alert("Please type name who is paying."); 
			 }
			 else if(holderName=="0"){
				 alert("Please select card holder name."); 
			 }
			 
			 else if(payBalance==""){
				 alert("Please enter amount."); 
			 } */
			 
			  else{
				  if($("#myForm").valid())
			        	document.getElementById("myForm").submit();
				//$('#paymentTransaction').submit();
			}
	        
	        
	        
	     	 
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
$(function() {
	var balance=Math.round(parseFloat($('#balance').text())*100)/100;
	var payBalance=Math.round(parseFloat($('#payBalance').val())*100)/100;
	 if(payBalance==balance){
		 document.getElementById('paymentCollectionType').value ="Full";
		 document.getElementById('paymentStatus').value ="Paid";
		 
	 }
	 $( "#payBalance").keyup(function() {
		 var balance=Math.round(parseFloat($('#balance').text())*100)/100;
			var payBalance=Math.round(parseFloat($('#payBalance').val())*100)/100;
			 
			 if(payBalance>=balance){
				 document.getElementById('paymentCollectionType').value ="Full";
				 document.getElementById('paymentStatus').value ="Paid";
			 }
			 else{
				 document.getElementById('paymentCollectionType').value ="Partial"; 
				 document.getElementById('paymentStatus').value ="Pending";
			 }
		});
	
/* 	$( "#add-pay" ).click(function() {
		
		    
		}); */
	  
});
  
  
$(function() {
	var clientPaymentMethod=$('#clientPaymentMethod').val();
	if(clientPaymentMethod=='cash' || clientPaymentMethod=='online' ){
		  $('.client-comments').show();
	  }
	else{
		  $('.client-comments').hide();
	}
	 
	$('#clientPaymentMethod').on('change', function() {
		   
		  if(this.value=='cash' || this.value=='online' ){
			  $('.client-comments').show();
		  }
		  else{
			  $('.client-comments').hide();
		  }  
		  
		 })
		
		 
 });

 </script>
 
 <script>
 $('#fromDate').datepicker({
	    //showTimePicker: false,
	    minDate:0,
	    dateFormat: 'dd-mm-yy', 
	    onSelect: function( selectedDate ) {
	        var date2 = $("#fromDate").datepicker('getDate', '+1d'); 
	        date2.setDate(date2.getDate()); 
	        $( "#toDate" ).datepicker('setDate', date2);
	      $( "#toDate" ).datepicker( "option", "minDate", date2 ); 
	       },
	     onClose: function() {
	         $("#toDate").focus();
	     }
	     
	    }).datepicker("setDate", new Date());

$('#toDate').datetimepicker({
	   dateFormat: 'dd-mm-yy',
	         minDate:0,
	         onSelect: function(selectedDate,i) { 
	         // $("#fromDate").datepicker("option", selected)
	             var todate = $(this).val();
	             
	         }
	  
	  }).datepicker("setDate", new Date());
$("#dueDate").datepicker({
 dateFormat: "dd-mm-yy",
  minDate:0,
/*   onClose: function() {
      $("#paidDate").focus();
  } */
});

$("#paidDate").datepicker({
 dateFormat: "dd-mm-yy", 
});



$("#paymentNotificationAlert").click( function() {
	
	var orderId= $('#paymentTxId').val();
    var transFromDate= $('#fromDate').val();
    var transToDate= $('#toDate').val();
    var transPaidDate= $('#paidDate').val();
    var transDueDate= $('#dueDate').val();
    var comments= $('#comment').val();
    var toEmails= $('#toEmails').val();
    var ccEmails= $('#ccEmails').val();
   
    	$.ajax({	
                url: 'insertHotelSupplierPaymentTxHistory',
                type: 'post',
                data:{transFromDate:transFromDate,transToDate:transToDate,transPaidDate:transPaidDate,transDueDate:transDueDate,comments:comments,orderId:orderId,toEmails:toEmails,ccEmails:ccEmails},
                success:function(data){
                if(data.statusMap.status=='1')
                	{ 
                	$("#notificationAlertForm").hide();
	                	$(".modal-footer").hide();
                	  txt1 = "Successfully notification created..";
                	 
                	$("#myNotification .modal-body").append(txt1);  
                	 setTimeout(function() {
                		 $('#myNotification').modal('hide');
                		 window.location.reload();
                		 $("#notificationAlertForm")[0].reset();  
                	 },1000); 
                	 
                	
                	}
                else{
                	
                  txt1 = "We found some error......";
                	$("#myNotification .modal-body").append( txt1);
                	setTimeout(function() {$('#myNotification').modal('hide');}, 1000);
                  
                }
                }
            }); 
    	 
 
		});
 
</script>
</body>
</html>