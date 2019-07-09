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
		<link rel="stylesheet" href="<%=basePath%>/public/bootstrap/css/bootstrap.min.css" />
		<script src="<%=basePath%>/public/bootstrap/js/bootstrap.min.js"></script>
		<link href="<%=basePath%>/public/bootstrap/css/bootstrap-select.min.css" rel="stylesheet" />
		<script src="<%=basePath%>/public/bootstrap/js/bootstrap-select.js"></script>
		
		<script src="<%=basePath%>/public/other/js/holder.min.js"></script>
		<link href="<%=basePath%>/public/other/css/style.css" rel="stylesheet" />
		
		<script src="<%=basePath%>/public/angularjs/angular.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/public/other/service/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/public/other/controller/baseController.js"></script>
		<script type="text/javascript" src="<%=basePath%>/public/other/controller/loginController.js"></script>
	</head>
	<body ng-app="facesignin" ng-controller="loginController">
		<!--顶部效果-->
		<div class="sign-up-top">
			<div class="container">
				<div style="vertical-align:middle; font-size:28px; margin-top: 3px;">
					<a href="#" style="text-decoration: none;">
							<img src="holder.js/60x60" style="vertical-align:middle; height: 56px; border-radius: 50%;"/>
							人脸识别签到系统
					</a>
					<div style="float: right; margin-top: 10px;">
						<h4><a href="../ui/uregister.action">用户注册</a>
							&nbsp;&nbsp;&nbsp;<a href="../ui/oregister.action">组织注册</a></h4>
					</div>
				</div>
			</div>
		</div>

		<!--登陆标识-->
		<div class="login-title">登录</div>
		<!--登录框-->
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<div class="form-line-up"></div>
				<div class="row">
					<div class="col-md-offset-1 col-md-10">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="panel-title" style="text-align: center;">
									<img style="height: 100%; border-radius: 50%;" src="holder.js/110x110" alt="">
								</div>
							</div>
							<div class="panel-body">
								<form>
									<div class="form-group">
										<div class="input-group input-group-lg">
											<span class="input-group-addon">
												<span>账&nbsp;&nbsp;&ensp;号<span style="color: red;"><sup>*</sup></span></span>
											</span>
											<input type="text" class="form-control" ng-model="entity.id" placeholder='账号...' id="id" ng-click="hideError('id')" />
										</div>
										<div class="error" id="idError"></div>
									</div>
									<div class="form-group">
										<div class="input-group input-group-lg">
											<span class="input-group-addon">
												<span>密&nbsp;&nbsp;&ensp;码<span style="color: red;"><sup>*</sup></span></span>
											</span>
											<input type="password" class='form-control' placeholder='密码...' id="pwd" ng-model="entity.pwd" ng-click="hideError('pwd')"/>
										</div>
										<div class="error" id="pwdError"></div>
									</div>
									<!-- verification code -->
									<div class="form-group">
										<div class="input-group input-group-lg">
											<span class="input-group-addon">
												<span>验证码<span style="color: red;"><sup>*</sup></span></span>
											</span>
											<input type="text" class='form-control' placeholder='验证码' id="verCode" ng-model="entity.verCode" ng-click="hideError('verCode')" style="width:73%;" />&nbsp;&nbsp;
											<span>
												<img onclick="changeVerCode(this, 40, 80)" width="23.4%" height="100%;" src="<%=basePath%>/utils/verCode.action?height=40&width=80" />
											</span>
										</div>
										<h4 class="error" id="verCodeError"></h4>
									</div>
									<div class="form-group ok" style="text-align: center;" ng-click="user={};org={};check();">
										<input style="width: 100%;" value="登录" class='btn btn-primary btn-lg'>
									</div>
									
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--顶部效果-->
		<%@ include file="/WEB-INF/footer.jsp" %>
	</body>
</html>