$(function() {
	var routeSelect = $("#route-select");
	
	var createBtn = $("#btn-create");
	
	routeSelect.on("change", function(){
		location.href= _ctx + "/templatetravelnote/index.do?cmd=edit&routeId="+this.value;
	});
	
	createBtn.click(function(){
		location.href = _ctx + "/templatetravelnote/create.do?routeId="+routeSelect.val();
	});
});
