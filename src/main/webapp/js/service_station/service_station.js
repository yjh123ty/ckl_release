/**
 * 路线界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 路线表格
	var serviceStationDatagrid = $("#service-station-datagrid");
	
	// 添加路线的按钮
	var addServiceStationBtn = $("#btn-add-service-station");
	
	// 搜索表单
	var searchForm = $("#service-station-search-form");
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
	// 搜索按钮
	var doSearch = $("#do-search");
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/servicestation/list.do";
	
	// 初始化 数据表格
	serviceStationDatagrid.datagrid(
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
		        {field:'name',title:'服务站名称',width:10,align:'center'},    
		        {field:'fullAddress',title:'服务站地址',width:10,align:'center'},
		        {field:'intro',title:'服务站描述',width:10,align:'left'},
		        {field:'imgs',title:'图片',width:10,align:'center',formatter:function(v,r,i){
		        	if(r.imgs){
		        		var picArry=JSON.stringify(r.imgs);
			        	return "<a href='javascript:void(0);' onclick='scanPic(this)' src='"+picArry+"'>查看服务站图片</a>";
		        	}else{
		        		return '暂无图片';
		        	}
		        }},
		        {field:'x1',title:'服务内容',width:10,align:'center',formatter:function(v,r,i){
		        	v = r['serviceTypes'];
		        	var len = v.length;
		        	var s = '';
					if(len){
		        		for (var j = 0; j < len; j++) {
		        			var type = v[j].type;
							if(type==1 || type ==2 || type ==3) {
								s += '<a href="'+_ctx+'/servicestation/serviceContentInfo.do?type='+type+'&stationId='+r.id+'">&nbsp;' +v[j].name+'&nbsp;</a>';
							}else {
								s+= "  " + v[j].name;
							}
						}
		        	}
					return s;
		        }
		        },
		        {field:'createTime',title:'录入时间',width:10,align:'center'},
		        {field:'status',title:'操作',width:10,align:'center',formatter:function(v,r,i){
		        	if(v == -1){
		        		return "<a href='"+_ctx+"/servicestation/index.do?cmd=update&id="+r.id+"'>编辑</a>--<a href='javascript:void(0);' onclick='changeStatus("+r.id+",0)' style='color:green;'>开通</a>";
		        	}else if(v == 0){
		        		return "<a href='"+_ctx+"/servicestation/index.do?cmd=update&id="+r.id+"'>编辑</a>--<a href='javascript:void(0);' style='color:red;' onclick='changeStatus("+r.id+",-1)'>关闭</a>";
		        	}
		        }}
		        ]]/*,
		        onLoadSuccess:function(){
		        	$("a.service-detail").click(function(){
		        		var typeIndex = $(this).data("type-index");
		        		var index = $(this).data("index");
				    	var row = serviceStationDatagrid.datagrid("getRows")[index];
				    	var type = row['serviceTypes'][typeIndex];
				    	// 显示酒店饭店洗车的详细信息
				    	if(type.type == 1 || type.type==2 || type.type==6){
				    		showModal(type.name, _ctx+"/servicestation/servicedetail.do?type="+type.type+"&stationId="+row.id,type.type==6?600:'',type.type==6?150:'');
				    	}
		        	});
			    }*/
		}
	);
	
	// 搜索操作
	var search = function(){
		// TODO 验证表单参数
		// 获取搜索表单 
		// 封装表单参数
		var paramObj = searchForm.serializeJson();
		// 发送post请求刷新 datagrid 的数据
		serviceStationDatagrid.datagrid("load",paramObj);
	};
	// 设置搜索按钮的点击事件
	searchConfirm.click(search);
	doSearch.click(search);
	
	// 添加路线按钮点击事件
	addServiceStationBtn.click(function(){
		//跳转到用户 添加、编辑界面
		location.href = _ctx + "/servicestation/index.do?cmd=save";
	});
	
});