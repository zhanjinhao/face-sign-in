app.controller('oregisterController', function($scope, $controller, $http, $q) {

	$controller('baseController', {$scope:$scope});

	//测试organization的uname是否合法
	$scope.checkOname=function(){
		var value = document.getElementById("oname").value;
		if(value.length <= 20 && value.length > 0)
			return true;
		document.getElementById("onameError").innerHTML="名称最多20个字符且不为空！";
		return false;
	}
	
	//测试organization的oemail是否合法
	$scope.checkOemail=function(){
		var value = document.getElementById("oemail").value;
		if(!$scope.isEmail(value)){
			setInnerHtmlValue("oemailError", "邮箱格式错误！");
			return false;
		}
		//ajax同步访问
		$.ajax({
			url: "../org/checkOemail.action?email="+$scope.entity.oemail,
			async:false,
			type:"POST",
			success:function(data){
				$scope.oemailc = data.info;
			},
			error:function(){
				$scope.oemailc = false;
			},
			dataType:"json"
		});
		if(!$scope.oemailc){
			setInnerHtmlValue("oemailError", "邮箱已存在！");
		}
		return $scope.oemailc;
	}
	
	//测试organization的opwd是否合法
	$scope.checkOpwd=function(){
		var value = document.getElementById("opwd").value;
		if(value.length >= 6 && value.length <= 20)
			return true;
		document.getElementById("opwdError").innerHTML="密码最低6位！";
		return false;
	}
	
	$scope.check=function(){
		//四个都检测成功弹出模态框进行邮箱验证和上传图片
		if($scope.checkOname() & $scope.checkOemail() & $scope.checkOpwd()){
			$scope.register();
		}
	}

	$scope.sendEmailVerCode=function(){
		$http({
		    method: "POST",
		    url: "../utils/sendEmailVerCode.action?type=org&email="+$scope.entity.oemail,
		}).then(function successCallback(response) {
			setInnerHtmlValue("verCodeError", "验证码已发送！");
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("verCodeError", "系统发生错误，请重试！");
	    });
	}
	
	$scope.register=function(){
		setInnerHtmlValue("verCodeError", "正在注册，请等待...");
		$http({
			method: "POST",
		    url: "../org/register.action",
		    data: $scope.entity
		}).then(function successCallback(response) {
			if(response.data.info == "注册成功，正在跳往登录界面！"){
				setInnerHtmlValue("verCodeError", "注册成功！组织ID为："+response.data.oid+"。正在跳往登录界面！");
				setTimeout(function(){
					window.location.href="../ui/login.action";
				}, 3000);
			}else if(response.data.info == "验证码错误！")
				setInnerHtmlValue("verCodeError", "验证码错误！");
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("verCodeError", "系统发生错误，请重试！");
	    });
	}
});