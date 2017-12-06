<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
 "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<title><tiles:getAsString name="title" /></title>  
</head>  
<body>   
  <%--  <tiles:insertAttribute name="header" /><br/>
   <hr/> --%>
    <tiles:insertAttribute name="body" /><br/>
  <%--  <hr/>
   <tiles:insertAttribute name="footer" /><br/> --%>
</body>  
</html>   