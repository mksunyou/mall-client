<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import = "mall.client.vo.*" %>
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
	<%
		List<Map<String,Object>> cartList = (List<Map<String,Object>>)(request.getAttribute("cartList"));

	%>	
	<table border="1">
		
		<tr>
			<td>cartNo</td>
			<td>ebookNo</td>
			<td>ebookTitle</td>
			<td>cartDate</td>
			<td>Order</td>
			<td>Delete</td>
		</tr>
		
		<%
				for(Map<String,Object> map : cartList) {
		%>
		<tr>
			<td><%=map.get("cartNo")%></td>
			<td><%=map.get("ebookNo")%></td>
			<td><%=map.get("ebookTitle")%></td>
			<td><%=map.get("cartDate")%></td>
			<!-- InsertOrdersController - insertOrders() deleteCart(): ISSUE 트랜처리 - redirect 주문리스트 -->	
			<td><a href="<%=request.getContextPath()%>/OrderCartController?cartNo=<%=map.get("cartNo")%>">Order</a></td>
			<!-- DeleteCartController - CartDao.deleteCart) - redirect:/CartListController -->
			<td><a href="<%=request.getContextPath()%>/DeleteCartController?cartNo=<%=map.get("cartNo")%>">Delete</a></td>
				
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>