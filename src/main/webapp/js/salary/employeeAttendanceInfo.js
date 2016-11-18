/**
* 基础工资标准的js
*/

$(function() {
	//1、声明页面需要使用的组件
	var employeeAttendanceInfoGrid,employeeAttendanceInfoSearchForm;
	//2、缓存组件
	employeeAttendanceInfoGrid = $("#employeeAttendanceInfo-datagrid");
	employeeAttendanceInfoSearchForm = $("#employeeAttendanceInfo-search-form");
	
	// 摸态框
	var modal = $('#days-modal');
	// 模态框内容
	var content=modal.find("#content");
	var showModal = function(url) {
    	// 显示服务详情
		content.attr("src", url);
    	modal.modal();
	};
	
	//3、初始化组件
	employeeAttendanceInfoGrid.datagrid(
			{
				//请求地址
			    url:_ctx + "/employeeAttendanceInfo/list.do",
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
			    // 表头属性
			    columns:[[    
	              	{field:'empNumber',title:'员工工号',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.employee){
			        		return r.employee.empNumber?r.employee.empNumber:'';
			        	}
			        	return null;
			        }},
			        {field:'null',title:'员工账号',width:10,align:'center',formatter:function(v,r,i){
			        	if(r.employee){
			        		return "<a href="+ _ctx + "/employee/index.do?cmd=center&id="+ r.employee.id +"  target=_blank>"+r.employee.mobile+"</a>";
			        	}
			        	return null;
			        }},
			        {field:'employee',title:'员工姓名',width:10,align:'center',formatter:objectFormatter},
			        {field:'jobTitle',title:'岗位类别',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.employee){
			        		return r.employee.jobTitle?r.employee.jobTitle.name:'';
			        	}
			        	return null;
			        }},
			        {field:'salaryLevel',title:'薪点级别',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.employee){
			        		return r.employee.salaryLevel?r.employee.salaryLevel:'';
			        	}
			        	return null;
			        }},
			        {field:'station',title:'所在服务站',width:10,align:'center',formatter:function(v,r,i){
		        		if(r.employee){
			        		return r.employee.station?r.employee.station.name:'';
			        	}
			        	return null;
			        }},
			        {field:'xx',title:'管理的服务站',width:20,align:'center',formatter:function(v,r,i){
			        	if(r.employee){
			        		if(r.employee.jobTitle != null && r.employee.jobTitle.id <= 4){
			        			var len = r.employee.stations.length*1;
			        			var result = '';
			        			if(len){
			        				for(var i=0*1;i<len;i++){
			        					console.debug(r.employee.stations[i].name);
			        					if(i == len - 1){
			        						result += r.employee.stations[i].name;
			        					}else{
			        						result += r.employee.stations[i].name + ' ，';
			        					}
			        				}
			        			}
			        			return result;
			        		}else{
			        			return "";
			        		}
			        	}
			        	return "";
			        }},
			        {field:'days',title:'本月上班天数',width:10,align:'center',formatter:function(v, r, i){
			        	if(v && r.employee.id && r.recordMonth){
			        		return "<a href="+ _ctx + "/employeeAttendance/index.do?employeeId="+r.employee.id+"&recordMonth="+r.recordMonth+">"+v+"</a>";
			        	}else{
			        		return "";
			        	}
			        }},
			        {field:'record_month',title:'考勤月份',width:10,align:'center',formatter:function(v,r,i){
			        	return r.recordMonth?r.recordMonth : "";
			        }}
			    ]]
			}
		);

});