var app = angular.module("myApp", ['ngSanitize','ui.tree','datatables', 'ngResource']);
app.config(['$httpProvider', function($httpProvider) {    
	$httpProvider.defaults.useXDomain = true;       
	delete $httpProvider.defaults.headers.common['X-Requested-With']; 
}
]);
app.controller("TreeController", ['$scope','$http','DTOptionsBuilder', 'DTColumnBuilder', function($scope,$http,DTOptionsBuilder, DTColumnBuilder) {

	$scope.loadeddisplay = "none";
	$scope.loadingdisplay = "show";
	$scope.names = [];
	//Load Commission From DB

	var sheetId =  angular.element(document.getElementById('sheetId'));
	//Load Commission From DB
	console.log(sheetId.val());
	var totUrl = $(location).attr('href');
	var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	var finalUrl = newUrl+"airlinecommissionlist.action";
	var finalUrlcommon = newUrl+"airlinesheetcommissioncommon.action";
	//console.log("....Final URL....."+finalUrl);

	$http({
		method : 'POST',
		url : finalUrl,
		data : 'Sheetid=' + JSON.stringify({"Sheetid":sheetId.val()}),
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


	$http({
		method : 'POST',
		url : finalUrlcommon,
		data : 'Sheetid=' + JSON.stringify({"Sheetid":sheetId.val()}),
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded'
		}
	}).success(function(data) {
		if (data.errors) {

		} else {
			console.log(data);				
			$scope.commonforsheet = data.jsonResult;
			$scope.vailddatefrom = data.jsonResult.ValidFrom.substring(0,10);
			$scope.vailddatetill = data.jsonResult.ValidTill.substring(0,10);

		}
	}).error(function(data, status) {
		return false;
	});
	
	
	$scope.dtOptions = DTOptionsBuilder.newOptions()
    .withPaginationType('full_numbers')
    .withDisplayLength(100)
    .withOption('lengthChange', true);
	

	$scope.applydisplay = "none";

	var childindex = 0;

	$scope.parentapplicabletypes = [{id:18,name:"Amount"}];

	//$scope.applicabletypes = [{id:0,name:"NIL"}];
	//$scope.applicabletypes.push({id:1,name:"All"});
	
	$scope.applicabletypes = [{id:2,name:"Class"}];
	$scope.applicabletypes.push({id:4,name:"Sector"});
	$scope.applicabletypes.push({id:3,name:"Airline"});
	$scope.applicabletypes.push({id:5,name:"Country"});     
	$scope.applicabletypes.push({id:7,name:"Travel Country"});
	$scope.applicabletypes.push({id:8,name:"City"});
	$scope.applicabletypes.push({id:9,name:"Soto"});
	$scope.applicabletypes.push({id:16,name:"Route"});     
	$scope.applicabletypes.push({id:17,name:"Tour"});
	$scope.applicabletypes.push({id:15,name:"Fare"});
	$scope.applicabletypes.push({id:14,name:"Passenger"});
	$scope.applicabletypes.push({id:12,name:"Codeshare"});
	$scope.applicabletypes.push({id:13,name:"Interline"});
	$scope.applicabletypes.push({id:10,name:"IB"});
	$scope.applicabletypes.push({id:11,name:"OB"});
	//$scope.applicabletypes.push({id:100,name:"Remarks"});


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

	var countrylist = [];
	var airportlist = [];
	var classlist = [{name:"F",value:"F"}];
	classlist.push({name:"A",value:"A"});
	classlist.push({name:"P",value:"P"});
	classlist.push({name:"J",value:"J"});
	classlist.push({name:"R",value:"R"});
	classlist.push({name:"D",value:"D"});
	classlist.push({name:"I",value:"I"});
	classlist.push({name:"Y",value:"Y"});
	classlist.push({name:"B",value:"B"});
	classlist.push({name:"H",value:"H"});
	classlist.push({name:"M",value:"M"});
	classlist.push({name:"K",value:"K"});
	classlist.push({name:"W",value:"W"});
	classlist.push({name:"S",value:"S"});
	classlist.push({name:"N",value:"N"});
	classlist.push({name:"Q",value:"Q"});
	classlist.push({name:"Economy",value:"Economy"});
	classlist.push({name:"business",value:"business"});
	$scope.generatedlist = [{name:"All",value:"All"}]

	classlist.sort(compare);
	//  Country List	
	$http.get('country.json').then(function (response) {		
		angular.forEach(response.data, function(value,index) {	
			//$scope.names.push(value.FIELD1);    
			countrylist.push({name:value.FIELD1,value:value.FIELD1});
		});
	});

	countrylist.sort(compare);
	$scope.list = countrylist;
	//  Airport List	
	$http.get('airport2.json').then(function (response) {		
		//console.log(response.data.airlinename);

		angular.forEach(response.data, function(value,index) {		
			airportlist.push({name:value.airport_name,value:value.airport_code});
		});
	});
	airportlist.sort(compare);

	var airlinelist = [];

	//  Airlines List 
	$http.get('airlines.json').then(function (response) {  
		//console.log(response.data.airlinename);
		response.data.sort(compareairline);
		angular.forEach(response.data, function(value,index) {  
			airlinelist.push({name:value.airlinename,value:value.airlinename});
		});
	});
	//airlinelist.sort(compare);

	function compare(a,b) {
		if (a.name < b.name)
			return -1;
		else if (a.name > b.name)
			return 1;
		else 
			return 0;
	}

	function compareairline(a, b) {
	    if (a.airlinename < b.airlinename) return -1;
	    else if (a.airlinename > b.airlinename) return 1;
	    else
	        return 0;
	}


	$scope.update = function(itemvalue,index){	
		//console.log(itemvalue.firstitem);
		console.log(index);
		angular.element(document.getElementById(index+'list').style.display = "none");
		/*	angular.element(document.getElementById(index+'countrylist').style.display = "none");
		angular.element(document.getElementById(index+'Tcountrylist').style.display = "none") ;
		angular.element(document.getElementById(index+'airlinelist').style.display = "none") ;
		angular.element(document.getElementById(index+'airportlist1').style.display = "none") ;
		angular.element(document.getElementById(index+'airportlist2').style.display = "none") ;
		angular.element(document.getElementById(index+'classlist').style.display = "none") ;

		angular.element(document.getElementById(index+'routetypeinput').style.display = "none") ;*/


		angular.element(document.getElementById(index+'input').style.display = "none") ;
		var ss = angular.element(document.getElementById(index+'input')) ;
		ss.attr("placeholder","");
		//console.log(ss.attr("placeholder"));

		var itemname = itemvalue.firstitem.split("-");

		if(itemname[1] == 'Country'){
			$scope.list = countrylist;
			angular.element(document.getElementById(index+'list').style.display = "inline-block") ;
			//angular.element(document.getElementById(index+'countrylist').style.display = "inline-block") ;	

		}
		else if(itemname[1] == 'Travel Country'){
			//angular.element(document.getElementById(index+'Tcountrylist').style.display = "inline-block") ;
			$scope.list = countrylist;
			angular.element(document.getElementById(index+'list').style.display = "inline-block") ;
		}
		else if(itemname[1] == 'Airline'){
			//angular.element(document.getElementById(index+'airlinelist').style.display = "inline-block") ;
			$scope.list = airlinelist;
			angular.element(document.getElementById(index+'list').style.display = "inline-block") ;
		}
		else if(itemname[1] == 'Class'){
			//angular.element(document.getElementById(index+'classlist').style.display = "inline-block") ;
			$scope.list = classlist;
			angular.element(document.getElementById(index+'list').style.display = "inline-block") ;
		}
		else if(itemname[1] == 'Sector'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;

		}
		else if(itemname[1] == 'Soto'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;			
		}
		else if(itemname[1] == 'Route'){		
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;		
			//angular.element(document.getElementById(index+'airportlist1').style.display = "inline-block") ;
			//angular.element(document.getElementById(index+'routetypeinput').style.display = "inline-block") ;
			//angular.element(document.getElementById(index+'airportlist2').style.display = "inline-block") ;
		}		
		else if(itemname[1] == 'Codeshare'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;			
		}
		else if(itemname[1] == 'Interline'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;			
		}
		else if(itemname[1] == 'City'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;			
		}
		else if(itemname[1] == 'Tour'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;			
		}
		else if(itemname[1] == 'Fare'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;			
		}
		else if(itemname[1] == 'Passenger'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;			
		}
		else if(itemname[1] == 'OB'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;			
		}
		else if(itemname[1] == 'IB'){
			angular.element(document.getElementById(index+'input').style.display = "inline-block") ;			
		}
		else if(itemname[1] == 'Amount'){

			var ss = angular.element(document.getElementById(index+'input').style.display = "inline-block") ;

			ss.attr("placeholder","Percentage");
		}
		else{			

		}

		//$scope.$apply(); 
	}



	$scope.deleteChild = function(data) {     
		data.nds = [];      
	}

	$scope.deleteMe = function(data,index) { 
		console.log(data);
		if(data.ndt == 1  && data.tp != 18  ){
			angular.element(document.getElementById('addbutton0').style.display = "none") ;			
		}
		if((data.ndt == 1  && data.tp == 1) || (data.ndt == 1  && data.tp == 18)){
			angular.element(document.getElementById('addbutton0').style.display = "inline-block") ;			
         }
		
		data.pr.splice(index,1);
		$scope.remarkreadable = createReadable("", $scope.tree); 
		
		
	}

	$scope.deleteAll = function(data) {  
		console.log(data);
		if(data.ndt == 2){
			angular.element(document.getElementById('addbutton0').style.display = "none") ;			
		}
		data.nds = []; 
		$scope.remarkreadable = createReadable("", $scope.tree); 
	}
	$scope.EditAmount = function(data, index) { 

		data.isc = false; 

		angular.element(document.getElementById(data.ix+'amountinput').style.display = "inline-block") ;	

		//data.ndt = 1;  
	}

	$scope.Edit = function(data, index) { 

		data.isc = false; 

		$scope.types = $scope.applicabletypes ;

		//data.ndt = 1;  
	}


	$scope.addForm = function(data, childname, childtype) {			
		console.log(data);
		console.log( $scope.tree);
		console.log( $scope.tree[0]);		
		childindex = childindex + 1; 		 
		var child = {ix:childindex, pr:data.nds,ndt:1, nm:childname,tp:childtype, nds: [],isc:false};
		var inc = {ix:-1, pr:child, ndt:2, nm: "inc",tp:"inc", nds: [], isc:true};
		var exc = {ix:-1, pr:child, ndt:3, nm: "exc",tp:"exc", nds: [],  isc:true};  
		if(data.ndt == 2 || data.ndt == 3){			
			$scope.types = $scope.applicabletypes ;
		}else{			
			$scope.types = $scope.parentapplicabletypes ;
		} 
		if(data.ndt == 0){
			angular.element(document.getElementById('addbutton'+data.ndt).style.display = "none") ;			
		}
		
		if(data.ndt != 3){	
			child.nds.push(inc);
			child.nds.push(exc);
		}		
		data.nds.push(child);		
	}

	$scope.editForm = function(data, childname, childtype) {		
		console.log(data);
		console.log( $scope.tree);
		console.log( $scope.tree[0]);					 
		var child = {ix:data.ix, pr:data.nds,ndt:1, nm:childname,tp:childtype, nds: data.nds,isc:false};
		var inc = {ix:-1, pr:child, ndt:2, nm: "inc",tp:"inc", nds: [], isc:true};
		var exc = {ix:-1, pr:child, ndt:3, nm: "exc",tp:"exc", nds: [],  isc:true}; 		
		if(data.ndt == 2 || data.ndt == 3){			
			$scope.types = $scope.applicabletypes ;
		}else{
			$scope.types = $scope.parentapplicabletypes ;
		} 		
		if(data.ndt != 3){	
			child.nds.push(inc);
			child.nds.push(exc);
		}		
		data.nds.push(child);		
	}

	//update tree remark...
	$scope.Submitdata = function(data, iata_selected, is_plb)
	{
		//console.log('Before  data' + $scope.commissionlist);
		var cache = [];
		var treestr = JSON.stringify(data, function(key, value) {
			if (typeof value === 'object' && value !== null) {
				if (cache.indexOf(value) !== -1) {
					// Circular reference found, discard key
					return;
				}
				// Store value in our collection
				cache.push(value);
			}
			return value;
		});
		cache = null; // Enable garbage collection
		//console.log()
		//	console.log("iata code ="+iata_selected+" \n plb tree data"+treestr );
		//	var remarktreestr = JSON.stringify(data);
		var commissionlistnew  = [];
		console.log(" $scope.commissionlist"+ $scope.commissionlist );
		angular.forEach($scope.commissionlist, function(rowitemvalue,rowitemindex) { 
			if(rowitemvalue.iata_code == iata_selected)
			{
				//console.log("iata code ="+iata_selected+" \n update fpound"+data );
				/*console.log(" row data"+rowitemvalue );
				console.log(" row data"+is_plb );
				console.log(" rowitemvalue.row_item.pr " +rowitemvalue.row_item.pr );
				console.log(" rowitemvalue.row_item.ir "+ rowitemvalue.row_item.ir);*/
				
				var rowcache = [];
				var rowstr = JSON.stringify(rowitemvalue, function(key, value) {
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
				
				console.log(rowstr);

				//var rowjson = JSON.parse(rowitemvalue.row_item);
				if(is_plb)
				{		
					rowitemvalue.pr = treestr;
					//rowjson.plbRemark = treestr;					
					//console.log("updated plb tree data"+rowjson.plbRemark );
				}
				else
				{
					rowitemvalue.ir = treestr;
					//rowjson.iataRemark = treestr;					
					//console.log("updated iata tree data"+rowjson.iataRemark );
				}

				var rowcache = [];
				var rowstr = JSON.stringify(rowitemvalue, function(key, value) {
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
				
				//console.log(rowstr);

				//rowitemvalue.row_item = rowstr;
				rowitemvalue.is_updatable = true;
				//console.log("iata code ="+iata_selected+" \n update found --- row updated item--"+rowitemvalue.row_item );
				console.log("after row data"+rowitemvalue );

			}
			commissionlistnew.push(rowitemvalue);
		});
		$scope.commissionlist =  null;
		$scope.commissionlist = commissionlistnew;
		//console.log('Updated data' + $scope.commissionlist);	
		//angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');
		//	$scope.tree = '';

	}

	$scope.close = function(){
		//$scope.tree = '';
	}

	$scope.confirmChild = function(data,index) {  

		if(data.tp != 18)
		{
			var selectedspan =  angular.element(document.getElementById(data.ix+'editform'));
			var values = selectedspan.children(':visible')[0].value.split("-");
			var values2 = "";
			if(selectedspan.children(':visible').length > 2){
				values2 = selectedspan.children(':visible')[1].value + selectedspan.children(':visible')[2].value + selectedspan.children(':visible')[3].value;
			}else{
				values2 = selectedspan.children(':visible')[1].value;
			}
			data = {ix:data.ix, pr:data.pr,ndt:1, nm: values2,tp:values[0], nds: data.nds, isc:true}
		}
		else
		{
			var selectedspan =  angular.element(document.getElementById(data.ix+'editformamount'));
			var value = selectedspan.children(':visible')[0].value;

			data = {ix:data.ix, pr:data.pr,ndt:1, nm: value,tp:"18", nds: data.nds, isc:true}
		}

		/*if(data.ndt == 0){
			angular.element(document.getElementById('addbutton'+data.ndt).style.display = "none") ;			
		}*/

		
		data.pr.splice(index,1);
		data.pr.push(data);
		$scope.remarkreadable = createReadable("", $scope.tree); 	
		inc = null;
		exc = null;
		
		
		
	}




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
	$scope.isfixed = function(currentitem,is_plb,index)
	{		
		var isfixed  = JSON.parse(currentitem.row_item).isi;		
		if(is_plb)
			isfixed  = JSON.parse(currentitem.row_item).isp;

		if(document.getElementById('plbcomm'+index)!=undefined){			 
			if(!isfixed && is_plb)
				angular.element(document.getElementById('plbcomm'+index).style.display = "none");
			if(!isfixed && !is_plb)
				angular.element(document.getElementById('iatacomm'+index).style.display = "none");
		}	
		return isfixed;		
	}
	$scope.isiatafixedchage = function(index,value,iatacode){
         
		var iatafixedval;
		if(value == 0){
			angular.element(document.getElementById('iatacomm'+index).style.display = "none");
			iatafixedval = false;
		} 
		else{
			angular.element(document.getElementById('iatacomm'+index).style.display = "inline-block");
			iatafixedval = true;
		}		

		var commissionlistnew  = [];
		angular.forEach($scope.commissionlist, function(rowitemvalue,rowitemindex) { 
			if(rowitemvalue.iata_code === iatacode)
			{			
				//console.log("before row data"+rowitemvalue.row_item );
				var rowjson = JSON.parse(rowitemvalue.row_item);				

				rowjson.isi = iatafixedval;
				if(!iatafixedval)
					rowjson.ic = 0.00;

				var rowcache = [];
				var rowstr = JSON.stringify(rowjson, function(key, value) {
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
				rowitemvalue.row_item = rowstr;
				rowitemvalue.is_updatable = true;
				//console.log("after row data"+rowitemvalue.row_item );
			}
			commissionlistnew.push(rowitemvalue);
		});
		$scope.commissionlist =  null;
		$scope.commissionlist = commissionlistnew;	
		angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');
		$scope.$apply(); 
	}

	$scope.isplbfixedchage = function(index,value,iatacode){

		var plbfixedval;
		if(value == 0){
			angular.element(document.getElementById('plbcomm'+index).style.display = "none");
			plbfixedval = false;
		} 
		else{
			angular.element(document.getElementById('plbcomm'+index).style.display = "inline-block");
			plbfixedval = true;
		}		

		var commissionlistnew  = [];
		angular.forEach($scope.commissionlist, function(rowitemvalue,rowitemindex) { 
			if(rowitemvalue.iata_code === iatacode)
			{			
				//console.log("before row data"+rowitemvalue.row_item );
				var rowjson = JSON.parse(rowitemvalue.row_item);				

				rowjson.isp = plbfixedval;							
				if(!plbfixedval)
					rowjson.pc = 0.00;

				var rowcache = [];
				var rowstr = JSON.stringify(rowjson, function(key, value) {
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
				rowitemvalue.row_item = rowstr;
				rowitemvalue.is_updatable = true;


				//console.log("after row data"+rowitemvalue.row_item );

			}
			commissionlistnew.push(rowitemvalue);
		});
		$scope.commissionlist =  null;
		$scope.commissionlist = commissionlistnew;	
		angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');
		$scope.$apply(); 
	}


	function traverse(superparent, parent) {
		//counter ++;
		console.log('counter----'+counter);
		for (var j=0; j < parent.length; j++) {
			var featureName=parent[j].nm; 
			var value=parent[j].tp; 
			var nds = parent[j].nds;
			console.log('--index--'+parent[j].ix);


			//only when the item s index is bigger than current index 
			var newIndex = parent[j].ix;
			if(childindex <= newIndex)
				childindex = newIndex;


			console.log('featureName----'+featureName);
			console.log('value----'+value);
			console.log('nds----'+nds.length);			
			if(nds != null && nds.length > 0)
			{
				nds = traverse(parent[j], nds);
			}
			//parent[j].ix = counter;
			parent[j].nds = nds;
			parent[j].pr = parent;
			//console.log('nds--modified--'+parent[j].nds);
			//console.log('nds--index--'+parent[j].index);
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
					//	readable =  readable + displaytype +"&nbsp;"+"<b>"+ displayname +"</b>" + childreadable  ;
						if(displaytype == 'Amount'){
							
							readable =  readable +"<b>"+ displayname + "</b>"+"&nbsp;"+ "% Percentage" +  childreadable  ;
						}
							
						else{
							
							readable =  readable + displaytype +"&nbsp;"+"<b>"+ displayname  +"</b>" + childreadable + ',' ;
						}
						
					}
					else{  
						
						
						if(displaytype == 'Amount'){
							
							readable =  readable +"<b>"+ displayname + "</b>"+"&nbsp;"+ "% Percentage" +  childreadable  ;
						}
							
						else{
							
							readable =  readable + displaytype +"&nbsp;"+"<b>"+ displayname  +"</b>" + childreadable + ',' ;
						}
							
							
						 //readable =  readable +"<b>"+ displayname + "</b>"+"&nbsp;"+ "% Percentage" +  childreadable  ; 
						 
					
					}

				}
				else{
					
					var childreadable = createReadable(readable,nds);
					//readable =   readable + displaytype +"&nbsp;"+"<b>"+ displayname +"</b>"+ childreadable + ',';
					if(displaytype == 'Amount'){
						
						readable =  readable +"<b>"+ displayname + "</b>"+"&nbsp;"+ "% Percentage" +  childreadable  ;
					}
						
					else{
						
						readable =  readable + displaytype +"&nbsp;"+"<b>"+ displayname  +"</b>" + childreadable + ',' ;
					}

				} 
			}
			else  if(parent[j].ndt == 2)
			{
				if(nds != null && nds.length>0)
				{     
				
					readable = readable + " for " + createReadable(readable, nds);		
					
					 
				    angular.element(document.getElementById('addbutton0').style.display = "inline-block") ;			
					
				}


			}
			else  if(parent[j].ndt == 3)
			{
				if(nds != null && nds.length>0)
				{     
					
					readable = readable + " not for "+ createReadable(readable, nds);    
					
					  angular.element(document.getElementById('addbutton0').style.display = "inline-block") ;
				}
			}
			//console.log("readable is..."+readable);
		} 

		return readable;
	}








	$scope.itemchange = function(textboxitem,index,is_iata){
		//	console.log(textboxitem.item);
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
				//console.log("before row data"+rowitemvalue.row_item );
				var rowjson = JSON.parse(rowitemvalue.row_item);				
				if(is_iata){
					//console.log("iata");
					rowjson.ic = currentitem.val();
				}				  
				else{
					// console.log("plb");
					rowjson.pc = currentitem.val();
				}				


				var rowcache = [];
				var rowstr = JSON.stringify(rowjson, function(key, value) {
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
				rowitemvalue.row_item = rowstr;
				rowitemvalue.is_updatable = true;
				//console.log("after row data"+rowitemvalue.row_item );
			}
			commissionlistnew.push(rowitemvalue);
		});
		$scope.commissionlist =  null;
		$scope.commissionlist = commissionlistnew;
		angular.element(document.getElementById('updatedbutton'+index).innerHTML = 'Update');
	}





	$scope.populatetree = function(remark){	
		$scope.tree = traverse(null, remark);
		//console.log("updated tree----"+$scope.tree);
	}	


	$scope.showtree = function(item,is_plb_tree){	



		var plb_remark = null;
		var iata_remark = null;

		var totUrl = $(location).attr('href');
		var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
		var finalUrl = newUrl+"airlinesheetcommissionremark.action";	

		$http({
			method : 'POST',
			url : finalUrl,
			data : 'RemarkItem=' + JSON.stringify({"iata_code":item.iata_code,"sheetid":$scope.commonforsheet.SheetId,"is_plb":is_plb_tree}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data) {
			if (data.errors) {

			} else {
				console.log(data);	

				if(is_plb_tree)
					plb_remark = data.jsonResult.plb_remark;
				else
					iata_remark = data.jsonResult.iata_remark;



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

			}
		}).error(function(data, status) {
			return false;
		});


		//$scope.tree = [{ix:0, pr:[],ndt:0, nm: "customize", tp:"100", nds: [], isc:true}];
		//ix for index, ndt for nodetype, nm for name, tp for type, nds for nodes, isc for isonfirm
	}

	function pad(n){return n<10 ? '0'+n : n}	
	$scope.Appludate = function(){
		var vaildfrom = angular.element(document.getElementById('twodpd2'));
		var vaildtill = angular.element(document.getElementById('twodpd1'));	

		$scope.UpdateDateInSheet(vaildfrom.val(),vaildtill.val());
	}

	$scope.UpdateDateInSheet =  function(fromdate,tilldate){

		$scope.applydisplay = "show";

		var totUrl = $(location).attr('href');
		var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
		var finalUrl = newUrl+"updateairlinecommissionlist.action";	

		$http({
			method : 'POST',
			url : finalUrl,
			data : 'SheetItem=' + JSON.stringify({"fromdate":fromdate,"tilldate":tilldate}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data) {
			if (data.errors) {

			} else {
				console.log(data);				
				if(data.jsonResult.Issuccess){
					$scope.applydisplay = "none";
					var vaildfrom = angular.element(document.getElementById('twodpd2'));
					var vaildtill = angular.element(document.getElementById('twodpd1'));
					vaildfrom.value = "";vaildtill.value = "";
				}


			}
		}).error(function(data, status) {
			return false;
		});
	}


	//Update Each Row in DB

	$scope.UpdateRowItem = function(currentitem,index){		
		console.log(currentitem);
		angular.element(document.getElementById('rowloading'+index).style.display = "inline-block");

		var totUrl = $(location).attr('href');
		var newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
		var finalUrl = newUrl+"updateairlinecommissionitem.action";	

		$http({
			method : 'POST',
			url : finalUrl,
			data : 'SheetItem=' + JSON.stringify(currentitem),
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

