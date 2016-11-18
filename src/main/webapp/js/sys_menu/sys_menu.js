/**
 * 菜单界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 菜单表格
	var menuDatagrid = $("#menu-datagrid");
	var menuDialog = $("#menu-dialog");
	var menuForm = $("#menu-form");
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/menu/list.do";
	
	// 添加/修改的url
	var editUrl= _ctx + "/menu/edit.do";
	
	// 菜单删除url
	var deleteUrl= _ctx + "/menu/delete.do";
	
	// 按钮时间统一监控对象
	var btnClickObj = {
		// 添加菜单	
		'add': function(){
			//清空表单放置 缓存
			menuForm.form("clear");
			// 选中正常状态
			$("#normalRadio").prop("checked", true);
			
			//弹出dialog
			menuDialog.dialog("open");
		},
		// 修改菜单	
		'update': function(){
			//清空菜单避免数据混淆
			menuForm.form("clear");
			
			//获取表单中 选中的行 的数据
			var row = menuDatagrid.datagrid("getSelected");
			
			if(row){
				//回显数据
				try {
					row["parent.id"] = row.parent.id;
				} catch (e) {
				}
				menuForm.form("load", row);
				
				console.debug(row);
				//修改标题
				menuDialog.dialog("setTitle","修改菜单");
				
				//开启修改的dialog
				menuDialog.dialog("open");
			} else {
				$.messager.alert("温馨提示","请选择一行","info");
			}
		},
		// 禁用菜单	
		'delete': function(){
			//获取选中行的数据
			var row = menuDatagrid.datagrid("getSelected");
			if(row){
				$.messager.confirm('你确定要删除吗？', '删除时子菜单也会被删除！', function(r){
					if (r){
						//发送ajax请求来 实现删除功能
						$.post(deleteUrl,{id:row.id},function(result){
							if(result.success){
								//刷新界面
								menuDatagrid.datagrid("reload");
							}else {
								$.messager.alert("温馨提示",result.msg,'error');
							}
						},'json');
					}
				});
			}else {
				$.messager.alert("温馨提示","请选中需要删除的菜单！",'info');
			}
		},
		// 刷新菜单列表
		'refresh': function(){
			menuDatagrid.datagrid("reload");
		},
		// 用户修改/添加提交	
		'editSubmit': function(){
			//进行表单的提交操作
			menuForm.form('submit', {    
			    url:editUrl,    
			    onSubmit: function(){
			    	// 表单验证 可以加入其它验证
			        return menuForm.form("validate");
			    },    
			    success:function(result){    
			        var data = $.parseJSON(result);
			        if(data.success){
			        	//关闭dialog
			        	menuDialog.dialog("close");
			        	//提示
				        	$.messager.alert('温馨提示', data.msg,'info',function(){
			        		//用户点击确定
			        		//刷新dataGrid
			        		menuDatagrid.datagrid("reload");
			        	});
			        } else {
			        	$.messager.alert('温馨提示', "操作失败：" + data.msg +",请稍后再试！", 'error');
			        }
			    }    
			}); 
		},
		// 用户修改/添加提交 重置
		'editReset': function(){
			menuForm.form("reset");
		},
		// 用户修改/添加 取消
		'editCancel': function(){
			menuDialog.dialog("close");
		}
	};
	
	var iconFormatter = function(v,r,i){
		return "<span class='"+ v +"'></span>";
	}
	
	// 初始化 数据表格
	menuDatagrid.datagrid(
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
		    //滚动条宽度
		    scrollbarSize:20,
		    pageList:[10,15],
		    // 表头属性
		    columns:[[    
		        {field:'name',title:'名称',width:80,align:'center'},    
		        {field:'iconCls',title:'图标',width:80,align:'center',formatter:iconFormatter},
		        {field:'url',title:'地址',width:80,align:'center'},
		        {field:'parent',title:'父菜单',width:80,align:'center',formatter:objectFormatter},
		        {field:'intro',title:'描述',width:160,align:'left'}
		    ]],
		    //工具条
		    toolbar:'#menu-toolbar'
		}
	);
	
	menuDialog.dialog({
	 	title: '编辑菜单',    
	    width: 'auto',    
	    height: 'auto',  
	    //是否可以关闭
	    closed: true,  
	    // 模态框模式
	    modal: true,
	    //是否可以拖拽
	    draggable:true,
	    //添加底部的buttons
	    buttons:'#menu-dialog-buttons'
	});
	
	//监听所有的自己设置的按钮  根据按钮上data-cmd 来设置按钮的事件
	$("[data-cmd]").click(function(){
		//获取方法的名字
		var methodName = $(this).data("cmd");
		//根据方法名 btnClickMethodsObj 对象来调用方法
		if(methodName && btnClickObj[methodName]){
			btnClickObj[methodName]();
		}
	});
	
});