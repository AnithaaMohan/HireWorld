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
     <%
	List<RecruiterDTO> recruitersDTO = (List<RecruiterDTO>) request.getAttribute("recruitersDTO");
	System.out.println(recruitersDTO);
	if (null != recruitersDTO) {
		System.out.println(recruitersDTO);
		if (!recruitersDTO.isEmpty()) {
	%>


      <%-- <%= session.getAttribute("text") %>
      <%= session.getAttribute("Recruiters") %>
    <% if(null != session.getAttribute("text")) {%>
       <h2><%= session.getAttribute("text") %></h2>
	<% } else {
	List<RecruiterDTO> recruitersDTO = (List<RecruiterDTO>) session.getAttribute("Recruiters");
	if (null != recruitersDTO) {
	%> --%>
	<%
	for (RecruiterDTO recruiterDTO : recruitersDTO) {
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
		<td>ApplicantsDTO :</td>
		<%
		if (null != applicantsDTO && !applicantsDTO.isEmpty()) {
		%>

		<td><%=applicantsDTO.stream().map(applicantDTO -> applicantDTO.getName()).collect(Collectors.joining(","))%></td>
		<%
		} else {
		%>
		<td>applicantDTO not yet assigned</td>
	</tr>
	<%
	}
	%>
	<tr>
		<td>---------------------</td>
	</tr>
	<%
	}
	}
	}
	%>
	</table>
	<a href="recruiterIndex.jsp">Back</a>
</body>
</html>