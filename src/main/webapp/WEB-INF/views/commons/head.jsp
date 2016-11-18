<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript">
	var _ctx = '${ctx}';
	var img_host = '${img_host}';
</script>
 <script type="text/javascript" src="${ctx}/easyui/jquery.min.js"></script>  
 <!-- 公共的js -->
 <script type="text/javascript" src="${ctx}/js/commons/common.js"></script>
 
 <script type="text/javascript" src="http://cdn.bootcss.com/iframe-resizer/3.5.5/iframeResizer.contentWindow.min.js"></script>
 
  <!-- 统一样式-->
 <link rel="stylesheet" type="text/css" href="${ctx}/css/commons/common.css">   
 <link rel="shortcut icon" href="${ctx}/images/logo.png" type="image/x-icon" />