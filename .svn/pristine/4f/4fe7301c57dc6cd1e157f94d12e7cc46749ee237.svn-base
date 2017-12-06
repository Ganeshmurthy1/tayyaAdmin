<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Entity List</title>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>


</head>
<body>
	<s:if test="CurrentCompany!=null">
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header"> 
				<div class="row">
					<div class="col-sm-3 clearfix pull-left">
						<h3>Company Entity List</h3>
					</div>
				  <div class="col-sm-9 clearfix pull-right " data-placement="top">
		<div class="row">
		 
		<div class="col-sm-8 clearfix " data-placement="top">		
				<a href="addCompanyEntity?companyid=<s:property value="companyid"/>&Email=<s:property value="Email"/>"
					class="btn btn-top-link btn-xs"  >
					 Add Company Entity
				</a> 
				<a href="superUserCompanyList" class="btn btn-top-link btn-xs" >
					 Company List
				</a>
				 
			</div> 
			</div>			
			</div>
				  
				  
				</div>

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

					<div class="table-responsive dash-table">
						<input type="hidden"
							value="<s:property value="%{#session.User.id}"/>"
							id="createdUserId">
						<table
							class="table table-striped table-bordered dataTable no-footer">
							<thead class="thead-inverse">
								<tr>
									<th>SNo.</th>
									<th>companyGstIn</th>
									<th>CompanyName</th>
									<th>CompanyEntityName</th>
									<th>Email</th>
									<th>Country</th>
									<th>state</th>
									<th>City</th>
									<th>Phone</th>
									<th width="200">Address1</th>
									<th width="200">Address2</th>
									<th>Update</th>
									<th>Delete</th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="CurrentCompany.getCompanyEntities" var="data"
									status="rowStatus">
									<tr> 
										<th scope="row">${rowStatus.count}</th> 
										<td><input type="hidden" id="companyEntityId"
											value="<s:property value="companyEntityId"/>" /> <input
											type="text" class="form-control input-sm"
											id="companyEntityGstIn${companyEntityId}"
											name="companyEntityList[<s:property value="%{#rowStatus.index}" />].companyEntityGstIn"
											value="<s:property value="companyEntityGstIn"/>"
											autocomplete="off" required style="width: 100px;" /></td>

										<td><input type="text" class="form-control input-sm"
											id="Companyname${companyEntityId}"
											name="companyEntityList[<s:property value="%{#rowStatus.index}" />].Companyname"
											value="<s:property value="Companyname"/>" autocomplete="off"
											required style="width: 100px;" /></td>
										<td><input type="text" class="form-control input-sm"
											id="CompanyEntityName${companyEntityId}"
											name="companyEntityList[<s:property value="%{#rowStatus.index}" />].CompanyEntityName"
											value="<s:property value="CompanyEntityName"/>"
											autocomplete="off" required style="width: 100px;" /></td>
										<td><input type="text" class="form-control input-sm"
											id="Email${companyEntityId}"
											name="companyEntityList[<s:property value="%{#rowStatus.index}" />].Email"
											value="<s:property value="Email"/>" autocomplete="off"
											required style="width: 200px;" /></td>
										<td><select class="form-control input-sm"
											name="companyEntityList[<s:property value="%{#rowStatus.index}" />].Countryname"
											id="Countryname${companyEntityId}" required style="width: 200px;">
												<option selected="selected"
													value="<s:property value="Countryname"/>"><s:property
														value="Countryname" /></option>
												<s:iterator value="CountryList">
													<option <c:if test='${c_name == data.countryname}'>Selected</c:if>><s:property value="c_name"></s:property></option>
												</s:iterator>
										</select></td>
										<td><select class="form-control input-sm"
											name="companyEntityList[<s:property value="%{#rowStatus.index}" />].State"
											id="State${companyEntityId}" required style="width: 200px;">
												<s:iterator value="StateList">
													<option value="${stateName}-${StateCode}"
														<c:if test='${stateCode == data.stateCode}'>Selected</c:if>
														>${stateName}</option>
												</s:iterator>

										</select></td>
										<td><input type="text" class="form-control input-sm"
											id="City${companyEntityId}"
											name="companyEntityList[<s:property value="%{#rowStatus.index}" />].City"
											value="<s:property value="City"/>" autocomplete="off"
											required style="width: 100px;"></td>
										<td><div class="input-group">
												<div class="input-group-btn">
													<select class="form-control input-sm" name="Countryname"
														id="ccc" required style="width: 60px;">
														<s:iterator value="CountryList">
															<s:if test="c_name=='India'">
																<option selected="selected"><s:property
																		value="isd_code"></s:property></option>
															</s:if>
															<s:if test="c_name !='India'">
																<option>
																	<s:property value="isd_code"></s:property></option>
															</s:if>
														</s:iterator>
													</select>
												</div>
												<input type="tel" class="form-control input-sm"
													name="companyEntityList[<s:property value="%{#rowStatus.index}" />].PhoneNo"
													id="telphone${companyEntityId}" value="<s:property value="PhoneNo"/>"
													autocomplete="off" maxlength="16" required
													onkeypress="return isNumberKey(event,this)"
													style="width: 100px;">
											</div></td>
										<td width="200"><textarea class="form-control input-sm"
												id="Address1${companyEntityId}" style="width: 150px;"> <s:property value="Address1"/></textarea></td>
										<td width="200"><textarea class="form-control input-sm"
												id="Address2${companyEntityId}" style="width: 150px;"> <s:property value="Address2"/></textarea></td>


									 	<td><input type="button" class="btn btn-success btn-xs"
											id="companyEntity<s:property value="companyEntityId"/>"
											value="Update"
											onclick="updateCompanyEntity('<s:property value="companyEntityId"/>')">
											<span id="<s:property value="id"/>loader"
											style="display: none"><i
												class="fa fa-spinner fa-spin" style="font-size: 15px"></i></span> <span
											id="<s:property value="id"/>result" style="display: none"><i
												class="fa fa-check-circle success" style="font-size: 20px;"></i></span>
										</td>
										<td><input type="button" class="btn btn-success btn-xs" value="Delete"
											onclick="deleteCompanyEntity('<s:property value="companyEntityId"/>')">
											<span id="<s:property value="id"/>loader"
											style="display: none"><i
												class="fa fa-spinner fa-spin" style="font-size: 15px"></i></span> <span
											id="<s:property value="id"/>result" style="display: none"><i
												class="fa fa-check-circle success" style="font-size: 20px;"></i></span>
										</td>
 
  


									</tr>
								</s:iterator>


							</tbody>
						</table>
					</div>
				</div>


			</section>
			<!-- /.content -->
		</div>


	</s:if>

	<%@ include file="DashboardFooter.jsp"%>
	<%--   	<%@ include file="DashFooter.jsp"%> --%>


	<script type="text/javascript">
		function updateCompanyEntity(rowId) {
			  var companyEntityGstIn = $('#companyEntityGstIn'+rowId).val();  
			var Companyname = $('#Companyname'+rowId).val();
			var CompanyEntityName = $('#CompanyEntityName'+rowId).val();
			var Email = $('#Email'+rowId).val();
			var Countryname = $('#Countryname'+rowId).val();
			var State = $('#State'+rowId).val();
			var City = $('#City'+rowId).val();
			var Phone = $('#telphone'+rowId).val();
			var Address1 = $('#Address1'+rowId).val();
			var Address2 = $('#Address2'+rowId).val();
			var totUrl = $(location).attr('href'); 
			$('#loader'+rowId).show();
			$.ajax({
				method : "GET",
				url : "updateCompanyEntity",
				data : {
					companyEntityId : rowId,
					companyEntityGstIn : companyEntityGstIn,
					companyEntityName : CompanyEntityName,
					email : Email,
					countryname : Countryname,
					state : State,
					city : City,
					companyname : Companyname,
					phone : Phone,
					address1 : Address1,
					address2 : Address2
				},

				success : function(data, status) {
					$.each(data, function(index, element) {
						console.log("data-------" + element);
						console.log("data-------" + element.status);
						if (element.status == "success") {
							$('#loader'+rowId).hide();
						}

						else if (element.status == "fail")
							$('#loader'+rowId).show();

					});
				},
				error : function(xhr, status, error) {
					$('#'+rowId+'loader').show();
					console.log("Error----------" + error);
				}
			});
		}
		function deleteCompanyEntity(rowId) {
			$.ajax({
				method : "GET",
				url : "deleteCompanyEntity",
				data : {
					companyEntityId : rowId
				},

				success : function(data, status) {
					$.each(data, function(index, element) {
						if (element.status == "success") {
							alert(element.status)
							window.location.reload();
						}
						else if (element.status == "fail"){
							
						}
						 
					});
				},
				error : function(xhr, status, error) {
					console.log("Error----------" + error);
				}
			});
		}
	</script>
</body>
</html>