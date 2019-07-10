app.controller('mobileVerifyController', function($scope, $controller, $http) {
	
	$controller('baseController', {$scope:$scope});
	
	$scope.getBase64 = function() {
	    //获得Canvas对象
		var cas = document.getElementById("canvas");
		var ctx = cas.getContext("2d");
		let video = document.getElementById("video");
		let canvas = document.getElementById("canvas");
	    ctx.drawImage(video, 0, 0, 600, 800);
	    var data = cas.toDataURL('image/png', 0.1);	//1表示质量(无损压缩)
	    return data;
	}
	
	//'file'  等价于   浏览本地文件上传时本地文件的名字  ... edge有兼容性问题
	$scope.dataURLtoFile=function(dataurl, filename = 'file'){
		dataurl = dataurl + "";
	    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
	        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
	    while(n--){
	        u8arr[n] = bstr.charCodeAt(n);
	    }
	    return new File([u8arr], filename, {type:mime});
	}
	
	$scope.dataURLtoBlob = function(dataurl) {
		dataurl = dataurl + "";
		var arr = dataurl.split(',');
		var mime = arr[0].match(/:(.*?);/)[1];
		var bstr =atob(arr[1]);
		var n = bstr.length;
		var u8arr =new Uint8Array(n);
		while (n--) {
			u8arr[n] = bstr.charCodeAt(n);    
		}
		return new Blob([u8arr], {type: mime });
	}
	$scope.blobToFile = function(theBlob, fileName = 'file'){
		theBlob.lastModifiedDate = new Date();    
		theBlob.name = fileName;    
		return theBlob;
	} 
	$scope.signCount = 0;
	$scope.startSignIn=function(){
		if($scope.signCount == 1){
			alert("请不要重复开启签到！")
			return;
		}
		$scope.signCount = 1;
		alert("签到已开启！")
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
				formData.append("file", $scope.blobToFile($scope.dataURLtoBlob($scope.getBase64()))); 
				$http({
					method:'POST',
					url:"../activity/upload.action?aid="+$scope.aid,
					data: formData,
					headers: {'Content-Type':undefined},
					transformRequest: angular.identity
				}).then(function successCallback(response) {
					
			    }, function errorCallback(response) {
			    	
			    });
			}, 3000);
				
	    });
		
	}
	
	$scope.endSignIn=function(){
		
		if($scope.signCount == 0){
			alert("没有正在进行的签到！")
			return;
		}
		$scope.signCount = 0;
		alert("签到已关闭！")
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
		$http({
			method:'POST',
			url:"../activity/echoSigninUsers.action",
		}).then(function successCallback(response) {
			$scope.signinUsers = response.data.info;
	    }, function errorCallback(response) {
	    });
		
	}
	
});

var i = 1;

function toggleCamera(){
	
	if(i == 1){
		alert("转为前置摄像头！");
		i = 0;
		let video = document.getElementById("video");
		
		if(navigator.mediaDevices === undefined) {
		    navigator.mediaDevices = {};
		}
		if(navigator.mediaDevices.getUserMedia === undefined) {
		    navigator.mediaDevices.getUserMedia = promisifiedOldGUM;
		}
		window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;
		var exArray = [];
	    navigator.mediaDevices.enumerateDevices().then(function(devices) {
	        devices.forEach(function (dv) {
	            var kind = dv.kind;
	            if (kind.match(/^video.*/)) {
	            	exArray.push(dv.deviceId);
	                console.log(dv);
	            }
	        });
	        let constraints = {
	    		video: {
	    			deviceId: exArray[0]
	    		},
	    		audio : false  
	        };
	        
	        let promise;
	        if (navigator.mediaDevices.getUserMedia) {
	    		//最新的标准API
	        	promise = navigator.mediaDevices.getUserMedia(constraints);
	        } else if (navigator.webkitGetUserMedia) {
	    		//webkit核心浏览器
	        	promise = navigator.webkitGetUserMedia(constraints, success, error)
	        } else if (navigator.mozGetUserMedia) {
	    		//firfox浏览器
	        	promise = navigator.mozGetUserMedia(constraints, success, error);
	        } else if (navigator.getUserMedia) {
	    		//旧版API
	        	promise = navigator.getUserMedia(constraints, success, error);
	        }
	        promise.then(function (MediaStream) {
	            video.srcObject = MediaStream;
	            video.play();
	        });
	        
	    });
		
	}
	else{
		alert("转为后置摄像头！");
		i = 1;
		let video = document.getElementById("video");
		
		if(navigator.mediaDevices === undefined) {
		    navigator.mediaDevices = {};
		}
		if(navigator.mediaDevices.getUserMedia === undefined) {
		    navigator.mediaDevices.getUserMedia = promisifiedOldGUM;
		}
		window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;
		var exArray = [];
	    navigator.mediaDevices.enumerateDevices().then(function(devices) {
	        devices.forEach(function (dv) {
	            var kind = dv.kind;
	            if (kind.match(/^video.*/)) {
	            	exArray.push(dv.deviceId);
	                console.log(dv);
	            }
	        });
	        i = exArray.length - 1;
	        let constraints = {
	    		video: {
	    			deviceId: exArray[i]
	    		},
	    		audio : false  
	        };
	        
	        let promise;
	        if (navigator.mediaDevices.getUserMedia) {
	    		//最新的标准API
	        	promise = navigator.mediaDevices.getUserMedia(constraints);
	        } else if (navigator.webkitGetUserMedia) {
	    		//webkit核心浏览器
	        	promise = navigator.webkitGetUserMedia(constraints, success, error)
	        } else if (navigator.mozGetUserMedia) {
	    		//firfox浏览器
	        	promise = navigator.mozGetUserMedia(constraints, success, error);
	        } else if (navigator.getUserMedia) {
	    		//旧版API
	        	promise = navigator.getUserMedia(constraints, success, error);
	        }
	        promise.then(function (MediaStream) {
	            video.srcObject = MediaStream;
	            video.play();
	        });
	        
	    });
		
	}
	
}

window.onload=function() {
	
	let video = document.getElementById("video");
	
	if(navigator.mediaDevices === undefined) {
	    navigator.mediaDevices = {};
	}
	if(navigator.mediaDevices.getUserMedia === undefined) {
	    navigator.mediaDevices.getUserMedia = promisifiedOldGUM;
	}
	window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;
	var exArray = [];
    navigator.mediaDevices.enumerateDevices().then(function(devices) {
        devices.forEach(function (dv) {
            var kind = dv.kind;
            if (kind.match(/^video.*/)) {
            	exArray.push(dv.deviceId);
                console.log(dv);
            }
        });
        i = exArray.length - 1;
        let constraints = {
    		video: {
    			deviceId: exArray[i]
    		},
    		audio : false  
        };
        
        let promise;
        if (navigator.mediaDevices.getUserMedia) {
    		//最新的标准API
        	promise = navigator.mediaDevices.getUserMedia(constraints);
        } else if (navigator.webkitGetUserMedia) {
    		//webkit核心浏览器
        	promise = navigator.webkitGetUserMedia(constraints, success, error)
        } else if (navigator.mozGetUserMedia) {
    		//firfox浏览器
        	promise = navigator.mozGetUserMedia(constraints, success, error);
        } else if (navigator.getUserMedia) {
    		//旧版API
        	promise = navigator.getUserMedia(constraints, success, error);
        }
        promise.then(function (MediaStream) {
            video.srcObject = MediaStream;
            video.play();
        });
        
    });
}