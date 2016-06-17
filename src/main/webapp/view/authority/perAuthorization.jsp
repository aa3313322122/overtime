<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<title>普伴资本加班系统-权限分配</title>

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

	<script src="<%=request.getContextPath()%>/view/script/jquery.ztree.core-3.5.js"></script>
	<script src="<%=request.getContextPath()%>/view/html/script/skins.min.js"></script>
	<script src="<%=request.getContextPath()%>/view/script/jquery.ztree.exedit-3.5.js"></script>
	<style type="text/css">
		.mytable {
			width: 660px;
			padding: 0;
			margin: 0;
		}

		th {
			font: bold 13px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
			border-right: 1px solid #ddd;
			border-bottom: 1px solid #ddd;
			border-top: 1px solid #ddd;
			letter-spacing: 2px;
			text-transform: uppercase;
			text-align: left;
			padding: 4PX;
		}

		.mytable td {
			border-right: 1px solid #ddd;
			border-bottom: 1px solid #ddd;
			padding: 4px;
		}

		th.specalt {
			border-left: 1px solid #ddd;
			border-top: 1px solid #ddd;
			background: #FFFFFF;
		}

		/*---------for IE 5.x bug*/
		html > body td {
			font-size: 13px;
		}

	</style>
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
				<dd class="menu_item "><a href="<%=request.getContextPath()%>/user/list">用户管理</a></dd>
				<dd class="menu_item "><a href="<%=request.getContextPath()%>/role/list">角色管理</a></dd>
				<dd class="menu_item menu_current"><a href="<%=request.getContextPath()%>/permission/plist">权限管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="col_pageContent left">
		<div class="main_title pd_030">
			<h2>权限配置</h2>
		</div>
		<div class="main_content pd_030">
			<div class="container-panel">
				<div class="content">
					<form method="post" id="from" name="form">
						<table class="mytable">
							<tr>
								<th class="specalt">一级菜单</th>
								<th class="specalt"><span>二级菜单</span><span style="float: right;margin-right: 150px;">按扭</span></th>
							</tr>
							<c:forEach items="${permissions}" var="k">
								<tr>
									<th class="specalt">
										<div class="checkbox">
											<label>
												<input type="checkbox" name="resId" id="menu" _key="menu_${k.id}" onclick="smenu(this,'${k.id}')" value="${k.id}">
												<span class="text">${k.name}</span>
											</label>
										</div>

									</th>
									<th class="specalt">
										<table class="mytable" style="width: 100%;height: 100%;">
											<c:forEach items="${k.children}" var="kc">
												<tr>
													<th class="specalt">

														<div class="checkbox">
															<label>
																<input type="checkbox" name="resId" id="menu"
																	   _key="menu_1_${k.id}" _key_1="menu_1_1_${kc.id}"
																	   onclick="menu_1(this,'${kc.id}','${k.id}')"
																	   value="${kc.id}">
																<span class="text">${kc.name}</span>
															</label>
														</div>
													</th>
													<th class="specalt">
														<table class="mytable" style="width: 100%;height: 100%;">
															<c:forEach items="${kc.children}" var="kcc">
																<tr>
																	<th class="specalt">

																		<div class="checkbox">
																			<label>
																				<input type="checkbox" name="resId"
																					   id="menu"
																					   _key="menu_1_1_${k.id}"
																					   _key_2="menu_1_1_${kc.id}"
																					   onclick="menu_1_1(this,'${kc.id}','${k.id}')"
																					   value="${kcc.id}">
																				<span class="text"> ${kcc.name}</span>
																			</label>
																		</div>
																	</th>
																</tr>
															</c:forEach>
														</table>
													</th>
												</tr>
											</c:forEach>
										</table>
									</th>
								</tr>
							</c:forEach>
						</table>
						<input id='roleId' name="roleId" type="hidden" value="${param.roleId}">
					</form>
					<div class="form-btns">
						<button type="button" class="btn btn-primary btn-sm" onclick="sub();">保存</button>
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
	function smenu(obj, id) {
		$("input[_key='menu_1_" + id + "']").each(function () {
			$(this).prop("checked", obj.checked);
		});
		$("input[_key='menu_1_1_" + id + "']").each(function () {
			$(this).prop("checked", obj.checked);
		});
	}
	;
	function menu_1(obj, id, pid) {
		$("input[_key_2='menu_1_1_" + id + "']").each(function () {
			$(this).prop("checked", obj.checked);
		});
		if (obj.checked == true) {
			$("input[_key='menu_" + pid + "']").each(function () {
				$(this).prop("checked", obj.checked);
			});
		}
	}
	;
	function menu_1_1(obj, id, pid) {
		if (obj.checked == true) {
			$("input[_key_1='menu_1_1_" + id + "']").each(function () {
				$(this).prop("checked", obj.checked);
			});
			$("input[_key='menu_" + pid + "']").each(function () {
				$(this).prop("checked", obj.checked);
			});
		}
	}
	function sub() {
		$.ajax({
			async: false,
			type: "POST",
			data: $("#from").serialize(),
			url: '<%=request.getContextPath()%>/permission/add_role_per',
			dataType: 'json',
			success: function (data) {
				if (data.ok == true) {
					alert(data.msg);
					window.location.href = '<%=request.getContextPath()%>/role/list';
				} else {
					alert(data.msg);
				}
			}
		});
	}
	$(function () {
		var roleId = ${role.fdId};
		$.ajax({
			async: false,
			type: "POST",
			data: {roleId: roleId},
			url: '<%=request.getContextPath()%>/permission/get_role_per',
			dataType: 'json',
			success: function (data) {
				for (var i = 0; i < data.length; i++) {
					$("input[name='resId']:checkbox[value='" + data[i].fdId + "']").prop('checked',
							'true');
				}
			}
		});
	});
</script>

</body>
</html>
