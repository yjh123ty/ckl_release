<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户个人中心</title>

<style type="text/css">
.mytable {
	border: solid 1px #add9c0;
	border-width: 1px 1px 1px 1px;
	text-align: center;
	width: 250px;
	height: 120px;
	float: left;
}

.head-icon-container {
	display: table-cell;
	vertical-align: middle;
	padding: 10px 0;
}

/*一行放table的数量*/
.tableDiv {
	float: left;
	width: 1010px;
}

/*头像*/
.userHeadContainer {
	margin: 10px;
	padding: 20px;
}
</style>
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<script type="text/javascript">
		
	</script>

</head>
<body>
	<div id="container" style="width: 1000px; margin: 15px; padding: 10px">
			<table class="userHeadContainer">
				<tr>
					<td>
						<div class="head-icon-container">
							<c:if test="${empty userCenter.user.headIcon}">
								<img src="${ctx}/images/default_headicon.png" class="am-circle" width="60px" height="60px">
							</c:if>
							<c:if test="${!empty userCenter.user.headIcon}">
								<img src="${img_host}${userCenter.user.headIcon}" class="am-circle" width="60px" height="60px">
							</c:if>
						</div>
					</td>
					<td>
						<div class="userHeadContainer">
						${userCenter.user.nickName} <br/>
						${userCenter.user.mobile}
						</div>
					</td>
				</tr>
			</table>


		<div class="tableDiv">
			<table class="mytable am-table-bordered">
				<tr>
					<td>${userCenter.user.level}<br /> <font color="green">等级</font>
					</td>
				</tr>
			</table>

			<table class="mytable am-table-bordered">
				<tr>
					<td>${userCenter.user.balance}<br /> <font color="green">钱包</font>
					</td>
				</tr>
			</table>

			<table class="mytable am-table-bordered am-table-hover" onclick='showBillInfo(${userCenter.user.id});'>
				<tr>
					<td>${userCenter.rechargeCount}<br />
						<font color="green">充值总计（刻）</font></td>
				</tr>
			</table>

			<table class="mytable am-table-bordered am-table-hover" onclick='showDistributionInfo(${userCenter.user.id});'>
				<tr>
					<td>${userCenter.totalCommission}<br />
						<font color="green">佣金总计（刻）</font></td>
				</tr>
			</table>
		</div>



		<div class="tableDiv">
			<table class="mytable am-table-bordered am-table-hover">
				<tr>
					<td>${userCenter.badgeCount}<br /> <font color="green">徽章</font>
					</td>
				</tr>
			</table>

			<table class="mytable am-table-bordered am-table-hover" onclick='showOrderInfo(${userCenter.user.id});'>
				<tr>
					<td>${userCenter.orderNumCount}<br /> <font
						color="green">订单总计</font></td>
				</tr>
			</table>

			<table class="mytable am-table-bordered am-table-hover" onclick='showAvgStarInfo(${userCenter.user.id});'>
				<tr>
					<td>
						${userCenter.avgStar}<br /> 
						<font color="green">评价星数</font>
					</td>
				</tr>
			</table>

			<table class="mytable am-table-bordered am-table-hover" onclick='showFeedbackInfo(${userCenter.user.id});'>
				<tr>
					<td>${userCenter.feedbackCount}<br /> <font
						color="green">意见反馈</font></td>
				</tr>
			</table>
		</div>


		<div class="tableDiv">
			<table class="mytable am-table-bordered am-table-hover" onclick='showTravelNoteInfo(${userCenter.user.id});'>
				<tr>
					<td>${userCenter.userTravelNoteCount}<br />
						<font color="green">游记</font></td>
				</tr>
			</table>

			<table class="mytable am-table-bordered am-table-hover" onclick='showCompanionTopicInfo(${userCenter.user.id});'>
				<tr>
					<td>
						${userCenter.companionTopicCount}
						<br /> 
						<font color="green">找旅伴帖子</font>
					</td>
				</tr>
			</table>

			<table class="mytable am-table-bordered am-table-hover" onclick='showForumTopicInfo(${userCenter.user.id});'>
				<tr>
					<td>
						${userCenter.topicCount}
						<br /> 
						<font color="green">社区帖子</font>
					</td>
				</tr>
			</table>

			<table class="mytable am-table-bordered">
				<tr>
					<td></td>
				</tr>
			</table>
		</div>

	</div>

	<script language="javascript">
function showOrderInfo(userId){
	var url = _ctx+ "/user/orderInfo.do";
	window.open(url+"?userId="+userId);
}

function showFeedbackInfo(userId){
	var url = _ctx+ "/feedback/feedbackInfo.do";
	window.open(url+"?userId="+userId);
}

function showTravelNoteInfo(userId){
	var url = _ctx+ "/usertravelnote/travelNoteInfo.do";
	window.open(url+"?userId="+userId);
}

function showAvgStarInfo(userId){
	var url = _ctx+ "/serviceEvaluation/serviceEvaluationInfo.do";
	window.open(url+"?userId="+userId);
}

function showForumTopicInfo(userId){
	var url = _ctx+ "/topic/index.do?cmd=ft&type=ftInfo";
	window.open(url+"&userId="+userId);
}

function showCompanionTopicInfo(userId){
	var url = _ctx+ "/topic/index.do?cmd=ct&type=ctInfo";
	window.open(url+"&userId="+userId);
}

function showBillInfo(userId){
	var url = _ctx+ "/userBill/index.do?cmd=rechargeDetail&uid=";
	window.open(url+userId);
}

function showDistributionInfo(userId){
	var url = _ctx+ "/userDistribution/index.do?cmd=listDetail&userId=";
	window.open(url+userId);
}
</script>

</body>
</html>