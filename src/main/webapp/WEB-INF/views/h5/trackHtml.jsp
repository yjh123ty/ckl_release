<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>轨迹分享</title>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
   <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
<script type="text/javascript"
src="http://webapi.amap.com/maps?v=1.3&key=514e9631f101a8624118e68be61e85af"></script>
<style type="text/css">
	.ckl{
		z-index: 999; position: absolute; display: inline-block;  top: 20px; left: 20px; background-color: #6A6968; padding: 0 10px;filter:alpha(Opacity=70);-moz-opacity:0.7;opacity: 0.7;color: white;font-size: 12px;
	}
	.ckl-text,.ckl{
		border-radius:300px;
	}
	.ckl-text{
		font-size: 16px;
		background: #00B9b7;
		padding:0 5px;
		margin-right: 5px;
	}
	.ckl-date{
		display: inline-block;
	}
	.track-info{
		background-color: #eee;position: absolute; z-index: 999; width: 100%; bottom: 0px;
	}
	.distance-info{
		color: white;
	}
</style>
</head>
   <!--
   {
  "trackId": 43,
  "date": "10月28日",
  "distance": 60,
  "dayCount": 1,
  "capitaCost": 600.0,
  "shareUrl": "http://120.76.20.226:8080/ckl//route/remote/showTrackHtml?trackId\u003d43",
  "suitNames": [
    "朋友"
  ],
  "trackCoordInfos": [
    {
      "lng": "102.719251",
      "lat": "25.032534"
    },
    {
      "lng": "102.729251",
      "lat": "25.062534"
    },
    {
      "lng": "102.679817",
      "lat": "25.049668"
    }
  ]
} 
    -->
<body>
		<div  class="ckl">
			<span class="ckl-text">车刻丽</span> ${trackDetailInfo.date}
		</div>
	<div class="track-info"> 
			<div class="distance-info">
				<span class="distance">
					${trackDetailInfo.distance}
				</span>
				<span>
					km
				</span>
			</div>
	</div>
	<div id="mapContainer">
	</div>
	
<script type="text/javascript">
	var data = ${trackCoordInfos};
	var arr=new Array();//经纬度坐标数组
	var map = new AMap.Map("mapContainer", {
	    resizeEnable: true
	});
	for(var i=0; i< data.length; i++){
		arr.push(new AMap.LngLat(data[i].lng,data[i].lat));
	}
	AMap.service('AMap.Driving',function(){
	    map.plugin("AMap.DragRoute",function(){
	       var MDrive = new AMap.DragRoute(map, arr, AMap.DrivingPolicy.LEAST_FEE); //构造拖拽导航类
	        MDrive.search(); //查询导航路径并开启拖拽导航
	    });
	}); 
</script>		
</body>
</html>
