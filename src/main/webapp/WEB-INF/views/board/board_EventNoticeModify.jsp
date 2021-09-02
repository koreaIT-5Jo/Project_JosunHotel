<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String id = (String) session.getAttribute("id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EVENT &amp; NOTICE 글수정 | 그랜드 조선 호텔</title>
<script type="text/javascript" src="resources/smarteditor2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/board/board_EventNoticeWrite.js"></script>
<script type="text/javascript" src="resources/js/header.js"></script>
<link rel="stylesheet" href="resources/css/board/board_EventNoticeWrite.css">
<link rel="stylesheet" href="resources/css/headerfooter.css">
<link rel="stylesheet" href="resources/css/default.css">
<script>
	//취소하기
	function writeCancle(idx) {
		var idx = idx;
		location.href='/www/enDetailView?idx='+idx;
	}
	
	//글수정
	function writeSave(curPage) {
		oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);
		
		var idx = (Number)($("#idx").val());
		
		var cate = $('input[name=cateselect]:checked').val();
		
		if(cate == 1) { cate = 1; } 
		else if(cate == 2) { cate = 2; }
		
		var tit = $("#title").val();
		
		var con = document.getElementById("txtContent").value;
		
		var fileN = $("#nmFile1").val();
		if(''!=$("#nmFile1").val()) { fileN = $("#nmFile1").val(); } 
		else { fileN = ''; } 
		
		var writePostData = { idx : idx, cate : cate, tit : tit, con : con, fileN : fileN }
		
		$.ajax({
			type:'post',
			url:'/www/eventNotice/modifyPostAction',
			data:JSON.stringify(writePostData),
			datatype: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				alert(response.msg);
				location.href = '/www/enDetailView?idx='+idx;
			},
			error:function(request, status, error) { 
				alert("code: " + request.status + "\n" + "massage: " + request.responseText + "\n" + "error: " + error);  
			}
		});
	}
</script>
</head>
<body>
	<div class="header">
		<div class="headArea">
			<strong class="logo"><a href="/www">JOSUN HOTELS &amp; RESORTS</a></strong>
			<button type="button" class="btnMenu">메뉴 열기</button>
			<div class="allMenu">
				<!-- 화면 높이값 계산 height:적용, body:overflow:hidden -->
				<div class="inner">
					<ul class="menuDepth01">
						<li>BRAND STORY
							<ul class="menuDepth02">
								<li><a href="/www/brand">그랜드 조선 제주</a></li>
							</ul>
						</li>
						<li>EVENT & NOTICE
							<ul class="menuDepth02">
								<li><a href="/www/enList">EVENT & NOTICE</a></li>
							</ul>
						</li>
						<li>RESERVATION
							<ul class="menuDepth02">
								<li><a href="/www/reserveConfirm">예약확인</a></li>
							</ul>
						</li>
						<li>CUSTOMER SERVICE
							<ul class="menuDepth02">
								<li><a href="/www/qna">Q&amp;A</a></li>
								<li><a href="/www/review/main">REVIEW</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<!-- //allMenu -->
			<div class="gnbUtil">
				<ul>
					<%if(id == null || id == ""){%>
					<li><a href="login">로그인</a></li>
					<li><a href="register">회원가입</a></li>
					<%}else if(id.equals("admin")){ %>
					<li><a href="logout">로그아웃</a></li>
					<li><a href="reserveConfirm">마이페이지</a></li>
					<li><a href="adminMember">관리자페이지</a></li>
					<%}else{ %>
					<li><a href="logout">로그아웃</a></li>
					<li><a href="reserveConfirm">마이페이지</a></li>
					<%} %>
				</ul>
			</div>
			<!-- //gnbUtil -->
		</div>
	</div>
	<!-- End. header -->
	<div id="container" class="container">
		<!-- Start. contents -->
		<div class="topArea">
			<div class="topInner">
				<h2 class="titDep">News</h2>
				<p class="pageGuide">조선호텔앤리조트 멤버를 위한 다양한 소식을 만나보세요.</p>
			</div>	
		</div>
			<div class="inner">
				<div class="contents">
					<ul class="intList">
						<li class="intList-address">
							<div class="intBox">
								<div class="intWrap">
									<span class="idx"> 
										<label for="idx">WRITE NUMBER</label> <span class="essential">*</span>
									</span>
									<span class="intInner">
										<span class="intArea"> 
											<input type="text" id="idx" name="idx" style="width: 10%; background-color: white; text-align: center;" aria-required="true" value="${idx}" disabled>
										</span>
									</span>
								</div>
							</div>
						</li>
						<li>
							<div class="intBox">
								<div class="intWrap">
									<span class="tit"><label for="category">CATEGORY</label><span class="essential">*</span></span>
								</div>
								<div class="intBox">
										<%	if(request.getAttribute("cate1") != null) {%>
										<span class="frm">
											<input type="radio" id="a1" name="cateselect" value="1" checked>
											<label for="a1">EVENT</label>
										</span>
										<span class="frm">
											<input type="radio" id="a2" name="cateselect" value="2">
											<label for="a2">NOTICE</label>
										</span> 
										<%	} else if(request.getAttribute("cate2") != null) {%>
										<span class="frm">
											<input type="radio" id="a1" name="cateselect" value="1">
											<label for="a1">EVENT</label>
										</span>
										<span class="frm">
											<input type="radio" id="a2" name="cateselect" value="2" checked>
											<label for="a2">NOTICE</label>
										</span> 
										<%	} %>
								</div>
							</div>
						</li>
						<li>
							<div class="intBox">
								<div class="intWrap">
									<span class="tit"> 
										<label for="title">TITLE</label> <span class="essential">*</span>
									</span>
								</div>
								<div class="intInner">
									<span class="intArea"> 
										<input type="text" id="title" name="title" style="width: 100%" aria-required="true" placeholder="제목을 입력해주세요." value="${tit}">
									</span>
								</div>
							</div>
						</li>
						<li>
							<div class="intBox">
								<div class="intWrap">
									<span class="tit"><label for="content">CONTENT</label><span class="essential">*</span></span>
								</div>
								<div class="intInner">
									<span class="intArea">
										<textarea id="txtContent" rows="10" cols="100" name="content" class="noLine" style="min-height:300px; width:100%;" placeholder="내용을 입력해주세요." value=${smarteditorVO.bo_content }>
										${con}
										</textarea>
									</span>
									<script id="smartEditor" type="text/javascript">
										var oEditors = [];
	
										nhn.husky.EZCreator.createInIFrame({
										    oAppRef: oEditors,
										    elPlaceHolder: "txtContent",  									//textarea ID 입력
										    sSkinURI: "resources/smarteditor2/SmartEditor2Skin.html",		//martEditor2Skin.html 경로 입력
										    fCreator: "createSEditor2",
										    htParams : { 
										    	// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
										        bUseToolbar : true, 
												// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
												bUseVerticalResizer : false, 
												// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
												bUseModeChanger : false 
										    }
										});
									</script>
								</div>
							</div>
						</li>
						<li>
							<div class="intBox">
								<div class="intWrap">
									<span class="tit"><label for="uploadBtn">ATTACHED FILE</label></span>
								</div>
								<div class="intInner">
									<!-- 스크립트 위임 적용 commonJs.setFileUpload('.fileUpload'); -->
									<div class="fileUpload">
										<div class="intDel">
											<input type="text" id="nmFile1" class="fileName" name="fileName" style="width: 554px" value="${fileName}">
											<!-- btnDel -->
											<button type="button" class="btnDel">삭제</button>
											<!-- //btnDel -->
										</div>
										<label for="uploadFile" class="btnSC btnM">파일선택</label> 
										<input type="file" id="uploadFile" name="uploadFile" class="uploadBtn" accept=".jpg, .jpeg, .png" onchange="checkSize(this)">
									</div>
								</div>
								<p class="txtGuide">* 첨부가능 파일종류 : jpg, png, jpeg (용량 5MB)</p>
							</div>
						</li>
					</ul>
					<div>
						<div class="btnArea">
							<a class="btnL btnSC cancel" href="#none" onclick="writeCancle(${idx});">취소</a> 
							<a class="btnL btnSC active" href="#none" onclick="writeSave(${idx})">저장</a>
							<div style="clear:both;"></div> 
						</div>
					</div>
				</div>
			</div>
		<!-- End. contents -->
	
		<!-- Start. footer -->
		<div style="background: #000;">
			<div id="footer">
				<div class="foot-logo">
					<img src="resources/img/01.main/bg_logo_footer.png" alt="그랜드 조선 제주">
				</div>
				<div class="foot-txt">
					서울시 중구 소공로 106 대표이사 한채양 T. 02-771-0500<br>
					사업자등록번호 104-81-27386 통신판매신고번호 중구 0623호<br> 
					020 JOSUN HOTELS &amp; RESORTS Co. All rights reserved.
				</div>
			</div>
		</div>
		<!-- End. footer -->
	</div>
</body>
</html>

<!-- input type text placeholder css :글상자 안에 힌트 명시하기 -->
<!-- textarea : 내용 -->
<!-- input 설명 : https://developer.mozilla.org/ko/docs/Web/HTML/Element/Input -->