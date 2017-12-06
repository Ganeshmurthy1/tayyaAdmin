<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>

<%-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/angular.js" type="text/javascript"></script> --%>
<link rel="stylesheet" href="css/alert.css">
     <link rel="stylesheet" href="css/fastselect.min.css">  
<%--  <script src="js/direct_company_list.js" type="text/javascript"></script>   --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 

<title><s:property value="user" /></title>

 
<script type="text/javascript">
	function numbersonly(e) {
		var unicode = e.charCode ? e.charCode : e.keyCode
		if (unicode != 8) { //if the key isn't the backspace key (which we should allow)
			if (unicode<48||unicode>57) //if not a number
				return false //disable key press
		}
	}

	function onlyAlphabets(e, t) {
		try {
			if (window.event) {
				var charCode = window.event.keyCode;
			} else if (e) {
				var charCode = e.which;
			} else {
				return true;
			}
			if ((charCode > 64 && charCode < 91)
					|| (charCode > 96 && charCode < 123))
				return true;
			else
				return false;
		} catch (err) {
			alert(err.Description);
		}
	}
</script>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Hotel Room Edit</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>


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

			<div class="row" id="dash-us-hotel">


				<div class="col-sm-12">
				
				<form action="hotelRoomUpdate" method="post" class="form-horizontal"
									name="myForm" id="myform2">
									<div class="cre-hot-details">
								<input type="hidden" name="id"  value="<s:property value="hotelRoomDetails.id"/>">
										<div class="col-sm-6">
											<div class="form-group">
												<label for="basePrice" class="col-sm-4 control-label">
													BasePrice</label>
												<div class="col-sm-6">
													<input type="number" class="form-control input-sm"
														name="basePrice" id="basePrice" value="<s:property value="hotelRoomDetails.basePrice"/>"       placeholder="Enter basePrice"
														autocomplete="off" required>
												</div>
											</div>

											<div class="form-group">
												<label for="taxPrice" class="col-sm-4 control-label">
													TaxPrice</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="taxPrice" value="<s:property value="hotelRoomDetails.taxPrice"/>"              id="taxPrice" placeholder="Enter taxPrice"
														autocomplete="off" required>
												</div>
											</div>

											<div class="form-group">
												<label for="availability" class="col-sm-4 control-label">
													Availability</label>
												<div class="col-sm-6">
													<input type="number" class="form-control input-sm"
														name="availability"  value="<s:property value="hotelRoomDetails.availability"/>"             id="availability" placeholder="Enter availability"
														autocomplete="off" required>
												</div>
											</div>
											
											<div class="form-group">
												<label for="startDate" class="col-sm-4 control-label">Start Date</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="startDate" id="startdate"   value="<s:property value="hotelRoomDetails.startDate"/>"                  placeholder="Enter Startdate"
														autocomplete="off" required>
												</div>
											</div>
											
											<div class="form-group">
												<label for="endDate" class="col-sm-4 control-label">End Date</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="endDate" id="enddate"  value="<s:property value="hotelRoomDetails.endDate"/>"            placeholder="Enter enddate"
														autocomplete="off" required>
												</div>
											</div>
										</div>

										<div class="col-sm-6">
										<div class="form-group">
												<label for="extraBedPrice" class="col-sm-4 control-label">ExtraBedPrice</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="extraBedPrice"    value="<s:property value="hotelRoomDetails.extraBedPrice"/>"              id="extraBedPrice" placeholder="Enter ExtraBedPrice"
														autocomplete="off" required>
												</div>
											</div>
											
											<div class="form-group">
												<label for="cancelBeforeDay" class="col-sm-4 control-label">CancelBeforeDay</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="cancelBeforeDay"   value="<s:property value="hotelRoomDetails.cancelBeforeDay"/>"              id="cancelBeforeDay" placeholder="Enter cancelBeforeDay"
														autocomplete="off" required>
												</div>
											</div>
											
												<div class="form-group">
												<label for="cancelAmount" class="col-sm-4 control-label">CancelAmount</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="cancelAmount"  value="<s:property value="hotelRoomDetails.cancelAmount"/>"                id="cancelAmount" placeholder="Enter CancelAmount"
														autocomplete="off" required>
												</div>
											</div>
											
											<div class="form-group">
												<label for="amountType" class="col-sm-4 control-label">AmountType</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="amountType"   value="<s:property value="hotelRoomDetails.amountType"/>"                 id="amountType" placeholder="Enter AmountType"
														autocomplete="off" required>
												</div>
											</div>
											
											<div class="form-group">
												<label for="condition" class="col-sm-4 control-label">Condition</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="cond"  value="<s:property value="hotelRoomDetails.cond"/>"            id="condition" placeholder="Enter condition"
														autocomplete="off" required>
												</div>
											</div>
										</div>
									</div>
									
									<div class="form-group text-right">
										<div class="input-group pull-right ">
											<!-- <button type="submit" 
												class="btn btn-primary but">
												Search <i class="fa fa-arrow-circle-right "></i>
											</button> -->
											<button id="hotelRoomSubmit" type="button" 
												class="btn btn-primary but">
												Search <i class="fa fa-arrow-circle-right "></i>
											</button>
										</div>
									</div>
									
								</form>
				 
				</div>
			</div>


		</section>



	</div>

	<script>
		function checkcatagory() {
			var selectedcatagory = $('#catagory').find(":selected").text();
			console.log(selectedcatagory);
			if (selectedcatagory == 'Flight')
				$('#descity').show();
			else
				$('#descity').hide();

		}

		$(document).ready(function() {

			$('#startdate').datepicker({
				numberOfMonths : 2,
				firstDay : 1,
				dateFormat : 'yy-mm-dd',
				minDate : '0',

				onSelect : function(dateStr) {
					var d1 = $(this).datepicker("getDate");
					d1.setDate(d1.getDate() + 0); // change to + 1 if necessary
					var d2 = $(this).datepicker("getDate");
					d2.setDate(d2.getDate() + 31); // change to + 29 if necessary
					$("#enddate").datepicker("setDate", null);
					$("#enddate").datepicker("option", "minDate", d1);
					// $("#twodpd2").datepicker("option", "maxDate", d2);
					$(this).valid();

				},
				onClose : function(dateSt) {
					$("#enddate").focus();
				}
			});

			$("#enddate").datepicker({
				numberOfMonths : 2,
				firstDay : 1,
				dateFormat : 'yy-mm-dd',

				onSelect : function(dateStr) {
					$(this).valid();
				}
			});

		});
	</script>
	<script src="js/DealUpload.js"></script>

		<script>
	$(document).ready(function(){
		  
		   $('#hotelRoomSubmit').click(function(){
			   $("#myform2").valid();
			   if($("#myform2").valid()){
				   document.getElementById("myform2").submit();
			   }
		   });
		 		   
		   $("#myform2").validate({
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
 
<%@ include file="DashboardFooter.jsp"%>



</body>
</html>