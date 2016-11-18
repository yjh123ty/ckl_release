/**
 * 
 */

$(function(){
	var carCarePriceForm = $("#form-carcareprice-edit");
	var modal = $("#my-alert");
	carCarePriceForm.validator({});
	
	$("#confirm").click(function(){
		carCarePriceForm.ajaxSubmit({
			url : _ctx + "/carcareprice/edit.do",
			type : "post",
			data : {},
			clearForm : false,
			resetForm : false,
			beforeSubmit : function() {
				// 校验参数
				return carCarePriceForm.validator("isFormValid");
			},
			success : function(data) {
				if (isJsonObj(data)) {
					if(data.success) {
						location.href=_ctx+"/carcareprice/index.do";
					} else {
						modal.find(".am-modal-bd").html(data.msg);
						modal.modal();
					}
				} else {
					modal.find(".am-modal-bd").html("系统异常");
					modal.modal();
				}
			}
		});
	});
});