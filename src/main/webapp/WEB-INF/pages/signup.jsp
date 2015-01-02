<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>Đăng ký</title>
<script src="<c:url value="/resources/scripts/signup.js"/>"></script>
</head>
<div class="col-sm-2">
	<h2>Đăng ký tài khoản</h2>
	<p>(*) Là phần thông tin bắt buộc</p>
</div>
<spring:bind path="user.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="alert alert-danger alert-dismissable">
			<a href="#" data-dismiss="alert" class="close">&times;</a>
			<c:forEach var="error" items="${status.errorMessages}">
				<c:out value="${error}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>
<form:form modelAttribute="user" method="post" action="signup" onsubmit="return validateSignup()" >
	<div style="background-color: #f5f5f5">
		<table style="border: 0;" align="center">
			<tr>
				<td><label for="username">Tên đăng nhập:</label></td>
				<td><form:input path="username" id="username" autofocus="true" size="50" onchange="ajaxValidateUserName();"/></td>
			</tr>

			<tr>
				<spring:bind path="user.password" />
				<td><label for="password">Mật khẩu:</label></td>
				<td><form:password path="password" id="password" showPassword="true" size="50" /></td>
			</tr>
			<tr>
				<spring:bind path="user.confirmPassword" />
				<td><label for="confirmPassword">Nhập lại mật khẩu:</label></td>
				<td><form:password path="confirmPassword" id="confirmPassword" size="50" /></td>
			</tr>
			<tr>
				<spring:bind path="user.email" />
				<td><label for="email">Email:</label></td>
				<td><form:input path="email" id="email" size="50" /></td>
			</tr>
			<tr>
				<spring:bind path="user.firstName" />
				<td><label for="firstName">Tên:</label></td>
				<td><form:input path="firstName" id="firstName" maxlength="50" size="50" /></td>
			</tr>
			<tr>
				<spring:bind path="user.lastName" />
				<td><label for="lastName">Họ:</label></td>
				<td><form:input path="lastName" id="lastName" maxlength="50" size="50" /></td>
			</tr>
			<tr>
				<spring:bind path="user.dob" />
				<td><label for="dob">Ngày sinh:(24/12/1990)</label></td>
				<td><form:input path="dob" id="dob" maxlength="50" size="50" /></td>
			</tr>

			<tr>
				<spring:bind path="user.height" />
				<td><label for="height">Chiều cao:</label></td>
				<td><form:input path="height" id="height" maxlength="50" size="50" /></td>
			</tr>

			<tr>
				<spring:bind path="user.weight" />
				<td><label for="weight">Cân nặng:</label></td>
				<td><form:input path="weight" id="weight" maxlength="50" size="50" /></td>
			</tr>

			<tr>
				<spring:bind path="user.position" />
				<td><label for="position">Vị trí sở trường:</label></td>
				<td><form:input path="position" id="position" maxlength="50" size="50" /></td>
			</tr>

		</table>
	</div>
	<input type="submit" value="Đăng ký" />
</form:form>

