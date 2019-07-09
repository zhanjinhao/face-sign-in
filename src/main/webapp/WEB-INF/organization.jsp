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
<script src="<%=basePath%>/public/other/controller/organizationController.js"></script>

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

<body class="hold-transition skin-red sidebar-mini" ng-app="facesignin"	ng-controller="organizationController" ng-init="entity2={};getGroupList();getOrgName();">
	<div class="sign-up-top">
		<div class="container" style="margin-top: 0px;">
			<div style="vertical-align: middle; font-size: 28px; margin-top: 3px;">
				<a href="<%=basePath%>/ui/login.action" style="text-decoration: none;"> <img src="holder.js/60x60"
					style="vertical-align: middle; height: 56px; border-radius: 50%;" />
					人脸识别签到系统
				</a>
				<div style="float: right; margin-top: 10px;">
					<div class="btn-group">
					    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">{{orgName}}&nbsp;&nbsp;
					        <span class="caret"></span>
					    </button>
					    <ul class="dropdown-menu" role="menu">
					        <li>
					            <a href="#" data-toggle="modal" data-target="#changePwd" ng-click="changePwd={};setInnerHtmlValue('changePwdError', '');">修改密码</a>
					        </li>
					        <li>
					            <a href="#" data-toggle="modal" data-target="#changeEmail" ng-click="changeEmail={};setInnerHtmlValue('changeEmailError', '');">更换邮箱</a>
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
	<div class="box-body">

		<!-- 数据表格 -->
		<div class="table-box">
			<div class="box-tools pull-right" style="margin-bottom: 6px; padding-left: 15px;">
				<div class="has-feedback">
					<button class="btn btn-default" data-toggle="modal" data-target="#createActivity" onclick="setInnerHtmlValue('createActivityError','')"  ng-click="entity={};getGroupList();">创建活动</button>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px;">
				<div class="has-feedback">
					<button class="btn btn-default" data-toggle="modal" data-target="#createGroup" onclick="setInnerHtmlValue('createGroupError', '');">创建组</button>
				</div>,
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px; padding-left: 15px;" >
				<div class="has-feedback">
					<button class="btn btn-default" data-toggle="modal" data-target="#showGroup">查看组</button>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px;">
				<div class="has-feedback">
					<button class="btn btn-default" data-toggle="modal" data-target="#addAdmin" onclick="setInnerHtmlValue('addAdminError', '');">添加管理员</button>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px; padding-left: 15px;" >
				<div class="has-feedback">
					<button class="btn btn-default" data-toggle="modal" data-target="#showAdmins" ng-click="selectOrgAdmins()">查看管理员</button>
				</div>
			</div>
			<div class="box-tools pull-right" style="margin-bottom: 6px;">
				<div class="has-feedback">
					<div class="row">
						<div class="col-md-9">
							<input class="form-control" ng-model="findAname" placeholder="查询活动..."/>
						</div>
						<div class="col-md-3" style="padding: 0px;">
							<button class="btn btn-default" ng-click="findOrgActivity()">查询</button>
						</div>
					</div>
				</div>
			</div>
			<!--数据列表-->
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
						<th style="text-align: center; width: 185px;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="act in activities">
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
						<td class="text-center">
							<button type="button" class="btn bg-olive btn-xs"
								data-toggle="modal" data-target="#editModal"
								ng-click="deleteActivity(act.aid)">删除</button>
							
							<button type="button" class="btn bg-olive btn-xs"
								data-toggle="modal" data-target="#modifyActivity"
								ng-click="initModify(act);getGroupList();">修改信息</button>
							
							<button type="button" class="btn bg-olive btn-xs"
								data-toggle="modal" data-target="#editModal"
								ng-click="enterActivity(act.aid)">进入活动</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<tm-pagination conf="activityPageConf"></tm-pagination>
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="createGroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						创建组
					</h4>
				</div>
				<div class="modal-body">
					<div class="row" style="margin: 0px;">
						<div class="col-md-3">
							<button type="button" class="btn btn-primary"
								style="width: 100%;">组名</button>
						</div>
						<div class="col-md-9">
							<input type="text" style="font-size: 15px;" ng-model="groupName" placeholder="组名..."
								tabindex="1" class="form-control" ng-click="hideError('createGroup')"/>
						</div>
					</div>
					<h4 class="error" id="createGroupError"></h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" ng-click="createGroup()">
						创建
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="addAdmin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						添加管理员
					</h4>
				</div>
				<div class="modal-body">
					<div class="row" style="margin: 0px;">
						<div class="col-md-3">
							<button type="button" class="btn btn-primary"
								style="width: 100%;">账号</button>
						</div>
						<div class="col-md-9">
							<input type="text" style="font-size: 15px;" ng-model="addAdminUid" placeholder="管理员账号..."
								tabindex="1" class="form-control" ng-click="hideError('addAdmin')"/>
						</div>
					</div>
					<h4 class="error" id="addAdminError"></h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" ng-click="addAdmin();">
						添加
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="showAdmins" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						查看管理员
					</h4>
				</div>
				<div class="modal-body">
					<table id="dataList"
						class="table table-bordered table-striped table-hover dataTable" style="text-align: center;">
						<thead>
							<tr>
								<th style="text-align: center;">学号</th>
								<th style="text-align: center;">姓名</th>
								<th style="text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="oa in orgAdmins">
								<td>{{oa.id}}</td>
								
								<td>
									<element ng-if="oa.name == null">
										未注册
									</element>
									<element ng-if="oa.name != null">
										{{oa.name}}
									</element>
								</td>
								<td class="text-center">
									<button type="button" class="btn bg-olive btn-xs"
										data-toggle="modal" data-target="#editModal"
										ng-click="deleteOrgAdmins(oa.id)">删除</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="showGroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
								<th style="text-align: center;">创建时间</th>
								<th style="text-align: center;">组内人数</th>
								<th style="text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="gd in groupDetail">
								<td>{{gd.gid}}</td>
								<td>{{gd.gname}}</td>
								<td>{{gd.gcreateTime}}</td>
								<td>{{gd.num}}</td>
								<td class="text-center">
									<button type="button" class="btn bg-olive btn-xs"
										data-toggle="modal" data-target="#editModal"
										ng-click="deleteGroup(gd.gid)">删除</button>
								</td>
							</tr>
						</tbody>
					</table>
					<tm-pagination conf="groupDetailConf"></tm-pagination>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="createActivity" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						创建活动
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-hover" style="border: 0px;">
					    <tbody style="text-align: center; font-size: 15px;">
						    <tr>
						        <td style="width: 100px; vertical-align: middle;">活动名称</td>
						        <td><input type="text" class="form-control" style="width: 100%;" id="aname" ng-model="entity.aname"/></td>
						    </tr>
						    <tr>
						        <td style="vertical-align: middle;">负责人</td>
						        <td style="border-radius: 3px;"><input type="text" style="width: 100%;" id="achargeMan" class="form-control" ng-model="entity.achargeMan"/></td>
						    </tr>
						    <tr>
						        <td style="vertical-align: middle;">联系方式</td>
						        <td><input type="text" placeholder="邮箱" style=" border-radius: 3px; width: 100%;" id="acontact" class="form-control" ng-model="entity.acontact"/></td>
						    </tr>
						    <tr>
						        <td style="vertical-align: middle;">开始时间</td>
						        <td>
						        	<input type="datetime-local" class="form-control" style="width: 100%;" id="astartTime" ng-model="entity.astartTime"/>
						        </td>
						    </tr>
						   <tr>
						        <td style="vertical-align: middle;">结束时间</td>
						        <td>
						        	<input type="datetime-local" class="form-control" style="width: 100%;" id="aendTime" ng-model="entity.aendTime"/>
						        </td>
						    </tr>
						    <tr>
						        <td style="vertical-align: middle;">所属组</td>
						        <td>
						       		<select class="selectpicker" data-live-search="true" ng-model="entity.gid" 
						       				data-live-search-placeholder="搜索" data-width="100%" data-size="5" id="groupL">
										<option value="">不选择组</option>
										<option ng-repeat="g in groupList" value="{{g.gid}}">{{g.gname}}</option>
									</select>
						        </td>
						    </tr>
					    </tbody>
					</table>
					<h4 class="error" id="createActivityError"></h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" ng-click="createActivity()">
						创建
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	
	<div class="modal fade" id="modifyActivity" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						修改活动
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-hover" style="border: 0px;">
					    <tbody style="text-align: center; font-size: 15px;">
						    <tr>
						        <td style="width: 100px; vertical-align: middle;">活动名称</td>
						        <td><input type="text" class="form-control" style="width: 100%;" id="aname2" ng-model="entity2.aname"/></td>
						    </tr>
						    <tr>
						        <td style="vertical-align: middle;">负责人</td>
						        <td style="border-radius: 3px;"><input type="text" style="width: 100%;" id="achargeMan2" class="form-control" ng-model="entity2.achargeMan"/></td>
						    </tr>
						    <tr>
						        <td style="vertical-align: middle;">联系方式</td>
						        <td><input type="text" placeholder="邮箱" style=" border-radius: 3px; width: 100%;" id="acontact2" class="form-control" ng-model="entity2.acontact"/></td>
						    </tr>
						    <tr>
						        <td style="vertical-align: middle;">开始时间</td>
						        <td>
						        	<input type="datetime-local" class="form-control" style="width: 100%;" id="astartTime2" ng-model="entity2.astartTime"/>
						        </td>
						    </tr>
						   <tr>
						        <td style="vertical-align: middle;">结束时间</td>
						        <td>
						        	<input type="datetime-local" class="form-control" style="width: 100%;" id="aendTime2" ng-model="entity2.aendTime"/>
						        </td>
						    </tr>
						    <tr>
						        <td style="vertical-align: middle;">所属组</td>
						        <td>
						       		<select class="selectpicker" data-live-search="true" ng-model="entity2.gid" 
						       				data-live-search-placeholder="搜索" data-width="100%" data-size="5" id="groupM">
										<option value="">不选择组</option>
										<option ng-repeat="g in groupList" value="{{g.gid}}">{{g.gname}}</option>
									</select>
						        </td>
						    </tr>
					    </tbody>
					</table>
					<h4 class="error" id="modifyActivityError"></h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" ng-click="modifyActivity()">
						修改
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>


	<!--底部效果-->
</body>
<%@ include file="/WEB-INF/footer.jsp"%>
</html>