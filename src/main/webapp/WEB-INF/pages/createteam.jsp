<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>Tạo đội bóng</title>
</head>
<div class="col-sm-2">
	<h2>Đăng ký đội bóng2</h2>
	<p>(*) Là phần thông tin bắt buộc</p>
</div>
<spring:bind path="team.*">
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
<h4>${message}</h4>
<form:form modelAttribute="team" method="post" action="createteam">
	<div style="background-color: #f5f5f5">
		<table style="border: 0;" align="center">
			<tr>
				<td><label for="nameInput">Tên đội bóng:</label></td>
				<td><form:input path="name" id="nameInput" size="50" /></td>
			</tr>

			<tr>
				<td><label for="colorInput">Màu Áo: </label></td>
				<td><form:input path="color" id="colorInput" size="50" /></td>
			</tr>
			<tr>
				<td><label for="typeOptions">Loại sân: </label></td>
				<td><form:select path="type" id="typeOptions" style="width: 340px;">
						<form:option value="5">Sân 5 người</form:option>
						<form:option value="7">Sân 7 người</form:option>
						<form:option value="9">Sân 9 người</form:option>
						<form:option value="11">Sân 11 người</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td><label for="stadiumInput">Sân Nhà: </label></td>
				<td><form:input path="stadium" id="stadiumInput" size="50" /></td>
			</tr>
			<tr>
				<td><label for="regionInput">Khu Vực: </label></td>
				<td><form:input cssClass="form-control" path="region" id="regionInput" size="50" /></td>
			</tr>
			<tr>
				<td><label for="descriptionInput">Thông tin: </label></td>
				<td><form:textarea path="description" id="descriptionInput" cols="47" /></td>
			</tr>
		</table>
	</div>
	<input type="submit" value="Tạo đội bóng" />
</form:form>
