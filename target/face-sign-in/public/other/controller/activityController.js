app.controller('activityController', function($scope, $controller, $http, $q) {

	$controller('baseController', {$scope:$scope});
	

	
	$scope.enterVerify=function(){
		window.location.href="../ui/verify.action";
	}
	
	$scope.getActivityName=function(){
		
		$http({
		    method: "POST",
		    url: "../activity/getActivityName.action"
		}).then(function successCallback(response) {
			$scope.activityName=response.data.activityName;
	    }, function errorCallback(response) {
	    	
	    });
		
	}
	
	$scope.generateQRCode=function(){
		
		
		
	}
	
	$scope.inputInfo=function(){
		
		if($scope.isUndefined($scope.uid) || !$scope.isUid($scope.uid)){
			alert("学号格式错误！");
			return;
		}
		
		
		$http({
		    method: "POST",
		    url: "../activity/inputInfo.action?uid="+$scope.uid
		}).then(function successCallback(response) {
			alert(response.data.info);
			$scope.users=response.data.rows;
			$scope.userPageConf.totalItems=response.data.total;//更新总记录数
			$scope.userPage($scope.userPageConf.currentPage, $scope.userPageConf.itemsPerPage);
	    }, function errorCallback(response) {
	    	
	    });
	}
	
	$scope.deleteUserRecord=function(uid){
		
		$http({
		    method: "POST",
		    url: "../activity/deleteUserRecord.action?uid="+uid
		}).then(function successCallback(response) {
			alert(response.data.info);
			
			if($scope.isUndefined($scope.usersInActivity) || $scope.isEmpty($scope.usersInActivity)){
				$scope.userPage($scope.userPageConf.currentPage, $scope.userPageConf.itemsPerPage);
			}else{
				$scope.findUsersInActivity();
			}
			
	    }, function errorCallback(response) {
	    	
	    });
		
	}
	$scope.resigninRecord=function(uid){
		$http({
			method: "POST",
			url: "../activity/resigninRecord.action?uid="+uid
		}).then(function successCallback(response) {
			alert(response.data.info);
			if($scope.isUndefined($scope.usersInActivity) || $scope.isEmpty($scope.usersInActivity)){
				$scope.userPage($scope.userPageConf.currentPage, $scope.userPageConf.itemsPerPage);
			}else{
				$scope.findUsersInActivity();
			}
			
		}, function errorCallback(response) {
			
		});
	}
	
	$scope.findUsersInActivity=function(){
		
		if($scope.isUndefined($scope.usersInActivity) || $scope.isEmpty($scope.usersInActivity)){
			$scope.userPage($scope.userPageConf.currentPage, $scope.userPageConf.itemsPerPage);
			return;
		}
		$http({
			method: "POST",
			url: "../activity/selectByLikeId.action?ulike="+$scope.usersInActivity
		}).then(function successCallback(response) {
			$scope.users=response.data.rows;
			
		}, function errorCallback(response) {
			
		});
	}
	
	//分页控件配置
	$scope.userPageConf = {
		currentPage: 1,
		totalItems: 10,
		itemsPerPage: 10,
		perPageOptions: [10, 20, 30, 40, 50],
		onChange: function(){
			//重新加载
			$scope.userPage($scope.userPageConf.currentPage, $scope.userPageConf.itemsPerPage);
		}
	};
	//分页
	$scope.userPage=function(page, rows){
		$http({
		    method: "POST",
		    url: "../activity/selectSigninRecordByAid.action?page="+page+"&rows="+rows,
		    data: $scope.entity
		}).then(function successCallback(response) {
			$scope.users=response.data.rows;
			$scope.userPageConf.totalItems=response.data.total;//更新总记录数
	    }, function errorCallback(response) {
	    	
	    });
	}
	
});
function changeQrCode(obj, height, width){
	var currentTime = new Date().getTime();
	obj.src="../activity/qrcodes.action?time=" + currentTime;
}