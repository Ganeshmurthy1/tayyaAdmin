<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/load_company_emp_names.js"></script>
 <script src="js/user_names.js" type="text/javascript"></script>
 	<script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
<style>
.ui-autocomplete {
	height: auto;
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
<link rel="stylesheet" href="css/alert.css">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Payment Receivable List</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<form action="paymentReceivableList" method="post" id="filterform">
				<div class="clearfix">
					<div class="col-sm-12">
						<div class="form-group" id="user_form-group">
							<input type="hidden"
								value="<s:property value="%{#session.Company.company_userid}"/>"
								id="companyUserId"> <input type="hidden"
								value="<s:property value="%{#session.Company.Email}"/>"
								id="email"> <input type="hidden"
								value="<s:property value="%{#session.User.company_userid}"/>"
								id="user_id">

							<div class="col-sm-12">
								<div class="row">
									<div class="col-sm-4">
										<a class="btn btn-primary" role="button"
											data-toggle="collapse" href="#filters" aria-expanded="false"
											aria-controls="filters"> <i class="fa fa-filter"
											aria-hidden="true"></i> Filters
										</a>
									</div>

									<div class="col-sm-3  items">
										<div class="form-group clearfix">
											<div class="col-sm-6">
											 <select class="form-control" name="companyFilterPage.maxItems"
											id="maxItems"   onchange="this.form.submit()">
												<c:forEach var="maxItems" items="${companyFilterPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${companyFilterPage.maxItems != null && companyFilterPage.maxItems == maxItems}">
													 <c:choose>
													 <c:when
														test="${companyFilterPage.maxItems == -1}">
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

									<div class="col-sm-5 clearfix pull-right">
										<div class="add-new">
											<a class="btn btn-primary but btn-clean" href="goPayment">
												Add Payment<i class="fa fa-angle-right"></i>
											</a>
											<a class="btn btn-primary but btn-clean" href="myLedgerReport">
												Ledger Report<i class="fa fa-angle-right"></i>
											</a>
											<a class="btn btn-primary but btn-clean" href="showKnockOffList">
												KnockOff Report<i class="fa fa-angle-right"></i>
											</a>
										</div>
									</div>
									 
								</div>
								<div class="collapse filter-box" id="filters">
									<div class="well">
										<div class="panel-body">
											<!-- Filter of main info -->
											<div class=" filter-text col-sm-12 clearfix">
												<div class="row">
													<div class="date col-sm-12 clearfix">
														<div class="row">
															<div class="col-sm-3">
																<div class="form-group clearfix">
																	<label class="col-sm-12 control-label">Company
																		Name </label>
																	<div class="col-sm-12">
																		<input type="text" autocomplete="off"
																			class="form-control" id="companyName"
																			placeholder="company Name"
																			name="companyFilterPage.companyFilter.companyName"
																			value="<s:property value="companyFilterPage.companyFilter.companyName"/>">
																		<input type="hidden" autocomplete="off"
																			class="form-control"
																			name="companyFilterPage.companyFilter.companyId"
																			id="companyId"
																			value="<s:property value="companyFilterPage.companyFilter.companyId"/>">
																	</div>

																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group clearfix">
																	<label class="col-sm-12 control-label">Transaction
																		ID </label>
																	<div class="col-sm-12">
																		<input type="text" autocomplete="off"
																			class="form-control" id="orderId"
																			placeholder="Transaction ID"
																			name="companyFilterPage.companyFilter.orderId"
																			value="<s:property value="companyFilterPage.companyFilter.orderId"/>">
																	</div>

																</div>
															</div>

															<div class="col-sm-3">
																<div class="form-group clearfix">
																	<label class="col-sm-12 control-label">Received
																		By </label>
																	<div class="col-sm-12">
																		<input type="text" autocomplete="off"
																			class="form-control" id="userName"
																			placeholder="Received By"
																			name="companyFilterPage.companyFilter.userName"
																			value="<s:property value="companyFilterPage.companyFilter.userName"/>">
																	</div>

																</div>
															</div>

														</div>
													</div>

													<div class="date col-sm-12 clearfix">
														<div class="row">
															<div class="col-sm-4">
																<div class="form-group clearfix">
																	<label class="col-sm-12 control-label">Received
																		Date </label>
																	<div class="col-sm-6">
																		<input type="text" class="form-control" id="twodt1"
																			placeholder="From Date(dd-mm-yyyy)"
																			name="companyFilterPage.companyFilter.dateFilterCreated.dtStart"
																			value='<s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtStart"/>'>
																	</div>
																	<div class="col-sm-6">
																		<input type="text" class="form-control" id="twodt2"
																			placeholder="To Date(dd-mm-yyyy)"
																			name="companyFilterPage.companyFilter.dateFilterCreated.dtEnd"
																			value='<s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtEnd"/>'>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<%-- <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Employee
														Name</label>
													<div class="col-sm-12">
														<input type="text" class="form-control" autocomplete="on"
															id="userIdSearch" placeholder="emp user name"
															name="companyFilterPage.companyFilter.userName"
															value="<s:property value="companyFilterPage.companyFilter.userName"/>">
													</div>
												</div>
											</div> --%>

												</div>
											</div>
											<div class="col-sm-12">

												<div class="col-sm-6 clearfix cc-all">
													<a href="" id="reset" class="text-right"><i
														class="fa fa-close"></i> Clear All</a>
												</div>
 											<div class="text-right filtr-btn col-sm-6 clearfix">
											<button type="submit" class="btn btn-primary"
												name="companyFilterPage.currentPageIndex"
												value="${companyFilterPage.currentPageIndex}">Submit</button>
											</div>  
											</div>

										</div>
									</div>
								</div>
							</div>


						</div>
					</div>
				</div>

				<div class="table-responsive dash-table">
					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>Received Date</th>
								<th>Company</th>
								<th>Receivable Amount</th>
								<th>Transaction ID</th>
								<th>Received By</th>
								<th>Edit</th>
								<th>View</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="companyFilterPage.paymentReceivableList" status="rowCount">
								<tr>
								<td><s:property value="%{#rowCount.count}"/></td>
								<td><s:date  format="dd-MM-yyyy" name="receivedDate"/></td>
								<td> <s:property value="companyName"/></td>
								<td> <s:property value="amount"/></td>
									<td> <s:property value="referenceNumber"/></td>
								<td> <s:property value="receivedBy"/></td>
								 
								<td> <p data-placement="top" >
										<a
											href="paymentRecievableEdit?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" data-toggle="modal">
											 Edit
										</a></td>
										<td> <p data-placement="top" >
										<a
											href="paymentRecievableView?id=<s:property value="id"/>"
											class="btn btn-success btn-xs" data-toggle="modal">
											 View
										</a></td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
 							<table id="pagtable">
									<tr id="tr">
										<span>Showing <s:property
												value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+1}" />
											to <s:property
												value="%{((companyFilterPage.currentPageIndex*companyFilterPage.maxItems) <= companyFilterPage.availableItems)?(companyFilterPage.currentPageIndex*companyFilterPage.maxItems):companyFilterPage.availableItems}" />
											of <s:property value="%{companyFilterPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">

										<c:if test="${companyFilterPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="companyFilterPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="companyFilterPage.currentPageIndex"
													value="${companyFilterPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach begin="${(companyFilterPage.currentPageIndex) > 1? (companyFilterPage.currentPageIndex) : 1}"
											end="${(companyFilterPage.currentPageIndex + 4) <= companyFilterPage.availablePages ? (companyFilterPage.currentPageIndex + 4) :  companyFilterPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="companyFilterPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${companyFilterPage.currentPageIndex == i}">
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
											test="${(companyFilterPage.currentPageIndex + 4) < companyFilterPage.availablePages}">
											<td id="td"><button type="submit"
													name="companyFilterPage.currentPageIndex"
													value="${companyFilterPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="companyFilterPage.currentPageIndex"
													value="${companyFilterPage.availablePages}"
													class="btn btn-primary">Last</button></td>
										</c:if>

									</tr>
								</table> 
				</div>

			</form>

		</div>
	</section>
</div>
 
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
<script type="text/javascript" src="js/reset-form.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#twodt1").keyup(function () {
		  $(this).next("#twodt2").focus();
		});
		 
		 $('#twodt1').datepicker({
			 //showTimePicker: false,
			dateFormat : "dd-mm-yy",
		changeMonth : true,
		changeYear : true,
			 onSelect: function( selectedDate ) {
			    	var date2 = $("#twodt1").datepicker('getDate', '+1d'); 
			  	  date2.setDate(date2.getDate() +1 ); 
			  	  $( "#twodt2" ).datepicker('setDate', date2);
			  	$( "#twodt2" ).datepicker( "option", "minDate", date2 ); 
			    },
			  onClose: function() {
			      $("#twodpd22").focus();
			  }
			  
		  });
	          
	     
	 
	 $('#twodt2').datepicker({
		 //timepicker: false,
		/*  changeMonth: true,   
    	 changeYear:true, */
    	 dateFormat : "dd-mm-yy",
			changeMonth : true,
			changeYear : true,
       	 minDate:0,
         onSelect: function(selectedDate,i) { 
        	// $("#fromDate").datepicker("option", selected)
              var date2 = $("#twodt2").datepicker('getDate', '+1d'); 
             
         }
	 
	 });

});
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "superUserCompanyList";
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
	$(function() {
		/*   $("#mytable1").DataTable(); */
		$('#mytable').DataTable({
			"paging" : false,
			"lengthChange" : false,
			"searching" : false,
			"ordering" : true,
			"info" : false,
			"autoWidth" : false,
			"search" : {
				"regex" : true,
			},
			"pagingType" : "full_numbers",
			"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],

		});
	});
</script>

<script>
	
	
	$(document).ready(function(){  
	    $("#addWalletForm").validate({
	    	 ignore: false,  
	        submitHandler: function (form) {   
	            return false;  
	        },
	 
	        highlight: function(element, errorClass, validClass) {  
	            $(element).addClass(errorClass).removeClass(validClass);
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	          },
	          success: function(element) { 
	         	element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            $(element).remove();
	          }
	    });
	    
	    $('#buttonSubmit').click(function() { 
	  	if($("#addWalletForm").valid())  
	    	 document.getElementById("addWalletForm").submit();
	  	else
	  		alert("Please Fill Required Feilds")
	    });    
	    
	});
	
	</script>





