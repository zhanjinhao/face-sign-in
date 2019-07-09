app.controller('verifyController', function($scope, $controller, $http, $q) {
	
	$controller('baseController', {$scope:$scope});
	
	$scope.getBase64 = function() {
	    //获得Canvas对象
		var cas = document.getElementById("canvas");
		var ctx = cas.getContext("2d");
		let video = document.getElementById("video");
		let canvas = document.getElementById("canvas");
	    ctx.drawImage(video, 0, 0, 800, 600);
	    var data = cas.toDataURL('image/png', 0.1);	//1表示质量(无损压缩)
	    return data;
	}
	
	//'file'  等价于   浏览本地文件上传时本地文件的名字
	$scope.dataURLtoFile=function(dataurl, filename = 'file'){
		dataurl = dataurl + "";
	    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
	        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
	    while(n--){
	        u8arr[n] = bstr.charCodeAt(n);
	    }
	    return new File([u8arr], filename, {type:mime});
	}
	
	$scope.startSignIn=function(){
		
		//获得aid
		$http({
			method:'POST',
			url:"../activity/initVerify.action",
		}).then(function successCallback(response) {
			$scope.aid=response.data.aid;
			
			$scope.myVar = setInterval(function(){
				
				//设置定时器...
				var formData=new FormData();
				//'file' 等价于 <input name='file' />中的file
				formData.append("file", $scope.dataURLtoFile($scope.getBase64())); 
				$http({
					method:'POST',
					url:"../activity/upload.action?aid="+$scope.aid,
					data: formData,
					headers: {'Content-Type':undefined},
					transformRequest: angular.identity
				}).then(function successCallback(response) {
					
			    }, function errorCallback(response) {
			    	
			    });
			
			}, 3000);  //开发环境中每500ms提交一张
			
	    });
		
	}
	
	$scope.endSignIn=function(){
		clearInterval($scope.myVar);
	}
	
	$scope.startEchoSignUsers=function(){
		
		$scope.myEchoSignUsers = setInterval(function(){
			
			$http({
				method:'POST',
				url:"../activity/echoSigninUsers.action",
			}).then(function successCallback(response) {
				$scope.signinUsers = response.data.info;
				
		    }, function errorCallback(response) {
		    	
		    });
			
		}, 3000);
		
	}
	
	$scope.endEchoSignUsers=function(){
		clearInterval($scope.myEchoSignUsers);
		$scope.deleteTempImg();
		$http({
			method:'POST',
			url:"../activity/echoSigninUsers.action",
		}).then(function successCallback(response) {
			$scope.signinUsers = response.data.info;
	    }, function errorCallback(response) {
	    });
	}
	
});

window.onload=function() {
	document.getElementById("banzi").style.height = parseInt(window.screen.height) - 300 + "px";
    let constraints = {
       video: {},
       audio: true,
       audio : false
    };
    let video = document.getElementById("video");
    let promise;
    if (navigator.mediaDevices.getUserMedia) {
		//最新的标准API
    	promise = navigator.mediaDevices.getUserMedia(constraints);
    } else if (navigator.webkitGetUserMedia) {
		//webkit核心浏览器
		navigator.webkitGetUserMedia(constraints, success, error)
    } else if (navigator.mozGetUserMedia) {
		//firfox浏览器
		navigator.mozGetUserMedia(constraints, success, error);
    } else if (navigator.getUserMedia) {
		//旧版API
		navigator.getUserMedia(constraints, success, error);
    }
    promise.then(function (MediaStream) {
        video.srcObject = MediaStream;
        video.play();
    });
    
    
}