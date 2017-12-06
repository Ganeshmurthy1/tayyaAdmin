<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="css/pagination_css.css" rel="stylesheet" type="text/css" />
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Supplier CommonConfig Update</h1>
	</section>
	<!-- Main content -->
	<section class="content">
	<form action="updateCommonConfig" method="post"
				class="form-horizontal" name="myForm">
				<input type="hidden" name="id" value="${id}">
		<!-- Small boxes (Stat box) -->
		<div class="row">
	<%-- <table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<tbody>
							<tr>
								<td>API Configuration Name</td>
								<td>
                		 <input type="text"  name="title"  value="${apiProviderCommonConfig.title}"> 
               				</td>
							</tr>
					</tbody>
					</table> --%>
					<c:if test="${apiProviderTboConfigs!=null && apiProviderTboConfigs.size()>0}">
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
								<td>TBO</td>
								<td><span >
                 <label class="switch"> <input type="checkbox"  name="tboActive"  value="${apiProviderCommonConfig.tboActive}"     data-id="${id}"
                  class="activeStatus activeCheckTbo" <c:if test="${apiProviderCommonConfig.tboActive==true}">checked</c:if>> 
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
							<th>Mode</th>
							<th>Switch</th>
							<th>Environment</th>
					</thead>
					<tbody>
					<tr>
								<td>1.</td>
								<th>TBO Hotel </th>
								<s:if test="apiProviderCommonConfig.tboHotelEnvironment==true">
								<td>Live</td>
								</s:if>
								 <s:else>
								 <td>Test</td>
								 </s:else>
								<td><span >
                  <label class="switch"> <input type="checkbox"  name="tboHotelActive"   value="${apiProviderCommonConfig.tboHotelActive}" data-id="${id}"
                  class="activeStatus checkApiTboHotel" <c:if test="${apiProviderCommonConfig.tboHotelActive==true}">checked</c:if>> 
                  <div class="slider"></div>
                </label> 
               </span></td>
								<td>
								 
								<input type="hidden" id="tboHotelEnvironment" value="${apiProviderCommonConfig.tboHotelEnvironment}"    name="tboHotelEnvironment">
							<select  class="form-control input-sm"
							 name="tboHotelId"  id="tboHotelId">
							<%--  <option    selected="selected"  value="${apiProviderCommonConfig.tboHotelId}" >Select Supplier_Environment </option>  --%>
								<s:iterator value="apiProviderTboConfigs" status="rowCount">
								 <option value="${id}"> ${title}_${environment}</option>
								<%--  <option value="${id}" <c:if test="${apiProviderCommonConfig.apiProviderTboConfigHotel.id==id}"> selected="selected" </c:if>> ${title}_${environment}</option> --%>
								</s:iterator>
							 </select>
						 	</td>
							</tr>
							<tr>
								<td>2.</td>
								<th>TBO Flight </th>
								<s:if test="apiProviderCommonConfig.tboFlightEnvironment==true">
								<td>Live</td>
								</s:if>
								 <s:else>
								 <td>Test</td>
								 </s:else>
								
								
								<td><span >
                 <label class="switch"> <input type="checkbox"  name="tboFlightActive"  value="${apiProviderCommonConfig.tboFlightActive}"   data-id="${id}"
                  class="activeStatus checkApiTboFlight" <c:if test="${apiProviderCommonConfig.tboFlightActive==true}">checked</c:if>> 
                  <div class="slider"></div>
                   </label>
                  </span></td>
								<td>
								
								<input type="hidden" id="tboFlightEnvironment" value="${apiProviderCommonConfig.tboFlightEnvironment}"     name="tboFlightEnvironment">
						<select  class="form-control input-sm" name="tboFlightId" required="required" id="tboFlightId">
								<%--  <option    selected="selected"   value="${apiProviderCommonConfig.tboFlightId}"   >Select Supplier_Environment</option>  --%>
								<s:iterator value="apiProviderTboConfigs" status="rowCount">
								 <option value="${id}"> ${title}_${environment}</option>
								<%--  <option value="${id}" <c:if test="${apiProviderCommonConfig.apiProviderTboConfigFlight.id==id}"> selected="selected" </c:if>> ${title}_${environment}</option> --%>
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
							
							</tr>
							</thead>
					<tbody>
							<tr>
								<td>Desiya</td>
								<td>
								 <span >
                 <label class="switch"> <input type="checkbox"  name="desiyaActive"  value="${apiProviderCommonConfig.desiyaActive}"     data-id="${id}"
                  class="activeStatus activeCheckDesiya" <c:if test="${apiProviderCommonConfig.desiyaActive==true}">checked</c:if>> 
                  <div class="slider"></div>
                   </label>
                  </span>
               </td>
					 </tr>
						 
					</tbody>
					</table>
					
					<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							 <th>ApiProvider</th>
							 <th>Mode</th>
							<th>Switch</th>
							<th>Environment</th>
					</thead>
					<tbody>
						 <tr>
							<td>1.</td>
								<th>Desiya Hotel</th>
									<s:if test="apiProviderCommonConfig.desiyaHotelEnvironment==true">
								<td>Live</td>
								</s:if>
								 <s:else>
								 <td>Test</td>
								 </s:else>
								
								
								<td><span>
                 <label class="switch"> <input type="checkbox"  name="desiyaHotelActive"   value="${apiProviderCommonConfig.desiyaHotelActive}" data-id="${id}"
                  class="activeStatus checkApiDesiya" <c:if test="${apiProviderCommonConfig.desiyaHotelActive==true}">checked</c:if>> 
                  <div class="slider"></div>
                  </label>
                  </span></td>
							<td>
								<input type="hidden" id="desiyaHotelEnvironment" value="${apiProviderCommonConfig.desiyaHotelEnvironment}"       name="desiyaHotelEnvironment" >
							<select  class="form-control input-sm"
								name="desiyaHotelId"  id="desiyaHotelId"    required="required">
								 	<%--  <option    selected="selected"  value="${apiProviderCommonConfig.desiyaHotelId}" >Select Supplier_Environment</option>  --%>
								<s:iterator value="apiProviderDesiyaConfigs" status="rowCount">
									 <option value="${id}"> ${title}_${environment}</option>
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
							
							</tr>
							</thead>
					<tbody>
							<tr>
								<td>Bluestar</td>
								<td>
								  <span >
                 <label class="switch"> <input type="checkbox"  name="bluestarActive"  value="${apiProviderCommonConfig.bluestarActive}"     data-id="${id}"
                  class="activeStatus activeCheckBluestar" <c:if test="${apiProviderCommonConfig.bluestarActive==true}">checked</c:if>> 
                  <div class="slider"></div>
                   </label>
                  </span>
               </td>
					 </tr>
						 
					</tbody>
					</table>
					
					<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							
							<th>ApiProvider</th>
							<th>Mode</th>
							<th>Switch</th>
							<th>Environment</th>
					</thead>
					<tbody>
						 <tr>
							<td>1.</td>
								<th>Bluestar Flight</th>
								<s:if test="apiProviderCommonConfig.bluestarFlightEnvironment==true">
								<td>Live</td>
								</s:if>
								 <s:else>
								 <td>Test</td>
								 </s:else>
								
								<td><span >
                 <label class="switch"> <input type="checkbox"  name="bluestarFlightActive"   value="${apiProviderCommonConfig.bluestarFlightActive}" data-id="${id}"
                  class="activeStatus checkApiBluestar" <c:if test="${apiProviderCommonConfig.bluestarFlightActive==true}">checked</c:if>> 
                  <div class="slider"></div>
                  </label>
                  </span></td>
							<td>
								<input type="hidden" id="bluestarFlightEnvironment" value="${apiProviderCommonConfig.bluestarFlightEnvironment}"       name="bluestarFlightEnvironment" >
							<select  class="form-control input-sm"
								name="bluestarFlightId"  id="bluestarFlightId"    required="required">
								<%--<option    selected="selected"  value="${apiProviderCommonConfig.bluestarFlightId}"   >Select Supplier_Environment</option>  --%>
								<s:iterator value="apiProviderBluestarConfigs" status="rowCount">
								 <option value="${id}"> ${title}_${environment}</option>
								</s:iterator>
							 </select>
						 	</td>
							
							</tr>
							</tbody>
							</table>
					 
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
							
							</tr>
							</thead>
					<tbody>
							<tr>
								<td>e-Travel Smart</td>
								<td>
								  <span >
                 <label class="switch"> <input type="checkbox"  name="etravelActive"  value="${apiProviderCommonConfig.etravelActive}"     data-id="${id}"
                  class="activeStatus activeCheckEtravel" <c:if test="${apiProviderCommonConfig.etravelActive==true}">checked</c:if>> 
                  <div class="slider"></div>
                   </label>
                  </span>
               </td>
					 </tr>
						 
					</tbody>
					</table>
					
					<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							
							<th>ApiProvider</th>
							<th>Mode</th>
							<th>Switch</th>
							<th>Environment</th>
					</thead>
					<tbody>
						 <tr>
							<td>1.</td>
								<th>e-Travel Bus</th>
								<s:if test="apiProviderCommonConfig.etravelBusEnvironment==true">
								<td>Live</td>
								</s:if>
								 <s:else>
								 <td>Test</td>
								 </s:else>
								
								<td><span >
                 <label class="switch"> <input type="checkbox"  name="etravelBusActive"   value="${apiProviderCommonConfig.etravelBusActive}" data-id="${id}"
                  class="activeStatus checkApiEtravelBus" <c:if test="${apiProviderCommonConfig.etravelBusActive==true}">checked</c:if>> 
                  <div class="slider"></div>
                  </label>
                  </span></td>
							<td>
								<input type="hidden" id="etravelBusEnvironment" value="${apiProviderCommonConfig.etravelBusEnvironment}"  name="etravelBusEnvironment" >
							<select  class="form-control input-sm"
								name="etravelBusId"  id="etravelBusId"    required="required">
								<s:iterator value="apiProviderEtravelSmartConfigList" status="rowCount">
								
								<c:choose>
									<c:when test="${id == apiProviderCommonConfig.apiProviderEtravelBusConfig.id}">
										<option value="${id}" selected="selected"> ${title}_${environment}</option>
									</c:when>
									<c:otherwise>
										<option value="${id}"> ${title}_${environment}</option>
									</c:otherwise>
								</c:choose>
								
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
							
							</tr>
							</thead>
					<tbody>
							<tr>
								<td>Trawell Tag</td>
								<td>
								  <span >
                 <label class="switch"> <input type="checkbox"  name="trawellTagActive"  value="${apiProviderCommonConfig.trawellTagActive}"     data-id="${id}"
                  class="activeStatus activeCheckTrawell" <c:if test="${apiProviderCommonConfig.trawellTagActive==true}">checked</c:if>> 
                  <div class="slider"></div>
                   </label>
                  </span>
               </td>
					 </tr>
						 
					</tbody>
					</table>
					
					<table id="mytable" class="table table-striped"
					data-sort-name="name" data-sort-order="desc">
					<thead>
						<tr>
							<th>S.No</th>
							
							<th>ApiProvider</th>
							<th>Mode</th>
							<th>Switch</th>
							<th>Environment</th>
					</thead>
					<tbody>
						 <tr>
							<td>1.</td>
								<th>Trawell Tag Insurance</th>
								<s:if test="apiProviderCommonConfig.trawellTagInsuranceEnvironment==true">
								<td>Live</td>
								</s:if>
								 <s:else>
								 <td>Test</td>
								 </s:else>
								
								<td><span >
                 <label class="switch"> <input type="checkbox"
												name="trawellTagInsuranceActive" id="trawelltag"
												value="${apiProviderCommonConfig.trawellTagInsuranceActive}"
												data-id="${id}" class="activeStatus checkApiInsurance"
												<c:if test="${apiProviderCommonConfig.trawellTagInsuranceActive==true}">checked</c:if>>
												<div class="slider"></div>
                  </label>
                  </span></td>
							<td>
								<input type="hidden" id="trawellTagInsuranceEnvironment" value="${apiProviderCommonConfig.trawellTagInsuranceEnvironment}"  name="trawellTagInsuranceEnvironment" >
							<select  class="form-control input-sm"
								name="trawellTagInsuranceId"  id="trawellTagInsuranceId"    required="required">
								<s:iterator value="apiProviderTrawellTagConfigList" status="rowCount">
								
								<c:choose>
									<c:when test="${id == apiProviderCommonConfig.apiProviderTrawellTagInsuranceConfig.id}">
										<option value="${id}" selected="selected"> ${title}_${environment}</option>
									</c:when>
									<c:otherwise>
										<option value="${id}"> ${title}_${environment}</option>
									</c:otherwise>
								</c:choose>
								
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
						<button type="submit" class="btn btn-primary btn-lg"> Update Common Config</button>
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
	 
});


$('#tboHotelId').change(function() { 
   var name = $("#tboHotelId option:selected").text();
   var tboHotelEnvironment = ${apiProviderCommonConfig.tboHotelEnvironment};
   if(name.endsWith("_live"))
	   $('#tboHotelEnvironment').val("true")
  		else if(name.endsWith("_test"))
	   $('#tboHotelEnvironment').val("false")
	 else 
	   $('#tboHotelEnvironment').val(tboHotelEnvironment)
	   
    
    });
$('#tboFlightId').change(function() { 
   var name = $("#tboFlightId option:selected").text();
   var tboFlightEnvironment = ${apiProviderCommonConfig.tboFlightEnvironment};
   
   if(name.endsWith("_live"))
	   $('#tboFlightEnvironment').val("true")
  		else if(name.endsWith("_test"))
	   $('#tboFlightEnvironment').val("false")
		  else 
		   $('#tboFlightEnvironment').val(tboFlightEnvironment)
		  
    });
    
$('#desiyaHotelId').change(function() { 
   var name = $("#desiyaHotelId option:selected").text();
   var desiyaHotelEnvironment = ${apiProviderCommonConfig.desiyaHotelEnvironment};
	 if(name.endsWith("_live"))
	   $('#desiyaHotelEnvironment').val("true")
  		else if(name.endsWith("_test"))
	   $('#desiyaHotelEnvironment').val("false")
	    else 
		   $('#desiyaHotelEnvironment').val(desiyaHotelEnvironment)
    });
$('#bluestarFlightId').change(function() { 
   var name = $("#bluestarFlightId option:selected").text();
   var bluestarFlightEnvironment = ${apiProviderCommonConfig.bluestarFlightEnvironment};
   if(name.endsWith("_live"))
	   $('#bluestarFlightEnvironment').val("true")
  		else if(name.endsWith("_test"))
	   $('#bluestarFlightEnvironment').val("false")
	   else 
		   $('#bluestarFlightEnvironment').val(bluestarFlightEnvironment)

    });
 
$('#etravelBusId').change(function() { 
	   var name = $("#etravelBusId option:selected").text();
	   var etravelBusEnvironment = ${apiProviderCommonConfig.etravelBusEnvironment};
	   if(name.endsWith("_live"))
		   $('#etravelBusEnvironment').val("true")
	  		else if(name.endsWith("_test"))
		   $('#etravelBusEnvironment').val("false")
		   else 
			   $('#etravelBusEnvironment').val(etravelBusEnvironment)

	    });
	 
</script>
<script type="text/javascript">
$('.checkApiInsurance').change(function (){
	if($(this).is(":checked")==false)
	{
		$(this).attr('checked', false);
		$('.activeCheckTrawell').attr('checked', false);
	}
	else{
		$(this).attr('checked', true);
		$('.activeCheckTrawell').attr('checked', true);
	}
});
$('.checkApiEtravelBus').change(function (){
	if($(this).is(":checked")==false)
	{
		$(this).attr('checked', false);
		$('.activeCheckEtravel').attr('checked', false);
	}
	else{
		$(this).attr('checked', true);
		$('.activeCheckEtravel').attr('checked', true);
	}
});
$('.checkApiDesiya').change(function (){
	var status = false;
	var id = $(this).data("id") ;
	var apiName = $(this).attr("id");
	if($(this).is(":checked")==false)
	{
		$(this).attr('checked', false);
		$('.activeCheckDesiya').attr('checked', false);
	}
	else{
		$(this).attr('checked', true);
		$('.activeCheckDesiya').attr('checked', true);
	}
});
$('.checkApiTboFlight').change(function (){
	if($(this).is(":checked")==true)
	{
		$(this).attr('checked', true);
		$('.activeCheckTbo').attr('checked', true);
	}

});
$('.checkApiTboHotel').change(function (){
	if($(this).is(":checked")==true)
	{
		$(this).attr('checked', true);
		$('.activeCheckTbo').attr('checked', true);
	}

});
$('.checkApiBluestar').change(function (){
	if($(this).is(":checked")==false)
	{
		$(this).attr('checked', false);
		$('.activeCheckBluestar').attr('checked', false);
	}
	else{
		$(this).attr('checked', true);
		$('.activeCheckBluestar').attr('checked', true);
	}
});
</script>
<script type="text/javascript">
$('.activeCheckTrawell').change(function (){
	if($(this).is(":checked")==false)
	{
		$(this).attr('checked', false);
		$('.checkApiInsurance').attr('checked', false);
	}
	else{
		$(this).attr('checked', true);
		$('.checkApiInsurance').attr('checked', true);
	}
});
$('.activeCheckEtravel').change(function (){
	if($(this).is(":checked")==false)
	{
		$(this).attr('checked', false);
		$('.checkApiEtravelBus').attr('checked', false);
	}
	else{
		$(this).attr('checked', true);
		$('.checkApiEtravelBus').attr('checked', true);
	}
});
$('.activeCheckDesiya').change(function (){
	var status = false;
	var id = $(this).data("id") ;
	var apiName = $(this).attr("id");
	if($(this).is(":checked")==false)
	{
		$(this).attr('checked', false);
		$('.checkApiDesiya').attr('checked', false);
	}
	else{
		$(this).attr('checked', true);
		$('.checkApiDesiya').attr('checked', true);
	}
});
$('.activeCheckTbo').change(function (){
	
	if($(this).is(":checked")==false)
	{
		$(this).attr('checked', false);
		$('.checkApiTboFlight').attr('checked', false);
		$('.checkApiTboHotel').attr('checked', false);
	}
	else{
		$(this).attr('checked', true);
		$('.checkApiTboFlight').attr('checked', true);
		$('.checkApiTboHotel').attr('checked', true);
	}

});
$('.activeCheckBluestar').change(function (){
	if($(this).is(":checked")==false)
	{
		$(this).attr('checked', false);
		$('.checkApiBluestar').attr('checked', false);
	}
	else{
		$(this).attr('checked', true);
		$('.checkApiBluestar').attr('checked', true);
	}
});
</script>
<!-- /.content-wrapper -->
<%@ include file="DashboardFooter.jsp"%>

