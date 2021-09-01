<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<form id="step1Go" name="step1Go" action="/www/reservation/step1" method="post">
		<input type="hidden" name="startDate" id="startDate" value="${reservationDto.startDate}">
		<input type="hidden" name="endDate" id="endDate" value="${reservationDto.endDate }">
		<input type="hidden" name="adultCnt" id="adultCnt" value="${reservationDto.adultCnt}">
		<input type="hidden" name="childrenCnt" id="childrenCnt" value="${reservationDto.childrenCnt}">
	</form>
	<script type="text/javascript">
		form = document.getElementById("step1Go");
		form.submit();
	</script>
</body>
</html>