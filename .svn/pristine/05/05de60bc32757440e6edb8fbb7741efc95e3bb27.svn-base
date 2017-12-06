<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html data-ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 
 
<!-- FontAwesome 4.3.0 -->
 
<link href="<s:text name="global.Appcss"></s:text>" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="//cdn.datatables.net/1.10.1/css/jquery.dataTables.css" />

<script src="js/angular.js"></script>
<script src="js/angular-sanitize.js"></script>
 <script src="js/angular-resource.min.js"></script>
    <script src="js/angular-datatables.min.js"></script>   
    <script src="js/jquery.dataTables.js"></script>
<script src="js/tree.js"></script>

<script type="text/ng-template" id="tree_item_renderer.html">
<div ui-tree-handle class="tree-node tree-node-content">
 <a class="btn btn-success btn-xs" data-nodrag ng-click="toggle(this)"><span class="glyphicon" ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>
   
<span ng-show="data.ndt != 1"> {{applicableNamesMap[data.tp]}} ({{data.nm}} )   </span>
 <span ng-show="data.ndt == 1 && data.isc==true" id="{{data.ix}}form"> 
  {{applicableNamesMap[data.tp]}} ({{data.nm}})
  </span>    
</div>  
  
<div class="tree-view-stuc1">
<ol ng-model="data.index" ng-class="{hidden: collapsed}">   
        <li ng-repeat="data in data.nds" ui-tree-node ng-include="'tree_item_renderer.html'">        
        </li>
  </ol>  

</div>


</script>
<script type="text/javascript">
	function update(obj) {
		angular.element(document.getElementById('someselect')).scope().update(
				obj.value);
	}
	function changeiataoption(obj) {
		var iatacode = $("#" + obj.id).attr('item');
		var index = obj.id.substr(11);
		angular.element(document.getElementById(obj.id)).scope()
				.isiatafixedchage(index, obj.value, iatacode);
	}
	function changeplboption(obj) {
		var iatacode = $("#" + obj.id).attr('item');
		var index = obj.id.substr(10);
		angular.element(document.getElementById(obj.id)).scope()
				.isplbfixedchage(index, obj.value, iatacode);
	}
</script>


<style type="text/css">
ul { /
	list-style: circle; /
	list-style: none;
}

li {
	margin-left: 20px;
}

.tree-view-stuc li ul.tree-view-stuc1 li::before {
	content: "\25b6";
	display: inline-block;
	margin: 2px 0 0;
	vertical-align: top;
	text-align: center;
	color: #e74c3c;
	font-size: 16px;
	line-height: 18px;
}

.tree-view-stuc li::before {
	content: "\25bc";
	list-style: circle;
}

.tree-view-stuc li {
	font-size: 16px;
}

.tree-view-stuc li ul.tree-view-stuc1 li {
	font-size: 15px;
}

.tree-view-stuc li ul.tree-view-stuc1 button, .tree-view-stuc li  button
	{
	padding: 1px 10px;
}

.tree-view-stuc li ul.tree-view-stuc1 {
	padding-left: 5px;
}

.tree-view-stuc li ul.tree-view-stuc1 li {
	margin-top: 10px;
}

.tree-view-stuc li ul.tree-view-stuc1 li select {
	width: 100px;
}

.frv .modal-dialog {
	overflow: intial !important;
}

.frv .modal-dialog .modal-content .modal-body {
	height: 250px;
	overflow: auto;
}

.tree-node-content {
	min-width: 600px;
	margin-bottom: 15px;
}
</style>

</head>
<body data-ng-controller="TreeController">
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Flight Commission Block</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>

		<section class="content" data-ng-style="{'display':loadingdisplay}"
			data-ng-clock>

			<img alt="" src="images/loading.GIF">

		</section>

		<section class="content" data-ng-style="{'display':loadeddisplay}"
			data-ng-clock>
			<div class="table-responsive dash-table">
				<div id="hiddenfields"></div>

				<!-- testing -->

				<div class="box clearfix">

					<div class="col-sm-9 df-ccm">
						<div class="form-group" id="user_form-group">
							<form class="form-inline" action="updatePLBandIATACommissions"
								method="post">
								<div class="form-group">
									<!-- <label for="exampleInputAmount">Company Type</label> -->
									<input type="hidden" name="blockId"
										value="<%=request.getParameter("blockId")%>" id="blockId">

									<div class="input-group">
										<input type="text" placeholder="iata retain %" maxlength='3' 
											class="form-control input-sm"  autocomplete="off"    name="iataAmount" id="iataAmountDefault" onkeypress="return isAlphabetKey(event,this);">
									</div>
									<div class="input-group">
										<input type="text" placeholder="plb retain %" maxlength='3' 
											class="form-control input-sm" autocomplete="off"
											name="plbAmount" id="plbAmountDefault"  onkeypress="return isAlphabetKey(event,this);">
									</div>
								</div>
								<div class="form-group rep-buto">
									<button type="button" class="btn btn-primary"
										onclick="setPlbIATADefault()">Set commissions</button>
										<label id="errorsetdefaultcomm" style="color: red;"></label>
								</div>
							</form>

						</div>
					</div>
 


					<table datatable="ng"  dt-options="dtOptions" class="table table-striped table-bordered" cellspacing="0"
						width="100%">
						<thead>
							<tr>
								<th>S.No</th>
								<th>Airline Name</th>		
								<th>IATA Commission Type</th>
								<th>Your IATA Commission %</th>
								<th>IATA Remark</th>
								<th>Retain your IATA % from affiliate(s)</th>
								<th>PLB Commission Type</th>
								<th>Your PLB Commission %</th>

								<th>PLB Remarks</th>
								<th>Retain your PLB % from affiliate(s)</th>
								<th>Evaluate</th>
							</tr>
						</thead>
						<tbody>
							<%-- <s:iterator value="airlineSheetitemlist" status="rowstatus">				 --%>
							<tr data-ng-repeat="item in commissionlist track by $index">
								<td>{{$index + 1}}</td>
								<td>{{item.airline}}</td>
								<!-- 	<td>{{getiatacommission(item)}}</td>	 -->
								<td>{{getcommissiontype(item,false)}}</td>
								<td><span data-ng-if="!getcommissionlabel(item,false)"
									style="color: red">Refer Remark</span> <span
									data-ng-if="getcommissionlabel(item,false)">{{getiatacommission(item)}}</span>
								</td>
								<td>

									<button type="button" class="btn btn-primary"
										data-toggle="modal" data-target=".frv"
										data-ng-click="showtree(item,false);">
										<i class="fa fa-edit"></i>
									</button>

								</td>
								<td><input type="text" name="iataCommission" maxlength='3' 
									value="{{getiatacommissionretain(item)}}"
									id="iatacomm{{$index}}" class="iatacomm"
									data-ng-blur="itemchange(this,$index,true)"
									style="display: inline-block" onkeypress="return isAlphabetKey(event,this);"> <span
									id="iatacommerr{{$index}}"
									style="display: inherit; color: red;"></span></td>


								<!--  <td>{{getplbcommission(item)}}</td>	 -->
								<td>{{getcommissiontype(item,true)}}</td>
								<td><span data-ng-if="!getcommissionlabel(item,true)"
									style="color: red">Refer Remark</span> <span
									data-ng-if="getcommissionlabel(item,true)" onkeypress="return isAlphabetKey(event,this);">{{getplbcommission(item)}}</span>
								</td>
								<td>
									<button type="button" class="btn btn-primary"
										data-toggle="modal" data-target=".frv"
										data-ng-click="showtree(item,true);"
										data-ng-model="remarkreadable">
										<i class="fa fa-edit"></i>
									</button>
								</td>
								<td><input type="text" name="plbCommission" maxlength='3' 
									value="{{getplbcommissionretain(item)}}" id="plbcomm{{$index}}"
									data-ng-blur="itemchange(this,$index,false)" class="plbcomm"
									style="display: inline-block"> <span
									id="plbcommerr{{$index}}" style="display: inherit; color: red;"></span>
								</td>


								<td><button type="button" class="btn btn-primary"
										data-ng-show="item.is_updatable == false">Updatable</button>
									<button type="button" id="updatedbutton{{$index}}"
										class="btn btn-primary"
										data-ng-show="item.is_updatable == true"
										data-ng-click="UpdateRowItem(item,$index)">Update</button> <img
									alt="" src="images/divloading.gif" id="rowloading{{$index}}"
									style="display: none;"></td>

							</tr>



							<%-- </s:iterator> --%>
						</tbody>
					</table>

				</div>
				<!-- /.box -->
			</div>

			<!-- PLB Remark -->

			<div class="modal fade frv" tabindex="-1" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content clearfix">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Flight Remarks Validation</h4>
							<div data-ng-bind-html="remarkreadable"></div>
						</div>
						<div class="modal-body">
							<ol data-ng-model="tree">
								<li data-ng-repeat="data in tree track by $index" ui-tree-node
									data-ng-include="'tree_item_renderer.html'"></li>
							</ol>
						</div>
						<div class="modal-footer">

							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>


						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->



		</section>


		<script>
			function setPlbIATADefault() {
 				var blockIdValue = $("#blockId").val();
				 var plbAmountValue = $("#plbAmountDefault").val();
				 var iataAmountValue = $("#iataAmountDefault").val();
				 var totUrl = $(location).attr('href');
				var newUrl = totUrl.substr(0, totUrl.lastIndexOf('/') + 1);
				var finalUrl = newUrl + "updatePLBandIATACommissions";
				// console.log("finalUrl..." + finalUrl);
				if(parseInt(plbAmountValue) <= 100 && parseInt(iataAmountValue) <= 100)
				{
				$.ajax({
					method : "POST",
					url : finalUrl,					
					data : {
						blockId : blockIdValue,
						iataAmount : iataAmountValue,
						plbAmount : plbAmountValue
					},					
					success : function(data, status) {
						console.log("success data-------" + status);
						console.log("target url-------"
								+ $(location).attr('href'));
						window.location.assign($(location).attr('href'));
					},
					error : function(xhr, status, error) {
						console.log("error data-------" + status + "error "
								+ error);
						console.log("target url-------"
								+ $(location).attr('href'));
						window.location.assign($(location).attr('href'));
					}
				});
				}
				else{
					 $("#errorsetdefaultcomm").text('Maximum 100 percentage alloweed.');
				}
			}

			$(document).ready(function() {

				$('#twodpd2').datepicker({
					numberOfMonths : 2,
					firstDay : 1,
					dateFormat : 'dd-mm-yy',
					minDate : '0',

					onSelect : function(dateStr) {
						var d1 = $(this).datepicker("getDate");
						d1.setDate(d1.getDate() + 0); // change to + 1 if necessary
						var d2 = $(this).datepicker("getDate");
						d2.setDate(d2.getDate() + 31); // change to + 29 if necessary
						$("#twodpd1").datepicker("setDate", null);
						$("#twodpd1").datepicker("option", "minDate", d1);
						// $("#twodpd2").datepicker("option", "maxDate", d2);

					},
					onClose : function(dateSt) {
						$("#twodpd1").focus();
					}
				});

				$("#twodpd1").datepicker({
					numberOfMonths : 2,
					firstDay : 1,
					dateFormat : 'dd-mm-yy',

					onSelect : function(dateStr) {

					}
				});

			});

			var query = (typeof (custom) == "undefined") ? window.location.search
					.substring(1)
					: custom;
			var hu = query;
			var gy = hu.split("&");
			var vars = [], hash;
			var airlines = [], hash;
			for (i = 0; i < gy.length; i++) {
				var ft = gy[i].split("=");
				myFunction(ft[0], ft[1])
			}
			function myFunction(name, value) {
				var x = document.createElement("INPUT");
				x.setAttribute("type", "hidden");

				x.setAttribute("id", name);

				x.setAttribute("name", name);
				x.setAttribute("value", decodeURIComponent(value));
				$("#hiddenfields").append(x);
			}
			
			function isAlphabetKey(evt,textbox){
				
		         evt = (evt) ? evt : window.event;
		          var charCode = (evt.which) ? evt.which : evt.keyCode;
		          if(textbox.value.trim() == ''){
		          	if(charCode == 32)
		          	       return false;
		          }          
		           
		          if (charCode > 31 && (charCode < 48 || charCode > 57))  {
		              return false;
		          }      
		          return true;
		    }
			
		</script>


		<script src="js/remarkstreeviewdistributor.js"></script>
		<%@ include file="DashboardFooter.jsp"%>
		<Script>
			
		</Script>
</body>
</html>