app.controller('adminController', function($scope, $controller, $http, $q) {

	$controller('baseController', {$scope:$scope});
	
	$scope.adminOrgConfig = {
		currentPage: 1,
		totalItems: 10,
		itemsPerPage: 10,
		perPageOptions: [10, 20, 30, 40, 50],
		onChange: function(){
			//重新加载
			$scope.adminOrgPage($scope.adminOrgConfig.currentPage, $scope.adminOrgConfig.itemsPerPage);
		}
	};
	//分页
	$scope.adminOrgPage=function(page, rows){
		$http({
		    method: "POST",
		    url: "../admin/getAdminOrgDetails.action?page="+page+"&rows="+rows,
		}).then(function successCallback(response) {
			$scope.aos=response.data.rows;
			$scope.adminOrgConfig.totalItems=response.data.total;//更新总记录数
	    }, function errorCallback(response) {
	    });
	}
	
	$scope.selectActConf = {
		currentPage: 1,
		totalItems: 10,
		itemsPerPage: 10,
		perPageOptions: [10, 20, 30, 40, 50],
		onChange: function(){
			//重新加载
			$scope.selectActPage($scope.selectActConf.currentPage, $scope.selectActConf.itemsPerPage);
		}
	};
	
	$scope.reloadSelectActList = function(){
		$scope.selectActPage($scope.selectActConf.currentPage, $scope.selectActConf.itemsPerPage);
	}
	
	//分页
	$scope.selectActPage=function(page, rows){
		if($scope.isUndefined($scope.selectAct) || $scope.isEmpty($scope.selectAct)){
			
			$http({
			    method: "POST",
			    url: "../admin/getAdminActDetails.action?page="+page+"&rows="+rows+"&like="
			}).then(function successCallback(response) {
				$scope.activities=response.data.rows;
				$scope.selectActConf.totalItems=response.data.total;//更新总记录数
		    }, function errorCallback(response) {
		    });
			
			return;
		}
		
		$http({
		    method: "POST",
		    url: "../admin/getAdminActDetails.action?page="+page+"&rows="+rows+"&like="+$scope.selectAct
		}).then(function successCallback(response) {
			$scope.activities=response.data.rows;
			$scope.selectActConf.totalItems=response.data.total;//更新总记录数
	    }, function errorCallback(response) {
	    });
	}
	
	$scope.selectUserConf = {
			currentPage: 1,
			totalItems: 10,
			itemsPerPage: 10,
			perPageOptions: [10, 20, 30, 40, 50],
			onChange: function(){
				//重新加载
				$scope.selectActPage($scope.selectUserConf.currentPage, $scope.selectUserConf.itemsPerPage);
			}
	};
	
	$scope.reloadSelectUserList = function(){
		$scope.selectUserPage($scope.selectUserConf.currentPage, $scope.selectUserConf.itemsPerPage);
	}
	
	//分页
	$scope.selectUserPage=function(page, rows){
		if($scope.isUndefined($scope.selectUser) || $scope.isEmpty($scope.selectUser)){
			
			$http({
				method: "POST",
				url: "../admin/getAdminUsers.action?page="+page+"&rows="+rows+"&like="
			}).then(function successCallback(response) {
				$scope.users=response.data.rows;
				$scope.selectUserConf.totalItems=response.data.total;//更新总记录数
			}, function errorCallback(response) {
			});
			
			return;
		}
		
		$http({
			method: "POST",
			url: "../admin/getAdminUsers.action?page="+page+"&rows="+rows+"&like="+$scope.selectUser
		}).then(function successCallback(response) {
			$scope.users=response.data.rows;
			$scope.selectUserConf.totalItems=response.data.total;//更新总记录数
		}, function errorCallback(response) {
		});
	}
	
	$scope.orgDetailsConf = {
			currentPage: 1,
			totalItems: 10,
			itemsPerPage: 10,
			perPageOptions: [10, 20, 30, 40, 50],
			onChange: function(){
				//重新加载
				$scope.getOrgDetail($scope.tempOid);
			}
	};
	
	//分页
	$scope.orgDetailsPage=function(page, rows, oid){
		
		$http({
			method: "POST",
			url: "../admin/getOrgActivityDetail.action?page="+page+"&rows="+rows+"&oid="+oid
		}).then(function successCallback(response) {
			$scope.orgDetails=response.data.rows;
			$scope.orgDetailsConf.totalItems=response.data.total;//更新总记录数
		}, function errorCallback(response) {
		});
		
	}
	
	$scope.getOrgDetail=function(oid){
		$scope.tempOid = oid;
		$scope.orgDetailsPage($scope.orgDetailsConf.currentPage, $scope.orgDetailsConf.itemsPerPage, oid);
	}
	
	
	$scope.selectOrgs=function(){
		
		if($scope.isUndefined($scope.selectOrg) || $scope.isEmpty($scope.selectOrg)){
			$scope.adminOrgPage($scope.adminOrgConfig.currentPage, $scope.adminOrgConfig.itemsPerPage);
			return;
		}
		$http({
			method: "POST",
			  url: "../admin/selectOrgs.action?like="+$scope.selectOrg
		}).then(function successCallback(response) {
			$scope.aos=response.data.rows;
		}, function errorCallback(response) {
			
		});
		
	}
	
	
});