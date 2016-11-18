/**
 * 游记管理界面的js
 */
$(function() {

	// 列表url
	var listUrl = _ctx + "/usertravelnote/list.do";
	
	var deleteUrl = _ctx + "/usertravelnote/delete.do";

	// 添加、修改url
	var editUrl = _ctx + "/usertravelnote/edit.do";

	/**
	 * 缓存界面组件
	 */
	var datagrid = $("#travel-note-datagrid");
	var searchUserId = $("#search-user-id");

	// 搜索表单
	var searchForm = $("#travel-note-search-form");
	
	function deleteTravelNote(id){
		if(id){
			$.post(deleteUrl, {id:id}, function(data){
				if(data){
					if(data.success) {
						datagrid.datagrid("reload");
					}else {
						msgShow("温馨提示", data.msg, "info");
					}
				}else {
					msgShow("温馨提示", "系统异常,请稍后再试", "warn");
				}
			},"json");
		}
	}

	// 初始化datagrid
	datagrid.datagrid({
		// 请求地址
		url : listUrl,
		// 大小自适应
		fit : false,
		// 无边框
		border : false,
		// 内容适应列的大小
		fitColumns : true,
		// 不自动高度
		autoRowHeight : true,
		// 只显示一行
		nowrap : false,
		loadMsg : '数据正在加载中，请稍等哒~',
		// 显示分页条
		pagination : true,
		// 测试的时候显示行号
		rownumbers : true,
		// 单选
		singleSelect : true,
		// 滚动条宽度
		scrollbarSize : 20,
		pageList: [10,15],
		// 表头属性
		columns : [ [ 
		{
			field : 'userMobile',
			title : '用户账号',
			width : 10,
			align : 'center',
			formatter:function(v,r,i){
				return r ? "<a href="+ _ctx + "/user/index.do?cmd=center&id="+ r.userId +" target=_blank>"+v+"</a>":'';
			}
		},
		{
			field : 'routeName',
			title : '路线名称',
			width : 10,
			align : 'center'
		},
		{
			field : 'title',
			title : '标题',
			width : 10,
			align : 'center',
			
		},{
			field : 'dayCount',
			title : '游玩天数',
			width : 10,
			align : 'center',
		},{
			field : 'suitNames',
			title : '适合人群',
			width : 10,
			align : 'center',
			formatter: function(v,r,i){
				if(v && v.length) {
					var s = '';
					for (var i = 0; i < v.length; i++) {
						if(i != v.length -1){
							s += v[i]+"、";
						} else {
							s += v[i];
						}
					}
					return s;
				}
			}
		},{
			field : 'departTime',
			title : '出发时间',
			width : 10,
			align : 'center',
		}, {
			field : 'distance',
			title : '行驶距离',
			width : 10,
			align : 'center',
		},{
			field : 'capitaCost',
			title : '消费金额',
			width : 10,
			align : 'center',
		},{
			field : 'xxxx',
			title : '查看游记内容',
			width : 10,
			align : 'center',
			formatter:function(v,r,i){
				return '<a href="javascript:void(0);" onclick="showContent('+r.id+')">游记内容</button>';
			}
		}, {
			field : 'createTime',
			title : '发布时间',
			width : 10,
			align : 'center'
		}, {
			field : 'xxx',
			title : '操作',
			width : 10,
			align : 'left',
			formatter : function(v, r, i) {
				return "<a href='javascript:void(0);' class='travel-note-delete' data-id='"+r.id+"'>删除</a>";
			}
		}
		] ],
		onLoadSuccess:function(){
			$(".travel-note-delete").click(function(){
				deleteTravelNote($(this).data("id"));
			});
		}
	});
	// 表单搜索
	var search = function() {
		var paramObj = searchForm.serializeJson();
		// 发送post请求刷新 datagrid 的数据
		datagrid.datagrid("load", paramObj);
	};

	/**
	 * 按钮事件监听对象
	 */
	var btnClickObj = {
		'search-confirm' : function() {
			search();
		},
		'do-search' : function() {
			search();
		}
	};

	// 统一事件监听
	$("[data-cmd]").click(function(){
		//获取方法的名字
		var methodName = $(this).data("cmd");
		//根据方法名 btnClickMethodsObj 对象来调用方法
		if(methodName && btnClickObj[methodName]){
			btnClickObj[methodName]();
		}
	});
	
	
	if(searchUserId.val()){
		  var paramObj = searchForm.serializeJson();
			// 发送post请求刷新 datagrid 的数据
		  datagrid.datagrid({
			  url:_ctx + "/usertravelnote/list.do",
			  queryParams:paramObj
		  });
	  }else {
		//请求地址
		  datagrid.datagrid({
			  url:_ctx + "/usertravelnote/list.do"
		  });
	  }

});
