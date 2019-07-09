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
<script src="<%=basePath%>/public/other/controller/adminController.js"></script>

<script src="<%=basePath%>/public/other/js/pagination.js"></script>
<link rel="stylesheet" href="<%=basePath%>/public/other/css/pagination.css">

<link rel="stylesheet" href="<%=basePath%>/public/other/select2/select2.css" />
<link rel="stylesheet" href="<%=basePath%>/public/other/select2/select2-bootstrap.css" />
<script src="<%=basePath%>/public/other/select2/select2.min.js" type="text/javascript"></script>

<!-- 自定义CSS -->
<link href="<%=basePath%>/public/other/css/style.css" rel="stylesheet" />

</head>

<body class="hold-transition skin-red sidebar-mini" ng-app="facesignin"	ng-controller="adminController">
	<div class="sign-up-top">
		<div class="container">
			<div style="vertical-align: middle; font-size: 28px; margin-top: 3px;">
				<a href="../ui/login.action" style="text-decoration: none;"> <img src="holder.js/60x60"
					style="vertical-align: middle; height: 56px; border-radius: 50%;" />
					人脸识别签到系统
				</a>
				<div style="float: right; margin-top: 10px;">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Admin&nbsp;&nbsp;
				        <span class="caret"></span>
				    </button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="showAct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						查看活动
					</h4>
				</div>
				<div class="modal-body">
					<table id="dataList"
						class="table table-bordered table-striped table-hover dataTable" style="text-align: center;">
						<thead>
							<tr>
								<th style="text-align: center;">活动名称</th>
								<th style="text-align: center; width: 165px;">开始时间</th>
								<th style="text-align: center; width: 165px;">结束时间</th>
								<th style="text-align: center; width: 165px;">组织</th>
								<th style="text-align: center; width: 75px;">参与人数</th>
								<th style="text-align: center; width: 100px;">状态</th>
								<th style="text-align: center;">属组</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="act in activities">
								<td>{{act.aname}}</td>
								<td>{{act.astartTime}}</td>
								<td>{{act.aendTime}}</td>
								<td>{{act.oname}}</td>
								<td>{{act.num}}</td>
								<td>
									<element ng-if="act.astatus == 1">
										正常
									</element>
									<element ng-if="act.astatus != 1">
										删除
									</element>
								</td>
								<td>{{act.gname}}</td>
							</tr>
						</tbody>
					</table>
					<tm-pagination conf="selectActConf"></tm-pagination>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="showUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						查看用戶
					</h4>
				</div>
				<div class="modal-body">
					<table id="dataList"
						class="table table-bordered table-striped table-hover dataTable" style="text-align: center;">
						<thead>
							<tr>
								<th style="text-align: center;">学号</th>
								<th style="text-align: center;">姓名</th>
								<th style="text-align: center;">签到次数</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="act in users">
								<td>{{act.uid}}</td>
								<td>{{act.uname}}</td>
								<td>{{act.num}}</td>
							</tr>
						</tbody>
					</table>
					<tm-pagination conf="selectUserConf"></tm-pagination>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="orgDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						查看组织
					</h4>
				</div>
				<div class="modal-body">
					<table id="dataList"
						class="table table-bordered table-striped table-hover dataTable" style="text-align: center;">
						<thead>
							<tr>
								<th style="text-align: center; width: 65px;">活动ID</th>
								<th style="text-align: center;">活动名称</th>
								<th style="text-align: center; width: 165px;">开始时间</th>
								<th style="text-align: center; width: 165px;">结束时间</th>
								<th style="text-align: center;">负责人</th>
								<th style="text-align: center;">联系方式</th>
								<th style="text-align: center; width: 75px;">参与人数</th>
								<th style="text-align: center;">状态</th>
								<th style="text-align: center;">属组</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="act in orgDetails">
								<td>{{act.aid}}</td>
								<td>{{act.aname}}</td>
								<td>{{act.astartTime}}</td>
								<td>{{act.aendTime}}</td>
								<td>{{act.achargeMan}}</td>
								<td>{{act.acontact}}</td>
								<td>{{act.num}}</td>
								<td>
									<element ng-if="act.astatus == 1">
										正常
									</element>
									<element ng-if="act.astatus != 1">
										删除
									</element>
								</td>
								<td>{{act.gname}}</td>
							</tr>
						</tbody>
					
					</table>
					<tm-pagination conf="orgDetailsConf"></tm-pagination>
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
			<div class="box-tools pull-right" style="margin-bottom: 6px;">
				<div class="has-feedback">
					<div class="row">
						<div class="col-md-9">
							<input class="form-control" ng-model="selectOrg" placeholder="查询组织..."/>
						</div>
						<div class="col-md-3" style="padding: 0px;">
							<button class="btn btn-default" ng-click="selectOrgs()">查询</button>
						</div>
					</div>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px;">
				<div class="has-feedback">
					<div class="row">
						<div class="col-md-9">
							<input class="form-control" ng-model="selectAct" placeholder="查询活动..."/>
						</div>
						<div class="col-md-3" style="padding: 0px;">
							<button class="btn btn-default" data-toggle="modal" data-target="#showAct" ng-click="reloadSelectActList()">查询</button>
						</div>
					</div>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px;">
				<div class="has-feedback">
					<div class="row">
						<div class="col-md-9">
							<input class="form-control" ng-model="selectUser" placeholder="查询用户..."/>
						</div>
						<div class="col-md-3" style="padding: 0px;">
							<button class="btn btn-default" data-toggle="modal" data-target="#showUser" ng-click="reloadSelectUserList()">查询</button>
						</div>
					</div>
				</div>
			</div>
			<!--数据列表-->
			<table id="dataList"
				class="table table-bordered table-striped table-hover dataTable" style="text-align: center;">
				<thead>
					<tr>
						<th style="text-align: center;">组织ID</th>
						<th style="text-align: center;">组织名称</th>
						<th style="text-align: center;">组织邮箱</th>
						<th style="text-align: center;">创建时间</th>
						<th style="text-align: center;">组织状态</th>
						<th style="text-align: center;">已举办活动数</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="ao in aos">
						<td>{{ao.oid}}</td>
						<td>{{ao.oname}}</td>
						<td>{{ao.oemail}}</td>
						<td>{{ao.ocreateTime}}</td>
						<td ng-if="ao.ostatus == null">
							正常
						</td>
						<td ng-if="ao.ostatus == 1">
							禁用
						</td>
						<td>{{ao.num}}</td>
						<td style="width: 200px;" >
							<a href="mailto:{{ao.oemail}}"><button type="button" class="btn bg-olive btn-xs">联系组织</button></a>
							<button type="button" class="btn bg-olive btn-xs"
								data-toggle="modal" data-target="#orgDetail"
								ng-click="getOrgDetail(ao.oid)">查看详情</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!--数据列表/-->

		</div>
		<tm-pagination conf="adminOrgConfig"></tm-pagination>
	</div>


	<!--顶部效果-->
</body>
<%@ include file="/WEB-INF/footer.jsp"%>
</html>