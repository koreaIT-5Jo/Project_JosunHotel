<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@page import="board.qna.BoardDAO" %>
<%@page import="board.qna.BoardDTO" %>
<%@page import="board.qna.BoardCommentDAO" %>
<%@page import="board.qna.BoardCommentDTO" %>
<%@page import="conn.DBConn" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.util.List" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	String id = (String) session.getAttribute("idKey");
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	//검색키와 키값
	String searchKey = request.getParameter("searchKey");
	String searchValue = request.getParameter("searchValue");
	
	if(searchValue==null){
		searchKey = "content";
		searchValue = "";
	}
	
	//첨부파일 경로
	String path = request.getContextPath()+"/upload";
	
	Connection conn = DBConn.getConnection();
	BoardDAO dao = new BoardDAO(conn);
	BoardCommentDAO daoC = new BoardCommentDAO(conn); 
	
	//글 가져오기
	BoardDTO dto = dao.getReadData(num);
	List<BoardCommentDTO> lists = daoC.getCommentData(num);
	
	//가져올 글이 없다면 LIST 화면으로 
	if(dto == null){
		response.sendRedirect("adminQnaList.jsp");
	}
	
	//글 라인 수
	int lineSu = dto.getContent().split("\n").length;
	
	//글 내용의 엔터를 <br>로 변경
	dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
	
	String param = "";
	if(!searchValue.equals("")){
		param = "&searchKey="+searchKey;
		//param += "&searchValue="+URLEncoder.encode(searchValue, "UTF-8");
		param += "&searchValue="+searchValue;
	}
	
	DBConn.close();
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADMIN | 그랜드 조선 호텔</title>
<link rel="stylesheet" href="resources/css/default.css">
<link rel="stylesheet" href="resources/css/admin/adminQnaRead.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/admin/adminQnaRead.js"></script>
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
				<li><a href="adminMember"><i class="fas fa-users-cog"></i> 회원관리</a></li>
				<li><a href="adminReservation"><i class="fas fa-calendar-alt"></i> 예약관리</a></li>
				<li class="on"><a href="adminQnaList"><i class="fas fa-question-circle"></i> 문의글 관리</a></li>
			</ul>
		</div>
		<div class="right">
			<div class="contents">
				<div class="qnaListRead">
					<p class="listIdx">NO. <span>${dto.idx}</span></p>
					<table>
						<tr>
							<th>카테고리</th>
							<td>${dto.categoryName}</td>
							<th>작성자</th>
							<td>${dto.name}</td>
						</tr>
						<tr>
							<th>핸드폰번호</th>
							<td>${dto.phone}</td>
							<th>이메일주소</th>
							<td>${dto.email}</td>
						</tr>
						<tr>
							<th>답변방식</th>
							<td>${dto.reply}</td>
							<th>작성일</th>
							<td>${dto.writeDate}</td>
						</tr>
						<tr class="content">
							<th>내용</th>
							<%pageContext.setAttribute("enter", "\n"); //Enter %>
							<c:set target="${dto}" property="content" value="${fn:replace(dto.content, enter, '<br>')}" />
							<td colspan="3">${dto.content}</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3">
								<c:if test="${fn:contains(dto.fileName, '첨부파일 없음')}">
									${dto.fileName}
								</c:if>
								<c:if test="${not fn:contains(dto.fileName, '첨부파일 없음')}">
									<a href="resources/upload/${dto.fileName}" download>${dto.fileName}</a>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="comment">
			<script>
				function commentOk(){
					var numQna = $(".listIdx span").text();
					$("#qnaNum").val(numQna);
					jQuery("#commentForm").submit();
				}
			</script>
				<form id="commentForm" name="commentForm" method="post" action="commentOk.jsp">
					<input type="hidden" id="qnaNum" name="qnaNum" value="">
					<div class="comCnt">
		                <strong>댓글</strong>
		            </div>
					<textarea rows="2" id="content" name="content" placeholder="댓글을 입력하세요"></textarea>
					<div class="comBtn">
	                	<button type="button" class="btnSC btnL active" onclick="commentOk()">등록</button>
	                </div>
                </form>
			</div>
			<div class="commentList">
				<c:forEach var="comment" items="${list}">
					<p><span>${comment.rnum}</span>${comment.content}</p>
				</c:forEach>
			</div>
			<div class="btnPrev">
				<a href="#none" class="btnSC btnL" onclick="javascript:location.href='adminQnaList';">목록</a>
			</div>
		</div>
	</div>
	<!-- End. container -->
</div>
<!-- 전체 감싸는 .wrapper -->
</body>
</html>