<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Insert_Title_Here</title>
<link rel="Shortcut Icon" href="" />
<script src="<%=basePath%>/public/other/js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet"
	href="<%=basePath%>/public/bootstrap/css/bootstrap.min.css" />
<script src="<%=basePath%>/public/bootstrap/js/bootstrap.min.js"></script>
<link href="<%=basePath%>/public/bootstrap/css/bootstrap-select.min.css"
	rel="stylesheet" />
<script src="<%=basePath%>/public/bootstrap/js/bootstrap-select.js"></script>

<script src="<%=basePath%>/public/other/js/holder.min.js"></script>

<link href="<%=basePath%>/public/other/css/style.css" rel="stylesheet" />

<script src="<%=basePath%>/public/angularjs/angular.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/public/other/service/base.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/public/other/controller/baseController.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/public/other/controller/uregisterController.js"></script>
</head>
<style>
</style>

<body ng-app="facesignin" ng-controller="uregisterController">
	<!--顶部效果-->
	<div class="sign-up-top">
		<div class="container" style="margin-top: 10px;">
			<div style="vertical-align:middle; font-size:28px;">
				<a href="#" style="text-decoration: none;">
						<img src="holder.js/60x60" style="vertical-align:middle; height: 56px; border-radius: 50%;"/>
						人脸识别签到系统
				</a>
				<div style="float: right; margin-top: 10px;">
					<h4><a href="../ui/login.action">登录</a>
						&nbsp;&nbsp;&nbsp;<a href="../ui/oregister.action">组织注册</a></h4>
				</div>
			</div>
		</div>
	</div>
	
	<!--登陆标识-->
	<div class="login-title">用户注册</div>
	<!--登录框-->
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="form-line-up"></div>
			<div class="row">
				<div class="col-md-offset-1 col-md-10">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="panel-title" style="text-align: center;">
								<img style="height: 110px; border-radius: 50%;"
									src="holder.js/110x110" />
							</div>
						</div>
						<div class="panel-body">
							<form onsubmit="return false">

								<div class="form-group">
									<div class="input-group input-group-lg">
										<span class="input-group-addon"> <span>账&nbsp;&nbsp;&ensp;号<span
												style="color: red;"><sup>*</sup></span></span>
										</span> <input type="text" class="form-control" ng-model="entity.uid"
											placeholder='账号...' id="uid" ng-click="hideError('uid')" />
									</div>
									<div class="error" id="uidError"></div>
								</div>
								<div class="form-group">
									<div class="input-group input-group-lg">
										<span class="input-group-addon"> <span>姓&nbsp;&nbsp;&ensp;名<span
												style="color: red;"><sup>*</sup></span></span>
										</span> <input type="text" class="form-control"
											ng-model="entity.uname" placeholder='昵称...' id="uname"
											ng-click="hideError('uname')" />
									</div>
									<div class="error" id="unameError"></div>
								</div>
								<div class="form-group">
									<div class="input-group input-group-lg">
										<span class="input-group-addon"> <span>密&nbsp;&nbsp;&ensp;码<span
												style="color: red;"><sup>*</sup></span></span>
										</span> <input type="password" class="form-control"
											ng-model="entity.upwd" placeholder="密码..." id="upwd"
											ng-click="hideError('upwd')">
									</div>
									<div class="error" id="upwdError"></div>
								</div>
								<div class="form-group">
									<div class="input-group input-group-lg">
										<span class="input-group-addon"> <span>邮&nbsp;&nbsp;&ensp;箱<span
												style="color: red;"><sup>*</sup></span></span>
										</span> <input type="text" class="form-control"
											ng-model="entity.uemail" placeholder="邮箱..." id="uemail"
											ng-click="hideError('uemail')">
									</div>
									<div class="error" id="uemailError"></div>
								</div>

								<div class="form-group ok" style="text-align: center;"
									ng-click="check()">
									<input style="width: 100%;" type="submit" value="下一步"
										class="btn btn-primary btn-lg">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
			
				<ul id="myTab" class="nav nav-tabs" style="margin-bottom: 10px; margin-top: 15px; padding-left: 2px;">
				    <li class="active">
				        <a href="#img" data-toggle="tab">上传照片</a>
				    </li>
				    <li>
				    	<a href="#takePhoto" data-toggle="tab" ng-click="openCamera();">拍照</a>
				    </li>
				</ul>
		
				<div id="myTabContent" class="tab-content">
					 <div class="tab-pane fade in active" id="img">
						<div class="modal-body">
					    	<div class="row">
								<div class="panel-title" style="text-align: center;">
									<input type="file" class="hidden" id="image"
										onchange="imgPreview(this)"> <img
										style="height: 200px; border-radius: 50%;"
										src="holder.js/300x300" onclick="select_file('image');" id="imgUpload" />
								</div>
								<div style="margin-top: 15px;">
									<div class="row" style="margin: 0px;">
										<div class="col-md-9">
											<input type="text" style="font-size: 15px;" ng-model="entity.uimgPath"
												ng-click="hideError('verCode')" placeholder="邮箱验证码..."
												tabindex="1" class="form-control" />
										</div>
										<div class="col-md-3">
											<button type="button" class="btn btn-primary"
												style="width: 100%;" ng-click="sendEmailVerCode1()">发送验证码</button>
										</div>
									</div>
									<h4 class="error" id="verCodeError1">点击图像区域上传照片</h4>
								</div>
							</div>
					    </div>
					    <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary" ng-click="register()">注册</button>
						</div>
					 </div>
					   
					 <div class="tab-pane fade" id="takePhoto">
					 	<div class="modal-body">
						  	<div class="row">
								<div class="panel-title" style="text-align: center;">
									<video id="video" style="height:350px; width:370px;" autoplay="autoplay"></video>
								</div>
								<div style="margin-top: 15px;">
									<div class="row" style="margin: 0px;">
										<div class="col-md-9">
											<input type="text" style="font-size: 15px;" ng-model="entity.uimgPath"
												ng-click="hideError('verCode')" placeholder="邮箱验证码..."
												tabindex="1" class="form-control" />
										</div>
										<div class="col-md-3">
											<button type="button" class="btn btn-primary"
												style="width: 100%;" ng-click="sendEmailVerCode2()">发送验证码</button>
										</div>
									</div>
									<h4 class="error" id="verCodeError2"></h4>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary" ng-click="takePhotoRegister()">注册</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<canvas id="myCanvas" hidden="hidden" width="370px" height="300px"></canvas>
	<!--底部效果-->
	<%@ include file="/WEB-INF/footer.jsp"%>
</body>
</html>
