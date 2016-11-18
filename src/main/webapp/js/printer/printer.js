/**
 * 云打印机界面的js
 */

$(function() {
	//1、声明页面需要使用的组件
	var printerGrid,printerSearchForm;
	//2、缓存组件
	printerGrid = $("#printer-datagrid");
	printerSearchForm = $("#printer-search-form");
	//3、初始化组件
	printerGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/printer/list.do",
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
			    pageList:[10,15],
			    // 表头属性
			    columns:[[    
			        {field:'name',title:'打印机名称',width:80,align:'center'}, 
			        {field:'partner',title:'用户id',width:80,align:'center'},
			        {field:'machineCode',title:'打印机终端号',width:80,align:'center'},
			        {field:'apiKey',title:'API密钥',width:80,align:'center'},
			        {field:'printerKey',title:'打印机密钥',width:80,align:'center'},
			        {field:'status',title:'打印机状态',width:80,align:'center',formatter:function(v,r,i){
			        	if(r.status){
			        		switch(r.status){
				        		case 0:
			        				 result = "离线";
				        			 break;
				        		case 1:
					        		 result = "在线使用";
					        		 break;
				        		case 2:
					        		 result = "缺纸";
					        		 break;
			        		}
			        		return result;
			        	}else{
			        		return "";
			        	}
			        }},
			        {field:'station',title:'所在服务站',width:80,align:'center',formatter:function(v,r,i){
			        	if(r.station){
			        		return r.station.name;
			        	}else{
			        		return "";
			        	}
			        }},
			        {field:'xxx',title:'操作',width:80,align:'center',formatter:function(v,r,i){
			        	return "<a href="+ _ctx + "/printer/index.do?cmd=update&id="+ r.id +">编辑</a>";
			        }}
			    ]],
			}
		);

});