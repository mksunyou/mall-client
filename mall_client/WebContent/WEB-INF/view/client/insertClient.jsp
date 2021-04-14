<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<form method = "post" action = "<%=request.getContextPath()%>/InsertClientController">
		<table>
			<thead>
				<tr>
					<th>메일주소</th>
					<th>비밀번호</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type = "text" name = "clientMail" placeholder="example@exam.com" required="required">
					</td>
					<td>
						<input type = "password" name = "clientPw" required="required">
					</td>
				</tr>
			</tbody>
		</table>
		<button type="submit">입력</button>
		<a href="<%=request.getContextPath()%>/IndexController"><button type="button">취소</button></a>
	</form>

</body>
</html> 