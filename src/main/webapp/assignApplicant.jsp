<%@page import="com.ideas2it.model.ApplicantDTO"%>
<%@page import="com.ideas2it.model.RecruiterDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<style>
body {
	background-color: #884EA0
}

body {
	color: #ECF0F1;
}

h1 {
	color: #80CBC4;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="assignApplicant" method="post">
		<table>
			<tr>
				<td>Enter the ApplicantId you want to Assign</td>
				<td><input type="number" name="applicantId"></td>
			</tr>

			<tr>
				<td>Enter the RecruiterId you want to Assign</td>
				<td><input type="number" name="recruiterId"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Assign"></td>
			</tr>
		</table>
	</form>
	
	<% if(null != request.getAttribute("status")) {%>
	<h2><%= request.getAttribute("status") %></h2>
	<% } %>
</body>
</html>