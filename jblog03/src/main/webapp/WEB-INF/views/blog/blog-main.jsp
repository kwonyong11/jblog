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
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.title }</h4>
					<p>${post.contents }
					<p>
				</div>
				<ul class="blog-list">
					<c:choose>
						<c:when test="${checkPost == 0 }">
							<li><a>아직 게시물이 없습니다.</a></li>
						</c:when>
						<c:otherwise>
							<c:forEach items='${postList }' var='postList'>
								<li><a
									href="${pageContext.request.contextPath}/${id }/${categoryNo }/${postList.no }">${postList.title }</a><span>${postList.regDate }</span></li>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</ul>
			</div>
		</div>
		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/assets/${basic.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:choose>
					<c:when test="${checkCategory == 0 }">
						<li><a>아직 카테고리가 없습니다.</a></li>
					</c:when>
					<c:otherwise>
						<c:forEach items='${category }' var='category'>
							<li><a href="${pageContext.request.contextPath}/${id }/${category.no }">${category.name }</a></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>