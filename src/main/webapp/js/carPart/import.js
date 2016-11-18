/**
 * 商品界面的js
 */

$(function() {
	var carConfirm = $("#car-confirm");
	var carPartImportForm = $("#form-import");
	var modal = $("#my-alert");
	
	carConfirm.click(function(){
		var data = {};
		carPartImportForm.ajaxSubmit({
			url : _ctx + "/carPart/import.do",
			type : "post",
			data : data,
			clearForm : false,
			resetForm : false,
			beforeSubmit : function() {
				var fileInput=carPartImportForm.find("[name='file']");
				if(fileInput.val()){
					// 校验参数
					var file = fileInput[0].files[0];
					if(file.type == "application/vnd.ms-excel"||file.type=="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"){
						return true;
					}else {
						modal.find(".am-modal-bd").html("文件类型有误！");
						modal.modal();
						return false;
					}
				}else {
					modal.find(".am-modal-bd").html("文件不能为空！");
					modal.modal();
					return false;
				}
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