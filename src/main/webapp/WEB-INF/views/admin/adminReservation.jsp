<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN | 그랜드 조선 호텔</title>
<link rel="stylesheet" href="resources/css/default.css">
<link rel="stylesheet" href="resources/css/admin/adminReservation.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
	<!-- Start. headerbar -->
	<div class="headerbar">
		<div class="maintit">
			<p>administrator</p>
		</div>
		<div class="hbmnu">
			<ul>
				<li>
					<a href="/www">홈페이지</a> <!-- 클릭하면 홈페이지 메인으로 이동 -->
				</li>
				<li>
					<a href="Logout">로그아웃</a> <!-- 클릭하면 로그아웃되면서, 홈페이지 로그인화면으로 이동 -->
				</li>
			</ul>
		</div>
	</div>
	<!-- End. headerbar -->
	<!-- Start. container -->
	<div class="container">
		<div class="left">
			<ul>
				<li><a href="adminMember"><i class="fas fa-users-cog"></i> 회원관리</a></li>
				<li class="on"><a href="adminReservation"><i class="fas fa-calendar-alt"></i> 예약관리</a></li>
				<li><a href="adminQnaList"><i class="fas fa-question-circle"></i> 문의글 관리</a></li>
			</ul>
		</div>
		<div class="right">
			<form name="searchfrm" method="post" action="">
				<div class="search">
					<div class="selectWrap">
						<select name="searchKey" id="searchType" class="form-control">
							<option value='name'>예약자</option>
							<option value='phone_number'>핸드폰번호</option>
						</select>
					</div>
					<div class="searchtxt">
					    <input type="text" name="searchValue" value="" placeholder="검색어를 입력해주세요.">
					</div>
					<div class="button">
					    <button type="submit">검색</button>
					</div>
				</div>
			</form>
			<div class="contents">
				<p>* 예약자명이 "#" 이면 예약취소된 내역입니다.</p>
				<table class="memberList">
					<thead>
						<tr>
							<th rowspan="2">번호</th>
							<th rowspan="2">예약자</th>
							<th rowspan="2">예약룸</th>
							<th rowspan="2" style="width:17%;">예약일자</th>
							<th colspan="2" style="border-bottom:none;">예약인원수</th>
							<th colspan="2" style="border-bottom:none;">조식</th>
							<th rowspan="2" style="width:25%;">문의사항</th>
							<th rowspan="2">결제금액</th>
							<th rowspan="2">핸드폰번호</th>
						</tr>
						<tr>
							<th style="width:6%;">성인</th>
							<th style="width:6%;">어린이</th>
							<th style="width:6%;">성인</th>
							<th style="width:6%; border-right:none;">어린이</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reserve" items="${list}">
							<tr>
								<td>${reserve.rnum}</td>
								<td>${reserve.name}</td>
								<td>${reserve.room_number}</td>
								<td>${fn:substring(reserve.startDate, 0, 10)} ~ ${fn:substring(reserve.endDate, 0, 10)}</td>
								<td>${reserve.adultCnt}</td>
								<td>${reserve.childrenCnt}</td>
								<td>${reserve.adult_breakfast}</td>
								<td>${reserve.chil_breakfast}</td>
								<td><span>${reserve.txtRequest}</span></td>
								<td>${reserve.total_pay}</td>
								<td>${reserve.phoneNum}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page">
					${pageNav}
				</div>
			</div>
		</div>
	</div>
	<!-- End. container -->
</div>
<!-- 전체 감싸는 .wrapper -->
</body>
</html>