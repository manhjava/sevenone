<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="<c:url value="/resources/scripts/jquery-2.0.3.min.js"/>"></script>
<script src="<c:url value="/resources/scripts/application.js"/>"></script>
<link rel="apple-touch-icon" href="<c:url value="/resources/images/favicon.png"/>" />
<link href="<c:url value="/resources/images/favicon.ico"/>" rel="shortcut icon" />
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/home-style.css"/>" />
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/default.css"/>" />
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/font-awesome.min.css"/>" />
<link rel="icon" href="<c:url value="/resources/images/favicon.ico"/>" />
<title><decorator:title /> | <fmt:message key="webapp.name" /></title>
<decorator:head />
</head>
<body>
	<header class="navbar navbar-fixed-top">
		<div class="navbar-inner navbar-green">
			<div class="logo">
				<a class="brand animated fadeInDown" href="<c:url value="/index.jsp"/>" title="Trang Chủ">Home page</a>
				<div class="divider-vertical pull-right"></div>
			</div>

			<div class="my-nav pull-right">
				<c:if test="${empty pageContext.request.remoteUser}">
					<ul class="nav pull-right">
						<li class="dropdown"><a id="loginDrop" data-toggle="dropdown" class="dropdown-toggle text_link" href="<c:url value="/login.jsp"/>"><i class="icon-signin icon-large"></i>
								Đăng Nhập </a>
							<div id="loginbox" class="dropdown-menu pull-right fade in" style="padding: 15px 15px 0 15px;">
								<input type="hidden" name="csrfmiddlewaretoken" value="tBg3NOlgmxP7iWAtScgIwefQmldrRUHI"> <input id="user_username" style="margin-bottom: 15px;" type="text"
									placeholder="Tên sử dụng" name="username" size="30"> <input id="user_password" style="margin-bottom: 15px;" type="password" placeholder="Mật khẩu" name="password"
									size="30">
								<button class="btn btn-success btn-block" type="submit">
									<i class="icon-white icon-lock"></i> Đăng Nhập
								</button>
								<a href="https://7bongda.com/accounts/password/reset/" class="btn btn-link">Bạn đã quên mật khẩu?</a>
							</div></li>
						<li><a href="<c:url value="/signup"/>" class="text_link"> <i class="icon-ok-sign icon-large"></i> Đăng Ký
						</a></li>
						<li><a href="<c:url value="/auth/facebook?scope=email"/>" class="text_link"> <i class="fa icon-facebook-sign fa-5x"></i> facebook-sign
						</a></li>
					</ul>
				</c:if>
				<c:if test="${not empty pageContext.request.remoteUser}">
					<ul class="nav pull-right" style="padding-right: 160px">

						<li><div id="noti_Container">
								<img src="<c:url value="/resources/images/notification.png"/>" alt="profile" />
								<div class="noti_bubble">7</div>
							</div></li>
							
						<li><div class="welcome_user">Xin chào: ${pageContext.request.remoteUser}</div></li>	

						<li class="dropdown"><a onclick="openLoginBox();" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="500" data-close-others="false"> <i
								class="icon-chevron-down"></i>
						</a>
							<div id="loginbox" class="dropdown-menu pull-right fade in" style="padding: 15px 15px 15px 15px;" aria-labelledby="loginDrop">
								<ul>
									<li><a href="<c:url value="/teammanager/myteam"/>">My Team</a></li>
									<li><a href="<c:url value="/my-account-details"/>"><i class="icon-info"></i> Thông tin cá nhân.</a></li>
									<li><a href="<c:url value="/logout.jsp"/>" class="text_link"> <i class="icon-signout icon-large"></i> Đăng xuất
									</a></li>
								</ul>
							</div></li>
					</ul>
				</c:if>
			</div>

			<div class="nav-collapse my-nav collapse">
				<ul class="nav" id="top-main-menu">
					<li><a href="<c:url value="/all-players"/>">Cầu thủ</a></li>
					<li><a href="<c:url value="/all-teams"/>">Đội bóng</a></li>
					<c:if test="${not empty pageContext.request.remoteUser}">
						<li><a href="<c:url value="/createteam"/>">Tạo đội bóng</a></li>
						<li><a href="<c:url value="/teammanager/myteam"/>">Đội bóng của tôi</a></li>
					</c:if>
				</ul>
			</div>

		</div>
	</header>

	<div class="container containerbody" id="content">
		<%@ include file="/common/messages.jsp"%>
		<div class="row">
			<div id="7bd-message"></div>
			<decorator:body />
		</div>
	</div>

	<div id="footer" class="container navbar-fixed-bottom">
		<span class="col-sm-6 text-left"><fmt:message key="webapp.version" /> <c:if test="${pageContext.request.remoteUser != null}">
            | <fmt:message key="user.status" /> ${pageContext.request.remoteUser}
            </c:if> </span> <span class="col-sm-6 text-right"> &copy; <fmt:message key="copyright.year" /> <a href="<fmt:message key="company.url"/>"><fmt:message key="company.name" /></a>
		</span>
	</div>
</body>
</html>
