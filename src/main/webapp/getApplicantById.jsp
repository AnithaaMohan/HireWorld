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
	color: #80CBC4 ;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="getApplicantById" method="post">

		<h1>Enter the id</h1>
		Id: <input type="number" name="applicantId"> <input
			type="submit" value="Display">
	</form>
<%-- 	  <%=session.getAttribute("applicantDTO") %>   --%>
	 <%
	ApplicantDTO applicantDTO = (ApplicantDTO) request.getAttribute("applicantDTO");
	%> 
	<%
	if (null != applicantDTO) {
	%>
	<table>
	<tr>
		<td>Id :</td>
		<td><%=applicantDTO.getId()%></td>
	</tr>

	<tr>
		<td>Name :</td>
		<td><%=applicantDTO.getName()%></td>
	</tr>

	<tr>
		<td>EmailAddress :</td>
		<td><%=applicantDTO.getEmailAddress()%></td>
	</tr>

	<tr>
		<td>MobileNumber :</td>
		<td><%=applicantDTO.getMobileNumber()%></td>
	</tr>

	<tr>
		<td>Qualification :</td>
		<td><%=applicantDTO.getQualification()%></td>
	</tr>

	<tr>
		<td>Date Of Birth :</td>
		<td><%=applicantDTO.getDateOfBirth()%></td>
	</tr>

	<tr>
		<td>Delete Status :</td>
		<td><%=applicantDTO.isDeleted()%></td>
	</tr>

	<%
	List<RecruiterDTO> recruitersDTO = applicantDTO.getRecruiters();
	%>

	<tr>
		<td>Recruiters :</td>
		<%
		if (null != recruitersDTO && !recruitersDTO.isEmpty()) {
		%>
		<td><%=recruitersDTO.stream().map(recruiterDTO -> recruiterDTO.getName()).collect(Collectors.joining(","))%></td>
		<%
		} else {
		%>
		<td>Recruiters not yet assigned</td>
	</tr>
	<%
	}
	}
	%>
	
	<%-- else {
	%>
	<td> id not found</td>
	<% } % >--%>
	</table> 
</body>
</html>