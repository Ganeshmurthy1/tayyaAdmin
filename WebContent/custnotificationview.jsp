<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html data-ng-app="NotifyAPP">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Notification</title>
 
 
<link rel="stylesheet" href="css/alert.css">

<style>
#ui-datepicker-div{

position: relative;

}
.pad10px{
padding-left:30px ;}


</style>
</head>
<body  data-ng-controller="headerController">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="row">
				<div class="col-sm-3 clearfix pull-left">
					<h3>view Notification</h3>
				</div>
				
			</div>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>
			 
        			<div class="row">
			<div class="col-sm-12">
				<!-- createHoteOfflineBooking -->
				<form action="" method="post"
					class="form-horizontal" name="myForm" id="myFormReset" >
					<div id="myfform"> 
					<input type="hidden" id="notiCreatedUserId" name="userId"
								value="<s:property value="#session.User.id" />"> 
								<input  name="companyId"
								type="hidden" id="notiCreatedCompanyId"
								value="<s:property value="#session.User.Companyid" />">
					 
								<input type="text" id=notificationId  name="notificationId" value="{{notfyid}}">
								 <br>

												 	 
												  <div class="form-group">
														<label for="currency" class="col-sm-2 control-label"> 
															Notification Title </label>
														<div class="col-sm-8">
															<input type="text" class="form-control input-sm" value="{{notifydetail.details[0].comments}}"
																required="required" name="description" id="description" placeholder="Notification Title"
																>
														</div>
												 </div>
												
	<!-- 											 
												 <div class="form-group">
														<label for="currency" class="col-sm-2 control-label"> 
															Select Notification Type </label>
															
															<div class="col-sm-8 checkbox notify-check">
															 
					
<label class="checkbox-inline">
  <input type="checkbox" id="admin" name="admin"  class="chk  " value="false"  > Admin
</label>										 
  <label class="checkbox-inline">
  
  <input type="checkbox"   id="email" name="email" class="chk " value="false"> Email
</label>

<label class="checkbox-inline">
<input type="checkbox" onclick="return false" /> SMS
Enable this input tag when SMS IS need to be clickable 
  <input type="checkbox" id="sms" name="sms" class="chk" value="false">SMS
</label>
 
 
 </div> 
 </div> -->
<!--  
 <div class="clearfix">
<div class="col-sm-8 col-sm-offset-2">
 
 <div class="notify-esabox clearfix">
 
 <div class="col-sm-6 fromdate">
 <div class="form-group">
		<label class=" control-label"> 
			From Date </label> 
		 
			 <input type="text" name="Notification Start Date" class="form-control input-sm" required="required" value=" " id="fromDate" placeholder="Select Notification Start Date" />
</div>
 </div>
 <div class="col-sm-6 todate">
  <div class="form-group">
		<label class="  control-label"> To Date
			 </label> 
			 <input type="text" name="Notification End Date" class="form-control input-sm" id="toDate" required="required" value=" " placeholder="Select Notification End Date" />
		<input type="hidden" name="Notification End Date" class="form-control input-sm" id=frmDate required="required" value=" " placeholder="Select Notification End Date" />
		
		</div>
 </div> 
  
 </div>
 </div> 	
 
 
 </div> -->
 
<%--   <div class="form-group clearfix">
								<label for="comments" class="col-sm-2 control-label">Notification
									Prefer Time </label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" value=" ${notification.timeInterval}"
										required="required" name="timeInterval" id="timeInterval"
										placeholder="Select PreferTime">
								</div>
							</div> 
 	<div class="form-group clearfix">
								<label for="comments" class="col-sm-2 control-label">Notification
									Message </label>
								<div class="col-sm-8">
									<input type="text" class="form-control input-sm" value="${notificationDetail.comments}"
										required="required" name="comments" id="comments"
										placeholder="Write Message here">
								</div>
							</div> 
 --%>
					<!-- harsha added colapse ended -->

					<%-- <div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button"  onclick="opencustomNotiffyurl()"  
								class="btn btn-primary btn-lg">Edit Notification <span class="pull-right pad10px" id="loading">
							  <img src="images/loginLoader.gif" width="20px" height="20px"/> Please Wait
							</span></button>	
						</div>
					</div> --%>
					
					
						</div>
				</form>
			</div>
			</div>
        
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body clearfix">
          <p id="desc">
     
      </p>
         
      </div>
     
    </div>
  </div>
</div>
	<%-- <script src="js/app.js" type="text/javascript"></script> --%>
	<%@ include file="DashboardFooter.jsp"%>
 
	
 
	
	
	
</body>
</html>
