<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
  <script type="text/javascript">
  	/*
  		判断当前的窗体是否是顶层的窗体，如果不是则将顶层窗体的地址置为当前窗体的地址
  	*/
  	if(window.top && window.top.location != window.location){
  		window.top.location = window.location;
  	}
  </script>
  <meta charset="UTF-8">
  <title>登录</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <%@ include file="/WEB-INF/views/commons/head.jsp" %>
  <%@ include file="/WEB-INF/views/commons/head_amazeui.jsp" %>
   <style>
  	body{
		background:url(${ctx}/images/background.png) no-repeat;
		background-size:cover;
	}
  	#formbox{
		position: absolute; 
		top:40%; 
		left:60%;
		text-align:center;
	}
	#login-form{
		width:400px;
		margin: 0 auto;
		padding:30px;
		text-align:center;
		color:#000;
		background:#fff;
		filter:alpha(Opacity=70);
		-moz-opacity:0.7;
		opacity: 0.7;
	}
	#login-title{
		font-size:25px;
		font-weight:bold;
		color:gray;
		padding-top:10px;
		padding-bottom:10px;
		background:#fff;
		filter:alpha(Opacity=80);
		-moz-opacity:0.8;
		opacity: 0.8;
	}
  </style>
</head>
<body>
	<div id="formbox">
    	<div id="login-title">登陆车刻丽运营平台</div>
          <form id="login-form" method="post" class="am-form">
                      <div class="am-input-group am-input-group-success">
                          <span class="am-input-group-label"><i
                              class="am-icon-user am-icon-fw"></i></span> <input name="mobile"
                              type="number" class="am-form-field" placeholder="请手机号码" value="15708437406">
                      </div>
  
                      <div class="am-input-group am-input-group-success am-margin-top">
                          <span class="am-input-group-label"><i
                              class="am-icon-lock am-icon-fw"></i></span> <input name="password"
                              type="password" class="am-form-field" placeholder="请输入密码" value="123456">
                      </div>
                      <div class="am-btn-group am-btn-group-justify am-margin-top">
                          <a id="toLogin" class="am-btn am-btn-primary" role="button">登录</a>
                      </div>
    
  		</form>
        </div>
	
	<!-- start 登录提示摸态框 -->
	<div class="am-modal am-modal-alert" tabindex="-1" id="msg-modal">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">温馨提示：</div>
	    <div id="msg_content" class="am-modal-bd">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn data-am-modal-confirm">确定</span>
	    </div>
	  </div>
	</div>
	<!-- end 登录提示摸态框 -->
	
	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript">
		$(function(){
			var form = $("#login-form");
			$("#toLogin").click(function(){
				/* 
					1.验证表单
					2.使用ajax的方式提交
					3.成功返回上级界面刷新
					4.失败直接提示
				*/
				form.ajaxSubmit( {    
				       url:       _ctx + "/login/checkLogin.do", 
				       type:      "post",
				       clearForm: false,     
				       resetForm: true,     
				       beforeSubmit: function(){
				       }, 
				       success: function(data){
		    	           var modal = $('#msg-modal');
				    	   if(isJsonObj(data)){
					    	   modal.find("#msg_content").html(data.msg);
					    	   if(data.success) {
					    		   location.href =  _ctx + "/main/index.do";
					    	   }else {
						    	   modal.modal();
					    	   }
				    	  }else {
				    		  modal.find("#msg_content").html("服务器异常");
				    		  modal.modal();
				    	  }
				       } 
				   });
			});
			
			$("#toReset").click(function(){
				form[0].reset();
			});
		});
	</script>
</body>
</html>
