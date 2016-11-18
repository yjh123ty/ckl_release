/**
 * 零部件分类界面的js
 */

$(function() {
	var carPartDepotConfirm = $("#car-depot-confirm");
	var carPartDepotEditForm = $("#form-edit");
	var modal = $("#my-alert");
	
	carPartDepotEditForm.validator({});
	
	var stationSelectDiv = $("#station-select");
	var stationSelect = stationSelectDiv.find("select")[0];
	
	$("input[name='address']").val($(stationSelect[0]).attr("address"));
	
	
	//仓库选择的世界动作
	$("input[name='centre']").on("change", function(){
		console.debug(this.value);
		if(this.value=='true'){
			// 中央仓库
			// 关闭并重置服务站输入的内容
			stationSelectDiv.hide();
			$("input[name='address']").val('');
			stationSelect.selectedIndex = 0;
		}
		if(this.value == 'false'){
			//服务站仓库
			// 显示服务站选择框
			// 设置地址框中的内容默认为服务站的地址
			stationSelectDiv.show();
			$("input[name='address']").val($(stationSelect[0]).attr("address"));
		}
	});
	
	$(stationSelect).on("change", function(){
		$("input[name='address']").val($(this[this.selectedIndex]).attr("address"));
	});
	
	carPartDepotConfirm.click(function(){
		var data = {};
		carPartDepotEditForm.ajaxSubmit({
			url : _ctx + "/carPartDepot/edit.do",
			type : "post",
			data : data,
			clearForm : false,
			resetForm : false,
			beforeSubmit : function() {
				// 校验参数
				return carPartDepotEditForm.validator("isFormValid");
			},
			success : function(data) {
				if (isJsonObj(data)) {
					if(data.success) {
						modal.on('close.modal.amui',function(){
							location.href = _ctx+"/carPartDepot/index.do";
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