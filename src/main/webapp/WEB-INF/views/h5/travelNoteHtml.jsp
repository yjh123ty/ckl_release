<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游记分享</title>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<style type="text/css">
.app-titlebar {
	height: 60px;
	line-height: 40px;
	padding: 10px;
	background-color: #fff;
}

.app-titlebar img {
	height: 40px;
}

.app-content {
	border-bottom: 1px solid #EEE;
	border-top: 1px solid #EEE;
	padding: 10px;
	background-color: white;
	color: gray;
}

.app-footer {
	background-color: #fff;
	padding: 20px;
}

.barcode-info {
	padding-left: 20px;
}

.click-barcode-info {
	font-size: 16px;
	color: black;
}

.barcode-title {
	font-size: 18px;
	font-weight: bold;
	color: #00b9b7;
}

.barcode-img-container {
	display: table-cell;
	vertical-align: middle;
	padding: 10px 0;
}

body {
	overflow-y: scroll;
	background: #f0f0f0;
}
</style>
</head>
<body>
	<div class="app-titlebar">
		<img alt="logo" src="${ctx}/images/share_logo.png"> <img
			alt="logo" src="${ctx}/images/share_logo_text.png" width="150"
			height="30px" style="float: right; vertical-align: middle;">
	</div>

	<div class="app-content">
		<div style="color: #000; font-size: 26px;">${travelNoteDetailInfo.title}</div>
		<div style="display: inline;">
			<div>${travelNoteDetailInfo.time}${travelNoteDetailInfo.userName}</div>
			<div></div>
		</div>
		<hr>
		<i class="am-icon-clock-o  am-icon-fw"></i> &nbsp;
		出发时间：${travelNoteDetailInfo.departTime}<br /> <i
			class="am-icon-calendar  am-icon-fw"></i> &nbsp;
		游玩天数：${travelNoteDetailInfo.dayCount}天<br /> <i
			class="am-icon-jpy  am-icon-fw"></i> &nbsp;
		人均消费：${travelNoteDetailInfo.capitaCost}元<br /> <i
			class="am-icon-user  am-icon-fw"></i> &nbsp; 适合人群：
		<c:forEach items="${travelNoteDetailInfo.suitNames}" var="suitName">
			${suitName} &nbsp;
		</c:forEach>
		<br />

		<c:forEach items="${travelNoteDetailInfo.travelNoteContentInfos}"
			var="travelNoteContentInfo">
			<c:if test="${travelNoteContentInfo.type == 1}">
				${travelNoteContentInfo.content} <br />
			</c:if>
			<c:if test="${travelNoteContentInfo.type == 2}">
				<img class="travelNoteImgs am-img-thumbnail"
					src="${travelNoteContentInfo.content}" />
				<br />
			</c:if>
		</c:forEach>
	</div>

	<div class="app-footer">
		<div class="barcode-img-container">
			<img
				src="${ctx}/user/remote/getQRCode?code=12345678&width=90&height=90"
				class="am-radius" width="90px" height="90px">
		</div>

		<div class="barcode-info">
			<div>
				<div class="click-barcode-info">扫描二维码进行识别</div>
				<div class="barcode-title">下载车刻丽,一起去旅游！</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		
	</script>
</body>
</html>