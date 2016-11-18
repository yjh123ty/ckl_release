<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/travel_note/template_travel_note.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/amazeui/css/amazeui.datetimepicker.css" />
<title>游记管理</title>
</head>
<body>
	<div  style="padding:8px;">
		<a href="${ctx}/templatetravelnote/index.do?cmd=save" class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">添&nbsp;加</a>
	</div>
	<div style="padding: 8px;">
		<form id="travel-note-search-form" class="am-form">
			<div class="am-g am-margin-top">
				<div class=" am-sm-1 am-u-md-1 am-u-lg-1"
					style="text-align: center;">时&emsp;间：</div>
				<div class="am-input-group date  am-sm-3 am-u-md-3 am-u-lg-3"
					id="datetimepicker" data-date-format="yyyy-mm-dd hh:ii:ss">
					<input name="startTime" size="16" type="text" class="am-form-field" readonly>
					<span class="am-input-group-label add-on"><i
						class="icon-th am-icon-calendar"></i></span>
				</div>
				<div class=" am-sm-1 am-u-md-1 am-u-lg-1"
					style="text-align: center;">到</div>
				<div class="am-input-group date  am-sm-3 am-u-md-3 am-u-lg-3"
					id="datetimepicker" data-date-format="yyyy-mm-dd hh:ii:ss">
					<input name="endTime" size="16" type="text" class="am-form-field" readonly>
					<span class="am-input-group-label add-on"><i
						class="icon-th am-icon-calendar"></i></span>
				</div>
				<div class="am-sm-1 am-u-md-1 am-u-lg-1 am-u-end">
					<button 
						data-cmd="search-confirm"
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="button">确&nbsp;定</button>
				</div>
			</div>
			<div class="am-g am-margin-top">
				<div class=" am-sm-1 am-u-md-1 am-u-lg-1"
					style="text-align: center;">关键词：</div>
				<div class="am-u-sm-6 am-u-md-5 am-u-lg-4 am-u-end">
					<div class="am-input-group">
						<input name="keyword" type="text" class="am-form-field" placeholder="请输入关键词">
						<span class="am-input-group-btn">
							<button  data-cmd="do-search" class="am-btn am-btn-success" type="button">
								<span class="am-icon-search"></span>
							</button>
						</span>
					</div>
				</div>
				<div class=" am-sm-1 am-u-md-1 am-u-lg-1 am-u-end">
					<button 
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="reset">清&nbsp;空</button>
				</div>
			</div>
		</form>
	</div>
	
	<table id="travel-note-datagrid" style="width: 100%;height: auto; min-height: 400px;">
	</table>
	
	<div id="travel-note-content-dialog"></div> 
	
	<script type="text/javascript" src="${ctx}/amazeui/js/amazeui.datetimepicker.min.js"></script>
	<script type="text/javascript" src="${ctx}/amazeui/js/amazeui.datetimepicker.zh-CN.js"></script>
		<script type="text/javascript">
		var dialog = $('#travel-note-content-dialog');
		var datagrid = $("#travel-note-datagrid");
		dialog.dialog({    
		    closed: true,    
		    cache: false, 
		    modal: true   
		});  
		$(".date").datetimepicker({
			'initialDate':new Date(),
			language:  'zh-CN'
		});
	</script>
</body>
</html>