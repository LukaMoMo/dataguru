<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="add">添加用户</a>
	用户列表 <br/>
	<c:forEach items="${users}" var="um">
		<a href="${um.value.username }"> ${um.value.username } </a>---------------${um.value.password }---------------${um.value.email } ---- ${um.value.nickname}  <a href="${um.value.username}/update">更新用户 </a>
		<a href="${um.value.username}/delete">删除</a><br/>
		
	</c:forEach>
</body>
</html>