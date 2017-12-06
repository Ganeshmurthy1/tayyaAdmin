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
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"ShowListOfDeals";
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide();
			
		});  
 });
 </script>
 <script type="text/javascript">
function numbersonly(e){
    var unicode=e.charCode? e.charCode : e.keyCode
    if (unicode!=8){ //if the key isn't the backspace key (which we should allow)
        if (unicode<48||unicode>57) //if not a number
            return false //disable key press
    }
}

function onlyAlphabets(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
            return true;
        else
            return false;
    }
    catch (err) {
        alert(err.Description);
    }
}
</script>
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



			 <form id="InsertDeal" action="InsertDeals" method="post" class="form-horizontal"
					name="myForm" enctype="multipart/form-data">
					<s:if test="hasActionErrors()">
						<div class="success-alert" style="display: none">
							<span class="fa fa-thumbs-o-up fa-1x"></span>
							<s:actionerror />
						</div>
					</s:if>
					<s:if test="hasActionMessages()">
						<div class="success-alert" style="display: none">
							<span class="fa fa-thumbs-o-up fa-1x"></span>
							<s:actionmessage />
						</div>
					</s:if>
			
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Enter Price
							</label>
						<div class="col-sm-8">
							<%-- <input type="text" class="form-control input-sm" id="price"
								name="price"  value='<s:property value="deal_price"/>'     placeholder="price" autocomplete="off"
								required min="4" maxlength="10" onkeypress="return numbersonly(event)"> --%>
								<input type="text" class="form-control input-sm" id="price"
								name="price"  value='<s:property value="deal_price"/>'     placeholder="price" autocomplete="off"
								required   maxlength="10" onkeypress="return numbersonly(event)">
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Start Date
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="startdate"
								name="Startdate"  value='<s:property value="Startdate"/>'     placeholder="yyyy-mm-dd" autocomplete="off"
								required  maxlength="10" onkeypress="return numbersonly(event)">
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">End Date
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="enddate"
								name="Enddate"  value='<s:property value="Enddate"/>'    placeholder="yyyy-mm-dd" autocomplete="off"
								required  maxlength="10" onkeypress="return numbersonly(event)">
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Image Caption
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="imageCaption"
								name="imageCaption"  value='<s:property value="image_Caption"/>'     placeholder="Image Caption" autocomplete="off"
								required  min="10" maxlength="25">
								 
						</div>
					</div>
					
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Image Name
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="imageName"
								name="imageName"  value='<s:property value="image_Name"/>'     placeholder="Image Name" autocomplete="off"
								required min="10" maxlength="25">
								 
						</div>
					</div>
			 
					<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Image Type</label>
							<div class="col-sm-8">
								<select class="form-control input-sm"  name="imageType"  required="required" >
									  <option selected="selected" value="">Select Image Type</option>
									 <option value="small">Small</option>
									 <option value="medium">Medium</option>
									 <option value="large">Large</option>
								 </select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Deals Type</label>
							<div class="col-sm-8">
								<select class="form-control input-sm"  name="dealsType"  required="required" >
									  <option selected="selected" value="">Select Deals Type</option>
									 <option value="promo">promo</option>
									 <option value="Discount">Discount</option>
									 <option value="last mintue">Last Minute Deals</option>
									 <option value="best deal">Best Deals</option>
									 <option value="super hotel deals">Super Hotel Deals</option>
								 </select>
							</div>
						</div>
						<div class="form-group" >
							<label for="Country" class="col-sm-2 control-label">Category Type</label>
							<div class="col-sm-8">
								<select class="form-control input-sm"  id="catagory" name="catagory" required="required" onchange="checkcatagory();">
									  <option selected="selected" value="">Select Catagory Type</option>
									 <option value="flight">Flight</option>
									 <option value="hotel">Hotel</option>
									 <option value="package">Package</option>
									 <option value="general">General</option>
								 </select>
							</div>
						</div>
						
						<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">City</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" name="City"
								id="city" placeholder="city" autocomplete="off" required>
						</div>
					</div>
					
					<div class="form-group" id="descity" style="display: none;">
						<label for="Username" class="col-sm-2 control-label">Destination City</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" name="City1"
								id="city1" placeholder="Destination City" autocomplete="off" required>
						</div>
					</div>
						
						<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Title
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="title"
								name="title"  value='<s:property value="Deals_title"/>' placeholder="Title" autocomplete="off"
								required>
								 
						</div>
					</div>
					
						<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">PageName
							</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" id="PageName"
								name="PageName"  value='<s:property value="Deals_title"/>'     placeholder="Page Name" autocomplete="off"
								required >
								 
						</div>
					</div>
					
					<div class="form-group">
							<label for="Country" class="col-sm-2 control-label">Currency</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" name="currency" required="required">
									<option value="" selected="selected">select currency</option>
								<s:iterator value="CountryList">
								<option value='<s:property value="cur_code"/>'><s:property value="cur_code"/></option>
								 </s:iterator>
								 </select>
							</div>
						</div>

					
					<div class="form-group">
						<label for="Description" class="col-sm-2 control-label">Description</label>
						<div class="col-sm-8">
							<textarea class="form-control input-sm" id="Description"
								name="description" placeholder="Description"
								autocomplete="off" required></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="uploadimage" class="col-sm-2 control-label">Upload
							Image</label>
						<div class="col-sm-8">

							<div class="row">
								<div class="col-sm-6 file-upload">

									<!-- <figure>
										<img ng-src="{{imageSrc}}" height="100" width="100">
									</figure> -->
									<input type="file" name="imageUrl" id="uploadimage" accept="image/*" ng-file-select="onFileSelect($files)" required> 
										<!-- <input	type="hidden" name="imageUrl" ng-model="Imagepath" value="{{Imagepath}}"> -->
									<%-- 	<s:file name="userImage" label="User Image" /> --%>
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
							<button id="InsertDealSubmit" type="button" class="btn btn-primary btn-lg">Add
								CMS</button>
						</div>
					</div>
				</form> 
			</div>
					
					
					
			
			</section>
		
		
		
		</div>
		
		<script>
		
		function checkcatagory()
		{
			var selectedcatagory =  $('#catagory').find(":selected").text();
			console.log(selectedcatagory);
			if(selectedcatagory == 'Flight')			
				$('#descity').show();
			else
				$('#descity').hide();
				
		}
		
		$(document).ready(function() {
			
			$('#startdate').datepicker({
				numberOfMonths : 2,
				firstDay : 1,
				dateFormat : 'yy-mm-dd',
				minDate : '0',

				onSelect : function(dateStr) {
					 $(this).valid();
					var d1 = $(this).datepicker("getDate");
					d1.setDate(d1.getDate() + 0); // change to + 1 if necessary
					var d2 = $(this).datepicker("getDate");
					d2.setDate(d2.getDate() + 31); // change to + 29 if necessary
					$("#enddate").datepicker("setDate", null);
					$("#enddate").datepicker("option", "minDate", d1);
					// $("#twodpd2").datepicker("option", "maxDate", d2);

				},
				onClose : function(dateSt) {
					$("#enddate").focus();
				}
			});

			$("#enddate").datepicker({
				numberOfMonths : 2,
				firstDay : 1,
				dateFormat : 'yy-mm-dd',

				onSelect : function(dateStr) {
					 $(this).valid();
				}
			});
			
			
		});
		$(document).ready(function(){
			  
			   $('#InsertDealSubmit').click(function(){
				   $("#InsertDeal").valid();
				   if($("#InsertDeal").valid()){
					   document.getElementById("InsertDeal").submit();
				   }
			   });
			   $("#InsertDeal").validate({
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
<script src="js/DealUpload.js"></script>	


	
<%@ include file="DashboardFooter.jsp"%>
		
		

</body>
</html>