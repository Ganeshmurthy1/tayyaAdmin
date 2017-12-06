<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript">
$(function() {
	var totUrl = $(location).attr('href');
	var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
	var finalUrl = newUrl+"hotelList";
	
	$('#success').click(function() {
		window.location.assign(finalUrl);
	$('#success-alert').hide();

});
$('#cancel').click(function() {
	$('#error-alert').hide();

});

});
 
</script>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<style type="text/css">
.error {
    color:red;
}
.valid {
    color:green;
}
</style>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Hotel Edit</h1>
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
				
								<form action="hotelupdate" method="post" class="form-horizontal"
									name="myForm" id="myform1">
									<div class="cre-hot-details">

										<div class="col-sm-6">
										  <div class="form-group">
										  
										  <input type="hidden"  name="id" value="${hotelDetails.id}">
										  
												<label for="name" class="col-sm-4 control-label">Enter
													Name </label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm" required
														name="name" id="name"  value="<s:property value="hotelDetails.name"/>"   placeholder="Enter Name"
														autocomplete="off">
												</div>
											</div>

											<div class="form-group">
												<label for="hotelCode" class="col-sm-4 control-label">Enter
													HotelCode</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="hotelCode" id="hotelCode" required 
														placeholder="Enter HotelCode"   value="<s:property value="hotelDetails.hotelCode"/>"   autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label for="hotelChain" class="col-sm-4 control-label">Enter
													HotelChain</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="hotelChain" id="hotelChain" required 
														placeholder="Enter HotelChain"  value="<s:property value="hotelDetails.hotelChain"/>"               autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label for="rating" class="col-sm-4 control-label">Enter
													Rating</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"  required 
														name="rating" value="<s:property value="hotelDetails.rating"/>"  id="rating" placeholder="Enter Rating"
														autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label for="city" class="col-sm-4 control-label">Enter
													City</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="city" id="city" placeholder="Enter city" required 
														autocomplete="off"    value="<s:property value="hotelDetails.city"/>"            >
												</div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label for="country" class="col-sm-4 control-label">
													Country</label>
												<div class="col-sm-6">
												
													<select class="form-control input-sm" name="country"  required 
														id="country" required="required">
														<option selected="selected" value="">select
																country</option>
																
																<c:forEach var="c" items="${countyList}">
																<c:choose>
																	<c:when
																		test="${hotelDetails.country != null && hotelDetails.country == c.c_name}">
																		<option value="${c.c_name}" selected="selected">${c.c_name}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${c.c_name}">${c.c_name}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
																
														<%-- <s:iterator value="countyList">
															
															<option value="<s:property value="c_name"/>"><s:property
																	value="c_name" /></option>
														</s:iterator>
 --%>
													</select>

												</div>
											</div>

											<div class="form-group">
												<label for="latitude" class="col-sm-4 control-label">Enter
													Latitude</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"  required 
														name="latitude"   value="<s:property value="hotelDetails.latitude"/>"               id="latitude" placeholder="Enter latitude"
														autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<label for="longitude" class="col-sm-4 control-label">Enter
													Longitude</label>
												<div class="col-sm-6">
													<input type="text" class="form-control input-sm"
														name="longitude" id="longitude" required 
														placeholder="Enter Longitude"  value="<s:property value="hotelDetails.longitude"/>"             autocomplete="off">
												</div>
											</div>

											<div class="form-group">
												<label for="active" class="col-sm-4 control-label">
													Status  </label>
												<div class="col-sm-6">
													<select class="form-control input-sm" name="ActiveStatus"
														id="active" required="required">
														<s:if test="hotelDetails.ActiveStatus==true">
														<option selected="selected" value="1">Active</option>
														<option value="0">In Active</option>
														</s:if>
														<s:else>
														<option  value="1">Active</option>
														<option value="0" selected="selected">In Active</option>
														
														</s:else>
														 
													</select>

												</div>
											</div>

											<div class="form-group">
												<label for="hotelType" class="col-sm-4 control-label">Hotel
													Type</label>
												<div class="col-sm-6">
													<select class="form-control input-sm hotelTypeval required" name="hotelType"
														id="hotelType" required="required">
														
														<option selected="selected" value="void">select
															hotel type</option>
														<option value="small">Small</option>
														<option value="medium">Medium</option>
														<option value="large">Large</option>
														<s:if test="hotelDetails.hotelType!=null">
														<option selected="selected" value="${hotelDetails.hotelType}">${hotelDetails.hotelType}</option>
														</s:if>
													</select>
												</div>
											</div>


										</div>
									</div>

									<div class="form-group text-right">
										<div class="input-group pull-right ">
											<button type="submit"
												class="btn btn-primary but" id="buttonSubmit">
												Update <i class="fa fa-arrow-circle-right"></i>
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

				}
			});

		});
	</script>
<script type="text/javascript">
$(document).ready(function(){
	
	jQuery.validator.addMethod("hotelTypeval",function(value) {
        var valuehotel =  $('#hotelType').val();
        return (valuehotel !=  'void');
      }, "please select product type");
	
     $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
          return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
      }, "This field cannot have symbols.");

      $.validator.addMethod("cusValidationAlphaName",function(value,element){
          return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
      },"This field cannot have numbers and symbols."); 
      $.validator.addMethod("cusValidationforprice",function(value,element){
          return this.optional(element) || /^[0-9.]+$/i.test(value);
      },"This field cannot have Char and symbols.");


$("#myform1").validate({
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

$('#buttonSubmit').click(function() {
	   if($("#myform1").valid())
	    	document.getElementById("myform1").submit();
	}); 
});
</script>  
<%@ include file="DashboardFooter.jsp"%>
</body>
</html>