app.controller('organizationController', function($scope, $controller, $http, $q) {

	$controller('baseController', {$scope:$scope});
	
	$scope.createGroup=function(){
		
		if($scope.isUndefined($scope.groupName) || $scope.isEmpty($scope.groupName)){
			setInnerHtmlValue("createGroupError", "请填写组名！");
			return;
		}
			
		$http({
		    method: "POST",
		    url: "../org/createGroup.action?groupName="+$scope.groupName,
		}).then(function successCallback(response) {
			setInnerHtmlValue("createGroupError", response.data.info);
			$scope.getGroupList();
			$scope.groupDetailPage(1, 8);
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("verCodeError", "系统发生错误，请重试！");
	    });
	}
	
	$scope.findOrgActivity=function(){
		
		if($scope.isUndefined($scope.findAname) || $scope.isEmpty($scope.findAname)){
			$scope.activityPage($scope.activityPageConf.currentPage, $scope.activityPageConf.itemsPerPage);
			return;
		}
		$http({
			method: "POST",
			url: "../org/selectByLikeAname.action?alike="+$scope.findAname
		}).then(function successCallback(response) {
			$scope.activities=response.data;
		}, function errorCallback(response) {
			
		});
		
	}
	
	$scope.getOrgName=function(){
		
		$http({
		    method: "POST",
		    url: "../org/getOrgName.action"
		}).then(function successCallback(response) {
			$scope.orgName=response.data.info;
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
		    url: "../org/getCurrentEmail.action"
		    
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
	
	$scope.sendChangeEmailVerCode=function(pwd, type){
		
		alert($scope.changeEmail.newemail);
		
		if(!(!$scope.isUndefined(pwd) && !$scope.isEmpty(pwd) && $scope.isEmail(pwd))){
			setInnerHtmlValue("changeEmailError", "邮箱格式错误！");
			return;
		}
		setInnerHtmlValue("changeEmailError", "");
		$http({
			
			method: "POST",
			url: "../org/getCurrentEmail.action"
				
		}).then(function successCallback(response) {
			var email = response.data.info;
			alert(email);
			
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
	
	$scope.realChangePwd=function(){
		
		if($scope.isUndefined($scope.changePwd.newpwd) || $scope.isEmpty($scope.changePwd.newpwd) 
			|| $scope.isUndefined($scope.changePwd.vercode) || $scope.isEmpty($scope.changePwd.vercode)){
			
			setInnerHtmlValue("changePwdError", "请完善信息！");
			return;
		}

		$http({
			
		    method: "POST",
		    url: "../org/realChangePwd.action?newPwd="+$scope.changePwd.newpwd+"&vercode="+$scope.changePwd.vercode
		    
		}).then(function successCallback(response) {
			setInnerHtmlValue("changePwdError", response.data.info);
			$scope.activityPage($scope.activityPageConf.currentPage, $scope.activityPageConf.itemsPerPage);
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("changePwdError", "系统发生错误，请重试！");
	    });
		
	}
	
	$scope.realChangeEmail=function(){
		
		alert($scope.changeEmail.oldvercode);
		alert($scope.changeEmail.newvercode);
		
		if($scope.isUndefined($scope.changeEmail.oldvercode) || $scope.isEmpty($scope.changeEmail.oldvercode) 
				|| $scope.isUndefined($scope.changeEmail.newvercode) || $scope.isEmpty($scope.changeEmail.newvercode)){
			
			setInnerHtmlValue("changeEmailError", "请完善信息！");
			return;
		}
		
		$http({
			
			method: "POST",
			url: "../org/realChangeEmail.action?oldvercode="+$scope.changeEmail.oldvercode+"&newvercode="+$scope.changeEmail.newvercode +"&newEmail="+$scope.changeEmail.newemail
			
		}).then(function successCallback(response) {
			setInnerHtmlValue("changeEmailError", response.data.info);
			$scope.activityPage($scope.activityPageConf.currentPage, $scope.activityPageConf.itemsPerPage);
		}, function errorCallback(response) {
			setInnerHtmlValue("changeEmailError", "系统发生错误，请重试！");
		});
		
		
	}
	
	
	$scope.createActivity=function(){
		if($scope.checkGid() & $scope.checkAname() & $scope.checkAchargeMan() 
				& $scope.checkAcontact() & $scope.checkAstartTime() & $scope.checkAendTime()){
			
			$scope.entity.astartTime=new Date($scope.entity.astartTime).format("yyyy-MM-dd hh:mm:ss");
			$scope.entity.aendTime=new Date($scope.entity.aendTime).format("yyyy-MM-dd hh:mm:ss");
			$http({
			    method: "POST",
			    url: "../org/createActivity.action",
			    data: $scope.entity
			}).then(function successCallback(response) {
				setInnerHtmlValue("createActivityError", response.data.info);
				$scope.activityPage($scope.activityPageConf.currentPage, $scope.activityPageConf.itemsPerPage);
		    }, function errorCallback(response) {
		    	setInnerHtmlValue("createActivityError", "系统发生错误，请重试！");
		    });
			
		}
	}

	//分页控件配置
	$scope.activityPageConf = {
		currentPage: 1,
		totalItems: 10,
		itemsPerPage: 10,
		perPageOptions: [10, 20, 30, 40, 50],
		onChange: function(){
			//重新加载
			$scope.activityPage($scope.activityPageConf.currentPage, $scope.activityPageConf.itemsPerPage);
		}
	};
	//分页
	$scope.activityPage=function(page, rows){
		$http({
		    method: "POST",
		    url: "../org/activityPage.action?page="+page+"&rows="+rows,
		    data: $scope.entity
		}).then(function successCallback(response) {
			$scope.activities=response.data.rows;
			$scope.activityPageConf.totalItems=response.data.total;//更新总记录数
	    }, function errorCallback(response) {
	    	setInnerHtmlValue("createActivityError", "系统发生错误，请重试！");
	    });
	}
	//创建活动时下拉列表组选择
	$scope.getGroupList=function(){
		$http({
		    method: "POST",
		    url: "../org/getGroupList.action",
		}).then(function successCallback(response) {
			$scope.groupList=response.data;
			$("#groupL").selectpicker('refresh');  
			$("#groupM").selectpicker('refresh');  
	    }, function errorCallback(response) {
//	    	setInnerHtmlValue("createActivityError", "系统发生错误，请重试！");
	    });
	}
	
	$scope.enterActivity=function(aid){
		
		$http({
		    method: "POST",
		    url: "../activity/activitySession.action?aid="+aid,
		}).then(function successCallback(response) {
			if(response.data.info == 1){
				window.location.href="../ui/activity.action"
			}
	    }, function errorCallback(response) {
	    	alert("oh shit!")
	    });
		
	}
	
	$scope.checkAname=function(){
		var value = document.getElementById("aname").value;
		if(value.length <= 10 && value.length > 0)
			return true;
		document.getElementById("createActivityError").innerHTML="活动名称最多10个字符且不为空！";
		return false;
	}
	$scope.checkAchargeMan=function(){
		var value = document.getElementById("achargeMan").value;
		if(value.length <= 10 && value.length > 0)
			return true;
		document.getElementById("createActivityError").innerHTML="活动负责人最多10个字符且不为空！";
		return false;
	}
	$scope.checkAcontact=function(){
		var value = document.getElementById("acontact").value;
		if(value.length <= 20 && value.length > 0 && $scope.isEmail(value))
			return true;
		document.getElementById("createActivityError").innerHTML="邮箱格式错误！";
		return false;
	}
	$scope.checkAstartTime=function(){
		if(!$scope.isUndefined($scope.entity.astartTime))
			return true;
		document.getElementById("createActivityError").innerHTML="请选择活动结束时间！";
		return false;
	}
	$scope.checkAendTime=function(){
		if(!$scope.isUndefined($scope.entity.aendTime))
			return true;
		document.getElementById("createActivityError").innerHTML="请选择活动结束时间！";
		return false;
	}
	$scope.checkGid=function(){
		if($scope.isUndefined($scope.entity.gid) || $scope.entity.gid == ""){
			$scope.entity.gid = "10000";
		}
		return true;
	}
	
	$scope.groupDetailConf = {
		currentPage: 1,
		totalItems: 10,
		itemsPerPage: 8,
		perPageOptions: [8, 16, 24],
		onChange: function(){
			$scope.groupDetailPage($scope.groupDetailConf.currentPage, $scope.groupDetailConf.itemsPerPage);
		}
	};
	$scope.groupDetailPage=function(page, rows){
		$http({
		    method: "POST",
		    url: "../org/getGroupDetail.action?page="+page+"&rows="+rows,
		}).then(function successCallback(response) {
			$scope.groupDetail = response.data.rows;
			$scope.groupDetailConf.totalItems=response.data.total;//更新总记录数
	    }, function errorCallback(response) {
	    	
	    });
	}
	
	$scope.checkAname2=function(){
		var value = document.getElementById("aname2").value;
		if(value.length <= 10 && value.length > 0)
			return true;
		document.getElementById("modifyActivityError").innerHTML="活动名称最多10个字符且不为空！";
		return false;
	}
	$scope.checkAchargeMan2=function(){
		var value = document.getElementById("achargeMan2").value;
		if(value.length <= 10 && value.length > 0)
			return true;
		document.getElementById("modifyActivityError").innerHTML="活动负责人最多10个字符且不为空！";
		return false;
	}
	$scope.checkAcontact2=function(){
		var value = document.getElementById("acontact2").value;
		if(value.length <= 20 && value.length > 0 && $scope.isEmail(value))
			return true;
		document.getElementById("modifyActivityError").innerHTML="邮箱格式错误！";
		return false;
	}
	$scope.checkAstartTime2=function(){
		if(!$scope.isUndefined($scope.entity2.astartTime))
			return true;
		document.getElementById("modifyActivityError").innerHTML="请选择活动结束时间！";
		return false;
	}
	$scope.checkAendTime2=function(){
		if(!$scope.isUndefined($scope.entity2.aendTime))
			return true;
		document.getElementById("modifyActivityError").innerHTML="请选择活动结束时间！";
		return false;
	}
	$scope.checkGid2=function(){
		if($scope.isUndefined($scope.entity2.gid) || $scope.entity2.gid == ""){
			$scope.entity2.gid = "10000";
		}
		return true;
	}
	
	$scope.changPwd=function(){
		
		
		
		
	}
	
	
	$scope.sendVercode=function(verCodeName){
		
		
		
	}
	
	$scope.deleteActivity=function(aid){
		if (confirm("删除活动操作不可恢复，请慎重确定！")==true){
		
			$http({
			    method: "POST",
			    url: "../org/deleteActivity.action?aid="+aid,
			}).then(function successCallback(response) {
				
				if($scope.isUndefined($scope.findAname) || $scope.isEmpty($scope.findAname)){
					$scope.activityPage($scope.activityPageConf.currentPage, $scope.activityPageConf.itemsPerPage);
				}else{
					$scope.findOrgActivity();
				}
				
		    }, function errorCallback(response) {
		    	
		    });
			
		}
	}
	
	$scope.deleteGroup=function(gid){
		
		$http({
			method: "POST",
			url: "../org/getGidUsedNum.action?gid="+gid,
		}).then(function successCallback(response) {
			$scope.gidUsedNum = response.data.info;
			$scope.msg = "此组被" + $scope.gidUsedNum + "个活动使用，删除组后会删除对应活动信息。 请慎重确定！"; 
			if (confirm($scope.msg)==true){
				$http({
					method: "POST",
					url: "../org/deleteGroup.action?gid="+gid,
				}).then(function successCallback(response) {
					$scope.groupDetailPage($scope.groupDetailConf.currentPage, $scope.groupDetailConf.itemsPerPage);
				}, function errorCallback(response) {
					
				});
			}
		}, function errorCallback(response) {
			
		});
		
	}
	
	$scope.initModify=function(act){
		$scope.entity2={};
		setInnerHtmlValue('modifyActivityError','');
		$scope.entity2.aid = act.aid;
		$scope.entity2.aname = act.aname;
		$scope.entity2.achargeMan = act.achargeMan;
		$scope.entity2.acontact = act.acontact;
		$scope.entity2.astartTime = new Date(act.astartTime);
		$scope.entity2.aendTime = new Date(act.aendTime);
	}
	
	$scope.modifyActivity=function(){
		
		if($scope.checkGid2() & $scope.checkAname2() & $scope.checkAchargeMan2() 
				& $scope.checkAcontact2() & $scope.checkAstartTime2() & $scope.checkAendTime2()){
		
			$scope.entity2.astartTime=new Date($scope.entity2.astartTime).format("yyyy-MM-dd hh:mm:ss");
			$scope.entity2.aendTime=new Date($scope.entity2.aendTime).format("yyyy-MM-dd hh:mm:ss");
			
			$http({
			    method: "POST",
			    url: "../org/modifyActivity.action",
			    data: $scope.entity2
			}).then(function successCallback(response) {
				setInnerHtmlValue("modifyActivityError", response.data.info);
				$scope.activityPage($scope.activityPageConf.currentPage, $scope.activityPageConf.itemsPerPage);
		    }, function errorCallback(response) {
		    	setInnerHtmlValue("modifyActivityError", "系统发生错误，请重试！");
		    });
		}
		
	}
	 	
	$scope.addAdmin=function(){
		
		if($scope.isUndefined($scope.addAdminUid) || !$scope.isUid($scope.addAdminUid)){
			setInnerHtmlValue("addAdminError", "学号错误！");
			return;
		}
		$http({
		    method: "POST",
		    url: "../org/addOrgAdmin.action?addOrgAdmin=" + $scope.addAdminUid
		}).then(function successCallback(response) {
			setInnerHtmlValue("addAdminError", response.data.info);
	    }, function errorCallback(response) {
	    });
		
		
	}
	
	$scope.selectOrgAdmins=function(){
		$http({
		    method: "POST",
		    url: "../org/selectOrgAdmins.action",
		}).then(function successCallback(response) {
			$scope.orgAdmins = response.data.info;
			console.log($scope.orgAdmins);
			setInnerHtmlValue("addAdminError", response.data.info);
	    }, function errorCallback(response) {
	    	
	    });
	}
	
	$scope.deleteOrgAdmins=function(id){
		$http({
			method: "POST",
			url: "../org/deleteOrgAdmins.action?uid=" + id,
		}).then(function successCallback(response) {
			$scope.selectOrgAdmins();
		}, function errorCallback(response) {
			
		});
	}
	
});