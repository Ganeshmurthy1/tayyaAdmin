 var app = angular.module('mainApp', []);

app.config(['$httpProvider', function($httpProvider) {    
        $httpProvider.defaults.useXDomain = true;       
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);
 
 app.controller('searchController',['$scope','$http',function($scope,$http){
   //$scope.locationurl = 'https://intellicomm.co.uk/LintasTravelAPI/Search/bycity/ba'
   $scope.names = [];  
   $scope.cities = [];  
  
   $http.get('hotels.json').success(function(data){
          //console.log(data);
          angular.forEach(data, function(value) {                     
                     /* $scope.names.push(value.chain+","+value.country+","+"("+value.code+")");    */ 
        	   $scope.selected = undefined;
                  $scope.names.push(value.chain);    
                  console.log(value.chain);
                
                  $scope.startsWith = function(state, viewValue) {
                    return state.substr(0, viewValue.length).toLowerCase() == viewValue.toLowerCase();
                  } 
            
        	  
        	  
        	/*  $scope.names.push(value.hname);    
                       console.log(value.hname);
                     */
                       // myCache.put('myData', $scope.names);  

                      });
          /*alert($scope.names.length);*/
          
         }).error(function(data, status, headers, config){
   console.log(data); 
  });
 }]);

 
// Caching the river...
app.factory('myCache', function($cacheFactory) {
 return $cacheFactory('myData');
}); 
app.directive('autoCompleteDirective', function($timeout) {
    return function(scope, iElement, iAttrs) {
            iElement.autocomplete({
                source: scope[iAttrs.uiItems]
               /* select: function() {
                    $timeout(function() {
                      iElement.trigger('input');
                    }, 0);
                }*/
            });
    };
});



