<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<!-- <link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" /> -->
	<!-- <link
	href="css/jquery-uii.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<title><s:property value="user" /></title>
<%-- <script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> --%>
<link rel="stylesheet" href="css/alert.css">
	<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
 <style type="text/css">
   .ui-autocomplete {
              max-height: 200px;
               width:auto;
              overflow-y: auto;
              /* prevent horizontal scrollbar */
              overflow-x: auto;
              font-family: "Trebuchet MS","Helvetica","Arial","Verdana","sans-serif";
			font-size:1em;
              /* add padding to account for vertical scrollbar */
              
      }
      /* IE 6 doesn't support max-height
       * we use height instead, but this forces the menu to always be this tall
       */
      * html .ui-autocomplete {
          height:  200px;
          width: auto;
      }
            
    .items label{
    margin-top: 5px;
    }
 
 </style>
 
<script type="text/javascript">
		$(function() {
		  var  reportType=document.getElementById('reportTypeHidden').value;
			 
			 document.getElementById('reportType').value = reportType;
			 
		});
</script>
</head>
<body>
	<div class="content-wrapper">
		<section class="content-header">
			<h1>Hotel List</h1>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-sm-12 clearfix">
					<s:if test="%{#session.Company!=null && #session.User!=null}">
					
							<form action="hotelList"
								method="post" id="filterform">
  					
<%-- 			<div class="col-sm-12">
	  <div class="row">
		  <div class="col-sm-8">
			  	<a class="btn btn-primary" role="button" data-toggle="collapse" href="#filters" aria-expanded="false" aria-controls="filters">
			  <i class="fa fa-filter" aria-hidden="true"></i> Filters
				</a>  
		  </div>
		  <div class="col-sm-4 pull-right items">
                <div class="form-group clearfix">
                  
                    <div class="col-sm-6">
                    	 <select class="form-control" name="hotelReportPage.maxItems" id="maxItems"
										  required onchange="this.form.submit()">																				 
												<c:forEach var="maxItems" items="${hotelReportPage.pageSizeQueue}" >
												<c:choose>
												<c:when test="${hotelReportPage.maxItems != null && hotelReportPage.maxItems == maxItems}">
													  <option value="${maxItems}"
													  selected = "selected" >${maxItems}</option>
												</c:when>				
												<c:otherwise>
													<option value="${maxItems}">${maxItems}</option>						
												</c:otherwise>
												</c:choose>
												 </c:forEach>										
										</select>
                       </div>
                        <label class="col-sm-5 control-label text-left">Items Per Page </label>
                      
                  </div>
                </div>
		  
		  
	  </div>
	  
<!-- filter box started -->	  
	  
<div class="collapse filter-box" id="filters">
  <div class="well">
  		      <div class="clearfix">
	 					
			 
 <!-- Filter of main info -->
		 <div class=" filter-text col-sm-12 clearfix">
		            <div class="row">

				<div class="col-sm-2">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Report Type</label>
                   <div class="col-sm-12">
                   
                   <input type="hidden" id="reportTypeHidden"  value="<s:property value="hotelReportPage.hotelReportFilter.reportType"/>">
									 <select class="form-control" name="hotelReportPage.hotelReportFilter.reportType"
											id="reportType"  required>											
											
											<option value="1">My Reports</option>
											<option value="0">ALL</option>
											<s:if test="%{#session.Company.companyRole.isDistributor()}">
											<option value="4">Agency</option>
											</s:if>
											<s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
											 <option value="3"><s:text name="global.Wholesaler" ></s:text></option>
											<option value="4">Agency</option>
											 </s:if>											 
										 </select>                   
                    
                    </div>
                  </div>
                </div>

          <div class="col-sm-2">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Order Id</label>
                    <div class="col-sm-12">
                    <input type="text" autocomplete="off"     class="form-control"
											id="companyName1" placeholder="type orderId"
											name="hotelReportPage.hotelReportFilter.orderId" value="<s:property value="hotelReportPage.hotelReportFilter.orderId"/>">
                      </div>
                </div>
              </div>


              <div class="col-sm-2">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Company Name</label>
                    <div class="col-sm-12">
                    <input type="text" autocomplete="off"     class="form-control"
											id="companyName1" placeholder="type company Name"
											name="hotelReportPage.hotelReportFilter.companyName" value="<s:property value="hotelReportPage.hotelReportFilter.companyName"/>">
                      </div>
                </div>
              </div>

               <div class="col-sm-2">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Employee Name</label>
                    <div class="col-sm-12">
                    <input type="text" class="form-control" autocomplete="off" 
											 placeholder="type emp user id"
											name="hotelReportPage.hotelReportFilter.userName" value="<s:property value="hotelReportPage.hotelReportFilter.userName"/>">
                      </div>
                </div>
              </div>
                  <div class="col-sm-2">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Hotel Name</label>
                    <div class="col-sm-12">
                    <input type="text" class="form-control" autocomplete="off" 
											 placeholder="type hotel name"
											name="hotelReportPage.hotelReportFilter.hotelName" value="<s:property value="hotelReportPage.hotelReportFilter.hotelName"/>">
                      </div>
                </div>
              </div>
                <div class="col-sm-2">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Email Address</label>
                    <div class="col-sm-12">
                    <input type="text" class="form-control" autocomplete="off" 
											 placeholder="email"
											name="hotelReportPage.hotelReportFilter.email" value="<s:property value="hotelReportPage.hotelReportFilter.email"/>">
                      </div>
                </div>
              </div>               

                <div class="col-sm-2">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Book Status</label>
                    <div class="col-sm-12">
                    <select class="form-control" name="hotelReportPage.hotelReportFilter.bookingStatus" id="bookingStatus"
											  required>
											 
											 <option value="ALL" >ALL</option>											 
												<c:forEach var="statusItem" items="${hotelReportPage.hotelReportFilter.bookingStatusQueue}" >
												<c:choose>
												<c:when test="${hotelReportPage.hotelReportFilter.bookingStatus != null && hotelReportPage.hotelReportFilter.bookingStatus == statusItem}">
													  <option value="${statusItem}"
													  selected = "selected" >${statusItem}</option>
												</c:when>				
												<c:otherwise>
													<option value="${statusItem}">${statusItem}</option>						
												</c:otherwise>
												</c:choose>
												 </c:forEach>										
										</select>
                      </div>
                  </div>
                </div>

                <div class="col-sm-2">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Payment Status</label>
                    <div class="col-sm-12">
                     <select class="form-control" name="hotelReportPage.hotelReportFilter.paymentStatus" id="paymentStatus"
											  required>
											 
											 <option value="ALL" >ALL</option>											 
												<c:forEach var="paymentStatusItem" items="${hotelReportPage.hotelReportFilter.paymentStatusQueue}" >
												<c:choose>
												<c:when test="${hotelReportPage.hotelReportFilter.paymentStatus != null && hotelReportPage.hotelReportFilter.paymentStatus == paymentStatusItem}">
													  <option value="${paymentStatusItem}"
													  selected = "selected" >${paymentStatusItem}</option>
												</c:when>				
												<c:otherwise>
													<option value="${paymentStatusItem}">${paymentStatusItem}</option>						
												</c:otherwise>
												</c:choose>
												 </c:forEach>										
										</select>
                     </div>
                  </div>
                </div>
                </div> 
           </div>
           
           
           <!-- Filter of additional info -->
         
                         <div class="date col-sm-12 clearfix">
            <div class="row">
 			 <div class="col-sm-4">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Booking Date</label>
                    <div class="col-sm-6">
                    <input type="text" class="form-control" id="twodpd1"
											placeholder="From Date(dd-mm-yyyy)" name="hotelReportPage.hotelReportFilter.dateFilterBooking.dtStart"
											value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterBooking.dtStart"/>'>
										 
                      </div>
                    <div class="col-sm-6">
                    <input type="text" class="form-control" id="twodpd2"
											placeholder="To Date(dd-mm-yyyy)" name="hotelReportPage.hotelReportFilter.dateFilterBooking.dtEnd"
											value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterBooking.dtEnd"/>'>
                      </div>
                  </div>
                </div>
                
                 <div class="col-sm-4">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Check-Out Date</label>
                    <div class="col-sm-6">
                    <input type="text" class="form-control" id="twoin"
											placeholder="From Date(dd-mm-yyyy)" name="hotelReportPage.hotelReportFilter.dateFilterCheckIn.dtStart"
											value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterCheckIn.dtStart"/>'>
										 
                      </div>
                    <div class="col-sm-6">
                    <input type="text" class="form-control" id="twoout"
											placeholder="To Date(dd-mm-yyyy)" name="hotelReportPage.hotelReportFilter.dateFilterCheckIn.dtEnd"
											value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterCheckIn.dtEnd"/>'>
                      </div>
                  </div>
                </div>
                
                  <div class="col-sm-4">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Check-In Date</label>
                    <div class="col-sm-6">
                    <input type="text" class="form-control" id="twocin"
											placeholder="From Date(dd-mm-yyyy)" name="hotelReportPage.hotelReportFilter.dateFilterCheckOut.dtStart"
											value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterCheckOut.dtStart"/>'>
										 
                      </div>
                    <div class="col-sm-6">
                    <input type="text" class="form-control" id="twocout"
											placeholder="To Date(dd-mm-yyyy)" name="hotelReportPage.hotelReportFilter.dateFilterCheckOut.dtEnd"
											value='<s:property value="hotelReportPage.hotelReportFilter.dateFilterCheckOut.dtEnd"/>'>
                      </div>
                  </div>
                </div>
                

                </div> 
           </div>
 <div class="slider col-sm-12 clearfix">
            <div class="row">
 	 <div class="col-sm-3">
                <div class="form-group clearfix">
                  <label class="col-sm-12 control-label">Total Price</label>
                    <div class="col-sm-12">
                     <div id="slider-1"></div>

                         <div class="col-sm-6">
                        <label class="col-sm-12 control-label">Min</label>
                          <div class="col-sm-12">
                           <input type="hidden" id="minPrice" name="hotelReportPage.hotelReportFilter.amountRangeBooking.amountMin" value='<s:property value="hotelReportPage.hotelReportFilter.amountRangeBooking.amountMin"/>'  class="form-control" >
                          
                           <input type="hidden" id="minPriceDefault" value='<s:property value="hotelReportPage.hotelReportFilter.amountRangeBooking.amountMinDefault"/>'  class="form-control" >
                              <input type="text"  id="price1" class="form-control" >
                          </div>
                      </div>
                       <div class="col-sm-6 text-right">
                          <label class="col-sm-12 control-label">Max</label>
                            <div class="col-sm-12">
                             <input type="hidden" id="maxPrice" name="hotelReportPage.hotelReportFilter.amountRangeBooking.amountMax" value='<s:property value="hotelReportPage.hotelReportFilter.amountRangeBooking.amountMax"/>'  class="form-control" >
                          
                             <input type="hidden" id="maxPriceDefault" value='<s:property value="hotelReportPage.hotelReportFilter.amountRangeBooking.amountMaxDefault"/>'  class="form-control" >
                             
                                 <input type="text" id="pric1" class="form-control"  >
                            </div>
                       </div>
                    </div>
                    
                  </div>
                </div>
                </div> 
           </div>
           
           <div class="col-sm-12">
           
           	<div class="col-sm-6 clearfix cc-all">
	  							<a href=""  id="reset"   class="text-right"><i class="fa fa-close"></i> Clear All</a>
						</div>
 	 		<div class="text-right filtr-btn col-sm-6 clearfix">
            	<button type="submit" class="btn btn-primary" name="hotelReportPage.currentPageIndex"  value="${hotelReportPage.currentPageIndex}" >Submit</button>
          	</div>
          	</div>
         
    </div>
  </div>
</div>	  
	  
  	  
  </div> --%>
     					
  	 
        <div class="col-sm-12 clearfix report-search">
								
								<div class="table-responsive dash-table">
									<div class="box clearfix">
										<table id="example" class="table table-striped table-bordered"
											 >
											<thead>
												<tr>
													<th>S.No</th>
												  	<th>HotelName</th>
												  	<th>Hotel Chain</th>
													<th>BookingDate</th>
													<th>Action</th>
													
													 										
													 
													<!-- <th>Action</th> -->
													<%-- <s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
													<th>SupplierName</th>
													</s:if> --%>
												 </tr>
											</thead>
											<tbody>
												<s:iterator value="hotelList" status="rowCount">
													<tr>
													<td><s:property value="%{#rowCount.count}" /></td>
															 <td><s:property value="name" /></td>
															  <td><s:property value="hotelChain" />  
															<td><s:property value="createdAt" /></td>
															<td>
																<a href="goHotelRoomList?id=<s:property value="id"/>"
																	class="btn btn-success btn-xs" >Room Details</a>
															
														</td>
														 
														 <td>
																<a href="hotelEdit?id=<s:property value="id"/>"
																	class="btn btn-success btn-xs" >Edit</a>
															
														</td>
														 
														 
														<%-- <td>
															<p data-placement="top" >
																<a
																	href="showReportDetails?selectedRowIndex=<s:property value="id"/>"
																	class="btn btn-success btn-xs" data-toggle="modal">Details</a>
															</p>
														</td> --%>
														<%-- <s:if test="%{(#session.Company.companyRole.Distributor==false && #session.Company.companyRole.Agent==false)}">
													<td><s:property value="apiProvider.vendorName"/></td>
													</s:if> --%>
													 </tr>
												</s:iterator>
											</tbody>
										</table>
					<%-- 				<table id="pagtable" >
				 						<tr id="tr">
				 					 <span>Showing <s:property value="%{((hotelReportPage.currentPageIndex - 1)*hotelReportPage.maxItems)+1}" /> to <s:property value="%{((hotelReportPage.currentPageIndex*hotelReportPage.maxItems) <= hotelReportPage.availableItems)?(hotelReportPage.currentPageIndex*hotelReportPage.maxItems):hotelReportPage.availableItems}" /> of <s:property value="%{hotelReportPage.availableItems}" /> items</span>
										 </tr>
											<tr id="tr">
			 
			 	<c:if test="${hotelReportPage.currentPageIndex>1}">
			 		<td id="td"><button type="submit" name="hotelReportPage.currentPageIndex"  value="1" class="btn btn-primary">First</button></td>			 
			 		<td id="td"><button type="submit" name="hotelReportPage.currentPageIndex"  value="${hotelReportPage.currentPageIndex - 1}" class="btn btn-primary">Prev</button></td>		
				</c:if>
					 
			 	<c:forEach begin="${(hotelReportPage.currentPageIndex) > 1? (hotelReportPage.currentPageIndex) : 1}" end="${ (hotelReportPage.currentPageIndex + 4) <= hotelReportPage.availablePages ? (hotelReportPage.currentPageIndex + 4) :  hotelReportPage.availablePages }" var="i">						
					<td>
					<button type="submit" name="hotelReportPage.currentPageIndex"  value="${i}" class="btn btn-primary" >
					<c:choose>
								<c:when test="${hotelReportPage.currentPageIndex == i}">
									 <u>${i}</u>
								</c:when>

								<c:otherwise>
									${i}								
								</c:otherwise>
					</c:choose>
					</button>
					</td>						
				</c:forEach>
				<c:if test="${(hotelReportPage.currentPageIndex + 4) < hotelReportPage.availablePages}">
			 		<td id="td"><button type="submit" name="hotelReportPage.currentPageIndex"  value="${hotelReportPage.currentPageIndex + 1}" class="btn btn-primary">Next</button></td>	
			 		<td id="td"><button type="submit" name="hotelReportPage.currentPageIndex"  value="${hotelReportPage.availablePages}" class="btn btn-primary">Last</button></td>
				</c:if>
			  	
					</tr> 
				</table> --%>
								</div>
								</div>
								</div>
							</form>						
						
					</s:if>
				</div>
			</div>
		</section>
	</div>
	<%@ include file="DashboardFooter.jsp"%>
	<script>
		$(document).ready(function() {
			$("#twodpd2").datepicker({
				dateFormat : "dd-mm-yy",
				 changeMonth: true,
				 changeYear: true
			});
			$("#twodpd1").datepicker({
				dateFormat : "dd-mm-yy",
			 changeMonth: true,
			 changeYear: true 
			});
			$("#twoin").datepicker({
				dateFormat : "dd-mm-yy",
				 changeMonth: true,
				 changeYear: true
			});
			$("#twoout").datepicker({
				dateFormat : "dd-mm-yy",
			 changeMonth: true,
			 changeYear: true 
			});
			$("#twocin").datepicker({
				dateFormat : "dd-mm-yy",
				 changeMonth: true,
				 changeYear: true
			});
			$("#twocout").datepicker({
				dateFormat : "dd-mm-yy",
			 changeMonth: true,
			 changeYear: true 
			});
		});
	</script>
	<script type="text/javascript">
		/* , 'pdf', 'print'  */
		$(document).ready(
				function() {
					var table = $('#example').DataTable({
						"paging" : false,
						"info" : false,
						"searching" : false,
						"ordering" : true,
						"search" : {
							"regex" : true,
						},
						
						buttons : [ 'excel' ]
					});

					table.buttons().container().appendTo(
							'#example_wrapper .col-sm-6:eq(0)');

				});
 </script>
	<script type="text/javascript">
		$(function() {
			/*  $('#row_dim').hide();  */
			$('#user').change(function() {
				//alert($('#user').val());
				if ($('#user').val() == 'ALL') {
					$('#company_form-group').hide();
				} else if ($('#user').val() == '0') {
					$('#company_form-group').show();

				} else {
					$('#company_form-group').hide();
				}
			});

			$('#companyName').change(function() {
				//alert($('#companyName').val());
				if ($('#companyName').val() == 'ALL') {
					$('#user_form-group').hide();
				} else if ($('#companyName').val() == '0') {
					$('#user_form-group').show();

				} else {
					$('#user_form-group').hide();
				}
			});

		});
	</script>
		<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script>



$(function() {
	
	  	$( "#slider-1" ).slider({
               range:true,
               min: parseInt($('#minPriceDefault').val()),
               max: parseInt($('#maxPriceDefault').val()),
               values: [parseInt($('#minPrice').val()), parseInt($('#maxPrice').val())],
               slide: function( event, ui ) {            	   
                  $( "#price1" ).val("(" + ui.values[0] + ")" );
                  $( "#pric1" ).val("(" + ui.values[1]  + ")" );   
                  $( "#minPrice" ).val(ui.values[0]);
                  $( "#maxPrice" ).val(ui.values[1]);   
 				}
           });
         	$( "#price1").val("("+ $( "#slider-1" ).slider( 'values', 0 ) + ")" );
            $( "#pric1" ).val("(" + $("#slider-1" ).slider( 'values', 1 ) + ")" );
         });



      </script>
	
</body>
</html>