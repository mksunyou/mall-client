<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- mainMenu -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- cartList -->
	<h1>cartList</h1>

	<table border="1">
		
		<tr>
			<td>cartNo</td>
			<td>ebookNo</td>
			<td>ebookTitle</td>
			<td>cartDate</td>
			<td>Order</td>
			<td>Delete</td>
		</tr>
		
	
		<c:forEach var="m" items="${cartList}">
		<tr>
			<td>${m.cartNo}</td>
			<td>${m.ebookNo}</td>
			<td>${m.ebookTitle}</td>
			<td>${m.cartDate.substring(0,11)}</td>
			<!-- InsertOrdersController - insertOrders() deleteCart(): ISSUE 트랜처리 - redirect 주문리스트 -->	
			<td><a href="${pageContext.request.contextPath}/InsertOrdersController?ebookNo=${m.ebookNo}">Order</a></td>
			<!-- DeleteCartController - CartDao.deleteCart) - redirect:/CartListController -->
			<td><a href="${pageContext.request.contextPath}/DeleteCartController?ebookNo=${m.ebookNo}">Delete</a></td>
				
		</tr>
		</c:forEach>
	</table>
</body>
</html>