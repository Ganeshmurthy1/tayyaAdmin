<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><s:property value="user" /></title>

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"ShowAirportList";
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

function onlyAlphabets(e, t) {
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
}
</script>
</head>
<body>

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Add Airport</h1>
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
					
			<div class="row" id="dash-us-register">



			 <form action="AddAirport" method="post" class="form-horizontal"
					id="AddAirport" name="myForm">
					<s:if test="hasActionErrors()">
						<div class="success-alert" style="display: none">
							<span class="fa fa-thumbs-o-up fa-1x"></span>
							<s:actionerror />
						</div>
					</s:if>
					<s:if test="hasActionMessages()">
						<div class="success-alert" style="display: none">
							<span class="fa fa-thumbs-o-up fa-1x"></span>
							<s:actionmessage />
						</div>
					</s:if>
			
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Airport Code
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" 
								name="airport_code"  value='<s:property value="airport_code"/>'  placeholder="Enter Airport Code" autocomplete="off"
								required  maxlength="3" >
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="airport_name" class="col-sm-2 control-label">Airport Name
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm"
								name="airport_name"  value='<s:property value="airport_name"/>'     placeholder="Enter Airport Name" autocomplete="off"
								required >
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="city" class="col-sm-2 control-label">City
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" 
								name="city"  value='<s:property value="city"/>'  placeholder="Enter City Name" autocomplete="off"
								required >
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Country
							</label>
						<div class="col-sm-8">
						<select class="form-control input-sm required countryTypeVal"  name="country" id="country" required>
								<option selected value="0">Select Country</option>
								 <s:iterator value="countryLi">								
									<option><s:property value="c_name"></s:property></option>
								</s:iterator>

							</select>
							 
						</div>
					</div>
					
				

					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" id="addAirportSubmit" class="btn btn-primary btn-lg">Add</button>
						</div>
					</div>
				</form> 
			</div>
					
					
					
			
			</section>
		
		
		
		</div>
		
		



	
<%@ include file="DashboardFooter.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	
		
	jQuery.validator.addMethod("countryTypeVal",function(value) {
        var country =  $('#country').val();
        return (country !=  '0');
      }, "please select country");
	 $("#AddAirport").validate({
    	 ignore: false,  
    	rules: {
           
            "email": {
                required: true,
                email: true
            },
        },
        
        messages: {
            
            "email": {
                required: "Please, enter an email",
                email: "Email is invalid"
            },
        },
        submitHandler: function (form) { // for demo
            // for demo
            return false; // for demo
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
    });
    
	
	
$('#addAirportSubmit').click(function() {
	 $("#AddAirport").valid();
	 if($("#AddAirport").valid()){     		        
     	 document.getElementById("AddAirport").submit();
     }
})

});
</script>		
		

</body>
</html>