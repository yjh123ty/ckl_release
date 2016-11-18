/**
 * 角色界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 角色表格
	var empRoleDatagrid = $("#emp-role-datagrid");
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/admin/list.do";
	
	// 初始化 数据表格
	empRoleDatagrid.datagrid(
		{
			//请求地址
		    url:listUrl,
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
		    loadMsg:'数据正在加载中，请稍等哒~',
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
		        {field:'realName',title:'用户名',width:80,align:'center'},    
		        {field:'role',title:'角色',width:80,align:'center',formatter:function(v,r,i){
		        	return v && v.name ? v.name : '';
		        }},
		        {field:'status',title:'状态',width:160,align:'center',formatter:function(v,r,i){
		        	if(v == -1) {
		        		return "<font color=\"red\">已禁用</font>";
		        	}
		        	if(v == 0) {
		        		return "<font color=\"#1FBAA8\">已启用</font>";
		        	}else {
		        		return "未知";
		        	}
		        }},
		        {field:'createTime',title:'创建时间',width:80,align:'center'},
		        {field:'xxx',title:'操作',width:80,align:'center',formatter:function(v,r,i){
		        	return "<a href="+ _ctx + "/role/index.do?cmd=grantUpdate&id="+ r.id +">编辑</a>";
		        }}
		    ]],
		}
	);
});