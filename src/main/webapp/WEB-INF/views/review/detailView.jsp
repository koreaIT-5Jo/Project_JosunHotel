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
<link rel="stylesheet" href="../resources/css/review/detailViewReview.css">
<link rel="stylesheet" href="../resources/css/headerfooter.css">
<link rel="stylesheet" href="../resources/css/default.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/header.js"></script>
<script>
	function boardMain(){
		let form = document.getElementById("detailReviewForm");
		form.action = "main";
		form.submit();
	}
	function reviewDelete(){
		let form = document.getElementById("detailReviewForm");
		form.action = "delete";
		form.submit();
	}
	function reviewModify(){
		let form = document.getElementById("detailReviewForm");
		form.action = "modify";
		form.submit();
	}
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
										<li><a href="qna">Q&amp;A</a></li>
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
							<li><a href="/www/login">로그인</a></li>
							<li><a href="/www/register">회원가입</a></li>
							<%}else if(id.equals("admin")){ %>
							<li><a href="/www/logout">로그아웃</a></li>
							<li><a href="/www/reserveConfirm">마이페이지</a></li>
							<li><a href="/www/adminMember">관리자페이지</a></li>
							<%}else{ %>
							<li><a href="/www/logout">로그아웃</a></li>
							<li><a href="/www/reserveConfirm">마이페이지</a></li>
							<%} %>
						</ul>
					</div>
					<!-- //gnbUtil -->
				</div>
			</div>
			<!-- End. header -->
		<form action="" id = "detailReviewForm" name="detailReviewForm">
		<input type="hidden" id="idx" name="idx" value="${reviewDto.idx }"/>
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
							<li><a href="/www/qna">Q&amp;A</a></li>
							<li class="on"><a href="/www/review/main">REVIEW</a></li>
						</ul>
					</div>
					<!-- //LNB -->
					<!-- detailView -->
					<h3 class="titDep2" style="margin-left: 400px;">REVIEW</h3>
					<div class="detailView">
						<ul class="roomlist">
							<li id="room">
								<dl class="roomIntro">
									<dt class="roomName">
										${roomDto.name } 
									</dt>
									<dd class="roomBenefit">NUMBER |  ${roomDto.getNum()} 호</dd>
									<dd class="roomBenefit">BEDS |  ${roomDto.beds }</dd>
									<dd class="roomBenefit">${roomDto.getDetailView() } |  Size: ${roomDto.r_Size}㎡</dd>
									<dd class="roomBenefit">ROOM FEATURES |  ${roomDto.features}</dd>
									<dd class="thum">
										<img src="../resources/${roomDto.img}" alt="${roomDto.name}">										
									</dd>
								</dl>
							</li>
						</ul>
						<h2 class="tit">${reviewDto.title }</h2> 
						<ul class="infoData">
						<li>그랜드 조선 제주</li>
						<li>${reviewDto.write_Date}</li>
						<li> 조회수 : ${reviewDto.hitCount}</li>
						<li>${reviewDto.member_ID }</li>
					</ul>
					<p class="txtBox">
						${reviewDto.content}
					</p>
					<c:if test="${reviewDto.fileName != null}">
						<div class="imgBox" style="background-image: url(../resources/img/review/${reviewDto.fileName});">
						</div>
						<input type="hidden" id="fileName" name="fileName" value="${reviewDto.fileName}">
				 	</c:if>	
					<div class="btnArea">
						<a href="#none" onclick="boardMain()" class="btnSC btnL">목록</a>
						
						<c:if test="${id == reviewDto.getMember_ID() || id == 'admin' }">
							<a class="btnEdit" onclick="reviewDelete()">삭제</a>
							<a class="btnEdit" onclick="reviewModify()">수정</a>
						</c:if>
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