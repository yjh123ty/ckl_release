$(function() {
	var confirmBtn = $("#confirm");
	var templateForm = $("#template-travel-note-form");
	var imgContent = $("#imgContent");
	var textContent = $("#textContent");
	var routeId = $("#route-id");
	var modal = $("#msg-modal");
	
	templateForm.validator({});
	$("input[name='type']").on("change", function(){
		if(this.value == 1) {
			imgContent.hide();
			textContent.show();
		} else if(this.value == 2) {
			textContent.hide();
			imgContent.show();
		}
	});
	confirmBtn.click(function(){
		templateForm.ajaxSubmit({
		   url:_ctx+"/templatetravelnote/editContent.do",
		   data:{id:{}},
	       clearForm: false,     
	       resetForm: false,     
	       beforeSubmit: function(){
	    	   return templateForm.validator("isFormValid");
	       }, 
	       success: function(data){
	    	   if(isJsonObj(data)){
		    	   modal.find("#msg_content").html(data.msg);
		    	   modal.find(".data-am-modal-confirm").on("click", function(){
			    	   if(data.success) {
			    		   location.href = _ctx + "/templatetravelnote/index.do?cmd=edit&routeId="+routeId.val();
			    	   }
		    	   });
		    	   modal.modal();
	    	  }else {
	    		  modal.find("#msg_content").html("服务器异常");
	    		  modal.modal();
	    	  }
	       } 
		});
	});
});
