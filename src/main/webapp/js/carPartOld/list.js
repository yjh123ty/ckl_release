/**
 * 商品界面的js
 */

$(function() {
	//2、缓存组件
	var carPartOldGrid = $("#carPartOld-datagrid");
	var carPartOldSearchForm = $("#carPartOld-search-form");
	var searchConfirm = $("#search-confirm");
	
	//3、初始化组件
	carPartOldGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/carPartStockIncome/listOld.do",
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
			        {field:'code',title:'商品条码',width:10,align:'center'},
					{field:'x1',title:'商品名称',width:10,align:'center',formatter:function(v,r,i){
						var carPart = r.carPart;
						return carPart && carPart.name ? carPart.name :'';
					}},
					{field:'depot',title:'仓库', width:10,align:'center',formatter:function(v,r,i){
						return v && v.name ? v.name : '';
					}},
					{field:'xxx',title:'服务站', width:10,align:'center',formatter:function(v,r,i){
						var stationName = '';
						try{
							stationName = r.depot.station.name;
						}catch (e) {
						}
						return stationName;
					}},
					{field:'x2',title:'老化月份',width:10,align:'center',formatter:function(v,r,i){
						var carPart = r.carPart;
						return carPart && carPart.oldMonths ? carPart.oldMonths : '';
					}},
					{field:'incomeDate',title:'入库时间日期', width:10,align:'center'},
					{field:'oldDate',title:'老化时间', width:10,align:'center'}
			    ]]
			}
		);
	
	searchConfirm.click(function(){
		var paramObj = carPartOldSearchForm.serializeJson();
		carPartOldGrid.datagrid('load', paramObj);
	});
});