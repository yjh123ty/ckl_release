/**
 * 用户充值报表界面的js
 */

$(function() {
	//1、声明页面需要使用的组件
	var userBillRechargeGrid, userBillRechargeSearchForm;
	//2、缓存组件
	userBillRechargeGrid = $("#userBillRecharge-datagrid");
	userBillRechargeSearchForm = $("#userBillRecharge-search-form");
	
	//3、初始化组件
	// 初始化 数据表格
	userBillRechargeGrid.datagrid(
		{
			//请求地址
		    url: _ctx + "/userBill/userBillRechargeList.do",
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
		        {field:'umobile',title:'用户账号',width:80,align:'center',formatter:function(v,r,i){
		        	return "<a href="+ _ctx + "/user/index.do?cmd=center&id="+ r.uid +" target=_blank>"+v+"</a>";
		        }},    
		        {field:'uname',title:'用户昵称',width:80,align:'center'},
		        {field:'total',title:'充值总金额(元)',width:80,align:'center',formatter:function(v,r,i){
		        	return "<a href="+ _ctx + "/userBill/index.do?cmd=rechargeDetail&uid="+ r.uid +">"+v+"</a>";
		        },sortable:true,order:'asc'}
		    ]],
		}
	);
	
	//表单清空
	$("#resetSearchForm").on("click", function() {
		searchForm.form("clear");
	});
	
});