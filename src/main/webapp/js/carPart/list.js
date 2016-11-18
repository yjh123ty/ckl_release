/**
 * 商品界面的js
 */

$(function() {
	//1、缓存组件
	var carPartGrid = $("#carPart-datagrid");
	var carPartSearchForm = $("#carPart-search-form");
	var searchConfirm = $("#search-confirm");
	
	var importExcelBtn = $("#import-excel");
	
	//3、初始化组件
	carPartGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/carPart/list.do",
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
			    pageList:[10,15],
			    //滚动条宽度
			    scrollbarSize:20,
			    // 表头属性
			    columns:[[    
	                {field:'sn',title:'商品类别编码',width:80,align:'center'},
			        {field:'name',title:'名称',width:80,align:'center'},    
			        {field:'xxxx',title:'分类',width:80,align:'center',formatter:function(v,r,i){
			        	if(r.type){
			        		return r.type.name;
			        	}
			        	return "暂无分类";
			        }},
			        {field:'salePrice',title:'销售价(元)',width:80,align:'center'},
			        {field:'model',title:'规格',width:80,align:'center'},
			        {field:'bbbb',title:'计量单位',width:80,align:'center',formatter:function(v,r,i){
			        	return r.unit && r.unit.name ? r.unit.name : '';
			        }},
			        {field:'pic',title:'商品图片',width:80,align:'center',formatter:function(v,r,i){
			        	var picArry=r.pic;
			        	return "<a href='javascript:void(0);' onclick='scanPic(this)' src='"+picArry+"'>查看图片</a>";
			        }},
			        {field:'centreOutPrice',title:'中央仓库出库价格',width:80,align:'center',formatter:function(v,r,i){
			        	return v;
			        }},
			        {field:'oldMonths',title:'老化月份',width:80,align:'center',formatter:function(v,r,i){
			        	return v;
			        }},
			        {field:'remark',title:'备注',width:80,align:'center'},
			        {field:'createTime',title:'创建日期',width:80,align:'center',formatter:function(v,r,i){
			        	return r.createTime?r.createTime : "";
			        }},
			        {field:'xxx',title:'操作',width:80,align:'center',formatter:function(v,r,i){
			        	return "<a href="+ _ctx + "/carPart/index.do?cmd=update&id="+ r.id +">编辑</a>" + "--" + "<a href='#' class='deleteCarPart' data-index='"+ r.id +"')><font color='red'>删除</font></a>";
			        }}
			    ]],
			    onLoadSuccess:function(){
			    	//删除一条数据
			    	$('.deleteCarPart').click(function(data){
			    		var rowId = $(this).data("index");
			    		$.messager.confirm("温馨提示", "是否删除？", function(yes) {
							if (yes) {
								$.post(_ctx + "/carPart/delete.do", {"id" : rowId}, 
									function(data) {
										// 判断
										if (data.success) {
											// 提示
											$.messager.alert("温馨提示", data.msg, "info",
												function() {
													//刷新页面
													carPartGrid.datagrid("reload");
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
	
	searchConfirm.click(function(){
		var paramObj = carPartSearchForm.serializeJson();
		carPartGrid.datagrid('load', paramObj);
	});
	
	importExcelBtn.click(function(){
		
	});

});


//图片预览
function scanPic(othis){
	var pic=($(othis).attr('src'));
	var myEle='<div id="picDivCom"><span onclick="picClose(this)" id="chacha">x</span></div>';
	$('body').append(myEle);
	$('#picDivCom').append('<img src="'+img_host+pic+'"/>');

}
function picClose(othis){
	$(othis).parent().remove();
}

