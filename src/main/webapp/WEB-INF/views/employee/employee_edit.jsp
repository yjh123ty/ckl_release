<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工添加</title>
</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="userEditForm" class="am-form am-form-horizontal">
			<input id="employeeId" type="hidden" name="id" value="${employee.id}">
			<input type="hidden" name="status" value="${employee.status}">
			<input type="hidden" name="role.id" value="${employee.role.id}">
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">岗位类别：</label>
				<div class="am-u-sm-10">
					<select id="isManager" name="jobTitle.id" style="width:300px" required="required">
					<!-- 如果是添加数据，则第一栏为请选择 -->
					<option value="-1">---请选择---</option>
					
						<c:forEach items="${jobs}" var="jobVal">
					        <option  value="${jobVal.id}"
					        	<c:if test="${employee.jobTitle.id == jobVal.id}">
				     			   selected="selected" 
								</c:if>
							>
								${jobVal.name}
					        </option>
						</c:forEach>
			        </select>
		      	</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">所属服务站：</label>
						<div class="am-u-sm-10">
							<select id="stationSelect" name="station.id" style="width:300px" required="required" 
								<c:if test="${employee.jobTitle.id<=4}">
									 disabled="disabled"
								</c:if>
							>
								<option value="-1">--请选择--</option>
								
								<c:forEach items="${stations}" var="stationVal">
							        <option  value="${stationVal.id}"
							        <c:if test="${employee.station.id == stationVal.id}">
						     			   selected="selected" 
									</c:if>
							        >
										${stationVal.name}
							        </option>
								</c:forEach>
					        </select>
					       <font color="red"> 注：若为管理人员，则不选所属服务站，请勾选下面对应的管理服务站。</font>
				      	</div>
			</div>
			
			<div class="am-form-group">
				 <label for="" class="am-u-sm-2 am-form-label">所管理的服务站：</label>
				 
					 <div class="am-u-sm-10" id="stations">
					 	<c:forEach items="${stationsForManager}" var="sVal">
									<label class="am-checkbox-inline"> 
										<input type="checkbox" value="${sVal.id}"
											<c:forEach items="${employee.stations}" var="s">
												<c:if test="${s.id == sVal.id}">
													checked="checked"
												</c:if>
											</c:forEach>
										>
									${sVal.name}		 
									</label>				 
						</c:forEach>
					</div>
    		</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">员工账号：</label>
				<div id="mobileDiv" class="am-u-sm-10 am-form-icon am-form-feedback">
					<input id="mobileId" name="mobile" type="text" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入员工手机号" value="${employee.mobile}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">员工姓名：</label>
				<div id="realNameDiv" class="am-u-sm-10 am-form-icon am-form-feedback">
					<input id="realName" name="realName" type="text" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入员工姓名" value="${employee.realName}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">员工工号：</label>
				<div id="empNumberDiv" class="am-u-sm-10 am-form-icon am-form-feedback">
					<input id="empNumber" name="empNumber" type="text" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入员工工号" value="${employee.empNumber}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">薪点级别：</label>
				<div id="levelDiv" class="am-u-sm-10 am-form-icon am-form-feedback">
					<input id="level" name="salaryLevel" type="text" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入薪点级别(1-9级)" value="${employee.salaryLevel}">
		      	</div>
			</div>
			
    		
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/employee/index.do'"  value="返回"></input> 
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
	
		function isNum(a)
		{
		    var reg = /^[1-9]{1}$/;
		    return reg.test(a);
		}
	
		$(function(){
			var form = $("#userEditForm");
			$("#confirm").click(function(){
				/* 
				3.验证表单
				4.使用ajax的方式提交
				5.成功返回上级界面刷新
				6.失败直接提示 */
				
				var data = {};
				var selects = $("input:checked");
				selects.each(function(i){
					data["stations["+ i +"].id"] = this.value;
				});
				form.ajaxSubmit( {    
				       url:       _ctx + "/employee/edit.do", 
				       type:      "post",
				       data: data,        
				       clearForm: true,     
				       resetForm: true,     
				       beforeSubmit: function(){
				    		// 校验参数
				       }, 
				       success: function(data){
		    	           var modal = $('#msg-modal');
				    	   if(typeof(data) == "object" &&  Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length){
					    	   modal.find("#msg_content").text(data.msg);
					    	   
					    	   modal.find(".data-am-modal-confirm").on("click", function(){
					    		   console.debug("alert 确定....");
						    	   if(data.success) {
						    		   location.href = _ctx + "/employee/index.do";
						    	   }
					    	   });
					    	   modal.modal();
				    	  }else {
				    		  modal.find("#msg_content").text(data.msg);
				    		  modal.modal();
				    	  }
				       } 
				   });
			});
			
			
			
				var mobile = $('#mobileId');
				var employeeInput = $("#employeeId");
				var mobileDiv = $("#mobileDiv");
				var empNumber = $("#empNumber");
				var empNumberDiv = $("#empNumberDiv");
				var realName = $("#realName");
				var realNameDiv = $("#realNameDiv");
				var level = $("#level");
				var levelDiv = $("#levelDiv");
				var stationsBox = $("#stations").children();

				//验证是否手机号重复
				mobile.blur(function(){
					var mobileVal = mobile.val();
					var employeeId = employeeInput.val();
					
					//删除div内的span标签
					var spans = mobileDiv.children("span");
					
					if(mobileVal){
						//向后台发送验证字段
						$.post(_ctx + "/employee/checkEmployeeByMobileOrNum.do", { "employeeId" : employeeId,"mobile": mobileVal },
					          function(data){
//	 							console.debug(data);
									if(data.success){
										//删除div内的span标签
										for(var i=0;i<spans.length;i++){
										    //遍历span标签,移除span
										    spans[i].remove();
										}
										//更改样式
										mobileDiv.removeClass('am-form-error');
										mobileDiv.addClass("am-form-success").append('<span class="am-icon-check"></span>');
										
									}else if(!data.success){
										//删除div内的span标签
										for(var i=0;i<spans.length;i++){
										    //遍历span标签,移除span
										    spans[i].remove();
										}
										//更改样式
										mobileDiv.addClass("am-form-error").append('<span class="am-icon-times">'+data.msg+'</span>');
									}
				          },"json");
					}else{
						//删除div内的span标签
						for(var i=0;i<spans.length;i++){
						    //遍历span标签,移除span
						    spans[i].remove();
						}
						//更改样式
						mobileDiv.addClass("am-form-error").append('<span class="am-icon-times">输入不能为空！</span>');
					}
				});
				
				
				//验证是否工号重复
				empNumber.blur(function(){
					var empNumberVal = empNumber.val();
					var employeeId = employeeInput.val();
					
					//删除div内的span标签
					var spans = empNumberDiv.children("span");
					
					if(empNumberVal){
						//向后台发送验证字段
						$.post(_ctx + "/employee/checkEmployeeByMobileOrNum.do", { "employeeId" : employeeId,"empNumber": empNumberVal },
					          function(data){
									if(data.success){
										//删除div内的span标签
										for(var i=0;i<spans.length;i++){
										    //遍历span标签,移除span
										    spans[i].remove();
										}
										//更改样式
										empNumberDiv.removeClass('am-form-error');
										empNumberDiv.addClass("am-form-success").append('<span class="am-icon-check"></span>');
										
									}else if(!data.success){
										//删除div内的span标签
										for(var i=0;i<spans.length;i++){
										    //遍历span标签,移除span
										    spans[i].remove();
										}
										//更改样式
										empNumberDiv.addClass("am-form-error").append('<span class="am-icon-times">'+data.msg+'</span>');
									}
				          },"json");
					}else{
						//删除div内的span标签
						for(var i=0;i<spans.length;i++){
						    //遍历span标签,移除span
						    spans[i].remove();
						}
						//更改样式
						empNumberDiv.addClass("am-form-error").append('<span class="am-icon-times">输入不能为空！</span>');
					}
				});
				
				//验证是否名称为空
				realName.blur(function(){
					var realNameVal = realName.val();
					
					//div内的span标签
					var spans = realNameDiv.children("span");
					
					if(realNameVal){
						//删除div内的span标签
						for(var i=0;i<spans.length;i++){
						    //遍历span标签,移除span
						    spans[i].remove();
						}
						//更改样式
						realNameDiv.removeClass('am-form-error');
						realNameDiv.addClass("am-form-success").append('<span class="am-icon-check"></span>');
						
					}else{
						//删除div内的span标签
						for(var i=0;i<spans.length;i++){
						    //遍历span标签,移除span
						    spans[i].remove();
						}
						//更改样式
						realNameDiv.addClass("am-form-error").append('<span class="am-icon-times">输入不能为空！</span>');
					}
				});
				
				//验证是否薪点级别为空
				level.blur(function(){
					var levelVal = level.val();
					
					//div内的span标签
					var spans = levelDiv.children("span");
					
					if(levelVal){
						if(isNum(levelVal)){
							//删除div内的span标签
							for(var i=0;i<spans.length;i++){
							    //遍历span标签,移除span
							    spans[i].remove();
							}
							//更改样式
							levelDiv.removeClass('am-form-error');
							levelDiv.addClass("am-form-success").append('<span class="am-icon-check"></span>');
						}else{
							//删除div内的span标签
							for(var i=0;i<spans.length;i++){
							    //遍历span标签,移除span
							    spans[i].remove();
							}
							//更改样式
							levelDiv.removeClass('am-form-success');
							levelDiv.addClass("am-form-error").append('<span class="am-icon-times">输入数字必须为1-9！</span>');
						}
						
					}else{
						//删除div内的span标签
						for(var i=0;i<spans.length;i++){
						    //遍历span标签,移除span
						    spans[i].remove();
						}
						//更改样式
						levelDiv.removeClass('am-form-success');
						levelDiv.addClass("am-form-error").append('<span class="am-icon-times">输入不能为空！</span>');
					}
				});
				
				
				//判断是否为管理人员，若是，禁用所在服务站下拉框,可用checkBox；若是普通员工，则禁用checkBox,打开下拉框
				var select = $('#isManager');
				var stationSelect = $("#stationSelect");
				select.on("change",function(){
					var jobId = select.val();
					
					if(jobId<5 && jobId>0){
						//更改样式(禁用所属服务站)
						stationSelect.attr('disabled',"disabled");
						var selectContent = stationSelect.find('option[value=-1]').text();
						$("#stationSelect option[value=-1]").attr("selected", true);
						
						//开启下面的复选框
						stationsBox.each(function(i, box){
							var box = $(box);
							var checkbox = box.find("input");
							checkbox.attr('disabled',false);
						});
					}
					
					else{
						//更改样式(开启所属服务站)
						stationSelect.attr('disabled',false);
						//禁用下面的复选框
						stationsBox.each(function(i, box){
							var box = $(box);
							var checkbox = box.find("input");
							checkbox.attr('checked',false);
							checkbox.attr('disabled','disabled');
						});
					}
				});
				
				
		});
	</script>
</body>
</html>