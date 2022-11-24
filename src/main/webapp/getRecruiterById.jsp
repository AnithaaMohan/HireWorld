<%@page import="java.util.stream.Collectors"%>
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
	<form action="getRecruiterById" method="post">
		
				<h1>Enter the id </h1>
				Id: <input type="number" name="recruiterId"><input
                         type="submit" value="Display">
			
			</form>
			<%
			RecruiterDTO recruiterDTO  = (RecruiterDTO ) request.getAttribute("recruiterDTO");
			%>
			<%
			if (null != recruiterDTO ) {
			%>
			<table>
			<tr>
				<td>Id :</td>
				<td><%=recruiterDTO.getId()%></td>
			</tr>

			<tr>
				<td>Name :</td>
				<td><%=recruiterDTO.getName()%></td>
			</tr>

			<tr>
				<td>EmailAddress :</td>
				<td><%=recruiterDTO.getEmailAddress()%></td>
			</tr>
			
			<tr>
				<td>MobileNumber :</td>
				<td><%=recruiterDTO.getMobileNumber()%></td>
			</tr>

			<tr>
				<td>Delete Status :</td>
				<td><%=recruiterDTO.isDeleted()%></td>
			</tr>

			<%
			List<ApplicantDTO> applicantsDTO = recruiterDTO.getApplicants();
			%>
			
			<tr>
				<td>Applicants :</td>
				<%
				if (null != applicantsDTO && !applicantsDTO.isEmpty()) {
				%>
				<td><%=applicantsDTO.stream().map(applicantDTO-> applicantDTO.getName()).collect(Collectors.joining(","))%></td>
				<%
				} else {
				%>
				<td>Applicant not yet assigned</td>
			</tr>
			<%
			}
			}
			%>
		</table>
</body>
</html>