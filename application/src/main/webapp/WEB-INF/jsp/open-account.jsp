<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ page isELIgnored="false" %>
<title>Banking App</title>
</head>
<body>

	<form:form method="POST" action="create-account.do" modelAttribute="accountOpen">
		<table>
			<tr>
				<td><form:label path="userId">Existing User ID</form:label></td>
				<td><form:input path="userId" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" type="email" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="mobile">Contact Number</form:label></td>
				<td><form:input path="mobile" required="true" /></td>
			</tr>
			<tr>
				<td><form:select path="accountType" items="${accountTypes}" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
        </form:form>
<a href="banking-home.html">Go to Home</a>
</body>
</html>