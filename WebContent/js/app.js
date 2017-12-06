var app = angular.module('app', []);
app.controller('AppCtrl',function($scope, fileReader,$http) {
	/* var lic=$location.protocol();
	 alert(lic);*/
	totUrl=$(location).attr('href');
	newUrl=totUrl.substr(0,totUrl.lastIndexOf('/')+1);
	finalUrl = newUrl+"Upload.action";
	console.log("....Final URL....."+finalUrl);
	/*
 url=location.protocol+"//"+location.host+"/"+"LintasTravelAdmin/Upload.action";
 console.log("....URL....."+url);*/
	$scope.password = null;
	$scope.passwordConfirmation = null;
	var file;

	$scope.getFile = function () {
		$scope.progress = 0;  


		var fd = new FormData();
		fd.append('file', $scope.file);  

		$http.post(finalUrl, fd,{

			withCredentials: false,
			headers: {'Content-Type': undefined },


		})
		.success(function(data){ 
			console.log(data);
			if(data.jsonobj.ImageUrl!=null){
				console.log(data.jsonobj.ImageUrl);
				$scope.Imagepath = data.jsonobj.ImageUrl;
				fileReader.readAsDataUrl($scope.file, $scope)
				.then(function(result) {  
					console.log("result image..."+result);
					$scope.imageSrc = null;
				});
				$scope.fileError=null;
				$scope.fileSizeError=null;

			}
			else if(data.jsonobj.fileSizeError!=null){
				console.log(data.jsonobj.fileSizeError);
				$scope.fileSizeError=data.jsonobj.fileSizeError;
				fileReader.readAsDataUrl($scope.file, $scope)
				.then(function(result) {  
					console.log("result image..."+result);
					$scope.imageSrc = null;
				});  
				$scope.fileError=null;
				$scope.imageSrc=null;
			}

			else if(data.jsonobj.fileError!=null){
				console.log(data.jsonobj.fileError);
				$scope.fileError=data.jsonobj.fileError;
				fileReader.readAsDataUrl($scope.file, $scope)
				.then(function(result) {  
					console.log("result image..."+result);
					$scope.imageSrc = null;
				});  
				$scope.fileSizeError=null;
			}





		})
		.error(function(data){
			console.log(data)
		});
		/*  fileReader.readAsDataUrl($scope.file, $scope)
                     .then(function(result) {  
                    	 console.log("result image..."+result);

                         $scope.imageSrc = result;
                     });*/
	};


	$scope.$on("fileProgress", function(e, progress) {
		$scope.progress = progress.loaded / progress.total;
	});

});
app.directive('passwordConfirm', ['$parse', function ($parse) {/*passwordConfirm*/
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
app.directive("ngFileSelect",function(){

	return {
		link: function($scope,el){

			el.bind("change", function(e){

				$scope.file = (e.srcElement || e.target).files[0];     

				var  fileExtension = $scope.file.name.substr(($scope.file.name.lastIndexOf('.') + 1));

				if(fileExtension == 'jpeg' || fileExtension == 'JPEG' || fileExtension == 'PNG' || fileExtension == 'png' || fileExtension == 'jpg' || fileExtension == 'JPG' || fileExtension == 'gif' || fileExtension == 'GIF')
				{
					$scope.fileError1 = "";

					if(parseInt($scope.file.size) > 1000000)
					{
						console.log($scope.file.size);
						$scope.fileError1 = "Select image less than 1 MB. " ;
					}
				}
				else{
					$scope.fileError1 = "Select image file. " ;
					console.log("$scope.file---------"+$scope.file);
					 
				}
				angular.element(document.querySelector('#fileError')).text( $scope.fileError1);

				// $scope.getFile();

			})

		}

	}


})

var fileReader = function ($q, $log) {

	var onLoad = function(reader, deferred, scope) {
		return function () {
			scope.$apply(function () {
				deferred.resolve(reader.result);
			});
		};
	};

	var onError = function (reader, deferred, scope) {
		return function () {
			scope.$apply(function () {
				deferred.reject(reader.result);
			});
		};
	};

	var onProgress = function(reader, scope) {
		return function (event) {
			scope.$broadcast("fileProgress",
					{
				total: event.total,
				loaded: event.loaded
					});
		};
	};

	var getReader = function(deferred, scope) {
		var reader = new FileReader();
		reader.onload = onLoad(reader, deferred, scope);
		reader.onerror = onError(reader, deferred, scope);
		reader.onprogress = onProgress(reader, scope);
		return reader;
	};

	var readAsDataURL = function (file, scope) {
		var deferred = $q.defer();

		var reader = getReader(deferred, scope);         
		reader.readAsDataURL(file);

		return deferred.promise;
	};

	return {
		readAsDataUrl: readAsDataURL  
	};
};

angular.module("app").factory("fileReader",
		["$q", "$log", fileReader]);


