<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好评额外奖励分数规则</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/pointRule/fiveCountRule.js"></script>
</head>
<body>
	<div class="am-g" style='margin: 1px; padding: 10px'>
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/performance/index.do">员工绩效</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/formula/index.do">绩效公式</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salary/index.do">员工工资表</a>
			&nbsp;
			<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" href="${ctx}/serviceRule/index.do">员工分数生成规则</a>
	</div>
	<hr>

	<div class="am-g" style='margin: 1px; padding: 10px'>
		<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/serviceRule/index.do">服务刻数产生分数规则</a>
		&emsp;&emsp;
		<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/starsRule/index.do">获得评分产生分数规则</a>
		&emsp;&emsp;
		<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" href="${ctx}/fiveCountRule/index.do">好评额外奖励分数规则</a>
	</div>
	<hr>
	
	<div id="content" style='margin: 10px; padding: 10px'>
		<!-- 操作栏 -->
		<div class="am-g">
			<a href="${ctx}/fiveCountRule/index.do?cmd=save"
				class="am-btn am-btn-success am-btn-xs"
				style="border-radius: 300px;" role="button">添加</a>
		</div>

		<!-- 查询栏 -->
		<form id="fiveCountRule-search-form" method="post">
			<div class="am-g am-margin">
				<div class="am-u-sm-1"
							style="text-align: center;">时间：</div>
				<div class="am-input-group am-datepicker-date am-u-sm-3" data-am-datepicker="{format: 'yyyy-mm-dd',viewMode:'days',minViewMode: 'days'}">
				  <input type="text" name="beginTimeStr" class="am-form-field" placeholder="开始时间" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
				</div>
				<div class="am-u-sm-1" style="text-align: center;" >
				到 
				</div>
				<div class="am-input-group am-datepicker-date am-u-sm-3 am-u-end" data-am-datepicker="{format: 'yyyy-mm-dd',viewMode:'days',minViewMode: 'days'}">
				  <input type="text" name="endTimeStr" class="am-form-field" placeholder="结束时间" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
				</div>
			</div>


		<div class="am-g am-margin am-form-group">
			<div class="am-u-sm-1">
			</div>
			<div class=" am-u-sm-11">
				<button 
					id="search-confirm" class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;" id="resetSearchForm">查&nbsp;询</button>&emsp;
				<button 
					class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;" type="reset" id="resetSearchForm">清&nbsp;空</button>
			</div>
		</div>
		</form>

		<!-- 数据表格 -->
		<table id="fiveCountRule-datagrid"></table>
	</div>

	<script type="text/javascript">
 		function Searching(){
 			search();
 		}

		var search = function(){
			// 获取搜索表单 
			// 封装表单参数
			var paramObj = $('#fiveCountRule-search-form').serializeJson();
			// 发送post请求刷新 datagrid 的数据
			$('#fiveCountRule-datagrid').datagrid("load",paramObj);
		};
		// 搜索确定按钮
		var searchConfirm = $("#search-confirm");
		
		$(function() {
			// 设置搜索按钮的点击事件
			searchConfirm.click(search);
		})

	</script>

</body>
</html>