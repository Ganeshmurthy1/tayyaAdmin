<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
  
 
<title><s:property value="user" /></title>
 
<link rel="stylesheet" href="css/alert.css">

	<link rel="stylesheet" type="text/css" href="print-pdf-excel/print-resource/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="print-pdf-excel/print-resource/buttons.dataTables.min.css">
	 <link rel="stylesheet" type="text/css" href="print-pdf-excel/print-resource/jquery.dataTables.min.css">
	<script type="text/javascript" async="" src="print-pdf-excel/print-resource/ga.js.download"></script>
	<%-- <script type="text/javascript" src="print-pdf-excel/print-resource/site.js.download">
	</script> --%>
	<%-- <script type="text/javascript" language="javascript" src="print-pdf-excel/print-resource/jquery-1.12.3.js.download">
	</script> --%>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/jquery.dataTables.min.js.download">
	</script>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/dataTables.buttons.min.js.download">
	</script>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/buttons.flash.min.js.download">
	</script>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/jszip.min.js.download">
	</script>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/pdfmake.min.js.download">
	</script>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/vfs_fonts.js.download">
	</script>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/buttons.html5.min.js.download">
	</script>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/buttons.print.min.js.download">
	</script>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/demo.js.download">
	</script>
	<script type="text/javascript"   src="print-pdf-excel/print-resource/buttons.colVis.min.js">
	</script>
<script type="text/javascript">
  function  updateKnockOff(rowId){
	  var totInvoiceAmount=$('#'+rowId+'totInvoiceAmount').val();
	  var receivedAmount=$('#'+rowId+'receivedAmount').val();
		console.log("totInvoiceAmount..."+totInvoiceAmount);
		console.log("receiveAmount..."+receivedAmount);
    	  var totUrl = $(location).attr('href');
  		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
  		var finalUrl=newUrl+"updateKnockOff";
  	  	console.log("finalUrl..."+finalUrl);
  	  $('#'+rowId+'loader').show();
  		 $.ajax({
				    method: "POST",
				    url:finalUrl,
				    data: {travelType:'V',rowId:rowId,totInvoiceAmount:totInvoiceAmount,receiveAmount:receivedAmount},
				    success:function(data,status)
					{ 
				        $.each(data, function(index, element) {
				    		  console.log("data-------"+element.status);
						     	if(element.status=="success"){  
						     		$('#'+rowId+'loader').hide();
						     		$('#'+rowId+'result').show();
						     		$('#'+rowId+'knockOffBut').hide();
				        			}
						    	 
						    	else if(element.status=="fail") 
						    		$('#'+rowId+'loader').show();
					    		 
				    	 });
				     },
					error: function(xhr, status, error)
					{
						$('#'+rowId+'loader').show();
					   console.log("Error----------"+error);
					}
				});  
    	   }
      
  </script> 
<style type="text/css">
.ui-autocomplete {
	max-height: 200px;
	width: auto;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: auto;
	font-family: "Trebuchet MS", "Helvetica", "Arial", "Verdana",
		"sans-serif";
	font-size: 1em;
	/* add padding to account for vertical scrollbar */
}
/* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
* html .ui-autocomplete {
	height: 200px;
	width: auto;
}

.items label {
	margin-top: 5px;
}
 
</style>
<style>
#pagtable {
	border-collapse: collapse;
	width: auto;
	float: right;
}

#th, #td {
	text-align: left;
	padding: 8px;
}
</style>
<script type="text/javascript">
		$(function() {
		  var  reportType=document.getElementById('reportTypeHidden').value;
			 document.getElementById('reportType').value = reportType;
		});
	</script>
<script src="js/load_company_emp_names.js"></script>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Visa KnockOff Reports</h1>
	<div class="sccuss-full-updated" id="success-alert"
				style="display: none">
				<div class="succfully-updated clearfix">

					<div class="col-sm-2">
						<i class="fa fa-check fa-3x"></i>
					</div>

					<div id="message" class="col-sm-10"></div>
					<button type="button" id="success" class="btn btn-primary">Ok</button>
				</div>

			</div>
		</section>
		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<!-- searchCompanyReportsList -->
			<div class="row">
			<!-- <input type="button" id="downloadExcel" value="downloadExcel"> -->
				<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">

				<form action="showKnockOffList" method="post" id="filterform">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-8">
								<a class="btn btn-primary" role="button" data-toggle="collapse"
									href="#filters" aria-expanded="false" aria-controls="filters">
									<i class="fa fa-filter" aria-hidden="true"></i> Filters
								</a>
							</div>

							<div class="col-sm-4 pull-right items">
								<div class="form-group clearfix">

									<div class="col-sm-6">
										<select class="form-control" name="knockOffPage.maxItems"
											id="maxItems" required onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${knockOffPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${knockOffPage.maxItems != null && knockOffPage.maxItems == maxItems}">
													 <c:choose>
													 <c:when
														test="${knockOffPage.maxItems == -1}">
													 <option value="${maxItems}" selected="selected">ALL</option>
													</c:when>
													 
													<c:otherwise>
														<option value="${maxItems}" selected="selected">${maxItems}</option>
													</c:otherwise>
													</c:choose>
													</c:when>
													 
													<c:otherwise>
													 <c:choose>
													 <c:when
														test="${maxItems == -1}">
														<option value="${maxItems}">ALL</option>
													</c:when>
													 
													<c:otherwise>
														<option value="${maxItems}">${maxItems}</option>
													</c:otherwise>
													</c:choose>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									<label class="col-sm-5 control-label text-left">Items
										Per Page </label>

								</div>
							</div>

						</div>


						<div class="collapse filter-box" id="filters">
							<div class="well">
								<div class="panel-body">

									<!-- Filter of main info -->
									<div class=" filter-text col-sm-12 clearfix">
										<div class="row">
											<%-- <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">PNR</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" value="<s:property value="knockOffPage.knockOffFilter.pnr"/>"
															placeholder="type pnr" autocomplete="off"    name="knockOffPage.knockOffFilter.pnr"  id="pnr">
													</div>
												</div>
											</div>   --%>
											<%-- <s:if test="%{(#session.Company.companyRole.isDistributor()==false && #session.Company.companyRole.isAgent()==false && #session.Company.companyRole.isCorporate()==false) || #session.Company.companyRole.isDistributor() || #session.Company.companyRole.isAgent()}">
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Company Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="on" class="form-control"
															id="companyName" placeholder="type company Name"
															name="knockOffPage.knockOffFilter.companyName"
															value="<s:property value="knockOffPage.knockOffFilter.companyName"/>">
															<input type="hidden"  id="companyId"  name="knockOffPage.knockOffFilter.companyId"     value="<s:property value="knockOffPage.knockOffFilter.companyId"/>">
															 
													</div>
												</div>
											</div>
										</s:if>		 --%>


											<%-- <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Employee
														Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="on"
															id="agentName" placeholder="type emp user id"
															name="knockOffPage.knockOffFilter.userName"
															value="<s:property value="knockOffPage.knockOffFilter.userName"/>">
															<input type="hidden"  id="userId"  name="knockOffPage.knockOffFilter.userId"     value="<s:property value="knockOffPage.knockOffFilter.userId"/>">
															
													</div>
												</div>
											</div> --%>
											<%-- <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Airline Name</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="knockOffPage.knockOffFilter.airlineName"
															id="airlinename" required>
															<option value="ALL">ALL</option>
															<s:iterator value="airlineList">

																<s:if
																	test="knockOffPage.knockOffFilter.airlineName != null && knockOffPage.knockOffFilter.airlineName == airlinename">
																	<option value="<s:property value="airlinename"/>"
																		selected="selected"><s:property
																			value="airlinename"></s:property></option>
																</s:if>
																<s:else>
																	<option value="<s:property value="airlinename"/>"><s:property
																			value="airlinename"></s:property></option>
																</s:else>
															</s:iterator>
														</select>

													</div>
												</div>
											</div>
 											--%>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Travel Type</label>
													<div class="col-sm-12">
														<select class="form-control"
															name="knockOffPage.knockOffFilter.travelType"
															id="travelType" required>
															<c:forEach var="travelTypeItem"
																items="${knockOffPage.knockOffFilter.travelTypeList}">
																<c:choose>
																	<c:when
																		test="${knockOffPage.knockOffFilter.travelType != null && knockOffPage.knockOffFilter.travelType == travelTypeItem}">
																		<option value="${travelTypeItem}" selected="selected">${travelTypeItem}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${travelTypeItem}">${travelTypeItem}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Knock Off</label> <select
														class="form-control"
														name="knockOffPage.knockOffFilter.knockOff" id="knockOff">
														<s:if test="knockOffPage.knockOffFilter.knockOff=='true'">
															<option value="ALL">ALL</option>
															<option value="true" selected="selected">YES</option>
															<option value="false">NO</option>
														</s:if>
														<s:if test="knockOffPage.knockOffFilter.knockOff=='false'">
																<option value="ALL">ALL</option>
															<option value="true">YES</option>
															<option value="false"  selected="selected">NO</option>
														</s:if>
													 <s:if test="knockOffPage.knockOffFilter.knockOff=='ALL'">
															<option value="ALL" selected="selected">ALL</option>
															<option value="true">YES</option>
															<option value="false">NO</option>
														</s:if>
													 
														 
													
													</select>
												</div>
											</div>
										</div>
									</div>

<%-- 
									<div class="date  clearfix">
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">From Date</label>
													 
														<input type="text" class="form-control" id="twodt1"
															placeholder="From Date(dd-mm-yyyy)"
															name="knockOffPage.knockOffFilter.dateFilterBooking.dtStart"
															value='<s:property value="knockOffPage.knockOffFilter.dateFilterBooking.dtStart"/>'>
													  
												</div>
												 
											</div>
											
											<div class="col-sm-3">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">To Date</label>
													 
														<input type="text" class="form-control" id="twodt2"
															placeholder="To Date(dd-mm-yyyy)"
															name="knockOffPage.knockOffFilter.dateFilterBooking.dtEnd"
															value='<s:property value="knockOffPage.knockOffFilter.dateFilterBooking.dtEnd"/>'>

													 
												</div>
												 
											</div>
 
										</div>
									</div> --%>
 
									<div class="col-sm-12">

										<div class="col-sm-6 clearfix cc-all">
											<a href="" id="reset" class="text-right"><i
												class="fa fa-close"></i> Clear All</a>
										</div>
										<div class="text-right filtr-btn col-sm-6 clearfix">
											<button type="submit" class="btn btn-primary"
												name="knockOffPage.currentPageIndex"
												value="${knockOffPage.currentPageIndex}">Submit</button>
										</div>
									</div>
 
								</div>
							</div>
						</div>
					</div>
					<!-- Filter ends here.... -->
					<div class="col-sm-12 clearfix report-search">
						<div class="table-responsive dash-table">
							<!-- testing -->
							<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Booking Created Date</th>
											<th>Order ID</th>
											<th>Booking Status</th>
											<th>Final Invoice Amount</th>
											<th>Refund Amount</th>
											
										</tr>
									</thead>
									<tbody>
									 <s:if test="knockOffPage.visaRows !=null && knockOffPage.visaRows.size()>0">
									 <s:iterator value="knockOffPage.visaRows" status="rowCount">
											<tr>
												<td><s:property value="%{((knockOffPage.currentPageIndex - 1)*knockOffPage.maxItems)+#rowCount.count}"/></td>
												<td><s:date name="createdAt" format="dd/MM/yyyy"></s:date></td>
												<td><s:property value="orderId"/></td>
												<td><s:property value="statusAction"/></td>
												<s:if test="KnockOff==true">
												<td> <s:property value="getText('{0,number,#,##0.00}',{totInvoiceAmount})"/></td>
												<td><s:property value="getText('{0,number,#,##0.00}',{recievedAmount})"/></td>
												<td> <i class="fa fa-check-circle success" style="font-size:20px;"></i></td>
												</s:if>
												 <s:else>
												 <td><input type="text" id="<s:property value="id"/>totInvoiceAmount"    value="<s:property value="getText('{0,number,#,##0.00}',{totInvoiceAmount})"/>"></td>
												<td><input type="text" id="<s:property value="id"/>receivedAmount"   value="<s:property value="getText('{0,number,#,##0.00}',{recievedAmount})"/>"> </td>
												 <td><input type="button"  id="<s:property value="id"/>knockOffBut"    value="Knock Off" onclick="updateKnockOff('<s:property value="id"/>')"><span id="<s:property value="id"/>loader" style="display:none" ><i class="fa fa-spinner fa-spin" style="font-size:15px"></i></span><span id="<s:property value="id"/>result" style="display:none"   ><i class="fa fa-check-circle success" style="font-size:20px;"></i></span></td>
												 </s:else>
												</tr> 
												 
										</s:iterator>
									 </s:if>
									 
									 <s:else>
									 <tr>
									 <td colspan="6">No Data</td>
									 </tr>
									 </s:else>
									</tbody>
								</table>
								<table id="pagtable">
									<tr id="tr">
										<span>Showing <s:property
												value="%{((knockOffPage.currentPageIndex - 1)*knockOffPage.maxItems)+1}" />
											to <s:property
												value="%{((knockOffPage.currentPageIndex*knockOffPage.maxItems) <= knockOffPage.availableItems)?(knockOffPage.currentPageIndex*knockOffPage.maxItems):knockOffPage.availableItems}" />
											of <s:property value="%{knockOffPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">

										<c:if test="${knockOffPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="knockOffPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="knockOffPage.currentPageIndex"
													value="${knockOffPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach
											begin="${(knockOffPage.currentPageIndex) > 1? (knockOffPage.currentPageIndex) : 1}"
											end="${ (knockOffPage.currentPageIndex + 4) <= knockOffPage.availablePages ? (knockOffPage.currentPageIndex + 4) :  knockOffPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="knockOffPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${knockOffPage.currentPageIndex == i}">
															<u>${i}</u>
														</c:when>

														<c:otherwise>
									${i}								
								</c:otherwise>
													</c:choose>
												</button>
											</td>
										</c:forEach>
										<c:if
											test="${(knockOffPage.currentPageIndex + 4) < knockOffPage.availablePages}">
											<td id="td"><button type="submit"
													name="knockOffPage.currentPageIndex"
													value="${knockOffPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="knockOffPage.currentPageIndex"
													value="${knockOffPage.availablePages}"
													class="btn btn-primary">Last</button></td>
										</c:if>

									</tr>
								</table>
							</div>
						</div>
						</div>
				</form>
			</div>
			</section>
	</div>
	 

	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript"  src="js/reset-form.js"></script>
	<script>
		$(document).ready(function() {
			$("#twodt2").datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth:true,
				changeYear:true
			});
			$("#twodt1").datepicker({
				dateFormat : "dd-mm-yy",
					changeMonth:true,
					changeYear:true
			 
			});
			$("#arrivalDate1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			$("#arrivalDate2").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			
			$("#departureDate1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			$("#departureDate2").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
			 
		});
	</script>
	<script type="text/javascript">/* , 'pdf', 'print'  */
		$(document).ready(
				function() {
					var currentDate = "export-data_"+$.datepicker.formatDate('yyyy-mm-dd', new Date())+(new Date).getTime();
					$('#example').DataTable( {
						dom: 'Bfrtip',
						"paging" : false,
						"info" : false,
						"searching" : false,
						 
					buttons: [
					            {
					                extend: 'csv',
					                exportOptions: {
					                	columns: "thead th:not(.noExport)"
					                },
					                filename: currentDate
					            },
					            'colvis'
					        ]
						        
					} );

				});
 
	</script>
	<script type="text/javascript">
		$(function() {
			/*  $('#row_dim').hide();  */
			$('#user').change(function() {
				//alert($('#user').val());
				if ($('#user').val() == 'ALL') {
					$('#company_form-group').hide();
				} else if ($('#user').val() == '0') {
					$('#company_form-group').show();

				} else {
					$('#company_form-group').hide();
				}
			});

			$('#companyName').change(function() {
				//alert($('#companyName').val());
				if ($('#companyName').val() == 'ALL') {
					$('#user_form-group').hide();
				} else if ($('#companyName').val() == '0') {
					$('#user_form-group').show();

				} else {
					$('#user_form-group').hide();
				}
			});
			 
			 
		});
	</script>
		<script>
	 $('spna[data-toggle="tooltip"]').tooltip({
    animated: 'fade',
    placement: 'bottom',
});
	</script>
	 
</body>
</html>