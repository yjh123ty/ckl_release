/**
 * 合作商家-饭店 界面的js
 */

// 改变商家的状态
var changeStatus = function(id, status){
	$.post(_ctx+"/restaurant/changeStatus.do", {
		id: id,
		status:status
		}, function(data){
		if(data.success) {
			$("#cooperator-rest-datagrid").datagrid("reload");
		}
	},"json");
};

$(function() {

	//1、声明页面需要使用的组件
	var cooperatorGrid, cooperatorSearchForm;
	//2、缓存组件
	cooperatorGrid = $("#cooperator-rest-datagrid");
	cooperatorSearchForm = $("#cooperator-rest-search-form");
	//3、初始化组件
	// 初始化 数据表格
	cooperatorGrid.datagrid(
		{
			//请求地址
		    url: _ctx + "/cooperatorRestaurant/list.do",
		    //大小自适应
		    fit:false,
		    //无边框
		    border:false,
		    //内容适应列的大小
		    fitColumns:true,
		    //不自动高度
		    autoRowHeight:true,
		    //只显示一行
		    nowrap:false,
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
		        {field:'xxx',title:'商家类型',width:10,align:'center',formatter:function(v,r,i){
		        	return "饭店";
		        }},    
		        {field:'name',title:'商家名称',width:10,align:'center'},
		        {field:'stars',title:'饭店星级',width:5,align:'center'},
		        {field:'fullAddress',title:'商家地址',width:20,align:'center',formatter:function(v,r,i){
		        	var result = null;
		        	if(r.fullAddress){
		        		result = r.fullAddress;
		        	}
		        	return result;
		        }},
		        {field:'mobile',title:'商家电话',width:10,align:'center'},
		        {field:'imgs',title:'饭店图片',width:0,align:'center',formatter:function(v,r,i){
		        	var picArry=JSON.stringify(r.imgs);
		        	return "<a href='javascript:void(0);' onclick='scanPic(this)' src='"+picArry+"'>查看图片</a>";
		        }},
		        {field:'createTime',title:'创建日期',width:10,align:'center'},
		        {field:'status',title:'操作',width:10,align:'center',formatter:function(v,r,i){
		        	if(v == -1){
		        		return "<a href='"+_ctx+"/cooperatorRestaurant/index.do?cmd=update&id="+r.id+"'>编辑</a>--<a href='javascript:void(0);' onclick='changeStatus("+r.id+",0)' style='color:green;'>开通</a>";
		        	}else if(v == 0){
		        		return "<a href='"+_ctx+"/cooperatorRestaurant/index.do?cmd=update&id="+r.id+"'>编辑</a>--<a href='javascript:void(0);' onclick='changeStatus("+r.id+",-1)' style='color:red;'>关闭</a>";
		        	}else{
		        		return "<a href='"+_ctx+"/cooperatorRestaurant/index.do?cmd=update&id="+r.id+"'>编辑</a>";
		        	}
		        }}
		        ]],
		        onLoadSuccess:function(){
			    }
		}
	);
	
	//4、创建一个命令对象（打包操作函数）
	var cmdObj = {
		refresh : function() {
			cooperatorGrid.datagrid("reload");
		},
		doSearch : function() {
			// 把表单条件转变为json对象
			var paramObj = cooperatorSearchForm.serializeJson();
			// 使用查询条件，过滤数据
			cooperatorGrid.datagrid("load", paramObj);
		},
		resetSearchForm : function() {
			cooperatorSearchForm.form("clear");
		}
	};
	//5、对页面按钮做一个统一的监听
	$("a[data-cmd]").on("click", function() {
		// 获取事件源（按钮）上的cmd属性值
		var cmd = $(this).data("cmd");
		if (cmd) {// 如果存在
			// 调用命令对象的方法
			cmdObj[cmd]();
		}
	});
	
});



