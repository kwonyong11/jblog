<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/menu.jsp" />
		<form class="search-form"
			action="${pageContext.request.contextPath }/" method="post">
			<fieldset>
				<input type="text" name="keyword" /> <input type="submit"
					value="검색" />
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="blog-title" checked="checked"> <label>블로그 제목</label>
				<input type="radio" name="which" value="tag"> <label>태그</label>
				<input type="radio" name="which" value="blog-user"> <label>블로거</label>
			</fieldset>
		</form>
		
		<table style="width:100%; border-top: 1px solid #444444; border-collapse: collapse;">
			<tr>
				<th style="border-bottom: 1px solid #444444; padding: 10px;">블로그 제목</th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;">블로거</th>
			</tr>
			<c:if test='${keyword!="" }'>
			<c:forEach items="${list }" var="list">
			<tr>
				<td style="border-bottom: 1px solid #444444; padding: 10px;"><a href="${pageContext.request.contextPath }/${list.id }">${list.title }</a></td>
				<td style="border-bottom: 1px solid #444444; padding: 10px;"><a href="${pageContext.request.contextPath }/${list.id }">${list.id }</a></td>
			</tr>
			</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>