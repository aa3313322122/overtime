<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<title>普伴资本加班系统-申请</title>

<link rel="shortcut icon" href="<%=request.getContextPath()%>/view/html/img/favicon.png" type="image/x-icon">

<link href="<%=request.getContextPath()%>/view/html/style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/view/html/style/beyond.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/view/html/style/animate.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/view/html/style/font-awesome.min.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/html/style/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/html/style/index.css">

<!--让IE认得HTML5-->
<!-–[if lt IE 9]--><script src="<%=request.getContextPath()%>/view/html/script/html5shiv.js "></script ><!--[endif]–- > 
<!--[if IE]>
<script>
(function(){if(!/*@cc_on!@*/0)return;var e = "header,footer,nav,article,section".split(','),i=e.length;while(i--){document.createElement(e[i])}})()
</script>
<![endif]-->

<!--让IE8-认得@media，上传服务器方可查看效果-->
<script src="<%=request.getContextPath()%>/view/html/script/Respond-master/dest/respond.src.js"></script>

<script src="<%=request.getContextPath()%>/view/html/script/skins.min.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/DatePicker/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var showChoice = 0;
/*  显示增加表单  */
function showApply()
{
	$('#applyUl').removeClass('hide');
	$('#taskUl').addClass('hide');
	$('#taskUl2').addClass('hide');
}
function showTaskApply()
{
	$('#applyUl').addClass('hide');
	$('#taskUl').addClass('hide');
	$('#taskUl2').removeClass('hide');
}
function showTaskGive()
{
	$('#applyUl').addClass('hide');
	$('#taskUl').removeClass('hide');
	$('#taskUl2').addClass('hide');
}
/*  显示增加表单  */

/*  切换Tab  */
function showTask()
{
	$('#selectTaskId').removeClass('hide');
	$('#selectTaskId').prev().removeClass('hide');
	showChoice = 1;
	queryTask(1);
}
function showNormalTask()
{
	$('#selectTaskId').removeClass('hide');
	$('#selectTaskId').prev().removeClass('hide');
	showChoice = 2;
	queryTask(1);
}
/*  切换Tab  */

/* 弹出层返回选项ID到主界面    */
function setTaskId()
{
	var checkedRadio = $('#showTasklistId input[type="radio"]:checked ').attr('id');
	var taskid = checkedRadio.substring(6,checkedRadio.length);
	if(showChoice == 1)
	{
		$('#taskfdId').val(taskid);
		var checkedName = $('#showTasklistId input[type="radio"]:checked ').next().html();
		$('#taskName').val(checkedName);
	}
	else if(showChoice == 2)
	{
		$('#normalTaskId').val(taskid);
		var checkedName = $('#showTasklistId input[type="radio"]:checked ').next().html();
		$('#normalTaskName').val(checkedName);
	}
	hideTask();
}
/* 弹出层返回选项ID到主界面    */
 
 
/*  显示更新弹出层 */
function showUpdate(applyId)
{
	var applyid = applyId.substring(6,applyId.length);
	findApply(applyid);
	$('#updateApplyId').removeClass('hide');
}
function showNormalUpdate(normalId)
{
	var normalid = normalId.substring(7,normalId.length);
	findNormal(normalid);
	$('#updateNormalId').removeClass('hide');
}
function showGiveUpdate(giveId)
{
	var giveid = giveId.substring(5,giveId.length);
	$('#updateGiveId').removeClass('hide');
	findGive(giveid);
}
/*  显示更新弹出层 */

/*  隐藏更新弹出层 */
function hideTask()
{
	$('#selectTaskId').addClass('hide');
	$('#selectTaskId').prev().addClass('hide');
	showChoice = 0;
}
function hideUpdate()
{
	$('#updateApplyId').addClass('hide');	
}
function hideNormalUpdate()
{
	$('#updateNormalId').addClass('hide');
}
function hideGiveUpdate()
{
	$('#updateGiveId').addClass('hide');
}
/*  隐藏更新弹出层 */
function minusTime(startTimeId, endTimeId, inputId)
{
	var endTime = $('#' + endTimeId).val();
	var t = Date.parse(endTime);
	var startTime = $('#' + startTimeId).val();
	var s = Date.parse(startTime);
	var h = (t - s)/60/60/1000;
	if(h>8)
	{
		if(h/24<=1.0)
		{
			h = 8;
		}
		else
		{
			var s = parseInt(h/24);
			var d = s*8;
			var f = h-s*24;
			if(f<8)
			{
				h = d+f;
			}else
			{
				h = d+8;
			}
		}
	}
	var m = parseFloat(h);
	if(m > 0)
	{
		$('#' + inputId).html(m.toFixed(1));
	}
}
function sumConlumn(tdName, totalId)
{
	var total = 0;
	var sum = document.getElementsByName(tdName);
	for(i = 0; i < sum.length; i++) {
		var td = sum[i].innerText;
		if(td > 0)
		{
			total += parseFloat(td);
		}
	}
	total = total.toFixed(1);
	$('#' + totalId).html("总时长：<strong>" + total + "</strong>");
}
/** 茫茫多的AJAX   **/
function setSelectedUser(selectId)
{
	var url = "<%=request.getContextPath()%>/user/userinfo";
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		dataType:"json",
		success:function (userList)
		{
			for(var i=0;i<userList.length;i++)
			{
				var tmp = "<option value='" + userList[i].fdId + "'>" + userList[i].fdUserName + "</option>";
				$('#' + selectId).append(tmp);
			}
		}
	});
}
function queryTask(userId)
{
	var url = "<%=request.getContextPath()%>/task/listbyuser";
	var data = "userId=" + userId;
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		data:data,
		dataType: "json",
		scriptCharset: 'utf-8',
		success:function (data)
		{
			var divData = "";
			for(var i=0;i<data.length;i++)
			{
				divData += "<div class=\"radio\">" +"<label>" + 
	                "<input type=\"radio\" id=\"radio_" + data[i].fdId +"\" name=\"form-field-radio\">" +
	                "<span class=\"text\">" + data[i].fdTaskName + "</span>" + "</label>" +"</div>";
			}
			$('#showTasklistId').html(divData);
		}
	});
}
function findApply(applyid)
{
	var url = "<%=request.getContextPath()%>/apply/findapply";
	var data = "applyId=" + applyid;
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		data:data,
		dataType: "json",
		success:function (overtime)
		{
			$('#updateApplyFdId').val(overtime.fdId);
			$('#findStartTimeId').val(overtime.fdStartTime);
			$('#findEndTimeId').val(overtime.fdEndTime);
			$('#findOverTimeId').html(overtime.fdOverTime);
			$('#findReasonId').val(overtime.fdReason);
		}
	});
}
function findNormal(normalid)
{
	var url = "<%=request.getContextPath()%>/task/findnormal";
	var data = "taskId=" + normalid;
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType:"json",
		success:function (normal)
		{
			$('#updateNormalFdId').val(normal.fdId);
			$('#findNormalStartTimeId').val(normal.fdNormalStartTime);
			$('#findNormalEndTimeId').val(normal.fdNormalEndTime);
			$('#findNormalALLTimeId').html(normal.fdNormalTaskTime);
			$('#findfdNormalTaskTime').val(normal.fdNormalTaskTime);
			$('#findNormalTaskContent').val(normal.fdNormalContent);
		}
	});
}
function findGive(giveid)
{
	var url = "<%=request.getContextPath()%>/task/findtask";
	var data = "taskId=" + giveid;
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		data:data,
		dataType: "json",
		success:function (give)
		{
			$('#updateGiveFdId').val(give.fdId);
			$('#findGiveStartTimeId').val(give.fdStartTime);
			$('#findGiveEndTimeId').val(give.fdEndTime);
			var time = give.fdTaskTime/60/60/1000;
			$('#findGiveAllTimeId').html(time.toFixed(1));
			$('#findfdGiveTaskTime').val(give.fdTaskTime);
			$('#findGiveTaskContent').val(give.fdTaskContent);
			$('#secondSelect').val(give.user.fdId);
		}
	});
}
function updateApply()
{
	var url = "<%=request.getContextPath()%>/apply/update";
	var data = $("#updateApplyFormId").serialize();
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType: "text",
		success:function (result)
		{
			if(result == 'success')
			{
				alert(result);
				showOverTimeList();
			}
			else
				alert('更新失败');
		}
	});
	hideUpdate();
}
function updateNormal()
{
	var url = "<%=request.getContextPath()%>/task/updatenormal";
	var data = $("#updateNormalFormId").serialize();
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType: "text",
		success:function (result)
		{
			if(result == 'success')
			{
				alert(result);
				showNormalTaskList();
			}
			else
				alert('更新失败');
		}
	});
	hideNormalUpdate();
}
function updateGive()
{
	var url = "<%=request.getContextPath()%>/task/updategive";
	var data = $("#updateGiveFormId").serialize();
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType: "text",
		success:function (result)
		{
			if(result == 'success')
			{
				alert(result);
				showGiveTaskList();
			}
			else
				alert('更新失败');
		}
	});
	hideGiveUpdate();
}
function addApply()
{
	var url = "<%=request.getContextPath()%>/apply/add";
	var data = $("#addApplyId").serialize();
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType: "text",
		success:function (result)
		{
			if(result == 'success')
			{
				alert('加班申请添加成功');
				document.getElementById("addApplyId").reset();
				$('#a1').click();
			}
			else
			{
				alert('加班申请添加失败，问问啥情况？');
			}
		}
	});
}
function addNormalTask()
{
	var url = "<%=request.getContextPath()%>/task/addnormal";
	var data = $("#normaladd").serialize();
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType:"text",
		success:function (result)
		{
			if(result == 'success')
			{
				alert('正常任务添加成功');
				document.getElementById("normaladd").reset();
				$('#a2').click();
			}
			else
			{
				alert('正常任务添加失败，问问啥情况？');
			}
		}
	});
}
function addGiveTask()
{
	var url = "<%=request.getContextPath()%>/task/add";
	var data = $("#giveadd").serialize();
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType: "text",
		success:function (result)
		{
			if(result == 'success')
			{
				alert('分配任务添加成功');
				document.getElementById("giveadd").reset();
				$('#a3').click();
			}
			else
			{
				alert('分配任务添加失败，问问啥情况？');
			}
		}
	});
}
function showOverTimeList()
{
	var url = "<%=request.getContextPath()%>/apply/list";
	var data = $("#formId").serialize();
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType: "html",
		success:function (result)
		{
			$('#info-tab1').html(result);
			sumConlumn('ovetTimeSum', 'totalOverTimeId');
		}
	});
}
function showGiveTaskList()
{
	var url = "<%=request.getContextPath()%>/task/listgive";
	var data = $("#giveFormListId").serialize();
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType: "html",
		success:function (result)
		{
			$('#info-tab3').html(result);
			sumConlumn('giveTaskSum', 'totalGiveTaskId');
		}
	});
}
function showNormalTaskList()
{
	var url = "<%=request.getContextPath()%>/task/listnormal";
	var data = $("#normalFormId").serialize();
	$.ajax({
		url:url,
		type:"POST",
		cache: false,
		asnyc:false,
		data:data,
		dataType: "html",
		success:function (result)
		{
			$('#info-tab2').html(result);
			sumConlumn('normalTaskSum', 'totalNormalTaskId');
		}
	});
}
function goToPage(pageNo)
{
	var form=document.getElementById("formId");
	form.currentpage.value=pageNo;
	showOverTimeList();
}
function changePage(){

	var pageSizes=document.getElementById("applyPageSizeId").value;
	document.getElementById("selectPageSizeId").value=pageSizes;
	document.getElementById("applyPageSizeId").value=pageSizes;
	goToPage(1);
}
function goToPageNormal(pageNo)
{
	var form=document.getElementById("normalFormId");
	form.taskcurrentpage.value=pageNo;
	showNormalTaskList();
}
function changetaskPage(){

	var pageSizes=document.getElementById("taskPageSizeId").value;
	document.getElementById("taskselectPageSizeId").value=pageSizes;
	document.getElementById("taskPageSizeId").value=pageSizes;
	goToPageNormal(1);
}
function goToPageGive(pageNo)
{
	var form=document.getElementById("giveFormListId");
	form.taskGivecurrentpage.value=pageNo;
	showGiveTaskList();
}
function changegivePage(){

	var pageSizes=document.getElementById("givePageSizeId").value;
	document.getElementById("selecttaskGivePageSizeId").value=pageSizes;
	document.getElementById("givePageSizeId").value=pageSizes;
	goToPageGive(1);
}
</script>
</head>
<!--Head Ends-->
<!--Body-->
<body onload="showOverTimeList(),setSelectedUser('firstSelect'),setSelectedUser('secondSelect')" class="y_index_bg">
<div class="index_header clearfix ">
    <a href="###" class="left logo"></a>
    <div class="header_right right">
        <span>您好！<%=request.getSession(false).getAttribute("loginName") %></span>
        <a href="<%=request.getContextPath()%>/userlogin/logout" class="exit">退出</a>
    </div>
</div>
<div class="write_apply">
    <div class="write_apply_controlWidth clearfix">
        <div class="write_title left">           
            <h2>请选择任务类型：</h2>
            <div class="clearfix">
                <div class="radio left">
                    <label>
                        <input type="radio" onclick="showTaskApply()" name="form-field-radio">
                        <span class="text">填写正常任务 </span>
                    </label>
                </div>
                <div class="radio left">
                    <label>
                        <input type="radio" onclick="showApply()" checked="checked" name="form-field-radio">
                        <span class="text">填写加班申请</span>
                    </label>
                </div>
				<shiro:hasRole name="dest">
					<div class="radio left">
						<label>
							<input type="radio" name="form-field-radio" onclick="showTaskGive()">
							<span class="text">任务分配 </span>
						</label>
					</div>
				</shiro:hasRole>
            </div>
        </div>
        
        <div class="left write_content">
            <form id="addApplyId" name="" method="post" action="<%=request.getContextPath()%>/apply/add">
            <!-- 加班申请 -->
            <ul id="applyUl" class="">
             	<li class="clearfix">
                    <div class="left works_title">
                    	<span class="text">填写加班申请 </span>
                        <span class="apply_name left">任务标题：</span>
                        <input type="text" id="taskName" name="task.fdTaskName" class="ovt_input left" onclick="showTask()">
                    	<input type="hidden" id="taskfdId" name="task.fdId" />
                    </div>
                    
                </li>
                <li class="overtime_proposer">
                    <span class="apply_name">申请人：</span>
                    <span class="proposer"><input type="text" id="fdUserName" name="user.fdUserName" value="<%=request.getSession(false).getAttribute("loginName") %>"/></span>
                </li>
                <li class="clearfix">
                    <div class="clearfix left overtime_startTime ">
                        <span class="apply_name left">加班时间段：</span>
                        <input type="text" class="Wdate" id="fdStartTime" name="fdStartTime"
                         onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})" value=""/>
                        <input type="text" class="Wdate" id="fdEndTime" name="fdEndTime"
                         onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'}), minusTime('fdStartTime', 'fdEndTime', 'overTimeTId')" value=""/>
                        时长：<span id="overTimeTId" class="duration"></span>
                    </div>
                </li>
                <li class="clearfix overtime_reason_li">
                    <span class="apply_name left">加班事由：</span>
                    <textarea class="overtime_reason left" id="fdReason" name="fdReason"></textarea>
                </li>
                <li class="">
                    <input type="button" onclick="addApply()" class="btn submit_btn" id="sub1" value="提 交" />
                </li>
            </ul>
            </form>
            <!-- end 加班申请 -->

            <!-- 任务分配 -->
            <ul id="taskUl" class="hide">
            <form id="giveadd" method="post" action="<%=request.getContextPath()%>/task/add">
                <li class="clearfix">
                    <div class="left works_title">
                   		<span class="text">填写任务 分配</span>
                        <span class="apply_name left">任务标题：</span>
                        <input type="text" name="fdTaskName" class="ovt_input left">
                    </div>
                    <div class="left works_principal">
                        <span class="apply_name">责任人：</span>
                        <select id="firstSelect" name="user.fdId">
                            <option>请选择负责人</option>
                        </select>
                    </div>
                </li>
                <li class="clearfix">
                    <div class="clearfix left overtime_startTime ">
                        <span class="apply_name left">任务时间段：</span>
                        <input type="text" class="ovt_input Wdate" id="fdGiveStartId" name="fdStartTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"/>
                        <input type="text" class="ovt_input Wdate" id="fdGiveEndId" name="fdEndTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'}), minusTime('fdGiveStartId', 'fdGiveEndId', 'fdGiveTId')"/>
                        时长：<span id="fdGiveTId" class="duration"></span>
                    </div>
                    
                </li>
                <li class="clearfix overtime_reason_li">
                    <span class="apply_name left">任务内容：</span>
                    <textarea class="overtime_reason left" name="fdTaskContent"></textarea>
                </li>
                <li class="">
                    <input type="button" onclick="addGiveTask()" class="btn submit_btn" value="提 交" />
                </li>
                </form>
            </ul>
            <!-- end 任务分配 -->

            <!-- 正常任务 -->
            <ul id="taskUl2" class="hide">
            <form id="normaladd" method="post" action="<%=request.getContextPath()%>/task/update">
                <li class="clearfix">
                    <div class="left works_title">
                    <span class="text">填写正常任务 </span>
                        <span class="apply_name left">任务标题：</span>
                        <input type="text" id="normalTaskName" name="task.fdTaskName" onclick="showNormalTask()" class="ovt_input left">
                        <input type="hidden" id="normalTaskId" name="task.fdId" />
                    </div>
                </li>
                <li class="clearfix">
                    <div class="clearfix left overtime_startTime ">
                        <span class="apply_name left">任务时间段：</span>
                        <input type="text" class="ovt_input Wdate" id="fdNormalStartId" name="fdNormalStartTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        <input type="text" class="ovt_input Wdate" id="fdNormalEndId" name="fdNormalEndTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'}), minusTime('fdNormalStartId', 'fdNormalEndId', 'fdNormalTId')"/>
                       时长： <span id="fdNormalTId" class="duration"></span>
                    </div>
                    
                </li>
                <li class="clearfix overtime_reason_li">
                    <span class="apply_name left">任务内容：</span>
                    <textarea class="overtime_reason left" name="fdNormalContent"></textarea>
                </li>
                <li class="">
                    <input type="button" onclick="addNormalTask()" class="btn submit_btn" value="提 交" />
                </li>
                </form>
            </ul>
            <!-- end 正常任务 -->
            
        </div>
    </div>
</div>

<div class="index_content">
    <div class="index_search clearfix hide">
        <input type="search" placeholder="请输入关键词" class="left sch_input_search">
        <input type="button" class="left sch_input_btn" value="搜 索">
    </div>  
    <div class="widget-header">
        <span class="widget-caption">任务列表信息</span>
        <a class="right export blue" href="#"><i class="fa fa-reply"> 导出表格</i></a>
    </div>  
    <div class="widget-body ">
        <div class="docs-example mg_t_20">
            <ul class="nav nav-tabs tabs-flat">
                <li class="active"><a id="a1" onclick="showOverTimeList()" href="#info-tab1" data-toggle="tab">已申请加班列表</a></li>
                <li class=""><a id="a2" onclick="showNormalTaskList()" href="#info-tab2" data-toggle="tab">正常任务列表</a></li>
                <li class=""><a id="a3" onclick="showGiveTaskList()" href="#info-tab3" data-toggle="tab">分配任务列表</a></li>
            </ul>
            <form id="formId" method="post" action="<%=request.getContextPath()%>/apply/list">
            <input type="hidden" name="currentpage"  id="currentpageId"  value=""/>
            <input type="hidden" name="selectPageSize"  id="selectPageSizeId"  value=""/>
            </form>
            <form id="normalFormId" method="post" action="<%=request.getContextPath()%>/task/listnormal">
            <input type="hidden" name="taskcurrentpage"  id="taskcurrentpageId"  value=""/>
            <input type="hidden" name="taskselectPageSize"  id="taskselectPageSizeId"  value=""/>
            </form>
            <form id="giveFormListId" method="post" action="<%=request.getContextPath()%>/task/listgive">
            <input type="hidden" name="taskGivecurrentpage"  id="taskGivecurrentpageId"  value=""/>
            <input type="hidden" name="selecttaskGivePageSize"  id="selecttaskGivePageSizeId"  value=""/>
            </form>
                <div class="tab-content tabs-flat">
                    <!-- 已申请加班列表 -->
                    <div class="clearfix tab-pane active" id="info-tab1">
                    </div>
                    <!-- 正常任务列表 -->
                    <div class="clearfix  tab-pane" id="info-tab2">
                    </div> 
                    <!-- 分配任务列表 -->
	               <div class="clearfix  tab-pane" id="info-tab3">
	               </div>
                    <!-- end 分配任务列表 -->
              </div>
        </div> 
    </div>   
</div>

<!-- 弹出层 - 全部任务 -->
<div class="bg_cover hide"></div>
<div id="selectTaskId" class="pop_content allmy_task hide" >
    <div class="title clearfix">
        <h3 class="left">全部任务</h3>
        <a onclick="hideTask()" class="right pop_close"></a>
    </div>
    <div class="pop_contents clearfix" id="showTasklistId">
    </div>
    <input type="button" class="index_btn_submit" onclick="setTaskId()" value="确 定" />
</div>
<!-- end 弹出层 - 全部任务 -->

<!-- 弹出层 - 修改 -->
<div class="bg_cover hide"></div>
<div id="updateApplyId" class="pop_content role_pop1 editOvertime_pop1 hide">
<form id="updateApplyFormId" action="" method="post">
    <div class="title clearfix">
        <h3 class="left">修改申请信息</h3>
        <a onclick="hideUpdate()" class="right pop_close"></a>
    </div>
    <div class="pop_contents clearfix">
    <input type="hidden" id="updateApplyFdId" name="fdId"/>
	    <div class="clearfix ">
	        <span class="span_block"> 开始时间：</span>
	        <input type="text" id="findStartTimeId" name="fdStartTime" class="ovt_input Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"/>
	    </div>
	    <div class="clearfix mg_t_10">
	        <span class="span_block"> 结束时间：</span>
	        <input type="text" id="findEndTimeId" name="fdEndTime" class="ovt_input Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})
	        , minusTime('findStartTimeId', 'findEndTimeId', 'findOverTimeId')"/>
	    </div>  
	    <div class="clearfix mg_t_10">        
	        <span class="span_block"> 加班时长：</span>
	        <span class="span_block_time" id="findOverTimeId"></span>
	    </div> 
	    <div class="clearfix mg_t_10">
	        <span class="span_block"> 加班事由：</span>
	        <textarea placeholder="1、加班事由;2、加班事由" id="findReasonId" name="fdReason"></textarea>            
	    </div>
    </div>
    <input type="button" class="index_btn_submit" onclick="updateApply()" value="保存" />
    </form>
</div>

<div class="bg_cover hide"></div>
<div id="updateNormalId" class="pop_content role_pop1 editOvertime_pop1 hide">
<form id="updateNormalFormId" action="" method="post">
    <div class="title clearfix">
        <h3 class="left">修改正常任务信息</h3>
        <a onclick="hideNormalUpdate()" class="right pop_close"></a>
    </div>
    <div class="pop_contents clearfix">
    <input type="hidden" id="updateNormalFdId" name="fdId"/>
	    <div class="clearfix ">
	        <span class="span_block"> 开始时间：</span>
	        <input type="text" id="findNormalStartTimeId" name="fdNormalStartTime" class="ovt_input Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"/>
	    </div>
	    <div class="clearfix mg_t_10">
	        <span class="span_block"> 结束时间：</span>
	        <input type="text" id="findNormalEndTimeId" name="fdNormalEndTime" class="ovt_input Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})
	        ,minusTime('findNormalStartTimeId', 'findNormalEndTimeId', 'findNormalALLTimeId')"/>
	    </div>  
	    <div class="clearfix mg_t_10">        
	        <span class="span_block"> 任务时长：</span>
	        <span class="span_block_time" id="findNormalALLTimeId">
	        </span>
	        <input type="hidden" id="findfdNormalTaskTime" name="fdNormalTaskTime" />
	    </div> 
	    <div class="clearfix mg_t_10">
	        <span class="span_block"> 任务内容：</span>
	        <textarea id="findNormalTaskContent" name="fdNormalContent"></textarea>            
	    </div>
    </div>
    <input type="button" class="index_btn_submit" onclick="updateNormal()" value="保存" />
    </form>
</div>

<div class="bg_cover hide"></div>
<div id="updateGiveId" class="pop_content role_pop1 editOvertime_pop1 hide">
<form id="updateGiveFormId" action="" method="post">
    <div class="title clearfix">
        <h3 class="left">修改分配任务信息</h3>
        <a onclick="hideGiveUpdate()" class="right pop_close"></a>
    </div>
    <div class="pop_contents clearfix">
    <input type="hidden" id="updateGiveFdId" name="fdId"/>
    	<div class="left works_principal">
            <span class="apply_name">责任人：</span>
            <select id="secondSelect" name="user.fdId">
                <option>请选择负责人</option>
            </select>
        </div>
	    <div class="clearfix ">
	        <span class="span_block"> 开始时间：</span>
	        <input type="text" id="findGiveStartTimeId" name="fdStartTime" class="ovt_input Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"/>
	    </div>
	    <div class="clearfix mg_t_10">
	        <span class="span_block"> 结束时间：</span>
	        <input type="text" id="findGiveEndTimeId" name="fdEndTime" class="ovt_input Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})
	        ,minusTime('findGiveStartTimeId', 'findGiveEndTimeId', 'findGiveAllTimeId')"/>
	    </div>  
	    <div class="clearfix mg_t_10">
	        <span class="span_block"> 任务时长：</span>
	        <span class="span_block_time" id="findGiveAllTimeId"></span>
	        <input type="hidden" id="findfdGiveTaskTime" name="fdTaskTime" />
	    </div> 
	    <div class="clearfix mg_t_10">        
	        <span class="span_block"> 任务内容：</span>
	        <textarea id="findGiveTaskContent" name="fdTaskContent"></textarea>            
	    </div>
    </div>
    <input type="button" class="index_btn_submit" onclick="updateGive()" value="保存" />
    </form>
</div>
<!-- end 弹出层 - 修改 -->


<script src="<%=request.getContextPath()%>/view/html/script/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/beyond.min.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/placeHolder.js"></script>

<script type="text/javascript">
    //--列表中的复选框、单选框、图像域 --
    /* $(".checkbox_lg_default").toggle(function(){
        $(this).removeClass("checkbox_lg_default").addClass("checkbox_lg_checked");
    },function(){
        $(this).removeClass("checkbox_lg_checked").addClass("checkbox_lg_default");
    }); */
</script>
</body>
</html>

