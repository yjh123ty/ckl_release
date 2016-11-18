


/**
 * 路线界面的js
 */
$(function(){
	// urls
	var getTemplateRoutesUrl = _ctx + "/route/getTemplateRoutes.do";
	
	// 缓存组件
	var lineSelect = $("#line-select");
	
	var routeEditForm = $("#form-route-edit"); 
	
	var modal = $('#msg-modal');
	
	// 缓存模板数据
	var templateRoutes = null;
	
	var routeId = $("#route-id");
	
	var routeSuitContainer = $("#route-suit-container");
	
	routeEditForm.validator({});
	
	if(!routeId.val()) {
		// 添加操作
		// 获取模板数据
		// 加载模板选择下拉列表
		// 加载表单数据
		$.post(getTemplateRoutesUrl, function(data){
			var len = data.length;
			var options = '';
			if(len){
				for (var i = 0; i < len; i++) {
					options += '<option value="'+ data[i].id +'">'+ data[i].name +'</option>';
				}
				// 初始化下拉列表
				lineSelect.html(options);
				templateRoutes = data;
				loadData(templateRoutes[0]);
			}
		},'json');
	}
	// 将站点列表转换为线路信息 xxx->xxx->xxx
	var stationsToDetail = function(v) {
		var len = v.length;
		if(len){
    		var detail = "";
    		var s = "->";
    		for (var i = 0; i < len; i++) {
				detail += v[i].name + (i== len-1 ? "" : s);
			}
    		return detail;
    	}else {
    		return "路线规划中...";
    	}
	};
	
	
	// 当选项变化时
	lineSelect.on("change", function(){
		var index = this.selectedIndex;
		loadData(templateRoutes[index]);
	});
	
	// 选中模板 回显数据
	var loadData = function(data) {
		if(data){
			data['intro'] = stationsToDetail(data.stations);
			for ( var name in data) {
				// 不回显 Id Id由自己控制
				if(name != 'id') {
					routeEditForm.find("[name="+ name +"]").val(data[name]);
				}
				
				// 回显 适合人群选项
				if(name == 'suits') {
					var suits = data[name];
					if(suits && suits.length) {
						for (var i = 0; i < suits.length; i++) {
							routeSuitContainer.find("input").each(function(index, input){
								if(input.value == suits[i].type){
									input.checked = true;
								}
							});
						}
					}
				}
			}
		}
	};
	
	$("#confirm").click(function(){
		/* 1.封装额外的参数
			获取用户选择的checkdid
			封装成menus的参数 menus[i].id=xxxx
			放入参数对象中
		3.验证表单
		4.使用ajax的方式提交
		5.成功返回上级界面刷新
		6.失败直接提示 */
		var form = $("#form-route-edit");
		// 表单提交的额外数据
		var data = {};
		// 封装适合人群的数据
		routeSuitContainer.find("input:checked").each(function(i, input){
			data["suits["+i+"].type"] = input.value;
			data["suits["+i+"].name"] = $(input).parent().text().trim();
		});
		
		form.ajaxSubmit( {    
		       url:       _ctx + "/route/edit.do", 
		       type:      "post",
		       data: data,        
		       clearForm: false,     
		       resetForm: false,     
		       beforeSubmit: function(){
		    	   return form.validator("isFormValid");
		       }, 
		       success: function(data){
		    	   if(isJsonObj(data)){
			    	   modal.find("#msg_content").html(data.msg);
			    	   modal.find(".data-am-modal-confirm").on("click", function(){
			    		   console.debug("alert 确定....");
				    	   if(data.success) {
				    		   location.href = _ctx + "/route/index.do";
				    	   }
			    	   });
			    	   modal.modal();
		    	  }else {
		    		  modal.find("#msg_content").html("服务器异常");
		    		  modal.modal();
		    	  }
		       } 
		   });
	});
}
);