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
	<form method="get" action="getApplicantsByRecruiterId">
		<table>
			<tr>
				<td>Enter the Recruiter ID </td>
				<td><input type="number" name="recruiterId" required></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>