<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印机添加/编辑</title>

</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="editForm" class="am-form am-form-horizontal">
			<input type="hidden" name="id" value="${printer.id}">
			
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">打印机名称：</label>
				<div class="am-u-sm-10">
					<input style="width:300px" name="name" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入打印机名称" value="${printer.name}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">用户id：</label>
				<div class="am-u-sm-10">
					<input style="width:300px" name="partner" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入用户id" value="${printer.partner}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">打印机终端号：</label>
				<div class="am-u-sm-10">
					<input style="width:300px" name="machineCode" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入打印机终端号" value="${printer.machineCode}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">API密钥：</label>
				<div class="am-u-sm-10">
					<input style="width:300px" name="apiKey" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入API密钥" value="${printer.apiKey}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">打印机密钥：</label>
				<div class="am-u-sm-10">
					<input style="width:300px" name="printerKey" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入打印机密钥" value="${printer.printerKey}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">所属服务站：</label>
						<div class="am-u-sm-10">
							<select id="stationSelect" name="station.id" style="width:300px" required="required">
								
								<c:forEach items="${stations}" var="stationVal">
							        <option  value="${stationVal.id}"
							        <c:if test="${printer.station.id == stationVal.id}">
						     			   selected="selected" 
									</c:if>
							        >
										${stationVal.name}
							        </option>
								</c:forEach>
					        </select>
				      	</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/printer/index.do'"  value="返回"></input> 
				</div>
			</div>
		</form>
	</div>

	<div class="am-modal am-modal-alert" tabindex="-1" id="msg-modal">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">温馨提示：</div>
	    <div id="msg_content" class="am-modal-bd">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn data-am-modal-confirm">确定</span>
	    </div>
	  </div>
	</div>
	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript">
		$(function(){
			
			$("#editForm").validator({});
			
			$("#confirm").click(function(){
				/* 1.封装额外的参数
					放入参数对象中
				3.验证表单
				4.使用ajax的方式提交
				5.成功返回上级界面刷新
				6.失败直接提示 */
				var form = $("#editForm");
				var data = {};
				form.ajaxSubmit( {    
				       url:       _ctx + "/printer/edit.do", 
				       type:      "post",
				       data: data,        
				       clearForm: true,     
				       resetForm: true,     
				       beforeSubmit: function(){
				    	// 校验参数
						return form.validator("isFormValid");
				       }, 
				       success: function(data){
		    	           var modal = $('#msg-modal');
				    	   if(isJsonObj(data)){
					    	   modal.find("#msg_content").text(data.msg);
					    	   
					    	   modal.find(".data-am-modal-confirm").on("click", function(){
						    	   if(data.success) {
						    		   location.href = _ctx + "/printer/index.do";
						    	   }
					    	   });
					    	   modal.modal();
				    	  }else {
				    		  modal.find("#msg_content").text("服务器异常");
				    		  modal.modal();
				    	  }
				       } 
				   });
			});
		});
	</script>
</body>
</html>