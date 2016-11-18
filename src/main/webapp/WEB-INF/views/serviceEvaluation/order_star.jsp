<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div style="text-align: center;margin-top:25px">
		<table style="text-align: center;margin:0 auto;">
			<c:forEach items="${orderStars}" var="orderStarVal">
				<input type="hidden" value="${orderStarVal.star}" />
					<tr>
						<td style="text-align: center;">
							<div class="am-g star-bar">
								${orderStarVal.name}&emsp;
							</div>
						</td>
						
						<td style="text-align: center;">
							<c:choose>
								<c:when test="${orderStarVal.star == 1}">
									<img src="${ctx}/images/star.png" />
								</c:when>
								<c:when test="${orderStarVal.star == 2}">
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
								</c:when>
								<c:when test="${orderStarVal.star == 3}">
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
								</c:when>
								<c:when test="${orderStarVal.star == 4}">
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
								</c:when>
								<c:when test="${orderStarVal.star == 5}">
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
									<img src="${ctx}/images/star.png" />
								</c:when>
							</c:choose>
						</td>
					</tr>

			</c:forEach>
		</table>
	</div>
	
	
</body>
</html>