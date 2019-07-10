app.controller('userController', function($scope, $controller, $http, $q) {

	$controller('baseController', {$scope:$scope});

	$scope.getUserJoinGroupList=function(){
		$http({
		    method: "POST",
		    url: "../user/getUserJoinGroupList.action",
		}).then(function successCallback(response) {
			$scope.userJoinGroupList = response.data;
			$("#groupU").selectpicker('refresh');
	    }, function errorCallback(response) {
	   
	    });
		
	}
	
	$scope.sendChangePwdVerCode=function(pwd, type){
		
		if($scope.isUndefined(pwd) || $scope.isEmpty(pwd)){
			setInnerHtmlValue("changePwdError", "密码不为空！");
			return;
		}
		setInnerHtmlValue("changePwdError", "");
		$http({
			
		    method: "POST",
		    url: "../user/getCurrentEmail.action"
		    
		}).then(function successCallback(response) {
			var email = response.data.info;
			$http({
			    method: "POST",
			    url: "../utils/sendEmailVerCode.action?email="+email+"&type="+type
			}).then(function successCallback(response) {
				setInnerHtmlValue("changePwdError", "验证码已发送！");
		    }, function errorCallback(response) {
		    	setInnerHtmlValue("changePwdError", "系统发生错误，请重试！");
		    });
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("changePwdError", "系统发生错误，请重试！");
	    });
		
	}
	
	$scope.realChangePwd=function(){
		
		if($scope.isUndefined($scope.changePwd.newpwd) || $scope.isEmpty($scope.changePwd.newpwd) 
			|| $scope.isUndefined($scope.changePwd.vercode) || $scope.isEmpty($scope.changePwd.vercode)){
			
			setInnerHtmlValue("changePwdError", "请完善信息！");
			return;
		}

		$http({
			
		    method: "POST",
		    url: "../user/realChangePwd.action?newPwd="+$scope.changePwd.newpwd+"&vercode="+$scope.changePwd.vercode
		    
		}).then(function successCallback(response) {
			setInnerHtmlValue("changePwdError", response.data.info);
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("changePwdError", "系统发生错误，请重试！");
	    });
		
	}
	
	$scope.sendChangeEmailVerCode=function(pwd, type){
		
		if(!(!$scope.isUndefined(pwd) && !$scope.isEmpty(pwd) && $scope.isEmail(pwd))){
			setInnerHtmlValue("changeEmailError", "邮箱格式错误！");
			return;
		}
		setInnerHtmlValue("changeEmailError", "");
		$http({
			
			method: "POST",
			url: "../user/getCurrentEmail.action"
				
		}).then(function successCallback(response) {
			var email = response.data.info;
			
			$http({
				method: "POST",
				url: "../utils/sendEmailVerCode.action?email="+email+"&type=oldEmailVercode"
			}).then(function successCallback(response) {
				setInnerHtmlValue("changeEmailError", "验证码已发送！");
			}, function errorCallback(response) {
				setInnerHtmlValue("changeEmailError", "系统发生错误，请重试！");
			});
			
			$http({
				method: "POST",
				url: "../utils/sendEmailVerCode.action?email="+pwd+"&type=newEmailVercode"
			}).then(function successCallback(response) {
				setInnerHtmlValue("changeEmailError", "验证码已发送！");
			}, function errorCallback(response) {
				setInnerHtmlValue("changeEmailError", "系统发生错误，请重试！");
			});
			
		}, function errorCallback(response) {
			
			setInnerHtmlValue("changeEmailError", "系统发生错误，请重试！");
			
		});
		
	}
	
	$scope.realChangeEmail=function(){
		
		if($scope.isUndefined($scope.changeEmail.oldvercode) || $scope.isEmpty($scope.changeEmail.oldvercode) 
				|| $scope.isUndefined($scope.changeEmail.newvercode) || $scope.isEmpty($scope.changeEmail.newvercode)){
			
			setInnerHtmlValue("changeEmailError", "请完善信息！");
			return;
		}
		
		$http({
			
			method: "POST",
			url: "../user/realChangeEmail.action?oldvercode="+$scope.changeEmail.oldvercode+"&newvercode="+$scope.changeEmail.newvercode +"&newEmail="+$scope.changeEmail.newemail
			
		}).then(function successCallback(response) {
			setInnerHtmlValue("changeEmailError", response.data.info);
		}, function errorCallback(response) {
			setInnerHtmlValue("changeEmailError", "系统发生错误，请重试！");
		});
		
		
	}
	
	$scope.exitGroup=function(gid){

		$http({
		    method: "POST",
		    url: "../user/exitGroup.action?gid="+gid,
		}).then(function successCallback(response) {
			$scope.userJoinGroupDetailPage($scope.userJoinGroupDetail.currentPage, $scope.userJoinGroupDetail.itemsPerPage);
	    }, function errorCallback(response) {
	    });
		
	}
	
	$scope.getUserName=function(gid){

		$http({
		    method: "POST",
		    url: "../user/getUserName.action",
		}).then(function successCallback(response) {
			$scope.userName = response.data.userName;
	    }, function errorCallback(response) {
	    });
		
	}
	
	$scope.reload=function(){
		$scope.userActivityPage($scope.userActivityConf.currentPage, $scope.userActivityConf.itemsPerPage);
	}
	
	$scope.userActivityConf = {
		currentPage: 1,
		totalItems: 10,
		itemsPerPage: 10,
		perPageOptions: [10, 20, 30, 40, 50],
		onChange: function(){
			//重新加载
			$scope.userActivityPage($scope.userActivityConf.currentPage, $scope.userActivityConf.itemsPerPage);
		}
	};
	//分页
	$scope.userActivityPage=function(page, rows){
		
		if($scope.isUndefined($scope.gid) || $scope.gid==""){
			
			$http({
			    method: "POST",
			    url: "../user/getUserActivityNotInGroup.action?page="+page+"&rows="+rows,
			}).then(function successCallback(response) {
				$scope.uas=response.data.rows;
				$scope.userActivityConf.totalItems=response.data.total;//更新总记录数
		    }, function errorCallback(response) {
		    });
			
		}else{
			
			$http({
			    method: "POST",
			    url: "../user/getUserActivityInGroup.action?page="+page+"&rows="+rows+"&gid="+$scope.gid,
			}).then(function successCallback(response) {
				$scope.uas=response.data.rows;
				$scope.userActivityConf.totalItems=response.data.total;//更新总记录数
		    }, function errorCallback(response) {
		    });
			
		}
	}
	
	
	$scope.userJoinGroupDetail = {
		currentPage: 1,
		totalItems: 10,
		itemsPerPage: 8,
		perPageOptions: [8, 16, 24],
		onChange: function(){
			//重新加载
			$scope.userJoinGroupDetailPage($scope.userJoinGroupDetail.currentPage, $scope.userJoinGroupDetail.itemsPerPage);
		}
	};
	//分页
	$scope.userJoinGroupDetailPage=function(page, rows){
		$http({
		    method: "POST",
		    url: "../user/getUserJoinGroupDetail.action?page="+page+"&rows="+rows,
		}).then(function successCallback(response) {
			$scope.userJoinGroups=response.data.rows;
			$scope.userJoinGroupDetail.totalItems=response.data.total;//更新总记录数
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("createActivityError", "系统发生错误，请重试！");
	    });
	}
	
	$scope.joinGroup=function(){
		
		$http({
		    method: "POST",
		    url: "../user/joinGroup.action?gid="+$scope.joingid
		}).then(function successCallback(response) {
			setInnerHtmlValue("verCodeError", response.data.info);
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("verCodeError", "系统发生错误，请重试！");
	    });
		
	}
	
	$scope.changeImg=function(){
		
		setInnerHtmlValue("verCodeError", "正在更换图片，请等待！");
		
		if($scope.isUndefined($scope.changeUfaceToken) || $scope.isEmpty($scope.changeUfaceToken)){
			setInnerHtmlValue("verCodeError", "请选择图片！");
			return;
		}
		
		if($scope.isUndefined($scope.changUserImg.uimgPath) || $scope.isEmpty($scope.changUserImg.uimgPath)){
			setInnerHtmlValue("verCodeError", "请填写密码！");
			return;
		}
		
		$scope.changUserImg.ufaceToken = $scope.changeUfaceToken;
		
		$http({
			method: "POST",
			url: "../user/changUserImg.action",
			data: $scope.changUserImg
		}).then(function successCallback(response) {
			
			setInnerHtmlValue("verCodeError", response.data.info);
			
		}, function errorCallback(response) {
			
			setInnerHtmlValue("verCodeError", "系统发生错误，请重试！");
			
		});
		
		
	}
	
	$scope.sendEmailVerCode=function(){
		
		$http({
			method: "POST",
			url: "../user/getCurrentEmail.action"
				
		}).then(function successCallback(response) {
			var email = response.data.info;
			
			$http({
			    method: "POST",
			    url: "../utils/sendEmailVerCode.action?email="+email+"&type=user"
			}).then(function successCallback(response) {
				setInnerHtmlValue("verCodeError", "验证码已发送！");
		    }, function errorCallback(response) {
		    	setInnerHtmlValue("verCodeError", "系统发生错误，请重试！");
		    });
			
		}, function errorCallback(response) {
			
			setInnerHtmlValue("changeEmailError", "系统发生错误，请重试！");
			
		});
		
	}
	
});


function select_file(fileid){
	setInnerHtmlValue("verCodeError", "");
    document.getElementById(fileid).click();
}

function imgPreview(fileDom){
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("请更换为新版火狐或谷歌浏览器！");
        return;
    }
    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片文件！");
        return;
    }
    var base64 = reader.readAsDataURL(file);
    //读取完成
    reader.onload = function(e) {
        //获取图片dom
        var img = document.getElementById("img");
        //图片路径设置为读取的图片
        img.src = e.target.result;
        //获取 $scope
        var appElement = document.querySelector('[ng-controller=userController]');
        var $scope = angular.element(appElement).scope();
        //去除 data:image/jpeg;base64,
        $scope.changeUfaceToken=reader.result.replace("data:image/jpeg;base64,", "");
    };
}