<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<% String id = (String) session.getAttribute("id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EVENT &amp; NOTICE 상세보기 | 그랜드 조선 호텔</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/header.js"></script>
<link rel="stylesheet" href="resources/css/default.css">
<link rel="stylesheet" href="resources/css/headerfooter.css">
<link rel="stylesheet" href="resources/css/board/board_EventNoticeView.css">
<script>
	//목록가기
	function fncGoList() {
		location.href='/www/enList';
	}
	
	//수정하기
	function writeModify(ci) {
		var curIdx = ci;
		location.href='/www/enModify?idx='+curIdx;
	}
	
	//삭제하기
	function writeDel(ci) {
		var curIdx = ci;
		var idx = { idx : curIdx };
		
		$.ajax({
			url:'/www/eventNotice/delPostAction/json',
			type:'post',
			data: JSON.stringify(idx),
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
					alert(response.msg);
					location.href = '/www/enList';
			},
			error:function(request, status, error) { 
				alert("code: " + request.status + "\n" + "massage: " + request.responseText + "\n" + "error: " + error); 
			}
		});
	}
	
	//이전 글
	function movePrevPost(pi) {
		var idx = pi;
		if(idx == 0) {
			alert('이전 글이 없습니다. 목록으로 이동합니다.');
			location.href='/www/enList';
		} else {
			location.href='/www/enDetailView?idx='+idx;
		}
	}
	
	//다음 글
	function moveNextPost(ni) {
		var idx = ni;
		if(idx == 0) {
			alert('다음 글이 없습니다. 목록으로 이동합니다.');
			location.href='/www/enList';
		} else {
			location.href='/www/enDetailView?idx='+idx;
		}
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
	<!-- Start. contents -->
	<div id="container" class="container">
		<div class="inner">
			<div class="mbsDetailCont">
				<div class="detailView">
						<h2 class="tit">${title}</h2>
						<ul class="infoData">
							<li>조선호텔앤리조트</li>
							<li>${writeDate}</li>
						</ul>
						<p class="txtBox">${content}</p>
					<ul class="shortList">
						<li class="prev">
							<span>이전 글</span>
							<a class="ellipsis" href="#none" onclick="movePrevPost(${prevIdx});">${prevTitle}</a>
						</li>
						<li class="next">
							<span>다음 글</span>
							<a class="ellipsis" href="#none" onclick="moveNextPost(${nextIdx});">${nextTitle}</a>
						</li>
					</ul>
					<div class="btnArea">
						<a href="#none" onclick="fncGoList();" class="btnSC btnL">목록</a> 
						<%if(id == null) {%>
							<div></div>
						<%} else if(id.equals("admin")) {%>
							<a href="#none" onclick="writeModify(${curIdx});" class="btnSC writeL">수정</a> 
							<a href="#none" onclick="writeDel(${curIdx});" class="btnSC writeD">삭제</a>
						<%} %>
					</div>
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
</body>
</html>