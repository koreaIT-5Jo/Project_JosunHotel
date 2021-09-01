<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ADMIN | 그랜드 조선 호텔</title>
<link rel="stylesheet" href="resources/css/default.css">
<link rel="stylesheet" href="resources/css/admin/adminMember.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/admin/adminMember.js"></script>
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
					<a href="logout">로그아웃</a> <!-- 클릭하면 로그아웃되면서, 홈페이지 로그인화면으로 이동 -->
				</li>
			</ul>
		</div>
	</div>
	<!-- End. headerbar -->
	<!-- Start. container -->
	<div class="container">
		<div class="left">
			<ul>
				<li><a href="admin"><i class="fas fa-home"></i> 대시보드</a></li>
				<li class="on"><a href="adminMember"><i class="fas fa-users-cog"></i> 회원관리</a></li>
				<li><a href="adminReservation"><i class="fas fa-calendar-alt"></i> 예약관리</a></li>
				<li><a href="adminQnaList"><i class="fas fa-question-circle"></i> 문의글 관리</a></li>
			</ul>
		</div>
		<div class="right">
			<form name="searchfrm" method="post" action="adminMember">
				<div class="search">
					<div class="selectWrap">
						<select name="searchKey" id="searchType" class="form-control">
							<option value='name'>이름</option>
							<option value='id'>아이디</option>
							<option value='phone'>핸드폰번호</option>
							<option value='email'>이메일주소</option>
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
				<table class="memberList">
					<thead>
						<tr>
							<th style="width:5%;">번호</th>
							<th style="width:10%;">회원이름</th>
							<th style="width:13%;">회원ID</th>
							<th style="width:15%;">핸드폰번호</th>
							<th style="width:15%;">이메일주소</th>
							<th style="width:40%;">주소</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${list}">
							<tr>
								<td>${member.rnum}</td>
								<td>${member.name}</td>
								<td>${member.id}</td>
								<td>${member.phone}</td>
								<td>${member.email}</td>
								<td>${member.address}</td>
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