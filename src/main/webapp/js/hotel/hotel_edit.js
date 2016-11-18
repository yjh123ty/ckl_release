/**
 * 服务站修改界面的js
 */
$(function() {
	
	// 缓存界面组件
	var hotelEditForm = $("#form-hotel-edit");
	
	// 区域地址
	var dAddress = $("#d-address");
	
	// 详细地址
	var tAddress = $("#t-address");
	
	var modal = $("#my-alert");
	
	var tabs = $("#tabs");
	
	var datagrid = $("#datagrid");
	
	var hotelId=$("#hotel-id");
	
	var dialog = $("#dialog");
	
	var roomForm = $("#room-form");
	
	hotelEditForm.validator({});
	
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
		'hotel-confirm' : function() {
			// 封装服务类型参数
			var data = {};
			data['station.id'] = $("#service-station-id").val();
			$(".service-content:checked").each(function(i, checkbox) {
				data['serviceContents[' + i + '].id'] = checkbox.value;
				data['serviceContents[' + i + '].name'] = $(checkbox).parent().text().trim();
			});
			hotelEditForm.ajaxSubmit({
				url : _ctx + "/hotel/edit.do",
				type : "post",
				data : data,
				clearForm : false,
				resetForm : false,
				beforeSubmit : function() {
					// 校验参数
					return hotelEditForm.validator("isFormValid");
				},
				success : function(data) {
					if (isJsonObj(data)) {
						if(data.success) {
							// 设置房间类型添加的id
							hotelId.val(data.data);
							// 转到房间类型添加界面
							datagrid.datagrid("load", {
								'id':hotelId.val()
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
		'hotel-back' : function() {
			location.href = _ctx + "/hotel/index.do";
		},
		'back' : function() {
			location.href = _ctx + "/hotel/index.do";
		},
		'add': function(){
			//清空表单放置 缓存
			roomForm.form("clear");
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
						$.post(_ctx+"/hotelroom/delete.do",{id:row.id},function(result){
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
			roomForm.form("clear");
			
			//获取表单中 选中的行 的数据
			var row = datagrid.datagrid("getSelected");
			
			if(row){
				//回显数据
				roomForm.form("load", row);
				
				//修改标题
				dialog.dialog("setTitle","修改房间信息");
				
				//开启修改的dialog
				dialog.dialog("open");
			} else {
				$.messager.alert("温馨提示","请选择一行","info");
			}
		},
		'editSubmit': function(){
			roomForm.form({
				queryParams:{
					'bedTypeName':roomForm.find("#cc").combo("getText"),
					'hotel.id':hotelId.val()
				}
			});
			//进行表单的提交操作
			roomForm.form('submit', {    
			    url:_ctx+"/hotelroom/edit.do",
			    onSubmit: function(){
			    	// 表单验证 可以加入其它验证
			        return roomForm.form("validate");
			    },    
			    success:function(result){   
			    	var data = null; 
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
				        } else {
				        	$.messager.alert('温馨提示', "操作失败：" + data.msg +",请稍后再试！", 'warn');
				        }
			        }else {
			        	$.messager.alert('温馨提示', "系统异常", 'error');
			        }
			    }    
			}); 
		},
		'editReset': function(){
			roomForm.form("reset");
		},
		'editCancel': function(){
			dialog.dialog("close");
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
				url:_ctx+"/hotelroom/list.do",
			    //大小自适应
			    fit:true,
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
			    pageList:[10,15],
			    // 表头属性
			    columns:[[    
			        {field:'name',title:'名称',width:80,align:'center'},    
			        {field:'price',title:'价格',width:80,align:'center'},
			        {field:'size',title:'面积(平米)',width:80,align:'center'},
			        {field:'bedTypeName',title:'床型',width:80,align:'center'},
			        {field:'totalNum',title:'总数量',width:80,align:'left'}
			    ]],
			    //工具条
			    toolbar:'#toolbar'
			}
		);

	dialog.dialog({
	 	title: '编辑房间',    
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
	
	if(!hotelId.val()){
		tabs.tabs("disableTab",1);
	}else {
		datagrid.datagrid("load", {
			'id':hotelId.val()
		});
	}
});
