<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<title>普伴资本加班系统-登录</title>

<link rel="shortcut icon" href="<%=request.getContextPath()%>/view/img/favicon.png" type="image/x-icon">

<link href="<%=request.getContextPath()%>/view/style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/view/style/beyond.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/view/style/animate.min.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/style/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/style/login.css">

<!--让IE认得HTML5-->
<!-–[if lt IE 9]--><script src="<%=request.getContextPath()%>/view/script/html5shiv.js "></script ><!--[endif]–- > 
<!--[if IE]>
<script>
(function(){if(!/*@cc_on!@*/0)return;var e = "header,footer,nav,article,section".split(','),i=e.length;while(i--){document.createElement(e[i])}})()
</script>
<![endif]-->

<!--让IE8-认得@media，上传服务器方可查看效果-->
<script src="<%=request.getContextPath()%>/view/script/Respond-master/dest/respond.src.js"></script>

<script src="<%=request.getContextPath()%>/view/script/skins.min.js"></script>
</head>
<!--Head Ends-->
<!--Body-->
<body class="y_login_bg">
<form name="loginFormName" id="loginFormId" method="post">
    <div class="login-container y_login-container animated fadeInDown">
        <div class="loginbox-title"><span class="y_login_logo"></span></div> 
        <div class="loginbox bg-white">                       
            <div class="loginbox-title">登录</div>
            <div class="loginbox-textbox">
                <input type="text" class="form-control" placeholder="请输入用户名" name="username" />
            </div>
            <div class="loginbox-textbox loginbox-textbox1">
                <input type="password" class="form-control" placeholder="请输入密码" name="password"/>
            </div>
            <div class="loginbox-textbox loginbox_vertCode clearfix hide">
                <input type="text" class="form-control left" placeholder="请输入验证码" /> 
                <div class="y_vertCode left mg_l_10" style="background-color: #eee"></div> 
                <a href="#" class="right" title="看不清？刷新"><i class="fa fa-refresh"></i></a>
            </div>
            <div class="loginbox-forgot clearfix">
                <div class="y_remenberPwd left">
                    <input type="checkbox" name="rememberMe" id="rememberMe">
                    <span class="checkbox_lg_default left"></span>
                    <span class="left">记住我</span>
                </div>
<%--                   <input type="checkbox" name="rememberMe"/>
                   <span class="left">记住我</span>--%>
            </div>
            <div class="loginbox-submit">
                <input type="button" class="btn btn-primary btn-block" value="登录" onclick="login();">
                <input type="hidden" name="loginStatus" value="${result.ok}" id="loginStatus"/>
                <input type="hidden" name="msg" value="${result.msg}" id="msg" />
            </div>            
        </div>
        <%--<div class="logobox y_tips t_c"><i></i><span>请输入正确的用户名或密码</span></div>--%>
        <div class="login_copyright">Copyright ©2015 puban Co.,Ltd<br/>普伴资本管理有限公司 版权所有 </div>
    </div>
</form>
<script src="<%=request.getContextPath()%>/view/script/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/view/script/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/view/script/beyond.min.js"></script>
<script src="<%=request.getContextPath()%>/view/script/placeHolder.js"></script>

<script type="text/javascript">
    //--列表中的复选框、单选框、图像域 --
    $(".checkbox_lg_default").toggle(function(){
        $(this).removeClass("checkbox_lg_default").addClass("checkbox_lg_checked");
    },function(){
        $(this).removeClass("checkbox_lg_checked").addClass("checkbox_lg_default");
    });
    function login(){
        var checkbox = $("#rememberMe").val('input[type=checkbox]:checked');
        $.ajax({
            cache: true,
            type: "POST",
            asnyc:false,
            dataType: "json",
            url: '<%=request.getContextPath()%>/userlogin/check-login',
            data: $('#loginFormId').serialize(),
            success: function (data) {
                if (data.ok == true) {
                    if(data.loginStatus !=0 && data.loginStatus != null){
                        var status = data.loginStatus;
                       /* if (status == 1) {  //超级管理员登陆
                           window.location.href = '<%=request.getContextPath()%>/permission/plist';
                        }*/
                        if (status == 1) {  //管理员登陆
                            window.location.href = '<%=request.getContextPath()%>/apply/admin';
                        }
                        if (status == 2) {  //分配者登陆
                            window.location.href = '<%=request.getContextPath()%>/apply/show';
                        }
                        if(status == 3){ //普通员工登陆
                            window.location.href = '<%=request.getContextPath()%>/apply/show';
                        }
                    }else{
                        window.location.href = '<%=request.getContextPath()%>/userlogin/index';
                    }

                } else {
                    alert(data.msg);
                }
            }
        });
    }
</script>
</body>
</html>
