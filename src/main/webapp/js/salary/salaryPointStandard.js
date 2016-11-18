/**
* 基础工资标准的js
*/

$(function() {
	//1、声明页面需要使用的组件
	var salaryPointStandardGrid,salaryPointStandardSearchForm;
	//2、缓存组件
	salaryPointStandardGrid = $("#salaryPointStandard-datagrid");
	salaryPointStandardSearchForm = $("#salaryPointStandard-search-form");
	//3、初始化组件
	salaryPointStandardGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/salaryPointStandard/list.do",
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
			        {field:'jobTitle',title:'岗位类别',width:10,align:'center',formatter:objectFormatter},
			        {field:'salaryLevel',title:'薪点级别',width:10,align:'center'},
			        {field:'salary',title:'薪点工资',width:10,align:'center'}
			    ]],
			}
		);

});