<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html>
<html>
<head>
<%-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  --%>

 <script src="js/jspdf.min.js"></script>  
  
<%-- <script src="js/jspdf.debug.js"></script> --%>
  
 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invoice</title>
<link rel="stylesheet" href="css/alert.css">
</head>
<body>

 <div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Hotel Customer Invoice</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<!-- Main content -->
<!-- Main content -->
        <section class="content">
    <div class="sccuss-full-updated" id="success-alert" style="display:none">
				<div class="succfully-updated clearfix">

					<div class="col-sm-2">
						<i class="fa fa-check fa-3x"></i>
					</div>

					<div id="message" class="col-sm-10">
 					</div>
 					 <button type="button" id="success" class="btn btn-primary">Ok</button>
 				 </div>
 				
			</div>
          <!-- Small boxes (Stat box) -->
          	<div class="col-sm-12 no-print">
						<h4  >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
     <div class="row">
 	<div class="col-sm-12" >
   <div id="butns" class="clearfix">
 <input type="hidden" value="<s:property  value="hotelInvObj.yourRef"/>" id="orderId"> 
          
          <input type="hidden" value="others" id="companyType"> 
          <button class=" btn btn-primary but no-print" onclick="sendCustomerInvoiceToCustomer();">Send Hotel Invoice To Email <i class="fa fa-arrow-circle-right"></i> </button>
      <button class=" btn btn-primary but no-print" onclick="sendHotelVoucherToCustomerEmail();">Send Hotel Voucher To Email <i class="fa fa-arrow-circle-right"></i> </button>
  
      
      </div> 


  <div id="invoice" class="clearfix"  >
<div class="row" >
   
 
 <s:if test="%{hotelInvObj.companyAddress.companyRole.isDistributor() || hotelInvObj.companyAddress.companyRole.isAgent()}">
<div class="col-xs-4">
<div class="logo">
                <img src="<s:url action='ImageAction?imageId=%{hotelInvObj.companyAddress.Imagepath}'/>">
              </div>
</div>
<div class="col-sm-8">
          <div class="lint-invoice-adde text-right">
             <h2>    <s:property  value="hotelInvObj.companyAddress.Companyname"/> </h2>
         <!--     <p>GST Reg.No: 001363410944 --><br>
        <s:property  value="hotelInvObj.companyAddress.Address"/> <br>
            Tel: <s:property  value="hotelInvObj.companyAddress.Phone"/><!--  Fax: +603-9287 2323 --><br>
            <b>Email:</b><s:property  value="hotelInvObj.companyAddress.Email"/> <b>Website:</b><s:property  value="hotelInvObj.companyAddress.Website"/></p>
          </div>
            </div>
 </s:if>
  <s:elseif test="%{hotelInvObj.companysGstOn=='lintas'}">
 <div class="col-xs-4">
<div class="logo">
                <img src="images/lintus-logo-admin.png">
              </div>
</div>
<div class="col-sm-8">
          <div class="lint-invoice-adde text-right">
             <h2>  Lintas Travel Services (M ) SDN BHD</h2>
             <p>GST Reg.No: 001363410944<br>
            2-2 Jalan 1/76D, Desa Pandan, 55100 Kuala Lumpur<br>
            Tel: +603-9207 488(Hunting) Fax: +603-9287 2323<br>
            <b>Email:</b> inquiries@lintastravel.com <b>Website:</b>www.lintastravel.com</p>
          </div>
            </div>
   </s:elseif>
  <s:elseif test="%{hotelInvObj.companysGstOn=='tayyarah'}">
 <div class="col-xs-4">
<div class="logo">
                <img src="images/tayarrah-admin.png">
              </div>
</div>
<div class="col-sm-8">
          <div class="lint-invoice-adde text-right">
          <h2> Tayyarah.com</h2>
             <p><br>
            # 19 "The Oyster",2nd floor, Nandi durga road, Jaymahal extension, Bengaluru-560046<br>
            Tel: +91-080-42855555<br>
            <b>Email:</b> care@tayyarah.com<b>Website:</b>www.tayyarah.com/</p>
          </div>
            </div>
  </s:elseif>
  
    </div>

<div class="row">

  <div class="in-head">

  <h4 class="text-center"><span>Tax Invoice</span></h4>
  </div>

  <div class="invoice-addres clearfix">
        <div class="col-xs-5">
          <div class="panel panel-default">
                 
                  <div class="panel-body">
                   <h4>To: <a href="#"><s:property  value="hotelInvObj.attn"/></a></h4>
                    <p>
                     <s:property  value="hotelInvObj.cusAddress"/>
                    </p>
                    <p>TEL:  <s:property  value="hotelInvObj.tel"/></p>
                   <!--  <p>Fax: 99876544</p> -->
                    <p>Attn: <s:property  value="hotelInvObj.attn"/></p>

                  </div>
                </div>
        </div>

<div class="col-xs-6">

           <table class="table inn-num table-bordered">
      
         <tbody>
          <tr>
            <td>Invoice No</td>
            <td><a href="#"> <s:property  value="hotelInvObj.invNo"/></a></td>
           
            
          </tr>
          <tr>
            <td>ACCT Code</td>
            <td><a href="#"> <s:property  value="hotelInvObj.ActCode"/></a></td>
          </tr>
          <tr>
            <td>Consultant</td>
            <td><a href="#"><s:property  value="hotelInvObj.consultant"/></a></td>
          </tr>
          <tr>
            <td>Book No</td>
            <td><a href="#"><s:property  value="hotelInvObj.bookNo"/></a></td>
          </tr>

          <tr>
            <td>Your Ref</td>
            <td><a href="#"><s:property  value="hotelInvObj.yourRef"/></a></td>
          </tr>
          <tr>
            <td>Page</td>
            <td><a href="#">Page 1 of 1</a></td>
          </tr>
          <tr>
            <td>Date</td>
            <td><a href="#"><s:property  value="hotelInvObj.date"/></a></td>
          </tr>
          </tbody>
        </table>
     </div>   
      </div> <!-- / end client details section -->

<div class="col-sm-12">
       <table class="table table-bordered in-table-border">
        <thead>
          <tr class="info">
          <th><h4><!-- GST Type --></h4></th>  
            <th><h4>Hotel Room & Guest Particulars</h4></th>
            <th><h4><!-- Qty --></h4></th>
            <th><h4><!-- Price --></h4></th>
            <th><h4><!-- Total(MYR) --></h4></th>
          </tr>
        </thead>
            <tbody>
        <s:iterator  value="hotelInvObj.hotelOrderGuest" status="rowCount" >
          <tr>
            <td> </td>
            
              <td>(<s:property  value="%{#rowCount.count}"/>)   <s:property  value="firstName"/> <s:property  value="lastName"/>      <span><s:property  value="email"/></span>           <span><s:property  value="convertDate"/></span> <%--  <span><s:property  value="originCode"/>/<s:property  value="destinationCode"/></span>  <span> <s:property  value="departureDate"/>  <s:property  value="departureTime"/>/<s:property  value="arrivalTime"/>  </span>  <span><s:property  value="tripType"/></span> --%></td>
            
            <%-- <td>
             <s:property  value="operatedByCode"/> 

               <div class="col-sm-2"><s:property  value="flightNumber"/></div> 
                  <div class="col-sm-8"><s:property  value="originCode"/>/<s:property  value="destinationCode"/>
                  <s:property  value="departureDate"/> <s:property  value="departureTime"/>/<s:property  value="arrivalTime"/>
                  </div>
                     <div class="col-sm-1"><s:property  value="tripType"/></div>
            
            
             </td> --%>
            <td ></td>
             <td ></td>
              <td ></td>
          </tr>
          </s:iterator>
           <s:iterator  value="hotelInvObj.hotelOrderRoomInfo">
            
          <tr>
            <td><!-- SR --></td>
            <td><a href="#"><s:property  value="name"/> <s:property  value="hotelInvObj.checkInDate"/></a>/<a href="#"><s:property  value="hotelInvObj.checkOutDate"/></a> </td>
           <td ><%-- <s:property  value="qty"/> --%></td>
             <td ><%-- <s:property  value="price"/> --%></td>
              <td ><%-- <s:property  value="price"/> --%></td>
          </tr>
          
         
          <%--  <tr>
            <td>SR</td>
            <td><a href="#">TX/AGENT FEES</a> <a href="#"> </a> <span> </span>  </td>
            <td> </td>
             <td> </td>
              <td></td>
          </tr> --%>
        
          
          </s:iterator>
            <s:if test="%{hotelInvObj.companysGstOn=='lintas'}">
           <tr>
            <th></th>
			 <th style="text-align: right;"> </th>
			 <th> </th>
			  <th> </th>
			  <th>TotalAmount(<s:property value="hotelInvObj.currency"/>)</th>
			 </tr>
         
             <tr>
             <td></td> 
 			 <td align="right"> </td>
			 <td> </td> 
			 <td> </td> 
			  <td> <s:property value="hotelInvObj.totBeforeGst"/>  </td>
 			 </tr>
              
           </s:if>
           <s:if test="%{hotelInvObj.companysGstOn=='tayyarah'}">
            <tr>
			 <th></th>
			  <th style="text-align:right;">Price</th>
			 <th>Tax</th>
			  <th>DisCount</th>
			 
			 <th >TotalAmount(<s:property value="hotelInvObj.currency"/>)</th>
			 </tr>
         
             <tr>
 			 <td></td> 
 			 <td align="right"><s:property value="hotelInvObj.price"/></td>
			 <td><s:property value="hotelInvObj.tax"/></td> 
			 <td>-<s:property value="hotelInvObj.discountAmount"/></td> 
			  <td> <s:property value="hotelInvObj.totBeforeGst"/></td>
 			 </tr>
              
          </s:if>
          
            
        </tbody>
      </table>
    </div>
 
<div class="clearfix">
  <div class="col-xs-12 payment-in">
  <div class="panel panel-info">
<!--   <div class="panel-heading">
    <h4>Payment details</h4>
  </div> -->
  <h4>Payment details</h4>
 
 <!-- <div class="panel-body"> -->
<table class="table table-bordered in-table-border"  >
<tr class="info">
<th>
Receipt
</th>
<th>
Date
</th>
<th>
Remarks
</th>
<th>
Payment Type
</th>
<th>
Amount
</th>
</tr>
<s:if test="hotelInvObj.txDetails.size>0">
 <s:iterator value="hotelInvObj.txDetails">
<tr >
<td>
DONE
</td>
 
<td>
<s:property  value="convertDate"/>
</td>

<td>
<s:property  value="response_message"/>
</td>
<td>
<s:property  value="payment_method"/>
</td>
<td>
<s:property  value="amount"/>
<%-- <s:property  value="amount"/> --%>
</td>
 </tr>
</s:iterator>
</s:if>
<s:else>
<tr>
<td>
No data
</td>
</tr>



</s:else>


</table>
 
 <%--    <ul class="heading clearfix">
      <li>Receipt</li>
      <li>Date</li>
      <li>Remark</li>
      <li>Payment Type</li>
      <li>Amount</li>
    </ul>
	 <s:iterator value="%{#session.customerInvoiceObj.txDetails}">
     <ul class="cont clearfix">
      <li>DONE</li>
      <li><s:property  value="createdAt"/></li>
      <li> <s:property  value="response_message"/></li>
      <li><s:property  value="payment_method"/></li>
      <li> <s:property  value="%{#session.customerInvoiceObj.totWithGst}"/></li>
    </ul>
   </s:iterator> --%>
 <!--  </div> -->
</div>
  </div>
  <div class="col-xs-12">
   <div class="panel panel-info">
     
  </div>
  </div>
</div>

<div class="clearfix signature-lint">

  <div class="col-sm-6">
    <h4>Received By:</h4>

    <p><span>customers Signature &amp; Chop</span></p>
  </div>

  <div class="col-sm-6 pull-right">
    <div class="pull-right">
      <h4>
			 <s:if test="%{hotelInvObj.companyAddress.companyRole.isDistributor() ||hotelInvObj.companyAddress.companyRole.isAgent()}">
									   <s:property  value="hotelInvObj.companyAddress.Companyname"/>
									</s:if>
									<s:elseif test="%{hotelInvObj.companysGstOn=='lintas'}">
									 LINTAS TRAVEL SERVICES (M) SDN BHD
									</s:elseif>
									<s:elseif test="%{hotelInvObj.companysGstOn=='tayyarah'}">
									TAYYARAH.COM
									</s:elseif>
									 </h4>
    <p><span>Authorised Signature</span></p>
    </div>
  </div>
  </div>




</div>
</div>

<div id="editor"></div>
            
</div><!-- /.row -->
<!-- Main row -->
          


  </div><!-- table-responsive -->
  </section>
       
</div><!-- /.row -->
<!-- Main row -->
 
 
      <%@ include file="DashboardFooter.jsp"%>
 
       <!-- /.content -->
        
      
        
        <script type="text/javascript">
      function  sendCustomerInvoiceToCustomer(){
    	   var companyType=$("#companyType").val();
    	  console.log("companyType..."+companyType);
    	  var orderId=$("#orderId").val();
    	  console.log("orderId..."+orderId);
    	  //var invoice = "<html><body style='border:2px solid;padding:10px 10px 10px 10px'>"+$("#invoice").html()+"</body></html>";
    	/*   var htmlMessage=$('#invoice').html(); */
    	  //console.log("--htmlMessage..."+invoice);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl = newUrl+"sendHotelInvoiceToMail";
  	  //console.log("finalUrl..."+finalUrl);
  		$('#h4').show();
  			$.ajax({
				    method: "POST",
				    url:finalUrl,
				    data: {orderId:$("#orderId").val(),companyType:$("#companyType").val()},
				    success:function(data,status)
					{ 
				        $.each(data, function(index, element) {
				    		  console.log("data-------"+element.status);

						     	if(element.status=="success"){
						     		$('#h4').hide();
						    		  $('#success-alert').show();
									  $('#message').text("Successfully sent mail.");
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					  window.location.assign($(location).attr('href'));
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
      
  </script>
  
  	 <script type="text/javascript">
  function  sendHotelVoucherToCustomerEmail(){
    	  var orderId=$("#orderId").val();
    	  console.log("orderId..."+orderId);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl = newUrl+"sendHotelVoucherToCustomerEmail";
  	  console.log("finalUrl..."+finalUrl);
  		$('#h4').show();
  			$.ajax({
				    method: "POST",
				    url:finalUrl,
				    data: {orderId:$("#orderId").val()},
				    success:function(data,status)
					{ 
				        $.each(data, function(index, element) {
				    		  console.log("data-------"+element.status);
						     	if(element.status=="success"){
						     		$('#h4').hide();
						    		  $('#success-alert').show();
									  $('#message').text("Successfully sent hotel voucher to email.");
									    $('#success').click(function() {
						  					  $('#success-alert').hide();
						  					  window.location.assign($(location).attr('href'));
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
      
  </script> 
 
</body>
</html>
 