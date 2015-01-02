<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title><fmt:message key="home.title" /></title>
<script src="<c:url value="/resources/scripts/player.js"/>"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/fp-jquery-ui.css"/>" />
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/fpstyle.css"/>" />
</head>
<body>
<table class="gridtable">

		<tr>
			<th>Tên đội bóng</th>
			<th>Khu vực</th>
			<th>Đội trưởng</th>
			<th>Màu áo</th>
			<th>Sân vận động</th>
			<th>Thông tin</th>
		</tr>

		<c:forEach items="${teams}" var="teamObj">
			<tr> 
				<td><a href="<c:url value='/teammanager/'/>${teamObj.id}">${teamObj.name}</a></td>
				<td>${teamObj.region}</td>
				<td>${teamObj.captain.username}</td>
				<td>${teamObj.color}</td>
				<td>${teamObj.stadium}</td>
				<td>${teamObj.description}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>