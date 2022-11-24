<%@page import="com.ideas2it.model.ApplicantDTO"%>
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
	color: #ECF0F1 ;
}

h1 {
	color: #80CBC4;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="updateApplicant">
		Applicant Id: <input type="text" name="applicantId"> <input
			type="submit" value="submit">
	</form>
</body>
</html>