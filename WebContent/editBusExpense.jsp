<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Designation</title>
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
												data-parent="#accordion" href="#collapseSix">  Bus Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
										<div class="panel-body">
										<form  action="updatebusexpenseForm" method="post"
					class="form-horizontal" name="busexpensesFormId" id="busexpensesFormId" enctype="multipart/form-data">
											
											
											
												<div class="col-sm-4">

													<div class="form-group">
														<label for="confirmationNumber" class=" control-label"> Confirmation Number </label> 
														<input type="text" autocomplete="off"
															name=busExpense.confirmationNumber
															class="form-control input-sm" id="confirmationNumber"
															value="${busExpense.confirmationNumber}" required
															placeholder="Enter Confirmation Number ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="passengerName" class=" control-label"> Passenger  Name </label> 
														<input type="text" autocomplete="off"
															name=busExpense.passengerName
															class="form-control input-sm" id="passengerName"
															value="${busExpense.passengerName}" required
															placeholder="Enter Passenger Name ">

													</div>
												</div>
											
													<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Bus  Type </label> <input type="text" autocomplete="off"
															name="busExpense.busType"
															class="form-control input-sm" id="bustype"
															value="${busExpense.busType}" 
															placeholder="Bus Type"  required>

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="busExpense.location" id="buslocation" 
															class="form-control input-sm"
															value="${busExpense.location}" 
															placeholder="Location " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">PickUp Location</label> <input type="text" autocomplete="off"
															name="busExpense.pickUp" id="buspickupLocation"
															class="form-control input-sm"
															value="${busExpense.pickUp}" 
															placeholder="PickUp Location "  required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">DropOff Location</label> <input type="text" autocomplete="off"
															name="busExpense.dropOff" id="busdropOffLocation"
															class="form-control input-sm"
															value="${busExpense.dropOff}" 
															placeholder="DropOff Location " required>

													</div>
												</div>
												
																<div class="col-sm-4">
             <div class="form-group">
        <label for="busExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="busExpense.expenseCurrency" required>
         <option value="${busExpense.expenseCurrency}" selected="selected">${busExpense.expenseCurrency}</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">TravelDate</label>
														 <input type="text" autocomplete="off"
															name="busExpense.travelDateTemp" id="bustraveldate"
															class="form-control input-sm"
															value="${busExpense.travelDate}" 
															placeholder="Select Travel Date " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="busExpense.department" id="busdepartmentType"
															class="form-control input-sm"
															value="${busExpense.department}" 
															placeholder="Department"  required>

													</div>
												</div>
						
										<div class="col-sm-4">

													<div class="form-group">
														<label for="supplierPrice" class=" control-label"> Supplier  Price </label> 
														<input type="number" autocomplete="off"
															name=busExpense.supplierPrice
															class="form-control input-sm" id="supplierPrice"
															value="${busExpense.supplierPrice}" required
															placeholder="Enter Supplier Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="basePrice" class=" control-label"> Base  Price </label> 
														<input type="number" autocomplete="off"
															name=busExpense.basePrice
															class="form-control input-sm" id="basePrice"
															value="${busExpense.basePrice}" required
															placeholder="Enter Base Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="managementFee" class=" control-label"> Management Fee   </label> 
														<input type="number" autocomplete="off"
															name=busExpense.managementFee
															class="form-control input-sm" id="managementFee"
															value="${busExpense.managementFee}" required
															placeholder="Enter Management Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label"> Convenience Fee </label> 
														<input type="number" autocomplete="off"
															name=busExpense.convenienceFee
															class="form-control input-sm" id="convenienceFee"
															value="${busExpense.convenienceFee}" required
															placeholder="Enter Convenience Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label"> Service Tax </label> 
														<input type="number" autocomplete="off"
															name=busExpense.serviceTax
															class="form-control input-sm" id="serviceTax"
															value="${busExpense.serviceTax}" required
															placeholder="Enter Service Tax">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="otherTaxes" class=" control-label"> Other Taxes </label> 
														<input type="number" autocomplete="off"
															name=busExpense.otherTaxes
															class="form-control input-sm" id="otherTaxes"
															value="${busExpense.otherTaxes}" required
															placeholder="Enter Other Taxes ">

													</div>
												</div>
												
												
										
										
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="busExpense.totalAmount" id="busexpenseamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${busExpense.totalAmount}" required>

												</div>
											</div>
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="busExpense.expenseReason" id="busexpansereason" placeholder="comments" required><c:out value="${busExpense.expenseReason}"/></textarea>

													</div>
													</div>
													<div class="col-sm-4">

													<div class="form-group">
														<label for="remarks" class=" control-label">Remarks </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="busExpense.remarks" id="busexpanseremarks" placeholder="comments" required><c:out value="${busExpense.remarks}"/></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label>
													 <!-- <input type="text" autocomplete="off"
														name="busExpense.Reimbursable" id="busReimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<select class="form-control input-sm"  name="busExpense.Reimbursable" id="busReimbursable" required >
															
															<option value="${busExpense.reimbursable}" selected="selected">${busExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													<%-- <select class="form-control input-sm"  name="busExpense.isBillable" id="busbillable" >
															<option value="" selected="selected">select Billable Type</option>
															
																<option value="true">Yes</option>
															
																<option value="false">No</option>
																</select> --%>
																<select class="form-control input-sm"
														name="busExpense.Billable" value="${busExpense.billable}" required>
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>

												</div>
											</div>
											<!-- 	<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="busuploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="busExpense.receiptFilename">
									<input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}">
								</div>

								ng-file-select="onFileSelect($files)"
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>

										<div id="filesize">7.6 KB</div>
										<div id="filetype">image/jpeg</div>
										<div id="filedim">90 x 50</div>
									</div>
								</div>
							</div>
												</div>
											</div> -->
											
																			<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimagebus" class="col-sm-8control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="busExpense.receiptFilename" value="${busExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="busExpense.receiptFilename" required> 
								</div>
							
							<!-- 	ng-file-select="onFileSelect($files)" -->
								 <div class="col-sm-6 ">
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
								<input type="hidden" name="busExpense.id" value="${id}" id="updatebusexpenseForm" />					
														<%-- <button type="button"  name="busExpense.id" value="${id}" id="updatebusexpenseForm" 
								class="btn btn-primary btn-lg">Bus Expanse Update</button> --%>
								<button type="button"   id="updatebusexpenseSubmit" 
								class="btn btn-primary btn-lg">Bus Expanse Update</button>
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
									$('#bustraveldate').datepicker({  
										dateFormat : 'dd-mm-yy',
										 onSelect : function(selectedDate) {
												
											 $(this).valid();
											 
										 }
									}); 
									
									
									$(document).ready(function(){
										  
										   $('#updatebusexpenseSubmit').click(function(){
											   $("#busexpensesFormId").valid();
											   if($("#busexpensesFormId").valid()){
												   document.getElementById("busexpensesFormId").submit();
											   }
										   });
										   $("#busexpensesFormId").validate({
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