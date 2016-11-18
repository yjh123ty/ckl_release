<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户充值套餐列表</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>


<script type="text/javascript" src="${ctx}/js/userRechargeCombo/list.js"></script>

</head>
<body>
	<!-- 操作栏 -->
	<form id="userRechargeCombo-search-form" method="post" class="am-form am-form-horizontal">
		<table class="am-margin">
		  <tr>
				<td>
					<a 
						href="${ctx}/userDistribution/index.do"
							class="am-btn am-btn-default am-btn-xs"
							style="border-radius: 300px;" type="button">用户业绩</a>
					<a 
						href="${ctx}/rechargeCombo/index.do"
							class="am-btn am-btn-success am-btn-xs"
							style="border-radius: 300px;" type="button">分润设置</a>
				</td>
			</tr>
			<tr>
				<td class="am-padding-top">
					<a 
						href="${ctx}/rechargeCombo/index.do"
							class="am-btn am-btn-success am-btn-xs"
							style="border-radius: 300px;" type="button">推荐奖励</a>
					<a 
						href="${ctx}/userDistribution/index.do?cmd=listAward"
							class="am-btn am-btn-default am-btn-xs"
							style="border-radius: 300px;" type="button">折让奖励</a>
					<a 
						href="${ctx}/userDistribution/index.do?cmd=listBeyondAward"
							class="am-btn am-btn-default am-btn-xs"
							style="border-radius: 300px;" type="button">超额奖励</a>
				</td>
			</tr>
			<%-- <tr>
				<td class="am-padding-left am-padding-bottom">
					<a href="${ctx}/rechargeCombo/index.do?cmd=save" 
						class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">添加</a>
				</td>
			</tr> --%>
			<tr>
				<td class="am-padding-left am-padding-top">
					<input name="keyword" type="text" class="am-form-field" placeholder="请输入关键词" style="width:400px">
				</td>
				<td class="am-padding-left">
					<button 
						id="search-confirm"
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="button">确&nbsp;定</button>
				</td>
			</tr>
		</table>
	</form>
	<!-- 数据表格 -->
	<table id="userRechargeCombo-datagrid" style="min-height: 400px; height: auto; width: 100%;"></table>
</body>
</html>