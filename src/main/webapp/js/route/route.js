/**
 * 路线界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 路线表格
	var routeDatagrid = $("#route-datagrid");
	
	// 添加路线的按钮
	var addRouteBtn = $("#btn-add-route");
	
	// 搜索表单
	var searchForm = $("#route-search-form");
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
	// 搜索按钮
	var doSearch = $("#do-search");
	
	// 摸态框
	var modal = $('#guide-modal');
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/route/list.do";
	
	// 初始化 数据表格
	routeDatagrid.datagrid(
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
		        {field:'name',title:'名称',width:10,align:'center'},    
		        {field:'img',title:'图片',width:10,align:'center',formatter:function(v,r,i){
		        	var picArry=r.img;
		        	return "<a href='javascript:void(0);' onclick='scanPic(this)' src='"+picArry+"'>查看图片</a>";
		        }},
		        {field:'intro',title:'详情',width:10,align:'left'},
		        {field:'days',title:'游玩天数',width:4,align:'center'},
		        {field:'distance',title:'里程数(KM)',width:5,align:'center'},
		        {field:'cost',title:'大概费用(元)',width:5,align:'center'},
		        {field:'suits',title:'适合人群',width:10,align:'center',formatter:function(v,r,i){
		        	if(v && v.length) {
		        		var s = '';
		        		for (var i = 0; i < v.length; i++) {
		        			if(i != v.length -1) {
		        				s+= v[i].name +"、";
		        			}else {
		        				s += v[i].name;
		        			}
						}
		        		return s;
		        	}else {
		        		return "未填写";
		        	}
		        }},
		        {field:'createTime',title:'录入时间',width:10,align:'center'},
		        {field:'null',title:'操作',width:12,align:'left',formatter:function(v,r,i){
		        	return "<a href="+ _ctx + "/route/index.do?cmd=update&id="+ r.id +">编辑</a>--<a href="+ _ctx + "/routeguide/index.do?routeId="+ r.id +">修改攻略</a>";
		        }}
		        
		    ]],
		    onLoadSuccess:function(){
		    	$("a.show-route-guide").click(function(data){
			    	var content=modal.find("#guide_content");
			    	var head = modal.find(".am-modal-hd");
			    	var index = $(this).data("index");
			    	var row = routeDatagrid.datagrid("getRows")[index];
			    	var guide = row['guide'];
			    	guide = guide ?  guide : "暂无攻略" ;
			    	var name = row['name'];
					content.html(guide);
					head.html(name);
			    	modal.find("#model-confirm").off('click.confirm.modal.amui').on('click', function() {
			    		modal.modal('close');
			    	});
			    	modal.modal();
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
		routeDatagrid.datagrid("load",paramObj);
	};
	// 设置搜索按钮的点击事件
	searchConfirm.click(search);
	doSearch.click(search);
	
	// 添加路线按钮点击事件
	addRouteBtn.click(function(){
		//跳转到用户 添加、编辑界面
		location.href = _ctx + "/route/index.do?cmd=save";
	});
});