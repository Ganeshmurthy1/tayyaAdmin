<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
 

 
<title><s:property value="user" /></title> 
<link rel="stylesheet" href="css/alert.css">
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
	$(document).ready(
			function() {
				var company_list = [];
				var agents_list = [];
				  var user_list = []; 
				 var cityMap=[];
				 var userMap=[];

				$.ajax({
					//Action Name
					url :"CompanyListUnderSuperUser",
					dataType : "json",
					data : {
					 parent_company_user_id : $("#companyUserId").val(),
						email : $("#email").val()
					},
					success : function(data, textStatus, jqXHR) {
						var items = data.records;
						for (var i = 0; i < data.records.length; i++) {
							//company_list.push(data.records[i].companyname +"("+data.records[i].company_userid+")"+","
							//		+ data.records[i].companyid);
							company_list.push(data.records[i].companyname);
							 var cityObj ={"key":data.records[i].companyid,"value":data.records[i].companyname}
	        				 cityMap.push(cityObj);
							 
						}
						console.log(company_list);
						//response(items);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});

				$("#companyName").autocomplete(
						{
							source : function(request, response) {
								var matcher = new RegExp('^'
										+ $.ui.autocomplete
												.escapeRegex(request.term),
										"i");
								response($.grep(company_list, function(item) {
									return matcher.test(item);

								}));
							},
							select : function(event, ui) {
								 $.map(cityMap, function(value, key) {
			          				    if(value.value==ui.item.label){
			          						console.log("value---"+value.value+"--------key----------"+value.key);
			          						$("#companyId").val(value.key);
			          					  }
			          				  
			          				});  
							 }
						});
				
				
				$.ajax({
					//Action Name
					url :"AgentsListUnderSuperUser",
					dataType : "json",
					data : {
					 parent_company_user_id : $("#companyUserId").val(),
						email : $("#email").val()
					},

					success : function(data, textStatus, jqXHR) {
 					for (var i = 0; i < data.agentList.length; i++) {
 						agents_list.push(data.agentList[i].username);
							/* agents_list.push(data.agentList[i].username + "("+data.agentList[i].company_userid+")"  + ","
									+ data.agentList[i].id); */
									 var userObj ={"key":data.agentList[i].id,"value":data.agentList[i].username}
									userMap.push(userObj);
 							 
						}
						console.log("------agents_list------"+agents_list);
						 user_list=agents_list;
							console.log("------user_list------"+user_list);
						 userlist(user_list,userMap);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
					}
				});
				
			  });
	
	 function userlist(userlist,userMap)
			{
		  if(userlist.length>0){
				$("#agentName").autocomplete(
						{
		 				source : function(request, response) {
								var matcher = new RegExp('^'
										+ $.ui.autocomplete
												.escapeRegex(request.term),
										"i");
								response($.grep(userlist, function(item) {
									return matcher.test(item);

								}));
							},
							select : function(event, ui) {
							 $.map(userMap, function(value, key) {
		          				    if(value.value==ui.item.label){
		          						console.log("value---"+value.value+"--------key----------"+value.key);
		          						$("#userId").val(value.key);
		          					  }
		          				  
		          				});  
						 }
						});	 
		 }
		  
	}
 </script>


<script type="text/javascript">
	function sendBusInvoiceToCustomerEmail(confirmNumber) {
var msg="";
		//var orderId=$("#orderId").val();
		console.log("confirmNumber..." + confirmNumber);
		var totUrl = $(location).attr('href');
		var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
		var finalUrl = newUrl + "sendBusInvoiceToCustomerEmail";
		msg="Successfully sent Bus Invoice to email.";
		console.log("finalUrl..." + finalUrl);
		$('#h4').show();
		$.ajax({
			method : "POST",
			url : finalUrl,
			data : {
				orderId : confirmNumber
			},
			success : function(data, status) {
				$.each(data, function(index, element) {
					console.log("data-------" + element.status);
					if (element.status == "success") {
						$('#h4').hide();
						$('#success-alert').show();
						 $('#message').text(msg);
						$('#success').click(function() {
							$('#success-alert').hide();
							//window.location.assign($(location).attr('href'));
						});
					} else if (element.status == "fail") {
						$('#h4').hide();
						$('#success-alert').show();
						$('#message').text("Failed.Try again.");
						$('#success').click(function() {
							$('#success-alert').hide();

						});
					}

				});
			},
			error : function(xhr, status, error) {
				$('#h4').hide();
				$('#success-alert').show();
				$('#message').text(error);
				$('#success').click(function() {
					$('#success-alert').hide();
				});
				console.log("Error----------" + error);
			}
		});
	}
</script>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Customer Order</h1>
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
					<s:if test="%{#session.Company!=null && #session.User!=null}">
					
					<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">
						
				<form action="CustomerOrderList" method="post" id="filterform">
				
					<input type="hidden" name="showType"
					value="<s:property value="showType"/>">
					
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
										<select class="form-control" name="companyFilterPage.maxItems"
											id="maxItems" required onchange="this.form.submit()">
											<c:forEach var="maxItems"
												items="${companyFilterPage.pageSizeQueue}">
												<c:choose>
													<c:when
														test="${companyFilterPage.maxItems != null && companyFilterPage.maxItems == maxItems}">
														<c:choose>
															<c:when test="${companyFilterPage.maxItems == -1}">
																<option value="${maxItems}" selected="selected">ALL</option>
															</c:when>

															<c:otherwise>
																<option value="${maxItems}" selected="selected">${maxItems}</option>
															</c:otherwise>
														</c:choose>
													</c:when>

													<c:otherwise>
														<c:choose>
															<c:when test="${maxItems == -1}">
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
										
										 
											
											 
												<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Email
																Address</label>
															<div class="col-sm-12">
																<input type="text" class="form-control" id="emailAddress"
																	autocomplete="off" placeholder="email"
																	name="companyFilterPage.companyFilter.email"
																	value="<s:property value="companyFilterPage.companyFilter.email"/>">
															</div>
														</div>
													</div> 
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">FirstName
																 </label>
															<div class="col-sm-12">
																<input type="text" class="form-control" id="firstName"
																	autocomplete="off" placeholder="firstName"
																	name="companyFilterPage.companyFilter.firstName"
																	value="<s:property value="companyFilterPage.companyFilter.firstName"/>">
															</div>
														</div>
													</div>
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">LastName
																 </label>
															<div class="col-sm-12">
																<input type="text" class="form-control" id="lastName"
																	autocomplete="off" placeholder="lastName"
																	name="companyFilterPage.companyFilter.lastName"
																	value="<s:property value="companyFilterPage.companyFilter.lastName"/>">
															</div>
														</div>
													</div>
													
													<div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Phone
																 </label>
															<div class="col-sm-12">
																<input type="text" class="form-control" id="phone"
																	autocomplete="off" placeholder="phone"
																	name="companyFilterPage.companyFilter.phone"
																	value="<s:property value="companyFilterPage.companyFilter.phone"/>">
															</div>
														</div>
													</div>
													
												  <%-- <div class="col-sm-2">
														<div class="form-group clearfix">
															<label class="col-sm-12 control-label">Company Type
																 </label>
															<div class="col-sm-12">
																<input type="text" class="form-control" id="companytype"
																	autocomplete="off" placeholder="phone"
																	name="companyFilterPage.companyFilter.phone"
																	value="<s:property value="companyFilterPage.companyFilter.phone"/>">
															</div>
														</div>
													</div>   --%>
										</div>
									</div>


									 
									

									<div class="col-sm-12">

										<div class="col-sm-6 clearfix cc-all">
											<button type="reset" class="btn btn-primary text-right">
												<i class="fa fa-close"></i> Clear All
											</button>
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
					<!-- Filter ends here.... -->
					<div class="col-sm-12 clearfix report-search">
						<div class="table-responsive dash-table">
							<!-- testing -->
							<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered" style="white-space:nowrap;">
									<thead>
										<tr>
											<th>S.No</th>  
												<th>FirstName</th>
												<th>LastName</th>
												<th>Gender</th>
												<th>Email Address</th>
												<th>Phone</th> 
												<th>Country Id</th>
												<th>View</th>
 
										</tr>
									</thead>
									<tbody>
										<s:iterator value="companyFilterPage.orderCustomerList" status="rowCount">
 											<tr>
											 <td> <s:property value="%{((companyFilterPage.currentPageIndex - 1)*companyFilterPage.maxItems)+#rowCount.count}" /></td> 
													<td> <s:property value="firstName"/></td>
													  <td> <s:property value="lastName"/></td>
													<td> <s:property value="gender"/> </td>
													<td> <s:property value="email"/> </td>
													<td> <s:property value="phone"/> </td> 
													<td> <s:property value="countryId"/> </td>  
													<td> <a href="showCustomerDetailsOfCustomerOrders?id=<s:property value="id"/>" class="btn btn-success btn-xs " >
															View Details </a>   </td>   

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
					</div>
				</form>
				</s:if>
			</div>
		</section>
	</div>


	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript"  src="js/reset-form.js"></script>
	<script>
		$(document).ready(function() {
			$("#twoout2").keyup(function () {
				  $(this).next("#twoout3").focus();
				});
				 
				 $("#twodt1").keyup(function () {
					  $(this).next("#twodt2").focus();
					}); 
				 $('#twoout2').datepicker({
					 //showTimePicker: false,
					dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
					 onSelect: function( selectedDate ) {
					    	var date2 = $("#twoout2").datepicker('getDate', '+1d'); 
					  	  date2.setDate(date2.getDate() + 1); 
					  	  $( "#twoout3" ).datepicker('setDate', date2);
					  	$( "#twoout3" ).datepicker( "option", "minDate", date2 ); 
					    },
					  onClose: function() {
					      $("#twoout3").focus();
					  }
					  
				  });
			          
			     
			 
			 $('#twoout3').datepicker({
				 //timepicker: false,
				/*  changeMonth: true,   
		    	 changeYear:true, */
		    	 dateFormat : "dd-mm-yy",
					changeMonth : true,
					changeYear : true,
		       	 minDate:0,
		         onSelect: function(selectedDate,i) { 
		        	// $("#fromDate").datepicker("option", selected)
		              var date2 = $("#twoout3").datepicker('getDate', '+1d'); 
		          
		            
		             
		         }
			 
			 });
	
	
			 
			 $('#twodt1').datepicker({
				 //showTimePicker: false,
				dateFormat : "dd-mm-yy",
			changeMonth : true,
			changeYear : true,
				 onSelect: function( selectedDate ) {
				    	var date2 = $("#twodt1").datepicker('getDate', '+1d'); 
				  	  date2.setDate(date2.getDate() + 1); 
				  	  $( "#twodt2" ).datepicker('setDate', date2);
				  	$( "#twodt2" ).datepicker( "option", "minDate", date2 ); 
				    },
				  onClose: function() {
				      $("#twodt2").focus();
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
	</script>
	 
	 

<%-- 	<script src="js/jquery-ui.js"></script> --%>


	<script>
		$('spna[data-toggle="tooltip"]').tooltip({
			animated : 'fade',
			placement : 'bottom',
		});
	</script>

</body>
</html>