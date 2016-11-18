/**
 * 员工界面的js
 */

function changeStatus(rowId,flag){	//行id,是否冻结（0 开通；-1 冻结）
	
	//若为开通状态，则改为禁用
	if(flag === 0){
		//发送请求，更改状态
		$.messager.confirm("温馨提示", "是否需要禁用？", function(yes) {
			if (yes) {
				$.post(_ctx + "/employee/disable.do", {
					"id" : rowId
				}, function(data) {
					// 判断
					if (data.success) {
						// 提示
						$.messager.alert("温馨提示", data.msg, "info",
							function() {
								//刷新页面
								$("#employee-datagrid").datagrid("reload");
							});
					} else {
						$.messager.alert("温馨提示", data.msg, "info");
					}
				}, "json");
			}
		});
	}
	
	//若为禁用状态，则改为开通
	if(flag === -1){
		//发送请求，更改状态
		$.messager.confirm("温馨提示", "是否需要开通？", function(yes) {
			if (yes) {
				$.post(_ctx + "/employee/invoke.do", {
					"id" : rowId
				}, function(data) {
					// 判断
					if (data.success) {
						// 提示
						$.messager.alert("温馨提示", data.msg, "info",
							function() {
								//刷新页面
								$("#employee-datagrid").datagrid("reload");
							});
					} else {
						$.messager.alert("温馨提示", data.msg, "info");
					}
				}, "json");
			}
		});
	}
}

//页面状态展示
function statusFormatter(value,row,index){
	var str = "";
	switch (value) {
	case 0:
		str = "<button onclick='changeStatus("+row.id+","+value+")' style='color:red'>冻结</button>&emsp;";
		str += "<a href="+ _ctx + "/employee/index.do?cmd=update&id="+ row.id +">编辑</a>";
		break;

	case -1:
		str = "<button onclick='changeStatus("+row.id+","+value+")' style='color:green'>开通</button>&emsp;";
		str += "<a href="+ _ctx + "/employee/index.do?cmd=update&id="+ row.id +">编辑</a>";
		break;
	}
	return str;
	
}

$(function() {

	//1、声明页面需要使用的组件
	var employeeGrid, employeeSearchForm;
	//2、缓存组件
	employeeGrid = $("#employee-datagrid");
	employeeSearchForm = $("#employee-search-form");
	
	//3、初始化组件
	// 初始化 数据表格
	employeeGrid.datagrid(
		{
			//请求地址
		    url:_ctx + "/employee/list.do",
		    //大小自适应
		    fit:false,
		    //无边框
		    border:false,
		    //内容适应列的大小
		    fitColumns:true,
		    //不自动高度
		    autoRowHeight:false,
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
		    //是否可以多列排序
		    multiSort:true,
		    // 表头属性
		    columns:[[  
		        {field:'emp_number',title:'工号',width:80,align:'center',formatter:function(v,r,i){
		        	return r.empNumber?r.empNumber : "";
		        },sortable:true,order:'asc'},      
		        {field:'mobile',title:'员工账号',width:80,align:'center',formatter:function(v,r,i){
		        	return "<a href="+ _ctx + "/employee/index.do?cmd=center&id="+ r.id +" target=_blank>"+v+"</a>";
		        }},    
		        {field:'realName',title:'员工姓名',width:80,align:'center'},
		        {field:'station',title:'所在服务站',width:160,align:'center',formatter:function(v,r,i){
	        		if(r.jobTitle != null && r.jobTitle.id <= 4){
	        			if(r.employee){
	        				return r.employee.stations[0].name;
	        			}else{
	        				return "";
	        			}
	        		}else if(r.jobTitle != null && r.jobTitle.id > 4){
	        			if(r.station){
	        				return r.station.name?r.station.name:'';
	        			}else{
	        				return '';
	        			}
	        		}
		        	else{
		        		return "";
		        	}
		        	
		        }},
		        {field:'stations',title:'管理的服务站',width:160,align:'center',formatter:function(v,r,i){
	        		if(r.jobTitle != null && r.jobTitle.id <= 4){
	        			var len = r.stations.length*1;
	        			var result = '';
	        			if(len){
	        				for(var i=0*1;i<len;i++){
	        					if(i == len - 1){
	        						result += r.stations[i].name;
	        					}else{
	        						result += r.stations[i].name + ' ，';
	        					}
	        				}
	        			}
        				return result;
	        		}else{
		        		return "";
		        	}
		        }},
		        {field:'jobTitle',title:'岗位类别',width:80,align:'center',formatter:objectFormatter},
		        {field:'salaryLevel',title:'薪点级别',width:80,align:'center'},
		        {field:'createTime',title:'注册时间',width:80,align:'center'},
		        {field:'status',title:'操作',width:160,align:'center',formatter:statusFormatter}
		    ]],
		    onLoadSuccess:function(){
		    	
		    }
		    
		}
	);
	
});



