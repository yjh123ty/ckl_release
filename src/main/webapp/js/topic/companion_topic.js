/**
 * 路线界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 路线表格
	var topicDatagrid = $("#topic-datagrid");
	
	// 搜索表单
	var searchForm = $("#topic-search-form");
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
	// 搜索按钮
	var doSearch = $("#do-search");
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/topic/listCompanionTopic.do";
	
	var deleteUrl = _ctx + "/topic/deleteCompanionTopic.do";
	
	var topUrl = _ctx + "/topic/topCompanionTopic.do";
	
	var deleteTopic = function(id){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	$.post(deleteUrl,{id:id},function(data){
					if(isJsonObj(data)){
						msgShow("温馨提示", data.msg, "info");
						if(data.success){
							topicDatagrid.datagrid("reload");
						}
					}else {
						msgShow("温馨提示", "服务器异常", "warning");
					}
				}, "json");   
		    }    
		});  
	};
	
	var topTopic = function(id){
		$.post(topUrl,{id:id},function(data){
			if(isJsonObj(data)){
				msgShow("温馨提示", data.msg, "info");
				if(data.success){
					topicDatagrid.datagrid("reload");
				}
			}else {
				msgShow("温馨提示", "服务器异常", "warning");
			}
		}, "json"); 
	};
	
	// 初始化 数据表格
	topicDatagrid.datagrid(
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
		    nowrap:false,
		    loadMsg:'数据正在加载中，请稍等哒~',
		    //显示分页条
		    pagination:true,
		    //测试的时候显示行号
		    rownumbers:true,
		    //单选
		    singleSelect:true,
		    pageList:[10,15],
		    //滚动条宽度
		    scrollbarSize:20,
		    // 表头属性
		    columns:[[    
		        {field:'x1',title:'发帖人账号',width:10,align:'center',formatter:function(v,r,i){
		        	var s = r ?  r['user'] : '';
		        	return s ? "<a href="+ _ctx + "/user/index.do?cmd=center&id="+ s.id +" target=_blank>"+s.mobile+"</a>" :'';
		        }},    
		        {field:'x2',title:'发帖人昵称',width:10,align:'center',formatter:function(v,r,i){
		        	var s = r ?  r['user'] : '';
	        		return s ? s.nickName:'';
		        }},
		        {field:'title',title:'标题',width:10,align:'center'},
		        {field:'content',title:'内容',width:10,align:'center'},
		        {field:'startTime',title:'出发时间',width:10,align:'center'},
		        {field:'endTime',title:'结束时间',width:10,align:'center'},
		        {field:'route',title:'路线名称',width:10,align:'center',formatter:objectFormatter},
		        {field:'createTime',title:'创建时间',width:10,align:'center'},
		        {field:'null',title:'操作',width:10,align:'left',formatter:function(v,r,i){
		        	return "<a href='javascript:void(0);'  class='topic-delete' data-index='"+ i +"' style='color:red'>删除</a> <a href='javascript:void(0);'  class='topic-top' data-index='"+ i +"' style='color:pink;'>置顶</a>";
		        }}
		        
		    ]],
		    onLoadSuccess:function(){
		    	$(".topic-delete").click(function(){
		    		var index = $(this).data("index");
			    	var row = topicDatagrid.datagrid("getRows")[index];
		    		deleteTopic(row.id);
		    	});
		    	$(".topic-top").click(function(){
		    		var index = $(this).data("index");
			    	var row = topicDatagrid.datagrid("getRows")[index];
		    		topTopic(row.id);
		    	});
		    }
		}
	);
	// 搜索操作
	var search = function(){
		// TODO 验证表单参数
		// 获取搜索表单 
		// 封装表单参数
		var paramObj = searchForm.serializeJson();
		// 发送post请求刷新 datagrid 的数据
		topicDatagrid.datagrid("load",paramObj);
	};
	// 设置搜索按钮的点击事件
	searchConfirm.click(search);
	doSearch.click(search);
	
	var searchUserId = $("#search-user-id");
	if(searchUserId.val()){
		  var paramObj = searchForm.serializeJson();
		  topicDatagrid.datagrid({
			  url:listUrl,
			  queryParams:paramObj
		  });
	  }else {
		//请求地址
		  topicDatagrid.datagrid({
			  url:listUrl
		  });
  }
});