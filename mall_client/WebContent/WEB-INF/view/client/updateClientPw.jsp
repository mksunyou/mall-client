<%@page import="mall.client.controller.ClientOneController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateClientPw</title>
</head>
<body>
<%
	Client client = (Client)request.getAttribute("client");
%>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 -->
	<h1>updateClientPw</h1>
	
	<form method = "post" action = "<%=request.getContextPath()%>/UpdateClientPwController">
		<input type = "hidden" value = "<%=client.getClientMail()%>" name = "clientMail">
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
		<a href="<%=request.getContextPath()%>/ClientOneController"><button type="button">취소</button></a>
	</form>
	
</body>
</html>