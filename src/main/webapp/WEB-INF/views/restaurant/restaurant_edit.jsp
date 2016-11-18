<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/amazeui/css/amazeui.datetimepicker.css">
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/restaurant/restaurant_edit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>饭店添加/修改</title>
</head>
<body>
	<div id="tabs" class="easyui-tabs"
		data-options="fit:true,border:false,plain:true">
		<div title="饭店基本信息" style="padding: 20px; display: none;" data-options="closeable: false,border:false">
			<!--  添加 -->
			<div class="am-u-lg-10 am-u-md-12 am-margin-top">
				<form id="form-restaurant-edit" class="am-form am-form-horizontal"
					enctype="multipart/form-data" method="post">
					<input type="hidden" name="id" id="restaurant-id" value="${restaurant.id}">
					<input type="hidden" name="latitude" id="restaurant-latitude" value="${restaurant.latitude}">
					<input type="hidden" name="longtitude" id="restaurant-longtitude" value="${restaurant.longtitude}">
					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">所属服务站：</label>
						<div class="am-u-sm-5 am-u-end">
							<select name="station.id" required="required">
							</select>
						</div>
					</div>
					
					<div class="am-form-group am-form-file">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">饭店封面图片：</label>
						<div class="am-u-sm-4">
							<button type="button" class="am-btn am-btn-success am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 请选择饭店封面图片
							</button>
							<input name="cover_img" class="doc-form-file" type="file" accept="image/png,image/jpeg">
						</div>
						<div class="am-u-sm-5 am-u-end file-list">没有选择任何文件</div>
					</div>
					
					<div class="am-form-group am-form-file">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">饭店环境图片：</label>
						<div class="am-u-sm-4">
							<button type="button" class="am-btn am-btn-success am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 请选择饭店环境图片
							</button>
							<input name="images" class="doc-form-file" type="file"
								multiple="multiple" accept="image/png,image/jpeg">
						</div>
						<div class="am-u-sm-5 am-u-end file-list">没有选择任何文件</div>
					</div>
					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">饭店名称：</label>
						<div class="am-u-sm-9">
							<input name="name" type="text" id="doc-ipt-3-a"
								class="am-form-field" placeholder="请输饭店名称" value="${restaurant.name}" required="required">
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">饭店区域地址：</label>
						<div class="am-u-sm-3 am-u-end" id="d-address">
							<span id="d"></span>
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">饭店详细地址：</label>
						<div class="am-u-sm-9">
							<input id="t-address" name="address" type="text" id="doc-ipt-3-a"
								class="am-form-field" placeholder="请输饭店详细地址"
								value="${restaurant.address}" required="required">
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">饭店星级：</label>
						<div class="am-u-sm-5 am-u-end">
							<select name="stars">
								<option value="1"
									<c:if test="${restaurant.stars == 1}">
								selected="selected"
							</c:if>>1</option>
								<option value="2"
									<c:if test="${restaurant.stars == 2}">
								selected="selected"
							</c:if>>2</option>
								<option value="3"
									<c:if test="${restaurant.stars == 3}">
								selected="selected"
							</c:if>>3</option>
								<option value="4"
									<c:if test="${restaurant.stars == 4}">
								selected="selected"
							</c:if>>4</option>
								<option value="5"
									<c:if test="${restaurant.stars == 5}">
								selected="selected"
							</c:if>>5</option>
							</select>
						</div>
					</div>
					
					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">最低消费：</label>
						<div class="am-u-sm-9">
							<input name="minimun" type="number" id="doc-ipt-3-a"
								class="am-form-field" placeholder="最低消费"
								value="${restaurant.minimun}" required="required" min="0">
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">联系电话：</label>
						<div class="am-u-sm-9">
							<input name="mobile" type="text" id="doc-ipt-3-a"
								class="am-form-field" placeholder="请输联系电话"
								value="${restaurant.mobile}" required="required" pattern="^[1][3,4,5,8][0-9]{9}$">
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">营业时间：</label>
						<div class="am-input-group date  am-sm-3 am-u-md-3 am-u-lg-3"
							id="datetimepicker" data-date-format="hh:ii">
							<input name="openTime" size="16" type="text" value="${restaurant.openTime}" required="required"
								placeholder="开始时间"
								class="am-form-field" readonly> <span
								class="am-input-group-label add-on"><i
								class="icon-th am-icon-calendar"></i></span>
						</div>
						<div class="am-sm-1 am-u-md-1 am-u-lg-1 am-form-label" style="text-align: center;">
						到
						</div>
						<div class="am-input-group date  am-sm-3 am-u-md-3 am-u-lg-3 am-u-end"
							id="datetimepicker" data-date-format="hh:ii">
							<input name="closeTime" size="16" type="text" value="${restaurant.closeTime}" required="required"
								placeholder="开始时间"
								class="am-form-field" readonly> <span
								class="am-input-group-label add-on"><i
								class="icon-th am-icon-calendar"></i></span>
						</div>
					</div>
					
					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">订餐提示：</label>
						<div class="am-u-sm-9">
							<textarea name="orderTips" rows="3" cols="16" id="doc-ipt-3-a"
								class="am-form-field" placeholder="请输入订餐提示">${restaurant.orderTips}</textarea>
						</div>
					</div>
					
					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">订餐规则：</label>
						<div class="am-u-sm-9">
							<textarea name="orderRule" rows="3" cols="16" id="doc-ipt-3-a"
								class="am-form-field" placeholder="请输入订餐规则">${restaurant.orderRule}</textarea>
						</div>
					</div>
					
					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">温馨提示：</label>
						<div class="am-u-sm-9">
							<textarea name="orderSweet" rows="3" cols="16" id="doc-ipt-3-a"
								class="am-form-field" placeholder="请输入温馨提示">${restaurant.orderSweet}</textarea>
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">饭店介绍：</label>
						<div class="am-u-sm-9">
							<textarea name="intro" rows="3" cols="16" id="doc-ipt-3-a"
								class="am-form-field" placeholder="描述内容">${restaurant.intro}</textarea>
						</div>
					</div>

					<div class="am-form-group">
						<div class="am-u-sm-7 am-u-sm-offset-5">
							<input data-cmd="restaurant-confirm" type="button"
								class="am-btn am-btn-success am-btn-sm" value="确定"></input> <input
								data-cmd="restaurant-back" type="button"
								class="am-btn am-btn-success am-btn-sm" value="返回"></input>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="tab2" title="饭店套餐信息" data-options="closeable:false,fit:true,border:false">
			<!-- 饭店房间类型的添加 -->
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'west',split:false" style="width: 50%;height: auto;">
					<table id="datagrid" style="height: auto; min-height: 400px; width: 100%;">
					</table>
					<div id="toolbar">
						<a href="#" class="easyui-linkbutton" data-cmd="back" data-options="iconCls:'icon-back',text:'返回'"></a>
						<a href="#" class="easyui-linkbutton" data-cmd="add" data-options="iconCls:'icon-add',text:'添加'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="delete" data-options="iconCls:'icon-remove',text:'删除'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="update" data-options="iconCls:'icon-edit',text:'修改'"></a>
					</div>
					<div id="dialog">
						<form id="combo-form" method="post" enctype="multipart/form-data">
							<input type="hidden" name="id">
							<table style="margin: 0 auto;">
								<caption style="border-bottom: 1px solid #EEEEEE; padding-bottom: 5px; margin-bottom: 5px; font-weight: bold; color: gray;">饭店房间</caption>
								<tr>
									<td>名称:</td>
									<td>
										<input id="cc" class="easyui-combobox" name="typeId" data-options="valueField:'id',textField:'name',editable:false,panelHeight:'auto',url:'${ctx}/dic/getdetails.do?name=combo_type',required:true"/>
									</td>
								</tr>
								<tr>
									<td>价格:</td>
									<td>
										<input type="text" class="easyui-numberbox" data-options="value:0,min:0,precision:2,required:true" name="price"></input> 
									</td>
								</tr>
								
								<tr>
									<td>开始日期:</td>
									<td align="left">
										<input class="easyui-datebox" type="text" name="startTime" data-options="required:true"/>
									</td>
								</tr>
								
								<tr>
									<td>结束日期:</td>
									<td align="left">
										<input class="easyui-datebox" type="text" name="endTime" data-options="required:true"/>
									</td>
								</tr>
								
								<tr>
									<td>套餐详情:</td>
									<td align="left">
									<textarea rows="3" cols="26" name="detail"></textarea>
									</td>
								</tr>
								<tr>
									<td>图片:</td>
									<td align="left">
										<input type="text" placeholder="上传套餐图片" class="easyui-filebox" data-options="buttonText: '选择文件', buttonAlign: 'right'" name="image"/>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="dialog-buttons">
						<a href="#" class="easyui-linkbutton" data-cmd="editSubmit" data-options="iconCls:'icon-ok',text:'确定'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="editReset" data-options="iconCls:'icon-reload',text:'清空'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="editCancel" data-options="iconCls:'icon-cancel',text:'取消'"></a>
					</div>
				</div>
				<!-- 菜品的表格 -->
				<div data-options="region:'center'" style="width: 50%;height: auto;">
					<table id="datagrid-detail" style="height: auto; min-height: 400px; width: 100%;">
					</table>
					<div id="toolbar1">
						<a href="#" class="easyui-linkbutton" data-cmd="add-dishes" data-options="iconCls:'icon-add',text:'添加'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="delete-dishes" data-options="iconCls:'icon-remove',text:'删除'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="update-dishes" data-options="iconCls:'icon-edit',text:'修改'"></a>
					</div>
					<div id="dialog1">
						<form id="dishes-form" method="post">
							<table style="margin: 0 auto;">
								<caption style="border-bottom: 1px solid #EEEEEE; padding-bottom: 5px; margin-bottom: 5px; font-weight: bold; color: gray;">饭店菜品</caption>
								<tr>
									<td>套餐:</td>
									<td>
										<input id="restaurant-dishes-select" name="dishesId" style="width: 170px;" data-options="required:true"/>
									</td>
								</tr>
								<tr>
									<td>数量:</td>
									<td>
										<input type="text" class="easyui-numberbox" value="0" data-options="min:0,precision:0,required:true" name="num"></input> 
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="dialog-buttons1">
						<a href="#" class="easyui-linkbutton" data-cmd="editDishesSubmit" data-options="iconCls:'icon-ok',text:'确定'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="editDishesReset" data-options="iconCls:'icon-reload',text:'清空'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="editDishesCancel" data-options="iconCls:'icon-cancel',text:'取消'"></a>
					</div>
				</div>
				
				<div id="dialog2">
						<form id="dishes-update-form" method="post">
							<input type="hidden" name="dishesId">
							<table style="margin: 10;">
								<tr>
									<td>数量:</td>
									<td>
										<input type="text" class="easyui-numberbox" value="0" data-options="min:0,precision:0,required:true" name="num"></input> 
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="dialog-buttons2">
						<a href="#" class="easyui-linkbutton" data-cmd="updateDishesSubmit" data-options="iconCls:'icon-ok',text:'确定'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="updateDishesReset" data-options="iconCls:'icon-reload',text:'清空'"> </a>
						<a href="#" class="easyui-linkbutton" data-cmd="updateDishesCancel" data-options="iconCls:'icon-cancel',text:'取消'"></a>
					</div>
				</div>
			</div>
		</div>
</div>
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
	<script type="text/javascript">
	$(function(){
	// 区域地址
	var dAddress = $("#d-address");
	
	// 详细地址
	var tAddress = $("#t-address");
	
	var stationId = '${restaurant.station.id}';
	
	var stations = null;
	
	// 加载服务站
	var stationSelect = $("[name='station.id']");
	
	var latitude = $("#restaurant-latitude");
	var longtitude = $("#restaurant-longtitude");
	
	
	$.get(_ctx + "/servicestation/getHasRestaurantStations.do", function(data){
		if(data && data.length) {
			var s = '';
			for (var i = 0; i < data.length; i++) {
				var addr = '';
				var code = '';
				if(data[i] && data[i].district){
					var pname = data[i].district.city.province.name;
					var cname = data[i].district.city.name;
					var dname = data[i].district.name;
					if(pname == cname) {
						addr = pname + dname;
					}else {
						addr = pname +cname+dname;
					}
					stations = data;
				}
				if(stationId && data[i].id == stationId){
					s+="<option value='" + data[i].id +"' data-address='"+addr+"'selected='selected'>"+data[i].name+"</option>";
				}else {
					s+="<option value='" + data[i].id +"' data-address='"+addr+"'>"+data[i].name+"</option>";
				}
			}
			stationSelect.html(s);
			var defaultSelect =$(stationSelect[0].children[stationSelect[0].selectedIndex]);
			var address = defaultSelect.data("address");
			dAddress.find("#d").text(address);
			latitude.val(data[stationSelect[0].selectedIndex].latitude);
			longtitude.val(data[stationSelect[0].selectedIndex].longtitude);
		}
	},"json");
	stationSelect.on("change",function(){
		var seleted=$(this.children[this.selectedIndex]);
		var address = seleted.data("address");
		dAddress.find("#d").text(address);
		latitude.val(stations[this.selectedIndex].latitude);
		longtitude.val(stations[this.selectedIndex].longtitude);
	});
	});
	</script>
	
	<script type="text/javascript" src="${ctx}/amazeui/js/amazeui.datetimepicker.min.js"></script>
	<script type="text/javascript" src="${ctx}/amazeui/js/amazeui.datetimepicker.zh-CN.js"></script>
	<script type="text/javascript">
		$(".date").datetimepicker({
			'initialDate':new Date(),
			startView:0,
			maxView:0,
			language:  'zh-CN'
		});
	</script>
</body>
</html>