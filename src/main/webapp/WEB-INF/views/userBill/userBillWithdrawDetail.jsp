<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户充值报表</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/userBill/userBillWithdrawDetail.js"></script>

</head>
<body>
<div id="content" style='margin: 10px; padding: 10px'>
	<form id="userBillWithdrawDetail-search-form" method="post">
		
		<input type="hidden" value="${uid}" id="uid">
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">时间：</div>
			<div class="am-input-group am-datepicker-date am-u-sm-3" data-am-datepicker="{format: 'yyyy-mm-dd',viewMode:'days',minViewMode: 'days'}">
				到 
			<input name="endTimeStr" class="easyui-datebox" style="width: 200px">
		</div>

		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">关键词：</div>
			
			<div class="am-u-sm-5 am-u-end">
				<input name="keywords" type="text" class="am-form-field" placeholder="用户账号或昵称">
			</div>
		</div>	
	</form>
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

	<!-- 数据表格 -->
	<table id="userBillWithdrawDetail-datagrid" style="height: auto; min-height:400px; width: 100%;">
	</table>
	
	<!-- 转账成功-模态对话框 -->
	<div style="text-align:center">
	    <div id="dd" >
	    	<form id="transfer-form" method="post" enctype="multipart/form-data" >
		    	<table>
		    		<tr>
		    			<td style="width:80px;height:100px;margin:10px;text-align:center;font-size:20px">
		    				转账时间:
		    			</td>
		    			<td>
		    				<input name="transferTime" class="easyui-datetimebox" style="width:180px;">
		    			</td>
		    		</tr>
		    		
		    		<tr>
		    			<td style="width:80px;height:100px;margin:10px;text-align:center;font-size:20px">
		    				转账凭证截图:
		    			</td>
		    			<td>
		    				<input name="introImg" class="doc-form-file" type="file" accept="image/png,image/jpeg">
		    			</td>
		    		</tr>
		    		
		    		<tr>
		    			<td style="width:220px;height:100px;margin:20px;text-align:center;font-size:20px">
							<a  href="javascript:void(0)" 
								class="am-btn am-btn-success commit" 
								style="border-radius: 100px;width:80px" role="button">提交</a>
		    			</td>
		    			<td >
			                <a  href="javascript:void(0)" 
								class="am-btn am-btn-success back" 
								style="border-radius: 100px;width:80px" role="button">返回</a>
		    			</td>
		    		</tr>
		    	</table>
	    	</form>
	    </div>
    </div>
</div>

	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script language="javascript">
	
		//查询控件
		function Searching(){
 			search();
 		}

		var search = function(){
			// TODO 验证表单参数
			// 获取搜索表单 
			// 封装表单参数
			var paramObj = $('#userBillWithdrawDetail-search-form').serializeJson();
			// 发送post请求刷新 datagrid 的数据
			$('#userBillWithdrawDetail-datagrid').datagrid("load",paramObj);
		};
		
		// 搜索确定按钮
		var searchConfirm = $("#search-confirm");
		
		$(function() {
			// 设置搜索按钮的点击事件
			searchConfirm.click(search);
			//表单清空
			$("#resetSearchForm").on("click", function() {
				$('#userBillWithdrawDetail-search-form').form("clear");
			});
		})
		
	</script>

</body>
</html>