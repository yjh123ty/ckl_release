/**
 * 商品界面的js
 */

$(function() {
	var carConfirm = $("#car-confirm");
	var carPartEditForm = $("#form-edit");
	var modal = $("#my-alert");
	
	carConfirm.click(function(){
		var data = {};
		carPartEditForm.ajaxSubmit({
			url : _ctx + "/carPart/edit.do",
			type : "post",
			data : data,
			clearForm : false,
			resetForm : false,
			beforeSubmit : function() {
				// 校验参数
			},
			success : function(data) {
				if (isJsonObj(data)) {
					if(data.success) {
						modal.on('close.modal.amui',function(){
							location.href = _ctx+"/carPart/index.do";
						});
					}
					modal.find(".am-modal-bd").html(data.msg);
					modal.modal();
				} else {
					modal.find(".am-modal-bd").html("服务异常");
					modal.modal();
				}
			}
		});
	});
});