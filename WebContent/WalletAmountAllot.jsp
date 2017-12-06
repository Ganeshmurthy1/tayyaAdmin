
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="dj" uri="/struts-dojo-tags"%>
<!DOCTYPE html  >
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wallet</title>

<%-- <script src="js/angular.js" type="text/javascript"></script> --%>
	<link rel="stylesheet" href="css/alert.css">

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
		  window.location.assign(finalUrl); 
		   $('#error-alert').hide();
		  
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
<body data-ng-controller="AppCtrl">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Add Wallet Balance to <s:property value="Username"/></h1>
		</section>
		<section class="content">
			<!-- Small boxes (Stat box) -->
 		<div class="col-sm-12">
						<h4 >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
						
					</div>
					<h4 >
						 <b>Credit balance</b> : <s:property value="agentWallet.walletbalance"/> <s:property value="agentWallet.currencyCode"/>&nbsp&nbsp&nbsp&nbsp     <b>Deposit balance</b> : <s:property value="user.agentWallet.depositBalance"/> <s:property value="agentWallet.currencyCode"/>
						</h4>
				 
			<!-- Small boxes (Stat box) -->
			 <s:if test="hasActionErrors()">
						 <div class="sccuss-full-updated" id="success-alert">
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

				<form action="addAgentWallet" method="get" class="form-horizontal"
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
					<label id="result"></label>
					 <input type="hidden" class="form-control input-sm"  
								name="userPreviousWalletBalance"    id="previousBalance" value="<s:property value="agentWallet.walletbalance"/>"
								required>
						
					<div class="form-group">
						<label for="Username" class="col-sm-2 control-label">Amount
							 </label>
						<div class="col-sm-8">
						 <input type="text" class="form-control input-sm"  
								name="agentWallet.walletbalance"   id="validAmount" onkeypress="return isNumberKey(event,this)" autocomplete="off"
								required>
						  <input type="hidden" class="form-control input-sm"  
								name="id" required value="<s:property value="id"/>">  
								 <input type="hidden" class="form-control input-sm"  
								name="agentWallet.walletId" placeholder="User ID " autocomplete="off"
								required value="<s:property value="agentWallet.walletId"/>"/>
								
								<input type="hidden" placeholder="Password" id="Password"
								class="form-control input-sm" 
								name="Password">
								
						</div>
					</div>
					<div class="form-group">
					<label for="Username" class="col-sm-2 control-label">Transaction Type
						</label>
						<div class="col-sm-8">
					<select class="form-control input-sm" name="agentWallet.transactionType"
								id="transactionType" autocomplete="off" required>
								<option selected value="Credit">Credit</option>
								<option  value="Debit">Debit</option>
								<s:if test="user.userrole_id.isUsermode()==true">
									<option  value="Deposit">Deposit</option>
									<option  value="Withdrawal">Withdrawal</option>
								</s:if>
					</select>	
					</div>
					</div>
 
 			<div class="form-group">
						<label for="City" class="col-sm-2 control-label">Currency</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" name="agentWallet.currencyCode"
								id="currency" autocomplete="off" required>
								<option selected value="<s:property value="agentWallet.currencyCode"/>"><s:property value="agentWallet.currencyCode"/></option>
							</select>
						</div>
					</div>
 
	 
					<div class="form-group payment-mode"  style="display:none">
						<label for="City" class="col-sm-2 control-label">Payment Mode</label>
						<div class="col-sm-8">
						<select  class="form-control input-sm" name="agentWallet.paymentMode"  id="paymentMode" required>
						
																	<option value="Cash" selected="selected">Cash</option>
																	<option value="Cheque">Cheque</option>
																	<option value="Credit">Credit Card</option>
																	<option value="Debit">Debit Card</option>
																	<option value="Neft">NEFT</option>
																	<option value="Imps">IMPS</option>
																</select>
						 
						</div>
					</div>
					
					<div class="form-group reference-number"  style="display:none">
						<label for="City" class="col-sm-2 control-label">Reference/Card Number</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm"  name="agentWallet.referenceNumber" placeholder="Reference/Card Number" required="required" />
						</div>
					</div>
					<div class="form-group">
						<label for="City" class="col-sm-2 control-label">Description</label>
						<div class="col-sm-8">
						<!-- 	<input type="text" class="form-control input-sm"  name="agentWallet.Description" placeholder="Description" required="required" /> -->
							  <textarea rows="2" cols="3" name="remarks"
								 class="form-control input-sm" placeholder="remarks"
								required="required"></textarea> 
						</div>
					</div>
					
					 
					<div class="form-group text-center">
						<div class="col-xs-12 submitWrap text-center">
							<button type="button"   id="walletBut" onClick="showPopUp();" class="btn btn-primary btn-lg">Add Wallet</button>
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
	
<div class="modal fade" id="updateBoxWallet" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
<div class="modal-dialog">
    <div class="modal-content up-pass">
          <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        <h4 class="modal-title custom_align text-center" id="Heading">Enter Password</h4>
      </div>
          <div class="modal-body">
        <div class="form-group">
       		<!-- <label for="exampleInputAmount">Company Type</label> -->
						<!-- <label> Password</label> -->
						<div class="input-group">
							<input type="password" placeholder="Password" id="Password_1"
								class="form-control input-sm" 
								name="Password_1">
						</div>
					</div>
					 <div class="modal-footer text-center">
					 <button type="button" onclick="javascript:userWalletVerifyByPassword();" class="btn btn-success text-center"  ><span>Send</span> </button>
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
 
	 if($("#validAmount").val()==null || $("#validAmount").val()== "" ){
		alert("Enter amount") 
	 }else{
	 $('#updateBoxWallet').modal({
	        show: true
	    }); 
	 } 
  }
 
      
     
 </script>
  <script type="text/javascript">
$(document).ready(function(){
	 var paymentMode=$("#paymentMode").val();
	 if(paymentMode=="Cash") 
		 $(".reference-number").hide();
	 
	 else 
		 $(".reference-number").show();
	  
    $("#transactionType").change(function(){
        $(this).find("option:selected").each(function(){
            var optionValue = $(this).attr("value");
            if(optionValue=="Deposit") 
                $(".payment-mode").show();
           else{
                $(".payment-mode").hide();
                $(".reference-number").hide();
            }
            var paymentMode=$("#paymentMode").val();
       	 if(paymentMode=="Cash")
       		 $(".reference-number").hide();
       	  
       	 else 
       		 $(".reference-number").show();
       	 
            
        });
    });
    $("#paymentMode").change(function(){
        $(this).find("option:selected").each(function(){
            var optionValue = $(this).attr("value");
            if(optionValue=="Cash") 
            	 $(".reference-number").hide();
             else 
            	  $(".reference-number").show();
           
        });
    });
});
</script>  
</body>

</html>