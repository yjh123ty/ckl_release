<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<title>酒店房间数量管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<!-- 酒店工具条 -->
	<div class="am-g am-margin-left" style="padding: 8px;">
		<form id="hotel-room-search-form" class="am-form" action="${ctx}/hotelroomrecord/list.do" method="post">
			<input type="hidden" value="${query.hotelId}" name="hotelId">
			<input type="hidden" value="1" name="currentPage">
			<input type="hidden" value="" name="recordId">
			<div class="am-g am-margin-top">
				<div class=" am-sm-1 am-u-md-1 am-u-lg-1"
					style="text-align: center;">时&emsp;间：</div>
				<div class="am-input-group am-datepicker-date am-sm-3 am-u-md-3 am-u-lg-3" data-am-datepicker="{format: 'yyyy-mm-dd'}">
					<fmt:formatDate value="${query.startDate}" pattern="yyyy-MM-dd" var="startDate"/>
				  <input type="text" name="startDate" class="am-form-field" placeholder="请输入开始时间" value="${startDate}" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
				</div>
				<div class=" am-sm-1 am-u-md-1 am-u-lg-1"
					style="text-align: center;">到</div>
				<div class="am-input-group am-datepicker-date am-sm-3 am-u-md-3 am-u-lg-3" data-am-datepicker="{format: 'yyyy-mm-dd'}">
					<fmt:formatDate value="${query.endDate}" pattern="yyyy-MM-dd" var="endDate"/>
				  <input type="text" name="endDate" class="am-form-field" placeholder="请输入结束时间" value="${endDate}" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
				</div>
				<div class="am-sm-4  am-u-md-4 am-u-lg-4 am-u-end">
					<button 
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="submit">确&nbsp;定</button>
						
					<button 
							class="am-btn am-btn-success am-btn-xs"
							style="border-radius: 300px;" type="button" onclick="queryClear();">清&nbsp;空</button>
							
					<a href="${ctx}/hotel/index.do"
							class="am-btn am-btn-success am-btn-xs"
							style="border-radius: 300px;">返&nbsp;回</a>
				</div>
			</div>
		</form>
	</div>
	<!-- 酒店表格 -->
	<hr />
	<table
		class="am-table am-table-bordered am-table-striped am-table-hover am-table-centered">
		<thead>
			<tr>
				<th>酒店名称</th>
				<th>时间</th>
				<c:forEach items="${page.rows[0].hotelRooms}" var="room">
					<th>${room.name}</th>
				</c:forEach>
				<th>总剩余数量</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.rows}" var="row">
				<tr>
					<td>${row.hotelName}</td>
					<fmt:formatDate value="${row.bookDate}" var="bookDate" pattern="yyyy年MM月dd日"/>
					<td>${bookDate}</td>
					<c:forEach items="${row.hotelRooms}" var="room">
						<th>
							<button type="button" class="am-btn am-btn-default" onclick="addRoomNum(${room.recordId}, ${query.currentPage}, this);"><span class="am-icon-minus"></span></button>
								 <span class="num">${room.remainNum}</span> 
							<button type="button" class="am-btn am-btn-default" onclick="removeRoomNum(${room.recordId}, ${query.currentPage}, this);"><span class="am-icon-plus"></span></button>
						</th>
					</c:forEach>
					<td>${row.totalRemainNum}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="am-cf">
     	 共 ${page.total}条记录
       <div class="am-fr am-margin-left am-margin-right">
         <ul class="am-pagination">
         <c:forEach begin="1" end="${page.totalPage}" var="i">
         	<c:choose>
         		<c:when test="${i eq 1}">
					<c:choose>
						<c:when test="${page.currentPage eq 1}">
							<li class="am-disabled"><a href="#">&laquo;</a></li>
							<li class="am-disabled"><a href="#">1</a></li>
						</c:when>
						<c:otherwise>
							<li class="am-active"><a href="javascript:go(1);">&laquo;</a></li>
							<li class="am-active"><a href="javascript:go(1);">1</a></li>
						</c:otherwise>
					</c:choose>
				</c:when>
				
				<c:when test="${i eq page.totalPage}">
					<c:choose>
						<c:when test="${page.currentPage eq page.totalPage}">
							<li class="am-disabled"><a href="#">${i}</a></li>
							<li class="am-disabled"><a href="#">&raquo;</a></li>
						</c:when>
						<c:otherwise>
							<li class="am-active"><a href="javascript:go(${i});">${i}</a></li>
							<li class="am-active"><a href="javascript:go(${i});">&raquo;</a></li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${i eq page.currentPage}">
			           	 	<li class="am-disabled"><a href="#">${i}</a></li>
				         </c:when>
				         <c:otherwise>
				           	 <li class="am-active"><a href="javascript:go(${i});">${i}</a></li>
				         </c:otherwise>
			         </c:choose>
		        </c:otherwise>
			</c:choose>
         </c:forEach>
         </ul>
       </div>
   </div>
   <script type="text/javascript">
 	var form = $("#hotel-room-search-form");
 	var currentPageInput = form.find("[name=currentPage]");
   	function go(currentPage){
   		currentPageInput.val(currentPage);
   		form.submit();
   	};
   	
   	function queryClear(){
   		form.find("input[type='text']").val('');
   	};
   	
	function addRoomNum(recordId, currentPage, btn){
		var context = $(btn);
   		$.post(_ctx+"/hotelroomrecord/addRoomNum.do", {recordId:recordId},function(data){
   	   			if(data) {
   	   				if(data.success){
   	   			   		currentPageInput.val(currentPage);
   	   			   		var num = context.closest("th").find(".num");
   	   			   		num.text(parseInt(num.text())-1);
   	   				}
   	   			}
   		},"json");
   	};
   	
   	function removeRoomNum(recordId, currentPage, btn){
		var context = $(btn);
		$.post(_ctx+"/hotelroomrecord/removeRoomNum.do",{
   			"recordId": recordId
   		},function(data){
   			if(data) {
   				if(data.success){
   					currentPageInput.val(currentPage);
   			   		var num = context.closest("th").find(".num");
   			   		num.text(parseInt(num.text())+1);
   				}else {
   					
   				}
   			}else {
   				
   			}
   		},"json");
   	};
	
   </script>
</body>
</html>