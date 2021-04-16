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
<%
	List<Map<String,Object>> ordersList = (List<Map<String,Object>>)request.getAttribute("ordersList");
%>

<!-- mainMenu -->
<div>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
</div>

	<!-- ordersList -->
	<h1>ordersList</h1>
	
	<table border="1">
		<tr>
			<th>ordersNo</th>
			<th>ebookNo</th>
			<th>ordersDate</th>
			<th>ordersState</th>
			<th>ebookTitle</th>
			<th>ebookPrice</th>
		</tr>
		<%
			for(Map m : ordersList) {
		%>
				<tr>
					<td><%=(Integer)(m.get("ordersNo")) %>
					<td><%=(Integer)(m.get("ebookNo")) %>
					<td><%=(String)(m.get("ordersDate")) %>
					<td><%=(String)(m.get("ordersState")) %>
					<td><%=(String)(m.get("ebookTitle")) %>
					<td><%=(Integer)(m.get("ebookPrice")) %>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>