/**
 * 饭店菜品js
 */

$(function(){
	
	// 变量、常量声明
	var listUrl = _ctx+"/restaurantdishes/list.do";
	
	var deleteUrl = _ctx + "/restaurantdishes/delete.do";
	
	// 缓存组件
	var searchForm = $("#restaurantdishes-search-form");
	
	var dishesDatagrid = $("#restaurantdishes-datagrid");
	
	var msgModal = $("#msg-modal");
	
	// 按钮事件监听
	var btnClickListener = {
		"do-search":function(btn){
			var paramObj = searchForm.serializeJson();
			dishesDatagrid.datagrid("load",paramObj);
		}
	};
	$("[data-cmd]").click(function(){
		var cmd = $(this).data("cmd");
		if(cmd) {
			btnClickListener[cmd](this);
		}
	});
	
	// 初始化数据表格
	dishesDatagrid.datagrid({
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
	    multiSort:false,
	    pageList:[10,15],
	    // 表头属性
	    columns:[[    
	              {field:'name',title:'菜品名称',width:10,align:'center'},
	              {field:'price',title:'菜品价格',width:10,align:'center'},
	              {field:'restaurantName',title:'菜品所在饭店',width:10,align:'center'},
	              {field:'img',title:'图片',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.img){
			        		var picArry=JSON.stringify(r.img);
				        	return "<a href='javascript:void(0);' onclick='scanPic(this)' src='"+picArry+"'>查看图片</a>";
			        	}else{
			        		return '暂无图片';
			        	}
			        }},
	              {field:'xxx',title:'操作',width:10,align:'center',formatter:function(v,r,i){
	            	 return "<a href='"+_ctx+"/restaurantdishes/index.do?cmd=update&id="+r.id+"'>编辑</a> <a href='javascript:void(0);' class='delete-dishes' data-id='"+r.id+"'>删除</a>";  
	              }}
		        ]],
        onLoadSuccess:function(){
        	$(".delete-dishes").click(function(){
        		var id = $(this).data("id");
        			$.post(
        				deleteUrl,
        				{id:id},
        				function(data) {
        					if (data) {
        						msgModal.find("#msg_content").html(data.msg);
        						msgModal.modal();
        						if(data.success) {
        							dishesDatagrid.datagrid("reload");
        						}
        					} else {
        						msgModal.find("#msg_content").html("服务器异常");
        						msgModal.modal();
        					}
        				},
        				"json"
        			);
        	});
        }
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