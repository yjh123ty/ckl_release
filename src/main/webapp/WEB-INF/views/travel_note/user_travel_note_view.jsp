<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					适合人群：${note.suitNames}
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
				<h3 class="am-panel-title">游记内容:</h3>
			</header>
			<c:forEach items="${note.travelNoteContents}" var="item">
				<section class="am-panel am-panel-default">
				<c:if test="${item.type == 1}">
					<header class="am-panel-hd">
						<h4 class="am-panel-title">${item.order}</h4>
					</header>
					<div class="am-panel-bd">
						${item.content}
					</div>
				</c:if>
				<c:if test="${item.type == 2 }">
					<header class="am-panel-hd">
						<h4 class="am-panel-title">${item.order}</h4>
					</header>
					<div class="am-panel-bd">
						<img  src="${img_host}${item.content}" alt="图片不存在" />
					</div>
				</c:if>
				</section>			
			</c:forEach>
		</section>
</body>
</html>