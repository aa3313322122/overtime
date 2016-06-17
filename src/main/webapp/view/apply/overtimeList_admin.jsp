<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/view/html/img/favicon.png" type="image/x-icon">

<link href="<%=request.getContextPath()%>/view/html/style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/view/html/style/beyond.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/view/html/style/animate.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/view/html/style/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/html/style/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/html/style/index.css">
<div class="clearfix index_list_title">
<h2 class="">已申请加班列表<span id="totalOverTimeId" class="my_overtime_time right"></span></h2>        
</div>
<div class="index_list">
<table width="100%">
 <thead>
     <tr>
         <th width="">序号</th>
         <th width="">任务标题</th>
         <th width="">申请人</th>
         <th width="">开始加班时间</th>
         <th width="">结束加班时间</th>
         <th width="">加班时长</th>
         <th width="">加班事由</th>
         <th width="">审批状态</th>
         <th>操作 / 审批意见</th>
     </tr>
 </thead>
 <tbody >
 <c:forEach var="entity" items="${pageView.records}" varStatus="status">
     <tr>
         <td>${ status.index + 1}</td>
         <td>${entity.task.fdTaskName}</td>
         <td>${entity.user.fdUserName}</td>
         <td>${entity.fdStartTime}</td>
         <td>${entity.fdEndTime}</td>
         <td name="ovetTimeSum">${entity.fdOverTime}</td>
         <td>${entity.fdReason}</td>
         <td class="untreated">
         	<c:if test="${entity.fdStatus eq 0}">未审批</c:if>
         	<c:if test="${entity.fdStatus eq 1}">通过</c:if>
         	<c:if test="${entity.fdStatus eq 2}">未通过</c:if>
		</td>
         <td class="untreated">
         	<c:if test="${entity.fdStatus eq 0}">
         	<a class="btn_feedback" id="apply_${entity.fdId}" onclick="showAdvice($(this).attr('id'))">同意</a>
         	<input type="hidden" value="${entity.fdStatus}"/>
         	</c:if>
             <c:if test="${entity.fdStatus ne 0}">
             	<a class="btn_feedback forbidden" id="showA_${entity.fdId}" onclick="showWatch($(this).attr('id'))">查看</a>
         	</c:if>                                        	
         </td>
     </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<!-- start 翻页 -->
<div class="DTTTFooter pd_b_15 mg_b_20 clearfix">
  <div class="DTTTFooter_left clearfix">
      <div class="dataTables_info left">共${pageView.totalrecord}条数据， 每页显示</div>
      <div class="dataTables_length left">
          <label>
          	<select class="form-control input-sm" aria-controls="searchable" onchange="changePage()" id="applyPageSizeId">
          		<option value="5" <c:if test="${pageView.maxresult==5}">selected="selected"</c:if> >5 </option>
                  <option value="10" <c:if test="${pageView.maxresult==10}">selected="selected"</c:if> >10 </option>
                  <option value="15" <c:if test="${pageView.maxresult==15}">selected="selected"</c:if> >15 </option>
                  <option value="30" <c:if test="${pageView.maxresult==30}">selected="selected"</c:if> >30 </option>
              </select>
          </label>
      </div>
      <input type="hidden" name="currentpage"  id="currentpageId"  value="${pageView.currentpage}"/>
      <div class="dataTables_info left">条</div>
  </div>
  <div class="DTTTFooter_right">
      <div class="dataTables_paginate paging_bootstrap">
          <ul class="pagination">
              <li class="index <c:if test="${pageView.currentpage-1<1}">disabled</c:if>">
                 	<a href="javascript:goToPage('1');">首页</a>
              </li>
              <li class="prev <c:if test="${pageView.currentpage-1<1}">disabled</c:if>">
                 	<c:if test="${pageView.currentpage-1<1}">
                 		<a href="javascript:void(0);">上一页</a>
                 	</c:if>
                 	<c:if test="${pageView.currentpage-1>=1}">
                     	<a href="javascript:goToPage('<c:if test="${pageView.currentpage-1<1}">1</c:if><c:if test="${pageView.currentpage-1>0}">${pageView.currentpage-1}</c:if>');">上一页</a>
                 	</c:if>
              </li>
              <c:forEach begin="${pageView.pageindex.startindex}"  end="${pageView.pageindex.endindex}" step="1" var="pageindex">
              	<li <c:if test="${pageindex==pageView.currentpage}">class='active'</c:if> >
                  	<a href="javascript:goToPage('${pageindex}');">${pageindex}</a>
              	</li> 
  			</c:forEach>
              <li class="next <c:if test="${pageView.currentpage+1>pageView.totalpage}">disabled</c:if>">
                  <c:if test="${pageView.currentpage+1>pageView.totalpage}">
                  	<a href="javascript:void(0);">下一页</a>
                  </c:if>
                  <c:if test="${pageView.currentpage+1<=pageView.totalpage}">
                  	<a href="javascript:goToPage('<c:if test="${pageView.currentpage+1>=pageView.totalpage}">${pageView.totalpage}</c:if><c:if test="${pageView.currentpage+1<pageView.totalpage}">${pageView.currentpage+1}</c:if>');">下一页</a>
             		</c:if>
              </li> 
              <li class="next <c:if test="${pageView.currentpage+1>pageView.totalpage}">disabled</c:if>">
                  <c:if test="${pageView.currentpage+1>pageView.totalpage}">
                  	<a href="javascript:void(0);">尾页</a>
                  </c:if>
                  <c:if test="${pageView.currentpage+1<=pageView.totalpage}">
                  	<a href="javascript:goToPage('${pageView.totalpage}');">尾页</a>
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