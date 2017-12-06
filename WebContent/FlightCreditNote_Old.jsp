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
	
<title>Flight Credit Note</title>
<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript"  >
var form ;
var totalwidth;
function clickpdf() {
	  totalwidth = $('#invoice').width();
	  
	  form = $('#form'),
	  cache_width = form.width(),
	  a4  =[ 795.28,  841.89];  // for a4 size paper width and height
 $('body').scrollTop(0);
 createPDF();
}

//create pdf
function createPDF(){
 getCanvas().then(function(canvas){
  var 
  img = canvas.toDataURL("image/png"),
  
  doc = new jsPDF({
          unit:'px', 
          format:'a2'
        });     
        doc.addImage(img, 'JPEG', 20, 20);
        
        doc.save('FlightInvoice.pdf');
        form.width(totalwidth).css('width','none');
 });
}
 
// create canvas object
function getCanvas(){
form.width((a4[0]*1.33333) -80).css('max-width','none');
 return html2canvas(form,{
     imageTimeout:2000,
     //removeContainer:true
    }); 
}

 (function(){
	 console.log('hi');
	 var totalwidth = $('#invoice').width()
	 var 
	  form = $('#form'),
	  cache_width = form.width(),
	  a4  =[ 595.28,  841.89];  // for a4 size paper width and height
	  
	  
	  
	// $('#createpdf').on('click',function(){
		
	
	  
	 }());
 
 </script>

</head>
<body>
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

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<s:if test="%{#session.Company.Companyid==creditNoteData.companyId}">
		<h1>Flight Customer Credit Note</h1>
	</s:if>
	<s:else>
	<h1>Flight Credit Note</h1>
	 </s:else>
			
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<!-- Main content -->
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
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
			
			<div class="row">
			 <div class="col-sm-12">
			 <s:if test="creditNoteData.message!=null">
			<div class="in-head" style="border:1px solid black ;color:red">

								<h4 class="text-center" >
									<span><s:property value="creditNoteData.message"></s:property></span>
								</h4>
							</div>
			 </s:if>
				 
 					<div id="invoice" class="clearfix">
 					<form action="" method="post" id="form">
						<div class="row">
						 <s:if test="%{#session.Company.Companyid==creditNoteData.companyId}">
						<div class="col-xs-4">
					<div class="logo">
                <img src="<s:url action='ImageAction?imageId=%{creditNoteData.companyAddress.Imagepath}'/>">
              </div>
				</div>
						<div class="col-sm-8">
          <div class="lint-invoice-adde text-right">
             <h2>    <s:property  value="creditNoteData.companyAddress.Companyname"/> </h2>
         <!--     <p>GST Reg.No: 001363410944 --><br>
        <s:property  value="creditNoteData.companyAddress.Address"/> <br>
            Tel: <s:property  value="creditNoteData.companyAddress.Phone"/><!--  Fax: +603-9287 2323 --><br>
            <b>Email:</b><s:property  value="creditNoteData.companyAddress.Email"/><br> <b>Website:</b><s:property  value="creditNoteData.companyAddress.Website"/></p>
          </div>
            </div>
			 </s:if>
			 <s:else>
			 			<div class="col-xs-4">
					<div class="logo">
                <img src="<s:url action='ImageAction?imageId=%{creditNoteData.companyAddress.Imagepath}'/>">
              </div>
				</div>
						<div class="col-sm-8">
          <div class="lint-invoice-adde text-right">
             <h2>    <s:property  value="creditNoteData.companyAddress.Companyname"/> </h2>
         <!--     <p>GST Reg.No: 001363410944 --><br>
        <s:property  value="creditNoteData.companyAddress.Address"/> <br>
            Tel: <s:property  value="creditNoteData.companyAddress.Phone"/><!--  Fax: +603-9287 2323 --><br>
            <b>Email:</b><s:property  value="creditNoteData.companyAddress.Email"/><br> <b>Website:</b><s:property  value="creditNoteData.companyAddress.Website"/></p>
          </div>
            </div>
			 
			 
			 </s:else>
			 
			 
							 
						</div>

						<div class="row">

							<div class="in-head">

								<h4 class="text-center">
									<span>CREDIT NOTE</span>
								</h4>
							</div>

							<div class="invoice-addres clearfix">
								<div class="col-xs-5">
									<div class="panel panel-default">

										<div class="panel-body">
										
											 
											 <h4>
											<%-- <s:if test="creditNoteData.agencyAddressList!=null"> --%>
											<s:if test="%{#session.Company.Companyid==creditNoteData.companyId}">
											To: <a href="#"><s:property value="creditNoteData.customerName"></s:property></a> <br>
											Address:<s:property value="creditNoteData.customerAdress"></s:property>
											</s:if>
											<s:else>
											 
													To: <a href="#"><s:property value="creditNoteData.childCompanyAddress.Companyname"></s:property></a>
													 
											</s:else>
											 <%-- </s:if> --%>
												
											</h4>

										</div>
									</div>
									
								</div>

								<div class="col-xs-6">

									<table class="table inn-num table-bordered">

										<tbody>
											<tr>
												<td>CN No : </td>
												<td><a href="#"><s:property value="creditNoteData.CNINumber"></s:property></a></td>


											</tr>
											<tr>
												<td>Confirmation Number: </td>
												<td><a href="#"><s:property value="creditNoteData.orderId"></s:property></a></td>

 												</tr>
											
											
											<tr>
												<td>Date : </td>
												<td><a href="#"><s:property value="creditNoteData.issuedDate"></s:property></a></td>
											</tr>
											<!-- <tr>
												<td>Act Code : </td>
												<td><a href="#">3000/R002</a></td>
											</tr> -->
											<tr>
												<td>Staff : </td>
												<td><a href="#"><s:property value="creditNoteData.staff"></s:property></a></td>
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
											 <th><h4>Particular(s)</h4></th>
											<th><h4>Qty</h4></th>
											<th><h4>Price</h4></th>
											<th><h4>Total (<s:property value="creditNoteData.baseCurrency"></s:property>)</h4></th>
										</tr>
									</thead>
									<tbody>
									<s:if test="creditNoteData.orderCustomerParticulars!=null">
												<s:iterator value="creditNoteData.orderCustomerParticulars"
													status="rowCount" var="obj">
 												<tr>
												<td><s:property value="firstName"/>/<s:property value="lastName"/>
												<s:if test="obj.mobile!=null">
												 - <s:property value="mobile"/><br>
												</s:if>
												 
												 </td>

												<td></td>
 												<td></td>
												<td></td>
											</tr>
													 

												</s:iterator>
											</s:if>
												<s:if test="creditNoteData.tripParticulars!=null">
												<s:iterator value="creditNoteData.tripParticulars"
													status="rowCount">
 												<tr>
												<td><s:property value="originCode"/>/<s:property value="destinationCode"/> - <s:property value="flightNumber"/><br>
													 </td>

												<td></td>
 												<td></td>
												<td></td>
											</tr>
													 

												</s:iterator>
											</s:if>
									
									 <tr>
												<td>AIR TICKET REFUND</td>
												 <td>1</td>
												<td><s:property value="creditNoteData.totalBookingAmount"></s:property></td>
												<td><s:property value="creditNoteData.totalBookingAmount"></s:property></td>
											</tr>
											<!-- <tr>
												<td>Airport Tax</td>
												 <td>1</td>
												<td>18.00</td>
												<td>18.00</td>
											</tr> -->
											<tr>
												<td>LESS CANCELLATION FEES</td>
												 <td>1</td>
												<td>-<s:property value="creditNoteData.cancellationFees"></s:property></td>
												<td>-<s:property value="creditNoteData.cancellationFees"></s:property></td>
											</tr>
											<tr>
												<td>LESS DOCUMENTATION FEES</td>
												 <td>1</td>
												<td>-<s:property value="creditNoteData.convenienceFees"></s:property></td>
												<td>-<s:property value="creditNoteData.convenienceFees"></s:property></td>
											</tr>
											
									<%--  <tr>
											 <th colspan="4">Markup : <s:property value="creditNoteData.markup"></s:property></th>
											 
										</tr>
									  --%>
 
										<!-- <tr>
											 <th colspan="4">MALAYSIA RINGGITS FOUR HUNDRED TWENTY-NINE AND 46 / 100</th>
											<th>Tax AMT(RM)</th>
											<th>E & O.E</th>
											<th colspan="4">Calculation</th>
										</tr>
 -->									<s:if test="%{creditNoteData.LintasGstOn=='lintas'}">
  											<s:if test="%{#session.Company.Companyid==creditNoteData.companyId}">
  											<tr>
											<td> </td>
											<td> </td>
											<td> </td>
											  <td colspan="4"><%-- TotAmount(<s:property value="creditNoteData.baseCurrency"></s:property>): <s:property value="creditNoteData.totwithGstAmount"></s:property> --%></td>
											  
											 
										 </tr>
										<tr>
											 
											<td>Tax Invoice No : <s:property value="creditNoteData.taxInvoiceNo"></s:property></td>
											<td>Tax Invoice Date : <s:property value="creditNoteData.taxInvoiceDate"></s:property></td>
											<td> </td>
											<td colspan="4">Total Amount : <s:property value="creditNoteData.totwithGstAmount"></s:property></td>

										</tr>
  											 </s:if>
 
 
 										<s:else>
										<tr>
											<td> </td>
											<td> </td>
											<td> </td>
											  
											<td colspan="4"><%-- TotBefGST : <s:property value="creditNoteData.refundedAmount"></s:property><br>
												TotGST : <s:property value="creditNoteData.gstAmount"></s:property> --%></td>
										 </tr>
										<tr>
											 
											<td>Tax Invoice No : <s:property value="creditNoteData.taxInvoiceNo"></s:property></td>
											<td>Tax Invoice Date : <s:property value="creditNoteData.taxInvoiceDate"></s:property></td>
											<td> </td>
											<td colspan="4">Total Amount : <s:property value="creditNoteData.totwithGstAmount"></s:property></td>

										</tr>
										</s:else>
										</s:if>
										<s:if test="%{creditNoteData.LintasGstOn=='tayyarah'}">
										<s:if test="%{#session.Company.Companyid==creditNoteData.companyId}">
											<tr>
											<td> </td>
											<td> </td>
											<td> </td>
											   <td colspan="4">Total Amount : <s:property value="creditNoteData.totwithGstAmount"></s:property> </td>
											  
											 
											<%-- <td colspan="4">TotBefGST : <s:property value="creditNoteData.refundedAmount"></s:property><br>
												TotGST : <s:property value="creditNoteData.gstAmount"></s:property></td> --%>
										 </tr>
										<tr>
											 
											<td>Tax Invoice No : <s:property value="creditNoteData.taxInvoiceNo"></s:property></td>
											<td>Tax Invoice Date :<s:property value="creditNoteData.taxInvoiceDate"></s:property></td>
											<td> </td>
											<td colspan="4">Total Amount : <s:property value="creditNoteData.totwithGstAmount"></s:property></td>

										</tr>
											
											</s:if>
										 <s:else>
										<tr>
											<td> </td>
											<td> </td>
											<td> </td>
											 
											 
											<td colspan="4"><%-- TotBefGST : <s:property value="creditNoteData.refundedAmount"></s:property><br>
												TotGST : <s:property value="creditNoteData.gstAmount"></s:property> --%></td>
										 </tr>
										<tr>
											 
											<td>Tax Invoice No : <s:property value="creditNoteData.taxInvoiceNo"></s:property></td>
											<td>Tax Invoice Date : <s:property value="creditNoteData.taxInvoiceDate"></s:property></td>
											<td> </td>
											<td colspan="4">Total Amount : <s:property value="creditNoteData.totwithGstAmount"></s:property></td>

										</tr>
										 </s:else>
										
										</s:if>
									</tbody>
								</table>
							</div>
							 
							<div class="col-xs-8" >
							<textarea rows="" cols="5" style="border:1px solid ; height: 100px;width:600px"> Reason Code:</textarea>
						 
						  </div>
							<div class="clearfix signature-lint">
 							<div class="col-sm-6 pull-right">
									<div class="pull-right">
									
									<h4><s:property value="creditNoteData.companyAddress.Companyname"></s:property></h4>
									
									 
									 
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
				<!-- /.row -->
				<!-- Main row -->



			</div>
			<!-- table-responsive -->
		</section>

	</div>
	<!-- /.row -->
	<!-- Main row -->


	<%@ include file="DashboardFooter.jsp"%>

	<!-- /.content -->



	 
</body>
</html>
