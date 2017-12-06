// define the app, include the file upload dependency
var app = angular.module('app', []);

// controller to handle the file upload event
app.controller('AppCtrl', 
  function ($scope, $rootScope, FileUploadService) {
    var service = FileUploadService;
    /** 
     *  Handler to upload a new file to the server.
     */
    $scope.uploadFile = function ($files) {
      var $file = $files[0];
      console.log($file);
      service.uploadFile($file, function (error) {
        if (error) {
          alert('There was a problem uploading the file.');
        }
        // handle successfully-uploaded file
      })
    }
  });

// services should interact with the outside world
app.factory('FileUploadService', function ($http) {
  var api = {
    uploadFile: function (file, callback) {
      $http.uploadFile({
        url: 'http://localhost:8080/LintasTravelAdmin/Upload.action',
        file: file
      }).progress(function(event) {
        console.log('percent: ' + parseInt(100.0 * event.loaded / event.total));
      }).error(function (data, status, headers, config) {
        console.error('Error uploading file')
        callback(status);
      }).then(function(data, status, headers, config) {
        callback(null);
      });
    }
  }
  return api;
});