<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
  
 
<title><s:property value="user" /></title>
 
<link rel="stylesheet" href="css/alert.css">
  
	 
<style type="text/css">

#example td
{
padding:0px;
}

#example td input
{
height:18px;
}
 
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
				
			  		});
 
 </script>
 
 <script type="text/javascript">
 var map = new Object(); // or var map = {};
 
 
 function updateAmount(id){
	 

	 var  balanceAmount=$('#'+id+'balanceAmount').text(); 
	 var  totAmount=0;
	 var  totBrvAmount=$('#totBRVAmount').val(); 
	 var  actualTotBrvAmount=$('#actualTotBrvAmount').val(); 
 /*  $('#'+id+'amount').keyup(function() {  */
		 var amount=$('#'+id+'amount').val();
		  if(balanceAmount==''){
			 $('#'+id+'balanceAmount').text(0);
			 $('#'+id+'balanceAmount1').attr('value',0);
			 
	 		}
	 	if(amount==''){ 
			$('#'+id+'amount').attr('value', 0);
			 amount=$('#'+id+'amount').val();
		 } 
	 	 if(balanceAmount=='0'){
	 		 $('#'+id+'balanceAmount1').attr('value',$('#'+id+'billAmount').text());
			 $('#'+id+'balanceAmount').text($('#'+id+'billAmount').text());
	 		}
	 	
	 		var balAmount=parseInt($('#'+id+'billAmount').text())-parseInt(amount);
			 $('#'+id+'balanceAmount').text(balAmount);
			 $('#'+id+'balanceAmount1').attr('value', balAmount);
			 $('#'+id+'knockedOffAmount').text(amount); 
			 $('#'+id+'knockedOffAmount1').attr('value', amount);
			 $('#'+id+'amount').attr('value', amount);
			 map[id] = $('#'+id+'knockedOffAmount').text();
			 $.each(map, function (i, val) {
				 totAmount=parseInt(totAmount)+parseInt(val);
				
				});
			 console.log("totAmount..."+totAmount);
			  if(parseInt(totAmount)>parseInt(totBrvAmount)){
				 totBrvAmount=parseInt(actualTotBrvAmount)-parseInt(totAmount);
			 }
			 else{
				 totBrvAmount=parseInt(totBrvAmount)-parseInt(totAmount);
			 } 
			  $('#totKnockedOffAmount').val(totAmount);
			  $('#totBRVAmount').val(totBrvAmount); 
			  if(parseInt(totBrvAmount)==0 ){
				  $('#overflow').hide();
				  $('.KnockOff').show(); 
			  }
			  else if(parseInt(totBrvAmount)<parseInt(totAmount)){
				  $('#overflow').show();
				 $('.KnockOff').hide(); 
				
			  }
			  else{
				  $('#overflow').hide();
			 $('.KnockOff').show(); 
			  }
			  
			  
			  
 }
 
 function   knockOffSelection(id){ 
	 var invoiceAmt=$('#'+id+'billAmount').text();
	 var balanceAmount=$('#'+id+'balanceAmount').text();
	 var  totBrvAmount=$('#totBRVAmount').val(); 
	 var  actualTotBrvAmount=$('#actualTotBrvAmount').val(); 
	 var  totAmount=0;
	 var  brvamountDummy=0;
	 var balAmount=0;
	 var checkbox = $('#'+id+'knockOffCheck');
	 checkbox.val(checkbox[0].checked ? "true" : "false" );
	 if (checkbox.is(":checked"))
	 {
		 
	
		 $('#'+id+'amount').attr('value', 0);
		 $('#'+id+'balanceAmount').text(balanceAmount);
		 $('#'+id+'balanceAmount1').attr('value',balanceAmount);
		 $('#'+id+'amount').attr('value', balanceAmount);
		 $('#'+id+'knockedOffAmount').text(balanceAmount);
		 $('#'+id+'knockedOffAmount1').attr('value', balanceAmount);
		 balAmount=parseInt($('#'+id+'balanceAmount').text())-parseInt($('#'+id+'amount').val()); 
		 $('#'+id+'balanceAmount').text(balAmount);
		 $('#'+id+'balanceAmount1').attr('value',balAmount);
	  	$('#'+id+'amount').attr("disabled",false);
	  	
	  	 map[id] = $('#'+id+'knockedOffAmount').text();
		 $.each(map, function (i, val) {
			 totAmount=parseInt(totAmount)+parseInt(val);
			 totBrvAmount=parseInt(actualTotBrvAmount)-parseInt(totAmount);
			});
		 console.log("totAmount..."+totAmount);
		 /* if(parseInt(totAmount)>parseInt(totBrvAmount)){
			 totBrvAmount=parseInt(actualTotBrvAmount)-parseInt(totAmount);
		 }
		 else{
			 totBrvAmount=parseInt(totBrvAmount)-parseInt(totAmount);
		 } */
		  $('#totKnockedOffAmount').val(totAmount);
		  $('#totBRVAmount').val(totBrvAmount); 
		  if(parseInt(totBrvAmount)==0 ){
			  $('#overflow').hide();
		  }
		  else if(parseInt(actualTotBrvAmount)<=parseInt(totAmount)){
			  $('#overflow').show();
			 $('.KnockOff').hide(); 
		  }
		  else{
			  $('#overflow').hide();
			 $('.KnockOff').show();  
		  }
	  	 
	 }
	 else{
		
	 
		 var knockedOffAmount=$('#'+id+'knockedOffAmount').text();
		 console.log("knockedOffAmount-------"+knockedOffAmount);
		 $('#'+id+'knockedOffAmount').text(parseInt(invoiceAmt)-parseInt(knockedOffAmount)); 
		 $('#'+id+'knockedOffAmount1').attr('value',parseInt(invoiceAmt)-parseInt(knockedOffAmount));
	 
		 $('#'+id+'balanceAmount').text(knockedOffAmount);
		 $('#'+id+'balanceAmount1').attr('value',knockedOffAmount);
		 $('#'+id+'amount').attr("disabled",true);
		 $('#'+id+'amount').attr('value', 0);
		 
		 map[id] = $('#'+id+'knockedOffAmount').text();
		 $.each(map, function (i, val) {
			 totAmount=parseInt(totAmount)+parseInt(val);
			 totBrvAmount=parseInt(actualTotBrvAmount)-parseInt(totAmount);
			});
		 console.log("totAmount..."+totAmount);
		/*  if(parseInt(totAmount)>parseInt(totBrvAmount)){
			 totBrvAmount=parseInt(actualTotBrvAmount)-parseInt(totAmount);
		 }
		 else{
			 totBrvAmount=parseInt(totBrvAmount)-parseInt(totAmount);
		 } */
		  $('#totKnockedOffAmount').val(totAmount);
		  $('#totBRVAmount').val(totBrvAmount); 
		  if(parseInt(totBrvAmount)==0 ){
			  $('#overflow').hide();
		  }
		  else if(parseInt(actualTotBrvAmount)<=parseInt(totAmount)){
			  $('#overflow').show();
			  $('.KnockOff').hide(); 
		  }
		  else{
			  $('#overflow').hide();
			  $('.KnockOff').show(); 
		  }
	 }
	 
 }
      
  </script> 
</head>
<body>
	<div class="content-wrapper" style="margin-top:2px">
		<!-- Content Header (Page header) -->
		<section class="content-header" >
			<h3>KnockOff Reports</h3>
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
				<input type="hidden"
					value="<s:property value="%{#session.Company.company_userid}"/>"
					id="companyUserId"> <input type="hidden"
					value="<s:property value="%{#session.Company.Email}"/>" id="email">
				<input type="hidden"
					value="<s:property value="%{#session.User.company_userid}"/>"
					id="user_id">

				<form action="showKnockOffList" method="post" id="filterform">
					<div class="col-sm-12">
						<div class="expand filter-box" id="filters">
							<div class="well" style="padding: 0px;">
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
							
								<div class="panel-body">

									<!-- Filter of main info -->
								 <div class=" filter-text col-sm-12 clearfix" style="margin-top: -20px;">
										<div class="row">
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Agency Name</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="off" class="form-control"
															id="companyName" placeholder="type Agency Name" name="companyFilterPage.companyFilter.companyName"
															value="<s:property value="companyFilterPage.companyFilter.companyName"/>"><%--  --%>
															<input type="hidden"  id="companyId"  name="companyFilterPage.companyFilter.companyId" value="<s:property value="companyFilterPage.companyFilter.companyId"/>">
															 
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Start Date</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="off" class="form-control"
															id="twodpd12" placeholder="Start Date" name="companyFilterPage.companyFilter.dateFilterCreated.dtStart"
															value="<s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtStart"/>"><%-- <s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtStart"/> --%>
															 
															 
													</div>
												</div>
											</div>
											<div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">End Date</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="off" class="form-control"
															id="twodpd22" placeholder="End Date" name="companyFilterPage.companyFilter.dateFilterCreated.dtEnd"
															value="<s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtEnd"/>"><%-- <s:property value="companyFilterPage.companyFilter.dateFilterCreated.dtEnd"/> --%>
														 
															 
													</div>
												</div>
											</div>
									 <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Search Specific BRV</label>
													<div class="col-sm-12">
														<input type="text" autocomplete="off" class="form-control"
															id="orderId" placeholder="Search Specific BRV" name="companyFilterPage.companyFilter.orderId"
															value="<s:property value="companyFilterPage.companyFilter.orderId"/>"><%-- <s:property value="companyFilterPage.companyFilter.orderId"/> --%>
													</div>
												</div>
											</div>
											<s:if test="companyFilterPage.walletAmountTranferHistory!=null">
											
											 <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">BRV Amount<span id="overflow" style="color:red;font-size:10px;display:none">(Overflow)</span></label>
													<div class="col-sm-12">
													<input type="text" id='totBRVAmount'    readonly="readonly" class="form-control" value="<s:property value="getText('{0,number,##0.00}',{companyFilterPage.walletAmountTranferHistory.amount})"/>">
													 <input type="hidden" id='actualTotBrvAmount'   value="<s:property value="getText('{0,number,##0.00}',{companyFilterPage.walletAmountTranferHistory.amount})"/>">
													</div>
												</div>
											</div>
											 <div class="col-sm-2">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Total KnockedOff Amount</label>
													<div class="col-sm-12">
													<input type="text"  id='totKnockedOffAmount' readonly="readonly" class="form-control" value="0.00"/>
													 
													</div>
												</div>
											</div>
											 <div class="col-sm-2 KnockOff">
												<div class="form-group clearfix">
													<label class="col-sm-12 control-label">Action</label>
													<div class="col-sm-12">
														<input type="hidden"    class="form-control" value="${companyFilterPage.currentPageIndex}" />
														<button type="submit" name="companyFilterPage.companyFilter.actionType"
													value="KnockOff"  id="KnockOff"   class="form-control  btn btn-primary">KnockOff</button>
													</div>
												</div>
											</div>
											</s:if>
										</div>
									</div>  
									<div class="col-sm-12">

										<div class="col-sm-6 clearfix cc-all">
											<a href="" id="reset" class="text-right"><i
												class="fa fa-close"></i> Clear All</a>
										</div>
										 
										<div class="text-right filtr-btn col-sm-6 clearfix">
											<button type="submit" class="btn btn-primary"
												name="companyFilterPage.companyFilter.actionType"
												value="Filter">Filter</button> 
										</div>
										<s:if test="companyFilterPage.walletAmountTranferHistory!=null">
										  <div class="text-right filtr-btn col-sm-9 clearfix" style="margin-top:-25px;">
											  <s:property value="companyFilterPage.companyFilter.orderId"/> Actual Amount: <s:property value="getText('{0,number,##0.00}',{companyFilterPage.walletAmountTranferHistory.BRVorCRVAmount})"/> &nbsp&nbsp&nbsp&nbsp&nbsp  Total KnockedOff Amount : <s:property value="getText('{0,number,##0.00}',{companyFilterPage.walletAmountTranferHistory.BRVorCRVSpentAmount})"/> &nbsp&nbsp&nbsp&nbsp&nbsp  Rest Amount : <s:property value="getText('{0,number,##0.00}',{companyFilterPage.walletAmountTranferHistory.restAmount})"/>        
										</div>
											</s:if> 
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Filter ends here.... -->
					<div class="col-sm-12 clearfix report-search">
						<div class="table-responsive dash-table" style="margin-top:-20px">
							<!-- testing -->
							<div class="box clearfix">
								<table id="example" class="table table-striped table-bordered">
									<thead>
										<tr>
									 <th><!-- <input type="checkbox" id="select_all" /> --></th>
											<th>Booking Ref</th>
											<th>Booking Date</th>
											<th>Bill Date</th>
											<th>Bill No</th>
											<th>Bill Amt</th>
											<th>Knocked off Amt</th>
											<th>Balance Amt</th>
											<th>Amount</th>
											<th>BRV/CRV</th>
											<th>Booking Type</th>
											<th>GDS/LCC</th>
										</tr>
									</thead>
									<tbody>
									 <s:if test="companyFilterPage.allKnockOffVOReports !=null && companyFilterPage.allKnockOffVOReports.size()>0">
									 <s:iterator  var="obj"    value="companyFilterPage.allKnockOffVOReports" status="rowCount">
									  <tr>
									    <td><input type="checkbox" name="knockOffVOList[${rowCount.count-1}].knockOffCheck"  value="false" id="${rowCount.count}knockOffCheck"  onchange="knockOffSelection('${rowCount.count}')"></td>
												   <td> <input type="hidden" name="knockOffVOList[${rowCount.count-1}].bookingRef" value="<s:property value="bookingRef"/>"> <span id="${rowCount.count}bookingRef"> <s:property value="bookingRef"/></span></td>
												   <td> <input type="hidden" name="knockOffVOList[${rowCount.count-1}].bookingDate" value="<s:property value="bookingDate"/>"><span id="${rowCount.count}bookingDate"> <s:property value="bookingDate"/></span></td>
												   <td><input type="hidden" name="knockOffVOList[${rowCount.count-1}].billDate" value="<s:property value="billDate"/>"><span id="${rowCount.count}billDate"> <s:property value="billDate"/></span></td>
												   <td><input type="hidden" name="knockOffVOList[${rowCount.count-1}].billNo" value="<s:property value="billNo"/>"> <span id="${rowCount.count}billNo"><s:property value="billNo"/></span></td>
												   <td><input type="hidden" name="knockOffVOList[${rowCount.count-1}].billAmount" value="<s:property value="billAmount"/>"><span id="${rowCount.count}billAmount"><s:property value="getText('{0,number,##0.00}',{billAmount})"/></span> </td>
												 	<td><input type="hidden" name="knockOffVOList[${rowCount.count-1}].knockedOffAmount"  id="${rowCount.count}knockedOffAmount1"   value="<s:property value="knockedOffAmount"/>"><span id="${rowCount.count}knockedOffAmount"><s:property value="getText('{0,number,##0.00}',{knockedOffAmount})"/></span></td>
													<td><input type="hidden" name="knockOffVOList[${rowCount.count-1}].balanceAmount" id="${rowCount.count}balanceAmount1"    value="<s:property value="balanceAmount"/>">    <span id="${rowCount.count}balanceAmount"><s:property value="getText('{0,number,##0.00}',{balanceAmount})"/></span></td>
												 <td><input type="text"   id="${rowCount.count}amount" name="knockOffVOList[${rowCount.count-1}].amount"   disabled="disabled"  value="<s:property value="getText('{0,number,##0}',{amount})"/>"  onchange="updateAmount('${rowCount.count}')" > </td> 
												  <s:if test="BRVorCRVList !=null && BRVorCRVList.size()>0">
												    <td><select>
												     <s:iterator  var="brv" value="BRVorCRVList " status="rowCount">
												     <option><s:property value="brv"/></option>
												    </s:iterator>
												    </select></td>
												  </s:if>
												 <s:else>
												  <td> NA</td>
												 </s:else>
													<td><input type="hidden" name="knockOffVOList[${rowCount.count-1}].bookingType" value="<s:property value="bookingType"/>"> <span id="${rowCount.count}bookingType"> <s:property value="bookingType"/></span></td>
													<td><input type="hidden" name="knockOffVOList[${rowCount.count-1}].GDSorLCC" value="<s:property value="GDSorLCC"/>"><span id="${rowCount.count}GDSorLCC"> <s:property value="GDSorLCC"/></span></td>
														<td><input type="hidden" name="knockOffVOList[${rowCount.count-1}].invoiceType" value="<s:property value="invoiceType"/>"> </td>
												<%-- <s:if test="KnockOff==true">
												<td> <s:property value="getText('{0,number,#,##0.00}',{totInvoiceAmount})"/></td>
												<td><s:property value="getText('{0,number,#,##0.00}',{recievedAmount})"/></td>
												<td> <i class="fa fa-check-circle success" style="font-size:20px;"></i></td>
												</s:if>
												 <s:else>
												 <td><input type="text" id="<s:property value="id"/>totInvoiceAmount"    value="<s:property value="getText('{0,number,#,##0.00}',{totInvoiceAmount})"/>"></td>
												<td><input type="text" id="<s:property value="id"/>receivedAmount"   value="<s:property value="getText('{0,number,#,##0.00}',{recievedAmount})"/>"> </td>
												 <td><input type="button"  id="<s:property value="id"/>knockOffBut"    value="Knock Off" onclick="updateKnockOff('<s:property value="id"/>')"><span id="<s:property value="id"/>loader" style="display:none" ><i class="fa fa-spinner fa-spin" style="font-size:15px"></i></span><span id="<s:property value="id"/>result" style="display:none"   ><i class="fa fa-check-circle success" style="font-size:20px;"></i></span></td>
												 </s:else> --%>
												</tr> 
												 
										</s:iterator>
									 </s:if>
									 <s:else>
									 <tr>
									 <td colspan="7">No Data</td>
									 </tr>
									 </s:else>
										
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
			</div>
			</section>
	</div>
	 

	<!-- /.content-wrapper -->
	<%@ include file="DashboardFooter.jsp"%>
	<script type="text/javascript"  src="js/reset-form.js"></script>
	  	<script>
	 
		$("#twodpd12").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			/* minDate : 0, */
			onSelect : function(selectedDate) {
				var date2 = $("#twodpd12").datepicker('getDate', '+1d');
				date2.setDate(date2.getDate() + 1);
				$("#twodpd22").datepicker('setDate', date2);
				$("#twodpd22").datepicker("option", "minDate", date2);
			},
			onClose : function() {
				$("#twodpd22").focus();
			}

		});
		$("#twodpd22").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			/* minDate : 1, */
			onSelect : function(selected) {
				$("#twodpd12").datepicker("option", selected)
			}

		});
		
		 
	</script>
	 
</body>
</html>