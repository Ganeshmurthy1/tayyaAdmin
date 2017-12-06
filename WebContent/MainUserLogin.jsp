<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Lintas-admin</title>

    <!-- Bootstrap -->
    <!-- <link href="css/bootstrap.css" rel="stylesheet"> -->
    <link href="css/styles.css" rel="stylesheet">
    <!-- <link href="css/font-awesome.min.css" rel="stylesheet"> -->
    

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
    
  </head>
  <body class="body">


<div id="loggit">
<div class="container">
   <div class="col-sm-6">
    <div class="admin-logo">
      <figure>
          <img src="images/lintus-logo-admin.png" alt="Lintus Logo" class="img-responsive">
      </figure>
    </div>
  </div>
<div class="col-sm-6"> 
<div class="row">
   
    <div class="col-sm-10 login"> 

      <form action="mainUserLogin" id="logForm" method="post" class="form-horizontal">
        <h3 class="h2 text-center">Main User Login</h3>
          <div class="form-group">
            <div class="col-xs-12">
              <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-envelope fa-fw"></i></span>
                <input type="email" name="Email" class="form-control input-sm" placeholder="Email" autocomplete="off" required>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="col-xs-12">
              <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                <input type="password" name="Password" class="form-control input-sm" placeholder="******" autocomplete="off" required>
              </div>
            </div>
          </div>
         
            <s:fielderror></s:fielderror>
           <s:if test="hasActionErrors()">
          <div class="errorDiv">
          <s:actionerror/>
           </div>
            </s:if>
             <div class="form-group">
            <div class="col-xs-12 submitWrap">
              <button type="submit" class="btn btn-primary btn-lg">Log In</button>
            </div>
          </div>
          <div class="form-group formNotice">
            <div class="col-xs-12">
              <p class="pull-right ">
                  Not a member yet ?
                  <a href="#" class="to_register">Join us</a>
               </p>
            </div>
          </div>
        </form>
          
          <div class="errorDiv">
          <s:actionmessage/>
           </div>
            
       
        
        
</div>     
        </div>
        
</div>
  </div>  

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>