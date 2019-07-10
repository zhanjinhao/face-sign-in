app.controller('baseController', function($scope, $controller, $http){
	
	$scope.selectIds = [];
	$scope.updateSelection = function($event, id) {		
		if($event.target.checked){
			//如果是被选中,则增加到数组
			$scope.selectIds.push(id);			
		}else{
			var idx = $scope.selectIds.indexOf(id);
			/* 删除数组指定的索引 */
		    $scope.selectIds.splice(idx, 1);
		}
	}
	
	$scope.jsonToString=function(jsonString, key){
		var objs = JSON.parse(jsonString);
		var value = "";
		var length = objs.length;
		for(var i = 0; i < length; i++){
			alert(objs[i][key] + " " + i + " " + length);
			if(i > 0){
				value += ",";
			}
			value += objs[i][key];
		}
		return value;
	}
	
	$scope.hideError=function(id){
		document.getElementById(id+"Error").innerHTML = "";
	}
	
	$scope.sendEmailVerCode=function(email, type){
		
		alert(email + "    " + type);
		if($scope.isUndefined(email) || $scope.isEmpty(email) || !$scope.isEmail(email)){
			alert("邮箱格式错误！");
			return;
		}
		
		$http({
		    method: "POST",
		    url: "../utils/sendEmailVerCode.action?email="+email+"&type="+type
		}).then(function successCallback(response) {
			setInnerHtmlValue("verCodeError", "验证码已发送！");
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("verCodeError", "系统发生错误，请重试！");
	    });
	}
	
	//由当前时间获得学号的正则表达式。
	$scope.getUidRegStr=function(){
		var today = new Date();
		var year = parseInt(today.getFullYear());
		var target = new Date(year, "8", "1", "0", "0", "0");
		var result = "";
		if(target > today){		//year-1, year-2, ...
			result = "(" + (year-1).toString() + ")|(" + (year-2).toString() + ")|(" + (year-3).toString() + ")|(" + (year-4).toString() + ")|(" + (year-5).toString() + ")|(" + (year-6).toString() + ")";
		}else{		//year, year-1, ...
			result = "(" + (year).toString() + ")|(" + (year-1).toString() + ")|(" + (year-2).toString() + ")|(" + (year-3).toString() + ")|(" + (year-4).toString() + ")|(" + (year-5).toString() + ")";
		}
		result = result.replace(new RegExp("20", "g"), "");
		return "^(" + result + "){1}" + "[0-9]{7}$";
	}
	
	$scope.isUndefined=function(obj){
		if(typeof(obj) == "undefined"){
			return true;
		}
		return false;
	}
	
	$scope.isUid=function(uid){
		var reg = new RegExp($scope.getUidRegStr());
		return reg.test(uid);
	}
	
	$scope.deleteTempImg = function(){
		$http({
			method:'POST',
			url:"../activity/deleteTempImg.action",
		}).then(function successCallback(response) {
			$scope.signinUsers = response.data.info;
			
	    }, function errorCallback(response) {
	    	
	    });
	}
	
	$scope.isEmpty=function(str){
		var reg = /^\s*$/;
		return reg.test(str);
	}
	
	//测试email格式是否合法
	$scope.isEmail=function(str) {
	    var reg=/^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/;
	    return reg.test(str);
	}
	
	$scope.getTimeMS=function(date){
		var date = new Date(date);
		return date.getTime(date);
	}
	
});

Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}        

function changeVerCode(obj, height, width){
	var currentTime = new Date().getTime();
	obj.src="../utils/verCode.action?height=" + height +"&width=" + width + "&time" + currentTime;
}

function setInnerHtmlValue(id, msg){
	document.getElementById(id).innerHTML=msg;
}