<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/carPartDepot/edit.js"></script>
<title>仓库添加/修改</title>
</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="form-edit" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="id" value="${carPartDepot.id}">
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入仓库名称" value="${carPartDepot.name}" required="required">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">编号：</label>
				<div class="am-u-sm-10">
					<input name="sn" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入仓库编号" value="${carPartDepot.sn}" required="required">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">库管员：</label>
					<div class="am-u-sm-10">
						<select name="keeper.id" style="width:300px" required="required">
						<c:forEach items="${admins}" var="admin">
						        <option  value="${admin.id}"
						        	<c:if test="${carPartDepot.keeper.id == admin.id}">
					     			   selected="selected" 
									</c:if>
								>
									${admin.realName}
						        </option>
						</c:forEach>
				        </select>
					</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">仓库类型：</label>
				<div class="am-u-sm-10">
						<label class="am-radio-inline">
				        <input type="radio"  value="false" name="centre"
				        	<c:if test="${carPartDepot.centre == null || !carPartDepot.centre}">
					        	checked="checked"
					        </c:if> 
				        > 服务站仓库
				      </label>
					<label class="am-radio-inline">
				        <input type="radio"  value="true" name="centre"
					        <c:if test="${carPartDepot.centre}">
					        	checked="checked"
					        </c:if> 
				        >中央仓库
			        </label>
				</div>
			</div>
			
			<div id="station-select" class="am-form-group" >
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务站：</label>
				<div class="am-u-sm-10">
					<select name="station.id" style="width:300px" required="required">
					<c:forEach items="${stations}" var="station">
					        <option  value="${station.id}" address="${station.fullAddress}"
					        	<c:if test="${carPartDepot.station.id == station.id}">
				     			   selected="selected" 
								</c:if>
							>
								${station.name}
					        </option>
					</c:forEach>
			        </select>
		      	</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">地址：</label>
				<div class="am-u-sm-10">
					<input name="address" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输仓库地址" value="${carPartDepot.address}" required="required">
				</div>
			</div>
			
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="car-depot-confirm" 
						type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<a class="am-btn am-btn-success am-btn-sm" href="${ctx}/carPartDepot/index.do">返回</a>
				</div>
			</div>
		</form>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	
	<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">温馨提示</div>
	    <div class="am-modal-bd">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn">确定</span>
	    </div>
	  </div>
	</div>
</body>
</html>