<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找旅伴帖子分享</title>
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
	.topic-travel-title{
		font-size: 16px;
		font-weight: bold;
	}
	.topic-travel-time{
		margin:10px 0;
		color: #01b9b7;
		font-weight:bold;
		font-size:14px;
	}
	.topic-route-name{
		float: right;
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
  "companionTopicId": 14,
  "title": "组队了，组队了",
  "content": "人生就像一场旅行，在乎的不是目的地，而是沿途的风景，还有陪我看风景的你",
  "userName": "Xiaoluo",
  "time": "10-27 19:43",
  "commentCount": 0,
  "startTime": "2016-10-27",
  "endTime": "2016-10-28",
  "routeId": 177,
  "routeName": "丽江行",
  "shareUrl": "http://120.76.20.226:8080/ckl//riders/remote/showCompanionTopicHtml?companionTopicId\u003d14",
  "imgs": [
    "http://120.76.20.226:8089/upload/topic/e0a07c1b-45c5-424f-b0af-4a802e7d2634.jpg",
    "http://120.76.20.226:8089/upload/topic/a997a23e-e4bc-4474-bf36-c577809309fa.jpg"
  ],
  "companionTopicCommentInfos": []
}
	-->
	<div class="app-titlebar">
		<img alt="logo" src="${ctx}/images/share_logo.png">
		<img alt="logo" src="${ctx}/images/share_logo_text.png" width="150" height="30px" style="float:right; vertical-align: middle;">
	</div>
	<div class="app-content">
		<div class="head-icon-container">
			<img src="${companionTopicDetailInfo.headIcon}" alt="head icon" class="am-circle" width="60px" height="60px">
		</div>
		<div class="user-info-container">
			<div>
				<div class="topic-name">${companionTopicDetailInfo.userName}</div>
				<div class="topic-time">${companionTopicDetailInfo.time}</div>
			</div>
		</div>
		<div class="topic-travel-title">
			${companionTopicDetailInfo.title}
		</div>
		<div class="topic-travel-time">
			${companionTopicDetailInfo.startTime}&emsp;至&emsp;${companionTopicDetailInfo.endTime}
			<span class="topic-route-name">
			${companionTopicDetailInfo.routeName}
			</span>
		</div>
		<div>
			${companionTopicDetailInfo.content}
		</div>
	<c:if test="${companionTopicDetailInfo.imgs != null && companionTopicDetailInfo.imgs.size() !=0}">
      <ul class="am-avg-sm-3 am-gallery-bordered gallery-list">
      <c:forEach items="${companionTopicDetailInfo.imgs}" var="img" varStatus="status">
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