<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<%-- 
<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
	
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 --%>
<link rel="stylesheet" href="css/alert.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><s:property value="user" /></title>

	<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<script>
  
  function maxLengthCheckForNumbers(object)
  {
	  
    if (object.value.length > object.maxLength)
      object.value = object.value.slice(0, object.maxLength)
      
      
   }
  function checkminvalue()
  {
	  
	  if (minValue.length!=4)
	  {
		  alert('min 4 digit is required');
		 
		  
	  }
	  
	 
  }
</script>
  <script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"PaymentCardList";
	$('#success').click(function() {
	  window.location.assign(finalUrl);  
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
 });
 </script>  
 <script type="text/javascript">
 function numbersonly(e){
    var unicode=e.charCode? e.charCode : e.keyCode
    if (unicode!=8){ //if the key isn't the backspace key (which we should allow)
        if (unicode<48||unicode>57) //if not a number
            return false //disable key press
    }
} 

/* function onlyAlphabets(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
            return true;
        else
            return false;
    }
    catch (err) {
        alert(err.Description);
    }
} */
</script>
</head>
<body>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Add Card Details</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
						<h4  >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
						<h4 >
						<a href="PaymentCardList;" class="btn btn-primary pull-left" style="
						border: 2px solid #a1a1a1;
						border-radius: 15px" role="button">
						<i class="fa fa-list">  View List</i></a>
						</h4>
						
					</div>
			
			
			<s:if test="hasActionErrors()">
				<div class="succfully-updated clearfix" id="error-alert">
					<div class="col-sm-2">
						<i class="fa fa-check fa-3x"></i>
					</div>
					<div class="col-sm-10">
						<p>
							<s:actionerror/>
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
					
			<div class="row" id="dash-us-register">
			 <form action="AddCardDetails" method="post" class="form-horizontal"
					name="myForm" id="addPamentInfo">
					<div class="form-group">
						<label for="userName" class="col-sm-2 control-label">Name
							</label>
						<div class="col-sm-8">
							<input maxlength="50" type="text" class="form-control input-sm" 
								name="userName"  value='<s:property value="userName"/>'  placeholder="Enter User Name" autocomplete="off"
								required   >
						</div>
					</div>
					
					<div class="form-group">
						<label for="bankName" class="col-sm-2 control-label">Bank Name
							</label>
						<div class="col-sm-8">
							<input maxlength="30" type="text" class="form-control input-sm"
								name="bankName"  value='<s:property value="bankName"/>'     placeholder="Enter Bank Name" autocomplete="off"
								required >
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="paymentType" class="col-sm-2 control-label">Payment Type
							</label>
						<div class="col-sm-8">
						<select class="form-control input-sm"  name="paymentType" id="paymentType" required>
						
						<s:if test="cardDetailsConfig.paymentType==true">
							<option value="false">Client</option>
							<option value="true"  selected="selected">Supplier</option>
						</s:if>
						 
					 	<s:else>
					 	<option value="">Select Payment Type</option>
  							<option value="false">Client</option>
							<option value="true">Supplier</option>
					 	</s:else>
						</select>
						 
						</div>
					</div>
					
					<div class="form-group">
						<label for="cardType" class="col-sm-2 control-label">Card type
							</label>
						<div class="col-sm-8">
						<select class="form-control input-sm"  name="cardType" id="cardType" required>
						
						<s:if test="cardDetailsConfig.cardType=='debit'">
						<option value="debit" selected="selected">Debit Card</option>
							<option value="credit">Credit Card</option>
						</s:if>
						<s:elseif test="cardDetailsConfig.cardType=='credit'">
						<option value="debit" >Debit Card</option>
							<option value="credit" selected="selected">Credit Card</option>
						</s:elseif>
					 <s:else>
					 <option value="">Select Card Type</option>
  							<option value="debit">Debit Card</option>
							<option value="credit">Credit Card</option>
					 </s:else>
						</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="cardNumber" class="col-sm-2 control-label">Card Number<font size="1" color="red"></font>
							</label>
						<div class="col-sm-8">
							<input id="cardNumber" min="0"   maxlength = "4" type="number" class="form-control input-sm"  oninput="maxLengthCheckForNumbers(this)"
								name="cardNumber"    placeholder="Enter last 4 digits of card" autocomplete="off"
								required >
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="expiryDate" class="col-sm-2 control-label">Valid Upto
							</label>
						<div class="col-sm-4">
							 <input type="number" class="form-control input-sm" oninput="maxLengthCheckForNumbers(this)"
								name="cardExpireMonth"  max="12"     value='<s:property value="cardExpireMonth"/>' autocomplete="off" placeholder="month"
								required maxlength="2"> 
								
						 <%--  <select  class="form-control input-sm" name="cardExpireMonth"   required >
									<option value="">Select Month</option>
									  <c:forEach var="monthlist" items="${months}">
												 <c:choose>
													 <c:when test="${monthlist!= null}">
															 <option value="${monthlist}">${monthlist}</option>
													 </c:when>
													 <c:otherwise>
															 <option value="${monthlist}">${monthlist}</option>
													 </c:otherwise>
												</c:choose>
									 </c:forEach> 
							    </select>   --%>
						</div>
						<div class="col-sm-4">
							<input type="number" class="form-control input-sm" oninput="maxLengthCheckForNumbers(this)"
								name="cardExpireYear"  value='<s:property value="cardExpireYear"/>' autocomplete="off" placeholder="year"
								required maxlength="2">
								 
						</div>
					</div>
					<div class="form-group">
						<label for="contactNumber" class="col-sm-2 control-label">Contact Number
							</label>
						<div class="col-sm-8">
							<input id="contactNumber" type="number" class="form-control input-sm"
								name="contactNumber"  value='<s:property value="contactNumber"/>' maxlength="15" oninput="maxLengthCheckForNumbers(this)"  
								placeholder="Enter contact Number" autocomplete="off" required >
								 
						</div>
					</div>
					<div class="form-group">
						<label for="mailId" class="col-sm-2 control-label">Mail id
							</label>
						<div class="col-sm-8">
							<input type="email" class="form-control input-sm"
								name="mailId"  value='<s:property value="mailId"/>'     placeholder="Enter Mail Id" autocomplete="off"
								required >
						</div>
					</div>
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button"   class="btn btn-primary btn-lg" id="buttonSubmit">Add Details</button>
						</div>
					</div>
				</form> 
			</div>
					
					
					
			
			</section>
		
		
		
		</div>
		
	
<%@ include file="DashboardFooter.jsp"%>
		
	<script> 
	$(document).ready(function(){  
	    $("#addPamentInfo").validate({
	    	 ignore: false,  
	        submitHandler: function (form) {   
	            return false;  
	        },
	 
	        highlight: function(element, errorClass, validClass) {  
	            $(element).addClass(errorClass).removeClass(validClass);
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	          },
	          success: function(element) { 
	         	element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            $(element).remove();
	          }
	    });
	    
	    $('#buttonSubmit').click(function() { 
	    	$("#addPamentInfo").valid();
	  	if($("#addPamentInfo").valid())  
	    	 document.getElementById("addPamentInfo").submit();
	  	else
	  		console.log("Please Fill Required Feilds")
	    });    
	    
	});
	
	</script>

</body>
</html>