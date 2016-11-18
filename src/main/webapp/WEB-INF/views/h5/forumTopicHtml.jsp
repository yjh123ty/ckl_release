<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社区帖子分享</title>
<%@ include file="/WEB-INF/views/commons/head.jsp" %>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp" %>
<style type="text/css">
	.app-titlebar{
		height:60px;
		line-height: 40px;
		padding:10px;
		background-color: #fff;
	}
	.app-titlebar img{
		height: 40px;
	}
	.app-content{
		border-bottom: 1px solid #EEE;
		border-top: 1px solid #EEE;
		padding:10px;
		background-color: #fff;
	}
	.head-icon-container,.barcode-img-container {
		display: table-cell;
		vertical-align: middle;
		padding: 10px 0;
	}
	.user-info-container,.barcode-info{
		display: table-cell;
		vertical-align: middle;
		padding-left: 10px;
	}
	.content-img{
		width: 100px;
		height: 50px;
	}
	.gallery-list li{
		padding-left: 0 !important;
	}
	.topic-time{
		color: gray;
	}
	.app-footer{
		background-color:#fff;
		padding: 20px;
	}
	.barcode-info{
		padding-left: 20px;
	}
	.click-barcode-info{
		font-size: 16px;
		color: black;
	}
	.barcode-title{
		font-size:18px;
		font-weight:bold;
		color: #00b9b7;
	}
	body{
		background:#f0f0f0; 
	}
</style>
</head>
<body>
	<!-- xxxxxxxxxxxx
	xxxxxxxxxxxx
	${trackDetailInfo.trackId}
	{
  "forumTopicId": 6,
  "userId": 92,
  "userName": "Xiaoluo",
  "headIcon": "http://120.76.20.226:8089/upload/user/headIcon/5978925b-4632-4f50-874f-abc477ba9cf2.jpg",
  "time": "10-28 16:27",
  "praiseCount": 0,
  "commentCount": 0,
  "isPraise": false,
  "imgs": [],
  "content": "车刻丽服务站A-\u003e车刻丽服务站A",
  "shareUrl": "http://120.76.20.226:8080/ckl//riders/remote/showForumTopicHtml?forumTopicId\u003d6"
	}
	-->
	<div class="app-titlebar">
		<img alt="logo" src="${ctx}/images/share_logo.png">
		<img alt="logo" src="${ctx}/images/share_logo_text.png" width="150" height="30px" style="float:right; vertical-align: middle;">
	</div>
	<div class="app-content">
		<div class="head-icon-container">
			<img src="${forumTopicDetailInfo.headIcon}" alt="head icon" class="am-circle" width="60px" height="60px">
		</div>
		<div class="user-info-container">
			<div>
				<div class="topic-name">${forumTopicDetailInfo.userName}</div>
				<div class="topic-time">${forumTopicDetailInfo.time}</div>
			</div>
		</div>
		<div>
			${forumTopicDetailInfo.content}
		</div>
	<c:if test="${forumTopicDetailInfo.imgs != null && forumTopicDetailInfo.imgs.size() !=0}">
      <ul class="am-avg-sm-3 am-gallery-bordered gallery-list">
      <c:forEach items="${forumTopicDetailInfo.imgs}" var="img" varStatus="status">
        <li>
	        <div class="am-gallery-item">
	          <a href="javascript:void(0);">
	            <img class="content-img" src="${img}" alt="${status.index}"/>
	          </a>
	        </div>
        </li>
      </c:forEach>
	</c:if>
	</div>
	<div class="app-footer">
		<a href="http://www.baidu.com">
			<div class="barcode-img-container">
				<img src="${ctx}/user/remote/getQRCode?code=12345678&width=90&height=90" alt="head icon" class="am-radius" width="90px" height="90px">
			</div>
			<div class="barcode-info">
				<div>
					<div class="click-barcode-info">扫描二维码进行识别</div>
					<div class="barcode-title">下载车刻丽,一起去旅游！</div>
				</div>
			</div>
		</a>
	</div>
</html>