/**
 * 服务站修改界面的js
 */
$(function(){
	// 缓存界面组件
	var serviceStationEditForm = $("#form-tourist-spot-edit");
	
	serviceStationEditForm.validator();
	
	//提交表单
	var submitEditForm = function (successDel){
    	var data = {};
    	var districtAddress = '';
    	var p = $("#addr-prov").find("option:selected").text().trim();
    	var c = $("#addr-city").find("option:selected").text().trim();
    	var d = $("#addr-dist").find("option:selected").text().trim();
    	if(p&&c&&d){
    		if(p==c){
    			districtAddress=p+d;
    		}else {
    			districtAddress=p+c+d;
    		}
    	}
    	data['districtAddress']=districtAddress;
    	// 设置额外的数据防止修改数据丢失 只有修改时才这么做
    	serviceStationEditForm.ajaxSubmit( {    
    	       url:       _ctx + "/touristspot/edit.do", 
    	       type:      "post",
    	       data: data,        
    	       clearForm: false,     
    	       resetForm: false,     
    	       beforeSubmit: function(){
    	    	   // 校验参数
    	    	   return serviceStationEditForm.validator("isFormValid");
    	       }, 
    	       success: function(data){
    	    	   successDel(data);
    	       }
    	   });
    };

	
	// 按钮事件统一监控对象
	var btnClickObj = {
		'confirm':function(){
			submitEditForm(function(data){
			   var title = "提示";
  	    	   var content = '';
  	    	   if(isJsonObj(data)){
  	    		   if(data.success) {
  	    			   location.href = _ctx + "/touristspot/index.do";
  	    			   return;
  	    		   }
  	    		   content = data.msg;
  	    	   }
  	    	   msgShow(title, content ? content : '服务器异常', "info");
			});
		}
	
	};
	
	// 统一监听按钮的事件
	$("[data-cmd]").click(function(){
		var cmd = $(this).data("cmd");
		if(cmd &&  btnClickObj[cmd]){
			btnClickObj[cmd](this);
		};
	});
	
});

//加载省 // 选择省动态加载市  //选择市动态加载 县
function addressInit(p,c,d){
	var proSelect = $("#addr-prov");
	var citySelect = $("#addr-city");
	var distSelect = $("#addr-dist");
	proSelect.html("<option value='-1'>---请选择省---</option>");
	citySelect.html("<option value='-1'>---请选择市---</option>");
	distSelect.html("<option value='-1'>---请选择区---</option>");
	
	
	// 初始化省 //回显省市区
	loadAddr("addr-prov", _ctx+"/address/getProvinces", "<option value='-1'>---请选择省---</option>",function(){
		if(p){
			// 选中省
			proSelect.val(p);
			// 初始化市
			loadAddr("addr-city", _ctx+"/address/getCitys?code="+p, "<option value='-1'>---请选择市---</option>",function(){
				if(c){
					// 选中市
					citySelect.val(c);
					// 初始化区
					loadAddr("addr-dist", _ctx+"/address/getDistricts?code="+c, "<option value='-1'>---请选择区---</option>",function(){
						if(d) {
							// 选中区
							distSelect.val(d);
						}
					});
				}
			});
		}
	});
	
	proSelect.on("change",function(){
		citySelect.html("<option value='-1'>---请选择市---</option>");
		distSelect.html("<option value='-1'>---请选择区---</option>");
		loadAddr("addr-city", _ctx+"/address/getCitys?code="+this.value, "<option value='-1'>---请选择市---</option>");
	});
	citySelect.on("change",function(){
		distSelect.html("<option value='-1'>---请选择区---</option>");
		loadAddr("addr-dist", _ctx+"/address/getDistricts?code="+this.value, "<option value='-1'>---请选择区---</option>");
	});
}

