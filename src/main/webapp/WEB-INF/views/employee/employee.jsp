<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>


<script type="text/javascript" src="${ctx}/js/employee/employee.js"></script>

</head>
<body>
<div class="am-margin-left">
		<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" href="${ctx}/employee/index.do">员工账号</a>
		&emsp;
<%-- 		<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/department/index.do">设定部门</a> --%>
<!-- 		&emsp; -->
		<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/jobTitle/index.do">设定岗位</a>
</div>
	<hr>
	<!-- 操作栏 -->
	<div class="am-margin-left">
		<a href="${ctx}/employee/index.do?cmd=save" 
			class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">添加员工</a>
		&emsp;
		<a href="${ctx}/employee/downloadTemplate.do"
			class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">下载导入模板</a>
		&emsp;
		<a href="#"
			class="am-btn am-btn-success am-btn-xs import-btn" style="border-radius: 300px;" role="button">导入员工</a>
		&emsp;
		<input type="button" onclick="downloadDomain('employee-search-form','employee')" style="border-radius: 300px;"
				class="am-btn am-btn-success am-btn-xs" value="导出员工">
	</div>
	<!-- 员工查询栏 -->
	<form id="employee-search-form" method="post">
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
		
		
<!-- 		<div class="am-g am-margin"> -->
<!-- 	               <div class="am-u-sm-1" -->
<!-- 							style="text-align: center;">所在部门：</div> -->
<!-- 	                <input name="deptId" class="easyui-combobox"  -->
<%-- 	                	data-options=" --%>
<%-- 	                		url: '${ctx}/department/getDataTree.do', --%>
<!-- 	                		valueField:'id', -->
<!-- 	                		textField:'name', -->
<!-- 	                		panelHeight:'auto', -->
<%-- 	                		" > --%>
<!-- 		</div> -->
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">关键词：</div>
			
			<div class="am-u-sm-5 am-u-end">
				<input name="keywords" type="text" class="am-form-field" placeholder="员工账号、姓名或岗位类别">
			</div>
		</div>
	</form>
	
	<div class="am-g am-margin">
		<div class="am-u-sm-1"
							style="text-align: center;">状态：</div>
		<button class="am-btn am-btn-default am-btn-xs" id="allStatusBtn" style="border-radius: 200px;">全部</button>
		&nbsp;
		<button class="am-btn am-btn-default am-btn-xs" id="invokeBtn" style="border-radius: 200px;">已开通</button>
		&nbsp;
		<button class="am-btn am-btn-default am-btn-xs" id="freezeBtn" style="border-radius: 200px;">已冻结</button>
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
	

	<!-- 员工数据表格 -->
	<table id="employee-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
	
	<!-- 导入员工模态对话框 -->
	<div style="text-align:center">
	    <div id="importModel" >
	    	<!-- 导入表单 -->
			<form id="importForm" enctype="multipart/form-data" method="post">
			
				<table style="margin:30px">
					<tr>
						<td style="width:500px">
							<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">导入员工：</label>
							<div class="am-form-file">
								
								<div class="am-u-sm-6">
									<button type="button" class="am-btn am-btn-success am-btn-sm">
										<i class="am-icon-cloud-upload"></i> 选择要上传的文件
									</button>
									<input name="importFile" class="doc-form-file" type="file">
								</div>
								<div class="am-u-sm-6 am-u-end file-list">没有选择任何文件</div>
							</div>
						</td>
					</tr>
		    	
					<tr>
						<td style="padding:30px;text-align:center">
							<div class="am-u-sm-10">
								<input type="button" class="am-btn am-btn-success am-btn-sm import-confirm" value="确定"></input> 
								&emsp;<input type="button" class="am-btn am-btn-success am-btn-sm import-back" value="返回"></input>
							</div>
						</td>
					</tr>
					
				</table>
			</form>
	    </div>
    </div>
  
  	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
  	<script type="text/javascript">
  	//查询控件
	function Searching(){
		search();
	}
	
	var searchForm = $('#employee-search-form');
	var importForm = $("#importForm");
	var datagrid = $("#employee-datagrid");
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
	//初始化导入对话框
	var model = $('#importModel');
	model.dialog({
	    title: '导入员工',
	    closed: true,
	    cache: false,
	    maximizable:true,
	    modal: true
	});
	
	//导入路径
	var importUrl = _ctx + '/employee/import.do';
	
	var search = function(){
		// TODO 验证表单参数
		// 获取搜索表单 
		// 封装表单参数
		var paramObj = searchForm.serializeJson();
		//发送post请求刷新 datagrid 的数据
		datagrid.datagrid("load",paramObj);
	};
	
	
	$(function() {
		// 设置搜索按钮的点击事件
		searchConfirm.click(search);
		
		var allStatusBtn = $("#allStatusBtn");
		var invokeBtn = $("#invokeBtn");
		var freezeBtn = $("#freezeBtn");
		
		allStatusBtn.click(function(){
			$(this).removeClass('am-btn-default');
			$(this).addClass('am-btn-success');
			
			invokeBtn.removeClass('am-btn-success');
			invokeBtn.addClass('am-btn-default');
			freezeBtn.removeClass('am-btn-success');
			freezeBtn.addClass('am-btn-default');
			var paramObj = searchForm.serializeJson();
			paramObj['status'] = "";
			//发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
		
		//已冻结按钮
		invokeBtn.click(function(){
			$(this).removeClass('am-btn-default');
			$(this).addClass('am-btn-success');
			
			freezeBtn.removeClass('am-btn-success');
			freezeBtn.addClass('am-btn-default');
			allStatusBtn.removeClass('am-btn-success');
			allStatusBtn.addClass('am-btn-default');
			var paramObj = searchForm.serializeJson();
			paramObj['status'] = 0;
			//发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
		
		
		//已开通按钮
		freezeBtn.click(function(){
			$(this).removeClass('am-btn-default');
			$(this).addClass('am-btn-success');
			
			invokeBtn.removeClass('am-btn-success');
			invokeBtn.addClass('am-btn-default');
			allStatusBtn.removeClass('am-btn-success');
			allStatusBtn.addClass('am-btn-default');
			var paramObj = searchForm.serializeJson();
			paramObj['status'] = -1;
			//发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
		
		//表单清空
		$("#resetSearchForm").on("click", function() {
			invokeBtn.removeClass('am-btn-success');
			invokeBtn.addClass('am-btn-default');
			freezeBtn.removeClass('am-btn-success');
			freezeBtn.addClass('am-btn-default');
			allStatusBtn.removeClass('am-btn-success');
			allStatusBtn.addClass('am-btn-default');
			searchForm.form("clear");
		});
		
		 var fileNames = '';
		//上传文件JS效果
		 $('.doc-form-file').on('change',function() {
				 $.each(this.files, function() {
					fileNames = '<span class="am-badge file-text">' + this.name
							+ '</span> ';
				});
			 	fileNames = fileNames ? fileNames : "没有选择任何文件";
			 	$(this).closest(".am-form-file").find('.file-list').html(fileNames);
		 });
		
		//点击返回按钮
    	$(".import-back").click(function(data){
    		fileNames = "没有选择任何文件";
    		$('.file-list').html(fileNames)
    		//刷新
    		model.dialog('refresh');
    		//关闭
    		model.dialog('close');
    	});
		 
		//上传execl
		$(".import-confirm").on("click", function() {
			//导入路径
			var importUrl = _ctx + '/employee/import.do';
			//提交表单
			var form = $("#importForm");
			
			var data = {};
			
			form.ajaxSubmit( {    
			       url:       importUrl, 
			       type:      "post",
			       data: data,        
			       clearForm: true,     
			       resetForm: true,     
			       beforeSubmit: function(){
			       }, 
			       success: function(data){
		    	        // 判断
	   					if (data.success) {
	   						// 提示
	   						$.messager.alert("温馨提示", data.msg, "info",
	   							function() {
	   								//刷新
	   								datagrid.datagrid("reload");
	   								//关闭对话框
									model.dialog('close');
	   							});
	   					} else {
	   						$.messager.alert("温馨提示", data.msg, "info");
	   					}
			       } 
		   });
		});
		
		//点击导入员工按钮
		$(".import-btn").click(function(){
			//初始化出现位置(以点击的按钮为参照，设置偏移量)
			var top = $(this).offset().top + 200;
			var left = $(this).offset().left + 400;
			model.window('open').window('resize',{width:'560px',height:'250px',top: top,left:left});
		});
		
		
	})
	
	</script>
  
</body>
</html>