/**
 * 服务站修改界面的js
 */
$(function() {
	
	// 缓存界面组件
	var restaurantEditForm = $("#form-restaurant-edit");
	
	var modal = $("#my-alert");
	
	var tabs = $("#tabs");
	
	var datagrid = $("#datagrid");
	
	var restaurantId=$("#restaurant-id");
	
	var dialog = $("#dialog");
	
	var comoboForm = $("#combo-form");
	
	var selectComboId = null;
	/////////////饭店套餐菜品新增////////////////
	var dishesDatagrid = $("#datagrid-detail");
	var dishesDialog = $("#dialog1");
	var dishesForm = $("#dishes-form");
	var dishesUpdateDialog = $("#dialog2");
	var dishesUpdateForm = $("#dishes-update-form");
	var restaurantDishesSelect = $("#restaurant-dishes-select");
	
	restaurantEditForm.validator({});
 	/*
	 * 文件上传 js 效果
	 */
	$('.doc-form-file').on('change', function() {
		var fileNames = '';
		$.each(this.files, function() {
			fileNames += '<span class="am-badge">' + this.name + '</span> ';
		});
		fileNames = fileNames ? fileNames : "没有选择任何文件";
		$(this).closest(".am-form-file").find('.file-list').html(fileNames);
	});

	// 按钮事件统一监控对象
	var btnClickObj = {
		'restaurant-confirm' : function() {
			// 封装服务类型参数
			var data = {};
			restaurantEditForm.ajaxSubmit({
				url : _ctx + "/restaurant/edit.do",
				type : "post",
				data : data,
				clearForm : false,
				resetForm : false,
				beforeSubmit : function() {
					// 校验参数
					return restaurantEditForm.validator("isFormValid");
				},
				success : function(data) {
					if (isJsonObj(data)) {
						if(data.success) {
							// 设置房间类型添加的id
							restaurantId.val(data.data);
							// 转到房间类型添加界面
							datagrid.datagrid("load", {
								'id':data.data
							});
							tabs.tabs("enableTab",1);
							tabs.tabs("select", 1);
						} else {
							modal.find(".am-modal-bd").html(data.msg);
							modal.modal();
						}
					} else {
						modal.find(".am-modal-bd").html("系统异常");
						modal.modal();
					}
				}
			});
		},
		'restaurant-back' : function() {
			location.href = _ctx + "/restaurant/index.do";
		},
		'back' : function() {
			location.href = _ctx + "/restaurant/index.do";
		},
		'add': function(){
			//清空表单放置 缓存
			comoboForm.form("clear");
			//弹出dialog
			dialog.dialog("open");
		},
		'delete': function(){
			//获取选中行的数据
			var row = datagrid.datagrid("getSelected");
			if(row){
				$.messager.confirm('你确定要删除吗？', '删除不可恢复！', function(r){
					if (r){
						//发送ajax请求来 实现删除功能
						$.post(_ctx+"/restaurantcombo/delete.do",{id:row.id},function(result){
							if(result.success){
								//刷新界面
								datagrid.datagrid("reload");
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
		'update': function(){
			//清空菜单避免数据混淆
			comoboForm.form("clear");
			
			//获取表单中 选中的行 的数据
			var row = datagrid.datagrid("getSelected");
			
			if(row){
				//回显数据
				comoboForm.form("load", row);
				
				//修改标题
				dialog.dialog("setTitle","修改房间信息");
				
				//开启修改的dialog
				dialog.dialog("open");
			} else {
				$.messager.alert("温馨提示","请选择一行","info");
			}
		},
		'editSubmit': function(){
			comoboForm.form({
				queryParams:{
					'name':comoboForm.find("#cc").combo("getText"),
					'restaurantId':restaurantId.val()
				}
			});
			//进行表单的提交操作
			comoboForm.form('submit', {    
			    url:_ctx+"/restaurantcombo/edit.do",
			    onSubmit: function(){
			    	// 表单验证 可以加入其它验证
			        return comoboForm.form("validate");
			    },    
			    success:function(result){    
			        var data = '';
			        try {
			        	data = $.parseJSON(result);
					} catch (e) {
					}
			        if(data){
			        	if(data.success){
			        		//关闭dialog
			        		dialog.dialog("close");
				        	$.messager.alert('温馨提示', data.msg,'info',function(){
			        		datagrid.datagrid("reload");
				        	});
			        	}else {
			        		$.messager.alert('温馨提示', data.msg, 'warn');
			        	}
			        } else {
			        	$.messager.alert('温馨提示', "操作失败：请稍后再试！", 'error');
			        }
			    }    
			}); 
		},
		'editReset': function(){
			comoboForm.form("reset");
		},
		'editCancel': function(){
			dialog.dialog("close");
		},
		'add-dishes': function(){
			var row = datagrid.datagrid("getSelected");
			if(!row) {
				$.messager.alert("温馨提示","请选中左边添加菜品的套餐！",'info');
				return;
			}
			restaurantDishesSelect.combobox("reload");
			//清空表单放置 缓存
			dishesForm.form("clear");
			//弹出dialog
			dishesDialog.dialog("open");
		},
		'delete-dishes': function(){
			//获取选中行的数据
			var row = dishesDatagrid.datagrid("getSelected");
			if(row){
				$.messager.confirm('你确定要删除吗？', '删除不可恢复！', function(r){
					if (r){
						//发送ajax请求来 实现删除功能
						$.post(_ctx+"/restaurantdishes/deleteComboDishes.do",{dishesId:row.id, comboId:selectComboId},function(result){
							if(result.success){
								//刷新界面
								dishesDatagrid.datagrid("reload");
				        		datagrid.datagrid("reload");
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
		'update-dishes': function(){
			//清空菜单避免数据混淆
			dishesUpdateForm.form("clear");
			
			//获取表单中 选中的行 的数据
			var row = dishesDatagrid.datagrid("getSelected");
			
			if(row){
				//回显数据
				row.dishesId = row.id;
				row.dishesName = row.name;
				dishesUpdateForm.form("load", row);
				
				//修改标题
				dishesUpdateDialog.dialog("setTitle","修改菜品信息");
				
				//开启修改的dialog
				dishesUpdateDialog.dialog("open");
			} else {
				$.messager.alert("温馨提示","请选择一行","info");
			}
		},
		'editDishesSubmit': function(){
			dishesForm.form({
				queryParams:{
					comboId:datagrid.datagrid("getSelected").id
				}
			});
			var url = _ctx+"/restaurantdishes/saveComboDishes.do";
			//进行表单的提交操作
			dishesForm.form('submit', {    
			    url:url,
			    onSubmit: function(){
			    	// 表单验证 可以加入其它验证
			        return dishesForm.form("validate");
			    },    
			    success:function(result){    
			        var data = '';
			        try {
			        	data = $.parseJSON(result);
					} catch (e) {
					}
			        if(data){
			        	//关闭dialog
			        	if(data.success){
			        		dishesDialog.dialog("close");
				        	$.messager.alert('温馨提示', data.msg,'info',function(){
			        		dishesDatagrid.datagrid("reload");
			        		datagrid.datagrid("reload");
				        	});
			        	} else{
			        		$.messager.alert('温馨提示', data.msg, 'warn');
			        	}
			        } else {
			        	$.messager.alert('温馨提示', "操作失败：请稍后再试！", 'error');
			        }
			    }    
			}); 
		},
		'editDishesReset': function(){
			dishesForm.form("reset");
		},
		'editDishesCancel': function(){
			dishesDialog.dialog("close");
		},
		'updateDishesSubmit': function(){
			dishesUpdateForm.form({
				queryParams:{
					comboId:selectComboId
				}
			});
			var url = _ctx+"/restaurantdishes/updateComboDishes.do";
			//进行表单的提交操作
			dishesUpdateForm.form('submit', {    
			    url:url,
			    onSubmit: function(){
			    	// 表单验证 可以加入其它验证
			        return dishesUpdateForm.form("validate");
			    },    
			    success:function(result){    
			        var data = '';
			        try {
			        	data = $.parseJSON(result);
					} catch (e) {
					}
			        if(data&&data.success){
		        		//关闭dialog
		        		dishesUpdateDialog.dialog("close");
			        	$.messager.alert('温馨提示', data.msg,'info',function(){
		        		dishesDatagrid.datagrid("reload");
		        		datagrid.datagrid("reload");
			        	});
			        } else {
			        	$.messager.alert('温馨提示', "操作失败：请稍后再试！", 'error');
			        }
			    }    
		}); 
	},
	'updateDishesReset': function(){
		dishesUpdateForm.form("reset");
	},
	'updateDishesCancel': function(){
		dishesUpdateDialog.dialog("close");
	}
		
	};

	// 统一监听按钮的事件
	$("[data-cmd]").click(function() {
		var cmd = $(this).data("cmd");
		if (cmd && btnClickObj[cmd]) {
			btnClickObj[cmd](this);
		}
	});
	
	datagrid.datagrid(
			{
			    //大小自适应
			    fit:true,
			    title:"套餐基本信息",
			    //无边框
			    border:false,
			    //内容适应列的大小
			    fitColumns:true,
			    //不自动高度
			    autoRowHeight:false,
			    //只显示一行
			    nowrap:true,
			    loadMsg:'数据正在加载中，请稍等哒~',
			    //显示分页条
			    pagination:false,
			    //测试的时候显示行号
			    rownumbers:true,
			    //单选
			    singleSelect:true,
			    //滚动条宽度
			    scrollbarSize:0,
			    // 表头属性
			    columns:[[    
			        {field:'name',title:'名称',width:80,align:'center'},    
			        {field:'price',title:'价格',width:80,align:'center'},
			        {field:'xxxx',title:'有效期',width:80,align:'center',formatter:function(v,r,i){
			        	var startTime = r['startTime'];
						var endTime = r['endTime'];
						return startTime&&endTime ? startTime +" 到 "+endTime : '';
			        }},
			        {field:'detail',title:'套餐详情',width:80,align:'center'},
			    ]],
			    //工具条
			    toolbar:'#toolbar',
			    onSelect:function(i,r){
			    	selectComboId = r.id;
			    	dishesDatagrid.datagrid("load",{id:r.id});
			    	restaurantDishesSelect.combobox({ 
						url: _ctx + "/restaurantdishes/listNotInComboDishes.do?restaurantId=" + restaurantId.val().trim()+"&comboId="+r.id,
					});
			    }
			}
		);

	dialog.dialog({
	 	title: '编辑套餐',    
	    width: 'auto',    
	    height: 'auto',  
	    //是否可以关闭
	    closed: true,  
	    // 模态框模式
	    modal: true,
	    //是否可以拖拽
	    draggable:true,
	    //添加底部的buttons
	    buttons:'#dialog-buttons'
	});
	
	if(!restaurantId.val()){
		tabs.tabs("disableTab",1);
		datagrid.datagrid({
			url:_ctx+"/restaurantcombo/list.do",
		});
	}else {
		datagrid.datagrid({
			url:_ctx+"/restaurantcombo/list.do?id="+restaurantId.val(),
		});
	}
	
	////////////////////菜品/////////////////////
	dishesDatagrid.datagrid(
			{
				url:_ctx+"/restaurantdishes/listByComboId.do",
			    //大小自适应
			    fit:true,
			    title:"套餐菜品信息",
			    //无边框
			    border:false,
			    //内容适应列的大小
			    fitColumns:true,
			    //不自动高度
			    autoRowHeight:false,
			    //只显示一行
			    nowrap:true,
			    loadMsg:'数据正在加载中，请稍等哒~',
			    //显示分页条
			    pagination:false,
			    //测试的时候显示行号
			    rownumbers:true,
			    //单选
			    singleSelect:true,
			    //滚动条宽度
			    scrollbarSize:0,
			    // 表头属性
			    columns:[[    
			        {field:'name',title:'名称',width:80,align:'center'},    
			        {field:'price',title:'价格',width:80,align:'center'},
			        {field:'num',title:'数量',width:80,align:'center'},
			        {field:'total',title:"总价",width:80,align:'center'}
			        ]],
			    //工具条
			    toolbar:'#toolbar1'
			}
		);
	
	restaurantDishesSelect.combobox({ 
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto'
	});  
	
	dishesDialog.dialog({
	 	title: '添加菜品',    
	    width: 'auto',    
	    height: 'auto',  
	    //是否可以关闭
	    closed: true,  
	    // 模态框模式
	    modal: true,
	    //是否可以拖拽
	    draggable:true,
	    //添加底部的buttons
	    buttons:'#dialog-buttons1'
	});
	
	dishesUpdateDialog.dialog({
		title: '修改菜品数量',    
		width: 'auto',    
		height: 'auto',  
		//是否可以关闭
		closed: true,  
		// 模态框模式
		modal: true,
		//是否可以拖拽
		draggable:true,
		//添加底部的buttons
		buttons:'#dialog-buttons2'
	});
});
