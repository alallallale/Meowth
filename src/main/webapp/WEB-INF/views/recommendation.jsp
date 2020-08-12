<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="/resources/js/recommendation.js?ver=<%=System.currentTimeMillis()%>"></script>

<%
	List<HashMap<String, String>> recommendationList = (List<HashMap<String, String>>) request
			.getAttribute("recommendationList");
	String situation = (String) request.getAttribute("situation");
	%>
<link rel="stylesheet"
	href="/resources/css/recommendation.css?ver=<%=System.currentTimeMillis()%>">
<div class='recommendation'>

	<span class="recommendationCount" id="recommendationCount">

<%=recommendationList.get(0).values().toArray()[1]%></span>

	<button type="button" class="btn btn-success btn-sm"
		onclick="insertrecommendation('YES')" id="recommendationbtn">
		<img src="/resources/img/plus.svg" alt="" width="16" height="16"
			title="Bootstrap"> 좋아?
	</button>
	<button type="button" class="btn btn-danger btn-sm" value="NO"
		onclick="insertrecommendation('NO')" id="recommendationbtn">
		<img src="/resources/img/dash.svg" alt="" width="16" height="16"
			title="Bootstrap"> 싫어?
	</button>
	<span class="decommendationCount" id="decommendationCount">
<%=recommendationList.get(1).values().toArray()[1]%></span>

</div>
