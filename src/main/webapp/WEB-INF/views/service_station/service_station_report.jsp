<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务站财务报表管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/amazeui/css/amazeui.datetimepicker.css">
<style type="text/css">
	#search-table td{
		margin: 0 5 0 5;
	}
</style>
</head>
<body>
<script type="text/javascript" src="${ctx}/js/service_station/service_station_report.js"></script>
	
		<form id="service-station-report-search-form" class="am-form">
		<table id="search-table" class="am-table">
			<tr>
				<td>时&emsp;间：</td>
				<td>
					<div class="am-input-group date" id="datetimepicker"
						data-date-format="yyyy-mm">
						<input id="start-time" name="startTime" size="16" type="text"
							class="am-form-field" readonly> <span
							class="am-input-group-label add-on"><i
							class="icon-th am-icon-calendar"></i></span>
					</div>
				</td>
				<td align="center">
					到
				</td>
				<td>
					<div class="am-input-group date" id="datetimepicker"
						data-date-format="yyyy-mm">
						<input id="end-time" name="endTime" size="16" type="text" class="am-form-field"
							readonly> <span class="am-input-group-label add-on"><i
							class="icon-th am-icon-calendar"></i></span>
					</div>
				</td>
				<td>
					<select id="station-select" autocomplete="off" name="stationId">
						<option value="-1">全部</option>
						<c:forEach items="${stations}" var="item">
							<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select> <span class="am-form-caret"></span>
				</td>
				<td>
					<button id="search-confirm" class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="button">确&nbsp;定</button>
					<button id="clear-search-form" class="am-btn am-btn-success am-btn-xs"
							style="border-radius: 300px;" type="button">清&nbsp;空</button>
				</td>
				<td>
					<input type="button" onclick="downloadDomain('service-station-report-search-form','servicestationreport')" style="border-radius: 300px;"
					class="am-btn am-btn-success am-btn-xs" value="下载报表">
				</td>
			</tr>
		</table>
				
		</form>
	
	<!-- 服务站财务报表表格 -->
	<table id="service-station-report-datagrid" style="width: 100%;height: auto; min-height: 400px;">
	</table>

	<script type="text/javascript" src="${ctx}/amazeui/js/amazeui.datetimepicker.min.js"></script>
	<script type="text/javascript" src="${ctx}/amazeui/js/amazeui.datetimepicker.zh-CN.js"></script>
	<script type="text/javascript">
		$(".date").datetimepicker({
			'initialDate':new Date(),
			'endDate': new Date(),
			'minView':3,
			'viewSelect':3,
			language:  'zh-CN'
		});
		
		function showIncome(stationId){
			var url = _ctx+ "/servicestationreport/orderInfo.do";
			location.href=url+"?"+parseParam({
				stationId:+stationId,
				startTime:$("#start-time").val(),
				endTime:$("#end-time").val()
			});
		}
		function showStar(stationId){
			var url = _ctx+ "/servicestationreport/starInfo.do";
			location.href=url+"?"+parseParam({
				stationId:+stationId,
				startTime:$("#start-time").val(),
				endTime:$("#end-time").val()
			});
		}
	</script>
</body>
</html>