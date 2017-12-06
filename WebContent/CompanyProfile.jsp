<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>
 
  <!--   SHOWING COMPANY PROFILE ACCORDING TO USER LOGIN  -->

<s:if test="%{#session.company_pro!=null}"> 
       <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
         Company Profile
           <!--  <small>Control panel</small> -->
          </h1>
          <!-- <ol class="breadcrumb">
            <li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Dashboard</li>
          </ol> -->
        </section>

        <!-- Main content -->
<section class="content">
           
<div class="row">
   
    <!-- left column -->
    <div class="col-md-6 col-sm-6 col-xs-12">
      <div class="text-center pro-img">
        <img src="<s:property value="%{#session.company_pro.Imagepath}"/>" class="avatar img-circle img-thumbnail" alt="profile image">
        <h4><s:property value="%{#session.company_pro.Companyname}"/></h4>
      </div>

        <div class="p-info clearfix">
            <div class="p-label">
              <p>Phone :</p>
            </div>
            <div class="p-inp">
               <p><s:property value="%{#session.company_pro.phone}"/></p>
            </div>
          </div>
 
          <div class="p-info clearfix">
            <div class="p-label">
              <p>Mail :</p>
            </div>
            <div class="p-inp">
               <p><s:property value="%{#session.company_pro.email}"/></p>
            </div>
          </div>
           <div class="p-info clearfix">
            <div class="p-label">
              <p>Website:</p>
            </div>
            <div class="p-inp">
               <p><s:property value="%{#session.company_pro.website}"/></p>
            </div>
          </div>

    </div>
    <!-- edit form column -->
    <div class="col-md-6 col-sm-6 col-xs-12 personal-info">
     
          <div class="p-info clearfix">
            <div class="p-label">
              <p>Company Name :</p>
            </div>
            <div class="p-inp">
               <p><s:property value="%{#session.company_pro.Companyname}"/></p>
            </div>
          </div>
           <div class="p-info clearfix">
            <div class="p-label">
              <p>Country:</p>
            </div>
            <div class="p-inp">
               <p><s:property value="%{#session.company_pro.countryname}"/></p>
            </div>
          </div>
          <div class="p-info clearfix">
            <div class="p-label">
              <p>City:</p>
            </div>
            <div class="p-inp">
               <p><s:property value="%{#session.company_pro.city}"/></p>
            </div>
          </div>
           <div class="p-info clearfix">
            <div class="p-label">
              <p>Agreement Expiry Date:</p>
            </div>
            <div class="p-inp">
               <p><s:property value="%{#session.company_pro.areementExpiryDate}"/></p>
            </div>
          </div>
          
      
    </div>
 </div><!-- /.row -->
          

        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      </s:if>
      
      <%@ include file="DashboardFooter.jsp"%> 
<%--   	<%@ include file="DashFooter.jsp"%> --%>
</body>
</html>