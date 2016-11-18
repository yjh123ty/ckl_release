<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工工资表编辑</title>
</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="editForm" class="am-form am-form-horizontal">
			<input type="hidden" name="id" value="${salary.id}">
			<input type="hidden" name="employee.id" value="${salary.employee.id}">
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">员工姓名：</label>
				<div class="am-u-sm-9">
					${salary.employee.realName}
				</div>
			</div>
			
			<div class="am-form-group">
				<label class="am-u-sm-3 am-form-label">员工工号：</label>
				<div class="am-u-sm-9">
					${salary.employee.empNumber}
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">月基本工资（元）：</label>
				<div class="am-u-sm-9">
					<input type="hidden" name="baseSalary" value="${salary.baseSalary}">${salary.baseSalary}
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">薪点工资（元）：</label>
				<div class="am-u-sm-9">
					<input type="hidden" name="pointSalary" value="${salary.pointSalary}">${salary.pointSalary}
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">绩效工资（元）：</label>
				<div class="am-u-sm-9">
					<input type="hidden" name="performanceSalary" value="${salary.performanceSalary}">${salary.performanceSalary}
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">津贴（元）：</label>
				<div class="am-u-sm-9">
					<input name="allowance" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" 
						<c:if test="${salary.allowance == null}">
							value="0.00"
						</c:if>
						<c:if test="${salary.allowance != null}">
							value="${salary.allowance}"
						</c:if>
					>
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">奖金（元）：</label>
				<div class="am-u-sm-9">
					<input name="bouns" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" 
						<c:if test="${salary.bouns == null}">
							value="0.00"
						</c:if>
						<c:if test="${salary.bouns != null}">
							value="${salary.bouns}"
						</c:if>
					>
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">扣减工资（元）：</label>
				<div class="am-u-sm-9">
					<input name="deductSalary" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" 
						<c:if test="${salary.deductSalary == null}">
							value="0.00"
						</c:if>
						<c:if test="${salary.deductSalary != null}">
							value="${salary.deductSalary}"
						</c:if>
					>
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">实发工资（元）：</label>
				<div class="am-u-sm-9">
					<input name="realSalary" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" value="${salary.realSalary}" disabled="disabled">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">备注信息：</label>
				<div class="am-u-sm-9">
					<input name="intro" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入" value="${salary.intro}">
				</div>
			</div>
    		
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/salary/index.do'"  value="返回"></input> 
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
				       url:       _ctx + "/salary/edit.do", 
				       type:      "post",
				       data: data,        
				       clearForm: true,     
				       resetForm: true,     
				       beforeSubmit: function(){
				       }, 
				       success: function(data){
		    	           var modal = $('#msg-modal');
				    	   if(typeof(data) == "object" &&  Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length){
					    	   modal.find("#msg_content").text(data.msg);
					    	   
					    	   modal.find(".data-am-modal-confirm").on("click", function(){
					    		   console.debug("alert 确定....");
						    	   if(data.success) {
						    		   location.href = _ctx + "/salary/index.do";
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
			
			
			//实发工资计算(页面加载时，获取所有数据，进行计算；当编辑框失去焦点，就触发统计一次)
			
			var form = $('#editForm');
			var baseSalary = form.find('input[name=baseSalary]').val();
			var pointSalary = form.find('input[name=pointSalary]').val();
			var performanceSalary = form.find('input[name=performanceSalary]').val();

			//从系统获取的订单数据
			var countSalary = baseSalary*1+pointSalary*1+performanceSalary*1;
			//自动计算小计:实发工资
			form.on('blur',"input[name=allowance],input[name=bouns],input[name=deductSalary]",function(){
				var allowance = form.find('input[name=allowance]').val();
				var deductSalary = form.find('input[name=deductSalary]').val();
				var bouns = form.find('input[name=bouns]').val();
				var amount = (countSalary*1+allowance*1+bouns*1-deductSalary*1).toFixed(2);
				form.find('input[name=realSalary]').val(amount);
			});
			
		});
		
		
	</script>
</body>
</html>