/**
 * 商品界面的js
 */

$(function() {
	//2、缓存组件
	var userRechargeComboGrid = $("#userDistribution-datagrid");
	var data = [
	            	{"point":2000,"ratio":0.02},
	            	{"point":4000,"ratio":0.04},
	            	{"point":6000,"ratio":0.06},
	            	{"point":8000,"ratio":0.08}
	            ];
	
	//3、初始化组件
	userRechargeComboGrid.datagrid(
			{
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
			    pagination:false,
			    //测试的时候显示行号
			    rownumbers:true,
			    //单选
			    singleSelect:true,
			    //滚动条宽度
			    scrollbarSize:20,
			    // 表头属性
			    columns:[[    
			        {field:'point',title:'当月累计点数',width:80,align:'center'},    
			        {field:'ratio',title:'返点比例',width:80,align:'center',formatter:function(v,r,i){
			        	return v && v*100 ? v*100:'';
			        }}
			        ]],
			    data:{
			    	"total":data.length,
			    	"rows":data
			    }
			}
		);
});