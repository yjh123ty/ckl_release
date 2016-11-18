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
	<%--
		1. 后台根据路线的信息自动生成游记的基本信息：
			 游玩天数、适合人群、消费水平、路线的距离、路线图片
		2. 可以添加路线游记的内容：
			type 1. 文字
			type 2. 图片
			根据图片和文字的顺序order来进行展示
	 --%>
 	<div class="am-form am-form-horizontal am-margin-top">
 		<div class="am-form-group">
 			<label class="am-form-label am-u-sm-3 am-u-md-2 am-u-lg-1">
 				所属路线：
 			</label>
			<c:if test="${note == null || note.id == null}">
				<div class="am-u-sm-2">
					<select id="route-select">
						<c:forEach items="${routes}" var="item">
							<c:choose>
								<c:when test="${routeId != null && routeId == item.id}">
									<option value="${item.id}" selected="selected">${item.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${item.id}">${item.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <span class="am-form-caret"></span>
				</div>
				<c:if test="${routes.size() ==0}">
					<a  href="${ctx}/route/index.do"
									class="am-btn am-btn-success am-btn-xs"
									style="border-radius: 300px;">添加路线</a>
				</c:if>
				<c:if test="${routes.size() !=0}">
					<div class="am-u-sm-2 am-u-end">
						<button id="btn-create"
									class="am-btn am-btn-success am-btn-xs"
									style="border-radius: 300px;">生成模板游记基本信息</button>
					</div>
				</c:if>
			</c:if>
			
			<c:if test="${note.id != null}">
				<div class="am-u-sm-2 am-u-end">
					<select id="route-select" autocomplete="off">
						<c:forEach items="${routes}" var="item">
							<option value="${item.id}" 
								<c:if test="${note.routeId == item.id}">
									selected="selected"
								</c:if>
							>${item.name}
							</option>
						</c:forEach>
					</select> <span class="am-form-caret"></span>
				</div>
			</c:if>
		</div>
	</div>

	<c:if test="${note.id != null}">
		<section class="am-panel am-panel-default">
			<header class="am-panel-hd">
				<h3 class="am-panel-title">游记基本信息:</h3>
				<h3 class="am-panel-title">标题：${note.title}</h3>
			</header>
			<div class="am-panel-bd">
				<div>
					路线图片：<img src="${img_host}/${note.routeImg}" height="200px" width="200px">
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
	</c:if>
	
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