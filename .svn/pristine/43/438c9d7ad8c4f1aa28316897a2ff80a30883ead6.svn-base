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
	<title>普伴资本加班系统-角色管理</title>

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
				<dd class="menu_item "><a href="<%=request.getContextPath()%>/user/list">用户管理</a></dd>
                <dd class="menu_item menu_current"><a href="<%=request.getContextPath()%>/role/list">角色管理</a></dd>
                <dd class="menu_item"><a href="<%=request.getContextPath()%>/permission/plist">权限管理</a>
			</dl>
		</div>
	</div>
	<div class="col_pageContent left">
		<div class="main_title pd_030">
			<h2>角色管理</h2>
		</div>
		<div class="main_content pd_030">
	<div class="container-panel">
	   <div class="content">
	   		 <form class="form-horizontal" action="${action}" id="form" style="width: 400px;">
	   		 	<div class="form-group">
				    <label class="col-sm-4 control-label">*名称:</label>
				    <div class="col-sm-8">
			            <input type="text" name="fdRoleName" value="${role.fdRoleName}" placeholder="名称" class="form-control input-sm" id="fdRoleName">
			            <input type="hidden" name="fdId" value="${role.fdId}" id="fdId">
				    </div>
				  </div>
				  	<div class="form-group">
				    <label class="col-sm-4 control-label">*key:</label>
				    <div class="col-sm-8">
			            <input type="text" name="fdRoleKey" value="${role.fdRoleKey}" placeholder="key" class="form-control input-sm" id="fdRoleKey">
				    </div>
				  </div>
					<div class="form-group">
					    <div class="col-sm-offset-4 col-sm-8">
					      <input type="hidden" name="id" value="${role.fdId}" />
					      <button type="button" class="btn btn-primary btn-sm" onclick="sub();">提交</button>
					    </div>
				  </div>
          </form>
	</div>
</div>
</div>
		<script src="<%=request.getContextPath()%>/view/html/script/jquery-1.8.2.js"></script>
		<script src="<%=request.getContextPath()%>/view/html/script/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/view/html/script/beyond.min.js"></script>
		<script src="<%=request.getContextPath()%>/view/html/script/placeHolder.js"></script>
	<script type="text/javascript">
	function sub(){
		var checkName = checkEmpty($("#fdRoleName").val(),'请填名称');
		var checkKey = checkEmpty($("#fdRoleKey").val(),'请填写key');
		if(checkName && checkKey){
			$.ajax({
				type : "POST",
				data : $("#form").serialize(),
				url : $("#form").attr("action"),
				dataType : 'json',
				success:function(data) {
					if (data.ok == true) {
						alert(data.msg);
						window.location.href = '<%=request.getContextPath()%>/role/list';
					} else {
						alert(data.msg);
					}
				}
			});
		}
	}
	//验证信息
	//验证空信息
	function checkEmpty(obj,msg){
		var check = false;
		if(obj == ""){
			alert(meg);
		}else{
			check = true;
		}
		return check;
	}
	</script>
</body>
</html>
