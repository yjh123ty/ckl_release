/**
 * 角色界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 角色表格
	var roleDatagrid = $("#role-datagrid");
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/role/list.do";
	
	// 初始化 数据表格
	roleDatagrid.datagrid(
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
		        {field:'name',title:'名称',width:80,align:'center'},    
		        {field:'menus',title:'权限',width:80,align:'center',formatter:function(v,r,i){
		        	if(v.length > 0){
		        		var p = '';
		        		for (var i = 0; i < v.length; i++) {
							p += v[i].name + "，";
						}
		        		p = p.substring(0, p.length -1);
		        		return p;
		        	}else {
		        		return "没有分配权限";
		        	}
		        }},
		        {field:'intro',title:'描述',width:160,align:'left'},
		        {field:'createTime',title:'创建时间',width:80,align:'center'},
		        {field:'status',title:'操作',width:80,align:'center',formatter:function(v,r,i){
		        	return "<a href="+ _ctx + "/role/index.do?cmd=update&id="+ r.id +">编辑</a>";
		        }}
		    ]],
		}
	);
});