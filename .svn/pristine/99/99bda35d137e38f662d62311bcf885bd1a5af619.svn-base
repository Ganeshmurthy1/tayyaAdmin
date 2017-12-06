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
<s:if test="%{#session.Company!=null && #session.User!=null}">
<s:if test="%{#session.User.userrole_id.isSuperuser()}">
 <div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Company Profile
					<!--  <small>Control panel</small> -->
				</h1>
				 
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Small boxes (Stat box) -->

				<div class="row">
					<div class="col-sm-12">
						<h1 class="page-header">
							Profile <a href="editcompanyprofile"
								class="fa fa-pencil-square-o"></a>
						</h1>
					</div>
				</div>
				<div class="row">

					<!-- left column -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="text-center pro-img">
						  <img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>"
								class="avatar img-circle img-thumbnail img-responsive" alt="profile image">
							<h4>
								<s:property value="CurrentCompany.Companyname" />
							</h4>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Phone </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.phone" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Mail </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.email" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Website</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.website" />
								</p>
							</div>
						</div>

					</div>
					<!-- edit form column -->
					<div class="col-md-6 col-sm-6 col-xs-12 personal-info">

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Company Name </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.Companyname" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p><s:text name="global.KPPPno" ></s:text> </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.registerNumber" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Payment-Type </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.agentWallet.walletType}" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.countryname" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>City</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.city" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Created on </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.createDate" />
								</time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Modified on </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.modifyDate" />
								</time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Billing Company </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.billingcompany" />
								</time>
							</div>
						</div>
						<%-- <div class="p-info clearfix">
							<div class="p-label">
								<p>Billing Reference </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.Company.billingreference}" />
								</time>
							</div>
						</div> --%>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Billing Country</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.billingcountry" />
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
<s:elseif test="%{#session.User.userrole_id.isUsermode()}">
<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent() || #session.Company.companyRole.isCorporate()}">
 
  <div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Company Profile
					<!--  <small>Control panel</small> -->
				</h1>
				<ol class="breadcrumb">
					<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Small boxes (Stat box) -->

				<div class="row">
					<div class="col-sm-12">
						<h1 class="page-header">
							Profile <a href="editcompanyprofile"
								class="fa fa-pencil-square-o"></a>
						</h1>
					</div>
				</div>
				<div class="row">

					<!-- left column -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="text-center pro-img">
							<img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>"
								class="avatar img-circle img-thumbnail img-responsive" alt="profile image">
							<h4>
								<s:property value="CurrentCompany.Companyname" />
							</h4>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Phone </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.phone" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Mail </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.email" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Website</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.website" />
								</p>
							</div>
						</div>

					</div>
					<!-- edit form column -->
					<div class="col-md-6 col-sm-6 col-xs-12 personal-info">

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Company Name </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.Companyname" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>KKKP Number </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.registerNumber" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Payment-Type </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.agentWallet.walletType}" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.countryname" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>City</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.city" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Created on </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.createDate" />
								</time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Modified on </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.modifyDate" />
								</time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Billing Company </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.billingcompany" />
								</time>
							</div>
						</div>
						<%-- <div class="p-info clearfix">
							<div class="p-label">
								<p>Billing Reference </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.Company.billingreference}" />
								</time>
							</div>
						</div> --%>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Billing Country</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.billingcountry" />
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
 </s:elseif>
 
  <s:else>
 	<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Employee Profile
					<!--  <small>Control panel</small> -->
				</h1>
				<ol class="breadcrumb">
					<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Small boxes (Stat box) -->

				<div class="row">
					<div class="col-sm-12">
						<h1 class="page-header">
							Profile <a href="setuserprofile" class="fa fa-pencil-square-o"></a>
						</h1>
					</div>
				</div>
				<div class="row">

					<!-- left column -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="text-center pro-img">
							<img src="<s:url action='ImageAction?imageId=%{#session.User.Imagepath}'/>"
								class="avatar img-circle img-thumbnail img-responsive" alt="profile image">
							<h4>
								<s:property value="%{#session.User.Username}" />
							</h4>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Mobile</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Mobile}" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Mail </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Email}" />
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
									<s:property value="%{#session.User.Username}" />
								</p>
							</div>
						</div>
						 <div class="p-info clearfix">
							<div class="p-label">
								<p><s:text name="global.Designation"></s:text></p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.User.Description}" />
								</time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Parent Company Id </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.company_userid}" />
								</p>
							</div>
						</div>
						
						 <div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Countryname}" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>City</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.City}" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Created on </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.User.createDate}" />
								</time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Address</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.User.Address}" />
								</time>
							</div>
						</div>

					</div>
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
  </s:else>
 
 
 
 
 
 
 
 
 
  </s:if>
<%--  <s:elseif  test="%{#session.User!=null  && #session.Company!=null}">
<s:if test="%{#session.User.userrole_id.isAdmin()}">
	<s:if test="%{#session.User.Companyid==#session.Company.companyid}">
	<s:if test="%{#session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
	<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					User Profile
					<!--  <small>Control panel</small> -->
				</h1>
				<ol class="breadcrumb">
					<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Small boxes (Stat box) -->

				<div class="row">
					<div class="col-sm-12">
						<h1 class="page-header">
							Profile <a href="setuserprofile" class="fa fa-pencil-square-o"></a>
						</h1>
					</div>
				</div>
				<div class="row">

					<!-- left column -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="text-center pro-img">
							<img src="<s:property value="%{#session.User.Imagepath}"/>"
								class="avatar img-circle img-thumbnail" alt="profile image">
							<h4>
								<s:property value="%{#session.User.Username}"/>
							</h4>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Mobile</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Mobile}" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Mail </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Email}" />
								</p>
							</div>
						</div>
					 

					</div>
					<!-- edit form column -->
					<div class="col-md-6 col-sm-6 col-xs-12 personal-info">

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Emp Code </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Username}" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Countryname}" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>City</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.City}" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Created on </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.User.Createddate}" />
								</time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Address</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.User.Address}" />
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
	 
 <s:else>
 	<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					User Profile
					<!--  <small>Control panel</small> -->
				</h1>
				<ol class="breadcrumb">
					<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Small boxes (Stat box) -->

				<div class="row">
					<div class="col-sm-12">
						<h1 class="page-header">
							Profile <a href="setuserprofile" class="fa fa-pencil-square-o"></a>
						</h1>
					</div>
				</div>
				<div class="row">

					<!-- left column -->
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="text-center pro-img">
							<img src="<s:property value="%{#session.User.Imagepath}"/>"
								class="avatar img-circle img-thumbnail" alt="profile image">
							<h4>
								<s:property value="%{#session.User.Username}" />
							</h4>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Mobile</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Mobile}" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Mail </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Email}" />
								</p>
							</div>
						</div>
					 

					</div>
					<!-- edit form column -->
					<div class="col-md-6 col-sm-6 col-xs-12 personal-info">

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Emp Code </p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Username}" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Country</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.Countryname}" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>City</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="%{#session.User.City}" />
								</p>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Created on </p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.User.Createddate}" />
								</time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Address</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.User.Address}" />
								</time>
							</div>
						</div>

					</div>
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
  </s:else>
 </s:if>
	</s:if>
	</s:elseif>
  --%>
 <%@ include file="DashboardFooter.jsp"%>
	<%--   	<%@ include file="DashFooter.jsp"%> --%>
</body>
</html>