<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <!--   <title>Lintas-Admin</title> -->
  <title>Request Password</title>

    <!-- Bootstrap -->
    
     <link href="css/bootstrap.css" rel="stylesheet">  
    <link href="css/font-awesome.min.css" rel="stylesheet"> 
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    
    <link href="<s:text name="global.indexCss" ></s:text>" rel="stylesheet"> 
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
 </script>
<style type="text/css">
 .error {
    color:red;
}
.valid {
    color:green;
}
</style>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
  </head>
  <body  class="body" >

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
<div id="loggit">
<div class="container">
   <div class="col-sm-6">
    <div class="admin-logo">
      <figure>
           <img src="<s:text name="global.indexlogo" ></s:text>" alt="Logo" class="img-responsive">
      <!--     <img src="images/lintus-logo-admin.png" alt="Lintus Logo" class="img-responsive"> -->
      </figure>
    </div>
  </div>
<div class="col-sm-6"> 
 <div class="row">
   <div class="col-sm-12">
					<h1>
						<a href="javascript:history.back();"><span class="pull-right"><i
								class="fa fa-angle-left"></i> Login</span></a>
					</h1>
				</div>
    <div class="col-sm-10 login"> 
 <form action="requestPassword" id="logForm" method="post" class="form-horizontal">
        <h4 class="h2 text-center">Forgot Password</h4>
          <div class="form-group">
            <div class="col-xs-12">
              <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-envelope fa-fw"></i></span>
                <input type="email"   name="forgetPasswordMail"    id="email"  class="form-control input-sm" placeholder="email" autocomplete="off" required>
              </div>
            </div>
          </div>
          
             <div class="form-group">
            <div class="col-xs-12 submitWrap">
              <button type="button" class="btn btn-primary btn-lg" id="buttonSubmit">Request password</button>
            </div>
          </div>
           
        </form>
          
</div>     
        </div>
        
</div>
  </div>  
</div>
  
  </body>
   <script type="text/javascript">
$(document).ready(function(){

$("#logForm").validate({
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
	   if($("#logForm").valid())
	    	document.getElementById("logForm").submit();
	}); 
});
</script>
</html>