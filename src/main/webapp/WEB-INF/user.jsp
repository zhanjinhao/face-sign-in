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
<script src="<%=basePath%>/public/other/controller/userController.js"></script>

<script src="<%=basePath%>/public/other/js/pagination.js"></script>
<link rel="stylesheet" href="<%=basePath%>/public/other/css/pagination.css">

<link rel="stylesheet" href="<%=basePath%>/public/other/select2/select2.css" />
<link rel="stylesheet" href="<%=basePath%>/public/other/select2/select2-bootstrap.css" />
<script src="<%=basePath%>/public/other/select2/select2.min.js" type="text/javascript"></script>

<!-- 自定义CSS -->
<link href="<%=basePath%>/public/other/css/style.css" rel="stylesheet" />

<link href="<%=basePath%>/public/other/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" />
<script src="<%=basePath%>/public/other/bootstrap-select/bootstrap-select.js"></script>

</head>

<body class="hold-transition skin-red sidebar-mini" ng-app="facesignin"	ng-controller="userController" ng-init="getUserJoinGroupList();getUserName();">
	<div class="sign-up-top">
		<div class="container" style="margin-top: 10px;">
			<div style="vertical-align: middle; font-size: 28px;">
				<a href="#" style="text-decoration: none;"> <img src="holder.js/60x60"
					style="vertical-align: middle; height: 56px; border-radius: 50%;" />
					人脸识别签到系统
				</a>
				<div style="float: right; margin-top: 10px;">
					<div class="btn-group">
					    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">{{userName}}&nbsp;&nbsp;
					        <span class="caret"></span>
					    </button>
					    <ul class="dropdown-menu" role="menu">
					        <li>
					            <a href="#" data-toggle="modal" data-target="#changePwd" ng-click="changePwd={};setInnerHtmlValue('changePwdError', '');">修改密码</a>
					        </li>
					        <li>
					            <a href="#" data-toggle="modal" data-target="#changeEmail" ng-click="changeEmail={};setInnerHtmlValue('changeEmailError', '');">更换邮箱</a>
					        </li>
					        <li>
					            <a href="#" data-toggle="modal" data-target="#changeImg">更换照片</a>
					        </li>
					    </ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="changePwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						修改密码
					</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" style="margin-left: 0px; padding-left: 0px;">
					  <div class="form-group">
					    <label for="inputPassword3" class="col-sm-3 control-label">新密码</label>
					    <div class="col-sm-8">
					      <input type="password" class="form-control" id="changePwdnewpwd" ng-model="changePwd.newpwd" placeholder="新密码...">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-3 col-sm-9">
					      <button type="submit" class="btn btn-default" ng-click="sendChangePwdVerCode(changePwd.newpwd, 'newpwd')">发送验证码</button>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-3 control-label">验证码</label>
					    <div class="col-sm-8">
					      <input type="text" class="form-control" id="changePwdvercode" ng-model="changePwd.vercode" placeholder="验证码...">
					    </div>
					  </div>
					</form>
					<h4 class="error" id="changePwdError"></h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" ng-click="realChangePwd()">
						修改
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="changeEmail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						修改邮箱
					</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
					  <div class="form-group">
					    <label for="inputPassword3" class="col-sm-3 control-label">新邮箱</label>
					    <div class="col-sm-8">
					      <input type="text" class="form-control" id="changeEmailnewemail" ng-model="changeEmail.newemail" placeholder="新邮箱...">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-3 col-sm-9">
					      <button type="submit" class="btn btn-default" ng-click="sendChangeEmailVerCode(changeEmail.newemail, 'oldemail')">发送验证码</button>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-3 control-label">旧邮箱验证码</label>
					    <div class="col-sm-8">
					      <input type="text" class="form-control" id="changeEmailoldvercode" ng-model="changeEmail.oldvercode" placeholder="旧邮箱验证码...">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputPassword3" class="col-sm-3 control-label">新邮箱验证码</label>
					    <div class="col-sm-8">
					      <input type="text" class="form-control" id="changeEmailnewvercode" ng-model="changeEmail.newvercode" placeholder="新邮箱验证码...">
					    </div>
					  </div>
					</form>
					<h4 class="error" id="changeEmailError"></h4>
				
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" ng-click="realChangeEmail()">
						修改
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="changeImg" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">&nbsp;上传照片</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="panel-title" style="text-align: center;">
							<input type="file" class="hidden" id="image"
								onchange="imgPreview(this)"> <img
								style="height: 200px; border-radius: 50%;"
								src="holder.js/300x300" onclick="select_file('image');" id="img" />
						</div>
						<div style="margin-top: 15px;">
							<div class="row" style="margin: 0px;">
								<div class="col-md-9">
									<input type="text" style="font-size: 15px;" ng-model="changUserImg.uimgPath"
										ng-click="hideError('verCode')" placeholder="邮箱验证码..."
										tabindex="1" class="form-control" />
								</div>
								<div class="col-md-3">
									<button type="button" class="btn btn-primary"
										style="width: 100%;" ng-click="sendEmailVerCode()">发送验证码</button>
								</div>
							</div>
							<h4 class="error" id="verCodeError">点击图像区域上传照片</h4>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" ng-click="changeImg()">修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<div class="modal fade" id="userJoinGroupDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						查看组
					</h4>
				</div>
				<div class="modal-body">
					<table id="dataList"
						class="table table-bordered table-striped table-hover dataTable" style="text-align: center;">
						<thead>
							<tr>
								<th style="text-align: center;">组号</th>
								<th style="text-align: center;">组名</th>
								<th style="text-align: center;">组织</th>
								<th style="text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="gd in userJoinGroups">
								<td>{{gd.gid}}</td>
								<td>{{gd.gname}}</td>
								<td>{{gd.oname}}</td>
								<td class="text-center">
									<button type="button" class="btn bg-olive btn-xs"
										data-toggle="modal" data-target="#editModal"
										ng-click="exitGroup(gd.gid)">退出</button>
								</td>
							</tr>
						</tbody>
					</table>
					<tm-pagination conf="userJoinGroupDetail"></tm-pagination>
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
		`<div class="box-tools pull-right" style="margin-bottom: 6px; padding-right: 4%;">
				<div class="has-feedback">
					<button class="btn btn-default"  data-toggle="modal" data-target="#userJoinGroupDetail" ng-click="getUserJoinGroupDetail();">查看组</button>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px; padding-right: 1%;">
				<div class="has-feedback">
					<button class="btn btn-default"  data-toggle="modal" data-target="#joinGroup">加入组</button>
				</div>
			</div>
			<div class="box-tools pull-right"  ng-click="getUserJoinGroupList()" style="margin-bottom: 6px; padding-right: 1%; width: 20%;">
				<div class="has-feedback">
					<select class="selectpicker" data-live-search="true" ng-model="gid" ng-change="reload()"
		       				data-live-search-placeholder="搜索" data-width="100%" data-size="5" id="groupU">
						<option value="">不使用组</option>
						<option ng-repeat="g in userJoinGroupList" value="{{g.gid}}">{{g.gname}}</option>
					</select>
				</div>
			</div>
			
			<!--数据列表-->
			<table id="dataList"
				class="table table-bordered table-striped table-hover dataTable" style="text-align: center;">
				<thead>
					<tr>
						<th style="text-align: center;">活动名称</th>
						<th style="text-align: center;">举办机构</th>
						<th style="text-align: center;">负责人</th>
						<th style="text-align: center;">联系方式</th>
						<th style="text-align: center;">识别方式</th>
						<th style="text-align: center;">签到状态</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="ua in uas">
						<td>{{ua.aname}}</td>
						<td>{{ua.oname}}</td>
						<td>{{ua.achargeMan}}</td>
						<td>{{ua.acontact}}</td>
						<td ng-if="ua.scheckType == 1">
							手动录入
						</td>
						<td ng-if="ua.scheckType == 2">
							人脸识别
						</td>
						<td ng-if="ua.scheckType == null">
							未签到
						</td>
						<td ng-if="ua.sstatus == 1">
							签到成功
						</td>
						<td ng-if="ua.sstatus == 2">
							删除
						</td>
						<td ng-if="ua.sstatus == null">
							未签到
						</td>
					</tr>
				</tbody>
			</table>
			<!--数据列表/-->

		</div>
		<tm-pagination conf="userActivityConf"></tm-pagination>
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="joinGroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						加入组
					</h4>
				</div>
				<div class="modal-body">
					<div class="row" style="margin: 0px;">
						<div class="col-md-3">
							<button type="button" class="btn btn-primary"
								style="width: 100%;">组号</button>
						</div>
						<div class="col-md-9">
							<input type="text" style="font-size: 15px;" ng-model="joingid" placeholder="组号..."
								tabindex="1" class="form-control" ng-click="gidError('joingid')"/>
						</div>
					</div>
					<h4 class="error" id="joingidError"></h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" ng-click="joinGroup()">
						加入
					</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!--顶部效果-->
</body>
<%@ include file="/WEB-INF/footer.jsp"%>
</html>