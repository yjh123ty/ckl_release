<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
<%@ include file="/WEB-INF/views/commons/head.jsp" %>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp" %>
</head>
<body>
   <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-gallery-imgbordered" data-am-gallery="{pureview:{target: 'a'}}" style="width:60px;">
  <li>
    <div class="am-gallery-item">
      <a href="https://farm4.staticflickr.com/3835/15329524682_554d4c0886_k.jpg">图片</a>
    </div>
  </li>
  <li>
    <div class="am-gallery-item">
      <a href="https://farm3.staticflickr.com/2941/15346557971_d8f3d52978_k.jpg">
      </a>
    </div>
  </li>
</ul>

<div data-am-widget="map" class="am-map am-map-default"
      data-name="云适配" data-address="北京市海淀区海淀大街27号亿景大厦3层西区" data-longitude="" data-latitude="" data-scaleControl="true" data-zoomControl="true" data-setZoom="17" data-icon="http://amuituku.qiniudn.com/mapicon.png">
    <div id="bd-map"></div>
  </div>
  <script type="text/javascript">
  		//准备jswebsockt连接对象
 		var websocket;
  		var server = "localhost:80/ckl";
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://"+server+"/newtask.do");
		} else if ('MozWebSocket' in window) {
			websocket = new MozWebSocket("ws://" + server + "/newtask.do");
		} else {
			websocket = new SockJS("http://" + server + "/sockjs/newtask.do");
		}
		websocket.onopen = function(event) {
			console.log("WebSocket:已连接");
			console.log(event);
		};
		websocket.onmessage = function(event) {
			var data=JSON.parse(event.data);
			console.debug(data);
		};
		websocket.onerror = function(event) {
			console.log("WebSocket:发生错误 ");
			console.log(event);
		};
		websocket.onclose = function(event) {
			console.log("WebSocket:已关闭");
			console.log(event);
		};
	</script>
</body>
</html>