<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% String id = (String)session.getAttribute("id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원정보수정 | 그랜드 조선 호텔</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="resources/js/member/memberModify.js"></script>
<script type="text/javascript" src="resources/js/header.js"></script>
<link rel="stylesheet" href="resources/css/member/memberModify.css">
<link rel="stylesheet" href="resources/css/headerfooter.css">
<link rel="stylesheet" href="resources/css/default.css">
<script>
function postcodeT(){
	new daum.Postcode({
	    oncomplete: function(data) {
	        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분
	        // http://postcode.map.daum.net/guide 에서 예제를 활용하여 커스텀
	
			$('input[name=postcode]').val(data.zonecode);      // 우편번호(5자리)
			$('input[name=address]').val(data.address);       // 기본주소
			//$('input[name=addr2]').val(data.buildingName);  // 건물명
	    }
	}).open();
}

function fncMyInfoUpdApi() {
	var phone1 = $("#telFrstNo").val();
	var phone2 = $("#telMidNo").val();
	var phone3 = $("#telIndNo").val();
	
	var postcode = $("#postcode").val();
	var addr = $("#address").val();
	var detailAdd = $("#detailAddress").val();
	
	var emailId = $("#emailId").val();
	var eDomain = $("#eDomain").val();
	
	var name = $("#korNm").val();							//이름
	var phone = phone1 + "-" + phone2 + "-" + phone3;		//폰번호
	var address = postcode + "//" + addr + "//" + detailAdd;//전체 주소
	var email = emailId + "@" + eDomain;					//전체 이메일	

	var modifyData = {
		name : name,
		phone : phone,
		address : address,
		email : email
	}
	
	/*
	사용자 입력정보 VALIDATION 체크
	해당 열  input, select 박스가 하나라도 미기재 된 경우 validation false
	최초 미입력 된 element로 focus 이동됨
	 */
 	var frstIdx = "";
	
	jQuery(".intList li").each(function(){
		var $this = jQuery(this);
		var validYn = true;
		$this.find("input[type='text']").each(function(idx){
			var value = jQuery(this).val();
			var id = jQuery(this).attr("id");
			if(value == "" && id != "emailType"){
				validYn = false;
				if(frstIdx == ""){
					frstIdx = jQuery(this);
				}
			}
		});
	
		if(!validYn){
			$this.addClass("error");
			$this.find(".alertMessage").show();
		}else{
			$this.removeClass("error");
			$this.find(".alertMessage").hide();
		}
	});
	
	//전체 값들이 들어갈때까지
	if(frstIdx != ""){
		frstIdx.focus();
		return false;
	}
	
	$.ajax({
		url:'/www/myPage/memberModifyAction/json',
		type:'post',
		data: JSON.stringify(modifyData),
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			alert(response.msg);
			location.href = '/www/memberModify';
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
					<%if(id.equals("admin")){ %>
					<li><a href="/www/Logout">로그아웃</a></li>
					<li><a href="/www/reserveConfirm">마이페이지</a></li>
					<li><a href="/www/adminMember">관리자페이지</a></li>
					<%}else{ %>
					<li><a href="/www/Logout">로그아웃</a></li>
					<li><a href="/www/reserveConfirm">마이페이지</a></li>
					<%} %>
				</ul>
			</div>
			<!-- //gnbUtil -->
		</div>
	</div>
	<!-- End. header -->
	
s	<!-- Start. contents -->
	<div id="container" class="container mypage">
		<div class="topArea">
			<div class="topInner">
				<h2 class="titDep1">My Page</h2>
				<p class="pageGuide">멤버십 회원을 위한 다양한 혜택이 준비되어 있습니다.</p>
			</div>
		</div>
		<div class="inner">
			<div class="lnbArea">
				<div class="myInfo">
					<p class="name">
						<a href="#"><em id="nm1">${name}</em> <!-- 님 -->님</a>
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
				<div class="myContents">
					<h3 class="titDep2">회원 정보 수정</h3>
					<div class="frmInfo">
						<ul class="intList">
							<li>
								<div class="intWrap">
									<span class="tit"><label for="kName">NAME</label> <span class="essential">*</span>
									</span>
								</div>
								<div class="intInner">
									<span class="intArea"> 
										<input type="text" id="korNm" name="korNm" placeholder="국문 이름을 입력하세요." style="width: 550px" aria-required="true" value="${name}">
										<span class="alertMessage">이름을 입력해주세요.</span>
									</span>
								</div>
							</li>
							<li>
								<div class="intWrap">
									<span class="tit">
										<label for="phone">PHONE NUMBER</label>
									<span class="essential">*</span></span>
								</div>
								<div class="intInner phoneInp">
									<span class="intArea">
										<input type="text" id="telFrstNo" name="telFrstNo" title="first phone number" style="width: 165px" aria-required="true" value="${phone1}">
									</span>
									<span class="dash"></span>
									<span class="intArea">
										<input type="text" id="telMidNo" name="telMidNo" title="second phone number" style="width: 165px" aria-required="true" value="${phone2}">
									</span>
									<span class="dash"></span>
									<span class="intArea">
										<input type="text" id="telIndNo" name="telIndNo" title="last phone number" style="width: 165px" aria-required="true" value="${phone3}">
									</span>
									<span class="alertMessage">휴대폰 번호를 입력해주세요.</span>
								</div>
							</li>
							<li class="intList-address">
								<div class="intWrap">
									<span class="tit">
										<label for="address">Address</label>
									<span class="essential">*</span></span>
								</div>
								<div class="intInner">
									<span class="intArea">
										<input type="text" id="postcode" name="postcode" style="width: 305px" aria-required="true" readonly value="${addr1}">
									</span>
									<button type="button" class="btnSC btnM" onclick="postcodeT();">우편번호 검색</button>
								</div>
								<div class="intInner duobuleInp">
									<span class="intArea">
										<input type="text" id="address" name="address" style="width: 490px" title="주소" aria-required="true" readonly value="${addr2}"></span>
									<span class="intArea">
										<input type="text" id="detailAddress" name="detailAddress" style="width: 490px" title="상세주소" aria-required="true" placeholder="상세주소를 입력해주세요." value="${addr3}">
									</span>
									<span class="alertMessage">상세주소를 입력해주세요.</span>
								</div>
							</li>
							<li>
								<div class="intWrap">
									<span class="tit"><label for="eMail">E-MAIL</label>
									<span class="essential">*</span></span>
								</div>
								<div class="intInner emailInp">
									<span class="intArea">
										<input type="text" id="emailId" name="emailId" style="width: 244px" aria-required="true" value="${email1}"></span>
									<span class="dash">@</span>
									<span class="intArea">
										<input type="text" id="eDomain" name="eDomain" style="width: 244px" aria-required="true" title="이메일주소직접입력" value="${email2}"></span>
									<div class="intArea selectWrap" style="width: 180px" first="true">
										<select class="my_select" id="emailType" name="emailType">
											<option value="직접 입력">직접입력</option>
											<option value="naver.com">naver.com</option>
											<option value="hanmail.net">hanmail.net</option>
											<option value="hotmail.com">hotmail.com</option>
											<option value="nate.com">nate.com</option>
											<option value="gmail.com">gmail.com</option>
										</select>
									</div>
									<span class="alertMessage">이메일주소를 입력해주세요.</span>
								</div>
							</li>
						</ul>
					</div>
					<div class="btnArea">
						<button type="button" id="btnActive" class="btnSC btnL active" onclick="fncMyInfoUpdApi();">저장</button>
					</div>
				</div>
			<!-- End. my contents -->
		</div>
		<!-- End. inner -->
	</div>
	
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
</body>
</html>