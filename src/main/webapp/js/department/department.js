/**
 * 部门界面的js
 */

$(function() {
	//1、声明页面需要使用的组件
	var departmentGrid,departmentSearchForm;
	//2、缓存组件
	departmentGrid = $("#department-datagrid");
	departmentSearchForm = $("#department-search-form");
	//3、初始化组件
	departmentGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/department/list.do",
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
			        {field:'name',title:'部门名称',width:80,align:'center'},    
			        {field:'intro',title:'部门描述',width:80,align:'center'},
			        {field:'createTime',title:'创建时间',width:80,align:'center'},
			        {field:'xxx',title:'操作',width:80,align:'center',formatter:function(v,r,i){
			        	return "<a href="+ _ctx + "/department/index.do?cmd=update&id="+ r.id +">编辑</a>";
			        }}
			    ]],
			}
		);

	//4、创建一个命令对象（打包操作函数）
	var cmdObj = {
		refresh : function() {
			departmentGrid.datagrid("reload");
		},
		doSearch : function() {
			// 把表单条件转变为json对象
			var paramObj = departmentSearchForm.serializeJson();
			// 使用查询条件，过滤数据
			departmentGrid.datagrid("load", paramObj);
		},
		resetSearchForm : function() {
			departmentSearchForm.form("clear");
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