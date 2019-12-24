<%@ page language="java" pageEncoding="UTF-8" errorPage="/errorPage.jsp"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<title>JavaWeb项目</title>
</head>

<body>
	<h1>我的网站</h1>
	<hr />
	
	<%
		// int i = 1/0; 
	%>
	
	<c:if test="${sessionScope.user==null}">
		欢迎光临！游客！
		<br />
		<br />
		<a href="${pageContext.request.contextPath }/regist.jsp">注册</a>
		<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
	</c:if>
	<c:if test="${sessionScope.user!=null}">
		欢迎回来！${sessionScope.user.username }！
		<a href="${pageContext.request.contextPath }/servlet/LogoutServlet">注销</a>
	</c:if>
</body>

</html>
