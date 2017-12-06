<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Details</title>
 <%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>

<script type="text/javascript">
$(function() {
	if(${companyUserProfile.agentWallet.walletType=='Postpaid'}){
		var str = document.getElementById("minus").value;
		  if(str.startsWith("-")){
			var minus = str.substr(1);
			var floatamount=parseFloat(minus);
			  $("#range").text(floatamount.toFixed(2));
		}
		  else{
			  $("#range").text(parseFloat(str).toFixed(2));  
		  }  
	}
	
 });
 </script>
 </head>
<body>
<s:if test="CurrentCompany!=null">
   <div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Company Details
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
						
						 <s:if test="%{CurrentCompany.Imagepath == null}"> 
                  <img  src="images/default.png" class="user-image avatar img-circle" alt="No Image" />
				  </s:if> 
						<s:else>
						 <img src="<s:url action='ImageAction?imageId=%{CurrentCompany.Imagepath}'/>" class="avatar img-circle img-thumbnail img-responsive" alt="profile image" />
						</s:else>
						
							 
						<%-- 	<img src="<s:property value="%{#session.companyDetailsObj.Imagepath}"/>"
								class="avatar img-circle img-thumbnail img-responsive" alt="profile image"> --%>
							<h4>
								<s:property value="CurrentCompany.Companyname"/>
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
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Address</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.address" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Terms and Conditions</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.temsandcondtions" />
								</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Agreement Expiry Date</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.agreementTranExpiryDate" />
								</p>
							</div>
						</div>

					</div>
					<!-- edit form column -->
					<div class="col-md-6 col-sm-6 col-xs-12 personal-info">
					<s:if test='CurrentCompany.companyRole.isDistributor()'>
								<div class="p-info clearfix">
							<div class="p-label">
							 <p>Company Type</p>
							</div>
							<div class="p-inp">
								<p>
								<s:property value="CurrentCompany.compType"/>
								</p>
							</div>
						</div>
							</s:if>
							<s:elseif test='CurrentCompany.companyRole.isAgent()'>
								<div class="p-info clearfix">
							<div class="p-label">
							 <p>Company Type</p>
							</div>
							<div class="p-inp">
								<p>
							<s:property value="CurrentCompany.compType"/>
								</p>
							</div>
						</div>
							</s:elseif>
							
					
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Company Name</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.Companyname" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p><s:text name="global.KPPPno" ></s:text></p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.registerNumber" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>GST In</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.companyGstIn" />
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Wallet Type</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="companyUserProfile.agentWallet.walletType"/>
								</p>
							</div>
						</div>
						<s:if test="%{companyUserProfile.agentWallet.walletType=='Postpaid'}">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Wallet Balance Range</p>
							</div>
							<div class="p-inp">
								<p id="range">
								<%-- <input type="hidden" id="minus"  value="<s:property value="companyUserProfile.agentWallet.walletBalanceRange"/>"> --%>
								<s:property value="companyUserProfile.walletRange"/>
								
									
								</p>
							</div>
						</div>
						
						
						</s:if>
						
						 <div class="p-info clearfix">
							<div class="p-label">
								<p>Company Id</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.company_userid" />
								</p>
							</div>
						</div>
						 
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Parent Company Id</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.parent_company_userid" />
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
						<s:if test="CurrentCompany.billingstate !=null">
						<div class="p-info clearfix">
							<div class="p-label">
								<p>State</p>
							</div>
							<div class="p-inp">
								<p>
									<s:property value="CurrentCompany.billingstate" />
								</p>
							</div>
						</div>
						</s:if>
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
								<p>Created on</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.createDate" />
								</time>
							</div>
						</div>

						<div class="p-info clearfix">
							<div class="p-label">
								<p>Modified on</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.modifyDate" />
								</time>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Billing Company</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.billingcompany" />
								</time>
							</div>
						</div>
					<%-- 	<div class="p-info clearfix">
							<div class="p-label">
								<p>Billing Reference</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="%{#session.companyDetailsObj.billingreference}" />
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
						
						 
						<%-- <div class="p-info clearfix">
							<div class="p-label">
								<p>Description</p>
							</div>
							<div class="p-inp">
								<time>
									<s:property value="CurrentCompany.Companydescription" />
								</time>
							</div>
						</div>
						 --%>
						
						
					</div>
				</div>
				<!-- /.row -->
				
				<div class="row">
						<div class=" col-sm-12 clearfix">
							<a class="btn btn-primary createdquotation collapsed" role="button" data-toggle="collapse" href="#companyEntity" aria-expanded="false" aria-controls="createdquotation">
								Company Entity  </a>
						</div>
						<div class="collapse" id="companyEntity" aria-expanded="false" >
							<div class="panel-body">
								<!-- Filter of main info -->
								<div class="clearfix">
									<div class="table-responsive">
										<table class="table">
											<thead class="thead-inverse">
												<tr>
													<th>SNo.</th>  
													<th>companyEntityGstIn</th>
													<th>Companyname</th>
													<th>CompanyEntityName</th>
													<th>Countryname</th>
													<th>Address1</th>
													<th>Address2</th> 
													<th>Email</th>
													<th>City</th> 
													<th>state</th>
													<th>StateCode</th>
													<th>PhoneNo</th>
													
												</tr>
											</thead>
											<tbody>
											<s:iterator value="CurrentCompany.getCompanyEntities"
													status="rowCount"> 
													<tr>
														<th scope="row">${rowCount.count}</th>  
														<td><s:property value="companyEntityGstIn"/></td> 
														<td><s:property value="Companyname"/></td> 
														<td><s:property value="CompanyEntityName"/></td> 
														<td><s:property value="Countryname"/></td>
														<td><s:property value="Address1"/></td> 
														<td><s:property value="Address2"/></td> 
														<td><s:property value="Email"/></td> 
														<td><s:property value="City"/></td> 
														<td><s:property value="State"/></td>
														<td><s:property value="StateCode"/></td> 
														<td><s:property value="PhoneNo"/></td>  
													</tr>
													</s:iterator>  
 
 
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>				
				

				
				
				
				
<%-- 				
<div class="col-sm-12">
				
	<div class="panel-group">
	    <div class="panel panel-default">
	      <div class="panel-heading">
	        <h4 class="panel-title">
	          <a class="collapsed" data-toggle="collapse" href="#companyEntity">Company entitys </a>
	        </h4>
	      </div>
		      <div id="companyEntity" class="panel-collapse collapse">
		        <div class="panel-body">
					<s:iterator value="CurrentCompany.getCompanyEntities"  >
						<div class="col-sm-12 personal-info"> 
							<div class="p-info clearfix">
							  <div class="p-label">
								 <p>CompanyEntityId</p>
									</div>
										<div class="p-inp">
											<p>
										<s:property value="companyEntityId"/>
									</p>
								</div>
							</div> 
							<div class="p-info clearfix">
							  <div class="p-label">
								 <p>CompanyEntityGstIn</p>
									</div>
										<div class="p-inp">
											<p>
										<s:property value="companyEntityGstIn"/>
									</p>
								</div>
							</div> 
							<div class="p-info clearfix">
							  <div class="p-label">
								 <p>Company Name</p>
									</div>
										<div class="p-inp">
											<p>
										<s:property value="Companyname"/>
									</p>
								</div>
							</div> 
						</div>
					
					
					</s:iterator>
					
						 
					</div>
				</div>
			</div>
		</div>
	</div>
			 --%>				
							
 


			</section>
			<!-- /.content -->
		</div>
 
  
  </s:if>
 
  <%@ include file="DashboardFooter.jsp"%>
	<%--   	<%@ include file="DashFooter.jsp"%> --%>
</body>
</html>