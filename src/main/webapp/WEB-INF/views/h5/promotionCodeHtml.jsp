<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>推广码分享</title>
<%@ include file="/WEB-INF/views/commons/head.jsp" %>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp" %>
<style type="text/css">
	.app-titlebar{
		height:60px;
		line-height: 40px;
		padding:10px;
		border-bottom: 1px solid #EEE;
	}
	.app-titlebar img{
		height: 40px;
	}
	.main{
		background-color: #fff;
	}
	.combo-title{
	 text-align: center;
	 font-weight: bold;
	 color: black;
	 font-size: 18px;
	 margin-top: 10px;
	}
	.combo-content{
		text-align:left;
		padding: 10px 30px;
	}
	.free-service, .promotion-info{
		padding:10px;
	}
	.scan-code, .input-code{
		width:50%;
		text-align:center;
		display: table-cell;
		vertical-align: middle;
		padding:10px;
	}
	.input-code{
		vertical-align: top;
	}
	.tip-text{
		color: #AFAFAF;
		font-size: 14px;
		padding-bottom: 10px;
	}
	.promotion-code{
		font-weight: bold;
		font-size: 18px;
		margin: 10px 0;
	}
	.am-panel-title{
		border-left: 5px solid #00B9B7;
		padding-left: 10px;
	}
	.service-table{
		width:100%;
		padding: 20px;
		margin:15px 0;
		color:#848484;
	}
	.service-table td{
		padding-left:15px;
	}
	body{
		background:#f0f0f0; 
	}
</style>
</head>
<body>
	<!-- xxxxxxxxxxxx
			{
		  "rechargeComboId": 0,
		  "name": "体验套餐B2",
		  "promotionCode": "abggr",
		  "promotionQRCodeUrl": "http://120.76.20.226:8080/ckl//user/remote/getQRCode?code\u003dabggr",
		  "shareUrl": null,
		  "price": 360.0,
		   "presentServiceInfos": [
		    {
		      "presentServiceId": 1,
		      "name": "打蜡",
		      "count": 1,
		      "intro": "351刻（原价）"
		    },
		    {
		      "presentServiceId": 2,
		      "name": "洗车",
		      "count": 6,
		      "intro": "378刻（原价）"
		    }]
		}
	-->
	<div class="main">
		<div class="app-titlebar">
			<img alt="logo" src="${ctx}/images/share_logo.png">
			<img alt="logo" src="${ctx}/images/share_logo_text.png" width="150" height="30px" style="float:right; vertical-align: middle;">
		</div>
		<div class="combo">
			<div class="combo-title">${promotionCodeInfo.name}</div>
			<div class="combo-content">
				${promotionCodeInfo.content}
			</div>
		</div>
		<div class="free-service">
		    <h3 class="am-panel-title">赠送服务(线下领券)：</h3>
		    <c:if test="${promotionCodeInfo.presentServiceInfos != null && promotionCodeInfo.presentServiceInfos.size() > 0 }">
			    <table class="service-table">
			    <c:forEach items="${promotionCodeInfo.presentServiceInfos}" var="info">
			    	<tr>
			    		<td>
			    			${info.name}
			    		</td>
			    		<td>
			    			${info.count}次
			    		</td>
			    		<td>
			    			${info.intro }
			    		</td>
			    	</tr>
			    </c:forEach>
			    </table>
	   	    </c:if>
		</div>
		<div class="promotion-info">
		    <h3 class="am-panel-title">充值方式：</h3>
		  <div>
		    <div class="scan-code">
		    	<div class="tip-text">
		    		1.扫码充值
		    	</div>
		    	<div>
		    		<img alt="qr code" src="${ctx}/user/remote/getQRCode?code=${promotionCodeInfo.promotionCode}&width=100&height=100">
		    	</div>
		    </div>
		    <div class="input-code">
		    	<div class="tip-text">
		    		2.输入推广代码充值
		    	</div>
		    	<div class="promotion-code">
		    		${promotionCodeInfo.promotionCode}
		    	</div>
		    	<div>
		    		<a class="am-btn am-btn-success" href="http://www.baidu.com" target="_blank">立即充值</a>
		    	</div>
		    </div>
		  </div>
		</div>
	</div>
</html>