var app = angular.module('mainApp', ['ui.bootstrap']);
app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]);
app.controller('searchController', ['$scope', '$http', 'limitToFilter', function($scope, $http, limitToFilter) {
    $scope.names = [];
  
    $http.get('resources/Apicredential.properties').then(function(response) {
        $scope.ApiURL = response.data.ApiURL;      
        $scope.cities = function(cityName) {
            var cityarr = [];
          
            return $http.get($scope.ApiURL+"Search/bycity/" + cityName).then(function(response) {
                angular.forEach(response.data, function(value) {
                    cityarr.push(value.city + "," + value.country + "," + "(" + value.airport_code + ")");
                });
                return limitToFilter(cityarr, 15);
            });
        };
    });
    Array.prototype.unique = function() {
        var a = [];
        for (i = 0; i < this.length; i++) {
            var current = this[i];
            if (a.indexOf(current) < 0) a.push(current);
        }
        this.length = 0;
        for (i = 0; i < a.length; i++) {
            this.push(a[i]);
        }
        return this.sort();
    }
}]);
app.factory('myCache', function($cacheFactory) {
    return $cacheFactory('myData');
});
app.directive('autoCompleteDirective', function($timeout) {
    return function(scope, iElement, iAttrs) {
        iElement.autocomplete({
            source: scope[iAttrs.uiItems],
        }, 3);
    };
});
app.directive('autoCompleteDirectives', function($timeout) {
    return function(scope, iElement, iAttrs) {
        iElement.autocomplete({
            source: scope[iAttrs.uiItems],
            select: function() {
                $timeout(function() {
                    var focused = angular.element(document.activeElement);
                    var twowaydate2 = angular.element(document.querySelector('#twodpd1'));
                    twowaydate2.focus();
                    if (focused.attr('id') == 'to') {
                        var onewaydate = angular.element(document.querySelector('#onedpd'));
                        onewaydate.focus();
                    }
                    if (focused.attr('id') == 'hotelcity') {
                        var checkdate = angular.element(document.querySelector('#datain'));
                        checkdate.focus();
                    }
                    if (focused.attr('id') == 'destmulti1') {
                        var multidate1 = angular.element(document.querySelector('#multidate1'));
                        multidate1.focus();
                    } else if (focused.attr('id') == 'destmulti2') {
                        var multidate2 = angular.element(document.querySelector('#multidate2'));
                        multidate2.focus();
                    } else if (focused.attr('id') == 'destmulti3') {
                        var multidate3 = angular.element(document.querySelector('#multidate3'));
                        multidate3.focus();
                    } else if (focused.attr('id') == 'destmulti4') {
                        var multidate4 = angular.element(document.querySelector('#multidate4'));
                        multidate4.focus();
                    } else {
                        var multidate5 = angular.element(document.querySelector('#multidate5'));
                        multidate5.focus();
                    }
                }, 0);
            }
        });
    };
});