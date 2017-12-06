<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edit visa</title>
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
.main-header{
    margin-left: -113px !important;
    }
</style>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet" href="css/alert.css">
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<script src="js/angular.js" type="text/javascript"></script>

</head>
<body class="container" data-ng-controller="AppCtrl">

		<div class="panel panel-default ExpensesPanel">
								 	<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseNine">Visa Expenses </a>
										</h4>
									</div>
								 	
										<div class="panel-body">
									<form action="updateVisaExpense" method="post"
					class="form-horizontal" name="updateVisaExpense" id="updateVisaExpense" enctype="multipart/form-data">
					
					
					
													<div class="col-sm-4">

													<div class="form-group">
														<label for="confirmationNumber" class=" control-label"> Confirmation Number </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.confirmationNumber
															class="form-control input-sm" id="confirmationNumber"
															value="${visaExpense.confirmationNumber}" required
															placeholder="Enter Confirmation Number ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="passengerName" class=" control-label"> Passenger  Name </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.passengerName
															class="form-control input-sm" id="passengerName"
															value="${visaExpense.passengerName}" required
															placeholder="Enter Passenger Name ">

													</div>
												</div>

												<div class="col-sm-4">

													<div class="form-group">
														<label for="travelDate" class=" control-label"> Travel Date </label> 
														<input type="text" autocomplete="off"
															name=visaExpense.travelDateTemp
															class="form-control input-sm" id="travelvisaDate"
															value="${visaExpense.travelDate}" required
															placeholder="Enter Travel Date ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="discription" class=" control-label"> Discription </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="visaExpense.description"  placeholder="Discription" required><c:out value="${visaExpense.description}"/></textarea>
														</div>
												</div>

													<div class="col-sm-4">

													<div class="form-group">
														<label for="remarks" class=" control-label"> Remarks </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="visaExpense.remarks"  placeholder="remarks" required><c:out value="${visaExpense.remarks}"/></textarea>
															

													</div>
												</div>

												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="supplierPrice" class=" control-label"> Supplier  Price </label> 
														<input type="number" autocomplete="off"
															name=visaExpense.supplierPrice
															class="form-control input-sm" id="supplierPrice"
															value="${visaExpense.supplierPrice}" required
															placeholder="Enter Supplier Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="basePrice" class=" control-label"> Base  Price </label> 
														<input type="number" autocomplete="off"
															name=visaExpense.basePrice
															class="form-control input-sm" id="basePrice"
															value="${visaExpense.basePrice}" required
															placeholder="Enter Base Price">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="otherTaxes" class=" control-label"> Other Taxes </label> 
														<input type="number" autocomplete="off"
															name=visaExpense.otherTaxes
															class="form-control input-sm" id="otherTaxes"
															value="${visaExpense.otherTaxes}" required
															placeholder="Enter Other Taxes ">

													</div>
												</div>
													<div class="col-sm-4">

													<div class="form-group">
														<label for="managementFee" class=" control-label">Management Fee</label> 
														<input type="number" autocomplete="off"
															name=visaExpense.managementFee
															class="form-control input-sm" id="managementFee"
															value="${visaExpense.managementFee}" required
															placeholder="Enter Management Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label"> Convenience Fee </label> 
														<input type="number" autocomplete="off"
															name=visaExpense.convenienceFee
															class="form-control input-sm" id="convenienceFee"
															value="${visaExpense.convenienceFee}" required
															placeholder="Enter Convenience Fee">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label"> Service Tax </label> 
														<input type="number" autocomplete="off"
															name=visaExpense.serviceTax
															class="form-control input-sm" id="serviceTax"
															value="${visaExpense.serviceTax}" required
															placeholder="Enter Service Tax ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="vfsCharges" class=" control-label"> VFS Charges </label> 
														<input type="number" autocomplete="off"
															name=visaExpense.vfsCharges
															class="form-control input-sm" id="vfsCharges"
															value="${visaExpense.vfsCharges}" required
															placeholder="Enter VFS Charges  ">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="courierCharges" class=" control-label"> Courier Charges</label> 
														<input type="number" autocomplete="off"
															name=visaExpense.courierCharges
															class="form-control input-sm" id="courierCharges"
															value="${visaExpense.courierCharges}" required
															placeholder="Enter Courier Charges ">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="totalInvoiceAmount" class=" control-label"> Total Amount </label> 
														<input type="number" autocomplete="off"
															name=visaExpense.totalInvoiceAmount
															class="form-control input-sm" id="totalInvoiceAmount"
															value="${visaExpense.totalInvoiceAmount}" required
															placeholder="Enter Total Amount ">

													</div>
												</div>
												
												
												<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimage" class="col-sm-6 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-6">

							<div class="row">
								<div class="col-sm-4 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="visaExpense.receiptFilename" accept="file_extension|audio/*|video/*|image/*|media_type" value="${visaExpense.receiptFilename}" ng-file-select="onFileSelect($files)" name="visaExpense.receiptFilename" required /> 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-4">
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
											<input type="hidden" name="visaExpense.id" value="${id}"  id="visaexpensesubmitupdate" />				
														<%-- <button type="submit"  name="visaExpense.id" value="${id}"  id="visaexpensesubmitupdate" 
								class="btn btn-primary btn-lg">Visa Expanse Update</button> --%>
								<button type="submit"  id="VisaSubmit"
								class="btn btn-primary btn-lg">Visa Expanse Update</button>
								
								 <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
					
					
										</form>
										</div>
										</div>
										<%@ include file="DashboardFooter.jsp"%>	
								<script >
									$('#travelvisaDate').datepicker({  
										dateFormat : 'dd-mm-yy',
										 onSelect : function(selectedDate) {
												
											 $(this).valid();
											 
										 }
									}); 
									
									$(document).ready(function(){
										  
										   $('#VisaSubmit').click(function(){
											   $("#updateVisaExpense").valid();
											   if($("#updateVisaExpense").valid()){
												   document.getElementById("updateVisaExpense").submit();
											   }
										   });
										   $("#updateVisaExpense").validate({
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