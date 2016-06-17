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

<link rel="shortcut icon" href="../img/favicon.png" type="image/x-icon">

<link href="style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="style/beyond.min.css" rel="stylesheet" type="text/css" />
<link href="style/animate.min.css" rel="stylesheet" type="text/css"/>
<link href="style/font-awesome.min.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" type="text/css" href="style/common.css">
<link rel="stylesheet" type="text/css" href="style/index.css">
<link rel="stylesheet" type="text/css" href="style/system/index.css">

<!--让IE认得HTML5-->
<!-–[if lt IE 9]--><script src="script/html5shiv.js "></script ><!--[endif]–- > 
<!--[if IE]>
<script>
(function(){if(!/*@cc_on!@*/0)return;var e = "header,footer,nav,article,section".split(','),i=e.length;while(i--){document.createElement(e[i])}})()
</script>
<![endif]-->

<!--让IE8-认得@media，上传服务器方可查看效果-->
<script src="script/Respond-master/dest/respond.src.js"></script>

<script src="script/skins.min.js"></script>
<script src="script/DatePicker/WdatePicker.js"></script>
</head>
<!--Head Ends-->
<!--Body-->
<body class="y_index_bg">
<div class="index_header clearfix ">
    <a href="###" class="left logo"></a>
    <div class="header_right right">
        <span>您好！尹文萍</span>
        <a href="###" class="exit">退出</a>
    </div>
</div>
<div class="index_content clearfix">
    
    <div class="col_pageBar left">
        <div class="menu_box">            
            <dl class="menu">
                <dt class="menu_title"><i class="icons ico_system"></i>系统管理</dt>
                <dd class="menu_item "><a href="#">维护人员管理</a></dd>
                <dd class="menu_item menu_current"><a href="#">角色管理</a></dd>
                <dd class="menu_item"><a href="#">权限管理</a></dd>
            </dl>
        </div>
    </div>
    <div class="col_pageContent left">
        <div class="main_title pd_030">
            <h2>角色管理</h2>
        </div>
        <div class="main_content pd_030">
            <div class="clearfix">
                <form action="<%=request.getContextPath()%>/role/roleList" name="roleForm" id="roleForm" method="post">
                    <div class="clearfix">
                        <div class="index_search clearfix left">
                            <input type="search" placeholder="请输入用户名" class="left sch_input_search" name="roleName">
                            <input type="hidden" name="currentPage" id="currentPageId"
                                   value="${pageView.currentpage}" />
                            <input type="hidden" name="pageSize" id="pageSizeId" value="${pageSize}" />
                        </div>
                        <input type="submit" class="left sch_input_btn mg_l_10" value="查  询">
                    </div>
                </form>
                <div class="operate_bar clearfix right ">
                    <a class="btns btn_new" href="#">新建角色</a>
                </div>
            </div>

            <div class="index_list maintainer_list">
                <table width="100%">
                    <thead>
                        <tr>
                            <th class="pl_1" width="3%">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" checked="checked">
                                        <span class="text"></span>
                                    </label>
                                </div>
                            </th>
                            <th>序号</th>
                            <th>名称</th>
                            <th class="t_l">角色说明</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="role" varStatus="status" items="${pageView.records}">
                        <tr>
                            <td>${status.index}</td>
                            <td>${role.fdRoleName}</td>
                            <td>${role.fdRoleKey}</td>
                            <td>
                                <a class="btns reset_pwd btn_sml" href="#" onclick="delUser(${role.fdId})">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <div class="DTTTFooter">
                <div class="DTTTFooter_left clearfix">
                    <div class="dataTables_info left">共${pageView.totalrecord}条数据</div>
                </div>
                <div class="DTTTFooter_right">
                    <div class="dataTables_paginate paging_bootstrap">
                        <ul class="pagination">
                            <li class="index">
                                <a href="javascript:goToPage('1');">首页</a>
                            </li>
                            <li class="prev">
                                <a href="javascript:goToPage('<c:if test="${pageView.currentpage-1<1}">1</c:if><c:if test="${pageView.currentpage-1>0}">${pageView.currentpage-1}</c:if>');">上一页</a>
                            </li>
                            <c:forEach var="i" begin="1" end="${pageView.totalpage}">
                                <li
                                        <c:if test="${pageView.currentpage==i}">class="active"</c:if> >
                                    <a href="javascript:goToPage('${i}')">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="next">
                                <a href="javascript:goToPage('<c:if test="${pageView.currentpage+1>=pageView.totalpage}">${pageView.totalpage}</c:if><c:if test="${pageView.currentpage+1<pageView.totalpage}">${pageView.currentpage+1}</c:if>');">下一页</a>
                            </li>
                            <li class="next">
                                <a href="javascript:goToPage('${pageView.totalpage}');">尾页</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- end 翻页 -->
        </div>
    </div>
</div>

<!-- 弹出层 -->
<div class="bg_cover "></div>
<div class="pop_content role_pop1 hide">
    <div class="title clearfix">
        <h3 class="left">修改角色信息</h3>
        <a href="javascript(0);" class="right pop_close"></a>
    </div>
    <form action="<%=request.getContextPath()%>/role/updateRole" method="post" id="updateRoleForm">
    <div class="pop_contents clearfix">        
        <div class="clearfix ">        
            <span class="span_block"> 名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span>
            <input placeholder="管理员" type="text" name="fdRoleName">
        </div>
        <%--<div class="clearfix mg_t_10">
            <span class="span_block"> 角色权限：</span>
            <select>
                <option>管理员权限</option>
                <option>评分权限</option>
            </select>
        </div>--%>
        <div class="clearfix mg_t_10">        
            <span class="span_block"> 角色说明：</span>
            <textarea placeholder="" name="fdRoleKey"></textarea>
        </div>
    </div>
    <input type="submit" class="index_btn_submit" value="确 定" />
    </form>
</div>

<div class="pop_content role_pop1 ">
    <div class="title clearfix">
        <h3 class="left">新增角色信息</h3>
        <a href="javascript(0);" class="right pop_close"></a>
    </div>
    <form action="<%=request.getContextPath()%>/role/addRole" method="post" id="addUserForm">
    <div class="pop_contents clearfix">        
        <div class="clearfix ">        
            <span class="span_block"> 名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span>
            <input placeholder="管理员" type="text">
        </div>
      <%--  <div class="clearfix mg_t_10">
            <span class="span_block"> 是否有效：</span>
            <div class="radio left">
                <label>
                    <input type="radio" checked="checked" name="form-field-radio">
                    <span class="text">是 </span>
                </label>
            </div>
            <div class="radio left">
                <label>
                    <input type="radio" checked="checked" name="form-field-radio">
                    <span class="text">否 </span>
                </label>
            </div>
        </div>        
        <div class="clearfix mg_t_10">        
            <span class="span_block"> 角色权限：</span>
            <select>
                <option>管理员权限</option>
                <option>评分权限</option>
            </select>
        </div>--%>
        <div class="clearfix mg_t_10">        
            <span class="span_block"> 角色说明：</span>
            <textarea placeholder="系统管理员，主要维护系统，以及相关人员信息的维护等" name="fdRoleKey"></textarea>
        </div>
    </div>
    <input type="submit" class="index_btn_submit" value="确 定" />
    </form>
</div>
<!-- end 弹出层 -->

<script src="script/jquery-1.8.2.js"></script>
<script src="script/bootstrap.min.js"></script>
<script src="script/beyond.min.js"></script>
<script src="script/placeHolder.js"></script>

<script type="text/javascript">
    function goToPage(pageNo) {
        var form = document.getElementById("roleForm");
        form.currentPage.value = pageNo;
        document.getElementById("roleForm").submit();
    }
</script>

</body>
</html>
