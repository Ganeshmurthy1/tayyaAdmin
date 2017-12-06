<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="css/alert.css">
<<%-- script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
	
</script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js">
	
</script> --%>
<!-- <link rel="stylesheet" type="text/css"
	href="css/jquerydarkness-ui.min.css"> -->

<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "commissionBlockList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
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
		<s:if test="%{#parameters.type[0]=='duplicate'}">
			<h1>Duplicate Company Block</h1>
		</s:if>
		<s:else>
			<h1>Update Company Block</h1>
		</s:else>

	</section>
	<!-- Main content -->
	<section class="cont-head">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<div class="clearfix content-box">
				<div class="col-sm-12">
					<div class="form-group" id="user_form-group">
						<form
							action="<s:if test="%{#parameters.type[0]=='duplicate'}">createDuplicateBlock</s:if><s:else>updateCompanyBlockSheet</s:else>" method="post">
							<input type="hidden" name="id"
								value="<s:property value="commissionBlockSheet.id"/>">
							<div class="form-group">
								<input type="text" name="name" class="form-control"
									id="dealSheetName"
									value="<s:property value="commissionBlockSheet.name"/>"
									placeholder="Deal Sheet Name" required="required">
							</div>
							<input type="hidden" id="isStatus"
								value="<s:property value="commissionBlockSheet.Active"/>">
 <input type="hidden" id="appliedSheetId" name ="appliedSheetId"
											value="<s:property value="commissionBlockSheet.appliedSheetId"/>">
							<%-- <s:if
								test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
								<div class="form-group">
									<s:if test="%{#parameters.type[0]=='duplicate'}">
										 <input type="hidden" id="appliedSheetId" name ="appliedSheetId"
											value="<s:property value="commissionBlockSheet.appliedSheetId"/>">
									</s:if>
									  <s:else>
										Select Deal Sheet <select name="appliedSheetId"
											class="form-control">
											<s:iterator value="sheetList">
												<option value='<s:property value="id"/>'>
													<s:property value="name" />
												</option>
											</s:iterator>
										</select>
									</s:else> 
								</div>
							</s:if> --%>

							<div class="form-group">
								<s:if test="%{#parameters.type[0]=='duplicate'}">
								<select name="dealStatus" class="form-control" id="dealStatus"
										required="required">
										<option value="0" selected="selected">InActive</option>
									</select>
								</s:if>
								<s:else>
								<select name="dealStatus" class="form-control" id="dealStatus"
										required="required">
										<option value="1" selected="selected">Active</option>
										<option value="0">InActive</option>
									</select>
									<script type="text/javascript">
	$(function() {
		var status = $("#isStatus").val();
		if (status == 'true') {
			document.getElementById('dealStatus').value = "1";
		} else {
			document.getElementById('dealStatus').value = "0";
		}
	});
</script>
								</s:else>
							</div>
							<div class="form-group">
								<textarea rows="" cols="" name="description"
									class="form-control" placeholder="Description" id="description"
									required="required"><s:property
										value="commissionBlockSheet.description" /> </textarea>
							</div>
							<button type="submit" class="btn btn-primary">
								<s:if test="%{#parameters.type[0]=='duplicate'}">
							Duplicate
						</s:if>
								<s:else>
							Update
							
						</s:else>
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Main content -->
</div>
<!-- content wrapper -->



<%@ include file="DashboardFooter.jsp"%>











