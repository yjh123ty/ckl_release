// 序列化查询表单参数，为一个JSON对象
$.fn.serializeJson=function(){
	// 获取查询表单内容
	var paramArr = $(this).serializeArray();
	// 把内容变成json格式
	var paramObj = {};
	$.each(paramArr,function(i,obj){ // { name="searchKey", value="ad"}
		//console.debug(arguments);
		paramObj[obj.name] = obj.value;
	});
	
	return paramObj;
};

// 将json对象转换成url参数 
var parseParam=function(param, key){ 
	  var paramStr=""; 

	  if(param instanceof String||param instanceof Number||param instanceof Boolean){ 

	    paramStr+="&"+key+"="+encodeURIComponent(param); 

	  }else{ 

	    $.each(param,function(i){ 

	      var k=key==null ? i : key+(param instanceof Array ? "["+i+"]":"."+i); 

	      paramStr+='&'+parseParam(this, k); 

	    }); 

	  } 
	  return paramStr.substr(1); 
};

// 公共的对象格式化
function objectFormatter(v,r,i){
	return v?v.name||v.nickName||v.realName||v.username||v.resource:"";
}


function genderFormatter(v,r,i){
	switch (v) {
	case 0:
		return '男';
		break;
	case 1:
		return '女';
		break;

	default:
		return '未知';
		break;
	}
}

function statusFormatter(v,r,i){
	switch (v) {
	case 0:
		return '开通';
		break;
	case -1:
		return '冻结';
		break;
		
	default:
		return '未知';
	break;
	}
}

/**
 * 	消息框js
 */
//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

/**
 * 	判断一个数据是否是json对象 不能判断是否json数组
 */
function isJsonObj(data){
	return typeof(data) == "object" &&  Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length;
}

/**
 * id 下拉列表的id
 * url 请求数据的url
 * init 初始下拉项 不选择的项
 * loadAddr("addr-prov", _ctx+"/address/getProvinces", "<option value='-1'>---请选择省---</option>");
 * method  初始化后执行的方法
 */
function loadAddr(id, url, init, method){
	var select = $("#"+id);
	$.get(url, function(data){
			var len = data.length;
			var s = init;
			if(len) {
				for (var i = 0; i < len; i++) {
					s+="<option value='"+data[i].code+"'>"+data[i].name+"</option>";
				}
			}
			select.html(s);
			if(method){
				method();
			}
	},"json");
};


/**
 * 下载
 * @param form 下载提交的查询表单
 * @param url	下载的domain
 */
function downloadDomain(form,url){
	//修改表单提交的url地址
	$("#"+ form +"").attr("action", _ctx +"/"+ url + "/download.do");
	$("#"+ form +"").submit();
	//变回查询地址
	$("#"+ form +"").attr("action", _ctx +"/" + url + "index.do");
}

$.ajaxSetup({ 
	contentType:"application/x-www-form-urlencoded;charset=utf-8",
	 complete : function(XMLHttpRequest, textStatus) {
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头,sessionstatus，
		if (sessionstatus == 'timeout') {
			window.top.location.href = _ctx + "/login/index.do"; //跳转到登陆页面
		}
	},
	error:function(XMLHttpRequest, textStatus){
		console.debug(XMLHttpRequest);
		msgShow("提示", "参数错误", "error");
	}
});

