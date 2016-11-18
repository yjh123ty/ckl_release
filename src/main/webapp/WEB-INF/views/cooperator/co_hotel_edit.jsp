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
<title>合作商家酒店添加/修改</title>
</head>
<body class="am-g">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="form-hotel-edit" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="id" value="${hotel.id}">
			<input type="hidden" name="type" value="2">
			<input type="hidden" name="latitude" id="latitude-id" value="${hotel.latitude}">
			<input type="hidden" name="longtitude" id="longtitude-id" value="${hotel.longtitude}">
			<input type="hidden" name="fullAddress" id="fullAddress" value="${hotel.fullAddress}">
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">商家类型：</label>
				<div class="am-u-sm-9">
					酒店
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">商家名称：</label>
				<div class="am-u-sm-9">
					<input name="name" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输酒店名称" value="${hotel.name}" required="required">
				</div>
			</div>
			
			<c:if test="${hotel.id != null}">
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">已选商家地址：</label>
				<div class="am-u-sm-9">
					${hotel.fullAddress}
				</div>
			</div>
			</c:if>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">商家地址：</label>
				<div class="am-u-sm-3">
					<select id="addr-prov">
					</select>
				</div>
				<div class="am-u-sm-3">
					<select id="addr-city">
					</select>
				</div>
				<div class="am-u-sm-3 am-u-end">
					<select id="addr-dist" name="district.code">
					</select>
				</div>
				<input id="distinctAddress" type="hidden" name="distinctAddress">
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">详细地址：</label>
				<div class="am-u-sm-9">
					<input id="t-address" name="address" type="text" id="doc-ipt-3-a"
						class="am-form-field" placeholder="请输酒店详细地址"
						value="${hotel.address}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">商家电话：</label>
				<div class="am-u-sm-9">
					<input name="mobile" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入电话" value="${hotel.mobile}" required="required">
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
				<div class="am-u-sm-5 am-u-end file-list"><font color="red">没有选择任何文件 &nbsp;*</font></div>
			</div>
			
			<div class="am-form-group am-form-file">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店图片：</label>
				<div class="am-u-sm-4">
					<button type="button" class="am-btn am-btn-success am-btn-sm">
						<i class="am-icon-cloud-upload"></i> 选择要上传的文件
					</button>
					<input name="outside_imgs" class="doc-form-file" type="file"
						multiple="multiple" accept="image/png,image/jpeg">
				</div>
				<div class="am-u-sm-5 am-u-end file-list"><font color="red">没有选择任何文件 &nbsp;*</font>  </div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店星级：</label>
				<div class="am-u-sm-5 am-u-end">
					<select name="stars">
						<option value="1"
							<c:if test="${hotel.stars == 1}">
								selected="selected"
							</c:if> 
						>1</option>
						<option value="2" 
							<c:if test="${hotel.stars == 2}">
								selected="selected"
							</c:if>
						>2</option>
						<option value="3"
							<c:if test="${hotel.stars == 3}">
								selected="selected"
							</c:if>
						>3</option>
						<option value="4"
							<c:if test="${hotel.stars == 4}">
								selected="selected"
							</c:if>
						>4</option>
						<option value="5"
							<c:if test="${hotel.stars == 5}">
								selected="selected"
							</c:if> 
						>5</option>
					</select>
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">最低商品价格：</label>
				<div class="am-u-sm-9">
					<input id="t-address" name="minimun" type="text" id="doc-ipt-3-a"
						class="am-form-field" placeholder="请输入价格（元）"
						value="${hotel.minimun}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">酒店介绍：</label>
				<div class="am-u-sm-9">
					<textarea name="intro" rows="4" cols="16" id="doc-ipt-3-a"
						class="am-form-field" placeholder="请输入">${hotel.intro}</textarea>
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-7 am-u-sm-offset-5">
					<input data-cmd="hotel-confirm" type="button"
						class="am-btn am-btn-success am-btn-sm" value="确定"></input> <input
						data-cmd="hotel-back"
						type="button" class="am-btn am-btn-success am-btn-sm"
						value="返回"></input>
				</div>
			</div>
			
		</form>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript">
	
		/* 
		 *	文件上传 js 效果
		 */
		 $('.doc-form-file').on('change',function() {
				var fileNames = '';
				$.each(this.files, function() {
					fileNames += '<span class="am-badge">' + this.name
							+ '</span> ';
				});
				fileNames = fileNames ? fileNames : "没有选择任何文件";
				$(this).closest(".am-form-file").find('.file-list').html(fileNames);
		 });
	
		/**
		 * 修改合作商家界面的js
		 */
		$(function(){
			// 缓存界面组件
			var hotelEditForm = $("#form-hotel-edit");
			var fullAddress = $("#fullAddress").val();
			console.debug(fullAddress);
			
			hotelEditForm.validator({});
			
			// 按钮事件统一监控对象
			var btnClickObj = {
				'hotel-confirm':function(){
					
					// 封装服务类型参数
					var data = {};
					
					var districtAddress = '';
			    	var p = $("#addr-prov").find("option:selected").text().trim();
			    	var c = $("#addr-city").find("option:selected").text().trim();
			    	var d = $("#addr-dist").find("option:selected").text().trim();
			    	if(p&&c&&d){
			    		if(p==c){
			    			districtAddress=p+d;
			    		}else {
			    			districtAddress=p+c+d;
			    		}
			    	}
			    	
			    	if(fullAddress){
				    	data['fullAddress']=fullAddress;
			    	}else{
				    	data['districtAddress']=districtAddress;
			    	}
					
					hotelEditForm.ajaxSubmit( {    
			    	       url:       _ctx+"/cooperatorHotel/edit.do", 
			    	       type:      "post",
			    	       data:data,
			    	       clearForm: false,     
			    	       resetForm: false,     
			    	       beforeSubmit: function(){
			    	    	   // 校验参数
								return hotelEditForm.validator("isFormValid");
			    	       }, 
			    	       success: function(data){
			    	    	   if(isJsonObj(data)){
			    	    		   $.messager.alert("温馨提示", data.msg, "info",
		    							function() {
		    								//页面跳转
		    								location.href = _ctx + "/cooperatorHotel/index.do";
		    							});
								} else {
									$.messager.alert("温馨提示", data.msg, "info");
								}
			    	       }
			    	   });
				},
				'hotel-back':function(){
					//页面跳转
					location.href = _ctx + "/cooperatorHotel/index.do";
				}
			};
			
			// 统一监听按钮的事件
			$("input[data-cmd]").click(function(){
				var cmd = $(this).data("cmd");
				if(cmd &&  btnClickObj[cmd]){
					btnClickObj[cmd](this);
				};
			});
			
			//省市县是三级联动
			
			//1、初始化参数
			var proSelect = $("#addr-prov");
			var citySelect = $("#addr-city");
			var distSelect = $("#addr-dist");
			proSelect.html("<option value='-1'>---请选择省---</option>");
			citySelect.html("<option value='-1'>---请选择市---</option>");
			distSelect.html("<option value='-1'>---请选择区---</option>");
			//初始化待拼接的字符串
			var addressCo = "";
			
			//初始化拼接展示的文本框
			var addressInput = $(":input[name=addressCo]");
			
			//根据选择更新地址
			function updateAddress(select){
				if(select.selectedIndex != 0) {
					addressCo += select.options[select.selectedIndex].text;
					addressInput.val(addressCo);
				}else {
					addressCo = "";
				}
			}
			
			//2、初始化省数据 
			loadAddr("addr-prov", _ctx+"/address/getProvinces", "<option value='-1'>---请选择省---</option>");
			
			//3、选择省动态加载市 
			proSelect.on("change",function(){
				citySelect.html("<option value='-1'>---请选择市---</option>");
				distSelect.html("<option value='-1'>---请选择区---</option>");
				addressCo = "";
				updateAddress(this);
				//加载市
				loadAddr("addr-city", _ctx+"/address/getCitys?code="+this.value, "<option value='-1'>---请选择市---</option>");
			});
			
			 //4、选择市动态加载 县
			citySelect.on("change",function(){
				distSelect.html("<option value='-1'>---请选择区---</option>");
				updateAddress(this);
				//加载县
				loadAddr("addr-dist", _ctx+"/address/getDistricts?code="+this.value, "<option value='-1'>---请选择区---</option>");
			});
			
			 //5、选择市，只需更新即可
			distSelect.on("change", function(){
				updateAddress(this);
			});
		});
		
	</script>
</body>
</html>