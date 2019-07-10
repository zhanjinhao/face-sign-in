<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Insert_Title_Here</title>
<link rel="Shortcut Icon" href="" />
 
<!-- jquery -->
<script src="<%=basePath%>/public/other/js/jquery-3.2.1.min.js"></script>

<!-- bootstrap -->
<link rel="stylesheet" href="<%=basePath%>/public/bootstrap/css/bootstrap.min.css" />
<script src="<%=basePath%>/public/bootstrap/js/bootstrap.min.js"></script>

<!-- 生成图片 -->
<script src="<%=basePath%>/public/other/js/holder.min.js"></script>

<!-- 表格的边框处理 -->
<link rel="stylesheet" href="<%=basePath%>/public/other/css/AdminLTE.css">
<link rel="stylesheet" href="<%=basePath%>/public/other/css/_all-skins.css">

<!-- angularjs -->
<script src="<%=basePath%>/public/angularjs/angular.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/public/other/service/base_pagination.js"></script>
<script src="<%=basePath%>/public/other/controller/baseController.js"></script>
<script src="<%=basePath%>/public/other/controller/mobileVerifyController.js"></script>

<script src="<%=basePath%>/public/other/js/pagination.js"></script>
<link rel="stylesheet" href="<%=basePath%>/public/other/css/pagination.css">

<link rel="stylesheet" href="<%=basePath%>/public/other/select2/select2.css" />
<link rel="stylesheet" href="<%=basePath%>/public/other/select2/select2-bootstrap.css" />
<script src="<%=basePath%>/public/other/select2/select2.min.js" type="text/javascript"></script>

<!-- 自定义CSS -->
<link href="<%=basePath%>/public/other/css/style.css" rel="stylesheet" />

<link href="<%=basePath%>/public/other/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" />
<script src="<%=basePath%>/public/other/bootstrap-select/bootstrap-select.js"></script>

<script src="<%=basePath%>/public/other/js/qrscan.js"></script>

<style type="text/css">

</style>


</head>

<body ng-app="facesignin" ng-controller="mobileVerifyController" ng-init="">
		<%-- <div class="sign-up-top">
		<div class="container" style="margin-top: 0px;">
			<div style="vertical-align: middle; font-size: 28px; margin-top: 3px;">
				<a href="<%=basePath%>/ui/login.action" style="text-decoration: none;"> <img src="holder.js/60x60"
					style="vertical-align: middle; height: 56px; border-radius: 50%;" />
					人脸识别签到系统
				</a>
				<div style="float: right; margin-top: 10px;">
				</div>
			</div>
		</div>
	</div> --%>
	
	<div style="padding:0px; margin-left: 0px; margin-right: 0px; text-align: center; padding-top: 40px;">
		<video class="myvideo" id="video" autoplay="autoplay" style="width: 1000px;"></video>
	</div>
	<div style="text-align: center; margin:0px; padding:0px; margin-top: 60px;">
		<button class="btn btn-info btn-lg" style="width: 200px; height: 100px; font-size: 38px;" ng-click="endSignIn();endEchoSignUsers();">停止签到</button>
		&nbsp;&nbsp;&nbsp;
		<button class="btn btn-primary btn-lg" style="width: 200px; height: 100px; font-size: 40px;" ng-click="entity={};startSignIn();startEchoSignUsers();">开始签到</button>
		&nbsp;&nbsp;&nbsp;
		<button class="btn btn-primary btn-lg" style="width: 250px; height: 100px; font-size: 40px;" onclick="toggleCamera()">切换摄像头</button>
			
		<table id="dataList"
			class="table table-bordered table-striped table-hover dataTable" style="text-align: center;">
			<tbody style="font-size: 50px;">
				<tr ng-repeat="user in signinUsers track by $index">
					<td>{{user}}</td>
				</tr>
			</tbody>
		</table>
	</div>

	<canvas id="canvas" hidden="hidden" width="600px" height="800px"></canvas>

</body>
</html>