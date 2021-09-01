<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ADMIN | 그랜드 조선 호텔</title>
<link rel="stylesheet" href="resources/css/default.css">
<link rel="stylesheet" href="resources/css/admin/adminDashboard.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
<script>
	$(function(){
		//일별 방문자수
		var visitChartLabels = []; // 받아올 데이터를 저장할 배열 선언
		var visitChartData = [];
		//일별 방문자수 차트 생성		
		function createVisitChart() {
	        var ctx = document.getElementById("visitChart").getContext("2d");
	        var visitChart = new Chart(ctx, {
	        	type: 'line',
	            data: visitChartData,
	            options:{
	            	legend: {
	    				labels: {
	    					fontColor: "#000",
	    					fontSize: 16,
	    				}
	    			},
	            	scales:{
	            		yAxes:[{
	            			ticks:{
	            				fontSize : 14,
	            			},
	            			gridLines:{
	    						color: 'rgba(238, 238, 238, 1)',
	    						lineWidth:1
	    					}
	            		}],
	            		xAxes:[{
	            			ticks:{
	            				fontSize : 14,
	            			},
	            			gridLines:{
	    						color: 'rgba(238, 238, 238, 1)',
	    						lineWidth:1
	    					}
	            		}]
	            	}
	            }
	        })
	    }
		//일별 방문자수 ㅓson 받아오기
		$.getJSON("dashboard/getVisitData", function(data) {
            $.each(data, function(key, value) {
            	visitChartLabels.push(value.v_date.substring(0,10));
            	visitChartData.push(value.v_count);
            });
            visitChartData = {
               	labels : visitChartLabels,
               	datasets : [{
           			label : "일별 방문자 수",
               		borderColor: 'rgb(57, 155, 204)',
               		backgroundColor : 'rgb(57, 155, 204)',
                    fill: false,
                    borderWidth: 3,
                    lineTension : 0,
                    data : visitChartData
                }]
            }
            createVisitChart();
        });
		
		//월별 매출현황
		var salesChartLabels = []; // 받아올 데이터를 저장할 배열 선언
		var salesChartData = [];
		
		//월별 매출현황 차트 생성
		function createStatusChart(){
			var ctx = document.getElementById("salesChart").getContext("2d");
			var salesChart = new Chart(ctx, {
	        	type: 'line',
	            data: salesChartData,
	            options:{
	            	legend: {
	    				labels: {
	    					fontColor: "#000",
	    					fontSize: 16,
	    				}
	    			},
	    			tooltips: { 
	    		    	mode: 'label', 
	    		        label: 'mylabel', 
	    		        callbacks: { 
	    		        	label: function(tooltipItem, data) { 
	    		            	return tooltipItem.yLabel.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); 
	    		            },
	    		        }, 
	    		    }, 
	            	scales:{
	            		yAxes:[{
	            			ticks:{
	            				fontSize : 14,
	                            userCallback: function(value, index, values) {
	                               value = value.toString();
	                               value = value.split(/(?=(?:...)*$)/);
	                               value = value.join(',');
	                               return value;
	                            }
	            			},
	            			gridLines:{
	    						color: 'rgba(238, 238, 238, 1)',
	    						lineWidth:1
	    					}
	            		}],
	            		xAxes:[{
	            			ticks:{
	            				fontSize : 14,
	            			},
	            			gridLines:{
	    						color: 'rgba(238, 238, 238, 1)',
	    						lineWidth:1
	    					}
	            		}]
	            	},
	            }
	        })
		}
		
		//월별 매출현황 json 받아오기
		$.getJSON("dashboard/getSalesChartData", function(data) {
            $.each(data, function(key, value) {
            	console.log(data)
            	salesChartLabels.push(value.month+"월");
            	salesChartData.push(value.totalpay);
            });
            salesChartData = {
               	labels : salesChartLabels,
               	datasets : [{
           			label : "월별 매출 현황",
               		borderColor: 'rgb(232, 65, 122)',
               		backgroundColor : 'rgb(232, 65, 122)',
                    fill: false,
                    borderWidth: 3,
                    lineTension : 0,
                    data : salesChartData
                }]
                
            }
            createStatusChart();
        });
		
		//매출현황
		$.ajax({
			type : 'POST',
			url : "dashboard/getSalesData",
			datatype : 'JSON',
			contentType : 'application/json; charset=utf-8',
			success : function(data){
				var monthCount = data.totalpayMonth;
				var yearCount = data.totalpayYear;
				if(monthCount != 0){
					$({ val : 0 }).animate({ val : monthCount }, {
						duration: 500,
						step: function() {
							var num = numberWithCommas(Math.floor(this.val));
						    $(".monthPay").text(num);
						},
						complete: function() {
						    var num = numberWithCommas(Math.floor(this.val));
						    $(".monthPay").text(num);
						}
					});
				}else{
					$(".monthPay").text(monthCount);
				}
				if(yearCount != 0){
					$({ val : 0 }).animate({ val : yearCount }, {
						duration: 500,
						step: function() {
							var num = numberWithCommas(Math.floor(this.val));
						    $(".yearPay").text(num);
						},
						complete: function() {
						    var num = numberWithCommas(Math.floor(this.val));
						    $(".yearPay").text(num);
						}
					});
				}else{
					$(".yearPay").text(yearCount);
				}
				function numberWithCommas(x) {
					return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				}
			}
		});
		
		//전체 회원수
		$.ajax({
			type : 'POST',
			url : "dashboard/getMemberData",
			datatype : 'JSON',
			contentType : 'application/json; charset=utf-8',
			success : function(data){
				var memberCount = data.result;
				if(memberCount != 0){
					$({ val : 0 }).animate({ val : memberCount }, {
						duration: 500,
						step: function() {
							var num1 = numberWithCommas(Math.floor(this.val));
						   	$(".memberCount").text(num1);
						},
						complete: function() {
						    var num1 = numberWithCommas(Math.floor(this.val));
							$(".memberCount").text(num1);
						}
					});
				}
				function numberWithCommas(x) {
					return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				}
			}
		});
	});
	
	
</script>
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
				<li class="on"><a href="admin"><i class="fas fa-home"></i> 대시보드</a></li>
				<li><a href="adminMember"><i class="fas fa-users-cog"></i> 회원관리</a></li>
				<li><a href="adminReservation"><i class="fas fa-calendar-alt"></i> 예약관리</a></li>
				<li><a href="adminQnaList"><i class="fas fa-question-circle"></i> 문의글 관리</a></li>
			</ul>
		</div>
		<div class="right">
			<div class="contents">
				<div class="first">
					<div class="salesStatusMonth box">
						<p>이번달 매출현황</p>
						<p class="monthPay"></p>
					</div>
					<div class="salesStatusYear box">
						<p>이번년도 매출현황</p>
						<p class="yearPay"></p>
					</div>
					<div class="allMemberCount box">
						<p>전체 회원수</p>
						<p class="memberCount"></p>
					</div>
				</div>
				<div class="sec">
					<div class="visitChartWarp box">
			            <canvas id="visitChart" style="width:100%;height:100%;"></canvas>
					</div>
					<div class="salesChartWarp box">
				    	<canvas id="salesChart" style="width:100%; height:100%;"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End. container -->
</div>
<!-- 전체 감싸는 .wrapper -->
</body>
</html>