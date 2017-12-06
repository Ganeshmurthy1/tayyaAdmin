<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Car Expense</title>
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
    
.error {
    color:red;
}
.valid {
    color:green;
}
</style>
<link rel="stylesheet" href="css/alert.css">
<script src="js/angular.js" type="text/javascript"></script>
 
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body class="container" data-ng-controller="AppCtrl">

			                  	<div class="panel panel-default ExpensesPanel">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseFour">  Car Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
										<div class="panel-body">
										<form action="updatecarexpenseForm" method="post"
					class="form-horizontal" name="carexpensesFormId" id="carexpensesFormId" enctype="multipart/form-data">
													<%-- <div class="col-sm-12">
											<div  style="text-align:center;">
												<div class="form-group">
													<label for="hotelName" class=" control-label">Order Id </label> <input type="text" autocomplete="off"
														name="hotelOrderHotelData.name"
														class="form-control input-sm" placeholder="Order Id"
														value="">

												</div>
											</div>
											</div>
											
											<center>
				<h2>OR</h2>
			</center>
							 --%>
							 <div class="col-sm-4">

													<div class="form-group">
														<label for="confirmationNumber" class=" control-label"> Confirmation Number </label> 
														<input type="text" autocomplete="off"
															name=carExpense.confirmationNumber
															class="form-control input-sm" id="confirmationNumber"
															value="${carExpense.confirmationNumber}" required
															placeholder="Enter Confirmation Number ">

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="passengerName" class=" control-label"> Passenger  Name </label> 
														<input type="text" autocomplete="off"
															name=carExpense.passengerName
															class="form-control input-sm" id="passengerName"
															value="${carExpense.passengerName}" required
															placeholder="Enter Passenger Name ">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="supplierPrice" class=" control-label"> Supplier  Price </label> 
														<input type="number" autocomplete="off"
															name=carExpense.supplierPrice
															class="form-control input-sm" id="supplierPrice"
															value="${carExpense.supplierPrice}" required
															placeholder="Enter Supplier Price">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="basePrice" class=" control-label"> Base  Price </label> 
														<input type="number" autocomplete="off"
															name=carExpense.basePrice
															class="form-control input-sm" id="basePrice"
															value="${carExpense.basePrice}" required
															placeholder="Enter Base Price">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="otherTaxes" class=" control-label"> Other Taxes </label> 
														<input type="number" autocomplete="off"
															name=carExpense.otherTaxes
															class="form-control input-sm" id="otherTaxes"
															value="${carExpense.otherTaxes}" required
															placeholder="Enter Other Taxes ">

													</div>
												</div>
												
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="tollOrParkingCharges" class=" control-label"> Toll or Parking Charges </label> 
														<input type="number" autocomplete="off"
															name=carExpense.tollOrParkingCharges
															class="form-control input-sm" id="tollOrParkingCharges"
															value="${carExpense.tollOrParkingCharges}" required
															placeholder="Enter toll/park charges">

													</div>
												</div>		
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="driverAllowanceDay" class=" control-label"> Driver Allowance Day </label> 
														<%-- <input type="number" autocomplete="off" class="form-control input-sm" name="carExpense.driverAllowanceDay   value="${carExpense.driverAllowanceDay}" 
															placeholder="Enter Driver Allowance Day" required> --%>
															<input type="number" autocomplete="off"
															name="carExpense.driverAllowanceDay"
															class="form-control input-sm" id="driverAllowanceDay"
															value="${carExpense.driverAllowanceDay}" required
															placeholder="Enter Driver Allowance Day">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="driverAllowanceNight" class=" control-label"> Driver Allowance Night </label> 
														<input type="number" autocomplete="off"
															name="carExpense.driverAllowanceNight"
															class="form-control input-sm" id="driverAllowanceNight"
															value="${carExpense.driverAllowanceNight}" required
															placeholder="Enter Driver Allowance Night">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="managementFee" class=" control-label">Management Fee</label> 
														<input type="number" autocomplete="off"
															name="carExpense.managementFee"
															class="form-control input-sm" id="managementFee"
															value="${carExpense.managementFee}" required
															placeholder="Enter Management Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="convenienceFee" class=" control-label"> Convenience Fee </label> 
														<input type="number" autocomplete="off"
															name="carExpense.convenienceFee"
															class="form-control input-sm" id="convenienceFee"
															value="${carExpense.convenienceFee}" required
															placeholder="Enter Convenience Fee">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="remarks" class=" control-label"> Remarks </label> 
														<textarea rows="1" cols="1" class="form-control input-sm" name="carExpense.remarks" placeholder="remarks" required><c:out value="${carExpense.remarks}"/></textarea>
															</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="serviceTax" class=" control-label"> Service Tax </label> 
														<input type="number" autocomplete="off"
															name="carExpense.serviceTax"
															class="form-control input-sm" id="serviceTax"
															value="${carExpense.serviceTax}" required
															placeholder="Enter Service Tax ">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="extraKM" class=" control-label"> Extra KM </label> 
														<input type="text" autocomplete="off"
															name="carExpense.extraKM"
															class="form-control input-sm" id="extraKM"
															value="${carExpense.extraKM}" required
															placeholder="Enter extraKM">

													</div>
												</div>
												
												<div class="col-sm-4">

													<div class="form-group">
														<label for="extraHours" class=" control-label"> Extra Hours </label> 
														<input type="text" autocomplete="off"
															name="carExpense.extraHours"
															class="form-control input-sm" id="extraHours"
															value="${carExpense.extraHours}" required
															placeholder="Enter Extra Hours">

													</div>
												</div>
												
											<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label"> Car Company Name </label> 
														<input type="text" autocomplete="off"
															name="carExpense.carCompanyName"
															class="form-control input-sm" id="carcompanyName"
															value="${carExpense.carCompanyName}" 
															placeholder="Car Company name" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> 
														<input type="text" autocomplete="off"
															name="carExpense.location"
															class="form-control input-sm" id="carlocation"
															value="${carExpense.location}" 
															placeholder="Location " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">PickUp </label>
														 <input type="text" autocomplete="off"
															name="carExpense.pickUp"
															class="form-control input-sm" id="carpickup"
															value="${carExpense.pickUp}" 
															placeholder="PickUp Location "  required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">DropOff </label> 
														<input type="text" autocomplete="off"
															name="carExpense.dropOff"
															class="form-control input-sm" id="cardropoff"
															value="${carExpense.dropOff}" 
															placeholder="DropOff Location "  required>

													</div>
												</div>
												
											<div class="col-sm-4">
													<div class="form-group">
								<label for="carExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="carExpense.expenseCurrency" id="carexpenseCurrency" required>
									<option value="${carExpense.expenseCurrency}" selected="selected">${carExpense.expenseCurrency}</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
											
											<div class="col-sm-13">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> 
														<input type="text" autocomplete="off"
															name="carExpense.department"
															class="form-control input-sm" id="cardepartmenttype"
															value="${carExpense.department}" 
															placeholder="Department" required>

													</div>
												</div>
												<div class="col-sm-13">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Travel Date </label> <input type="text" autocomplete="off"
														name="carExpense.travelDateTemp" id="cartravelDate"
														class="form-control input-sm" placeholder="Travel Date "
														value="${carExpense.travelDate}" required>

												</div> 
											</div>
												
											
													</div>
													
													
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="carExpense.totalAmount" id="carexpenseamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${carExpense.totalAmount}" required>

												</div>
											</div>
							
												
										
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													<!-- <input type="text" autocomplete="off"
														name="carExpense.Reimbursable" id="carreimbursable"
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<select class="form-control input-sm"  name="carExpense.Reimbursable" id="carreimbursable"  required>
															<option value="${carExpense.reimbursable}" selected="selected">${carExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
												<select class="form-control input-sm"
														name="carExpense.Billable" value="${carExpense.billable}" required>
														
														<option  selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>
												</div>
											</div>
														
												<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="1" cols="1" class="form-control input-sm"  name="carExpense.expenseReason" placeholder="comments" required><c:out value="${carExpense.expenseReason}"/></textarea>

													</div>
													</div>
												<div class="col-sm-6">
											 <div class="form-group">
						<label for="uploadimagecar" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-7">

							<div class="row">
								<div class="col-sm-9 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="carExpense.receiptFilename" value="${carExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="carExpense.receiptFilename" > 
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
														<input type="hidden" name="carExpense.id" value="${id}">
														<button type="button"    id="updatecaressxpenseForm" 
								class="btn btn-primary btn-lg">Car Expanse Update</button> <!-- <input type="text" autocomplete="off"
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
									$('#cartravelDate').datepicker({  
										dateFormat : 'dd-mm-yy',
									}); 
									</script>
									
<script type="text/javascript">
$(document).ready(function(){
	
     $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
          return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
      }, "This field cannot have symbols.");

      $.validator.addMethod("cusValidationAlphaName",function(value,element){
          return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
      },"This field cannot have numbers and symbols."); 
      $.validator.addMethod("cusValidationforprice",function(value,element){
          return this.optional(element) || /^[0-9.]+$/i.test(value);
      },"This field cannot have Char and symbols.");


$("#carexpensesFormId").validate({
	 rules: {
         
         "email": {
             required: true,
             email: true
         }
     },
     messages: {
         "email": {
             required: "Please, enter an email",
             email: "Email is invalid"
         }
     },
    submitHandler: function (form) { 
        return false; 
    }
});

$('#updatecaressxpenseForm').click(function() {
	   if($("#carexpensesFormId").valid())
	    	document.getElementById("carexpensesFormId").submit();
	 	 
	}); 

});


</script>  
</body>
</html>