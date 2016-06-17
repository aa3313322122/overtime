<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/view/html/img/favicon.png" type="image/x-icon">

<link href="<%=request.getContextPath()%>/view/html/style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/view/html/style/beyond.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/view/html/style/animate.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/view/html/style/font-awesome.min.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/html/style/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/html/style/index.css">
<div class="clearfix index_list_title">
 <h2 class="">正常任务列表<span id="totalNormalTaskId" class="my_overtime_time right"></span></h2>        
</div>
<div class="index_list">
<table width="100%">
    <thead>
        <tr>
            <th width="">序号</th>
            <th width="">任务标题</th>
            <th width="">开始时间</th>
            <th width="">结束时间</th>
            <th width="">任务时长</th>
            <th width="">任务内容</th>
            <th width="">责任人</th>
        </tr>
    </thead>
    <tbody >
        <c:forEach var="entity" items="${taskpageView.records}" varStatus="status">
        <tr>
        	<td>${ status.index + 1}</td>
        	<td>${entity.task.fdTaskName}</td>
          <td>${entity.fdNormalStartTime}</td>
          <td>${entity.fdNormalEndTime}</td>
          <td name="normalTaskSum">${entity.fdNormalTaskTime}</td>
          <td>${entity.fdNormalContent}</td>
          <td>${entity.user.fdUserName}</td>
        </tr>
       </c:forEach>
    </tbody>
</table>
</div>
<!-- start 翻页 -->
<div class="DTTTFooter pd_b_15 mg_b_20 clearfix">
<div class="DTTTFooter_left clearfix">
    <div class="dataTables_info left">共${taskpageView.totalrecord}条数据， 每页显示</div>
  <div class="dataTables_length left">
      <label>
      	<select class="form-control input-sm" aria-controls="searchable" onchange="changetaskPage()" id="taskPageSizeId">
      		<option value="5" <c:if test="${taskpageView.maxresult==5}">selected="selected"</c:if> >5 </option>
              <option value="10" <c:if test="${taskpageView.maxresult==10}">selected="selected"</c:if> >10 </option>
              <option value="15" <c:if test="${taskpageView.maxresult==15}">selected="selected"</c:if> >15 </option>
              <option value="30" <c:if test="${taskpageView.maxresult==30}">selected="selected"</c:if> >30 </option>
          </select>
      </label>
  </div>
  <input type="hidden" name="taskcurrentpage"  id="taskcurrentpageId"  value="${taskpageView.currentpage}"/>
    <div class="dataTables_info left">条</div>
</div>
<div class="DTTTFooter_right">
    <div class="dataTables_paginate paging_bootstrap">
        <ul class="pagination">
            <li class="index <c:if test="${taskpageView.currentpage-1<1}">disabled</c:if>">
             	<a href="javascript:goToPageNormal('1');">首页</a>
          </li>
          <li class="prev <c:if test="${taskpageView.currentpage-1<1}">disabled</c:if>">
             	<c:if test="${taskpageView.currentpage-1<1}">
             		<a href="javascript:void(0);">上一页</a>
             	</c:if>
             	<c:if test="${taskpageView.currentpage-1>=1}">
                 	<a href="javascript:goToPageNormal('<c:if test="${taskpageView.currentpage-1<1}">1</c:if><c:if test="${taskpageView.currentpage-1>0}">${taskpageView.currentpage-1}</c:if>');">上一页</a>
             	</c:if>
          </li>
          <c:forEach begin="${taskpageView.pageindex.startindex}"  end="${taskpageView.pageindex.endindex}" step="1" var="taskpageindex">
          	<li <c:if test="${taskpageindex==taskpageView.currentpage}">class='active'</c:if> >
              	<a href="javascript:goToPageNormal('${taskpageindex}');">${taskpageindex}</a>
          	</li> 
	</c:forEach>
          <li class="next <c:if test="${taskpageView.currentpage+1>taskpageView.totalpage}">disabled</c:if>">
              <c:if test="${taskpageView.currentpage+1>taskpageView.totalpage}">
              	<a href="javascript:void(0);">下一页</a>
              </c:if>
              <c:if test="${taskpageView.currentpage+1<=taskpageView.totalpage}">
              	<a href="javascript:goToPageNormal('<c:if test="${taskpageView.currentpage+1>=taskpageView.totalpage}">${taskpageView.totalpage}</c:if><c:if test="${taskpageView.currentpage+1<taskpageView.totalpage}">${taskpageView.currentpage+1}</c:if>');">下一页</a>
         		</c:if>
          </li> 
          <li class="next <c:if test="${taskpageView.currentpage+1>taskpageView.totalpage}">disabled</c:if>">
              <c:if test="${taskpageView.currentpage+1>taskpageView.totalpage}">
              	<a href="javascript:void(0);">尾页</a>
              </c:if>
              <c:if test="${taskpageView.currentpage+1<=taskpageView.totalpage}">
              	<a href="javascript:goToPageNormal('${taskpageView.totalpage}');">尾页</a>
         		</c:if>
                </li>  
            </ul>
        </div>
    </div>
</div>
<!-- end 翻页 -->
<script src="<%=request.getContextPath()%>/view/html/script/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/beyond.min.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/placeHolder.js"></script>