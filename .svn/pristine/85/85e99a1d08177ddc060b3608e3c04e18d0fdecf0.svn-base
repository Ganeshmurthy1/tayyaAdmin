<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html data-ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 
 
    <link href="<s:text name="global.Appcss"></s:text>" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="//cdn.datatables.net/1.10.1/css/jquery.dataTables.css" />
 <script src="js/angular.js"></script>

  <script src="js/angular-sanitize.js"></script>
   <script src="js/angular-resource.min.js"></script>
    <script src="js/angular-datatables.min.js"></script>   
    <script src="js/jquery.dataTables.js"></script>
   <script src="js/tree.js"></script>

 <!-- <input type="text"  id="{{data.ix}}autoinput" placeholder="" style="display:none;" auto-complete-directives ui-items="names" data-ng-model="twoorigin">
 -->
 
 <script type="text/ng-template"  id="tree_item_renderer.html">
<div ui-tree-handle class="tree-node tree-node-content">
 <a class="btn btn-success btn-xs" data-nodrag ng-click="toggle(this)"><span class="glyphicon" ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>
   
<span ng-show="data.ndt != 1"> {{applicableNamesMap[data.tp]}} ({{data.nm}} )   </span>
   
    
    <span ng-show="data.isc==false" id="{{data.ix}}form">
    <span id="{{data.ix}}editform" ng-show="data.tp!=18">      
   			<select ng-change="update(this,data.ix)" item="{{data.ix}}" id="someselect{{data.ix}}" ng-model=firstitem>    
     			<option ng-repeat="applicable in types" value="{{applicable.id}}-{{applicable.name}}">{{applicable.name}}</option>
    		</select>
    		<select id="{{data.ix}}list" style="display:none;">    
    			<option ng-repeat="item in list" value="{{item.name}}">{{item.name}}</option>
    		</select>  		
			<input type="text" name="commoninput" id="{{data.ix}}input" placeholder="" style="display:none;" >
            <input type="text" name="commoninput" id="{{data.ix}}ibobinput" ng-model="myDate" placeholder="" style="display:none;" >		
    </span>
	<span id="{{data.ix}}editformamount" ng-show="data.tp==18">      
   			Amount  		
			<input type="text" name="commoninput" id="{{data.ix}}amountinput" placeholder="" style="display:none;" value="{{data.nm}}">	
    </span>
		
      	<button class="btn btn-success" ng-click="confirmChild(data,$index)" >ok</button> 
 	</span>


 
 <span ng-show="data.ndt == 1 && data.isc==true" id="{{data.ix}}form"> 
  {{applicableNamesMap[data.tp]}} ({{data.nm}})
 <button ng-click="EditAmount(data,$index)" class="btn btn-default" ng-show="data.tp == 18">Edit</button>
 <button ng-click="Edit(data,$index)" class="btn btn-default" ng-show="data.tp != 18">Edit</button>
  </span>


    <button ng-click="addForm(data,'india',1)" class="btn btn-info" id="addbutton{{data.ndt}}" ng-show="data.ndt != 1">+</button> 
    
     
    
      <button ng-click="deleteMe(data,$index)" class="btn btn-danger" ng-show="data.ndt == 1 ">-</button>
    
    
    
     <button ng-click="deleteAll(data,$index)" class="btn btn-warning" ng-show="data.ndt == 2 || data.ndt == 3"> - All</button>
    
</div>  
  
<div class="tree-view-stuc1">
<ol ng-model="data.index" ng-class="{hidden: collapsed}">   
        <li ng-repeat="data in data.nds" ui-tree-node ng-include="'tree_item_renderer.html'">        
        </li>
  </ol>  

</div>
</script>
<script></script>
 <script type="text/javascript">
        function update(obj) {   
        	console.log(obj.id);
        	console.log(obj.value)
        	var index = document.getElementById(obj.id).attr('item');    
        	
        	console.log(index)
            angular.element(document.getElementById(obj.id)).scope().update(obj.value,index);
        }
        function changeiataoption(obj) {     
        	console.log(obj.id);
        	var iatacode = $("#"+obj.id).attr('item');        
        	var index = obj.id.substr(11);        
           angular.element(document.getElementById(obj.id)).scope().isiatafixedchage(index,obj.value,iatacode);
           $("#iatacomm"+index).val('0.00');   
        }       
        function changeplboption(obj) {        
        	var iatacode = $("#"+obj.id).attr('item');        
        	var index = obj.id.substr(10);        
           angular.element(document.getElementById(obj.id)).scope().isplbfixedchage(index,obj.value,iatacode);
           $("#plbcomm"+index).val('0.00');   
        } 
    </script>


<style type="text/css">
ul {
    / list-style: circle; /
    list-style: none;
}
li {
    margin-left: 20px;
}


.tree-view-stuc li ul.tree-view-stuc1 li::before
{
content: "\25b6";
    display: inline-block;
    margin: 2px 0 0;
    vertical-align: top;
    text-align: center;
    color: #e74c3c;
    font-size: 16px;
    line-height: 18px;
}
.tree-view-stuc li::before{
content: "\25bc";
list-style: circle;
 
}
.tree-view-stuc li{
font-size: 16px;
}
.tree-view-stuc li ul.tree-view-stuc1 li{
font-size: 15px;

}
.tree-view-stuc li ul.tree-view-stuc1 button, .tree-view-stuc li  button{
padding: 1px 10px;
}
.tree-view-stuc li ul.tree-view-stuc1{
padding-left: 5px;
}

.tree-view-stuc li ul.tree-view-stuc1 li{
margin-top: 10px;
}
.tree-view-stuc li ul.tree-view-stuc1 li select
{
width: 100px;
} 
.frv .modal-dialog{
overflow: intial!important;
}

.frv .modal-dialog .modal-content .modal-body{
height: 250px;

overflow: auto;

}
.tree-node-content{
min-width: 600px;
margin-bottom: 15px;

}
</style>

</head>
<body data-ng-controller="TreeController">
 <div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Flight Commission Sheet</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="home"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Dashboard</li>
			</ol> -->
		</section>
		<section class="content" data-ng-style="{'display':loadingdisplay}" data-ng-clock >
		
		<img alt="" src="images/loading.GIF">
		
		</section>
            <section class="content" data-ng-style="{'display':loadeddisplay}" data-ng-clock>
             <div id="hiddenfields"></div>
							<div class="table-responsive dash-table">
                              <div>ValidFrom : <input type="text" placeholder="dd-mm-yyyy" id="twodpd2" value="{{vailddatefrom}}" >
								ValidTo : <input type="text" placeholder="dd-mm-yyyy"  id="twodpd1" value="{{vailddatetill}}" >
								<button type="button" class="btn btn-primary" data-ng-click="Appludate()">Apply</button><img alt="" src="images/divloading.gif" id="applyloading" data-ng-style="{'display' : applydisplay}" data-ng-cloak>
								</div>
								<!-- testing -->

								<div class="box clearfix">
									<table datatable="ng"  dt-options="dtOptions"  class="table table-striped table-bordered"
										cellspacing="0" width="100%">
								<thead>
											<tr>
												<th>S.No</th>
												<th>Airline Name</th>												
												<th>IATA Commission</th>	
												<th>IATA Remark</th>											
												<th>PLB Commission</th>	
												<th>PLB Remarks</th>																						
												<th>Evaluate</th>																				
											</tr>
										</thead>
										<tbody>	
											<%-- <s:iterator value="airlineSheetitemlist" status="rowstatus">				 --%>				
												<tr data-ng-repeat="item in commissionlist" data-ng-clock>
													<td>{{$index + 1}}</td>
													<td>{{item.airline}}</td>
													<td>
													<span data-ng-show="{{isfixed(item,false,$index)}} == true">
													<select id="isiatafixed{{$index}}" item="{{item.iata_code}}" onchange="changeiataoption(this);" >
													<option value="1" selected>Fixed</option>
													<option value="0">Dynamic</option>
													</select>
													</span> 
													<span data-ng-show="{{isfixed(item,false,$index)}} == false">
													<select id="isiatafixed{{$index}}"  item="{{item.iata_code}}" onchange="changeiataoption(this);">
													<option value="0" selected>Dynamic</option>
													<option value="1" >Fixed</option>													
													</select>
													</span>
													  &nbsp &nbsp  <input type="text" name="iataCommission"  value="{{getiatacommission(item)}}" id="iatacomm{{$index}}" maxlength='2'  data-ng-blur="itemchange(this,$index,true)" style="display:inline-block" onkeypress="return isAlphabetKey(event,this);"></td>
												    
												    <td>
												    
												   <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".frv" data-ng-click="showtree(item,false);" ><i class="fa fa-edit"></i></button>
												    <!--  <button type="button" class="btn btn-info" data-toggle="modal" data-target=".frv"><i class="fa fa-eye"></i></button> -->
												    
												    </td>
													<td>
													<span data-ng-show="{{isfixed(item,true,$index)}} == true">
													<select id="isplbfixed{{$index}}" item="{{item.iata_code}}" onchange="changeplboption(this);">
													<option value="0">Dynamic</option>
													<option value="1" selected>Fixed</option>
													
													</select>
													</span> 
													<span data-ng-show="{{isfixed(item,true,$index)}} == false">
													<select id="isplbfixed{{$index}}" item="{{item.iata_code}}" onchange="changeplboption(this);">
													<option value="0" selected>Dynamic</option>
													<option value="1" >Fixed</option>
													
													</select>
													</span>
													  &nbsp &nbsp
													
													
													<input type="text" name="plbCommission" value="{{getplbcommission(item)}}" id="plbcomm{{$index}}" maxlength='2' data-ng-blur="itemchange(this,$index,false)" style="display:inline-block"onkeypress="return isAlphabetKey(event,this);" ></td>
												   <td>
												  <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".frv" data-ng-click="showtree(item,true);" data-ng-model="remarkreadable"><i class="fa fa-edit"></i></button>
												    <!--  <button type="button" class="btn btn-info" data-toggle="modal" data-target=".frv"><i class="fa fa-eye"></i></button> -->
												   
												  </td>
													
													<td><button type="button" class="btn btn-primary" data-ng-show="item.is_updatable == false">Updatable</button><button type="button" id="updatedbutton{{$index}}" class="btn btn-primary" data-ng-show="item.is_updatable == true" data-ng-click="UpdateRowItem(item,$index)">Update</button><img alt="" src="images/divloading.gif" id="rowloading{{$index}}" style="display:none;" ></td>
													
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
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Flight Remarks Validation</h4>
       <div data-ng-bind-html="remarkreadable">
        
        </div>
      </div>
      <div class="modal-body"> 
     
        <ol data-ng-model="tree">
       <li data-ng-repeat="data in tree track by $index" ui-tree-node data-ng-include="'tree_item_renderer.html'"></li>
       </ol>
     
      </div>
      <div class="modal-footer">
      
        <button type="button" class="btn btn-default" data-dismiss="modal" data-ng-click=close(tree);>Close</button>
        <button type="button" class="btn btn-primary" data-ng-click="Submitdata(tree, iata_selected, is_plb)" data-dismiss="modal">Submit</button>
       
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



</section>
	

<script>
		$(document).ready(function() {
			
			$('#twodpd2').datepicker({
			    numberOfMonths: 2,
			    firstDay: 1,
			    dateFormat: 'dd-mm-yy',
			    minDate: '0', 
			  
			    onSelect: function(dateStr) {
			        var d1 = $(this).datepicker("getDate");
			        d1.setDate(d1.getDate() + 0); // change to + 1 if necessary
			        var d2 = $(this).datepicker("getDate");
			        d2.setDate(d2.getDate() + 31); // change to + 29 if necessary
			        $("#twodpd1").datepicker("setDate", null);
			        $("#twodpd1").datepicker("option", "minDate", d1);
			       // $("#twodpd2").datepicker("option", "maxDate", d2);
			       
			    },
			    onClose : function(dateSt)
			    {
			      $("#twodpd1").focus();  
			    }
			});

			$("#twodpd1").datepicker({
			  numberOfMonths: 2,
			    firstDay: 1,
			    dateFormat: 'dd-mm-yy',
			   
			    onSelect: function(dateStr) {
			       
			    }
			});
			
			
		});
		
		var query = (typeof(custom)=="undefined") ? window.location.search.substring(1) : custom;
        var hu = query;     
        var gy = hu.split("&");    
         var vars = [], hash;
         var airlines = [], hash;
        for (i=0;i<gy.length;i++) {
          var ft = gy[i].split("=");  
          myFunction(ft[0],ft[1])
  	  }   
	 function myFunction(name,value) {   	 
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

  <%@ include file="DashboardFooter.jsp"%>
  
 
  <script src="js/remarkstreeview.js"></script>
  
</body>
</html>