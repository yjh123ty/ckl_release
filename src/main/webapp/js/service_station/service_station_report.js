/**
 * 路线界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 路线表格
	var serviceStationReportDatagrid = $("#service-station-report-datagrid");
	
	// 搜索表单
	var searchForm = $("#service-station-report-search-form");
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
	var stationSelect = $("#station-select");
	
	var clearSearchFormBtn = $("#clear-search-form");
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/servicestationreport/list.do";
	
	
	// 初始化 数据表格
	serviceStationReportDatagrid.datagrid(
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
		        {field:'stationName',title:'服务站名称',width:10,align:'center'},    
		        {field:'stationAddress',title:'服务站地址',width:10,align:'center'},
		        {field:'restaurantIncome',title:'餐饮',width:10,align:'left'},
		        {field:'hotelIncome',title:'酒店',width:10,align:'left',sortable:true},
		        {field:'carCareIncome',title:'汽车维护',width:10,align:'left',sortable:true},
		        {field:'carRepairIncome',title:'汽车修理',width:10,align:'left',sortable:true},
		        {field:'roadSideIncome',title:'道路救援',width:10,align:'left',sortable:true},
		        {field:'allIncome',title:'总收入',width:10,align:'left',formatter:function(v,r,i){
		        	if(v != 0){
		        		return "<a href='javascript:void(0);' onclick='showIncome("+r.stationId+");'>"+v+"</a>";
		        	}else{
		        		return v;
		        	}
		        },sortable:true},
		        {field:'avgStar',title:'评分',width:10,align:'left',formatter:function(v,r,i){
		        	if(v){
		        		return "<a href='javascript:void(0);' onclick='showStar("+r.stationId+")'>"+v+"</a>";
		        	}else{
		        		return v;
		        	}
		        },sortable:true}
		        ]],
		        onLoadSuccess:function(){
		        	
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
		serviceStationReportDatagrid.datagrid("load",paramObj);
	};
	
	stationSelect.on("change",search);
	// 设置搜索按钮的点击事件
	searchConfirm.click(search);
	clearSearchFormBtn.click(function(){
		searchForm[0].reset();
		search();
	});
});