<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clientOne</title>
</head>
<body>
<%
	Client client = (Client)request.getAttribute("client");
%>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 -->

	<h1>고객정보</h1>
	<table border="1">
		<tr>
			<td>clientNo</td>
			<td><%=client.getClientNo() %></td>
		</tr>

		<tr>
			<td>clientMail</td>
			<td><%=client.getClientMail() %></td>
		</tr>

		<tr>
			<td>clientDate</td>
			<td><%=client.getClientDate() %></td>
		</tr>
	</table>
	<!-- UpdateClientPwController.doGet() - updateClientPw.jsp(forward) -->
	<!-- UpdateClientPwController.doPost() - ClientDao.updateClientPw() - session.invalidate()(update 성공했을때) - redirect:/IndexController(로그아웃) -->
	<a href="<%=request.getContextPath()%>/UpdateClientPwController"><button type="button">비밀번호수정</button></a>
	<!-- DeleteClientController - CartDao.deleteCartByClient(mail), ClientDao.deleteClient() - session.invalidate()(탈퇴 후 로그아웃) - redirect:/IndexController(로그아웃) -->
	<a href="<%=request.getContextPath()%>/DeleteClientController"><button type="button">회원탈퇴</button></a>
</body>
</html>  