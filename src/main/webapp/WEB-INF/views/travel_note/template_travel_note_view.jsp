<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<script type="text/javascript" src="${ctx}/js/travel_note/template_travel_note_edit.js"></script>
<title>游记管理</title>
</head>
<body>
		<div class="am-margin">
			<div class="am-margin">
				<a href="${ctx}/templatetravelnote/index.do"
					role="button" class="am-btn am-btn-success am-btn-sm">返回</a>
			</div>
		</div>
		<section class="am-panel am-panel-default">
			<header class="am-panel-hd">
				<h3 class="am-panel-title">游记基本信息:</h3>
				<h3 class="am-panel-title">标题：${note.title}</h3>
			</header>
			<div class="am-panel-bd">
				<div>
					路线图片：<img src="${img_host}${note.routeImg}" height="200px" width="200px">
				</div>
				<div>
					适合人群：${note.templateTravelNoteSuitNames}
				</div>
				<div>
					游玩天数：${note.dayCount}
				</div>
				<div>
					游玩距离：${note.distance}
				</div>
				<div>
					消费金额：${note.capitaCost}
				</div>
			</div>
		</section>
		<section class="am-panel am-panel-default">
			<header class="am-panel-hd">
				<h3 class="am-panel-title">游记内容:
					<span class="am-margin-left">
						<a href="${ctx}/templatetravelnote/index.do?cmd=saveContent&id=${note.id}&routeId=${routeId}" class="am-icon-plus am-icon-sm" title="添加标题"></a>
					</span>
				</h3>
			</header>
			<c:forEach items="${note.templateTravelNoteContents}" var="item">
				<section class="am-panel am-panel-default">
				<c:if test="${item.type == 1}">
					<header class="am-panel-hd">
						<h4 class="am-panel-title">${item.order}
							<span class="am-margin-left">
								<a href="${ctx}/templatetravelnote/index.do?cmd=editContent&id=${item.id}&routeId=${routeId}" class="am-icon-pencil am-icon-sm" title="修改内容"></a>
								<a onclick="deleteContent('${item.id}');" href="javascript:void(0);" class="am-icon-remove am-icon-sm" title="删除内容"></a>
							</span>
						</h4>
					</header>
					<div class="am-panel-bd">
						${item.content}
					</div>
				</c:if>
				<c:if test="${item.type == 2 }">
					<header class="am-panel-hd">
						<h4 class="am-panel-title">${item.order}
							<span class="am-margin-left">
								<a href="${ctx}/templatetravelnote/index.do?cmd=editContent&id=${item.id}&routeId=${routeId}" class="am-icon-pencil am-icon-sm" title="修改内容"></a>
								<a onclick="deleteContent('${item.id}');" href="javascript:void(0);" class="am-icon-remove am-icon-sm" title="删除内容"></a>
							</span>
						</h4>
					</header>
					<div class="am-panel-bd">
						<img  src="${img_host}${item.content}" alt="图片不存在" />
					</div>
				</c:if>
				</section>			
			</c:forEach>
		</section>
	
	<div class="am-modal am-modal-alert" tabindex="-1" id="msg-modal">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">温馨提示：</div>
			<div id="msg_content" class="am-modal-bd"></div>
			<div class="am-modal-footer">
				<span class="am-modal-btn data-am-modal-confirm">确定</span>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var modal = $("#msg-modal"); 
		function deleteContent(id){
			var url = "${ctx}/templatetravelnote/deleteContent.do";
			$.post(url,{id:id},function(data){
				if(data){
			    	   modal.find("#msg_content").html(data.msg);
			    	   if(data.success){
			    		   location.reload(true);
			    	   }
			    	   modal.modal();
		    	  }else {
		    		  modal.find("#msg_content").html("服务器异常");
		    		  modal.modal();
		    	  }
			}, "json");
		};
	</script>
</body>
</html>