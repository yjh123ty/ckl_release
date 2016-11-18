<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/WEB-INF/views/commons/head.jsp"%>
	<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
	<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<title>攻略管理</title>
</head>
<body class="easyui-layout">   
    <div data-options="region:'west',border:false,split:false" style="width:20%;height: 100%">
		<ul class="am-list admin-sidebar-list">
			<li class="am-panel"><a href="#guide-all">
					攻略目录
				</a>
			</li>
			<c:forEach items="${items}" var="item">
				<li class="am-panel"><a href="#item-${item.id}">
						${item.name}
				</a>
				<c:if test="${item.subItems != null}">
					<ul class="am-list admin-sidebar-sub">
						<c:forEach items="${item.subItems}" var="subItem">
							<li><a href="#subitem-${subItem.id}">${subItem.name}</a></li>
						</c:forEach>
					</ul>
				</c:if>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div data-options="region:'center',border:false,collapsible:false,split:false,fit:false" style="width: 80%; height:100%; padding: 20px;" >

		<section class="am-panel am-panel-default" id="guide-all">
			<header class="am-panel-hd">
				<h3 class="am-panel-title">
					攻略详情：
					 <span style="margin-left: 20px;">
								<a href="${ctx}/routeguide/index.do?routeId=${routeId}&cmd=saveItem" class="am-icon-plus am-icon-sm" title="添加标题"></a> 
					</span> 
				</h3>
			</header>
		</section>
		<c:forEach items="${items}" var="item">
			
				<div id="item-${item.id}">
					<section class="am-panel am-panel-default">
					  <header class="am-panel-hd">
					    <h3 class="am-panel-title">${item.name}--${item.no}
					    <span style="margin-left: 20px;">
								<a href="${ctx}/routeguide/index.do?id=${item.id}&routeId=${routeId}&cmd=editItem" class="am-icon-pencil am-icon-sm" title="修改标题"></a> 
								<a href="${ctx}/routeguide/index.do?id=${item.id}&routeId=${routeId}&cmd=saveSubItem&superId=${item.id}" class="am-icon-plus am-icon-sm" title="添加子标题"></a> 
								<a href="${ctx}/routeguide/index.do?id=${item.id}&routeId=${routeId}&cmd=deleteItem" class="am-icon-remove am-icon-sm" title="删除标题"></a>
						</span> 
						</h3>
					  </header>
					  <div class="am-panel-bd">
					  	<div>
		      				<img src="${img_host}${item.img}" height="200px" width="200px">
		      				${item.text}
	      				</div>
					  </div>
	      			<c:forEach items="${item.subItems}" var="subItem">
	      				<div id="subitem-${subItem.id}">
		     				<section class="am-panel am-panel-default">
							  <header class="am-panel-hd">
							    <h3 class="am-panel-title">${subItem.name}--${subItem.no}
							    <span style="margin-left: 20px;">
										<a href="${ctx}/routeguide/index.do?id=${subItem.id}&routeId=${routeId}&cmd=editSubItem" class="am-icon-pencil am-icon-sm" title="修改标题"></a> 
									<a href="${ctx}/routeguide/index.do?id=${subItem.id}&routeId=${routeId}&cmd=deleteSubItem" class="am-icon-remove am-icon-sm" title="删除标题"></a>
								</span> 
								</h3>
							  </header>
							  <div class="am-panel-bd">
							  	<div>
				      				<c:if test="${subItem.img != null}">
				      					<img src="${img_host}${subItem.img}" height="200px">
				      				</c:if>
			      					${subItem.text}
			      				</div>
							  </div>
							</section>
	      				</div>
	      			</c:forEach>
	      			</section>
      			</div>
			</c:forEach>
	</div>
	
</body>
</html>