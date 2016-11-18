/**
 * 商品界面的js
 */

$(function() {
	//2、缓存组件
	var userCommissionGrid = $("#userCommission-datagrid");
	var userCommissionSearchForm = $("#userCommission-search-form");
	var searchConfirm = $("#search-confirm");
	var searchCancel = $("#search-cancel");
	
	searchCancel.click(function(){
		$(this).closest("form").find("input").val('');
	});
	
	
	//3、初始化组件
	userCommissionGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/userDistribution/listTotalCommission.do",
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
			        {field:'userMobile',title:'用户账号',width:80,align:'center'},    
			        {field:'userNickName',title:'用户昵称',width:80,align:'center'},
			        {field:'totalCommisssion',title:'总佣金',width:80,align:'center',formatter:function(v,r,i){
			        	return v ? "<a href='"+_ctx+"/userDistribution/index.do?cmd=listDetail&userId="+r.userId+"'>"+v+"</a>":0;
			        }}
			    ]]
			}
		);
	
	searchConfirm.click(function(){
		var paramObj = userCommissionSearchForm.serializeJson();
		userCommissionGrid.datagrid('load', paramObj);
	});
});