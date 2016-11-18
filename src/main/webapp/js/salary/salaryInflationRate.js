/**
* 基础工资标准的js
*/

$(function() {
	//1、声明页面需要使用的组件
	var salaryInflationRateGrid,salaryInflationRateSearchForm;
	//2、缓存组件
	salaryInflationRateGrid = $("#salaryInflationRate-datagrid");
	salaryInflationRateSearchForm = $("#salaryInflationRate-search-form");
	//3、初始化组件
	salaryInflationRateGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/salaryInflationRate/list.do",
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
			        {field:'recordYear',title:'年份',width:10,align:'center'},
			        {field:'ratio',title:'薪点值',width:10,align:'center'},
			        {field:'xxx',title:'操作',width:10,align:'center',formatter:function(v,r,i){
			        	return "<a href="+ _ctx + "/salaryInflationRate/index.do?cmd=update&id="+ r.id +">编辑</a>";
			        }}
			    ]],
			}
		);

});