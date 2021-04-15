<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ebook One</title>
</head>
<body>
<%
	Ebook ebook = (Ebook)request.getAttribute("ebook");
%>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- 메뉴 1 -->
	<h1>ebook정보</h1>
	<table border="1">
		<tr>
			<td>ebook_no</td>
			<td><%=ebook.getEbookNo()%></td>
		</tr>
		
		<tr>
			<td>ebook_ISBN</td>
			<td><%=ebook.getEbookISBN()%></td>
		</tr>
		
		<tr>
			<td>category_name</td>
			<td><%=ebook.getCategoryName()%></td>
		</tr>
		
		<tr>
			<td>ebook_title</td>
			<td><%=ebook.getEbookTitle()%></td>
		</tr>
		
		<tr>
			<td>ebook_author</td>
			<td><%=ebook.getEbookAuthor()%></td>
		</tr>
		
		<tr>
			<td>ebook_company</td>
			<td><%=ebook.getEbookCompany()%></td>
		</tr>
		
		<tr>
			<td>ebook_pageCount</td>
			<td><%=ebook.getEbookPageCount()%></td>
		</tr>
		
		<tr>
			<td>ebook_price</td>
			<td><%=ebook.getEbookPrice()%></td>
		</tr>
		
		<tr>
			<td>ebook_img</td>
			<td><img src="<%=request.getContextPath()%>/img/<%=ebook.getEbookImg()%>"></td>
		</tr>
		
		<tr>
			<td>ebook_summary</td>
			<td><%=ebook.getEbookSummary()%></td>
		</tr>
		
		<tr>
			<td>ebook_date</td>
			<td><%=ebook.getEbookDate()%></td>
		</tr>
		
		<tr>
			<td>ebook_state</td>
			<td><%=ebook.getEbookState()%></td>
		</tr>
	</table>
	<!-- InsertCartController?ebookNo - CartDao.insertCart() - redirect: CartListController -->
	<a href="<%=request.getContextPath()%>/InsertCartController?ebookNo=<%=ebook.getEbookNo()%>">
		<%
			if(session.getAttribute("loginClient") == null 
			|| ebook.getEbookState().equals("품절")
			|| ebook.getEbookState().equals("절판")
			|| ebook.getEbookState().equals("구편절판")) {
		%>
			<button type="button" disabled="disabled">장바구니 추가</button> <!--  -->
		<%
			} else {
		%>
			<button type="button">장바구니 추가</button>
		<%
		}
		%>
	</a>
</body>
</html>