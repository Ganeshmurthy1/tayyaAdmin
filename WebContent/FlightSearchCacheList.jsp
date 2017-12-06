<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Config</title>
<link rel="stylesheet" href="css/alert.css">


<script type="text/javascript">
	$(function() {
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "c_ConfigList";
		$('#success').click(function() {
			window.location.assign(finalUrl);
			$('#success-alert').hide();

		});
		$('#cancel').click(function() {
			$('#error-alert').hide();

		});
	});
</script>
 <script src="js/jquery.validate.min.js"></script> 
 <script src="js/additional-methods.js"></script>
</head>
<body>

	<!-- Content Wrapper. Contains page content -->

	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Flight Cache Cities</h1>
		</section>

		<!-- Main content -->
		<section class="content">


			<div class="col-sm-12">
				<h4>
					<a href="javascript:history.back();"><span class="pull-right"><i
							class="fa fa-angle-left"></i> Back</span></a>
				</h4>
			</div>

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
			<div class="row" id="dash-us-register">
				<form id="addFlightSearchCacheForm" action="addFlightSearchCache" method="GET"
					class="form-horizontal">
					<input id="flightCityUrl" type="hidden" value="<s:text name="global.flightCitiesUrl" ></s:text>">
					<input type="hidden"
						value="<s:property value="%{#session.Company.companyRole.isDistributor()}"/>"
						class="form-control input-sm" id="companyRoleType">
						<div class="row">
						<div class="col-sm-3">
								<div class="form-group">
									<label for="country" class="control-label">Origin </label> <input
										type="text" autocomplete="on" id="origin" name="origin"
										class="form-control input-sm origin" required="required"
										placeholder="City">
								</div>
						</div>
						<div class="col-sm-2"> &nbsp;
						</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label for="destination" class="control-label">Destination
									</label> <input type="text" autocomplete="on" id="destination"
										name="destination" class="form-control input-sm destination"
										required="required" placeholder="City">
								</div>
							</div>
							<!-- <div class="">
								<div class="form-group">
									<label for="destination" class="control-label">One Way
									</label> <input type="checkbox" id="oneway" name="oneway"
									value="true" autocomplete="off">
								</div>
							</div> -->
							<div class="col-sm-4">
							<div class="form-group">
								<button id="addFlightSearchCacheAdd" type="button" class="btn btn-primary btn-sm">Add</button>
							</div>
							</div>
						</div>
				</form>
			</div>

			<div class="table-responsive dash-table">
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							<th>Origin</th>
							<th>Destination</th>
							<!-- <th>OneWay</th> -->
							<th>Actions</th>
							
						</tr>
					</thead>
					<tbody>
						<s:iterator value="flightSearchCasheList" status="rowCount">
							<tr>
							<form action="updateFlightSearchCache" method="GET" id ="formupdate_${rowCount.count}"
						class="form-horizontal updateFlightFormSubmission">
						
							<td><s:property value="%{#rowCount.count}" /></td>
							<td>
							<div class="">
								<div class="form-group">
									<label for="country" class="control-label">Origin </label> <input
										type="text" autocomplete="on" id="origin" name="origin"
										class="form-control input-sm origin" required="required" value="${origin}"
										placeholder="City">
								</div>
							</div>
							</td>
							<td>
							 <input type="hidden"  name="id"  required="required"  value="${id}">
							 <input type="hidden"  id="cacheList"  required="required"  value="${flightSearchCasheList}">
							<div class="">
								<div class="form-group">
									<label for="destination" class="control-label">Destination
									</label> <input type="text" autocomplete="on" id="destination"
										name="destination" class="form-control input-sm destination"
										required="required" placeholder="City"  value="${destination}">
								</div>
							</div>
							</td>
							<%-- <td>
							<div class="">
								<div class="form-group">
									<label for="destination" class="control-label">One Way
									</label> 
									<s:if test="oneway">
   
									<input type="checkbox" checked id="onewayupdate" name="oneway"
									value='' autocomplete="off">
									</s:if>
									<s:else>
									<input type="checkbox" id="onewayupdate" name="oneway"
									value='' autocomplete="off">
									</s:else>
								</div>
							</div>
							</td> --%>
							<td>
									<div class="form-group text-center">
								<div class="col-xs-5 submitWrap text-center">
									<button  type="submit" class="btn btn-primary btn-sm">Update</button>
								</div>
								
								<div class="col-xs-5 submitWrap text-center">
									<button type="button" onclick="deleteId(this)" id="${id}" class="btn btn-primary btn-sm ">Delete</button>
								</div>
							</div>
							</td>
							</form>
							</tr>
						</s:iterator>
					</tbody>

				</table>
				<table id="pagtable">
					<tr id="tr">
						<span>Showing <s:property
								value="%{((customerIpFilterPage.currentPageIndex - 1)*customerIpFilterPage.maxItems)+1}" />
							to <s:property
								value="%{((customerIpFilterPage.currentPageIndex*customerIpFilterPage.maxItems) <= customerIpFilterPage.availableItems)?(customerIpFilterPage.currentPageIndex*customerIpFilterPage.maxItems):customerIpFilterPage.availableItems}" />
							of <s:property value="%{customerIpFilterPage.availableItems}" />
							items
						</span>

					</tr>
					<tr id="tr">

						<c:if test="${customerIpFilterPage.currentPageIndex>1}">
							<td id="td"><button type="submit"
									name="customerIpFilterPage.currentPageIndex" value="1"
									class="btn btn-primary">First</button></td>
							<td id="td"><button type="submit"
									name="customerIpFilterPage.currentPageIndex"
									value="${customerIpFilterPage.currentPageIndex - 1}"
									class="btn btn-primary">Prev</button></td>
						</c:if>

						<c:forEach
							begin="${(customerIpFilterPage.currentPageIndex) > 1? (customerIpFilterPage.currentPageIndex) : 1}"
							end="${ (customerIpFilterPage.currentPageIndex + 4) <= customerIpFilterPage.availablePages ? (customerIpFilterPage.currentPageIndex + 4) :  customerIpFilterPage.availablePages }"
							var="i">
							<td>
								<button type="submit"
									name="customerIpFilterPage.currentPageIndex" value="${i}"
									class="btn btn-primary">
									<c:choose>
										<c:when test="${customerIpFilterPage.currentPageIndex == i}">
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
							test="${(customerIpFilterPage.currentPageIndex + 4) < customerIpFilterPage.availablePages}">
							<td id="td"><button type="submit"
									name="customerIpFilterPage.currentPageIndex"
									value="${customerIpFilterPage.currentPageIndex + 1}"
									class="btn btn-primary">Next</button></td>
							<td id="td"><button type="submit"
									name="customerIpFilterPage.currentPageIndex"
									value="${customerIpFilterPage.availablePages}"
									class="btn btn-primary">Last</button></td>
						</c:if>

					</tr>
				</table>
			</div>
			<!-- /.row -->
			<!-- Main row -->
		</section>
		<!-- /.content -->
	</div>


	<script type="text/javascript" src="js/app.js"></script>
	<script>
		$(document).ready(function() {
			
			
		 	var flightCityList = [];
			$
					.ajax({
						url : $("#flightCityUrl").val(),
						type : 'GET',
						dataType : 'json',
						success : function(data) {
							console.log(data.length)
							for (var i = 0; i < data.length; i++) {
								flightCityList
										.push( data[i].airport_code);
							}
							flightCityList = Array
									.from(new Set(
											flightCityList));
						},
						error : function(jqXhr, textStatus,
								errorThrown) {
							console.log("Error---"
									+ errorThrown
									+ "-------Status----------"
									+ textStatus);
						}
					});
			
			$(".origin")
			.removeClass('ui-autocomplete-input')
			.unbind()
			.autocomplete(
					{
						source : function(request,
								response) {
							var matcher = new RegExp(
									$.ui.autocomplete
											.escapeRegex(request.term),
									"i");
							response($
									.grep(
											flightCityList,
											function(
													item) {
												return matcher
														.test(item);

											}));
						}
					});

			$(".destination")
			.removeClass('ui-autocomplete-input')
			.unbind()
			.autocomplete(
					{
						source : function(request,response) {
							var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term),"i");
							response($.grep(flightCityList,
											function(item) {
												return matcher.test(item);

											}));
						}
					});
			
			var cachelist=$("#cacheList").val();
			console.log(cachelist);
			/* cachelist.forEach(function(entry) {
			    console.log(entry);
			}); */
			/* $.each(${flightSearchCasheList}, function (val) {
				console.log('fg'); */
				
				
			
				
			
			
		});
	</script>
	<script type="text/javascript">
		var dynamicList = [];
		$(document).ready(function() {
			$('#btnSave').click(function() {
				addCheckbox($('#txtName').val());
				/* $('#txtName').hide();
				$('#btnSave').hide(); */
			});
		});

		function addCheckbox(name) {
			var container = $('#cblist');
			var inputs = container.find('input');
			var id = inputs.length + 1;

			dynamicList[0] = "John";
			dynamicList[id] = name;
			/* alert(dynamicList); */

			$('<label />', {
				'for' : 'cb' + id,
				text : name,
				'class' : 'col-sm-2 control-label'
			}).appendTo(container);
			$('<input />', {
				type : 'checkbox',
				id : 'cb' + id,
				name : name,
				value : true,
				'class' : 'col-sm-7 '
			}).appendTo(container);
		}
	</script>

<script type="text/javascript">
function deleteId(s) { 

	
	var id=$(s).attr('id');
	$.ajax({
		url : "deleteFlightSearchCache?id="+id,
		type : 'GET',
		success : function(data) {
			location.reload();
		},
		error : function(jqXhr, textStatus,
				errorThrown) {
			
		}
	});
	
}
$(document).ready(function(){
	  
	   $('#addFlightSearchCacheAdd').click(function(){
		   $("#addFlightSearchCacheForm").valid();
		   if($("#addFlightSearchCacheForm").valid()){
			   document.getElementById("addFlightSearchCacheForm").submit();
		   }
	   });
	   $("#addFlightSearchCacheForm").validate({
		   submitHandler: function (form) {  
	            
	            return false;
	        },
	        highlight: function(element, errorClass, validClass) { 
	            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-ok').addClass('glyphicon-remove');
	            $(element).addClass(errorClass).removeClass(validClass);
	            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	          },
	          success: function(element) {
	            $(element).nextAll('.form-control-feedback').show().removeClass('glyphicon-remove').addClass('glyphicon-ok');
	         element.closest('.form-group').removeClass('has-error').addClass('has-success');
	            $(element).remove();
	          }
	   })
	     
	   
	   });
</script>

	<%@ include file="DashboardFooter.jsp"%>


</body>

</html>
