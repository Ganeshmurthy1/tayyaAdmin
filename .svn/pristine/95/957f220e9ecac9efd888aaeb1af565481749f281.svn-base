<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<style>
.content-header {
	padding: 15px;
	border-bottom: 1px solid #d3d7db;
	border-top: 1px solid #eee;
	background: #f7f7f7;
	position: relative;
}

.content-header h1 {
	font-size: 28px;
	color: #1D2939;
	letter-spacing: -0.5px;
	margin: 0;
}

.content-header h1 .fa {
	font-size: 24px;
	margin-right: 5px;
	padding: 6px 7px;
	border: 2px solid #1D2939;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
}

.content-header .breadcrumb-wrapper {
	position: absolute;
	top: 23px;
	right: 25px;
}

.content-header .breadcrumb-wrapper .label {
	color: #999;
	text-transform: uppercase;
	font-size: 11px;
	font-weight: normal;
	display: inline-block;
}

.panel-heading, .panel-footer {
	background: #fff;
	border-color: #eee;
}

.panel-title {
	font-size: 18px;
	color: #111;
	/* font-family: 'LatoBold'; */
}

.content {
	padding: 20px;
	position: relative;
	background: #ffffff;
}

.subtitle-lined {
	border-bottom: 1px dotted #ddd;
	padding-bottom: 5px;
}

.subtitle {
	font-size: 13px;
	text-transform: uppercase;
	color: #333;
	font-family: 'LatoBold';
	margin-bottom: 15px;
	margin-top: 0;
}

.bug-key-title {
	margin: 0;
	margin-bottom: 5px;
}

.subTitleBold {
	font-size: 12px;
	font-weight: 700;
}

.comment-box {
	display: none;
}

.noshadow {
	-moz-box-shadow: none;
	-webkit-box-shadow: none;
	box-shadow: none;
}

.tab-content {
	background: #fcfcfc;
	padding: 15px;
	-moz-box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
	-webkit-box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
	box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
	-moz-border-radius: 0 0 3px 3px;
	-webkit-border-radius: 0 0 3px 3px;
	border-radius: 0 0 3px 3px;
}

.media-list {
	padding-left: 0;
	list-style: none;
}

.comment-list .media {
	border-bottom: 1px solid #ddd;
}

.media:first-child {
	margin-top: 0;
}

.media, .media .media {
	margin-top: 15px;
	padding-bottom: 15px;
}

.media, .media-body {
	overflow: hidden;
	zoom: 1;
}

.media>.pull-left {
	margin-right: 10px;
}

.pull-left {
	float: left !important;
}

.comment-list .media-body {
	font-size: 13px;
	position: relative;
}

.media, .media-body {
	overflow: hidden;
	zoom: 1;
}

.comment-list .reply {
	padding: 3px 8px;
	line-height: normal;
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
}

.comment-list p {
	margin-bottom: 10px;
}

.mynav>li>a:hover, .mynav>li>a:active, .mynav>li>a:focus {
	color: #444;
	background: #c8c8c8;
}
</style>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			<i class="fa fa-bug"></i>Bug Issues
		</h1>
		<div class="breadcrumb-wrapper">
			<%-- <span class="label">You are here:</span> --%>
			<ol class="breadcrumb">
				<li><a href="addBugTracker">Add Bug</a></li>
				<li><a href="goBugTrackerList">Bug List</a></li>
				<li><a href="goTestCases?id=${id}">Create Test Case</a></li>
				<li class="active">View Issue</li>
				
			</ol>
		</div>
	</section>
	<!-- Main content -->
	<section class="content">
		<div class="panel">
			<div class="panel-heading">
				<h5 class="bug-key-title">${bugTracker.referenceNo}
					&nbsp;&nbsp;<small class="text-muted"><fmt:formatDate
							value="${bugTracker.createdAt}" pattern="E, MMM dd, yyyy h:m a" /></small>
				</h5>
				<div class="panel-title">${bugTracker.title}</div>
			</div>
			<div class="panel-body">
				<div class="btn-group ">
					<a href="editBugTracker?id=${bugTracker.id}"
						class="btn btn btn-primary"><i class="fa fa-pencil mr5"></i>
						Edit</a>
					<!-- <button class="btn btn-primary" type="button">
						<i class="fa fa-comments mr5"></i> Comment
					</button> -->
				</div>

				<div class="btn-group ">
					<button class="btn btn-default bug-status-btn"
						data-status="Pending" data-bug-id="${bugTracker.id}">
						<i class="fa fa-eye"></i> Mark Pending
					</button>
					<button class="btn btn-default bug-status-btn"
						data-status="WorkInProgress" data-bug-id="${bugTracker.id}">
						<i class="fa fa-eye"></i> Work In Progress
					</button>
					<button class="btn btn-default bug-status-btn"
						data-status="TestReview" data-bug-id="${bugTracker.id}">
						<i class="fa fa-fix"></i> Ask Test Review
					</button>
					<button class="btn btn-default bug-status-btn" data-status="Review"
						data-bug-id="${bugTracker.id}">
						<i class="fa fa-eye"></i> Ask Review
					</button>
					<button class="btn btn-default bug-status-btn" data-status="Closed"
						data-bug-id="${bugTracker.id}">
						<i class="fa fa-stop"></i> Close Issue
					</button>
				</div>

				<br> <br>

				<div class="row">
					<div class="col-sm-12">
						<h5 class="subtitle subtitle-lined">Details</h5>
						<div class="row">
							<div class="col-sm-6">
								<div class="row">
									<div class="col-xs-6 subTitleBold">Type</div>
									<div class="col-xs-6">${bugTracker.bugType}</div>
								</div>
								<div class="row">
									<div class="col-xs-6 subTitleBold">Priority</div>
									<div class="col-xs-6">${bugTracker.level}</div>
								</div>
								<div class="row">
									<div class="col-xs-6 subTitleBold">Status</div>
									<div class="col-xs-6">${bugTracker.status}</div>
								</div>
								<c:choose>
									<c:when
										test="${session.User.userrole_id.techHead==true || session.User.userrole_id.techSupport==true }">
										<div class="row">
											<div class="col-xs-6 subTitleBold">Estimated Hours</div>
											<div class="col-xs-6">
												<c:choose>
													<c:when test="bugTracker.totalEstimatedHours!=null">
										${bugTracker.totalEstimatedHours}
									</c:when>
													<c:otherwise>
										 NA
										</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-6 subTitleBold">WorkingHours</div>
											<div class="col-xs-6">
												<c:choose>
													<c:when test="bugTracker.totalWorkingHours!=null">
										${bugTracker.totalWorkingHours}
									</c:when>
													<c:otherwise>
										 NA
										</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="row">
											<div class="col-xs-6 subTitleBold">ExtraHours</div>
											<div class="col-xs-6">
												<c:choose>
													<c:when test="bugTracker.totalExtraHours!=null">
										${bugTracker.totalExtraHours}
									</c:when>
													<c:otherwise>
										 NA
										</c:otherwise>
												</c:choose>
											</div>
										</div>
									</c:when>
								</c:choose>
							</div>
							<!-- col-sm-6 -->
							<div class="col-sm-6">

								<div class="row">
									<div class="col-xs-6 subTitleBold">Assignee</div>
									<div class="col-xs-6">${bugTracker.assignedByName}</div>
								</div>
								<div class="row">
									<div class="col-xs-6 subTitleBold">Assigned To</div>
									<div class="col-xs-6">${bugTracker.assignedToName}</div>
								</div>
								<div class="row">
									<div class="col-xs-6 subTitleBold">Assigned Date</div>
									<div class="col-xs-6">
										<c:choose>
											<c:when test="bugTracker.assignDate!=null">
												<fmt:formatDate value="${bugTracker.assignDate}"
													pattern="E, MMM dd, yyyy h:m a" />
											</c:when>
											<c:otherwise>
										 NA
										</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 subTitleBold">Work Start Date</div>
									<div class="col-xs-6">
										<c:choose>
											<c:when test="bugTracker.startToWorkDate!=null">
												<fmt:formatDate value="${bugTracker.startToWorkDate}"
													pattern="E, MMM dd, yyyy h:m a" />
											</c:when>
											<c:otherwise>
										 NA
										</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 subTitleBold">Work Finish Date</div>
									<div class="col-xs-6">
										<c:choose>
											<c:when test="bugTracker.workFinishDate!=null">
												<fmt:formatDate value="${bugTracker.workFinishDate}"
													pattern="E, MMM dd, yyyy h:m a" />
											</c:when>
											<c:otherwise>
										 NA
										</c:otherwise>
										</c:choose>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-6 subTitleBold">Reporter</div>
									<div class="col-xs-6">${bugTracker.createdByUserName}</div>
								</div>
							</div>
							<!-- col-sm-6 -->
						</div>
						<!-- row -->

						<br> <br>
						<h5 class="subtitle subtitle-lined">Description</h5>
						<p>${bugTracker.description}</p>

						<br> <br>
						<ul class="nav nav-tabs mynav">
							<li class="active"><a data-toggle="tab" href="#comments"><strong>Comments</strong></a></li>
							<li><a data-toggle="tab" href="#history"><strong>History</strong></a></li>
							<li><a data-toggle="tab" href="#testCases"><strong>Test Cases</strong></a></li>
						</ul>
						<div class="tab-content noshadow">
							<div id="comments" class="tab-pane active">
								<ul class="media-list comment-list">
									<s:iterator value="bugTrackerCommentList"
										status="rowCountComment">

										<li class="media">
											<%-- <a href="#" class="pull-left"> <img
											alt="" src="ImageAction.action?imageId=${createdBy}" class="media-object">
									</a> --%>
											<div class="media-body">
												<!-- <a class="btn btn-default btn-xs pull-right reply" href=""><i
												class="fa fa-reply"></i></a> -->
												<h4>${createdByUserName}</h4>
												<small class="text-muted"><fmt:formatDate
														value="${createdAt}" pattern="E, MMM dd, yyyy h:m a" /></small>
												<%-- ${createdAt} January 10, 2014 at 7:30am</small> --%>
												<p>${comments}.</p>
												<div class="row">
													<div class="col-sm-12 ">
														<c:if test="${filePath !=null}">
															<div class="download">
																<p>
																	<b>Download</b>
																</p>
																<p>
																	<a
																		href="downloadBugTrackerHistoryFile?fileName=${filePath}"
																		class="btn btn-success btn-xs">${filePath} </a>
																</p>
															</div>
														</c:if>
													</div>
												</div>
											</div> <!-- media-body -->
										</li>
									</s:iterator>
								</ul>
								<br>
								<button class="btn btn-primary add-comment-btn">
									<i class="fa fa-comments mr5"></i>Add Comment
								</button>
								<div class="row comment-box">
									<div class="col-12">
										<form action="saveBugTrackerComment" method="post"
											class="form-horizontal" name="myForm" id="myForm"
											enctype="multipart/form-data">
											<input type="hidden" name="bugTrackerId" id="bugTrackerId"
												value="${bugTracker.id}" />

											<div class="row comment-box">
												<div class="col-8">
													<div class="form-group">
														<label class="col-sm-2 control-label">comments</label>
														<div class="col-sm-8">
															<textarea rows="2" cols="2" class="form-control input-sm"
																name="comments" placeholder="comments"></textarea>
														</div>
													</div>
													<div class="form-group">
														<label for="uploadimage" class="col-sm-2 control-label">Upload
															File </label>
														<div class="col-sm-8">
															<div class="row">
																<div class="col-sm-6 file-upload">
																	<input type="file" id="filePath"
																		accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel,image/*"
																		ng-file-select="onFileSelect($files)"
																		name="bugTrackerHistory.filePath">
																</div>
																<!-- 	ng-file-select="onFileSelect($files)" -->
																<div class="col-sm-6 ">
																	<div id="fileinfo">
																		<div id="fileError"></div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="col-4">
													<div class="form-group text-center">
														<div class="col-xs-12 submitWrap text-center">
															<button type="submit" id="save"
																class="btn btn-primary btn-lg">Add</button>
														</div>
													</div>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
							<!-- tab-pane -->
							<div id="history" class="tab-pane">

								<div class="row">
									<div class="bug-tracker">
										<div class="bug-section">
											<div class="panel-group" id="accordion" role="tablist"
												aria-multiselectable="true">
												<s:iterator value="bugHistoryList" status="rowCount">
													<div class="panel panel-default">
														<div class="panel-heading" role="tab" id="headingOne">
															<h4 class="panel-title">
																<a role="button" class="bugtracker-toggle"
																	data-toggle="collapse" data-parent="#accordion"
																	href="#collapse" aria-expanded="true">
																	${rowCount.count} <span><b>Date : </b> <small
																		class="text-muted"> <fmt:formatDate
																				value="${createdAt}" pattern="E, MMM dd, yyyy h:m a" /></small></span>
																	<span><b>Status : </b> ${status}</span>
																</a>
															</h4>
														</div>
														<div id="collapse" class="panel-collapse collapse"
															role="tabpanel">
															<div class="panel-body">

																<div class="row">
																	<div class="col-sm-6">
																		<div class="row">
																			<div class="col-xs-6">Type</div>
																			<div class="col-xs-6">${bugTracker.bugType}</div>
																		</div>
																		<div class="row">
																			<div class="col-xs-6">Priority</div>
																			<div class="col-xs-6">${level}</div>
																		</div>
																		<c:choose>
																			<c:when
																				test="${session.User.userrole_id.techHead==true || session.User.userrole_id.techSupport==true }">
																				<div class="row">
																					<div class="col-xs-6">Estimated Hours</div>
																					<div class="col-xs-6">${estimatedHours}</div>
																				</div>
																				<div class="row">
																					<div class="col-xs-6">WorkingHours</div>
																					<div class="col-xs-6">${workingHours}</div>
																				</div>
																				<div class="row">
																					<div class="col-xs-6">ExtraHours</div>
																					<div class="col-xs-6">${extraHours}</div>
																				</div>
																			</c:when>
																		</c:choose>
																	</div>
																	<!-- col-sm-6 -->
																	<div class="col-sm-6">
																		<div class="row">
																			<div class="col-xs-6">Status</div>
																			<div class="col-xs-6">${status}</div>
																		</div>
																		<div class="row">
																			<div class="col-xs-6">Assignee</div>
																			<div class="col-xs-6">${assignedByName}</div>
																		</div>
																		<div class="row">
																			<div class="col-xs-6">Assigned To</div>
																			<div class="col-xs-6">${assignedToName}</div>
																		</div>

																	</div>
																	<!-- col-sm-6 -->
																</div>
																<div class="row">
																	<div class="col-sm-12 ">
																		<c:if test="comments!=null">
																			<div class="b-comments">
																				<p>
																					<b>Comments : </b> ${comments}
																				</p>
																			</div>
																		</c:if>
																		<c:if test="${filePath !=null}">
																			<div class="download">
																				<p>Download</p>
																				<p>
																					<a
																						href="downloadBugTrackerHistoryFile?fileName=${filePath}"
																						class="btn btn-success btn-xs">${filePath} </a>
																				</p>
																			</div>
																		</c:if>
																	</div>
																</div>

															</div>
														</div>
													</div>
												</s:iterator>
											</div>
										</div>
									</div>
								</div>

							</div>
							<div id="testCases" class="tab-pane">
								<div class="form-group">
									<label class="col-sm-2 control-label"> </label>
									<div class="col-sm-12">
										<div class="support">
											<div class="level1">
												<div id="level1">
															<ul class="media-list comment-list">
															<s:iterator value="bugTestCasesList"
																status="count">
																<li class="media">
																<div class="col-sm-12 tc">
																	<div class="row">
																		<div class="col-sm-2 ">
																			<div class="p-info clearfix">
																				<p align="left">Test Case:${count.count}</p>
																				<small class="text-muted">
																				${createdByUserName} :
																				<fmt:formatDate value="${createdAt}" pattern="E, MMM dd, yyyy h:m a" />
																				</small>
																			</div>
																		</div>
																		<div class="col-sm-7 ">
																			<div class="p-info clearfix">
																				<p>${testCase} </p>
																			</div>
																		</div>
																		<s:if test="verify!=true">
																			<div class="col-sm-3 pull-top">
																				<a href="bugTestCaseVerify?id=${id}"
																					class=" btn btn-primary" id="verify">verify</a> <a
																					href="editBugTestCase?id=${id}"
																					class="btn btn-success btn-xs"><i
																					class="fa fa-edit"></i> Edit</a> <a
																					href="deleteBugTestCase?id=${id}"
																					class="btn btn-success btn-xs"><i
																					class="fa fa-edit"></i> Delete</a>
																			</div>
																		</s:if>
																		<s:else>
																			<div class="col-sm-3 pull-top">
																				<a href="#" class="btn btn-success btn-xs"
																					id="verify" style="cursor: pointer !important;"><i
																					class="fa fa-check ">Verified</i> </a>
																			</div>
																		</s:else>
																	</div>
																	<div class="row">
																		<div class="col-sm-12"></div>
																	</div>
																</div>
															</s:iterator>
															</ul>
												</div>
											</div>
										</div>

									</div>
									<!--  support -->
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="modal fade" id="emailModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Alert !</h4>
				</div>
				<div class="modal-body">
					<p id="desc"></p>

				</div>

			</div>
		</div>
	</div>
</div>
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>


<script>
	var i = 0;
	var j = 0;
	$('.panel-collapse ').each(function() {
		i++;
		var newID = 'collapse' + i;
		$(this).attr('id', newID);
		$(this).val(i);
	});

	$('.bugtracker-toggle').each(function() {
		j++;
		var newIDS = '#collapse' + j;
		$(this).attr('href', newIDS);

		$(this).val(j);
	});

	$(".add-comment-btn").click(function() {
		$(".comment-box").show();
	});
	$(document).ready(function() {
		var noOfBugTracker = $(".panel-group").find('.panel-default').length;
		//alert(noOfBugTracker);
		$(".panel-default:first-child").find(".panel-collapse").addClass("in");
	});
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('.bug-status-btn')
								.click(
										function(e) {
											/* showModalPopUp("Saving Details, Please wait ..","i"); */
											var bugId = $(this).data("bug-id");
											var bugStatus = $(this).data(
													"status");
											$
													.ajax({
														url : "updateBugTrackerStatus?status="
																+ bugStatus
																+ "&id="
																+ bugId
																+ "&statusChanged=true",
														type : "GET",
														dataType : 'html',
														success : function(data) {
															alert("Updated");
															window.reload();
														},
														error : function(
																request,
																status, error) {
															alert("Error, Contact Support with Bug Id");
														}
													});
										});
					});
</script>




