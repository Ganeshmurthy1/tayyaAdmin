<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en" data-ng-app="app">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Register</title>

<!-- Bootstrap -->
 <link href="css/bootstrap.css" rel="stylesheet">
 <link href="css/styles.css" rel="stylesheet"> 
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/admin-lintas.css" rel="stylesheet">
<script src="js/angular.js"></script>
<script src="js/imageupload.js"></script>
<script src="js/app.js"></script>
  <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
	 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>

<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"index.jsp";
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide();
	 
		
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
 });
$(function() {
    $('#password').keydown(function(e) {
        if (e.keyCode == 32) // 32 is the ASCII value for a space
            e.preventDefault();
    });
    $("#company").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    $("#registerNumber").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    $("#Website").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    $("#username").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    $("#email").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    $("#address").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    $("#city").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    
    $("#location").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    
    $("#Billingcompany").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
    $("#Billingaddress").on("keypress", function(e) {
        if (e.which === 32 && !this.value.length)
            e.preventDefault();
    });
    
 });
 
 
 
 </script>

<script type="text/javascript">
	$(document).ready(
			function() {
 			var currency_list = [];
 			 $('#country').change(function(){
 				var country=$('#country').val();
 				 
			  $.ajax({
					//Action Name
					url :"currencyJson",
					dataType : "json",
					success : function(data, textStatus, jqXHR) {
						$.each(data.currencyMap, function( key, value ) {
							if(country!="0"){
							if(key==country){
								console.log( key + ": " + value );
								 document.getElementById('currency').value =value;
								 document.getElementById('Billingcountry').value =country;
							 
							}
							}
							else{
								 document.getElementById('currency').value ="0";
								 document.getElementById('Billingcountry').value ="0";
							}
							
							});
 					 
					 console.log(data.currencyMap);  
						 
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log("-------Error-----status-----------"+textStatus);
						console.log("-------jqXHR--- -----------"+jqXHR);
						console.log("-----errorThrown-----------"+errorThrown);
					}
				}); 

			   });
 		        });
	 
 </script>
</head>
<body data-ng-controller="AppCtrl">


	<header>
		<nav class="navbar navbar-default" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="container">
				<div class="navbar-header col-sm-3">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-ex1-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="logo">
						<h1>
							<a href="#"><img src="<s:text name="global.indexlogo" ></s:text>"
								class="img-responsive"></a>
						</h1>
					</div>



				</div>

				<div class="col-sm-9">
					<!-- <div class="row">
              <div class="collapse navbar-collapse navbar-ex1-collapse col-sm-6 col-md-7">
                <ul class="nav navbar-nav ">        
                  <li role="presentation" class="active"><a href="#flight" id="flight-tab" role="tab" data-toggle="tab" aria-controls="flight" aria-expanded="true"> Flight</a></li>
                  <li role="presentation" class=""><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">Hotel</a></li>                 
                  <li><a href="#">Holidays</a></li>
                  <li><a href="#">Cars</a></li>
                  <li><a href="#">Restaurants</a></li>
                  <li><a href="#">City Breaks</a></li>   
                </ul>
              </div>/.navbar-collapse -->

					<div class="col-sm-6 col-md-5 top-header">
						<ul class="list-inline clearfix pull-right">
							<li id="login"><a href="index.jsp" class="last-child h3">Login
							</a></li>

						</ul>
					</div>
					<!-- </div> -->
				</div>
			</div>
			</div>
		</nav>
	</header>

	<div id="common">
		<div class="register">
			<h3 class="h2 ">Company registration</h3>
		</div>
		<div class="col-sm-12">
			<h2>
				<a href="javascript:history.back();"><span class="pull-right"><i
						class="fa fa-angle-left"></i> Back</span></a>
			</h2>
		</div>
		<div class="container register-profile">
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

			<div class="row">
				<div class="col-sm-8 ">


					<form id="companyRegisterForm" action="companyRegister" method="post"
						class="form-horizontal" enctype="multipart/form-data">

						<div class="border-common">

							<p class="h4">
								<b>Basic information</b>
							</p>

						</div>

						<div class="form-group">
							<label for="Company" class="col-sm-2 control-label">Company Name</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="company"
									name="Companyname" placeholder="Company Name" title="Enter Company Name"
									autocomplete="off" required>
								<!-- <input type="hidden" class="form-control input-sm" id="companyRole"
									name="companyRole" value="super"> -->
							</div>
						</div>

 						<div class="form-group">
						<label for="Username" class="col-sm-2 control-label"><s:text name="global.KPPPno" ></s:text>
							 </label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="registerNumber"
								name="registerNumber" value='<s:property value="registerNumber"/>'
								placeholder="<s:text name="global.KPPPno" ></s:text>" autocomplete="off" required="required"> <!-- (Note : for lintas use only) -->

						</div>
					</div>


						<div class="form-group">
							<label for="Website" class="col-sm-2 control-label">Website</label>
							<div class="col-sm-8">
								<input class="form-control input-sm" type="url" name="Website"
									placeholder="http://www.example.com" value="http://" />
							</div> 
						</div>
						<div class="form-group">
							<label for="Username" class="col-sm-2 control-label">
							User ID 
							</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="username"
									name="Username" placeholder="User ID " autocomplete="off"
									required>
							</div>
						</div>

						<div class="form-group">
							<label for="Password" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-8">
								<input type="password" ng-model="password"
									class="form-control input-sm" name="Password" id="password"
									placeholder="Password" autocomplete="off" required>
							</div>
						</div>

						<div class="form-group">
							<label for="Email" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-8">
								<input type="email" class="form-control input-sm" name="Email"
									id="email" placeholder="Email" autocomplete="off" required>
							</div>
						</div>

						<div class="form-group">
							<label for="Address" class="col-sm-2 control-label">Address</label>
							<div class="col-sm-8">
								<textarea class="form-control input-sm" id="address"
									name="Address" placeholder="Address" autocomplete="off"
									required></textarea>

							</div>
						</div>

						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Country</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" name="Countryname"  id="country">
									<option value="0" selected="selected">-----------select country------------</option>
								<s:iterator value="CountryList">
								<option value='<s:property value="c_name"/>'><s:property value="c_name"/></option>
								 </s:iterator>
								 </select>
							</div>
						</div>
					<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Language</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" name="Language">
									<option value="0" selected="selected">-----------select Language------------</option>
								<s:iterator value="LanguageList">
								<option value='<s:property value="language"/>'><s:property value="language"/></option>
								 </s:iterator>
								 </select>
							</div>
						</div>

 						 	<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Currency</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" name="currencyCode" id="currency">
									<option value="0" selected="selected">-----------select currency------------</option>
								<s:iterator value="CountryList">
								<option value='<s:property value="cur_code"/>'><s:property value="cur_code"/></option>
								 </s:iterator>
								 </select>
							</div>
						</div>








						<div class="form-group">
							<label for="Username" class="col-sm-2 control-label">City</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" name="City"
									id="city" placeholder="city" autocomplete="off" required>
							</div>
						</div>
						<div class="form-group">
							<label for="telphone" class="col-sm-2 control-label">Phone</label>
							<div class="col-sm-8">
								<input type="tel" class="form-control input-sm" name="Phone"
									id="telphone" placeholder="phone number" autocomplete="off"
									required>
							</div>
						</div>

						<div class="border-common">

							<p class="h4">
								<b>Logo</b>
							</p>

						</div>
								<div class="form-group">
						<label for="uploadimage" class="col-sm-2 control-label">Upload
							Image</label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<figure>
										<!-- <img ng-src="{{imageSrc}}" height="100" width="100"> -->
									</figure>
									<input type="file" id="uploadimage" accept="image/*" ng-file-select="onFileSelect($files)" name="Imagepath"
										 > <!-- <input
										type="hidden" name="Imagepath" ng-model="Imagepath"
										value="{{Imagepath}}"> -->
								</div>
							
						<!-- ng-file-select="onFileSelect($files)" -->
								<div class="col-sm-6 ">
									<div id="fileinfo">

										<div id="fileError"></div>
										
									 
									</div>
								</div>
							</div>


						</div>

					</div>


						<div class="border-common">

							<p class="h4">
								<b>State/City</b>
							</p>

						</div>


						<div class="form-group">
							<label for="Username" class="col-sm-2 control-label">Location</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" name="location"
									id="location" placeholder="location" autocomplete="off"
									required>
							</div>
						</div>

						 
						<div class="border-common">

							<p class="h4">
								<b>Billing Address</b>
							</p>

						</div>

						<div class="form-group">
							<label for="Company" class="col-sm-2 control-label">Company</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="company"
									name="Billingcompany" placeholder="Company Name"
									autocomplete="off" required>
							</div>
						</div>

						<!-- <div class="form-group">
							<label for="Reference" class="col-sm-2 control-label">Reference</label>
							<div class="col-sm-8">
								<input type="text" class="form-control input-sm" id="Reference"
									name="Billingreference" placeholder="Your Reference"
									autocomplete="off" required>
							</div>
						</div> -->

						<div class="form-group">
							<label for="Address" class="col-sm-2 control-label">Address</label>
							<div class="col-sm-8">
								<textarea class="form-control input-sm" id="address"
									name="Billingaddress" placeholder="Address" autocomplete="off"
									required></textarea>
							</div>
						</div>
						<div class="form-group ">
							<label for="Country" class="col-sm-2 control-label">Country</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" name="Billingcountry"
									id="country" autocomplete="off" required>
									<option value="0" selected="selected" >-----------select country------------</option>
									<s:iterator value="CountryList">
								<option value='<s:property value="c_name"/>'><s:property value="c_name"/></option>
								 </s:iterator>
								</select>
							</div>
						</div>

						 

						<div class="form-group text-center">
							<div class="col-xs-12 submitWrap text-center">
								<button id="RegisterSubmit" type="button" class="btn btn-primary">Register</button>
							</div>
						</div>

					</form>
				</div>
 	</div>


		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<%-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<%-- <script src="js/bootstrap.min.js"></script> --%>
	<script type="text/javascript" src="js/app.js"></script>

<script>
 
 $("#uploadimage" ).change(function() {
	   var  fileExtension = $('#uploadimage').val().substr(($('#uploadimage').val().lastIndexOf('.') + 1));
		if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
		{
			 
		}
		else{
			  alert("Please select Image File.");
			  reset_form_element( $('#uploadimage') );
			    e.preventDefault();
			  
		}
});
function reset_form_element (e) {
  e.wrap('<form>').parent('form').trigger('reset');
  e.unwrap();
}
 </script>
 <script>
	$(document).ready(function(){
		  
		   $('#RegisterSubmit').click(function(){
			   $("#companyRegisterForm").valid();
			   if($("#companyRegisterForm").valid()){
				   document.getElementById("companyRegisterForm").submit();
			   }
		   });
		 		   
		   $("#companyRegisterForm").validate({
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
</html>