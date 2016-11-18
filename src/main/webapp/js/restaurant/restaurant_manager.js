/**
 * 路线界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 饭店数据表格
	var datagrid = $("#restaurant-datagrid");
	
	// 搜索表单
	var searchForm = $("#restaurant-search-form");
	
	// 搜索按钮
	var doSearch = $("#do-search");
	
	// 星级按钮组
	var starsBtnGroup = $("#stars-btn-group");
	
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/restaurant/list.do";
	
	// 初始化 数据表格
	datagrid.datagrid(
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
		    //是否可以多列排序
		    multiSort:true,
		    pageList:[10,15],
		    // 表头属性
		    columns:[[    
			        {field:'name',title:'饭店名称',width:10,align:'center'},    
			        {field:'s.name',title:'服务站名称',width:10,align:'center',formatter:function(v,r,i){
			        	var station = r['station'];
						return station ? station.name : "";
			        },sortable:true,order:'asc'},
			        {field:'fullAddress',title:'地址',width:10,align:'left'},
			        {field:'mobile',title:'电话',width:0,align:'center'},
			        {field:'stars',title:'饭店星级',width:10,align:'center',sortable:true},
			        {field:'xxxxx',title:'营业时间',width:10,align:'center',formatter:function(v, r, i){
			        	var openTime = r['openTime'];
						var closeTime = r['closeTime'];
						return openTime && closeTime ? openTime+"-"+closeTime :"";
			        }},
			        {field:'x',title:'套餐类型',width:10,align:'center',formatter:function(v, r, i){
			        	var combos = r['restaurantCombos'];
			        	var s = '';
						if(combos&&combos.length){
			        		for (var i = 0; i < combos.length; i++) {
								s += combos[i].name+"、";
							}
			        		return s;
			        	}else {
			        		return '无套餐';
			        	}
			        }},
			        {field:'createTime',title:'录入时间',width:10,align:'center',sortable:true},
			        {field:'status',title:'操作',width:10,align:'center',formatter:function(v,r,i){
			        	if(v == -1){
			        		return "<a href='"+_ctx+"/restaurant/index.do?cmd=update&id="+r.id+"'>编辑</a>--<a href='javascript:void(0);' onclick='changeStatus("+r.id+",0)' style='color:green;'>开通</a>";
			        	}else if(v == 0){
			        		return "<a href='"+_ctx+"/restaurant/index.do?cmd=update&id="+r.id+"'>编辑</a>--<a href='javascript:void(0);' style='color:red;' onclick='changeStatus("+r.id+",-1)'>关闭</a>";
			        	}
			        }}
		        ]]
		}
	);
	var starsBtn = starsBtnGroup.children();
	var stars = null;
	// 搜索操作
	var search = function(){
		// TODO 验证表单参数
		// 获取搜索表单 
		// 封装表单参数
		var paramObj = searchForm.serializeJson();
		if(stars){
			paramObj.stars = stars;
		}
		// 发送post请求刷新 datagrid 的数据
		datagrid.datagrid("load",paramObj);
	};
	// 设置搜索按钮的点击事件
	doSearch.click(search);
	
	starsBtn.each(function(i, btn){
		$(btn).click(function(){
			starsBtn.removeClass('am-btn-success');
			starsBtn.addClass('am-btn-default');
			$(this).addClass('am-btn-success');
			stars = i==0 ? null : 6 - i;
			var paramObj = searchForm.serializeJson();
			if(stars){
				paramObj.stars = stars;
			}
			// 发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
		
	});
});