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
<title>Welcome Applicant!</title>
</head>
<body>

<a href = "createApplicant"> 
<input type ="button" value = "Create Applicant">
</a><br><br>
<a href = "displayApplicants"> 
<input type ="button" value = "Display All Applicants">
</a><br><br>
<a href = "getApplicantById.jsp"> 
<input type ="button" value = "Display Applicant By Id">
</a><br><br>
<a href = "updateApplicant.jsp"> 
<input type ="button" value = "Update Applicant">
</a><br><br>
<a href = "removeApplicant.jsp"> 
<input type ="button" value = "Remove Applicant">
</a><br><br>
<a href = "getApplicantsByRecruiterId.jsp"> 
<input type ="button" value = "List Applicants by RecruiterID">
</a><br><br>

</body>
</html>