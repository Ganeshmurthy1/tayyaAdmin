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
												data-parent="#accordion" href="#collapseThree">  Hotel Expenses </a>
										</h4>
									</div>
									<!--/.panel-heading -->
										<div class="panel-body">
										<form action="createhotelexpenseForm" method="post"
					class="form-horizontal" name="hotelexpensesFormId" id="hotelexpensesFormId" enctype="multipart/form-data">
												<!-- <div class="col-sm-12">
													<div class="col-sm-4" id="flightnumberdiv">
												<div class="form-group">
												<div><label for="hotel expenses" class="control-label">Have OrderId ?</label></div>
													 <label for="chkYes1">
  																<input type="radio" id="chkYes1" name="chkPinNo1"  value="1"/> Yes
													</label>
													<label for="chkNo1">
 																 <input type="radio" id="chkNo1" name="chkPinNo1"  value="2"/> No
													</label>


												</div>
											</div>
											</div>
 -->
											<!-- 
													<div class="col-sm-12"  id="hotelOrderIdforradio">
											
												<div class="form-group">
													<label for="hotelName" class=" control-label">Order Id </label> <input type="text" autocomplete="off"
														name="hotelExpense.OrderId" id="hotelorderId"
														class="form-control input-sm" placeholder="Order Id"
														value="" >

												</div>
										
											</div> -->
						 <div class=" hotelwithoutOrderId">					
											
							              <div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Hotel Confirmation Number</label> <input type="text" autocomplete="off"
															name="hotelExpense.HotelConfirmNumber"
															class="form-control input-sm" id="hotelCobfirmNumber"
															value="" 
															placeholder="Hotel Confirmation Number" required>

													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label for="checkInDate" class=" control-label">Hotel Name</label> <input type="text" autocomplete="off"
															name="hotelExpense.hotelName"
															class="form-control input-sm" id="hotelname"
															value="" 
															placeholder="Hotel Name" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Location </label> <input type="text" autocomplete="off"
															name="hotelExpense.location"
															class="form-control input-sm" id="hotellocation"
															value="${hotelQuotation.roomType}"
															placeholder="Location " required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Room
															Type </label> <input type="text" autocomplete="off"
															name="hotelExpense.roomType"
															class="form-control input-sm" id="hotelRoomtype"
															value="" 
															placeholder="Room Type" required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">CheckIn Date</label> <input type="text" autocomplete="off"
															name="hotelExpense.checkInDateTemp"
															class="form-control input-sm" id="hotelcheckindate"
															value=""
															placeholder="CheckIn Date" readonly required>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">CheckOut  Date</label> <input type="text" autocomplete="off"
															name="hotelExpense.checkOutDateTemp"
															class="form-control input-sm" id="hotelcheckoutdate"
															value="" 
															placeholder="CheckOut  Date" readonly required>

													</div>
												</div>
												</div>
												<div class="hotelcommonOrderId">
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Department </label> <input type="text" autocomplete="off"
															name="hotelExpense.department"
															class="form-control input-sm" id="hoteldepartmentType"
															value="" 
															placeholder="Department" required>

													</div>
												</div>
												
											
												<div class="col-sm-4">
													<div class="form-group">
								<label for="hotelExpense.expenseCurrency">Currency </label>	<select class="form-control input-sm" name="hotelExpense.expenseCurrency" id="hotelexpenseCurrency" required>
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
									<option value="<s:property value="cur_code"/>"><s:property value="cur_code" /></option>
								</s:iterator>
								 </select>
							</div>
											
													</div>
												
											<div class="col-sm-4">
												<div class="form-group">
													<label for="hotelCategory" class=" control-label">Total Amount </label> <input type="number" autocomplete="off"
														name="hotelExpense.totalAmount" id="hoteltotalamount"
														class="form-control input-sm" placeholder="Total Amount"
														value="" required >

												</div>
											</div>
							
											
												<div class="col-sm-4">

													<div class="form-group">
														<label for="expansereason" class=" control-label">Expense Reason </label> 
														<textarea rows="2" cols="2" class="form-control input-sm" name="hotelExpense.expenseReason" id="hotelexpensereason" placeholder="comments" required></textarea>

													</div>
												</div>
												<div class="col-sm-4">

													<div class="form-group">
														<label for="checkInDate" class=" control-label">Reimbursable</label>
														 <!-- <input type="text" autocomplete="off"
															name="hotelExpense.Reimbursable"
															class="form-control input-sm" id="hotelreimbursable"
															value=""
															placeholder="Reimbursable"> -->
															<select class="form-control input-sm"  name="hotelExpense.Reimbursable" id="hotelreimbursable" required >
															
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
														name="hotelExpense.Billable" required>
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
									<input type="file" id="busuploadimage" ngf-pattern="image/*" ng-file-select="onFileSelect($files)" name="hotelExpense.receiptFilename">
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
							<label for="uploadimagehotel" class="col-sm-8 control-label">Upload
								Receipt File </label>
							<div class="col-sm-8">

								<div class="row">
									<div class="col-sm-6 file-upload">

										<figure>
											<img data-ng-src="{{imageSrc}}">
										</figure>
										<input type="file" id="hotelExpense.receiptFilename"
											value="${hotelExpense.receiptFilename}"
											accept="file_extension|audio/*|video/*|image/*|media_type"
											data-ng-file-select="onFileSelect($files)"
											name="hotelExpense.receiptFilename" required>
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
											
											
											
											
											
							<!-- 	<div class="col-sm-4">			
					 <div class="form-group">
						<label for="uploadimagehotel" class="col-sm-8 control-label">Upload Receipt File
							 </label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<img data-ng-src="{{imageSrc}}" >
									</figure>
									<input type="file" id="hotelExpense.receiptFilename" accept="file_extension|audio/*|video/*|image/*|media_type" data-ng-file-select="onFileSelect($files)" name="hotelExpense.receiptFilename" > 
								</div>
							
								ng-file-select="onFileSelect($files)"
								 <div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										 
									</div>
								</div>  
							</div>


						</div>

					</div> 
					 </div> -->
					 	
								<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
														<input type="hidden" name="expid" value="${expid}">
														<input type="hidden" name="expense.trip_id" value="${trip_id}" >
														<button type="button" id="hotelexpensesubmitnew" 
								class="btn btn-primary btn-lg">Hotel Expanse Save</button>
								<%-- <button type="submit"   name="expense.trip_id" value="${trip_id}"  id="hotelexpensesubmitnew" 
								class="btn btn-primary btn-lg">Hotel Expanse Save</button> --%>
								 <!-- <input type="text" autocomplete="off"
															name="flightExpense.department"
															class="form-control input-sm" id="flightdepartment"
															value=""
															placeholder="Department type" > -->

													</div>
												</div>
					 
					 
											</div>
											
												</form>
											</div>
										</div>
										
										<%@ include file="DashboardFooter.jsp"%>	
<script type="text/javascript">
$(document).ready(function() 
		 { $("#hotelcheckindate").keyup(function () {
		  $(this).next("#hotelcheckoutdate").focus();
		});
			  }); 
		 $('#hotelcheckindate').datepicker({
				 
				 dateFormat: 'dd-mm-yy', 
				 onSelect: function( selectedDate ) {
				    	var date2 = $("#hotelcheckindate").datepicker('getDate', '+1d'); 
				  	  date2.setDate(date2.getDate()+1); 
				  	  
				  	$( "#hotelcheckoutdate" ).datepicker( "option", "minDate", date2 ); 
				  	 $(this).valid();
				    },
				  onClose: function() {
				      $("#hotelcheckoutdate").focus();
				  }
				  
			  });
		    
		 $('#hotelcheckoutdate').datepicker({
			 dateFormat: 'dd-mm-yy',
	       	 minDate:0,
	         onSelect: function() { 
	        	 $(this).valid();
	         }
		 
		 
		 });
			$(document).ready(function(){
				  
				   $('#hotelexpensesubmitnew').click(function(){
					   $("#hotelexpensesFormId").valid();
					   if($("#hotelexpensesFormId").valid()){
						   document.getElementById("hotelexpensesFormId").submit();
					   }
				   });
				   $("#hotelexpensesFormId").validate({
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
<script type="text/javascript" src="js/app.js"></script>
</html>