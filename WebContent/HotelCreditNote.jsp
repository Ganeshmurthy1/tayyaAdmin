<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<style>
thead{
background: #fff;
}
img {
    margin-top: 0px!important;  
  margin-bottom: 0px!important;  
}
</style>


	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
		<s:if test="%{#session.Company.Companyid==creditNoteData.companyId}">
		<h1>Hotel Customer Credit Note</h1>
	</s:if>
	<s:else>
	<h1>Hotel Credit Note</h1>
	 </s:else>
	 *(<small>Kindly refresh the page if credit note does not appear</small>)
			
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<!-- Main content -->
					<!-- Modal -->
	<div id="invoiceModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Send Credit Note To Email</h4>
      </div>
      <span style='color:red;font-size:15px'>* Please separate email-id's with semicolon </span>
      <div class="modal-body">
      
      <form action="" method="post" class="form-horizontal" name="myForm"  id="invoiceForm">
     <div class="row">
     <div class="col-sm-12"> 
   
      <div class="col-sm-6"> 
      <div class="form-group">
  <label for="comment">To Mail(s):</label> 
  <textarea class="form-control" rows="2" id="invoiceToEmails"></textarea>
</div>
</div>
     <div class="col-sm-6"> 
      <div class="form-group">
  <label for="comment">CC Mail(s):</label>
  <textarea class="form-control" rows="2" id="invoiceFromEmails"></textarea>
</div>
</div>
   
     </div>
     </div>
     </form>
      </div>
      <div class="modal-footer">
       <button type="button"   onclick="sendOfflineVoucherOrInvoiceToMail('CreditNote','${creditNoteData.creditNoteId}');" class="btn btn-primary btn-sm">Send</button>
        <button type="button"  class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>	
<section class="content">
          	<div class="col-sm-12 no-print">
          	<div class="col-sm-6"> 
          		<a data-toggle="modal" href="#invoiceModal" class="btn btn-warning btn-xs">Send Credit Note Via Email</a>
          		<a href="#" class="btn btn-warning btn-xs" onclick="printDiv('display-api-content')">print</a>
          		<a href='<s:text name="global.resourceApiUrl"></s:text>getpdf/creditnote?orderid=<s:property value="creditNoteData.creditNoteId"/>&travelcode=2' class="btn btn-success btn-xs">
															Pdf </a> 
          	</div>
          	<div class="col-sm-6">
						<h4 >
							  <a href="javascript:history.back();"><span
								class="pull-right"><i class="fa fa-angle-left"></i>
									Back</span></a>
						</h4>
						</div>
					</div>
					 
					  <div class="row">
                    <div class="col-sm-12 clearfix ">
                        <div id="display-api-content" class="container">
						</div>
                    </div>
                </div> 
                </section>
<script>
	$(document).ready(function() {
			calldataplugin();
	});

	function calldataplugin(sessionKey)
	{

		 var ApiURL;
		  $.ajax({
				url : 'resources/ApiResourceURLcredential.properties',
				type : "GET",
				dataType : "json",
				success : function(response,textStatus,jqXHR) {
					ApiURL=  response.ApiResourceURL  
					 var formURL = ApiURL+"/Email/getHtmlTemplateById?orderid=${creditNoteData.creditNoteId}&emailType=24";  
		
	

		$.ajax({
			url : formURL,
			type : "GET",
			dataType : "html",
			success : function(data,textStatus,jqXHR) {
				if (jqXHR.status == 200) {					
					$("#display-api-content").html(data);
					setInterval(function() {
						$("#display-api-content").show();
					}, 1000); 
				} 
			},
			error : function(jqXHR,textStatus,errorThrown) {
				console.debug(jqXHR.status);
				$(".display-api-content").html("We could not get the content, please contact administrator.");
			}
		});
	}
		  }
		  );
		  }
	</script>
      <script type="text/javascript">
      
       function sendOfflineVoucherOrInvoiceToMail(type,orderId) {
        	var toEmails= null;
        	var ccEmails= null;
        	if(type=='CreditNote'){
				toEmails= $('#invoiceToEmails').val();
        		ccEmails= $('#invoiceFromEmails').val();
        	}
        	var txt1;
        	 
          $.ajax({	
	                url: 'sendOfflineVoucherOrInvoiceToMail',
	                type: 'post',
	                data:{type:type,orderId:orderId,toEmails:toEmails,ccEmails:ccEmails},
	                success:function(data){
	                if(data.statusMap.status=='CreditNote')
                	{ 
                	$("#invoiceForm").hide();
	                	$(".modal-footer").hide();
                	  txt1 = "Credit Note successfully sent to mail.Please check.";
                	  $("#invoiceForm")[0].reset(); 
                	$("#invoiceModal.modal-body").append( txt1);  
                	 setTimeout(function() {
                		 $('#invoiceModal').modal('hide');
                		 window.location.reload();
                		 
                	 },1000); 
                	 
                	}
                else{
                  txt1 = "We found some error......";
                	$("#invoiceModal.modal-body").append( txt1);
                	setTimeout(function() {$('#invoiceModal').modal('hide');}, 1000);
                  
                }
	           
	                }
	            });   
     		}
       function printDiv(divName) {
  	     var printContents = document.getElementById(divName).innerHTML;
  	     var originalContents = document.body.innerHTML; 
  	     document.body.innerHTML = printContents; 
  	     window.print(); 
  	     document.body.innerHTML = originalContents;
  	}
</script>
		<!-- Main content -->
		

	</div>
	<!-- /.row -->
	<!-- Main row -->


	<%@ include file="DashboardFooter.jsp"%>

	<!-- /.content -->
</body>
</html>
