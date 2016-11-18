<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退款订单详情</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>

</head>
<body>
<div id="content" style='margin: 10px; padding: 10px'>
	<!-- 操作栏 -->
	<div class="am-g" style='margin: 1px; padding: 10px'>
		<a href="${ctx}/order/index.do?cmd=refund" class="am-btn am-btn-success am-btn-xs" 
			style="border-radius: 300px;width:100px" role="button">返回</a>
	</div>
	
	
	<!-- 数据展示 -->
	<input id="rowId" value="${detail.id}" type="hidden"/>
	
	<div style="float:left;width:500px;height:600px">
		<table class="orderTable">
		  <tr>
		    <td>订单号:</td>
		    <td>${detail.orderNumber}</td>
		  </tr>
		  <tr>
		    <td>订单状态:</td>
		    <td>
		    	<c:choose>
		    		<c:when test="${detail.status == 1}">
	    			已退款
		    		</c:when>
		    		
		    		<c:when test="${detail.status == 2}">
		    		退款中
		    		</c:when>
		    		
		    		<c:when test="${detail.status == 3}">
		    		已取消
		    		</c:when>
		    		
		    		<c:when test="${detail.status == 4}">
		    		待支付
		    		</c:when>
		    		
		    		<c:when test="${detail.status == 5}">
		    		待服务
		    		</c:when>
		    		
		    		<c:when test="${detail.status == 6}">
		    		待完成
		    		</c:when>
		    		
		    		<c:when test="${detail.status == 7}">
		    		待评价
		    		</c:when>
		    		
		    		<c:when test="${detail.status == 8}">
		    		已完成
		    		</c:when>
		    	</c:choose>
		    </td>
		  </tr>
		  
		  <tr>
		    <td>订单类型:</td>
		    <td>
		    	${detail.serviceType.name}
		    </td>
		  </tr>
		  
		  <tr>
		    <td>服务站点:</td>
		    <td>${detail.station.name}</td>
		  </tr>
		  
		  <tr>
		    <td>订单金额:</td>
		    <td>${detail.totalAmount}</td>
		  </tr>
		  
		  <tr>
		    <td>支付方式:</td>
		    <td>
		    	<c:choose>
		    		<c:when test="${detail.payMethod == 1}">
	    			支付宝
		    		</c:when>
		    		
		    		<c:when test="${detail.payMethod == 2}">
		    		微信支付
		    		</c:when>
		    		
		    		<c:when test="${detail.payMethod == 3}">
		    		平台币
		    		</c:when>
		    		
		    		<c:when test="${detail.payMethod == 4}">
		    		人工帮助
		    		</c:when>
		    	</c:choose>
		    </td>
		  </tr>
		  
		  <tr>
		    <td>用户账号:</td>
		    <td>${detail.user.mobile}</td>
		  </tr>
		  
		  <tr>
		    <td>用户昵称:</td>
		    <td>${detail.user.nickName}</td>
		  </tr>
		  
		  <tr>
		    <td>创建时间:</td>
		    <td>
			    ${detail.createTime}
		    </td>
		  </tr>
		</table>
	</div>
	
	<hr>
	
	<!-- 根据不同的订单类型显示不同的字段 -->
		<!-- 酒店 -->
		<c:if test="${detail.serviceType.id == 1}">
			<c:forEach items="${orderServiceDetail}" var="detailVal">
				<table class="orderTable">
				  <th>入住信息</th>	
				  <tr>
				    <td>姓名:</td>
				    <td>${detailVal.attrCustomer}</td>
				  </tr>
				  <tr>
				    <td>手机号:</td>
				    <td>${detailVal.attrMobile}</td>
				  </tr>
				  <tr>
				    <td>房间类型:</td>
				    <td>${detailVal.serviceName}</td>
				  </tr>
				  <tr>
				    <td>房间数量:</td>
				    <td>${detailVal.num}</td>
				  </tr>
				  <tr>
				    <td>入住天数:</td>
				    <td>${detailVal.attrDays}</td>
				  </tr>
				  <tr>
				    <td>入住时间:</td>
				    <td>
				    	${detailVal.order.startTime}
				    </td>
				  </tr>
				  <tr>
				    <td>退房时间:</td>
				    <td>
				    	${detailVal.order.endTime}
				    </td>
				  </tr>
				</table>
			</c:forEach>
		</c:if>
		
		<!-- 饭店 -->
		<c:if test="${detail.serviceType.id == 2}">
			<c:forEach items="${orderServiceDetail}" var="detailVal">
				<table class="orderTable">
				  <th>商品信息</th>	
				  <tr>
				    <td>套餐:</td>
				    <td>
						${detailVal.serviceName}				
				    </td>
				  </tr>
				  
				   <tr>
				    <td>使用时间:</td>
				    <td>
				    	${detailVal.order.startTime}
				    	-
				    	${detailVal.order.endTime}
				    </td>
				   </tr>
				   
				  <tr>
				    <td>菜品介绍:</td>
				    <td>${detailVal.intro}</td>
				  </tr>
				</table>
			</c:forEach>
		</c:if>
		
		<!-- 汽车保养 -->
		<c:if test="${detail.serviceType.type == 3 || detail.serviceType.type == 4}">
			<table class="orderTable">
			  <tr>
			    <td>服务项目:</td>
			    <td>${detail.serviceType.name}</td>
			  </tr>
			  
			  <tr>
			    <td>车辆型号:</td>
			    <td>${detail.carType}</td>
			  </tr>
			  
			  <tr>
			    <td>车牌号:</td>
			    <td>${detail.carPlate}</td>
			  </tr>
			  
			  <tr>
			    <td>上牌时间:</td>
			    <td>
			  	  ${detail.carPlateDate}
			    </td>
			  </tr>
			  
			  <tr>
			    <td>行驶里程:</td>
			    <td>${detail.travelDistance}</td>
			  </tr>
			</table>
			
			<!-- 若该订单是变更的订单，则要显示出变更之前购买的记录 -->
			<table class="orderTable">
			  <tr>
			    <th>商品信息</th>
			  </tr>
			  
			  <c:forEach items="${orderServiceDetail}" var="detailVal">
			  	<c:if test="${detailVal.type == 3}">
				  	<tr>
					    <td>${detailVal.serviceName}:</td>
					    <td>${detailVal.servicePrice}元	&emsp;<font style="color:red">(已退)</font></td>
				  	</tr>
			  	</c:if>
			  	  
				  <tr>
				    <td>${detailVal.serviceName}:</td>
				    <td>${detailVal.servicePrice}元</td>
				  </tr>
			  </c:forEach>
			</table>
			
		</c:if>
		
		<!-- 道路救援 -->
		<c:if test="${detail.serviceType.id == 5}">
			<table class="orderTable">
			  <tr>
			    <td>汽车位置:</td>
			    <td>${detail.serviceAddress}</td>
			  </tr>
			  
			  <tr>
			    <td>车主电话:</td>
			    <td>${detail.user.mobile}</td>
			  </tr>
			  
			  <tr>
			    <td>服务项目:</td>
			    <td>${detail.serviceType.name}</td>
			  </tr>
			  
			  <tr>
			    <td>车辆型号:</td>
			    <td>${detail.carType}</td>
			  </tr>
			  
			  <tr>
			    <td>车牌号:</td>
			    <td>${detail.carPlate}</td>
			  </tr>
			  
			  <tr>
			    <td>上牌时间:</td>
				    <td>
				 	   ${detail.carPlateDate}
				    </td>
			  </tr>
			  
			  <tr>
			    <td>行驶里程:</td>
			    <td>${detail.travelDistance}</td>
			  </tr>
			  
			  <tr>
			    <td>受损情况:</td>
			    <td>${detail.carRemark}</td>
			  </tr>
			</table>
			
			<table class="orderTable">
			  <tr>
			    <th>商品信息</th>
			  </tr>
			  
			  <c:forEach items="${orderServiceDetail}" var="detailVal">
				  <tr>
				    <td>${detailVal.serviceName}:</td>
				    <td>${detailVal.servicePrice}元</td>
				  </tr>
			  </c:forEach>
			</table>
		</c:if>
		
	
</div>

<script type="text/javascript">

	$(function(){
		//请求路径
		var url = ''+ _ctx+ '/order/askRefundById.do';
		//订单id
		var rowId = $('#rowId').val();
		
		//申请退款
		$("a.ask-refund").click(function(data){
			$.messager.confirm("温馨提示", "是否申请退款？", function(yes) {
    			if (yes) {
    				$.post(url, {"id" : rowId}, 
						function(data) {
	    					// 判断
	    					if (data.success) {
	    						// 提示
	    						$.messager.alert("温馨提示", data.msg, "info",
	    							function() {
	    								//刷新页面
	    								$("#order-datagrid").datagrid("reload");
	    							});
	    					} else {
	    						$.messager.alert("温馨提示", data.msg, "info");
	    					}
    					}, "json");
    			}
			});
		});
			
	})
</script>

</body>
</html>