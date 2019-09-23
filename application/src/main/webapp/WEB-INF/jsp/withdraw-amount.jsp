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

	<form:form method="POST" action="withdraw-funds.do" modelAttribute="withdrawAmount">
		<table>
			<tr>
				<td><form:label path="userId">User ID</form:label></td>
				<td><form:input path="userId" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="accountNumber">Account Number</form:label></td>
				<td><form:input path="accountNumber" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="amount">Amount</form:label></td>
				<td><form:input path="amount" required="true" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
        </form:form>
<a href="banking-home.html">Go to Home</a>
</body>
</html>