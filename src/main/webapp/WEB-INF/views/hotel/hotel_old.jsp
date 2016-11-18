<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:forEach items="${hotels}" var="hotel">
		<div class="am-g am-margin-top">
			<div class="am-u-sm-3">酒店外观图：</div>
			<div
			  class="am-slider am-slider-default am-slider-carousel  am-u-sm-3 am-u-end">
			  <ul class="am-slides">
			  	<c:forEach items="${hotel.outsideImgs}" var="outsideimg">
					<li><img
						style="height: 100px;width: 200px;"
						src="${outsideimg}"></li>
				  </c:forEach>
				</ul>
			</div>
		</div>
		
		<div class="am-g">
			<div class="am-u-sm-3">酒店大厅图：</div>
			<div
			  class="am-slider am-slider-default am-slider-carousel  am-u-sm-3 am-u-end">
			  <ul class="am-slides">
					<c:forEach items="${hotel.innerImgs}" var="innerImg">
					<li><img
						style="height: 100px;width: 200px;"
						src="${innerImg}"></li>
				  </c:forEach>
				</ul>
			</div>
		</div>
		
		<div class="am-g">
				<div class="am-u-sm-3">
					${hotel.name}
				</div>
				<div class="am-u-sm-2">
					${hotel.stars}星级
				</div>
				<div class="am-u-sm-3">
					${hotel.mobile}
				</div>
				<div class="am-u-sm-4">
					${hotel.address}
				</div>
		</div>
		<div class="am-g">
			<div class="am-u-sm-3">服务内容：</div>
			<div class="am-u-sm-9">
				<c:forEach items="#{hotel.serviceContents}" var="serviceContent" varStatus="s">
					${serviceContent.name}、
				</c:forEach>
			</div>
		</div>
		<div class="am-g">
			<div class="am-u-sm-3">酒店介绍：</div>
			<div class="am-u-sm-9">
				${hotel.intro}
			</div>
		</div>
		
		<c:forEach items="${hotel.hotelRooms}" var="room">
			<div class="am-g">
				<div class="am-u-sm-3">酒店客房：</div>
				<div class="am-u-sm-3">
					${room.name} ${room.price}
				</div>
				<div
			  class="am-slider am-slider-default am-slider-carousel  am-u-sm-3 am-u-end">
					<ul class="am-slides">
					<c:forEach items="${room.imgs}" var="img">
					<li><img
						style="height: 100px;width: 200px;"
						src="${img}"></li>
				  </c:forEach>
				</ul>
				</div>
			</div>
		</c:forEach>
	</c:forEach>
	<script type="text/javascript">
	$('.am-slider').flexslider({itemWidth: 200, itemMargin: 5, slideshow: false});
	</script>
</body>
</html>