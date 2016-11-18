<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/hotel/hotel_edit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>酒店添加/修改</title>
</head>
<body>
	<div id="tabs" class="easyui-tabs"
		data-options="fit:true,border:false,plain:true">
		<div title="酒店基本信息" style="padding: 20px; display: none;" data-options="closeable: false,border:false">
			<!--  添加-->
			<div class="am-u-lg-10 am-u-md-12 am-margin-top">
				<form id="form-hotel-edit" class="am-form am-form-horizontal"
					enctype="multipart/form-data" method="post">
					<input type="hidden" name="id" id="hotel-id" value="${hotel.id}">
					<input type="hidden" name="latitude" id="restaurant-latitude" value="${hotel.latitude}">
					<input type="hidden" name="longtitude" id="restaurant-longtitude" value="${hotel.longtitude}">
					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">所属服务站：</label>
						<div class="am-u-sm-5 am-u-end">
							<select name="station.id" required="required">
							</select>
						</div>
					</div>
					<div class="am-form-group am-form-file">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店封面图片：</label>
						<div class="am-u-sm-4">
							<button type="button" class="am-btn am-btn-success am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 请选择酒店封面图片
							</button>
							<input name="cover_img" class="doc-form-file" type="file" accept="image/png,image/jpeg">
						</div>
						<div class="am-u-sm-5 am-u-end file-list">没有选择任何文件</div>
					</div>
					<div class="am-form-group am-form-file">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店外观图片：</label>
						<div class="am-u-sm-4">
							<button type="button" class="am-btn am-btn-success am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 请选择酒店外观图片
							</button>
							<input name="outside_imgs" class="doc-form-file" type="file"
								multiple="multiple" accept="image/png,image/jpeg">
						</div>
						<div class="am-u-sm-5 am-u-end file-list">没有选择任何文件</div>
					</div>
					<div class="am-form-group am-form-file">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店大厅图片：</label>
						<div class="am-u-sm-4">
							<button type="button" class="am-btn am-btn-success am-btn-sm">
								<i class="am-icon-cloud-upload"></i> 请选择酒店大厅图片
							</button>
							<input name="inner_imgs" class="doc-form-file" type="file"
								multiple="multiple" accept="image/png,image/jpeg">
						</div>
						<div class="am-u-sm-5 am-u-end file-list">没有选择任何文件</div>
					</div>
					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店名称：</label>
						<div class="am-u-sm-9">
							<input name="name" type="text" id="doc-ipt-3-a"
								class="am-form-field" placeholder="请输酒店名称" value="${hotel.name}" required="required">
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店区域地址：</label>
						<div class="am-u-sm-3 am-u-end" id="d-address">
							<span id="d"></span>
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店详细地址：</label>
						<div class="am-u-sm-9">
							<input id="t-address" name="address" type="text" id="doc-ipt-3-a"
								class="am-form-field" placeholder="请输酒店详细地址"
								value="${hotel.address}" required="required">
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店星级：</label>
						<div class="am-u-sm-5 am-u-end">
							<select name="stars">
								<option value="1"
									<c:if test="${hotel.stars == 1}">
								selected="selected"
							</c:if>>1</option>
								<option value="2"
									<c:if test="${hotel.stars == 2}">
								selected="selected"
							</c:if>>2</option>
								<option value="3"
									<c:if test="${hotel.stars == 3}">
								selected="selected"
							</c:if>>3</option>
								<option value="4"
									<c:if test="${hotel.stars == 4}">
								selected="selected"
							</c:if>>4</option>
								<option value="5"
									<c:if test="${hotel.stars == 5}">
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
								value="${hotel.minimun}" required="required" min="0">
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">联系电话：</label>
						<div class="am-u-sm-9">
							<input name="mobile" type="text" id="doc-ipt-3-a"
								class="am-form-field" placeholder="请输联系电话"
								value="${hotel.mobile}" pattern="^[1][3,4,5,8][0-9]{9}$" required="required">
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">服务内容：</label>
						<div class="am-u-sm-9">
							<c:forEach items="${content}" var="cont" varStatus="status">
								<label class="am-checkbox-inline"> <input
									type="checkbox" value="${cont.id}" class="service-content"
									<c:forEach items="${hotel.serviceContents}" var="item">
									<c:if test="${item.id == cont.id}"> 
										checked="checked"
									</c:if>
									</c:forEach>
								>
									${cont.name}
								</label>
							</c:forEach>
						</div>
					</div>

					<div class="am-form-group">
						<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店介绍：</label>
						<div class="am-u-sm-9">
							<textarea name="intro" rows="4" cols="16" id="doc-ipt-3-a"
								class="am-form-field" placeholder="描述内容">${hotel.intro}</textarea>
						</div>
					</div>

					<div class="am-form-group">
						<div class="am-u-sm-7 am-u-sm-offset-5">
							<input data-cmd="hotel-confirm" type="button"
								class="am-btn am-btn-success am-btn-sm" value="确定"></input> <input
								data-cmd="hotel-back" type="button"
								class="am-btn am-btn-success am-btn-sm" value="返回"></input>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="tab2" title="酒店房间信息" data-options="closeable:false,fit:true,border:false">
			<!-- 酒店房间类型的添加 -->
			<table id="datagrid">
			</table>
			<div id="toolbar">
				<a href="#" class="easyui-linkbutton" data-cmd="back" data-options="iconCls:'icon-back',text:'返回'"></a>
				<a href="#" class="easyui-linkbutton" data-cmd="add" data-options="iconCls:'icon-add',text:'添加'"> </a>
				<a href="#" class="easyui-linkbutton" data-cmd="delete" data-options="iconCls:'icon-remove',text:'删除'"> </a>
				<a href="#" class="easyui-linkbutton" data-cmd="update" data-options="iconCls:'icon-edit',text:'修改'"></a>
			</div>
			<div id="dialog">
				<form id="room-form" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id">
					<table style="margin: 20px;">
						<caption style="border-bottom: 1px solid #EEEEEE; padding-bottom: 5px; margin-bottom: 5px; font-weight: bold; color: gray;">酒店房间</caption>
						<tr>
							<td>名称:</td>
							<td>
								<input class="easyui-textbox" type="text" name="name" data-options="required:true"/>
							</td>
						</tr>
						<tr>
							<td>价格:</td>
							<td>
								<input class="easyui-numberbox" type="number" name="price" data-options="min:0,precision:2,required:true"/>
							</td>
						</tr>
						
						<tr>
							<td>面积:</td>
							<td align="left">
								<input class="easyui-numberbox" type="text" name="size" data-options="min:0,precision:2,required:true"/>
							</td>
						</tr>
						
						<tr>
							<td>床型:</td>
							<td align="left">
								<input id="cc" class="easyui-combobox" name="bedTypeId" data-options="valueField:'id',textField:'name',editable:false,panelHeight:'auto',url:'${ctx}/dic/getdetails.do?name=bed_type',required:true"/>  
							</td>
						</tr>
						<tr>
							<td>数量:</td>
							<td align="left">
								<input class="easyui-numberbox" type="number" name="totalNum" data-options="min:0,precision:0,required:true"/>
							</td>
						</tr>
						<tr>
							<td>封面:</td>
							<td align="left">
								<input class="easyui-filebox" name="cover_img" accept="image/jpeg" placeholder="请选择房间封面图片" data-options="buttonText:'选择图片'"/>
							</td>
						</tr>
						<tr>
							<td>图片:</td>
							<td align="left">
								<input class="easyui-filebox" name="images" accept="image/jpeg" multiple="multiple" placeholder="请选择房间内部图片" data-options="buttonText:'选择图片'"/>
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
	
	var stationId = '${hotel.station.id}';
	
	var latitude = $("#restaurant-latitude");
	var longtitude = $("#restaurant-longtitude");
	var stations = null;
	
	// 加载服务站
	var stationSelect = $("[name='station.id']");
	$.get(_ctx + "/servicestation/getHasHotelStations.do", function(data){
		if(data && data.length) {
			var s = '';
			for (var i = 0; i < data.length; i++) {
				var addr = '';
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
					s+="<option value='" + data[i].id +"' data-address='"+addr+"' selected='selected'>"+data[i].name+"</option>";
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
</body>
</html>