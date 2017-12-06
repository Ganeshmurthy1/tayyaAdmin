<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Supplier CommonConfig List</h1>
	</section>
	<!-- Main content -->
	<section class="content">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<div class="col-sm-1">
					<input type="hidden" value="tboActive" id="${id}tboActive">
					<span>tbo <label class="switch"> <input type="checkbox"
							name="tboActive" id="tbo" value=""
							data-id="${id}" class="allActiveStatus"
							<c:if test="${tboAllActive==true}">checked</c:if>
							>
							<div class="slider"></div>
					</label>
					</span>
				</div>
				<div class="col-sm-1">
					<input type="hidden" value="bluestarActive" id="${id}bluestarActive">
					<span> bluestar<label class="switch"> <input type="checkbox"
							name="bluestarActive" id="bluestar" <%-- value="${tboActive}" --%>
							data-id="${id}" class="allActiveStatus"
							<c:if test="${bluestarAllActive==true}">checked</c:if>>

							<div class="slider"></div>
					</label>
					</span>
				</div>
				<div class="col-sm-1">
					<input type="hidden" value="desiyaActive" id="${id}desiyaActive">
					<span>desiya <label class="switch"> <input type="checkbox"
							name="desiyaActive" id="desiya" value=""
							data-id="${id}" class="allActiveStatus"
							<c:if test="${desiyaAllActive==true}">checked</c:if>
							>
							<div class="slider"></div>
					</label>
					</span>
				</div>
				<div class="col-sm-1">
					<input type="hidden" value="etravelActive" id="${id}etravelActive">
					<span> e-Travel<label class="switch"> <input type="checkbox"
							name="etravelActive" id="etravel" <%-- value="${tboActive}" --%>
							data-id="${id}" class="allActiveStatus"
							<c:if test="${etravelAllActive==true}">checked</c:if>>

							<div class="slider"></div>
					</label>
					</span>
				</div>
				<div class="col-sm-1">
					<input type="hidden" value="trawellTagActive" id="${id}trawellTagActive">
					<span> trawellTag<label class="switch"> <input type="checkbox"
							name="trawellTagActive" id="trawelltag" <%-- value="${tboActive}" --%>
							data-id="${id}" class="allActiveStatus "
							<c:if test="${trawellTagAllActive==true}">checked</c:if>>
							<div class="slider"></div>
					</label>
					</span>
				</div>

				<div class="col-sm-3 clearfix pull-right">
					<div class="add-new">
						<a class="btn btn-primary but btn-clean" href="addCommonConfig">
							Add New <i class="fa fa-angle-right"></i>
						</a>
					</div>
				</div>
		</div>


		<div class="row">

			<form class="form-inline" action="apiProviderList" method="post">
				<%-- <div class="clearfix">
					<div class="col-sm-12">
						<div  id="user_form-group">
						
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
													name="apiProviderDesiyaConfigs.maxItems" id="maxItems"
													onchange="this.form.submit()">
													<c:forEach var="maxItems"
														items="${apiProviderDesiyaConfigs.pageSizeQueue}">
														<c:choose>
															<c:when
																test="${apiProviderDesiyaConfigs.maxItems != null && apiProviderDesiyaConfigs.maxItems == maxItems}">
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
											<a class="btn btn-primary but btn-clean"
												href="addApiProvider"> Add New <i
												class="fa fa-angle-right"></i>
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
															<label class="col-sm-12 control-label">Supplier Name
																</label>
															<div class="col-sm-12">
																<input type="text" autocomplete="off"
																	class="form-control" id="supplierName"
																	placeholder="Supplier Name"
																	name="apiProviderDesiyaConfigs.apiProviderFilter.supplierName"
																	value="<s:property value="apiProviderDesiyaConfigs.apiProviderFilter.supplierName"/>">
															</div>
														</div>
													</div>
													 
												</div>
											</div>
											<div class="col-sm-12">
												<div class="col-sm-6 clearfix cc-all">
													<a href=" " id="reset" class="text-right"><i
														class="fa fa-close"></i> Clear All</a>
												</div>
												<div class="text-right filtr-btn col-sm-6 clearfix">
													<button type="submit" class="btn btn-primary"
														name="apiProviderDesiyaConfigs.currentPageIndex"
														value="${apiProviderDesiyaConfigs.currentPageIndex}">Submit</button>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div> --%>
				<div class="table-responsive dash-table">

					<table id="mytable" class="table table-striped"
						data-sort-name="name" data-sort-order="desc">
						<thead>
							<tr>
								<th>S.No</th>
								<th>CompanyName</th>
								<th>Company Config Type</th>
								<th>Config Mode</th>
								<th>TBO</th>
								<th>Blue star</th>
								<th>Desiya</th>
								<th>e-Travel</th>
								<th>Trawell Tag</th>
								<th>Action</th>
							</tr>
						</thead>

						<tbody>
							<s:iterator value="apiProviderCommonConfigs" status="rowCount">
								<tr>
									<%-- <td><s:property value="%{((apiProviderDesiyaConfigs.currentPageIndex - 1)*apiProviderDesiyaConfigs.maxItems)+#rowCount.count}" /></td> --%>
									<td>${rowCount.count}</td>
									<th>${companyName}</th>
									<th>${configType}</th>
									<td><div class="col-sm-9">
											<%-- <input type="hidden" value="active" id="${id}active"> --%>
											<span> <label class="switch"> <input
													type="checkbox" name="active" id="$active"
													value="${active}" data-id="${id}" class="activeStatus"
													<c:if test="${active==true}">checked</c:if>>
													<div class="slider"></div>
											</label>
											</span>
										</div></td>
									<td><div class="col-sm-9">
											<input type="hidden" value="tboActive" id="${id}tboActive">
											<span> <label class="switch"> <input
													type="checkbox" name="tboActive" id="tboActive"
													value="${tboActive}" data-id="${id}" class="activeStatus allTboChecked"
													<c:if test="${tboActive==true}">checked</c:if>>

													<div class="slider"></div>
											</label>
											</span>
										</div></td>

									<td><div class="col-sm-9">
											<input type="hidden" value="bluestarActive"
												id="${id}bluestarActive"> <span> <label
												class="switch"> <input type="checkbox"
													name="bluestarActive" id="bluestarActive"
													value="${bluestarActive}" data-id="${id}"
													class="activeStatus allBluestarChecked"
													<c:if test="${bluestarActive==true}">checked</c:if>>
													<div class="slider"></div>
											</label>
											</span>
										</div></td>

									<td><div class="col-sm-9">
											<input type="hidden" value="desiyaActive"
												id="${id}desiyaActive"> <span> <label
												class="switch"> <input type="checkbox"
													name="desiyaActive" id="desiyaActive"
													value="${desiyaActive}" data-id="${id}"
													class="activeStatus allDesiyaChecked"
													<c:if test="${desiyaActive==true}">checked</c:if>>
													<div class="slider"></div>
											</label>
											</span>
										</div></td>

									<td><div class="col-sm-9">
											<input type="hidden" value="etravelActive"
												id="${id}etravelActive"> <span> <label
												class="switch"> <input type="checkbox"
													name="etravelActive" id="etravelActive"
													value="${etravelActive}" data-id="${id}"
													class="activeStatus alleTravelChecked"
													<c:if test="${etravelActive==true}">checked</c:if>>
													<div class="slider"></div>
											</label>
											</span>
										</div></td>

									<td><div class="col-sm-9">
											<input type="hidden" value="trawellTagActive"
												id="${id}trawellTagActive"> <span> <label
												class="switch"> <input type="checkbox"
													name="trawellTagActive" id="trawellTagActive"
													value="${trawellTagActive}" data-id="${id}"
													class="activeStatus allTrawellChecked"
													<c:if test="${trawellTagActive==true}">checked</c:if>>
													<div class="slider"></div>
											</label>
											</span>
										</div></td>

									<td>
										<p data-placement="top">
											<a href="editCommonConfig?id=<s:property value="id"/>"
												class="btn btn-success btn-xs">Edit </a>
										</p>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<%-- <table id="pagtable"  >
				 <tr id="tr">
				 		 <span>Showing <s:property value="%{((apiProviderDesiyaConfigs.currentPageIndex - 1)*apiProviderDesiyaConfigs.maxItems)+1}" /> to <s:property value="%{((apiProviderDesiyaConfigs.currentPageIndex*apiProviderDesiyaConfigs.maxItems) <= apiProviderDesiyaConfigs.availableItems)?(apiProviderDesiyaConfigs.currentPageIndex*apiProviderDesiyaConfigs.maxItems):flightReportPage.availableItems}" /> of <s:property value="%{apiProviderDesiyaConfigs.availableItems}" /> items</span>
				 
				 </tr>
				 <tr id="tr">
			 
			 	<c:if test="${apiProviderDesiyaConfigs.currentPageIndex>1}">
			 		<td id="td"><button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="1" class="btn btn-primary">First</button></td>			 
			 		<td id="td"><button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="${apiProviderDesiyaConfigs.currentPageIndex - 1}" class="btn btn-primary">Prev</button></td>		
				</c:if>
					 
			 	<c:forEach begin="${(apiProviderDesiyaConfigs.currentPageIndex) > 1? (apiProviderDesiyaConfigs.currentPageIndex) : 1}" end="${ (apiProviderDesiyaConfigs.currentPageIndex + 4) <= apiProviderDesiyaConfigs.availablePages ? (apiProviderDesiyaConfigs.currentPageIndex + 4) :  apiProviderDesiyaConfigs.availablePages }" var="i">						
					<td>
					<button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="${i}" class="btn btn-primary" >
					<c:choose>
								<c:when test="${apiProviderDesiyaConfigs.currentPageIndex == i}">
									 <u>${i}</u>
								</c:when>

								<c:otherwise>
									${i}								
								</c:otherwise>
					</c:choose>
					</button>
					</td>						
				</c:forEach>
				<c:if test="${(apiProviderDesiyaConfigs.currentPageIndex + 4) < apiProviderDesiyaConfigs.availablePages}">
			 		<td id="td"><button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="${apiProviderDesiyaConfigs.currentPageIndex + 1}" class="btn btn-primary">Next</button></td>	
			 		<td id="td"><button type="submit" name="apiProviderDesiyaConfigs.currentPageIndex"  value="${apiProviderDesiyaConfigs.availablePages}" class="btn btn-primary">Last</button></td>
				</c:if>
			  	
					</tr> 
				</table> --%>

				</div>
			</form>
		</div>
	</section>
</div>
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>
<script src="js/common_config_status_update.js" type="text/javascript"></script>
<script>
$('.allActiveStatus').change(function (){
	var status = false;
	var id = $(this).data("id");
	var name = $(this).attr("name");
	var apiName = $(this).attr("id");
	if($(this).is(":checked")==false)
	{
		status= false;
		$(this).attr('checked', false);
		if(apiName === "tbo")
		$('.allTboChecked').attr('checked', false);
		if(apiName === "bluestar")
			$('.allBluestarChecked').attr('checked', false);
		if(apiName === "desiya")
			$('.allDesiyaChecked').attr('checked', false);
		if(apiName === "etravel")
			$('.alleTravelChecked').attr('checked', false);
		if(apiName === "trawelltag")
			$('.allTrawellChecked').attr('checked', false);
		
	}
	else{
		status= true;
		$(this).attr('checked', true);
		if(apiName === "tbo")
			$('.allTboChecked').attr('checked', true);
		if(apiName === "bluestar")
			$('.allBluestarChecked').attr('checked', true);
		if(apiName === "desiya")
			$('.allDesiyaChecked').attr('checked', true);
		if(apiName === "etravel")
			$('.alleTravelChecked').attr('checked', true);
		if(apiName === "trawelltag")
			$('.allTrawellChecked').attr('checked', true);
	}
	
	$.ajax({
		url :"ApiAllActiveStatus",
		data : {
			id :id,
			status:status,
			switchType:name,
			apiName:apiName
		}, 
		success : function(data, textStatus, jqXHR) {
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus);
		}

	});
});
</script>



