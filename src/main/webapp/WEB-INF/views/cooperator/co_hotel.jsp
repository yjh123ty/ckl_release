<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合作商家-酒店管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/cooperator/co_hotel.js"></script>
</head>

<body>
<div class="am-g" style='margin: 1px; padding: 10px'>
	<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;width:100px;" href="${ctx}/cooperatorHotel/index.do">酒店</a>
	&emsp;
	<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;width:100px;" href="${ctx}/cooperatorRestaurant/index.do">饭店</a>
</div>
	<hr>
  <div id="content" style='margin: 10px; padding: 10px'>
	<!-- 操作栏 -->
	<div class="am-g" style='margin: 10px; padding: 10px'>
		<a href="${ctx}/cooperatorHotel/index.do?cmd=save" class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">添加商家</a>
	</div>
	<!-- 查询栏 -->
	<form id="cooperator-search-form" method="post">
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
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">关键词：</div>
			
			<div class="am-u-sm-5 am-u-end">
				<input name="keywords" type="text" class="am-form-field" placeholder="酒店名称">
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
	<table id="cooperator-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
  </div>
  
  	<script type="text/javascript">
 	// 改变合作商家的状态
	var changeStatus = function(id, status){
		$.post(_ctx+"/hotel/changeStatus.do", {
			id: id,
			status:status
			}, function(data){
			if(data.success) {
				$("#cooperator-datagrid").datagrid("reload");
			}
		},"json");
	};
  	
	function Searching(){
		search();
	}

	var search = function(){
		// TODO 验证表单参数
		// 获取搜索表单 
		// 封装表单参数
		var paramObj = $('#cooperator-search-form').serializeJson();
		// 发送post请求刷新 datagrid 的数据
		$('#cooperator-datagrid').datagrid("load",paramObj);
	};
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
	$(function() {
		// 设置搜索按钮的点击事件
		searchConfirm.click(search);
		
		//表单清空
		$("#resetSearchForm").on("click", function() {
			$('#cooperator-search-form').form("clear");
		});
	})
	
		//图片预览
		function scanPic(othis){
			var picArr=JSON.parse($(othis).attr('src'));
			var myEle='<div id="picDivCom"><span onclick="picClose(this)" id="chacha">x</span></div>';
			$('body').append(myEle);
			$.each(picArr,function(oindex,odata){
				$('#picDivCom').append('<img src="'+img_host+odata+'"/>');
			});
		}
		function picClose(othis){
			$(othis).parent().remove();
		}

	</script>
  
</body>
</html>