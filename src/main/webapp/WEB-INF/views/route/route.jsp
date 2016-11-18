<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/amazeui/css/amazeui.datetimepicker.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路线管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/route/route.js"></script>
</head>
<body>
	
	<!-- 路线工具条 -->
	<div class="am-g am-margin-left" style="padding: 8px;">
		<div class="am-g">
			<div class="am-u-sm-3">
				<button id="btn-add-route"
					class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;">添&nbsp;加</button>
			</div>
		</div>
		<form id="route-search-form" class="am-form">
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
						id="search-confirm"
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
							<button  id="do-search" class="am-btn am-btn-success" type="button">
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
	
	<!-- 路线表格 -->
	<table id="route-datagrid" style="width: 100%;height: auto; min-height: 400px;">
	</table>
	<!-- 攻略模态框 -->
	<div class="am-modal am-modal-alert" tabindex="-1" id="guide-modal">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd"></div>
	    <div id="guide_content" class="am-modal-bd">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn" id="model-confirm">确定</span>
	    </div>
	  </div>
	</div>
	
	<script type="text/javascript" src="${ctx}/amazeui/js/amazeui.datetimepicker.min.js"></script>
	<script type="text/javascript" src="${ctx}/amazeui/js/amazeui.datetimepicker.zh-CN.js"></script>
	<script type="text/javascript">
		$(".date").datetimepicker({
			'initialDate':new Date(),
			language:  'zh-CN'
		});
		
		//图片预览
		function scanPic(othis){
			var pic=($(othis).attr('src'));
			var myEle='<div id="picDivCom"><span onclick="picClose(this)" id="chacha">x</span></div>';
			$('body').append(myEle);
			$('#picDivCom').append('<img src="'+img_host+pic+'"/>');

		}
		function picClose(othis){
			$(othis).parent().remove();
		}
	</script>
</body>
</html>