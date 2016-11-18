/**
 * 商品入库（中央仓库）管理界面的js
 */

$(function() {
	//1、声明页面需要使用的组件
	var carPartInStockInfoGrid,carPartInStockInfoSearchForm;
	//2、缓存组件
	carPartInStockInfoGrid = $("#carPartInStockInfo-datagrid");
	carPartInStockInfoSearchForm = $("#carPartInStockInfo-search-form");
	//3、初始化组件
	carPartInStockInfoGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/carPartStockInfo/inStockList.do",
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
			    // 表头属性
			    columns:[[    
			        {field:'carPartName',title:'商品名称',width:80,align:'center'},    
			        {field:'typeName',title:'商品分类',width:80,align:'center'},
			        {field:'unit',title:'商品规格(个/箱)',width:80,align:'center'},
			        {field:'num',title:'商品数量(箱)',width:80,align:'center'},
			        {field:'lastTime',title:'最后入库时间',width:80,align:'center'}
			    ]],
			    onLoadSuccess:function(){
			    }
			}
		);

});