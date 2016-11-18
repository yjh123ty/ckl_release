/**
 * 零部件分类界面的js
 */

$(function() {
	var carTypeConfirm = $("#car-type-confirm");
	var carPartTypeEditForm = $("#form-edit");
	var modal = $("#my-alert");
	
	carTypeConfirm.click(function(){
		var data = {};
		carPartTypeEditForm.ajaxSubmit({
			url : _ctx + "/carPartType/edit.do",
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
							location.href = _ctx+"/carPartType/index.do";
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