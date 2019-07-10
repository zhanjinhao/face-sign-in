app.controller('errorController', function($scope, $controller, $http, $q) {

	$controller('baseController', {$scope:$scope});
	
	$scope.init=function(){
		
		$http({
			method:'POST',
			url:"../activity/echoErrorJSPInfo.action",
		}).then(function successCallback(response) {
			
			$scope.errorInfo = response.data.info;
			
			alert($scope.errorInfo);
			
	    }, function errorCallback(response) {
	    	
	    });
		
	}
	
});