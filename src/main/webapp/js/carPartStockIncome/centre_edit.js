/**
 * 零部件分类界面的js
 */

$(function() {
	var carPartStockIncomeConfirm = $("#car-part-stock-income-confirm");
	var carPartStockIncomeEditForm = $("#form-edit");
	var modal = $("#my-alert");
	
	carPartStockIncomeConfirm.click(function(){
		var data = {};
		carPartStockIncomeEditForm.ajaxSubmit({
			url : _ctx + "/carPartStockIncome/edit.do",
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
							location.href = _ctx+"/carPartStockIncome/index.do";
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