/**
* 员工工资表的js
*/

$(function() {
	//1、声明页面需要使用的组件
	var salaryGrid,salarySearchForm;
	//2、缓存组件
	salaryGrid = $("#salary-datagrid");
	salarySearchForm = $("#salary-search-form");
	
	//3、初始化组件
	salaryGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/salary/list.do",
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
			        {field:'null',title:'员工账号',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.employee){
			        		return "<a href="+ _ctx + "/employee/index.do?cmd=center&id="+ r.employee.id +"  target=_blank>"+r.employee.mobile+"</a>";
			        	}
			        	return null;
			        }},
			        {field:'employee',title:'员工姓名',width:10,align:'center',formatter:objectFormatter},
//			        {field:'dept',title:'所在部门',width:10,align:'center',formatter:function(v,r,i){
//			        	if(r.employee){
//			        		return r.employee.dept?r.employee.dept.name:'';
//			        	}
//			        	return null;
//			        }},
			        {field:'jobTitle',title:'岗位类别',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.employee){
			        		return r.employee.jobTitle?r.employee.jobTitle.name:'';
			        	}
			        	return null;
			        }},
			        {field:'salaryLevel',title:'薪点级别',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.employee){
			        		return r.employee.salaryLevel?r.employee.salaryLevel:'';
			        	}
			        	return null;
			        }},
			        {field:'baseSalary',title:'基本工资（元）',width:10,align:'center'},
			        {field:'pointSalary',title:'薪点工资（元）',width:10,align:'center'},
			        {field:'performanceSalary',title:'绩效工资（元）',width:10,align:'center'},
			        {field:'allowance',title:'津贴（元）',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.realSalary){
			        		return r.allowance;
			        	}else if(r.realSalary == null){
			        		return null;
			        	}
			        }},
			        {field:'bouns',title:'奖金(元)',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.realSalary){
			        		return r.bouns;
			        	}else if(r.realSalary == null){
			        		return null;
			        	}
			        },sortable:true,order:'asc'},
			        {field:'deductSalary',title:'扣减工资（元）',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.realSalary){
			        		return r.deductSalary;
			        	}else if(r.realSalary == null){
			        		return null;
			        	}
			        }},
			        {field:'real_salary',title:'应发工资(元)',width:10,align:'center',formatter:function(v,r,i){
			        	var realSalary = r.baseSalary + r.pointSalary + r.performanceSalary + r.allowance + r.bouns - r.deductSalary;
			        	return r.realSalary = realSalary.toFixed(2);
			        },sortable:true,order:'asc'},
			        {field:'record_month',title:'发放月份',width:10,align:'center',formatter:function(v,r,i){
			        	return r.recordMonth?r.recordMonth : "";
			        },sortable:true,order:'asc'},
			        {field:'xxx',title:'操作',width:10,align:'center',formatter:function(v,r,i){
			        	return "<a href="+ _ctx + "/salary/index.do?cmd=update&id="+ r.id +">编辑</a>";
			        }}
			    ]],
			}
		);
	
	
});