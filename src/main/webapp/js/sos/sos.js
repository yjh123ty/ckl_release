/**
* sos的js
*/


$(function() {
	//1、声明页面需要使用的组件
	var datagrid,searchForm;
	//2、缓存组件
	datagrid = $("#sos-datagrid");
	searchForm = $("#sos-search-form");
	
	//3、初始化组件
	datagrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/sos/list.do",
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
	              	{field:'userMobile',title:'求助用户电话',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.user){
			        		return r.user.mobile?r.user.mobile:'';
			        	}
			        	return null;
			        }},
			        {field:'userName',title:'求助用户姓名',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.user){
			        		return r.user.nickName?r.user.nickName:'';
			        	}
			        	return null;
			        }},
			        {field:'sex',title:'求助用户性别',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.user){
			        		if(r.user.sex == 0){
			        			return "女";
			        		}else if(r.user.sex == 1){
			        			return "男";
			        		}
			        	}
			        	return null;
			        }},
			        {field:'empMobile',title:'处理人员工号',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.employee){
			        		return r.employee.empNumber?r.employee.empNumber:'';
			        	}
			        	return null;
			        }},
			        {field:'empName',title:'处理人员姓名',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.employee){
			        		return r.employee.realName?r.employee.realName:'';
			        	}
			        	return null;
			        }},
			        {field:'isHandle',title:'是否处理',width:10,align:'center',formatter:function(v,r,i){
			        	console.debug(r.isHandle);
		        		if(r.isHandle){
			        		return "已处理";
			        	}else{
			        		return "待处理";
			        	}
			        }},
			        {field:'createTime',title:'求助时间',width:10,align:'center',formatter:function(v,r,i){
			        	return r.createTime?r.createTime : "";
			        }},
			        {field:'modifyTime',title:'处理时间',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.isHandle){
			        		return r.modifyTime?r.modifyTime : "";
			        	}else{
			        		return "<a class='handle' href='#' data-index='"+r.id+"'>处理</a>";
			        	}
			        }}
			    ]],
			    onLoadSuccess:function(){
			    	$("a.handle").click(function(data){
			    		//获得要更改的id
			    		var rowId = $(this).data("index");
			    		
		    				$.post(_ctx + "/sos/handleById.do", {"id" : rowId}, 
	    						function(data) {
			    					// 判断
			    					if (data.success) {
			    						// 提示
			    						$.messager.alert("温馨提示", data.msg, "info",
			    							function() {
			    								//刷新页面
			    							datagrid.datagrid("reload");
			    							});
			    					} else {
			    						$.messager.alert("温馨提示", data.msg, "info");
			    					}
			    				}, "json");
			    	});
			    }
			}
		);
	

});