<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 没有写action  -->
<fm:form method="post" modelAttribute="user"> <br/>
	UserName:<fm:input path="username"/> <fm:errors path="username"></fm:errors> <br/>
	Password:<fm:password path="password"/> <fm:errors path="password"></fm:errors> <br/>
	NickName:<fm:input path="nickname"/> <fm:errors path="nickname"></fm:errors> <br/>
	email:<fm:input path="email"/> <fm:errors path="email"></fm:errors> <br/>
	<input type="submit" value="更新"/>
</fm:form>

</body>
</html>