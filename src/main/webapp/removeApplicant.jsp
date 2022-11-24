<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
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
<title>Delete Applicant by Id</title>
</head>
<body>
    <h1>Delete Applicant</h1>
    <form action="removeApplicant" method="post">
        <table>
            <tr>
                <td>Enter the id you want to delete</td>
                <td><input type="number" name="applicantId"></td>
            </tr>

            <tr>
                <td><input type="submit" value="Delete"></td>
            </tr>
                <h3> ${message} </h3>     
            <tr>
                <td><br> <a href="applicantIndex.jsp"><b>Back to home</b></a></td>
            </tr>
        </table>
    </form>
</body>
</html>