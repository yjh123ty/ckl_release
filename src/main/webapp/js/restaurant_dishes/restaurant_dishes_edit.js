/**
 * 饭店菜品js
 */

$(function(){
	
	// 变量、常量声明
	var getRestaurantListUrl = _ctx+"/restaurant/findRestaurantsSimpleInfo.do";
	
	// 缓存组件
	var restaurantSelect = $("#restaurant-select");
	
	var dishesForm = $("#form-dishes");
	
	var msgModal = $("#msg-modal");
	
	dishesForm.validator({});
	
	// 初始化下拉列表
	$.get(getRestaurantListUrl,function(data){
		var s = "";
		var selectId = restaurantSelect.data("id");
		if(data) {
			var len = data.length;
			if(len){
				for (var i = 0; i < len; i++) {
					if(selectId && selectId == data[i].id){
						s += "<option value='" + data[i].id +"' selected='selected'>" + data[i].name+"</option>";
					}else {
						s += "<option value='" + data[i].id +"'>" + data[i].name+"</option>";
					}
				}
				restaurantSelect.html(s);
			}
		}
	},"json");
	
	// 按钮事件监听
	var btnClickListener = {
		'edit-confrim':function(btn) {
			dishesForm.ajaxSubmit({
				url : _ctx + "/restaurantdishes/edit.do",
				type : "post",
				clearForm : false,
				resetForm : false,
				beforeSubmit : function() {
					return dishesForm.validator("isFormValid");
				},
				success : function(data) {
					if (isJsonObj(data)) {
						msgModal.find("#msg_content").html(data.msg);
						msgModal.find(".data-am-modal-confirm").on(
								"click",
								function() {
									if (data.success) {
										location.href = _ctx + "/restaurantdishes/index.do";
									}
								});
						msgModal.modal();
					} else {
						msgModal.find("#msg_content").html("服务器异常");
						msgModal.modal();
					}
				}
			});
		}
	};
	$("[data-cmd]").click(function(){
		var cmd = $(this).data("cmd");
		if(cmd) {
			btnClickListener[cmd](this);
		}
	});
	
	
});