app.controller('uregisterController', function($scope, $controller, $http, $q) {

	$controller('baseController', {$scope:$scope});

	//测试user的uname是否合法
	$scope.checkUname=function(){
		var value = document.getElementById("uname").value;
		if(value.length <= 10 && value.length > 0)
			return true;
		document.getElementById("unameError").innerHTML="姓名最多10个字符且不为空！";
		return false;
	}
	
	//测试user的uid是否合法
	$scope.checkUid=function(){
		var reg = new RegExp($scope.getUidRegStr());
		var value = document.getElementById("uid").value;
		if(!reg.test(value)){
			setInnerHtmlValue("uidError", "学号格式错误！");
			return false;
		}
		$.ajax({
			url: "../user/checkUid.action?uid="+$scope.entity.uid,
			async:false,
			type:"POST",
			success:function(data){
				$scope.uidc = data.info;
			},
			error:function(){
				$scope.uidc = false;
			},
			dataType:"json"
		});
		if(!$scope.uidc){
			setInnerHtmlValue("uidError", "学号已注册！");
		}
		return $scope.uidc;
	}
	
	//测试user的uemail是否合法
	$scope.checkUemail=function(){
		var value = document.getElementById("uemail").value;
		if(!$scope.isEmail(value)){
			setInnerHtmlValue("uemailError", "邮箱格式错误！");
			return false;
		}
		//ajax同步访问
		$.ajax({
			url: "../user/checkUemail.action?email="+$scope.entity.uemail,
			async:false,
			type:"POST",
			success:function(data){
				$scope.uemailc = data.info;
			},
			error:function(){
				$scope.uemailc = false;
			},
			dataType:"json"
		});
		if(!$scope.uemailc){
			setInnerHtmlValue("uemailError", "邮箱已存在！");
		}
		return $scope.uemailc;
	}
	
	//测试user的upwd是否合法
	$scope.checkUpwd=function(){
		var value = document.getElementById("upwd").value;
		if(value.length >= 6)
			return true;
		document.getElementById("upwdError").innerHTML="密码最低6位！";
		return false;
	}
	
	var j = 1;
	
	$scope.openCamera=function(){
		
		if(j != 1)
			return;
		j++;
		
		let video = document.getElementById("video");
		
		/**
		 * constraints：打开摄像头时约束，下面的约束对video没有约束，它选择设备的默认摄像头，但是禁止了音频
		 */
	    let constraints = {
	       video: {},
	       audio : false
	    };
		
		let promise;
		if(navigator.mediaDevices.getUserMedia){
	        //最新标准API
	        promise = navigator.mediaDevices.getUserMedia(constraints);
	    } else if (navigator.webkitGetUserMedia){
	        //webkit内核浏览器
	        promise = navigator.webkitGetUserMedia(constraints);
	    } else if (navigator.mozGetUserMedia){
	        //Firefox浏览器
	        promise = navagator.mozGetUserMedia(constraints);
	    } else if (navigator.getUserMedia){
	        //旧版API
	        promise = navigator.getUserMedia(constraints);
	    }
	    
		promise.then(function (MediaStream) {
	        video.srcObject = MediaStream;
	        video.play();
	    });
		
	}
	
	$scope.check=function(){
		//四个都检测成功弹出模态框进行邮箱验证和上传图片
		if($scope.checkUid() & $scope.checkUname() & $scope.checkUemail() & $scope.checkUpwd()){
			$('#myModal').modal('show');
		}
	}

	$scope.sendEmailVerCode1=function(){
		$http({
		    method: "POST",
		    url: "../utils/sendEmailVerCode.action?email="+$scope.entity.uemail+"&type=user"
		}).then(function successCallback(response) {
			setInnerHtmlValue("verCodeError1", "验证码已发送！");
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("verCodeError1", "系统发生错误，请重试！");
	    });
	}
	$scope.sendEmailVerCode2=function(){
		$http({
			method: "POST",
			url: "../utils/sendEmailVerCode.action?email="+$scope.entity.uemail+"&type=user"
		}).then(function successCallback(response) {
			setInnerHtmlValue("verCodeError2", "验证码已发送！");
		}, function errorCallback(response) {
			setInnerHtmlValue("verCodeError2", "系统发生错误，请重试！");
		});
	}
	
	var i = 1;
	
	$scope.takePhotoRegister=function(){
		
		if(i != 1){
			setInnerHtmlValue("verCodeError2", "请不要重复注册！");
			return;
		}
		i++;
		
		$scope.entity.ufaceToken = getBase64();
		
		if($scope.entity.ufaceToken == null || $scope.entity.ufaceToken == ''){
			setInnerHtmlValue("verCodeError2", "请更换为谷歌浏览器！");
			return;
		}
		setInnerHtmlValue("verCodeError2", "正在注册，请等待...");
		$http({
			method: "POST",
		    url: "../user/register.action",
		    data: $scope.entity
		}).then(function successCallback(response) {
			setInnerHtmlValue("verCodeError2", response.data.info);
			if(response.data.info == "注册成功，正在跳往登录界面！"){
				setTimeout(function(){
					window.location.href="../ui/login.action";
				}, 3000);
			}
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("verCodeError2", "系统发生错误，请重试！");
	    });

	}
	
	$scope.register=function(){
		
		if(i != 1){
			setInnerHtmlValue("verCodeError1", "请不要重复注册！");
			return;
		}
		i++;
		
		if($scope.entity.ufaceToken == null || $scope.entity.ufaceToken == ''){
			setInnerHtmlValue("verCodeError1", "请上传照片！");
			return;
		}
		setInnerHtmlValue("verCodeError1", "正在注册，请等待...");
		$http({
			method: "POST",
		    url: "../user/register.action",
		    data: $scope.entity
		}).then(function successCallback(response) {
			setInnerHtmlValue("verCodeError1", response.data.info);
			if(response.data.info == "注册成功，正在跳往登录界面！"){
				setTimeout(function(){
					window.location.href="../ui/login.action";
				}, 3000);
			}
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("verCodeError1", "系统发生错误，请重试！");
	    });
	}
});

function getBase64() {
    //获得Canvas对象
	var cas = document.getElementById("myCanvas");
	var ctx = cas.getContext("2d");
	let video = document.getElementById("video");
    ctx.drawImage(video, 0, 0, 370, 300);
    var data = cas.toDataURL('image/png', 0.1);	//1表示质量(无损压缩)
    return data;
}

function select_file(fileid){
	setInnerHtmlValue("verCodeError1", "");
    document.getElementById(fileid).click();
}

function imgPreview(fileDom){
    //判断是否支持FileReader
    /**
     * FileReader 对象允许Web应用程序异步读取存储在用户计算机上的文件
     */
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("请更新您的浏览器！");
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
    /*
     * readAsDataURL 方法会读取指定的 Blob 或 File 对象。返回文件的base64编码
     */
    reader.readAsDataURL(file);
    /**
     * 当文件读取完成的时候会回调此函数，e就是获取的文件
     */
    reader.onload = function(e) {
        //获取图片dom
        var img = document.getElementById("imgUpload");
        //e是获取的文件，reader.readAsDataURL(file);读取的Base64编码存放在target.result中
        img.src = e.target.result;
        
        //获取 $scope
        var appElement = document.querySelector('[ng-controller=uregisterController]');
        var $scope = angular.element(appElement).scope();
        //去除 data:image/jpeg;base64,
        $scope.entity.ufaceToken=reader.result.replace("data:image/jpeg;base64,", "");
        console.log($scope.entity.ufaceToken);
    };
}