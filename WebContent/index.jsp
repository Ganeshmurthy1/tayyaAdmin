<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<html lang="en" ng-app="">
  <head>
  <meta http-equiv="Cache-control" content="no-cache">
   <link rel="icon" href="<s:text name="global.tabIcon"/>" type="image/x-icon">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <!--   <title>Lintas-Admin</title> -->

 <title>Login</title>	
    <!-- Bootstrap -->
 <link href="css/bootstrap.css" rel="stylesheet">  
    <!--   <link href="css/styles.css" rel="stylesheet"> -->  <!--enable for lintas -->
      <link href="<s:text name="global.indexCss" ></s:text>" rel="stylesheet"> <!--enable for tayyarah -->
    <link href="css/font-awesome.min.css" rel="stylesheet">
      <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    
    
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <%-- <script src="js/bootstrap.min.js"></script> --%>
     <script type="text/javascript">
  $(document).ready(function(){
	  element = document.getElementById('Joinus');
		$.ajax({
			//Action Name
			url :"isAnyCompanyExist",
			dataType : "json",
			 success : function(data, textStatus, jqXHR) {
				 if(data.companyExist){
					  element.style.display = "none";
					 
				  }
				 else{
					 element.style.display = "block";
				 }
 	  			//console.log(data.companyExist);
			 	},
			error : function(jqXHR, textStatus, errorThrown) {
				//console.log(textStatus);
			}
		});
  	});	
  </script>
  
   <script type="text/javascript"> 
      $(document).ready( function() {
        $('#resultDiv').delay(4000).fadeOut();
      });
    </script>
  
  
  
    </head>
  <body  class="body" >


<div id="loggit">
<div class="container">
<%-- 
 <div class="col-sm-12 log-welcome">
 <p class="text-center"> <span class= "h1 block">Welcome to Lintas</span><s:property value="msg"/></p>
 </div>
 --%>
   <div class="col-sm-6">
    <div class="admin-logo">
      <figure>
       <img src="<s:text name="global.indexlogo" ></s:text>" alt="Logo" class="img-responsive">
          <!-- <img src="images/tayarrah-admin.png" alt="Lintus Logo" class="img-responsive"> -->
      <!--     <img src="images/lintus-logo-admin.png" alt="Lintus Logo" class="img-responsive"> -->
      </figure>
    </div>
  </div>
<div class="col-sm-6 l-form"> 
<div class="row">
   
    <div class="col-sm-10 login"> 
 <form action="companylogin" id="logForm" method="post" class="form-horizontal">
        <h3 class="h2 text-center">Login</h3>
          <div class="form-group">
            <div class="col-xs-12">
              <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-envelope fa-fw"></i></span>
                <input type="email" name="Email"  value="<s:property value="Email"/>"   id="email"  class="form-control input-sm" placeholder="youremail@example.com" autocomplete="off" required>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="col-xs-12">
              <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                <input type="password" name="Password"  id="Password"   class="form-control input-sm" placeholder="******" autocomplete="off"     required >
              </div>
            </div>
          </div>
         <div class="form-group">
            <div class="col-xs-12">
              <div class="input-group">
                <span class="input-group-addon">  <i class="fa fa-key fa-fw"></i>  </span>  
               
                <input type="text" name="company_userid"  value="<s:property value="company_userid"/>" class="form-control input-sm" placeholder="EX1" autocomplete="off"    required>
       
              </div>
             
            </div>
             
          </div>
          <s:fielderror></s:fielderror>
           <s:if test="hasActionErrors()">
          <div class="errorDiv">
          <s:actionerror/>
           </div>
            </s:if>
            <s:if test="hasActionMessages()">
          <div id="resultDiv">
          <s:actionmessage/>
           </div>
            </s:if>
             <div class="form-group">
            <div class="col-xs-12 submitWrap">
              <button type="submit" class="btn btn-primary btn-lg">Log In</button>
            </div>
          </div>
           <div class="form-group formNotice">
            <div class="col-xs-12">
              <p class="pull-right "><!-- goRequestPassword -->
                 <a href="goRequestPassword" id="forgot_link" class="to_register">Forgot Password?</a>
               <!--   Not a member yet ? -->  <a href="createAccount" class="to_register"  id="Joinus">Join us</a> 
                 </p>
            </div>
          </div>  
        </form>
   
</div>     
        </div>
        
</div>
  </div>  
</div>

<script src="http://www.maxmind.com/js/apis/geoip2/v2.1/geoip2.js"></script>
<script>


var fillInPage = (function () {
    var updateCityText = function (geoipResponse) {
    	
 
    var geoIp =JSON.stringify(geoipResponse)
    var obj ={
      "geoIp":geoipResponse
}
 //console.log(JSON.stringify(obj)); 
    //$('#geoIpObj').val(JSON.stringify(browsingObj));
   //var geoipResponse = '?browsingObj='+browsingObj;
   $.ajax({
         url: 'insertPageBrowsingHistoryLogin',
         type: 'POST', 
         dataType: 'json',  
         data: {pageHistoryInfo : JSON.stringify(obj)},
         success: function(response) {
        	 
        	 
         }
    });  
     }
    var onSuccess = function (geoipResponse) {
        updateCityText(geoipResponse);
    };
 
    /* If we get an error we will */
    var onError = function (error) {
        return;
    };
 
    return function () {
        geoip2.city( onSuccess, onError );
    };
 
    }());
</script>

<script>
$(document).ready(function(){
	
	fillInPage();

});

</script>
  
 </body>
</html>