<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include指令</title>
</head>
<body>
	<%-- request.getRequestDispatcher("/include/head.jsp").forward(request, response);// 这样就是请求转发了 --%>
	<% request.getRequestDispatcher("/include/head.jsp").include(request, response);// 这样就是请求转发了 %>
	<h1>body</h1>
	<% request.getRequestDispatcher("/include/foot.jsp").include(request, response);// 这样就是请求转发了 %>
	<!-- 上面这种是动态引入，所有的jsp文件翻译执行完，合并后再输出，但是输出的顺序和引入的顺序不一致 -->
	<h1>——————————分割线——————————</h1>
	
	<!-- 下面这种是静态引入，所有的jsp文件翻译执行完，合并后再输出 -->
	<%@ include file="/include/head.jsp" %>
	<h1>body</h1>
	<%@ include file="/include/head.jsp" %>
</body>
</html>