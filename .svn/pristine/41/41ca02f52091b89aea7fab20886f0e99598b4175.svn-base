<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<%-- <script src="js/angular.js" type="text/javascript"></script>


<script
	src="js/jquery.min.js"></script> --%>

<link rel="stylesheet" href="css/alert.css">
<style type="text/css">
#register {
	margin-left: 100px;
}

#register label {
	margin-right: 5px;
}

#register input {
	padding: 5px 7px;
	border: 1px solid #d5d9da;
	box-shadow: 0 0 5px #e8e9eb inset;
	width: 250px;
	font-size: 1em;
	outline: 0;
}

#result {
	margin-left: 5px;
}

.short {
	color: #FF0000;
}

.weak {
	color: #E66C2C;
}

.good {
	color: #2D98F3;
}

.strong {
	color: #006400;
}
</style>


<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "home";
		$('#success').click(function() {
			 $("#uploadfile").css("display", "none");
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			 $("#uploadfile").css("display", "none");
			$('#error-alert').hide();

		});
		
		$('#typeOfWallet').change(function(){
		   	 if($('#typeOfWallet').val()== 'Postpaid') {
		           $('#Wallet-type-div').show();
		           $('#postAmount').val("");
		           
		        } 
		       else if($('#typeOfWallet').val() == 'Prepaid') {
		       	 $('#Wallet-type-div').hide(); 
		         $('#postAmount').val("");
		         
		      } 
		       
		   });
 });
	
	
</script>

</head>
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Insurance Data Upload</h1>
		
		</section>

		<!-- Main content -->
		<section class="content">

<div class="companysetup clearfix"> 
					<div class="companyset-heading"> 
						<div class="companyset-icon"> 
						<i class="fa fa-upload fa-2x" aria-hidden="true"></i> 
								 <b>Upload Employee</b>  
						</div> 
					</div>
					<div class="inner-compreg">
					
										<label for="uploadimage" class="col-sm-2 control-label">Upload
						File </label>
					<form action="doInsuranceExcelUpload" method="post" id="uploadform"
						enctype="multipart/form-data">
						<div class="col-sm-6">
							<div class="row">
								<div class="col-sm-6 file-upload">
									<input type="file" id="filePath"
										accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
										ng-file-select="onFileSelect($files)" name="excelFile">
								</div>
								<div class="col-sm-6 ">
									<div id="fileinfo">
										<div id="fileError"></div>

									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="inline">
								<button type="button" id="save" class="btn btn-primary btn-xs" onclick="validate();">Upload</button>
								 <img alt="" src="images/divloading.gif" id="uploadfile" style="display: none;background: #CC5D00;width: 25px; height: 25px;" >
							</div>
							<!-- <div class="inline">
							<a class="btn btn-primary btn-xs" href="downloadSampleEmployeeExcelFileDownload?fileName=TestingEmployeeData.xlsx"
										class="btn btn-success btn-xs" >Download Sample Excel File</a>
										</div> -->
						</div>
						
							
								
					</form>
					
					</div>
					</div>
			
			
			 
 
			<s:if test="hasActionErrors()">
				<div class="succfully-updated clearfix" id="error-alert">

					<div class="col-sm-2">
						<i class="fa fa-close fa-3x red"></i>
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
        <h4 class="modal-title" id="myModalLabel">Alert !</h4>
      </div>
      <div class="modal-body clearfix">
          <p id="desc">
     
      </p>
         
      </div>
     
    </div>
  </div>
</div>
	<script src="js/app.js" type="text/javascript"></script>
	<!--optinal-->
	<%@ include file="DashboardFooter.jsp"%>
	<script>
	

function reset_form_element (e) {
  e.wrap('<form>').parent('form').trigger('reset');
  e.unwrap();
}
function validate() { 
	
	 var _validFileExtensions = [".csv", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel",];    
	     var oForm = $("#uploadform #filePath");
	    
	     var arrInputs =  $("#uploadform #filePath");
	     for (var i = 0; i < arrInputs.length; i++) {
	         var oInput = arrInputs[i];
	         if (oInput.type == "file") {
	             var sFileName = oInput.value;
	             if (sFileName.length > 0) {
	            	 $("#uploadfile").css("display", "inline-block");
	        		 $('form#uploadform').submit();
	             }
	             else{
	            	 $("#uploadfile").css("display", "none");	            	
	            	 $("#emailModal").modal('show');		    		
		        	 $("#emailModal #desc").text("Select your employee excel file.");
	            	 }
	             }
	         }
}
 </script>
</body>

</html>