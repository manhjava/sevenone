<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chi tiết tài khoản</title>
<script src="<c:url value="/resources/scripts/player.js"/>"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/fp-jquery-ui.css"/>" />
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/fpstyle.css"/>" />
</head>
<body>
<input type="hidden" id="username" value="<c:out value="${user.username}" />">
<h2>Thông tin tài khoản</h2>
		<table>
		<tr><td>Tên đăng nhập </td><td><c:out value="${user.username}" /></td></tr>
		<tr><td>Email:</td><td><input type="text" value='<c:out value="${user.email}" />' id="email"></td></tr>
		<tr><td>Số điện thoại:</td><td><input type="text" value='<c:out value="${user.phoneNumber}" />' id="phoneNumber"></td></tr>
		<tr><td>Ngày sinh:</td><td><input type="text" value='<c:out value="${user.dob}" />' id="dob"></td></tr>
		<tr><td>Vị trí sở trường:</td><td><input type="text" value='<c:out value="${user.position}" />' id="position"></td></tr>
		<tr><td><input type="button" value="Cập nhật thông tin" onclick="updateAccount()"></td></tr>
		</table>
</body>
</html>