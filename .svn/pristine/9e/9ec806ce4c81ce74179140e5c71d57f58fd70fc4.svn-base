var app = angular.module('app', []);

app.controller('AppCtrl', ['$scope', function($scope) {
  $scope.password = null;
  $scope.passwordConfirmation = null;
  console.log('eher');
}]);
   
app.directive('passwordConfirm', ['$parse', function ($parse) {
 return {
    restrict: 'A',
    scope: {
      matchTarget: '=',
    },
    require: 'ngModel',
    link: function link(scope, elem, attrs, ctrl) {    	
      var validator = function (value) {
        ctrl.$setValidity('match', value === scope.matchTarget);
        return value;
      }
      /**
       * This function is added to the list of the $parsers.
       * It will be executed the DOM (the view value) change.
       * Array.unshift() put it in the beginning of the list, so
       * it will be executed before all the other
       */
     
      //  $parsers are called as soon as the value in the form input is modified by the user
      ctrl.$parsers.unshift(validator);
     // $formatters are useful when there is a possibility of the value getting modified from the code
      ctrl.$formatters.push(validator);
      
      // This is to force validator when the original password gets changed
      scope.$watch('matchTarget', function(newval, oldval) {
        validator(ctrl.$viewValue);
      });

    }
  };
}]);