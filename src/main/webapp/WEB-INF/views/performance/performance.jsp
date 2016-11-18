<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工绩效管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/performance/performance.js"></script>

</head>
<body>

	<div class="am-g" style='margin: 1px; padding: 10px'>
			
			<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" href='${ctx}/performance/index.do'>员工绩效</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/formula/index.do">绩效公式</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salary/index.do">员工工资表</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/serviceRule/index.do">员工分数生成规则</a>
			
			&emsp;&emsp;
			<input type="button" onclick="downloadDomain('performance-search-form','performance')" style="border-radius: 300px;"
				class="am-btn am-btn-success am-btn-xs" value="导出">
			
	</div>
	<hr>
	
  <div id="content" style='margin: 10px; padding: 10px'>
	<!-- 查询栏 -->
	<form id="performance-search-form" method="post">
	
		<div class="am-g am-margin">
			<div class="am-u-sm-1" 
					style="text-align: center;" display="display">绩效时间段：</div>
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
			<input class="easyui-datetimespinner" name="searchTimeStr" 
			data-options="formatter:formatter2,parser:parser2,selections:[[0,4],[5,7]]" 
			style="width:180px;">
<!-- 			&emsp;<span style="color:gray;">|</span>&emsp; -->
		</div>
		
		<div class="am-g am-margin">
               <div class="am-u-sm-1"
					style="text-align: center;">所在部门：</div>
               <input name="deptId" class="easyui-combobox" 
               		data-options="
	               		url: '${ctx}/department/getDataTree.do',
	               		valueField:'id',
	               		textField:'name',
	               		panelHeight:'auto',
	               		" >
		</div>
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
				style="text-align: center;">关键词：</div>
			<input name="keywords" class="easyui-searchbox" 
				data-options="prompt:'员工账号或姓名、工号、服务站名称、职位'"
				style="width:260px" >
		</div>	
	</form>
	
	<div class="am-g am-margin">
			<div class=" am-u-sm-2 am-u-md-4" style="text-align: left;">
				<button 
					id="search-confirm" class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;" id="resetSearchForm">查&nbsp;询</button>&emsp;
				<button 
					class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;" type="reset" id="resetSearchForm">清&nbsp;空</button>
			</div>
	</div>

	<!-- 员工数据表格 -->
	<table id="performance-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
  </div>
  
  <script type="text/javascript">
  	//日期控件
  	function formatter2(date){
		if (!date){return '';}
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		return y + '-' + (m<10?('0'+m):m);
	}
	function parser2(s){
		if (!s){return null;}
		var ss = s.split('-');
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		if (!isNaN(y) && !isNaN(m)){
			return new Date(y,m-1,1);
		} else {
			return new Date();
		}
	}
	
	var searchForm = $('#performance-search-form');
	var datagrid = $('#performance-datagrid');
	
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	// 日期查询按钮组
	var dateBtnGroup = $('#date-btn-group');
	
	$(function() {
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
				
// 				console.debug(getDate(dateIndex));
// 				console.debug(getDate(dateIndex)[0],getDate(dateIndex)[1]);
				paramObj.beginTimeStr = getDate(dateIndex)[0];
				paramObj.endTimeStr = getDate(dateIndex)[1];
				beginTimeStrVal = getDate(dateIndex)[0];
				endTimeStrVal = getDate(dateIndex)[1];
// 				// 发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
		});
		
		//表单清空
		$("#resetSearchForm").on("click", function() {
			dateBtn.removeClass('am-btn-success');
			dateBtn.addClass('am-btn-default');
			searchForm.form("clear");
			recordMonthVal = null;
		});
		
		// 设置搜索按钮的点击事件
		searchConfirm.click(function(){
			paramObj = searchForm.serializeJson();
			
			paramObj.recordMonth = recordMonthVal;
			// 发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
	})

  </script>
  
</body>
</html>