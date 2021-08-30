<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String) session.getAttribute("id"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CUSTOMER SERVICE - 리뷰 | 그랜드 조선 호텔</title>
<link rel="stylesheet" href="../resources/css/review/writeReview.css">
<link rel="stylesheet" href="../resources/css/default.css">
<link rel="stylesheet" href="../resources/css/headerfooter.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/header.js"></script>
<script>
	
	function cancle(){
		location.href="main";
	}

	function registReview(){
		let form = document.getElementById("review_WriteForm");
		
		if($("#reservedRoom").val().length==0 || $("#reservedRoom").val()==null ){
			alert("예약했던 객실을 선택해주시기 바랍니다.");
			return false;
		}
		if($("#title").val().length==0 || $("#title").val() == null	){
			alert("제목을 필수 항목입니다.");
			$("#title").focus();
			return false;
		}
		if($("#dsCont").val().length==0 || $("#dsCont").val() == null	){
			alert("내용을 작성 해주시기 바랍니다.");
			$("#dsCont").focus();
			return false;
		}
		form.action = "reviewWriteAction";
		form.submit();
	}
	
	$(document).ready(function(){
		$(".roomlist li").click(function(){
			//alert("!");
			$(".roomlist li").children().css({"border" : ""})
			$(this).children().css({"border" : "5px solid black"})
			let a = $(this).attr("id");
			a = a.substr(4)
			$("#reservedRoom").val(a);
		});
	});
</script>
</head>
<body>
	<div class="wrapper ">
		<div class="header">
				<!-- 메뉴 열리면 gnbOn 클래스 추가 -->
				<div class="headArea">
					<strong class="logo"><a href="/www">JOSUN HOTELS &amp;	RESORTS</a></strong>
					<button type="button" class="btnMenu">메뉴 열기</button>
					<div class="allMenu">
						<!-- 화면 높이값 계산 height:적용, body:overflow:hidden -->
						<div class="inner">
							<ul class="menuDepth01">
								<li>BRAND STORY
									<ul class="menuDepth02">
										<li><a href="brand">그랜드 조선 제주</a></li>
									</ul>
								</li>
								<li>EVENT & NOTICE
									<ul class="menuDepth02">
										<li><a href="enList">EVENT & NOTICE</a></li>
									</ul>
								</li>
								<li>RESERVATION
									<ul class="menuDepth02">
										<li><a href="reserveConfirm">예약확인</a></li>
									</ul>
								</li>
								<li>CUSTOMER SERVICE
									<ul class="menuDepth02">
										<li><a href="qna">Q&amp;A</a></li>
										<li><a href="review/main">REVIEW</a></li>
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
		<form id="review_WriteForm" name="review_WriteForm" method="post" action="" enctype="multipart/form-data">
		<input type="hidden" id="reservedRoom" name="room_Number" value="" />
		<div id="container" class="container mypage ctmService">
				<!-- 컨텐츠 S -->
				<h1 class="hidden">고객센터</h1>
				<div class="topArea">
					<div class="topInner">
						<h2 class="titDep1">Customer Service</h2>
						<p class="pageGuide">
							 조선호텔앤리조트는 언제나 고객의 목소리에 귀기울이고 있습니다.<br>
							 소중한 충고와 격려, 또는 제안의 말씀을 남겨 주시면 최대한 반영하여 더 나은 서비스로 보답하겠습니다.
						</p>
					</div>
				</div>
				<div class="inner">
					<!-- LNB -->
					<div class="lnbArea">
						<ul class="lnb ctmType">
							<li><a href="/ProjectWepJosun/qna.jsp">Q&amp;A</a></li>
							<li class="on"><a href="reviewboard?command=reviewmain">REVIEW</a></li>
						</ul>
					</div>
					<!-- //LNB -->
					<!-- writeReview -->
					<h3 class="titDep2" style="margin-left: 400px;">REVIEW</h3>
					<div class="writeReView">
						<ul class="roomlist">
							<!-- 반복문 -->
							<c:forEach var="dto" items="${list }">
								<li id="room${dto.num}" >
									<dl class="roomIntro">
										<dt class="roomName">
											${dto.name}
										</dt>
										<dd class="roomBenefit">NUMBER |  ${dto.num}호</dd>
										<dd class="roomBenefit">BEDS |  ${dto.beds}</dd>
										<dd class="roomBenefit">${dto.getDetailView()} |  Size: ${dto.r_Size}㎡</dd>
										<dd class="roomBenefit">ROOM FEATURES |  ${dto.features}</dd>
										<dd class="thum">
											<img src="../resourcese/${dto.img}">										
										</dd>
									</dl>
								</li>
							</c:forEach>
						</ul>
						<ul class="intList">
							<li>
								<div class="intWrap">
									<span class="tit"> 
										<label for="title">TITLE</label> <span class="essential">*</span>
									</span>
								</div>
								<div class="intInner">
									<span class="intArea"> 
										<input type="text" id="title" name="title" style="width: 100%" aria-required="true" placeholder="제목을 입력해주세요." > 
									</span>
								</div>
							</li>
							<li>
								<div class="intWrap">
									<span class="tit"><label for="content">CONTENT</label><span class="essential">*</span></span>
								</div>
								<div class="intInner">
									<span class="intArea">
										<textarea id="dsCont" name="content" class="noLine" style="min-height:300px; width:100%;" placeholder="내용을 입력해주세요."></textarea></span>
								</div>
							</li>
							<li>
								<div class="intWrap">
									<span class="tit"><label for="uploadBtn">ATTACHED FILE</label></span>
								</div>
								<div class="intInner">
									<!-- 스크립트 위임 적용 commonJs.setFileUpload('.fileUpload'); -->
									<div class="fileUpload">
										<div class="intDel">
											<input type="text" id="nmFile1" class="fileName" name="file_Name" style="width: 554px" readonly="">
											<!-- btnDel -->
											<button type="button" class="btnDel">삭제</button>
											<!-- //btnDel -->
										</div>
										<label for="uploadFile" class="btnSC btnM">파일선택</label> 
										<input type="file" id="uploadFile" name="uploadFile" class="uploadBtn" accept=".jpg, .jpeg, .png" >
									</div>
								</div>
								<p class="txtGuide">* 첨부가능 파일종류 : jpg, png, jpeg (용량 5MB)</p>
							</li>
						</ul>
					<div class="btnArea">
								<a href="#none" onclick="cancle()" class="btnSC btnL cancel">취소</a> 
								<a href="#none" onclick="registReview()" class="btnSC btnL active">저장</a>
								<div style="clear:both;"></div> 
							</div>
					</div>
					<!-- //myContents -->
				</div>
				<!-- //inner -->
			</div>
			<!-- //container -->
		</form>
		<!-- footer -->
		<div style="background:#000;"><!-- Start. footer -->
			<div id="footer">
				<div class="foot-logo"><img src="../resources/img/01.main/bg_logo_footer.png" alt="그랜드 조선 제주"></div>
				<div class="foot-txt">
					서울시 중구 소공로 106 대표이사 한채양 T. 02-771-0500<br>
					사업자등록번호 104-81-27386 통신판매신고번호 중구 0623호<br>
					2020 JOSUN HOTELS &amp; RESORTS Co. All rights reserved.
				</div>
			</div>
		</div><!-- End. footer -->
	</div>
</body>
</html>