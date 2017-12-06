<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edit labour</title>

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
<script src="js/angular.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body class="container" data-ng-controller="AppCtrl">
               	<div class="panel panel-default ExpensesPanel">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseEight"> Labor Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
										<div class="panel-body">
										<form action="updatelaborexpenseForm" method="post"
					class="form-horizontal" name="laborexpensesFormId" id="laborexpensesFormId" enctype="multipart/form-data">
										<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">WorkName </label> <input type="text" autocomplete="off"
															name="laborExpense.workName" id="labourWorkname"
															class="form-control input-sm"
															value="${laborExpense.workName}" required
															placeholder="Work Name">

													</div>
												</div>
										<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="laborExpense.department" id="labourDepartment"
															class="form-control input-sm"
															value="${laborExpense.department}"
															placeholder="Department" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="laborExpense.location" id="labourLocation"
															class="form-control input-sm"
															value="${laborExpense.location}"
															placeholder="Location " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Hours </label> <input type="text" autocomplete="off"
															name="laborExpense.hours" id="labourHours" value="${laborExpense.hours}"
															class="form-control input-sm" id="labourworkinghours" placeholder="Woked Hours" required>
													</div>
												</div>
												
												
																	<div class="col-sm-4">
             <div class="form-group">
        <label for="laborExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="laborExpense.expenseCurrency" required>
         <option value="${laborExpense.expenseCurrency}" selected="selected">${laborExpense.expenseCurrency}</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
             
             								<div class="col-sm-4">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Expense Date </label> <input type="text" autocomplete="off"
														name="laborExpense.expenseDateTemp" id="labourexpenseDate" 
														class="form-control input-sm" placeholder="Expense Date"
														value="${laborExpense.expenseDate}" required>

												</div>
											</div>
										
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="laborExpense.totalAmount" id="laborexpenseAmount"
														class="form-control input-sm" placeholder="Total Amount"
														value="${laborExpense.totalAmount}" required>

												</div>
											</div>
											
											<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="1" cols="1" class="form-control input-sm"  name="laborExpense.expenseReason" placeholder="comments" required><c:out value="${laborExpense.expenseReason}"/></textarea>
														</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
													
														<select class="form-control input-sm" name="laborExpense.Reimbursable" id="labourReimbursable"  required>
															
															<option value="${laborExpense.reimbursable}" selected="selected">${laborExpense.reimbursable}</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													
																<select class="form-control input-sm"
														name="laborExpense.Billable" value="${laborExpense.billable}" required>
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
									<input type="file" id="labouruploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="laborExpense.receiptFilename">
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
						<label for="uploadimagelabour" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-4 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure> 
									<input type="file" id="laborExpense.receiptFilename"  value="${laborExpense.receiptFilename}" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" name="laborExpense.receiptFilename" > 
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
														
														<button type="button"  name="laborExpense.id" value="${id}"  id="updatelaborexpenseForm" 
								class="btn btn-primary btn-lg">Labor Expanse Update</button> <!-- <input type="text" autocomplete="off"
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
									$('#labourexpenseDate').datepicker({  
										dateFormat : 'dd-mm-yy',
									}); 
									</script>
									
											<script> 
	$(document).ready(function(){  
	 
		 
	    $("#laborexpensesFormId").validate({
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
	            },
	        }, 
	        highlight: function(element, errorClass, validClass) { 
	            $(element).addClass(errorClass).removeClass(validClass);
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	          },
	          success: function(element) { 
	         element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            $(element).remove();
	          },
	        submitHandler: function (form) {   
	            return false;  
	        } 
	    });
	    
	    $('#updatelaborexpenseForm').click(function() { 
	  	if($("#laborexpensesFormId").valid())  
	    	 document.getElementById("laborexpensesFormId").submit(); 
	    });    
	    
	});

	</script>			
									
</body>
</html>