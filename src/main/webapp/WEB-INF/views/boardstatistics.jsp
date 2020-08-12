<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/resources/css/boardstatistics.css?ver=<%=System.currentTimeMillis()%>" rel="stylesheet">
<script src="/resources/js/boardstatistics.js?ver=<%=System.currentTimeMillis()%>"></script>
<script src="/resources/js/Chart.min.js"></script>
<div class="container">
	<h1>홈페이지 차트</h1>
	
	<div id="chartselectgroup">
		<button type="button" class="btn btn-primary lastYear" onclick="boardChart(2019, 'board')">2019년도</button>
		<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-bar-chart-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			<rect width="4" height="5" x="1" y="10" rx="1"/>
			<rect width="4" height="9" x="6" y="6" rx="1"/>
			<rect width="4" height="14" x="11" y="1" rx="1"/>
		</svg>	
		<div class="btn-group">
			<button type="button" class="btn btn-warning dropdown-toggle btn-sm"
				id="chartbtn" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">
				board
			</button>
			<div class="dropdown-menu">
				<button class="dropdown-item" 
					onclick="boardChart(2020, this.textContent)">board</button>
				<button class="dropdown-item"
					onclick="boardChart(2020, this.textContent)">account</button>
				<button class="dropdown-item"
					onclick="boardChart(2020, this.textContent)">comment</button>
			</div>
		</div>
		<button type="button" class="btn btn-primary nextYear" onclick="boardChart(2021, 'board')">2021년도</button>
	</div>
	<canvas id="myChart" width="400" height="400"></canvas>
	<script
		src="/resources/js/boardchart.js?ver=<%=System.currentTimeMillis()%>"></script>
</div>
