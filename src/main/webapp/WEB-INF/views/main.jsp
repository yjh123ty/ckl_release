<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>车刻丽运营平台</title>
<%@ include file="/WEB-INF/views/commons/head.jsp" %>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp" %>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp" %>
<script type="text/javascript" src="http://cdn.bootcss.com/iframe-resizer/3.5.5/iframeResizer.min.js"></script>
<script type="text/javascript">
	function openTreeItem(url,param){
		// 根据url地址找到 菜单项
		if(url) {
			var item = $("#mainTree").find("[data-url='" + url +"']");
			if(item){
				item.parent().collapse('open');
				var paramStr = '';
				if(param){
					paramStr = parseParam(param);
				}
				if(paramStr) {
					$("#mainIframe").attr("src", url+"?"+paramStr);
				}else {
					$("#mainIframe").attr("src", url);
				}
				$("#mainTree li").css("background-color", "white");
				item.css("background-color", "pink");
			}
		}
	}
	
	var str ="";
	$.get("${ctx}/main/getUserMenuTree.do", function(data){
		str += "<ul id='treeRoot' class='am-list admin-sidebar-list'>";
		str += "<li class='am-panel' data-url=${ctx}/main/mainInfo.do><a>"+
        "<i class='am-icon-home am-margin-left-sm'></i>首页<i class='am-icon-angle-right am-fr am-margin-right'></i>"+
        "</a></li>";
		for(var i = 0; i < data.length; i++){
			if(data[i].children.length === 0) {
				str += "<li class='am-panel' data-url='${ctx}"+ data[i].url + "'><a>"+
		        "<i class='am-icon-users am-margin-left-sm'></i>"+ data[i].name +"<i class='am-icon-angle-right am-fr am-margin-right'></i>"+
		        "</a></li>";
				continue;
			}
			str += "<li  class='am-panel'><a class='am-cf' data-am-collapse=\"{target: '#nav-"+ data[i].id +"'}\">"+
	        "<i class='am-icon-users am-margin-left-sm'></i>"+ data[i].name +"<i class='am-icon-angle-right am-fr am-margin-right'></i>"+
	        "</a>";
			var str2 = "<ul id='nav-"+ data[i].id +"' class='am-list am-collapse admin-sidebar-sub'>";
			for(var j = 0; j < data[i].children.length; j++){
				if(j === 0){
					str += str2;
				}
				if(j === data[i].children.length - 1) {
					str += "<li data-url='${ctx}"+ data[i].children[j].url + "'><a href='javascript:void(0);'><span class='am-icon-table am-margin-left-sm'></span>"+ data[i].children[j].name +"</a></li></li></ul>";
					continue;
				}
				str += "<li data-url='${ctx}"+ data[i].children[j].url + "'><a href='javascript:void(0);'><span class='am-icon-table am-margin-left-sm'></span>"+ data[i].children[j].name +"</a></li>";
			}
			str += "</li>";
		}
		str += "</ul>";
		$("#mainTree").append(str);
		openTreeItem("${ctx}/main/mainInfo.do");
	});
</script>

</head>
<body class="am-g">
<header class="am-topbar am-topbar-inverse admin-header" style="background-color: #1FBAA8; color:white;">
  <div class="am-topbar-brand">
    <img alt="logo" src="${ctx}/images/welcome_logo.png">
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
	
    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
     	 <li>
      		<a class="message-btn" href="javascript:void(0);"><span class="am-icon-tasks"></span> 任务
      				<span id="task-count" class="am-badge am-badge-warning">${taskCount==null || taskCount ==0  ? '暂无任务':taskCount}</span>
   			</a>
    	</li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span>${userInSession.realName}<span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="${ctx}/admin/index.do" target="mainIframe"><span class="am-icon-cog"></span> 修改密码</a></li>
          <li><a href="${ctx}/admin/logout.do" target="mainIframe"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
    </ul>
  </div>
</header>

<div  class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div style="overflow:hidden;"  class="am-offcanvas-bar admin-offcanvas-bar" id="mainTree">
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
	<div class="admin-content">
			<div class="admin-content-body">
		      <div class="am-cf am-padding-xs am-padding-bottom-0">
					<ol class="am-breadcrumb" id="nowModel">
						<li class="am-active">首页</li>
					</ol>
		    </div>
		    <hr style="margin: 0;"/>
			<iframe name="mainIframe" id="mainIframe" width="100%" height="auto" frameborder="0" scrolling="no"></iframe>  
		</div>
	<!-- content end -->
	</div>
</div>
<script type="text/javascript">
var mainIframe = $("#mainIframe");
$("#mainTree").on("click", "li", function(event){
	if($(this).children("ul").length === 0){
		event.stopPropagation();
		var url = $(this).attr("data-url");
		if(mainIframe.prop("src") != url) {
			mainIframe.prop("src",url);
			$("#mainTree li").css("background-color", "white");
			$(this).css("background-color", "pink");
		}
		if(url) {
			var parent = $(this).closest("li.am-panel").find("a.am-cf");
			var u = '<a href="'+url+'" target="mainIframe">'+$(this).text()+'</a>';
			$("#nowModel").html(parent.attr("id")!='treeRoot'&&parent.text() ?"<li><a href=\"javascript:void(0);\">"+parent.text()+"</a></li><li class=\"am-active\">"+u+"</li>" : " <li class=\"am-active\">"+u+"</li>");
		}
	}
});

var isOldIE = (navigator.userAgent.indexOf("MSIE") !== -1); // Detect IE10 and below

iFrameResize( {
	log:false,
	checkOrigin:false,
    heightCalculationMethod: isOldIE ? 'max' : 'lowestElement'
});

$(".message-btn").click(function(){
		try{
			var taskCount = parseInt($(this).find("#task-count").text());
			if(taskCount && taskCount > 0) {
				mainIframe.attr("src", _ctx+"/adminTask/index.do");
			}
		}catch (e) {
			
		}
});
</script>

<script src="//cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<script type="text/javascript">
 $(function(){
		//准备jswebsockt连接对象
		var websocket;
		var server = window.location.host+_ctx;
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://"+server+"/newtask.do");
		} else if ('MozWebSocket' in window) {
			websocket = new MozWebSocket("ws://" + server + "/newtask.do");
		} else {
			// 使用长轮询
			console.debug("use SockJS...");
			websocket = new SockJS("http://" + server + "/sockjs/newtask.do");
		}
		websocket.onopen = function(event) {
			console.debug("建立连接...");
		};
		websocket.onmessage = function(event) {
			var data = null;
			try {
				data = $.parseJSON(event.data);
			} catch (e) {
			}
			console.debug(data);
			if(data){
				var title = "你有新的任务";
				var msg="";
				if(data.type==1){
					title="你有新的sos求助";
				}
				if(data.type == 2){
					title ="你有新的退款";
				}
				if(data.content){
					msg = data.content;
				}
				$.messager.alert(title, msg, "info", function(){
						openTreeItem(_ctx+data.url);
				});
			}
		};
		websocket.onerror = function(event) {
			console.log(event);
		};
		websocket.onclose = function(event) {
			console.log(event);
		};
 });
</script>
</body>
</html>