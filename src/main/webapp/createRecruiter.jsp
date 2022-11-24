<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
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
	<form:form method="post" action="insertRecruiter" modelAttribute = "recruiterDTO">
		<table>
		    <tr>
				<td><form:hidden path="id" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input type="text" path="name"
						placeholder="Enter the name"
						pattern="[^\\s][a-zA-Z\\s]*"
						title=" Please Enter Alphabets Only"></form:input></td>
			</tr>
			<tr>
				<td>MobileNumber</td>
				<td><form:input type="text" path="mobileNumber" pattern="[6-9][0-9]{9}"
					title=" Please Enter Valid Number"
					></form:input></td>
			</tr>
			<tr>
				<td>EmailAddress</td>
				<td><form:input type="text" path="emailAddress" pattern="[a-zA-Z0-9.]+@[a-z]+[.][a-z]{2,3}$"
					title=" Example : name@gmail.com" ></form:input></td>
			</tr>	
			<tr>
				<td><input type="submit" value="Insert"></td>
			</tr>
		</table>
	</form:form>
</body>
<% if(null != request.getAttribute("recruiterDTO")) { %>
    <h1> ${message} </h1>
<% } %>


