<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<c:forEach var="post" items="${postsEntity}">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">${post.title}</h4>
				<a href="/posts/${post.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div><!-- end of card -->
	</c:forEach>
</div><!-- end of container -->



<%@ include file="../layout/footer.jsp"%>