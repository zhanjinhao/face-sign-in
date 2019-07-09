<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";  %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert_Title_Here</title>
		<link rel="Shortcut Icon" href="Insert_Favicon_Here" />
		<!-- jquery -->
		<script src="<%=basePath%>/public/other/js/jquery-3.2.1.min.js"></script>
		
		<!-- bootstrap -->
		<link rel="stylesheet" href="<%=basePath%>/public/bootstrap/css/bootstrap.min.css" />
		<script src="<%=basePath%>/public/bootstrap/js/bootstrap.min.js"></script>
		
		<!-- angularjs -->
		<script src="<%=basePath%>/public/angularjs/angular.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/public/other/service/base_pagination.js"></script>
		<script src="<%=basePath%>/public/other/controller/baseController.js"></script>
		<script src="<%=basePath%>/public/other/controller/errorController.js"></script>
		
		<script src="<%=basePath%>/public/other/js/pagination.js"></script>
		<link rel="stylesheet" href="<%=basePath%>/public/other/css/pagination.css">
		
		<style>
			#child {
			    position: absolute;
			    top: 50%;
			    width: 50%;
			    transform: translate(0, -50%);
			}
			
		</style>
		
	</head>
	<body ng-app="facesignin" ng-controller="errorController" ng-init="init()">
	    <div style="height: 100%; text-align: center;">
	    	<h1>
	    		{{errorInfo}}
	    	</h1>
   		</div>
	</body>
</html>