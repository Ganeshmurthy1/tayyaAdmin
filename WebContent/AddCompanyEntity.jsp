 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Company Entity</title>


 
<link rel="stylesheet" href="css/alert.css">
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<style type="text/css">
#register {
	margin-left: 100px;
}

#register label {
	margin-right: 5px;
}

#register input {
	padding: 5px 7px;
	border: 1px solid #d5d9da;
	box-shadow: 0 0 5px #e8e9eb inset;
	width: 250px;
	font-size: 1em;
	outline: 0;
}

#result {
	margin-left: 5px;
}

.short {
	color: #FF0000;
}

.weak {
	color: #E66C2C;
}

.good {
	color: #2D98F3;
}

.strong {
	color: #006400;
}
 
#sss .input-group-addon, .input-group-btn{
    width: 25%;

}
 
 #sss .input-group-addon, .input-group-btn select{
 padding: 0px;
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
#State option{
color: #555;
}
.ui-autocomplete {
	max-height: 200px;
	width: auto;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: auto;
	font-family: "Trebuchet MS", "Helvetica", "Arial", "Verdana",
		"sans-serif";
	font-size: 1em;
	/* add padding to account for vertical scrollbar */
}
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}
</style>

<script type="text/javascript">
/* function companyEnt(){
	var totUrl=$(location).attr('href'); 
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);  
	var email = $('#compemail').val();
	var companyid = $('#companyid').val();  
	var finalUrl = newUrl+"superUserCompanyEntityUpdate"+"?companyid="+companyid+"&Email="+email; 
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide(); 
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
 } */
 
 $(function() {
		var totUrl=$(location).attr('href');
		var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
		 // var finalUrl = newUrl+"superUserCompanyEntityUpdate"+"?companyid="+${companyid}+"&Email="+${Email};
		 var email = $('#compemail').val();
	var companyid = $('#companyid').val();  
	var finalUrl = newUrl+"superUserCompanyEntityUpdate"+"?companyid="+companyid+"&Email="+email; 
	 
		$('#success').click(function() { 
		 window.location.assign(finalUrl); 
			$('#success-alert').hide(); 
		});
		  $('#cancel').click(function() {
			   $('#error-alert').hide();
				
			});  
	 }); 

 function isNumberKey(evt,obj){
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57))   
	    
	        return false;
	 }
	function InvalidMsg(textbox) {
	    
	    if(textbox.validity.patternMismatch){
	       textbox.setCustomValidity('Mobile number not vaild.');
	   }    
	   else {
	       textbox.setCustomValidity('');
	   }
	   return true;
	}
	function InvalidEmailMsg(textbox) {
	    
	    if(textbox.validity.patternMismatch){
	       textbox.setCustomValidity('Email address not vaild.');
	   }    
	   else {
	       textbox.setCustomValidity('');
	   }
	   return true;
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
        }}

 </script>
<%-- <script type="text/javascript">
 $(document).ready(
   function() {
    var currency_list = [];
     $('#country').change(function(){
     var country=$('#country').val();
      document.getElementById('Billingcountry').value =country;

      });
           });
  
 </script> --%>
 

</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header"> 
			<div class="row">
					<div class="col-sm-3 clearfix pull-left">
						<h3>Add Company Entity</h3>
					</div>
				  <div class="col-sm-9 clearfix pull-right " data-placement="top">
		<div class="row">
		 
		<div class="col-sm-8 clearfix " data-placement="top">		
				<a href="superUserCompanyEntityUpdate?companyid=<s:property value="companyid"/>&Email=<s:property value="Email"/>"
					class="btn btn-top-link btn-xs"  >
					 Company Entity List
				</a> 
				<a href="superUserCompanyList" class="btn btn-top-link btn-xs" >
					 Company List
				</a> 
			</div> 
			</div>			
			</div>
				  
				  
				</div>
			
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4> 
					<a href="superUserCompanyList"><span class="pull-right"><i
							class="fa fa-angle-left"></i>< Back</span></a>
				</h4>
			</div>
			<s:if test="hasActionErrors()">
				<div class="sccuss-full-updated" id="error-alert">
					<div class="succfully-updated clearfix">

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
 
			 
		 
			<div class="clearfix">
				<form action="addSuperCompanyEntity" method="post" class="form-horizontal"
					name="myForm" enctype="multipart/form-data" id="myfform">
					<input type="hidden" value="<s:property value="%{#session.User.id}"/>" id="createdUserId"> 
					<input type="hidden" id="companyid" name="companyid" value="<s:property value="%{CurrentCompany.companyid}"/>" >
					<input type="hidden" id="compemail" name="email" value="<s:property value="%{CurrentCompany.email}"/>" >
				<!-- ----------------- company entity --------------- --> 
 <div class="col-sm-12">
      <div class="panel-group clearfix" id="companyEntity" >
        	<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
									<a   data-toggle="collapse" data-parent="#companyEntity" > Company entity 1 </a>
									</h4>
								</div> 
								
							<div id="companyEntityDetails"
									class="panel-collapse"> 
									 
							<div class="panel-body" >  
									<div class="col-sm-3">
									<div class="form-group">
						<label for="companyEntityGstIn" class="  control-label">companyEntityGstIn</label>
						 
							<input type="text" class="form-control input-sm"
								id="companyEntityGstIn" name="companyEntityList[0].companyEntityGstIn"
								placeholder="CompanyEntityGstIn" autocomplete="off"
								required>
						 
					</div>
									</div>
									<div class="col-sm-3">
									<div class="form-group">
						<label for="Companyname" class="  control-label">Companyname</label>
						 
							<input type="text" class="form-control input-sm"
								id="Companyname" name="companyEntityList[0].Companyname"
								placeholder="Companyname" autocomplete="off"
								required>
						 
					</div>
									</div>
									<div class="col-sm-3">
									<div class="form-group">
						<label for="CompanyEntityName" class="  control-label">CompanyEntityName</label>
						 
							<input type="text" class="form-control input-sm"
								id="CompanyEntityName" name="companyEntityList[0].CompanyEntityName"
								placeholder="CompanyEntityName" autocomplete="off"
								required>
						 
					</div>
									</div>
									<div class="col-sm-3">
									<div class="form-group">
						<label for="Email" class=" control-label">Email</label>
						 
							<input type="text" class="form-control input-sm"
								id="Email" name="companyEntityList[0].Email"
								placeholder="Email" autocomplete="off"
								required>
						 
					</div>
									</div>
									<div class="col-sm-3">
									<div class="form-group">
							<label for="Countryname" class="  control-label">Country</label>
							 
							 <select class="form-control input-sm" name="companyEntityList[0].Countryname" id="Countryname"
								required>
								
								<s:iterator  value="CountryList">
								<s:if test="c_name=='India'">
								<option selected="selected"><s:property value="c_name"></s:property></option>
								</s:if>
								<s:if test="c_name !='India'">
								<option  ><s:property value="c_name"></s:property></option>
								</s:if>	
								</s:iterator>

							</select>
							 
						</div>
									</div>
									<div class="col-sm-3">
									 <div class="form-group">
						<label for="State" class="  control-label">State</label>
						
						<select class="form-control input-sm" name="companyEntityList[0].State" id="State"
								required>
								<s:iterator  value="StateList">  
								<option value="<s:property value="stateName"/>-<s:property value="StateCode"/>"><s:property value="stateName"></s:property></option> 
								</s:iterator>

							</select>
						 
							<!-- <input type="text" class="form-control input-sm"
								id="State" name="companyEntityList[0].State"
								placeholder="State" autocomplete="off"
								required> -->
						</div>
					 
									</div>
									 
									<div class="col-sm-3">
									<div class="form-group">
						<label for="City" class="  control-label">City</label>
						 
							<input type="text" class="form-control input-sm"
								id="City" name="companyEntityList[0].City"
								placeholder="City" autocomplete="off"
								required>
						 
					</div>
									</div>
									
									
									<div class="col-sm-3">
									<div class="form-group" >
<label for="PhoneNo" class=" control-label">phone</label> 
    <div class="input-group">
      <div class="input-group-btn">  
        					<select
								class="form-control input-sm" name="Countryname" id="ccc"
								required>
								<s:iterator  value="CountryList">
								<s:if test="c_name=='India'">
								<option selected="selected"><s:property value="isd_code"></s:property></option> 
								</s:if>
								<s:if test="c_name !='India'">
								<option  > <s:property value="isd_code"></s:property></option> 
								</s:if>	
								</s:iterator> 
							</select> 
      </div>
    <input type="tel" class="form-control input-sm" name="companyEntityList[0].PhoneNo" id="telphone"
								  placeholder="Phone"
								autocomplete="off" maxlength="16" required
								onkeypress="return isNumberKey(event,this)">
    </div> 
  </div>
									</div>
									<div class="col-sm-3">
									<div class="form-group">
						<label for="Address1" class=" control-label">Address1</label> 
							<textarea class="form-control input-sm" id="Address1"
								name="companyEntityList[0].Address1" placeholder="Address" required> </textarea> 
								</div>
									</div>
									<div class="col-sm-3">
									<div class="form-group">
						<label for="Address2" class=" control-label">Address2</label>
						 

							<textarea class="form-control input-sm" id="Address2"
								name="companyEntityList[0].Address2" placeholder="Address2" required> </textarea>
						 
						 
								</div>
									</div> 
									</div>
									</div>
								 
								</div> 
										<div id="add-entity"></div>
									<div class="col-sm-12 " style="margin-top: 20px;">
										<div class="row">

											<a class="btn btn-primary btn-sm" id="add_companyenity">Add Company Enity</a>
											<a class="btn btn-primary btn-sm" id="remove_entity">Remove
												Enity</a>
										</div>

									</div>
        
        </div> 
         
        </div> 
				<!-- ----------------- company entity ends ---------- --> 
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" id="addEntity" class="btn btn-primary btn-lg" >Add Entity</button>
						</div>
					</div>
				</form>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<script src="js/app.js" type="text/javascript"></script>
	<%@ include file="DashboardFooter.jsp"%>


	
<script>
$('#add_companyenity').on('click', function() { 
	 	var $entity = $('#companyEntity .panel-default:first').clone();
 			  $('#add-entity').append($entity); 
			var noOfEntitys = $('#add-entity .panel-default').length; 
		 for (var i = noOfEntitys; i <= noOfEntitys; i++) {
			 $entity.find("h4.panel-title a:first").text("Company entity" + (i+1)); 
			 $entity.find("h4.panel-title a[href='#companyEntityDetails']").attr("href", "#companyEntityDetails" + (i));
			 $entity.find("div#companyEntityDetails").attr("id", "companyEntityDetails" + (i)); 
			 $entity.find(".panel-body .form-group input[name^=companyEntityList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				});
			 $entity.find(".panel-body .form-group select[name^=companyEntityList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				}); 
			 $entity.find(".panel-body .form-group textarea[name^=companyEntityList]").each(function() {
					var name = $(this).attr('name');
					var indexOfThirdBracket = name.indexOf('[') + 1;
					name = name.substring(0,
							indexOfThirdBracket)
							+ i
							+ name
									.substring(indexOfThirdBracket + 1);
					$(this).attr('name', name);
				}); 
		 }
		 
		 
	 	  
});

$('#remove_entity').on('click', function() {
	
	$('#add-entity > .panel-default:last').remove();
	 
	var count=	$ ('#add-entity > .panel-default').val();
	/* if(count>0){
		count=count-1;
		$ ('#tripDetailCount').val(count);
	}
	else{
		$ ('#tripDetailCount').val(count);
	} */
});

$(document).ready(function(){
	
	 $("#myfform").validate({
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
   
	
	
$('#addEntity').click(function() {
	console.log("clivk");
	 $("#myfform").valid();
	 if($("#myfform").valid()){  
		 console.log("addent");
    	 document.getElementById("myfform").submit();
    }
})

});

</script>


</body>

</html>
 