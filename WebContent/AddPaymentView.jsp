
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Receivable View</title>
</head>
<body>
 
   <div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
				Payment Receivable View
				</h1>
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
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Received By </p>
							</div>
							<div class="p-inp">
								<p>
									${paymentRecievable.receivedBy}
								</p>
							</div>
						</div>

					<div class="p-info clearfix">
							<div class="p-label">
								<p>Received Date </p>
							</div>
							<div class="p-inp">
								<p>
									${paymentRecievable.transReceivedDate}
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Company</p>
							</div>
							<div class="p-inp">
								<p>
									${paymentRecievable.companyName}
								</p>
							</div>
						</div>
						 
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Payment</p>
							</div>
							<div class="p-inp">
								<p>
									${paymentRecievable.amount}
								</p>
							</div>
						</div>
						
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Payment Period</p>
							</div>
							<div class="p-inp">
								<p>
									From : ${paymentRecievable.transFromDt} &nbsp&nbsp&nbsp&nbsp  To : ${paymentRecievable.transToDt}
								</p>
							</div>
						</div>
						
					</div>
					<!-- edit form column -->
					<div class="col-md-6 col-sm-6 col-xs-12 personal-info">
						 
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Transaction ID</p>
							</div>
							<div class="p-inp">
								<p>
									 ${paymentRecievable.referenceNumber} 
								</p>
							</div>
						</div>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Payment Mode</p>
							</div>
							<div class="p-inp">
							<s:if test="paymentRecievable.paymentMode=='JV'">
							<p>
								 Journal Voucher
								</p>
							</s:if>
							<s:else>
							<p>
								 ${paymentRecievable.paymentMode} 
								</p>
							</s:else>
								
							</div>
						</div>
						<s:if test="paymentRecievable.paymentMode!='Cash'">
							<div class="p-info clearfix">
							<div class="p-label">
								<p>Reference Number</p>
							</div>
							
							<div class="p-inp">
								<p>
									${paymentRecievable.referenceNumber} 
								</p>
							</div>
							
							
						</div>
						 </s:if>
						<div class="p-info clearfix">
							<div class="p-label">
								<p>Description </p>
							</div>
							<div class="p-inp">
								<p>
								${paymentRecievable.description} 
								</p>
							</div>
						</div>
						 
 						<div class="p-info clearfix">
							<div class="p-label">
								<p>Payment Type</p>
							</div>
							<div class="p-inp">
								<p>
									${paymentRecievable.paymentType}
								</p>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
  
 
  <%@ include file="DashboardFooter.jsp"%>
	<%--   	<%@ include file="DashFooter.jsp"%> --%>
</body>
</html>
