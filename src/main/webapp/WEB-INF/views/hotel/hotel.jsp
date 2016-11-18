<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<title>服务站酒店信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="am-margin">
			<div class="am-margin">
				<a href="${ctx}/servicestation/index.do"
					role="button" class="am-btn am-btn-success am-btn-sm">返回</a>
			</div>
	</div>
	<c:if test="${hotels.size() == 0}">
		<h3 style="text-align: center;">暂无酒店信息</h3>
	</c:if>
	<c:if test="${hotels.size() > 0}">
<c:forEach items="${hotels}" var="hotel">
	<section class="am-panel am-panel-default">
			<header class="am-panel-hd">
				<h3 class="am-panel-title">${hotel.name}</h3>
			</header>
			<div class="am-panel-bd">
				<div>
					封面图片：<img src="${img_host}${hotel.cover}" height="200px" width="200px">
				</div>
				<div>
					外观图片：
					<div class="am-slider am-slider-default am-slider-carousel" style="width: 300px; display: inline-block;">
					  <ul class="am-slides">
					  	<c:forEach items="${hotel.outsideImgs}" var="outsideimg">
							<li><img
								style="height: 200px;width: 200px;"
								src="${img_host}${outsideimg}"></li>
						  </c:forEach>
						</ul>
					</div>
				</div>
				<div>
					大厅图片：
					<div class="am-slider am-slider-default am-slider-carousel" style="width: 300px; display: inline-block;">
					  <ul class="am-slides">
							<c:forEach items="${img_host}${hotel.innerImgs}" var="innerImg">
							<li> <img style="height: 200px; width: 200px;" src="${innerImg}"></li>
						  </c:forEach>
						</ul>
					</div>
				</div>
				<div>
					所在地址：${hotel.fullAddress}
				</div>
				<div>
					联系电话：${hotel.mobile}
				</div>
				<div>
					酒店星级：${hotel.stars}
				</div>
				<div>
					最低消费：${hotel.minimun}
				</div>
				<div>
					服务内容：
					<c:forEach items="${hotel.serviceContents}" var="cc">
						${cc.name}
					</c:forEach>
				</div>
				<div>
					酒店介绍：${hotel.intro}
				</div>
				<c:forEach items="${hotel.hotelRooms}" var="room">
					<section class="am-panel am-panel-default">
						<header class="am-panel-hd">
							<h3 class="am-panel-title">${room.name}</h3>
						</header>
						<div class="am-panel-bd">
							<div>
								封面图片：<img src="${img_host}${room.cover}" height="200px" width="200px">
							</div>
							<div>
								房间图片：
								<div class="am-slider am-slider-default am-slider-carousel" style="width: 300px; display: inline-block;">
								  <ul class="am-slides">
										<c:forEach items="${room.imgs}" var="img">
										<li> <img style="height: 200px; width: 200px;" src="${img_host}${img}"></li>
									  </c:forEach>
									</ul>
								</div>
							</div>
							<div>
								预订价格：${room.price}
							</div>
							<div>
								床型：${room.bedTypeName}
							</div>
							<div>
								大小：${room.size}
							</div>
							<div>
								总数量：${room.totalNum}
							</div>
						</div>
					</section>
				</c:forEach>
			</div>
		</section>
</c:forEach>
</c:if>
<script type="text/javascript">
$('.am-slider').flexslider({itemWidth: 200, itemMargin: 5, slideshow: false});
</script>
</body>
</html>