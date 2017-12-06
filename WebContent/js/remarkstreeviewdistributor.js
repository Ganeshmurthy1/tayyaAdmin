var app = angular.module("myApp", ['ngSanitize','ui.tree','datatables', 'ngResource']);
app.config(['$httpProvider', function($httpProvider) {    
	$httpProvider.defaults.useXDomain = true;       
	delete $httpProvider.defaults.headers.common['X-Requested-With']; 
}
]);
app.controller("TreeController", ['$scope','$http','$element','DTOptionsBuilder', 'DTColumnBuilder', function($scope,$http,$element,DTOptionsBuilder, DTColumnBuilder) {
	
	$scope.loadeddisplay = "none";
	$scope.loadingdisplay = "show";
	//Load Commission From DB
	
	var blockId =  angular.element(document.getElementById('blockId'));
	var companyid =  angular.element(document.getElementById('companyId'));
	var configid =  angular.element(document.getElementById('configId'));
	
	$scope.applicableNamesMap = {"0":"NIL"};
	$scope.applicableNamesMap[1] = "All";
	$scope.applicableNamesMap[2] = "Class";
	$scope.applicableNamesMap[3] = "Airline";
	$scope.applicableNamesMap[4] = "Sector";
	$scope.applicableNamesMap[5] = "Country";     
	$scope.applicableNamesMap[7] = "Travel Country";
	$scope.applicableNamesMap[8] = "City";
	$scope.applicableNamesMap[9] = "Soto";
	$scope.applicableNamesMap[16] = "Route";     
	$scope.applicableNamesMap[17] = "Tour";
	$scope.applicableNamesMap[15] = "Fare";
	$scope.applicableNamesMap[14] = "Passenger";
	$scope.applicableNamesMap[12] = "Codeshare";
	$scope.applicableNamesMap[13] = "Interline";
	$scope.applicableNamesMap[10] = "IB";
	$scope.applicableNamesMap[11] = "OB";
	$scope.applicableNamesMap[18] = "Amount";
	$scope.applicableNamesMap[100] = "Remarks";
	
	var totUrl = $(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	var finalUrl = newUrl+"airlinecommissionblocklist.action";
	//console.log("....Final URL....."+finalUrl);

	$http({
		method : 'POST',
		url : finalUrl,
		data : 'Blockid=' + JSON.stringify({"blockid":blockId.val()}),
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded'
		}
	}).success(function(data) {
		if (data.errors) {
			
		} else {
			console.log(data);				
			$scope.commissionlist = data.jsonResult.sheet;
			   
			$scope.loadingdisplay = "none";
			$scope.loadeddisplay = "show";
		}
	}).error(function(data, status) {
		return false;
	});
	
	 $scope.dtOptions = DTOptionsBuilder.newOptions()
     .withPaginationType('full_numbers')
     .withDisplayLength(100)
     .withOption('lengthChange', true);

	$scope.applydisplay = "show";

	
	$scope.getiatacommission = function(currentitem)
	{
		var iatacomm  = JSON.parse(currentitem.row_item).ic;
		return parseFloat(iatacomm).toFixed(2);
	}
	$scope.getplbcommission = function(currentitem)
	{
		var plbcomm  = JSON.parse(currentitem.row_item).pc;
		return parseFloat(plbcomm).toFixed(2);		
	}
	
	$scope.getiatacommissionretain = function(currentitem)
	{
		var iatacomm  = JSON.parse(currentitem.block_item).icr;
		return parseFloat(iatacomm).toFixed(2);
	}
	$scope.getplbcommissionretain = function(currentitem)
	{
		var plbcomm  = JSON.parse(currentitem.block_item).pcr;
		return parseFloat(plbcomm).toFixed(2);		
	}
	$scope.getcommissionlabel = function(currentitem, isplb)
	{
		var commtype  = JSON.parse(currentitem.row_item).isi;
		if(isplb)
			commtype  = JSON.parse(currentitem.row_item).isp;	
		
		return commtype;		
		
	}
	
	$scope.getcommissiontype = function(currentitem, isplb)
	{
		var commtype  = JSON.parse(currentitem.row_item).isi;
		if(isplb)
			commtype  = JSON.parse(currentitem.row_item).isp;
		var typelabel;
		if(commtype)
			typelabel = "Fixed";
		else
			typelabel = "Dynamic";
		
		return typelabel;
	}

	function traverse(superparent, parent) {
		counter ++;
		//console.log('counter----'+counter);
		for (var j=0; j < parent.length; j++) {
			var featureName=parent[j].name; 
			var value=parent[j].displaytype; 
			var nodes = parent[j].nodes;
			//console.log('--index--'+parent[j].index);
			//console.log('featureName----'+featureName);
			//console.log('value----'+value);
			//console.log('nodes----'+nodes.length);			
			if(nodes != null && nodes.length > 0)
			{
				nodes = traverse(parent[j], nodes);
			}
			//parent[j].index = counter;
			parent[j].nodes = nodes;
			parent[j].parent = parent;
			//console.log('nodes--modified--'+parent[j].nodes);
			//console.log('nodes--index--'+parent[j].index);
		}
		//console.log('parent--modified--'+parent);
		return parent;

	}  
	var counter = 0;

	function createReadable(parentreadable, parent) { 
		counter ++;
		//  console.log("########traverse counter.."+counter);

		//var readable = parentreadable ;
		var readable = "";
		for (var j=0; j < parent.length; j++) {
			var featureName=parent[j].nm; 
			//var displaytype=parent[j].displaytype; 
			var displaytype=$scope.applicableNamesMap[parent[j].tp]; 
			var displayname=parent[j].nm; 
			var nds = parent[j].nds;
			if(parent[j].ndt == 0)
			{    
				readable = "<b>Remarks :</b>" +"&nbsp;"+ createReadable(readable,nds);    
				//readable = readable + childreadable;
			}
			else if(parent[j].ndt == 1)
			{ 
				if(j ==  (parent.length -1)) {

					var childreadable = createReadable(readable,nds);
					if(nds == null && nds.length==0) {
						readable =  readable + displaytype +"&nbsp;"+"<b>"+ displayname +"</b>" + childreadable  ;
					}
					else{  

						if(displaytype == "Amount")
							readable =  readable +"<b>"+ displayname + "</b>"+"&nbsp;"+ "% Percentage" +  childreadable  ; 
						else
							readable =  readable + displaytype +"&nbsp;"+"<b>"+ displayname  +"</b>" + childreadable  ; 
					}

				}
				else{
					var childreadable = createReadable(readable,nds);
					readable =   readable + displaytype +"&nbsp;"+"<b>"+ displayname +"</b>"+ childreadable + ',';

				} 
			}
			else  if(parent[j].ndt == 2)
			{
				if(nds != null && nds.length>0)
				{     
					readable = readable + " for " + createReadable(readable, nds);    
				}


			}
			else  if(parent[j].ndt == 3)
			{
				if(nds != null && nds.length>0)
				{     
					readable = readable + " not for "+ createReadable(readable, nds);     
				}
			}
			//console.log("readable is..."+readable);
		} 

		return readable;
	}
	
	
	
	$scope.itemchange = function(textboxitem,index,is_iata){
           
		angular.element(document.getElementById('iatacommerr'+index).innerHTML = '');
		angular.element(document.getElementById('plbcommerr'+index).innerHTML = '');
		angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Updatable');
		
		var currentitem ;
		if(is_iata)
		   currentitem =  angular.element(document.getElementById('iatacomm'+index));
		else
			currentitem =  angular.element(document.getElementById('plbcomm'+index));
		
		//console.log(currentitem.val());
		var commissionlistnew  = [];
		angular.forEach($scope.commissionlist, function(rowitemvalue,rowitemindex) { 
			if(rowitemvalue.iata_code === textboxitem.item.iata_code)
			{			
				//console.log("before row data"+rowitemvalue.block_item );
				var blockjson = JSON.parse(rowitemvalue.block_item);
				var rowjson = JSON.parse(rowitemvalue.row_item);	
				if(is_iata){										
						blockjson.icr = currentitem.val();					
				}				  
				else{
					// console.log("plb");
					blockjson.pcr = currentitem.val();
				}				
				  
				
				var rowcache = [];
				var rowstr = JSON.stringify(blockjson, function(key, value) {
					if (typeof value === 'object' && value !== null) {
						if (rowcache.indexOf(value) !== -1) {
							// Circular reference found, discard key
							return;
						}
						// Store value in our collection
						rowcache.push(value);
					}
					return value;
				});
				rowcache = null;
				
				/*console.log(parseFloat(rowjson.ic));
				console.log(parseFloat(currentitem.val()));
				console.log( parseFloat(currentitem.val()) < parseFloat(rowjson.ic));
				console.log(parseFloat(currentitem.val()) < parseFloat(rowjson.ic));*/
				
				var commtype  = JSON.parse(rowitemvalue.row_item).isp; 
				if(is_iata)
					commtype = JSON.parse(rowitemvalue.row_item).isi;
					
				
				//rowitemvalue.is_updatable = true;
				
				console.log(parseInt(currentitem.val()));
				if(parseInt(currentitem.val()) > 100){
					if(is_iata)
						angular.element(document.getElementById('iatacommerr'+index).innerHTML = 'Retain Error');
					else
						angular.element(document.getElementById('plbcommerr'+index).innerHTML = 'Retain Error');	
					
					
					rowitemvalue.is_updatable = false;
				
				}
				else
					{
					if(is_iata){
						rowitemvalue.block_item = rowstr;
						rowitemvalue.is_updatable = true;
						 angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');	
					}
					else{
						rowitemvalue.block_item = rowstr;
						rowitemvalue.is_updatable = true;
						 angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');
					}
					}
				
				/*if(commtype){
					rowitemvalue.block_item = rowstr;
					rowitemvalue.is_updatable = true;
					 angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');
				if(parseFloat(currentitem.val()) < parseFloat(rowjson.pc)){
					rowitemvalue.block_item = rowstr;
					rowitemvalue.is_updatable = true;
					 angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');
				}else if(parseFloat(currentitem.val()) < parseFloat(rowjson.ic)){
					rowitemvalue.block_item = rowstr;
					rowitemvalue.is_updatable = true;
					 angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');					
				}
				else{
					if(is_iata)
						angular.element(document.getElementById('iatacommerr'+index).innerHTML = 'Retain Error');
					else
						angular.element(document.getElementById('plbcommerr'+index).innerHTML = 'Retain Error');	
					
					
					rowitemvalue.is_updatable = false;
				}
				}
				else	
					{
					if(is_iata){
						rowitemvalue.block_item = rowstr;
						rowitemvalue.is_updatable = true;
						 angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');	
					}
					else{
						rowitemvalue.block_item = rowstr;
						rowitemvalue.is_updatable = true;
						 angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');
					}
						
					}*/
				
				//console.log("after row data"+rowitemvalue.block_item );
				}
			commissionlistnew.push(rowitemvalue);
		});

		$scope.commissionlist = commissionlistnew;
		
	}
	
	
	
	
	
	$scope.populatetree = function(remark){	
		$scope.tree = traverse(null, remark);
		//console.log("updated tree----"+$scope.tree);
	}	


	$scope.showtree = function(item,is_plb_tree){	
		
		/*var plb_remark = null;
		var iata_remark = null;
		var sheetid = JSON.parse(item.block_item).si;
		
		var totUrl = $(location).attr('href');
		var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
		//var finalUrl = newUrl+"airlinesheetcommissionremark.action";	
		var finalUrl = newUrl+"airlinecommissionsheetremark.action";	
		
		$http({
			method : 'POST',
			url : finalUrl,
			data : 'remarkitem=' + JSON.stringify({"iata_code":item.iata_code,"companyid":companyid.val(),"configid":configid.val()}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data) {
			if (data.errors) {

			} else {
				console.log(data);	
				
				if(is_plb_tree)
				   plb_remark = JSON.parse(data.jsonResult.remark).pr;
				else
					iata_remark = JSON.parse(data.jsonResult.remark).ir;
		*/
			
				
		    var plb_remark = JSON.parse(item.row_item).pr;
		    var iata_remark = JSON.parse(item.row_item).ir;
		    
		    console.log('iata_remark' + iata_remark);
		
				$scope.iata_selected = item.iata_code; 	
				if(is_plb_tree){
					$scope.is_plb = true;					
					if(plb_remark != null && plb_remark != ''){
						$scope.populatetree(JSON.parse(plb_remark));
					}else{
						$scope.tree = [{ix:0, pr:[],ndt:0, nm: "customize", tp:"100",nds: [], isc:true}];
					}		
				}
				else{
					
					$scope.is_plb = false;
					if(iata_remark != null && iata_remark != ''){
						$scope.populatetree(JSON.parse(iata_remark)); 
					}else{
						$scope.tree = [{ix:0, pr:[],ndt:0, nm: "customize", tp:"100", nds: [], isc:true}];
					}
				}
				$scope.remarkreadable = createReadable("", $scope.tree); 

			
	
		

		//$scope.tree = [{ix:0, pr:[],ndt:0, nm: "customize", tp:"100", nds: [], isc:true}];
		//ix for index, ndt for nodetype, nm for name, tp for type, nds for nodes, isc for isonfirm
	}
	
	
	/*$scope.showtree = function(item,is_plb_tree){		
		$scope.iata_selected = item.iata_code; 	
		if(is_plb_tree){
			$scope.is_plb = true;
			var plbRemark = JSON.parse(item.row_item).plbRemark;		
			if(plbRemark != null){
				$scope.populatetree(JSON.parse(plbRemark));
			}else{
				$scope.tree = [{index:0, parent:[],nodetype:0, name: "customize", type:"100", displaytype:'No Remarks', nodes: [], editarea:"",isconfirm:true}];
			}		
		}
		else{
			var iataremark = JSON.parse(item.row_item).iataRemark;	
			$scope.is_plb = false;
			if(iataremark != null){
				$scope.populatetree(JSON.parse(iataremark)); 
			}else{
				$scope.tree = [{index:0, parent:null,nodetype:0, name: "customize", type:"100", displaytype:'No Remarks', nodes: [], editarea:"",isconfirm:true}];
			}
		}
		  $scope.remarkreadable = $scope.createReadable("", $scope.tree); 
	}*/
	
	
	
	//Update Each Row in DB
	
	$scope.UpdateRowItem = function(currentitem,index){		
		
		 angular.element(document.getElementById('rowloading'+index).style.display = "inline-block");
		
		var totUrl = $(location).attr('href');
		var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
		var finalUrl = newUrl+"airlinecommissionblockupdatelist.action";	
        var data = {"blockId":blockId,"SheetItem":currentitem};
		
		$http({
			method : 'POST',
			url : finalUrl,
			data : 'SheetItem=' + JSON.stringify(currentitem)+'&blockid='+blockId.val(),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data) {
			if (data.errors) {
				
			} else {
				//console.log(data);				
				if(data.jsonResult.Issuccess){
					 angular.element(document.getElementById('rowloading'+index).style.display = "none");
					 angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Updated');
				}
				   
				
			}
		}).error(function(data, status) {
			return false;
		});
	}






}]);
