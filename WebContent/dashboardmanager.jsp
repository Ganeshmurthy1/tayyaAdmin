<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
 "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<title><tiles:getAsString name="title" /></title>  
</head>  
<body>  
 <tiles:insertAttribute name="header" /><br/>
  <tiles:insertAttribute name="commonmenu" /><br/>
 <tiles:insertAttribute name="sidemenu" /><br/>
   <tiles:insertAttribute name="body" /><br/>
   <%--  <tiles:insertAttribute name="footer" /><br/> --%>
   <!-- livezilla.net code (PLEASE PLACE IN BODY TAG) -->
<div class="livechat" style="position: fixed;bottom:0px;right:0px;background-color: #0082CA;padding: 5px;border-radius: 10px;">
<div style="text-align:center;width:auto;"><div id="livezilla_tracking" style="display:none"></div><script type="text/javascript">
var script = document.createElement("script");script.async=true;script.type="text/javascript";var src = "https://tayyarah.com/livezilla/server.php?a=97089&rqst=track&output=jcrpt&nse="+Math.random();setTimeout("script.src=src;document.getElementById('livezilla_tracking').appendChild(script)",1);</script><noscript><img src="https://tayyarah.com/livezilla/server.php?a=97089&amp;rqst=track&amp;output=nojcrpt" width="0" height="0" style="visibility:hidden;" alt=""></noscript><a href="javascript:void(window.open('https://tayyarah.com/livezilla/chat.php?a=aae6a','','width=610,height=760,left=0,top=0,resizable=yes,menubar=no,location=no,status=yes,scrollbars=yes'))" class="lz_cbl" style="color: #fff;font-size: 16px;text-decoration: none;font-weight: bold;padding:10px;"><i class="fa fa-weixin"></i> Live Chat<!-- <img src="https://tayyarah.com/livezilla/image.php?a=54735&amp;id=1&amp;type=inlay" width="201" height="53" style="border:0px;" alt="LiveZilla Live Chat Software"> --></a></div></div>
<!-- http://www.livezilla.net -->
   
 </body>  
</html>   