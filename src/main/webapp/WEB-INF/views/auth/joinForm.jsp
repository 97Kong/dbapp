<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/join" method="post">
		<div class="form-group">
			<label for="text">Username:</label> 
			<input type="text" class="form-control" placeholder="Enter username" name="username"/>
		</div>

		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" placeholder="Enter password" name="password"/>
		</div>

		<div class="form-group">
			<label for="email">Email:</label> 
			<input type="email" class="form-control" placeholder="Enter email" name="email"/>
		</div>


		<input class="btn btn-info" type="button" onClick="goPopup();" value="주소찾기" />
		<div class="form-group">
			<label for="address">address:</label> 
			<input type="text" class="form-control" placeholder="Enter address" name="address" id="address" readonly="readonly"/>
		</div>

		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>

<script>
	// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
	//document.domain = "abc.go.kr";

	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.

		var pop = window.open("/juso", "pop","width=570,height=420, scrollbars=yes, resizable=yes");
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(roadFullAddr) {
		let addressEL =  document.querySelector("#address");
		addressEL.value = roadFullAddr;
		console.log(addressEL);
	}
</script>

<%@ include file="../layout/footer.jsp"%>
