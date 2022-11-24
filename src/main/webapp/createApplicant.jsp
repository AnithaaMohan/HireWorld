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
	<form:form method="post" action="insertApplicant" modelAttribute = "applicantDTO">
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
			<!-- <tr>
				<td>Name</td>
				<td><input type="text" name="name" pattern="[^\\s][a-zA-Z\\s]*"
					title=" Please Enter Alphanumeric and Space Only"
					required></td>
			</tr> -->
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
				<td><label for="birthDate">Date of Birth</label></td>
				<td><input name="birthDate" id="birthDate" type="date"
					value="${applicantDTO.getDateOfBirth()}" /></td>
			</tr>
			<tr>
				<td><form:label path="gender">Gender </form:label></td>
				<td><form:radiobutton path="gender" value="M" label="male" />
				<form:radiobutton path="gender" value="F" label="female" />
				<form:radiobutton path="gender" value="O" label="others" />
				</td>
				</tr>
				
				<!-- <input type="radio" name="gender" value="M" id = "male"><label for = "male">MALE</label>
				<input type="radio" name="gender" value="F" id = "female"><label for = "female">FEMALE</label>
				<input type="radio" name="gender" value="O" id = "others"><label for = "others">OTHERS</label></td> -->
			
			
			<tr>
			    <td><form:label path="qualification">Qualification</form:label></td>
				<td><form:select path="qualification">
						<form:option value="BE"    label="BE"/>
						<form:option value="BTech" label="BTech"/>
						<form:option value="BCA"   label="BCA"/>
						<form:option value="BSC"   label="BSC"/>
						<form:option value="ME"    label="ME"/>
						<form:option value="MTech" label="MTech"/>
						<form:option value="MCA"   label="MCA"/>
						<form:option value="MSC"   label="MSC"/>
						<form:option value="OTHERS" label="OTHERS"/>
				</form:select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Insert"></td>
			</tr>
		</table>
	</form:form>
</body>
<% if(null != request.getAttribute("applicantDTO")) { %>
    <h1> ${message} </h1>
<% } %>