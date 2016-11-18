/**
 * 用户界面的js
 */
function changeStatus(rowId,flag){	//行id,是否冻结（0 开通；-1 冻结）
	//若为开通状态，则改为禁用
	if(flag === 0){
		//发送请求，更改状态
		$.messager.confirm("温馨提示", "是否需要禁用？", function(yes) {
			if (yes) {
				$.post(_ctx + "/user/disable.do", {
					"id" : rowId
				}, function(data) {
					// 判断
					if (data.success) {
						// 提示
						$.messager.alert("温馨提示", data.msg, "info",
							function() {
								//刷新页面
								$("#user-datagrid").datagrid("reload");
							});
					} else {
						$.messager.alert("温馨提示", data.msg, "info");
					}
				}, "json");
			}
		});
	}
	
	//若为禁用状态，则改为开通
	if(flag === -1){
		//发送请求，更改状态
		$.messager.confirm("温馨提示", "是否需要开通？", function(yes) {
			if (yes) {
				$.post(_ctx + "/user/invoke.do", {
					"id" : rowId
				}, function(data) {
					// 判断
					if (data.success) {
						// 提示
						$.messager.alert("温馨提示", data.msg, "info",
							function() {
								//刷新页面
								$("#user-datagrid").datagrid("reload");
							});
					} else {
						$.messager.alert("温馨提示", data.msg, "info");
					}
				}, "json");
			}
		});
	}
	
}

//页面状态展示
function statusFormatter(value,row,index){
	switch (value) {
	case 0:
		return "<button onclick='changeStatus("+row.id+","+value+")' style='color:red'>冻结</button>";
		break;

	case -1:
		return "<button onclick='changeStatus("+row.id+","+value+")' style='color:green'>开通</button>";
		break;
	}
}

$(function() {
	//1、声明页面需要使用的组件
	var userGrid, userSearchForm;
	//2、缓存组件
	userGrid = $("#user-datagrid");
	userSearchForm = $("#user-search-form");
	var statusBtnGroup = $("#status-btn-group");
	
	//3、初始化组件
	// 初始化 数据表格
	userGrid.datagrid(
		{
			//请求地址
		    url: _ctx + "/user/list.do",
		    //大小自适应
		    fit:false,
		    //无边框
		    border:false,
		    //内容适应列的大小
		    fitColumns:true,
		    //不自动高度
		    autoRowHeight:false,
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
		        {field:'mobile',title:'用户账号',width:80,align:'center',formatter:function(v,r,i){
		        	return r ? "<a href="+ _ctx + "/user/index.do?cmd=center&id="+ r.id +" target=_blank>"+v+"</a>" : '';
		        }},    
		        {field:'nickName',title:'昵称',width:80,align:'center'},
		        {field:'createTime',title:'注册时间',width:80,align:'center'},
		        {field:'status',title:'操作',width:80,align:'center',formatter:statusFormatter}
		    ]],
		}
	);
	
});