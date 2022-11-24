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
<title>Welcome Recruiter! </title>
</head>
<body>
<a href = "createRecruiter"> 
<input type ="button" value = "Create Recruiter">
</a><br><br>
<a href = "displayRecruiters"> 
<input type ="button" value = "Display All Recruiters">
</a><br><br>
<a href = "getRecruiterById.jsp"> 
<input type ="button" value = "Display Recruiter By ID">
</a><br><br>
<a href = "updateRecruiter.jsp"> 
<input type ="button" value = "Update Recruiter">
</a><br><br>
<a href = "removeRecruiter.jsp"> 
<input type ="button" value = "Remove Recruiter">
</a><br><br>

<a href = "assignApplicant.jsp"> 
<input type ="button" value = "assignApplicant">
</a><br><br>
<a href = "unAssignApplicant.jsp"> 
<input type ="button" value = "unAssignApplicant">
</a><br><br>

<a href = "getRecruitersByApplicantId.jsp"> 
<input type ="button" value = "List Recruiters by ApplicantID">
</a><br><br>

</body>
</html>