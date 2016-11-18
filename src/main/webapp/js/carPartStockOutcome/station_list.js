/**
 * 商品界面的js
 */

$(function() {
	//2、缓存组件
	var carPartStockOutcomeGrid = $("#carPartStockOutcome-datagrid");
	var carPartStockOutcomeSearchForm = $("#carPartStockOutcome-search-form");
	var searchConfirm = $("#search-confirm");
	var stationSelect = $("#station-select");
	
	stationSelect.on("change",function(){
		var paramObj = carPartStockOutcomeSearchForm.serializeJson();
		carPartStockOutcomeGrid.datagrid('load', paramObj);
	});
	
	//3、初始化组件
	carPartStockOutcomeGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/carPartStockOutcome/list.do?centre=false",
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
					{field:'ssss',title:'服务站',width:80,align:'center', formatter:function(v,r,i){
						return r.depot && r.depot.station && r.depot.station.name ? r.depot.station.name : '';
					}}, 
			        {field:'depot',title:'仓库',width:80,align:'center', formatter:function(v,r,i){
			        	return v && v.name ? v.name : '';
			        }},    
			        {field:'carPart',title:'零部件',width:80,align:'center',formatter:function(v,r,i){
			        	return v && v.name ? v.name : '';
			        }},
			        {field:'outPrice',title:'出库价格',width:80,align:'center'},
			        {field:'outcomeDate',title:'出库时间',width:80,align:'center'},
			        {field:'outcomeUser',title:'出库员工',width:80,align:'center',formatter:function(v,r,i){
			        	return v && v.realName ? v.realName : '';
			        }},
			        {field:'outcomeType',title:'出库类型',width:80,align:'center',formatter:function(v,r,i){
			        	if(v == 1) {
			        		return '销售出库';
			        	}
			        	if(v==2){
			        		return '维修出库';
			        	}
			        }},
			        {field:'xxx',title:'操作',width:80,align:'center',formatter:function(v,r,i){
			        	return  "<a href='#' class='deleteCarPartStockOutcome' data-index='"+ r.id +"')><font color='red'>删除</font></a>";
			        }}
			    ]],
			    onLoadSuccess:function(){
			    	//删除一条数据
			    	$('.deleteCarPartStockOutcome').click(function(){
			    		var rowId = $(this).data("index");
			    		$.messager.confirm("温馨提示", "是否删除？", function(yes) {
							if (yes) {
								$.post(_ctx + "/carPartStockOutcome/delete.do", {"id" : rowId}, 
									function(data) {
										// 判断
										if (data.success) {
											// 提示
											$.messager.alert("温馨提示", data.msg, "info",
												function() {
													//刷新页面
													carPartStockOutcomeGrid.datagrid("reload");
												});
										} else {
											$.messager.alert("温馨提示", data.msg, "info");
										}
								}, "json");
							}
				    	});
			    	});
			    	
			    }
			}
		);
	
	searchConfirm.click(function(){
		var paramObj = carPartStockOutcomeSearchForm.serializeJson();
		carPartStockOutcomeGrid.datagrid('load', paramObj);
	});
});