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
<script src="<%=basePath%>/public/other/controller/activityController.js"></script>

<script src="<%=basePath%>/public/other/js/pagination.js"></script>
<link rel="stylesheet" href="<%=basePath%>/public/other/css/pagination.css">

<link rel="stylesheet" href="<%=basePath%>/public/other/select2/select2.css" />
<link rel="stylesheet" href="<%=basePath%>/public/other/select2/select2-bootstrap.css" />
<script src="<%=basePath%>/public/other/select2/select2.min.js" type="text/javascript"></script>

<!-- 自定义CSS -->
<link href="<%=basePath%>/public/other/css/style.css" rel="stylesheet" />

</head>

<body class="hold-transition skin-red sidebar-mini" ng-app="facesignin" ng-controller="activityController" ng-init="getActivityName();">
	<div class="sign-up-top">
		<div class="container"">
			<div style="vertical-align: middle; font-size: 28px; margin-top: 3px;">
				<a href="<%=basePath%>/ui/login.action" style="text-decoration: none;"> <img src="holder.js/60x60"
					style="vertical-align: middle; height: 56px; border-radius: 50%;" />
					人脸识别签到系统
				</a>
					<div style="float: right; margin-top: 10px;">
									<div class="btn-group">
					    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">{{activityName}}&nbsp;&nbsp;
					        <span class="caret"></span>
					    </button>
					    <ul class="dropdown-menu" role="menu">
					        <li>
					            <a href="../ui/organization.action">组织主页</a>
					        </li>
					        <li>
					            <a href="#" ng-click="generateQRCode()" data-toggle="modal" data-target="#QRCode">手机验证</a>
					        </li>
					    </ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="QRCode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						扫描进入签到
					</h4>
				</div>
				<div class="modal-body">
				<div style="text-align: center; margin-top: 10px; margin-bottom: 10px;">
					<img src="<%=basePath%>/activity/qrcodes.action" onclick="changeQrCode(this);" style="vertical-align: middle; height: 300px;" />
				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="box-body">
		<!-- 数据表格 -->
		<div class="table-box">
			<div class="box-tools pull-right" style="margin-bottom: 6px; padding-left: 15px;">
				<div class="has-feedback">
				<a href="<%=basePath%>/activity/download.action">
					<button class="btn btn-default" ng-click="deleteTempImg();">
						导出活动记录
					</button>
				</a>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px; padding-left: 15px;" >
				<div class="has-feedback">
					<button class="btn btn-default" ng-click="enterVerify()">进入验证界面</button>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px;">
				<div class="has-feedback">
					<div class="row">
						<div class="col-md-9">
							<input class="form-control" ng-model="usersInActivity" placeholder="查询用户..."/>
						</div>
						<div class="col-md-3" style="padding: 0px;">
							<button class="btn btn-default" ng-click="findUsersInActivity()">查询</button>
						</div>
					</div>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px;">
				<div class="has-feedback">
					<div class="row">
						<div class="col-md-9">
							<input class="form-control" ng-model="uid" placeholder="用户账号..."/>
						</div>
						<div class="col-md-3" style="padding: 0px;">
							<button class="btn btn-default" ng-click="inputInfo();">录入</button>
						</div>
					</div>
				</div>
			</div>
			<!--数据列表-->
			<table id="dataList"
				class="table table-bordered table-striped table-hover dataTable" style="text-align: center;">
				<thead>
					<tr style="font-size: 15px;">
						<th class="sorting_asc" style="text-align: center;">参与者</th>
						<th class="sorting" style="text-align: center;">到场时间</th>
						<th class="sorting" style="text-align: center;">离场时间</th>
						<th class="sorting" style="text-align: center;">验证方式</th>
						<th class="sorting" style="text-align: center;">签到状态</th>
						<th class="text-center" style="text-align: center; ">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="user in users">
						<td>{{user.uid}}</td>
						<td>{{user.sinTime}}</td>
						<td>{{user.soutTime}}</td>
						<td ng-if="user.scheckType == '1'">
							手动录入
						</td>
						<td ng-if="user.scheckType == '2'">
							人脸录入
						</td>
						<td ng-if="user.sstatus == '1'">
							签到成功
						</td>
						<td ng-if="user.sstatus == '2'">
							删除
						</td>
						<td ng-if="user.sstatus == '3'">
							迟到
						</td>
						<td ng-if="user.sstatus == '2'">
							<button type="button" class="btn bg-olive btn-xs"
								data-toggle="modal" data-target="#editModal"
								ng-click="resigninRecord(user.uid)">恢复</button>
						</td>
						<td ng-if="user.sstatus == '1'">
							<button type="button" class="btn bg-olive btn-xs"
								data-toggle="modal" data-target="#editModal"
								ng-click="deleteUserRecord(user.uid)">删除</button>
						</td>
						<td ng-if="user.sstatus == '3'">
							<button type="button" class="btn bg-olive btn-xs"
								data-toggle="modal" data-target="#editModal"
								ng-click="deleteUserRecord(user.uid)">删除</button>
						</td>
					</tr>
					
				</tbody>
			</table>
			<!--数据列表/-->

		</div>
		<div id="userPageConfEle">
			<tm-pagination conf="userPageConf"></tm-pagination>
		</div>
	</div>


	<!--顶部效果-->
</body>
<%@ include file="/WEB-INF/footer.jsp"%>
</html>