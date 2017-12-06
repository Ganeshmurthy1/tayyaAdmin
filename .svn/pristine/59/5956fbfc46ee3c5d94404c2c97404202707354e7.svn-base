<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html data-ng-app="NotifyAPP">
<head>
<%--  <script src= https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js> </script>
    <script src= https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js> </script> --%>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->
<link rel="stylesheet" href="css/pagination_css.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="user" /></title>
<style type="text/css">
[ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
  display: none !important;
}
</style>
</head>
<body  data-ng-controller="headerController" data-ng-clock>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Your Notifications</h1>

		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Small boxes (Stat box) -->
			<div class="row">
			
					
					<div class="col-sm-12 clearfix report-search">
						<div class="table-responsive dash-table">
							<div class="box clearfix">
							<div class="list-group-item" data-ng-repeat="notify in notifylist" >
                        <div class="notification">
                          <div class="notification-media">
                            <img class="rounded" width="40" height="40" src="{{getImageUrl(notify)}}" alt="Daniel Taylor">
                          </div>
                          <div class="notification-content">
                            <button class="notification-timestamp" data-ng-click="showdetail(notify)" >Read</button>
                            <small class="notification-timestamp" style="padding-right: 10px;">{{convertMS(notify.createdAt)}}</small>
                            <h5 class="notification-heading">{{notify.description}}</h5>
                            <p class="notification-text" data-ng-repeat="detail in notify.details" >                                                       
                              <small class="truncate"><b>{{detail.description}} :</b><b> {{notify.userName}} </b> {{getdetailsinfo(notify)}} {{detail.inventoryId}} </small>
                            </p>
                          </div>
                        </div>
                      </div>
							</div>
							</div>
							</div>

				<%-- 	<div class="col-sm-12 clearfix report-search">
						<div class="table-responsive dash-table">
							<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>S.No</th>
											<th>UserID</th>
											<th>CompanyID</th>
											<th>Amount</th>
											<th>TransactionType</th>
											<th>Remarks</th>
											<th>OpeningBal</th>
											<th>ClosingBal</th>
											<th>AllotedAt</th>
											<th>AllotedBy</th>

										</tr>
									</thead>
									<tbody>
									
											<tr data-ng-repeat="(index,notify) in notifylist">
												<td>{{index + 1}}</td>
												<td><s:property value="agencyName" /></td>
												<td><s:property value="company_userid" /></td>
												<td><s:if test="transactionType=='Debit'">(-)</s:if> <s:property
														value="amount" /></td>
												<td><s:property value="transactionType" /></td>
												<td><s:property value="remarks"/></td>
												<td><s:property value="parentOpeningBalance" /></td>
												<td><s:property value="parentClosingBalance" /></td>
												<td><s:property value="convertDate" /></td>
												<td><s:property value="parentcompany_userid" /></td>

											</tr>

									

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

										<c:forEach
											begin="${(companyFilterPage.currentPageIndex) > 1? (companyFilterPage.currentPageIndex) : 1}"
											end="${ (companyFilterPage.currentPageIndex + 4) <= companyFilterPage.availablePages ? (companyFilterPage.currentPageIndex + 4) :  companyFilterPage.availablePages }"
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

						</div>

					</div> --%>
				
			</div>
		</section>
	</div>


	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	
	 <script src="js/header.js" ></script> 
</body>
</html>