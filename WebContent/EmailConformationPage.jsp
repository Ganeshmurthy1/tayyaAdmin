<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="icon" href="<s:text name="global.tabIcon"/>" type="image/x-icon">
<title> <s:text name="global.welcome"></s:text></title>

 <link href="<s:text name="global.Appcss"></s:text>" rel="stylesheet" type="text/css" />
 
</head>
<body>
  <div class="wrapper">
      <header class="main-header2">
        <div> 
            <a href="#" class="logo">
            <span class="logo-lg"><img src="<s:text name="global.ImagePath" ></s:text>" class="img-responsive"></span>
            </a>
        </div>
      </header>
      <article class="log-welcome">
        <h1>Welcome To <s:text name="global.companyName"></s:text></h1>
        <p class="cont">Your Email is verified. Please contact for your login credentials</p>
        <p > Contact details:</p>
 
	<address>
            Name: <a href="#"><s:property value="pname"></s:property></a><br/>
            Contact  Number: <a href="#" ><s:property value="pphone"></s:property></a><br/>
           	Email: <a href="#" style="cursor:auto;"><s:text name="global.companyMail" ></s:text></a>
          
        
        </address>
 	</article>
      </div>
     <footer class="log-footer">
   <address>
   
   		Web:<a href="<s:text name="global.website"></s:text>"><s:text name="global.website" ></s:text></a><br/>
           Email: <a href="#" style="cursor:auto;"><s:text name="global.companyMail" ></s:text></a> |
           Phone: <a href="#" style="cursor:auto;"><s:text name="global.phone" ></s:text></a>
    </address>
       

</footer>
    
  </body>
 
</html>