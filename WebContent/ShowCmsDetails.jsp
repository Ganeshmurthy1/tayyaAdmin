
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMS Details</title>
</head>
<body>
<s:if test="%{#session.CmsDetailsObj!=null}">
   <div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Cms Details
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
						<%-- <div class="text-center pro-img">
						<figure>
						
							<img src="<s:property value="%{#session.CmsDetailsObj.image_url}"/>"
								class="avatar img-circle img-thumbnail img-responsive" alt="profile image">
								<figcaption>
								 <s:property value="%{#session.CmsDetailsObj.image_url}"/>
										<s:property value="%{#session.CmsDetailsObj.imageCaption}" />
								</figcaption>
								</figure>
								<input
										type="hidden" name="imageUrl" ng-model="Imagepath"
										value="{{Imagepath}}">
							<h4>
								<s:property value="%{#session.CmsDetailsObj.imageCaption}" />
							</h4>
						</div> --%>
						<div class="form-group">
						
						<div class="col-sm-12">

							
								<div class="file-upload">

									<figure>
									
										<img src="<s:url action='DealImageAction?imageId=%{#session.CmsDetailsObj.imageUrl}'/>" height="300" width="500" >
									</figure>
									
								</div>
								<h4 class="text-center">
								<s:property value="%{#session.CmsDetailsObj.imageCaption}" />
							</h4>
							


						</div>
 					 
					</div>	

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Currency </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.CmsDetailsObj.currency}" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Price </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.CmsDetailsObj.price}" />
								</p>
							</div>
						</div>
						 
						<div class="p-info clearfix">
							<div class="p-label">
								<p>City </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.CmsDetailsObj.City}" />
								</p>
							</div>
						</div>
					</div>
					<!-- edit form column -->
					<div class="col-md-6 col-sm-6 col-xs-12 personal-info">
						 
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Image Type</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.CmsDetailsObj.imageType}" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Deal Type </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.CmsDetailsObj.dealsType}" />
								</p>
							</div>
						</div>
							<div class="p-info clearfix">
							<div class="p-label">
								<p> Deals Category</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.CmsDetailsObj.catagory}" />
								</p>
							</div>
						</div>
						 
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Deal Description </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.CmsDetailsObj.description}" />
								</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Deal Title</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.CmsDetailsObj.title}" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Deal Page Name</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.CmsDetailsObj.PageName}" />
								</p>
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
