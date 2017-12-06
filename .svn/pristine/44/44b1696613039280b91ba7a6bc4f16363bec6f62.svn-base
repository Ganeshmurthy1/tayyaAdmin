<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>

</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
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
		<section class="content-header">
			<h1>Flight Customer Credit Note List</h1>
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
			<div class="row">
				<div class="col-sm-12 clearfix report-search">
				<s:if test="%{#session.Company!=null && #session.User!=null}">
				<s:if test="%{#session.User.userrole_id.isSuperuser()}">
				<form class="form-inline" action="searchSuperAgentCommInvoiceList"
							method="post">
							<div class="form-group" id="fromDateDiv">
								<label for="exampleInputAmount">From Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd1"
										placeholder="yyyy-mm-dd" name="fromDate"
										value='<s:property value="fromDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>

							<div class="form-group" id="endDateDiv">
								<label for="exampleInputAmount">End Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="yyyy-mm-dd" name="toDate"
										value='<s:property value="toDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
							<div class="form-group" id="user_form-group">
								<label for="Country">Company Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="companyType"
										id="filterCompanyType" autocomplete="off" required>
										<option value="mine">My Commission</option>
										<option value="all">ALL</option>
										<option value="dis">Wholesaler</option>
										<option value="agency">Agency</option>
									</select>
								</div>
							</div>

							<div class="form-group" id="user_form-group">
								<label for="Country">Report Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="period" id="period"
										autocomplete="off" required>
										<option value="0">select type</option>
										<option value="month">Month</option>
										<option value="week">Week</option>


									</select>
								</div>
							</div>

							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Show</button>
							</div>

							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">

									<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Action</th>
												<th>OrderId</th>
													<th>TotalAmount</th>
												<!-- <th>MyRevenue</th> -->
												<th>BaseCurrency</th>
												<th>BookedDate</th>
													<th>StatusAction</th>
												<th>PayStatus</th>
											 <th>Agency</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="%{agentCreditNoteList.size()>0}">
												<s:iterator value="agentCreditNoteList"
													status="rowCount">

													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td>
															<p data-placement="top" title="Generate Credit Note">

																<a
																	href="generateFlightCustomerCreditNote?id=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	 Issue Credit Note
																</a>
															</p>

														</td> 
														
														
														
														<td><s:property value="orderId"/></td>
														  
  														<td><s:property value="finalPrice"/></td>
														<%--  <td><s:property value="myProfit" /></td> --%>
														  <td><s:property value="curCode"/></td>
														  <td><s:property value="bookingDate"/></td>
														<td><s:property value="status"/></td>
															<td><s:property value="paymentStatus"/></td>
														<td><s:property value="createdBy"/></td>
														 
													</tr>

												</s:iterator>
											</s:if>
										 </tbody>
										<%-- <tbody>
											<tr>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th>Total_spent</th>
												<th>Total_comm</th>
												<th>Total</th>
 											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>

												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmountSpent}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAgentComm}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmount}" /></td>

											</tr>

										</tbody> --%>
									</table>

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>
				 	</s:if>
		 	
				<s:elseif test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isDistributor()}">
				<form class="form-inline" action="searchSuperAgentCommInvoiceList"
							method="post">
							<div class="form-group" id="fromDateDiv">
								<label for="exampleInputAmount">From Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd1"
										placeholder="yyyy-mm-dd" name="fromDate"
										value='<s:property value="fromDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>

							<div class="form-group" id="endDateDiv">
								<label for="exampleInputAmount">End Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="yyyy-mm-dd" name="toDate"
										value='<s:property value="toDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
							<div class="form-group" id="user_form-group">
								<label for="Country">Company Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="filterCompanyType"
										id="filterCompanyType" autocomplete="off" required>
										<option value="mine">My Commission</option>
										<option value="all">ALL</option>
										 <option value="agency">Agency</option>
									</select>
								</div>
							</div>

							<div class="form-group" id="user_form-group">
								<label for="Country">Report Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="period" id="period"
										autocomplete="off" required>
										<option value="0">select type</option>
										<option value="month">Month</option>
										<option value="week">Week</option>


									</select>
								</div>
							</div>

							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Show</button>
							</div>

							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">

								<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Action</th>
												<th>OrderId</th>
													<th>TotalAmount</th>
												<!-- <th>MyRevenue</th> -->
												<th>BaseCurrency</th>
												<th>BookedDate</th>
													<th>StatusAction</th>
												<th>PayStatus</th>
											 <th>Agency</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="%{agentCreditNoteList.size()>0}">
												<s:iterator value="agentCreditNoteList"
													status="rowCount">

													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td>
															<p data-placement="top" title="Generate Credit Note">

																<a
																	href="generateFlightCustomerCreditNote?id=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	 Issue Credit Note
																</a>
															</p>

														</td> 
														
														
														
														<td><s:property value="orderId"/></td>
														  
  														<td><s:property value="finalPrice"/></td>
														<%--  <td><s:property value="myProfit" /></td> --%>
														  <td><s:property value="curCode"/></td>
														  <td><s:property value="bookingDate"/></td>
														<td><s:property value="status"/></td>
															<td><s:property value="paymentStatus"/></td>
														<td><s:property value="createdBy"/></td>
														 
													</tr>

												</s:iterator>
											</s:if>
										 </tbody>
										<%-- <tbody>
											<tr>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th>Total_spent</th>
												<th>Total_comm</th>
												<th>Total</th>
 											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>

												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmountSpent}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAgentComm}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmount}" /></td>

											</tr>

										</tbody> --%>
									</table>

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>
				
				 </s:elseif>
				 <s:elseif test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isAgent()}">
				  <form class="form-inline" action="searchSuperAgentCommInvoiceList"
							method="post">
							<div class="form-group" id="fromDateDiv">
								<label for="exampleInputAmount">From Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd1"
										placeholder="yyyy-mm-dd" name="fromDate"
										value='<s:property value="fromDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>

							<div class="form-group" id="endDateDiv">
								<label for="exampleInputAmount">End Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="yyyy-mm-dd" name="toDate"
										value='<s:property value="toDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
							<div class="form-group" id="user_form-group">
								<label for="Country">Company Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="filterCompanyType"
										id="filterCompanyType" autocomplete="off" required>
										<option value="mine">My Commission</option>
										<option value="all">ALL</option>
									 </select>
								</div>
							</div>

							<div class="form-group" id="user_form-group">
								<label for="Country">Report Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="period" id="period"
										autocomplete="off" required>
										<option value="0">select type</option>
										<option value="month">Month</option>
										<option value="week">Week</option>


									</select>
								</div>
							</div>

							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Show</button>
							</div>
							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">

									<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Action</th>
												<th>OrderId</th>
													<th>TotalAmount</th>
												<!-- <th>MyRevenue</th> -->
												<th>BaseCurrency</th>
												<th>BookedDate</th>
													<th>StatusAction</th>
												<th>PayStatus</th>
											 <th>Agency</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="%{agentCreditNoteList.size()>0}">
												<s:iterator value="agentCreditNoteList"
													status="rowCount">

													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td>
															<p data-placement="top" title="Generate Credit Note">

																<a
																	href="generateFlightCustomerCreditNote?id=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	 Issue Credit Note
																</a>
															</p>

														</td> 
														
														
														
														<td><s:property value="orderId"/></td>
														  
  														<td><s:property value="finalPrice"/></td>
														<%--  <td><s:property value="myProfit" /></td> --%>
														  <td><s:property value="curCode"/></td>
														  <td><s:property value="bookingDate"/></td>
														<td><s:property value="status"/></td>
															<td><s:property value="paymentStatus"/></td>
														<td><s:property value="createdBy"/></td>
														 
													</tr>

												</s:iterator>
											</s:if>
										 </tbody>
										<%-- <tbody>
											<tr>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th>Total_spent</th>
												<th>Total_comm</th>
												<th>Total</th>
 											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>

												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmountSpent}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAgentComm}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmount}" /></td>

											</tr>

										</tbody> --%>
									</table>

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>
				   </s:elseif>
				 <s:elseif
					test="%{(#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() && #session.User.userrole_id.isCms()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder() && #session.User.userrole_id.isCms())|| 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isCms()) || 
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isReports()) ||
  (#session.User.userrole_id.isAdmin() && #session.User.userrole_id.isOrder())|| (#session.User.userrole_id.isAdmin())}">
							<s:if
								test="%{#session.User.Companyid==#session.Company.companyid}">
								<form class="form-inline" action="searchSuperAgentCommInvoiceList"
							method="post">
							<div class="form-group" id="fromDateDiv">
								<label for="exampleInputAmount">From Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd1"
										placeholder="yyyy-mm-dd" name="fromDate"
										value='<s:property value="fromDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>

							<div class="form-group" id="endDateDiv">
								<label for="exampleInputAmount">End Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="yyyy-mm-dd" name="toDate"
										value='<s:property value="toDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
							<div class="form-group" id="user_form-group">
								<label for="Country">Company Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="filterCompanyType"
										id="filterCompanyType" autocomplete="off" required>
										 <s:if test="%{#session.User.userrole_id.isUserMode() && #session.Company.companyRole.isAgent()}">
										 <option value="all">ALL</option>
										  </s:if>
										 	<s:elseif test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isWholesaler()}">
										 	 <option value="all">ALL</option>
										 <option value="agency">Agency</option>
										 	</s:elseif>
										 <s:elseif test="%{#session.User.userrole_id.isSuperuser()}">
										 <option value="all">ALL</option>
										<option value="dis">Wholesaler</option>
										<option value="agency">Agency</option>
										 </s:elseif>
									 </select>
								</div>
							</div>

							<div class="form-group" id="user_form-group">
								<label for="Country">Report Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="period" id="period"
										autocomplete="off" required>
										<option value="0">select type</option>
										<option value="month">Month</option>
										<option value="week">Week</option>


									</select>
								</div>
							</div>

							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Show</button>
							</div>

							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">

									<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Action</th>
												<th>OrderId</th>
													<th>TotalAmount</th>
												<!-- <th>MyRevenue</th> -->
												<th>BaseCurrency</th>
												<th>BookedDate</th>
													<th>StatusAction</th>
												<th>PayStatus</th>
											 <th>Agency</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="%{agentCreditNoteList.size()>0}">
												<s:iterator value="agentCreditNoteList"
													status="rowCount">

													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td>
															<p data-placement="top" title="Generate Credit Note">

																<a
																	href="generateFlightCustomerCreditNote?id=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	 Issue Credit Note
																</a>
															</p>

														</td> 
														
														
														
														<td><s:property value="orderId"/></td>
														  
  														<td><s:property value="finalPrice"/></td>
														<%--  <td><s:property value="myProfit" /></td> --%>
														  <td><s:property value="curCode"/></td>
														  <td><s:property value="bookingDate"/></td>
														<td><s:property value="status"/></td>
															<td><s:property value="paymentStatus"/></td>
														<td><s:property value="createdBy"/></td>
														 
													</tr>

												</s:iterator>
											</s:if>
										 </tbody>
										<%-- <tbody>
											<tr>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th>Total_spent</th>
												<th>Total_comm</th>
												<th>Total</th>
 											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>

												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmountSpent}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAgentComm}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmount}" /></td>

											</tr>

										</tbody> --%>
									</table>

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>
						 </s:if>
						  </s:elseif>
						  	<s:elseif test="%{#session.User.userrole_id.isReports() &&  #session.User.userrole_id.isOrder()}">
							<s:if
								test="%{#session.User.Companyid==#session.Company.companyid}">
								<form class="form-inline" action="searchSuperAgentCommInvoiceList"
							method="post">
							<div class="form-group" id="fromDateDiv">
								<label for="exampleInputAmount">From Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd1"
										placeholder="yyyy-mm-dd" name="fromDate"
										value='<s:property value="fromDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>

							<div class="form-group" id="endDateDiv">
								<label for="exampleInputAmount">End Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="yyyy-mm-dd" name="toDate"
										value='<s:property value="toDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
							<div class="form-group" id="user_form-group">
								<label for="Country">Company Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="filterCompanyType"
										id="filterCompanyType" autocomplete="off" required>
										 <s:if test="%{#session.User.userrole_id.isUserMode() && #session.Company.companyRole.isAgent()}">
										 <option value="all">ALL</option>
										  </s:if>
										 	<s:elseif test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isDistributor()}">
										 	 <option value="all">ALL</option>
										 <option value="agency">Agency</option>
										 	</s:elseif>
										 <s:else>
										 <option value="all">ALL</option>
										<option value="dis">Wholesaler</option>
										<option value="agency">Agency</option>
										 </s:else>
										 
									 </select>
								</div>
							</div>

							<div class="form-group" id="user_form-group">
								<label for="Country">Report Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="period" id="period"
										autocomplete="off" required>
										<option value="0">select type</option>
										<option value="month">Month</option>
										<option value="week">Week</option>


									</select>
								</div>
							</div>

							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Show</button>
							</div>

							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">

									<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Action</th>
												<th>OrderId</th>
													<th>TotalAmount</th>
												<!-- <th>MyRevenue</th> -->
												<th>BaseCurrency</th>
												<th>BookedDate</th>
													<th>StatusAction</th>
												<th>PayStatus</th>
											 <th>Agency</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="%{agentCreditNoteList.size()>0}">
												<s:iterator value="agentCreditNoteList"
													status="rowCount">

													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td>
															<p data-placement="top" title="Generate Credit Note">

																<a
																	href="generateFlightCustomerCreditNote?id=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	 Issue Credit Note
																</a>
															</p>

														</td> 
														
														
														
														<td><s:property value="orderId"/></td>
														  
  														<td><s:property value="finalPrice"/></td>
														<%--  <td><s:property value="myProfit" /></td> --%>
														  <td><s:property value="curCode"/></td>
														  <td><s:property value="bookingDate"/></td>
														<td><s:property value="status"/></td>
															<td><s:property value="paymentStatus"/></td>
														<td><s:property value="createdBy"/></td>
														 
													</tr>

												</s:iterator>
											</s:if>
										 </tbody>
										<%-- <tbody>
											<tr>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th>Total_spent</th>
												<th>Total_comm</th>
												<th>Total</th>
 											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>

												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmountSpent}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAgentComm}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmount}" /></td>

											</tr>

										</tbody> --%>
									</table>

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>
						 </s:if>
						 </s:elseif>
						  <s:elseif test="%{#session.User.userrole_id.isReports()}">
							<s:if
								test="%{#session.User.Companyid==#session.Company.companyid}">
								<form class="form-inline" action="searchSuperAgentCommInvoiceList"
							method="post">
							<div class="form-group" id="fromDateDiv">
								<label for="exampleInputAmount">From Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd1"
										placeholder="yyyy-mm-dd" name="fromDate"
										value='<s:property value="fromDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>

							<div class="form-group" id="endDateDiv">
								<label for="exampleInputAmount">End Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="yyyy-mm-dd" name="toDate"
										value='<s:property value="toDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
							<div class="form-group" id="user_form-group">
								<label for="Country">Company Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="filterCompanyType"
										id="filterCompanyType" autocomplete="off" required>
										 <s:if test="%{#session.User.userrole_id.isUserMode() && #session.Company.companyRole.isAgent()}">
										 <option value="all">ALL</option>
										  </s:if>
										 	<s:elseif test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isDistributor()}">
										 	 <option value="all">ALL</option>
										 <option value="agency">Agency</option>
										 	</s:elseif>
										  <s:else>
										 <option value="all">ALL</option>
										<option value="dis">Wholesaler</option>
										<option value="agency">Agency</option>
										 </s:else>
										 
									 </select>
								</div>
							</div>

							<div class="form-group" id="user_form-group">
								<label for="Country">Report Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="period" id="period"
										autocomplete="off" required>
										<option value="0">select type</option>
										<option value="month">Month</option>
										<option value="week">Week</option>


									</select>
								</div>
							</div>

							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Show</button>
							</div>

							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">

								<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Action</th>
												<th>OrderId</th>
													<th>TotalAmount</th>
												<!-- <th>MyRevenue</th> -->
												<th>BaseCurrency</th>
												<th>BookedDate</th>
													<th>StatusAction</th>
												<th>PayStatus</th>
											 <th>Agency</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="%{agentCreditNoteList.size()>0}">
												<s:iterator value="agentCreditNoteList"
													status="rowCount">

													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td>
															<p data-placement="top" title="Generate Credit Note">

																<a
																	href="generateFlightCustomerCreditNote?id=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	 Issue Credit Note
																</a>
															</p>

														</td> 
														
														
														
														<td><s:property value="orderId"/></td>
														  
  														<td><s:property value="finalPrice"/></td>
														<%--  <td><s:property value="myProfit" /></td> --%>
														  <td><s:property value="curCode"/></td>
														  <td><s:property value="bookingDate"/></td>
														<td><s:property value="status"/></td>
															<td><s:property value="paymentStatus"/></td>
														<td><s:property value="createdBy"/></td>
														 
													</tr>

												</s:iterator>
											</s:if>
										 </tbody>
										<%-- <tbody>
											<tr>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th>Total_spent</th>
												<th>Total_comm</th>
												<th>Total</th>
 											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>

												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmountSpent}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAgentComm}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmount}" /></td>

											</tr>

										</tbody> --%>
									</table>

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>
						 </s:if>
						 </s:elseif>
				   <s:elseif test="%{#session.User.userrole_id.isOrder()}">
							<s:if
								test="%{#session.User.Companyid==#session.Company.companyid}">
								<form class="form-inline" action="searchSuperAgentCommInvoiceList"
							method="post">
							<div class="form-group" id="fromDateDiv">
								<label for="exampleInputAmount">From Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd1"
										placeholder="yyyy-mm-dd" name="fromDate"
										value='<s:property value="fromDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>

							<div class="form-group" id="endDateDiv">
								<label for="exampleInputAmount">End Date</label>
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="twodpd2"
										placeholder="yyyy-mm-dd" name="toDate"
										value='<s:property value="toDate"/>'>
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
							<div class="form-group" id="user_form-group">
								<label for="Country">Company Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="filterCompanyType"
										id="filterCompanyType" autocomplete="off" required>
										 <s:if test="%{#session.User.userrole_id.isUserMode() && #session.Company.companyRole.isAgent()}">
										 <option value="all">ALL</option>
										  </s:if>
										 	<s:elseif test="%{#session.User.userrole_id.isUsermode() && #session.Company.companyRole.isDistributor()}">
										 	 <option value="all">ALL</option>
										 <option value="agency">Agency</option>
										 	</s:elseif>
										 <s:else>
										 <option value="all">ALL</option>
										<option value="dis">Wholesaler</option>
										<option value="agency">Agency</option>
										 </s:else>
										 
									 </select>
								</div>
							</div>

							<div class="form-group" id="user_form-group">
								<label for="Country">Report Type</label>
								<div class="input-group">
									<select class="form-control input-sm" name="period" id="period"
										autocomplete="off" required>
										<option value="0">select type</option>
										<option value="month">Month</option>
										<option value="week">Week</option>


									</select>
								</div>
							</div>

							<div class="form-group rep-buto">
								<button type="submit" class="btn btn-primary">Show</button>
							</div>

							<!-- </form> -->
							<div class="table-responsive dash-table">

								<!-- testing -->

								<div class="box clearfix">

								<table id="example" class="table table-striped table-bordered"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Action</th>
												<th>OrderId</th>
													<th>TotalAmount</th>
												<!-- <th>MyRevenue</th> -->
												<th>BaseCurrency</th>
												<th>BookedDate</th>
													<th>StatusAction</th>
												<th>PayStatus</th>
											 <th>Agency</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="%{agentCreditNoteList.size()>0}">
												<s:iterator value="agentCreditNoteList"
													status="rowCount">

													<tr>
														<td><s:property value="%{#rowCount.count}" /></td>
														<td>
															<p data-placement="top" title="Generate Credit Note">

																<a
																	href="generateFlightCustomerCreditNote?id=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">
																	 Issue Credit Note
																</a>
															</p>

														</td> 
														
														
														
														<td><s:property value="orderId"/></td>
														  
  														<td><s:property value="finalPrice"/></td>
														<%--  <td><s:property value="myProfit" /></td> --%>
														  <td><s:property value="curCode"/></td>
														  <td><s:property value="bookingDate"/></td>
														<td><s:property value="status"/></td>
															<td><s:property value="paymentStatus"/></td>
														<td><s:property value="createdBy"/></td>
														 
													</tr>

												</s:iterator>
											</s:if>
										 </tbody>
										<%-- <tbody>
											<tr>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th>Total_spent</th>
												<th>Total_comm</th>
												<th>Total</th>
 											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>

												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmountSpent}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAgentComm}" /></td>
												<td><s:property
														value="%{#session.flightAentTotalCommObj.totAmount}" /></td>

											</tr>

										</tbody> --%>
									</table>

								</div>
								<!-- /.box -->

							</div>
							<!-- table-responsive -->
						</form>
						 </s:if>
						 </s:elseif>
				   	</s:if>
				 </div>
			</div>
			<!-- /.row -->
			<!-- Main row -->


		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script>
		$(document).ready(function() {
			$("#twodpd2").datepicker({
				dateFormat : "yy-mm-dd"
			});
			$("#twodpd1").datepicker({
				dateFormat : "yy-mm-dd"
			/*  changeMonth: true,
			 changeYear: true */
			});
		});
	</script>
	<script type="text/javascript">
 
	
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						lengthChange : false,
						"paging" : false,
						"searching" : false,
						 "aLengthMenu": [[1, 5, 10, 20, -1], [1, 5, 10, 20, "All"]],
						/* "pagingType" : "full_numbers", */
						/* 	"lengthMenu" : [ 10, 25, 50, 75, 100, 500,100 ], */
						buttons : [ 'excel', 'pdf', 'print' ]
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});

		/*  $(function () {
		 	$('#example').DataTable({
		    	 "paging": true,
		         "lengthChange": true,
		        "searching": true,
		        "ordering": true,  
		           "info": true,
		         "autoWidth": false,  
		        "search": {
		      	    "regex": true,
		      	  }, 
		      	 
		      "pagingType": "full_numbers",
		      "lengthMenu": [10, 25, 50, 75, 100, 500 ],
		     
		      
		     });  
		  
		   });   */
	</script>
	<script type="text/javascript">
		$(function() {
			/*  $('#row_dim').hide();  */
			$('#period').change(function() {
				//alert($('#user').val());
				if ($('#period').val() == '0') {
					$('#fromDateDiv').show();
					$('#endDateDiv').show();
				} else {
					$('#fromDateDiv').hide();
					$('#endDateDiv').hide();
					if($("#twodpd1").val()!=""){
						$("#twodpd1").val("");
					}
					if($("#twodpd2").val()!=""){
						$("#twodpd2").val("");
					}
					
					
					
				}
			});

			 
			
			$('#companyName').change(function() {
				//alert($('#companyName').val());
				if ($('#companyName').val() == 'ALL') {
					$('#user_form-group').show();
				} else {
					$('#user_form-group').hide();
				}
			});

		});
	</script>

	<!-- 
 
  $(document).ready(function() 
    	 { 
    		 $("#twodpd1").datepicker({
    			 dateFormat: "yy-mm-dd"  
    			/*  changeMonth: true,
    			 changeYear: true */
    		 }); 
    		 }); -->

</body>
</html>