<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp" %>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<title>任务中心</title>
</style>
</head>
<body>
	<table class="am-table am-table-bordered am-table-centered">
		<c:forEach items="${tasks}" var="task">
			<tr>
				<td>${task.content}</td>
				<fmt:formatDate value="${task.createTime}" pattern="yyyy-MM-dd" timeZone="GMT+8" var="createTime"/>
				<td>${createTime}</td>
				<td><a href="javascript:void(0);" class="task-detail" data-url="${ctx}${task.url}">查看详情</a></td>
				<td><a class="complete-task" href="javascript:void(0)" data-taskid="${task.id}">完成</a></td>
			</tr>
		</c:forEach>
	</table>
	
<div class="am-modal am-modal-alert" tabindex="-1" id="msg-modal">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">温馨提示：</div>
	    <div id="msg_content" class="am-modal-bd">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn data-am-modal-confirm">确定</span>
	    </div>
	  </div>
	</div>
<script type="text/javascript">
$(function(){
	var msgModal = $("#msg-modal");
	var taskCount = $("#task-count", window.parent.document);
	$(".task-detail").click(function(){
		console.debug(this);
		var url = $(this).data('url');
		if(url){
			window.parent.openTreeItem(url);
		} else {
			msgShow("温馨提示", "暂无详情", "info");
		};
	});
	$(".complete-task").click(function(){
		var taskId = $(this).data("taskid");
		if(taskId) {
			$.post(_ctx + "/adminTask/handleTask.do",{taskId:taskId},function(data){
				if(data){
					if(data.success) {
						msgModal.on('close.modal.amui',function(){
							var text = taskCount.text();
							if(text){
								taskCount.text(parseInt(text)-1);
								location.reload();
							}
						});
					}
					msgModal.find(".am-modal-bd").html(data.msg);
					msgModal.modal();
				}else {
					msgModal.find(".am-modal-bd").html("服务器异常");
					msgModal.modal();
				}
			},'json');
		};
	});
});
</script>
</body>
</html>