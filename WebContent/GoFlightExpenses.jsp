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
</head>

<body class="container" data-ng-controller="AppCtrl">

								<div class="panel panel-default" style="margin:52px -107px 21px 137px;">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="collapsed" data-toggle="collapse"
												data-parent="#accordion" href="#collapseTwo"> Flight Expenses </a>
										</h4>
									</div>
									
									<!--/.panel-heading -->
									
										<div class="panel-body" >
										<form action="flightexpansesForm" method="post" enctype="multipart/form-data"
					class="form-horizontal" name="flightexpensemyForm" id="flightexpensesFormId">
										
			 					<div class=" withoutOrderId">
			                                     	<div class="col-sm-4" id="flightpnrnumberdiv">
												<div class="form-group">
													<label for="hotel address1" class="control-label">PNR Number</label> <input type="text" autocomplete="off"
														name="flightExpense.pnrNumber" id="flightpnrnumber"
														class="form-control input-sm" placeholder="Flight pnr Number"
														value="" required>

												</div>
											</div>
													<div class="col-sm-4" id="flightnumberdiv">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Flight Number</label> <input type="text" autocomplete="off"
														name="flightExpense.flightNumber" id="flightnumber"
														class="form-control input-sm" placeholder="Flight Number"
														value=""  required>

												</div>
											</div>
											<div class="col-sm-4" id="flightcarrierdiv">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Flight Carrier </label> <input type="text" autocomplete="off"
														name="flightExpense.flightCarrier" id="flightcarrier"
														class="form-control input-sm" placeholder="Flight Carrier"
														value=""  required>

												</div>
											</div>
											
											<div class="col-sm-4" id="flightdepartureDatediv">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Departure Date </label> 
													<input type="text" autocomplete="off"
														name="flightExpense.departuralDate" id="flightdepartureDate"
														class="form-control input-sm" placeholder="Flight Departure Date" readonly
														 required>

												</div>
											</div>
											<div class="col-sm-4" id="flightarrivDatediv">

												<div class="form-group">
													<label for="hotel city" class="  control-label">Arrival Date </label> <input type="text" autocomplete="off"
														name="flightExpense.arrivalDate" id="flightarrivDate"
														class="form-control input-sm" placeholder="Arrival  Date"
														value="" readonly required>

												</div>
											</div>
											
											</div>
											<div class="flightwithorderIdcommon"> 
											<div class="col-sm-4" >

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value="" 
															placeholder="Department type"  required>

													</div>
												</div>
												<div class="col-sm-4">
             <div class="form-group">
        <label for="flightExpense.expenseCurrency">Currency </label> <select class="form-control input-sm" name="flightExpense.expenseCurrency" required>
         <option value=""  selected="selected" >select currency</option>
        <s:iterator value="CountryList">
         <option value="<s:property value="cur_code" />"><s:property value="cur_code" /></option>
        </s:iterator>
         </select>
       </div>
           
             </div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="flightExpense.totalAmount" id="flighttotalamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required >

												</div>
											</div>
											
													<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="flightExpense.expenseReason" id="flightexpansereason" placeholder="comments" required></textarea>

													</div>
													</div>
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Reimbursable</label> 
														<select class="form-control input-sm"  name="flightExpense.Reimbursable" id="flightreimbursable"  required>
															
															<option value="" selected="selected">select Reimbursable</option>
																<option value="yes">Yes</option>
															
																<option value="no">No</option>
																</select>
														

												</div>
											</div>
														<div class="col-sm-4">
												<div class="form-group">
													<label for="hotel address1" class="control-label">Is Billable</label> 
													
																
													<select class="form-control input-sm"
														name="flightExpense.Billable" required>
														<option selected="selected" value="true">YES</option>
														<option value="false">NO</option>
													</select>
												

												</div>
											</div>
											
											
											
												<!-- 			<div class="col-sm-4">
												<div class="form-group">
													<label for="flightExpense.receiptFilename" class="control-label">Upload Receipt File</label> 
								<div class="row">
								    <div class="col-sm-6 file-upload">

									<figure>
										<img data-ng-src="{{imageSrc}}" >
									</figure>
									<input type="file" id="flightexpensivefile" accept="file_extension|audio/*|video/*|image/*|media_type"  data-ng-file-select="onFileSelect($files)" name="flightExpense.receiptFilename">
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
						<label for="uploadimageflight" class="col-sm-5 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img ng-src="{{imageSrc}}">
									</figure>
									<input type="file" id="flightExpense.receiptFilename" accept="file_extension|audio/*|video/*|image/*|media_type" ng-file-select="onFileSelect($files)" value="${flightExpense.receiptFilename}" name="flightExpense.receiptFilename" required> 
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
														<button type="button" id="flightexpensesubmitnew" 
								class="btn btn-primary btn-lg">Flight Expanse Save</button>  
														<%-- <button type="submit"  name="expense.trip_id" value="${trip_id}" id="flightexpensesubmitnew" 
								class="btn btn-primary btn-lg">Flight Expanse Save</button>   --%>

													</div>
												</div>	
											</div>
											</form>
										</div>
										<!--/.panel-body -->
									</div>
									<!--/.panel-collapse -->
									
						<%@ include file="DashboardFooter.jsp"%>	
									
									<script type="text/javascript">
$(document).ready(function() 
		 { $("#flightdepartureDate").keyup(function () {
		  $(this).next("#flightarrivDate").focus();
		});
			  }); 
		 $('#flightdepartureDate').datepicker({
				 
				 dateFormat: 'dd-mm-yy', 
				 onSelect: function( selectedDate ) {
				    	var date2 = $("#flightdepartureDate").datepicker('getDate', '+1d'); 
				  	  date2.setDate(date2.getDate()+1); 
				  	  
				  	$( "#flightarrivDate" ).datepicker( "option", "minDate", date2 ); 
				  	 $(this).valid();
				    },
				  onClose: function() {
				      $("#flightarrivDate").focus();
				  }
				  
			  });
		    
		 $('#flightarrivDate').datepicker({
			 dateFormat: 'dd-mm-yy',
	       	 minDate:0,
	         onSelect: function() { 
	        	 $(this).valid();
	         }
		 
		 });
		
			$(document).ready(function(){
				  
				   $('#flightexpensesubmitnew').click(function(){
					   $("#flightexpensesFormId").valid();
					   if($("#flightexpensesFormId").valid()){
						   document.getElementById("flightexpensesFormId").submit();
					   }
				   });
				   $("#flightexpensesFormId").validate({
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
	<script type="text/javascript" src="js/app.js"></script>
</body>
</html>