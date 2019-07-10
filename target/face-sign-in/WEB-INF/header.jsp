<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function confirmSignOff(){
		var msg = "您真的注销吗？  注销后会删除您在本系统的所有信息，请三思！";
		if (confirm(msg) == true){
			window.location.href="${pageContext.request.contextPath}/user/signOffAccount";
		}
		else
			return false;
	}
</script>
<div class="sign-up-top">
	<div class="container" style="margin-top: 10px;">
		<div style="vertical-align:middle; font-size:28px;">
			<a href="${pageContext.request.contextPath}/" style="text-decoration: none;">
				<img style="border-radius: 50%;" src="holder.js/60x60" style="vertical-align:middle;"/>
				<span style="letter-spacing: 2px;">人脸识别签到系统</span>
			</a>
			<div style="float: right; margin-top: 10px;">
				<h4>
					<c:if test="${empty sessionScope.user}">
						<a href="${pageContext.request.contextPath}/user/loginui">登录</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/user/registerui">注册</a>
					</c:if>
				</h4>
			</div>
			<div style="float: right; margin-top: 0px;padding:7px;">
				<h4 style="margin-top: 0px;padding:0px;">
					<c:if test="${!empty sessionScope.user}">
						<ul class="nav nav-tabs" style="margin-top:0px;padding-top:0px;">
						    <li class="dropdown" style="margin-top:0px;padding-top:0px;">
						        <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="margin-top:0px;">${sessionScope.user.name}
						            <span class="caret"></span></a>
						        <ul class="dropdown-menu" style="width:0px;min-width:99%;">
						        <c:if test="${sessionScope.user.id ne \"jhzhan_16\" }">
						      		<li>
						                <a href="${pageContext.request.contextPath}/user/changePwdUI">修改密码</a></li>
						        </c:if>
						        <c:if test="${sessionScope.user.level == 1 }">
						      		<li>
						                <a onclick="confirmSignOff();">账号注销</a></li>
					                <li>
						                <a href="${pageContext.request.contextPath}/user/student.action">我的主页</a></li>
						        </c:if>
						         <c:if test="${sessionScope.user.level == 2 }">
						      		<li>
						                <a href="${pageContext.request.contextPath}/user/classAdmin.action">我的主页</a></li>
						        </c:if>
						         <c:if test="${sessionScope.user.level == 3 }">
						      		<li>
						                <a href="${pageContext.request.contextPath}/user/collegeAdmin.action">我的主页</a></li>
						        </c:if>
						            <li>
						                <a href="${pageContext.request.contextPath}/user/quit">退出系统</a></li>
						        </ul>
						    </li>
						</ul>
					</c:if>
				</h4>
			</div>
		</div>
	</div>
</div>