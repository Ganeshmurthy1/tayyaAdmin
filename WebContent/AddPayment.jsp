
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wallet</title>
	<link rel="stylesheet" href="css/alert.css">
	<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"showWalletUsers";
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide();
		
	});
	  $('#cancel').click(function() {
		  /* window.location.assign(finalUrl);  */
		   $('#error-alert').hide();
		  
		 });  
 });
 </script>
 
 <script type="text/javascript">
 
  $(document).ready(function() {
	 var loginCompanyId=$("#loginCompanyId").val()
            var company_list = []; 
	 var citymap = []; 
			  $.ajax({
				//Action Name
				url :"CompanyListUnderSuperUser",
				dataType : "json",
				data : {
				 parent_company_user_id : $("#companyUserId").val(),
					email : $("#email").val()
				},
				success : function(data, textStatus, jqXHR) {
					var items = data.records;
					for (var i = 0; i < data.records.length; i++) {
						dataValue = data.records[i];
						if(data.records[i].companyid!=loginCompanyId){
							   company_list.push(data.records[i].companyname+"("+data.records[i].email+")");
								citymap[data.records[i].companyname+"("+data.records[i].email+")"] = data.records[i].companyid;
						   }
					}
					console.log(citymap);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			}); 
			  
			  $("#companyName").autocomplete(
						{
							source : function(request, response) {
								var matcher = new RegExp('^'
										+ $.ui.autocomplete.escapeRegex(request.term),
										"i");
								response($.grep(company_list, function(item) {
									return matcher.test(item);

								}));
							},
							select: function (event, ui) {
								 			
								var  companyId = citymap[ui.item.value];
								  $('#companyId').val(companyId);
							 }
						});
            });
 
 
  </script>
 <script type="text/javascript">
  
 function isNumberKey(evt,obj){
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    if (charCode > 31  && (charCode > 57 || charCode <46 ))  
	   	 return false;
	   
	} 
 $(function() {
	  $('#validAmount').keyup(function() {
		   
		  if($(this).val() == '0'){
		      $(this).val('');  
		    }
		  var pattern = /^([a-zA-Z1-9].*)$/;
		    if(!pattern.test($(this).val())){
			  $(this).val('');  
		  }
		  
		    if(isNaN($('#validAmount').val())){
				 $(this).val(''); 
			 } 
		  
		  
	  });
	     
});
  </script>
 </head>
<body >
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Add Payment</h1>
		</section>
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-left"><i
							class="fa fa-angle-left"></i> Back</span></a>
							<a href="paymentReceivableList"  class="btn btn-primary but btn-clean pull-right"><span class="pull-right">Payment list<i
							class="fa fa-angle-right"></i></span></a>
							 
							 
				</h4>

			</div>
						 
			<!-- Small boxes (Stat box) -->
			<s:if test="hasActionErrors()">
				<div class="sccuss-full-updated" id="error-alert">
					<div class="succfully-updated clearfix">
						<div class="col-sm-2">
							<i class="fa fa-times fa-3x red"></i>
						</div>
						<div class="col-sm-10">
							<s:actionerror />
							<button type="button" id="cancel" class="btn btn-primary">Ok</button>
						</div>

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
				<!-- addAgentWallet -->
				<form action="addPayment" method="post" class="form-horizontal" enctype="multipart/form-data" id="addPayment">
				 <input type="hidden"  id="email"  value='<s:property value="#session.Company.Email"/>'>
        		      <input type="hidden" id="companyUserId"    value='<s:property value="#session.Company.company_userid" />'>
        		        <input type="hidden" id="loginCompanyId"    value='<s:property value="#session.Company.Companyid" />'>
					<label id="result"></label>
					 
					<!-- <div class="form-group" >
						<label for="City" class="col-sm-2 control-label"> is Knock off?</label>
						<div class="col-sm-1">
						<div   class="checkbox">
								<input type="checkbox" id="knockOff"     name="KnockOff" value="false">
								 
						</div>
						
					</div>
				 </div>
					 -->
					 
					<div class="form-group fileupload-group" style="display: none">
							<label for="uploadimage" class="col-sm-2 control-label">Upload Excel Only</label>
							<div class="col-sm-8">
								<div class="row" >
									<div class="col-sm-6 file-upload">
										<input type="file" id="filePath" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" 
											ng-file-select="onFileSelect($files)" name="transFilePath">
									</div>
									<div class="col-sm-6 ">
										<div id="fileinfo">
 									<div id="fileError"></div>
										</div>
									</div>
								</div> 
							</div> 
						</div> 
						
					 <div class="form-group  required" >
						<label for="validAmount" class="col-sm-2 control-label">Amount</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" autocomplete="off"
								name="amount" id="validAmount" value="${paymentRecievable.amount}"
								placeholder="amount" required="required" />
						</div> 
					</div>
					
					 <div class="form-group  required" >
						<label for="paymentType" class="col-sm-2 control-label">Payment Type</label>
						<div class="col-sm-8">
							<select  class="form-control input-sm" name="paymentType" required="required">
							<option value="Debit">Debit</option>
								<option value="Credit">Credit</option>
							</select>
						</div>
					</div>
					 <div class="form-group not-required" >
						<label for="transFromDt" class="col-sm-2 control-label">Period</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" autocomplete="off"
								name="transFromDt" id="fromdate" value="${paymentRecievable.transFromDt}"
								placeholder="from date" required="required" />
						</div>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" autocomplete="off"
								name="transToDt" id="todate" value="${paymentRecievable.transToDt}"
								placeholder="to date" required="required" />
						</div> 
					</div>
					 
					 <div class="form-group">
						<label for="companyName" class="col-sm-2 control-label">Company</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" autocomplete="off"   id="companyName" placeholder="Type Company name" required="required" />
							<input type="hidden"  autocomplete="off" name="companyId"  id="companyId" value="${paymentRecievable.companyId}" />
						</div>
					</div>
					<div class="form-group payment-mode not-required">
						<label for="City" class="col-sm-2 control-label">Payment Mode</label>
						<div class="col-sm-8">
							<select class="form-control input-sm"
								name="paymentMode" id="paymentMode" required> 
								<option value="Cash" selected="selected">Cash</option>
								<option value="Cheque">Cheque</option>
								<option value="Credit">Credit Card</option>
								<option value="Debit">Debit Card</option>
								<option value="Neft">NEFT</option>
								<option value="Imps">IMPS</option>
								<option value="JV">Journal Voucher</option>
							
							</select>

						</div>
					</div>
					<div class="form-group reference-number" style="display: none">
						<label for="City" class="col-sm-2 control-label">Reference/Card Number</label>
						<div class="col-sm-8">
							<input type="text" autocomplete="off"   class="form-control input-sm"
								name="referenceNumber"  
								placeholder="Reference/Card Number" />
						</div>
					</div>
						<div class="form-group not-required">
						<label for="transReceivedDate" class="col-sm-2 control-label">Received Date</label>
						<div class="col-sm-8">
							<input type="text"  id="receivedDate" autocomplete="off"   class="form-control input-sm" name="transReceivedDate"  placeholder="dd-mm-yyyy" required="required" value="${paymentRecievable.transReceivedDate}" />
						</div>
					</div>
					<div class="form-group not-required">
						<label for="receivedBy" class="col-sm-2 control-label">Received By</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" name="receivedBy" autocomplete="off" id="userName"    placeholder="received by"  value="${paymentRecievable.receivedBy}" />
						</div>
					</div>
					 
					<div class="form-group">
						<label for="City" class="col-sm-2 control-label">Description</label>
						<div class="col-sm-8">
							<!-- 	<input type="text" class="form-control input-sm"  name="agentWallet.Description" placeholder="Description" required="required" /> -->
							<textarea rows="2" cols="3" name="description"
								class="form-control input-sm" placeholder="Description"  
								required="required">${paymentRecievable.description}</textarea>
						</div>
					</div>


					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<input type="button" class="btn btn-primary btn-lg" value="Add Payment" id="buttonSubmit"> 
						</div>
					</div>
				</form>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
 
	<%@ include file="DashboardFooter.jsp"%>
	<script src="js/reset-form.js" type="text/javascript"></script>
	<script src="js/user_names.js" type="text/javascript"></script>
	 
	<script type="text/javascript">

	$("#receivedDate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
	});
	
		function showPopUp() {
			if ($("#validAmount").val() == null
					|| $("#validAmount").val() == "") {
				alert("Enter amount")
			} else {
				$('#updateBoxWallet').modal({
					show : true
				});
			}
		}
	</script>
	<script type="text/javascript">
$(document).ready(function(){
	 var paymentMode=$("#paymentMode").val();
	 if(paymentMode=="Cash" || paymentMode=="JV") 
		 $(".reference-number").hide();
	 else 
		 $(".reference-number").show();
    $("#paymentMode").change(function(){
        $(this).find("option:selected").each(function(){
            var optionValue = $(this).attr("value");
         console.log(optionValue);
            if(optionValue=="Cash" || optionValue=="JV") 
            	 $(".reference-number").hide();
             else 
            	  $(".reference-number").show();
           
        });
    });
    if($('#knockOff').prop('checked')){ 
    	 $("#knockOff").val(true);
		$('.fileupload-group').show();
		$('.required').hide();
		 
    }
else{
	 $("#knockOff").val(false);
	$('.fileupload-group').hide();
	 $('.required').show();
}
    
    $("#knockOff").click(function(){
    	if($('#knockOff').prop('checked')){
    		   $("#knockOff").val(true);
    		   $('.fileupload-group').show();
    		 $('.required').hide(); 
    	}
    		
    else{ 
    	 $("#knockOff").val(false);
    	$('.fileupload-group').hide();
     $('.required').show(); 
    }
    
    });
    
    $("#fromdate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy", 
		onSelect : function(selectedDate) {
			var date2 = $("#fromdate").datepicker('getDate', '+1d');
			date2.setDate(date2.getDate() + 1);
			$("#todate").datepicker('setDate', date2);
			$("#todate").datepicker("option", "minDate", date2);
		},
		onClose : function() {
			$("#todate").focus();
		}

	});
	$("#todate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy", 
		onSelect : function(selected) {
			$("#fromdate").datepicker("option", selected)
		}

	});
    
	});
	
	
$(document).ready(function(){ 
	 
    $("#addPayment").validate({
    	 rules: { 
            "email": {
                required: true,
                email: true
            },
            fromdate: {
                required: true,
                date: true
              },
              todate: {
                  required: true,
                  date: true
                }, 
                receivedDate: {
                    required: true,
                    date: true
                  },
                  receivedBy: {
                      required: true, 
                  },
                  paymentType:{
                  	 required: true,
                  },
                  validAmount:{
                	  required: true,
                  }
                	  
        }, 
        
        messages: { 
            "email": {
                required: "Please, enter an email",
                email: "Email is invalid"
            },
        }, 
        highlight: function(element, errorClass, validClass) { 
            $(element).addClass(errorClass).removeClass(validClass);
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
          },
          success: function(element) { 
         element.closest('.form-group').removeClass('has-error').addClass('has-success');
            $(element).remove();
          },
        submitHandler: function (form) {   
            return false;  
        }
 
       
    });
    
    $('#buttonSubmit').click(function() { 
  	if($("#addPayment").valid())  
    	 document.getElementById("addPayment").submit();
  /* 	else
  		document.getElementById("requiredspan").val = "Please Fill Required Feilds"  */
    });    
    
});

</script>

 
</body>

</html>