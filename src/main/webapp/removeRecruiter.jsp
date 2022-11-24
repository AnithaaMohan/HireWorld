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
<title>Delete Recruiter by Id</title>
</head>
<body>
	<form action="removeRecruiter" method="post">
		<table>
			<tr>
				<td>Enter the Recruiter id you want to delete</td>
				<td><input type="number" name="recruiterId"></td>
			</tr>

			<tr>
                <td><input type="submit" value="Delete"></td>
            </tr>
                <h3> ${message} </h3>     
            <tr>
                <td><br> <a href="recruiterIndex.jsp"><b>Back to home</b></a></td>
            </tr>
		</table>
	</form>
</body>
</html>