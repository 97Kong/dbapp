<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<!-- username=머시기&password=머시기&email=머시기&address=머시기 -->
	<form action="#" onsubmit="updatePost()">
		<div class="form-group">
			<label for="title">Title:</label> <input id="title" value="${postEntity.title}" type="text" class="form-control" placeholder="Enter title" name="title" required="required" />
		</div>

		<div class="form-group">
			<textarea id="content" rows="10" class="form-control" name="content">
				${postEntity.content}
			</textarea>
		</div> 

		<button type="submit" class="btn btn-primary">수정 완료</button>
	</form>
</div>

<script>
	async function updatePost(){
		console.log(event);
		event.preventDefault();
		
		let title = document.querySelector("#title").value;
		let content = document.querySelector("#content").value;
		
		console.log(title);
		console.log(content);
		
		let updateDto = {
				title: title,
				content: content
		};
		
		let response = await fetch("/post/${postEntity.id}", {
			method:"put",
			body:JSON.stringify(updateDto),
			headers: {
				"Content-Type":"application/json; charset=utf-8"
			}
		});
		let parseResponse = await response.text();
		
		console.log(parseResponse);
		
		if(parseResponse === "ok") {
			location.href = "/post/${postEntity.id}";
		}
	}
	
	$(document).ready(function() {
		$('#content').summernote({
			height : 500, // set editor height
			placeholder : 'Hello Bootstrap 4',
			tabsize : 2,
			minHeight : null, // set minimum height of editor
			maxHeight : null, // set maximum height of editor
			focus : true
		// set focus to editable area after initializing summernote
		});
	});
</script>


<%@ include file="../layout/footer.jsp"%>