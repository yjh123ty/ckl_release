<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务评价管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/serviceEvaluation/serviceEvaluation.js"></script>

</head>
<body>
	<form id="serviceEvaluation-search-form" method="post">
		<input id="search-station-id" type="hidden" name="stationId" value="${stationId}">
		<input id="search-user-id" type="hidden" name="userId" value="${userId}">
		<input id="search-employee-id" type="hidden" name="employeeId" value="${employeeId}">
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">时间：</div>
			<div class="am-input-group am-datepicker-date am-sm-3 am-u-md-3 am-u-lg-3" data-am-datepicker="{format: 'yyyy-mm-dd',minView:2}">
			  <input type="text" name="beginTimeStr" class="am-form-field" placeholder="请输入开始时间" value="${beginTimeStr}" readonly>
			  <span class="am-input-group-btn am-datepicker-add-on">
			    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
			  </span>
			</div>
			<div class="am-u-sm-1" style="text-align: center;">
				到
			</div> 
			<div class="am-input-group am-datepicker-date am-sm-3 am-u-md-3 am-u-lg-3 am-u-end" data-am-datepicker="{format: 'yyyy-mm-dd',minView:2}">
			  <input type="text" name="endTimeStr" class="am-form-field" placeholder="请输入开始时间" value="${endTimeStr}" readonly>
			  <span class="am-input-group-btn am-datepicker-add-on">
			    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
			  </span>
			</div>
		</div>
		
		<div class="am-g am-margin">
       		<div class="am-u-sm-1"
				style="text-align: center;">订单类型：</div>
				<div class="am-u-sm-3 am-u-end">
				<select name="serviceTypeId" id="orderType" class="am-form-field" >
					<option value="-1">全部</option>
					<option value="1">酒店</option>
					<option value="2">饭店 </option>
					<option value="3">汽车保养</option>
					<option value="4">零部件维修</option>
					<option value="5">道路救援</option>
				</select>
			</div>
		</div>


		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">关键词：</div>
			
			<div class="am-u-sm-5 am-u-end">
				<input name="keywords" type="text" class="am-form-field" placeholder="用户账号、订单号、服务站点，服务员工姓名，评价关键字">
			</div>
		</div>
	</form>
	
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">评价星级：</div>
			<div id="stars-btn-group" class="am-u-sm-5 am-u-end">
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">5星</button>
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">4星</button>
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">3星</button>
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">2星</button>
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">1星</button>
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

	<!-- 数据表格 -->
	<table id="serviceEvaluation-datagrid" style="height: auto; min-height:400px; width: 100%;">
	</table>
	
	<!-- 评价星级弹框 -->
	<div class="am-modal am-modal-no-btn"  id="star-modal">
	  <div class="am-modal-dialog" >
	    <div class="am-modal-bd" style="margin:0px;padding:0px">
      		<iframe id="content" height="100%" width="100%" >
			</iframe>
	    </div>
	  </div>
	</div>
	
	<script type="text/javascript">

 		var starsBtnGroup = $('#stars-btn-group');
 		var searchForm = $('#serviceEvaluation-search-form');
 		var datagrid = $('#serviceEvaluation-datagrid');
 		
		// 搜索确定按钮
		var searchConfirm = $("#search-confirm");
		
		$(function(){
			var starsBtn = starsBtnGroup.children();
			// 查询对象
			var paramObj = searchForm.serializeJson();
			//缓存查询值
			var starVal = paramObj.star;
			var serviceTypeIdVal = paramObj.serviceTypeId;
			
			//订单服务类型
			var select = $('#orderType');
			select.on("change",function(){
				paramObj.serviceTypeId = select.val();
				serviceTypeIdVal = select.val();
				// 发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
			
			starsBtn.each(function(i, btn){
				var star = 5 - i;
				$(btn).click(function(){
					starsBtn.removeClass('am-btn-success');
					starsBtn.addClass('am-btn-default');
					$(this).addClass('am-btn-success');
					paramObj.star = star;
					starVal = star;
					// 发送post请求刷新 datagrid 的数据
					datagrid.datagrid("load",paramObj);
				});
			});
			
			//表单清空
			$("#resetSearchForm").on("click", function() {
				starsBtn.removeClass('am-btn-success');
				starsBtn.addClass('am-btn-default');
				searchForm.form("clear");
				//重新添加下拉框
				select.html("<option value='-1'>--请选择--</option>")
				.append("<option value='1'>酒店</option>")
				.append("<option value='2'>饭店 </option>")
				.append("<option value='3'>汽车保养</option>")
				.append("<option value='4'>零部件维修</option>")
				.append("<option value='5'>道路救援</option>");
				starVal = null;
				serviceTypeIdVal = null;
			});
			
			// 设置搜索按钮的点击事件
			searchConfirm.click(function(){
				paramObj = searchForm.serializeJson();
				
				paramObj.star = starVal;
				paramObj.serviceTypeId = serviceTypeIdVal;
				// 发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
			
		})

	</script>
	
</body>
</html>