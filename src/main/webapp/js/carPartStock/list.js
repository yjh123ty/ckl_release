/**
 * 商品界面的js
 */

$(function() {
	//2、缓存组件
	var carPartStockGrid = $("#carPartStock-datagrid");
	var carPartStockSearchForm = $("#carPartStock-search-form");
	var searchConfirm = $("#search-confirm");
	
	//3、初始化组件
	carPartStockGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/carPartStock/list.do",
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
			    pageList:[10,15],
			    // 表头属性
			    columns:[[    
			        {field:'depot',title:'仓库',width:80,align:'center',formatter:function(v,r,i){
			        	return v && v.name ? v.name :'';
			        }},    
			        {field:'carPart',title:'零部件',width:80,align:'center',formatter:function(v,r,i){
			        	return v && v.name ? v.name : '';
			        }},
			        {field:'num',title:'剩余数量',width:80,align:'center'}
			    ]]
			}
		);
	
	searchConfirm.click(function(){
		var paramObj = carPartStockSearchForm.serializeJson();
		carPartStockGrid.datagrid('load', paramObj);
	});
});