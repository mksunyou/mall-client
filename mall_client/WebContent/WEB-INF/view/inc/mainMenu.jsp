<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${loginClient == null }">
		<!-- 로그아웃일때 -->
		<form action = "${pageContext.request.contextPath}/LoginController" method = "post">
			ID : <input type = "text" name = "clientMail">
			PW : <input type = "password" name = "clientPw">
			<button type = "submit">로그인</button>
		</form>
		<ul>
			<!-- InsertClientController -> /view/insertClient.jsp -->
			<li><a href = "${pageContext.request.contextPath}/InsertClientController">회원가입</a></li>
		</ul>
		<ul>
			<!-- InsertClientController -> /view/insertClient.jsp -->
			<li><a href = "${pageContext.request.contextPath}/EbookCalendarController">ebook 달력</a></li>
		</ul>
</c:if>

<c:if test="${loginClient != null }">
		<!-- 로그인일때 -->
		<div>
			<div>
				${loginClient.clientMail}님 반갑습니다.
			</div>
			<ul>
				<li><a href="${pageContext.request.contextPath}/IndexController">홈으로</a></li>
				<li><a href = "${pageContext.request.contextPath}/CartListController">장바구니</a></li>
				<li><a href="${pageContext.request.contextPath}/EbookCalendarController">ebook 달력</a>
				<!-- ClientOneController -> ClientDao.selectclientOne() -> /view/client/clientOne.jsp-->
				<li><a href = "${pageContext.request.contextPath}/ClientOneController">회원정보</a></li>
				<li><a href = "${pageContext.request.contextPath}/LogoutController">로그아웃</a></li>
				<li><a href = "${pageContext.request.contextPath}/OrdersListController">주문내역</a></li>
			</ul>
		</div>
</c:if>