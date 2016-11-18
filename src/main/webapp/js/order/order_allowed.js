/**
 * 订单界面的js
 */

$(function() {
	//1、声明页面需要使用的组件
	var orderGrid,orderSearchForm,serviceCodeForm;
	//2、缓存组件
	orderGrid = $("#order-datagrid");
	orderSearchForm = $("#order-search-form");
	serviceCodeForm = $("#order-service-code-form");
	
	//3、初始化组件
	orderGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/order/list.do",
			    //参数
			    queryParams:{'status':2},
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
			    pageList:[10],
			    // 表头属性
			    columns:[[    
			        {field:'orderNumber',title:'订单号',width:80,align:'center'},    
			        {field:'total_amount',title:'订单金额(元)',width:80,align:'center',sortable: true,formatter:function(v,r,i){
			        	return r.totalAmount?r.totalAmount:"";
			        },sortable:true,order:'asc'},
			        {field:'order_type',title:'订单类型',width:80,align:'center',formatter:function(v,r,i){
			        	return r.serviceType.name?r.serviceType.name:"";
			        },sortable:true,order:'asc'},
			        {field:'station',title:'服务站点',width:80,align:'center',formatter:objectFormatter,sortable:true,order:'asc'},
			        {field:'user.mobile',title:'用户账号',width:80,align:'center',formatter:function(v,r,i){
			        	if(r.user){
			        		return "<a href="+ _ctx + "/user/index.do?cmd=center&id="+ r.user.id +" target=_blank>"+r.user.mobile+"</a>";
			        	}
			        	return "";
			        }},
			        {field:'user.nickName',title:'用户昵称',width:80,align:'center',formatter:function(v,r,i){
			        	if(r.user){
			        		return r.user.nickName;
			        	}
			        	return "";
			        }},
			        {field:'status',title:'订单状态',width:80,align:'center',formatter:function(v,r,i){
			        	if(v){
			        		switch(v){
			        			case 1:
			        				v = '已退款';
			        				break;
			        			case 2:
			        				v = '退款中';
			        				break;
			        			case 3:
			        				v = '已取消';
			        				break;
			        			case 4:
			        				v = '待支付';
			        				break;
			        			case 5:
			        				v = '待服务';
			        				break;
			        			case 6:
			        				v = '待完成';
			        				break;
			        			case 7:
			        				v = '待评价';
			        				break;
			        			case 8:
			        				v = '已完成';
			        				break;
			        		}
			        	}
			        	return v;
			        },sortable:true,order:'asc'},
			        {field:'create_time',title:'创建时间',width:80,align:'center',formatter:function(v,r,i){
			        	return r.createTime?r.createTime : "";
			        },sortable:true,order:'asc'},
			        {field:'xxx',title:'操作',width:80,align:'center',formatter:function(v,r,i){
			        	if(r.status == 2){
			        		return '<a href='+ _ctx + '/order/index.do?cmd=detail&id='+ r.id +'>查看</a>'+'&emsp;<a href="#" class="refund-status" data-index="'+r.id+'">同意退款</a>';
			        	}else{
			        		return "<a href="+ _ctx + "/order/index.do?cmd=detail&id="+ r.id +">查看</a>";
			        	}
			        }}
			    ]],
			    onLoadSuccess:function(){
			    	$("a.refund-status").click(function(data){
			    		//获得要更改的id
			    		var rowId = $(this).data("index");
			    		
			    		$.messager.confirm("温馨提示", "是否确认退款？", function(yes) {
			    			if (yes) {
			    				$.post(_ctx + "/order/refundById.do", {"id" : rowId}, 
		    						function(data) {
				    					// 判断
				    					if (data.success) {
				    						// 提示
				    						$.messager.alert("温馨提示", data.msg, "info",
				    							function() {
				    								//刷新页面
				    								$("#order-datagrid").datagrid("reload");
				    							});
				    					} else {
				    						$.messager.alert("温馨提示", data.msg, "info");
				    					}
				    				}, "json");
			    			}
			    		});
			    	});
			  
			    	
			    }
			}
		);
	
});