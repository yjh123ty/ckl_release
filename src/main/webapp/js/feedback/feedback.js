/**
 * 用户反馈的js
 */

//处理
function changeStatus(rowId,flag){	//行id,是否冻结（0 未处理； 1已经处理）
	
	//若为未处理状态，则改为处理
	if(flag === 0){
		//发送请求，更改状态
		$.messager.confirm("温馨提示", "确认处理？", function(yes) {
			if (yes) {
				$.post(_ctx + "/feedback/handle.do", {
					"id" : rowId
				}, function(data) {
					// 判断
					if (data.success) {
						// 提示
						$.messager.alert("温馨提示", data.msg, "info",
							function() {
								//刷新页面
								$("#feedback-datagrid").datagrid("reload");
							});
					} else {
						$.messager.alert("温馨提示", data.msg, "info");
					}
				}, "json");
			}
		});
	}
}

//删除反馈
function deleteData(rowId){	
		//发送请求，删除数据
		$.messager.confirm("温馨提示", "确认删除？", function(yes) {
			if (yes) {
				$.post(_ctx + "/feedback/delete.do", {
					"id" : rowId
				}, function(data) {
					// 判断
					if (data.success) {
						// 提示
						$.messager.alert("温馨提示", data.msg, "info",
							function() {
								//刷新页面
								$("#feedback-datagrid").datagrid("reload");
							});
					} else {
						$.messager.alert("温馨提示", data.msg, "info");
					}
				}, "json");
			}
		});
}

//页面每一行数据的操作栏
function statusFormatter(value,row,index){
	var str = "";
	switch (value) {
	//如果状态为1，则显示处理时间
	case 1:
		str = row.handleTime;
		str += "&emsp;&emsp;&emsp;<button onclick='deleteData("+row.id+")' style='color:red'>删除</button>";
		break;
	//如果状态为0，则为待处理
	case 0:
		str = "<button onclick='changeStatus("+row.id+","+value+")' style='color:green'>处理完成</button>&emsp;&emsp;&emsp;";
		str += "<button onclick='deleteData("+row.id+")' style='color:red'>删除</button>";
		break;
	}
	return str;
	
}

$(function() {
	//1、声明页面需要使用的组件
	var feedbackGrid, feedbackSearchForm;
	//2、缓存组件
	feedbackGrid = $("#feedback-datagrid");
	feedbackSearchForm = $("#feedback-search-form");
	
	//3、初始化组件
	// 初始化 数据表格
	feedbackGrid.datagrid(
		{
			//请求地址
		    url: _ctx+"/feedback/list.do",
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
		    //是否可以多列排序
		    multiSort:true,
		    // 表头属性
		    columns:[[    
		        {field:'user',title:'用户账号',width:80,align:'center'
		        	,formatter:function(v,r,i){
		        		if(r.user){
		        			return "<a href="+ _ctx + "/user/index.do?cmd=center&id="+ r.user.id +" target=_blank>"+v.mobile+"</a>";
		        		}else{
		        			return "";
		        		}
		        	}
		        },  
		        {field:'null',title:'昵称',width:80,align:'center',formatter:function(v,r,i){
		        	if(r.user){
		        		return r.user.nickName;
		        	}
		        }},
		        {field:'content',title:'反馈内容',width:80,align:'center'},
		        {field:'create_time',title:'提交日期',width:80,align:'center',formatter:function(v,r,i){
		        	return r.createTime?r.createTime : "";
		        },sortable:true,order:'asc'},
		        {field:'status',title:'操作',width:80,align:'center',formatter:statusFormatter}
		    ]],
		}
	);
		var searchUserId = $("#search-user-id");
	  if(searchUserId.val()){
		  var paramObj = feedbackSearchForm.serializeJson();
		  feedbackGrid.datagrid({
			  url:_ctx + "/feedback/list.do",
			  queryParams:paramObj
		  });
	  }else {
		//请求地址
		  feedbackGrid.datagrid({
			  url:_ctx + "/feedback/list.do"
		  });
	  }
	
});