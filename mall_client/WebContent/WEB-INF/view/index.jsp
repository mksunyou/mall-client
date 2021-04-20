<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<!-- 메뉴1 -->
	<!-- 메뉴2 -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 로그인/회원가입/내정보-->
	<!-- 장바구니(세션) -->
	<!-- 캘린더(이번달에 나온 책들)-->
	<!-- 베스트셀러(주문량) -->
	<!-- 메뉴2 카테고리-->
	<h1>index</h1>
	
	<!-- bestseller 출력(1~5위) -->	
	<h3>Best Seller</h3>
	
	<table border="1">
		<tr>
			<c:forEach var="m" items="${bestOrdersList}">
					<td>
						<div><img src="${pageContext.request.contextPath}/img/default.jpg"></div>
						<!-- EbookOneController - EbookDao.selectEbookOne() - ebookOne.jsp -->
						<div>
							<a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${m.ebookNo}">
								${m.ebookTitle}%>
							</a>
						</div>

						<div>￦${m.ebookPrice}</div>
						<div>판대 부수 : ${m.cnt}</div>
					</td>
			</c:forEach>
		</tr>
	</table>
	
	
	<!-- 카테고리별 -->
		<ul>
			<li><a href = "${pageContext.request.contextPath}/IndexController">전체보기</a></li>
	
	<c:forEach var="c" items="${categoryList}">
			<li><a href = "${pageContext.request.contextPath}/IndexController?categoryName=${c.categoryName}">${c.categoryName}</a></li>	
	</c:forEach>

		</ul>
	
	<!-- 상품 출력 -->
	<table border="1">
		<tr>
			<c:set var = "count" value = "0"/>
			<c:forEach var = "ebook" items = "${ebookList}">
				<c:set var = "count" value = "${count + 1}"/>
				<td>
					<div><img src="${pageContext.request.contextPath}/img/default.jpg"></div>
					<!-- EbookOneController - EbookDao.selectEbookOne() - ebookOne.jsp -->
					<div>
						<a href = "${pageContext.request.contextPath}/EbookOneController?ebookNo=${ebook.ebookNo}">
							${ebook.ebookTitle}</a>
					</div>
					<div>￦${ebook.ebookPrice}</div>
				</td>
				<c:if test = "${count % 5 == 0}">
					</tr><tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	
	<br>
	<div class = "searchBox">
		<form action = "${pageContext.request.contextPath}/IndexController" method = "get">
			<input type = text name="searchWord" placeholder="책 제목을 입력해주세요"> <button type = submit> 검색</button>
		</form>
	</div>
	<br>
	<div class = "paging">
	<!-- 페이징 만드는 반복문 -->
		<c:forEach var = "i" begin="1" end="10" step="1">
			<!-- 마지막 페이지 체크 (break문이 없기때문에 이런식으로 함) -->
			<c:if test="${(pageRange * 10)+ i < (lastPage + 1)}">
				<!-- 특정 카테고리가 있을때 (카테고리 정렬과 검색어 기능을 같이 사용할수없음.) -->
				<c:if test="${categoryName != null}">
					<!-- 이전버튼 생성 -->
					<c:if test="${i == 1}">
						<!-- 1페이지이면 이전버튼 비 활성화 -->
						<c:if test="${currentPage == 1}">
		
							<button disabled="disabled">이전</button>
						</c:if>
						<!-- 1페이지가 아니라면 이전 페이지로 이동 -->
						<c:if test="${currentPage != 1}">
							<a href="${pageContext.request.contextPath}/IndexController?categoryName=${categoryName }&currentPage=${currentPage -1}"><button>이전</button></a>
						</c:if>	
					</c:if>
					<a href="${pageContext.request.contextPath}/IndexController?categoryName=${categoryName }&currentPage=${(pageRange*10)+i}"><button>${(pageRange*10)+i}</button></a>

					<!-- 마지막 페이지엔 X -->
					<c:if test="${i == 10}">
						<a href="${pageContext.request.contextPath}/IndexController?categoryName=${categoryName }&currentPage=${currentPage + 1}"><button>다음</button></a>
					</c:if>		
				</c:if>
				<!-- 카테고리 정렬이 없을때 -->
				<c:if test="${categoryName == null}">
					<!-- 검색어가 있을경우 -->
					<c:if test="${searchWord != null}">
						<c:if test="${i == 1}">
							<!-- 1페이지이면 이전버튼 비 활성화 -->
							<c:if test="${currentPage == 1}">
								<button disabled="disabled">이전</button>
							</c:if>
							<!-- 1페이지가 아니라면 이전 페이지로 이동 -->
							<c:if test="${currentPage != 1}">
								<a href="${pageContext.request.contextPath}/IndexController?searchWord=${searchWord }&currentPage=${currentPage -1}"><button>이전</button></a>
							</c:if>	
						</c:if>
						<!-- 페이징 숫자표현 -->
						<a href="${pageContext.request.contextPath}/IndexController?searchWord=${searchWord }&currentPage=${(pageRange*10)+i}"><button>${(pageRange*10)+i}</button></a>
						
						<!-- 마지막 페이지이면 여기까지 코드 진행 되지않음. -->
						<c:if test="${i == 10}">
							<a href="${pageContext.request.contextPath}/IndexController?searchWord=${searchWord }&currentPage=${currentPage +1}"><button>다음</button></a>
						</c:if>	

					</c:if>
								
					<!-- 검색어가 없을 경우 -->
					<c:if test="${searchWord == null}">
						<c:if test="${i == 1}">
						
							<!-- 1페이지이면 이전버튼 비 활성화 -->
							<c:if test="${currentPage == 1}">
								<button disabled="disabled">이전</button>
					</c:if>
							<!-- 1페이지가 아니라면 이전 페이지로 이동 -->
							<c:if test="${currentPage != 1}">
								<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage -1}"><button>이전</button></a>
							</c:if>	
						</c:if>
						<!-- 페이징 숫자표현 -->
						<a href="${pageContext.request.contextPath}/IndexController?currentPage=${(pageRange*10)+i}"><button>${(pageRange*10)+i}</button></a>

						<!-- 마지막 페이지이면 여기까지 코드 진행 되지않음. -->
						<c:if test="${i == 10}">
							<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage +1}"><button>다음</button></a>
						</c:if>	
					</c:if>
				</c:if>
			</c:if>
			<!-- 마지막페이지 검사 끝 -->
		</c:forEach>
	</div>
</body>
</html>