<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wallet</title>

<script src="js/angular.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
 
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">
<!-- <link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css"> -->
	<style type="text/css">
	
	.error {
    color:red;
}
.valid {
    color:green;
}
	
	</style>
<script type="text/javascript">
$(function() {
	var totUrl=$(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	  var finalUrl = newUrl+"superMyTx";
	$('#success').click(function() {
	 window.location.assign(finalUrl); 
		$('#success-alert').hide(); 
	});
	  $('#cancel').click(function() {
		   $('#error-alert').hide(); 
		});  
 });
 
 function isNumberKey(evt,obj){
    var charCode = (evt.which) ? evt.which : event.keyCode;
     if (charCode > 31 && (charCode < 46 || charCode > 57))  
   	 return false; 
}  
 $(function() {
		  $('#amount').keyup(function() {
			   
			  if($(this).val() == '0'){
			      $(this).val('');  
			    }
			 var pattern = /^([a-zA-Z1-9].*)$/;
			 // var pattern = /^([a-zA-Z0-9]*)$/;
			  if(!pattern.test($(this).val())){
				  $(this).val('');  
			  } 
			 if(isNaN($('#amount').val())){
				 $(this).val(''); 
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
			<h1>Add MyWallet</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
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
<span id="requiredspan"></span>
				<form action="addMyWallet" method="post" class="form-horizontal"
					name="myForm" id="addWalletForm">
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
						<label for="Username" class="col-sm-2 control-label">Amount
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm"
								name="agentWallet.walletbalance" autocomplete="off" id="amount"
								onkeypress="return isNumberKey(event,this)" required>
								
								 <input
								type="hidden" class="form-control input-sm" name="id" required
								value="<s:property value="%{#session.User.id}"/>">
								
								 <input type="hidden" class="form-control input-sm" id=""
								name="agentWallet.walletId" autocomplete="off" required
								value="<s:property value="%{#session.User.agentWallet.walletId}"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Transaction
							Type </label>
						<div class="col-sm-8">
							<select class="form-control input-sm" 
								name="agentWallet.transactionType"
								id="agentWallet.transactionType" autocomplete="off" required>
								<option selected value="Credit">Credit</option>
								<option value="Debit">Debit</option>
								<option  value="Deposit">Deposit</option>
								<%-- <s:if test="val">
									<option  value="Deposit">Deposit</option>
								</s:if> --%>
							</select>
						</div>
					</div>
					<div id="hidendivision" class="form-group" style="display: none;">
						<label for="Username" class="col-sm-2 control-label">Transaction
							Type </label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm"
								name="" autocomplete="off" id="hidendepo" required>
						</div>
					</div>
					<div class="form-group">
						<label for="City" class="col-sm-2 control-label">Currency</label>
						<div class="col-sm-8">
							<select class="form-control input-sm"
								name="agentWallet.currencyCode" id="currency" autocomplete="off"
								required>
								<option selected
									value="<s:property value="%{#session.User.agentWallet.currencyCode}"/>"><s:property
										value="%{#session.User.agentWallet.currencyCode}" /></option>
								<%-- <s:iterator value="#session.countryList">
									<option value="<s:property value="cur_code"/>"><s:property
											value="cur_code"></s:property></option>
								</s:iterator>
 --%>
							</select>
						</div>
					</div>
					<%-- <div class="form-group">
						<label for="City" class="col-sm-2 control-label">Currency</label>
						<div class="col-sm-8">
							<select class="form-control input-sm"
								name="agentWallet.currencyCode" id="currency" autocomplete="off"
								required>
								<option selected
									value="<s:property value="%{#session.User.agentWallet.currencyCode}"/>"><s:property
										value="%{#session.User.agentWallet.currencyCode}" /></option>
								<s:iterator value="#session.countryList">
									<option value="<s:property value="cur_code"/>"><s:property
											value="cur_code"></s:property></option>
								</s:iterator>

							</select>
						</div>
					</div> --%>

					<input type="hidden" placeholder="Password" id="Password"
						class="form-control input-sm" name="Password">
					<div class="form-group">
						<label for="City" class="col-sm-2 control-label">Remarks</label>
						<div class="col-sm-8">
							<textarea rows="2" cols="3" name="remarks"
								class="form-control input-sm" placeholder="Remarks"
								required="required"></textarea>
						</div>
					</div>


					<!-- <div class="form-group box">
						<label for="City" class="col-sm-2 control-label">deposit
							balance</label>
						<div class="col-sm-8">
						<input type="text" class="form-control input-sm"  name="agentWallet.depositBalance" placeholder="deposit balance" required="required" />
							<textarea rows="2" cols="3" name="agentWallet.depositBalance"
								class="form-control input-sm" placeholder="depositBalance"
								required="required"></textarea>
						</div>
					</div> -->

					<!-- <div class="form-group box">
						<label for="City" class="col-sm-2 control-label">reference
							no</label>
						<div class="col-sm-8">
						
							<input type="text" class="form-control input-sm"  name="agentWallet.refernceNo" placeholder="refernceNo" required="required" />
							<textarea rows="2" cols="3" name="agentWallet.refernceNo"
								class="form-control input-sm" placeholder="refernceNo"
								required="required"></textarea>
						</div>
					</div> -->

					<!-- <div class="form-group box">
						<label for="City" class="col-sm-2 control-label">payment
							mode</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm"  name="agentWallet.paymentMode" placeholder="payment mode" required="required" />
						
							<textarea rows="2" cols="3" name="agentWallet.paymentMode"
								class="form-control input-sm" placeholder="paymentMode"
								required="required"></textarea>
						</div>
					</div> -->
					<!-- <div class="form-group box">
						<label for="City" class="col-sm-2 control-label">description</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm"  name="agentWallet.Description" placeholder="Description" required="required" />
							<textarea rows="2" cols="3" name="agentWallet.Description"
								class="form-control input-sm" placeholder="Description"
								required="required"></textarea>
						</div>
					</div>
 -->
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button" onclick="javascript:showPopUp();"
								class="btn btn-primary btn-lg" id="buttonSubmit">Add Wallet</button>
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
	
	<div class="modal fade" id="updateBoxWallet" tabindex="-1"
		role="dialog" aria-labelledby="edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content up-pass">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<h4 class="modal-title custom_align text-center" id="Heading">Enter
						Password</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<!-- <label for="exampleInputAmount">Company Type</label> -->
						<!-- <label> Password</label> -->
						<div class="input-group">
							<input type="password" placeholder="Password" id="Password_1"
								class="form-control input-sm" name="Password_1">
						</div>
					</div>
					<div class="modal-footer text-center">
						<button type="button"
							onclick="javascript:userWalletVerifyByPassword();"
							class="btn btn-success text-center">
							<span>Send</span> 
						</button>
						<%-- <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button> --%>
					</div>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<%@ include file="DashboardFooter.jsp"%>
	<script src="js/app.js" type="text/javascript"></script>
	<script type="text/javascript">
 function userWalletVerifyByPassword()
 {
	 var password = $("#Password_1").val();
	 console.debug(password);
	 $("#Password").val(password);
	 $("#addWalletForm").submit();
  }
 function showPopUp()
 {
	 if($("#amount").val()==null || $("#amount").val()=="" ){
		alert("Enter amount") 
	 }else{
	 $('#updateBoxWallet').modal({
	        show: 'true'
	    }); 
	 }
  }
 function openDiv() {
	 alert("saas");
	  $("#hidendivision").val("some");
	 
  }
/*  
 $(function() {
	    $('#deposit').on('click', function() {
	        $(this).hide(); //hide text
	        $('#txtBox').show(); //show textbox
	    });

	    $('#txtBox').on('blur', function() {
	        var that = $(this);
	        $('#txtBoxValue').text(that.val()).show(); //updated text value and show text
	        that.hide(); //hide textbox
	    });
	}); */
 </script>
 
 
 <script type="text/javascript">
$(document).ready(function(){
	 $(".box").hide();
    $("select").change(function(){
        $(this).find("option:selected").each(function(){
            var optionValue = $(this).attr("value");
            if(optionValue=="Deposit"){
                /* $(".box").not("." + optionValue).hide();
                $("." + optionValue).show(); */
                $(".box").show();
            } else{
                $(".box").hide();
            }
        });
    }).change();
});


$(document).ready(function(){ 
	//jQuery.validator.messages.required = "";
/* 	jQuery.validator.addMethod("baseFareprice",function(value) {
        var startprice = 1;
        return (startprice < parseFloat($('#basePrice').val()));
      }, "please add  base fare");

	jQuery.validator.addMethod("productTypeVal",function(value) {
        var startprice =  $('#managementFeesForSend').val();
        return (startprice !=  '00.00');
      }, "please select product type");

        jQuery.validator.classRuleSettings.baseFareprice = { baseFareprice: true };

        $.validator.addMethod("cusValidationAlphaNum", function(value, element) {
             return this.optional(element) || /^[a-zA-Z0-9._ ]+$/i.test(value);
         }, "This field cannot have symbols.");

         $.validator.addMethod("cusValidationAlphaName",function(value,element){
             return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
         },"This field cannot have numbers and symbols."); 
         $.validator.addMethod("cusValidationforprice",function(value,element){
             return this.optional(element) || /^[0-9.]+$/i.test(value);
         },"This field cannot have Char and symbols.");
 */
        
    $("#addWalletForm").validate({
    	 rules: { 
            "email": {
                required: true,
                email: true
            },
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
 /*         invalidHandler: function(e,validator) {
            for (var i=0;i<validator.errorList.length;i++){   
                $(validator.errorList[i].element).parents('.panel-collapse.collapse').collapse('show');
            }
        },  */
       
    });
    
    $('#buttonSubmit').click(function() { 
  	if($("#addWalletForm").valid())  
    	 document.getElementById("addWalletForm").submit();
  /* 	else
  		document.getElementById("requiredspan").val = "Please Fill Required Feilds"  */
    });    
    
});

</script>
</body>

</html>