/**
* 考勤的js
*/

$(function() {
	//1、声明页面需要使用的组件
	var employeeAttendanceGrid,employeeAttendanceSearchForm;
	//2、缓存组件
	employeeAttendanceGrid = $("#employeeAttendance-datagrid");
	employeeAttendanceSearchForm = $("#employeeAttendance-search-form");
	
	//3、初始化组件
	employeeAttendanceGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/employeeAttendance/list.do",
			    //大小自适应
			    fit:false,
			    //无边框
			    border:false,
			    //内容适应列的大小
			    fitColumns:true,
			    //不自动高度
			    autoRowHeight:false,
			    //只显示一行
			    nowrap:true,
			    loadMsg:'数据正在加载中，请稍等',
			    //显示分页条
			    pagination:true,
			    //测试的时候显示行号
			    rownumbers:true,
			    //单选
			    singleSelect:true,
			    //滚动条宽度
			    scrollbarSize:20,
			    pageList:[10,15],
			    // 表头属性
			    columns:[[    
	              	{field:'empNumber',title:'员工工号',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.employee){
			        		return r.employee.empNumber?r.employee.empNumber:'';
			        	}
			        	return null;
			        }},
			        {field:'employee',title:'员工姓名',width:10,align:'center',formatter:objectFormatter},
			        {field:'intro',title:'打卡备注',width:10,align:'center'},
			        {field:'createTime',title:'打卡记录',width:10,align:'center',formatter:function(v,r,i){
			        	return r.createTime?r.createTime : "";
			        }}
			    ]]
			}
		);
	
	var searchEmployeeId = $("#search-employee-id");
	var searchRecordMonth = $("#search-record-month");
	if(searchRecordMonth.val() || searchEmployeeId.val()){
		  var paramObj = employeeAttendanceSearchForm.serializeJson();
		  employeeAttendanceGrid.datagrid({
			  url:_ctx + "/employeeAttendance/list.do",
			  queryParams:paramObj
		  });
	  }else {
		//请求地址
		  employeeAttendanceGrid.datagrid({
			  url:_ctx + "/employeeAttendance/list.do"
		  });
	  }
	

});