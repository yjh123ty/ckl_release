/**
 * 路线界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 路线表格
	var touristSpotDatagrid = $("#tourist-spot-datagrid");
	
	// 搜索表单
	var searchForm = $("#tourist-spot-search-form");
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
	// 搜索按钮
	var doSearch = $("#do-search");
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/touristspot/list.do";
	
	// 初始化 数据表格
	touristSpotDatagrid.datagrid(
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
		    autoRowHeight:false,
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
		        {field:'name',title:'景点名称',width:10,align:'center'},    
		        {field:'imgs',title:'景点图片',width:10,align:'center',formatter:function(v,r,i){
		        	if(r.imgs){
		        		var picArry=JSON.stringify(r.imgs);
		        		return "<a href='javascript:void(0);' onclick='scanPic(this)' src='"+picArry+"'>查看景点图片</a>";
		        	}else{
		        		return '暂无图片';
		        	}
		        }},
		        {field:'fullAddress',title:'地址',width:10,align:'center'},
		        {field:'intro',title:'描述',width:10,align:'center'},
		        {field:'createTime',title:'录入时间',width:10,align:'center'},
		        {field:'null',title:'操作',width:10,align:'left',formatter:function(v,r,i){
		        	return "<a href='"+_ctx+"/touristspot/index.do?cmd=update&id="+r.id+"'>编辑</a>--<a href='javascript:deleteTouristSpot("+r.id+");'>删除</a>";
		        }}
		        
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
		touristSpotDatagrid.datagrid("load",paramObj);
	};
	// 设置搜索按钮的点击事件
	searchConfirm.click(search);
	doSearch.click(search);
});