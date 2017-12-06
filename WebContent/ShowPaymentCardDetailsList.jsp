<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
</script> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/pagination_css.css">
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<div class="sccuss-full-updated" id="success-alertdelete"
		style="display: none">
		<div class="succfully-updated clearfix">

			<div class="col-sm-2">
				<i class="fa fa-check fa-3x"></i>
			</div>

			<div class="col-sm-10" id="messagedelete"></div>
			<button type="button" id="successdelete" class="btn btn-primary">Ok</button>

		</div>
	</div>

	<section class="content-header">
		<h1>Card Detail List</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
			<li class="active">Dashboard</li>
		</ol> -->
	</section>

	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->

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

		<div class="row">
			<form action="PaymentCardList" method="post" id="filterform">
			<div class="clearfix">
		<div class="col-sm-12">
			<div class="form-group" id="user_form-group" >
				 
				 		<div class="col-sm-12">

						<div class="row">
							<div class="col-sm-4">
								<a class="btn btn-primary" role="button" data-toggle="collapse"
									href="#filters" aria-expanded="false" aria-controls="filters">
									<i class="fa fa-filter" aria-hidden="true"></i> Filters
								</a>
							</div>

							<div class="col-sm-5  items">
								<div class="form-group clearfix">

									<div class="col-sm-6">
										<select class="form-control" name="commonConfigPage.maxItems"
											id="maxItems"   onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${commonConfigPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${commonConfigPage.maxItems != null && commonConfigPage.maxItems == maxItems}">
														<option value="${maxItems}" selected="selected">${maxItems}</option>
													</c:when>
													<c:otherwise>
														<option value="${maxItems}">${maxItems}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									<label class="col-sm-5 control-label text-left">Items
										Per Page </label>

								</div>
							</div>
							
							<div class="col-sm-3 clearfix pull-right">
								<div class="add-new">
									<a class="btn btn-primary but btn-clean" style="border: 2px solid #a1a1a1;border-radius: 15px" role="button"
										href="addPamentInfo"> <i class="fa fa-plus "> Add New </i>
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
											 <div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Card Holder Name
															</label>
															<div class="col-sm-12">
																<select class="form-control" name="commonConfigPage.commonConfigFilter.id"
																		id="country">

																	<option value="ALL">ALL</option>
																	 
																	  <c:forEach var="roleItem" items="${paymentCardDetailsList}">
																		<c:choose>
																			<c:when test="${commonConfigPage.commonConfigFilter.id!= null }">
																			<option value="${roleItem.id}" selected="selected">${roleItem.userName}(${roleItem.cardNumber})</option>
																			</c:when>
																			<c:otherwise>
																			<option value="${roleItem.id}">${roleItem.userName}(${roleItem.cardNumber})</option>
																			</c:otherwise>
 
																		</c:choose>
																	</c:forEach>  
																</select>
															</div>
														</div>
													</div>
						 
						 							<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Bank Name
															</label>
															<div class="col-sm-12">
																<select class="form-control"
																 	name="commonConfigPage.commonConfigFilter.bankName"  
																	id="country">

																	<option value="ALL">ALL</option>
																	   <c:forEach var="roleItem" items="${paymentCardDetailsList}">
																		<c:choose>
																			<c:when
																				test="${commonConfigPage.commonConfigFilter.bankName!= null && commonConfigPage.commonConfigFilter.bankName == roleItem.bankName}">
																				<option value="${roleItem.bankName}" selected="selected">${roleItem.bankName}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${roleItem.bankName}">${roleItem.bankName}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach> 
																</select>
															</div>
														</div>

													</div>
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Payment type
															</label>
															<div class="col-sm-12">
																<select class="form-control"
																 	name="commonConfigPage.commonConfigFilter.paymentType"  
																	id="country">

																	<option value="ALL">ALL</option>
																	  
																		<c:choose>
																			<c:when
																				test="${commonConfigPage.commonConfigFilter.paymentType=='true'}">
																				<option value="false">Client</option>
																				<option value="${commonConfigPage.commonConfigFilter.paymentType}" selected="selected">Supplier</option>
																			</c:when>
																			<c:when
																				test="${commonConfigPage.commonConfigFilter.paymentType=='false'}">
																				<option value="${commonConfigPage.commonConfigFilter.paymentType}" selected="selected">Client</option>
																				<option value="true">Supplier</option>
																			</c:when>
																			<c:otherwise>
																				<option value="false">Client</option>
																				<option value="true">Supplier</option>
																			</c:otherwise>
																		</c:choose>
																	
																</select>
															</div>
														</div>

													</div>
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Email Id
															</label>
															<div class="col-sm-12">
																<select class="form-control"
																 	name="commonConfigPage.commonConfigFilter.mailId"  
																	id="country">

																	<option value="ALL">ALL</option>
																	   <c:forEach var="roleItem"
																		items="${paymentCardDetailsList}">
																		<c:choose>
																			<c:when
																				test="${commonConfigPage.commonConfigFilter.mailId!= null && commonConfigPage.commonConfigFilter.mailId == roleItem.mailId}">
																				<option value="${roleItem.mailId}" selected="selected">${roleItem.mailId}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${roleItem.mailId}">${roleItem.mailId}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach> 
																</select>
															</div>
														</div>

													</div>
													  
						 						</div>
											</div>
							  <div class="col-sm-12">
										<div class="col-sm-6 clearfix cc-all">
											<a href="" id="reset" class="text-right"><i
												class="fa fa-close"></i> Clear All</a>
										</div>
										<div class="text-right filtr-btn col-sm-6 clearfix">
											<button type="submit" class="btn btn-primary"
												name="commonConfigPage.currentPageIndex"
												value="${commonConfigPage.currentPageIndex}">Submit</button>
										</div>
									</div>
 
								</div>
							</div>
						</div>
					</div>
				
				 
			</div>
			</div>
			 </div>
			
			
				<%-- <div class="clearfix">
					<div class="col-sm-12">
						<div class="form-group" id="user_form-group">

							<div class="col-sm-12">

								<div class="row">
									 <div class="col-sm-4">
										<a class="btn btn-primary" role="button"
											data-toggle="collapse" href="#filters" aria-expanded="false"
											aria-controls="filters"> <i class="fa fa-filter"
											aria-hidden="true"></i> Filters
										</a>
									</div>
									
									<div class="col-sm-5  items">
										<div class="form-group clearfix">
											<div class="col-sm-6">
												<select class="form-control"
													name="maxPageToDisplay" id="maxItems"
													onchange="this.form.submit()">
													<c:forEach var="maxItems"
														items="${paginationArray}">
														<c:choose>
															<c:when
																test="${maxPageToDisplay != null && maxPageToDisplay == maxItems}">
																<option value="${maxItems}" selected="selected">${maxItems}</option>
															</c:when>
															<c:otherwise>
																<option value="${maxItems}">${maxItems}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
											<label class="col-sm-5 control-label text-left">Items
												Per Page </label>

										</div>
									</div> 
									<div class="col-sm-3 clearfix pull-right">
										<div class="add-new">
											<a class="btn btn-primary but btn-clean" style="border: 2px solid #a1a1a1;border-radius: 15px" role="button"
											 href="addPamentInfo"> <i class="fa fa-plus "> Add New </i>
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
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Card Holder Name
															</label>
															<div class="col-sm-12">
																<select class="form-control" name="cardHolderName"
																		id="country">

																	<option value="ALL">ALL</option>
																	 
																	  <c:forEach var="roleItem" items="${paymentCardDetailsConfigList}">
																		<c:choose>
																			<c:when
																				test="${filterCardHolderName!= null && filterCardHolderName == roleItem.userName}">
																				<option value="${roleItem.userName} ">${roleItem.userName}${roleItem.cardNumber}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${roleItem.userName}">${roleItem.userName}${roleItem.cardNumber}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>  
																</select>
															</div>
														</div>

													</div>
													
													
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Bank Name
															</label>
															<div class="col-sm-12">
																<select class="form-control"
																 	name="filterBankName"  
																	id="country">

																	<option value="ALL">ALL</option>
																	   <c:forEach var="roleItem" items="${paymentCardDetailsConfigList}">
																		<c:choose>
																			<c:when
																				test="${filterBankName!= null && filterBankName == roleItem.bankName}">
																				<option value="${roleItem.bankName}" selected="selected">${roleItem.bankName}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${roleItem.bankName}">${roleItem.bankName}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach> 
																</select>
															</div>
														</div>

													</div>
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Payment type
															</label>
															<div class="col-sm-12">
																<select class="form-control"
																 	name="filterPaymentType"  
																	id="country">

																	<option value="ALL">ALL</option>
																	  
																		<c:choose>
																			<c:when
																				test="${filterPaymentType!= null &&  filterPaymentType == roleItem.paymentType}">
																				<option value="0" selected="selected">Client</option>
																				<option value="1">Supplier</option>
																			</c:when>
																			<c:otherwise>
																				<option value="0">Client</option>
																				<option value="1">Supplier</option>
																			</c:otherwise>
																		</c:choose>
																	
																</select>
															</div>
														</div>

													</div>
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Email Id
															</label>
															<div class="col-sm-12">
																<select class="form-control"
																 	name="filterMailId"  
																	id="country">

																	<option value="ALL">ALL</option>
																	   <c:forEach var="roleItem"
																		items="${paymentCardDetailsConfigList}">
																		<c:choose>
																			<c:when
																				test="${filterMailId!= null && filterMailId == roleItem.mailId}">
																				<option value="${roleItem.mailId}" selected="selected">${roleItem.mailId}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${roleItem.mailId}">${roleItem.mailId}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach> 
																</select>
															</div>
														</div>

													</div>
													 </div>


												<div class="col-sm-12">
													<div class="col-sm-6 clearfix cc-all">
														<a href="" id="reset" class="text-right"><i
															class="fa fa-close"></i> Clear All</a>
													</div>
													<div class="text-right filtr-btn col-sm-6 clearfix">
														<button type="submit" class="btn btn-primary"
															name="filterCurrentPageIndex" onclick="PaymentCardList"
															value="filter">Submit</button>
													</div>
												</div>

											</div>
										</div>
									</div>
								</div> 
								
							</div>
						</div>
					</div>

				</div>
 --%>
				<div class="table-responsive dash-table">
					<table  id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">

						<thead>
							<tr>
								<th>S.No</th>
								<th>CardHolderName</th>
								<th>BankName</th>
								<th>PaymentType</th>
								<th>CardNumber</th>
								<th>CardType</th>
								<th>Contact</th>
								<th>ExpiryDate</th>
								<th>Mail</th>
								<th>Action</th>

							</tr>
						</thead>
						<tbody>
							<s:iterator value="commonConfigPage.paymentCardDetailsConfigList" status="rowCount">
								<tr>
									 <td><s:property
											value="%{((commonConfigPage.currentPageIndex - 1)*commonConfigPage.maxItems)+#rowCount.count}" /></td>	
									<td><s:property value="userName" /></td>
									<td><s:property value="bankName" /></td>
									<td><s:property value="TransPaymentType"/></td>
									<td><s:property value="cardNumber" /></td>
									<td><s:property value="cardType" /></td>
									<td><s:property value="contactNumber" /></td>
									<td><s:property value="expiryDate" /></td>
									<td><s:property value="mailId" /></td>
									<td>
										<p data-placement="top" title="edit">

											<a
												href="EditPamentCardInfo?id=<s:property value="id"/>" style="border: 1px solid #a1a1a1;border-radius: 3px;" role="button"
												class="btn btn-success btn-xs" data-toggle="modal" > <strong> Edit</strong>
											</a>
										</p>
									</td>

									<td>
										<p data-placement="top" title="Delete">
											<a href="javascript:void(0)" style="border: 1px solid #a1a1a1;border-radius: 3px; font-size: 15px;" role="button"
												class="btn btn-danger btn-xs fa fa-trash"
												data-title="Delete" data-toggle="modal"
												data-target="#delete<s:property value="id"/>"
												onclick="userDelete('<s:property value="id"/>')">
											</a>
										</p>


										<div class="modal fade"
											id="delete<s:property value="id"/>" tabindex="-1"
											role="dialog" aria-labelledby="edit" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">
															<span class="glyphicon glyphicon-remove"
																aria-hidden="true"></span>
														</button>
														<h4 class="modal-title custom_align" id="Heading">Delete
															this entry</h4>
													</div>
													<div class="modal-body">

														<div class="alert alert-danger">
															<span class="glyphicon glyphicon-warning-sign"></span>
															Are you sure you want to delete this Record?
														</div>

													</div>
													<div class="modal-footer ">
														<button type="button" class="btn btn-success"
															id="yes<s:property value="id"/>">
															<span class="glyphicon glyphicon-ok-sign"></span> Yes
														</button>
														<button type="button" class="btn btn-default"
															data-dismiss="modal">
															<span class="glyphicon glyphicon-remove"></span> No
														</button>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div>


									</td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					<!-- footer pagination buttons -->
						<table id="pagtable">
									<tr id="tr">
										<span>Showing <s:property
												value="%{((commonConfigPage.currentPageIndex - 1)*commonConfigPage.maxItems)+1}" />
											to <s:property
												value="%{((commonConfigPage.currentPageIndex*commonConfigPage.maxItems) <= commonConfigPage.availableItems)?(commonConfigPage.currentPageIndex*commonConfigPage.maxItems):commonConfigPage.availableItems}" />
											of <s:property value="%{commonConfigPage.availableItems}" />
											items
										</span>

									</tr>
									<tr id="tr">
										<c:if test="${commonConfigPage.currentPageIndex>1}">
											<td id="td"><button type="submit"
													name="commonConfigPage.currentPageIndex" value="1"
													class="btn btn-primary">First</button></td>
											<td id="td"><button type="submit"
													name="commonConfigPage.currentPageIndex"
													value="${commonConfigPage.currentPageIndex - 1}"
													class="btn btn-primary">Prev</button></td>
										</c:if>

										<c:forEach
											begin="${(commonConfigPage.currentPageIndex) > 1? (commonConfigPage.currentPageIndex) : 1}"
											end="${ (commonConfigPage.currentPageIndex + 4) <= commonConfigPage.availablePages ? (commonConfigPage.currentPageIndex + 4) :  commonConfigPage.availablePages }"
											var="i">
											<td>
												<button type="submit"
													name="commonConfigPage.currentPageIndex" value="${i}"
													class="btn btn-primary">
													<c:choose>
														<c:when test="${commonConfigPage.currentPageIndex == i}">
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
											test="${(commonConfigPage.currentPageIndex + 4) < commonConfigPage.availablePages}">
											<td id="td"><button type="submit"
													name="commonConfigPage.currentPageIndex"
													value="${commonConfigPage.currentPageIndex + 1}"
													class="btn btn-primary">Next</button></td>
											<td id="td"><button type="submit"
													name="commonConfigPage.currentPageIndex"
													value="${commonConfigPage.availablePages}"
													class="btn btn-primary">Last</button></td>
										</c:if>

									</tr>
								</table>
				 
					
					
					
					
					
					
					
					
					
					
					
					
			<%-- 	    <table id="pagtable">
						<tr id="tr">
							<span>Showing <s:property
									value="%{((currentPageIndex - 1)*maxPageToDisplay)+1}" />
								to <s:property
									value="%{((currentPageIndex*maxPageToDisplay) <= availableItems)?(currentPageIndex*maxPageToDisplay):availableItems}" />
								of <s:property value="%{availableItems}" />
								items
							</span>

						</tr>
						<tr id="tr">
							<c:if test="${currentPageIndex>1}">
								<td id="td"><button type="submit"
										name="currentPageIndex" value="1"
										class="btn btn-primary">First</button></td>
								<td id="td"><button type="submit"
										name="companyFilterPage.currentPageIndex"
										value="${currentPageIndex - 1}"
										class="btn btn-primary">Prev</button></td>
							</c:if>
							<c:forEach
								begin="${(currentPageIndex) > 1? currentPageIndex : 1}"
								end="${(currentPageIndex + 4) <= availablePages ? (currentPageIndex + 4) :  availablePages }"
								var="i">
								<td>
									<button type="submit" name="companyFilterPage.currentPageIndex"
										value="${i}" class="btn btn-primary">
										<c:choose>
											<c:when test="${currentPageIndex == i}">
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
								test="${(currentPageIndex + 4) < availablePages}">
								<td id="td"><button type="submit"
										name="currentPageIndex"
										value="${currentPageIndex + 1}"
										class="btn btn-primary">Next</button></td>
								<td id="td"><button type="submit"
										name="currentPageIndex"
										value="${availablePages}"
										class="btn btn-primary">Last</button></td>
							</c:if>
						</tr>
					</table>  --%>
					
				</div>
			</form>
		</div>
</div>

<!-- /.content -->

<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

<link rel="stylesheet" href="css/alert.css">

<script type="text/javascript">
	$(function() {
		/*   $("#mytable1").DataTable(); */
		$('#mytable').DataTable({
			"paging" :false,
			"lengthChange" :false,
			"searching" :false,
			"ordering" :false,
			"info" :false,
			"autoWidth" : false,
			"search" : {
				"regex" : true,
			},
			"pagingType" : "full_numbers",
			"lengthMenu" : [ 10, 25, 50, 75, 100, 500 ],

		});
	});
</script>

<script type="text/javascript">
		function userDelete(id) {

			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "DeletePaymentCardInfo";
             console.log(id);
			$("#yes" + id)
					.click(
							function() {
								$("#delete" + id).hide();
								$(".modal-backdrop").hide();
								$
										.ajax({
											method : "POST",
											url : finalUrl,
											data : {
												id : id
											},

											success : function(data, status) {
												 console.log(data);
												 console.log(status);
												if (data == "deleted") {
													$('#success-alertdelete').show();
													$('#messagedelete').text("Successfully deleted.");
													$('#successdelete').click(
																	function() {
																		$('#success-alertdelete').hide();
																		window.location.assign($(location).attr('href'));
																	});

												} else if (data == "failed") {
													$('#error-alert').show();
													$('#message')
															.text(
																	"Failed.Try again.");
													$('#cancel')
															.click(
																	function() {
																		$(
																				'#error-alert')
																				.hide();

																	});
												}

											},
											error : function(data, status) {
												console.log(data);
												console.log(status);
											}
										});

							});

		}
		
		
	</script>
 
<script type="text/javascript">
		$(function() {
			var totUrl = $(location).attr('href');
			var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
			var finalUrl = newUrl + "ShowAirportList";
			$('#success').click(function() {
				window.location.assign(finalUrl);
				$('#success-alert').hide();

			});
			$('#cancel').click(function() {
				$('#error-alert').hide();

			});
		});
	</script>
