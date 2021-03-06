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
                            <h2 class="">分配任务列表<span id="totalGiveTaskId" class="my_overtime_time right"></span></h2>        
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
              <th width="">加班时间</th>
              <th width="">正常任务时间</th>
              <th width="">任务内容</th>
               <th width="">责任人</th> 
          </tr>
      </thead>
      <tbody>
          <c:forEach var="entity" items="${taskGivePageView.records}" varStatus="status">
          <tr>
          <td>${ status.index + 1}</td>
          <td>${entity.fdTaskName}</td>
          <td>${entity.fdStartTime}</td>
          <td>${entity.fdEndTime}</td>
          <td name="giveTaskSum"><fmt:formatNumber type="number" value="${entity.fdTaskTime /60/60/1000}" maxFractionDigits="1" /></td>
          <td>${ovetTimes[status.index]}</td>
          <td>${normalTimes[status.index]}</td>
          <td>${entity.fdTaskContent}</td>
          <td>${entity.user.fdUserName}</td>
          </tr>
         </c:forEach>
      </tbody>
  </table>
  </div>
   <!-- start 翻页 -->
                <div class="DTTTFooter pd_b_15 mg_b_20 clearfix">
                <div class="DTTTFooter_left clearfix">
                    <div class="dataTables_info left">共${taskGivePageView.totalrecord}条数据， 每页显示</div>
                    <div class="dataTables_length left">
                        <label>
                        	<select class="form-control input-sm" aria-controls="searchable" onchange="changegivePage()" id="givePageSizeId">
                        		<option value="5" <c:if test="${taskGivePageView.maxresult==5}">selected="selected"</c:if> >5 </option>
                                <option value="10" <c:if test="${taskGivePageView.maxresult==10}">selected="selected"</c:if> >10 </option>
                                <option value="15" <c:if test="${taskGivePageView.maxresult==15}">selected="selected"</c:if> >15 </option>
                                <option value="30" <c:if test="${taskGivePageView.maxresult==30}">selected="selected"</c:if> >30 </option>
                            </select>
                        </label>
                    </div>
                    <input type="hidden" name="taskGivecurrentpage"  id="taskGivecurrentpageId"  value="${taskGivePageView.currentpage}"/>
                    <div class="dataTables_info left">条</div>
                </div>
                <div class="DTTTFooter_right">
                    <div class="dataTables_paginate paging_bootstrap">
                        <ul class="pagination">
                            <li class="index <c:if test="${taskGivePageView.currentpage-1<1}">disabled</c:if>">
                               	<a href="javascript:goToPageGive('1');">首页</a>
                            </li>
                            <li class="prev <c:if test="${taskGivePageView.currentpage-1<1}">disabled</c:if>">
                               	<c:if test="${taskGivePageView.currentpage-1<1}">
                               		<a href="javascript:void(0);">上一页</a>
                               	</c:if>
                               	<c:if test="${taskGivePageView.currentpage-1>=1}">
                                   	<a href="javascript:goToPageGive('<c:if test="${taskGivePageView.currentpage-1<1}">1</c:if><c:if test="${taskGivePageView.currentpage-1>0}">${taskGivePageView.currentpage-1}</c:if>');">上一页</a>
                               	</c:if>
                            </li>
                            <c:forEach begin="${taskGivePageView.pageindex.startindex}"  end="${taskGivePageView.pageindex.endindex}" step="1" var="taskGivepageindex">
                            	<li <c:if test="${taskpageindex==taskGivePageView.currentpage}">class='active'</c:if> >
                                	<a href="javascript:goToPageGive('${taskGivepageindex}');">${taskGivepageindex}</a>
                            	</li> 
                			</c:forEach>
                            <li class="next <c:if test="${taskGivePageView.currentpage+1>taskGivePageView.totalpage}">disabled</c:if>">
                                <c:if test="${taskGivePageView.currentpage+1>taskGivePageView.totalpage}">
                                	<a href="javascript:void(0);">下一页</a>
                                </c:if>
                                <c:if test="${taskGivePageView.currentpage+1<=taskGivePageView.totalpage}">
                                	<a href="javascript:goToPageGive('<c:if test="${taskGivePageView.currentpage+1>=taskGivePageView.totalpage}">${taskGivePageView.totalpage}</c:if><c:if test="${taskGivePageView.currentpage+1<taskGivePageView.totalpage}">${taskGivePageView.currentpage+1}</c:if>');">下一页</a>
                           		</c:if>
                            </li> 
                            <li class="next <c:if test="${taskGivePageView.currentpage+1>taskGivePageView.totalpage}">disabled</c:if>">
                                <c:if test="${taskGivePageView.currentpage+1>taskGivePageView.totalpage}">
                                	<a href="javascript:void(0);">尾页</a>
                                </c:if>
                                <c:if test="${taskGivePageView.currentpage+1<=taskGivePageView.totalpage}">
                                	<a href="javascript:goToPageGive('${taskGivePageView.totalpage}');">尾页</a>
                           		</c:if>
                            </li>  
                        </ul>
                    </div>
                </div>
            </div>
            </div>
<!-- end 翻页 -->
<script src="<%=request.getContextPath()%>/view/html/script/jquery-1.8.2.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/beyond.min.js"></script>
<script src="<%=request.getContextPath()%>/view/html/script/placeHolder.js"></script>