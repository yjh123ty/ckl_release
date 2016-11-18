/**
 * 菜单界面的js
 */
$(function(){
	// 缓存界面的easy UI 控件
	
	// 菜单表格
	var carCarePriceDatagrid = $("#car-care-price-datagrid");
	
	// 公共变量
	// 获取列表数据的url
	var listUrl = _ctx + "/carcareprice/list.do";
	
	// 添加/修改的url
	var editUrl= _ctx + "/carcareprice/edit.do";
	
	// 菜单删除url
	var deleteUrl= _ctx + "/carcareprice/delete.do";
	
	var btnClickObj = {
		'delete': function(btn){
			var id = $(btn).data("index");
			if(id){
				$.messager.confirm('你确定要删除吗？', '删除后不可恢复！', function(r){
					if (r){
							$.post(deleteUrl, {id:id}, function(data){
								if(data){
									if(data.success){
										carCarePriceDatagrid.datagrid("reload");
									} else {
										msgShow("温馨提示", "服务器异常,请稍后再试!", "warn");
									}
								}else {
									msgShow("提示", "服务器异常,请稍后再试!", "warn");
								}
							}, "json");
						}
				});
			}else {
				msgShow("提示", "参数异常,请稍后再试!", "warn");
			}
		}
	};
	

	// 初始化 数据表格
	carCarePriceDatagrid.datagrid(
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
		        {field:'name',title:'名称',width:80,align:'center'},    
		        {field:'price',title:'价格',width:80,align:'center'},
		        {field:'content',title:'服务内容',width:80,align:'center'},
		        {field:'createTime',title:'录入时间',width:160,align:'left'},
		        {field:'xxxx',title:'操作',width:160,align:'left',formatter:function(v,r,i){
		        	return "<a href='"+_ctx+"/carcareprice/index.do?cmd=update&id="+r.id+"'>编辑</a>--<a href='javascript:void(0);' data-cmd='delete' data-index='"+r.id+"' style='color:green;'>删除</a>";
		        }}
		    ]],
		    onLoadSuccess:function(){
		    	//监听所有的自己设置的按钮  根据按钮上data-cmd 来设置按钮的事件
		    	$("[data-cmd]").click(function(){
		    		//获取方法的名字
		    		var methodName = $(this).data("cmd");
		    		//根据方法名 btnClickMethodsObj 对象来调用方法
		    		if(methodName && btnClickObj[methodName]){
		    			btnClickObj[methodName](this);
		    		}
		    	});
		    }
		}
	);
	
});