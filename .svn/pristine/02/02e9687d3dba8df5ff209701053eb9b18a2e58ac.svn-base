<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Create Supplier Common Config</h1>
	</section>
	<!-- Main content -->
	<section class="content">
	<form action="createCommonConfig" method="post"
				class="form-horizontal" name="myForm">
		<!-- Small boxes (Stat box) -->
		<div class="row">
			<!-- <table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					
					<tbody>
							<tr>
								<td>API Configuration Name</td>
								<td>
                		<input type="text"  name="title"  >
               				</td>
							</tr>
					</tbody>
				</table> -->
				
				<div class="table-responsive dash-table">
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
						 
								<td><h4><b>Select Company For Common Configuration</b></h4>
								 <select  class="form-control input-sm"
								  name="companyConfigIds"  id="companyConfigIds"      required="required">
								  <option value="">Select company ConfigType</option>
								<s:iterator value="companyConfigsList" status="rowCount">
								<option value="${company_id}/${config_id}" >${companyName}(${companyUserId})_${configType}</option>
								</s:iterator>
							 </select></td>
							 
							</tr>
					</thead>
				</table>
				</div>
				
				
				<div class="table-responsive dash-table">
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<tbody>
							<tr>
								<td>Activate All API</td>
								<td><span >
                 <label class="switch"> <%-- ${tboActive} --%><input type="checkbox"  name="allAPIActive"  data-id="${id}"
                  class="allAPIActiveStatus" >
                  <div class="slider"></div>
                </label> 
               </span></td>
							</tr>
						 
					</tbody>
				</table>
				</div>
				
				
				<c:if test="${apiProviderTboConfigs!=null && apiProviderTboConfigs.size()>0}">
			<div class="table-responsive dash-table">
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>ApiProvider</th>
							<th>API Switch</th>
							</tr>
					</thead>
					<tbody>
						 
							<tr>
								<td>TBO</td>
								<td><span >
                 <label class="switch"> <%-- ${tboActive} --%>
                 <input type="checkbox"  name="tboActive"  data-id="${id}" class="activeStatus" <c:if test="${tboActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
							</tr>
						 
					</tbody>
				</table>
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							
							<th>ApiProvider</th>
							<th>Switch</th>
					</thead>
					<tbody>
						
							<tr>
								<td>1.</td>
								<th>TBO Hotel</th>
								<td><span >
                 <label class="switch"> <%-- ${tboHotelActive} --%><input type="checkbox" name="tboHotelActive"    data-id="${id}"
                  class="activeStatus" <c:if test="${tboHotelActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
								<td>
								
								<input type="hidden" id="tboHotelEnvironment" name="tboHotelEnvironment">
							<select  class="form-control input-sm"
								name="tboHotelId"  id="tboHotelId"    required="required">
									
								<s:iterator value="apiProviderTboConfigs" status="rowCount">
									<option value="${id}" >${title}_${environment}</option>
								</s:iterator>
							 </select>
						 	</td>
							</tr>
							<tr>
								<td>2.</td>
								<th>TBO Flight</th>
								<td><span >
                 <label class="switch"> <%-- ${tboFlightActive} --%><input type="checkbox"   name="tboFlightActive"    data-id="${id}"
                  class="activeStatus" <c:if test="${tboFlightActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
								<td>
								<input type="hidden" id="tboFlightEnvironment" name="tboFlightEnvironment">
							<select  class="form-control input-sm"
								  name="tboFlightId"  id="tboFlightId"      required="required">
								  
								<s:iterator value="apiProviderTboConfigs" status="rowCount">
								<option value="${id}" >${title}_${environment}</option>
								</s:iterator>
							 </select>
						 	</td>
							</tr>
							
					</tbody>
				</table>
			</div>
			</c:if>
			<c:if test="${apiProviderDesiyaConfigs!=null && apiProviderDesiyaConfigs.size()>0}">
			<div class="table-responsive dash-table">
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>ApiProvider</th>
							<th>API Switch</th>
							 
					</thead>
					<tbody>
						 
							<tr>
								<td>Desiya</td>
								<td><span >
                 <label class="switch"> <%-- ${desiyaActive} --%><input type="checkbox"  name="desiyaActive"  data-id="${id}"
                  class="activeStatus" <c:if test="${desiyaActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
							</tr>
						 
					</tbody>
				</table>
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							
							<th>ApiProvider</th>
							<th>Switch</th>
					</thead>
					<tbody>
						
							<tr>
								<td>1.</td>
								<th>Desiya Hotel</th>
								<td><span >
                 <label class="switch"> <%-- ${desiyaHotelActive} --%><input type="checkbox" name="desiyaHotelActive"    data-id="${id}"
                  class="activeStatus" <c:if test="${desiyaHotelActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
								<td>
								<input type="hidden" id="desiyaHotelEnvironment" name="desiyaHotelEnvironment" >
							<select  class="form-control input-sm"
								name="desiyaHotelId"  id="desiyaHotelId"    required="required">
								
								<s:iterator value="apiProviderDesiyaConfigs" status="rowCount">
									<option value="${id}" >${title}_${environment}</option>
								</s:iterator>
							 </select>
						 	</td>
							</tr>
							
					</tbody>
				</table>
							
			</div>
			</c:if>
			<c:if test="${apiProviderBluestarConfigs!=null && apiProviderBluestarConfigs.size()>0}">
			<div class="table-responsive dash-table">
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>ApiProvider</th>
							<th>API Switch</th>
					</thead>
					<tbody>
						 
							<tr>
								<td>Bluestar</td>
								<td><span >
                 <label class="switch"> <%-- ${bluestarActive} --%><input type="checkbox"  name="bluestarActive"  data-id="${id}"
                  class="activeStatus" <c:if test="${bluestarActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
							</tr>
						 
					</tbody>
				</table>
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							
							<th>ApiProvider</th>
							<th>Switch</th>
					</thead>
					<tbody>
						
							
							<tr>
								<td>1.</td>
								<th>Bluestar Flight</th>
								<td><span >
                 <label class="switch"> <%-- ${bluestarFlightActive} --%><input type="checkbox"   name="bluestarFlightActive"    data-id="${id}"
                  class="activeStatus" <c:if test="${bluestarFlightActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
								<td>
									<input type="hidden" id="bluestarFlightEnvironment" name="bluestarFlightEnvironment">
							<select  class="form-control input-sm"
								  name="bluestarFlightId" id="bluestarFlightId"  required="required">
								<s:iterator value="apiProviderBluestarConfigs" status="rowCount">
								<option value="${id}">${title}_${environment}</option>
								</s:iterator>
							 </select>
						 	</td>
							</tr>
							
					</tbody>
				</table>
				
				<!-- <div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="submit" class="btn btn-primary btn-lg">Add</button>
					</div>
				</div> -->
			</div>
			</c:if>
			
			<c:if test="${apiProviderEtravelSmartConfigList!=null && apiProviderEtravelSmartConfigList.size()>0}">
			<div class="table-responsive dash-table">
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>ApiProvider</th>
							<th>API Switch</th>
							 
					</thead>
					<tbody>
						 
							<tr>
								<td>e-Travel Smart</td>
								<td><span >
                 <label class="switch"> <input type="checkbox"  name="etravelActive"  data-id="${id}"
                  class="activeStatus" <c:if test="${etravelActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
							</tr>
						 
					</tbody>
				</table>
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							
							<th>ApiProvider</th>
							<th>Switch</th>
					</thead>
					<tbody>
						
							<tr>
								<td>1.</td>
								<th>e-Travel Bus</th>
								<td><span >
                 <label class="switch"><input type="checkbox" name="etravelBusActive"    data-id="${id}"
                  class="activeStatus" <c:if test="${etravelBusActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
								<td>
								<input type="hidden" id="etravelBusEnvironment" name="etravelBusEnvironment" >
							<select  class="form-control input-sm"
								name="etravelBusId"  id="etravelBusId"    required="required">
								
								<s:iterator value="apiProviderEtravelSmartConfigList" status="rowCount">
									<option value="${id}" >${title}_${environment}</option>
								</s:iterator>
							 </select>
						 	</td>
							</tr>
							
					</tbody>
				</table>
							
			</div>
			</c:if>
			
			<c:if test="${apiProviderTrawellTagConfigList!=null && apiProviderTrawellTagConfigList.size()>0}">
			<div class="table-responsive dash-table">
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>ApiProvider</th>
							<th>API Switch</th>
							 
					</thead>
					<tbody>
							<tr>
								<td>Trawell Tag</td>
								<td><span >
                 <label class="switch"> <input type="checkbox"  name="trawellTagActive"  data-id="${id}"
                  class="activeStatus" <c:if test="${trawellTagActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
							</tr>
						 
					</tbody>
				</table>
				<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							
							<th>ApiProvider</th>
							<th>Switch</th>
					</thead>
					<tbody>
						
							<tr>
								<td>1.</td>
								<th>Trawell Tag Bus</th>
								<td><span >
                 <label class="switch"><input type="checkbox" name="trawellTagInsuranceActive"    data-id="${id}"
                  class="activeStatus" <c:if test="${trawellTagInsuranceActive==true}">checked</c:if> >
                  <div class="slider"></div>
                </label> 
               </span></td>
								<td>
								<input type="hidden" id="trawellTagInsuranceEnvironment" name="trawellTagInsuranceEnvironment" >
							<select  class="form-control input-sm"
								name="trawellTagInsuranceId"  id="trawellTagInsuranceId"    required="required">
								
								<s:iterator value="apiProviderTrawellTagConfigList" status="rowCount">
									<option value="${id}" >${title}_${environment}</option>
								</s:iterator>
							 </select>
						 	</td>
							</tr>
							
					</tbody>
				</table>
							
			</div>
			</c:if>
			
			<div class="form-group text-center">
					<div class="col-xs-12 submitWrap text-center">
						<button type="submit" class="btn btn-primary btn-lg">Add</button>
					</div>
				</div>
		</div>
		</form>
	</section>
</div>

<script>
$(document).ready(function(){
var tboHotelMode = $("#tboHotelId option:selected").text();
if(tboHotelMode.endsWith("_live"))
	   $('#tboHotelEnvironment').val("true")
		if(tboHotelMode.endsWith("_test"))
	   $('#tboHotelEnvironment').val("false")
	   var tboFlightMode = $("#tboFlightId option:selected").text();
if(tboFlightMode.endsWith("_live"))
	   $('#tboFlightEnvironment').val("true")
		if(tboFlightMode.endsWith("_test"))
	   $('#tboFlightEnvironment').val("false")

 var desiyaHotelMode = $("#desiyaHotelId option:selected").text();
	 if(desiyaHotelMode.endsWith("_live"))
	   $('#desiyaHotelEnvironment').val("true")
  		if(desiyaHotelMode.endsWith("_test"))
	   $('#desiyaHotelEnvironment').val("false")

 var bluestarHotelMode = $("#bluestarFlightId option:selected").text();
   if(bluestarHotelMode.endsWith("_live"))
	   $('#bluestarFlightEnvironment').val("true")
  		if(bluestarHotelMode.endsWith("_test"))
	   $('#bluestarFlightEnvironment').val("false")
	   
 var etravelBusMode = $("#etravelBusId option:selected").text();
   if(etravelBusMode.endsWith("_live"))
	   $('#etravelBusEnvironment').val("true")
   if(etravelBusMode.endsWith("_test"))
	   $('#etravelBusEnvironment').val("false")
	   
 var trawellTagInsuranceMode = $("#trawellTagInsuranceId option:selected").text();
   if(trawellTagInsuranceMode.endsWith("_live"))
	   $('#trawellTagInsuranceEnvironment').val("true")
   if(trawellTagInsuranceMode.endsWith("_test"))
	   $('#trawellTagInsuranceEnvironment').val("false")

});
 
$('.activeStatus').change(function (){
	 var status = false;
	 var id = $(this).data("id");
	 if($(this).is(":checked")==false)
	  {
	   status= false;
	   $(this).attr('checked', false);
	   $(this).attr('value', false);
	  }
	 else{
	   status= true;
	   $(this).attr('checked', true);
	   $(this).attr('value', true);
	  }
	 /* alert(status); */
});

$(".allAPIActiveStatus:checkbox").change(function() { 
	 var status = false;
	 var id = $('.activeStatus');
	 console.log('here');
	 if($(this).is(":checked")==false)
	  {
	   status= false;
	   $('input:checkbox').attr('checked',false);
	   $('input:checkbox').attr('value', false);
	  }
	 else{
	   status= true;
	   $('input:checkbox').attr('checked',true);
	   $('input:checkbox').attr('value', true);
	  }
	
});

/* $('.allAPIActiveStatus:checkbox').change(function () {
    if($(this).attr("checked")) $('input:checkbox').attr('checked','checked');
    else $('input:checkbox').removeAttr('checked');
}); */

$('#tboHotelId').change(function() { 
   var name = $("#tboHotelId option:selected").text();
  	   if(name.endsWith("_live"))
	   $('#tboHotelEnvironment').val("true")
  		if(name.endsWith("_test"))
	   $('#tboHotelEnvironment').val("false")
    
    });
$('#tboFlightId').change(function() { 
   var name = $("#tboFlightId option:selected").text();
   if(name.endsWith("_live"))
	   $('#tboFlightEnvironment').val("true")
  		if(name.endsWith("_test"))
	   $('#tboFlightEnvironment').val("false")
   
    });
    
$('#desiyaHotelId').change(function() { 
   var name = $("#desiyaHotelId option:selected").text();
	 if(name.endsWith("_live"))
	   $('#desiyaHotelEnvironment').val("true")
  		if(name.endsWith("_test"))
	   $('#desiyaHotelEnvironment').val("false")
    });
$('#bluestarFlightId').change(function() { 
   var name = $("#bluestarFlightId option:selected").text();
   if(name.endsWith("_live"))
	   $('#bluestarFlightEnvironment').val("true")
  		if(name.endsWith("_test"))
	   $('#bluestarFlightEnvironment').val("false")

    });
    
$('#etravelBusId').change(function() { 
	   var name = $("#etravelBusId option:selected").text();
	   if(name.endsWith("_live"))
		   $('#etravelBusEnvironment').val("true")
	  		if(name.endsWith("_test"))
		   $('#etravelBusEnvironment').val("false")

	    });

$('#trawellTagInsuranceId').change(function() { 
	   var name = $("#trawellTagInsuranceId option:selected").text();
	   if(name.endsWith("_live"))
		   $('#trawellTagInsuranceEnvironment').val("true")
	  		if(name.endsWith("_test"))
		   $('#trawellTagInsuranceEnvironment').val("false")

	    });


</script>

<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

