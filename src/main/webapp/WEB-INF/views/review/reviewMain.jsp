<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String id = (String) session.getAttribute("id");
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CUSTOMER SERVICE - 리뷰 | 그랜드 조선 호텔</title>
<link rel="stylesheet" href="../resources/css/review/reviewMain.css">
<link rel="stylesheet" href="../resources/css/headerfooter.css">
<link rel="stylesheet" href="../resources/css/default.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/header.js"></script>
</head>
<body>
<script>
	function fncPage(param){
		let form = document.getElementById("reviewForm")
		form.page.value = param;
		form.action="main"
		form.submit();
	}
	function loginGo(){
		let form = document.getElementById("loginForm");
		form.method="get";
		form.action='Login';
		form.submit();
	}
	
	function writeReview(){
		let form = document.getElementById("reviewForm");
		form.action="writeReview"
		form.submit();
	}
	
	function noWrite(){
		alert("로그인 후 사용해주시기 바랍니다.");
	}
	
    function minusFive(){
    	let page = (Number)(document.getElementById("page").value);
    	//alert(page);
		if(page > 1 || page == null || page==""){
			page = page-5
			if(page<1 || page == null || page==""){
				page = 1
			}
		let form = document.getElementById("reviewForm")
		form.page.value = page;
		form.action="main"
		form.submit();
		}
    }
	function minusOne(){
		let page = (Number)(document.getElementById("page").value);
		if(page > 1 || page == null || page==""){
			page = page-1;
			if(page<1 || page == null || page==""){
				page = 1;
			}
		let form = document.getElementById("reviewForm")
		form.page.value = page;
		form.action="main"
		form.submit();
		}
	}
    
	function plusOne(){
		let page = (Number)(document.getElementById("page").value);
		let pagecnt = "<c:out value='${pageVo.getPageCnt()}'/>";
		if(page<pagecnt){
			page = page+1;
			if(page>pagecnt){
				page = pagecnt;
			}
			let form = document.getElementById("reviewForm")
			form.page.value = page;
			form.action="main"
			form.submit();
		}
	}
	
	function plusFive(){
		let page = (Number)(document.getElementById("page").value);
		let pagecnt = "<c:out value='${pageVo.getPageCnt()}'/>";
		if(page<pagecnt){
			page = page+5;
			if(page>pagecnt){
				page = pagecnt;
			}
			let form = document.getElementById("reviewForm")
			form.page.value = page;
			form.action="main"
			form.submit();
		}
	}
    
	$(document).ready(function() {
		
		
       $(".myContents tr").click(function(){
    	  let bno = $(this).children(":first").attr("class");
    	  bno = bno.substr(7);
    	  //alert(bno); 
    	  $("#bno").val(bno);
    	  $("#reviewForm").attr("action", "detailView");
    	  $("#reviewForm").submit();
       });
       
       
       
    });
</script>
<div class="wrapper">
		<div class="header">
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
					<li><a href="#none" onclick="loginGo()">로그인</a></li>
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
	<form name="loginForm" id="loginForm" method="post" action="">
		<input type="hidden" name="url" id="nextURL" value="">
	</form>
	<form id="reviewForm" name="reviewForm" method="post">
	<input type="hidden" id="page" name="currentPage" value="${pageVo.getCurrentPage() }">
	<input type="hidden" id="bno" name="bno" value=""/>
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
						<li class="on"><a href="review/main">REVIEW</a></li>
					</ul>
				</div>
				<!-- //LNB -->
				<!-- myContents -->
				<h3 class="titDep2" style="margin-left: 400px;">REVIEW</h3>
				<div class="myContents">
					<table>
						<tbody>
						<!-- 반복문 -->
							<c:forEach var="dto" items="${list}">
								<tr>
									<td class="writing${dto.idx}"><div style="background-image:url(../resources/img/review/${dto.file_Name});"></div></td>
									<td>
										<p>
											제목&nbsp;&nbsp;&nbsp; | ${dto.title}  <br>
											내용&nbsp;&nbsp;&nbsp; | ${dto.content} <br>
											작성일 | ${dto.write_Date}<br>
											작성자 | ${dto.member_ID} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 조회수 | ${dto.hitCount} 
										</p>
									</td>
								</tr>
							</c:forEach>				
				
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<a class="first" onclick="minusFive()"><span class="hidden">first</span></a>
					<a class="prev" onclick="minusOne()"><span class="hidden">prev</span></a>
					<c:forEach var="i" begin="${pageVo.startPage}" end="${pageVo.endPage}" step="1">
						<c:choose>
							<c:when test = "${i == pageVo.currentPage }">
								<span class="current"><span class="hidden">현재페이지</span>${i}</span>
							</c:when>
							<c:otherwise>
								<a href="javascript:fncPage(${i});">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<a class="next" onclick="plusOne()"><span class="hidden">next</span></a> 
					<a class="last" onclick="plusFive()"><span class="hidden">last</span></a> 
					<%if(id==null) { %>
					<a id="write" onclick="noWrite()" >글쓰기</a>	
					<%	} else{
					%>
					<a id="write" onclick="writeReview()" >글쓰기</a>
					<%} %>
				</div>
				<!-- //myContents -->
			</div>
			<!-- //inner -->
			<!-- 컨텐츠 E -->
		</div>
		<!-- //container -->
	</form>
		<!-- //container -->
	<div style="background:#000;"><!-- Start. footer -->
		<div id="footer">
			<div class="foot-logo">
				<img src="../resources/img/01.main/bg_logo_footer.png" alt="그랜드 조선 제주">
			</div>
			<div class="foot-txt">
				서울시 중구 소공로 106 대표이사 한채양 T. 02-771-0500<br>
				사업자등록번호 104-81-27386 통신판매신고번호 중구 0623호<br>
				2020 JOSUN HOTELS &amp; RESORTS Co. All rights reserved.
			</div>
		</div>
	</div>
	<!-- End. footer -->
</div>
</body>
</html>