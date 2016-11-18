<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<div class="am-margin">
		<div class="am-margin">
			<a href="${ctx}/servicestation/index.do" role="button"
				class="am-btn am-btn-success am-btn-sm">返回</a>
		</div>
	</div>
	<c:if test="${restaurants.size() == 0}">
		<h3 style="text-align: center;">暂无饭店信息</h3>
	</c:if>
	<c:if test="${restaurants.size() > 0}">
	<c:forEach items="${restaurants}" var="restaurant">
		<section class="am-panel am-panel-default">
			<header class="am-panel-hd">
				<h3 class="am-panel-title">${restaurant.name}</h3>
			</header>
			<div class="am-panel-bd">
				<div>
					封面图片：<img src="${img_host}${restaurant.cover}" height="200px" width="200px">
				</div>
				<div>
					外观图片：
					<div class="am-slider am-slider-default am-slider-carousel" style="width: 300px; display: inline-block;">
					  <ul class="am-slides">
					  	<c:forEach items="${restaurant.imgs}" var="img">
							<li><img
								style="height: 200px;width: 200px;"
								src="${img_host}${img}"></li>
						  </c:forEach>
						</ul>
					</div>
				</div>
				<div>
					所在地址：${restaurant.fullAddress}
				</div>
				<div>
					联系电话：${restaurant.mobile}
				</div>
				<div>
					饭店星级：${restaurant.stars}
				</div>
				<div>
					最低消费：${restaurant.minimun}
				</div>
				<div>
					营业时间：${restaurant.openTime}-${restaurant.closeTime}
				</div>
				<div>
					订餐提示：${restaurant.orderTips}
				</div>
				<div>
					订餐规则：${restaurant.orderRule}
				</div>
				<div>
					温馨提示：${restaurant.orderRule}
				</div>
				<div>
					饭店介绍：${restaurant.intro}
				</div>
				<c:forEach items="${restaurant.restaurantCombos}" var="combo">
					<section class="am-panel am-panel-default">
						<header class="am-panel-hd">
							<h3 class="am-panel-title">${combo.name}</h3>
						</header>
						<div class="am-panel-bd">
							<div>
								套餐图片：<img src="${img_host}${combo.img}" height="200px" width="200px">
							</div>
							<div>
								<fmt:formatDate value="${combo.startTime}" var="startTime" pattern="yyyy.MM.dd"></fmt:formatDate>
								<fmt:formatDate value="${combo.endTime}" var="endTime" pattern="yyyy.MM.dd"></fmt:formatDate>
								有效期：
								<c:if test="${startTime != null && endTime != null}">
									${startTime}-${endTime}
								</c:if>
							</div>
							<div>
								预订价格：${combo.price}
							</div>
							<div>
								详细描述：${combo.detail}
							</div>
						</div>
					</section>
				</c:forEach>
			</div>
		</section>
	</c:forEach>
</c:if>
	<script type="text/javascript">
	$('.am-slider').flexslider();
	</script>
</body>
</html>