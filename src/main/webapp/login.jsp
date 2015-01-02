<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <title><fmt:message key="login.title"/></title>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/styles/page-style.css"/>" />
    <script src="<c:url value="/resources/scripts/login.js"/>"></script>
</head>
<body id="login">

<form method="post" id="loginForm" action="<c:url value='/j_security_check'/>"
    onsubmit="return validateLoginForm()" class="form-signin" autocomplete="off">
    <br>
    <h2 class="form-signin-heading">
        Đăng nhập
    </h2>
<c:if test="${param.error != null}">
    <div class="alert alert-danger alert-dismissable">
        <fmt:message key="errors.password.mismatch"/>
    </div>
</c:if>
    <input type="text" name="j_username" id="j_username" class="form-control"
           placeholder="Tên đăng nhập hoặc email" tabindex="1">
    <input type="password" class="form-control" name="j_password" id="j_password" tabindex="2"
           placeholder="Mật khẩu" >


    <button type="submit" class="btn-lg btn-primary btn-block" name="login" tabindex="4">
        Đăng nhập
    </button>
		
<!-- FACEBOOK SIGNIN -->
<p style="padding: 15px"><a href="<c:url value="/auth/facebook?scope=email"/>"><img src="<c:url value="/resources/images/sign-in-with-facebook.png"/>" border="0"/></a></p>
<p>
    Chưa là thành viên? <a href="<c:url value="/signup"/>">Đăng ký mới.</a>
</p>

<p>Quên mật khẩu? <a href="?" onclick="requestRecoveryToken(); return false">Gửi mật khẩu mới</a> tới email.</p>
</form>
<script type="text/javascript">
var menuS = $( "#top-main-menu" ).html();
menuS += '<li><a href="http://7bongda.com/nhat-ky/">Lịch thi đấu</a></li>';

$( "#top-main-menu" ).html(menuS);
</script>
</body>