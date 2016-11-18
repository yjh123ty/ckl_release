/**
 * 服务评价界面的js
 */

$(function() {
	//1、声明页面需要使用的组件
	var serviceEvaluationGrid,serviceEvaluationSearchForm;
	//2、缓存组件
	serviceEvaluationGrid = $("#serviceEvaluation-datagrid");
	serviceEvaluationSearchForm = $("#serviceEvaluation-search-form");
	// 摸态框
	var modal = $('#star-modal');
	// 模态框内容
	var content=modal.find("#content");
	var searchStationId = $("#search-station-id");
	var searchUserId = $("#search-user-id");
	var searchEmployeeId = $("#search-employee-id");
	
	var showModal = function(url) {
    	// 显示服务详情
		content.attr("src", url);
    	modal.modal();
	};
	
	//3、初始化组件
	serviceEvaluationGrid.datagrid(
			{
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
			        {field:'orderNumber',title:'订单号',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.order){
			        		return r.order.orderNumber;
			        	}
			        	return "";
			        }},    
			        {field:'serviceType',title:'服务类型',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.order && r.order.serviceType){
			        		return r.order.serviceType.name?r.order.serviceType.name:"";
			        	}
			        	return "";
			        }},
			        {field:'umobile',title:'用户账号',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.order && r.order.user){
			        		return "<a href="+ _ctx + "/user/index.do?cmd=center&id="+ r.order.user.id +" target=_blank>"+r.order.user.mobile+"</a>";
			        	}
			        	return "";
			        }},
			        {field:'unickName',title:'用户昵称',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.order && r.order.user){
			        		return r.order.user.nickName?r.order.user.nickName:"";
			        	}
			        	return "";
			        }},
			        {field:'station',title:'服务站点',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.order && r.order.station){
			        		return r.order.station.name?r.order.station.name:"";
			        	}
			        	return "";
			        }},
			        {field:'employee',title:'服务员工账号',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.order && r.order.employee){
			        		return "<a href="+ _ctx + "/employee/index.do?cmd=center&id="+ r.order.employee.id +" target=_blank>"+r.order.employee.mobile+"</a>";
			        	}
			        	return "";
			        }},
			        {field:'employName',title:'服务员工姓名',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.order && r.order.employee){
			        		return r.order.employee.realName?r.order.employee.realName:"";
			        	}
			        	return "";
			        }},
			        {field:'star',title:'评价星级',width:10,align:'center',formatter:function(v, r, i){
			        	if(r.order){
			        		return "<a href='javascript:void(0);' class='show-evaluation-detail' data-index='"+ r.order.id +"'>"+v+"</a>";
			        	}else{
			        		return "";
			        	}
			        },sortable:true,order:'asc'},
			        {field:'content',title:'评价内容',width:10,align:'center'},
			        {field:'create_time',title:'评价日期',width:10,align:'center',formatter:function(v,r,i){
			        	return r.createTime?r.createTime : "";
			        },sortable:true,order:'asc'},
			    ]],
			    
			    onLoadSuccess:function(){
			    	$("a.show-evaluation-detail").click(function(data){
				    	var index = $(this).data("index");
						//通过订单id拿到服务评价明细数据
						showModal(_ctx+"/serviceEvaluationDetail/index.do?orderId="+index);
						
				    	modal.find("#model-confirm").off('click.confirm.modal.amui').on('click', function() {
				    		modal.modal('close');
				    	});
			    	});
			    }
			}
		);
	
		if (searchStationId.val || searchUserId.val || searchEmployeeId.val) {
			var paramObj = serviceEvaluationSearchForm.serializeJson();
			serviceEvaluationGrid.datagrid({
				//请求地址
				url : _ctx + "/serviceEvaluation/list.do",
				queryParams:paramObj
			});
		}else {
			serviceEvaluationGrid.datagrid({
				//请求地址
				url : _ctx + "/serviceEvaluation/list.do"
			});
		}

});