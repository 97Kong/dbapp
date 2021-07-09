<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<!-- username=머시기&password=머시기&email=머시기&address=머시기 -->
	<form action="/post/${postEntity.id}/update" method="post">
		<div class="form-group">
			<label for="title">Title:</label> 
			<input value="${postEntity.title}" type="text" class="form-control" placeholder="Enter title" name="title" required="required" />
		</div>

		<div class="form-group">
			<textarea id="summernote" rows="10" class="form-control" name="content">
				${postEntity.content}
			</textarea>
		</div>

		<button type="submit" class="btn btn-primary">수정 완료</button>
	</form>
</div>

<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			height : 500, // set editor height
			placeholder: 'Hello Bootstrap 4',
			tabsize: 2,
			minHeight : null, // set minimum height of editor
			maxHeight : null, // set maximum height of editor
			focus : true
		// set focus to editable area after initializing summernote
		});
	});
</script>


<%@ include file="../layout/footer.jsp"%>