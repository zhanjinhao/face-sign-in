app.controller('loginController', function($scope, $controller, $http, $q) {

	$controller('baseController', {$scope:$scope});
	
	$scope.check=function(){
		if($scope.isUndefined($scope.entity.id) || $scope.isUndefined($scope.entity.pwd) 
				|| $scope.isUndefined($scope.entity.verCode))
			return false;
		
		if($scope.isUid($scope.entity.id)){
			$scope.user.uid=$scope.entity.id;
			$scope.user.upwd=$scope.entity.pwd;
			$scope.user.uname=$scope.entity.verCode;
			$http({
			    method: "POST",
			    url: "../user/login.action?",
			    data: $scope.user
			}).then(function successCallback(response) {
				setInnerHtmlValue("verCodeError", response.data.info);
				if(response.data.info == "登录成功！正在跳往用户主页！"){
					setTimeout(function(){
						window.location.href="../ui/user.action";
					}, 2000);
				}
				
		    }, function errorCallback(response) {
		    	setInnerHtmlValue("verCodeError", "系统发生错误，请重试！");
		    });
		}

		if($scope.isEmail($scope.entity.id)){
			$scope.org.oemail=$scope.entity.id;
			$scope.org.opwd=$scope.entity.pwd;
			$scope.org.oname=$scope.entity.verCode;
			$http({
			    method: "POST",
			    url: "../org/login.action?",
			    data: $scope.org
			}).then(function successCallback(response) {
				setInnerHtmlValue("verCodeError", response.data.info);
				if(response.data.info == "登录成功！正在跳往组织主页！"){
					setTimeout(function(){
						window.location.href="../ui/organization.action";
					}, 2000);
				}
				
		    }, function errorCallback(response) {
		    	setInnerHtmlValue("verCodeError", "系统发生错误，请重试！");
		    });
		}
	}
	
});