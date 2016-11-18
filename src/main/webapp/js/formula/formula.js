/**
 * 员工绩效的js
 */

$(function() {
	// 1、声明页面需要使用的组件
	var formulaGrid, formulaSearchForm;
	// 2、缓存组件
	formulaGrid = $("#formula-datagrid");
	formulaSearchForm = $("#formula-search-form");
	// 3、初始化组件
	formulaGrid.datagrid({
		// 请求地址
		url : _ctx + "/formula/list.do",
		// 大小自适应
		fit : false,
		// 无边框
		border : false,
		// 内容适应列的大小
		fitColumns : true,
		// 不自动高度
		autoRowHeight : true,
		// 只显示一行
		nowrap : true,
		loadMsg : '数据正在加载中，请稍等',
		// 显示分页条
		pagination : true,
		// 测试的时候显示行号
		rownumbers : true,
		// 单选
		singleSelect : true,
		// 滚动条宽度
		scrollbarSize : 20,
		pageList:[10,15],
		//是否可以多列排序
	    multiSort:true,
		// 表头属性
		columns : [ [
				{
					field : 'dept',
					title : '部门',
					width : 10,
					align : 'center',
					formatter : function(v, r, i) {
						return r.jobTitle.dept.name?r.jobTitle.dept.name:"";
					}
				},
				{
					field : 'jobTitle',
					title : '职位',
					width : 20,
					align : 'center',
					formatter : objectFormatter
				},
				{
					field : 'pointCondition',
					title : '员工分数',
					width : 10,
					align : 'center',
					formatter : function(v, r, i) {
						return v?"满" + v + "分" : "";
					}
				},
				{
					field : 'bouns',
					title : '奖金',
					width : 10,
					align : 'center',
					sortable:true,
					order:'asc'
				},
				{
					field : 'createTime',
					title : '创建时间',
					width : 10,
					align : 'center'
				},
				{
					field : 'xxx',
					title : '操作',
					width : 20,
					align : 'center',
					formatter : function(v, r, i) {
						return "<a href=" + _ctx
								+ "/formula/index.do?cmd=update&id=" + r.id
								+ ">编辑</a>";
					}
				}, ] ],
			
	});

});