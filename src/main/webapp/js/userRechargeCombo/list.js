/**
 * 商品界面的js
 */

$(function() {
	//2、缓存组件
	var userRechargeComboGrid = $("#userRechargeCombo-datagrid");
	var userRechargeComboSearchForm = $("#userRechargeCombo-search-form");
	var searchConfirm = $("#search-confirm");
	
	//3、初始化组件
	userRechargeComboGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/rechargeCombo/list.do",
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
			        {field:'name',title:'套餐名称',width:80,align:'center'},    
			        {field:'price',title:'套餐价格',width:80,align:'center'},
			        {field:'xxxxs',title:'返点比例',width:80,align:'center',formatter:function(){
			        	return "10%";
			        }},
			        {field:'xxxx',title:'发放时间',width:80,align:'center',formatter:function(){
			        	return "实时";
			        }},
			        {field:'xxxxxxx',title:'操作',width:80,align:'center',formatter:function(v,r,i){
			        	return "<a href='"+_ctx+"/rechargeCombo/index.do?cmd=update&id="+r.id+"'>编辑</a>&emsp;";
			        }}
			    ]]/*,
			    onLoadSuccess:function(){
			    	//删除一条数据
			    	$('.deleteRechargeCombo').click(function(data){
			    		var rowId = $(this).data("index");
			    		$.messager.confirm("温馨提示", "是否删除？", function(yes) {
							if (yes) {
								$.post(_ctx + "/rechargeCombo/delete.do", {"id" : rowId}, 
									function(data) {
										// 判断
										if (data.success) {
											// 提示
											$.messager.alert("温馨提示", data.msg, "info",
												function() {
													//刷新页面
													userRechargeComboGrid.datagrid("reload");
												});
										} else {
											$.messager.alert("温馨提示", data.msg, "info");
										}
								}, "json");
							}
				    	});
			    	});
			    	
			    }*/
			}
		);
	
	searchConfirm.click(function(){
		var paramObj = userRechargeComboSearchForm.serializeJson();
		userRechargeComboGrid.datagrid('load', paramObj);
	});
});