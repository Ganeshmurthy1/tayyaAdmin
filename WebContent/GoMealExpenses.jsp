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
	<script src="js/angular.js" type="text/javascript"></script>
	<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> 
	
	<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
	<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script>
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

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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
												data-parent="#accordion" href="#collapseSeven"> Meal Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
										<div class="panel-body">
											
											<form action="createmealexpenseForm" method="post"
					class="form-horizontal" name="mealexpensesFormId" id="mealexpensesFormId" enctype="multipart/form-data">
											<!-- -->
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Vendor </label>
														 <input type="text" autocomplete="off"
															name="mealExpense.vendor" id="mealVendor"
															class="form-control input-sm"
															value="" 
															placeholder="Vendor" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> 
														<input type="text" autocomplete="off"
															name="mealExpense.location" id="mealLocation"
															class="form-control input-sm"
															value="" 
															placeholder="Location " required> 

													</div>
												</div>
												<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="control-label">Expense Date </label> 
													<input type="text" autocomplete="off"
														name=mealExpense.expenseDateTemp id="mealexpensiveDate"
														class="form-control input-sm" placeholder="Expense Date"
														value="" required>

												</div>
											</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="mealExpense.department" id="mealDepartment"
															class="form-control input-sm"
															value="" 
															placeholder="Department"  required>

													</div>
												</div> 
																							<div class="col-sm-4">
             <div class="form-group">
        <label for="mealExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="mealExpense.expenseCurrency" required>
         <option value="" selected="selected" required>select currency</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
											
											<%-- <div class="col-sm-4">
													<div class="form-group">
								<label for="mealExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="mealExpense.expenseCurrency" id="mealexpenseCurrency" >
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div> --%>
													
											<!--  -->
											
											
											
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="mealExpense.totalAmount" id="mealexpenseAmount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required>

												</div>
											</div>
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="mealExpense.expenseReason" id="mealeExpensereason" placeholder="comments" required></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label" >Reimbursable</label>
													<!--  <input type="text" autocomplete="off"
														name="mealExpense.Reimbursable" id="mealReimbursable" 
														class="form-control input-sm" placeholder="Reimbursable"
														value=""> -->
														<select class="form-control input-sm"  name="mealExpense.Reimbursable" id="mealReimbursable"  required>
															
															<option value="" selected="selected">select Reimbursable</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													<%-- <select class="form-control input-sm"  name="mealExpense.isBillable" id="mealsbillable" >
															<option value="" selected="selected">select Billable Type</option>
															
																<option value="true">Yes</option>
															
																<option value="false">No</option>
																</select> --%>
																<select class="form-control input-sm"
														name="mealExpense.Billable" required>
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>

												</div>
											</div>
												<!-- <div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure>
									<input type="file" id="mealuploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="mealExpense.receiptFilename">
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
											</div>
											 -->
												
											<div class="col-sm-4">
											 <div class="form-group">
						<label for="uploadimagemeal" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">
									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="mealExpense.receiptFilename" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="mealExpense.receiptFilename" > 
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
														<input type="hidden" name="expid" value="${expid}">
														<input type="hidden" name="expense.trip_id" value="${trip_id}">
														<button type="submit"   id="mealexpensesubmitnew"
														
								class="btn btn-primary btn-lg">Meal Expanse Save</button> <!-- <input type="text" autocomplete="off"
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
									$('#mealexpensiveDate').datepicker({  
										dateFormat : 'dd-mm-yy',
										onSelect : function(selectedDate) {
											 $(this).valid();
										}
									}); 
									</script>
									<script type="text/javascript" src="js/app.js"></script>
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


$("#mealexpensesFormId").validate({
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

$('#mealexpensesubmitnew').click(function() {
	   if($("#mealexpensesFormId").valid())
	    	document.getElementById("mealexpensesFormId").submit();
	}); 
});
</script>  
</body>
</html>