<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html>
<html>
<head>
<%-- <script type="text/javascript" src="js/jquery.min.js"></script> --%>
<script type="text/javascript" src="js/jspdf.min.js"></script>
<script type="text/javascript" src="js/html2canvas.min.js"></script>
<%-- <script src="js/jspdf.debug.js"></script> --%>
  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agent Invoice</title>
<link rel="stylesheet" href="css/alert.css">
</head>
<body>

 <div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		
		
		
		<s:if test="%{invObj.invoiceType == 0}">
		<h1>My Flight Invoice</h1>
		</s:if>
		<s:elseif test="%{invObj.invoiceType == 1}">
 		<h1>Agent's Flight Invoice</h1>
   		</s:elseif>
   
   
   
   
   
			
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
   
<!-- 
        <button  type="button"    class="btn btn-primary but no-print" onclick="jQuery('invoice').print()" >Print <i class="fa fa-arrow-circle-right"></i> </button>
  -->
           <!-- <button type="button"  id="pdf"  class="btn btn-primary but no-print">PDF <i class="fa fa-arrow-circle-right"></i></button>
 -->
 
  
             <!--  <button type="button" class="btn btn-primary but no-print" >Mail   <i class="fa fa-arrow-circle-right"></i></button> -->
          <input type="hidden" value="<s:property  value="invObj.yourRef"/>" id="orderId"> 
          
          
         <s:if test="%{#session.User.userrole_id.isSuperuser()}">
          <input type="hidden" value="super" id="companyType"> 
          <button class=" btn btn-primary but no-print" onclick="sendCustomerInvoiceToCustomer();">Send Mail <i class="fa fa-arrow-circle-right"></i> </button>
          </s:if>
          <s:elseif test="%{#session.Company.companyRole.isDistributor()}">
           <button class=" btn btn-primary but no-print" onclick="sendCustomerInvoiceToCustomer();">Send Mail <i class="fa fa-arrow-circle-right"></i> </button>
           <input type="hidden" value="dis" id="companyType"> 
          </s:elseif>
            <s:elseif test="%{#session.Company.companyRole.isAgent()}">
          <!--  <button class=" btn btn-primary but no-print" onclick="sendCustomerInvoiceToCustomer();">Send Mail <i class="fa fa-arrow-circle-right"></i> </button> -->
          </s:elseif>
          <button type="button" class="btn btn-primary but no-print" id="h4"  style="display: none" >sending-------<i class="fa fa-arrow-circle-right"></i></button>
         </div>  

<!--  <button  type="button"    class="btn btn-primary but no-print"  id="pdf" >Print <i class="fa fa-arrow-circle-right"></i> </button> -->
<!--  <button type="button"  id="createpdf" class="btn btn-primary but no-print" onclick="clickpdf();">
							pdf <i class="fa fa-arrow-circle-right"></i>
						</button> -->
						
						
 <div id="invoice" class="clearfix">
 <form action=""  method="post"  id="form">
<div class="row" >
<%-- <div><div><h2>To :  <s:property  value="invObj.invoiceCompany.Companyname"/></h2> </div><div align="right"><h2> From :  <s:property  value="invObj.parentCompany.Companyname"/></h2></div></div>   --%>
 <s:if test="%{invObj.parentCompany.companyRole.isDistributor() || invObj.parentCompany.companyRole.isAgent()}">
 

			<div class="col-xs-4">
			<div class="logo">
			      <img src="<s:url action='ImageAction?imageId=%{invObj.parentCompany.Imagepath}'/>">
			</div>
			</div>
			<div class="col-sm-8">
			<div class="lint-invoice-adde text-right">
			<h2>    <s:property  value="invObj.parentCompany.Companyname"/> </h2>
			<!--     <p>GST Reg.No: 001363410944 --><br>
			<s:property  value="invObj.parentCompany.Address"/> <br>
			Tel: <s:property  value="invObj.parentCompany.Phone"/><!--  Fax: +603-9287 2323 --><br>
			<b>Email:</b><s:property  value="invObj.parentCompany.Email"/> <b>Website:</b><s:property  value="invObj.parentCompany.Website"/></p>
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

  <h4 class="text-center"><span>Tax Invoice</span></h4>
  </div>

  <div class="invoice-addres clearfix">
        <div class="col-xs-5">
          <div class="panel panel-default">
                 <s:if test="%{invObj.userDetails.size()>0}">
                 <s:iterator value="invObj.userDetails">
                  <div class="panel-body">
                   <h4>To: <a href="#"><s:property  value="invObj.invoiceCompany.Companyname"/></a></h4>
                   
                   	<s:property  value="invObj.invoiceCompany.Address"/> <br>
					Tel: <s:property  value="invObj.invoiceCompany.Phone"/><!--  Fax: +603-9287 2323 --><br>
					<b>Email:</b><s:property  value="invObj.invoiceCompany.Email"/> <b>Website:</b><s:property  value="invObj.invoiceCompany.Website"/></p>
			
                   
                   
                   
                   <%--  <p>
                     <s:property  value="Address"/>
                    </p>
                    <p>TEL: <s:property  value="Phone"/></p>
                      <p>Mobile:  <s:property  value="Mobile"/></p>
                   <!--  <p>Fax: 99876544</p> -->
                    <p>Attn: <s:property  value="Firstname"/> <s:property  value="Lastname"/></p> --%>

                  </div>
                  </s:iterator>
                  </s:if>
                </div>
        </div>

<div class="col-xs-6">

           <table class="table inn-num table-bordered">
      
         <tbody>
          <tr>
            <td>Invoice No</td>
            <td><a href="#"> <s:property  value="invObj.invNo"/></a></td>
           
            
          </tr>
          <tr>
            <td>ACCT Code</td>
            <td><a href="#"> <s:property  value="invObj.companyAddress.registerNumber"/></a></td>
          </tr>
          <tr>
            <td>Consultant</td>
            <td><a href="#"><s:property  value="invObj.consultant"/></a></td>
          </tr>
          <tr>
            <td>Book No</td>
            <td><a href="#"><s:property  value="invObj.yourRef"/></a></td>
          </tr>

          <tr>
            <td>Your Ref</td>
            <td><a href="#"><s:property  value="invObj.yourRef"/></a></td>
          </tr>
          
          <tr>
            <td>Date</td>
            <td><a href="#"><s:property  value="invObj.bookedDate"/></a></td>
          </tr>
          </tbody>
        </table>
     </div>   
      </div> <!-- / end client details section -->

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
            <th><h4>Total</h4></th>
          </tr>
        </thead>
            <tbody>
        <s:iterator  value="invObj.tripParticulars" >
          <tr>
           <s:if test="%{invObj.companysGstOn=='lintas'}">
			<td></td>
			 </s:if>
			 <s:else>
			 <td></td>
			 </s:else>
           <td> <s:property  value="operatedByCode"/>  <s:property  value="flightNumber"/>  <span><s:property  value="originCode"/>/<s:property  value="destinationCode"/> <s:property  value="convertDate"/> <%--  <s:property  value="departureTime"/>/<s:property  value="arrivalTime"/>  --%> </span>  <span><s:property  value="tripType"/></span></td>
             
            <td ></td>
             <td ></td>
              <td ></td>
          </tr>
          </s:iterator>
           <s:iterator  value="invoiceDetails">
            
          <tr>
           <s:if test="%{invObj.companysGstOn=='lintas'}">
		  <td>ZRE</td>
			 </s:if>
			 <s:else>
			 <td></td>
			 </s:else>
          
            <td><a href="#"><s:property  value="firstName"/><s:property  value="lastName"/></a> <a href="#"></a> </td>
           <td ><s:property  value="qty"/></td>
             <td ><s:property  value="price"/></td>
              <td ><s:property  value="totPrice"/></td>
          </tr>
          
          <tr>
          <s:if test="%{invObj.companysGstOn=='lintas'}">
		  <td>ZRE</td>
			 </s:if>
			 <s:else>
			 <td></td>
			 </s:else>
          
            <td><a href="#">AIRPORT TAX</a> <a href="#"> </a> <span> </span>  </td>
            <td><s:property  value="qty"/></td>
             <td><s:property  value="tax"/></td>
              <td><s:property  value="totalTax"/></td>
          </tr>
         
          </s:iterator>
            	<s:if test="%{intlTax!=null}">
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
             <td> Agency Markup :
            </td>
            <td> 
            </td>
            <td>
              <b><s:property  value="invObj.GSTOnTotMarkup"/></b>        	  
            </td>
            </tr>
              <tr>       
           
           
            <td> 
            </td>
            <td>
            </td>
             <td>  GST On Markup :
            </td>
            <td> 6% of  <s:property  value="invObj.GSTOnTotMarkup"/>
            </td>
            <td>
              <b><s:property  value="invObj.totGst"/> </b>      	  
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
        	<s:if test="%{invObj.companysGstOn=='tayyarah'}">
            <tr>
			 <th>Agent Commission </th>
			 <th>TDS</th>
			  <th>Final Commission</th>
			 <!-- <th>Tax AMT(INR)</th> -->
			<th>Net Payable(INR)</th>
			 <th >TotalAmount</th>
			 </tr>
 			 <tr>
 			 <td><s:property value="invObj.totAgentComm"/></td>
			 <td>(-)<s:property value="invObj.totalTDS"/></td> <td><s:property value="invObj.finalCommWithTdsDeduct"/> </td>  <td><s:property value="invObj.totBeforeGst"/> </td> 
			  <td> <s:property value="invObj.totAmount"/>  </td>
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
<!-- 
<th>
AgentComm
</th> -->
</tr>
 <s:if test="invObj.txDetails.size()>0">
 <s:iterator value="invObj.txDetails">
<tr>
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
</td>
<!-- <td><s:property  value="%{#session.agentCommInvoiceObj.totMarkup}"/></td>-->
 </tr>
</s:iterator>

</s:if>
<s:else>
<tr>
<td colspan="6">
Payment details not available

</td>
 </tr>
</s:else>
 </table>
 <h4>Wallet details</h4>
 <table class="table table-bordered in-table-border"  >
<tr class="info">
<th>
Created At
</th>
<th>
Action
</th>
<th>
Amount
</th>
<th>
OpeningBal
</th>
<th>
ClosingBal
</th>
<!-- <th>
Currency
</th> -->
 
</tr>
<s:if test="invObj.agentWalletTxDetails.size()>0">
<s:iterator value="invObj.agentWalletTxDetails">
<tr>
<td>
<s:property  value="convertDate"/>
</td>
 
<td>
<s:property  value="action"/>
</td>

<td>
<s:property  value="amount"/>
</td>
<td>
<s:property  value="openingBalance"/>
</td>
<td>
<s:property  value="closingBalance"/>
</td>
<%-- <td><s:property  value="currency"/></td> --%>
 </tr>
</s:iterator>
</s:if>
<s:else>
<tr>
<td colspan="6">
Wallet details not available

</td>
 </tr>


</s:else>

</table>
  
</div>
  </div>
 <%--  <div class="col-xs-12">
   <div class="panel panel-info">
    
    <div class="panel-body payment-invo-made">
      <p class="h4">
         *Please make CHEQUE Payable to 
           <s:if test="%{invObj.companyAddress.companyRole.isDistributor() || invObj.companyAddress.companyRole.isAgent()}">
									   <s:property  value="invObj.companyAddress.Companyname"/>
									</s:if>
									<s:elseif test="%{invObj.companysGstOn=='lintas'}">
									 LINTAS TRAVEL SERVICES (M) SDN BHD
									</s:elseif>
									<s:elseif test="%{invObj.companysGstOn=='tayyarah'}">
									TAYYARAH.COM
									</s:elseif>

         <ol >
      <li>MAYBANK BERHAD <span>A/C: 5677-2344-2570</span></li>
      <li>MAYBANK BERHAD <span>A/C: 5677-2344-2570</span></li>
     
    </ol>
      
      
    </div>
  </div>
  </div> --%>
</div>

<div class="clearfix signature-lint">

  <div class="col-sm-6">
    <h4>Received By:</h4>

    <p><span>Customers Signature &amp; Chop</span></p>
  </div>

  <div class="col-sm-6 pull-right">
    <div class="pull-right">
      <h4>  <s:if test="%{invObj.parentCompany.companyRole.isDistributor() || invObj.parentCompany.companyRole.isAgent()}">
									   <s:property  value="invObj.parentCompany.Companyname"/>
									</s:if>
									<s:elseif test="%{invObj.companysGstOn=='lintas'}">
									 LINTAS TRAVEL SERVICES (M) SDN BHD
									</s:elseif>
									<s:elseif test="%{invObj.companysGstOn=='tayyarah'}">
									TAYYARAH.COM
									</s:elseif>

    <p><span>Authorised Signature</span></p>
    </div>
  </div>
  </div>




</div>



</form>
 
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
  		var finalUrl = newUrl+"sendInvoiceToMail";
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
</body>
</html>
 