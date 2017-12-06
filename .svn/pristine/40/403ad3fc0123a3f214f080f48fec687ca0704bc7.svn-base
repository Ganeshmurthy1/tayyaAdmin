<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html data-ng-app="app">
<head> 
 
<link rel="stylesheet" href="css/alert.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><s:property value="user" /></title>



<script type="text/javascript">
	  $(function() {
		 
		var id=$("#uniqueId").val();
		var category=$("#category").val();
		  document.getElementById('Category'+id).value =category;
	 }); 
	
	
</script>


<script type="text/javascript">
	  $(function() {
		 
		var id=$("#dealuniqueId").val();
		var dealstype=$("#dealstype").val();
		  document.getElementById('DealsType'+id).value =dealstype;
	 }); 
	
	
</script>
<script type="text/javascript">
	  $(function() {
		 
		var id=$("#dealsuniqueId").val();
		var imagetype=$("#imagetype").val();
		  document.getElementById('ImageType'+id).value =imagetype;
	 }); 
	
	
</script>
<script type="text/javascript">
	  $(function() {
		 
		var id=$("#curencyuniqueId").val();
		var currency=$("#currency").val();
		  document.getElementById('Currency'+id).value =currency;
	 }); 
	
	
</script>
 <script src="js/jquery.js"></script> 
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body data-ng-controller="AppCtrl">
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Content Management System</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
						<h4  >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
					</div>
	<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl+"UpdateDeals";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});

	});
</script>
						 <s:if test="hasActionErrors()">

							<div class="succfully-updated clearfix" id="error-alert">

								<div class="col-sm-2">
									<i class="fa fa-check fa-3x"></i>
								</div>

								<div class="col-sm-10">

									<p>
										<s:actionerror />
									</p>

									<button type="button" id="cancel" class="btn btn-primary">Ok</button>

								</div>

							</div>

						</s:if>

						<s:if test="hasActionMessages()">
							<div class="sccuss-full-updated" id="success-alert">
								<div class="succfully-updated clearfix">

									<div class="col-sm-2">
										<i class="fa fa-check fa-3x"></i>
									</div>

									<div class="col-sm-10">
										<s:actionmessage />
										<button type="button" id="success" class="btn btn-primary">Ok</button>

									</div>

								</div>
							</div>
						</s:if>
					
			<div class="row" id="dash-us-register">



			 <form id="UpdateDealForm" action="UpdateDeals" method="post" class="form-horizontal"
					name="myForm" enctype="multipart/form-data">
					<input type="hidden" name="id" value="<s:property value="CurrentDeals.id" />">
			
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Enter Price
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="price"
								name="price"  value='<s:property value="CurrentDeals.price"/>'     placeholder="price" autocomplete="off"
								required>
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Image Caption
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="imageCaption"
								name="imageCaption"  value='<s:property value="CurrentDeals.imageCaption"/>'     placeholder="Image Caption" autocomplete="off"
								required>
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Image Name
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="imageName"
								name="imageName"  value='<s:property value="CurrentDeals.imageName"/>'     placeholder="Image Name" autocomplete="off"
								required>
								 
						</div>
					</div>
			 
					<div class="form-group">
					<input type="hidden"
									value="<s:property value="CurrentDeals.id"/>"
									id="dealsuniqueId"> 
									<input type="hidden"
									value="<s:property value="CurrentDeals.imageType"/>"
								id="imagetype">
							<label for="Country" class="col-sm-2 control-label">Image Type</label>
							<div class="col-sm-8">
								<%-- <select class="form-control input-sm"  name="imageType"  >
									  <option selected="selected">Select Image Type</option> --%>
									  
								<select class="form-control input-sm"  name="imageType"  
								id="ImageType<s:property value="CurrentDeals.id"/>"  required>
									 <option value="small">Small</option>
									 <option value="medium">Medium</option>
									 <option value="large">Large</option>
								 </select>
							</div>
						</div>
						
						<div class="form-group">
						<input type="hidden"
									value="<s:property value="CurrentDeals.id"/>"
									id="dealuniqueId"> 
									<input type="hidden"
									value="<s:property value="CurrentDeals.dealsType"/>"
									id="dealstype">
						
							<label for="Country" class="col-sm-2 control-label">Deals Type</label>
							<div class="col-sm-8">
								
								<select class="form-control input-sm"  name="dealsType"  
								id="DealsType<s:property value="CurrentDeals.id"/>" required>
									 <option value="promo">promo</option>
									 <option value="Discount">Discount</option>
									 <option value="last mintue">Last Minute Deals</option>
									 <option value="best deal">Best Deals</option>
									 <option value="super hotel deals">Super Hotel Deals</option>
								 </select>
							</div>
						</div>
						<div class="form-group">
						<input type="hidden"
									value="<s:property value="CurrentDeals.id"/>"
									id="uniqueId"> 
									<input type="hidden"
									value="<s:property value="CurrentDeals.catagory"/>"
									id="category">
									
							<label for="Country" class="col-sm-2 control-label">Category Type</label>
							<div class="col-sm-8">
								<select class="form-control input-sm"  name="catagory"  
								id="Category<s:property value="CurrentDeals.id"/>" required>
									  <option value="flight">Flight</option>
									 <option value="hotel">Hotel</option>
									 <option value="package">Package</option>
									 <option value="general">General</option>
								 </select>
							</div>
						</div>
						<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Title
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="title"
								name="title" value='<s:property value="CurrentDeals.title"/>' placeholder="Title" autocomplete="off"
								required>
								 
						</div>
					</div>
					
						<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">PageName
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="PageName"
								name="PageName"  value='<s:property value="CurrentDeals.PageName"/>'     placeholder="Page Name" autocomplete="off"
								required>
								 
						</div>
					</div>
				<div class="form-group">
								<label for="Country" class="col-sm-2 control-label">Currency</label>
								<div class="col-sm-8">
								<input type="hidden"
									value="<s:property value="CurrentDeals.id"/>"
									id="curencyuniqueId"> 
									<input type="hidden"
									value="<s:property value="CurrentDeals.currency"/>"
									id="currency">
								
								
									<select class="form-control input-sm" name="currency"
										id="language_id" autocomplete="off" required>
										<option
											value="<s:property value="CurrentDeals.currency"/>"
											selected="selected"><s:property
												value="CurrentDeals.currency" /></option>

										<s:iterator value="CountryList">
											<option value="<s:property value="cur_code"/>"><s:property
													value="cur_code"></s:property></option>
										</s:iterator>

						</select>
						</div>
							</div>
							
							
							
								
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">City</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" name="City"
								id="city"  value='<s:property value="CurrentDeals.City"/>' placeholder="city" autocomplete="off" required>
						</div>
					</div>
					<div class="form-group">
								<label for="Description" class="col-sm-2 control-label">Description</label>
								<div class="col-sm-8">
									<textarea class="form-control input-sm" id="Description"
										name="description" placeholder="Description"
										autocomplete="off" required><s:property
											value="CurrentDeals.description" /></textarea>
								</div>
							</div>

					<div class="form-group">
						<label for="uploadimage" class="col-sm-2 control-label">Upload
							Image</label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<%-- <figure>
										<img src="<s:property value="%{#session.EditdDeals.imageUrl}"/>" height="100" width="100">
									</figure> --%>
									<input type="file" id="uploadimage" accept="image/*" name="imageUrl"
										ng-file-select="onFileSelect($files)" required> 
										<!-- <input
										type="hidden" name="imageUrl" ng-model="Imagepath"
										value="{{Imagepath}}"> -->
								</div>
								<div class="col-sm-6 ">
									<div id="fileinfo">
										<div id="fileError"></div>										
									</div>
								</div>

							</div>


						</div>
 					 
					</div>						



					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button id="UpdateCmsSubmit" type="button" class="btn btn-primary btn-lg">
								Update CMS</button>
						</div>
					</div>
				</form> 
			</div>
					
					
					
			
			</section>
		
		
		
		</div>
		
		
<script src="js/DealUpload.js"></script>	
<%@ include file="DashboardFooter.jsp"%>
		
	<script>
	$(document).ready(function(){
		  
		   $('#UpdateCmsSubmit').click(function(){
			   $("#UpdateDealForm").valid();
			   if($("#UpdateDealForm").valid()){
				   document.getElementById("UpdateDealForm").submit();
			   }
		   });
		   $("#UpdateDealForm").validate({
			   submitHandler: function (form) {  
		            
		            return false;
		        },
		        highlight: function(element, errorClass, validClass) { 
		            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-ok').addClass('glyphicon-remove');
		            $(element).addClass(errorClass).removeClass(validClass);
		            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		          },
		          success: function(element) {
		            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-remove').addClass('glyphicon-ok');
		         element.closest('.form-group').removeClass('has-error').addClass('has-success');
		            $(element).remove();
		          }
		   })
		   });
	</script>	

</body>
</html>