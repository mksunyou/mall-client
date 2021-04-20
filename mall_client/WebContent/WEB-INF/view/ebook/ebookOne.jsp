<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ebook One</title>
</head>
<body>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- 메뉴 1 -->
	<h1>ebook정보</h1>
	<table border="1">
		<tr>
			<td>ebook_no</td>
			<td>${ebook.ebookNo}</td>
		</tr>
		
		<tr>
			<td>ebook_ISBN</td>
			<td>${ebook.ebookISBN}</td>
		</tr>
		
		<tr>
			<td>category_name</td>
			<td>${ebook.categoryName}</td>
		</tr>
		
		<tr>
			<td>ebook_title</td>
			<td>${ebook.ebookTitle}</td>
		</tr>
		
		<tr>
			<td>ebook_author</td>
			<td>${ebook.ebookAuthor}</td>
		</tr>
		
		<tr>
			<td>ebook_company</td>
			<td>${ebook.ebookCompany}</td>
		</tr>
		
		<tr>
			<td>ebook_pageCount</td>
			<td>${ebook.ebookPageCount}</td>
		</tr>
		
		<tr>
			<td>ebook_price</td>
			<td>${ebook.ebookPrice}</td>
		</tr>
		
		<tr>
			<td>ebook_img</td>
			<td><img src="${pageContext.request.contextPath}/img/${ebook.ebookImg}"></td>
		</tr>
		
		<tr>
			<td>ebook_summary</td>
			<td>${ebook.ebookSummary}</td>
		</tr>
		
		<tr>
			<td>ebook_date</td>
			<td>${ebook.ebookDate}</td>
		</tr>
		
		<tr>
			<td>ebook_state</td>
			<td>${ebook.ebookState}</td>
		</tr>
	</table>
	<!-- InsertCartController?ebookNo - CartDao.insertCart() - redirect: CartListController -->
	<a href="${pageContext.request.contextPath}/InsertCartController?ebookNo=${ebook.ebookNo}">
		<c:if test="${loginClient != null && ebook.ebookState.equals('판매중')}"> <!-- test = "자바코드" -->
			<button type="button">장바구니 추가</button>
		</c:if>
		<c:if test="${loginClient == null || ebook.ebookState.equals('품절') || ebook.ebookState.equals('절판') || ebook.ebookState.equals('구편절판')}"> <!-- test = "자바코드" -->
			<button type="button">장바구니 추가</button>
		</c:if>
		
	</a>
</body>
</html>