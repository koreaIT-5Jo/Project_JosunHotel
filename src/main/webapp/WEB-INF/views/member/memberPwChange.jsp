<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.PrintWriter" %>
<% String id = (String)session.getAttribute("id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>비밀번호 변경 | 그랜드 조선 호텔</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/member/memberPwChange.js"></script>
<script type="text/javascript" src="resources/js/header.js"></script>
<link rel="stylesheet" href="resources/css/member/memberPwChange.css">
<link rel="stylesheet" href="resources/css/headerfooter.css">
<link rel="stylesheet" href="resources/css/default.css">
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
					<%if(id.equals("admin")){ %>
					<li><a href="logout">로그아웃</a></li>
					<li><a href="reserveConfirm">마이페이지</a></li>
					<li><a href="admin">관리자페이지</a></li>
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
	<!-- Start. container -->
	<div id="container" class="container join">
		<div class="topArea">
			<div class="topInner">
				<h2 class="titDep1">My Page</h2>
				<p class="pageGuide">
					멤버십 회원을 위한 다양한 혜택이 준비되어 있습니다.
				</p>
			</div>
		</div>
		<div class="inner">
			<div class="lnbArea">
				<div class="myInfo">
					<p class="name">
						<a href="#"><em id="nm1">${name}</em>님</a>
					</p>
				</div>
				<ul class="lnb">
					<li>예약확인
						<ul>
							<li><a href="/www/reserveConfirm">객실 · 예약 내역</a></li>
						</ul>
					</li>
					<li>개인정보관리
						<ul>
							<li><a href="/www/memberModify">회원 정보 수정</a></li>
							<li><a href="/www/memberPwChange">비밀번호 변경</a></li>
							<li><a href="/www/memberDelete">회원 탈퇴</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- myContents -->
			<div class="myContents">
				<h3 class="titDep2">비밀 번호 변경</h3>
				<p class="pageGuide tleft">현재 비밀번호를 올바르게 입력해야만 비밀번호 변경이 가능합니다.</p>
				<div class="frmInfo">
					<ul class="intList">
						<li>    
							<div class="intWrap">
								<span class="tit"><label for="userpw">CURRENT PASSWORD</label><span class="essential">필수</span></span>
							</div>
							<div class="intInner">
								<span class="intArea">
									<input type="password" id="curPw" name="curPw" placeholder="현재 비밀번호를 입력해주세요." style="width: 487px" aria-required="true" required>
									<span class="alertMessage">비밀번호를 입력해주세요.</span>
								</span>
							</div>
						</li>
						<li>
							<div class="intWrap">
								<span class="tit">
									<label for="userpw">NEW PASSWORD</label>
									<span class="essential">필수</span>
								</span>
							</div>
							<div class="intInner">
								<span class="intArea">
									<input type="password" id="newPw" name="newPw" placeholder="영문, 숫자, 특수문자 조합 8~12자리를 입력해주세요." style="width: 487px" aria-required="true" required>
										<span class="alertMessage"> <!-- 비밀번호를 입력해주세요. -->비밀번호를 입력해주세요.</span>
								</span>
							</div>
						</li>
						<li class="intList-repwd">
							<div class="intWrap">
								<span class="tit">
									<label for="userpw-re">CONFIRM PASSWORD</label>
									<span class="essential">필수</span>
								</span>
							</div>
							<div class="intInner">
								<span class="intArea">
									<input type="password" id="newPwRe" name="newPwRe" placeholder="동일한 비밀번호를 입력해주세요." style="width: 487px" aria-required="true" required>
									<span class="alertMessage">동일한 비밀번호를 입력해주세요.</span>
								</span>
							</div>
						</li>
					</ul>
				</div>
				<div class="btnArea">
					<button type="button" class="btnSC btnL active" onclick="fnChngPwApi()">확인</button>
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
					서울시 중구 소공로 106 대표이사 한채양 T. 02-771-0500<br> 사업자등록번호 104-81-27386
					통신판매신고번호 중구 0623호<br> 2020 JOSUN HOTELS &amp; RESORTS Co. All
					rights reserved.
				</div>
			</div>
		</div>
		<!-- End. footer -->
	</div>
	<!-- End. container -->
</body>
</html>