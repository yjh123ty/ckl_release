<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>酒店添加/修改</title>
</head>
<body class="am-g">

	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="form-wash-car-edit" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="hotel-id" value="${services[0].id}">
			<input type="hidden" name="isFree" value="0">
			<input type="hidden" name="own" value="1">
			<input type="hidden" name="serviceType" value="${services[0].serviceType}">
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">最低洗车价格：</label>
				<div class="am-u-sm-9">
					<input name="minimal" type="number" value="${services[0].minimal}">
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-7 am-u-sm-offset-5">
					<input data-cmd="hotel-confirm" type="button"
						class="am-btn am-btn-success am-btn-sm" value="确定"></input> <input
						data-cmd="hotel-back"
						type="button" class="am-btn am-btn-success am-btn-sm"
						value="返回"></input>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript">
		/**
		 * 服务站修改界面的js
		 */
		var washCarInit = function(){
			// 缓存界面组件
			var hotelEditForm = $("#form-wash-car-edit");
			
			var modal = $("#service-detail-dialog");
			
			// 按钮事件统一监控对象
			var btnClickObj = {
				'hotel-confirm':function(){
					// 封装服务类型参数
					var data = {};
					data['station.id'] = $("#service-station-id").val();
					hotelEditForm.ajaxSubmit( {    
			    	       url:       _ctx+"/stationservice/editWashCar.do", 
			    	       type:      "post",
			    	       data:data,
			    	       clearForm: false,     
			    	       resetForm: false,     
			    	       beforeSubmit: function(){
			    	    	   // 校验参数
			    	       }, 
			    	       success: function(data){
			    	    	   if(isJsonObj(data)){
									if(data.success){
										modal.dialog("close");
									}else {
										msgShow("提示", data.msg, "info");
									}
								} else {
									msgShow("提示", "系统异常", "info");
								}
			    	       }
			    	   });
				},
				'hotel-back':function(){
					modal.dialog("close");
				}
			};
			
			// 统一监听按钮的事件
			$("input[data-cmd]").click(function(){
				var cmd = $(this).data("cmd");
				if(cmd &&  btnClickObj[cmd]){
					btnClickObj[cmd](this);
				};
			});
		};
		washCarInit();
	</script>
</body>
</html>