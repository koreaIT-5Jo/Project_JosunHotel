<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="com.josun.dto.BoardEventNoticeDTO" %>
<% String id = (String)session.getAttribute("id");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EVENT &amp; NOTICE 글목록 | 그랜드 조선 호텔</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/board/board_EventNoticeList.js"></script>
<script type="text/javascript" src="resources/js/header.js"></script>
<link rel="stylesheet" href="resources/css/default.css">
<link rel="stylesheet" href="resources/css/headerfooter.css">
<link rel="stylesheet" href="resources/css/board/board_EventNoticeList.css">
<script>
	//글쓰기
	function enWrite() {
		location.href = '/www/enWrite';
	}
</script>
</head>
<body>
	<div class="wrapper">
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
		 
		<!-- Start. container -->
		<div id="container" class="container">
			<div class="topArea">
				<div class="topInner">
					<a href="enList"><h2 class="titDep1">EVENT & NOTICE</h2></a>
					<p class="pageGuide">조선호텔앤리조트 멤버를 위한 다양한 소식을 만나보세요.</p>
				</div>
			</div>
			<div class="searchBox package">
				<div class="inner">
					<div class="searchOp">
						<form action="enList">
							<div class="selectWrap" style="width: 296px" first="true">
								<select name="category" id="searchCtgry" class="form-control">
									<option data-display="전체" value="0">전체</option>
									<option value="1">EVENT</option>
									<option value="2">NOTICE</option>
								</select>
							</div>
							<div class="intWord">
								<span class="intArea"> 
									<input type="text" style="width: 873px" id="searchDataValue" name="keyword" title="검색어 입력" placeholder="검색어를 입력해주세요." value="">
								</span>
								<button type="submit" class="btnSC btnM">검색</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="inner">
				<table class="tblH">
					<colgroup>
						<col style="width: 16%">
						<col style="width: 50%">
						<col style="width: 17%">
						<col style="width: auto">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">카테고리</th>
							<th scope="col">제목</th>
							<th scope="col">조회수</th>
							<th scope="col">등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="eventNotice" items="${enList}">
							<tr>
								<td>
									<c:out value="${eventNotice.cateName}" />
								</td>
								<td id = "td" class="tleft">
									<a href="enDetailView?idx=${eventNotice.idx}"><c:out value="${eventNotice.title}" /></a>
								</td>
								<td>
									<c:out value="${eventNotice.hitCount}" />
								</td>
								<td class="date">
									<fmt:parseDate var='writeDay' value='${eventNotice.write_date}' pattern='yyyy-MM-dd HH:mm:ss'/>
									<fmt:formatDate value="${writeDay}" pattern="yy.mm.dd"/>
								</td>
							</tr>					
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination">
					${pageNav}
				</div> 
	 			<div class="btnArea">
					<%if(id == null || id == ""){%>
						<div></div>
					<%}else if(id.equals("admin")){ %>
						<a class="btnSC writeL" onclick="enWrite();">글쓰기</a>
					<%}else{ %>
						<div></div>
					<%} %>
				</div>
			</div>
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
	</div>
</body>
</html>