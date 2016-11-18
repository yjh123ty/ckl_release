/**
 * 零部件分类界面的js
 */

$(function() {
	var carPartStockOutcomeConfirm = $("#car-part-stock-outcome-confirm");
	var carPartStockOutcomeEditForm = $("#form-edit");
	var modal = $("#my-alert");
	
	carPartStockOutcomeConfirm.click(function(){
		var data = {};
		carPartStockOutcomeEditForm.ajaxSubmit({
			url : _ctx + "/carPartStockOutcome/edit.do",
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
							location.href = _ctx+"/carPartStockOutcome/index.do";
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