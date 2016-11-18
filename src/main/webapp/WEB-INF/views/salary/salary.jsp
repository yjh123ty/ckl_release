<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工工资表</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/salary/salary.js"></script>

</head>
<body>

<div class="am-g" style='padding: 8px'>
		
			<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" href="${ctx}/salary/index.do">员工工资表</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/employeeAttendanceInfo/index.do">员工考勤表</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryBase/index.do">基本工资标准</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryPointStandard/index.do">薪点工资标准</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryInflationRate/index.do">薪点值</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/starsRule/index.do">服务星级评定标准</a>
			
			&emsp;&emsp;
			<input type="button" onclick="downloadDomain('salary-search-form','salary')" style="border-radius: 300px;"
				class="am-btn am-btn-success am-btn-xs" value="导出">
				
			<!-- 演示工资生成按钮 -->
			&emsp;&emsp;
			<input type="button" onclick="display()" style="border-radius: 300px;"
				class="am-btn am-btn-success am-btn-xs" value="生成工资明细">
</div>
<hr>

  <div id="content" style='padding: 8px'>
	<!-- 查询栏 -->
	<form id="salary-search-form" method="post">
		<input id="search-employee-id" type="hidden" name="employeeId" value="${employeeId}">
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1" 
					style="text-align: center;" display="display">时间段：</div>
			<div id="date-btn-group" class="am-u-sm-5 am-u-end am-button-inline">
		        <input type="button" class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" value="本月">
		        &emsp; 
		        <input type="button" class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" value="3个月"> 
		        &emsp;
		        <input type="button" class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" value="6个月">
		        &emsp;
		        <input type="button" class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" value="1年">
	        </div>
		</div>
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
					style="text-align: center;">时间：</div> 
			<div class="am-input-group am-datepicker-date am-u-sm-3 am-u-end" data-am-datepicker="{format: 'yyyy-mm',viewMode:'months',minViewMode: 'months'}">
				  <input type="text" name="searchTimeStr" class="am-form-field" placeholder="月份" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
			</div>
		</div>
		
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">关键词：</div>
			
			<div class="am-u-sm-5 am-u-end">
				<input name="keywords" type="text" class="am-form-field" placeholder="员工账号、姓名、职位">
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

	<!-- 员工数据表格 -->
	<table id="salary-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
  </div>
  
   <script type="text/javascript">
	
	var searchForm = $('#salary-search-form');
	var datagrid = $('#salary-datagrid');
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	// 日期查询按钮组
	var dateBtnGroup = $('#date-btn-group');
	
	var searchEmployeeId = $("#search-employee-id");
	
	if(searchEmployeeId.val()){
		  var paramObj = searchForm.serializeJson();
		  datagrid.datagrid({
			  url:_ctx + "/salary/list.do",
			  queryParams:paramObj
		  });
	  	}else {
		//请求地址
		  datagrid.datagrid({
			  url:_ctx + "/salary/list.do"
		  });
  	}
	
	// 生成工资明细，测试用
	function display(){
		$.post(_ctx+'/salaryDisplay/run.do',function(data){
			if(data){
				msgShow('系统提示',data.msg,'info');
				location.href = _ctx + "/salary/index.do";
			}
		});
	}
	
	$(function() {
		//日期按钮组
		var dateBtn = dateBtnGroup.children();
		
		//查询对象
		var paramObj = searchForm.serializeJson();
		//缓存当前查询
		var beginTimeStrVal = paramObj.beginTimeStr;
		var endTimeStrVal = paramObj.endTimeStr;
		
		//获取当前日期(拼成想要的格式yyyy-MM)
		function getDate(dateIndex){
			var dates = [];
			//日期对象
			var mydate = new Date();
			//当前年
			var year = mydate.getFullYear();
			//当前月
			var month = mydate.getMonth()+1;
			var monthNow = '';
			if(month < 10){
				monthNow = "0"+""+month;
			}else{
				monthNow = month;
			}
			var nowDate = year + "-" + monthNow;
			
			//处理4种按钮
			switch(dateIndex){
				case 0:
					//当前月份，日期就是上面计算出来的日期
					dates[0] = nowDate;
					break;
				case 1:
					if(month-3<0){
						//需要计算上一年的最后？个月
						var lastMonth = 3 - month;
						//则是从去年的几月份开始统计
						year = year - 1;
						month = 12 - lastMonth;
					}else{
						month = month-3;
					}
					
					if(month < 10){
						month = "0"+""+month;
					}else{
						month = month;
					}
					
					dates[0] = year+ '-' + month;
					break;
				case 2:
					if(month-6<0){
						//需要计算上一年的最后？个月
						var lastMonth = 6 - month;
						//则是从去年的几月份开始统计
						year = year - 1;
						month = 12 - lastMonth;
					}else{
						month = month-6;
					}
					
					if(month < 10){
						month = "0"+""+month;
					}else{
						month = month;
					}
					dates[0] = year+ '-' + month;
					break;
				case 3:
					year = year - 1;
					if(month < 10){
						month = "0"+""+month;
					}else{
						month = month;
					}
					dates[0] = year+ '-' + month;
					break;
					
			}
			dates[1] = nowDate;
			return dates;
		}
		
		//时间按钮组
		dateBtn.each(function(i, btn){
			var dateIndex = i;
			$(btn).click(function(){
				dateBtn.removeClass('am-btn-success');
				dateBtn.addClass('am-btn-default');
				$(this).addClass('am-btn-success');
				paramObj.beginTimeStr = getDate(dateIndex)[0];
				paramObj.endTimeStr = getDate(dateIndex)[1];
				beginTimeStrVal = getDate(dateIndex)[0];
				endTimeStrVal = getDate(dateIndex)[1];
				// 发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
		});
		
		//表单清空
		$("#resetSearchForm").on("click", function() {
			dateBtn.removeClass('am-btn-success');
			dateBtn.addClass('am-btn-default');
			searchForm.form("clear");
		});
		
		// 设置搜索按钮的点击事件
		searchConfirm.click(function(){
			paramObj = searchForm.serializeJson();
			
			// 发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
		
	})
	
  </script>
  
</body>
</html>