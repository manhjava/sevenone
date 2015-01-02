<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Quản lý đội bóng</title>

<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/team.css"/>" />
</head>
<body>

	<div class="content" >
		<h2><c:out value="${team.name}" /></h2>
	</div>

	<ol id="toc">
		<li><a href="#tab-overview"><span>OVERVIEW</span></a></li>
		<li><a href="#tab-squad"><span>SQUAD</span></a></li>
		<li><a href="#tab-statistics"><span>STATISTICS</span></a></li>
		<li><a href="#tab-stadium"><span>STADIUM</span></a></li>
		<li><a href="#tab-schedule"><span>SCHEDULE</span></a></li>
	</ol>

	<div class="content" id="tab-overview">
		<h2>Thông tin</h2>
		<table>
		<tr><th>Tên đội:</th><td><c:out value="${team.name}" /></td></tr>
		<tr><th>Màu áo:</th><td><c:out value="${team.color}" /></td></tr>
		<tr><th>Loại sân:</th><td><c:out value="${team.type}" /></td></tr>
		<tr><th>Sân vận động</th><td><c:out value="${team.stadium}" /></td></tr>
		<tr><th>Khu vực:</th><td><c:out value="${team.region}" /></td></tr>
		<tr><th>Thông tin:</th><td><c:out value="${team.description}" /></td></tr>
		</table>
	</div>
	<div class="content" id="tab-squad">
		<h2>Squad</h2>
		<table>
			<tr>
				<th></th>
				<th>Name</th>
				<th>Ngày sinh</th>
				<th>Position</th>
			</tr>
			<c:forEach items="${team.players}" var="player">
				<tr>
					<td><img src="${player.fbimageUrl}" border="0"/></td>
					<td><a href="${player.fbprofileUrl}" target="_blank">${player.fbdisplayName}</a></td>
					<td>${player.dob}</td>
					<td>${player.position}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="content" id="tab-statistics">
		<h2>Page 3</h2>
		<p>Text...</p>
	</div>
	<div class="content" id="tab-stadium">
		<h2>Page 4</h2>
		<p>Text...</p>
	</div>
	<div class="content" id="tab-schedule">
		<h2>Schedule</h2>
		<table>
			<tr>
				<th>Date</th>
				<th>Match</th>
				<th>Stadium</th>
				<th>Result</th>
			</tr>
			<tr>
				<td>20-10-2015</td>
				<td>Manchester vs Real Mandrid</td>
				<td>Old tralford</td>
				<td>2-1</td>
			</tr>
			<tr>
				<td>27-10-2015</td>
				<td>Real VS MU</td>
				<td>Benabuer</td>
				<td>2-2</td>
			</tr>
			<tr>
				<td>31-10-2015</td>
				<td>Tot VS MU</td>
				<td>White hardlane</td>
				<td>0-1</td>
			</tr>
		</table>
	</div>
	<script src="<c:url value="/resources/scripts/team.js"/>"></script>
	<script type="text/javascript">
		pageLoad();
	</script>
</body>
</html>