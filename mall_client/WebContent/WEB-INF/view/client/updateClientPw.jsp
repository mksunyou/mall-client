<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateClientPw</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 -->
	<h1>updateClientPw</h1>
	
	<form method = "post" action = "${pageContext.request.contextPath}/UpdateClientPwController">
		<input type = "hidden" value = "${pageContext.request.contextPath}" name = "clientMail">
		<table border="1">

			<tr>
				<td>ClientPw</td>
				<td><input type = "password" name = "clientPw" required="required"></td>
			</tr>
			
			<tr>
				<td>NewClientPw</td>
				<td><input type = "password" name="newPw" required="required"></td>
			</tr>
		</table>
		<button type="submit">비밀번호 변경</button>
		<a href="${pageContext.request.contextPath}/ClientOneController"><button type="button">취소</button></a>
	</form>
	
</body>
</html>