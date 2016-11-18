/**
 * 获得评分产生分数规则页面的js
 */

$(function() {
	//1、声明页面需要使用的组件
	var starsRuleGrid,starsRuleSearchForm;
	//2、缓存组件
	starsRuleGrid = $("#starsRule-datagrid");
	starsRuleSearchForm = $("#starsRule-search-form");
	//3、初始化组件
	starsRuleGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/starsRule/list.do",
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
			        {field:'star',title:'满足星数档次',width:80,align:'center',formatter:function(v,r,i){
			        	return  "满"+v+"星";
			        }},	    
			        {field:'starRatio',title:'获得绩效工资比例',width:80,align:'center'},
			        {field:'create_Time',title:'创建时间',width:80,align:'center',formatter:function(v,r,i){
			        	return r.createTime?r.createTime:"";
			        }},
			        {field:'xxx',title:'操作',width:80,align:'center',formatter:function(v,r,i){
			        	return "<a href="+ _ctx + "/starsRule/index.do?cmd=update&id="+ r.id +">编辑</a>";
			        }}
			    ]],
			}
		);

	//4、创建一个命令对象（打包操作函数）
	var cmdObj = {
		refresh : function() {
			starsRuleGrid.datagrid("reload");
		},
		doSearch : function() {
			// 把表单条件转变为json对象
			var paramObj = starsRuleSearchForm.serializeJson();
			// 使用查询条件，过滤数据
			starsRuleGrid.datagrid("load", paramObj);
		},
		resetSearchForm : function() {
			starsRuleSearchForm.form("clear");
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