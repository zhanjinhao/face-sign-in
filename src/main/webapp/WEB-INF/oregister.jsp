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
	src="<%=basePath%>/public/other/controller/oregisterController.js"></script>
</head>
<style>
</style>
<body ng-app="facesignin" ng-controller="oregisterController">
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
						&nbsp;&nbsp;&nbsp;<a href="../ui/uregister.action">用户注册</a></h4>
				</div>
			</div>
		</div>
	</div>
	
	<!--登陆标识-->
	<div class="login-title">组织注册</div>
	<!--登录框-->
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="form-line-up"></div>
			<div class="row">
				<div class="col-md-offset-1 col-md-10">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="panel-title" style="text-align: center;">
								<img style="height: 60px; border-radius: 50%;"
									src="holder.js/110x110" />
							</div>
						</div>
						<div class="panel-body">
							<form onsubmit="return false">
								<div class="form-group">
									<div class="input-group input-group-lg">
										<span class="input-group-addon"> <span>名&nbsp;&nbsp;&ensp;称<span
												style="color: red;"><sup>*</sup></span></span>
										</span> <input type="text" class="form-control"
											ng-model="entity.oname" placeholder="名称..." id="oname"
											ng-click="hideError('oname')" />
									</div>
									<div class="error" id="onameError"></div>
								</div>
								<div class="form-group">
									<div class="input-group input-group-lg">
										<span class="input-group-addon"> <span>密&nbsp;&nbsp;&ensp;码<span
												style="color: red;"><sup>*</sup></span></span>
										</span> <input type="password" class="form-control"
											ng-model="entity.opwd" placeholder="密码..." id="opwd"
											ng-click="hideError('opwd')">
									</div>
									<div class="error" id="opwdError"></div>
								</div>
								<div class="form-group">
									<div class="input-group input-group-lg">
										<span class="input-group-addon"> <span>邮&nbsp;&nbsp;&ensp;箱<span
												style="color: red;"><sup>*</sup></span></span>
										</span> <input type="text" class="form-control"
											ng-model="entity.oemail" placeholder="邮箱..." id="oemail"
											ng-click="hideError('oemail')">
									</div>
									<div class="error" id="oemailError"></div>
								</div>
								<div class="row" style="margin-top: 30px;margin-bottom: 30px;">
									<div class="col-md-8">
										<input id="" type="text" style="font-size: 15px;" ng-model="entity.oowner"
											ng-click="hideError('verCode')" placeholder="邮箱验证码..."
											tabindex="1" class="form-control" />
									</div>
									<div class="col-md-4">
										<button type="button" class="btn btn-primary"
											style="width: 100%;" ng-click="sendEmailVerCode()">发送验证码</button>
									</div>
									<h4 class="error" id="verCodeError"></h4>
								</div>
								<div class="form-group ok" style="text-align: center;"
									ng-click="check()">
									<input style="width: 100%;" type="submit" value="注册"
										class="btn btn-primary btn-lg">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--底部效果-->
	<%@ include file="/WEB-INF/footer.jsp"%>
</body>
</html>
