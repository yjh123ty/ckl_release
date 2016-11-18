/**
 * 修改密码界面的js
 */

$(function(){
	
	var backBtn = $(".am-back");
	backBtn.click(function(){
		window.top.location.href= _ctx + '/main/index.do';
	});
	
	var changePasswordForm = $("#form-admin-change-password");
	var msgModal = $("#msg-modal");
	var confirmBtn =$("#confirm");
	changePasswordForm.validator();
	confirmBtn.click(function(){
		changePasswordForm.ajaxSubmit({
			url : _ctx + "/admin/changePassword.do",
			type : "post",
			clearForm : false,
			resetForm : false,
			beforeSubmit : function() {
				return changePasswordForm.validator('isFormValid');
			},
			success : function(data) {
				if (isJsonObj(data)) {
					if(data.success) {
						msgModal.on('close.modal.amui',function(){
							window.top.location.href = _ctx+"/admin/logout.do";
						});
					}
					msgModal.find(".am-modal-bd").html(data.msg);
					msgModal.modal();
				} else {
					msgModal.find(".am-modal-bd").html("服务异常");
					msgModal.modal();
				}
			}
		});
	});
	
});