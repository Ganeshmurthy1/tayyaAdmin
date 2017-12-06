<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
 
<script type="text/javascript" src="js/jspdf.min.js"></script>
<script type="text/javascript" src="js/html2canvas.min.js"></script>
<%-- <script src="js/jspdf.debug.js"></script> --%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Invoice</title>
<link rel="stylesheet" href="css/alert.css">
 
</head>
<body>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Flight Customer Invoice</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<!-- Main content -->
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
		<!-- Main content -->
		<section class="content">
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
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12 no-print">
				<h4>
					<a href="getCustomerInvoiceList"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
			<div class="row">
				<div class="col-sm-12">
			 <div id="butns" class="clearfix">

 <input type="hidden" value="<s:property  value="invObj.yourRef"/>" id="orderId"> 
          
          <input type="hidden" value="others" id="companyType"> 
          <button class=" btn btn-primary but no-print" onclick="sendCustomerInvoiceToCustomer();">Send Invoice To Email <i class="fa fa-arrow-circle-right"></i> </button>
    		<button class=" btn btn-primary but no-print" onclick="sendFlightVoucherToCustomerEmail();">Send Voucher To Email <i class="fa fa-arrow-circle-right"></i> </button>
  
					</div>  
					 
 					<div id="invoice" class="clearfix">
 					<form action="" method="post" id="form">
						<div class="row">
					
 <s:if test="%{invObj.companyAddress.companyRole.isDistributor() || invObj.companyAddress.companyRole.isAgent()}">
<div class="col-xs-4">
<div class="logo">
                <img src="<s:url action='ImageAction?imageId=%{invObj.companyAddress.Imagepath}'/>">
              </div>
</div>
<div class="col-sm-8">
          <div class="lint-invoice-adde text-right">
             <h2>    <s:property  value="invObj.companyAddress.Companyname"/> </h2>
         <!--     <p>GST Reg.No: 001363410944 --><br>
        <s:property  value="invObj.companyAddress.Address"/> <br>
            Tel: <s:property  value="invObj.companyAddress.Phone"/><!--  Fax: +603-9287 2323 --><br>
            <b>Email:</b><s:property  value="invObj.companyAddress.Email"/> <b>Website:</b><s:property  value="invObj.companyAddress.Website"/></p>
          </div>
            </div>
 </s:if>
 <s:elseif test="%{invObj.companysGstOn=='lintas'}">
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
  <s:elseif test="%{invObj.companysGstOn=='tayyarah'}">
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

								<h4 class="text-center">
									<span>Tax Invoice</span>
								</h4>
							</div>

							<div class="invoice-addres clearfix">
								<div class="col-xs-5">
									<div class="panel panel-default">

										<div class="panel-body">
											<h4>
												To: <a href="#"><s:property
														value="invObj.attn" /></a>
											</h4>
											<p>
												<s:property
													value="invObj.cusAddress" />
											</p>
											<p>
												TEL:
												<s:property value="invObj.tel" />
											</p>
											<!--  <p>Fax: 99876544</p> -->
											<p>
												Attn:
												<s:property value="invObj.attn" />
											</p>

										</div>
									</div>
								</div>

								<div class="col-xs-6">

									<table class="table inn-num table-bordered">

										<tbody>
											<tr>
												<td>Invoice No</td>
												<td><a href="#"> <s:property
															value="invObj.invNo" /></a></td>


											</tr>
											<tr>
												<td>ACCT Code</td>
												<td><a href="#"> <s:property
															value="invObj.ActCode" /></a></td>
											</tr>
											<tr>
												<td>Consultant</td>
												<td><a href="#"><s:property
															value="invObj.consultant" /></a></td>
											</tr>
											<tr>
												<td>Book No</td>
												<td><a href="#"><s:property
															value="invObj.bookNo" /></a></td>
											</tr>

											<tr>
												<td>Your Ref</td>
												<td><a href="#"><s:property
															value="invObj.yourRef" /></a></td>
											</tr>
											<tr>
												<td>Page</td>
												<td><a href="#">Page 1 of 1</a></td>
											</tr>
											<tr>
												<td>Date</td>
												<td><a href="#"><s:property
															value="invObj.bookedDate" /></a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!-- / end client details section -->

							<div class="col-sm-12">
								<table class="table table-bordered in-table-border">
									<thead>
										<tr class="info">
										<s:if test="%{invObj.companysGstOn=='lintas'}">
												<th><h4>GST Type</h4></th>
												</s:if>
												<s:else><td></td></s:else>
											
											<th><h4>Particular(s)</h4></th>
											<th><h4>Qty</h4></th>
											<th><h4>Price</h4></th>
											<th><h4>Total(<s:property value="invObj.currency" /> )</h4></th>
										</tr>
									</thead>
									<tbody>
										<s:iterator
											value="invObj.tripParticulars">
											<tr>
											 <s:if test="%{invObj.companysGstOn=='lintas'}">
												<td></td>
												</s:if>
												 <s:else><td></td></s:else>

												<td><s:property value="operatedByCode" /> <span><s:property
															value="flightNumber" /></span> <span><s:property
															value="originCode" />/<s:property value="destinationCode" /></span>
													<span> <s:property value="convertDate"/> <s:property
															value="departureTime" />/<s:property value="arrivalTime" />
												</span> <span><s:property value="tripType" /></span></td>

												<%-- <td>
             <s:property  value="operatedByCode"/> 

               <div class="col-sm-2"><s:property  value="flightNumber"/></div> 
                  <div class="col-sm-8"><s:property  value="originCode"/>/<s:property  value="destinationCode"/>
                  <s:property  value="departureDate"/> <s:property  value="departureTime"/>/<s:property  value="arrivalTime"/>
                  </div>
                     <div class="col-sm-1"><s:property  value="tripType"/></div>
            
            
             </td> --%>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</s:iterator>
										<s:iterator value="invoiceDetails">

											<tr>
											<s:if test="%{invObj.companysGstOn=='lintas'}">
												<td>ZRE</td>
												</s:if>
												 <s:else><td></td></s:else>
												<td><a href="#"><s:property value="firstName" /></a></td>
												<td><s:property value="qty" /></td>
												<td><s:property value="price" /></td>
												<td><s:property value="totPrice" /></td>
											</tr>

											<tr>
											<s:if test="%{invObj.companysGstOn=='lintas'}">
												<td>ZRE</td>
												</s:if>
												 <s:else><td></td></s:else>
												 <td><a href="#">AIRPORT TAX</a> <a href="#"> </a> <span>
												</span></td>
												<td><s:property value="qty" /></td>
												<td><s:property value="tax" /></td>
												<td><s:property value="totalTax" /></td>
											</tr>
											<%-- <tr>
												<td>SR</td>
												<td><a href="#">TX/AGENT FEES</a> <a href="#"> </a> <span>
												</span></td>
												<td></td>
												<td></td>
												<td></td>
											</tr> --%>
											<%-- <tr>
												<td>SR</td>
												<td><a href="#">1 ADT x MYR 22.00</a> <a href="#">
												</a> <span> </span></td>
												<td></td>
												<td></td>
												<td></td>
											</tr> --%>

										</s:iterator>
										<s:if test="%{intlTax !=null}">
										<tr>
												<td></td>
												<td>MALAYSIA AIRPORT TAX-INTL</td>
												<td></td>
												<td> </td>
												<td> </td>
											</tr>
 												<tr>
												<td>SR</td>
												<td><a href="#"><%-- <s:property
													value="intlTax.qty"/> --%> <s:property
													value="intlTax.passengerType" /> x MYR <s:property
													value="intlTax.MYamount" /></a> <a href="#">
												</a> <span> </span></td>
												<td><s:property
													value="intlTax.qty"/></td>
												<td><s:property
													value="intlTax.MYamount" /></td>
												<td><s:property
													value="intlTax.totalMYamount" /></td>
											</tr>
											</s:if>
 								
			<s:if test="%{invObj.companysGstOn=='lintas'}">
			
			
		 	 <tr>
		 	 <th></th>	
		 	 <th></th>	
		 	<th>Price Summary</th>		 	
            <th> GST Summary </th>
            <th colspan="4"> Price Amount  </th>
             </tr>
             <tr>                   
            
            <td> 
            </td>
            <td>
            </td>
            <td> Ticket Price :
            </td>
            <td> 
            </td>
            <td>
              <b><s:property  value="invObj.totBeforeGst"/>  </b>     	  
            </td>
            </tr>
              <tr>
                        
            
            <td> 
            </td>
            <td>
            </td>
            <td> GST On Flights :
            </td>
            <td> 6% of  <s:property  value="invObj.GSTOnFlightsTotMarkup"/>
            </td>
            <td>
              <b><s:property  value="invObj.GSTOnFlights"/> </b>        	  
            </td>
            </tr>
            
              
             <tr>            
            
            <td> 
            </td>
            <td>
            </td>
            <td>   Total Payable :
            </td>
            <td> 
            </td>
            <td>
              <b><s:property  value="invObj.totWithGst"/>  </b>     	  
            </td>
            </tr>
            
           
            
            
            </s:if>
 										<%-- <s:if test="%{invObj.companysGstOn=='lintas'}">
 										<tr>
											<th>GST Summary</th>
											<th>GST Code</th>
											<th>Tax AMT(RM)</th>
											<th>E & O.E</th>
											<th colspan="4">Calculation</th>
										</tr>

										<tr>
											<td><s:property
													value="invObj.GSTOnMArkup" /><br>0.00</td>
											<td>SR<br>ZRE</td>
											<td><s:property
													value="invObj.totGst" /><br>0.00</td>
											<td>E & O.E</td>
											<td colspan="4">TotBefGST : <s:property
													value="invObj.totBeforeGst" /><br>
												TotGST : <s:property
													value="invObj.totGst" /><br>
												TotAmount: <s:property
													value="invObj.totWithGst" />
											</td>

										</tr>
 										
 										</s:if> --%>
 									<s:else>
										<tr>
											<th> </th>
											<th></th>
											<th>Tax AMT(INR)</th>
											<th></th>
											<th colspan="4">Calculation</th>
										</tr>

										<tr>
											<td> </td>
											<td> </td>
											<td>0.00</td>
											<td> </td>
											<td colspan="4">TotalAmount : <s:property
													value="invObj.totBeforeGst"/> 
											 
											</td>

										</tr>
										</s:else> 

									</tbody>
								</table>
							</div>

							<div class="clearfix">
								<div class="col-xs-12 payment-in">
									<div class="panel panel-info">
										<!--   <div class="panel-heading">
    <h4>Payment details</h4>
  </div> -->

										<s:if test="invObj.txDetails.size()>0">
											<h4>Payment details</h4>
											<!-- <div class="panel-body"> -->
											<table class="table table-bordered in-table-border">
												<tr class="info">
													<th>Receipt</th>
													<th>Date</th>
													<th>Remarks</th>
													<th>Payment Type</th>
													<th>Amount</th>
												</tr>
												<s:iterator value="invObj.txDetails">
													<tr>
														<td>DONE</td>

														<td><s:property value="convertDate" /></td>

														<td><s:property value="response_message" /></td>
														<td><s:property value="payment_method" /></td>
														 
												 
												<td><s:property
													value="amount"/></td>
												 
														 
													</tr>
												</s:iterator>
											</table>
										</s:if>
										<s:else>
											<h4>Payment details</h4>
											<table class="table table-bordered in-table-border">
												<tr class="info">
													<th>Receipt</th>
													<th>Date</th>
													<th>Remarks</th>
													<th>Payment Type</th>
													<th>Amount</th>
												</tr>
												<tr>
													<td colspan="5">Payment details not available</td>
												</tr>
											</table>
										</s:else>
										<%-- <s:if
											test="invObj.agentWalletTxDetails.size()>0">
											<h4>Wallet details</h4>
											<table class="table table-bordered in-table-border">
												<tr class="info">

													<th>Date</th>
													<th>Remark</th>
													<th>Amount</th>
													<th>Opening Balance</th>
													<th>Closing Balance</th>
												</tr>
												<s:iterator
													value="invObj.agentWalletTxDetails">
													<tr>

														<td><s:property value="convertDate" /></td>

														<td><s:property value="action" /></td>
														<td><s:property value="amount"/></td>
														<td><s:property value="openingBalance" /></td>
														<td><s:property value="closingBalance" /></td>
													</tr>
												</s:iterator>
											</table>
										</s:if>
										<s:else>
											<h4>Wallet details</h4>
											<table class="table table-bordered in-table-border">
												<tr class="info">

													<th>Date</th>
													<th>Remark</th>
													<th>Amount</th>
													<th>Opening Balance</th>
													<th>Closing Balance</th>
												</tr>
												<tr>
													<td colspan="5">Wallet details not available</td>
												</tr>
											</table>
										</s:else> --%>
									</div>
								</div>
								 
							</div>

							<div class="clearfix signature-lint">

								<div class="col-sm-6">
									<h4>Received By:</h4>

									<p>
										<span>Customers Signature &amp; Chop</span>
									</p>
								</div>

								<div class="col-sm-6 pull-right">
									<div class="pull-right">
									  <h4>
									  <s:if test="%{invObj.companyAddress.companyRole.isDistributor() || invObj.companyAddress.companyRole.isAgent()}">
									   <s:property  value="invObj.companyAddress.Companyname"/>
									</s:if>
									<s:elseif test="%{invObj.companysGstOn=='lintas'}">
									 LINTAS TRAVEL SERVICES (M) SDN BHD
									</s:elseif>
									<s:elseif test="%{invObj.companysGstOn=='tayyarah'}">
									TAYYARAH.COM
									</s:elseif>
									
									   </h4>

										<p>
											<span>Authorized Signature</span>
										</p>
									</div>
								</div>
							</div>
 							</div>
						</form>
					</div>

					<div id="editor"></div>

				</div>
			 </div>
		 </section>
 </div>
	 <%@ include file="DashboardFooter.jsp"%>
 <script type="text/javascript">
  function  sendCustomerInvoiceToCustomer(){
    	   var companyType=$("#companyType").val();
    	  console.log("companyType..."+companyType);
    	  var orderId=$("#orderId").val();
    	  console.log("orderId..."+orderId);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl = newUrl+"sendInvoiceToMail";
  	  console.log("finalUrl..."+finalUrl);
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
									  $('#message').text("Successfully sent invoice to email.");
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
  function  sendFlightVoucherToCustomerEmail(){
    	  var orderId=$("#orderId").val();
    	  console.log("orderId..."+orderId);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl = newUrl+"sendFlightVoucherToCustomerEmail";
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
									  $('#message').text("Successfully sent flight voucher to email.");
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
