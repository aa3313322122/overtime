﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<head>
	<meta name="description" content="Dashboard" />
	<meta content="text/html;charset=utf-8;text/x-component" http-equiv="Content-Type">
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
	<meta name="referrer" content="always">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>普伴资本加班系统-用户管理</title>

	<link rel="shortcut icon" href="img/favicon.png" type="image/x-icon">

	<link href="<%=request.getContextPath()%>/view/html/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/view/html/style/beyond.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/view/html/style/animate.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/view/html/style/font-awesome.min.css" rel="stylesheet" type="text/css" />

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/html/style/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/html/style/index.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/html/style/system/index.css">

	<!--让IE认得HTML5-->
	<!-–[if lt IE 9]-->
	<script src="<%=request.getContextPath()%>/view/html/script/html5shiv.js "></script>
	<!--[endif]–- >
<!--[if IE]>
	<script>
		(function(){if(!/*@cc_on!@*/0)return;var e = "header,footer,nav,article,section".split(','),i=e.length;while(i--){document.createElement(e[i])}})()
	</script>
	<![endif]-->

	<!--让IE8-认得@media，上传服务器方可查看效果-->
	<script src="<%=request.getContextPath()%>/view/html/script/Respond-master/dest/respond.src.js"></script>

	<script src="<%=request.getContextPath()%>/view/html/script/skins.min.js"></script>
</head>
<!--Head Ends-->
<!--Body-->
<body class="y_index_bg">
<div class="index_header clearfix ">
	<a href="###" class="left logo"></a>
	<div class="header_right right">
		<span>您好！<%=request.getSession(false).getAttribute("loginName") %></span>
		<a href="<%=request.getContextPath()%>/userlogin/logout" class="exit">退出</a>
	</div>
</div>
<div class="index_content clearfix">

	<div class="col_pageBar left">
		<div class="menu_box">
			<dl class="menu">
				<dt class="menu_title"><i class="icons ico_system"></i>系统管理</dt>
				<dd class="menu_item menu_current"><a href="<%=request.getContextPath()%>/user/list">用户管理</a></dd>
                <dd class="menu_item"><a href="<%=request.getContextPath()%>/role/list">角色管理</a></dd>
                <dd class="menu_item"><a href="<%=request.getContextPath()%>/permission/plist">权限管理</a>
			</dl>
		</div>
	</div>
	<div class="col_pageContent left">
		<div class="main_title pd_030">
			<h2>用户管理</h2>
		</div>
		<div class="main_content pd_030">
			<div class="clearfix">
				<div class="operate_bar clearfix right ">
				</div>
			</div>

			<div class="index_list maintainer_list">
				<div class="content">
					<form class="form-horizontal" action="${action}" id="form">
						<table class="table form-table table-condensed">
							<tr>
								<td class="tdleft" width="6%">*登录名:</td>
								<td class="tdright" width="19%">
									<input type="text" id="fdUserName" name="fdUserName" value="${user.fdUserName}" placeholder="登录名" class="form-control input-sm">
									<input type="hidden" id="fdId" name="fdId" value="${user.fdId}"/>
								</td>
								<td class="tdleft" width="6%">*密码:</td>
								<td class="tdright" width="19%"><input type="text" id="fdPassword" name="fdPassword" value="${user.fdPassword}" placeholder="密码" class="form-control input-sm">
							</tr>
							<tr>
								</td>
								<td class="tdleft" width="6%">邮箱:</td>
								<td class="tdright" width="19%"><input type="text" name="fdEmail" value="${user.fdEmail}" placeholder="邮箱" class="form-control input-sm">
								</td>
								<td class="tdleft" width="6%">手机号:</td>
								<td class="tdright" width="19%"><input type="text" name="fdMobilePhone" value="${user.fdMobilePhone}" placeholder="手机号" class="form-control input-sm">
								</td>

							</tr>
							<tr>
								<td class="tdleft">*选择角色:</td>
								<td class="tdright" colspan="3">
									<c:forEach var="r" items="${roles}" varStatus="s">
										<div class="checkbox">
											<label>
												<input type="checkbox" name="roleId" value="${r.fdId}">
												<span class="text"> ${r.fdRoleName}</span>
											</label>
										</div>
									</c:forEach>
								</td>
							</tr>
						</table>
						<input type="hidden" name="id" value="${user.fdId}" />
					</form>
					<div class="form-btns">
						<button type="button" class="btn btn-primary btn-sm" onclick="sub();">提交</button>
					</div>
				</div>

			</div>

		</div>
	</div>
</div>


<script src="<%=request.getContextPath()%>/view/html/script/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/beyond.min.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/placeHolder.js"></script>
<script type="text/javascript">
	$(function () {
		<c:forEach items="${user.userRoles}" var="k">
		$("input[name='roleId']:checkbox[value='${k.role.fdId}']").prop('checked',
				'true');
		</c:forEach>
	});
	function sub() {
		var checkName = checkEmpty($("#fdUserName").val(), '请填名称');
		var checkKey = checkEmpty($("#fdPassword").val(), '请填写密码');
		if (checkName && checkKey) {
			$.ajax({
				type: "POST",
				data: $("#form").serialize(),
				url: $("#form").attr("action"),
				dataType: 'json',
				success: function (data) {
					if (data.ok == true) {
						alert(data.msg);
						window.location.href = '<%=request.getContextPath()%>/user/list';
					} else {
						alert(data.msg);
					}
				}
			});
		}
	}
	//验证信息
	//验证空信息
	function checkEmpty(obj, msg) {
		var check = false;
		if (obj == "" || obj ==null) {
			alert(msg);
		} else {
			check = true;
		}
		return check;
	}
</script>

</body>
</html>
