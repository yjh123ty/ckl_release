/**
 * 用户提现报表界面的js
 */

$(function() {
	var userBillWithdrawDetailGrid = $("#userBillWithdrawDetail-datagrid");
	var userBillWithdrawDetailSearchForm = $("#userBillWithdrawDetail-search-form");
	var transferForm = $("#transfer-form");
	var uid = $('#uid').val();
	
	//初始化验证框
	var model = $('#dd');
	model.dialog({
	    title: '转账成功',
	    closed: true,
	    cache: false,
	    maximizable:true,
	    modal: true
	});
	
	//3、初始化组件
	// 初始化 数据表格
	userBillWithdrawDetailGrid.datagrid(
		{
			//请求地址
		    url:_ctx + "/userBill/userBillWithdrawDetailList.do",
		    //请求参数
		    queryParams: {         
		    	'uid': uid         
    		},
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
		    //是否可以多列排序
		    multiSort:true,
		    pageList:[10,15],
		    // 表头属性
		    columns:[[    
		        {field:'umobile',title:'用户账号',width:80,align:'center',formatter:function(v,r,i){
		        	return "<a href="+ _ctx + "/user/index.do?cmd=center&id="+ r.user.id +" target=_blank>"+r.user.mobile+"</a>";
		        }},
		        {field:'uname',title:'用户昵称',width:80,align:'center',formatter:function(v,r,i){
		        	return r.user.nickName?r.user.nickName:"";
		        }},
		        {field:'balance',title:'提现金额(元)',width:80,align:'center',sortable: true,sortable:true,order:'desc'},
		        {field:'pay_method',title:'到账方式',width:80,align:'center',sortable: true,formatter:function(v,r,i){
		        	var result = r.payMethod==null?"":r.payMethod;
		        	if(result){
	        			switch(result){
			        		case 1:
			        			result = "支付宝";
			        			break; 
			        		case 2:
			        			result = "微信";
			        			break; 
			        		case 3:
			        			result = "平台币";
			        			break; 
			        		case 4:
			        			result = "人工帮助";
			        			break; 
		        		}
		        	}
		        	return result;
		        },sortable:true,order:'asc'},
		        {field:'intro',title:'到账账号',width:80,align:'center'},
		        {field:'status',title:'转账截图',width:80,align:'center',formatter:function(v,r,i){
		        	var src =  r.introImg;
		        	if(v == 3 && src == null){
		        		return "待转账";
		        	}else if(v == 4 && src != null){
		        		return '<img src="'+src+'" style="width:80px; height:40px;">';
		        	}else{
		        		return "无";
		        	}
		        }},
		        {field:'modifyTime',title:'提现日期',width:80,align:'center',formatter:function(v,r,i){
		        	return r.modifyTime?r.modifyTime : "";
		        },sortable:true,order:'desc'},
		        {field:'id',title:'操作',width:80,align:'center',formatter:function(v,r,i){
		        	if(r.status == 3){
		        		return "<a href='#' class='transfer' data-val='"+v+"'>转账完成</a>";
		        	}else{
		        		return "转账日期："+ r.transferTime;
		        	}
		        }}
		    ]],
		    onLoadSuccess:function(){
		    	var rowId = '';
		    	$(".transfer").click(function(){
		    		rowId = $(this).data("val");
		    		//获得UserBill的id,传入表单
	 				//初始化出现位置(以点击的按钮为参照，设置偏移量)
	 				var top = $(this).offset().top + 100;
	 				var left = $(this).offset().left - 1000;
	 				model.window('open').window('resize',{width:'460px',height:'350px',top: top,left:left});
				});
		    	
		    	//提交表单，后台接收参数，做处理、
		    	$("a.commit").click(function(data){
		    		// 封装服务类型参数
					var data = {'id':rowId};
					
					transferForm.ajaxSubmit( {    
			    	       url:       _ctx +'/userBill/transferById.do', 
			    	       data:data,
			    	       type:      "post",
			    	       clearForm: false,     
			    	       resetForm: false,     
			    	       beforeSubmit: function(){
			    	    	   // 校验参数
			    	       }, 
			    	       success: function(data){
			    	    	   if(isJsonObj(data)){
									if(data.success){
										msgShow("提示", data.msg, "info");
										//关闭
										model.dialog('close');
										//重新跳转
										location.href= _ctx + "/userBill/index.do?cmd=withdrawDetail&uid=" + uid;
									}else {
										msgShow("提示", data.msg, "info");
										//关闭
										model.dialog('close');
									}
								} else {
									msgShow("提示", "系统异常", "info");
									//关闭
									model.dialog('close');
								}
			    	       }
			    	   });
								
					
		    	});
		    	
		    	//点击返回按钮
		    	$("a.back").click(function(data){
		    		//清除数据
		    		transferForm.form("clear")
		    		//关闭
		    		model.dialog('close');
		    	});
		    }
    		
		}
	);
	
});