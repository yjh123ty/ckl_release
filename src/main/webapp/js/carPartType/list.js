/**
 * 商品界面的js
 */

$(function() {
	//2、缓存组件
	var carPartTypeGrid = $("#carPartType-datagrid");
	var carPartTypeSearchForm = $("#carPartType-search-form");
	var searchConfirm = $("#search-confirm");
	
	//3、初始化组件
	carPartTypeGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/carPartType/list.do",
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
			        {field:'name',title:'类别名称',width:80,align:'center'},    
			        {field:'order',title:'排序',width:80,align:'center'},
			        {field:'intro',title:'描述',width:80,align:'center'},
			        {field:'createTime',title:'创建日期',width:80,align:'center'},
			        {field:'xxx',title:'操作',width:80,align:'center',formatter:function(v,r,i){
			        	return "<a href="+ _ctx + "/carPartType/index.do?cmd=update&id="+ r.id +">编辑</a>" + "--" + "<a href='#' class='deleteCarPartType' data-index='"+ r.id +"')><font color='red'>删除</font></a>";
			        }}
			    ]],
			    onLoadSuccess:function(){
			    	//删除一条数据
			    	$('.deleteCarPartType').click(function(data){
			    		var rowId = $(this).data("index");
			    		$.messager.confirm("温馨提示", "是否删除？", function(yes) {
							if (yes) {
								$.post(_ctx + "/carPartType/delete.do", {"id" : rowId}, 
									function(data) {
										// 判断
										if (data.success) {
											// 提示
											$.messager.alert("温馨提示", data.msg, "info",
												function() {
													//刷新页面
													carPartTypeGrid.datagrid("reload");
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
		var paramObj = carPartTypeSearchForm.serializeJson();
		carPartTypeGrid.datagrid('load', paramObj);
	});
});