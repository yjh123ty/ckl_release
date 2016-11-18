<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工个人中心</title>

<style type="text/css">
.mytable {
	border: solid 1px #add9c0;
	border-width: 1px 1px 1px 1px;
	text-align: center;
	width: 250px;
	height: 120px;
	float: left;
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

.head-icon-container {
	display: table-cell;
	vertical-align: middle;
	padding: 10px 0;
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
							<c:if test="${empty employee.headIcon}">
								<img src="${ctx}/images/default_headicon.png" class="am-circle" width="60px" height="60px">
							</c:if>
							<c:if test="${!empty employee.headIcon}">
								<img src="${img_host}${employee.headIcon}" class="am-circle" width="60px" height="60px">
							</c:if>
						</div>
					</td>
					<td>
						<div class="userHeadContainer">
							${employee.realName} <br/>
							${employee.mobile}
						</div>
					</td>
				</tr>
			</table>
			
			
			<div class="tableDiv">
				<table class="mytable am-table-bordered">
					<tr>
						<td>${employee.empNumber}<br /> <font color="green">工号</font>
						</td>
					</tr>
				</table>
	
				<table class="mytable am-table-bordered">
					<tr>
						<td>${employee.jobTitle.name}<br /> <font color="green">岗位类别</font>
						</td>
					</tr>
				</table>
	
				<table class="mytable am-table-bordered">
					<tr>
						<td>
							<c:if test="${empty employee.salaryLevel}">
								<font color="red">未设置</font>
							</c:if>
							<c:if test="${!empty employee.salaryLevel}">
								${employee.salaryLevel}
							</c:if>
							
						<br />
							<font color="green">薪点级别</font>
						</td>
					</tr>
				</table>
	
				<table class="mytable am-table-bordered">
					<tr>
						<td>${employee.station.name}<br />
							<font color="green">所属服务站</font></td>
					</tr>
				</table>
			</div>


			<div class="tableDiv">
				<table class="mytable am-table-bordered am-table-hover" onclick='showOrderInfo(${employee.id});'>
					<tr>
						<td>
							<c:if test="${employee.numInCount == null}">
								0
							</c:if>
							<c:if test="${employee.numInCount != null}">
								${employee.numInCount}
							</c:if>
							<br /> 
							<font color="green">服务订单</font></td>
					</tr>
				</table>
				
				<table class="mytable am-table-bordered am-table-hover" onclick='showSalaryInfo(${employee.id});'>
					<tr>
						<td>
						
								<c:if test="${employee.salaryInCount == null}">0</c:if>
								<c:if test="${employee.salaryInCount != null}">${employee.salaryInCount}</c:if>
							<br /> 
							<font color="green">工资总额(刻)</font>
						</td>
					</tr>
				</table>
	
				<table class="mytable am-table-bordered am-table-hover" onclick='showAvgStarInfo(${employee.id});'>
					<tr>
						<td>
								<c:if test="${avgStar == null}">0</c:if>
								<c:if test="${avgStar != null}">${avgStar}</c:if>
							<br /> 
							<font color="green">上月评价星数</font>
						</td>
					</tr>
				</table>
	
				<table class="mytable am-table-bordered">
					<tr>
						<td>
						
							<c:choose>
								<c:when test="${fn:length(employee.stations)==0}">
									无
								</c:when>
								<c:otherwise>
									<c:forEach items="${employee.stations}" var="station">
										${station.name} &nbsp;
									</c:forEach>
								</c:otherwise>
							</c:choose>
						
							<br />
							<font color="green">所管理的服务站</font>
						</td>
					</tr>
				</table>
			</div>


       </div>
		
		
<script language="javascript">
function showOrderInfo(employeeId){
	var url = _ctx+ "/employee/orderInfo.do";
	window.open(url+"?employeeId="+employeeId);
}

function showAvgStarInfo(employeeId){
	var url = _ctx+ "/serviceEvaluation/serviceEvaluationInfo.do";
	window.open(url+"?employeeId="+employeeId);
}

function showSalaryInfo(employeeId){
	var url = _ctx+ "/employee/salaryInfo.do";
	window.open(url+"?employeeId="+employeeId);
}

</script>
		
</body>
</html>