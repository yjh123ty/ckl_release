/**
* 员工绩效的js
*/

$(function() {
	//1、声明页面需要使用的组件
	var performanceGrid,performanceSearchForm;
	//2、缓存组件
	performanceGrid = $("#performance-datagrid");
	performanceSearchForm = $("#performance-search-form");
	//3、初始化组件
	performanceGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/performance/list.do",
			    //大小自适应
			    fit:false,
			    //无边框
			    border:false,
			    //内容适应列的大小
			    fitColumns:true,
			    //不自动高度
			    autoRowHeight:true,
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
			    //是否可以多列排序
			    multiSort:true,
			    pageList:[10,15],
			    // 表头属性
			    columns:[[    
			        {field:'xxx',title:'排名',width:10,align:'center',formatter:function(v,r,i){
		        		return i+1;
			        }}, 
			        {field:'emp_number',title:'工号',width:15,align:'center',formatter:function(v,r,i){
			        	if(r.employee){
			        		return r.employee.empNumber;
			        	}else{
			        		return null;
			        	}
			        },sortable:true},
			        {field:'empMobile',title:'员工账号',width:15,align:'center',formatter:function(v,r,i){
			        	if(r.employee){
			        		return "<a href="+ _ctx + "/employee/index.do?cmd=center&id="+ r.employee.id +">"+r.employee.mobile+"</a>";
			        	}else{
			        		return null;
			        	}
			        }},
			        {field:'employee',title:'员工姓名',width:10,align:'center',formatter:objectFormatter},
			        {field:'did',title:'所在部门',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.employee.dept){
			        		return r.employee.dept.name;
			        	}else{
			        		return null;
			        	}
			        },sortable:true},
			        {field:'jid',title:'职位',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.employee.jobTitle){
			        		return r.employee.jobTitle.name;
			        	}else{
			        		return null;
			        	}
			        },sortable:true},
			        {field:'stid',title:'所属服务站',width:20,align:'center',formatter:function(v,r,i){
			        	if(r.employee.station){
			        		return r.employee.station.name;
			        	}else{
			        		return null;
			        	}
			        },sortable:true},
			        {field:'ke_in_month',title:'总刻数',width:10,align:'center',formatter:function(v,r,i){
			        	return r.keInMonth?r.keInMonth:"";
			        },sortable:true},
			        {field:'avg_star',title:'平均评分',width:10,align:'center',formatter:function(v,r,i){
			        	return r.avgStar?r.avgStar:"";
			        },sortable:true},
			        {field:'score',title:'分数',width:10,align:'center',formatter:function(v,r,i){
			        	return r.score?r.score:"";
			        },sortable:true},
			        {field:'record_month',title:'绩效月份',width:20,align:'center',formatter:function(v,r,i){
			        	return r.recordMonth?r.recordMonth:"";
			        },sortable:true}
			    ]],
			}
		);
	
});