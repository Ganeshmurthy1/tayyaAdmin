 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<!-- Content Wrapper. Contains page content -->
<style>
thead{
background: #fff;
}
img {
    margin-top: 0px!important;  
  margin-bottom: 0px!important;  
}
</style>
 <div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
				<div class="row">
		<div class="col-sm-3 clearfix pull-left">
		<h3>Insurance Invoice</h3>
		</div>
		<div class="col-sm-9 clearfix pull-right " data-placement="top">
						<div class="row">
							<div class="col-sm-5 clearfix pull-left " data-placement="top">
							</div>
							<div class="col-sm-8 clearfix " data-placement="top">
								<a href="goTrips" class="btn btn-top-link btn-xs"> All Trips
								</a> <a href="FlightTravelRequestList"
									class="btn btn-top-link btn-xs"> Flight Trips </a> <a
									href="HotelTravelRequestList" class="btn btn-top-link btn-xs">
									Hotel Trips </a> <a href="CarTravelRequestList"
									class="btn btn-top-link btn-xs"> Car Trips </a> <a
									href="BusTravelRequestList" class="btn btn-top-link btn-xs">
									Bus Trips </a> <a href="TrainTravelRequestList"
									class="btn btn-top-link btn-xs"> Train Trips </a> <a
									href="VisaTravelRequestList" class="btn btn-top-link btn-xs">
									Visa Trips </a>
							</div>
							<div class="col-sm-3 clearfix pull-right" data-placement="top">
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

										<!--  <li><a href="allexpenses">Expenses</a></li> -->
									</ul>
								</div>
							</div>
						</div>
					</div>
		</div>
		</section>
		<!-- Main content -->
		
			 
			<!-- Modal --> 
	<div id="invoiceModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">SEND INVOICE TO EMAIL</h4>
      </div>
      <span style='color:red;font-size:15px'>* Please separate emails with semicolon </span>
      <div class="modal-body">
      
      <form action="" method="post" class="form-horizontal" name="myForm"  id="invoiceForm">
     <div class="row">
     <div class="col-sm-12"> 
   <input type="hidden" id="insurid"	value="<s:property value="insuranceOrderRow.orderId"/>">
      <div class="col-sm-6"> 
      <div class="form-group">
  <label for="comment">To Mail(s):</label> 
  <textarea class="form-control" rows="2" id="invoiceToEmails"></textarea>
</div>
</div>
     <div class="col-sm-6"> 
      <div class="form-group">
  <label for="comment">CC Mail(s):</label>
  <textarea class="form-control" rows="2" id="invoiceFromEmails"></textarea>
</div>
</div>
   
     </div>
     </div>
     </form>
     
     
      </div>
      <div class="modal-footer">
       <button type="button"   onclick="sendInsurenceOfflineVoucherOrInvoiceToMail('invoice','<s:property value="insuranceOrderRow.orderId"/>');" class="btn btn-primary btn-sm">SEND INVOICE</button>
        <button type="button"  class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>	
<!-- Main content -->
  <section class="content">
          	<div class="col-sm-12 no-print">
          	<div class="col-sm-6">  
          		<a data-toggle="modal" href="#invoiceModal" class="btn btn-warning btn-xs">Send Invoice Via Email</a>
          		<a href="#" class="btn btn-warning btn-xs" onclick="printDiv('display-api-content')">print</a>
          		<a href='<s:text name="global.resourceApiUrl"></s:text>getpdf/invoice?orderid=<s:property value="insuranceOrderRow.orderId"/>&travelcode=7' class="btn btn-success btn-xs"   >
															Pdf </a> 
          	</div>
          	<div class="col-sm-6">
						<h4 >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
						</div>
					</div>
					 
					  <div class="row">
                    <div class="col-sm-12 clearfix">
                        <div id="display-api-content" class="container">
						</div>
                    </div>
                </div>  
  </section>
       
</div>
<!-- Main row -->
      <%@ include file="DashboardFooter.jsp"%>
      
      <script>
	$(document).ready(function() {
			calldataplugin();
	});

	function calldataplugin()
	{
		var ApiURL;
		  $.ajax({
				url : 'resources/ApiResourceURLcredential.properties',
				type : "GET",
				dataType : "json",
				success : function(response,textStatus,jqXHR) {
					ApiURL=  response.ApiResourceURL
		var orderid = $("#insurid").val();
		 
		var formURL = ApiURL+"Email/getHtmlTemplateById?orderid="+orderid+"&emailType=68";
		//var formURL = "http://localhost:8090/LintasTravelAPI/Email/getHtmlTemplateById?orderid="+orderid+"&emailType=68";
		
		
		console.debug("getting plugin data content----------"+formURL);
		$.ajax({
			url : formURL,
			type : "GET",
			dataType : "html",
			success : function(data,textStatus,jqXHR) {
				if (jqXHR.status == 200) { 
					$("#display-api-content").html(data);
					setInterval(function() {
						$("#display-api-content").show();
					}, 1000); 
				} 
			},
			error : function(jqXHR,textStatus,errorThrown) {
				console.debug(jqXHR.status);
				$(".display-api-content").html("We could not get the content, please contact administrator.");
			}
		});
	 	}
			 });
	}
	</script>
      <script type="text/javascript">
      
       function sendInsurenceOfflineVoucherOrInvoiceToMail(type,orderId) {
        	var toEmails= null;
        	var ccEmails= null;
        	if(type=='voucher'){
        		toEmails= $('#toEmails').val();
        		ccEmails= $('#ccEmails').val();
        	}
			if(type=='invoice'){
				toEmails= $('#invoiceToEmails').val();
        		ccEmails= $('#invoiceFromEmails').val();
        	}
        	 
        	var txt1;
        	 
          $.ajax({	
	                url: 'sendInsurenceOfflineVoucherOrInvoiceToMail',
	                type: 'post',
	                data:{type:type,orderId:orderId,toEmails:toEmails,ccEmails:ccEmails},
	                success:function(data){
	                	  if(data.statusMap.status=='voucher')
	                	{ 
	                	$("#voucherForm").hide();
		                	$(".modal-footer").hide();
	                	  txt1 = "Successfully voucher sent to mail.Please check.";
	                	  $("#voucherForm")[0].reset(); 
	                	  $("#voucherModal.modal-body").append(txt1);  
	                	 setTimeout(function() {
	                		 $('#voucherModal').modal('hide');
	                		 window.location.reload();
	                	 },1000); 
	                	}
	                else{
	                  txt1 = "We found some error......";
	                	$("#voucherModal.modal-body").append( txt1);
	                	setTimeout(function() {$('#voucherModal').modal('hide');}, 1000);
	                  
	                }
	                
	                if(data.statusMap.status=='invoice')
                	{ 
                	$("#invoiceForm").hide();
	                	$(".modal-footer").hide();
                	  txt1 = "Successfully invoice sent to mail.Please check.";
                	  $("#invoiceForm")[0].reset(); 
                	$("#invoiceModal.modal-body").append( txt1);  
                	 setTimeout(function() {
                		 $('#invoiceModal').modal('hide');
                		 window.location.reload();
                		 
                	 },1000); 
                	 
                	}
                else{
                  txt1 = "We found some error......";
                	$("#invoiceModal.modal-body").append( txt1);
                	setTimeout(function() {$('#invoiceModal').modal('hide');}, 1000);
                  
                }
	           
	                }
	            });   
        	 
     
			}
       
       
       
       function printDiv(divName) {
  	     var printContents = document.getElementById(divName).innerHTML;
  	     var originalContents = document.body.innerHTML; 
  	     document.body.innerHTML = printContents; 
  	     window.print(); 
  	     document.body.innerHTML = originalContents;
  	}
</script>
 
       <!-- /.content -->