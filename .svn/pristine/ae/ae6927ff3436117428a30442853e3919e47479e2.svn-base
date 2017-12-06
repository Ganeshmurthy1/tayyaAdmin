<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agent Profile</title>
</head>
<body>
<s:if test="CurrentProfile!=null">
   <div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Employee Details
					<!--  <small>Control panel</small> -->
				</h1>
				<!-- <ol class="breadcrumb">
					<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol> -->
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Small boxes (Stat box) -->
				<div class="col-sm-12">
					<h1>
						<a href="javascript:history.back();"><span class="pull-right"><i
								class="fa fa-angle-left"></i> Back</span></a>
					</h1>
				</div>
				 
				<div class="row">

					<!-- left column -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="text-center pro-img">
						<s:if test="%{CurrentProfile.Imagepath == null}"> 
                  <img  src="images/default.png" class="user-image avatar img-circle" alt="No Image" />
				  </s:if> 
						<s:else>
						  <img src="<s:url action='ImageAction?imageId=%{CurrentProfile.Imagepath}'/>" class="avatar img-circle img-thumbnail img-responsive" alt="profile image" />
							 </s:else>
							<h4>
								<s:property value="CurrentProfile.Username" />
							</h4>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Phone </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.Mobile" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Email </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.Email" />
								</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Employee Role</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.role" />
								</p>
							</div>
						</div>
						 

					</div>
					<!-- edit form column -->
					<div class="col-md-6 col-sm-6 col-xs-12 personal-info">
						 
						<div class="p-info clearfix">
							<div class="p-label">
								<p>User ID  </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.Username" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>First Name </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.Firstname" />
								</p>
							</div>
						</div>
							<div class="p-info clearfix">
							<div class="p-label">
								<p>Last Name </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.Lastname" />
								</p>
							</div>
						</div>
						 
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Parent Company Id </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.company_userid" />
								</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.Countryname" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>City</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.City" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Language</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentProfile.language" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Created on </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentProfile.createDate" />
								</time>
							</div>
						</div>

						<%-- <div class="p-info clearfix">
							<div class="p-label">
								<p>Modified on </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.agentDetailsObj.Modifieddate}" />
								</time>
							</div>
						</div> --%>
						  <div class="p-info clearfix">
							<div class="p-label">
								<p><s:text name="global.Designation"></s:text></p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentProfile.Description" />
								</time>
							</div>
						</div>
						
						  <div class="p-info clearfix">
							<div class="p-label">
								<p>Address</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentProfile.address" />
								</time>
							</div>
						</div>
						
						
						
					</div>
				</div>
				<!-- /.row -->


			</section>
			<!-- /.content -->
		</div>
   </s:if>
 
  <%@ include file="DashboardFooter.jsp"%>
	<%--   	<%@ include file="DashFooter.jsp"%> --%>
</body>
</html>